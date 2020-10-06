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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.rest.controller.ActivityGroupsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ActivityGroupsRestControllerTest {

	@ElasticsearchIndex(
		name = "activity-groups", resourcePath = "activity_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetActivityGroup() throws Exception {
		JSONAssert.assertEquals(
			_elasticsearchInvoker.fetch(
				"activity-groups", "348853726639670821"),
			new JSONObject(
				_activityGroupsRestController.getActivityGroup(
					"348853726639670821")),
			false);
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "activity-groups", resourcePath = "activity_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetActivityGroups() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_activity_groups.json", this),
			new JSONObject(
				_activityGroupsRestController.getActivityGroups(
					null, null, null, 0, 20, null)),
			true);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_activity_groups_expand_filter.json",
				this),
			new JSONObject(
				_activityGroupsRestController.getActivityGroups(
					"activities,activities-count",
					"((userId eq 'db1ed215-9ed2-46a4-90de-535604c02c65'))",
					null, 0, 20, null)),
			true);
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "activity-groups", resourcePath = "activity_groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetActivityGroupTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_activity_group_transformations.json",
				this),
			new JSONObject(
				_activityGroupsRestController.getActivityGroupTransformations(
					"groupby((activityType))", "activityType eq 'BROWSE'", 0,
					20)),
			true);
	}

	@Autowired
	private ActivityGroupsRestController _activityGroupsRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}