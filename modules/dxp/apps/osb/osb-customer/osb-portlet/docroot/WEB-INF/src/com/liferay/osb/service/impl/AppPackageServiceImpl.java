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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.service.base.AppPackageServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.service.permission.OSBAppPackagePermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.io.File;

import java.util.List;

/**
 * @author Ryan Park
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AppPackageServiceImpl extends AppPackageServiceBaseImpl {

	public AppPackage addAppPackage(
			long appEntryId, long appVersionId, int compatibility,
			boolean compatibilityPlus)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntry, OSBActionKeys.ADD_APP_PACKAGE);

		return appPackageLocalService.addAppPackage(
			appEntryId, appVersionId, compatibility, compatibilityPlus);
	}

	public void addAppPackageSrc(long appPackageId, String fileName, File file)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackagePersistence.fetchByPrimaryKey(
			appPackageId);

		OSBAppPackagePermission.check(
			getPermissionChecker(), appPackage,
			OSBActionKeys.UPDATE_APP_PACKAGE_SRC);

		appPackageLocalService.addAppPackageSrc(
			getUserId(), appPackageId, fileName, file);
	}

	public AppPackage deleteAppPackage(long appPackageId)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackageLocalService.getAppPackage(
			appPackageId);

		OSBAppPackagePermission.check(
			getPermissionChecker(), appPackage,
			OSBActionKeys.DELETE_APP_PACKAGE);

		return appPackageLocalService.deleteAppPackage(appPackage);
	}

	public void deleteAppPackageSrc(long appPackageId)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackagePersistence.fetchByPrimaryKey(
			appPackageId);

		OSBAppPackagePermission.check(
			getPermissionChecker(), appPackage,
			OSBActionKeys.UPDATE_APP_PACKAGE_SRC);

		long classNameId = PortalUtil.getClassNameId(
			AppPackage.class.getName());

		List<AssetAttachment> assetAttachments =
			assetAttachmentPersistence.findByC_C_T(
				classNameId, appPackageId,
				AssetAttachmentConstants.TYPE_PACKAGE_SRC);

		for (AssetAttachment assetAttachment : assetAttachments) {
			assetAttachmentLocalService.deleteAssetAttachment(assetAttachment);
		}
	}

	public AppPackage fetchAppPackage(long appVersionId, int compatibility)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackageLocalService.fetchAppPackage(
			appVersionId, compatibility);

		if (appPackage == null) {
			return null;
		}

		AppVersion appVersion = appPackage.getAppVersion();

		if (appVersion.isApproved()) {
			OSBAppPackagePermission.check(
				getPermissionChecker(), appPackage, OSBActionKeys.VIEW);
		}
		else {
			OSBAppPackagePermission.check(
				getPermissionChecker(), appPackage,
				OSBActionKeys.VIEW_UNAPPROVED_APP_PACKAGE);
		}

		return appPackage;
	}

	public AppPackage getAppPackage(long appPackageId)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackageLocalService.getAppPackage(
			appPackageId);

		AppVersion appVersion = appPackage.getAppVersion();

		if (appVersion.isApproved()) {
			OSBAppPackagePermission.check(
				getPermissionChecker(), appPackage, OSBActionKeys.VIEW);
		}
		else {
			OSBAppPackagePermission.check(
				getPermissionChecker(), appPackage,
				OSBActionKeys.VIEW_UNAPPROVED_APP_PACKAGE);
		}

		return appPackage;
	}

	public AppPackage updateAppPackage(
			long appPackageId, boolean compatibilityPlus)
		throws PortalException, SystemException {

		OSBAppPackagePermission.check(
			getPermissionChecker(), appPackageId, OSBActionKeys.UPDATE);

		return appPackageLocalService.updateAppPackage(
			appPackageId, compatibilityPlus);
	}

}