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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Michael Bowerman
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoFieldDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

		_dataSourceJSONObject = elasticsearchInvoker.add(
			"data-sources", FaroInfoTestUtil.buildCSVDataSourceJSONObject());

		elasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				_dataSourceJSONObject.getString("id"), "date", "date", "Date"));
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
	public void testYearDashMonthDashDatePattern() throws Exception {
		_testDateField("1965-03-27", "1965-03-27T");
	}

	@Test
	public void testYearSlashMonthSlashDatePattern() throws Exception {
		_testDateField("1954/02/03", "1954-02-03T");
	}

	private void _testDateField(Object date, String expectedDateString)
		throws Exception {

		JSONObject contextJSONObject = _faroInfoFieldDog.buildContextJSONObject(
			"demographics",
			FaroInfoTestUtil.buildCSVIndividualJSONObject(
				_dataSourceJSONObject.getString("id"),
				RandomTestUtil.randomUUID(),
				new HashMap<String, Object>() {
					{
						put("date", date);
					}
				}),
			_dataSourceJSONObject, "individual");

		String actualDateString = String.valueOf(
			JSONUtil.getValue(
				contextJSONObject, "JSONArray/date", "Object/0",
				"Object/value"));

		Assert.assertTrue(
			"Expected date to start with \"" + expectedDateString +
				"\" instead of \"" + actualDateString + "\"",
			actualDateString.startsWith(expectedDateString));
	}

	private JSONObject _dataSourceJSONObject;

	@Autowired
	private FaroInfoFieldDog _faroInfoFieldDog;

}