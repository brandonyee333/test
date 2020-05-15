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

import com.liferay.osb.asah.backend.rest.response.embedded.IndividualsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
public class IndividualsEmbeddedJSONObjectCreatorTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandAccountNames() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "account-names");

		JSONObject individualJSONObject = embeddedJSONObjectCreator.create(
			"346468649722790279");

		JSONArray accountNamesJSONArray = individualJSONObject.getJSONArray(
			"account-names");

		Assert.assertEquals(4, accountNamesJSONArray.length());
		Assert.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "Liferay, Inc."));
		Assert.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "Nozomi Project"));
		Assert.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "The Space Program"));
		Assert.assertTrue(
			JSONUtil.hasValue(
				accountNamesJSONArray, "The World's Foremost Chess Club"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandAccounts() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "accounts");

		JSONObject individualJSONObject = embeddedJSONObjectCreator.create(
			"346468649722790279");

		JSONArray accountsJSONArray = individualJSONObject.getJSONArray(
			"accounts");

		Assert.assertEquals(4, accountsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandDataSources() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "data-sources");

		JSONObject individualJSONObject = embeddedJSONObjectCreator.create(
			"123");

		JSONArray dataSourcesJSONArray = individualJSONObject.getJSONArray(
			"data-sources");

		Assert.assertEquals(2, dataSourcesJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual-segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandIndividualSegments() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "individual-segments");

		JSONObject individualJSONObject = embeddedJSONObjectCreator.create(
			"123");

		JSONArray individualSegmentsJSONArray =
			individualJSONObject.getJSONArray("individual-segments");

		Assert.assertEquals(2, individualSegmentsJSONArray.length());
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}