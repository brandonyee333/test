/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncAccountModelListenerTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncAccountModelListener = new SyncAccountModelListener();

		SyncAccountService.registerModelListener(_syncAccountModelListener);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		try {
			SyncAccountService.resetActiveSyncAccountIds();

			SyncAccountService.unregisterModelListener(
				_syncAccountModelListener);
		}
		finally {
			super.tearDown();
		}
	}

	@Test
	public void testOnRemove() throws Exception {
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(
			activeSyncAccountIds.toString(), 1, activeSyncAccountIds.size());

		SyncAccountService.deleteSyncAccount(syncAccount.getSyncAccountId());

		activeSyncAccountIds = SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(
			activeSyncAccountIds.toString(), 0, activeSyncAccountIds.size());
	}

	@Test
	public void testOnUpdate() throws Exception {
		Set<Long> activeSyncAccountIds =
			SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(
			activeSyncAccountIds.toString(), 1, activeSyncAccountIds.size());

		syncAccount.setActive(false);

		SyncAccountService.update(syncAccount);

		activeSyncAccountIds = SyncAccountService.getActiveSyncAccountIds();

		Assert.assertEquals(
			activeSyncAccountIds.toString(), 0, activeSyncAccountIds.size());
	}

	private SyncAccountModelListener _syncAccountModelListener;

}