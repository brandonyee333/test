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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dto.AssetDTO;
import com.liferay.osb.asah.backend.rest.controller.AssetsRestController;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class AssetsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
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

	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testGetAssetDTOPageDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_assets.json", this),
			_objectMapper.convertValue(
				_assetsRestController.getAssetDTOPageDTO(null, 0, 20, null),
				JSONObject.class),
			false);
	}

	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testGetAssetTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_asset_transformations.json", this),
			_objectMapper.convertValue(
				_assetsRestController.getAssetTransformationDTOPageDTO(
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