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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.repository.EventAttributeRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit5ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ExtendWith(OSBAsahSpringJUnit5ClassRunner.class)
@Import(JDBCTestConfiguration.class)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class EventAttributeRepositoryTest {

	@SQLResource(resourcePath = "test_event_attributes.sql")
	@Test
	public void testFindDistinctAttributeValues() {
		Date date = DateUtil.newDate();

		Assertions.assertEquals(
			new ArrayList<EventAttributeValue>() {
				{
					add(
						new EventAttributeValue(
							_getExpectedDate(date, -1), "Windshield Wipers"));
					add(
						new EventAttributeValue(
							_getExpectedDate(date, -2), "Wheels"));
					add(
						new EventAttributeValue(
							_getExpectedDate(date, -3), "Plates"));
					add(
						new EventAttributeValue(
							_getExpectedDate(date, -4), "Apples"));
					add(
						new EventAttributeValue(
							_getExpectedDate(date, -6), "Hair Dye"));
				}
			},
			_eventAttributeRepository.findDistinctAttributeValues(2001L, 5));
	}

	@SQLResource(resourcePath = "test_event_attributes_1.sql")
	@Test
	public void testFindDistinctAttributeValuesNoMatchingValues() {
		Assertions.assertEquals(
			Collections.emptyList(),
			_eventAttributeRepository.findDistinctAttributeValues(2001L, 10));
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
	private EventAttributeRepository _eventAttributeRepository;

}