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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.DuplicateHolidayEntryException;
import com.liferay.osb.exception.HolidayEntryDateException;
import com.liferay.osb.exception.HolidayEntryStartDateLaterThanEndDateException;
import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.model.impl.HolidayEntryImpl;
import com.liferay.osb.service.base.HolidayEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Brent Krone-Schmidt
 */
public class HolidayEntryLocalServiceImpl
	extends HolidayEntryLocalServiceBaseImpl {

	public HolidayEntry addHolidayEntry(
			long holidayCalendarId, String name, String description,
			Date startDate, Date endDate, boolean repeatYearly)
		throws PortalException, SystemException {

		validate(holidayCalendarId, name, description, startDate, endDate);

		long holidayEntryId = counterLocalService.increment();

		HolidayEntry holidayEntry = holidayEntryPersistence.create(
			holidayEntryId);

		holidayEntry.setHolidayCalendarId(holidayCalendarId);
		holidayEntry.setName(name);
		holidayEntry.setDescription(description);
		holidayEntry.setStartDate(startDate);
		holidayEntry.setEndDate(endDate);
		holidayEntry.setRepeatYearly(repeatYearly);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		holidayEntryPersistence.update(holidayEntry, serviceContext);

		return holidayEntry;
	}

	public List<HolidayEntry> getHolidayEntries(long holidayCalendarId)
		throws SystemException {

		return holidayEntryPersistence.findByHolidayCalendar(holidayCalendarId);
	}

	public List<HolidayEntry> getHolidayEntriesBetween(
			long userId, Date startDate, Date endDate, TimeZone timeZone)
		throws SystemException {

		List<HolidayEntry> holidayEntries = holidayEntryFinder.findByU_RY_SD_ED(
			userId, false, startDate, endDate);

		holidayEntries.addAll(
			getCurrentRepeatYearlyHolidayEntries(
				userId, startDate, endDate, timeZone));

		return holidayEntries;
	}

	public int getHolidayEntriesCount(long userId, Date date)
		throws SystemException {

		List<HolidayEntry> holidayEntries = getHolidayEntriesBetween(
			userId, date, date, null);

		return holidayEntries.size();
	}

	public HolidayEntry updateHolidayEntry(
			long holidayEntryId, long holidayCalendarId, String name,
			String description, Date startDate, Date endDate,
			boolean repeatYearly)
		throws PortalException, SystemException {

		validate(holidayCalendarId, name, description, startDate, endDate);

		HolidayEntry holidayEntry = holidayEntryPersistence.findByPrimaryKey(
			holidayEntryId);

		holidayEntry.setHolidayCalendarId(holidayCalendarId);
		holidayEntry.setName(name);
		holidayEntry.setDescription(description);
		holidayEntry.setStartDate(startDate);
		holidayEntry.setEndDate(endDate);
		holidayEntry.setRepeatYearly(repeatYearly);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		holidayEntryPersistence.update(holidayEntry, serviceContext);

		return holidayEntry;
	}

	protected List<HolidayEntry> getCurrentRepeatYearlyHolidayEntries(
			long userId, Date startDate, Date endDate, TimeZone timeZone)
		throws SystemException {

		if (startDate.after(endDate)) {
			return Collections.EMPTY_LIST;
		}

		Calendar startCal = Calendar.getInstance();

		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();

		endCal.setTime(endDate);

		int timeZoneOffset = 0;

		if (timeZone != null) {
			timeZoneOffset = timeZone.getRawOffset();
		}

		List<HolidayEntry> currentHolidayEntries =
			new ArrayList<HolidayEntry>();

		List<HolidayEntry> holidayEntries = holidayEntryFinder.findByU_RY(
			userId, true);

		for (HolidayEntry holidayEntry : holidayEntries) {
			Calendar holidayStartCal = Calendar.getInstance();

			holidayStartCal.setTime(holidayEntry.getStartDate());

			holidayStartCal.add(Calendar.MILLISECOND, timeZoneOffset);

			Calendar holidayEndCal = Calendar.getInstance();

			holidayEndCal.setTime(holidayEntry.getEndDate());

			holidayEndCal.add(Calendar.MILLISECOND, timeZoneOffset);

			int holidayStartYear = holidayStartCal.get(Calendar.YEAR);

			if (holidayStartYear != startCal.get(Calendar.YEAR)) {
				holidayStartCal.add(
					Calendar.YEAR,
					startCal.get(Calendar.YEAR) - holidayStartYear);
				holidayEndCal.add(
					Calendar.YEAR,
					startCal.get(Calendar.YEAR) - holidayStartYear);

				if (startCal.after(holidayEndCal)) {
					holidayStartCal.add(Calendar.YEAR, 1);
					holidayEndCal.add(Calendar.YEAR, 1);
				}
				else if (endCal.before(holidayStartCal)) {
					holidayStartCal.add(Calendar.YEAR, -1);
					holidayEndCal.add(Calendar.YEAR, -1);
				}
			}

			if (!holidayStartCal.after(endCal) &&
				!holidayEndCal.before(startCal)) {

				HolidayEntry currentHolidayEntry = new HolidayEntryImpl();

				currentHolidayEntry.setStartDate(holidayStartCal.getTime());
				currentHolidayEntry.setEndDate(holidayEndCal.getTime());

				currentHolidayEntries.add(currentHolidayEntry);
			}
		}

		return currentHolidayEntries;
	}

	protected void validate(
			long holidayCalendarId, String name, String description,
			Date startDate, Date endDate)
		throws PortalException, SystemException {

		holidayCalendarPersistence.findByPrimaryKey(holidayCalendarId);

		List<HolidayEntry> holidayEntries =
			holidayEntryPersistence.findByHolidayCalendar(holidayCalendarId);

		if ((startDate == null) || (endDate == null)) {
			throw new HolidayEntryDateException();
		}

		for (HolidayEntry holidayEntry : holidayEntries) {
			if (startDate.equals(holidayEntry.getStartDate()) &&
				endDate.equals(holidayEntry.getEndDate())) {

				throw new DuplicateHolidayEntryException();
			}
		}

		if (DateUtil.compareTo(startDate, endDate) > 0) {
			throw new HolidayEntryStartDateLaterThanEndDateException();
		}
	}

}