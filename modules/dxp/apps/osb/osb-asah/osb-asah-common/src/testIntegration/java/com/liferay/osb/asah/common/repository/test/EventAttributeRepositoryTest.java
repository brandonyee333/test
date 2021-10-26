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

import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.repository.EventAttributeRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventAttributeRepositoryTest {

	@SQLResource(resourcePath = "test_event_attributes.sql")
	@Test
	public void testFindDistinctAttributeValues() {
		Assert.assertEquals(
			new ArrayList<EventAttributeValue>() {
				{
					add(
						new EventAttributeValue(
							_getExpectedDate(2021, Calendar.OCTOBER, 25),
							"Windshield Wipers"));
					add(
						new EventAttributeValue(
							_getExpectedDate(2021, Calendar.OCTOBER, 24),
							"Wheels"));
					add(
						new EventAttributeValue(
							_getExpectedDate(2021, Calendar.OCTOBER, 23),
							"Plates"));
					add(
						new EventAttributeValue(
							_getExpectedDate(2021, Calendar.OCTOBER, 22),
							"Apples"));
					add(
						new EventAttributeValue(
							_getExpectedDate(2021, Calendar.OCTOBER, 20),
							"Hair Dye"));
				}
			},
			_eventAttributeRepository.findDistinctAttributeValues(2001L, 5));
	}

	private Date _getExpectedDate(int year, int month, int date) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.set(Calendar.DATE, date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

	@Autowired
	private EventAttributeRepository _eventAttributeRepository;

}