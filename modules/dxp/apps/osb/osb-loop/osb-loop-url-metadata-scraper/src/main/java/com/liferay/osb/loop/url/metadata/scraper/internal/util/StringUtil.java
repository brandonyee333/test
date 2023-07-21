/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.url.metadata.scraper.internal.util;

/**
 * @author Evan Thibodeau
 */
public class StringUtil {

	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	public static String shorten(String s, int length, String suffix) {
		if ((s == null) || (suffix == null)) {
			return null;
		}

		if (s.length() <= length) {
			return s;
		}

		if (length < suffix.length()) {
			return s.substring(0, length);
		}

		int curLength = length;

		for (int j = curLength - suffix.length(); j >= 0; j--) {
			if (Character.isWhitespace(s.charAt(j))) {
				curLength = j;

				break;
			}
		}

		if (curLength == length) {
			curLength = length - suffix.length();
		}

		String temp = s.substring(0, curLength);

		return temp.concat(suffix);
	}

}