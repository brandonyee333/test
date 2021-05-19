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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.CustomAssetDashboardDog;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class CustomAssetDashboardDogTest {

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom_asset_dashboards_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testCustomAssetDashboardNotFound() {
		Assert.assertNull(
			_customAssetDashboardDog.fetchCustomAssetDashboard("0"));
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom_asset_dashboards_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetCustomAssetDashboard() {
		String customAssetDashboardId =
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9";

		CustomAssetDashboard customAssetDashboard =
			_customAssetDashboardDog.fetchCustomAssetDashboard(
				customAssetDashboardId);

		Assert.assertNotNull(customAssetDashboard);
		Assert.assertEquals(
			"Asset Title 1", customAssetDashboard.getAssetTitle());
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom_asset_dashboards_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetCustomAssetDashboardPageAll() {
		Page<CustomAssetDashboard> customAssetDashboardPage =
			_customAssetDashboardDog.getCustomAssetDashboardPage(
				1L, null, 0, 10, Sort.asc("assetTitle"));

		Assert.assertNotNull(customAssetDashboardPage);
		Assert.assertEquals(3, customAssetDashboardPage.getTotalElements());

		List<CustomAssetDashboard> customAssetDashboards =
			customAssetDashboardPage.getContent();

		Assert.assertEquals(
			customAssetDashboards.toString(), 3, customAssetDashboards.size());
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom_asset_dashboards_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetCustomAssetDashboardPagePaginated() {
		Page<CustomAssetDashboard> customAssetDashboardPage =
			_customAssetDashboardDog.getCustomAssetDashboardPage(
				1L, null, 1, 1, Sort.asc("assetTitle"));

		Assert.assertNotNull(customAssetDashboardPage);
		Assert.assertEquals(3, customAssetDashboardPage.getTotalElements());

		List<CustomAssetDashboard> customAssetDashboards =
			customAssetDashboardPage.getContent();

		Assert.assertEquals(
			customAssetDashboards.toString(), 1, customAssetDashboards.size());

		CustomAssetDashboard customAssetDashboard = customAssetDashboards.get(
			0);

		Assert.assertEquals(
			"Asset Title 2", customAssetDashboard.getAssetTitle());
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom_asset_dashboards_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUpdateCustomAssetDashboard() {
		String customAssetDashboardId =
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9";

		CustomAssetDashboard customAssetDashboard =
			_customAssetDashboardDog.updateCustomAssetDashboard(
				customAssetDashboardId,
				"{\"rows\": [{\"panels\":[{\"title\":\"MyPanel\"," +
					"\"width\":100,\"metric\":\"assetViewed\"," +
						"\"chartType\":\"line\"}]}]}",
				"123", "Pedro");

		Assert.assertNotNull(customAssetDashboard);
		Assert.assertEquals("1", customAssetDashboard.getAssetId());
		Assert.assertEquals(
			"Asset Title 1", customAssetDashboard.getAssetTitle());
		Assert.assertEquals("default", customAssetDashboard.getCategory());
		Assert.assertEquals(
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9",
			customAssetDashboard.getId());
		Assert.assertEquals("123", customAssetDashboard.getModifiedByUserId());
		Assert.assertEquals(
			"Pedro", customAssetDashboard.getModifiedByUserName());
	}

	@Autowired
	private CustomAssetDashboardDog _customAssetDashboardDog;

}