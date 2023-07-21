/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.portlet;

import com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.util.PropsValues;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + LayoutAdminPortletKeys.MY_PAGES,
	service = ControlPanelEntry.class
)
public class MyPagesControlPanelEntry extends BaseControlPanelEntry {

	@Override
	protected boolean hasAccessPermissionDenied(
			PermissionChecker permissionChecker, Group group, Portlet portlet)
		throws Exception {

		if (!PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED &&
			!PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {

			return true;
		}

		if ((PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_POWER_USER_REQUIRED ||
			 PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED) &&
			!_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.POWER_USER, true)) {

			return true;
		}

		return super.hasAccessPermissionDenied(
			permissionChecker, group, portlet);
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	private RoleLocalService _roleLocalService;

}