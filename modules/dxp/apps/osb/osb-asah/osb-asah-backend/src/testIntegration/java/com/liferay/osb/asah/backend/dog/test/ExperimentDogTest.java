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
import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.VariantMetrics;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import java.util.List;

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
public class ExperimentDogTest {

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteExperiment() {
		Experiment experiment = _experimentDog.fetchExperiment("2");

		Assert.assertNotNull(experiment);

		Assert.assertEquals(
			ExperimentStatus.DRAFT, experiment.getExperimentStatus());

		_experimentDog.deleteExperiment("2", true);

		Mockito.verify(
			_dxpClient, Mockito.times(1)
		).deleteDXPExperiment(
			Mockito.eq("333962835564819755"), Mockito.eq("2")
		);

		Assert.assertNull(_experimentDog.fetchExperiment("2"));
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testDeleteExperimentNotAllowed() {
		Experiment experiment = _experimentDog.fetchExperiment("1");

		Assert.assertNotNull(experiment);

		Assert.assertEquals(
			ExperimentStatus.RUNNING, experiment.getExperimentStatus());

		_experimentDog.deleteExperiment("1", true);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExperimentNotFound() {
		Experiment experiment = _experimentDog.fetchExperiment("0");

		Assert.assertNull(experiment);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testExperimentNotFoundWhileEstimatingDaysDuration() {
		_experimentDog.getExperimentEstimatedDaysDuration(95, null, "0");
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment_metrics_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetEmptyExperimentMetricsRunningExperiment() {
		ExperimentMetrics experimentMetrics =
			_experimentDog.getExperimentMetrics("1");

		Assert.assertEquals(2, experimentMetrics.getElapsedDays());
		Assert.assertNull(experimentMetrics.getEstimatedDaysLeft());

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		Assert.assertEquals(
			variantMetricsList.toString(), 2, variantMetricsList.size());

		_assertVariantMetrics(
			variantMetricsList.get(0), new double[] {0, 0}, 0, 0, 0, "1");
		_assertVariantMetrics(
			variantMetricsList.get(1), new double[] {0, 0}, 0, 0, 0, "2");
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperiment() {
		Experiment experiment = _experimentDog.getExperiment("2");

		Assert.assertEquals("Regular test", experiment.getName());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment_metrics_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testGetExperimentMetricsDraftExperiment() {
		_experimentDog.getExperimentMetrics("2");
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiment_metrics_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperimentMetricsRunningExperiment() {
		ExperimentMetrics experimentMetrics =
			_experimentDog.getExperimentMetrics("3");

		Assert.assertEquals(2, experimentMetrics.getElapsedDays());
		Assert.assertEquals(
			Long.valueOf(12), experimentMetrics.getEstimatedDaysLeft());

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		Assert.assertEquals(
			variantMetricsList.toString(), 2, variantMetricsList.size());

		_assertVariantMetrics(
			variantMetricsList.get(0), new double[] {0.6, 2.6}, 0.4, 0.5, 0.25,
			"1");
		_assertVariantMetrics(
			variantMetricsList.get(1), new double[] {0.7, 3.5}, 0.6, 0.6, 0.5,
			"2");
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetExperimentsAll() {
		ResultBag<Experiment> resultBag = _experimentDog.getExperimentResultBag(
			"1", null, 10, Sort.asc("name.raw"), 0);

		Assert.assertNotNull(resultBag);
		Assert.assertEquals(3, resultBag.getTotal());

		List<Experiment> experiments = resultBag.getResults();

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
			_experimentDog.getExperimentSessionHistogramMetrics("1", null);

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
		ResultBag<Experiment> resultBag = _experimentDog.getExperimentResultBag(
			"1", null, 1, Sort.asc("name.raw"), 1);

		Assert.assertNotNull(resultBag);
		Assert.assertEquals(3, resultBag.getTotal());

		List<Experiment> experiments = resultBag.getResults();

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
			_experimentDog.getExperimentSessionHistogramMetrics("1", "1");

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
			_experimentDog.getExperimentSessionHistogramMetrics("1", "2");

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
		Long totalSessions = _experimentDog.getExperimentSessions("1");

		Assert.assertNotNull(totalSessions);
		Assert.assertEquals(9L, totalSessions.longValue());
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "experiment_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetVariantUniqueVisitors() {
		Long variant1UniqueVisitors = _experimentDog.getVariantUniqueVisitors(
			"1", "1");

		Assert.assertNotNull(variant1UniqueVisitors);
		Assert.assertEquals(3, variant1UniqueVisitors.longValue());

		Long variant2UniqueVisitors = _experimentDog.getVariantUniqueVisitors(
			"1", "2");

		Assert.assertNotNull(variant2UniqueVisitors);
		Assert.assertEquals(3, variant2UniqueVisitors.longValue());
	}

	private void _assertSessionHistogramMetric(
		String expectedHistogramMetricKey, double expectedHistogramMetricValue,
		HistogramMetric actualHistogramMetric) {

		Assert.assertEquals(
			expectedHistogramMetricKey, actualHistogramMetric.getKey());
		Assert.assertEquals(
			expectedHistogramMetricValue, actualHistogramMetric.getValue(), .1);
	}

	private void _assertVariantMetrics(
		VariantMetrics actualVariantMetrics,
		double[] expectedConfidenceIntervalArray, double expectetedImprovement,
		double expectedMedian, double expectedProbabilityToWin,
		String expectedDXPVariantId) {

		Assert.assertArrayEquals(
			expectedConfidenceIntervalArray,
			actualVariantMetrics.getConfidenceIntervalArray(), .1);
		Assert.assertEquals(
			expectedMedian, actualVariantMetrics.getMedian(), .1);
		Assert.assertEquals(
			expectedProbabilityToWin,
			actualVariantMetrics.getProbabilityToWin(), .1);
		Assert.assertEquals(
			expectedDXPVariantId, actualVariantMetrics.getDXPVariantId());
		Assert.assertEquals(
			expectetedImprovement, actualVariantMetrics.getImprovement(), .1);
	}

	@MockBean
	private DXPClient _dxpClient;

	@Autowired
	private ExperimentDog _experimentDog;

	@MockBean
	private ExperimentMetricDog _experimentMetricDog;

}