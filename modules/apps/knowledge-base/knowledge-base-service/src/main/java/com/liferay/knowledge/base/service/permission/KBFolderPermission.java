/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service.permission;

import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.util.PropsValues;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 * @author Roberto Díaz
 */
@Component(
	property = "model.class.name=com.liferay.knowledge.base.model.KBFolder",
	service = BaseModelPermissionChecker.class
)
public class KBFolderPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, KBFolder kbFolder,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kbFolder, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long kbFolderId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, kbFolderId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kbFolderId,
			String actionId)
		throws PortalException {

		KBFolder kbFolder = KBFolderLocalServiceUtil.getKBFolder(kbFolderId);

		check(permissionChecker, kbFolder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, KBFolder kbFolder,
			String actionId)
		throws PortalException {

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE &&
			!contains(
				permissionChecker, kbFolder.getGroupId(),
				kbFolder.getParentKBFolderId(), actionId)) {

			return false;
		}

		if (permissionChecker.hasOwnerPermission(
				kbFolder.getCompanyId(), KBFolder.class.getName(),
				kbFolder.getKbFolderId(), kbFolder.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kbFolder.getGroupId(), KBFolder.class.getName(),
			kbFolder.getKbFolderId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long kbFolderId,
			String actionId)
		throws PortalException {

		if (kbFolderId == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			if (actionId.equals(ActionKeys.VIEW)) {
				return true;
			}

			return AdminPermission.contains(
				permissionChecker, groupId, actionId);
		}

		KBFolder kbFolder = KBFolderLocalServiceUtil.getKBFolder(kbFolderId);

		return contains(permissionChecker, kbFolder, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, groupId, primaryKey, actionId);
	}

}