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

package com.liferay.osb.asah.common.cerebro.info.dog.test;

import com.liferay.osb.asah.common.cerebro.info.dog.CerebroInfoDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class CerebroInfoDogTest {

	@Before
	public void setUp() throws Exception {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forCerebroInfo();

		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSegmentNamesUpdated() throws Exception {
		JSONObject individualSegmentJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"individual-segments",
				JSONUtil.put(
					"channelId", "1"
				).put(
					"id", "327968823603500656"
				).put(
					"name", "Test Segment 1"
				).put(
					"referencedAssetIds", JSONUtil.put("1234")
				));

		_cerebroInfoDog.updateSegmentNames(individualSegmentJSONObject);

		JSONArray pagesJSONArray = _elasticsearchInvoker.get(
			"pages", QueryBuilders.termQuery("individualId", "1"));

		for (int i = 0; i < pagesJSONArray.length(); i++) {
			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(i);

			JSONAssert.assertEquals(
				JSONUtil.put("Test Segment 1"),
				pageJSONObject.getJSONArray("segmentNames"), true);
		}
	}

	@Autowired
	private CerebroInfoDog _cerebroInfoDog;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}