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
public class WatsonHistoryAuditSoap implements Serializable {
	public static WatsonHistoryAuditSoap toSoapModel(WatsonHistoryAudit model) {
		WatsonHistoryAuditSoap soapModel = new WatsonHistoryAuditSoap();

		soapModel.setWatsonHistoryAuditId(model.getWatsonHistoryAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonHistoryId(model.getWatsonHistoryId());
		soapModel.setWatsonParentId(model.getWatsonParentId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonHistoryAuditSoap[] toSoapModels(
		WatsonHistoryAudit[] models) {
		WatsonHistoryAuditSoap[] soapModels = new WatsonHistoryAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonHistoryAuditSoap[][] toSoapModels(
		WatsonHistoryAudit[][] models) {
		WatsonHistoryAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonHistoryAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonHistoryAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonHistoryAuditSoap[] toSoapModels(
		List<WatsonHistoryAudit> models) {
		List<WatsonHistoryAuditSoap> soapModels = new ArrayList<WatsonHistoryAuditSoap>(models.size());

		for (WatsonHistoryAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonHistoryAuditSoap[soapModels.size()]);
	}

	public WatsonHistoryAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonHistoryAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonHistoryAuditId(pk);
	}

	public long getWatsonHistoryAuditId() {
		return _watsonHistoryAuditId;
	}

	public void setWatsonHistoryAuditId(long watsonHistoryAuditId) {
		_watsonHistoryAuditId = watsonHistoryAuditId;
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

	public long getWatsonHistoryId() {
		return _watsonHistoryId;
	}

	public void setWatsonHistoryId(long watsonHistoryId) {
		_watsonHistoryId = watsonHistoryId;
	}

	public long getWatsonParentId() {
		return _watsonParentId;
	}

	public void setWatsonParentId(long watsonParentId) {
		_watsonParentId = watsonParentId;
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

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonHistoryAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonHistoryId;
	private long _watsonParentId;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private int _status;
}