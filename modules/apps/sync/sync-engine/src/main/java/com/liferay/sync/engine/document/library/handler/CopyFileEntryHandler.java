/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.util.FileEventUtil;
import com.liferay.sync.engine.model.SyncFile;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class CopyFileEntryHandler extends AddFileFolderHandler {

	public CopyFileEntryHandler(Event event) {
		super(event);
	}

	@Override
	public boolean handlePortalException(String exception) throws Exception {
		if (exception.isEmpty()) {
			return false;
		}

		if (_logger.isDebugEnabled()) {
			_logger.debug("Handling exception {}", exception);
		}

		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		FileEventUtil.addFile(
			Paths.get(syncFile.getFilePathName()), syncFile.getParentFolderId(),
			syncFile.getRepositoryId(), getSyncAccountId(),
			syncFile.getChecksum(), syncFile.getName(), syncFile.getMimeType(),
			syncFile);

		return true;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		CopyFileEntryHandler.class);

}