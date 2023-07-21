/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.util;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.model.SyncFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shinn Lok
 */
public class FileEventManager {

	public static void addEvent(Event event) {
		synchronized (_eventMap) {
			SyncFile syncFile = (SyncFile)event.getParameterValue("syncFile");

			if (syncFile == null) {
				return;
			}

			Set<Event> events = _eventMap.get(syncFile.getSyncFileId());

			if (events == null) {
				events = Collections.newSetFromMap(
					new ConcurrentHashMap<Event, Boolean>());

				_eventMap.put(syncFile.getSyncFileId(), events);
			}

			events.add(event);
		}
	}

	public static Set<Event> getEvents(long syncFileId) {
		Set<Event> events = _eventMap.get(syncFileId);

		if (events == null) {
			return new HashSet<>();
		}

		return events;
	}

	public static void removeEvent(Event event) {
		synchronized (_eventMap) {
			SyncFile syncFile = (SyncFile)event.getParameterValue("syncFile");

			if (syncFile == null) {
				return;
			}

			Set<Event> events = _eventMap.get(syncFile.getSyncFileId());

			if (events == null) {
				return;
			}

			events.remove(event);

			if (events.isEmpty()) {
				_eventMap.remove(syncFile.getSyncFileId());
			}
		}
	}

	private static final Map<Long, Set<Event>> _eventMap = new HashMap<>();

}