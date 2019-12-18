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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountEntrySoap implements Serializable {
	public static AccountEntrySoap toSoapModel(AccountEntry model) {
		AccountEntrySoap soapModel = new AccountEntrySoap();

		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKoroneikiAccountKey(model.getKoroneikiAccountKey());
		soapModel.setDossieraAccountKey(model.getDossieraAccountKey());
		soapModel.setName(model.getName());
		soapModel.setCode(model.getCode());
		soapModel.setInstructions(model.getInstructions());
		soapModel.setActiveSupport(model.getActiveSupport());
		soapModel.setActiveTicketSupport(model.getActiveTicketSupport());
		soapModel.setLastZendeskAuditDate(model.getLastZendeskAuditDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static AccountEntrySoap[] toSoapModels(AccountEntry[] models) {
		AccountEntrySoap[] soapModels = new AccountEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountEntrySoap[][] toSoapModels(AccountEntry[][] models) {
		AccountEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountEntrySoap[] toSoapModels(List<AccountEntry> models) {
		List<AccountEntrySoap> soapModels = new ArrayList<AccountEntrySoap>(models.size());

		for (AccountEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountEntrySoap[soapModels.size()]);
	}

	public AccountEntrySoap() {
	}

	public long getPrimaryKey() {
		return _accountEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAccountEntryId(pk);
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
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

	public String getKoroneikiAccountKey() {
		return _koroneikiAccountKey;
	}

	public void setKoroneikiAccountKey(String koroneikiAccountKey) {
		_koroneikiAccountKey = koroneikiAccountKey;
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getInstructions() {
		return _instructions;
	}

	public void setInstructions(String instructions) {
		_instructions = instructions;
	}

	public boolean getActiveSupport() {
		return _activeSupport;
	}

	public boolean isActiveSupport() {
		return _activeSupport;
	}

	public void setActiveSupport(boolean activeSupport) {
		_activeSupport = activeSupport;
	}

	public boolean getActiveTicketSupport() {
		return _activeTicketSupport;
	}

	public boolean isActiveTicketSupport() {
		return _activeTicketSupport;
	}

	public void setActiveTicketSupport(boolean activeTicketSupport) {
		_activeTicketSupport = activeTicketSupport;
	}

	public Date getLastZendeskAuditDate() {
		return _lastZendeskAuditDate;
	}

	public void setLastZendeskAuditDate(Date lastZendeskAuditDate) {
		_lastZendeskAuditDate = lastZendeskAuditDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _accountEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private String _koroneikiAccountKey;
	private String _dossieraAccountKey;
	private String _name;
	private String _code;
	private String _instructions;
	private boolean _activeSupport;
	private boolean _activeTicketSupport;
	private Date _lastZendeskAuditDate;
	private int _status;
}