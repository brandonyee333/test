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

package com.liferay.osb.asah.common.date;

import java.time.LocalDate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael Bowerman
 */
public class DateUtilTest {

	@Test
	public void testAddDaysForNegativeDays() throws Exception {
		Assert.assertEquals(
			"2013-08-03T14:02:46.984Z",
			DateUtil.addDays("2014-01-12T14:02:46.984Z", -162));
	}

	@Test
	public void testAddDaysForPositiveDays() throws Exception {
		Assert.assertEquals(
			"2021-03-09T15:15:16.012Z",
			DateUtil.addDays("2019-03-31T15:15:16.012Z", 709));
	}

	@Test
	public void testAddDaysForZeroDays() throws Exception {
		Assert.assertEquals(
			"2020-02-17T15:25:51.433Z",
			DateUtil.addDays("2020-02-17T15:25:51.433Z", 0));
	}

	@Test
	public void testAddMonthsClampsToValidDate() throws Exception {
		Assert.assertEquals(
			"2016-02-29T22:48:42.105Z",
			DateUtil.addMonths("2016-05-31T22:48:42.105Z", -3));
	}

	@Test
	public void testAddMonthsForNegativeMonths() throws Exception {
		Assert.assertEquals(
			"2016-02-26T00:47:58.314Z",
			DateUtil.addMonths("2019-08-26T00:47:58.314Z", -42));
	}

	@Test
	public void testAddMonthsForPositiveMonths() throws Exception {
		Assert.assertEquals(
			"2022-06-30T00:46:32.089Z",
			DateUtil.addMonths("2020-10-30T00:46:32.089Z", 20));
	}

	@Test
	public void testAddMonthsForZeroMonths() throws Exception {
		Assert.assertEquals(
			"2011-06-04T07:17:50.706Z",
			DateUtil.addMonths("2011-06-04T07:17:50.706Z", 0));
	}

	@Test
	public void testGetDeltaMillisecondsForEarlierDate() throws Exception {
		Assert.assertEquals(
			-243634878350L,
			DateUtil.getDeltaMilliseconds(
				"2025-04-10T21:43:45.843Z", "2017-07-22T01:22:27.493Z"));
	}

	@Test
	public void testGetDeltaMillisecondsForEqualDate() throws Exception {
		Assert.assertEquals(
			0,
			DateUtil.getDeltaMilliseconds(
				"2024-06-30T06:37:50.417Z", "2024-06-30T06:37:50.417Z"));
	}

	@Test
	public void testGetDeltaMillisecondsForLaterDate() throws Exception {
		Assert.assertEquals(
			122202552140L,
			DateUtil.getDeltaMilliseconds(
				"2017-01-07T22:19:40.162Z", "2020-11-22T07:28:52.302Z"));
	}

	@Test
	public void testGetDeltaMonths() {
		Assert.assertEquals(
			3,
			DateUtil.getDeltaMonths(
				LocalDate.of(2018, 4, 13), LocalDate.of(2018, 6, 2)));
		Assert.assertEquals(
			4,
			DateUtil.getDeltaMonths(
				LocalDate.of(2018, 12, 31), LocalDate.of(2019, 3, 1)));
	}

	@Test
	public void testGetDeltaWeeks() {
		Assert.assertEquals(
			13,
			DateUtil.getDeltaWeeks(
				LocalDate.of(2018, 9, 23), LocalDate.of(2018, 12, 21)));
		Assert.assertEquals(
			14,
			DateUtil.getDeltaWeeks(
				LocalDate.of(2019, 12, 3), LocalDate.of(2020, 3, 1)));
	}

	@Test
	public void testNewDayDateString() {
		String day = DateUtil.newDayDateString();

		Assert.assertTrue(
			"New day date string did not end with \"T00:00:00.000Z\"",
			day.endsWith("T00:00:00.000Z"));
	}

	@Test
	public void testNewEndOfDayDate() throws Exception {
		Assert.assertEquals(
			"2019-05-31T23:59:59.999Z",
			DateUtil.newEndOfDayDateString("2019-05-31T14:23:31.309Z"));
	}

	@Test
	public void testNewEndOfMonthDate() throws Exception {
		Assert.assertEquals(
			_toDate(2020, 12, 31, 23, 59, 59, 999),
			DateUtil.newEndOfMonthDate("2020-12-20T04:07:43.642Z"));
	}

	@Test
	public void testNewEndOfMonthDateHandlesLeapYears() throws Exception {
		Assert.assertEquals(
			_toDate(2024, 2, 29, 23, 59, 59, 999),
			DateUtil.newEndOfMonthDate("2024-02-08T20:53:29.533Z"));
	}

	@Test
	public void testNewEndOfMonthDateString() throws Exception {
		Assert.assertEquals(
			"2019-05-31T23:59:59.999Z",
			DateUtil.newEndOfMonthDateString("2019-05-26T07:23:31.309Z"));
	}

	@Test
	public void testNewEndOfMonthDateStringHandlesLeapYears() throws Exception {
		Assert.assertEquals(
			"2012-02-29T23:59:59.999Z",
			DateUtil.newEndOfMonthDateString("2012-02-13T08:35:53.954Z"));
	}

	@Test
	public void testNewEpochDate() {
		Assert.assertEquals(
			"1970-01-01T00:00:00.000Z",
			DateUtil.toUTCString(DateUtil.newEpochDate()));
	}

	@Test
	public void testNewEpochDateString() {
		Assert.assertEquals(
			"1970-01-01T00:00:00.000Z", DateUtil.newEpochDateString());
	}

	@Test
	public void testNewMonthDateString() {
		String month = DateUtil.newMonthDateString();

		Assert.assertTrue(
			"New day date string did not end with \"-01T00:00:00.000Z\"",
			month.endsWith("-01T00:00:00.000Z"));
	}

	@Test
	public void testNewUTCDateString() {
		String utcString = DateUtil.toUTCString(new Date());

		String utcDateString = DateUtil.newUTCDateString();

		Assert.assertTrue(
			"New UTC date string did not start with " +
				utcString.substring(0, utcString.indexOf("T")),
			utcDateString.startsWith(
				utcString.substring(0, utcString.indexOf("T"))));
	}

	@Test
	public void testToDate() throws Exception {
		Assert.assertEquals(
			_toDate(2018, 9, 15, 0, 40, 48, 502),
			DateUtil.toUTCDate("2018-09-15T00:40:48.502Z"));
	}

	@Test
	public void testToString() {
		Assert.assertEquals(
			"2013-10-20T03:04:09.360Z",
			DateUtil.toUTCString(_toDate(2013, 10, 20, 3, 4, 9, 360)));
	}

	private Date _toDate(
		int year, int month, int day, int hour, int minute, int second,
		int millisecond) {

		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.set(Calendar.AM_PM, hour / 12);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.HOUR, hour % 12);
		calendar.set(Calendar.MILLISECOND, millisecond);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

}