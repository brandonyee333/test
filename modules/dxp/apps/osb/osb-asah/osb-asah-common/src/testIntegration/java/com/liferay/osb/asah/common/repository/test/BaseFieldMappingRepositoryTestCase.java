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

import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcos Martins
 */
public abstract class BaseFieldMappingRepositoryTestCase
	extends BaseRepositoryTestCase<FieldMapping, Long> {

	@Before
	public void setUp() {
		FieldMapping fieldMapping = new FieldMapping();

		fieldMapping.setContext("custom");
		fieldMapping.setDisplayName("Field 1");
		fieldMapping.setDisplayType("input-field");
		fieldMapping.setFieldName("field1");
		fieldMapping.setFieldType("Text");
		fieldMapping.setOwnerType("individual");

		setUpRepository(fieldMapping);
	}

	@Test
	public void testFindByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType() {
		Optional<FieldMapping> fieldMappingOptional =
			_fieldMappingRepository.
				findByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType(
					"custom", "Field 1", "selection-list", "Text",
					"individual");

		Assert.assertFalse(fieldMappingOptional.isPresent());

		fieldMappingOptional =
			_fieldMappingRepository.
				findByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType(
					"custom", "Field 1", "input-field", "Text", "individual");

		Assert.assertTrue(fieldMappingOptional.isPresent());
	}

	@Override
	protected CrudRepository<FieldMapping, Long> getCrudRepository() {
		return _fieldMappingRepository;
	}

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

}