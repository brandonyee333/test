/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncLanClient;
import com.liferay.sync.engine.service.persistence.SyncLanClientPersistence;

import java.sql.SQLException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class SyncLanClientService {

	public static void deleteSyncLanClient(SyncLanClient syncLanClient) {
		try {
			SyncLanEndpointService.deleteSyncLanEndpoints(
				syncLanClient.getSyncLanClientUuid());

			_syncLanClientPersistence.delete(syncLanClient);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static void deleteSyncLanClients(long modifiedTime) {
		try {
			List<SyncLanClient> syncLanClients =
				_syncLanClientPersistence.findByModifiedTime(modifiedTime);

			for (SyncLanClient syncLanClient : syncLanClients) {
				deleteSyncLanClient(syncLanClient);
			}
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncLanClient fetchSyncLanClient(String syncLanClientUuid) {
		try {
			return _syncLanClientPersistence.queryForId(syncLanClientUuid);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncLanClientPersistence getSyncLanClientPersistence() {
		if (_syncLanClientPersistence != null) {
			return _syncLanClientPersistence;
		}

		try {
			_syncLanClientPersistence = new SyncLanClientPersistence();

			return _syncLanClientPersistence;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static void registerModelListener(
		ModelListener<SyncLanClient> modelListener) {

		_syncLanClientPersistence.registerModelListener(modelListener);
	}

	public static void unregisterModelListener(
		ModelListener<SyncLanClient> modelListener) {

		_syncLanClientPersistence.unregisterModelListener(modelListener);
	}

	public static SyncLanClient update(SyncLanClient syncLanClient) {
		try {
			_syncLanClientPersistence.createOrUpdate(syncLanClient);

			return syncLanClient;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SyncLanClientService.class);

	private static SyncLanClientPersistence _syncLanClientPersistence =
		getSyncLanClientPersistence();

}