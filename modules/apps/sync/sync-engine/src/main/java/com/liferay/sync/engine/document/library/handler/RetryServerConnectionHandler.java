/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.model.SyncContext;
import com.liferay.sync.engine.document.library.util.FileEventManager;
import com.liferay.sync.engine.document.library.util.FileEventUtil;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.ConnectionRetryUtil;
import com.liferay.sync.engine.util.ServerInfo;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class RetryServerConnectionHandler extends GetSyncContextHandler {

	public RetryServerConnectionHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncContext syncContext = doProcessResponse(response);

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		if (ServerInfo.isCompatible(syncContext)) {
			if (_logger.isDebugEnabled()) {
				_logger.debug("Connected to {}", syncAccount.getUrl());
			}

			syncAccount.setState(SyncAccount.STATE_CONNECTED);
			syncAccount.setUiEvent(SyncAccount.UI_EVENT_NONE);

			SyncAccountService.update(syncAccount);

			List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
				SyncFile.STATE_IN_PROGRESS, syncAccount.getSyncAccountId());

			for (SyncFile syncFile : syncFiles) {
				Set<Event> events = FileEventManager.getEvents(
					syncFile.getSyncFileId());

				for (Event event : events) {
					event.cancel();
				}
			}

			FileEventUtil.retryFileTransfers(getSyncAccountId());

			ConnectionRetryUtil.resetRetry(getSyncAccountId());
		}
		else {
			syncAccount.setState(SyncAccount.STATE_DISCONNECTED);
			syncAccount.setUiEvent(SyncAccount.UI_EVENT_SYNC_WEB_OUT_OF_DATE);

			SyncAccountService.update(syncAccount);
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		RetryServerConnectionHandler.class);

}