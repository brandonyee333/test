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
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataPoint;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class ExperimentDataDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BQSQLResource(resourcePath = "bq_experiment_pages.sql")
	@RepositoryResource(
		repositoryClass = ExperimentRepository.class,
		resourcePath = "osbasahfaroinfo/experiments.json"
	)
	@Test
	public void testExperienceDichotomousDataPoints() {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		_assertDataPoint(
			_experimentDataDog.fetchDichotomousDataPoint(
				"http://192.168.108.90:8080/", PageMetricType.BOUNCE,
				TimeRange.of(localDate.minusDays(2))),
			10, 8D);
	}

	@BQSQLResource(resourcePath = "bq_experiment_pages.sql")
	@RepositoryResource(
		repositoryClass = ExperimentRepository.class,
		resourcePath = "osbasahfaroinfo/experiments.json"
	)
	@Test
	public void testFetchDichotomousDataPointBounceMetric() {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		ExperimentDataPoint<Double> experimentDataPoint =
			_experimentDataDog.fetchDichotomousDataPoint(
				1L, PageMetricType.BOUNCE,
				TimeRange.of(localDate.minusDays(1), localDate.minusDays(3)),
				"1");

		Assertions.assertEquals(4, experimentDataPoint.getTrials());
		Assertions.assertEquals(2.0, experimentDataPoint.getValue(), 0);
	}

	@BQSQLResource(resourcePath = "bq_experiment_pages.sql")
	@RepositoryResource(
		repositoryClass = ExperimentRepository.class,
		resourcePath = "osbasahfaroinfo/experiments.json"
	)
	@Test
	public void testVariantDichotomousDataPoints() {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		_assertDataPoint(
			_experimentDataDog.fetchDichotomousDataPoint(
				1L, PageMetricType.BOUNCE, TimeRange.of(localDate.minusDays(2)),
				"1"),
			5, 3D);
	}

	private void _assertDataPoint(
		ExperimentDataPoint<Double> experimentDataPoint, long trials,
		Double value) {

		Assertions.assertEquals(trials, experimentDataPoint.getTrials());
		Assertions.assertEquals(value, experimentDataPoint.getValue());
	}

	@Autowired
	private ExperimentDataDog _experimentDataDog;

}