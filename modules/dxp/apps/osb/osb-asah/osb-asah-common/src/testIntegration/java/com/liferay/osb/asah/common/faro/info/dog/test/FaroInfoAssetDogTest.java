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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAssetDog;
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
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoAssetDogTest extends BaseFaroInfoDogTestCase {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets-duplicate-keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetKeywordsOmitsDuplicateKeywords() {
		List<String> keywords = _faroInfoAssetDog.getKeywords();

		Assert.assertEquals(
			keywords.toString(),
			Arrays.asList("bar", "blar", "floo", "foo", "glue", "jar"),
			keywords);
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets-form-keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetKeywordsOmitsFormKeywords() {
		List<String> keywords = _faroInfoAssetDog.getKeywords();

		Assert.assertEquals(
			keywords.toString(),
			Arrays.asList(
				"bar", "blar", "floo", "foo", "gllue", "glue", "jar", "jlar"),
			keywords);
	}

	@Autowired
	private FaroInfoAssetDog _faroInfoAssetDog;

}