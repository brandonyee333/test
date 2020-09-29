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

import com.liferay.osb.asah.backend.dog.VisitorCohortHeatMapDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.CohortHeatMapMetric;
import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gabriel Ibson
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class VisitorCohortHeatMapDogTest {

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor_cohort_heat_map_by_day_page_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorCohortHeatMetricsAllVisitorsByDay() {
		List<CohortHeatMapMetric> cohortHeatMapMetrics =
			_getCohortHeatMapMetrics(SiteMetricType.VISITORS, Interval.DAY);

		Assert.assertEquals(
			cohortHeatMapMetrics.toString(), 43, cohortHeatMapMetrics.size());

		Map<Pair<Integer, Integer>, Double> expectedRetentionsMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 0), 100.0);
					put(Pair.of(0, 5), 100.0);
					put(Pair.of(0, 6), 100.0);
					put(Pair.of(0, 7), 100.0);
					put(Pair.of(1, 0), 60.0);
					put(Pair.of(1, 5), 60.0);
					put(Pair.of(1, 6), 66.66666666666666);
					put(Pair.of(1, 7), 50.0);
					put(Pair.of(2, 0), 37.5);
					put(Pair.of(2, 5), 40.0);
					put(Pair.of(2, 6), 33.33333333333333);
					put(Pair.of(3, 0), 20.0);
					put(Pair.of(3, 5), 20.0);
				}
			};

		double[] expectedRetentions = _getExpectedRetentions(
			expectedRetentionsMap, 8, cohortHeatMapMetrics.size());

		double[] actualRetentions = _getActualRetentions(cohortHeatMapMetrics);

		Assert.assertArrayEquals(expectedRetentions, actualRetentions, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor_cohort_heat_map_by_month_page_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorCohortHeatMetricsAllVisitorsByMonth() {
		List<CohortHeatMapMetric> cohortHeatMapMetrics =
			_getCohortHeatMapMetrics(SiteMetricType.VISITORS, Interval.MONTH);

		Assert.assertEquals(
			cohortHeatMapMetrics.toString(), 34, cohortHeatMapMetrics.size());

		Map<Pair<Integer, Integer>, Double> expectedRetentionsMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 0), 100.0);
					put(Pair.of(0, 4), 100.0);
					put(Pair.of(0, 5), 100.0);
					put(Pair.of(0, 6), 100.0);
					put(Pair.of(1, 0), 60.0);
					put(Pair.of(1, 4), 60.0);
					put(Pair.of(1, 5), 66.66666666666666);
					put(Pair.of(1, 6), 50.0);
					put(Pair.of(2, 0), 37.5);
					put(Pair.of(2, 4), 40.0);
					put(Pair.of(2, 5), 33.33333333333333);
					put(Pair.of(3, 0), 20.0);
					put(Pair.of(3, 4), 20.0);
				}
			};

		double[] expectedRetentions = _getExpectedRetentions(
			expectedRetentionsMap, 7, cohortHeatMapMetrics.size());

		double[] actualRetentions = _getActualRetentions(cohortHeatMapMetrics);

		Assert.assertArrayEquals(expectedRetentions, actualRetentions, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor_cohort_heat_map_by_week_page_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorCohortHeatMetricsAllVisitorsByWeek() {
		List<CohortHeatMapMetric> cohortHeatMapMetrics =
			_getCohortHeatMapMetrics(SiteMetricType.VISITORS, Interval.WEEK);

		Assert.assertEquals(
			cohortHeatMapMetrics.toString(), 34, cohortHeatMapMetrics.size());

		Map<Pair<Integer, Integer>, Double> expectedRetentionsMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 0), 100.0);
					put(Pair.of(0, 4), 100.0);
					put(Pair.of(0, 5), 100.0);
					put(Pair.of(0, 6), 100.0);
					put(Pair.of(1, 0), 60.0);
					put(Pair.of(1, 4), 60.0);
					put(Pair.of(1, 5), 66.66666666666666);
					put(Pair.of(1, 6), 50.0);
					put(Pair.of(2, 0), 37.5);
					put(Pair.of(2, 4), 40.0);
					put(Pair.of(2, 5), 33.33333333333333);
					put(Pair.of(3, 0), 20.0);
					put(Pair.of(3, 4), 20.0);
				}
			};

		double[] expectedRetentions = _getExpectedRetentions(
			expectedRetentionsMap, 7, cohortHeatMapMetrics.size());

		double[] actualRetentions = _getActualRetentions(cohortHeatMapMetrics);

		Assert.assertArrayEquals(expectedRetentions, actualRetentions, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor_cohort_heat_map_by_day_page_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorCohortHeatMetricsAnonymousVisitorsByDay() {
		List<CohortHeatMapMetric> cohortHeatMapMetrics =
			_getCohortHeatMapMetrics(
				SiteMetricType.ANONYMOUS_VISITORS, Interval.DAY);

		Assert.assertEquals(
			cohortHeatMapMetrics.toString(), 43, cohortHeatMapMetrics.size());

		Map<Pair<Integer, Integer>, Double> expectedRetentionsMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 0), 100.0);
					put(Pair.of(0, 5), 100.0);
					put(Pair.of(0, 6), 100.0);
					put(Pair.of(0, 7), 100.0);
					put(Pair.of(1, 0), 75.0);
					put(Pair.of(1, 5), 50.0);
					put(Pair.of(1, 6), 100.0);
					put(Pair.of(1, 7), 100.0);
					put(Pair.of(2, 0), 66.66666666666666);
					put(Pair.of(2, 5), 50.0);
					put(Pair.of(2, 6), 100.0);
					put(Pair.of(3, 0), 50.0);
					put(Pair.of(3, 5), 50.0);
				}
			};

		double[] expectedRetentions = _getExpectedRetentions(
			expectedRetentionsMap, 8, cohortHeatMapMetrics.size());

		double[] actualRetentions = _getActualRetentions(cohortHeatMapMetrics);

		Assert.assertArrayEquals(expectedRetentions, actualRetentions, 0);
	}

	@ElasticsearchIndex(
		name = "pages",
		resourcePath = "visitor_cohort_heat_map_by_day_page_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorCohortHeatMetricsKnownVisitorsByDay() {
		List<CohortHeatMapMetric> cohortHeatMapMetrics =
			_getCohortHeatMapMetrics(
				SiteMetricType.KNOWN_VISITORS, Interval.DAY);

		Assert.assertEquals(
			cohortHeatMapMetrics.toString(), 43, cohortHeatMapMetrics.size());

		Map<Pair<Integer, Integer>, Double> expectedRetentionsMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 0), 100.0);
					put(Pair.of(0, 5), 100.0);
					put(Pair.of(0, 6), 100.0);
					put(Pair.of(0, 7), 100.0);
					put(Pair.of(1, 0), 50.0);
					put(Pair.of(1, 5), 66.66666666666666);
					put(Pair.of(1, 6), 50.0);
					put(Pair.of(2, 0), 20.0);
					put(Pair.of(2, 5), 33.33333333333333);
				}
			};

		double[] expectedRetentions = _getExpectedRetentions(
			expectedRetentionsMap, 8, cohortHeatMapMetrics.size());

		double[] actualRetentions = _getActualRetentions(cohortHeatMapMetrics);

		Assert.assertArrayEquals(expectedRetentions, actualRetentions, 0);
	}

	private double[] _getActualRetentions(
		List<CohortHeatMapMetric> cohortHeatMapMetrics) {

		Stream<CohortHeatMapMetric> cohortHeatMapMetricStream =
			cohortHeatMapMetrics.stream();

		return cohortHeatMapMetricStream.mapToDouble(
			CohortHeatMapMetric::getRetention
		).toArray();
	}

	private List<CohortHeatMapMetric> _getCohortHeatMapMetrics(
		MetricType metricType, Interval interval) {

		return _visitorCohortHeatMapDog.getCohortHeatMapMetrics(
			metricType,
			new SearchQueryContext(AssetType.SITE) {
				{
					setInterval(interval.getKey());
				}
			});
	}

	private double[] _getExpectedRetentions(
		Map<Pair<Integer, Integer>, Double> expectedRetentionsMap,
		int totalLines, int size) {

		double[] expectedRetentions = new double[size];

		int totalLinesMissed = 0;

		for (Map.Entry<Pair<Integer, Integer>, Double> entry :
				expectedRetentionsMap.entrySet()) {

			Pair<Integer, Integer> key = entry.getKey();

			int col = key.getLeft();

			int row = key.getRight();

			int index = (col * totalLines) + row;

			if ((col > 2) && (row == 0)) {
				totalLinesMissed += col - 2;
			}

			if ((totalLinesMissed > 0) && (col > 2)) {
				index -= totalLinesMissed;
			}

			expectedRetentions[index] = entry.getValue();
		}

		return expectedRetentions;
	}

	@Autowired
	private VisitorCohortHeatMapDog _visitorCohortHeatMapDog;

}