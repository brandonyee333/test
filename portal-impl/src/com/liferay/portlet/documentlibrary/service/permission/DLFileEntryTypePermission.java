/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.PortletKeys;

/**
 * @author Alexander Chow
 */
public class DLFileEntryTypePermission {

	public static void check(
			PermissionChecker permissionChecker, DLFileEntryType fileEntryType,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, fileEntryType, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DLFileEntryType.class.getName(),
				fileEntryType.getFileEntryTypeId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long fileEntryTypeId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, fileEntryTypeId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DLFileEntryType.class.getName(),
				fileEntryTypeId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, DLFileEntryType fileEntryType,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, fileEntryType.getGroupId(),
			DLFileEntryType.class.getName(), fileEntryType.getFileEntryTypeId(),
			PortletKeys.DOCUMENT_LIBRARY_ADMIN, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				fileEntryType.getCompanyId(), DLFileEntryType.class.getName(),
				fileEntryType.getFileEntryTypeId(), fileEntryType.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			fileEntryType.getGroupId(), DLFileEntryType.class.getName(),
			fileEntryType.getFileEntryTypeId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileEntryTypeId,
			String actionId)
		throws PortalException {

		DLFileEntryType fileEntryType =
			DLFileEntryTypeLocalServiceUtil.getFileEntryType(fileEntryTypeId);

		return contains(permissionChecker, fileEntryType, actionId);
	}

}