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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountEnvironmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountEnvironmentServiceSoap
 * @generated
 */
@ProviderType
public class AccountEnvironmentSoap implements Serializable {
	public static AccountEnvironmentSoap toSoapModel(AccountEnvironment model) {
		AccountEnvironmentSoap soapModel = new AccountEnvironmentSoap();

		soapModel.setAccountEnvironmentId(model.getAccountEnvironmentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setName(model.getName());
		soapModel.setEnvOS(model.getEnvOS());
		soapModel.setEnvOSCustom(model.getEnvOSCustom());
		soapModel.setEnvDB(model.getEnvDB());
		soapModel.setEnvJVM(model.getEnvJVM());
		soapModel.setEnvAS(model.getEnvAS());
		soapModel.setEnvLFR(model.getEnvLFR());
		soapModel.setEnvCommerce(model.getEnvCommerce());
		soapModel.setEnvBrowser(model.getEnvBrowser());
		soapModel.setEnvCS(model.getEnvCS());
		soapModel.setEnvSearch(model.getEnvSearch());

		return soapModel;
	}

	public static AccountEnvironmentSoap[] toSoapModels(
		AccountEnvironment[] models) {
		AccountEnvironmentSoap[] soapModels = new AccountEnvironmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountEnvironmentSoap[][] toSoapModels(
		AccountEnvironment[][] models) {
		AccountEnvironmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountEnvironmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountEnvironmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountEnvironmentSoap[] toSoapModels(
		List<AccountEnvironment> models) {
		List<AccountEnvironmentSoap> soapModels = new ArrayList<AccountEnvironmentSoap>(models.size());

		for (AccountEnvironment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountEnvironmentSoap[soapModels.size()]);
	}

	public AccountEnvironmentSoap() {
	}

	public long getPrimaryKey() {
		return _accountEnvironmentId;
	}

	public void setPrimaryKey(long pk) {
		setAccountEnvironmentId(pk);
	}

	public long getAccountEnvironmentId() {
		return _accountEnvironmentId;
	}

	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironmentId = accountEnvironmentId;
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

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getEnvOS() {
		return _envOS;
	}

	public void setEnvOS(int envOS) {
		_envOS = envOS;
	}

	public String getEnvOSCustom() {
		return _envOSCustom;
	}

	public void setEnvOSCustom(String envOSCustom) {
		_envOSCustom = envOSCustom;
	}

	public int getEnvDB() {
		return _envDB;
	}

	public void setEnvDB(int envDB) {
		_envDB = envDB;
	}

	public int getEnvJVM() {
		return _envJVM;
	}

	public void setEnvJVM(int envJVM) {
		_envJVM = envJVM;
	}

	public int getEnvAS() {
		return _envAS;
	}

	public void setEnvAS(int envAS) {
		_envAS = envAS;
	}

	public int getEnvLFR() {
		return _envLFR;
	}

	public void setEnvLFR(int envLFR) {
		_envLFR = envLFR;
	}

	public int getEnvCommerce() {
		return _envCommerce;
	}

	public void setEnvCommerce(int envCommerce) {
		_envCommerce = envCommerce;
	}

	public int getEnvBrowser() {
		return _envBrowser;
	}

	public void setEnvBrowser(int envBrowser) {
		_envBrowser = envBrowser;
	}

	public int getEnvCS() {
		return _envCS;
	}

	public void setEnvCS(int envCS) {
		_envCS = envCS;
	}

	public String getEnvSearch() {
		return _envSearch;
	}

	public void setEnvSearch(String envSearch) {
		_envSearch = envSearch;
	}

	private long _accountEnvironmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _productEntryId;
	private String _name;
	private int _envOS;
	private String _envOSCustom;
	private int _envDB;
	private int _envJVM;
	private int _envAS;
	private int _envLFR;
	private int _envCommerce;
	private int _envBrowser;
	private int _envCS;
	private String _envSearch;
}