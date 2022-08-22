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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.ActivitiesRestController;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shinn Lok
 */
public class ActivitiesRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Disabled
	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_3.json"
	)
	@Test
	public void testGetAssetTransformations1() throws Exception {
		JSONObject jsonObject = new JSONObject(
			_activitiesRestController.getAssetTransformations(
				"contains(object.name, 'clicks')", 0, 10,
				new String[] {"count", "desc"}));

		Assertions.assertEquals(
			4,
			JSONUtil.getValue(
				jsonObject, "JSONObject/_embedded",
				"JSONArray/asset-transformations", "Object/0", "Object/count"));

		jsonObject = new JSONObject(
			_activitiesRestController.getAssetTransformations(
				"(channelId eq '1') and contains(object.name, 'clicks')", 0, 10,
				new String[] {"count", "desc"}));

		Assertions.assertEquals(
			2,
			JSONUtil.getValue(
				jsonObject, "JSONObject/_embedded",
				"JSONArray/asset-transformations", "Object/0", "Object/count"));
	}

	@Disabled
	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_3.json"
	)
	@Test
	public void testGetAssetTransformations2() throws Exception {
		JSONObject jsonObject = new JSONObject(
			_activitiesRestController.getAssetTransformations(
				"contains(object.name, 'random')", 0, 10,
				new String[] {"count", "desc"}));

		Assertions.assertEquals(
			0,
			JSONUtil.getValue(
				jsonObject, "JSONObject/page", "Object/totalElements"));
	}

	@Autowired
	private ActivitiesRestController _activitiesRestController;

}