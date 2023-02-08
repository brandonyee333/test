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

import com.liferay.osb.asah.backend.model.JournalMetric;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public class JournalAssetMetricRepositoryTest
	extends BaseAssetMetricRepositoryTestCase<JournalMetric> {

	@SQLResource(
		resourcePath = "journal_asset_metric_canonical_urls_last_7_days.sql"
	)
	@Test
	public void testGetAppearsOnMetricLast7Days() {
		super.assertAppearsOnMetric(
			JournalMetricType.VIEWS, TimeRange.LAST_7_DAYS);
	}

	@SQLResource(
		resourcePath = "journal_asset_metric_views_browser_last_30_days.sql"
	)
	@Test
	public void testGetBrowserMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				new Tuple2("Firefox", 14D), new Tuple2("Chrome", 9D),
				new Tuple2("Unknown", 3D)),
			_assetMetricRepository.getBrowserMetrics(
				"e131fabc", null, 1L, JournalMetricType.VIEWS,
				TimeRange.LAST_30_DAYS));
	}

	@Disabled
	@SQLResource(
		resourcePath = "journal_asset_metric_views_device_last_30_days.sql"
	)
	@Test
	public void testGetDeviceMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				new Tuple2("Desktop", 22D), new Tuple2("Mobile", 12D)),
			_assetMetricRepository.getDeviceMetrics(
				"e131fabc", null, 1L, JournalMetricType.VIEWS,
				TimeRange.LAST_30_DAYS));
	}

	@Disabled
	@SQLResource(
		resourcePath = "journal_asset_metric_views_geolocation_last_30_days.sql"
	)
	@Test
	public void testGetGeolocationMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				new Tuple2("France", 9D), new Tuple2("Japan", 7D),
				new Tuple2("United States", 5D)),
			_assetMetricRepository.getGeolocationMetrics(
				"e131fabc", null, 1L, JournalMetricType.VIEWS,
				TimeRange.LAST_30_DAYS));
	}

	@Disabled
	@SQLResource(
		resourcePath = "journal_asset_metric_views_individuals_last_30_days.sql"
	)
	@Test
	public void testGetIndividualsCountLast30Days() {
		assertGetIndividualsCount(
			JournalMetricType.VIEWS, TimeRange.LAST_30_DAYS);
	}

	@Disabled
	@SQLResource(
		resourcePath = "journal_asset_metric_views_segments_last_30_days.sql"
	)
	@Test
	public void testGetSegmentedCountLast30Days() {
		assertGetSegmentedIndividualsCount(
			JournalMetricType.VIEWS, TimeRange.LAST_30_DAYS);
	}

	@Disabled
	@SQLResource(
		resourcePath = "journal_asset_metric_views_segments_last_7_days.sql"
	)
	@Test
	public void testGetSegmentMetrics7Days() {
		assertGetSegmentMetrics(JournalMetricType.VIEWS, TimeRange.LAST_7_DAYS);
	}

	@SQLResource(
		resourcePath = "journal_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsAssetMetric() {
		JournalMetric journalMetric = _assetMetricRepository.getAssetMetric(
			"e131fabc", null, 1L, SetUtil.of(JournalMetricType.VIEWS.getName()),
			TimeRange.LAST_24_HOURS);

		Assertions.assertNotNull(journalMetric);

		Metric viewsMetric = journalMetric.getViewsMetric();

		Assertions.assertEquals(7D, viewsMetric.getValue(), 0);
	}

	@SQLResource(
		resourcePath = "journal_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsAssetMetrics() {
		assertAssetMetrics(
			new Double[] {7D, 6D},
			_assetMetricRepository.getAssetMetrics(
				1L, null, PageRequest.of(0, 10),
				SetUtil.of(JournalMetricType.VIEWS.getName()),
				TimeRange.LAST_24_HOURS),
			JournalMetric::getViewsMetric);
	}

	@SQLResource(
		resourcePath = "journal_asset_metric_views_histogram_last_7_days.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast7Days() {
		assertHistogramMetrics(
			SetUtil.of(3.0),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", null, 1L, false, Interval.DAY,
				JournalMetricType.VIEWS, TimeRange.LAST_7_DAYS));
	}

	@SQLResource(
		resourcePath = "journal_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24Hours() {
		assertHistogramMetrics(
			SetUtil.of((double)1, (double)2, (double)4),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", null, 1L, false, Interval.HOUR,
				JournalMetricType.VIEWS, TimeRange.LAST_24_HOURS));
	}

	@SQLResource(
		resourcePath = "journal_asset_metric_views_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast24HoursDifferentTimezone() {
		assertHistogramMetricsDifferentTimezone(
			"e131fabc", 1L, JournalMetricType.VIEWS, -3, "America/Fortaleza");
	}

	@Override
	protected AssetMetricRepository<JournalMetric> getAssetMetricRepository() {
		return _assetMetricRepository;
	}

	@Autowired
	@Qualifier("JournalAssetMetricRepository")
	private AssetMetricRepository<JournalMetric> _assetMetricRepository;

}