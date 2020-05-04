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

import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataPoint;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ExperimentDataDogTest {

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment-pages-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExperienceContinuousDataPoints() {
		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			_experimentDataDog.fetchContinuousDataPoints(
				"1", PageMetricType.TIME_ON_PAGE);

		Assert.assertEquals(
			experimentDataPoints.toString(), 6, experimentDataPoints.size());

		_assertDataPoint(experimentDataPoints.get(0), 0, new Double[0]);
		_assertDataPoint(experimentDataPoints.get(1), 0, new Double[0]);
		_assertDataPoint(experimentDataPoints.get(2), 0, new Double[0]);
		_assertDataPoint(experimentDataPoints.get(3), 0, new Double[0]);

		_assertDataPoint(
			experimentDataPoints.get(4), 4,
			new Double[] {61000D, 18000D, 70600D, 30000D});

		_assertDataPoint(
			experimentDataPoints.get(5), 3,
			new Double[] {44000D, 28000D, 1800D});
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment-pages-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExperienceDichotomousDataPoints() {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		_assertDataPoint(
			_experimentDataDog.fetchDichotomousDataPoint(
				"1", "1", "1", PageMetricType.BOUNCE_RATE,
				"http://192.168.108.90:8080/",
				TimeRange.of(localDate.minusDays(2)), null),
			9, 7D);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment-pages-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVariantContinuousDataPoints() {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);

		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			_experimentDataDog.fetchContinuousDataPoints(
				PageMetricType.TIME_ON_PAGE, localDateTime.minusDays(2), "1");

		Assert.assertEquals(
			experimentDataPoints.toString(), 2, experimentDataPoints.size());

		_assertDataPoint(
			experimentDataPoints.get(0), 3,
			new Double[] {61000D, 18000D, 70600D});

		_assertDataPoint(experimentDataPoints.get(1), 1, new Double[] {1800D});

		experimentDataPoints = _experimentDataDog.fetchContinuousDataPoints(
			PageMetricType.TIME_ON_PAGE, localDateTime.minusDays(2), "2");

		Assert.assertEquals(
			experimentDataPoints.toString(), 2, experimentDataPoints.size());

		_assertDataPoint(experimentDataPoints.get(0), 1, new Double[] {30000D});

		_assertDataPoint(
			experimentDataPoints.get(1), 2, new Double[] {44000D, 28000D});
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment-pages-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testVariantDichotomousDataPoints() {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		_assertDataPoint(
			_experimentDataDog.fetchDichotomousDataPoint(
				"1", "1", "1", PageMetricType.BOUNCE_RATE,
				"http://192.168.108.90:8080/",
				TimeRange.of(localDate.minusDays(2)), "1"),
			5, 3D);
	}

	private void _assertDataPoint(
		ExperimentDataPoint<Double> experimentDataPoint, long trials,
		Double value) {

		Assert.assertEquals(trials, experimentDataPoint.getTrials());
		Assert.assertEquals(value, experimentDataPoint.getValue());
	}

	private void _assertDataPoint(
		ExperimentDataPoint<Double[]> experimentDataPoint, long trials,
		Double[] value) {

		Assert.assertEquals(trials, experimentDataPoint.getTrials());
		Assert.assertArrayEquals(value, experimentDataPoint.getValue());
	}

	@Autowired
	private ExperimentDataDog _experimentDataDog;

}