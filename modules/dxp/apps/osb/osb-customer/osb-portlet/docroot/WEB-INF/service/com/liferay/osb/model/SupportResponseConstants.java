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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

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
		return getSupportLevelLabel(supportLevel, LocaleUtil.US);
	}

	public static String getSupportLevelLabel(int supportLevel, Locale locale) {
		if (supportLevel == SUPPORT_LEVEL_FLOATING) {
			return "floating";
		}
		else if (supportLevel == SUPPORT_LEVEL_GOLD) {
			if (locale.equals(LocaleUtil.JAPAN)) {
				return "light";
			}

			return "gold";
		}
		else if (supportLevel == SUPPORT_LEVEL_LIMITED) {
			return "limited";
		}
		else if (supportLevel == SUPPORT_LEVEL_NA) {
			return StringPool.BLANK;
		}
		else if (supportLevel == SUPPORT_LEVEL_PLATINUM) {
			if (locale.equals(LocaleUtil.JAPAN)) {
				return "standard";
			}

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