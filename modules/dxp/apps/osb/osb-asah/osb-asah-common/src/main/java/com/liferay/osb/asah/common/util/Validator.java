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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Rachael Koestartyo
 */
public class Validator {

	public static boolean isBoolean(String value) {
		return ArrayUtil.contains(_BOOLEANS, value.toLowerCase());
	}

	public static boolean isDate(String value) {
		try {
			_dateFormat.parse(value);

			return true;
		}
		catch (ParseException parseException) {
			return false;
		}
	}

	public static boolean isNumber(String value) {
		return NumberUtils.isParsable(value);
	}

	private static final String[] _BOOLEANS = {"false", "true"};

	private static final DateFormat _dateFormat = new SimpleDateFormat(
		DateUtil.PATTERN_ISO_8601);

}