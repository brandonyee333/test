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

package com.liferay.osb.asah.upgrade.v4_0_0.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.IdentityMigrationUpgradeStep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.jooq.tools.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Robson Pastor
 */
public class IdentityMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_setupIndex("individuals", "osbasahfaroinfo");
		_setupIndex("user-sessions", "osbasahcerebroinfo");

		tearDown();
	}

	@AfterEach
	public void tearDown() {
		_faroInfoElasticsearchInvoker.delete(
			"individuals", QueryBuilders.matchAllQuery());
		_cerebroInfoElasticsearchInvoker.delete(
			"user-sessions", QueryBuilders.matchAllQuery());
	}

	@Test
	public void testGetNextBatch() throws Exception {
		_faroInfoElasticsearchInvoker.add(
			"individuals",
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/individuals.json", this))));
		_cerebroInfoElasticsearchInvoker.add(
			"user-sessions",
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/user_sessions.json", this))));

		Collection<JSONObject> identityCollection =
			ReflectionTestUtils.invokeMethod(
				_identityMigrationUpgradeStep, "_getNextBatch", "test");

		Assertions.assertNotNull(identityCollection);
		Assertions.assertEquals(500, identityCollection.size());

		List<JSONObject> identityList = new ArrayList(identityCollection);

		JSONObject identityJSONObject = identityList.get(0);

		Assertions.assertEquals(5, identityJSONObject.length());

		JSONArray identityActivityJSONArray = ReflectionTestUtils.invokeMethod(
			_identityMigrationUpgradeStep, "_getIdentityActivities",
			identityCollection);

		Assertions.assertEquals(4, identityJSONObject.length());

		Assertions.assertEquals(
			JSONUtil.put(
				"createDate", DateUtil.toUTCDate("2019-02-11T17:05:20.000Z")
			).put(
				"individualId", "7e66ee66-c09b-4a19-b9a9-c0483e34803d"
			).put(
				"projectId", "test"
			).put(
				"userId", "004e0bb7-555b-48f3-8106-89ee13399afa"
			).toString(),
			identityJSONObject.toString());

		JSONObject identityActivityJSONObject =
			identityActivityJSONArray.getJSONObject(0);

		Assertions.assertEquals(
			JSONUtil.put(
				"channelId", 1L
			).put(
				"createDate", DateUtil.toUTCDate("2022-04-04T20:12:12.000Z")
			).put(
				"dataSourceId", 355524992631037473L
			).put(
				"id",
				"test#004e0bb7-555b-48f3-8106-89ee13399afa#355524992631037473#1"
			).put(
				"identityId", "004e0bb7-555b-48f3-8106-89ee13399afa"
			).put(
				"individualId", "7e66ee66-c09b-4a19-b9a9-c0483e34803d"
			).toString(),
			identityActivityJSONObject.toString());

		identityCollection = ReflectionTestUtils.invokeMethod(
			_identityMigrationUpgradeStep, "_getNextBatch", "test");

		Assertions.assertNotNull(identityCollection);
		Assertions.assertEquals(4, identityCollection.size());
		identityCollection = ReflectionTestUtils.invokeMethod(
			_identityMigrationUpgradeStep, "_getNextBatch", "test");

		Assertions.assertNotNull(identityCollection);
		Assertions.assertTrue(identityCollection.isEmpty());
	}

	private void _setupIndex(String collectionName, String namespace)
		throws Exception {

		String indexName = String.format(
			"test_%s_%s", namespace, collectionName);

		_elasticsearchIndexManager.delete(indexName);

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				String.format(
					"dependencies/%s_index_configuration.json",
					StringUtils.replace(collectionName, "-", "_")),
				this),
			indexName);

		_elasticsearchIndexManager.addAlias(
			String.format("%s_alias", indexName), indexName);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private IdentityMigrationUpgradeStep _identityMigrationUpgradeStep;

}