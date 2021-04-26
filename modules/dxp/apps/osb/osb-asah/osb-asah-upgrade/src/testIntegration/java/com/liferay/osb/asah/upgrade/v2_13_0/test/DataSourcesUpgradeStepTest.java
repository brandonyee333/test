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

package com.liferay.osb.asah.upgrade.v2_13_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_13_0.DataSourcesUpgradeStep;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcos Martins
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class DataSourcesUpgradeStepTest {

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteIndexAndAliasUpgrade() throws Exception {
		Assert.assertTrue(
			_elasticsearchIndexManager.exists("test_osbasahfaroinfo_channels"));

		Assert.assertTrue(
			_elasticsearchIndexManager.exists(
				"test_osbasahfaroinfo_data-sources"));

		_dataSourcesUpgradeStep.upgrade("");

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_channels.json", this),
			_elasticsearchInvoker.get("channels"), false);

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_data-sources.json", this),
			_elasticsearchInvoker.get("data-sources"), false);
	}

	@Autowired
	private DataSourcesUpgradeStep _dataSourcesUpgradeStep;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}