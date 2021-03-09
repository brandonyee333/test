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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class DateUtil {

	public static final long DAY = DateUtil.HOUR * 24;

	public static final long HOUR = DateUtil.MINUTE * 60;

	public static final long MINUTE = DateUtil.SECOND * 60;

	public static final long MONTH = DAY * 30;

	public static final String PATTERN_ISO_8601 =
		"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final long SECOND = 1000;

	public static final long WEEK = DAY * 7;

	public static final long YEAR = DAY * 365;

	public static String addDays(String dateString, int days) throws Exception {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(toUTCDate(dateString));

		calendar.add(Calendar.DATE, days);

		return toUTCString(calendar.getTime());
	}

	public static String addHours(String dateString, int hours)
		throws Exception {

		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(toUTCDate(dateString));

		calendar.add(Calendar.HOUR, hours);

		return toUTCString(calendar.getTime());
	}

	public static String addMonths(String dateString, int months)
		throws Exception {

		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(toUTCDate(dateString));

		calendar.add(Calendar.MONTH, months);

		return toUTCString(calendar.getTime());
	}

	public static LocalDate fromUTC(
		LocalDateTime localDateTime, ZoneId zoneId) {

		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);

		zonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);

		return zonedDateTime.toLocalDate();
	}

	public static int getDeltaDays(Date date1, Date date2) {
		long millisecondsBetween = getDeltaMilliseconds(date1, date2);

		return (int)(millisecondsBetween / DAY);
	}

	public static int getDeltaDays(String dateString) throws Exception {
		return getDeltaDays(newDayDateString(dateString), newDayDateString());
	}

	public static int getDeltaDays(String dateString1, String dateString2)
		throws Exception {

		long millisecondsBetween = getDeltaMilliseconds(
			newDayDateString(dateString1), newDayDateString(dateString2));

		return (int)(millisecondsBetween / DAY);
	}

	public static long getDeltaMilliseconds(Date date1, Date date2) {
		return date2.getTime() - date1.getTime();
	}

	public static long getDeltaMilliseconds(
			String dateString1, String dateString2)
		throws Exception {

		Date date1 = toUTCDate(dateString1);
		Date date2 = toUTCDate(dateString2);

		return date2.getTime() - date1.getTime();
	}

	public static int getDeltaMonths(
		LocalDate localDate1, LocalDate localDate2) {

		int deltaMonths = 0;

		int dayOfMonth = localDate1.getDayOfMonth();

		if (dayOfMonth != 1) {
			localDate1 = localDate1.withDayOfMonth(1);
		}

		while (!localDate1.isAfter(localDate2)) {
			localDate1 = localDate1.plusMonths(1);

			deltaMonths++;
		}

		return deltaMonths;
	}

	public static int getDeltaWeeks(
		LocalDate localDate1, LocalDate localDate2) {

		int deltaWeeks = 0;

		DayOfWeek dayOfWeek = localDate1.getDayOfWeek();

		if (dayOfWeek != DayOfWeek.SUNDAY) {
			localDate1 = localDate1.minusDays(dayOfWeek.getValue());
		}

		while (!localDate1.isAfter(localDate2)) {
			localDate1 = localDate1.plusWeeks(1);

			deltaWeeks++;
		}

		return deltaWeeks;
	}

	public static String minusMinutes(
		LocalDateTime localDateTime, int minutes) {

		LocalDateTime newLocalDateTime = localDateTime.minusMinutes(minutes);

		return newLocalDateTime.toString();
	}

	public static String newDateString() {
		return toUTCString(new Date());
	}

	public static Date newDayDate() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(new Date());

		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static Date newDayDate(String dateString) throws Exception {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(toUTCDate(dateString));

		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static String newDayDateString() {
		return toUTCString(newDayDate());
	}

	public static String newDayDateString(String dateString) throws Exception {
		return toUTCString(newDayDate(dateString));
	}

	public static LocalDateTime newDayLocalDateTime(ZoneId zoneId) {
		return LocalDateTime.of(LocalDate.now(zoneId), LocalTime.MIDNIGHT);
	}

	public static String newDayLocalDateTimeString(
		LocalDateTime localDateTime) {

		LocalDateTime newLocalDateTime = localDateTime.with(LocalTime.MIDNIGHT);

		return newLocalDateTime.toString();
	}

	public static String newDayLocalDateTimeString(ZoneId zoneId) {
		LocalDateTime newDayLocalDateTime = newDayLocalDateTime(zoneId);

		return newDayLocalDateTime.toString();
	}

	public static Date newEndOfDayDate(Date date) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	public static Date newEndOfDayDate(String dateString) throws Exception {
		return newEndOfDayDate(toUTCDate(dateString));
	}

	public static String newEndOfDayDateString(String dateString)
		throws Exception {

		return toUTCString(newEndOfDayDate(dateString));
	}

	public static Date newEndOfMonthDate(String dateString) throws Exception {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(toUTCDate(dateString));

		calendar.add(Calendar.MONTH, 1);

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.add(Calendar.DATE, -1);

		return newEndOfDayDate(calendar.getTime());
	}

	public static String newEndOfMonthDateString(String dateString)
		throws Exception {

		return toUTCString(newEndOfMonthDate(dateString));
	}

	public static Date newEpochDate() {
		return Date.from(Instant.EPOCH);
	}

	public static String newEpochDateString() {
		return toUTCString(newEpochDate());
	}

	public static Date newMonthDate() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(new Date());

		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static String newMonthDateString() {
		return toUTCString(newMonthDate());
	}

	public static String newUTCDateString() {
		return toUTCString(new Date());
	}

	public static LocalDate toLocalDate(long date, ZoneId zoneId) {
		Instant instant = Instant.ofEpochMilli(date);

		ZonedDateTime zonedDateTime = instant.atZone(zoneId);

		return zonedDateTime.toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
		Instant instant = date.toInstant();

		ZonedDateTime zonedDateTime = instant.atZone(zoneId);

		return zonedDateTime.toLocalDateTime();
	}

	public static String toString(Date date) {
		DateFormat dateFormat = _newSimpleDateFormat();

		return dateFormat.format(date);
	}

	public static String toString(String dateString) {
		DateFormat dateFormat = _newSimpleDateFormat();

		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dateFormat.format(new Date(Long.parseLong(dateString)));
	}

	public static Date toUTCDate(LocalDateTime localDateTime) {
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);

		return Date.from(zonedDateTime.toInstant());
	}

	public static Date toUTCDate(String dateString) throws ParseException {
		DateFormat dateFormat = _newSimpleDateFormat();

		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dateFormat.parse(dateString);
	}

	public static String toUTCString(Date date) {
		DateFormat dateFormat = _newSimpleDateFormat();

		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dateFormat.format(date);
	}

	public static String toUTCString(LocalDateTime localDateTime) {
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);

		return zonedDateTime.format(_newDateTimeFormatter());
	}

	private static DateTimeFormatter _newDateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	}

	private static DateFormat _newSimpleDateFormat() {
		return new SimpleDateFormat(PATTERN_ISO_8601);
	}

}