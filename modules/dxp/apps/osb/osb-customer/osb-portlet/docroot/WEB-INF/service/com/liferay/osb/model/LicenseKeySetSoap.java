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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.LicenseKeySetServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.LicenseKeySetServiceSoap
 * @generated
 */
@ProviderType
public class LicenseKeySetSoap implements Serializable {
	public static LicenseKeySetSoap toSoapModel(LicenseKeySet model) {
		LicenseKeySetSoap soapModel = new LicenseKeySetSoap();

		soapModel.setLicenseKeySetId(model.getLicenseKeySetId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static LicenseKeySetSoap[] toSoapModels(LicenseKeySet[] models) {
		LicenseKeySetSoap[] soapModels = new LicenseKeySetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LicenseKeySetSoap[][] toSoapModels(LicenseKeySet[][] models) {
		LicenseKeySetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LicenseKeySetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LicenseKeySetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LicenseKeySetSoap[] toSoapModels(List<LicenseKeySet> models) {
		List<LicenseKeySetSoap> soapModels = new ArrayList<LicenseKeySetSoap>(models.size());

		for (LicenseKeySet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LicenseKeySetSoap[soapModels.size()]);
	}

	public LicenseKeySetSoap() {
	}

	public long getPrimaryKey() {
		return _licenseKeySetId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseKeySetId(pk);
	}

	public long getLicenseKeySetId() {
		return _licenseKeySetId;
	}

	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKeySetId = licenseKeySetId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _licenseKeySetId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private String _name;
}