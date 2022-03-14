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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.BlockedKeywordMigrationUpgradeStep;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robson Pastor
 */
public class BlockedKeywordMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_blockedKeywordRepository.deleteAll();

		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_blocked-keywords");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/blocked_keywords_index_configuration.json", this),
			"test_osbasahfaroinfo_blocked-keywords");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_blocked-keywords_alias",
			"test_osbasahfaroinfo_blocked-keywords");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_blocked-keywords");
		_blockedKeywordRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/blocked_keywords.json", this)));

		Assertions.assertFalse(jsonArray.isEmpty());

		_elasticsearchInvoker.add("blocked-keywords", jsonArray);

		_blockedKeywordMigrationUpgradeStep.upgrade("");

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(
					object, BlockedKeyword.class)
			).collect(
				Collectors.toList()
			),
			_blockedKeywordRepository.findAll());

		Assertions.assertTrue(
			_blockedKeywordMigrationUpgradeStep.isSequenceSync());
	}

	@Autowired
	private BlockedKeywordMigrationUpgradeStep
		_blockedKeywordMigrationUpgradeStep;

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}