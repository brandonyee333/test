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

import com.liferay.osb.model.HolidayCalendar;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing HolidayCalendar in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendar
 * @generated
 */
public class HolidayCalendarCacheModel implements CacheModel<HolidayCalendar>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{holidayCalendarId=");
		sb.append(holidayCalendarId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public HolidayCalendar toEntityModel() {
		HolidayCalendarImpl holidayCalendarImpl = new HolidayCalendarImpl();

		holidayCalendarImpl.setHolidayCalendarId(holidayCalendarId);

		if (name == null) {
			holidayCalendarImpl.setName(StringPool.BLANK);
		}
		else {
			holidayCalendarImpl.setName(name);
		}

		if (description == null) {
			holidayCalendarImpl.setDescription(StringPool.BLANK);
		}
		else {
			holidayCalendarImpl.setDescription(description);
		}

		holidayCalendarImpl.resetOriginalValues();

		return holidayCalendarImpl;
	}

	public long holidayCalendarId;
	public String name;
	public String description;
}