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
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AssetDogTest {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_duplicate_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetKeywordsOmitsDuplicateKeywords() {
		List<String> keywords = _assetDog.getKeywords("Page");

		Assert.assertEquals(
			keywords.toString(),
			Arrays.asList("bar", "blar", "floo", "foo", "glue", "jar"),
			keywords);
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets_form_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetKeywordsOmitsFormKeywords() {
		List<String> keywords = _assetDog.getKeywords("Page");

		Assert.assertEquals(
			keywords.toString(),
			Arrays.asList(
				"bar", "blar", "floo", "foo", "gllue", "glue", "jar", "jlar"),
			keywords);
	}

	@Autowired
	private AssetDog _assetDog;

}