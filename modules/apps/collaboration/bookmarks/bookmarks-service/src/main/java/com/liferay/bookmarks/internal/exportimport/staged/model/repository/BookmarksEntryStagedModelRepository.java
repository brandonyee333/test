/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.internal.exportimport.staged.model.repository;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.base.BaseStagedModelRepository;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.bookmarks.model.BookmarksEntry",
	service = {
		BookmarksEntryStagedModelRepository.class, StagedModelRepository.class
	}
)
public class BookmarksEntryStagedModelRepository
	extends BaseStagedModelRepository<BookmarksEntry> {

	@Override
	public BookmarksEntry addStagedModel(
			PortletDataContext portletDataContext,
			BookmarksEntry bookmarksEntry)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			bookmarksEntry.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			bookmarksEntry);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(bookmarksEntry.getUuid());
		}

		return _bookmarksEntryLocalService.addEntry(
			userId, bookmarksEntry.getGroupId(), bookmarksEntry.getFolderId(),
			bookmarksEntry.getName(), bookmarksEntry.getUrl(),
			bookmarksEntry.getDescription(), serviceContext);
	}

	@Override
	public void deleteStagedModel(BookmarksEntry bookmarksEntry)
		throws PortalException {

		_bookmarksEntryLocalService.deleteEntry(bookmarksEntry);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		BookmarksEntry bookmarksEntry = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (bookmarksEntry != null) {
			deleteStagedModel(bookmarksEntry);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {

		_bookmarksEntryLocalService.deleteEntries(
			portletDataContext.getScopeGroupId(),
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
	}

	@Override
	public BookmarksEntry fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _bookmarksEntryLocalService.fetchBookmarksEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<BookmarksEntry> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _bookmarksEntryLocalService.
			getBookmarksEntriesByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return _bookmarksEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext,
			BookmarksEntry bookmarksEntry)
		throws PortletDataException {

		long userId = portletDataContext.getUserId(
			bookmarksEntry.getUserUuid());

		BookmarksEntry existingBookmarksEntry =
			fetchStagedModelByUuidAndGroupId(
				bookmarksEntry.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingBookmarksEntry == null) ||
			!isStagedModelInTrash(existingBookmarksEntry)) {

			return;
		}

		TrashHandler trashHandler = existingBookmarksEntry.getTrashHandler();

		try {
			if (trashHandler.isRestorable(
					existingBookmarksEntry.getEntryId())) {

				trashHandler.restoreTrashEntry(
					userId, existingBookmarksEntry.getEntryId());
			}
		}
		catch (PortalException pe) {
			throw new PortletDataException(pe);
		}
	}

	@Override
	public BookmarksEntry saveStagedModel(BookmarksEntry bookmarksEntry) {
		return _bookmarksEntryLocalService.updateBookmarksEntry(bookmarksEntry);
	}

	@Override
	public BookmarksEntry updateStagedModel(
		PortletDataContext portletDataContext, BookmarksEntry bookmarksEntry) {

		throw new UnsupportedOperationException();
	}

	public BookmarksEntry updateStagedModel(
			PortletDataContext portletDataContext,
			BookmarksEntry bookmarksEntry, long existingEntryId)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			bookmarksEntry.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			bookmarksEntry);

		return _bookmarksEntryLocalService.updateEntry(
			userId, existingEntryId, bookmarksEntry.getGroupId(),
			bookmarksEntry.getFolderId(), bookmarksEntry.getName(),
			bookmarksEntry.getUrl(), bookmarksEntry.getDescription(),
			serviceContext);
	}

	@Reference
	private BookmarksEntryLocalService _bookmarksEntryLocalService;

}