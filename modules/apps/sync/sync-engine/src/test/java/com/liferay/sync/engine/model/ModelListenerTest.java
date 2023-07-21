/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.test.SyncFileTestUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class ModelListenerTest extends BaseTestCase {

	@Test
	public void testUpdateFilePathNameSyncFile() throws Exception {
		SyncFileService.registerModelListener(new SyncFileModelListener());

		SyncFile syncFile = SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "test"), 0,
			syncAccount.getSyncAccountId());

		Assert.assertTrue(_onCreateCalled);

		syncFile.setFilePathName(
			FileUtil.getFilePathName(filePathName, "test2"));
		syncFile.setSize(256);

		SyncFileService.update(syncFile);

		Assert.assertEquals(
			FileUtil.getFilePathName(filePathName, "test"),
			_originalFieldValues.get("filePathName"));
		Assert.assertEquals(
			_originalFieldValues.toString(), 2, _originalFieldValues.size());

		SyncFileService.deleteSyncFile(syncFile);

		Assert.assertTrue(_onRemoveCalled);
	}

	private boolean _onCreateCalled;
	private boolean _onRemoveCalled;
	private Map<String, Object> _originalFieldValues = new HashMap<>();

	private class SyncFileModelListener implements ModelListener<SyncFile> {

		@Override
		public void onCreate(SyncFile model) {
			_onCreateCalled = true;
		}

		@Override
		public void onRemove(SyncFile model) {
			_onRemoveCalled = true;
		}

		@Override
		public void onUpdate(
			SyncFile syncFile, Map<String, Object> originalValues) {

			_originalFieldValues = originalValues;
		}

	}

}