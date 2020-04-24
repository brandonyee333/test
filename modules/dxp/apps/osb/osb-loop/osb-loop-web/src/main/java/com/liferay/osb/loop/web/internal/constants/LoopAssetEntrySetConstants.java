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

import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetConstants;

/**
 * @author Calvin Keum
 */
public class LoopAssetEntrySetConstants extends AssetEntrySetConstants {

	public static final String LABEL_PAGE = "page";

	public static final String LABEL_POST = "post";

	public static final String PAYLOAD_KEY_CREATOR = "creator";

	public static final String PAYLOAD_KEY_LIKED_PARTICIPANTS =
		"likedParticipants";

	public static final int TYPE_ANNOUNCEMENT = 1;

	public static final int TYPE_PAGE = 2;

	public static final int TYPE_PAGE_POST = 3;

	public static final int TYPE_POST = 0;

	public static final int[] TYPES_ELIGIBLE = {
		TYPE_ANNOUNCEMENT, TYPE_PAGE_POST, TYPE_POST
	};

}