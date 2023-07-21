/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncProp;
import com.liferay.sync.engine.model.SyncPropModelListener;
import com.liferay.sync.engine.service.persistence.SyncPropPersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncPropService {

	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);

		if (value == null) {
			return defaultValue;
		}

		return Boolean.parseBoolean(value);
	}

	public static int getInteger(String key) {
		return getInteger(key, 0);
	}

	public static int getInteger(String key, int defaultValue) {
		String value = getValue(key);

		if (value == null) {
			return defaultValue;
		}

		return Integer.parseInt(value);
	}

	public static SyncPropPersistence getSyncPropPersistence() {
		if (_syncPropPersistence != null) {
			return _syncPropPersistence;
		}

		try {
			_syncPropPersistence = new SyncPropPersistence();

			registerModelListener(new SyncPropModelListener());
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncPropPersistence;
	}

	public static String getValue(String key) {
		try {
			SyncProp syncProp = _syncPropPersistence.queryForId(key);

			if (syncProp == null) {
				return null;
			}

			return syncProp.getValue();
		}
		catch (SQLException sqle) {
			return null;
		}
	}

	public static void registerModelListener(
		ModelListener<SyncProp> modelListener) {

		_syncPropPersistence.registerModelListener(modelListener);
	}

	public static SyncProp updateSyncProp(String key, Object value)
		throws Exception {

		SyncProp syncProp = new SyncProp();

		syncProp.setKey(key);
		syncProp.setValue(String.valueOf(value));

		_syncPropPersistence.createOrUpdate(syncProp);

		return syncProp;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SyncPropService.class);

	private static SyncPropPersistence _syncPropPersistence =
		getSyncPropPersistence();

}