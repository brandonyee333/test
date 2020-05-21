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

package com.liferay.osb.asah.backend.dog.helper;

import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.model.VisitorCohortMetric;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.petra.string.StringPool;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 * @author Rachael Koestartyo
 */
@Component
public class MetricHelper {

	public Map<String, Metric> createMetrics(
		Clock clock, Interval interval, TimeRange timeRange,
		MetricType metricType) {

		Map<String, Metric> metrics = new LinkedHashMap<>();

		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(clock), LocalTime.MIDNIGHT);

		if (!timeRange.getIncludeToday() &&
			!TimeRange.YESTERDAY.equals(timeRange)) {

			localDateTime = localDateTime.minusDays(1);
		}

		if (TimeRange.LAST_24_HOURS.equals(timeRange)) {
			localDateTime = LocalDateTime.now(clock);

			localDateTime = localDateTime.withMinute(0);
			localDateTime = localDateTime.withNano(0);
			localDateTime = localDateTime.withSecond(0);

			for (int i = 23; i >= 0; i--) {
				LocalDateTime periodLocalDateTime = localDateTime.minusHours(i);

				metrics.put(
					String.valueOf(periodLocalDateTime),
					_createMetric(
						periodLocalDateTime, interval, metricType, timeRange));
			}
		}
		else if (TimeRange.LAST_28_DAYS.equals(timeRange)) {
			_addMetric(
				28, interval, localDateTime, metricType, metrics, timeRange);
		}
		else if (TimeRange.LAST_30_DAYS.equals(timeRange)) {
			_addMetric(
				30, interval, localDateTime, metricType, metrics, timeRange);
		}
		else if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
			_addMetric(
				7, interval, localDateTime, metricType, metrics, timeRange);
		}
		else if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			_addMetric(
				90, interval, localDateTime, metricType, metrics, timeRange);
		}
		else if (TimeRange.YESTERDAY.equals(timeRange)) {
			for (int i = 24; i > 0; i--) {
				LocalDateTime periodLocalDateTime = localDateTime.minusHours(i);

				metrics.put(
					String.valueOf(periodLocalDateTime),
					_createMetric(
						periodLocalDateTime, interval, metricType, timeRange));
			}
		}
		else {
			_addMetric(
				timeRange.getDeltaDays(), interval, localDateTime, metricType,
				metrics, timeRange);
		}

		return metrics;
	}

	public Map<String, VisitorCohortMetric> createVisitorCohortMetrics(
		Clock clock, Interval interval, MetricType metricType) {

		Map<String, VisitorCohortMetric> visitorCohortMetrics =
			new LinkedHashMap<>();

		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(clock), LocalTime.MIDNIGHT);

		if (Interval.DAY.equals(interval)) {
			for (int i = 7; i >= 0; i--) {
				ZonedDateTime zonedDateTime = ZonedDateTime.of(
					localDateTime, clock.getZone());

				zonedDateTime = zonedDateTime.minusDays(i);

				visitorCohortMetrics.put(
					String.valueOf(zonedDateTime.toLocalDate()),
					new VisitorCohortMetric(
						Collections.emptySet(),
						String.valueOf(zonedDateTime.toLocalDate()), metricType,
						0.0));
			}
		}
		else if (Interval.MONTH.equals(interval)) {
			localDateTime = localDateTime.withDayOfMonth(1);

			for (int i = 6; i >= 0; i--) {
				ZonedDateTime zonedDateTime = ZonedDateTime.of(
					localDateTime, clock.getZone());

				zonedDateTime = zonedDateTime.minusMonths(i);

				visitorCohortMetrics.put(
					String.valueOf(zonedDateTime.toLocalDate()),
					new VisitorCohortMetric(
						Collections.emptySet(),
						String.valueOf(zonedDateTime.toLocalDate()), metricType,
						0.0));
			}
		}
		else if (Interval.WEEK.equals(interval)) {
			localDateTime = localDateTime.with(DayOfWeek.MONDAY);

			localDateTime = localDateTime.minusDays(1);

			for (int i = 6; i >= 0; i--) {
				ZonedDateTime zonedDateTime = ZonedDateTime.of(
					localDateTime, clock.getZone());

				zonedDateTime = zonedDateTime.minusWeeks(i);

				visitorCohortMetrics.put(
					String.valueOf(zonedDateTime.toLocalDate()),
					new VisitorCohortMetric(
						Collections.emptySet(),
						String.valueOf(zonedDateTime.toLocalDate()), metricType,
						0.0));
			}
		}

		return visitorCohortMetrics;
	}

	public LocalDateTime getPreviousPeriodLocalDateTime(
		LocalDateTime currentPeriodLocalDateTime, Interval interval,
		TimeRange timeRange) {

		LocalDateTime previousPeriodLocalDateTime = LocalDateTime.from(
			currentPeriodLocalDateTime);

		if (TimeRange.LAST_24_HOURS.equals(timeRange)) {
			return previousPeriodLocalDateTime.minusDays(1);
		}
		else if (Interval.DAY.equals(interval)) {
			return previousPeriodLocalDateTime.minusDays(
				timeRange.getDeltaDays());
		}
		else if (Interval.WEEK.equals(interval)) {
			if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
				return previousPeriodLocalDateTime.minusWeeks(1);
			}
			else if (TimeRange.LAST_28_DAYS.equals(timeRange) ||
					 TimeRange.LAST_30_DAYS.equals(timeRange)) {

				return previousPeriodLocalDateTime.minusWeeks(4);
			}
			else if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
				return previousPeriodLocalDateTime.minusWeeks(13);
			}
		}
		else if (Interval.MONTH.equals(interval)) {
			previousPeriodLocalDateTime = previousPeriodLocalDateTime.minusDays(
				timeRange.getDeltaDays() - 1);

			return previousPeriodLocalDateTime.withDayOfMonth(1);
		}

		return previousPeriodLocalDateTime;
	}

	private void _addMetric(
		long days, Interval interval, LocalDateTime localDateTime,
		MetricType metricType, Map<String, Metric> metrics,
		TimeRange timeRange) {

		LocalDate localDate = localDateTime.toLocalDate();

		if (Interval.WEEK.equals(interval)) {
			if (localDateTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
				localDateTime = localDateTime.minusWeeks(1);
				localDateTime = localDateTime.with(DayOfWeek.SUNDAY);
			}

			for (int i = _countWeeks(days, localDate) - 1; i >= 0; i--) {
				LocalDateTime periodLocalDateTime = localDateTime.minusWeeks(i);

				Metric metric = _createMetric(
					periodLocalDateTime, interval, metricType, timeRange);

				metric.setPreviousValueKey(
					_getWeekPreviousValueKey(
						periodLocalDateTime.toLocalDate(), days));

				metric.setValueKey(
					_getWeekValueKey(periodLocalDateTime.toLocalDate()));

				metrics.put(String.valueOf(periodLocalDateTime), metric);
			}
		}
		else if (Interval.MONTH.equals(interval)) {
			if (localDateTime.getDayOfMonth() != 1) {
				localDateTime = localDateTime.withDayOfMonth(1);
			}

			for (int i = _countMonths(days, localDate) - 1; i >= 0; i--) {
				LocalDateTime periodLocalDateTime = localDateTime.minusMonths(
					i);

				Metric metric = _createMetric(
					periodLocalDateTime, interval, metricType, timeRange);

				metric.setPreviousValueKey(
					_getMonthPreviousValueKey(
						days, periodLocalDateTime.toLocalDate()));

				metric.setValueKey(
					_getMonthValueKey(periodLocalDateTime.toLocalDate()));

				metrics.put(String.valueOf(periodLocalDateTime), metric);
			}
		}
		else {
			LocalDate periodLocalDate = timeRange.getStartLocalDate();

			LocalDateTime periodLocalDateTime = periodLocalDate.atStartOfDay();

			LocalDateTime endLocalDateTime = timeRange.getEndLocalDateTime();

			while (!periodLocalDateTime.isAfter(endLocalDateTime)) {
				metrics.put(
					String.valueOf(periodLocalDateTime),
					_createMetric(
						periodLocalDateTime, interval, metricType, timeRange));

				periodLocalDateTime = periodLocalDateTime.plusDays(1);
			}
		}
	}

	private int _countMonths(long days, LocalDate localDate) {
		LocalDate startLocalDate = localDate.minusDays(days - 1);

		return DateUtil.getDeltaMonths(startLocalDate, localDate);
	}

	private int _countWeeks(long days, LocalDate localDate) {
		LocalDate startLocalDate = localDate.minusDays(days - 1);

		return DateUtil.getDeltaWeeks(startLocalDate, localDate);
	}

	private Metric _createMetric(
		LocalDateTime currentPeriodLocalDateTime, Interval interval,
		MetricType metricType, TimeRange timeRange) {

		Metric metric = new Metric(metricType);

		metric.setPreviousValue(0.0);

		LocalDateTime previousPeriodLocalDateTime =
			getPreviousPeriodLocalDateTime(
				currentPeriodLocalDateTime, interval, timeRange);

		metric.setPreviousValueKey(previousPeriodLocalDateTime.toString());

		metric.setValue(0.0);
		metric.setValueKey(currentPeriodLocalDateTime.toString());

		return metric;
	}

	private String _getMonthPreviousValueKey(
		long days, LocalDate currentLocalDate) {

		currentLocalDate = currentLocalDate.minusMonths(1);

		currentLocalDate = currentLocalDate.minusDays(days - 1);

		LocalDate firstMonthDayLocalDate = currentLocalDate.withDayOfMonth(1);

		LocalDate lastMonthDayLocalDate = firstMonthDayLocalDate.withDayOfMonth(
			firstMonthDayLocalDate.lengthOfMonth());

		return firstMonthDayLocalDate + StringPool.SLASH +
			lastMonthDayLocalDate;
	}

	private String _getMonthValueKey(LocalDate currentLocalDate) {
		LocalDate firstMonthDayLocalDate = currentLocalDate.withDayOfMonth(1);

		LocalDate lastMonthDayLocalDate = currentLocalDate.withDayOfMonth(
			firstMonthDayLocalDate.lengthOfMonth());

		if (firstMonthDayLocalDate.isEqual(lastMonthDayLocalDate)) {
			return String.valueOf(firstMonthDayLocalDate);
		}

		return firstMonthDayLocalDate + StringPool.SLASH +
			lastMonthDayLocalDate;
	}

	private String _getWeekPreviousValueKey(
		LocalDate currentLocalDate, long days) {

		currentLocalDate = currentLocalDate.minusWeeks(1);

		currentLocalDate = currentLocalDate.minusDays(days);

		LocalDate firstWeekdayLocalDate = currentLocalDate.with(
			DayOfWeek.SUNDAY);

		LocalDate lastWeekdayLocalDate = firstWeekdayLocalDate.plusDays(6);

		return firstWeekdayLocalDate + StringPool.SLASH + lastWeekdayLocalDate;
	}

	private String _getWeekValueKey(LocalDate currentLocalDate) {
		LocalDate lastWeekdayLocalDate = currentLocalDate.plusDays(6);

		if (currentLocalDate.isEqual(lastWeekdayLocalDate)) {
			return String.valueOf(currentLocalDate);
		}

		return currentLocalDate + StringPool.SLASH + lastWeekdayLocalDate;
	}

}