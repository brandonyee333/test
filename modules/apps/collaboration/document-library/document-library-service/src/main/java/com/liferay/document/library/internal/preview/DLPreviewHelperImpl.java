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

package com.liferay.document.library.internal.preview;

import com.liferay.document.library.model.DLFileVersionPreview;
import com.liferay.document.library.service.DLFileVersionPreviewLocalService;
import com.liferay.portlet.preview.DLPreviewHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lianne Louie
 */
@Component(immediate = true, service = DLPreviewHelper.class)
public class DLPreviewHelperImpl implements DLPreviewHelper {

	@Override
	public long getDLFileVersionPreviewId(
		long fileEntryId, long fileVersionId, int fileVersionPreviewStatus) {

		DLFileVersionPreview dlFileVersionPreview =
			_dlFileVersionPreviewLocalService.fetchDLFileVersionPreview(
				fileEntryId, fileVersionId, fileVersionPreviewStatus);

		if (dlFileVersionPreview == null) {
			return 0;
		}

		return dlFileVersionPreview.getDlFileVersionPreviewId();
	}

	@Reference
	private DLFileVersionPreviewLocalService _dlFileVersionPreviewLocalService;

}