/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.internal.util;

import com.liferay.portal.kernel.json.JSONArray;

/**
 * @author Shinn Lok
 * @author Peter Fellwock
 */
public class TimelineProcessorUtil {

	public static TimelineProcessor getInstance() {
		if (_timelineProcessor == null) {
			_timelineProcessor = new HttpTimelineProcessor();
		}

		return _timelineProcessor;
	}

	public static JSONArray getUserTimelineJSONArray(
		String twitterScreenName, long sinceId) {

		return getInstance().getUserTimelineJSONArray(
			twitterScreenName, sinceId);
	}

	private static TimelineProcessor _timelineProcessor;

}