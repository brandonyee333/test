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

package com.liferay.osb.asah.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Shinn Lok
 */
public class StringUtil {

	public static boolean isQuoted(String s) {
		if (StringUtils.isBlank(s) || (s.length() == 1)) {
			return false;
		}

		if (((s.charAt(0) == '\'') && (s.charAt(s.length() - 1) == '\'')) ||
			((s.charAt(0) == '"') && (s.charAt(s.length() - 1) == '"'))) {

			return true;
		}

		return false;
	}

	public static Object toObject(String string) {
		if (string.startsWith("'") && string.endsWith("'")) {
			return unquoteAndDecodeInnerQuotes(string);
		}
		else if (string.equalsIgnoreCase("false")) {
			return false;
		}
		else if (string.equalsIgnoreCase("null")) {
			return null;
		}
		else if (string.equalsIgnoreCase("true")) {
			return true;
		}
		else if (string.startsWith("[")) {
			return new JSONArray(string);
		}
		else if (string.startsWith("{")) {
			return new JSONObject(string);
		}
		else if (NumberUtils.isCreatable(string)) {
			if (string.contains(".")) {
				return Double.valueOf(string);
			}

			return Long.valueOf(string);
		}

		throw new IllegalArgumentException(
			"Unknown object " + string + " used in filter");
	}

	public static String unquote(String s) {
		if (StringUtils.isBlank(s) || (s.length() == 1)) {
			return s;
		}

		if (isQuoted(s)) {
			return s.substring(1, s.length() - 1);
		}

		return s;
	}

	public static String unquoteAndDecodeInnerQuotes(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}

		s = unquote(s);

		return s.replaceAll("''", "'");
	}

}