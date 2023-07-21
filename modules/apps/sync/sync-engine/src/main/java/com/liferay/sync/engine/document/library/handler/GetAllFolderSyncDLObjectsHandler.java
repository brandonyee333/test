/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.fasterxml.jackson.core.type.TypeReference;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class GetAllFolderSyncDLObjectsHandler extends BaseJSONHandler {

	public GetAllFolderSyncDLObjectsHandler(Event event) {
		super(event);
	}

	public List<SyncFile> getSyncFiles() {
		return _syncFiles;
	}

	@Override
	public void processResponse(String response) throws Exception {
		_syncFiles = JSONUtil.readValue(
			response,
			new TypeReference<List<SyncFile>>() {
			});
	}

	private List<SyncFile> _syncFiles = new ArrayList<>();

}