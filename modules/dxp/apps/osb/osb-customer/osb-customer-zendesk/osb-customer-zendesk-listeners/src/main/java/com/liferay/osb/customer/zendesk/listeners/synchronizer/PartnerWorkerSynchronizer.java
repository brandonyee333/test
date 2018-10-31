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

package com.liferay.osb.customer.zendesk.listeners.synchronizer;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.listeners.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

	public void add(PartnerWorker partnerWorker) throws PortalException {
		try {
			User user = _userLocalService.getUser(partnerWorker.getUserId());

			Set<String> tags = new HashSet<>();

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			tags.add(ZendeskTagConstants.OSB_PARTNER);

			long zendeskUserId = _userSynchronizer.sync(user, null, tags);

			long[] zendeskOrganizationIds = getZendeskOrganizationIds(
				partnerWorker);

			if (zendeskOrganizationIds.length > 0) {
				_asyncZendeskUserWebService.
					createZendeskUserOrganizationMemberships(
						zendeskUserId, zendeskOrganizationIds);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void remove(PartnerWorker partnerWorker) throws PortalException {
		try {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				partnerWorker.getUserId());

			long[] zendeskOrganizationIds = getZendeskOrganizationIds(
				partnerWorker);

			if (zendeskOrganizationIds.length > 0) {
				_asyncZendeskUserWebService.
					deleteZendeskUserOrganizationMemberships(
						zendeskUserId, zendeskOrganizationIds);
			}

			_userSynchronizer.removeObsoleteTags(partnerWorker.getUserId());
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected long[] getZendeskOrganizationIds(PartnerWorker partnerWorker)
		throws PortalException {

		List<Long> zendeskOrganizationIds = new ArrayList<>();

		PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

		for (AccountEntry accountEntry : partnerEntry.getAccountEntries()) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			if (zendeskOrganizationId != 0) {
				zendeskOrganizationIds.add(zendeskOrganizationId);
			}
		}

		return ArrayUtil.toLongArray(zendeskOrganizationIds);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PartnerWorkerSynchronizer.class);

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}