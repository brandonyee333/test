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
import com.liferay.osb.customer.zendesk.listeners.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = UserSynchronizer.class)
public class UserSynchronizer {

	public void removeObsoleteTags(long userId) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				return;
			}
		}

		Set<String> tags = new HashSet<>();

		if (AccountEntryLocalServiceUtil.getUserAccountEntriesCount(userId) ==
				0) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		}

		tags.add(ZendeskTagConstants.OSB_PARTNER);

		_asyncZendeskUserWebService.deleteZendeskUserTags(zendeskUserId, tags);
	}

	public long sync(User user, String organizationName, Set<String> tags)
		throws PortalException {

		String locale = ZendeskModelListenerUtil.convertToZendeskLocale(
			user.getLanguageId());

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if (zendeskUserId != 0) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				user.getUuid(), user.getEmailAddress(), locale,
				user.getFullName(), organizationName, null);

			_asyncZendeskUserWebService.addZendeskUserTags(zendeskUserId, tags);
		}
		else {
			ZendeskUser zendeskUser =
				_zendeskUserWebService.createOrUpdateZendeskUser(
					user.getUuid(), user.getEmailAddress(), locale,
					user.getFullName(), organizationName, tags);

			zendeskUserId = zendeskUser.getZendeskUserId();
		}

		return zendeskUserId;
	}

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}