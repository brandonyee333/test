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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.HolidayEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing HolidayEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayEntry
 * @generated
 */
public class HolidayEntryCacheModel implements CacheModel<HolidayEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{holidayEntryId=");
		sb.append(holidayEntryId);
		sb.append(", holidayCalendarId=");
		sb.append(holidayCalendarId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", repeatYearly=");
		sb.append(repeatYearly);
		sb.append("}");

		return sb.toString();
	}

	public HolidayEntry toEntityModel() {
		HolidayEntryImpl holidayEntryImpl = new HolidayEntryImpl();

		holidayEntryImpl.setHolidayEntryId(holidayEntryId);
		holidayEntryImpl.setHolidayCalendarId(holidayCalendarId);

		if (name == null) {
			holidayEntryImpl.setName(StringPool.BLANK);
		}
		else {
			holidayEntryImpl.setName(name);
		}

		if (description == null) {
			holidayEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			holidayEntryImpl.setDescription(description);
		}

		if (startDate == Long.MIN_VALUE) {
			holidayEntryImpl.setStartDate(null);
		}
		else {
			holidayEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			holidayEntryImpl.setEndDate(null);
		}
		else {
			holidayEntryImpl.setEndDate(new Date(endDate));
		}

		holidayEntryImpl.setRepeatYearly(repeatYearly);

		holidayEntryImpl.resetOriginalValues();

		return holidayEntryImpl;
	}

	public long holidayEntryId;
	public long holidayCalendarId;
	public String name;
	public String description;
	public long startDate;
	public long endDate;
	public boolean repeatYearly;
}