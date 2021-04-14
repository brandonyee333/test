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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahSpringBootApplication.class)
public class AssetDogTest {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBag() {
		Page<Asset> assetPage = _assetDog.getAssetPage(
			"Page", null, null, 0, 20, Sort.desc("id"));

		Assert.assertEquals(3, assetPage.getTotalElements());
		Assert.assertEquals(
			Arrays.asList(
				"seize compelling action-items", "engineer intuitive models",
				"empower holistic ROI"),
			_getPageAssetTitles(assetPage.getContent()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBagWithKeywords() {
		Page<Asset> assetPage = _assetDog.getAssetPage(
			"Page", "seize", null, 0, 20, Sort.desc("id"));

		Assert.assertEquals(1, assetPage.getTotalElements());
		Assert.assertEquals(
			Arrays.asList("seize compelling action-items"),
			_getPageAssetTitles(assetPage.getContent()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBagWithNamePropertyFilter() {
		Page<Asset> assetPage = _assetDog.getAssetPage(
			"Page", null,
			Arrays.asList(
				new PropertyFilter("name = engineer intuitive models", false)),
			0, 20, Sort.desc("id"));

		Assert.assertEquals(1, assetPage.getTotalElements());
		Assert.assertEquals(
			Arrays.asList("engineer intuitive models"),
			_getPageAssetTitles(assetPage.getContent()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBagWithURLPropertyFilter() {
		Page<Asset> assetPage = _assetDog.getAssetPage(
			"Page", null,
			Arrays.asList(
				new PropertyFilter(
					"url = https://www.terrance-lueilwitz.biz", false)),
			0, 20, Sort.desc("id"));

		Assert.assertEquals(1, assetPage.getTotalElements());
		Assert.assertEquals(
			Arrays.asList("empower holistic ROI"),
			_getPageAssetTitles(assetPage.getContent()));
	}

	private List<String> _getPageAssetTitles(List<Asset> assets) {
		return ListUtil.map(assets, Asset::getTitle);
	}

	@Autowired
	private AssetDog _assetDog;

}