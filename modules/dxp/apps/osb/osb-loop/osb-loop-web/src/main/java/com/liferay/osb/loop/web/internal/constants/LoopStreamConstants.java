/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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