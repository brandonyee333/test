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

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.HolidayEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.HolidayEntryServiceSoap
 * @generated
 */
@ProviderType
public class HolidayEntrySoap implements Serializable {
	public static HolidayEntrySoap toSoapModel(HolidayEntry model) {
		HolidayEntrySoap soapModel = new HolidayEntrySoap();

		soapModel.setHolidayEntryId(model.getHolidayEntryId());
		soapModel.setHolidayCalendarId(model.getHolidayCalendarId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setRepeatYearly(model.getRepeatYearly());

		return soapModel;
	}

	public static HolidayEntrySoap[] toSoapModels(HolidayEntry[] models) {
		HolidayEntrySoap[] soapModels = new HolidayEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HolidayEntrySoap[][] toSoapModels(HolidayEntry[][] models) {
		HolidayEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HolidayEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HolidayEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HolidayEntrySoap[] toSoapModels(List<HolidayEntry> models) {
		List<HolidayEntrySoap> soapModels = new ArrayList<HolidayEntrySoap>(models.size());

		for (HolidayEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HolidayEntrySoap[soapModels.size()]);
	}

	public HolidayEntrySoap() {
	}

	public long getPrimaryKey() {
		return _holidayEntryId;
	}

	public void setPrimaryKey(long pk) {
		setHolidayEntryId(pk);
	}

	public long getHolidayEntryId() {
		return _holidayEntryId;
	}

	public void setHolidayEntryId(long holidayEntryId) {
		_holidayEntryId = holidayEntryId;
	}

	public long getHolidayCalendarId() {
		return _holidayCalendarId;
	}

	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendarId = holidayCalendarId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public boolean getRepeatYearly() {
		return _repeatYearly;
	}

	public boolean isRepeatYearly() {
		return _repeatYearly;
	}

	public void setRepeatYearly(boolean repeatYearly) {
		_repeatYearly = repeatYearly;
	}

	private long _holidayEntryId;
	private long _holidayCalendarId;
	private String _name;
	private String _description;
	private Date _startDate;
	private Date _endDate;
	private boolean _repeatYearly;
}