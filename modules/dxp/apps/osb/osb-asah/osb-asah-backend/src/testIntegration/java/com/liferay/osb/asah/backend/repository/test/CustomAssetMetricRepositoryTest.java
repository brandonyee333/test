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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.repository.CustomAssetMetricRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.trino.enabled=true")
@SQLResource(dataSource = "trinoDataSource", resourcePath = "/hive_tables.sql")
public class CustomAssetMetricRepositoryTest {

	@Before
	public void setUp() {
		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			"UTC"
		);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "custom_asset_metric_read_time_histogram_last_7_days.sql"
	)
	@Test
	public void testGetReadingTimeHistogramMetricsLast24Hours() {
		List<Tuple2<LocalDateTime, BigDecimal>> tuples =
			_customAssetMetricRepository.getHistogramMetrics(
				1L, CustomAssetMetricType.READING_TIME, "e131fabc",
				Interval.DAY, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(tuples.toString(), 1, tuples.size());
		Assert.assertEquals(
			SetUtil.of(BigDecimal.valueOf(750.0)),
			SetUtil.map(tuples, Tuple2::getT2));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "custom_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24Hours() {
		List<Tuple2<LocalDateTime, BigDecimal>> tuples =
			_customAssetMetricRepository.getHistogramMetrics(
				1L, CustomAssetMetricType.VIEWS, "e131fabc", Interval.HOUR,
				TimeRange.LAST_24_HOURS);

		Assert.assertEquals(tuples.toString(), 3, tuples.size());
		Assert.assertEquals(
			SetUtil.of(
				BigDecimal.valueOf(1), BigDecimal.valueOf(2),
				BigDecimal.valueOf(4)),
			SetUtil.map(tuples, Tuple2::getT2));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "custom_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24HoursDifferentTimezone() {
		List<LocalDateTime> buckets = _getBuckets(
			_customAssetMetricRepository.getHistogramMetrics(
				1L, CustomAssetMetricType.VIEWS, "e131fabc", Interval.HOUR,
				TimeRange.LAST_24_HOURS));

		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			"America/Fortaleza"
		);

		List<LocalDateTime> bucketsShifted = _getBuckets(
			_customAssetMetricRepository.getHistogramMetrics(
				1L, CustomAssetMetricType.VIEWS, "e131fabc", Interval.HOUR,
				TimeRange.LAST_24_HOURS));

		Assert.assertEquals(
			bucketsShifted.toString(), buckets.size(), bucketsShifted.size());

		for (int i = 0; i < buckets.size(); i++) {

			// America/Fortaleza expected delta to UTC is 3 hours

			Duration duration = Duration.between(
				bucketsShifted.get(i), buckets.get(i));

			Assert.assertEquals(3, duration.toHours());
		}
	}

	private List<LocalDateTime> _getBuckets(
		List<Tuple2<LocalDateTime, BigDecimal>> tuples) {

		Stream<Tuple2<LocalDateTime, BigDecimal>> stream = tuples.stream();

		return stream.map(
			Tuple2::getT1
		).sorted(
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private CustomAssetMetricRepository _customAssetMetricRepository;

	@MockBean
	private TimeZoneDog _timeZoneDog;

}