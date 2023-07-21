/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.file.system.util;

import com.liferay.sync.engine.file.system.BarbaryWatcher;
import com.liferay.sync.engine.file.system.DummyWatcher;
import com.liferay.sync.engine.file.system.JPathWatcher;
import com.liferay.sync.engine.file.system.Watcher;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.util.OSDetector;

import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class WatcherManager {

	public static synchronized Watcher getWatcher(long syncAccountId) {
		Watcher watcher = _watchers.get(syncAccountId);

		if (watcher != null) {
			return watcher;
		}

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccountId);

		if (syncAccount == null) {
			return _dummyWatcher;
		}

		if (OSDetector.isApple()) {
			watcher = new BarbaryWatcher(
				syncAccount.getSyncAccountId(),
				Paths.get(syncAccount.getFilePathName()));
		}
		else {
			watcher = new JPathWatcher(
				syncAccount.getSyncAccountId(),
				Paths.get(syncAccount.getFilePathName()));
		}

		_watchers.put(syncAccountId, watcher);

		return watcher;
	}

	public static synchronized void removeWatcher(long syncAccountId) {
		_watchers.remove(syncAccountId);
	}

	private static final Watcher _dummyWatcher = new DummyWatcher();
	private static final Map<Long, Watcher> _watchers = new HashMap<>();

}