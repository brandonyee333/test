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

import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseFieldRepositoryTestCase
	extends BaseRepositoryTestCase<Field, Long> {

	@BeforeEach
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

		_field1 = entityModels.get(0);
		_field2 = entityModels.get(1);
		_field3 = entityModels.get(2);
	}

	@Test
	public void testCountFields() {
		Assertions.assertEquals(
			1,
			_fieldRepository.countFields(new FilterHelper("name eq 'field3'")));
	}

	@Test
	public void testDeleteByContextAndDataSourceIdAndNameAndOwnerIdInAndOwnerType() {
		_fieldRepository.
			deleteByContextAndDataSourceIdAndNameAndOwnerIdInAndOwnerType(
				"organization", 1L, "field1", Arrays.asList(10L), "account");

		Assertions.assertEquals(1, _fieldRepository.count());
	}

	@Test
	public void testDeleteByContextAndOwnerId() {
		_fieldRepository.deleteByContextAndOwnerId("organization", 10L);

		Assertions.assertEquals(0, _fieldRepository.count());
	}

	@Test
	public void testDeleteByDataSourceId() {
		_fieldRepository.deleteByDataSourceId(1L);

		Assertions.assertEquals(0, _fieldRepository.count());
	}

	@Test
	public void testDeleteByOwnerIdAndOwnerType() {
		_fieldRepository.deleteByOwnerIdAndOwnerType(10L, "account");

		Assertions.assertEquals(0, _fieldRepository.count());
	}

	@Test
	public void testDeleteByOwnerIdInAndOwnerType() {
		_fieldRepository.deleteByOwnerIdInAndOwnerType(
			Arrays.asList(10L), "account");

		Assertions.assertEquals(0, _fieldRepository.count());
	}

	@Test
	public void testExistsByNameAndOwnerId() {
		Assertions.assertTrue(
			_fieldRepository.existsByNameAndOwnerId("field1", 10L));
	}

	@Test
	public void testFindByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType() {
		List<Field> fields =
			_fieldRepository.
				findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
					"organization", 1L, "field1", 10L, "account");

		Assertions.assertEquals(2, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
		_assertFieldEquals(_field2, fields.get(1));
	}

	@Test
	public void testFindByContextAndDataSourceIdAndOwnerIdAndOwnerType() {
		List<Field> fields =
			_fieldRepository.findByContextAndDataSourceIdAndOwnerIdAndOwnerType(
				"organization", 1L, 10L, "account");

		Assertions.assertEquals(3, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
		_assertFieldEquals(_field2, fields.get(1));
		_assertFieldEquals(_field3, fields.get(2));
	}

	@Test
	public void testFindByContextAndDataSourceIdAndOwnerIdInAndOwnerType() {
		List<Field> fields =
			_fieldRepository.
				findByContextAndDataSourceIdAndOwnerIdInAndOwnerType(
					"organization", 1L, Arrays.asList(10L), "account");

		Assertions.assertEquals(3, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
		_assertFieldEquals(_field2, fields.get(1));
		_assertFieldEquals(_field3, fields.get(2));
	}

	@Test
	public void testFindByContextAndNameAndOwnerIdAndOwnerType() {
		List<Field> fields =
			_fieldRepository.findByContextAndNameAndOwnerIdAndOwnerType(
				"organization", "field1", 10L, "account");

		Assertions.assertEquals(2, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
		_assertFieldEquals(_field2, fields.get(1));
	}

	@Test
	public void testFindByContextAndNameAndOwnerIdInAndOwnerType() {
		List<Field> fields =
			_fieldRepository.findByContextAndNameAndOwnerIdInAndOwnerType(
				"organization", "field1", Arrays.asList(10L), "account");

		Assertions.assertEquals(2, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
		_assertFieldEquals(_field2, fields.get(1));
	}

	@Test
	public void testFindByContextAndOwnerIdAndOwnerType() {
		List<Field> fields =
			_fieldRepository.findByContextAndOwnerIdAndOwnerType(
				"organization", 10L, "account");

		Assertions.assertEquals(3, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
		_assertFieldEquals(_field2, fields.get(1));
		_assertFieldEquals(_field3, fields.get(2));
	}

	@Test
	public void testFindByContextAndOwnerIdGroupByMaxModifiedDateAndName() {
		List<Field> fields =
			_fieldRepository.
				findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
					"organization", 10L);

		Assertions.assertEquals(2, fields.size(), fields.toString());

		_assertFieldEquals(_field2, fields.get(0));
		_assertFieldEquals(_field3, fields.get(1));
	}

	@Test
	public void testFindByContextAndOwnerIdInGroupByMaxModifiedDateAndName() {
		List<Field> fields =
			_fieldRepository.
				findByContextAndOwnerIdInGroupByMaxModifiedDateAndName(
					"organization", Arrays.asList(10L));

		Assertions.assertEquals(2, fields.size(), fields.toString());

		_assertFieldEquals(_field2, fields.get(0));
		_assertFieldEquals(_field3, fields.get(1));
	}

	@Test
	public void testFindByFieldTypeAndOwnerTypeAndValueIn() {
		List<Field> fields =
			_fieldRepository.findByFieldTypeAndOwnerTypeAndValueIn(
				"Text", "account", Collections.singletonList("field three"));

		Assertions.assertEquals(1, fields.size(), fields.toString());

		_assertFieldEquals(_field3, fields.get(0));
	}

	@Test
	public void testGetFieldTransformations() {
		List<Transformation> transformations =
			_fieldRepository.getFieldTransformations(
				"groupby((name))", FilterHelper.EMPTY,
				PageRequest.of(
					0, 10,
					SortUtil.getSort(
						Sort.by(Sort.Order.desc("totalElements")),
						new String[] {
							"totalElements", "desc", "terms", "asc"
						})));

		Assertions.assertTrue(
			transformations.contains(
				new Transformation(
					Collections.singletonMap("name", "field1"), 2)));
		Assertions.assertTrue(
			transformations.contains(
				new Transformation(
					Collections.singletonMap("name", "field3"), 1)));
	}

	@Test
	public void testSearchFields() {
		List<Field> fields = _fieldRepository.searchFields(
			new FilterHelper("name eq 'field1'"), PageRequest.of(0, 1));

		Assertions.assertEquals(1, fields.size(), fields.toString());

		_assertFieldEquals(_field1, fields.get(0));
	}

	@Test
	public void testUpdateDataSourceNameByDataSourceId() {
		_fieldRepository.updateDataSourceNameByDataSourceId(
			1L, "Source 1 Edited");

		List<Field> fields =
			_fieldRepository.findByContextAndDataSourceIdAndOwnerIdAndOwnerType(
				"organization", 1L, 10L, "account");

		fields.forEach(
			field -> Assertions.assertEquals(
				"Source 1 Edited", field.getDataSourceName()));
	}

	@Override
	protected PagingAndSortingRepository<Field, Long>
		getPagingAndSortingRepository() {

		return _fieldRepository;
	}

	private void _assertFieldEquals(Field expectedField, Field actualField) {
		Assertions.assertEquals(
			expectedField.getContext(), actualField.getContext());
		Assertions.assertEquals(
			expectedField.getDataSourceId(), actualField.getDataSourceId());
		Assertions.assertEquals(
			expectedField.getDataSourceName(), actualField.getDataSourceName());
		Assertions.assertEquals(
			expectedField.getFieldType(), actualField.getFieldType());
		Assertions.assertEquals(expectedField.getName(), actualField.getName());
		Assertions.assertEquals(
			expectedField.getOwnerId(), actualField.getOwnerId());
		Assertions.assertEquals(
			expectedField.getOwnerType(), actualField.getOwnerType());
		Assertions.assertEquals(
			expectedField.getSourceName(), actualField.getSourceName());
		Assertions.assertEquals(
			expectedField.getValue(), actualField.getValue());
	}

	private Field _field1;
	private Field _field2;
	private Field _field3;

	@Autowired
	private FieldRepository _fieldRepository;

}