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

package com.liferay.osb.asah.upgrade.v2_12_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_12_0.RunLogUpgradeStep;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class RunLogUpgradeStepTest {

	@ElasticsearchIndex(
		name = "run-logs", resourcePath = "run-logs.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpgrade() throws Exception {
		_runLogUpgradeStep.upgrade("");

		JSONAssert.assertEquals(
			JSONUtil.put(
				"context",
				JSONUtil.put(
					"extraPropertyA", 42
				).put(
					"extraPropertyB", false
				).put(
					"extraPropertyC", "true"
				)
			).put(
				"dateLogged", "2020-12-04T00:06:27.003Z"
			).put(
				"id", "458153372317996126"
			).put(
				"naniteClassName", "IndividualSegmentEngagementScoresNanite"
			).put(
				"status", "COMPLETED"
			),
			_elasticsearchInvoker.get("run-logs", "458153372317996126"), true);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"context",
				JSONUtil.put(
					"extraPropertyA", 27
				).put(
					"extraPropertyB", true
				)
			).put(
				"dateLogged", "2020-11-30T03:36:48.077Z"
			).put(
				"id", "456886320875798205"
			).put(
				"naniteClassName", "ExperimentNanite"
			).put(
				"status", "INTERRUPTED"
			),
			_elasticsearchInvoker.get("run-logs", "456886320875798205"), true);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private RunLogUpgradeStep _runLogUpgradeStep;

}