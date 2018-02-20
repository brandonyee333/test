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
public class WatsonIncidentRelAuditSoap implements Serializable {
	public static WatsonIncidentRelAuditSoap toSoapModel(
		WatsonIncidentRelAudit model) {
		WatsonIncidentRelAuditSoap soapModel = new WatsonIncidentRelAuditSoap();

		soapModel.setWatsonIncidentRelAuditId(model.getWatsonIncidentRelAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonIncidentId1(model.getWatsonIncidentId1());
		soapModel.setWatsonIncidentId2(model.getWatsonIncidentId2());
		soapModel.setWatsonIncidentRelId(model.getWatsonIncidentRelId());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonIncidentRelAuditSoap[] toSoapModels(
		WatsonIncidentRelAudit[] models) {
		WatsonIncidentRelAuditSoap[] soapModels = new WatsonIncidentRelAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentRelAuditSoap[][] toSoapModels(
		WatsonIncidentRelAudit[][] models) {
		WatsonIncidentRelAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonIncidentRelAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonIncidentRelAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentRelAuditSoap[] toSoapModels(
		List<WatsonIncidentRelAudit> models) {
		List<WatsonIncidentRelAuditSoap> soapModels = new ArrayList<WatsonIncidentRelAuditSoap>(models.size());

		for (WatsonIncidentRelAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonIncidentRelAuditSoap[soapModels.size()]);
	}

	public WatsonIncidentRelAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonIncidentRelAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonIncidentRelAuditId(pk);
	}

	public long getWatsonIncidentRelAuditId() {
		return _watsonIncidentRelAuditId;
	}

	public void setWatsonIncidentRelAuditId(long watsonIncidentRelAuditId) {
		_watsonIncidentRelAuditId = watsonIncidentRelAuditId;
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

	public long getWatsonIncidentId1() {
		return _watsonIncidentId1;
	}

	public void setWatsonIncidentId1(long watsonIncidentId1) {
		_watsonIncidentId1 = watsonIncidentId1;
	}

	public long getWatsonIncidentId2() {
		return _watsonIncidentId2;
	}

	public void setWatsonIncidentId2(long watsonIncidentId2) {
		_watsonIncidentId2 = watsonIncidentId2;
	}

	public long getWatsonIncidentRelId() {
		return _watsonIncidentRelId;
	}

	public void setWatsonIncidentRelId(long watsonIncidentRelId) {
		_watsonIncidentRelId = watsonIncidentRelId;
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

	private long _watsonIncidentRelAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonIncidentId1;
	private long _watsonIncidentId2;
	private long _watsonIncidentRelId;
	private String _type;
	private int _status;
}