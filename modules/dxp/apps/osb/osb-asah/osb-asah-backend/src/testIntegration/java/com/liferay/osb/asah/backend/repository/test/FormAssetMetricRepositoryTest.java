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

import com.liferay.osb.asah.backend.model.FormMetric;
import com.liferay.osb.asah.backend.model.FormMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import reactor.util.function.Tuples;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.trino.enabled=true")
@SQLResource(dataSource = "trinoDataSource", resourcePath = "/hive_tables.sql")
public class FormAssetMetricRepositoryTest
	extends BaseAssetMetricRepositoryTestCase {

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "form_asset_metric_views_geolocation_last_30_days.sql"
	)
	@Test
	public void testGetGeolocationMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				Tuples.of("France", 9D), Tuples.of("Japan", 7D),
				Tuples.of("United States", 5D)),
			_assetMetricRepository.getGeolocationMetrics(
				"e131fabc", 1L, FormMetricType.VIEWS, TimeRange.LAST_30_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "form_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsAssetMetric() {
		FormMetric formMetric = _assetMetricRepository.getAssetMetric(
			"e131fabc", 1L, SetUtil.of(FormMetricType.VIEWS.getName()),
			TimeRange.LAST_24_HOURS);

		Assert.assertNotNull(formMetric);

		Metric viewsMetric = formMetric.getViewsMetric();

		Assert.assertEquals(7D, viewsMetric.getValue(), 0);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "form_asset_metric_views_histogram_last_7_days.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast7Days() {
		assertHistogramMetrics(
			SetUtil.of(Double.valueOf(3)),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", 1L, Interval.DAY, FormMetricType.VIEWS,
				TimeRange.LAST_7_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "form_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24Hours() {
		assertHistogramMetrics(
			SetUtil.of(Double.valueOf(1), Double.valueOf(2), Double.valueOf(4)),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", 1L, Interval.HOUR, FormMetricType.VIEWS,
				TimeRange.LAST_24_HOURS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "form_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24HoursDifferentTimezone() {
		assertHistogramMetricsDifferentTimezone(
			"e131fabc", 1L, FormMetricType.VIEWS, 3, "America/Fortaleza");
	}

	@Override
	protected AssetMetricRepository getAssetMetricRepository() {
		return _assetMetricRepository;
	}

	@Autowired
	@Qualifier("FormAssetMetricRepository")
	private AssetMetricRepository<FormMetric> _assetMetricRepository;

}