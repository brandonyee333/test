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
public class WatsonActivityAuditSoap implements Serializable {
	public static WatsonActivityAuditSoap toSoapModel(WatsonActivityAudit model) {
		WatsonActivityAuditSoap soapModel = new WatsonActivityAuditSoap();

		soapModel.setWatsonActivityAuditId(model.getWatsonActivityAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonActivityId(model.getWatsonActivityId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setSubtypeWatsonListTypeId(model.getSubtypeWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setNarrative(model.getNarrative());
		soapModel.setReportDate(model.getReportDate());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonActivityAuditSoap[] toSoapModels(
		WatsonActivityAudit[] models) {
		WatsonActivityAuditSoap[] soapModels = new WatsonActivityAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonActivityAuditSoap[][] toSoapModels(
		WatsonActivityAudit[][] models) {
		WatsonActivityAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonActivityAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonActivityAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonActivityAuditSoap[] toSoapModels(
		List<WatsonActivityAudit> models) {
		List<WatsonActivityAuditSoap> soapModels = new ArrayList<WatsonActivityAuditSoap>(models.size());

		for (WatsonActivityAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonActivityAuditSoap[soapModels.size()]);
	}

	public WatsonActivityAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonActivityAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonActivityAuditId(pk);
	}

	public long getWatsonActivityAuditId() {
		return _watsonActivityAuditId;
	}

	public void setWatsonActivityAuditId(long watsonActivityAuditId) {
		_watsonActivityAuditId = watsonActivityAuditId;
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

	public long getWatsonActivityId() {
		return _watsonActivityId;
	}

	public void setWatsonActivityId(long watsonActivityId) {
		_watsonActivityId = watsonActivityId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getSubtypeWatsonListTypeId() {
		return _subtypeWatsonListTypeId;
	}

	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_subtypeWatsonListTypeId = subtypeWatsonListTypeId;
	}

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
	}

	public String getNarrative() {
		return _narrative;
	}

	public void setNarrative(String narrative) {
		_narrative = narrative;
	}

	public Date getReportDate() {
		return _reportDate;
	}

	public void setReportDate(Date reportDate) {
		_reportDate = reportDate;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonActivityAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonActivityId;
	private long _typeWatsonListTypeId;
	private long _subtypeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _narrative;
	private Date _reportDate;
	private Date _startDate;
	private int _status;
}