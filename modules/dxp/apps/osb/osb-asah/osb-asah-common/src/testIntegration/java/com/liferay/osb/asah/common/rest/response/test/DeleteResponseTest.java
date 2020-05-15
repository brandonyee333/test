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
import com.liferay.osb.asah.common.rest.response.DeleteResponse;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

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
public class DeleteResponseTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "delete-response-individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRespondWithDefaultProperties() {
		DeleteResponse deleteResponse = new DeleteResponse();

		deleteResponse.setCollectionName("individuals");
		deleteResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		deleteResponse.setId("123");

		JSONObject responseJSONObject = new JSONObject(
			deleteResponse.respond());

		Assert.assertTrue(responseJSONObject.getBoolean("deleted"));

		Assert.assertFalse(
			_elasticsearchInvoker.exists(
				"individuals", QueryBuilders.matchAllQuery()));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}