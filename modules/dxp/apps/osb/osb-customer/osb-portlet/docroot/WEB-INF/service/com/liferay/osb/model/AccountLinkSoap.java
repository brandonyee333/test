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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountLinkServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AccountLinkServiceSoap
 * @generated
 */
public class AccountLinkSoap implements Serializable {
	public static AccountLinkSoap toSoapModel(AccountLink model) {
		AccountLinkSoap soapModel = new AccountLinkSoap();

		soapModel.setAccountLinkId(model.getAccountLinkId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setUrl(model.getUrl());

		return soapModel;
	}

	public static AccountLinkSoap[] toSoapModels(AccountLink[] models) {
		AccountLinkSoap[] soapModels = new AccountLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountLinkSoap[][] toSoapModels(AccountLink[][] models) {
		AccountLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountLinkSoap[] toSoapModels(List<AccountLink> models) {
		List<AccountLinkSoap> soapModels = new ArrayList<AccountLinkSoap>(models.size());

		for (AccountLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountLinkSoap[soapModels.size()]);
	}

	public AccountLinkSoap() {
	}

	public long getPrimaryKey() {
		return _accountLinkId;
	}

	public void setPrimaryKey(long pk) {
		setAccountLinkId(pk);
	}

	public long getAccountLinkId() {
		return _accountLinkId;
	}

	public void setAccountLinkId(long accountLinkId) {
		_accountLinkId = accountLinkId;
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

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private long _accountLinkId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private String _url;
}