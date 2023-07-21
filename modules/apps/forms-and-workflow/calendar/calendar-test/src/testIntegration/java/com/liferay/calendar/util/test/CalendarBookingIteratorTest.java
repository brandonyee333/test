/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.impl.CalendarBookingImpl;
import com.liferay.calendar.util.CalendarBookingIterator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.text.ParseException;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class CalendarBookingIteratorTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testRecurrenceIsNull() throws ParseException {
		Calendar calendar = Calendar.getInstance();

		CalendarBooking calendarBooking = new MockCalendarBooking();

		calendarBooking.setStartTime(calendar.getTimeInMillis());
		calendarBooking.setRecurrence(null);

		CalendarBookingIterator calendarBookingIterator =
			new CalendarBookingIterator(calendarBooking);

		int count = 0;

		while (calendarBookingIterator.hasNext()) {
			calendarBookingIterator.next();

			count++;
		}

		Assert.assertEquals(1, count);
	}

	@Test
	public void testRecurrenceStartsMondayRepeatsMonday()
		throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		CalendarBooking calendarBooking = new MockCalendarBooking();

		calendarBooking.setStartTime(calendar.getTimeInMillis());
		calendarBooking.setRecurrence(
			"RRULE:FREQ=WEEKLY;COUNT=2;INTERVAL=1;BYDAY=MO");

		CalendarBookingIterator calendarBookingIterator =
			new CalendarBookingIterator(calendarBooking);

		int count = 0;

		while (calendarBookingIterator.hasNext()) {
			calendarBookingIterator.next();

			count++;
		}

		Assert.assertEquals(2, count);
	}

	@Test
	public void testRecurrenceStartsMondayRepeatsWednesday()
		throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		CalendarBooking calendarBooking = new MockCalendarBooking();

		calendarBooking.setStartTime(calendar.getTimeInMillis());
		calendarBooking.setRecurrence(
			"RRULE:FREQ=WEEKLY;COUNT=2;INTERVAL=1;BYDAY=WE");

		CalendarBookingIterator calendarBookingIterator =
			new CalendarBookingIterator(calendarBooking);

		int count = 0;

		while (calendarBookingIterator.hasNext()) {
			calendarBookingIterator.next();

			count++;
		}

		Assert.assertEquals(2, count);
	}

	protected class MockCalendarBooking extends CalendarBookingImpl {

		@Override
		public TimeZone getTimeZone() {
			return TimeZoneUtil.getTimeZone(StringPool.UTC);
		}

	}

}