/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.document.library.handler.LanDownloadFileHandler;
import com.liferay.sync.engine.lan.session.LanSession;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import org.apache.http.client.methods.HttpGet;

/**
 * @author Dennis Ju
 */
public class LanDownloadFileEvent extends BaseEvent {

	public LanDownloadFileEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, "", parameters);

		_handler = new LanDownloadFileHandler(this);
	}

	@Override
	public void cancel() {
		if (_httpGet != null) {
			_httpGet.abort();
		}

		super.cancel();
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		Path filePath = Paths.get(syncFile.getFilePathName());

		syncFile.setPreviousModifiedTime(
			FileUtil.getLastModifiedTime(filePath));

		syncFile.setState(SyncFile.STATE_IN_PROGRESS);
		syncFile.setUiEvent(SyncFile.UI_EVENT_DOWNLOADING);

		SyncFileService.update(syncFile);

		LanSession lanSession = LanSession.getLanSession();

		_httpGet = lanSession.downloadFile(this);
	}

	private final Handler<Void> _handler;
	private HttpGet _httpGet;

}