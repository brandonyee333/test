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

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.listener.internal.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountCustomerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.PartnerWorkerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
		long accountEntryId = (Long)message.get("accountEntryId");

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		if (!hasZendeskOrganization(accountEntry) &&
			!accountEntry.isActiveSupport()) {

			AccountEntryLocalServiceUtil.updateLastZendeskAuditDate(
				OSBCustomerConstants.USER_DEFAULT_USER_ID, accountEntryId,
				StringPool.BLANK, StringPool.BLANK);

			return;
		}

		try {
			synchronize(accountEntry);
		}
		catch (Exception e) {
			_log.error(e, e);

			AccountEntryLocalServiceUtil.updateLastZendeskAuditDate(
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
			ExternalIdMapperLocalServiceUtil.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers) {
			return true;
		}

		return false;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void synchronize(AccountEntry accountEntry)
		throws PortalException {

		_accountEntrySynchronizer.update(accountEntry);

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
				_accountCustomerSynchronizer.reassignTickets(
					user.getUserId(), accountEntry.getAccountEntryId(),
					zendeskOrganizationId, zendeskUser.getZendeskUserId());

				_asyncZendeskOrganizationMembershipWebService.
					deleteOrganizationMemberships(
						zendeskUser.getZendeskUserId(),
						new long[] {zendeskOrganizationId});
			}
			else {
				if (accountCustomer != null) {
					_accountCustomerSynchronizer.sync(
						zendeskUser, accountCustomer);
				}

				if (partnerWorker != null) {
					_partnerWorkerSynchronizer.sync(
						zendeskUser, accountEntry.getAccountEntryId(),
						partnerWorker);
				}
			}

			Set<String> removeTags = new HashSet<>();

			if (accountCustomer == null) {
				removeTags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}

			_userSynchronizer.removeObsoleteTags(
				user.getUserId(), zendeskUser.getTags(), removeTags);
		}

		for (AccountCustomer accountCustomer : accountCustomerMap.values()) {
			_accountCustomerSynchronizer.add(accountCustomer);

			_accountCustomerSynchronizer.addOrganizationSubscription(
				accountCustomer);
		}

		for (PartnerWorker partnerWorker : partnerWorkerMap.values()) {
			_partnerWorkerSynchronizer.add(partnerWorker);
		}

		AccountEntryLocalServiceUtil.updateLastZendeskAuditDate(
			OSBCustomerConstants.USER_DEFAULT_USER_ID,
			accountEntry.getAccountEntryId(), "Synced project to Zendesk.",
			StringPool.BLANK);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizeAccountEntryMessageListener.class);

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_asyncZendeskOrganizationMembershipWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

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