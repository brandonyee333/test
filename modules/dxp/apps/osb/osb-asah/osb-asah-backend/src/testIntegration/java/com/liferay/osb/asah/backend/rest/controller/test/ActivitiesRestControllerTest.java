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

import com.liferay.osb.asah.backend.rest.controller.ActivitiesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Shinn Lok
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ActivitiesRestControllerTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetActivities() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_activities.json", this),
			new JSONObject(
				_activitiesRestController.getActivities(null, 0, 20, null)),
			false);
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetActivity() throws Exception {
		JSONAssert.assertEquals(
			_elasticsearchInvoker.fetch("activities", "348853926240043267"),
			new JSONObject(
				_activitiesRestController.getActivity("348853926240043267")),
			false);
	}

	@Test
	public void testGetActivityTransformations1() throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"day", DateUtil.newDateString()
		).put(
			"userId", "311355742999294554"
		);

		_elasticsearchInvoker.add("activities", jsonObject);

		JSONObject activityTransformationsJSONObject = new JSONObject(
			_activitiesRestController.getActivityTransformations(
				"compute(day(day) as temp)/groupby((temp))",
				"userId eq '311355742999294554'", true, 0, 1));

		Assert.assertEquals(
			1,
			JSONUtil.getValue(
				activityTransformationsJSONObject, "JSONObject/_embedded",
				"JSONArray/activity-transformations", "Object/0",
				"Object/totalElements"));
	}

	@Test
	public void testGetActivityTransformations2() throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"day", DateUtil.newDateString()
		).put(
			"userId", "311355742999294554"
		);

		_elasticsearchInvoker.add("activities", jsonObject);

		JSONObject activityTransformationsJSONObject = new JSONObject(
			_activitiesRestController.getActivityTransformations(
				"compute(day(day) as temp)/groupby((temp))", "userId eq 'abc'",
				true, 0, 1));

		Assert.assertEquals(
			0,
			JSONUtil.getValue(
				activityTransformationsJSONObject, "JSONObject/_embedded",
				"JSONArray/activity-transformations", "Object/0",
				"Object/totalElements"));
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAssetTransformations1() throws Exception {
		JSONObject jsonObject = new JSONObject(
			_activitiesRestController.getAssetTransformations(
				"contains(object.name, 'clicks')", 0, 10,
				new String[] {"count", "desc"}));

		Assert.assertEquals(
			4,
			JSONUtil.getValue(
				jsonObject, "JSONObject/_embedded",
				"JSONArray/asset-transformations", "Object/0", "Object/count"));

		jsonObject = new JSONObject(
			_activitiesRestController.getAssetTransformations(
				"(channelId eq '1') and contains(object.name, 'clicks')", 0, 10,
				new String[] {"count", "desc"}));

		Assert.assertEquals(
			2,
			JSONUtil.getValue(
				jsonObject, "JSONObject/_embedded",
				"JSONArray/asset-transformations", "Object/0", "Object/count"));
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAssetTransformations2() throws Exception {
		JSONObject jsonObject = new JSONObject(
			_activitiesRestController.getAssetTransformations(
				"contains(object.name, 'random')", 0, 10,
				new String[] {"count", "desc"}));

		Assert.assertEquals(
			0,
			JSONUtil.getValue(
				jsonObject, "JSONObject/page", "Object/totalElements"));
	}

	@Autowired
	private ActivitiesRestController _activitiesRestController;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}