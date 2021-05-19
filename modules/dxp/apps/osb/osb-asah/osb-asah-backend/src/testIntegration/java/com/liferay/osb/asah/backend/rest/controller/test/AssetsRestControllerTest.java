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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.dto.AssetDTO;
import com.liferay.osb.asah.backend.rest.controller.AssetsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
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
public class AssetsRestControllerTest {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAssetDTO() {
		JSONAssert.assertEquals(
			_objectMapper.convertValue(
				new AssetDTO(_assetDog.getAsset(355525458918155906L)),
				JSONObject.class),
			_objectMapper.convertValue(
				_assetsRestController.getAssetDTO(355525458918155906L),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAssetDTOsPageDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_assets.json", this),
			_objectMapper.convertValue(
				_assetsRestController.getAssetDTOsPageDTO(null, 0, 20, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAssetTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_asset_transformations.json", this),
			_objectMapper.convertValue(
				_assetsRestController.getAssetTransformationDTOsPageDTO(
					"groupby((assetType))", null, 0, 20),
				JSONObject.class),
			false);
	}

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private AssetsRestController _assetsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}