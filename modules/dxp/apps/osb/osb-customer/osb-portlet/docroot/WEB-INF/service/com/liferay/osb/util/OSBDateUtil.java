/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.model.SupportLabor;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.service.HolidayEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Amos Fong
 */
public class OSBDateUtil {

	public static final String[] DAY_NAMES = {
		"sunday", "monday", "tuesday", "wednesday", "thursday", "friday",
		"saturday"
	};

	public static final String[] SHORT_DAY_NAMES =
		{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

	public static Date addWeekdayTime(Date date, long time, TimeZone timeZone) {
		Calendar cal = Calendar.getInstance(timeZone, LocaleUtil.US);

		cal.setTime(date);
		cal = roundToWeekdayNext(cal);

		for (int i = 0; i < (time / Time.DAY); i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			}
			else if (dayOfWeek == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

		int remainder = (int)(time % Time.DAY);

		if (remainder > 0) {
			cal.add(Calendar.MILLISECOND, remainder);

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			}
			else if (dayOfWeek == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

		return cal.getTime();
	}

	public static long getHolidayTime(
			SupportWorker supportWorker, Date startDate, Date endDate)
		throws PortalException, SystemException {

		SupportLabor supportLabor = supportWorker.getSupportLabor();

		TimeZone timeZone = TimeZoneUtil.getTimeZone(
			supportLabor.getTimeZoneId());

		List<HolidayEntry> holidayEntries =
			HolidayEntryLocalServiceUtil.getHolidayEntriesBetween(
				supportWorker.getUserId(), startDate, endDate, timeZone);

		long holidayTime = 0;

		for (HolidayEntry holidayEntry : holidayEntries) {
			Date holidayStartDate = holidayEntry.getStartDate();
			Date holidayEndDate = holidayEntry.getEndDate();

			if (holidayStartDate.after(startDate)) {
				if (holidayEndDate.before(endDate)) {
					holidayTime +=
						holidayEndDate.getTime() - holidayStartDate.getTime();
				}
				else {
					holidayTime +=
						endDate.getTime() - holidayStartDate.getTime();
				}
			}
			else if (holidayStartDate.before(startDate)) {
				if (holidayEndDate.before(endDate)) {
					holidayTime +=
						holidayEndDate.getTime() - startDate.getTime();
				}
				else {
					holidayTime += endDate.getTime() - startDate.getTime();
				}
			}
		}

		return holidayTime;
	}

	public static long getWeekdayTimeBetween(
		Date startDate, Date endDate, TimeZone timeZone) {

		Calendar startCal = Calendar.getInstance(timeZone, LocaleUtil.US);

		startCal.setTime(startDate);

		startCal = roundToWeekdayNext(startCal);

		Calendar endCal = Calendar.getInstance(timeZone, LocaleUtil.US);

		endCal.setTime(endDate);

		endCal = roundToWeekdayNext(endCal);

		long diff = endCal.getTimeInMillis() - startCal.getTimeInMillis();

		long weekendCount = (diff / (7 * Time.DAY)) * 2;

		if (startCal.get(Calendar.DAY_OF_WEEK) >
				endCal.get(Calendar.DAY_OF_WEEK)) {

			weekendCount = weekendCount + 2;
		}

		return (diff - (weekendCount * Time.DAY));
	}

	public static Date subtractWeekdayTime(
		Date date, long time, TimeZone timeZone) {

		Calendar cal = Calendar.getInstance(timeZone, LocaleUtil.US);

		cal.setTime(date);
		cal = roundToWeekdayPrevious(cal);

		for (int i = 0; i < (time / Time.DAY); i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			else if (dayOfWeek == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, -2);
			}
		}

		int remainder = (int)(time % Time.DAY);

		if (remainder > 0) {
			cal.add(Calendar.MILLISECOND, -remainder);

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			else if (dayOfWeek == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, -2);
			}
		}

		return cal.getTime();
	}

	protected static Calendar roundToWeekdayNext(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		if ((dayOfWeek == Calendar.SATURDAY) ||
			(dayOfWeek == Calendar.SUNDAY)) {

			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			if (dayOfWeek == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			}
			else if (dayOfWeek == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

		return cal;
	}

	protected static Calendar roundToWeekdayPrevious(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		if ((dayOfWeek == Calendar.SATURDAY) ||
			(dayOfWeek == Calendar.SUNDAY)) {

			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);

			if (dayOfWeek == Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			else if (dayOfWeek == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, -2);
			}
		}

		return cal;
	}

}