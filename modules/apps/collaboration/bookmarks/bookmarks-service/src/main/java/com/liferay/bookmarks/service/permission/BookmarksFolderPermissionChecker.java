/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.service.permission;

import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.exception.NoSuchFolderException;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.util.PropsValues;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.bookmarks.model.BookmarksFolder"
)
public class BookmarksFolderPermissionChecker
	implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, BookmarksFolder folder,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, folder, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, BookmarksFolder.class.getName(),
				folder.getFolderId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, folderId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, BookmarksFolder.class.getName(), folderId,
				actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, BookmarksFolder folder,
			String actionId)
		throws PortalException {

		if (actionId.equals(ActionKeys.ADD_FOLDER)) {
			actionId = ActionKeys.ADD_SUBFOLDER;
		}

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, folder.getGroupId(),
			BookmarksFolder.class.getName(), folder.getFolderId(),
			BookmarksPortletKeys.BOOKMARKS_ADMIN, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			try {
				long folderId = folder.getFolderId();

				while (folderId !=
							BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					folder = _bookmarksFolderLocalService.getFolder(folderId);

					if (!_hasPermission(permissionChecker, folder, actionId)) {
						return false;
					}

					folderId = folder.getParentFolderId();
				}
			}
			catch (NoSuchFolderException nsfe) {
				if (!folder.isInTrash()) {
					throw nsfe;
				}
			}

			return BookmarksResourcePermissionChecker.contains(
				permissionChecker, folder.getGroupId(), actionId);
		}

		return _hasPermission(permissionChecker, folder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException {

		if (folderId == BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return BookmarksResourcePermissionChecker.contains(
				permissionChecker, groupId, actionId);
		}

		BookmarksFolder folder =
			_bookmarksFolderLocalService.getBookmarksFolder(folderId);

		return contains(permissionChecker, folder, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, groupId, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setBookmarksFolderLocalService(
		BookmarksFolderLocalService bookmarksFolderLocalService) {

		_bookmarksFolderLocalService = bookmarksFolderLocalService;
	}

	private static boolean _hasPermission(
		PermissionChecker permissionChecker, BookmarksFolder folder,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				folder.getCompanyId(), BookmarksFolder.class.getName(),
				folder.getFolderId(), folder.getUserId(), actionId) ||
			permissionChecker.hasPermission(
				folder.getGroupId(), BookmarksFolder.class.getName(),
				folder.getFolderId(), actionId)) {

			return true;
		}

		return false;
	}

	private static BookmarksFolderLocalService _bookmarksFolderLocalService;

}