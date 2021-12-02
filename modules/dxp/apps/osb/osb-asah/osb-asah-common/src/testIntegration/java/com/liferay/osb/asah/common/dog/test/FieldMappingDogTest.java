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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class FieldMappingDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testAddDuplicateWithDifferentTypeFieldMappings() {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		String name = "What is your income?";

		_fieldMappingDog.addFieldMapping(
			"custom", name, dataSource.getId(), "input-field", name, "Number",
			"individual");

		Assertions.assertEquals(1, _fieldMappingRepository.count());

		List<FieldMapping> fieldMappings =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				"custom", name, "individual");

		FieldMapping fieldMapping = fieldMappings.get(0);

		Assertions.assertEquals(name, fieldMapping.getDisplayName());
		Assertions.assertEquals(
			"What_is_your_income_", fieldMapping.getFieldName());

		_fieldMappingDog.addFieldMapping(
			"custom", name, dataSource.getId(), "input-field", name, "Text",
			"individual");

		Assertions.assertEquals(2, _fieldMappingRepository.count());

		fieldMappings =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				"custom", name, "individual");

		Assertions.assertEquals(
			Arrays.asList("What_is_your_income_", "What_is_your_income__1"),
			ListUtil.map(fieldMappings, FieldMapping::getFieldName));

		_fieldMappingDog.addFieldMapping(
			"custom", name, dataSource.getId(), "input-field", name, "Number",
			"individual");

		Assertions.assertEquals(2, _fieldMappingRepository.count());

		Optional<FieldMapping> fieldMappingOptional =
			_fieldMappingRepository.findByContextAndFieldNameAndOwnerType(
				"custom", "What_is_your_income_", "individual");

		fieldMapping = fieldMappingOptional.get();

		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		Assertions.assertFalse(dataSourceFieldNames.isEmpty());

		fieldMappingOptional =
			_fieldMappingRepository.findByContextAndFieldNameAndOwnerType(
				"custom", "What_is_your_income__1", "individual");

		fieldMapping = fieldMappingOptional.get();

		dataSourceFieldNames = fieldMapping.getDataSourceFieldNames();

		Assertions.assertTrue(dataSourceFieldNames.isEmpty());
	}

	@Test
	public void testAddEmailFieldMappingCSVDataSource() {
		_testAddEmailFieldMapping(
			_dataSourceRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildCSVDataSourceJSONObject(),
					DataSource.class)),
			"email");
	}

	@Test
	public void testAddEmailFieldMappingLiferayDataSource() {
		_testAddEmailFieldMapping(
			_dataSourceRepository.save(
				FaroInfoTestUtil.buildLiferayDataSource()),
			"emailAddress");
	}

	@Test
	public void testAddEmailFieldMappingSalesforceDataSource() {
		_testAddEmailFieldMapping(
			_dataSourceRepository.save(
				FaroInfoTestUtil.buildSalesforceDataSource()),
			"email");
	}

	@Test
	public void testAddInvalidCharactersFieldMapping() {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		String name = "test!@#$%^Invalid_Characters^&*()";

		_fieldMappingDog.addFieldMapping(
			"custom", name, dataSource.getId(), "input-field", name, "Text",
			"individual");

		Assertions.assertEquals(1, _fieldMappingRepository.count());

		Iterable<FieldMapping> fieldMappings =
			_fieldMappingRepository.findAll();

		Iterator<FieldMapping> iterator = fieldMappings.iterator();

		FieldMapping fieldMapping = iterator.next();

		Assertions.assertEquals(name, fieldMapping.getDisplayName());
		Assertions.assertEquals(
			"test______Invalid_Characters_____", fieldMapping.getFieldName());
	}

	@Test
	public void testAddSanitizedDuplicateFieldMappings() {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		String name = "What is your income?";

		_fieldMappingDog.addFieldMapping(
			"custom", name, dataSource.getId(), "input-field", name, "Number",
			"individual");

		Assertions.assertEquals(1, _fieldMappingRepository.count());

		Iterable<FieldMapping> fieldMappings =
			_fieldMappingRepository.findAll();

		Iterator<FieldMapping> iterator = fieldMappings.iterator();

		FieldMapping fieldMapping = iterator.next();

		Assertions.assertEquals(name, fieldMapping.getDisplayName());
		Assertions.assertEquals(
			"What_is_your_income_", fieldMapping.getFieldName());

		name = "What-is-your-income?";

		_fieldMappingDog.addFieldMapping(
			"custom", name, dataSource.getId(), "input-field", name, "Number",
			"individual");

		Assertions.assertEquals(2, _fieldMappingRepository.count());

		List<FieldMapping> fieldMappingOptional =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				"custom", name, "individual");

		fieldMapping = fieldMappingOptional.get(0);

		Assertions.assertEquals(
			"What_is_your_income__1", fieldMapping.getFieldName());
	}

	@Test
	public void testDeleteFieldMapping() {
		FieldMapping fieldMapping = new FieldMapping();

		fieldMapping.setAuthorId("123");
		fieldMapping.setAuthorName("test");

		fieldMapping = _fieldMappingRepository.save(fieldMapping);

		Assertions.assertTrue(
			_fieldMappingDog.deleteFieldMapping(fieldMapping));

		fieldMapping = new FieldMapping();

		fieldMapping.setAuthorId("FARO_SYSTEM");
		fieldMapping.setAuthorName("FARO_SYSTEM");

		fieldMapping = _fieldMappingRepository.save(fieldMapping);

		Assertions.assertFalse(
			_fieldMappingDog.deleteFieldMapping(fieldMapping));

		Assertions.assertEquals(1, _fieldMappingRepository.count());
	}

	@Test
	public void testFetchFieldMappingJSONObject() {
		FieldMapping fieldMapping = new FieldMapping();

		fieldMapping.setContext("demographics");
		fieldMapping.setFieldName("email");
		fieldMapping.setOwnerType("individual");

		_fieldMappingRepository.save(fieldMapping);

		Assertions.assertNotNull(
			_fieldMappingDog.fetchFieldMapping(
				"demographics", "email", "individual"));
	}

	@Test
	public void testGetFieldMappings() {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		_fieldMappingDog.addEmailFieldMapping(dataSource.getId());

		Iterable<FieldMapping> fieldMappings =
			_fieldMappingRepository.findAll();

		Iterator<FieldMapping> iterator = fieldMappings.iterator();

		FieldMapping fieldMapping = iterator.next();

		Assertions.assertEquals(
			Collections.singletonList(fieldMapping),
			_fieldMappingDog.getFieldMappings(
				Collections.singleton(fieldMapping.getId())));

		Assertions.assertEquals(
			Collections.emptyList(),
			_fieldMappingDog.getFieldMappings(Collections.singleton(null)));
	}

	@Test
	public void testRemoveDataSourceFieldName() {
		FieldMapping fieldMapping = new FieldMapping();

		fieldMapping.setAuthorId("123");
		fieldMapping.setAuthorName("test");
		fieldMapping.setContext("demographics");
		fieldMapping.setCreateDate(new Date());
		fieldMapping.setDataSourceFieldNames(
			new HashMap<String, String>() {
				{
					put("123", "testFieldName1");
					put("234", "testFieldName2");
				}
			});
		fieldMapping.setOwnerType("individual");

		fieldMapping = _fieldMappingRepository.save(fieldMapping);

		fieldMapping = _fieldMappingDog.removeDataSourceFieldName(
			123L, fieldMapping);

		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		Assertions.assertFalse(dataSourceFieldNames.containsKey("123"));
		Assertions.assertTrue(dataSourceFieldNames.containsKey("234"));
	}

	@Test
	public void testUpdateEmailFieldMapping() {
		_testAddEmailFieldMapping(
			_dataSourceRepository.save(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildCSVDataSourceJSONObject(),
					DataSource.class)),
			"email");

		_testAddEmailFieldMapping(
			_dataSourceRepository.save(
				FaroInfoTestUtil.buildLiferayDataSource()),
			"emailAddress");

		_testAddEmailFieldMapping(
			_dataSourceRepository.save(
				FaroInfoTestUtil.buildSalesforceDataSource()),
			"email");
	}

	private void _testAddEmailFieldMapping(
		DataSource dataSource, String emailFieldName) {

		_fieldMappingDog.addEmailFieldMapping(dataSource.getId());

		Assertions.assertEquals(1, _fieldMappingRepository.count());

		Iterable<FieldMapping> fieldMappings =
			_fieldMappingRepository.findAll();

		Iterator<FieldMapping> iterator = fieldMappings.iterator();

		FieldMapping fieldMapping = iterator.next();

		fieldMapping = _fieldMappingDog.fetchFieldMapping(fieldMapping.getId());

		Assertions.assertEquals("demographics", fieldMapping.getContext());
		Assertions.assertNotNull(fieldMapping.getCreateDate());
		Assertions.assertEquals("email", fieldMapping.getFieldName());
		Assertions.assertEquals(
			"http://schema.org/email", fieldMapping.getFieldType());
		Assertions.assertNotNull(fieldMapping.getModifiedDate());
		Assertions.assertEquals("individual", fieldMapping.getOwnerType());
		Assertions.assertEquals("MOST_RECENT", fieldMapping.getStrategyKey());

		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		Assertions.assertEquals(
			emailFieldName,
			dataSourceFieldNames.get(String.valueOf(dataSource.getId())));
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}