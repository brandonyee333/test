/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.CopyFileEntryHandler;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Paths;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class CopyFileEntryEvent extends BaseEvent {

	public CopyFileEntryEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.COPY_FILE_ENTRY, parameters);

		_handler = new CopyFileEntryHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		syncFile.setPreviousModifiedTime(
			FileUtil.getLastModifiedTime(
				Paths.get(syncFile.getFilePathName())));
		syncFile.setState(SyncFile.STATE_IN_PROGRESS);
		syncFile.setUiEvent(SyncFile.UI_EVENT_UPLOADING);

		SyncFileService.update(syncFile);

		processAsynchronousRequest();
	}

	private final Handler<Void> _handler;

}