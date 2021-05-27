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
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
public class SearchTest {

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "search_blogs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testAssetSearch() {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setAssetType(AssetType.BLOG);
		searchQueryContext.setKeywords("titul");
		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		int assetMetricsCount = _metricDog.getAssetMetricsCount(
			searchQueryContext);

		Assert.assertEquals(2, assetMetricsCount);
	}

	@ElasticsearchIndex(
		name = "custom-asset-dashboards",
		resourcePath = "search_custom_asset_dashboards_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testCustomAssetDashboardSearch() {
		Page<CustomAssetDashboard> customAssetDashboardPage =
			_customAssetDashboardDog.getCustomAssetDashboardPage(
				1L, "ASSET", 0, 10, Sort.asc("assetTitle"));

		Assert.assertEquals(3, customAssetDashboardPage.getTotalElements());
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "search_pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPageSearch() {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setAssetType(AssetType.PAGE);
		searchQueryContext.setKeywords("pag");
		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		int assetMetricsCount = _metricDog.getAssetMetricsCount(
			searchQueryContext);

		Assert.assertEquals(3, assetMetricsCount);
	}

	@Autowired
	private CustomAssetDashboardDog _customAssetDashboardDog;

	@Autowired
	private MetricDog _metricDog;

}