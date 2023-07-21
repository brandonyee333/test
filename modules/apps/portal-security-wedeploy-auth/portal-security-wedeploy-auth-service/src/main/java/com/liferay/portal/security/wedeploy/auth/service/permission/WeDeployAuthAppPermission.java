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
	property = "resource.name=com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp",
	service = ResourcePermissionChecker.class
)
public class WeDeployAuthAppPermission extends BaseResourcePermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long weDeployAuthId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, weDeployAuthId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker.getUserId(), WeDeployAuthApp.class.getName(),
				weDeployAuthId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long weDeployAuthId,
		String actionId) {

		String portletId = PortletProviderUtil.getPortletId(
			WeDeployAuthApp.class.getName(), PortletProvider.Action.VIEW);

		return contains(
			permissionChecker, WeDeployAuthApp.class.getName(), portletId,
			weDeployAuthId, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long weDeployAuthId,
		String actionId) {

		return contains(permissionChecker, weDeployAuthId, actionId);
	}

}