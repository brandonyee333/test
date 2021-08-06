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

import com.liferay.osb.asah.backend.model.DocumentLibraryMetric;
import com.liferay.osb.asah.backend.model.DocumentLibraryMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@DirtiesContext
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.trino.enabled=true")
@SQLResource(dataSource = "trinoDataSource", resourcePath = "/hive_tables.sql")
public class DocumentLibraryAssetMetricRepositoryTest
	extends BaseAssetMetricRepositoryTestCase<DocumentLibraryMetric> {

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_browser_last_30_days.sql"
	)
	@Test
	public void testGetBrowserMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				new Tuple2("Firefox", 14D), new Tuple2("Chrome", 9D),
				new Tuple2("Opera Desktop", 3D)),
			_assetMetricRepository.getBrowserMetrics(
				"e131fabc", 1L, DocumentLibraryMetricType.PREVIEWS,
				TimeRange.LAST_30_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_canonical_urls_last_7_days.sql"
	)
	@Test
	public void testGetCanonicalUrls7Days() {
		super.assertGetCanonicalUrls(TimeRange.LAST_7_DAYS);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_device_last_30_days.sql"
	)
	@Test
	public void testGetDeviceMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				new Tuple2("Desktop", 22D), new Tuple2("Mobile", 12D)),
			_assetMetricRepository.getDeviceMetrics(
				"e131fabc", 1L, DocumentLibraryMetricType.PREVIEWS,
				TimeRange.LAST_30_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_geolocation_last_30_days.sql"
	)
	@Test
	public void testGetGeolocationMetricsLast30Days() {
		assertMetrics(
			Arrays.asList(
				new Tuple2("France", 9D), new Tuple2("Japan", 7D),
				new Tuple2("United States", 5D)),
			_assetMetricRepository.getGeolocationMetrics(
				"e131fabc", 1L, DocumentLibraryMetricType.PREVIEWS,
				TimeRange.LAST_30_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_views_individuals_last_30_days.sql"
	)
	@Test
	public void testGetIndividualsCountLast30Days() {
		assertGetIndividualsCount(
			DocumentLibraryMetricType.PREVIEWS, TimeRange.LAST_30_DAYS);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetPreviewsAssetMetric() {
		DocumentLibraryMetric documentLibraryMetric =
			_assetMetricRepository.getAssetMetric(
				"e131fabc", 1L,
				SetUtil.of(DocumentLibraryMetricType.PREVIEWS.getName()),
				TimeRange.LAST_24_HOURS);

		Assert.assertNotNull(documentLibraryMetric);

		Metric previewsMetric = documentLibraryMetric.getPreviewsMetric();

		Assert.assertEquals(7D, previewsMetric.getValue(), 0);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetPreviewsAssetMetrics() {
		assertAssetMetrics(
			new Double[] {7D, 6D},
			_assetMetricRepository.getAssetMetrics(
				1L, PageRequest.of(0, 10),
				SetUtil.of(DocumentLibraryMetricType.PREVIEWS.getName()),
				TimeRange.LAST_24_HOURS),
			DocumentLibraryMetric::getPreviewsMetric);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_histogram_last_7_days.sql"
	)
	@Test
	public void testGetPreviewsHistogramMetricsLast7Days() {
		assertHistogramMetrics(
			SetUtil.of(Double.valueOf(3)),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", 1L, Interval.DAY,
				DocumentLibraryMetricType.PREVIEWS, TimeRange.LAST_7_DAYS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetPreviewsHistogramMetricsLast24Hours() {
		assertHistogramMetrics(
			SetUtil.of(Double.valueOf(1), Double.valueOf(2), Double.valueOf(4)),
			_assetMetricRepository.getHistogramMetrics(
				"e131fabc", 1L, Interval.HOUR,
				DocumentLibraryMetricType.PREVIEWS, TimeRange.LAST_24_HOURS));
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetPreviewsHistogramMetricsLast24HoursDifferentTimezone() {
		assertHistogramMetricsDifferentTimezone(
			"e131fabc", 1L, DocumentLibraryMetricType.PREVIEWS, 3,
			"America/Fortaleza");
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_ratings_histogram_last_24_hours.sql"
	)
	@Test
	public void testGetRatingsAssetMetric() {
		DocumentLibraryMetric documentLibraryMetric =
			_assetMetricRepository.getAssetMetric(
				"e131fabc", 1L,
				SetUtil.of(DocumentLibraryMetricType.RATINGS.getName()),
				TimeRange.LAST_24_HOURS);

		Assert.assertNotNull(documentLibraryMetric);

		Metric ratingsMetric = documentLibraryMetric.getRatingsMetric();

		Assert.assertEquals(2.33, ratingsMetric.getValue(), 0.01);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_views_segments_last_30_days.sql"
	)
	@Test
	public void testGetSegmentedCountLast30Days() {
		assertGetSegmentedIndividualsCount(
			DocumentLibraryMetricType.PREVIEWS, TimeRange.LAST_30_DAYS);
	}

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_views_segments_last_7_days.sql"
	)
	@Test
	public void testGetSegmentMetrics7Days() {
		assertGetSegmentMetrics(
			DocumentLibraryMetricType.PREVIEWS, TimeRange.LAST_7_DAYS);
	}

	@Override
	protected AssetMetricRepository getAssetMetricRepository() {
		return _assetMetricRepository;
	}

	@Autowired
	@Qualifier("DocumentLibraryAssetMetricRepository")
	private AssetMetricRepository<DocumentLibraryMetric> _assetMetricRepository;

}