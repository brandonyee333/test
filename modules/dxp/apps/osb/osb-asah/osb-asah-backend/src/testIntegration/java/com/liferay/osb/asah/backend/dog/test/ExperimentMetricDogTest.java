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

import com.liferay.osb.asah.backend.dog.ExperimentDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataPoint;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentMetricDog;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ExperimentMetricDogTest {

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment-metrics-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testEstimateDaysLeft() {
		_mockVariantData("37729", 1000, 500);
		_mockVariantData("DEFAULT", 800, 250);

		ExperimentMetrics experimentMetrics =
			_experimentMetricDog.calculateMetrics(
				_experimentDog.getExperiment("4"));

		Assert.assertEquals(25.42, experimentMetrics.getCompletion(), 0.1D);

		Assert.assertEquals(
			Long.valueOf(18), experimentMetrics.getEstimatedDaysLeft());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment-metrics-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testEstimateDaysLeftNoData() {
		_mockVariantData("37729", 0, 0);
		_mockVariantData("DEFAULT", 10, 5);

		ExperimentMetrics experimentMetrics =
			_experimentMetricDog.calculateMetrics(
				_experimentDog.getExperiment("4"));

		Assert.assertEquals(0D, experimentMetrics.getCompletion(), 0D);

		Assert.assertNull(experimentMetrics.getEstimatedDaysLeft());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment-metrics-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testEstimateDaysLeftVariantComplete() {
		_mockVariantData("37729", 10000, 5000D);
		_mockVariantData("DEFAULT", 800, 250D);

		ExperimentMetrics experimentMetrics =
			_experimentMetricDog.calculateMetrics(
				_experimentDog.getExperiment("4"));

		Assert.assertEquals(61.3, experimentMetrics.getCompletion(), 0.1D);

		Assert.assertEquals(
			Long.valueOf(18), experimentMetrics.getEstimatedDaysLeft());
	}

	private void _mockVariantData(
		String dxpVariantId, long trials, double value) {

		Mockito.when(
			_experimentDataDog.fetchDichotomousDataPoint(
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.any(MetricType.class), Mockito.anyString(),
				Mockito.any(TimeRange.class), Mockito.eq(dxpVariantId))
		).thenReturn(
			new ExperimentDataPoint<>(trials, value)
		);
	}

	@MockBean
	private ExperimentDataDog _experimentDataDog;

	@Autowired
	private ExperimentDog _experimentDog;

	@Autowired
	private ExperimentMetricDog _experimentMetricDog;

}