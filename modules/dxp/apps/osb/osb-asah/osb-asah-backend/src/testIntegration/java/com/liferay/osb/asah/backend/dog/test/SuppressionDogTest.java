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

import com.liferay.osb.asah.backend.dog.SuppressionDog;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.backend.model.Suppression;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
				null, 10, _getSort("createDate", "DESC"), 0);

		Assert.assertEquals(3, suppressionResultBag.getTotal());

		List<Suppression> suppressions = suppressionResultBag.getResults();

		Stream<Suppression> stream = suppressions.stream();

		Assert.assertEquals(
			Arrays.asList(
				"jane.doe@gmail.com", "test@liferay.com", "john.doe@gmail.com"),
			stream.map(
				Suppression::getEmailAddress
			).collect(
				Collectors.toList()
			));
	}

	@Test
	public void testGetSuppressionResultBagSearch() {
		ResultBag<Suppression> suppressionResultBag =
			_suppressionDog.getSuppressionResultBag(
				"liferay", 10, _getSort("createDate", "DESC"), 0);

		Assert.assertEquals(1, suppressionResultBag.getTotal());

		List<Suppression> suppressions = suppressionResultBag.getResults();

		Suppression suppression = suppressions.get(0);

		Assert.assertEquals("test@liferay.com", suppression.getEmailAddress());
	}

	private Map<String, String> _getSort(String column, String type) {
		Map<String, String> sort = new HashMap<>();

		sort.put("column", column);
		sort.put("type", type);

		return sort;
	}

	@Autowired
	private SuppressionDog _suppressionDog;

}