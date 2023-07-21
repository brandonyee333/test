/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.JSONUtil;

/**
 * @author Shinn Lok
 */
public class UpdateFolderHandler extends BaseJSONHandler {

	public UpdateFolderHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncFile remoteSyncFile = JSONUtil.readValue(response, SyncFile.class);

		SyncFile localSyncFile = getLocalSyncFile();

		if (localSyncFile == null) {
			return;
		}

		localSyncFile.setModifiedTime(remoteSyncFile.getModifiedTime());
		localSyncFile.setState(SyncFile.STATE_SYNCED);

		SyncFileService.update(localSyncFile);
	}

}