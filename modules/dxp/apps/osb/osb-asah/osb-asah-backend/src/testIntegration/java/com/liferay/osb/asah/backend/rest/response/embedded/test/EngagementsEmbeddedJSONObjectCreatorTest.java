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

package com.liferay.osb.asah.backend.rest.response.embedded.test;

import com.liferay.osb.asah.backend.rest.response.embedded.EngagementsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.apache.commons.lang3.RandomStringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EngagementsEmbeddedJSONObjectCreatorTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "engagements", resourcePath = "engagements.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void test() throws Exception {
		_testExpandEngagementsAggregation(30, "123", 0.5, 0.6);
		_testExpandEngagementsAggregation(60, "234", 0.5, 0.2);
		_testExpandEngagementsAggregation(90, "234", 0.3, 0.4);
	}

	@Test
	public void testExpandNullReturnsNull() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new EngagementsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, null);

		Assert.assertNull(
			embeddedJSONObjectCreator.create(
				RandomStringUtils.randomNumeric(5, 7)));
	}

	private void _testExpandEngagementsAggregation(
			int days, String id, double average1, double average2)
		throws Exception {

		String expand = "engagement-aggregation-last-" + days + "-days";

		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new EngagementsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, expand);

		JSONObject responseJSONObject = embeddedJSONObjectCreator.create(id);

		JSONArray engagementAggregationJSONArray =
			responseJSONObject.getJSONArray(expand);

		Assert.assertEquals(days, engagementAggregationJSONArray.length());

		JSONObject engagementJSONObject1 =
			engagementAggregationJSONArray.getJSONObject(0);

		Assert.assertEquals(
			average1, engagementJSONObject1.getDouble("scoreAvg"), 0);
		Assert.assertEquals(1, engagementJSONObject1.getInt("totalElements"));

		JSONObject engagementJSONObject2 =
			engagementAggregationJSONArray.getJSONObject(8);

		Assert.assertEquals(
			average2, engagementJSONObject2.getDouble("scoreAvg"), 0);
		Assert.assertEquals(1, engagementJSONObject2.getInt("totalElements"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}