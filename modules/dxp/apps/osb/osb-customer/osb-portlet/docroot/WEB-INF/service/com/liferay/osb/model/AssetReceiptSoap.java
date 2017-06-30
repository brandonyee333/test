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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AssetReceiptServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AssetReceiptServiceSoap
 * @generated
 */
public class AssetReceiptSoap implements Serializable {
	public static AssetReceiptSoap toSoapModel(AssetReceipt model) {
		AssetReceiptSoap soapModel = new AssetReceiptSoap();

		soapModel.setAssetReceiptId(model.getAssetReceiptId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setOwnerClassNameId(model.getOwnerClassNameId());
		soapModel.setOwnerClassPK(model.getOwnerClassPK());
		soapModel.setLegalEntityName(model.getLegalEntityName());
		soapModel.setProductClassNameId(model.getProductClassNameId());
		soapModel.setProductClassPK(model.getProductClassPK());
		soapModel.setType(model.getType());
		soapModel.setCurrencyEntryId(model.getCurrencyEntryId());
		soapModel.setActualPrice(model.getActualPrice());

		return soapModel;
	}

	public static AssetReceiptSoap[] toSoapModels(AssetReceipt[] models) {
		AssetReceiptSoap[] soapModels = new AssetReceiptSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetReceiptSoap[][] toSoapModels(AssetReceipt[][] models) {
		AssetReceiptSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetReceiptSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetReceiptSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetReceiptSoap[] toSoapModels(List<AssetReceipt> models) {
		List<AssetReceiptSoap> soapModels = new ArrayList<AssetReceiptSoap>(models.size());

		for (AssetReceipt model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetReceiptSoap[soapModels.size()]);
	}

	public AssetReceiptSoap() {
	}

	public long getPrimaryKey() {
		return _assetReceiptId;
	}

	public void setPrimaryKey(long pk) {
		setAssetReceiptId(pk);
	}

	public long getAssetReceiptId() {
		return _assetReceiptId;
	}

	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptId = assetReceiptId;
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

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;
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

	public long getType() {
		return _type;
	}

	public void setType(long type) {
		_type = type;
	}

	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;
	}

	public double getActualPrice() {
		return _actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		_actualPrice = actualPrice;
	}

	private long _assetReceiptId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _assetEntryId;
	private long _ownerClassNameId;
	private long _ownerClassPK;
	private String _legalEntityName;
	private long _productClassNameId;
	private long _productClassPK;
	private long _type;
	private long _currencyEntryId;
	private double _actualPrice;
}