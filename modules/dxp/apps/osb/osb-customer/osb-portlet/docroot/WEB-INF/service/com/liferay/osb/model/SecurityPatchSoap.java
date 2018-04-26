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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SecurityPatchServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.SecurityPatchServiceSoap
 * @generated
 */
@ProviderType
public class SecurityPatchSoap implements Serializable {
	public static SecurityPatchSoap toSoapModel(SecurityPatch model) {
		SecurityPatchSoap soapModel = new SecurityPatchSoap();

		soapModel.setSecurityPatchId(model.getSecurityPatchId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setPortletId(model.getPortletId());
		soapModel.setEnvLFR(model.getEnvLFR());
		soapModel.setName(model.getName());
		soapModel.setFileName(model.getFileName());

		return soapModel;
	}

	public static SecurityPatchSoap[] toSoapModels(SecurityPatch[] models) {
		SecurityPatchSoap[] soapModels = new SecurityPatchSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SecurityPatchSoap[][] toSoapModels(SecurityPatch[][] models) {
		SecurityPatchSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SecurityPatchSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SecurityPatchSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SecurityPatchSoap[] toSoapModels(List<SecurityPatch> models) {
		List<SecurityPatchSoap> soapModels = new ArrayList<SecurityPatchSoap>(models.size());

		for (SecurityPatch model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SecurityPatchSoap[soapModels.size()]);
	}

	public SecurityPatchSoap() {
	}

	public long getPrimaryKey() {
		return _securityPatchId;
	}

	public void setPrimaryKey(long pk) {
		setSecurityPatchId(pk);
	}

	public long getSecurityPatchId() {
		return _securityPatchId;
	}

	public void setSecurityPatchId(long securityPatchId) {
		_securityPatchId = securityPatchId;
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

	public String getPortletId() {
		return _portletId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public int getEnvLFR() {
		return _envLFR;
	}

	public void setEnvLFR(int envLFR) {
		_envLFR = envLFR;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	private long _securityPatchId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private String _portletId;
	private int _envLFR;
	private String _name;
	private String _fileName;
}