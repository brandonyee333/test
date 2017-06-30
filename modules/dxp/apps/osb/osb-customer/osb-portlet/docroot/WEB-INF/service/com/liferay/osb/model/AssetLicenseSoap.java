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
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class AssetLicenseSoap implements Serializable {
	public static AssetLicenseSoap toSoapModel(AssetLicense model) {
		AssetLicenseSoap soapModel = new AssetLicenseSoap();

		soapModel.setAssetLicenseId(model.getAssetLicenseId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setLicenseId(model.getLicenseId());
		soapModel.setName(model.getName());
		soapModel.setRequiredVersion(model.getRequiredVersion());
		soapModel.setUsageType(model.getUsageType());
		soapModel.setLicenseType(model.getLicenseType());
		soapModel.setLicenseTypeAllotment(model.getLicenseTypeAllotment());
		soapModel.setLifetime(model.getLifetime());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static AssetLicenseSoap[] toSoapModels(AssetLicense[] models) {
		AssetLicenseSoap[] soapModels = new AssetLicenseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetLicenseSoap[][] toSoapModels(AssetLicense[][] models) {
		AssetLicenseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetLicenseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetLicenseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetLicenseSoap[] toSoapModels(List<AssetLicense> models) {
		List<AssetLicenseSoap> soapModels = new ArrayList<AssetLicenseSoap>(models.size());

		for (AssetLicense model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetLicenseSoap[soapModels.size()]);
	}

	public AssetLicenseSoap() {
	}

	public long getPrimaryKey() {
		return _assetLicenseId;
	}

	public void setPrimaryKey(long pk) {
		setAssetLicenseId(pk);
	}

	public long getAssetLicenseId() {
		return _assetLicenseId;
	}

	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicenseId = assetLicenseId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getLicenseId() {
		return _licenseId;
	}

	public void setLicenseId(String licenseId) {
		_licenseId = licenseId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public double getRequiredVersion() {
		return _requiredVersion;
	}

	public void setRequiredVersion(double requiredVersion) {
		_requiredVersion = requiredVersion;
	}

	public int getUsageType() {
		return _usageType;
	}

	public void setUsageType(int usageType) {
		_usageType = usageType;
	}

	public int getLicenseType() {
		return _licenseType;
	}

	public void setLicenseType(int licenseType) {
		_licenseType = licenseType;
	}

	public long getLicenseTypeAllotment() {
		return _licenseTypeAllotment;
	}

	public void setLicenseTypeAllotment(long licenseTypeAllotment) {
		_licenseTypeAllotment = licenseTypeAllotment;
	}

	public long getLifetime() {
		return _lifetime;
	}

	public void setLifetime(long lifetime) {
		_lifetime = lifetime;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _assetLicenseId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _licenseId;
	private String _name;
	private double _requiredVersion;
	private int _usageType;
	private int _licenseType;
	private long _licenseTypeAllotment;
	private long _lifetime;
	private int _status;
}