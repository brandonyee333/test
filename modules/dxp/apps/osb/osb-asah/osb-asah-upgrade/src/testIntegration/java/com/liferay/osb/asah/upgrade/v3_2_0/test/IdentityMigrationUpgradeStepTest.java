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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.IdentityMigrationUpgradeStep;

import java.util.Objects;

import org.elasticsearch.index.query.QueryBuilders;

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
	public void setUp() {
		ProjectIdThreadLocal.setProjectId("test");

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

		JSONArray identityJSONArray = ReflectionTestUtils.invokeMethod(
			_identityMigrationUpgradeStep, "_getNextBatch", "test");

		Assertions.assertNotNull(identityJSONArray);
		Assertions.assertEquals(6, identityJSONArray.length());

		JSONObject expectedJSONObject = JSONUtil.put(
			"channelId", 1L
		).put(
			"createDate", "Mon Feb 11 17:05:20 UTC 2019"
		).put(
			"dataSourceId", 355524992631037473L
		).put(
			"emailAddressHashed", "797c78f4-d30b-420f-9058-13ce5a8ac532"
		).put(
			"id",
			"8b02dfcfc8504dea9553760638c1f756c0feb35aab575befba5e7476b9e2ee23"
		).put(
			"projectId", "test"
		).put(
			"userId", "0cbc8e60-99cd-11e9-9129-a75b6df1b957"
		);

		JSONObject actualIdentityJSONObject = identityJSONArray.getJSONObject(
			0);

		Assertions.assertTrue(
			Objects.equals(
				expectedJSONObject.toMap(), actualIdentityJSONObject.toMap()));

		identityJSONArray = ReflectionTestUtils.invokeMethod(
			_identityMigrationUpgradeStep, "_getNextBatch", "test");

		Assertions.assertNotNull(identityJSONArray);
		Assertions.assertTrue(identityJSONArray.isEmpty());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private IdentityMigrationUpgradeStep _identityMigrationUpgradeStep;

}