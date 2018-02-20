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
public class WatsonVehicleAuditSoap implements Serializable {
	public static WatsonVehicleAuditSoap toSoapModel(WatsonVehicleAudit model) {
		WatsonVehicleAuditSoap soapModel = new WatsonVehicleAuditSoap();

		soapModel.setWatsonVehicleAuditId(model.getWatsonVehicleAuditId());
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
		soapModel.setWatsonVehicleId(model.getWatsonVehicleId());
		soapModel.setYear(model.getYear());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setLicensePlate(model.getLicensePlate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonVehicleAuditSoap[] toSoapModels(
		WatsonVehicleAudit[] models) {
		WatsonVehicleAuditSoap[] soapModels = new WatsonVehicleAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonVehicleAuditSoap[][] toSoapModels(
		WatsonVehicleAudit[][] models) {
		WatsonVehicleAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonVehicleAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonVehicleAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonVehicleAuditSoap[] toSoapModels(
		List<WatsonVehicleAudit> models) {
		List<WatsonVehicleAuditSoap> soapModels = new ArrayList<WatsonVehicleAuditSoap>(models.size());

		for (WatsonVehicleAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonVehicleAuditSoap[soapModels.size()]);
	}

	public WatsonVehicleAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonVehicleAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonVehicleAuditId(pk);
	}

	public long getWatsonVehicleAuditId() {
		return _watsonVehicleAuditId;
	}

	public void setWatsonVehicleAuditId(long watsonVehicleAuditId) {
		_watsonVehicleAuditId = watsonVehicleAuditId;
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

	public long getWatsonVehicleId() {
		return _watsonVehicleId;
	}

	public void setWatsonVehicleId(long watsonVehicleId) {
		_watsonVehicleId = watsonVehicleId;
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

	private long _watsonVehicleAuditId;
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
	private long _watsonVehicleId;
	private int _year;
	private String _description;
	private String _imagePayload;
	private String _licensePlate;
	private int _status;
}