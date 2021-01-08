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

import com.liferay.osb.asah.backend.dog.SiteVisitorHeatMapDog;
import com.liferay.osb.asah.backend.model.HeatMapMetric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class SiteVisitorHeatMapDogTest {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "visitor-heat-map-page-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHeatMapMetricsLast24Hours() {
		List<HeatMapMetric> heatMapMetrics =
			_siteVisitorHeatMapDog.getHeatMapMetrics(null, "1", 0, "UTC");

		double[] expectedValues = new double[heatMapMetrics.size()];

		double[] actualValues = _getActualValues(heatMapMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "visitor-heat-map-page-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHeatMapMetricsLast28Days() {
		List<HeatMapMetric> heatMapMetrics =
			_siteVisitorHeatMapDog.getHeatMapMetrics(null, "1", 28, "UTC");

		Map<Pair<Integer, Integer>, Double> expectedValuesMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(2, 10), 1.0);
					put(Pair.of(4, 1), 1.0);
					put(Pair.of(5, 10), 1.0);
					put(Pair.of(6, 1), 1.0);
					put(Pair.of(6, 20), 2.0);
				}
			};

		double[] expectedValues = _getExpectedValues(
			expectedValuesMap, "UTC", heatMapMetrics.size());

		double[] actualValues = _getActualValues(heatMapMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "visitor-heat-map-page-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHeatMapMetricsLast30Days() {
		List<HeatMapMetric> heatMapMetrics =
			_siteVisitorHeatMapDog.getHeatMapMetrics(null, "1", 30, "UTC");

		double[] actualValues = _getActualValues(heatMapMetrics);

		Map<Pair<Integer, Integer>, Double> expectedValuesMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(2, 10), 1.0);
					put(Pair.of(4, 1), 1.0);
					put(Pair.of(5, 10), 1.0);
					put(Pair.of(6, 1), 1.0);
					put(Pair.of(6, 20), 3.0);
				}
			};

		double[] expectedValues = _getExpectedValues(
			expectedValuesMap, "UTC", heatMapMetrics.size());

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "visitor-heat-map-page-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHeatMapMetricsLast90Days() {
		List<HeatMapMetric> heatMapMetrics =
			_siteVisitorHeatMapDog.getHeatMapMetrics(null, "1", 90, "UTC");

		Assert.assertEquals(
			heatMapMetrics.toString(), 168, heatMapMetrics.size());

		double[] actualValues = _getActualValues(heatMapMetrics);

		Map<Pair<Integer, Integer>, Double> expectedValuesMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 23), 1.0);
					put(Pair.of(2, 0), 1.0);
					put(Pair.of(2, 10), 1.0);
					put(Pair.of(4, 1), 1.0);
					put(Pair.of(5, 10), 1.0);
					put(Pair.of(6, 1), 1.0);
					put(Pair.of(6, 13), 1.0);
					put(Pair.of(6, 20), 3.0);
				}
			};

		double[] expectedValues = _getExpectedValues(
			expectedValuesMap, "UTC", heatMapMetrics.size());

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "visitor-heat-map-page-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Ignore
	@Test
	public void testVisitorHeatMapMetricsWithTimeZone() {
		List<HeatMapMetric> heatMapMetrics =
			_siteVisitorHeatMapDog.getHeatMapMetrics(null, "1", 90, "-07:00");

		Map<Pair<Integer, Integer>, Double> expectedValuesMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(0, 16), 1.0);
					put(Pair.of(1, 17), 1.0);
					put(Pair.of(2, 3), 1.0);
					put(Pair.of(3, 18), 1.0);
					put(Pair.of(5, 18), 1.0);
					put(Pair.of(5, 3), 1.0);
					put(Pair.of(6, 13), 3.0);
					put(Pair.of(6, 6), 1.0);
				}
			};

		double[] expectedValues = _getExpectedValues(
			expectedValuesMap, "-07:00", heatMapMetrics.size());

		double[] actualValues = _getActualValues(heatMapMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);

		heatMapMetrics = _siteVisitorHeatMapDog.getHeatMapMetrics(
			null, "1", 90, "-03:00");

		expectedValuesMap = new HashMap<Pair<Integer, Integer>, Double>() {
			{
				put(Pair.of(0, 20), 1.0);
				put(Pair.of(1, 21), 1.0);
				put(Pair.of(2, 7), 1.0);
				put(Pair.of(3, 22), 1.0);
				put(Pair.of(5, 22), 1.0);
				put(Pair.of(5, 7), 1.0);
				put(Pair.of(6, 10), 1.0);
				put(Pair.of(6, 17), 3.0);
			}
		};

		expectedValues = _getExpectedValues(
			expectedValuesMap, "-03:00", heatMapMetrics.size());

		actualValues = _getActualValues(heatMapMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "visitor-heat-map-page-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVisitorHeatMapMetricsYesterday() {
		List<HeatMapMetric> heatMapMetrics =
			_siteVisitorHeatMapDog.getHeatMapMetrics(null, "1", 1, "UTC");

		Map<Pair<Integer, Integer>, Double> expectedValuesMap =
			new HashMap<Pair<Integer, Integer>, Double>() {
				{
					put(Pair.of(6, 20), 2.0);
				}
			};

		double[] expectedValues = _getExpectedValues(
			expectedValuesMap, "UTC", heatMapMetrics.size());

		double[] actualValues = _getActualValues(heatMapMetrics);

		Assert.assertArrayEquals(expectedValues, actualValues, 0);
	}

	private double[] _getActualValues(List<HeatMapMetric> heatMapMetrics) {
		double[] actualValues = new double[heatMapMetrics.size()];

		for (int i = 0; i < heatMapMetrics.size(); i++) {
			HeatMapMetric heatMapMetric = heatMapMetrics.get(i);

			actualValues[i] = heatMapMetric.getValue();
		}

		return actualValues;
	}

	private double[] _getExpectedValues(
		Map<Pair<Integer, Integer>, Double> expectedValueMap, String timeZoneId,
		int size) {

		double[] expectedValues = new double[size];

		LocalDateTime localDateTime = DateUtil.toLocalDateTime(
			new Date(), ZoneId.of(timeZoneId));

		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

		for (Map.Entry<Pair<Integer, Integer>, Double> entry :
				expectedValueMap.entrySet()) {

			Pair<Integer, Integer> key = entry.getKey();

			int day = key.getLeft() + dayOfWeek.getValue();

			if (day >= 7) {
				day = day - 7;
			}

			int hour = key.getRight();

			int index = (day * 24) + hour;

			expectedValues[index] = entry.getValue();
		}

		return expectedValues;
	}

	@Autowired
	private SiteVisitorHeatMapDog _siteVisitorHeatMapDog;

}