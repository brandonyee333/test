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

package com.liferay.osb.customer.zendesk.listeners.util;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PartnerWorkerUtil.class)
public class PartnerWorkerUtil {

	public void addPartnerWorker(
			PartnerWorker partnerWorker, long[] zendeskOrganizationIds)
		throws PortalException {

		try {
			User user = UserLocalServiceUtil.getUser(partnerWorker.getUserId());

			Set<String> tags = new HashSet<>();

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			tags.add(ZendeskTagConstants.OSB_PARTNER);

			String locale = ZendeskModelListenerUtil.convertToZendeskLocale(
				user.getLanguageId());

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				partnerWorker.getUserId());

			if (zendeskUserId == 0) {
				ZendeskUser zendeskUser =
					_zendeskUserWebService.createOrUpdateZendeskUser(
						user.getUuid(), user.getEmailAddress(), locale,
						user.getFullName(), null, tags);

				zendeskUserId = zendeskUser.getZendeskUserId();
			}
			else {
				_asyncZendeskUserWebService.addZendeskUserTags(
					zendeskUserId, tags);
			}

			_asyncZendeskUserWebService.
				createZendeskUserOrganizationMemberships(
					zendeskUserId, zendeskOrganizationIds);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public void removePartnerWorker(
			PartnerWorker partnerWorker, long[] zendeskOrganizationIds)
		throws PortalException {

		try {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				partnerWorker.getUserId());

			if (zendeskOrganizationIds.length > 0) {
				_zendeskUserWebService.deleteZendeskUserOrganizationMemberships(
					zendeskUserId, zendeskOrganizationIds);
			}

			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
					partnerWorker.getUserId());

			if (partnerWorkers.isEmpty() ||
				(partnerWorker.getRole() ==
					PartnerWorkerConstants.ROLE_WATCHER)) {

				Set<String> tags = new HashSet<>();

				if (AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
						partnerWorker.getUserId()) == 0) {

					tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				}

				tags.add(ZendeskTagConstants.OSB_PARTNER);

				_zendeskUserWebService.deleteZendeskUserTags(
					zendeskUserId, tags);
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
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
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}