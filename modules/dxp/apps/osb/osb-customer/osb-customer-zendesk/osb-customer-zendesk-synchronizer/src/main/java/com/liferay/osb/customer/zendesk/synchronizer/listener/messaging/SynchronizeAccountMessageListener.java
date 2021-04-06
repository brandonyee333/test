/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.zendesk.synchronizer.listener.messaging;

import com.liferay.osb.customer.admin.constants.EntitlementConstants;
import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.CustomerSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.TeamSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_SYNC,
	service = MessageListener.class
)
public class SynchronizeAccountMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				ZendeskDestinationNames.ACCOUNT_SYNC);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> dictionary = new HashMapDictionary<>();

		dictionary.put("destination.name", destination.getName());

		_serviceRegistration = _bundleContext.registerService(
			Destination.class, destination, dictionary);
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			Destination destination = _bundleContext.getService(
				_serviceRegistration.getReference());

			_serviceRegistration.unregister();

			destination.destroy();
		}

		_bundleContext = null;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String koroneikiAccountKey = (String)message.get("koroneikiAccountKey");

		Account account = _accountWebService.fetchAccount(koroneikiAccountKey);

		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				koroneikiAccountKey);

		if (account == null) {
			if ((accountEntry != null) &&
				!hasZendeskOrganization(accountEntry) &&
				!accountEntry.isActiveSupport()) {

				_accountEntryLocalService.updateLastZendeskAuditDate(
					OSBCustomerConstants.USER_DEFAULT_USER_ID,
					accountEntry.getAccountEntryId(), StringPool.BLANK,
					StringPool.BLANK);
			}

			return;
		}

		try {
			List<Contact> contacts =
				_contactWebService.getAccountCustomerContacts(
					account.getKey(), 1, 1000);

			if (accountEntry != null) {
				List<ProductPurchase> productPurchases =
					_accountReader.getProductPurchases(koroneikiAccountKey);

				if (_accountReader.isSyncAccount(productPurchases)) {
					synchronizeCustomers(account, contacts, accountEntry);
				}
			}

			synchronizePartners(account, contacts);
		}
		catch (Exception e) {
			_log.error(e, e);

			if (accountEntry != null) {
				_accountEntryLocalService.updateLastZendeskAuditDate(
					OSBCustomerConstants.USER_DEFAULT_USER_ID,
					accountEntry.getAccountEntryId(), StringPool.BLANK,
					StringPool.BLANK);
			}
		}
	}

	protected List<ZendeskUser> getZendeskUsers(long zendeskOrganizationId)
		throws PortalException {

		List<ZendeskOrganizationMembership> zendeskOrganizationMemberships =
			_zendeskOrganizationMembershipWebService.getOrganizationMemberships(
				zendeskOrganizationId);

		long[] zendeskUserIds = new long[zendeskOrganizationMemberships.size()];

		for (int i = 0; i < zendeskOrganizationMemberships.size(); i++) {
			ZendeskOrganizationMembership zendeskOrganizationMembership =
				zendeskOrganizationMemberships.get(i);

			zendeskUserIds[i] =
				zendeskOrganizationMembership.getZendeskUserId();
		}

		return _zendeskUserWebService.getZendeskUsers(zendeskUserIds);
	}

	protected boolean hasZendeskOrganization(AccountEntry accountEntry)
		throws PortalException {

		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		boolean externalIdMappers =
			_externalIdMapperLocalService.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers) {
			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setDestinationFactory(
		DestinationFactory destinationFactory) {

		_destinationFactory = destinationFactory;
	}

	protected void synchronizeCustomers(
			Account account, List<Contact> contacts, AccountEntry accountEntry)
		throws Exception {

		Map<String, User> customerUsersMap = new HashMap<>();

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			ContactRole[] contactRoles = contact.getContactRoles();

			for (ContactRole contactRole : contactRoles) {
				if (ArrayUtil.contains(
						ContactRoleConstants.SUPPORT_CONTACT_ROLES,
						contactRole.getName())) {

					customerUsersMap.put(user.getUuid(), user);

					break;
				}
			}
		}

		Map<String, User> firstLineSupportUsersMap = new HashMap<>();

		Team firstLineSupportTeam = _accountReader.getFirstLineSupportTeam(
			account);

		if (firstLineSupportTeam != null) {
			List<Contact> teamContacts = _contactWebService.getTeamContacts(
				firstLineSupportTeam.getKey(), 1, 1000);

			for (Contact contact : teamContacts) {
				User user = _userIdentityProvider.fetchUserByEmailAddress(
					contact.getEmailAddress());

				if (user == null) {
					continue;
				}

				firstLineSupportUsersMap.put(user.getUuid(), user);
			}
		}

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		List<ZendeskUser> zendeskUsers = getZendeskUsers(zendeskOrganizationId);

		for (ZendeskUser zendeskUser : zendeskUsers) {
			User customerUser = customerUsersMap.remove(
				zendeskUser.getExternalId());
			User firstLineSupportUser = firstLineSupportUsersMap.remove(
				zendeskUser.getExternalId());

			User user = _userIdentityProvider.fetchUserByEmailAddress(
				zendeskUser.getEmail());

			if (user == null) {
				continue;
			}

			if ((customerUser == null) && (firstLineSupportUser == null)) {
				_accountSynchronizer.reassignTickets(
					account.getKey(), accountEntry, user);

				_asyncZendeskOrganizationMembershipWebService.
					deleteOrganizationMemberships(
						zendeskUser.getZendeskUserId(),
						new long[] {zendeskOrganizationId});
			}
			else {
				_userSynchronizer.sync(user, zendeskUser);
			}
		}

		for (User user : customerUsersMap.values()) {
			_customerSynchronizer.add(user, account, accountEntry);
		}

		for (User user : firstLineSupportUsersMap.values()) {
			_teamSynchronizer.add(firstLineSupportTeam, user);
		}

		_accountEntryLocalService.updateLastZendeskAuditDate(
			OSBCustomerConstants.USER_DEFAULT_USER_ID,
			accountEntry.getAccountEntryId(), "Synced project to Zendesk.",
			StringPool.BLANK);
	}

	protected void synchronizePartners(Account account, List<Contact> contacts)
		throws Exception {

		boolean partner = false;

		Entitlement[] entitlements = account.getEntitlements();

		if (entitlements != null) {
			for (Entitlement entitlement : entitlements) {
				String name = entitlement.getName();

				if (name.equals(EntitlementConstants.NAME_PARTNER)) {
					partner = true;
				}
			}
		}

		if (!partner) {
			return;
		}

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			ContactRole[] contactRoles = contact.getContactRoles();

			for (ContactRole contactRole : contactRoles) {
				if (ArrayUtil.contains(
						ContactRoleConstants.PARTNER_CONTACT_ROLES,
						contactRole.getName())) {

					_userSynchronizer.update(user, null);

					break;
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizeAccountMessageListener.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountWebService _accountWebService;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_asyncZendeskOrganizationMembershipWebService;

	private volatile BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CustomerSynchronizer _customerSynchronizer;

	private DestinationFactory _destinationFactory;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference
	private TeamSynchronizer _teamSynchronizer;

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskOrganizationMembershipWebService
		_zendeskOrganizationMembershipWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}