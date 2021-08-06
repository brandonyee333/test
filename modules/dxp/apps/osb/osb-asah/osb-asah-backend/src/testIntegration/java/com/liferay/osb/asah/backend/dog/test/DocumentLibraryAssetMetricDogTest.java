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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.DocumentLibraryAssetMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.DocumentLibraryMetricType;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
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
public class DocumentLibraryAssetMetricDogTest {

	@SQLResource(
		dataSource = "trinoDataSource",
		resourcePath = "document_library_asset_metric_previews_histogram_last_14_days.sql"
	)
	@Test
	public void testGetPreviewsHistogramMetricsLast7Days() {
		HistogramMetricBag histogramMetricBag =
			_documentLibraryAssetMetricDog.getHistogramMetricBag(
				DocumentLibraryMetricType.PREVIEWS,
				new SearchQueryContext() {
					{
						setAssetId("e131fabc");
						setChannelId("1");
						setIncludePrevious(Boolean.TRUE);
						setInterval("D");
						setTimeRange(TimeRange.LAST_7_DAYS);
					}
				});

		List<HistogramMetric> histogramMetrics =
			histogramMetricBag.getMetrics();

		HistogramMetric lastHistogramMetric = histogramMetrics.get(
			histogramMetrics.size() - 1);

		Assert.assertEquals(
			Double.valueOf(7), lastHistogramMetric.getPreviousValue());
		Assert.assertEquals(Double.valueOf(4), lastHistogramMetric.getValue());
	}

	@Autowired
	private DocumentLibraryAssetMetricDog _documentLibraryAssetMetricDog;

}