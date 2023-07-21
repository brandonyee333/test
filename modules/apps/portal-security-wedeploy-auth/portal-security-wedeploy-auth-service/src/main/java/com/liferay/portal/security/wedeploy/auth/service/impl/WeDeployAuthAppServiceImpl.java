/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.security.wedeploy.auth.constants.WeDeployAuthActionKeys;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;
import com.liferay.portal.security.wedeploy.auth.service.base.WeDeployAuthAppServiceBaseImpl;
import com.liferay.portal.security.wedeploy.auth.service.permission.WeDeployAuthAppPermission;
import com.liferay.portal.security.wedeploy.auth.service.permission.WeDeployAuthPermission;

/**
 * @author Supritha Sundaram
 */
public class WeDeployAuthAppServiceImpl extends WeDeployAuthAppServiceBaseImpl {

	@Override
	public WeDeployAuthApp addWeDeployAuthApp(
			String name, String redirectURI, ServiceContext serviceContext)
		throws PortalException {

		WeDeployAuthPermission.check(
			getPermissionChecker(), WeDeployAuthActionKeys.ADD_APP);

		return weDeployAuthAppLocalService.addWeDeployAuthApp(
			getUserId(), name, redirectURI, serviceContext);
	}

	@Override
	public WeDeployAuthApp deleteWeDeployAuthApp(long weDeployAuthAppId)
		throws PortalException {

		WeDeployAuthAppPermission.check(
			getPermissionChecker(), weDeployAuthAppId, ActionKeys.DELETE);

		return weDeployAuthAppLocalService.deleteWeDeployAuthApp(
			weDeployAuthAppId);
	}

}