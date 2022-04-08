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
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.BQEventPropertyValue;
import com.liferay.osb.asah.common.repository.BQEventPropertyRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leslie Wong
 */
@Import(JDBCTestConfiguration.class)
public class BQEventPropertyRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_bq_event_properties.sql")
	@Test
	public void testFindBQEventPropertyValuesByEventAttributeDefinitionId() {
		Date date = DateUtil.newDate();

		Assertions.assertEquals(
			new ArrayList<BQEventPropertyValue>() {
				{
					add(
						new BQEventPropertyValue(
							_getExpectedDate(date, -1), "Windshield Wipers"));
					add(
						new BQEventPropertyValue(
							_getExpectedDate(date, -2), "Wheels"));
					add(
						new BQEventPropertyValue(
							_getExpectedDate(date, -3), "Plates"));
					add(
						new BQEventPropertyValue(
							_getExpectedDate(date, -4), "Apples"));
					add(
						new BQEventPropertyValue(
							_getExpectedDate(date, -6), "Hair Dye"));
				}
			},
			_bqEventPropertyRepository.
				findBQEventPropertyValuesByEventAttributeDefinitionId(
					2001L, 5));
	}

	@SQLResource(resourcePath = "test_bq_event_properties_1.sql")
	@Test
	public void testFindBQEventPropertyValuesByEventAttributeDefinitionIdNoMatchingValues() {
		Assertions.assertEquals(
			Collections.emptyList(),
			_bqEventPropertyRepository.
				findBQEventPropertyValuesByEventAttributeDefinitionId(
					2001L, 10));
	}

	@SQLResource(resourcePath = "test_bq_event_properties_2.sql")
	@Test
	public void testSearchValues() {
		List<String> values = _bqEventPropertyRepository.searchValues(
			1L, 3001L, 3002L, "Attribute Value", PageRequest.of(0, 100));

		Assertions.assertEquals(4, values.size());

		for (String value :
				Arrays.asList(
					"event attribute value 4", "event attribute value 3",
					"event attribute value 2", "event attribute value 1")) {

			Assertions.assertTrue(values.contains(value));
		}

		values = _bqEventPropertyRepository.searchValues(
			1L, 3001L, 3002L, "Attribute Value", PageRequest.of(0, 3));

		Assertions.assertEquals(3, values.size());

		values = _bqEventPropertyRepository.searchValues(
			1L, 3001L, 3002L, "Attribute Value", PageRequest.of(1, 3));

		Assertions.assertEquals(1, values.size());

		Assertions.assertEquals(
			4,
			_bqEventPropertyRepository.countValues(
				1L, 3001L, 3002L, "Attribute Value"));
	}

	private Date _getExpectedDate(Date date, int deltaDays) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(date);

		calendar.add(Calendar.DATE, deltaDays);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	@Autowired
	private BQEventPropertyRepository _bqEventPropertyRepository;

}