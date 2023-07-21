/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.document.library.kernel.exception.FileShortcutPermissionException;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portlet.documentlibrary.service.base.DLFileShortcutServiceBaseImpl;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFileShortcutPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileShortcutServiceImpl extends DLFileShortcutServiceBaseImpl {

	@Override
	public DLFileShortcut addFileShortcut(
			long groupId, long repositoryId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException {

		DLFolderPermission.check(
			getPermissionChecker(), groupId, folderId, ActionKeys.ADD_SHORTCUT);

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), toFileEntryId, ActionKeys.VIEW);
		}
		catch (PrincipalException pe) {
			throw new FileShortcutPermissionException(pe);
		}

		return dlFileShortcutLocalService.addFileShortcut(
			getUserId(), groupId, repositoryId, folderId, toFileEntryId,
			serviceContext);
	}

	@Override
	public void deleteFileShortcut(long fileShortcutId) throws PortalException {
		DLFileShortcutPermission.check(
			getPermissionChecker(), fileShortcutId, ActionKeys.DELETE);

		dlFileShortcutLocalService.deleteFileShortcut(fileShortcutId);
	}

	@Override
	public DLFileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException {

		DLFileShortcutPermission.check(
			getPermissionChecker(), fileShortcutId, ActionKeys.VIEW);

		return dlFileShortcutLocalService.getFileShortcut(fileShortcutId);
	}

	@Override
	public DLFileShortcut updateFileShortcut(
			long fileShortcutId, long repositoryId, long folderId,
			long toFileEntryId, ServiceContext serviceContext)
		throws PortalException {

		DLFileShortcutPermission.check(
			getPermissionChecker(), fileShortcutId, ActionKeys.UPDATE);

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), toFileEntryId, ActionKeys.VIEW);
		}
		catch (PrincipalException pe) {
			throw new FileShortcutPermissionException(pe);
		}

		return dlFileShortcutLocalService.updateFileShortcut(
			getUserId(), fileShortcutId, repositoryId, folderId, toFileEntryId,
			serviceContext);
	}

	@Override
	public void updateFileShortcuts(
			long oldToFileEntryId, long newToFileEntryId)
		throws PortalException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), oldToFileEntryId, ActionKeys.VIEW);

			DLFileEntryPermission.check(
				getPermissionChecker(), newToFileEntryId, ActionKeys.VIEW);
		}
		catch (PrincipalException pe) {
			throw new FileShortcutPermissionException(pe);
		}

		dlFileShortcutLocalService.updateFileShortcuts(
			oldToFileEntryId, newToFileEntryId);
	}

}