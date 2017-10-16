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
public class WatsonResourceSoap implements Serializable {
	public static WatsonResourceSoap toSoapModel(WatsonResource model) {
		WatsonResourceSoap soapModel = new WatsonResourceSoap();

		soapModel.setWatsonResourceId(model.getWatsonResourceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOriginalWatsonResourceId(model.getOriginalWatsonResourceId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonResourceSoap[] toSoapModels(WatsonResource[] models) {
		WatsonResourceSoap[] soapModels = new WatsonResourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonResourceSoap[][] toSoapModels(WatsonResource[][] models) {
		WatsonResourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonResourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonResourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonResourceSoap[] toSoapModels(List<WatsonResource> models) {
		List<WatsonResourceSoap> soapModels = new ArrayList<WatsonResourceSoap>(models.size());

		for (WatsonResource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonResourceSoap[soapModels.size()]);
	}

	public WatsonResourceSoap() {
	}

	public long getPrimaryKey() {
		return _watsonResourceId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonResourceId(pk);
	}

	public long getWatsonResourceId() {
		return _watsonResourceId;
	}

	public void setWatsonResourceId(long watsonResourceId) {
		_watsonResourceId = watsonResourceId;
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

	public long getOriginalWatsonResourceId() {
		return _originalWatsonResourceId;
	}

	public void setOriginalWatsonResourceId(long originalWatsonResourceId) {
		_originalWatsonResourceId = originalWatsonResourceId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
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

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonResourceId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _originalWatsonResourceId;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _name;
	private String _description;
	private String _imagePayload;
	private int _status;
}