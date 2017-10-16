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
public class WatsonListTypeRelSoap implements Serializable {
	public static WatsonListTypeRelSoap toSoapModel(WatsonListTypeRel model) {
		WatsonListTypeRelSoap soapModel = new WatsonListTypeRelSoap();

		soapModel.setWatsonListTypeRelId(model.getWatsonListTypeRelId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonListTypeId(model.getWatsonListTypeId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setPrimary(model.getPrimary());
		soapModel.setValue(model.getValue());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonListTypeRelSoap[] toSoapModels(
		WatsonListTypeRel[] models) {
		WatsonListTypeRelSoap[] soapModels = new WatsonListTypeRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonListTypeRelSoap[][] toSoapModels(
		WatsonListTypeRel[][] models) {
		WatsonListTypeRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonListTypeRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonListTypeRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonListTypeRelSoap[] toSoapModels(
		List<WatsonListTypeRel> models) {
		List<WatsonListTypeRelSoap> soapModels = new ArrayList<WatsonListTypeRelSoap>(models.size());

		for (WatsonListTypeRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonListTypeRelSoap[soapModels.size()]);
	}

	public WatsonListTypeRelSoap() {
	}

	public long getPrimaryKey() {
		return _watsonListTypeRelId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonListTypeRelId(pk);
	}

	public long getWatsonListTypeRelId() {
		return _watsonListTypeRelId;
	}

	public void setWatsonListTypeRelId(long watsonListTypeRelId) {
		_watsonListTypeRelId = watsonListTypeRelId;
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

	public long getWatsonListTypeId() {
		return _watsonListTypeId;
	}

	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeId = watsonListTypeId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public boolean getPrimary() {
		return _primary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public void setPrimary(boolean primary) {
		_primary = primary;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
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

	private long _watsonListTypeRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonListTypeId;
	private long _classNameId;
	private long _classPK;
	private boolean _primary;
	private String _value;
	private String _type;
	private int _status;
}