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

import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.Suppression;
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
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "suppressions", resourcePath = "suppressions.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahSpringBootApplication.class)
public class SuppressionDogTest {

	@Test
	public void testGetSuppressionResultBag() {
		Page<Suppression> suppressionPage = _suppressionDog.getSuppressionPage(
			null, 0, 10, Sort.desc("createDate"));

		Assert.assertEquals(3, suppressionPage.getTotalElements());

		Assert.assertEquals(
			Arrays.asList(
				"jane.doe@gmail.com", "test@liferay.com", "john.doe@gmail.com"),
			ListUtil.map(
				suppressionPage.getContent(), Suppression::getEmailAddress));
	}

	@Test
	public void testGetSuppressionResultBagSearch() {
		Page<Suppression> suppressionPage = _suppressionDog.getSuppressionPage(
			"liferay", 0, 10, Sort.desc("createDate"));

		Assert.assertEquals(1, suppressionPage.getTotalElements());

		List<Suppression> suppressions = suppressionPage.getContent();

		Suppression suppression = suppressions.get(0);

		Assert.assertEquals("test@liferay.com", suppression.getEmailAddress());
	}

	@Autowired
	private SuppressionDog _suppressionDog;

}