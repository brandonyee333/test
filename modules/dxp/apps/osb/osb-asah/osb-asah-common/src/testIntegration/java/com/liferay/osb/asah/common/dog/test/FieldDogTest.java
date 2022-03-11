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

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 */
public class FieldDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildCSVDataSource());

		_fieldMapping = _fieldMappingDog.addFieldMapping(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				_dataSource.getId(), "date", "date", "Date"));
	}

	@AfterEach
	public void tearDown() throws Exception {
		_fieldDog.deleteFields(_dataSource.getId());
		_fieldMappingDog.deleteFieldMapping(_fieldMapping);

		_dataSourceDog.deleteDataSource(_dataSource);
	}

	@Test
	public void test12HourTimeAtBeginning() throws Exception {
		_testDateField("3:49:38PM 1995/04/29", "1995-04-29T15:49:38");
	}

	@Test
	public void test12HourTimeAtEnd() throws Exception {
		_testDateField("1999/02/21 2:49:05PM", "1999-02-21T14:49:05");
	}

	@Test
	public void test24HourTimeAtBeginning() throws Exception {
		_testDateField("6:55:26 1997/05/23", "1997-05-23T06:55:26");
	}

	@Test
	public void test24HourTimeAtEnd() throws Exception {
		_testDateField("1930/03/23 10:52:41", "1930-03-23T10:52:41");
	}

	@Test
	public void testAbbreviatedMonthNameDateCommaYearPattern()
		throws Exception {

		_testDateField("Dec 6, 1982", "1982-12-06T");
	}

	@Test
	public void testDateToStringPattern() throws Exception {
		_testDateField(
			"Sun Oct 22 18:45:27 GMT 2028", "2028-10-22T18:45:27.000Z");
	}

	@Test
	public void testFullMonthNameDateCommaYearPattern() throws Exception {
		_testDateField("June 28, 1987", "1987-06-28T");
	}

	@Test
	public void testMonthDashDateDashYearPattern() throws Exception {
		_testDateField("5-18-2048", "2048-05-18T");
	}

	@Test
	public void testMonthSlashDateSlashYearPattern() throws Exception {
		_testDateField("7/30/1934", "1934-07-30T");
	}

	@Test
	public void testNegativeTimestamp() throws Exception {
		_testDateField(-1569829057471L, "1920-04-03T16:22:22.529Z");
	}

	@Test
	public void testTimestamp() throws Exception {
		_testDateField(2153192221164L, "2038-03-26T04:57:01.164Z");
	}

	@Test
	public void testUpdateFields() throws Exception {
		_fieldMappingDog.addFieldMapping(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				_dataSource.getId(), "test", "test", "Text"));

		_fieldDog.addFields(
			"demographics", JSONUtil.put("date", "1954/02/03"), _dataSource, 1L,
			"individual");

		_fieldDog.updateFields(
			"demographics", JSONUtil.put("test", "test"), _dataSource,
			Collections.singletonList(1L), "individual");

		List<Field> fields = _fieldDog.getFields(
			"demographics", Collections.singletonList(1L));

		Stream<Field> stream = fields.stream();

		Optional<Field> fieldOptional = stream.filter(
			field -> StringUtils.equals(field.getName(), "test")
		).findFirst();

		Assertions.assertTrue(fieldOptional.isPresent());

		Field field = fieldOptional.get();

		Assertions.assertEquals(field.getValue(), "test");
	}

	@Test
	public void testYearDashMonthDashDatePattern() throws Exception {
		_testDateField("1965-03-27", "1965-03-27T");
	}

	@Test
	public void testYearSlashMonthSlashDatePattern() throws Exception {
		_testDateField("1954/02/03", "1954-02-03T");
	}

	private void _testDateField(Object date, String expectedDateString)
		throws Exception {

		CSVIndividual csvIndividual = _csvIndividualRepository.save(
			FaroInfoTestUtil.buildCSVIndividual(
				_dataSource.getId(), RandomTestUtil.randomUUID(),
				_objectMapper.convertValue(
					new HashMap<String, Object>() {
						{
							put("date", date);
						}
					},
					JSONObject.class)));

		List<Field> fields = _fieldDog.addFields(
			"demographics", csvIndividual.getFieldsJSONObject(), _dataSource,
			null, "individual");

		Field dateField = fields.get(0);

		String actualDateString = String.valueOf(dateField.getValue());

		Assertions.assertTrue(
			actualDateString.startsWith(expectedDateString),
			"Expected date to start with \"" + expectedDateString +
				"\" instead of \"" + actualDateString + "\"");
	}

	@Autowired
	private CSVIndividualRepository _csvIndividualRepository;

	private DataSource _dataSource;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FieldDog _fieldDog;

	private FieldMapping _fieldMapping;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private ObjectMapper _objectMapper;

}