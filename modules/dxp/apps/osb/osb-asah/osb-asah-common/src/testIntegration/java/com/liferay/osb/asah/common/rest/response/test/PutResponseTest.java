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

package com.liferay.osb.asah.common.rest.response.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.PutResponse;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
public class PutResponseTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "put-response-individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRespondWithDefaultProperties() throws Exception {
		PutResponse putResponse = new PutResponse();

		putResponse.setCollectionName("individuals");
		putResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		putResponse.setId("123");
		putResponse.setJSON(String.valueOf(JSONUtil.put("floo", "blar")));

		// TODO Our PUT responses actually behaves like a PATCH. We should fix
		// this one day.

		JSONObject responseJSONObject = new JSONObject(putResponse.respond());

		Assert.assertEquals("blar", responseJSONObject.getString("floo"));
		Assert.assertEquals("bar", responseJSONObject.getString("foo"));
		Assert.assertNotNull(responseJSONObject.optString("id"));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			"individuals", "123");

		Assert.assertEquals("blar", actualJSONObject.getString("floo"));
		Assert.assertEquals("bar", actualJSONObject.getString("foo"));
		Assert.assertNotNull(actualJSONObject.optString("id"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}