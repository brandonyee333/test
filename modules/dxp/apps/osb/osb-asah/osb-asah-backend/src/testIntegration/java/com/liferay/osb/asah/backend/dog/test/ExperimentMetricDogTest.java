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
import com.liferay.osb.asah.backend.dog.ExperimentDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataPoint;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentMetricDog;
import com.liferay.osb.asah.common.entity.ExperimentMetric;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author André Miranda
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class ExperimentMetricDogTest
	implements OSBAsahBackendSpringTestContext {

	@RepositoryResource(
		repositoryClass = ExperimentRepository.class,
		resourcePath = "osbasahfaroinfo/experiment_metrics.json"
	)
	@Test
	public void testEstimateDaysLeft() {
		_mockVariantData("37729", 1000, 500);
		_mockVariantData("DEFAULT", 800, 250);

		ExperimentMetric experimentMetric =
			_experimentMetricDog.calculateExperimentMetric(
				_experimentDog.getExperiment(4L));

		Assertions.assertEquals(25.42, experimentMetric.getCompletion(), 0.1D);

		Assertions.assertEquals(18, experimentMetric.getEstimatedDaysLeft());
	}

	@RepositoryResource(
		repositoryClass = ExperimentRepository.class,
		resourcePath = "osbasahfaroinfo/experiment_metrics.json"
	)
	@Test
	public void testEstimateDaysLeftNoData() {
		_mockVariantData("37729", 0, 0);
		_mockVariantData("DEFAULT", 10, 5);

		ExperimentMetric experimentMetric =
			_experimentMetricDog.calculateExperimentMetric(
				_experimentDog.getExperiment(4L));

		Assertions.assertNull(experimentMetric.getCompletion());

		Assertions.assertNull(experimentMetric.getEstimatedDaysLeft());
	}

	@RepositoryResource(
		repositoryClass = ExperimentRepository.class,
		resourcePath = "osbasahfaroinfo/experiment_metrics.json"
	)
	@Test
	public void testEstimateDaysLeftVariantComplete() {
		_mockVariantData("37729", 10000, 5000D);
		_mockVariantData("DEFAULT", 800, 250D);

		ExperimentMetric experimentMetric =
			_experimentMetricDog.calculateExperimentMetric(
				_experimentDog.getExperiment(4L));

		Assertions.assertEquals(61.3, experimentMetric.getCompletion(), 0.1D);

		Assertions.assertEquals(18, experimentMetric.getEstimatedDaysLeft());
	}

	private void _mockVariantData(
		String dxpVariantId, long trials, double value) {

		Mockito.when(
			_experimentDataDog.fetchDichotomousDataPoint(
				ArgumentMatchers.any(),
				ArgumentMatchers.any(PageMetricType.class),
				ArgumentMatchers.any(TimeRange.class),
				ArgumentMatchers.eq(dxpVariantId))
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