/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.license.service.http.LicenseKeySetServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeySetSoap implements Serializable {

	public static LicenseKeySetSoap toSoapModel(LicenseKeySet model) {
		LicenseKeySetSoap soapModel = new LicenseKeySetSoap();

		soapModel.setLicenseKeySetId(model.getLicenseKeySetId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKoroneikiAccountKey(model.getKoroneikiAccountKey());
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
		List<LicenseKeySetSoap> soapModels = new ArrayList<LicenseKeySetSoap>(
			models.size());

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

	public String getKoroneikiAccountKey() {
		return _koroneikiAccountKey;
	}

	public void setKoroneikiAccountKey(String koroneikiAccountKey) {
		_koroneikiAccountKey = koroneikiAccountKey;
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
	private String _koroneikiAccountKey;
	private long _accountEntryId;
	private String _name;

}