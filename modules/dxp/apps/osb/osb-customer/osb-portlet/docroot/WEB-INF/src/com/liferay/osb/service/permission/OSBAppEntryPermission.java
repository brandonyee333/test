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
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.ContractAuditLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Amos Fong
 * @author Ryan Park
 */
public class OSBAppEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, AppEntry appEntry,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, appEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long appEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, appEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, AppEntry appEntry,
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

		if (actionId.equals(OSBActionKeys.PURCHASE_APP) ||
			actionId.equals(OSBActionKeys.REVIEW_APP)) {

			if (!RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_VERIFIED_USER_ID)) {

				return false;
			}
		}

		if (actionId.equals(OSBActionKeys.PURCHASE_APP)) {
			if (appEntry.isLiferayEEPlugin() &&
				!OSBMarketplacePermission.hasEEPermissions(
					permissionChecker.getUserId())) {

				return false;
			}

			if (appEntry.hasCustomOrderWorkflow()) {
				return false;
			}

			if (appEntry.isPortalRequired()) {
				return false;
			}

			if (appEntry.isApproved()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (actionId.equals(OSBActionKeys.REVIEW_APP) ||
				 actionId.equals(OSBActionKeys.VIEW)) {

			if (appEntry.isApproved()) {
				return true;
			}
			else if (RoleLocalServiceUtil.hasUserRole(
						permissionChecker.getUserId(),
						OSBConstants.ROLE_OSB_MARKETPLACE_TESTER_ID)) {

				return true;
			}
		}

		if (!MarketplaceUtil.isAppEntryDeveloper(
				permissionChecker.getUserId(), appEntry)) {

			return false;
		}

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		String className = StringPool.BLANK;
		long classPK = 0;

		if (developerEntry.isCompany()) {
			className = CorpEntry.class.getName();

			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
				developerEntry.getDossieraAccountKey());

			classPK = corpEntry.getCorpEntryId();
		}
		else if (developerEntry.isUser()) {
			className = User.class.getName();
			classPK = developerEntry.getUserId();
		}

		if (!ContractAuditLocalServiceUtil.hasLatestContractAudit(
				ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
				ContractEntryConstants.DEFAULT_CLASS_PK,
				ContractEntryConstants.TYPE_TERMS_OF_SERVICE, className,
				classPK)) {

			return false;
		}

		if (actionId.equals(OSBActionKeys.ADD_APP_PACKAGE) ||
			actionId.equals(OSBActionKeys.ADD_APP_PACKAGE_PLUGIN) ||
			actionId.equals(OSBActionKeys.DELETE_APP_PACKAGE_PLUGIN) ||
			actionId.equals(OSBActionKeys.UPDATE_APP_VERSION)) {

			AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntry.getAppEntryId(),
				AppVersionConstants.STATUSES_USER_EDITABLE, null);

			if (appVersion != null) {
				return true;
			}

			return false;
		}
		else if (actionId.equals(OSBActionKeys.DELETE) ||
				 actionId.equals(OSBActionKeys.EXPIRE_APP) ||
				 actionId.equals(OSBActionKeys.UPDATE) ||
				 actionId.equals(OSBActionKeys.VIEW)) {

			return true;
		}
		else if (actionId.equals(OSBActionKeys.MANAGE_APP)) {
			if (appEntry.isFree() || !developerEntry.isSubscriptionExpired()) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long appEntryId,
			String actionId)
		throws PortalException, SystemException {

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		return contains(permissionChecker, appEntry, actionId);
	}

}