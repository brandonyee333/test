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
public class WatsonIncidentSoap implements Serializable {
	public static WatsonIncidentSoap toSoapModel(WatsonIncident model) {
		WatsonIncidentSoap soapModel = new WatsonIncidentSoap();

		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSourceWatsonListTypeId(model.getSourceWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setSubtypeWatsonListTypeId(model.getSubtypeWatsonListTypeId());
		soapModel.setAudienceKey(model.getAudienceKey());
		soapModel.setName(model.getName());
		soapModel.setExternalCaseId(model.getExternalCaseId());
		soapModel.setDescription(model.getDescription());
		soapModel.setReportDate(model.getReportDate());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setIncidentStatus(model.getIncidentStatus());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonIncidentSoap[] toSoapModels(WatsonIncident[] models) {
		WatsonIncidentSoap[] soapModels = new WatsonIncidentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentSoap[][] toSoapModels(WatsonIncident[][] models) {
		WatsonIncidentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonIncidentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonIncidentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentSoap[] toSoapModels(List<WatsonIncident> models) {
		List<WatsonIncidentSoap> soapModels = new ArrayList<WatsonIncidentSoap>(models.size());

		for (WatsonIncident model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonIncidentSoap[soapModels.size()]);
	}

	public WatsonIncidentSoap() {
	}

	public long getPrimaryKey() {
		return _watsonIncidentId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonIncidentId(pk);
	}

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
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

	public String getAudienceKey() {
		return _audienceKey;
	}

	public void setAudienceKey(String audienceKey) {
		_audienceKey = audienceKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getExternalCaseId() {
		return _externalCaseId;
	}

	public void setExternalCaseId(String externalCaseId) {
		_externalCaseId = externalCaseId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
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

	private long _watsonIncidentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _sourceWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _subtypeWatsonListTypeId;
	private String _audienceKey;
	private String _name;
	private String _externalCaseId;
	private String _description;
	private Date _reportDate;
	private Date _startDate;
	private Date _endDate;
	private int _incidentStatus;
	private int _status;
}