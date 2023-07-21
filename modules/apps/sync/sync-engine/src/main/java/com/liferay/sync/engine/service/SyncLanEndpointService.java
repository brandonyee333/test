/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncLanEndpoint;
import com.liferay.sync.engine.service.persistence.SyncLanEndpointPersistence;

import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class SyncLanEndpointService {

	public static void deleteSyncLanEndpoint(SyncLanEndpoint syncLanEndpoint) {
		try {
			_syncLanEndpointPersistence.delete(syncLanEndpoint);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static void deleteSyncLanEndpoints(String syncLanClientUuid) {
		try {
			_syncLanEndpointPersistence.deleteBySyncLanClientUuid(
				syncLanClientUuid);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static List<String> findSyncLanClientUuids(
		String lanServerUuid, long repositoryId) {

		try {
			return _syncLanEndpointPersistence.findByL_R(
				lanServerUuid, repositoryId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static List<SyncLanEndpoint> findSyncLanEndPoints(
		String syncLanClientUuid) {

		try {
			return _syncLanEndpointPersistence.findBySyncLanClientUuid(
				syncLanClientUuid);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static SyncLanEndpointPersistence getSyncLanEndpointPersistence() {
		if (_syncLanEndpointPersistence != null) {
			return _syncLanEndpointPersistence;
		}

		try {
			_syncLanEndpointPersistence = new SyncLanEndpointPersistence();

			return _syncLanEndpointPersistence;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static void registerModelListener(
		ModelListener<SyncLanEndpoint> modelListener) {

		_syncLanEndpointPersistence.registerModelListener(modelListener);
	}

	public static void unregisterModelListener(
		ModelListener<SyncLanEndpoint> modelListener) {

		_syncLanEndpointPersistence.unregisterModelListener(modelListener);
	}

	public static SyncLanEndpoint update(SyncLanEndpoint syncLanEndpoint) {
		try {
			_syncLanEndpointPersistence.createOrUpdate(syncLanEndpoint);

			return syncLanEndpoint;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SyncLanEndpointService.class);

	private static SyncLanEndpointPersistence _syncLanEndpointPersistence =
		getSyncLanEndpointPersistence();

}