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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportLaborServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.SupportLaborServiceSoap
 * @generated
 */
public class SupportLaborSoap implements Serializable {
	public static SupportLaborSoap toSoapModel(SupportLabor model) {
		SupportLaborSoap soapModel = new SupportLaborSoap();

		soapModel.setSupportLaborId(model.getSupportLaborId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setTimeZoneId(model.getTimeZoneId());
		soapModel.setSunOpen(model.getSunOpen());
		soapModel.setSunClose(model.getSunClose());
		soapModel.setMonOpen(model.getMonOpen());
		soapModel.setMonClose(model.getMonClose());
		soapModel.setTueOpen(model.getTueOpen());
		soapModel.setTueClose(model.getTueClose());
		soapModel.setWedOpen(model.getWedOpen());
		soapModel.setWedClose(model.getWedClose());
		soapModel.setThuOpen(model.getThuOpen());
		soapModel.setThuClose(model.getThuClose());
		soapModel.setFriOpen(model.getFriOpen());
		soapModel.setFriClose(model.getFriClose());
		soapModel.setSatOpen(model.getSatOpen());
		soapModel.setSatClose(model.getSatClose());

		return soapModel;
	}

	public static SupportLaborSoap[] toSoapModels(SupportLabor[] models) {
		SupportLaborSoap[] soapModels = new SupportLaborSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportLaborSoap[][] toSoapModels(SupportLabor[][] models) {
		SupportLaborSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportLaborSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportLaborSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportLaborSoap[] toSoapModels(List<SupportLabor> models) {
		List<SupportLaborSoap> soapModels = new ArrayList<SupportLaborSoap>(models.size());

		for (SupportLabor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportLaborSoap[soapModels.size()]);
	}

	public SupportLaborSoap() {
	}

	public long getPrimaryKey() {
		return _supportLaborId;
	}

	public void setPrimaryKey(long pk) {
		setSupportLaborId(pk);
	}

	public long getSupportLaborId() {
		return _supportLaborId;
	}

	public void setSupportLaborId(long supportLaborId) {
		_supportLaborId = supportLaborId;
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

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	public int getSunOpen() {
		return _sunOpen;
	}

	public void setSunOpen(int sunOpen) {
		_sunOpen = sunOpen;
	}

	public int getSunClose() {
		return _sunClose;
	}

	public void setSunClose(int sunClose) {
		_sunClose = sunClose;
	}

	public int getMonOpen() {
		return _monOpen;
	}

	public void setMonOpen(int monOpen) {
		_monOpen = monOpen;
	}

	public int getMonClose() {
		return _monClose;
	}

	public void setMonClose(int monClose) {
		_monClose = monClose;
	}

	public int getTueOpen() {
		return _tueOpen;
	}

	public void setTueOpen(int tueOpen) {
		_tueOpen = tueOpen;
	}

	public int getTueClose() {
		return _tueClose;
	}

	public void setTueClose(int tueClose) {
		_tueClose = tueClose;
	}

	public int getWedOpen() {
		return _wedOpen;
	}

	public void setWedOpen(int wedOpen) {
		_wedOpen = wedOpen;
	}

	public int getWedClose() {
		return _wedClose;
	}

	public void setWedClose(int wedClose) {
		_wedClose = wedClose;
	}

	public int getThuOpen() {
		return _thuOpen;
	}

	public void setThuOpen(int thuOpen) {
		_thuOpen = thuOpen;
	}

	public int getThuClose() {
		return _thuClose;
	}

	public void setThuClose(int thuClose) {
		_thuClose = thuClose;
	}

	public int getFriOpen() {
		return _friOpen;
	}

	public void setFriOpen(int friOpen) {
		_friOpen = friOpen;
	}

	public int getFriClose() {
		return _friClose;
	}

	public void setFriClose(int friClose) {
		_friClose = friClose;
	}

	public int getSatOpen() {
		return _satOpen;
	}

	public void setSatOpen(int satOpen) {
		_satOpen = satOpen;
	}

	public int getSatClose() {
		return _satClose;
	}

	public void setSatClose(int satClose) {
		_satClose = satClose;
	}

	private long _supportLaborId;
	private String _name;
	private String _description;
	private String _timeZoneId;
	private int _sunOpen;
	private int _sunClose;
	private int _monOpen;
	private int _monClose;
	private int _tueOpen;
	private int _tueClose;
	private int _wedOpen;
	private int _wedClose;
	private int _thuOpen;
	private int _thuClose;
	private int _friOpen;
	private int _friClose;
	private int _satOpen;
	private int _satClose;
}