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

package com.liferay.osb.asah.stream.curator.bot.nanite.util;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.NavigableSet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Marcellus Tavares
 */
public class NaniteUtil {

	public static String digest(Object... objects) {
		StringBuilder sb = new StringBuilder();

		for (Object object : objects) {
			if (object instanceof Date) {
				Date date = (Date)object;

				sb.append(DateUtil.toUTCString(date));
			}
			else {
				sb.append(object);
			}
		}

		return DigestUtils.sha256Hex(sb.toString());
	}

	public static Integer getReadPercentile(String depthString) {
		Integer depth = Integer.valueOf(depthString);

		if (depth <= 25) {
			return 25;
		}

		if (depth <= 50) {
			return 50;
		}

		if (depth <= 75) {
			return 75;
		}

		return 100;
	}

	public static long getSubmissionsTime(
		Date eventDate, NavigableSet<Date> submissionDates,
		NavigableSet<Date> viewDates) {

		long submissionsTime = 0;

		for (Date submissionDate : submissionDates) {
			Date viewDate = viewDates.lower(submissionDate);

			if (viewDate != null) {
				submissionsTime +=
					submissionDate.getTime() - viewDate.getTime();
			}
			else {
				submissionsTime +=
					submissionDate.getTime() - eventDate.getTime();
			}
		}

		return submissionsTime;
	}

}