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

package com.liferay.osb.asah.upgrade.v2_12_2.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_12_2.ActivityGroupsUpgradeStep;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class ActivityGroupsUpgradeStepTest {

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "activity-groups", resourcePath = "activity_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "preferences", resourcePath = "preferences.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void test() throws Exception {
		_activityGroupsUpgradeStep.upgrade("");

		_elasticsearchInvoker.refresh();

		_assertActivityGroup(
			"2021-06-27T10:44:22.375Z", "2021-06-27T03:44:22.375",
			"2021-06-27T10:44:22.375Z", "348853726639670813");
		_assertActivityGroup(
			"2021-06-28T23:14:00.003Z", "2021-06-28T16:14:00.003",
			"2021-06-28T10:44:22.375Z", "348853726639670821");
		_assertActivityGroup(
			"2021-07-11T17:00:03.843Z", "2021-07-11T10:00:03.843",
			"2021-07-11T12:15:10.123Z", "337459731793278417");
		_assertActivityGroup(
			"2021-07-14T22:20:10.843Z", "2021-07-14T15:20:10.843",
			"2021-07-14T01:22:10.000Z", "337459731793278429");
	}

	private void _assertActivityGroup(
		String expectedEndTime, String expectedEndTimeLocal,
		String expectedStartTime, String id) {

		JSONObject activityGroupJSONObject = _elasticsearchInvoker.fetch(
			"activity-groups", id);

		Assert.assertEquals(
			expectedEndTime, activityGroupJSONObject.getString("endTime"));
		Assert.assertEquals(
			expectedEndTimeLocal,
			activityGroupJSONObject.getString("endTimeLocal"));
		Assert.assertEquals(
			expectedStartTime, activityGroupJSONObject.getString("startTime"));
	}

	@Autowired
	private ActivityGroupsUpgradeStep _activityGroupsUpgradeStep;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}