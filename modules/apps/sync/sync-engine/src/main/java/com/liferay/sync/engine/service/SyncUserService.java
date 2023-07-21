/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncUser;
import com.liferay.sync.engine.service.persistence.SyncUserPersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class SyncUserService {

	public static void deleteSyncUser(long syncUserId) {
		try {
			_syncUserPersistence.deleteById(syncUserId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncUser fetchSyncUser(long syncAccountId) {
		try {
			return _syncUserPersistence.fetchBySyncAccountId(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncUserPersistence getSyncUserPersistence() {
		if (_syncUserPersistence != null) {
			return _syncUserPersistence;
		}

		try {
			_syncUserPersistence = new SyncUserPersistence();

			return _syncUserPersistence;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static void registerModelListener(
		ModelListener<SyncUser> modelListener) {

		_syncUserPersistence.registerModelListener(modelListener);
	}

	public static void unregisterModelListener(
		ModelListener<SyncUser> modelListener) {

		_syncUserPersistence.unregisterModelListener(modelListener);
	}

	public static SyncUser update(SyncUser syncUser) {
		try {
			_syncUserPersistence.createOrUpdate(syncUser);

			return syncUser;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SyncUserService.class);

	private static SyncUserPersistence _syncUserPersistence =
		getSyncUserPersistence();

}