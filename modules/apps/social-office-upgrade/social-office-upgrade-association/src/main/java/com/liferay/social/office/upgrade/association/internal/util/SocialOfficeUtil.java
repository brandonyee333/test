/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.office.upgrade.association.internal.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 * @author Eudaldo Alonso
 * @author Sergio González
 */
@Component(service = SocialOfficeUtil.class)
public class SocialOfficeUtil {

	public void disableSocialOffice(Group group) throws Exception {
		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.remove("customJspServletContextName");

		groupLocalService.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());

		ExpandoValue expandoValue = expandoValueLocalService.getValue(
			group.getCompanyId(), Group.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeEnabled",
			group.getGroupId());

		expandoValue.setBoolean(false);

		expandoValueLocalService.updateExpandoValue(expandoValue);
	}

	public void enableSocialOffice(Group group) throws Exception {
		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"customJspServletContextName", "so-hook");

		groupLocalService.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());

		expandoValueLocalService.addValue(
			group.getCompanyId(), Group.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeEnabled",
			group.getGroupId(), true);
	}

	public boolean isSocialOfficeGroup(long groupId) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		ExpandoBridge expandoBridge = group.getExpandoBridge();

		return GetterUtil.getBoolean(
			expandoBridge.getAttribute("socialOfficeEnabled", false));
	}

	@Reference
	protected ExpandoValueLocalService expandoValueLocalService;

	@Reference
	protected GroupLocalService groupLocalService;

}