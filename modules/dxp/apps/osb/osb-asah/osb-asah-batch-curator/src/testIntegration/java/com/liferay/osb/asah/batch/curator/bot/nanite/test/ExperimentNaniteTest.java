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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.ExperimentNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.ExperimentMetric;
import com.liferay.osb.asah.common.http.ExperimentHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.model.GoalMetric;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;

import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class ExperimentNaniteTest extends BaseNaniteTestCase {

	@BeforeEach
	public void setUp() throws Exception {
		Experiment experiment = FaroInfoTestUtil.buildExperiment(
			ExperimentStatus.RUNNING, GoalMetric.CLICK_RATE, 1L);

		experiment.setIsNew(Boolean.TRUE);

		_experimentRepository.save(experiment);
	}

	@Test
	public void testFinishedExperimentNoWinner() throws Exception {
		_testFinishedExperiment(
			ExperimentStatus.FINISHED_NO_WINNER,
			JSONUtil.put(
				"estimatedDaysLeft", 0
			).put(
				"processedDate", DateUtil.newDateString()
			).put(
				"variantMetrics",
				JSONUtil.putAll(
					_createExperimentVariantMetricJSONObject(
						new double[] {30, 45}, true, "1"),
					_createExperimentVariantMetricJSONObject(
						new double[] {20, 32}, false, "2"))
			),
			null);

		Mockito.verify(
			_dxpClient
		).updateDXPExperimentStatus(
			ArgumentMatchers.eq(1L), ArgumentMatchers.eq(1L),
			ArgumentMatchers.eq(ExperimentStatus.FINISHED_NO_WINNER),
			ArgumentMatchers.isNull()
		);
	}

	@Test
	public void testFinishedExperimentWinner() throws Exception {
		_testFinishedExperiment(
			ExperimentStatus.FINISHED_WINNER,
			JSONUtil.put(
				"estimatedDaysLeft", 0
			).put(
				"processedDate", DateUtil.newDateString()
			).put(
				"variantMetrics",
				JSONUtil.putAll(
					_createExperimentVariantMetricJSONObject(
						new double[] {30, 45}, true, "10"),
					_createExperimentVariantMetricJSONObject(
						new double[] {15, 28}, false, "20"))
			),
			"10");

		Mockito.verify(
			_dxpClient
		).updateDXPExperimentStatus(
			ArgumentMatchers.eq(1L), ArgumentMatchers.eq(1L),
			ArgumentMatchers.eq(ExperimentStatus.FINISHED_WINNER),
			ArgumentMatchers.eq("10")
		);
	}

	@Test
	public void testFinishedExperimentWinnerBounceRate() throws Exception {
		Experiment experiment = FaroInfoTestUtil.buildExperiment(
			ExperimentStatus.RUNNING, GoalMetric.BOUNCE_RATE, 1L);

		_experimentRepository.save(experiment);

		_testFinishedExperiment(
			ExperimentStatus.FINISHED_WINNER,
			JSONUtil.put(
				"estimatedDaysLeft", 0
			).put(
				"processedDate", DateUtil.newDateString()
			).put(
				"variantMetrics",
				JSONUtil.putAll(
					_createExperimentVariantMetricJSONObject(
						new double[] {30, 45}, true, "10"),
					_createExperimentVariantMetricJSONObject(
						new double[] {15, 28}, false, "20"))
			),
			"20");

		Mockito.verify(
			_dxpClient
		).updateDXPExperimentStatus(
			ArgumentMatchers.eq(1L), ArgumentMatchers.eq(1L),
			ArgumentMatchers.eq(ExperimentStatus.FINISHED_WINNER),
			ArgumentMatchers.eq("20")
		);
	}

	@Test
	public void testNotFinishedExperiment() throws Exception {
		Mockito.when(
			_experimentHttp.getExperimentMetricsJSONObject(
				ArgumentMatchers.anyString())
		).thenReturn(
			JSONUtil.put("estimatedDaysLeft", 1)
		);

		_experimentNanite.run(null);

		Optional<Experiment> experimentOptional =
			_experimentRepository.findById(1L);

		Experiment experiment = experimentOptional.get();

		Assertions.assertEquals(
			ExperimentStatus.RUNNING, experiment.getExperimentStatus());
		Assertions.assertNull(experiment.getFinishedDate());
	}

	private JSONObject _createExperimentVariantMetricJSONObject(
		double[] confidenceInterval, boolean control, String dxpVariantId) {

		return JSONUtil.put(
			"confidenceInterval", confidenceInterval
		).put(
			"control", control
		).put(
			"dxpVariantId", dxpVariantId
		);
	}

	private void _testFinishedExperiment(
			ExperimentStatus expectedStatus, JSONObject responseBody,
			String winnerDXPVariantId)
		throws Exception {

		Mockito.when(
			_experimentHttp.getExperimentMetricsJSONObject(
				ArgumentMatchers.anyString())
		).thenReturn(
			responseBody
		);

		_experimentNanite.run(null);

		Optional<Experiment> experimentOptional =
			_experimentRepository.findById(1L);

		Experiment experiment = experimentOptional.get();

		Set<ExperimentMetric> experimentMetrics =
			experiment.getExperimentMetrics();

		Assertions.assertNotEquals(experimentMetrics.size(), 0);

		Assertions.assertEquals(
			expectedStatus, experiment.getExperimentStatus());
		Assertions.assertNotNull(experiment.getFinishedDate());
		Assertions.assertEquals(
			winnerDXPVariantId, experiment.getWinnerDXPVariantId());
	}

	@MockBean
	private DXPClient _dxpClient;

	@MockBean
	private ExperimentHttp _experimentHttp;

	@Autowired
	private ExperimentNanite _experimentNanite;

	@Autowired
	private ExperimentRepository _experimentRepository;

}