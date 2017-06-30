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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AssetAuditServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AssetAuditServiceSoap
 * @generated
 */
public class AssetAuditSoap implements Serializable {
	public static AssetAuditSoap toSoapModel(AssetAudit model) {
		AssetAuditSoap soapModel = new AssetAuditSoap();

		soapModel.setAssetAuditId(model.getAssetAuditId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLegalEntityName(model.getLegalEntityName());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setVendorClassNameId(model.getVendorClassNameId());
		soapModel.setVendorClassPK(model.getVendorClassPK());
		soapModel.setType(model.getType());
		soapModel.setDomain(model.getDomain());
		soapModel.setTime(model.getTime());
		soapModel.setCurrencyCode(model.getCurrencyCode());
		soapModel.setPrice(model.getPrice());

		return soapModel;
	}

	public static AssetAuditSoap[] toSoapModels(AssetAudit[] models) {
		AssetAuditSoap[] soapModels = new AssetAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetAuditSoap[][] toSoapModels(AssetAudit[][] models) {
		AssetAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetAuditSoap[] toSoapModels(List<AssetAudit> models) {
		List<AssetAuditSoap> soapModels = new ArrayList<AssetAuditSoap>(models.size());

		for (AssetAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetAuditSoap[soapModels.size()]);
	}

	public AssetAuditSoap() {
	}

	public long getPrimaryKey() {
		return _assetAuditId;
	}

	public void setPrimaryKey(long pk) {
		setAssetAuditId(pk);
	}

	public long getAssetAuditId() {
		return _assetAuditId;
	}

	public void setAssetAuditId(long assetAuditId) {
		_assetAuditId = assetAuditId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;
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

	public long getVendorClassNameId() {
		return _vendorClassNameId;
	}

	public void setVendorClassNameId(long vendorClassNameId) {
		_vendorClassNameId = vendorClassNameId;
	}

	public long getVendorClassPK() {
		return _vendorClassPK;
	}

	public void setVendorClassPK(long vendorClassPK) {
		_vendorClassPK = vendorClassPK;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getDomain() {
		return _domain;
	}

	public void setDomain(int domain) {
		_domain = domain;
	}

	public long getTime() {
		return _time;
	}

	public void setTime(long time) {
		_time = time;
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	private long _assetAuditId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _legalEntityName;
	private long _classNameId;
	private long _classPK;
	private long _vendorClassNameId;
	private long _vendorClassPK;
	private int _type;
	private int _domain;
	private long _time;
	private String _currencyCode;
	private double _price;
}