/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.document.library.handler.MoveToTrashHandler;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class MoveFolderToTrashEvent extends BaseEvent {

	public MoveFolderToTrashEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.MOVE_FOLDER_TO_TRASH, parameters);

		_handler = new MoveToTrashHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		SyncFileService.setStatuses(
			syncFile, SyncFile.STATE_IN_PROGRESS,
			SyncFile.UI_EVENT_DELETED_LOCAL);

		processAsynchronousRequest();
	}

	private final Handler<Void> _handler;

}