/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.service.permission;

import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.exception.NoSuchFolderException;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
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
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.bookmarks.model.BookmarksEntry"
)
public class BookmarksEntryPermissionChecker
	implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, BookmarksEntry entry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entry, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, BookmarksEntry.class.getName(),
				entry.getEntryId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, BookmarksEntry.class.getName(), entryId,
				actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, BookmarksEntry entry,
			String actionId)
		throws PortalException {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, entry.getGroupId(),
			BookmarksEntry.class.getName(), entry.getEntryId(),
			BookmarksPortletKeys.BOOKMARKS_ADMIN, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			long folderId = entry.getFolderId();

			if (folderId == BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				if (!BookmarksResourcePermissionChecker.contains(
						permissionChecker, entry.getGroupId(), actionId)) {

					return false;
				}
			}
			else {
				try {
					BookmarksFolder folder =
						_bookmarksFolderLocalService.getFolder(folderId);

					if (!BookmarksFolderPermissionChecker.contains(
							permissionChecker, folder, ActionKeys.ACCESS) &&
						!BookmarksFolderPermissionChecker.contains(
							permissionChecker, folder, ActionKeys.VIEW)) {

						return false;
					}
				}
				catch (NoSuchFolderException nsfe) {
					if (!entry.isInTrash()) {
						throw nsfe;
					}
				}
			}
		}

		if (permissionChecker.hasOwnerPermission(
				entry.getCompanyId(), BookmarksEntry.class.getName(),
				entry.getEntryId(), entry.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			entry.getGroupId(), BookmarksEntry.class.getName(),
			entry.getEntryId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

		BookmarksEntry entry = _bookmarksEntryLocalService.getEntry(entryId);

		return contains(permissionChecker, entry, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setBookmarksEntryLocalService(
		BookmarksEntryLocalService bookmarksEntryLocalService) {

		_bookmarksEntryLocalService = bookmarksEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setBookmarksFolderLocalService(
		BookmarksFolderLocalService bookmarksFolderLocalService) {

		_bookmarksFolderLocalService = bookmarksFolderLocalService;
	}

	private static BookmarksEntryLocalService _bookmarksEntryLocalService;
	private static BookmarksFolderLocalService _bookmarksFolderLocalService;

}