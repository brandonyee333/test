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
import com.liferay.osb.asah.backend.dog.experiment.ExperimentMetricDog;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.ExperimentMetric;
import com.liferay.osb.asah.common.entity.ExperimentVariantMetric;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ExperimentDogTest {

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteExperiment() {
		Experiment experiment = _experimentDog.fetchExperiment(2L);

		Assert.assertNotNull(experiment);

		Assert.assertEquals(
			ExperimentStatus.DRAFT, experiment.getExperimentStatus());

		_experimentDog.deleteExperiment(2L, true);

		Mockito.verify(
			_dxpClient, Mockito.times(1)
		).deleteDXPExperiment(
			ArgumentMatchers.eq(333962835564819755L), ArgumentMatchers.eq(2L)
		);

		Assert.assertNull(_experimentDog.fetchExperiment(2L));
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testDeleteExperimentNotAllowed() {
		Experiment experiment = _experimentDog.fetchExperiment(1L);

		Assert.assertNotNull(experiment);

		Assert.assertEquals(
			ExperimentStatus.RUNNING, experiment.getExperimentStatus());

		_experimentDog.deleteExperiment(1L, true);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExperimentNotFound() {
		Experiment experiment = _experimentDog.fetchExperiment(0L);

		Assert.assertNull(experiment);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testExperimentNotFoundWhileEstimatingDaysDuration() {
		_experimentDog.getExperimentEstimatedDaysDuration(95, null, 0L);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment_metrics_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetEmptyExperimentMetricsRunningExperiment() {
		ExperimentMetric experimentMetric = _experimentDog.getExperimentMetric(
			1L);

		Assert.assertEquals(2, experimentMetric.getElapsedDays(), 0.0);
		Assert.assertNull(experimentMetric.getEstimatedDaysLeft());

		List<ExperimentVariantMetric> experimentVariantMetrics =
			new ArrayList<>(experimentMetric.getExperimentVariantMetrics());

		Assert.assertEquals(
			experimentVariantMetrics.toString(), 2,
			experimentVariantMetrics.size());

		_assertExperimentVariantMetric(
			experimentVariantMetrics.get(0),
			new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)},
			0, 0, 0, "1");
		_assertExperimentVariantMetric(
			experimentVariantMetrics.get(1),
			new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)},
			0, 0, 0, "2");
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperiment() {
		Experiment experiment = _experimentDog.getExperiment(2L);

		Assert.assertEquals("Regular test", experiment.getName());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment_metrics_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testGetExperimentMetricsDraftExperiment() {
		_experimentDog.getExperimentMetric(2L);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment_metrics_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperimentMetricsRunningExperiment() {
		ExperimentMetric experimentMetric = _experimentDog.getExperimentMetric(
			3L);

		Assert.assertEquals(2, experimentMetric.getElapsedDays(), 0.0);
		Assert.assertEquals(
			Long.valueOf(12), experimentMetric.getEstimatedDaysLeft());

		List<ExperimentVariantMetric> experimentVariantMetrics =
			new ArrayList<>(experimentMetric.getExperimentVariantMetrics());

		Assert.assertEquals(
			experimentVariantMetrics.toString(), 2,
			experimentVariantMetrics.size());

		_assertExperimentVariantMetric(
			experimentVariantMetrics.get(0),
			new BigDecimal[] {BigDecimal.valueOf(0.6), BigDecimal.valueOf(2.6)},
			0.4, 0.5, 0.25, "1");
		_assertExperimentVariantMetric(
			experimentVariantMetrics.get(1),
			new BigDecimal[] {BigDecimal.valueOf(0.7), BigDecimal.valueOf(3.5)},
			0.6, 0.6, 0.5, "2");
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperimentsAll() {
		List<Experiment> experiments = _experimentDog.getExperiments(
			1L, null, 0, 10, Sort.asc("name.raw"));

		Assert.assertNotNull(experiments);
		Assert.assertEquals(experiments.toString(), 3, experiments.size());

		Assert.assertEquals(experiments.toString(), 3, experiments.size());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetExperimentSessionHistogramMetrics() {
		List<HistogramMetric> experimentSessionHistogramMetrics =
			_experimentDog.getExperimentSessionHistogramMetrics(1L, null);

		Assert.assertEquals(
			experimentSessionHistogramMetrics.toString(), 3,
			experimentSessionHistogramMetrics.size());

		LocalDateTime nowLocalDateTime = LocalDateTime.of(
			LocalDate.now(ZoneOffset.UTC), LocalTime.MIDNIGHT);

		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime.minusDays(2)), 4,
			experimentSessionHistogramMetrics.get(0));
		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime.minusDays(1)), 3,
			experimentSessionHistogramMetrics.get(1));
		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime), 2,
			experimentSessionHistogramMetrics.get(2));
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperimentsPaginated() {
		List<Experiment> experiments = _experimentDog.getExperiments(
			1L, null, 1, 1, Sort.asc("name.raw"));

		Assert.assertNotNull(experiments);
		Assert.assertEquals(experiments.toString(), 1, experiments.size());

		Assert.assertEquals(experiments.toString(), 1, experiments.size());

		Experiment experiment = experiments.get(0);

		Assert.assertEquals("Crazy test", experiment.getName());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetExperimentVariant1SessionHistogramMetrics() {
		List<HistogramMetric> experimentSessionHistogramMetrics =
			_experimentDog.getExperimentSessionHistogramMetrics(1L, "1");

		Assert.assertEquals(
			experimentSessionHistogramMetrics.toString(), 3,
			experimentSessionHistogramMetrics.size());

		LocalDateTime nowLocalDateTime = LocalDateTime.of(
			LocalDate.now(ZoneOffset.UTC), LocalTime.MIDNIGHT);

		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime.minusDays(2)), 3,
			experimentSessionHistogramMetrics.get(0));
		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime.minusDays(1)), 1,
			experimentSessionHistogramMetrics.get(1));
		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime), 1,
			experimentSessionHistogramMetrics.get(2));
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetExperimentVariant2SessionHistogramMetrics() {
		List<HistogramMetric> experimentSessionHistogramMetrics =
			_experimentDog.getExperimentSessionHistogramMetrics(1L, "2");

		Assert.assertEquals(
			experimentSessionHistogramMetrics.toString(), 3,
			experimentSessionHistogramMetrics.size());

		LocalDateTime nowLocalDateTime = LocalDateTime.of(
			LocalDate.now(ZoneOffset.UTC), LocalTime.MIDNIGHT);

		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime.minusDays(2)), 1,
			experimentSessionHistogramMetrics.get(0));
		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime.minusDays(1)), 2,
			experimentSessionHistogramMetrics.get(1));
		_assertSessionHistogramMetric(
			String.valueOf(nowLocalDateTime), 1,
			experimentSessionHistogramMetrics.get(2));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetTotalSessions() {
		Long totalSessions = _experimentDog.getExperimentSessions(1L);

		Assert.assertNotNull(totalSessions);
		Assert.assertEquals(9L, totalSessions.longValue());
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetVariantUniqueVisitors() {
		Long variantUniqueVisitors1 = _experimentDog.getVariantUniqueVisitors(
			1L, "1");

		Assert.assertNotNull(variantUniqueVisitors1);
		Assert.assertEquals(3L, variantUniqueVisitors1.longValue());

		Long variantUniqueVisitors2 = _experimentDog.getVariantUniqueVisitors(
			1L, "2");

		Assert.assertNotNull(variantUniqueVisitors2);
		Assert.assertEquals(3L, variantUniqueVisitors2.longValue());
	}

	private void _assertExperimentVariantMetric(
		ExperimentVariantMetric actualExperimentVariantMetric,
		BigDecimal[] expectedConfidenceIntervals, double expectetedImprovement,
		double expectedMedian, double expectedProbabilityToWin,
		String expectedDXPVariantId) {

		Assert.assertArrayEquals(
			_mapToDoubles(expectedConfidenceIntervals),
			_mapToDoubles(
				actualExperimentVariantMetric.getConfidenceIntervals()),
			.1);

		Assert.assertEquals(
			expectedMedian,
			Optional.ofNullable(
				actualExperimentVariantMetric.getMedian()
			).orElse(
				0.0
			),
			.1);
		Assert.assertEquals(
			expectedProbabilityToWin,
			Optional.ofNullable(
				actualExperimentVariantMetric.getProbabilityToWin()
			).orElse(
				0.0
			),
			.1);
		Assert.assertEquals(
			expectedDXPVariantId,
			actualExperimentVariantMetric.getDXPVariantId());
		Assert.assertEquals(
			expectetedImprovement,
			Optional.ofNullable(
				actualExperimentVariantMetric.getImprovement()
			).orElse(
				0.0
			),
			.1);
	}

	private void _assertSessionHistogramMetric(
		String expectedHistogramMetricKey, double expectedHistogramMetricValue,
		HistogramMetric actualHistogramMetric) {

		Assert.assertEquals(
			expectedHistogramMetricKey, actualHistogramMetric.getKey());
		Assert.assertEquals(
			expectedHistogramMetricValue, actualHistogramMetric.getValue(), .1);
	}

	private double[] _mapToDoubles(BigDecimal[] bigDecimals) {
		ToDoubleFunction<BigDecimal> toDoubleFunction = BigDecimal::doubleValue;

		return Stream.of(
			bigDecimals
		).mapToDouble(
			toDoubleFunction
		).toArray();
	}

	@MockBean
	private DXPClient _dxpClient;

	@Autowired
	private ExperimentDog _experimentDog;

	@MockBean
	private ExperimentMetricDog _experimentMetricDog;

}