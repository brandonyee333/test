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

package com.liferay.osb.customer.zendesk.model.listener;

import com.liferay.osb.customer.zendesk.model.listener.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountCustomerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.PartnerWorkerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class PartnerWorkerModelListener
	extends BaseModelListener<PartnerWorker> {

	@Override
	public void onAfterCreate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			_partnerWorkerSynchronizer.add(partnerWorker);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterRemove(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			User user = _userLocalService.fetchUser(partnerWorker.getUserId());

			if (user == null) {
				return;
			}

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				user.getUserId());

			if (zendeskUserId <= 0) {
				return;
			}

			reassignPartnerWorkerTickets(partnerWorker);

			_partnerWorkerSynchronizer.remove(partnerWorker);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if ((_oldRole.get() == PartnerWorkerConstants.ROLE_WATCHER) &&
				(partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER)) {

				_partnerWorkerSynchronizer.updateRole(partnerWorker);
			}

			if ((_oldRole.get() != PartnerWorkerConstants.ROLE_WATCHER) &&
				(partnerWorker.getRole() ==
					PartnerWorkerConstants.ROLE_WATCHER)) {

				reassignPartnerWorkerTickets(partnerWorker);

				_partnerWorkerSynchronizer.updateRole(partnerWorker);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			PartnerWorker oldPartnerWorker =
				PartnerWorkerLocalServiceUtil.getPartnerWorker(
					partnerWorker.getPartnerWorkerId());

			_oldRole.set(oldPartnerWorker.getRole());
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected void reassignPartnerWorkerTickets(PartnerWorker partnerWorker)
		throws PortalException {

		PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

		List<AccountEntry> accountEntries =
			partnerEntry.getPartnerManagedAccountEntries();

		for (AccountEntry accountEntry : accountEntries) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			if (zendeskOrganizationId <= 0) {
				continue;
			}

			_accountEntrySynchronizer.reassignTickets(
				partnerWorker, accountEntry.getAccountEntryId());
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PartnerWorkerModelListener.class);

	private static final ThreadLocal<Integer> _oldRole =
		new CentralizedThreadLocal<>(
			PartnerWorkerModelListener.class + "._oldRole");

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

	@Reference
	private PartnerWorkerSynchronizer _partnerWorkerSynchronizer;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}