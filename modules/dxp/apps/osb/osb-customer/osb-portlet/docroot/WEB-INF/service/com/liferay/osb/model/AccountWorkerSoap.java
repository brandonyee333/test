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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountWorkerServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountWorkerServiceSoap
 * @generated
 */
@ProviderType
public class AccountWorkerSoap implements Serializable {
	public static AccountWorkerSoap toSoapModel(AccountWorker model) {
		AccountWorkerSoap soapModel = new AccountWorkerSoap();

		soapModel.setAccountWorkerId(model.getAccountWorkerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setRole(model.getRole());

		return soapModel;
	}

	public static AccountWorkerSoap[] toSoapModels(AccountWorker[] models) {
		AccountWorkerSoap[] soapModels = new AccountWorkerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountWorkerSoap[][] toSoapModels(AccountWorker[][] models) {
		AccountWorkerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountWorkerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountWorkerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountWorkerSoap[] toSoapModels(List<AccountWorker> models) {
		List<AccountWorkerSoap> soapModels = new ArrayList<AccountWorkerSoap>(models.size());

		for (AccountWorker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountWorkerSoap[soapModels.size()]);
	}

	public AccountWorkerSoap() {
	}

	public long getPrimaryKey() {
		return _accountWorkerId;
	}

	public void setPrimaryKey(long pk) {
		setAccountWorkerId(pk);
	}

	public long getAccountWorkerId() {
		return _accountWorkerId;
	}

	public void setAccountWorkerId(long accountWorkerId) {
		_accountWorkerId = accountWorkerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	private long _accountWorkerId;
	private long _userId;
	private long _accountEntryId;
	private int _role;
}