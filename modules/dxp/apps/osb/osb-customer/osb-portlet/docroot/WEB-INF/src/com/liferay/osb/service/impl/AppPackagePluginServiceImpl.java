/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.service.base.AppPackagePluginServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.service.permission.OSBAppPackagePermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.io.File;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AppPackagePluginServiceImpl
	extends AppPackagePluginServiceBaseImpl {

	public AppPackagePlugin addAppPackagePlugin(
			long appVersionId, String fileName, File file, int compatibility,
			boolean compatibilityPlus)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.ADD_APP_PACKAGE_PLUGIN);

		return appPackagePluginLocalService.addAppPackagePlugin(
			getUserId(), appVersionId, fileName, file, compatibility,
			compatibilityPlus);
	}

	public AppPackagePlugin deleteAppPackagePlugin(long appPackagePluginId)
		throws PortalException, SystemException {

		AppPackagePlugin appPackagePlugin =
			appPackagePluginPersistence.findByPrimaryKey(appPackagePluginId);

		OSBAppPackagePermission.check(
			getPermissionChecker(), appPackagePlugin.getAppPackageId(),
			OSBActionKeys.DELETE_APP_PACKAGE_PLUGIN);

		return appPackagePluginLocalService.deleteAppPackagePlugin(
			appPackagePluginId);
	}

	public void deleteInvalidAppPackagePlugins(long appVersionId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.DELETE_APP_PACKAGE_PLUGIN);

		appPackagePluginLocalService.deleteInvalidAppPackagePlugins(
			appVersionId);
	}

}