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

package com.liferay.osb.service.permission;

import com.liferay.osb.marketplace.util.MarketplaceUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.List;

/**
 * @author Ryan Park
 */
public class OSBAppPackagePermission {

	public static void check(
			PermissionChecker permissionChecker, AppPackage appPackage,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, appPackage, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long appPackageId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, appPackageId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, AppPackage appPackage,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			return true;
		}

		AppEntry appEntry = appPackage.getAppEntry();
		AppVersion appVersion = appPackage.getAppVersion();

		if (MarketplaceUtil.isAppEntryDeveloper(
				permissionChecker.getUserId(), appEntry) &&
			((appVersion.getStatus() ==
				WorkflowConstants.STATUS_DENIED) ||
			 (appVersion.getStatus() ==
				WorkflowConstants.STATUS_DRAFT))) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.VIEW_UNAPPROVED_APP_PACKAGE)) {
			return OSBAppEntryPermission.contains(
				permissionChecker, appVersion.getAppEntryId(),
				OSBActionKeys.UPDATE);
		}

		if (!appVersion.isApproved()) {
			return false;
		}

		if (actionId.equals(OSBActionKeys.VIEW)) {
			if (permissionChecker.getUserId() ==
					appVersion.getUserId()) {

				return true;
			}

			if (appEntry.getAppEntryId() ==
					PortletPropsValues.MARKETPLACE_APP_ENTRY_ID) {

				return true;
			}

			if (AssetReceiptLocalServiceUtil.hasAssetReceipt(
					User.class.getName(), permissionChecker.getUserId(),
					AppEntry.class.getName(), appVersion.getAppEntryId())) {

				return true;
			}

			List<CorpProject> corpProjects =
				CorpProjectLocalServiceUtil.getUserCorpProjects(
					permissionChecker.getUserId());

			for (CorpProject corpProject : corpProjects) {
				if (AssetReceiptLocalServiceUtil.hasAssetReceipt(
						CorpProject.class.getName(),
						corpProject.getCorpProjectId(),
						AppEntry.class.getName(), appVersion.getAppEntryId())) {

					return true;
				}
			}

			DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

			if (developerEntry.isCompany()) {
				CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
					developerEntry.getDossieraAccountKey());

				if (CorpEntryLocalServiceUtil.hasUserCorpEntryRole(
						permissionChecker.getUserId(),
						corpEntry.getCorpEntryId(),
						OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID)) {

					return true;
				}
			}
			else if (developerEntry.isUser()) {
				if (developerEntry.getUserId() ==
						permissionChecker.getUserId()) {

					return true;
				}
			}

			if (appEntry.isLiferayEEPlugin() &&
				OSBMarketplacePermission.hasEEPermissions(
					permissionChecker.getUserId())) {

				return true;
			}
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long appPackageId,
			String actionId)
		throws PortalException, SystemException {

		AppPackage appPackage = AppPackageLocalServiceUtil.getAppPackage(
			appPackageId);

		return contains(permissionChecker, appPackage, actionId);
	}

}