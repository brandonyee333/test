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

package com.liferay.osb.asah.backend.repository.test;

import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.trino.enabled=true")
@SQLResource(dataSource = "trinoDataSource", resourcePath = "/hive_tables.sql")
public class JournalAssetMetricRepositoryTest {

	@Before
	public void setUp() {
		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			"UTC"
		);

		Mockito.when(
			_timeZoneDog.getZoneId()
		).thenReturn(
			ZoneId.of("UTC")
		);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "journal_asset_metric_views_histogram_last_7_days.sql"
	)
	@Test
	public void testGetReadingTimeHistogramMetricsLast24Hours() {
		List<HistogramMetric> histogramMetrics =
			_journalAssetMetricRepositoryImpl.getHistogramMetrics(
				"e131fabc", 1L, Interval.DAY, JournalMetricType.VIEWS,
				TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			histogramMetrics.toString(), 1, histogramMetrics.size());
		Assert.assertEquals(
			SetUtil.of(Double.valueOf(3)),
			SetUtil.map(histogramMetrics, HistogramMetric::getValue));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "journal_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24Hours() {
		List<HistogramMetric> histogramMetrics =
			_journalAssetMetricRepositoryImpl.getHistogramMetrics(
				"e131fabc", 1L, Interval.HOUR, JournalMetricType.VIEWS,
				TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			histogramMetrics.toString(), 3, histogramMetrics.size());
		Assert.assertEquals(
			SetUtil.of(Double.valueOf(1), Double.valueOf(2), Double.valueOf(4)),
			SetUtil.map(histogramMetrics, HistogramMetric::getValue));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "journal_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24HoursDifferentTimezone() {
		List<LocalDateTime> buckets = _getBuckets(
			_journalAssetMetricRepositoryImpl.getHistogramMetrics(
				"e131fabc", 1L, Interval.HOUR, CustomAssetMetricType.VIEWS,
				TimeRange.LAST_24_HOURS));

		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			"America/Fortaleza"
		);

		List<LocalDateTime> bucketsShifted = _getBuckets(
			_journalAssetMetricRepositoryImpl.getHistogramMetrics(
				"e131fabc", 1L, Interval.HOUR, CustomAssetMetricType.VIEWS,
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
		List<HistogramMetric> histogramMetrics) {

		Stream<HistogramMetric> stream = histogramMetrics.stream();

		return stream.map(
			HistogramMetric::getKey
		).map(
			LocalDateTime::parse
		).sorted(
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	@Qualifier("JournalAssetMetricRepository")
	private AssetMetricRepository _journalAssetMetricRepositoryImpl;

	@MockBean
	private TimeZoneDog _timeZoneDog;

}