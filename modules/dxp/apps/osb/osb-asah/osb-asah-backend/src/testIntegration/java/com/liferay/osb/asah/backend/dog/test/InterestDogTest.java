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

import com.liferay.osb.asah.backend.dog.InterestDog;
import com.liferay.osb.asah.backend.model.Interest;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

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
public class InterestDogTest {

	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterestResultBag() {
		ResultBag<Interest> interestResultBag =
			_interestDog.getInterestResultBag(
				"374790572703144534", "individual", 20, 0);

		Assert.assertEquals(1, interestResultBag.getTotal());

		List<Interest> interests = interestResultBag.getResults();

		Interest interest = interests.get(0);

		Assert.assertEquals("compelling metrics", interest.getName());
	}

	@Autowired
	private InterestDog _interestDog;

}