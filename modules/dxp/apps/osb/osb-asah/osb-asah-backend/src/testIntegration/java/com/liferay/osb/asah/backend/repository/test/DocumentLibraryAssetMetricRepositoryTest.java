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

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.trino.enabled=true")
@SQLResource(dataSource = "trinoDataSource", resourcePath = "/hive_tables.sql")
public class DocumentLibraryAssetMetricRepositoryTest
	extends BaseAssetMetricRepositoryTestCase {

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

	@Override
	protected AssetMetricRepository getAssetMetricRepository() {
		return _assetMetricRepository;
	}

	@Autowired
	@Qualifier("DocumentLibraryAssetMetricRepository")
	private AssetMetricRepository<DocumentLibraryMetric> _assetMetricRepository;

}