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

import com.liferay.osb.asah.backend.model.CustomAssetMetric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@DirtiesContext
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.trino.enabled=true")
@SQLResource(dataSource = "trinoDataSource", resourcePath = "/hive_tables.sql")
public class CustomAssetMetricRepositoryTest
	extends BaseAssetMetricRepositoryTestCase {

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "custom_asset_metric_read_time_histogram_last_7_days.sql"
	)
	@Test
	public void testGetReadingTimeHistogramMetricsLast7days() {
		assertHistogramMetrics(
			SetUtil.of((double)750),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", 1L, Interval.DAY,
				CustomAssetMetricType.READING_TIME, TimeRange.LAST_7_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "custom_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24Hours() {
		assertHistogramMetrics(
			SetUtil.of((double)1, (double)2, (double)4),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", 1L, Interval.HOUR, CustomAssetMetricType.VIEWS,
				TimeRange.LAST_24_HOURS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "custom_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24HoursDifferentTimezone() {
		assertHistogramMetricsDifferentTimezone(
			"e131fabc", 1L, CustomAssetMetricType.VIEWS, 3,
			"America/Fortaleza");
	}

	@Override
	protected AssetMetricRepository getAssetMetricRepository() {
		return _assetMetricRepository;
	}

	@Autowired
	@Qualifier("CustomAssetMetricRepository")
	private AssetMetricRepository<CustomAssetMetric> _assetMetricRepository;

}