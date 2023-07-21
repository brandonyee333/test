/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.constants;

/**
 * @author Timothy Bell
 */
public class LoopStreamConstants {

	public static final long LOOP_STREAM_ALIAS_ID_ANNOUNCEMENTS = 2;

	public static final long LOOP_STREAM_ALIAS_ID_ANNOUNCEMENTS_STICKY = 3;

	public static final long LOOP_STREAM_ALIAS_ID_BOOKMARKS = 1;

	public static final long LOOP_STREAM_ALIAS_ID_FOLLOWING = 0;

	public static final long LOOP_STREAM_ALIAS_ID_PRIVATE = 4;

	public static final long LOOP_STREAM_ID_BOOKMARKS = 1;

	public static final long LOOP_STREAM_ID_FOLLOWING = 0;

	public static long getLoopStreamId(long loopStreamAliasId) {
		if (loopStreamAliasId == LOOP_STREAM_ALIAS_ID_BOOKMARKS) {
			return LoopStreamConstants.LOOP_STREAM_ID_BOOKMARKS;
		}

		return LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING;
	}

}