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
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

	public static String replaceSQLVariables(String sql) {
		LocalDateTime localDateTime = LocalDateTime.now(Clock.systemUTC());

		LocalDateTime newDayLocalDateTime = DateUtil.newDayLocalDateTime(
			ZoneOffset.UTC);

		return StringUtils.replaceEach(
			_replaceTimeExpressions(sql, true),
			new String[] {"${now}", "${today}"},
			new String[] {
				localDateTime.format(_dateTimeFormatter),
				newDayLocalDateTime.format(_dateTimeFormatter)
			});
	}

	public static String replaceVariables(String json) {
		return replaceVariables(json, DateUtil.newDateString());
	}

	public static String replaceVariables(String json, String newDateString) {
		return StringUtils.replaceEach(
			_replaceTimeExpressions(json, false),
			new String[] {"${now}", "${random_long}", "${today}"},
			new String[] {
				newDateString, String.valueOf(RandomUtils.nextLong()),
				DateUtil.newDayDateString()
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
			return DateUtil.newDayLocalDateTime(ZoneOffset.UTC);
		}

		return LocalDateTime.now(Clock.systemUTC());
	}

	private static String _replaceTimeExpressions(
		String resource, boolean sql) {

		StringBuffer sb = new StringBuffer();

		Matcher matcher = _timeExpressionPattern.matcher(resource);

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
			else if (sql) {
				matcher.appendReplacement(
					sb, localDateTime.format(_dateTimeFormatter));
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

	private static final DateTimeFormatter _dateTimeFormatter =
		DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS");
	private static final Pattern _timeExpressionPattern = Pattern.compile(
		"\\$\\{(now!?|today)([-+][0-9]+)([Mhdmy])(T{0,1}\\d{2}:\\d{2})?" +
			"(:\\d{2}\\.\\d{3}Z{0,1})?}");

}