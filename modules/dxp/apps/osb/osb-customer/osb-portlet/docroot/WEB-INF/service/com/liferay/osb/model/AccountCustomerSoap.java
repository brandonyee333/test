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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountCustomerServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountCustomerServiceSoap
 * @generated
 */
@ProviderType
public class AccountCustomerSoap implements Serializable {
	public static AccountCustomerSoap toSoapModel(AccountCustomer model) {
		AccountCustomerSoap soapModel = new AccountCustomerSoap();

		soapModel.setAccountCustomerId(model.getAccountCustomerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setRole(model.getRole());
		soapModel.setClosedWatcher(model.getClosedWatcher());

		return soapModel;
	}

	public static AccountCustomerSoap[] toSoapModels(AccountCustomer[] models) {
		AccountCustomerSoap[] soapModels = new AccountCustomerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountCustomerSoap[][] toSoapModels(
		AccountCustomer[][] models) {
		AccountCustomerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountCustomerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountCustomerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountCustomerSoap[] toSoapModels(
		List<AccountCustomer> models) {
		List<AccountCustomerSoap> soapModels = new ArrayList<AccountCustomerSoap>(models.size());

		for (AccountCustomer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountCustomerSoap[soapModels.size()]);
	}

	public AccountCustomerSoap() {
	}

	public long getPrimaryKey() {
		return _accountCustomerId;
	}

	public void setPrimaryKey(long pk) {
		setAccountCustomerId(pk);
	}

	public long getAccountCustomerId() {
		return _accountCustomerId;
	}

	public void setAccountCustomerId(long accountCustomerId) {
		_accountCustomerId = accountCustomerId;
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

	public boolean getClosedWatcher() {
		return _closedWatcher;
	}

	public boolean isClosedWatcher() {
		return _closedWatcher;
	}

	public void setClosedWatcher(boolean closedWatcher) {
		_closedWatcher = closedWatcher;
	}

	private long _accountCustomerId;
	private long _userId;
	private long _accountEntryId;
	private int _role;
	private boolean _closedWatcher;
}