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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountInformationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountInformationServiceSoap
 * @generated
 */
@ProviderType
public class AccountInformationSoap implements Serializable {
	public static AccountInformationSoap toSoapModel(AccountInformation model) {
		AccountInformationSoap soapModel = new AccountInformationSoap();

		soapModel.setAccountInformationId(model.getAccountInformationId());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setAccountProjectId(model.getAccountProjectId());
		soapModel.setFieldId(model.getFieldId());
		soapModel.setData(model.getData());

		return soapModel;
	}

	public static AccountInformationSoap[] toSoapModels(
		AccountInformation[] models) {
		AccountInformationSoap[] soapModels = new AccountInformationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountInformationSoap[][] toSoapModels(
		AccountInformation[][] models) {
		AccountInformationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountInformationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountInformationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountInformationSoap[] toSoapModels(
		List<AccountInformation> models) {
		List<AccountInformationSoap> soapModels = new ArrayList<AccountInformationSoap>(models.size());

		for (AccountInformation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountInformationSoap[soapModels.size()]);
	}

	public AccountInformationSoap() {
	}

	public long getPrimaryKey() {
		return _accountInformationId;
	}

	public void setPrimaryKey(long pk) {
		setAccountInformationId(pk);
	}

	public long getAccountInformationId() {
		return _accountInformationId;
	}

	public void setAccountInformationId(long accountInformationId) {
		_accountInformationId = accountInformationId;
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

	public long getAccountProjectId() {
		return _accountProjectId;
	}

	public void setAccountProjectId(long accountProjectId) {
		_accountProjectId = accountProjectId;
	}

	public int getFieldId() {
		return _fieldId;
	}

	public void setFieldId(int fieldId) {
		_fieldId = fieldId;
	}

	public String getData() {
		return _data;
	}

	public void setData(String data) {
		_data = data;
	}

	private long _accountInformationId;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _accountProjectId;
	private int _fieldId;
	private String _data;
}