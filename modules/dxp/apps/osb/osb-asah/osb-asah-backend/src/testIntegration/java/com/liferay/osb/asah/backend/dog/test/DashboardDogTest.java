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

import com.liferay.osb.asah.backend.dog.DashboardDog;
import com.liferay.osb.asah.backend.model.Dashboard;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.elasticsearch.search.sort.SortBuilders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class DashboardDogTest {

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom-asset-dashboards-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testDashboardNotFound() {
		Dashboard dashboard = _dashboardDog.getDashboard("0");

		Assert.assertNull(dashboard);
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom-asset-dashboards-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDashboard() {
		Dashboard dashboard = _dashboardDog.getDashboard(
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9");

		Assert.assertNotNull(dashboard);
		Assert.assertEquals("Asset Title 1", dashboard.getAssetTitle());
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom-asset-dashboards-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDashboardBagAll() {
		ResultBag<Dashboard> resultBag = _dashboardDog.getDashboardResultBag(
			"1", SortBuilders.fieldSort("assetTitle"), null, 10, 0);

		Assert.assertNotNull(resultBag);
		Assert.assertEquals(3, resultBag.getTotal());

		List<Dashboard> dashboards = resultBag.getResults();

		Assert.assertEquals(dashboards.toString(), 3, dashboards.size());
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom-asset-dashboards-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDashboardBagPaginated() {
		ResultBag<Dashboard> resultBag = _dashboardDog.getDashboardResultBag(
			"1", SortBuilders.fieldSort("assetTitle"), null, 1, 1);

		Assert.assertNotNull(resultBag);
		Assert.assertEquals(3, resultBag.getTotal());

		List<Dashboard> dashboards = resultBag.getResults();

		Assert.assertEquals(dashboards.toString(), 1, dashboards.size());

		Dashboard dashboard = dashboards.get(0);

		Assert.assertEquals("Asset Title 2", dashboard.getAssetTitle());
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "custom-asset-dashboards-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUpdateDashboard() {
		Dashboard dashboard = _dashboardDog.updateDashboard(
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9",
			"{\"rows\": [{\"panels\":[{\"title\":\"MyPanel\"," +
				"\"width\":100,\"metric\":\"assetViewed\"," +
					"\"chartType\":\"line\"}]}]}",
			"123", "Pedro");

		Assert.assertNotNull(dashboard);
		Assert.assertEquals("1", dashboard.getAssetId());
		Assert.assertEquals("Asset Title 1", dashboard.getAssetTitle());
		Assert.assertEquals("default", dashboard.getCategory());
		Assert.assertEquals(
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9",
			dashboard.getId());
		Assert.assertEquals("123", dashboard.getModifiedByUserId());
		Assert.assertEquals("Pedro", dashboard.getModifiedByUserName());
	}

	@Autowired
	private DashboardDog _dashboardDog;

}