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
public class WatsonRelationshipAuditSoap implements Serializable {
	public static WatsonRelationshipAuditSoap toSoapModel(
		WatsonRelationshipAudit model) {
		WatsonRelationshipAuditSoap soapModel = new WatsonRelationshipAuditSoap();

		soapModel.setWatsonRelationshipAuditId(model.getWatsonRelationshipAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setWatsonRelationshipId(model.getWatsonRelationshipId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setClassNameId1(model.getClassNameId1());
		soapModel.setClassPK1(model.getClassPK1());
		soapModel.setClassNameId2(model.getClassNameId2());
		soapModel.setClassPK2(model.getClassPK2());
		soapModel.setDescription(model.getDescription());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonRelationshipAuditSoap[] toSoapModels(
		WatsonRelationshipAudit[] models) {
		WatsonRelationshipAuditSoap[] soapModels = new WatsonRelationshipAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonRelationshipAuditSoap[][] toSoapModels(
		WatsonRelationshipAudit[][] models) {
		WatsonRelationshipAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonRelationshipAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonRelationshipAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonRelationshipAuditSoap[] toSoapModels(
		List<WatsonRelationshipAudit> models) {
		List<WatsonRelationshipAuditSoap> soapModels = new ArrayList<WatsonRelationshipAuditSoap>(models.size());

		for (WatsonRelationshipAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonRelationshipAuditSoap[soapModels.size()]);
	}

	public WatsonRelationshipAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonRelationshipAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonRelationshipAuditId(pk);
	}

	public long getWatsonRelationshipAuditId() {
		return _watsonRelationshipAuditId;
	}

	public void setWatsonRelationshipAuditId(long watsonRelationshipAuditId) {
		_watsonRelationshipAuditId = watsonRelationshipAuditId;
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

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
	}

	public long getWatsonRelationshipId() {
		return _watsonRelationshipId;
	}

	public void setWatsonRelationshipId(long watsonRelationshipId) {
		_watsonRelationshipId = watsonRelationshipId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getClassNameId1() {
		return _classNameId1;
	}

	public void setClassNameId1(long classNameId1) {
		_classNameId1 = classNameId1;
	}

	public long getClassPK1() {
		return _classPK1;
	}

	public void setClassPK1(long classPK1) {
		_classPK1 = classPK1;
	}

	public long getClassNameId2() {
		return _classNameId2;
	}

	public void setClassNameId2(long classNameId2) {
		_classNameId2 = classNameId2;
	}

	public long getClassPK2() {
		return _classPK2;
	}

	public void setClassPK2(long classPK2) {
		_classPK2 = classPK2;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonRelationshipAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonIncidentId;
	private long _watsonRelationshipId;
	private long _typeWatsonListTypeId;
	private long _classNameId1;
	private long _classPK1;
	private long _classNameId2;
	private long _classPK2;
	private String _description;
	private int _status;
}