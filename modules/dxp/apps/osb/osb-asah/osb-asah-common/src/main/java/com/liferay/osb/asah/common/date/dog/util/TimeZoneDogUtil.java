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

package com.liferay.osb.asah.common.date.dog.util;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;

import java.time.ZoneId;

/**
 * @author Geyson Silva
 */
public class TimeZoneDogUtil {

	public static String getTimeZoneId() {
		if (_timeZoneDogStatic == null) {
			return "UTC";
		}

		return _timeZoneDogStatic.getTimeZoneId();
	}

	public static ZoneId getZoneId() {
		if (_timeZoneDogStatic == null) {
			return ZoneId.of("UTC");
		}

		return _timeZoneDogStatic.getZoneId();
	}

	public static void setTimeZoneDog(TimeZoneDog timeZoneDog) {
		_timeZoneDogStatic = timeZoneDog;
	}

	private static TimeZoneDog _timeZoneDogStatic;

}