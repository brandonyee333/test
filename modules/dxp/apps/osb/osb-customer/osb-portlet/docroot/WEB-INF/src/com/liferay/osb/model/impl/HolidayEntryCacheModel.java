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

import com.liferay.osb.model.HolidayEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HolidayEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayEntry
 * @generated
 */
@ProviderType
public class HolidayEntryCacheModel implements CacheModel<HolidayEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayEntryCacheModel)) {
			return false;
		}

		HolidayEntryCacheModel holidayEntryCacheModel = (HolidayEntryCacheModel)obj;

		if (holidayEntryId == holidayEntryCacheModel.holidayEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, holidayEntryId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		holidayEntryId = objectInput.readLong();

		holidayCalendarId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		repeatYearly = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(holidayEntryId);

		objectOutput.writeLong(holidayCalendarId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeBoolean(repeatYearly);
	}

	public long holidayEntryId;
	public long holidayCalendarId;
	public String name;
	public String description;
	public long startDate;
	public long endDate;
	public boolean repeatYearly;
}