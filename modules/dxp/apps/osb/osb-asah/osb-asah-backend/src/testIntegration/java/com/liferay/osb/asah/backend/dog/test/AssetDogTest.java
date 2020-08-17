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

import com.liferay.osb.asah.backend.dog.AssetDog;
import com.liferay.osb.asah.backend.model.PageAsset;
import com.liferay.osb.asah.backend.model.PropertyFilter;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class AssetDogTest {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBag() {
		ResultBag<PageAsset> pageAssetResultBag =
			_assetDog.getPageAssetResultBag(null, null, 20, Sort.desc("id"), 0);

		Assert.assertEquals(3, pageAssetResultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList(
				"seize compelling action-items", "engineer intuitive models",
				"empower holistic ROI"),
			_getPageAssetTitles(pageAssetResultBag.getResults()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBagWithKeywords() {
		ResultBag<PageAsset> pageAssetResultBag =
			_assetDog.getPageAssetResultBag(
				"seize", null, 20, Sort.desc("id"), 0);

		Assert.assertEquals(1, pageAssetResultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList("seize compelling action-items"),
			_getPageAssetTitles(pageAssetResultBag.getResults()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBagWithNamePropertyFilter() {
		ResultBag<PageAsset> pageAssetResultBag =
			_assetDog.getPageAssetResultBag(
				null,
				Arrays.asList(
					new PropertyFilter(
						"name = engineer intuitive models", false)),
				20, Sort.desc("id"), 0);

		Assert.assertEquals(1, pageAssetResultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList("engineer intuitive models"),
			_getPageAssetTitles(pageAssetResultBag.getResults()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageAssetResultBagWithURLPropertyFilter() {
		ResultBag<PageAsset> pageAssetResultBag =
			_assetDog.getPageAssetResultBag(
				null,
				Arrays.asList(
					new PropertyFilter(
						"url = https://www.terrance-lueilwitz.biz", false)),
				20, Sort.desc("id"), 0);

		Assert.assertEquals(1, pageAssetResultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList("empower holistic ROI"),
			_getPageAssetTitles(pageAssetResultBag.getResults()));
	}

	private List<String> _getPageAssetTitles(List<PageAsset> pageAssets) {
		Stream<PageAsset> stream = pageAssets.stream();

		return stream.map(
			PageAsset::getTitle
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private AssetDog _assetDog;

}