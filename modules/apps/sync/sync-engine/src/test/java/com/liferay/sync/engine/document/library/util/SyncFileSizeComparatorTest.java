/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.util;

import com.liferay.sync.engine.document.library.util.comparator.SyncFileSizeComparator;
import com.liferay.sync.engine.model.SyncFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncFileSizeComparatorTest {

	@Test
	public void testSort() {
		SyncFile syncFile1 = addSyncFile(1, SyncFile.EVENT_ADD, 100);
		SyncFile syncFile2 = addSyncFile(2, SyncFile.EVENT_ADD, 200);
		SyncFile syncFile3 = addSyncFile(3, SyncFile.EVENT_DELETE, 0);
		SyncFile syncFile4 = addSyncFile(4, SyncFile.EVENT_TRASH, 300);
		SyncFile syncFile5 = addSyncFile(5, SyncFile.EVENT_UPDATE, 0);
		SyncFile syncFile6 = addSyncFile(6, SyncFile.EVENT_MOVE, 0);

		Collections.sort(_syncFiles, new SyncFileSizeComparator());

		Assert.assertArrayEquals(
			new SyncFile[] {
				syncFile3, syncFile4, syncFile6, syncFile5, syncFile1, syncFile2
			},
			_syncFiles.toArray());
	}

	protected SyncFile addSyncFile(long syncFileId, String event, long size) {
		SyncFile syncFile = new SyncFile();

		syncFile.setSyncFileId(syncFileId);
		syncFile.setEvent(event);
		syncFile.setSize(size);

		_syncFiles.add(syncFile);

		return syncFile;
	}

	private final List<SyncFile> _syncFiles = new ArrayList<>();

}