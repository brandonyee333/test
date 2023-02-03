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

import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.CustomAssetDashboardMigrationUpgradeStep;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public class CustomAssetDashboardMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_customAssetDashboardRepository.deleteAll();

		_elasticsearchIndexManager.delete(
			"test_osbasahcerebroinfo_custom-asset-dashboards");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies" +
					"/custom_asset_dashboards_items_index_configuration.json",
				this),
			"test_osbasahcerebroinfo_custom-asset-dashboards");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahcerebroinfo_custom-asset-dashboards_alias",
			"test_osbasahcerebroinfo_custom-asset-dashboards");
	}

	@AfterEach
	public void tearDown() {
		_customAssetDashboardRepository.deleteAll();
		_elasticsearchIndexManager.delete(
			"test_osbasahcerebroinfo_custom-asset-dashboards");
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/custom_asset_dashboards.json", this));

		Assertions.assertFalse(jsonArray.isEmpty());

		_cerebroInfoElasticsearchInvoker.add(
			"custom-asset-dashboards", jsonArray);

		_customAssetDashboardMigrationUpgradeStep.upgrade("");

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(
					object, CustomAssetDashboard.class)
			).collect(
				Collectors.toSet()
			),
			new HashSet<>(
				IterableUtils.toList(
					_customAssetDashboardRepository.findAll())));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private CustomAssetDashboardMigrationUpgradeStep
		_customAssetDashboardMigrationUpgradeStep;

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ObjectMapper _objectMapper;

}