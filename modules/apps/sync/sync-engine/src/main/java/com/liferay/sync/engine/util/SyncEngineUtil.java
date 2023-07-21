/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import com.liferay.sync.engine.listener.SyncEngineListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shinn Lok
 */
public class SyncEngineUtil {

	public static final int SYNC_ENGINE_STATE_PROCESSED = 6;

	public static final int SYNC_ENGINE_STATE_PROCESSING = 5;

	public static final int SYNC_ENGINE_STATE_STARTED = 1;

	public static final int SYNC_ENGINE_STATE_STARTING = 2;

	public static final int SYNC_ENGINE_STATE_STOPPED = 3;

	public static final int SYNC_ENGINE_STATE_STOPPING = 4;

	public static final int SYNC_ENGINE_UPDATE_AVAILABLE = 7;

	public static void fireSyncEngineStateChanged(final int syncEngineState) {
		fireSyncEngineStateChanged(0, syncEngineState);
	}

	public static void fireSyncEngineStateChanged(
		final long syncAccountId, final int syncEngineState) {

		for (final SyncEngineListener syncEngineListener :
				_syncEngineListeners) {

			_executorService.execute(
				new Runnable() {

					@Override
					public void run() {
						syncEngineListener.syncEngineStateChanged(
							syncAccountId, syncEngineState);
					}

				});
		}
	}

	public static void registerSyncEngineListener(
		SyncEngineListener syncEngineListener) {

		_syncEngineListeners.add(syncEngineListener);
	}

	public static void unregisterSyncEngineListener(
		SyncEngineListener syncEngineListener) {

		_syncEngineListeners.remove(syncEngineListener);
	}

	private static final ExecutorService _executorService =
		Executors.newSingleThreadScheduledExecutor();
	private static final List<SyncEngineListener> _syncEngineListeners =
		new ArrayList<>();

}