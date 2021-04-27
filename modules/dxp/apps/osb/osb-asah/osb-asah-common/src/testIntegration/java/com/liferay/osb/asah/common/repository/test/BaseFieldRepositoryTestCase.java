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

import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.repository.FieldRepository;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseFieldRepositoryTestCase
	extends BaseRepositoryTestCase<Field, Long> {

	@Before
	public void setUp() {
		Field field1 = new Field();

		field1.setContext("organization");
		field1.setDataSourceId(1L);
		field1.setDataSourceName("Source 1");
		field1.setFieldType("Text");
		field1.setModifiedDate(new Date(System.currentTimeMillis() - 10000));
		field1.setName("field1");
		field1.setOwnerId(10L);
		field1.setOwnerType("account");
		field1.setSourceName("Field 1");
		field1.setValue("field one");

		Field field2 = new Field();

		field2.setContext("organization");
		field2.setDataSourceId(1L);
		field2.setDataSourceName("Source 1");
		field2.setFieldType("Text");
		field2.setModifiedDate(new Date(System.currentTimeMillis()));
		field2.setName("field1");
		field2.setOwnerId(10L);
		field2.setOwnerType("account");
		field2.setSourceName("Field 1");
		field2.setValue("field two");

		Field field3 = new Field();

		field3.setContext("organization");
		field3.setDataSourceId(1L);
		field3.setDataSourceName("Source 1");
		field3.setFieldType("Text");
		field3.setModifiedDate(new Date());
		field3.setName("field3");
		field3.setOwnerId(10L);
		field3.setOwnerType("account");
		field3.setSourceName("Field 3");
		field3.setValue("field three");

		setUpRepository(field1, field2, field3);

		_field2 = entityModels.get(1);
		_field3 = entityModels.get(2);
	}

	@Test
	public void testFindByContextAndOwnerIdGroupByMaxModifiedDateAndName() {
		List<Field> fields =
			_fieldRepository.
				findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
					"organization", 10L);

		Assert.assertEquals(fields.toString(), 2, fields.size());

		_assertFieldEquals(_field2, fields.get(0));
		_assertFieldEquals(_field3, fields.get(1));
	}

	@Override
	protected CrudRepository<Field, Long> getCrudRepository() {
		return _fieldRepository;
	}

	private void _assertFieldEquals(Field expectedField, Field actualField) {
		Assert.assertEquals(
			expectedField.getContext(), actualField.getContext());
		Assert.assertEquals(
			expectedField.getDataSourceId(), actualField.getDataSourceId());
		Assert.assertEquals(
			expectedField.getDataSourceName(), actualField.getDataSourceName());
		Assert.assertEquals(
			expectedField.getFieldType(), actualField.getFieldType());
		Assert.assertEquals(expectedField.getName(), actualField.getName());
		Assert.assertEquals(
			expectedField.getOwnerId(), actualField.getOwnerId());
		Assert.assertEquals(
			expectedField.getOwnerType(), actualField.getOwnerType());
		Assert.assertEquals(
			expectedField.getSourceName(), actualField.getSourceName());
		Assert.assertEquals(expectedField.getValue(), actualField.getValue());
	}

	private Field _field2;
	private Field _field3;

	@Autowired
	private FieldRepository _fieldRepository;

}