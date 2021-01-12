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
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Dashboard;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
	public void testCustomAssetSearch() {
		ResultBag<Dashboard> resultBag = _dashboardDog.getDashboardResultBag(
			"1", "ASSET", 10, Sort.asc("assetTitle"), 0);

		Assert.assertEquals(3, resultBag.getTotal());
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
	private DashboardDog _dashboardDog;

	@Autowired
	private MetricDog _metricDog;

}