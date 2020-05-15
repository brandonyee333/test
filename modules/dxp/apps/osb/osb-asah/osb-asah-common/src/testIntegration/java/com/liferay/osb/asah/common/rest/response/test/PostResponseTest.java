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
import com.liferay.osb.asah.common.rest.response.PostResponse;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
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
public class PostResponseTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testRespondWithDefaultProperties() throws Exception {
		PostResponse postResponse = new PostResponse();

		postResponse.setCollectionName("individuals");
		postResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		postResponse.setJSON(String.valueOf(JSONUtil.put("foo", "bar")));

		JSONObject responseJSONObject = new JSONObject(postResponse.respond());

		Assert.assertEquals("bar", responseJSONObject.getString("foo"));
		Assert.assertNotNull(responseJSONObject.optString("id"));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			"individuals", responseJSONObject.getString("id"));

		Assert.assertEquals("bar", actualJSONObject.getString("foo"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}