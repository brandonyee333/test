/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.internal.util;

import com.liferay.portal.kernel.json.JSONArray;

/**
 * @author Shinn Lok
 */
public interface TimelineProcessor {

	public JSONArray getUserTimelineJSONArray(
		String twitterScreenName, long sinceId);

}