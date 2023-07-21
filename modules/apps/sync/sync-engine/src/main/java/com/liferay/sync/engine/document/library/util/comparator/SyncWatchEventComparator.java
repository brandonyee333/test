/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.util.comparator;

import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncWatchEvent;

import java.util.Comparator;

/**
 * @author Dennis Ju
 */
public class SyncWatchEventComparator implements Comparator<SyncWatchEvent> {

	@Override
	public int compare(
		SyncWatchEvent syncWatchEvent1, SyncWatchEvent syncWatchEvent2) {

		String fileType1 = syncWatchEvent1.getFileType();
		String fileType2 = syncWatchEvent2.getFileType();

		if (!fileType1.equals(fileType2)) {
			if (fileType1.equals(SyncFile.TYPE_FOLDER)) {
				return -1;
			}

			return 1;
		}

		String eventType1 = syncWatchEvent1.getEventType();
		String eventType2 = syncWatchEvent2.getEventType();

		if (eventType1.equals(SyncWatchEvent.EVENT_TYPE_CREATE) &&
			eventType2.equals(SyncWatchEvent.EVENT_TYPE_DELETE)) {

			return -1;
		}
		else if (eventType1.equals(SyncWatchEvent.EVENT_TYPE_DELETE) &&
				 eventType2.equals(SyncWatchEvent.EVENT_TYPE_CREATE)) {

			return 1;
		}

		return Long.compare(
			syncWatchEvent1.getSyncWatchEventId(),
			syncWatchEvent2.getSyncWatchEventId());
	}

}