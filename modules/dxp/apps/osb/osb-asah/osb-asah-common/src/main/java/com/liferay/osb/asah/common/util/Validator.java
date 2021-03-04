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

import com.liferay.osb.asah.common.date.DateUtil;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rachael Koestartyo
 */
public class Validator {

	public static boolean isBoolean(String value) {
		return ArrayUtil.contains(_BOOLEANS, value.toLowerCase());
	}

	public static boolean isDate(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		if (isDateDayMonthYear(value) || isDateMonthDayYear(value) ||
			isDateYearDayMonth(value) || isDateYearMonthDay(value)) {

			try {
				if (DateUtil.getLocalDateTime(value) != null) {
					return true;
				}
			}
			catch (Exception exception) {
				return false;
			}
		}

		return false;
	}

	public static boolean isDateDayMonthYear(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		if (value.matches(
				"^" + _REGEX_DAY + _REGEX_SEPARATOR + _REGEX_MONTH +
					_REGEX_SEPARATOR + _REGEX_YEAR + _REGEX_TIME)) {

			return true;
		}

		return false;
	}

	public static boolean isDateMonthDayYear(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		if (value.matches(
				"^" + _REGEX_MONTH + _REGEX_SEPARATOR + _REGEX_DAY +
					_REGEX_SEPARATOR + _REGEX_YEAR + _REGEX_TIME)) {

			return true;
		}

		return false;
	}

	public static boolean isDateYearDayMonth(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		if (value.matches(
				"^" + _REGEX_YEAR + _REGEX_SEPARATOR + _REGEX_DAY +
					_REGEX_SEPARATOR + _REGEX_MONTH + _REGEX_TIME)) {

			return true;
		}

		return false;
	}

	public static boolean isDateYearMonthDay(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		if (value.matches(
				"^" + _REGEX_YEAR + _REGEX_SEPARATOR + _REGEX_MONTH +
					_REGEX_SEPARATOR + _REGEX_DAY + _REGEX_TIME)) {

			return true;
		}

		return false;
	}

	public static boolean isDigit(char c) {
		int x = c;

		if ((x >= _DIGIT_BEGIN) && (x <= _DIGIT_END)) {
			return true;
		}

		return false;
	}

	public static boolean isDouble(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		try {
			Double.parseDouble(value.trim());

			return true;
		}
		catch (NumberFormatException numberFormatException) {
			return false;
		}
	}

	public static boolean isNumber(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		}

		for (char c : value.toCharArray()) {
			if (!isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	private static final String[] _BOOLEANS = {"false", "true"};

	private static final int _DIGIT_BEGIN = 48;

	private static final int _DIGIT_END = 57;

	private static final String _REGEX_DAY = "(0?[1-9]|[12][0-9]|3[01])";

	private static final String _REGEX_MONTH = "(0?[1-9]|1[0-2])";

	private static final String _REGEX_SEPARATOR = "[-/ ]?";

	private static final String _REGEX_TIME =
		"((T| )?(00|0?[0-9]|1[0-9]|2[0-3]):?([0-9]|[0-5][0-9])(:?" +
			"(:?([0-9]|[0-5][0-9])(.?[0-9]{3})?((z|Z)|([+-][0-9]{1,2}:?" +
				"(00|30)))?)?)?$";

	private static final String _REGEX_YEAR = "\\d\\d\\d\\d";

}