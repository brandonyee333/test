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

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.CustomerSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_CONTACT_SYNC,
	service = MessageListener.class
)
public class ContactMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				ZendeskDestinationNames.ACCOUNT_CONTACT_SYNC);

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
		JSONObject jsonObject = _jsonFactory.createJSONObject(
			(String)message.getPayload());

		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));
		Contact contact = ContactSerDes.toDTO(jsonObject.getString("contact"));
		ContactRole contactRole = ContactRoleSerDes.toDTO(
			jsonObject.getString("contactRole"));

		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				account.getKey());

		if ((accountEntry == null) && !isPartner(account)) {
			return;
		}

		User user = _userIdentityProvider.fetchUserByEmailAddress(
			contact.getEmailAddress());

		if (user == null) {
			return;
		}

		String topic = message.getString("topic");

		if (topic.equals("koroneiki.account.contactrole.assigned")) {
			onContactRoleAssign(account, accountEntry, user, contactRole);
		}
		else if (topic.equals("koroneiki.account.contactrole.unassigned")) {
			onContactRoleUnassign(account, accountEntry, user, contactRole);
		}
	}

	protected boolean isPartner(Account account) {
		Entitlement[] entitlements = account.getEntitlements();

		if (entitlements != null) {
			for (Entitlement entitlement : entitlements) {
				String name = entitlement.getName();

				if (name.equals(EntitlementConstants.NAME_PARTNER)) {
					return true;
				}
			}
		}

		return false;
	}

	protected void onContactRoleAssign(
		Account account, AccountEntry accountEntry, User user,
		ContactRole contactRole) {

		try {
			String name = contactRole.getName();

			if (!isPartner(account) && (accountEntry != null) &&
				!accountEntry.isActiveTicketSupport() &&
				!name.equals(
					ContactRoleConstants.NAME_SUPPORT_CLOSED_WATCHER)) {

				return;
			}

			if (ArrayUtil.contains(
					ContactRoleConstants.SUPPORT_CONTACT_ROLES, name) ||
				ArrayUtil.contains(
					ContactRoleConstants.PARTNER_CONTACT_ROLES, name)) {

				_customerSynchronizer.add(user, account, accountEntry);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected void onContactRoleUnassign(
		Account account, AccountEntry accountEntry, User user,
		ContactRole contactRole) {

		try {
			String accountName = account.getName();
			String contactRoleName = contactRole.getName();

			if (accountName.equals("Liferay, Inc.") &&
				contactRoleName.equals(ContactRoleConstants.NAME_MEMBER)) {

				_userSynchronizer.removeAgentRole(user);
			}

			if (!ArrayUtil.contains(
					ContactRoleConstants.SUPPORT_CONTACT_ROLES,
					contactRoleName) &&
				!ArrayUtil.contains(
					ContactRoleConstants.PARTNER_CONTACT_ROLES,
					contactRoleName)) {

				return;
			}

			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountContactRoles(
					account.getKey(), user.getUuid(), 1, 1000);

			boolean flsPartner = false;

			Team firstLineSupportTeam = _accountReader.getFirstLineSupportTeam(
				account);

			if (firstLineSupportTeam != null) {
				List<ContactRole> firstLineSupportContactRoles =
					_contactRoleWebService.getTeamContactRoles(
						firstLineSupportTeam.getKey(), user.getUuid(), 1, 1000);

				if (!firstLineSupportContactRoles.isEmpty()) {
					flsPartner = true;
				}
			}

			if (contactRoles.isEmpty() && !flsPartner) {
				onContactUnassign(account, accountEntry, user);

				return;
			}

			for (ContactRole curContactRole : contactRoles) {
				String name = curContactRole.getName();

				if ((accountEntry != null) &&
					!accountEntry.isActiveTicketSupport() &&
					!name.equals(
						ContactRoleConstants.NAME_SUPPORT_CLOSED_WATCHER)) {

					continue;
				}

				if (ArrayUtil.contains(
						ContactRoleConstants.SUPPORT_CONTACT_ROLES,
						curContactRole.getName()) ||
					ArrayUtil.contains(
						ContactRoleConstants.PARTNER_CONTACT_ROLES,
						curContactRole.getName())) {

					_customerSynchronizer.update(user);

					if (name.equals(ContactRoleConstants.NAME_ADMINISTRATOR) ||
						contactRoleName.equals(
							ContactRoleConstants.NAME_SUPPORT_DEVELOPER)) {

						_accountSynchronizer.reassignTickets(
							account.getKey(), accountEntry, user);
					}

					return;
				}
			}

			onContactUnassign(account, accountEntry, user);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected void onContactUnassign(
		Account account, AccountEntry accountEntry, User user) {

		try {
			if (accountEntry != null) {
				_accountSynchronizer.reassignTickets(
					account.getKey(), accountEntry, user);
			}

			_customerSynchronizer.remove(user, accountEntry);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContactMessageListener.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	private volatile BundleContext _bundleContext;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CustomerSynchronizer _customerSynchronizer;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private JSONFactory _jsonFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}