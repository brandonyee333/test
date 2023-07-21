/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.exportimport.lifecycle;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lifecycle.BaseExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(service = ExportImportLifecycleListener.class)
public class CascadeFileEntryTypesExportImportLifecycleListener
	extends BaseExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return true;
	}

	protected DLFolder getProcessableRootFolder(DLFolder dlFolder)
		throws PortalException {

		long dlFolderId = dlFolder.getFolderId();

		if (_processedFolderIds.contains(dlFolderId)) {
			return null;
		}

		_processedFolderIds.add(dlFolderId);

		DLFolder parentFolder = dlFolder.getParentFolder();

		if ((parentFolder == null) ||
			!_importedFolderIds.containsValue(parentFolder.getFolderId())) {

			return dlFolder;
		}

		return getProcessableRootFolder(parentFolder);
	}

	@Override
	protected void onLayoutImportProcessFinished(
			PortletDataContext portletDataContext)
		throws Exception {

		_importedFolderIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DLFolder.class);

		if (MapUtil.isEmpty(_importedFolderIds)) {
			return;
		}

		_processedFolderIds = new HashSet<>();

		processFolderIds(_importedFolderIds.values());
	}

	@Override
	protected void onPortletImportProcessFinished(
			PortletDataContext portletDataContext)
		throws Exception {

		_importedFolderIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DLFolder.class);

		if (MapUtil.isEmpty(_importedFolderIds)) {
			return;
		}

		_processedFolderIds = new HashSet<>();

		processFolderIds(_importedFolderIds.values());
	}

	protected void processFolderIds(Collection<Long> folderIds)
		throws PortalException {

		for (Long folderId : folderIds) {
			DLFolder dlFolder = _dlFolderLocalService.fetchDLFolder(folderId);

			DLFolder rootFolder = getProcessableRootFolder(dlFolder);

			if (rootFolder != null) {
				_dlFileEntryTypeLocalService.cascadeFileEntryTypes(
					rootFolder.getUserId(), rootFolder);
			}
		}
	}

	@Reference(unbind = "-")
	protected void setDLFileEntryTypeLocalService(
		DLFileEntryTypeLocalService dlFileEntryTypeLocalService) {

		_dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setDlFolderLocalService(
		DLFolderLocalService dlFolderLocalService) {

		_dlFolderLocalService = dlFolderLocalService;
	}

	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;
	private DLFolderLocalService _dlFolderLocalService;
	private Map<Long, Long> _importedFolderIds;
	private Set<Long> _processedFolderIds;

}