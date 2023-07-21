/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.document.library.handler.UpdateFileEntriesHandler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class UpdateFileEntriesEvent extends BaseEvent {

	public UpdateFileEntriesEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.UPDATE_FILE_ENTRIES, parameters);

		_handler = new UpdateFileEntriesHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void logEvent() {
		Class<?> clazz = getClass();

		_logger.trace("Processing event {}", clazz.getSimpleName());
	}

	@Override
	protected void processRequest() throws Exception {
		executeAsynchronousPost(getURLPath(), getParameters());
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		UpdateFileEntriesEvent.class);

	private final Handler<Void> _handler;

}