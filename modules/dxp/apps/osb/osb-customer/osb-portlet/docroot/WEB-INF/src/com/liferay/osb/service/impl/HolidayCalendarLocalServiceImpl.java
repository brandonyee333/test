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

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.HolidayCalendarNameException;
import com.liferay.osb.model.HolidayCalendar;
import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.service.base.HolidayCalendarLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Brent Krone-Schmidt
 */
public class HolidayCalendarLocalServiceImpl
	extends HolidayCalendarLocalServiceBaseImpl {

	public HolidayCalendar addHolidayCalendar(
			String name, String description, List<HolidayEntry> holidayEntries)
		throws PortalException, SystemException {

		// Holiday calendar

		validate(name);

		long holidayCalendarId = counterLocalService.increment();

		HolidayCalendar holidayCalendar = holidayCalendarPersistence.create(
			holidayCalendarId);

		holidayCalendar.setName(name);
		holidayCalendar.setDescription(description);

		holidayCalendarPersistence.update(holidayCalendar, false);

		// Holiday entries

		setHolidayEntries(holidayCalendarId, holidayEntries);

		return holidayCalendar;
	}

	@Override
	public HolidayCalendar deleteHolidayCalendar(long holidayCalendarId)
		throws PortalException, SystemException {

		// Holiday calendar

		HolidayCalendar holidayCalendar = holidayCalendarPersistence.remove(
			holidayCalendarId);

		// Holiday calendar relations

		holidayCalendarRelLocalService.deleteHolidayCalendarRels(
			holidayCalendarId);

		// Holiday entries

		List<HolidayEntry> holidayEntries =
			holidayEntryPersistence.findByHolidayCalendar(holidayCalendarId);

		for (HolidayEntry holidayEntry : holidayEntries) {
			holidayEntryLocalService.deleteHolidayEntry(holidayEntry);
		}

		return holidayCalendar;
	}

	public HolidayCalendar updateHolidayCalendar(
			long holidayCalendarId, String name, String description,
			List<HolidayEntry> holidayEntries)
		throws PortalException, SystemException {

		validate(name);

		// Holiday calendar

		HolidayCalendar holidayCalendar =
			holidayCalendarPersistence.findByPrimaryKey(holidayCalendarId);

		holidayCalendar.setName(name);
		holidayCalendar.setDescription(description);

		holidayCalendarPersistence.update(holidayCalendar, false);

		// Holiday entries

		setHolidayEntries(holidayCalendarId, holidayEntries);

		return holidayCalendar;
	}

	protected void setHolidayEntries(
			long holidayCalendarId, List<HolidayEntry> holidayEntries)
		throws PortalException, SystemException {

		Set<Long> holidayEntryIds = new HashSet<Long>();

		for (HolidayEntry holidayEntry : holidayEntries) {
			long holidayEntryId = holidayEntry.getHolidayEntryId();

			if (holidayEntryId <= 0) {
				holidayEntry = holidayEntryLocalService.addHolidayEntry(
					holidayCalendarId, holidayEntry.getName(),
					holidayEntry.getDescription(), holidayEntry.getStartDate(),
					holidayEntry.getEndDate(), holidayEntry.getRepeatYearly());

				holidayEntryId = holidayEntry.getHolidayEntryId();
			}
			else {
				holidayEntryLocalService.updateHolidayEntry(
					holidayEntry.getHolidayEntryId(), holidayCalendarId,
					holidayEntry.getName(), holidayEntry.getDescription(),
					holidayEntry.getStartDate(), holidayEntry.getEndDate(),
					holidayEntry.getRepeatYearly());
			}

			holidayEntryIds.add(holidayEntryId);
		}

		holidayEntries = holidayEntryLocalService.getHolidayEntries(
			holidayCalendarId);

		for (HolidayEntry holidayEntry : holidayEntries) {
			if (!holidayEntryIds.contains(holidayEntry.getHolidayEntryId())) {
				holidayEntryLocalService.deleteHolidayEntry(
					holidayEntry.getHolidayEntryId());
			}
		}
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new HolidayCalendarNameException();
		}
	}

}