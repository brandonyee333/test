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
public class WatsonIncidentAuditSoap implements Serializable {
	public static WatsonIncidentAuditSoap toSoapModel(WatsonIncidentAudit model) {
		WatsonIncidentAuditSoap soapModel = new WatsonIncidentAuditSoap();

		soapModel.setWatsonIncidentAuditId(model.getWatsonIncidentAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setExternalCaseWatsonListTypeId(model.getExternalCaseWatsonListTypeId());
		soapModel.setSourceWatsonListTypeId(model.getSourceWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setSubtypeWatsonListTypeId(model.getSubtypeWatsonListTypeId());
		soapModel.setAudienceAdultCount(model.getAudienceAdultCount());
		soapModel.setAudienceChildCount(model.getAudienceChildCount());
		soapModel.setVictimAdultCount(model.getVictimAdultCount());
		soapModel.setVictimChildCount(model.getVictimChildCount());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setExternalCaseId(model.getExternalCaseId());
		soapModel.setReportDate(model.getReportDate());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setExpenses(model.getExpenses());
		soapModel.setIncidentStatus(model.getIncidentStatus());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonIncidentAuditSoap[] toSoapModels(
		WatsonIncidentAudit[] models) {
		WatsonIncidentAuditSoap[] soapModels = new WatsonIncidentAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentAuditSoap[][] toSoapModels(
		WatsonIncidentAudit[][] models) {
		WatsonIncidentAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonIncidentAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonIncidentAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentAuditSoap[] toSoapModels(
		List<WatsonIncidentAudit> models) {
		List<WatsonIncidentAuditSoap> soapModels = new ArrayList<WatsonIncidentAuditSoap>(models.size());

		for (WatsonIncidentAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonIncidentAuditSoap[soapModels.size()]);
	}

	public WatsonIncidentAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonIncidentAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonIncidentAuditId(pk);
	}

	public long getWatsonIncidentAuditId() {
		return _watsonIncidentAuditId;
	}

	public void setWatsonIncidentAuditId(long watsonIncidentAuditId) {
		_watsonIncidentAuditId = watsonIncidentAuditId;
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

	public long getExternalCaseWatsonListTypeId() {
		return _externalCaseWatsonListTypeId;
	}

	public void setExternalCaseWatsonListTypeId(
		long externalCaseWatsonListTypeId) {
		_externalCaseWatsonListTypeId = externalCaseWatsonListTypeId;
	}

	public long getSourceWatsonListTypeId() {
		return _sourceWatsonListTypeId;
	}

	public void setSourceWatsonListTypeId(long sourceWatsonListTypeId) {
		_sourceWatsonListTypeId = sourceWatsonListTypeId;
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

	public long getAudienceAdultCount() {
		return _audienceAdultCount;
	}

	public void setAudienceAdultCount(long audienceAdultCount) {
		_audienceAdultCount = audienceAdultCount;
	}

	public long getAudienceChildCount() {
		return _audienceChildCount;
	}

	public void setAudienceChildCount(long audienceChildCount) {
		_audienceChildCount = audienceChildCount;
	}

	public long getVictimAdultCount() {
		return _victimAdultCount;
	}

	public void setVictimAdultCount(long victimAdultCount) {
		_victimAdultCount = victimAdultCount;
	}

	public long getVictimChildCount() {
		return _victimChildCount;
	}

	public void setVictimChildCount(long victimChildCount) {
		_victimChildCount = victimChildCount;
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

	public String getExternalCaseId() {
		return _externalCaseId;
	}

	public void setExternalCaseId(String externalCaseId) {
		_externalCaseId = externalCaseId;
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

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public double getExpenses() {
		return _expenses;
	}

	public void setExpenses(double expenses) {
		_expenses = expenses;
	}

	public int getIncidentStatus() {
		return _incidentStatus;
	}

	public void setIncidentStatus(int incidentStatus) {
		_incidentStatus = incidentStatus;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonIncidentAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _externalCaseWatsonListTypeId;
	private long _sourceWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _subtypeWatsonListTypeId;
	private long _audienceAdultCount;
	private long _audienceChildCount;
	private long _victimAdultCount;
	private long _victimChildCount;
	private long _watsonIncidentId;
	private String _name;
	private String _description;
	private String _externalCaseId;
	private Date _reportDate;
	private Date _startDate;
	private Date _endDate;
	private double _expenses;
	private int _incidentStatus;
	private int _status;
}