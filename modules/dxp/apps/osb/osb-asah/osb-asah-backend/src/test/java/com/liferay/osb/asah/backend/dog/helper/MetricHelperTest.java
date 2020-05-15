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

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mock;

/**
 * @author Marcellus Tavares
 */
public class MetricHelperTest {

	@Test
	public void testCreateMetricsLast7Days() {
		List<String> expectedKeys = Arrays.asList(
			"2018-03-25T00:00", "2018-03-26T00:00", "2018-03-27T00:00",
			"2018-03-28T00:00", "2018-03-29T00:00", "2018-03-30T00:00",
			"2018-03-31T00:00");

		List<String> actualKeys = _createMetricsKeys(
			Interval.DAY, LocalDateTime.of(2018, 4, 1, 10, 30),
			TimeRange.LAST_7_DAYS);

		Assert.assertEquals(expectedKeys.toString(), expectedKeys, actualKeys);
	}

	@Test
	public void testCreateMetricsLast24Hours() {
		List<String> expectedKeys = Arrays.asList(
			"2018-03-31T11:00", "2018-03-31T12:00", "2018-03-31T13:00",
			"2018-03-31T14:00", "2018-03-31T15:00", "2018-03-31T16:00",
			"2018-03-31T17:00", "2018-03-31T18:00", "2018-03-31T19:00",
			"2018-03-31T20:00", "2018-03-31T21:00", "2018-03-31T22:00",
			"2018-03-31T23:00", "2018-04-01T00:00", "2018-04-01T01:00",
			"2018-04-01T02:00", "2018-04-01T03:00", "2018-04-01T04:00",
			"2018-04-01T05:00", "2018-04-01T06:00", "2018-04-01T07:00",
			"2018-04-01T08:00", "2018-04-01T09:00", "2018-04-01T10:00");

		List<String> actualKeys = _createMetricsKeys(
			Interval.DAY, LocalDateTime.of(2018, 4, 1, 10, 30),
			TimeRange.LAST_24_HOURS);

		Assert.assertEquals(expectedKeys.toString(), expectedKeys, actualKeys);
	}

	@Test
	public void testCreateMetricsLast28Days() {
		List<String> expectedKeys = Arrays.asList(
			"2018-03-04T00:00", "2018-03-05T00:00", "2018-03-06T00:00",
			"2018-03-07T00:00", "2018-03-08T00:00", "2018-03-09T00:00",
			"2018-03-10T00:00", "2018-03-11T00:00", "2018-03-12T00:00",
			"2018-03-13T00:00", "2018-03-14T00:00", "2018-03-15T00:00",
			"2018-03-16T00:00", "2018-03-17T00:00", "2018-03-18T00:00",
			"2018-03-19T00:00", "2018-03-20T00:00", "2018-03-21T00:00",
			"2018-03-22T00:00", "2018-03-23T00:00", "2018-03-24T00:00",
			"2018-03-25T00:00", "2018-03-26T00:00", "2018-03-27T00:00",
			"2018-03-28T00:00", "2018-03-29T00:00", "2018-03-30T00:00",
			"2018-03-31T00:00");

		List<String> actualKeys = _createMetricsKeys(
			Interval.DAY, LocalDateTime.of(2018, 4, 1, 10, 30),
			TimeRange.LAST_28_DAYS);

		Assert.assertEquals(expectedKeys, actualKeys);
	}

	@Test
	public void testCreateMetricsLast30Days() {
		List<String> expectedKeys = Arrays.asList(
			"2018-03-02T00:00", "2018-03-03T00:00", "2018-03-04T00:00",
			"2018-03-05T00:00", "2018-03-06T00:00", "2018-03-07T00:00",
			"2018-03-08T00:00", "2018-03-09T00:00", "2018-03-10T00:00",
			"2018-03-11T00:00", "2018-03-12T00:00", "2018-03-13T00:00",
			"2018-03-14T00:00", "2018-03-15T00:00", "2018-03-16T00:00",
			"2018-03-17T00:00", "2018-03-18T00:00", "2018-03-19T00:00",
			"2018-03-20T00:00", "2018-03-21T00:00", "2018-03-22T00:00",
			"2018-03-23T00:00", "2018-03-24T00:00", "2018-03-25T00:00",
			"2018-03-26T00:00", "2018-03-27T00:00", "2018-03-28T00:00",
			"2018-03-29T00:00", "2018-03-30T00:00", "2018-03-31T00:00");

		List<String> actualKeys = _createMetricsKeys(
			Interval.DAY, LocalDateTime.of(2018, 4, 1, 10, 30),
			TimeRange.LAST_30_DAYS);

		Assert.assertEquals(expectedKeys, actualKeys);
	}

	@Test
	public void testCreateMetricsLast90DaysTodayOnSaturday() {
		List<String> expectedKeys = Arrays.asList(
			"2018-08-26T00:00", "2018-09-02T00:00", "2018-09-09T00:00",
			"2018-09-16T00:00", "2018-09-23T00:00", "2018-09-30T00:00",
			"2018-10-07T00:00", "2018-10-14T00:00", "2018-10-21T00:00",
			"2018-10-28T00:00", "2018-11-04T00:00", "2018-11-11T00:00",
			"2018-11-18T00:00");

		Map<String, Metric> metricsMap = _createMetrics(
			Interval.WEEK, LocalDateTime.of(2018, 11, 24, 10, 30),
			TimeRange.LAST_90_DAYS);

		Assert.assertEquals(expectedKeys, new ArrayList<>(metricsMap.keySet()));

		List<String> expectedPreviousValueKeys = Arrays.asList(
			"2018-05-27/2018-06-02", "2018-06-03/2018-06-09",
			"2018-06-10/2018-06-16", "2018-06-17/2018-06-23",
			"2018-06-24/2018-06-30", "2018-07-01/2018-07-07",
			"2018-07-08/2018-07-14", "2018-07-15/2018-07-21",
			"2018-07-22/2018-07-28", "2018-07-29/2018-08-04",
			"2018-08-05/2018-08-11", "2018-08-12/2018-08-18",
			"2018-08-19/2018-08-25");

		List<String> actualPreviousValueKeys = _getMetricValueKeys(
			metricsMap.values(), Metric::getPreviousValueKey);

		Assert.assertEquals(expectedPreviousValueKeys, actualPreviousValueKeys);

		List<String> expectedValueKeys = Arrays.asList(
			"2018-08-26/2018-09-01", "2018-09-02/2018-09-08",
			"2018-09-09/2018-09-15", "2018-09-16/2018-09-22",
			"2018-09-23/2018-09-29", "2018-09-30/2018-10-06",
			"2018-10-07/2018-10-13", "2018-10-14/2018-10-20",
			"2018-10-21/2018-10-27", "2018-10-28/2018-11-03",
			"2018-11-04/2018-11-10", "2018-11-11/2018-11-17",
			"2018-11-18/2018-11-24");

		List<String> actualValueKeys = _getMetricValueKeys(
			metricsMap.values(), Metric::getValueKey);

		Assert.assertEquals(expectedValueKeys, actualValueKeys);
	}

	@Test
	public void testCreateMetricsLast90DaysTodayOnSundayOrWeekday1() {
		List<String> expectedKeys = Arrays.asList(
			"2018-08-26T00:00", "2018-09-02T00:00", "2018-09-09T00:00",
			"2018-09-16T00:00", "2018-09-23T00:00", "2018-09-30T00:00",
			"2018-10-07T00:00", "2018-10-14T00:00", "2018-10-21T00:00",
			"2018-10-28T00:00", "2018-11-04T00:00", "2018-11-11T00:00",
			"2018-11-18T00:00", "2018-11-25T00:00");

		Map<String, Metric> metricsMap = _createMetrics(
			Interval.WEEK, LocalDateTime.of(2018, 11, 29, 10, 30),
			TimeRange.LAST_90_DAYS);

		Assert.assertEquals(expectedKeys, new ArrayList<>(metricsMap.keySet()));

		List<String> expectedPreviousValueKeys = Arrays.asList(
			"2018-05-27/2018-06-02", "2018-06-03/2018-06-09",
			"2018-06-10/2018-06-16", "2018-06-17/2018-06-23",
			"2018-06-24/2018-06-30", "2018-07-01/2018-07-07",
			"2018-07-08/2018-07-14", "2018-07-15/2018-07-21",
			"2018-07-22/2018-07-28", "2018-07-29/2018-08-04",
			"2018-08-05/2018-08-11", "2018-08-12/2018-08-18",
			"2018-08-19/2018-08-25", "2018-08-26/2018-09-01");

		List<String> actualPreviousValueKeys = _getMetricValueKeys(
			metricsMap.values(), Metric::getPreviousValueKey);

		Assert.assertEquals(expectedPreviousValueKeys, actualPreviousValueKeys);

		List<String> expectedValueKeys = Arrays.asList(
			"2018-08-26/2018-09-01", "2018-09-02/2018-09-08",
			"2018-09-09/2018-09-15", "2018-09-16/2018-09-22",
			"2018-09-23/2018-09-29", "2018-09-30/2018-10-06",
			"2018-10-07/2018-10-13", "2018-10-14/2018-10-20",
			"2018-10-21/2018-10-27", "2018-10-28/2018-11-03",
			"2018-11-04/2018-11-10", "2018-11-11/2018-11-17",
			"2018-11-18/2018-11-24", "2018-11-25/2018-12-01");

		List<String> actualValueKeys = _getMetricValueKeys(
			metricsMap.values(), Metric::getValueKey);

		Assert.assertEquals(expectedValueKeys, actualValueKeys);
	}

	@Test
	public void testCreateMetricsLast90DaysTodayOnSundayOrWeekday2() {
		List<String> expectedKeys = Arrays.asList(
			"2018-09-09T00:00", "2018-09-16T00:00", "2018-09-23T00:00",
			"2018-09-30T00:00", "2018-10-07T00:00", "2018-10-14T00:00",
			"2018-10-21T00:00", "2018-10-28T00:00", "2018-11-04T00:00",
			"2018-11-11T00:00", "2018-11-18T00:00", "2018-11-25T00:00",
			"2018-12-02T00:00", "2018-12-09T00:00");

		Map<String, Metric> metricsMap = _createMetrics(
			Interval.WEEK, LocalDateTime.of(2018, 12, 14, 10, 30),
			TimeRange.LAST_90_DAYS);

		Assert.assertEquals(expectedKeys, new ArrayList<>(metricsMap.keySet()));

		List<String> expectedPreviousValueKeys = Arrays.asList(
			"2018-06-10/2018-06-16", "2018-06-17/2018-06-23",
			"2018-06-24/2018-06-30", "2018-07-01/2018-07-07",
			"2018-07-08/2018-07-14", "2018-07-15/2018-07-21",
			"2018-07-22/2018-07-28", "2018-07-29/2018-08-04",
			"2018-08-05/2018-08-11", "2018-08-12/2018-08-18",
			"2018-08-19/2018-08-25", "2018-08-26/2018-09-01",
			"2018-09-02/2018-09-08", "2018-09-09/2018-09-15");

		List<String> actualPreviousValueKeys = _getMetricValueKeys(
			metricsMap.values(), Metric::getPreviousValueKey);

		Assert.assertEquals(expectedPreviousValueKeys, actualPreviousValueKeys);

		List<String> expectedValueKeys = Arrays.asList(
			"2018-09-09/2018-09-15", "2018-09-16/2018-09-22",
			"2018-09-23/2018-09-29", "2018-09-30/2018-10-06",
			"2018-10-07/2018-10-13", "2018-10-14/2018-10-20",
			"2018-10-21/2018-10-27", "2018-10-28/2018-11-03",
			"2018-11-04/2018-11-10", "2018-11-11/2018-11-17",
			"2018-11-18/2018-11-24", "2018-11-25/2018-12-01",
			"2018-12-02/2018-12-08", "2018-12-09/2018-12-15");

		List<String> actualValueKeys = _getMetricValueKeys(
			metricsMap.values(), Metric::getValueKey);

		Assert.assertEquals(expectedValueKeys, actualValueKeys);
	}

	@Test
	public void testCreateMetricsYesterday() {
		List<String> expectedKeys = Arrays.asList(
			"2018-03-31T00:00", "2018-03-31T01:00", "2018-03-31T02:00",
			"2018-03-31T03:00", "2018-03-31T04:00", "2018-03-31T05:00",
			"2018-03-31T06:00", "2018-03-31T07:00", "2018-03-31T08:00",
			"2018-03-31T09:00", "2018-03-31T10:00", "2018-03-31T11:00",
			"2018-03-31T12:00", "2018-03-31T13:00", "2018-03-31T14:00",
			"2018-03-31T15:00", "2018-03-31T16:00", "2018-03-31T17:00",
			"2018-03-31T18:00", "2018-03-31T19:00", "2018-03-31T20:00",
			"2018-03-31T21:00", "2018-03-31T22:00", "2018-03-31T23:00");

		List<String> actualKeys = _createMetricsKeys(
			Interval.DAY, LocalDateTime.of(2018, 4, 1, 10, 30),
			TimeRange.YESTERDAY);

		Assert.assertEquals(expectedKeys, actualKeys);
	}

	private Clock _createFixedClock(LocalDateTime localDateTime) {
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);

		return Clock.fixed(zonedDateTime.toInstant(), ZoneOffset.UTC);
	}

	private Map<String, Metric> _createMetrics(
		Interval interval, LocalDateTime localDateTime, TimeRange timeRange) {

		Clock clock = _createFixedClock(localDateTime);

		return _metricHelper.createMetrics(
			clock, interval, timeRange.withClock(clock), _metricType);
	}

	private List<String> _createMetricsKeys(
		Interval interval, LocalDateTime localDateTime, TimeRange timeRange) {

		Map<String, Metric> actualMetrics = _createMetrics(
			interval, localDateTime, timeRange);

		return new ArrayList<>(actualMetrics.keySet());
	}

	private List<String> _getMetricValueKeys(
		Collection<Metric> metrics,
		Function<Metric, String> valueKeyMapperFunction) {

		Stream<Metric> stream = metrics.stream();

		return stream.map(
			valueKeyMapperFunction
		).collect(
			Collectors.toList()
		);
	}

	private final MetricHelper _metricHelper = new MetricHelper();

	@Mock
	private MetricType _metricType;

}