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

import com.liferay.osb.asah.common.dog.RecommendationDog;
import com.liferay.osb.asah.common.model.ItemRecommendation;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class RecommendationDogTest {

	@ElasticsearchIndex(
		name = "recommended-items",
		resourcePath = "recommended_items_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteItemRecommendationsByJobId() {
		_recommendationDog.deleteItemRecommendationsByJobId(1L);

		Page<ItemRecommendation> itemRecommendationPage =
			_recommendationDog.getItemRecommendationPage(
				1L, 0, 20, Sort.desc("id"));

		Assert.assertEquals(0, itemRecommendationPage.getTotalElements());
	}

	@ElasticsearchIndex(
		name = "recommended-items",
		resourcePath = "recommended_items_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetItemRecommendation() {
		ItemRecommendation itemRecommendation =
			_recommendationDog.getItemRecommendation(
				"fd98459b712bf8a15c6f9a71e4ae52f641b25eb2");

		Assert.assertEquals(
			"https://www-prd.liferay.com/services/training/online",
			itemRecommendation.getItemId());
		Assert.assertEquals(1, (long)itemRecommendation.getJobId());
		Assert.assertEquals(
			Arrays.asList(
				"https://www-prd.liferay.com/blog",
				"https://www-prd.liferay.com/resource"),
			itemRecommendation.getRecommendedItemIds());
	}

	@ElasticsearchIndex(
		name = "recommended-items",
		resourcePath = "recommended_items_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetItemRecommendationResultBag() {
		Page<ItemRecommendation> itemRecommendationPage =
			_recommendationDog.getItemRecommendationPage(
				1L, 0, 20, Sort.desc("id"));

		List<ItemRecommendation> itemRecommendations =
			itemRecommendationPage.getContent();

		Assert.assertEquals(
			itemRecommendations.toString(), 3, itemRecommendations.size());

		Assert.assertEquals(3, itemRecommendationPage.getTotalElements());
	}

	@Autowired
	private RecommendationDog _recommendationDog;

}