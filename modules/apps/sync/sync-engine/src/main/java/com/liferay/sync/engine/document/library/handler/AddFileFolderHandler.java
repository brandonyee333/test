/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.JSONUtil;

import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class AddFileFolderHandler extends BaseJSONHandler {

	public AddFileFolderHandler(Event event) {
		super(event);
	}

	@Override
	public void handleException(Exception e) {
		if (e instanceof SocketException) {
			String message = e.getMessage();

			if (message.equals("Broken pipe")) {
				if (_logger.isDebugEnabled()) {
					_logger.debug("Handling exception {}", e.toString());
				}

				SyncFile syncFile = getLocalSyncFile();

				if (syncFile == null) {
					return;
				}

				syncFile.setState(SyncFile.STATE_ERROR);
				syncFile.setUiEvent(SyncFile.UI_EVENT_UPLOAD_EXCEPTION);

				SyncFileService.update(syncFile);

				return;
			}
		}

		super.handleException(e);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncFile remoteSyncFile = JSONUtil.readValue(response, SyncFile.class);

		SyncFile localSyncFile = getLocalSyncFile();

		if (localSyncFile == null) {
			return;
		}

		localSyncFile.setCompanyId(remoteSyncFile.getCompanyId());
		localSyncFile.setCreateTime(remoteSyncFile.getCreateTime());
		localSyncFile.setExtension(remoteSyncFile.getExtension());
		localSyncFile.setExtraSettings(remoteSyncFile.getExtraSettings());
		localSyncFile.setLanTokenKey(remoteSyncFile.getLanTokenKey());
		localSyncFile.setLockExpirationDate(
			remoteSyncFile.getLockExpirationDate());
		localSyncFile.setLockUserId(remoteSyncFile.getLockUserId());
		localSyncFile.setLockUserName(remoteSyncFile.getLockUserName());
		localSyncFile.setModifiedTime(remoteSyncFile.getModifiedTime());
		localSyncFile.setParentFolderId(remoteSyncFile.getParentFolderId());
		localSyncFile.setSize(remoteSyncFile.getSize());
		localSyncFile.setState(SyncFile.STATE_SYNCED);
		localSyncFile.setSyncAccountId(getSyncAccountId());
		localSyncFile.setTypePK(remoteSyncFile.getTypePK());
		localSyncFile.setTypeUuid(remoteSyncFile.getTypeUuid());
		localSyncFile.setUiEvent(SyncFile.UI_EVENT_UPLOADED);
		localSyncFile.setUserId(remoteSyncFile.getUserId());
		localSyncFile.setUserName(remoteSyncFile.getUserName());
		localSyncFile.setVersion(remoteSyncFile.getVersion());
		localSyncFile.setVersionId(remoteSyncFile.getVersionId());

		SyncFileService.update(localSyncFile);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		AddFileFolderHandler.class);

}