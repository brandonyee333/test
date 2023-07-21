/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.GetSyncDLObjectUpdateHandler;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.ServerInfo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class GetSyncDLObjectUpdateEvent extends BaseEvent {

	public GetSyncDLObjectUpdateEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(
			syncAccountId, EventURLPaths.GET_SYNC_DL_OBJECT_UPDATE, parameters);

		_handler = new GetSyncDLObjectUpdateHandler(this);
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
		Long parentFolderId = (Long)getParameterValue("parentFolderId");

		if (parentFolderId != null) {
			super.processRequest();

			return;
		}

		SyncSite syncSite = (SyncSite)getParameterValue("syncSite");

		// Refetch for updated last remote sync time

		syncSite = SyncSiteService.fetchSyncSite(
			syncSite.getGroupId(), syncSite.getSyncAccountId());

		if (!syncSite.getActive()) {
			return;
		}

		syncSite.setState(SyncSite.STATE_IN_PROGRESS);

		SyncSiteService.update(syncSite);

		Map<String, Object> parameters = getParameters();

		parameters.put("lastAccessTime", syncSite.getRemoteSyncTime());
		parameters.put("max", 0);
		parameters.put("repositoryId", syncSite.getGroupId());

		if ((syncSite.getRemoteSyncTime() == -1) &&
			ServerInfo.supportsRetrieveFromCache(getSyncAccountId())) {

			parameters.put("retrieveFromCache", false);
		}

		parameters.put("syncSite", syncSite);

		executePost(getURLPath(), parameters);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		GetSyncDLObjectUpdateEvent.class);

	private final Handler<Void> _handler;

}