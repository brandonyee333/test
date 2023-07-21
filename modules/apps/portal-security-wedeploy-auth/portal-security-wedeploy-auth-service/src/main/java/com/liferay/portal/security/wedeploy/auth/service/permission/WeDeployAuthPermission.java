/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;

import org.osgi.service.component.annotations.Component;

/**
 * @author Supritha Sundaram
 */
@Component(
	property = "resource.name=" + WeDeployAuthPermission.RESOURCE_NAME,
	service = ResourcePermissionChecker.class
)
public class WeDeployAuthPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME =
		"com.liferay.portal.security.wedeploy.auth";

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker.getUserId(), RESOURCE_NAME, 0, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionId) {

		String portletId = PortletProviderUtil.getPortletId(
			WeDeployAuthApp.class.getName(), PortletProvider.Action.EDIT);

		return contains(
			permissionChecker, RESOURCE_NAME, portletId, 0, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return contains(permissionChecker, actionId);
	}

}