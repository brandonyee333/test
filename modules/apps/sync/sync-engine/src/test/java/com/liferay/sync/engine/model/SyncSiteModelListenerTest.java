/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.test.SyncSiteTestUtil;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncSiteModelListenerTest extends BaseTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_syncSiteModelListener = new SyncSiteModelListener();

		SyncSiteService.registerModelListener(_syncSiteModelListener);

		_syncSite = SyncSiteTestUtil.addSyncSite(
			10158, FileUtil.getFilePathName(filePathName, "test-site"), 10185,
			syncAccount.getSyncAccountId());

		_syncSite.setActive(true);

		SyncSiteService.update(_syncSite);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		try {
			SyncSiteService.unregisterModelListener(_syncSiteModelListener);

			_syncSite = SyncSiteService.fetchSyncSite(
				_syncSite.getSyncSiteId());

			if (_syncSite != null) {
				SyncSiteService.deleteSyncSite(_syncSite.getSyncSiteId());
			}
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
		finally {
			super.tearDown();
		}
	}

	@Test
	public void testOnRemove() throws Exception {
		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(
			activeSyncSiteIds.toString(), 1, activeSyncSiteIds.size());

		SyncSiteService.deleteSyncSite(_syncSite.getSyncSiteId());

		activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(
			activeSyncSiteIds.toString(), 0, activeSyncSiteIds.size());
	}

	@Test
	public void testOnUpdate() throws Exception {
		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(
			activeSyncSiteIds.toString(), 1, activeSyncSiteIds.size());

		_syncSite.setActive(false);

		SyncSiteService.update(_syncSite);

		activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncAccount.getSyncAccountId());

		Assert.assertEquals(
			activeSyncSiteIds.toString(), 0, activeSyncSiteIds.size());
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SyncSiteModelListenerTest.class);

	private SyncSite _syncSite;
	private SyncSiteModelListener _syncSiteModelListener;

}