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

package com.liferay.osb.customer.zendesk.model.listener.internal.messaging;

import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountCustomerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.PartnerWorkerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = "destination.name=liferay/account_entry_zendesk_sync",
	service = MessageListener.class
)
public class AccountEntryZendeskSyncMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		long accountEntryId = (Long)message.get("accountEntryId");

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.fetchAccountEntry(accountEntryId);

		_accountEntrySynchronizer.update(accountEntry);

		Map<Long, AccountCustomer> accountCustomerMap = new HashMap<>();

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			_accountCustomerSynchronizer.add(accountCustomer);

			_accountCustomerSynchronizer.addOrganizationSubscription(
				accountCustomer);

			accountCustomerMap.put(
				accountCustomer.getUserId(), accountCustomer);
		}

		Map<Long, PartnerWorker> partnerWorkerMap = new HashMap<>();

		if (accountEntry.isPartnerManagedSupport()) {
			PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

			for (PartnerWorker partnerWorker :
					partnerEntry.getPartnerWorkers()) {

				_partnerWorkerSynchronizer.add(partnerWorker);

				partnerWorkerMap.put(partnerWorker.getUserId(), partnerWorker);
			}
		}

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);

		List<ZendeskOrganizationMembership> zendeskOrganizationMemberships =
			_zendeskOrganizationMembershipWebService.getOrganizationMemberships(
				zendeskOrganizationId);

		for (ZendeskOrganizationMembership zendeskOrganizationMembership :
				zendeskOrganizationMemberships) {

			long zendeskUserId =
				zendeskOrganizationMembership.getZendeskUserId();

			ZendeskUser zendeskUser = _zendeskUserWebService.getZendeskUser(
				zendeskUserId);

			User user = _userLocalService.fetchUserByUuidAndCompanyId(
				zendeskUser.getExternalId(), accountEntry.getCompanyId());

			AccountCustomer accountCustomer = accountCustomerMap.get(
				user.getUserId());
			PartnerWorker partnerWorker = partnerWorkerMap.get(
				user.getUserId());

			if ((accountCustomer == null) && (partnerWorker == null)) {
				_accountCustomerSynchronizer.reassignTickets(
					user.getUserId(), accountEntryId, zendeskOrganizationId,
					zendeskUserId);

				_asyncZendeskOrganizationMembershipWebService.
					deleteOrganizationMemberships(
						zendeskUserId, new long[] {zendeskOrganizationId});

				_userSynchronizer.removeObsoleteTags(user.getUserId());
			}
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_asyncZendeskOrganizationMembershipWebService;

	@Reference
	private PartnerWorkerSynchronizer _partnerWorkerSynchronizer;

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
	private ZendeskUserWebService _zendeskUserWebService;

}