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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonVehicleSoap implements Serializable {
	public static WatsonVehicleSoap toSoapModel(WatsonVehicle model) {
		WatsonVehicleSoap soapModel = new WatsonVehicleSoap();

		soapModel.setWatsonVehicleId(model.getWatsonVehicleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setColorWatsonListTypeId(model.getColorWatsonListTypeId());
		soapModel.setMakeWatsonListTypeId(model.getMakeWatsonListTypeId());
		soapModel.setModelWatsonListTypeId(model.getModelWatsonListTypeId());
		soapModel.setOriginalWatsonVehicleId(model.getOriginalWatsonVehicleId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setYearWatsonListTypeId(model.getYearWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setYear(model.getYear());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setLicensePlate(model.getLicensePlate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonVehicleSoap[] toSoapModels(WatsonVehicle[] models) {
		WatsonVehicleSoap[] soapModels = new WatsonVehicleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonVehicleSoap[][] toSoapModels(WatsonVehicle[][] models) {
		WatsonVehicleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonVehicleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonVehicleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonVehicleSoap[] toSoapModels(List<WatsonVehicle> models) {
		List<WatsonVehicleSoap> soapModels = new ArrayList<WatsonVehicleSoap>(models.size());

		for (WatsonVehicle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonVehicleSoap[soapModels.size()]);
	}

	public WatsonVehicleSoap() {
	}

	public long getPrimaryKey() {
		return _watsonVehicleId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonVehicleId(pk);
	}

	public long getWatsonVehicleId() {
		return _watsonVehicleId;
	}

	public void setWatsonVehicleId(long watsonVehicleId) {
		_watsonVehicleId = watsonVehicleId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getColorWatsonListTypeId() {
		return _colorWatsonListTypeId;
	}

	public void setColorWatsonListTypeId(long colorWatsonListTypeId) {
		_colorWatsonListTypeId = colorWatsonListTypeId;
	}

	public long getMakeWatsonListTypeId() {
		return _makeWatsonListTypeId;
	}

	public void setMakeWatsonListTypeId(long makeWatsonListTypeId) {
		_makeWatsonListTypeId = makeWatsonListTypeId;
	}

	public long getModelWatsonListTypeId() {
		return _modelWatsonListTypeId;
	}

	public void setModelWatsonListTypeId(long modelWatsonListTypeId) {
		_modelWatsonListTypeId = modelWatsonListTypeId;
	}

	public long getOriginalWatsonVehicleId() {
		return _originalWatsonVehicleId;
	}

	public void setOriginalWatsonVehicleId(long originalWatsonVehicleId) {
		_originalWatsonVehicleId = originalWatsonVehicleId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getYearWatsonListTypeId() {
		return _yearWatsonListTypeId;
	}

	public void setYearWatsonListTypeId(long yearWatsonListTypeId) {
		_yearWatsonListTypeId = yearWatsonListTypeId;
	}

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public String getLicensePlate() {
		return _licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		_licensePlate = licensePlate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonVehicleId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _colorWatsonListTypeId;
	private long _makeWatsonListTypeId;
	private long _modelWatsonListTypeId;
	private long _originalWatsonVehicleId;
	private long _typeWatsonListTypeId;
	private long _yearWatsonListTypeId;
	private long _watsonIncidentId;
	private int _year;
	private String _description;
	private String _imagePayload;
	private String _licensePlate;
	private int _status;
}