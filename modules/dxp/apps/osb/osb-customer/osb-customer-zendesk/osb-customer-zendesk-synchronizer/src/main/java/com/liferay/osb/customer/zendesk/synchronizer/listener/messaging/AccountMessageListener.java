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

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.synchronizer.util.AccountUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.service.ClassNameLocalService;
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
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_SYNC,
	service = MessageListener.class
)
public class AccountMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_SYNCHRONOUS,
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
		JSONObject jsonObject = _jsonFactory.createJSONObject(
			(String)message.getPayload());

		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));

		String topic = message.getString("topic");

		if (topic.equals("koroneiki.account.delete")) {
			onDelete(account);
		}
		else {
			onUpdate(account);
		}
	}

	protected void onDelete(Account account) {
		try {
			AccountEntry accountEntry =
				_accountEntryLocalService.fetchKoroneikiAccountEntry(
					account.getKey());

			if (accountEntry == null) {
				return;
			}

			_accountSynchronizer.remove(account, accountEntry);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected void onUpdate(Account account) {
		try {
			AccountEntry accountEntry =
				_accountEntryLocalService.fetchKoroneikiAccountEntry(
					account.getKey());

			if ((accountEntry == null) ||
				(accountEntry.getAccountEntryId() ==
					OSBCustomerConstants.ACCOUNT_ENTRY_LRDCOM_ID)) {

				return;
			}

			if (!_hasZendeskOrganization(accountEntry) &&
				!_accountUtil.hasActiveSupport(account)) {

				return;
			}

			_accountSynchronizer.update(account, accountEntry);

			if (!_accountUtil.hasActiveTicketSupport(account)) {
				_accountSynchronizer.closeZendeskTickets(account, accountEntry);

				List<Contact> contacts = _contactWebService.getAccountContacts(
					account.getKey(), 1, 1000);

				for (Contact contact : contacts) {
					User user = _userIdentityProvider.fetchUserByEmailAddress(
						contact.getEmailAddress());

					if (user == null) {
						continue;
					}

					_userSynchronizer.updateTags(user);
				}
			}

			if (!_accountUtil.hasActiveSupport(account)) {
				_accountSynchronizer.removeCustomers(account, accountEntry);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	private boolean _hasZendeskOrganization(AccountEntry accountEntry)
		throws PortalException {

		boolean externalIdMappers =
			_externalIdMapperLocalService.hasExternalIdMappers(
				_classNameLocalService.getClassNameId(AccountEntry.class),
				accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountMessageListener.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountUtil _accountUtil;

	private volatile BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference(target = "(provider=okta)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

}