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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountCallServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountCallServiceSoap
 * @generated
 */
@ProviderType
public class AccountCallSoap implements Serializable {
	public static AccountCallSoap toSoapModel(AccountCall model) {
		AccountCallSoap soapModel = new AccountCallSoap();

		soapModel.setAccountCallId(model.getAccountCallId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setType(model.getType());
		soapModel.setCallDate(model.getCallDate());
		soapModel.setCallLength(model.getCallLength());
		soapModel.setSummary(model.getSummary());
		soapModel.setClientsPresent(model.getClientsPresent());
		soapModel.setNotes(model.getNotes());
		soapModel.setActionItems(model.getActionItems());

		return soapModel;
	}

	public static AccountCallSoap[] toSoapModels(AccountCall[] models) {
		AccountCallSoap[] soapModels = new AccountCallSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountCallSoap[][] toSoapModels(AccountCall[][] models) {
		AccountCallSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountCallSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountCallSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountCallSoap[] toSoapModels(List<AccountCall> models) {
		List<AccountCallSoap> soapModels = new ArrayList<AccountCallSoap>(models.size());

		for (AccountCall model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountCallSoap[soapModels.size()]);
	}

	public AccountCallSoap() {
	}

	public long getPrimaryKey() {
		return _accountCallId;
	}

	public void setPrimaryKey(long pk) {
		setAccountCallId(pk);
	}

	public long getAccountCallId() {
		return _accountCallId;
	}

	public void setAccountCallId(long accountCallId) {
		_accountCallId = accountCallId;
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

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public Date getCallDate() {
		return _callDate;
	}

	public void setCallDate(Date callDate) {
		_callDate = callDate;
	}

	public long getCallLength() {
		return _callLength;
	}

	public void setCallLength(long callLength) {
		_callLength = callLength;
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public String getClientsPresent() {
		return _clientsPresent;
	}

	public void setClientsPresent(String clientsPresent) {
		_clientsPresent = clientsPresent;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	public String getActionItems() {
		return _actionItems;
	}

	public void setActionItems(String actionItems) {
		_actionItems = actionItems;
	}

	private long _accountCallId;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private int _type;
	private Date _callDate;
	private long _callLength;
	private String _summary;
	private String _clientsPresent;
	private String _notes;
	private String _actionItems;
}