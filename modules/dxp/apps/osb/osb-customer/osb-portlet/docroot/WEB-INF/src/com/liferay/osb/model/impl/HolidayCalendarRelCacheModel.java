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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.HolidayCalendarRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HolidayCalendarRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRel
 * @generated
 */
@ProviderType
public class HolidayCalendarRelCacheModel implements CacheModel<HolidayCalendarRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayCalendarRelCacheModel)) {
			return false;
		}

		HolidayCalendarRelCacheModel holidayCalendarRelCacheModel = (HolidayCalendarRelCacheModel)obj;

		if (holidayCalendarRelId == holidayCalendarRelCacheModel.holidayCalendarRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, holidayCalendarRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{holidayCalendarRelId=");
		sb.append(holidayCalendarRelId);
		sb.append(", holidayCalendarId=");
		sb.append(holidayCalendarId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HolidayCalendarRel toEntityModel() {
		HolidayCalendarRelImpl holidayCalendarRelImpl = new HolidayCalendarRelImpl();

		holidayCalendarRelImpl.setHolidayCalendarRelId(holidayCalendarRelId);
		holidayCalendarRelImpl.setHolidayCalendarId(holidayCalendarId);
		holidayCalendarRelImpl.setUserId(userId);

		holidayCalendarRelImpl.resetOriginalValues();

		return holidayCalendarRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		holidayCalendarRelId = objectInput.readLong();

		holidayCalendarId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(holidayCalendarRelId);

		objectOutput.writeLong(holidayCalendarId);

		objectOutput.writeLong(userId);
	}

	public long holidayCalendarRelId;
	public long holidayCalendarId;
	public long userId;
}