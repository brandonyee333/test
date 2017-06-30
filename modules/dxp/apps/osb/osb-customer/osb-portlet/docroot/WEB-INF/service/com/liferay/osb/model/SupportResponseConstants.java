/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class SupportResponseConstants {

	public static final int[] SEVERITIES = {
		SupportResponseConstants.SEVERITY_CRITICAL,
		SupportResponseConstants.SEVERITY_MAJOR,
		SupportResponseConstants.SEVERITY_MINOR
	};

	public static final int SEVERITY_CRITICAL = 3;

	public static final int SEVERITY_MAJOR = 2;

	public static final int SEVERITY_MINOR = 1;

	public static final int SUPPORT_LEVEL_FLOATING = 1;

	public static final int SUPPORT_LEVEL_GOLD = 30;

	public static final int SUPPORT_LEVEL_LIMITED = 10;

	public static final int SUPPORT_LEVEL_NA = 0;

	public static final int SUPPORT_LEVEL_PLATINUM = 40;

	public static final int SUPPORT_LEVEL_SILVER = 20;

	public static final int[] SUPPORT_LEVELS = {
		SUPPORT_LEVEL_NA, SUPPORT_LEVEL_PLATINUM, SUPPORT_LEVEL_GOLD,
		SUPPORT_LEVEL_SILVER, SUPPORT_LEVEL_LIMITED, SUPPORT_LEVEL_FLOATING
	};

	public static String getSupportLevelLabel(int supportLevel) {
		if (supportLevel == SUPPORT_LEVEL_FLOATING) {
			return "floating";
		}
		else if (supportLevel == SUPPORT_LEVEL_GOLD) {
			return "gold";
		}
		else if (supportLevel == SUPPORT_LEVEL_LIMITED) {
			return "limited";
		}
		else if (supportLevel == SUPPORT_LEVEL_NA) {
			return StringPool.BLANK;
		}
		else if (supportLevel == SUPPORT_LEVEL_PLATINUM) {
			return "platinum";
		}
		else if (supportLevel == SUPPORT_LEVEL_SILVER) {
			return "silver";
		}
		else {
			return StringPool.BLANK;
		}
	}

}