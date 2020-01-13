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

import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.zendesk.synchronizer.constants.ZendeskDestinationNames;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

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
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				"liferay/zendesk_account_sync");

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
		Account account = AccountSerDes.toDTO((String)message.getPayload());

		String topic = message.getString("topic");

		if (topic.equals("koroeniki.account.create")) {
			onCreate(account);
		}
		else if (topic.equals("koroneiki.account.delete")) {
			onDelete(account);
		}
		else {
			onUpdate(account);
		}
	}

	protected void onCreate(Account account) {

		// TODO

	}

	protected void onDelete(Account account) {

		// TODO

	}

	protected void onUpdate(Account account) {

		// TODO

	}

	/*	TODO
	 *
	 * @Override
		public void onAfterCreate(AccountEntry accountEntry)
			throws ModelListenerException {
			try {
				if (!accountEntry.isActiveSupport()) {
					return;
				}

				_accountEntrySynchronizer.update(accountEntry);
			}
			catch (Exception e) {
				_log.error(e, e);

				throw new ZendeskIntegrationException(e);
			}
		}

		@Override
		public void onAfterUpdate(AccountEntry accountEntry)
			throws ModelListenerException {
			try {
				AccountEntry oldAccountEntry = _oldAccountEntry.get();

				if ((!_zendeskOrganization.get() &&
					 !accountEntry.isActiveSupport()) ||
					(accountEntry.getAccountEntryId() ==
						OSBConstants.ACCOUNT_ENTRY_LRDCOM_ID)) {

					return;
				}

				if (isUpdateAccountEntry(oldAccountEntry, accountEntry)) {
					_accountEntrySynchronizer.update(accountEntry);
				}

				if (_zendeskOrganization.get() &&
					((oldAccountEntry.isPartnerManagedSupport() &&
					  !accountEntry.isPartnerManagedSupport()) ||
					 (oldAccountEntry.getPartnerEntryId() !=
						 accountEntry.getPartnerEntryId()))) {

					_accountEntrySynchronizer.removePartnerManagedSupport(
						oldAccountEntry);
				}

				if (oldAccountEntry.isActiveTicketSupport() &&
					!accountEntry.isActiveTicketSupport()) {

					_accountEntrySynchronizer.closeZendeskTickets(accountEntry);
					_accountEntrySynchronizer.updateTags(accountEntry);
				}

				if (oldAccountEntry.isActiveSupport() &&
					!accountEntry.isActiveSupport()) {

					_accountEntrySynchronizer.removeAccountCustomers(accountEntry);
				}

				if (accountEntry.isActiveSupport()) {
					if ((oldAccountEntry.isActiveTicketSupport() !=
							accountEntry.isActiveTicketSupport()) ||
						(oldAccountEntry.isActiveSupport() !=
							accountEntry.isActiveSupport())) {

						_accountEntrySynchronizer.addAccountCustomers(accountEntry);
					}

					if (accountEntry.isPartnerManagedSupport() &&
						(!_zendeskOrganization.get() ||
						 !oldAccountEntry.isPartnerManagedSupport() ||
						 (oldAccountEntry.getPartnerEntryId() !=
							 accountEntry.getPartnerEntryId()))) {

						_accountEntrySynchronizer.addPartnerManagedSupport(
							accountEntry);
					}
				}
			}
			catch (Exception e) {
				_log.error(e, e);

				throw new ZendeskIntegrationException(e);
			}
		}

		@Override
		public void onBeforeRemove(AccountEntry accountEntry)
			throws ModelListenerException {
			try {
				_accountEntrySynchronizer.remove(accountEntry);
			}
			catch (Exception e) {
				_log.error(e, e);

				throw new ZendeskIntegrationException(e);
			}
		}

		@Override
		public void onBeforeUpdate(AccountEntry accountEntry)
			throws ModelListenerException {
			try {
				AccountEntry oldAccountEntry =
					_accountEntryLocalService.getAccountEntry(
						accountEntry.getAccountEntryId());

				_oldAccountEntry.set(oldAccountEntry);
				_zendeskOrganization.set(hasZendeskOrganization(accountEntry));
			}
			catch (Exception e) {
				_log.error(e, e);

				throw new ZendeskIntegrationException(e);
			}
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

		protected boolean isUpdateAccountEntry(
			AccountEntry oldAccountEntry, AccountEntry accountEntry) {

			if (!DateUtil.equals(
					oldAccountEntry.getLastZendeskAuditDate(),
					accountEntry.getLastZendeskAuditDate())) {

				return false;
			}

			if (accountEntry.isActiveSupport()) {
				return true;
			}

			if (oldAccountEntry.isActiveSupport() &&
				!accountEntry.isActiveSupport()) {

				return true;
			}

			String oldAccountEntryCode = oldAccountEntry.getCode();
			String oldAccountEntryName = oldAccountEntry.getName();

			if (!oldAccountEntryCode.equals(accountEntry.getCode()) ||
				!oldAccountEntryName.equals(accountEntry.getName())) {

				return true;
			}

			return false;
		}

		private static final Log _log = LogFactoryUtil.getLog(
			AccountEntryModelListener.class);
		private static final ThreadLocal<AccountEntry> _oldAccountEntry =
			new CentralizedThreadLocal<>(
				AccountEntryModelListener.class + "._oldAccountEntry");
		private static final ThreadLocal<Boolean> _zendeskOrganization =
			new CentralizedThreadLocal<>(
				AccountEntryModelListener.class + "._zendeskOrganization");*/

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	private volatile BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	/*@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;*/

	@Reference
	private JSONFactory _jsonFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}