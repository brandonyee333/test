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

package com.liferay.osb.asah.upgrade.v2_5_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_5_0.IndividualActivityFieldsUpgradeStep;

import org.json.JSONArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class IndividualActivityFieldsUpgradeStepTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpgrade() throws Exception {
		_generateActivities(
			"1", "1", "1", "2020-04-05T13:00:00Z", "2020-04-06T12:30:00Z",
			"2020-04-06T13:00:00Z");
		_generateActivities(
			"2", "2", "1", "2020-04-06T12:00:00Z", "2020-04-06T14:00:00Z",
			"2020-04-06T13:00:00Z");
		_generateActivities(
			"1", "1", "2", "2020-04-06T15:00:00Z", "2020-04-07T15:00:00Z");
		_generateActivities(
			"2", "2", "2", "2020-04-07T15:59:59Z", "2020-04-07T16:00:00Z");

		_individualActivityFieldsUpgradeStep.upgrade("");

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_individuals.json", this),
			_elasticsearchInvoker.get("individuals"), true);
	}

	private void _generateActivities(
		String channelId, String dataSourceId, String ownerId,
		String... endTimes) {

		JSONArray jsonArray = new JSONArray();

		for (String endTime : endTimes) {
			jsonArray.put(
				JSONUtil.put(
					"channelId", channelId
				).put(
					"dataSourceId", dataSourceId
				).put(
					"endTime", endTime
				).put(
					"ownerId", ownerId
				));
		}

		_elasticsearchInvoker.add("activities", jsonArray);
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private IndividualActivityFieldsUpgradeStep
		_individualActivityFieldsUpgradeStep;

}