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

import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class OSBAssetReceiptPermission {

	public static void check(
			PermissionChecker permissionChecker, AssetReceipt assetReceipt,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, assetReceipt, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long assetReceiptId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, assetReceiptId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, AssetReceipt assetReceipt,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		if (actionId.equals(OSBActionKeys.UPDATE)) {
			return false;
		}

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		if (assetReceipt == null) {
			return false;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			return true;
		}

		long userId = permissionChecker.getUserId();

		if (actionId.equals(OSBActionKeys.ADD_LICENSE) ||
			actionId.equals(OSBActionKeys.MANAGE) ||
			actionId.equals(OSBActionKeys.RENEW_LICENSE) ||
			actionId.equals(OSBActionKeys.VIEW)) {

			String ownerClassName = assetReceipt.getOwnerClassName();

			if (ownerClassName.equals(CorpProject.class.getName()) &&
				CorpProjectLocalServiceUtil.hasUserCorpProject(
					userId, assetReceipt.getOwnerClassPK())) {

				return true;
			}
			else if (ownerClassName.equals(User.class.getName()) &&
					 (assetReceipt.getOwnerClassPK() == userId)) {

				return true;
			}
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long assetReceiptId,
			String actionId)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

		return contains(permissionChecker, assetReceipt, actionId);
	}

}