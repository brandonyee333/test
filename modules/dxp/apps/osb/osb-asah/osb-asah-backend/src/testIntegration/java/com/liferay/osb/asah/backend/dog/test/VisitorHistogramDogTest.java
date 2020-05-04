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

import com.liferay.osb.asah.backend.dog.VisitorHistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class VisitorHistogramDogTest {

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor-histogram-page-last-7-days-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHistogramMetricsLast7Days() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			histogramMetrics.toString(), 7, histogramMetrics.size());

		double[] expectedValues = {0, 0, 1, 0, 0, 0, 1};

		double[] actualValues = _getActualValues(histogramMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor-histogram-page-last-24-hours-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHistogramMetricsLast24Hours() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			histogramMetrics.toString(), 24, histogramMetrics.size());

		double[] expectedValues = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 1, 0, 0, 0, 0, 0, 0,
			0
		};

		double[] actualValues = _getActualValues(histogramMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor-histogram-page-last-28-days-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHistogramMetricsLast28Days() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_28_DAYS);

		Assert.assertEquals(
			histogramMetrics.toString(), 28, histogramMetrics.size());

		double[] expectedValues = new double[28];

		Arrays.fill(expectedValues, 1);

		double[] actualValues = _getActualValues(histogramMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor-histogram-page-last-90-days-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Ignore
	@Test
	public void testVisitorHistogramMetricsLast90Days() {
		double[] expectedValues = DogTestUtil.create90DaysHistogramBuckets();

		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.WEEK, TimeRange.LAST_90_DAYS);

		double[] actualValues = _getActualValues(histogramMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);

		HistogramMetric histogramMetric = histogramMetrics.get(0);

		LocalDateTime localDateTime = LocalDateTime.now();

		localDateTime = localDateTime.minusDays(179);

		if (localDateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
			histogramMetric = histogramMetrics.get(1);
		}

		Assert.assertEquals(1D, histogramMetric.getPreviousValue(), 0);
	}

	private double[] _getActualValues(List<HistogramMetric> histogramMetrics) {
		double[] actualValues = new double[histogramMetrics.size()];

		for (int i = 0; i < histogramMetrics.size(); i++) {
			HistogramMetric histogramMetric = histogramMetrics.get(i);

			actualValues[i] = histogramMetric.getValue();
		}

		return actualValues;
	}

	private List<HistogramMetric> _getHistogramMetrics(
		Interval interval, TimeRange timeRange) {

		return _visitorHistogramDog.getHistogramMetrics(
			false, PageMetricType.VISITORS,
			new SearchQueryContext(AssetType.PAGE) {
				{
					setInterval(interval.getKey());
					setTimeRange(timeRange);
				}
			});
	}

	@Autowired
	private VisitorHistogramDog _visitorHistogramDog;

}