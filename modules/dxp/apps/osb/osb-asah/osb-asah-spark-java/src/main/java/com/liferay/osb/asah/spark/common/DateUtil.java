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

package com.liferay.osb.asah.spark.common;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.TimeZone;

/**
 * @author Robson Pastor
 */
public class DateUtil {

	public static Timestamp max(Timestamp timestamp1, Timestamp timestamp2) {
		if (timestamp1.after(timestamp2)) {
			return timestamp1;
		}

		return timestamp2;
	}

	public static Timestamp min(Timestamp timestamp1, Timestamp timestamp2) {
		if (timestamp1.before(timestamp2)) {
			return timestamp1;
		}

		return timestamp2;
	}

	public static Date toDate(String dateString) {
		java.util.Date date = _parseDate(dateString, _simpleDateFormat);

		return new Date(date.getTime());
	}

	public static Timestamp toTimestamp(String timestampString) {
		java.util.Date date = _parseDate(
			timestampString, _simpleTimestampFormat);

		return new Timestamp(date.getTime());
	}

	private static java.util.Date _parseDate(
		String dateString, SimpleDateFormat simpleDateFormat) {

		try {
			return simpleDateFormat.parse(dateString);
		}
		catch (ParseException parseException) {
			throw new RuntimeException(parseException);
		}
	}

	private static final SimpleDateFormat _simpleDateFormat =
		new SimpleDateFormat("yyyy-MM-dd") {
			{
				setTimeZone(TimeZone.getTimeZone("GMT"));
			}
		};
	private static final SimpleDateFormat _simpleTimestampFormat =
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") {
			{
				setTimeZone(TimeZone.getTimeZone("GMT"));
			}
		};

}