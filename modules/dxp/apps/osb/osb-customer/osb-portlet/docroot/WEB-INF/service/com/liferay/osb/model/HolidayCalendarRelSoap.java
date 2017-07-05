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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.HolidayCalendarRelServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.HolidayCalendarRelServiceSoap
 * @generated
 */
@ProviderType
public class HolidayCalendarRelSoap implements Serializable {
	public static HolidayCalendarRelSoap toSoapModel(HolidayCalendarRel model) {
		HolidayCalendarRelSoap soapModel = new HolidayCalendarRelSoap();

		soapModel.setHolidayCalendarRelId(model.getHolidayCalendarRelId());
		soapModel.setHolidayCalendarId(model.getHolidayCalendarId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static HolidayCalendarRelSoap[] toSoapModels(
		HolidayCalendarRel[] models) {
		HolidayCalendarRelSoap[] soapModels = new HolidayCalendarRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HolidayCalendarRelSoap[][] toSoapModels(
		HolidayCalendarRel[][] models) {
		HolidayCalendarRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HolidayCalendarRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HolidayCalendarRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HolidayCalendarRelSoap[] toSoapModels(
		List<HolidayCalendarRel> models) {
		List<HolidayCalendarRelSoap> soapModels = new ArrayList<HolidayCalendarRelSoap>(models.size());

		for (HolidayCalendarRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HolidayCalendarRelSoap[soapModels.size()]);
	}

	public HolidayCalendarRelSoap() {
	}

	public long getPrimaryKey() {
		return _holidayCalendarRelId;
	}

	public void setPrimaryKey(long pk) {
		setHolidayCalendarRelId(pk);
	}

	public long getHolidayCalendarRelId() {
		return _holidayCalendarRelId;
	}

	public void setHolidayCalendarRelId(long holidayCalendarRelId) {
		_holidayCalendarRelId = holidayCalendarRelId;
	}

	public long getHolidayCalendarId() {
		return _holidayCalendarId;
	}

	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendarId = holidayCalendarId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _holidayCalendarRelId;
	private long _holidayCalendarId;
	private long _userId;
}