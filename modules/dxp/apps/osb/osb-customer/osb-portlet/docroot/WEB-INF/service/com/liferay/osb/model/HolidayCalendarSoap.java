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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.HolidayCalendarServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.HolidayCalendarServiceSoap
 * @generated
 */
public class HolidayCalendarSoap implements Serializable {
	public static HolidayCalendarSoap toSoapModel(HolidayCalendar model) {
		HolidayCalendarSoap soapModel = new HolidayCalendarSoap();

		soapModel.setHolidayCalendarId(model.getHolidayCalendarId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static HolidayCalendarSoap[] toSoapModels(HolidayCalendar[] models) {
		HolidayCalendarSoap[] soapModels = new HolidayCalendarSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HolidayCalendarSoap[][] toSoapModels(
		HolidayCalendar[][] models) {
		HolidayCalendarSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HolidayCalendarSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HolidayCalendarSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HolidayCalendarSoap[] toSoapModels(
		List<HolidayCalendar> models) {
		List<HolidayCalendarSoap> soapModels = new ArrayList<HolidayCalendarSoap>(models.size());

		for (HolidayCalendar model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HolidayCalendarSoap[soapModels.size()]);
	}

	public HolidayCalendarSoap() {
	}

	public long getPrimaryKey() {
		return _holidayCalendarId;
	}

	public void setPrimaryKey(long pk) {
		setHolidayCalendarId(pk);
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

	private long _holidayCalendarId;
	private String _name;
	private String _description;
}