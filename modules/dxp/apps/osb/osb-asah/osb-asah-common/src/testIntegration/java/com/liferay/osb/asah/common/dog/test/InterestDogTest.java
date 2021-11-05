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

import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcellus Tavares
 */
public class InterestDogTest implements OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterest() {
		Interest interest = _interestDog.getInterest(635452168436521350L);

		Assertions.assertEquals(
			Long.valueOf(635452168436521350L), interest.getId());

		Assertions.assertEquals("sales", interest.getName());
	}

	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterestBadRequest() {
		Assertions.assertThrows(
			OSBAsahException.class,
			() -> _interestDog.getInterest(374790572703144534L));
	}

	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterestPage() {
		Page<Interest> interestPage = _interestDog.getInterestPage(
			374790572703144534L, "individual", 20, 0);

		Assertions.assertEquals(1, interestPage.getTotalElements());

		List<Interest> interests = interestPage.getContent();

		Interest interest = interests.get(0);

		Assertions.assertEquals("compelling metrics", interest.getName());
	}

	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterestPageByFilterStringAndScore() {
		Page<Interest> interestPage = _interestDog.getInterestPage(
			"name eq 'javascript'", 0.1, 0, 20, null);

		Assertions.assertEquals(2, interestPage.getTotalElements());

		List<Interest> interests = interestPage.getContent();

		Interest interest = interests.get(0);

		Assertions.assertEquals("javascript", interest.getName());
	}

	@Autowired
	private InterestDog _interestDog;

}