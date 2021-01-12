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

import com.liferay.osb.asah.backend.rest.response.embedded.InterestsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.apache.commons.lang3.RandomStringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class InterestsEmbeddedJSONObjectCreatorTest {

	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandInterestsAggregation() throws Exception {
		_testExpandInterestsAggregation(30, "123", 0.5, 0.6);
		_testExpandInterestsAggregation(60, "234", 0.5, 0.2);
		_testExpandInterestsAggregation(90, "345", 0.3, 0.4);
	}

	@Test
	public void testExpandInterestsAggregationWithInvalidExpand()
		throws Exception {

		InterestsEmbeddedJSONObjectCreator interestsEmbeddedJSONObjectCreator =
			new InterestsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "pages");

		Assert.assertNull(
			interestsEmbeddedJSONObjectCreator.create(
				RandomStringUtils.randomNumeric(5, 7)));
	}

	@Test
	public void testExpandInterestsAggregationWithNullExpand()
		throws Exception {

		InterestsEmbeddedJSONObjectCreator interestsEmbeddedJSONObjectCreator =
			new InterestsEmbeddedJSONObjectCreator(_elasticsearchInvoker, null);

		Assert.assertNull(
			interestsEmbeddedJSONObjectCreator.create(
				RandomStringUtils.randomNumeric(5, 7)));
	}

	private void _testExpandInterestsAggregation(
			int days, String id, double average1, double average2)
		throws Exception {

		String expand = "interest-aggregation-last-" + days + "-days";

		InterestsEmbeddedJSONObjectCreator interestsEmbeddedJSONObjectCreator =
			new InterestsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, expand);

		JSONObject responseJSONObject =
			interestsEmbeddedJSONObjectCreator.create(id);

		String[] expandParts = expand.split(",");

		JSONArray interestsAggregationJSONArray =
			responseJSONObject.getJSONArray(expandParts[0]);

		Assert.assertEquals(days, interestsAggregationJSONArray.length());

		JSONObject interestsAggregationJSONObject1 =
			interestsAggregationJSONArray.getJSONObject(0);

		Assert.assertEquals(
			average1, interestsAggregationJSONObject1.getDouble("scoreAvg"), 0);
		Assert.assertEquals(
			1, interestsAggregationJSONObject1.getInt("totalElements"));
		Assert.assertEquals(
			1, interestsAggregationJSONObject1.getInt("viewsSum"));

		JSONObject interestsAggregationJSONObject2 =
			interestsAggregationJSONArray.getJSONObject(8);

		Assert.assertEquals(
			average2, interestsAggregationJSONObject2.getDouble("scoreAvg"), 0);
		Assert.assertEquals(
			1, interestsAggregationJSONObject2.getInt("totalElements"));
		Assert.assertEquals(
			1, interestsAggregationJSONObject2.getInt("viewsSum"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}