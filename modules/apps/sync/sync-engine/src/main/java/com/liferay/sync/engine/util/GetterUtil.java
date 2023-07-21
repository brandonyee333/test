/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

/**
 * @author Shinn Lok
 */
public class GetterUtil {

	public static final boolean DEFAULT_BOOLEAN = false;

	public static final int DEFAULT_INTEGER = 0;

	public static final long DEFAULT_LONG = 0;

	public static boolean getBoolean(Object value) {
		return getBoolean(String.valueOf(value));
	}

	public static boolean getBoolean(String value) {
		return getBoolean(value, DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(String value, boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		return Boolean.valueOf(value);
	}

	public static int getInteger(String value) {
		return getInteger(value, DEFAULT_INTEGER);
	}

	public static int getInteger(String value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(value);
		}
		catch (NumberFormatException nfe) {
			return defaultValue;
		}
	}

	public static long getLong(String value) {
		return getLong(value, DEFAULT_LONG);
	}

	public static long getLong(String value, long defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		try {
			return Long.parseLong(value);
		}
		catch (NumberFormatException nfe) {
			return defaultValue;
		}
	}

}