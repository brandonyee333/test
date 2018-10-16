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

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.PartnerEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.PartnerEntryServiceSoap
 * @generated
 */
@ProviderType
public class PartnerEntrySoap implements Serializable {
	public static PartnerEntrySoap toSoapModel(PartnerEntry model) {
		PartnerEntrySoap soapModel = new PartnerEntrySoap();

		soapModel.setPartnerEntryId(model.getPartnerEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentPartnerEntryId(model.getParentPartnerEntryId());
		soapModel.setDossieraAccountKey(model.getDossieraAccountKey());
		soapModel.setCode(model.getCode());
		soapModel.setJiraProjectKey(model.getJiraProjectKey());
		soapModel.setNotes(model.getNotes());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static PartnerEntrySoap[] toSoapModels(PartnerEntry[] models) {
		PartnerEntrySoap[] soapModels = new PartnerEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PartnerEntrySoap[][] toSoapModels(PartnerEntry[][] models) {
		PartnerEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PartnerEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new PartnerEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PartnerEntrySoap[] toSoapModels(List<PartnerEntry> models) {
		List<PartnerEntrySoap> soapModels = new ArrayList<PartnerEntrySoap>(models.size());

		for (PartnerEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PartnerEntrySoap[soapModels.size()]);
	}

	public PartnerEntrySoap() {
	}

	public long getPrimaryKey() {
		return _partnerEntryId;
	}

	public void setPrimaryKey(long pk) {
		setPartnerEntryId(pk);
	}

	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;
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

	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getParentPartnerEntryId() {
		return _parentPartnerEntryId;
	}

	public void setParentPartnerEntryId(long parentPartnerEntryId) {
		_parentPartnerEntryId = parentPartnerEntryId;
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getJiraProjectKey() {
		return _jiraProjectKey;
	}

	public void setJiraProjectKey(String jiraProjectKey) {
		_jiraProjectKey = jiraProjectKey;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _partnerEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _parentPartnerEntryId;
	private String _dossieraAccountKey;
	private String _code;
	private String _jiraProjectKey;
	private String _notes;
	private int _status;
}