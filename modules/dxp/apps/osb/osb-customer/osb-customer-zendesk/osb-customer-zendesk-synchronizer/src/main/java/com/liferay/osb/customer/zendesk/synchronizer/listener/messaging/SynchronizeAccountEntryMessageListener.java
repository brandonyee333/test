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

import com.liferay.osb.customer.zendesk.synchronizer.constants.ZendeskDestinationNames;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_ENTRY_SYNC,
	service = MessageListener.class
)
public class SynchronizeAccountEntryMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
	}

	/*@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				ZendeskDestinationNames.ACCOUNT_ENTRY_SYNC);

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
		long accountEntryId = (Long)message.get("accountEntryId");

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			accountEntryId);

		if ((!hasZendeskOrganization(accountEntry) &&
			 !accountEntry.isActiveSupport()) ||
			(accountEntry.getAccountEntryId() ==
				OSBCustomerConstants.ACCOUNT_ENTRY_LRDCOM_ID)) {

			_accountEntryLocalService.updateLastZendeskAuditDate(
				OSBCustomerConstants.USER_DEFAULT_USER_ID, accountEntryId,
				StringPool.BLANK, StringPool.BLANK);

			return;
		}

		try {
			synchronize(accountEntry);
		}
		catch (Exception e) {
			_log.error(e, e);

			_accountEntryLocalService.updateLastZendeskAuditDate(
				OSBCustomerConstants.USER_DEFAULT_USER_ID, accountEntryId,
				StringPool.BLANK, StringPool.BLANK);
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

	protected void synchronize(AccountEntry accountEntry)
		throws PortalException {

		_accountEntrySynchronizer.update(accountEntry);

		TODO
		Map<Long, AccountCustomer> accountCustomerMap = new HashMap<>();

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			accountCustomerMap.put(
				accountCustomer.getUserId(), accountCustomer);
		}

		Map<Long, PartnerWorker> partnerWorkerMap = new HashMap<>();

		if (accountEntry.isPartnerManagedSupport()) {
			PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

			for (PartnerWorker partnerWorker :
					partnerEntry.getPartnerWorkers()) {

				partnerWorkerMap.put(partnerWorker.getUserId(), partnerWorker);
			}
		}

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		List<ZendeskUser> zendeskUsers = getZendeskUsers(zendeskOrganizationId);

		for (ZendeskUser zendeskUser : zendeskUsers) {
			User user = _userLocalService.fetchUserByUuidAndCompanyId(
				zendeskUser.getExternalId(), accountEntry.getCompanyId());

			if (user == null) {
				continue;
			}

			AccountCustomer accountCustomer = accountCustomerMap.remove(
				user.getUserId());
			PartnerWorker partnerWorker = partnerWorkerMap.remove(
				user.getUserId());

			if ((accountCustomer == null) && (partnerWorker == null)) {
				_accountEntrySynchronizer.reassignTickets(
					user.getUserId(), accountEntry.getAccountEntryId(),
					zendeskOrganizationId, zendeskUser.getZendeskUserId());

				_asyncZendeskOrganizationMembershipWebService.
					deleteOrganizationMemberships(
						zendeskUser.getZendeskUserId(),
						new long[] {zendeskOrganizationId});
			}
			else {
				_userSynchronizer.sync(zendeskUser, user);
			}
		}

		for (AccountCustomer accountCustomer : accountCustomerMap.values()) {
			_accountCustomerSynchronizer.add(accountCustomer);

			_accountCustomerSynchronizer.addOrganizationSubscription(
				accountCustomer);
		}

		for (PartnerWorker partnerWorker : partnerWorkerMap.values()) {
			_partnerWorkerSynchronizer.add(partnerWorker);
		}

		_accountEntryLocalService.updateLastZendeskAuditDate(
			OSBCustomerConstants.USER_DEFAULT_USER_ID,
			accountEntry.getAccountEntryId(), "Synced project to Zendesk.",
			StringPool.BLANK);

	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizeAccountEntryMessageListener.class);

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountEntrySynchronizer;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_asyncZendeskOrganizationMembershipWebService;

	private volatile BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	private DestinationFactory _destinationFactory;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference
	private PartnerWorkerSynchronizer _partnerWorkerSynchronizer;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskOrganizationMembershipWebService
		_zendeskOrganizationMembershipWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;*/

}