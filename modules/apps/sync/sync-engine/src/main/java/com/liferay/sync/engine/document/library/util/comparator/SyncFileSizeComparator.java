/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.util.comparator;

import com.liferay.sync.engine.model.SyncFile;

import java.util.Comparator;

/**
 * @author Shinn Lok
 */
public class SyncFileSizeComparator implements Comparator<SyncFile> {

	@Override
	public int compare(SyncFile syncFile1, SyncFile syncFile2) {
		String event1 = syncFile1.getEvent();
		String event2 = syncFile2.getEvent();

		if (event1.equals(SyncFile.EVENT_DELETE) ||
			event1.equals(SyncFile.EVENT_MOVE) ||
			event1.equals(SyncFile.EVENT_TRASH)) {

			if (event2.equals(SyncFile.EVENT_DELETE) ||
				event2.equals(SyncFile.EVENT_MOVE) ||
				event2.equals(SyncFile.EVENT_TRASH)) {

				return 0;
			}

			return -1;
		}
		else if (event2.equals(SyncFile.EVENT_DELETE) ||
				 event2.equals(SyncFile.EVENT_MOVE) ||
				 event2.equals(SyncFile.EVENT_TRASH)) {

			return 1;
		}

		return Long.compare(syncFile1.getSize(), syncFile2.getSize());
	}

}