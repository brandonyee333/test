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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.entity.Suppression;
import com.liferay.osb.asah.common.repository.SuppressionRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leilany Ulisses
 */
public class SuppressionRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		Suppression suppression = new Suppression();

		suppression.setEmailAddress("test@liferay.com");
		suppression.setEmailAddressHashed("1232324234");
		suppression.setIsNew(true);

		_suppressionRepository.save(suppression);
	}

	@Test
	public void testCountByEmailAddressContainingIgnoreCase() {
		Assertions.assertEquals(
			1,
			_suppressionRepository.countByEmailAddressContainingIgnoreCase(
				"TEST"));
	}

	@Test
	public void testDeleteByEmailAddress() {
		_suppressionRepository.deleteByEmailAddress("test@liferay.com");

		Assertions.assertFalse(
			_suppressionRepository.existsByEmailAddress("test@liferay.com"));
	}

	@Test
	public void testExistsByEmailAddress() {
		Assertions.assertTrue(
			_suppressionRepository.existsByEmailAddress("test@liferay.com"));
	}

	@Test
	public void testExistsByEmailAddressHashed() {
		Assertions.assertTrue(
			_suppressionRepository.existsByEmailAddressHashed("1232324234"));
	}

	@Test
	public void testFindByEmailAddress() {
		Optional<Suppression> suppressionOptional =
			_suppressionRepository.findByEmailAddress("test@liferay.com");

		Suppression suppression = suppressionOptional.get();

		Assertions.assertEquals(
			"test@liferay.com", suppression.getEmailAddress());
	}

	@Test
	public void testFindByEmailAddressContainingIgnoreCase() {
		List<Suppression> suppressions =
			_suppressionRepository.findByEmailAddressContainingIgnoreCase(
				"test@liferay.com", PageRequest.of(0, 10));

		Suppression suppression = suppressions.get(0);

		Assertions.assertEquals(
			"test@liferay.com", suppression.getEmailAddress());
	}

	@Autowired
	private SuppressionRepository _suppressionRepository;

}