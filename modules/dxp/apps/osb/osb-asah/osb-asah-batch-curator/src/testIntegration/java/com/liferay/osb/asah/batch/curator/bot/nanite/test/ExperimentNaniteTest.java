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
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.http.ExperimentHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
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
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class,
		OSBAsahRedisDisabledTestConfiguration.class
	}
)
public class ExperimentNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		faroInfoElasticsearchInvoker.add(
			"experiments",
			FaroInfoTestUtil.buildExperimentJSONObject(
				"1", "CLICK_RATE", "RUNNING"));
	}

	@Test
	public void testFinishedExperimentNoWinner() throws Exception {
		_testFinishedExperiment(
			"FINISHED_NO_WINNER",
			JSONUtil.put(
				"estimatedDaysLeft", 0
			).put(
				"processedDate", DateUtil.newUTCDateString()
			).put(
				"variantMetrics",
				JSONUtil.putAll(
					_createVariantMetricsJSONObject(
						new double[] {30, 45}, true, "1"),
					_createVariantMetricsJSONObject(
						new double[] {20, 32}, false, "2"))
			),
			null);

		Mockito.verify(
			_dxpClient
		).updateDXPExperimentStatus(
			Mockito.eq("1"), Mockito.eq("1"),
			Mockito.eq(ExperimentStatus.FINISHED_NO_WINNER),
			Mockito.isNull(String.class)
		);
	}

	@Test
	public void testFinishedExperimentWinner() throws Exception {
		_testFinishedExperiment(
			"FINISHED_WINNER",
			JSONUtil.put(
				"estimatedDaysLeft", 0
			).put(
				"processedDate", DateUtil.newUTCDateString()
			).put(
				"variantMetrics",
				JSONUtil.putAll(
					_createVariantMetricsJSONObject(
						new double[] {30, 45}, true, "10"),
					_createVariantMetricsJSONObject(
						new double[] {15, 28}, false, "20"))
			),
			"10");

		Mockito.verify(
			_dxpClient
		).updateDXPExperimentStatus(
			Mockito.eq("1"), Mockito.eq("1"),
			Mockito.eq(ExperimentStatus.FINISHED_WINNER), Mockito.eq("10")
		);
	}

	@Test
	public void testFinishedExperimentWinnerBounceRate() throws Exception {
		faroInfoElasticsearchInvoker.update(
			"experiments",
			FaroInfoTestUtil.buildExperimentJSONObject(
				"1", "BOUNCE_RATE", "RUNNING"));

		_testFinishedExperiment(
			"FINISHED_WINNER",
			JSONUtil.put(
				"estimatedDaysLeft", 0
			).put(
				"processedDate", DateUtil.newUTCDateString()
			).put(
				"variantMetrics",
				JSONUtil.putAll(
					_createVariantMetricsJSONObject(
						new double[] {30, 45}, true, "10"),
					_createVariantMetricsJSONObject(
						new double[] {15, 28}, false, "20"))
			),
			"20");

		Mockito.verify(
			_dxpClient
		).updateDXPExperimentStatus(
			Mockito.eq("1"), Mockito.eq("1"),
			Mockito.eq(ExperimentStatus.FINISHED_WINNER), Mockito.eq("20")
		);
	}

	@Test
	public void testNotFinishedExperiment() throws Exception {
		Mockito.when(
			_experimentHttp.getExperimentMetricsJSONObject(Mockito.anyString())
		).thenReturn(
			JSONUtil.put("estimatedDaysLeft", 1)
		);

		_experimentNanite.run(null);

		JSONObject experimentJSONObject = faroInfoElasticsearchInvoker.get(
			"experiments", "1");

		Assert.assertEquals(
			"RUNNING", experimentJSONObject.getString("status"));
		Assert.assertNull(experimentJSONObject.optString("finishedDate", null));
	}

	private JSONObject _createVariantMetricsJSONObject(
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
			String expectedStatus, JSONObject responseBody,
			String winnerDXPVariantId)
		throws Exception {

		Mockito.when(
			_experimentHttp.getExperimentMetricsJSONObject(Mockito.anyString())
		).thenReturn(
			responseBody
		);

		_experimentNanite.run(null);

		JSONObject experimentJSONObject = faroInfoElasticsearchInvoker.get(
			"experiments", "1");

		Assert.assertEquals(
			expectedStatus, experimentJSONObject.getString("status"));
		Assert.assertEquals(
			winnerDXPVariantId,
			experimentJSONObject.optString("winnerDXPVariantId", null));
		Assert.assertNotNull(
			experimentJSONObject.optString("finishedDate", null));
	}

	@MockBean
	private DXPClient _dxpClient;

	@MockBean
	private ExperimentHttp _experimentHttp;

	@Autowired
	private ExperimentNanite _experimentNanite;

}