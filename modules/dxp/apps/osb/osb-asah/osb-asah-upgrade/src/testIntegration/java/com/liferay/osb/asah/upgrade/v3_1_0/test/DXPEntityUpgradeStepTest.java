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

package com.liferay.osb.asah.upgrade.v3_1_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v3_1_0.DXPEntityUpgradeStep;

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
public class DXPEntityUpgradeStepTest {

	@ElasticsearchIndex(
		name = "groups", resourcePath = "groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@ElasticsearchIndex(
		name = "organizations", resourcePath = "organizations.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@ElasticsearchIndex(
		name = "roles", resourcePath = "roles.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@ElasticsearchIndex(
		name = "teams", resourcePath = "teams.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@ElasticsearchIndex(
		name = "user-groups", resourcePath = "user-groups.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void testUpgrade() throws Exception {
		String[] collectionNames = {
			"groups", "organizations", "roles", "teams", "user-groups", "users"
		};

		for (String collectionName : collectionNames) {
			Assert.assertTrue(
				collectionName,
				_elasticsearchIndexManager.exists(
					String.format("test_osbasahdxpraw_%s", collectionName)));
		}

		_dxpEntityUpgradeStep.upgrade("");

		for (String collectionName : collectionNames) {
			JSONAssert.assertEquals(
				collectionName,
				ResourceUtil.readResourceToJSONArray(
					String.format(
						"dependencies/expected_%s.json", collectionName),
					this),
				_elasticsearchInvoker.get(collectionName), false);
		}
	}

	@Autowired
	private DXPEntityUpgradeStep _dxpEntityUpgradeStep;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _elasticsearchInvoker;

}