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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AssetReceiptLicenseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AssetReceiptLicenseServiceSoap
 * @generated
 */
@ProviderType
public class AssetReceiptLicenseSoap implements Serializable {
	public static AssetReceiptLicenseSoap toSoapModel(AssetReceiptLicense model) {
		AssetReceiptLicenseSoap soapModel = new AssetReceiptLicenseSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAssetReceiptLicenseId(model.getAssetReceiptLicenseId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetReceiptId(model.getAssetReceiptId());
		soapModel.setAssetLicenseId(model.getAssetLicenseId());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setOwnerClassNameId(model.getOwnerClassNameId());
		soapModel.setOwnerClassPK(model.getOwnerClassPK());
		soapModel.setProductClassNameId(model.getProductClassNameId());
		soapModel.setProductClassPK(model.getProductClassPK());
		soapModel.setProductId(model.getProductId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setUsageType(model.getUsageType());
		soapModel.setLicenseType(model.getLicenseType());
		soapModel.setLicenseTypeAllotment(model.getLicenseTypeAllotment());
		soapModel.setLicenseLifetime(model.getLicenseLifetime());

		return soapModel;
	}

	public static AssetReceiptLicenseSoap[] toSoapModels(
		AssetReceiptLicense[] models) {
		AssetReceiptLicenseSoap[] soapModels = new AssetReceiptLicenseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetReceiptLicenseSoap[][] toSoapModels(
		AssetReceiptLicense[][] models) {
		AssetReceiptLicenseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetReceiptLicenseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetReceiptLicenseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetReceiptLicenseSoap[] toSoapModels(
		List<AssetReceiptLicense> models) {
		List<AssetReceiptLicenseSoap> soapModels = new ArrayList<AssetReceiptLicenseSoap>(models.size());

		for (AssetReceiptLicense model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetReceiptLicenseSoap[soapModels.size()]);
	}

	public AssetReceiptLicenseSoap() {
	}

	public long getPrimaryKey() {
		return _assetReceiptLicenseId;
	}

	public void setPrimaryKey(long pk) {
		setAssetReceiptLicenseId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAssetReceiptLicenseId() {
		return _assetReceiptLicenseId;
	}

	public void setAssetReceiptLicenseId(long assetReceiptLicenseId) {
		_assetReceiptLicenseId = assetReceiptLicenseId;
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

	public long getAssetReceiptId() {
		return _assetReceiptId;
	}

	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptId = assetReceiptId;
	}

	public long getAssetLicenseId() {
		return _assetLicenseId;
	}

	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicenseId = assetLicenseId;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public long getOwnerClassNameId() {
		return _ownerClassNameId;
	}

	public void setOwnerClassNameId(long ownerClassNameId) {
		_ownerClassNameId = ownerClassNameId;
	}

	public long getOwnerClassPK() {
		return _ownerClassPK;
	}

	public void setOwnerClassPK(long ownerClassPK) {
		_ownerClassPK = ownerClassPK;
	}

	public long getProductClassNameId() {
		return _productClassNameId;
	}

	public void setProductClassNameId(long productClassNameId) {
		_productClassNameId = productClassNameId;
	}

	public long getProductClassPK() {
		return _productClassPK;
	}

	public void setProductClassPK(long productClassPK) {
		_productClassPK = productClassPK;
	}

	public String getProductId() {
		return _productId;
	}

	public void setProductId(String productId) {
		_productId = productId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
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

	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	public void setLicenseLifetime(long licenseLifetime) {
		_licenseLifetime = licenseLifetime;
	}

	private String _uuid;
	private long _assetReceiptLicenseId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetReceiptId;
	private long _assetLicenseId;
	private long _assetEntryId;
	private long _ownerClassNameId;
	private long _ownerClassPK;
	private long _productClassNameId;
	private long _productClassPK;
	private String _productId;
	private Date _startDate;
	private Date _endDate;
	private int _usageType;
	private int _licenseType;
	private long _licenseTypeAllotment;
	private long _licenseLifetime;
}