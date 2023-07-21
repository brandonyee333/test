/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.verify;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.verify.VerifyProcess;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author     Raymond Augé
 * @author     Alexander Chow
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.bookmarks.internal.verify.BookmarksServiceVerifyProcess}
 */
@Deprecated
public class BookmarksServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		updateEntryAssets();
		updateFolderAssets();
		verifyTree();
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

	protected void updateEntryAssets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<BookmarksEntry> entries =
				_bookmarksEntryLocalService.getNoAssetEntries();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing " + entries.size() + " entries with no asset");
			}

			for (BookmarksEntry entry : entries) {
				try {
					_bookmarksEntryLocalService.updateAsset(
						entry.getUserId(), entry, null, null, null, null);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Unable to update asset for entry ",
								String.valueOf(entry.getEntryId()), ": ",
								e.getMessage()));
					}
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Assets verified for entries");
			}
		}
	}

	protected void updateFolderAssets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<BookmarksFolder> folders =
				_bookmarksFolderLocalService.getNoAssetFolders();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing " + folders.size() + " folders with no asset");
			}

			for (BookmarksFolder folder : folders) {
				try {
					_bookmarksFolderLocalService.updateAsset(
						folder.getUserId(), folder, null, null, null, null);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Unable to update asset for folder ",
								String.valueOf(folder.getFolderId()), ": ",
								e.getMessage()));
					}
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Assets verified for folders");
			}
		}
	}

	protected void verifyTree() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long[] companyIds =
				_portalInstancesLocalService.getCompanyIdsBySQL();

			for (long companyId : companyIds) {
				_bookmarksFolderLocalService.rebuildTree(companyId);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BookmarksServiceVerifyProcess.class);

	private BookmarksEntryLocalService _bookmarksEntryLocalService;
	private BookmarksFolderLocalService _bookmarksFolderLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

}