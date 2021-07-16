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
import com.liferay.osb.asah.upgrade.v2_12_2.IndividualActivitiesUpgradeStep;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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
public class IndividualActivitiesUpgradeStepTest {

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void test() throws Exception {
		_individualActivitiesUpgradeStep.upgrade("");

		_elasticsearchInvoker.refresh();

		_assertActivitiesCounts(
			new HashMap<String, Long>() {
				{
					put("461522890926789186", 2L);
					put("461522890926789187", 1L);
				}
			},
			"461522894540428770");
		_assertLastActivityDates(
			new HashMap<String, String>() {
				{
					put("461522890926789186", "2021-06-28T23:14:00.003Z");
					put("461522890926789187", "2021-06-27T15:55:13.241Z");
				}
			},
			"461522894540428770");

		_assertActivitiesCounts(
			new HashMap<String, Long>() {
				{
					put("461522890926789186", 2L);
				}
			},
			"461522893963850897");
		_assertLastActivityDates(
			new HashMap<String, String>() {
				{
					put("461522890926789186", "2021-07-11T17:00:03.843Z");
				}
			},
			"461522893963850897");

		_assertActivitiesCounts(
			new HashMap<String, Long>() {
				{
					put("461522890926789186", 3L);
				}
			},
			"461522898827194579");
		_assertLastActivityDates(
			new HashMap<String, String>() {
				{
					put("461522890926789186", "2021-07-14T22:20:10.843Z");
				}
			},
			"461522898827194579");
	}

	private void _assertActivitiesCounts(
		Map<String, Long> expectedActivitiesCounts, String individualId) {

		JSONObject individualJSONObject = _elasticsearchInvoker.fetch(
			"individuals", individualId);

		JSONArray activitiesCountsJSONArray = individualJSONObject.optJSONArray(
			"activitiesCounts");

		for (int i = 0; i < activitiesCountsJSONArray.length(); i++) {
			JSONObject activitiesCountJSONObject =
				activitiesCountsJSONArray.getJSONObject(i);

			String channelId = activitiesCountJSONObject.getString("channelId");

			Assert.assertEquals(
				expectedActivitiesCounts.get(channelId),
				(Long)activitiesCountJSONObject.getLong("activitiesCount"));

			expectedActivitiesCounts.remove(channelId);
		}

		Assert.assertTrue(
			"Missing activity counts for individual " +
				individualJSONObject.getString("id"),
			expectedActivitiesCounts.isEmpty());
	}

	private void _assertLastActivityDates(
		Map<String, String> expectedLastActivityDates, String individualId) {

		JSONObject individualJSONObject = _elasticsearchInvoker.fetch(
			"individuals", individualId);

		JSONArray lastActivityDatesJSONArray =
			individualJSONObject.optJSONArray("lastActivityDates");

		for (int i = 0; i < lastActivityDatesJSONArray.length(); i++) {
			JSONObject lastActivityDateJSONObject =
				lastActivityDatesJSONArray.getJSONObject(i);

			String channelId = lastActivityDateJSONObject.getString(
				"channelId");

			Assert.assertEquals(
				expectedLastActivityDates.get(channelId),
				lastActivityDateJSONObject.getString("lastActivityDate"));

			expectedLastActivityDates.remove(channelId);
		}

		Assert.assertTrue(
			"Missing last activity dates for individual " +
				individualJSONObject.getString("id"),
			expectedLastActivityDates.isEmpty());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private IndividualActivitiesUpgradeStep _individualActivitiesUpgradeStep;

}