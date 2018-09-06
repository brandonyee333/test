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

package com.liferay.osb.customer.account.entry.details.web.internal.util;

import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskMapperUtil.class)
public class ZendeskMapperUtil {

	public AccountEntry getAccountEntry(long zendeskOrganizationId)
		throws PortalException {

		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, ExternalIdMapperConstants.TYPE_ZENDESK,
				String.valueOf(zendeskOrganizationId));

		if (externalIdMappers.isEmpty()) {
			throw new NoSuchAccountEntryException();
		}

		ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

		return AccountEntryLocalServiceUtil.getAccountEntry(
			externalIdMapper.getClassPK());
	}

	public long getZendeskUserId(long userId) throws PortalException {
		long classNameId = _classNameLocalService.getClassNameId(User.class);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, userId, ExternalIdMapperConstants.TYPE_ZENDESK);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			return GetterUtil.getLong(externalIdMapper.getExternalId());
		}

		ZendeskUser zendeskUser = _zendeskUserWebService.getZendeskUser(userId);

		ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
			classNameId, userId, ExternalIdMapperConstants.TYPE_ZENDESK,
			String.valueOf(zendeskUser.getZendeskUserId()));

		return zendeskUser.getZendeskUserId();
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}