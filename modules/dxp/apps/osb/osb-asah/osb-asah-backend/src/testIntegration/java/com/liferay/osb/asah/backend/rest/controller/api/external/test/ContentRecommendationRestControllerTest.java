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

package com.liferay.osb.asah.backend.rest.controller.api.external.test;

import com.liferay.osb.asah.backend.rest.controller.api.external.ContentRecommendationRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ContentRecommendationRestControllerTest {

	@ElasticsearchIndex(
		name = "recommended-items",
		resourcePath = "recommended-items-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetRecommendedItems() throws Exception {
		String json = _contentRecommendationRestController.getRecommendedItems(
			JSONUtil.put(
				"item", "https://www-prd.liferay.com/services/training/online"
			).toString(),
			"1");

		JSONObject jsonObject = new JSONObject(json);

		List<String> recommendedItems = JSONUtil.toStringList(
			jsonObject.getJSONArray("recommendedItems"));

		Assert.assertEquals(
			Arrays.asList(
				"https://www-prd.liferay.com/blog",
				"https://www-prd.liferay.com/resource"),
			recommendedItems);
	}

	@Autowired
	private ContentRecommendationRestController
		_contentRecommendationRestController;

}