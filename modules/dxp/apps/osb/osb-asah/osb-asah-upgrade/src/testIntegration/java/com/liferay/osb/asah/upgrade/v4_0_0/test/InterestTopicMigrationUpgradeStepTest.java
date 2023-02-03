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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.InterestTopic;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.InterestTopicRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.InterestTopicMigrationUpgradeStep;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leilany Ulisses
 */
public class InterestTopicMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_interestTopicRepository.deleteAll();

		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_interest-topics");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/interest_topics_index_configuration.json", this),
			"test_osbasahfaroinfo_interest-topics");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_interest-topics_alias",
			"test_osbasahfaroinfo_interest-topics");
	}

	@AfterEach
	public void tearDown() {
		_elasticsearchIndexManager.delete("interest-topics");

		_interestTopicRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/interest_topics.json", this));

		_elasticsearchInvoker.add("interest-topics", jsonArray);

		_interestTopicMigrationUpgradeStep.upgrade("");

		Assertions.assertEquals(
			JSONUtil.toList(
				jsonArray,
				jsonObject -> _objectMapper.convertValue(
					jsonObject, InterestTopic.class)),
			_interestTopicRepository.findAll());

		Assertions.assertTrue(
			_interestTopicMigrationUpgradeStep.isSequenceSync());
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private InterestTopicMigrationUpgradeStep
		_interestTopicMigrationUpgradeStep;

	@Autowired
	private InterestTopicRepository _interestTopicRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}