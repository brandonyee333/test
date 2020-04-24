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