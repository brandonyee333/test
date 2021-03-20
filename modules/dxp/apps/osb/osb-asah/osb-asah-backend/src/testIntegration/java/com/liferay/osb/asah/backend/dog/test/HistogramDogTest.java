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

import com.liferay.osb.asah.backend.dog.HistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.model.Trend;
import com.liferay.osb.asah.backend.model.TrendClassification;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lino Alves
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class HistogramDogTest {

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_last_90_days_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testHistogramMetricsCustomRange() {
		double[] expectedValues = {
			0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
		};

		LocalDate localDate = LocalDate.now(ZoneId.of("UTC"));

		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY,
			TimeRange.of(localDate.minusDays(85), localDate.minusDays(105)));

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_last_7_days_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testHistogramMetricsLast7Days() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			histogramMetrics.toString(), 7, histogramMetrics.size());

		double[] expectedValues = {6, 6, 5, 4, 3, 2, 1};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_last_24_hours_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testHistogramMetricsLast24Hours() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			histogramMetrics.toString(), 24, histogramMetrics.size());

		double[] expectedValues = {
			0, 1, 1, 1, 1, 2, 1, 2, 1, 2, 0, 3, 1, 1, 4, 6, 4, 2, 4, 3, 2, 1, 2,
			0
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_last_28_days_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testHistogramMetricsLast28Days() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_28_DAYS);

		Assert.assertEquals(
			histogramMetrics.toString(), 28, histogramMetrics.size());

		double[] expectedValues = {
			28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12,
			11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_last_90_days_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Ignore
	@Test
	public void testHistogramMetricsLast90Days() {
		double[] expectedValues = DogTestUtil.create90DaysHistogramBuckets();

		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.WEEK, TimeRange.LAST_90_DAYS);

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_day_interval_time_zone_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "preferences",
		resourcePath = "preference_time_zone_america_los_angeles.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testHistogramMetricsTimeZoneDayInterval() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY,
			TimeRange.of(
				LocalDateTime.parse("2021-03-13T00:00:00"),
				LocalDateTime.parse("2021-03-08T00:00:00")));

		Assert.assertEquals(
			histogramMetrics.toString(), 6, histogramMetrics.size());

		double[] expectedValues = {4, 0, 4, 2, 2, 1};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_month_interval_time_zone_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "preferences",
		resourcePath = "preference_time_zone_america_los_angeles.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testHistogramMetricsTimeZoneMonthInterval() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.MONTH,
			TimeRange.of(
				LocalDateTime.parse("2021-03-31T23:59:59"),
				LocalDateTime.parse("2021-01-01T00:00:00")));

		Assert.assertEquals(
			histogramMetrics.toString(), 3, histogramMetrics.size());

		double[] expectedValues = {24, 15, 22};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_week_interval_time_zone_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "preferences",
		resourcePath = "preference_time_zone_america_los_angeles.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testHistogramMetricsTimeZoneWeekInterval() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.WEEK,
			TimeRange.of(
				LocalDateTime.parse("2021-03-13T23:59:59"),
				LocalDateTime.parse("2021-02-07T00:00:00")));

		Assert.assertEquals(
			histogramMetrics.toString(), 5, histogramMetrics.size());

		double[] expectedValues = {8, 12, 7, 7, 9};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_last_7_days_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testHistogramMetricsTrendLast7Days() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			histogramMetrics.toString(), 7, histogramMetrics.size());

		_assertHistogramMetric(
			histogramMetrics.get(0), 1, TrendClassification.POSITIVE,
			BigDecimal.valueOf(500.0), 6);
		_assertHistogramMetric(
			histogramMetrics.get(1), 1, TrendClassification.POSITIVE,
			BigDecimal.valueOf(500.0), 6);
		_assertHistogramMetric(
			histogramMetrics.get(2), 1, TrendClassification.POSITIVE,
			BigDecimal.valueOf(400.0), 5);
		_assertHistogramMetric(
			histogramMetrics.get(3), 2, TrendClassification.POSITIVE,
			BigDecimal.valueOf(100.0), 4);
		_assertHistogramMetric(
			histogramMetrics.get(4), 3, TrendClassification.NEUTRAL,
			BigDecimal.valueOf(0.0), 3);
		_assertHistogramMetric(
			histogramMetrics.get(5), 4, TrendClassification.NEGATIVE,
			BigDecimal.valueOf(-50.0), 2);
		_assertHistogramMetric(
			histogramMetrics.get(6), 5, TrendClassification.NEGATIVE,
			BigDecimal.valueOf(-80.0), 1);
	}

	@ElasticsearchIndex(
		name = "journals",
		resourcePath = "histogram_journal_yesterday_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testHistogramMetricsYesterday() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			Interval.DAY, TimeRange.YESTERDAY);

		Assert.assertEquals(
			histogramMetrics.toString(), 24, histogramMetrics.size());

		double[] expectedValues = {
			0, 1, 1, 1, 2, 3, 2, 1, 7, 0, 1, 0, 3, 0, 1, 1, 2, 2, 1, 2, 4, 2, 3,
			2
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	private void _assertHistogramMetric(
		HistogramMetric actualHistogramMetric, double expectedPreviousValue,
		TrendClassification expectedTrendClassification,
		BigDecimal expectedTrendPercentage, double expectedValue) {

		Assert.assertEquals(
			expectedValue, actualHistogramMetric.getValue(), 0.01);
		Assert.assertEquals(
			expectedPreviousValue, actualHistogramMetric.getPreviousValue(),
			0.01);

		Trend actualTrend = actualHistogramMetric.getTrend();

		Assert.assertEquals(
			expectedTrendPercentage, actualTrend.getPercentage());
		Assert.assertEquals(
			expectedTrendClassification, actualTrend.getTrendClassification());
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

		HistogramMetricBag histogramMetricBag =
			_histogramDog.getHistogramMetricBag(
				true, JournalMetricType.VIEWS,
				new SearchQueryContext("1", AssetType.JOURNAL) {
					{
						setInterval(interval.getKey());
						setTimeRange(timeRange);
					}
				});

		return histogramMetricBag.getMetrics();
	}

	@Autowired
	private HistogramDog _histogramDog;

}