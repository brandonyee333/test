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
 * @author Eddie Olson
 * @generated
 */
@ProviderType
public class WatsonListTypeSoap implements Serializable {
	public static WatsonListTypeSoap toSoapModel(WatsonListType model) {
		WatsonListTypeSoap soapModel = new WatsonListTypeSoap();

		soapModel.setWatsonListTypeId(model.getWatsonListTypeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentWatsonListTypeId(model.getParentWatsonListTypeId());
		soapModel.setName(model.getName());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonListTypeSoap[] toSoapModels(WatsonListType[] models) {
		WatsonListTypeSoap[] soapModels = new WatsonListTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonListTypeSoap[][] toSoapModels(WatsonListType[][] models) {
		WatsonListTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonListTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonListTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonListTypeSoap[] toSoapModels(List<WatsonListType> models) {
		List<WatsonListTypeSoap> soapModels = new ArrayList<WatsonListTypeSoap>(models.size());

		for (WatsonListType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonListTypeSoap[soapModels.size()]);
	}

	public WatsonListTypeSoap() {
	}

	public long getPrimaryKey() {
		return _watsonListTypeId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonListTypeId(pk);
	}

	public long getWatsonListTypeId() {
		return _watsonListTypeId;
	}

	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeId = watsonListTypeId;
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

	public long getParentWatsonListTypeId() {
		return _parentWatsonListTypeId;
	}

	public void setParentWatsonListTypeId(long parentWatsonListTypeId) {
		_parentWatsonListTypeId = parentWatsonListTypeId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonListTypeId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentWatsonListTypeId;
	private String _name;
	private String _type;
	private int _status;
}