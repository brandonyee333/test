/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.service.permission;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Basto
 * @author Levente Hudák
 */
@Component(
	immediate = true, property = "resource.name=" + DDLPermission.RESOURCE_NAME,
	service = ResourcePermissionChecker.class
)
public class DDLPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = "com.liferay.dynamic.data.lists";

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, groupId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		String portletId = PortletProviderUtil.getPortletId(
			DDLRecord.class.getName(), PortletProvider.Action.EDIT);

		return contains(
			permissionChecker, RESOURCE_NAME, portletId, groupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String portletId,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, groupId, RESOURCE_NAME, groupId, portletId,
			actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		return permissionChecker.hasPermission(
			groupId, RESOURCE_NAME, groupId, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return contains(permissionChecker, classPK, actionId);
	}

}