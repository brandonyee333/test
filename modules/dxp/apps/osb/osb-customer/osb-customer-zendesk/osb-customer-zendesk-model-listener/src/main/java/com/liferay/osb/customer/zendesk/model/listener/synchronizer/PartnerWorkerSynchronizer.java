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

package com.liferay.osb.customer.zendesk.model.listener.synchronizer;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PartnerWorkerSynchronizer.class)
public class PartnerWorkerSynchronizer {

	public void add(long accountEntryId, PartnerWorker partnerWorker)
		throws PortalException {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);

		long zendeskUserId = addUser(
			partnerWorker, new long[] {zendeskOrganizationId});

		if (partnerWorker.getRole() != PartnerWorkerConstants.ROLE_WATCHER) {
			addOrganizationMemberships(
				zendeskUserId, new long[] {zendeskOrganizationId});
		}
	}

	public void add(PartnerWorker partnerWorker) throws PortalException {
		long[] zendeskOrganizationIds = getZendeskOrganizationIds(
			partnerWorker);

		long zendeskUserId = addUser(partnerWorker, zendeskOrganizationIds);

		if (partnerWorker.getRole() != PartnerWorkerConstants.ROLE_WATCHER) {
			addOrganizationMemberships(zendeskUserId, zendeskOrganizationIds);
		}
	}

	public void remove(long accountEntryId, PartnerWorker partnerWorker)
		throws PortalException {

		if (partnerWorker.getRole() != PartnerWorkerConstants.ROLE_WATCHER) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				partnerWorker.getUserId());

			if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
				removeOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});
			}
		}

		_userSynchronizer.removeObsoleteTags(partnerWorker.getUserId());
	}

	public void remove(PartnerWorker partnerWorker) throws PortalException {
		if (partnerWorker.getRole() != PartnerWorkerConstants.ROLE_WATCHER) {
			long[] zendeskOrganizationIds = getZendeskOrganizationIds(
				partnerWorker);
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				partnerWorker.getUserId());

			if (zendeskUserId > 0) {
				removeOrganizationMemberships(
					zendeskUserId, zendeskOrganizationIds);
			}
		}

		_userSynchronizer.removeObsoleteTags(partnerWorker.getUserId());
	}

	public void updateRole(PartnerWorker partnerWorker) throws PortalException {
		if (partnerWorker.getRole() == PartnerWorkerConstants.ROLE_WATCHER) {
			long[] zendeskOrganizationIds = getZendeskOrganizationIds(
				partnerWorker);
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				partnerWorker.getUserId());

			if (zendeskUserId > 0) {
				removeOrganizationMemberships(
					zendeskUserId, zendeskOrganizationIds);
			}

			_userSynchronizer.removeObsoleteTags(partnerWorker.getUserId());
		}
		else {
			add(partnerWorker);
		}
	}

	protected void addOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		if (ArrayUtil.isEmpty(zendeskOrganizationIds)) {
			return;
		}

		_asyncZendeskUserWebService.createZendeskUserOrganizationMemberships(
			zendeskUserId, zendeskOrganizationIds);

		for (long zendeskOrganizationId : zendeskOrganizationIds) {
			_asyncZendeskUserWebService.
				createZendeskUserOrganizationSubscription(
					zendeskUserId, zendeskOrganizationId);
		}
	}

	protected long addUser(
			PartnerWorker partnerWorker, long[] zendeskOrganizationIds)
		throws PortalException {

		User user = _userLocalService.getUser(partnerWorker.getUserId());

		Set<String> tags = new HashSet<>();

		tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);

		if ((partnerWorker.getRole() != PartnerWorkerConstants.ROLE_WATCHER) &&
			!ArrayUtil.isEmpty(zendeskOrganizationIds)) {

			tags.add(ZendeskTagConstants.OSB_PARTNER);
		}

		return _userSynchronizer.sync(user, null, tags);
	}

	protected long[] getZendeskOrganizationIds(PartnerWorker partnerWorker)
		throws PortalException {

		List<Long> zendeskOrganizationIds = new ArrayList<>();

		PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

		for (AccountEntry accountEntry :
				partnerEntry.getPartnerManagedAccountEntries()) {

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			if (zendeskOrganizationId != 0) {
				zendeskOrganizationIds.add(zendeskOrganizationId);
			}
		}

		return ArrayUtil.toLongArray(zendeskOrganizationIds);
	}

	protected void removeOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		if (ArrayUtil.isEmpty(zendeskOrganizationIds)) {
			return;
		}

		_asyncZendeskUserWebService.deleteZendeskUserOrganizationMemberships(
			zendeskUserId, zendeskOrganizationIds);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}