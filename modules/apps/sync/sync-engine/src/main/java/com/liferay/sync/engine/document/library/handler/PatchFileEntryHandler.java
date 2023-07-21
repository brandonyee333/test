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
public class PatchFileEntryHandler extends BaseJSONHandler {

	public PatchFileEntryHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncFile remoteSyncFile = JSONUtil.readValue(response, SyncFile.class);

		SyncFile localSyncFile = getLocalSyncFile();

		if (localSyncFile == null) {
			return;
		}

		localSyncFile.setLanTokenKey(remoteSyncFile.getLanTokenKey());
		localSyncFile.setModifiedTime(remoteSyncFile.getModifiedTime());
		localSyncFile.setParentFolderId(remoteSyncFile.getParentFolderId());
		localSyncFile.setSize(remoteSyncFile.getSize());
		localSyncFile.setState(SyncFile.STATE_SYNCED);
		localSyncFile.setUiEvent(SyncFile.UI_EVENT_UPLOADED);
		localSyncFile.setUserId(remoteSyncFile.getUserId());
		localSyncFile.setUserName(remoteSyncFile.getUserName());
		localSyncFile.setVersion(remoteSyncFile.getVersion());
		localSyncFile.setVersionId(remoteSyncFile.getVersionId());

		SyncFileService.update(localSyncFile);
	}

}