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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.JournalAssetMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public class JournalAssetMetricDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(
		resourcePath = "journal_asset_metric_views_histogram_last_14_days.sql"
	)
	@Test
	public void testGetViewsHistogramMetricsLast7Days() {
		HistogramMetricBag histogramMetricBag =
			_journalAssetMetricDog.getHistogramMetricBag(
				JournalMetricType.VIEWS,
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

		Assertions.assertEquals(7, lastHistogramMetric.getPreviousValue());
		Assertions.assertEquals(4, lastHistogramMetric.getValue());
	}

	@Autowired
	private JournalAssetMetricDog _journalAssetMetricDog;

}