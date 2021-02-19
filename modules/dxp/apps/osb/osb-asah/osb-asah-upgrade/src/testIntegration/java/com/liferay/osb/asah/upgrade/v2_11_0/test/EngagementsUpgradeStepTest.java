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

package com.liferay.osb.asah.upgrade.v2_11_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_11_0.EngagementsUpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class EngagementsUpgradeStepTest {

	@ElasticsearchIndex(
		name = "engagements", resourcePath = "engagements.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteIndexAndAliasUpgrade() {
		Assert.assertTrue(
			_elasticsearchIndexManager.aliasExists(
				"test_osbasahfaroinfo_engagements_alias"));
		Assert.assertTrue(
			_elasticsearchIndexManager.exists(
				"test_osbasahfaroinfo_engagements"));

		_engagementsUpgradeStep.upgrade("");

		Assert.assertFalse(
			_elasticsearchIndexManager.aliasExists(
				"test_osbasahfaroinfo_engagements_alias"));
		Assert.assertFalse(
			_elasticsearchIndexManager.exists(
				"test_osbasahfaroinfo_engagements"));
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteMarkers() {
		_engagementsUpgradeStep.upgrade("");

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"OSBAsahMarkers", QueryBuilders.matchAllQuery()));
	}

	@ElasticsearchIndex(
		name = "run-logs", resourcePath = "run-logs.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteRunLogs() {
		_engagementsUpgradeStep.upgrade("");

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"run-logs", QueryBuilders.matchAllQuery()));
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private EngagementsUpgradeStep _engagementsUpgradeStep;

}