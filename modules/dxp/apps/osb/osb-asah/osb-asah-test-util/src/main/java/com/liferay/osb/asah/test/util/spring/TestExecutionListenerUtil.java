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

package com.liferay.osb.asah.test.util.spring;

import com.liferay.osb.asah.common.date.DateUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author André Miranda
 */
public class TestExecutionListenerUtil {

	public static String replaceVariables(String json) {
		return StringUtils.replaceEach(
			_replaceTimeExpressions(json),
			new String[] {"${now}", "\"${random_long}\""},
			new String[] {
				DateUtil.newDayDateString(),
				String.valueOf(RandomUtils.nextLong())
			});
	}

	private static LocalDateTime _clampDay(
		LocalDateTime startLocalDateTime, LocalDateTime localDateTime) {

		long daysDelta = ChronoUnit.DAYS.between(
			startLocalDateTime.truncatedTo(ChronoUnit.DAYS),
			localDateTime.truncatedTo(ChronoUnit.DAYS));

		if (daysDelta == 0) {
			return localDateTime;
		}

		LocalDate localDate = startLocalDateTime.toLocalDate();

		if (daysDelta > 0) {
			return localDate.atTime(23, 59, 59);
		}

		return localDate.atStartOfDay();
	}

	private static LocalDateTime _getStartLocalDateTime(String reference) {
		if (reference.equals("today")) {
			LocalDate localDate = LocalDate.now(Clock.systemUTC());

			return localDate.atStartOfDay();
		}

		return LocalDateTime.now(Clock.systemUTC());
	}

	private static String _replaceTimeExpressions(String json) {
		StringBuffer sb = new StringBuffer();

		Matcher matcher = _timeExpressionPattern.matcher(json);

		while (matcher.find()) {
			TemporalUnit temporalUnit = _toTemporalUnit(matcher.group(3));

			String reference = matcher.group(1);

			LocalDateTime startLocalDateTime = _getStartLocalDateTime(
				reference);

			long offset = Long.parseLong(matcher.group(2));

			LocalDateTime localDateTime = temporalUnit.addTo(
				startLocalDateTime, offset);

			if (reference.contains("!")) {
				localDateTime = _clampDay(startLocalDateTime, localDateTime);
			}

			if (matcher.group(5) != null) {
				matcher.appendReplacement(
					sb,
					localDateTime.toLocalDate() + matcher.group(4) +
						matcher.group(5));
			}
			else if (matcher.group(4) != null) {
				matcher.appendReplacement(
					sb, localDateTime.toLocalDate() + matcher.group(4));
			}
			else {
				matcher.appendReplacement(
					sb, DateUtil.toUTCString(localDateTime));
			}
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	private static TemporalUnit _toTemporalUnit(String unit) {
		if (unit.equals("M")) {
			return ChronoUnit.MONTHS;
		}

		if (unit.equals("d")) {
			return ChronoUnit.DAYS;
		}

		if (unit.equals("h")) {
			return ChronoUnit.HOURS;
		}

		if (unit.equals("m")) {
			return ChronoUnit.MINUTES;
		}

		if (unit.equals("y")) {
			return ChronoUnit.YEARS;
		}

		throw new IllegalArgumentException(unit);
	}

	private static final Pattern _timeExpressionPattern = Pattern.compile(
		"\\$\\{(now!?|today)([-+][0-9]+)([Mhdmy])(T\\d{2}:\\d{2})?(:\\d{2}\\." +
			"\\d{3}Z)?}");

}