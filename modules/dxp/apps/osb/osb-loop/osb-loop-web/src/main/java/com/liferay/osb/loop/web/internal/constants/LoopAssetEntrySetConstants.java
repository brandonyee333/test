/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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