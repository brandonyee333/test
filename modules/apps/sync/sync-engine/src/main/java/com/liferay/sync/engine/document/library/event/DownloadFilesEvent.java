/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.DownloadFilesHandler;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.document.library.util.ServerUtil;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class DownloadFilesEvent extends BaseEvent {

	public DownloadFilesEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.DOWNLOAD_FILES, parameters);

		_handler = new DownloadFilesHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		String url = ServerUtil.getDownloadURL(
			syncAccount.getSyncAccountId(), syncAccount.getUrl());

		executeAsynchronousPost(url + getURLPath(), getParameters(), _handler);
	}

	private final Handler<Void> _handler;

}