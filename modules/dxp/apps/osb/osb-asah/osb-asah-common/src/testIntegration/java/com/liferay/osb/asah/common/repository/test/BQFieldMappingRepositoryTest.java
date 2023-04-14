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
import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.repository.BQFieldMappingRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Marcellus Tavares
 */
@Import(JDBCTestConfiguration.class)
public class BQFieldMappingRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testCount() {
		Assertions.assertEquals(9, _bqFieldMappingRepository.count());
	}

	@Test
	public void testCountBQFieldMappings() {
		Assertions.assertEquals(
			0,
			_bqFieldMappingRepository.countByFilterString(
				"(context eq 'custom')"));
		Assertions.assertEquals(
			9,
			_bqFieldMappingRepository.countByFilterString(
				"(context eq 'demographics')"));
	}

	@Test
	public void testFindByDisplayName() {
		Optional<BQFieldMapping> bqFieldMappingOptional =
			_bqFieldMappingRepository.findByDisplayNameAndFieldType(
				"additionalName", "text");

		Assertions.assertNotNull(bqFieldMappingOptional.orElse(null));

		bqFieldMappingOptional =
			_bqFieldMappingRepository.findByDisplayNameAndFieldType(
				"name", "text");

		Assertions.assertNull(bqFieldMappingOptional.orElse(null));
	}

	@Test
	public void testFindByFieldName() {
		Optional<BQFieldMapping> bqFieldMappingOptional =
			_bqFieldMappingRepository.findByFieldName("middleName");

		Assertions.assertNotNull(bqFieldMappingOptional.orElse(null));

		bqFieldMappingOptional = _bqFieldMappingRepository.findByFieldName(
			"name");

		Assertions.assertNull(bqFieldMappingOptional.orElse(null));
	}

	@Test
	public void testSearchByFilterString() {
		List<BQFieldMapping> bqFieldMappings =
			_bqFieldMappingRepository.searchByFilterString(
				"((context eq 'demographics') and (ownerType eq 'individual'))",
				PageRequest.of(0, 20));

		Assertions.assertEquals(9, bqFieldMappings.size());
	}

	@Autowired
	private BQFieldMappingRepository _bqFieldMappingRepository;

}