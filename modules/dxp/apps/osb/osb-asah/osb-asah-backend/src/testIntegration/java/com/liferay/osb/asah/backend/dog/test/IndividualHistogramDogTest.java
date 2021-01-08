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

import com.liferay.osb.asah.backend.dog.IndividualHistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.IndividualMetricType;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class IndividualHistogramDogTest {

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-histogram-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testActiveIndividualHistogramMetrics() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			true, IndividualMetricType.TOTAL_INDIVIDUALS);

		Assert.assertEquals(
			histogramMetrics.toString(), 30, histogramMetrics.size());

		double[] expectedValues = {
			0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 2, 2
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-histogram-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAnonymousIndividualHistogramMetrics() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			null, IndividualMetricType.ANONYMOUS_INDIVIDUALS);

		Assert.assertEquals(
			histogramMetrics.toString(), 30, histogramMetrics.size());

		double[] expectedValues = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-histogram-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testInactiveIndividualHistogramMetrics() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			false, IndividualMetricType.TOTAL_INDIVIDUALS);

		Assert.assertEquals(
			histogramMetrics.toString(), 30, histogramMetrics.size());

		double[] expectedValues = {
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-histogram-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testKnownIndividualHistogramMetrics() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			null, IndividualMetricType.KNOWN_INDIVIDUALS);

		Assert.assertEquals(
			histogramMetrics.toString(), 30, histogramMetrics.size());

		double[] expectedValues = {
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2,
			2, 2, 2, 3, 3, 4, 4
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-histogram-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testTotalIndividualHistogramMetrics() {
		List<HistogramMetric> histogramMetrics = _getHistogramMetrics(
			null, IndividualMetricType.TOTAL_INDIVIDUALS);

		Assert.assertEquals(
			histogramMetrics.toString(), 30, histogramMetrics.size());

		double[] expectedValues = {
			1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4,
			4, 4, 4, 4, 4, 5, 5
		};

		Assert.assertArrayEquals(
			expectedValues, _getActualValues(histogramMetrics), 0);
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
		Boolean active, MetricType metricType) {

		return _individualHistogramDog.getHistogramMetrics(
			metricType,
			new SearchQueryContext(AssetType.INDIVIDUAL_METRIC) {
				{
					setActive(active);
					setInterval("D");
					setRangeKey(30);
				}
			});
	}

	@Autowired
	private IndividualHistogramDog _individualHistogramDog;

}