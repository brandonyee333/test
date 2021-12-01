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

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Marcos Martins
 */
public abstract class BaseFieldMappingRepositoryTestCase
	extends BaseRepositoryTestCase<FieldMapping, Long> {

	@BeforeEach
	public void setUp() {
		FieldMapping fieldMapping1 = new FieldMapping();

		fieldMapping1.setContext("custom");
		fieldMapping1.setDisplayName("Field 1");
		fieldMapping1.setDisplayType("input-field");
		fieldMapping1.setFieldName("field1");
		fieldMapping1.setFieldType("Text");
		fieldMapping1.setOwnerType("individual");

		FieldMapping fieldMapping2 = new FieldMapping();

		fieldMapping2.setContext("custom");
		fieldMapping2.setDisplayName("Field 1");
		fieldMapping2.setDisplayType("input-field");
		fieldMapping2.setFieldName("field2");
		fieldMapping2.setFieldType("Text");
		fieldMapping2.setOwnerType("individual");

		FieldMapping fieldMapping3 = new FieldMapping();

		fieldMapping3.setContext("custom");
		fieldMapping3.setDisplayName("Field 3");
		fieldMapping3.setDisplayType("input-field");
		fieldMapping3.setFieldName("field3");
		fieldMapping3.setFieldType("Text");
		fieldMapping3.setOwnerType("individual");

		setUpRepository(fieldMapping1, fieldMapping2, fieldMapping3);
	}

	@Test
	public void testFindByContextAndDisplayNameAndOwnerType() {
		List<FieldMapping> fieldMappings =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				"custom", "Field 1", "individual");

		Assertions.assertEquals(2, fieldMappings.size());

		fieldMappings =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				"custom", "Field 3", "individual");

		Assertions.assertEquals(1, fieldMappings.size());
		fieldMappings =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				"custom", "Field 4", "individual");

		Assertions.assertEquals(0, fieldMappings.size());
	}

	@Override
	protected PagingAndSortingRepository<FieldMapping, Long>
		getPagingAndSortingRepository() {

		return _fieldMappingRepository;
	}

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

}