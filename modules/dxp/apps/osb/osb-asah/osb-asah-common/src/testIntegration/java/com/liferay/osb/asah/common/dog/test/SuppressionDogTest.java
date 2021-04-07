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
import com.liferay.osb.asah.common.model.Suppression;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
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

/**
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "suppressions", resourcePath = "suppressions.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class SuppressionDogTest {

	@Test
	public void testGetSuppressionResultBag() {
		ResultBag<Suppression> suppressionResultBag =
			_suppressionDog.getSuppressionResultBag(
				null, 10, Sort.desc("createDate"), 0);

		Assert.assertEquals(3, suppressionResultBag.getTotal());

		Assert.assertEquals(
			Arrays.asList(
				"jane.doe@gmail.com", "test@liferay.com", "john.doe@gmail.com"),
			ListUtil.map(
				suppressionResultBag.getResults(),
				Suppression::getEmailAddress));
	}

	@Test
	public void testGetSuppressionResultBagSearch() {
		ResultBag<Suppression> suppressionResultBag =
			_suppressionDog.getSuppressionResultBag(
				"liferay", 10, Sort.desc("createDate"), 0);

		Assert.assertEquals(1, suppressionResultBag.getTotal());

		List<Suppression> suppressions = suppressionResultBag.getResults();

		Suppression suppression = suppressions.get(0);

		Assert.assertEquals("test@liferay.com", suppression.getEmailAddress());
	}

	@Autowired
	private SuppressionDog _suppressionDog;

}