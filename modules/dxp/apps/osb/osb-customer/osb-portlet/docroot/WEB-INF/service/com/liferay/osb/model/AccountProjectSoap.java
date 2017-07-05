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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountProjectServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountProjectServiceSoap
 * @generated
 */
@ProviderType
public class AccountProjectSoap implements Serializable {
	public static AccountProjectSoap toSoapModel(AccountProject model) {
		AccountProjectSoap soapModel = new AccountProjectSoap();

		soapModel.setAccountProjectId(model.getAccountProjectId());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static AccountProjectSoap[] toSoapModels(AccountProject[] models) {
		AccountProjectSoap[] soapModels = new AccountProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountProjectSoap[][] toSoapModels(AccountProject[][] models) {
		AccountProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountProjectSoap[] toSoapModels(List<AccountProject> models) {
		List<AccountProjectSoap> soapModels = new ArrayList<AccountProjectSoap>(models.size());

		for (AccountProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountProjectSoap[soapModels.size()]);
	}

	public AccountProjectSoap() {
	}

	public long getPrimaryKey() {
		return _accountProjectId;
	}

	public void setPrimaryKey(long pk) {
		setAccountProjectId(pk);
	}

	public long getAccountProjectId() {
		return _accountProjectId;
	}

	public void setAccountProjectId(long accountProjectId) {
		_accountProjectId = accountProjectId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _accountProjectId;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private String _name;
}