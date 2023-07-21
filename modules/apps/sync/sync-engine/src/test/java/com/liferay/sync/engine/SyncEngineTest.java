/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine;

import com.liferay.sync.engine.listener.SyncEngineListener;
import com.liferay.sync.engine.util.SyncEngineUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncEngineTest {

	@Test
	public void testStop() throws Exception {
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		SyncEngineListener syncEngineListener = new SyncEngineListener() {

			@Override
			public void syncEngineStateChanged(
				long syncAccountId, int syncEngineState) {

				if (syncEngineState ==
						SyncEngineUtil.SYNC_ENGINE_STATE_STOPPED) {

					_stopped = true;

					countDownLatch.countDown();
				}
			}

		};

		SyncEngineUtil.registerSyncEngineListener(syncEngineListener);

		SyncEngine.start();

		SyncEngine.stop();

		countDownLatch.await(5, TimeUnit.SECONDS);

		Assert.assertTrue(_stopped);
	}

	private boolean _stopped;

}