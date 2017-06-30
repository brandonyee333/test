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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AssetReceiptRedeemTokenServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AssetReceiptRedeemTokenServiceSoap
 * @generated
 */
public class AssetReceiptRedeemTokenSoap implements Serializable {
	public static AssetReceiptRedeemTokenSoap toSoapModel(
		AssetReceiptRedeemToken model) {
		AssetReceiptRedeemTokenSoap soapModel = new AssetReceiptRedeemTokenSoap();

		soapModel.setAssetReceiptRedeemTokenId(model.getAssetReceiptRedeemTokenId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setRedeemEmailAddress(model.getRedeemEmailAddress());
		soapModel.setRedeemDate(model.getRedeemDate());
		soapModel.setToken(model.getToken());

		return soapModel;
	}

	public static AssetReceiptRedeemTokenSoap[] toSoapModels(
		AssetReceiptRedeemToken[] models) {
		AssetReceiptRedeemTokenSoap[] soapModels = new AssetReceiptRedeemTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetReceiptRedeemTokenSoap[][] toSoapModels(
		AssetReceiptRedeemToken[][] models) {
		AssetReceiptRedeemTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetReceiptRedeemTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetReceiptRedeemTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetReceiptRedeemTokenSoap[] toSoapModels(
		List<AssetReceiptRedeemToken> models) {
		List<AssetReceiptRedeemTokenSoap> soapModels = new ArrayList<AssetReceiptRedeemTokenSoap>(models.size());

		for (AssetReceiptRedeemToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetReceiptRedeemTokenSoap[soapModels.size()]);
	}

	public AssetReceiptRedeemTokenSoap() {
	}

	public long getPrimaryKey() {
		return _AssetReceiptRedeemTokenId;
	}

	public void setPrimaryKey(long pk) {
		setAssetReceiptRedeemTokenId(pk);
	}

	public long getAssetReceiptRedeemTokenId() {
		return _AssetReceiptRedeemTokenId;
	}

	public void setAssetReceiptRedeemTokenId(long AssetReceiptRedeemTokenId) {
		_AssetReceiptRedeemTokenId = AssetReceiptRedeemTokenId;
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

	public String getRedeemEmailAddress() {
		return _redeemEmailAddress;
	}

	public void setRedeemEmailAddress(String redeemEmailAddress) {
		_redeemEmailAddress = redeemEmailAddress;
	}

	public Date getRedeemDate() {
		return _redeemDate;
	}

	public void setRedeemDate(Date redeemDate) {
		_redeemDate = redeemDate;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	private long _AssetReceiptRedeemTokenId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _redeemEmailAddress;
	private Date _redeemDate;
	private String _token;
}