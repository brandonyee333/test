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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.OfferingEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.OfferingEntryServiceSoap
 * @generated
 */
@ProviderType
public class OfferingEntrySoap implements Serializable {
	public static OfferingEntrySoap toSoapModel(OfferingEntry model) {
		OfferingEntrySoap soapModel = new OfferingEntrySoap();

		soapModel.setOfferingEntryId(model.getOfferingEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setOrderEntryId(model.getOrderEntryId());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setSupportResponseId(model.getSupportResponseId());
		soapModel.setProductDescription(model.getProductDescription());
		soapModel.setType(model.getType());
		soapModel.setVersion(model.getVersion());
		soapModel.setPlatform(model.getPlatform());
		soapModel.setPlatformVersion(model.getPlatformVersion());
		soapModel.setLicenses(model.getLicenses());
		soapModel.setLicenseLifetime(model.getLicenseLifetime());
		soapModel.setMaxConcurrentUsers(model.getMaxConcurrentUsers());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setSupportTickets(model.getSupportTickets());
		soapModel.setSupportLifetime(model.getSupportLifetime());
		soapModel.setSupportEndDate(model.getSupportEndDate());
		soapModel.setSizing(model.getSizing());
		soapModel.setQuantity(model.getQuantity());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static OfferingEntrySoap[] toSoapModels(OfferingEntry[] models) {
		OfferingEntrySoap[] soapModels = new OfferingEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OfferingEntrySoap[][] toSoapModels(OfferingEntry[][] models) {
		OfferingEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OfferingEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new OfferingEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OfferingEntrySoap[] toSoapModels(List<OfferingEntry> models) {
		List<OfferingEntrySoap> soapModels = new ArrayList<OfferingEntrySoap>(models.size());

		for (OfferingEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OfferingEntrySoap[soapModels.size()]);
	}

	public OfferingEntrySoap() {
	}

	public long getPrimaryKey() {
		return _offeringEntryId;
	}

	public void setPrimaryKey(long pk) {
		setOfferingEntryId(pk);
	}

	public long getOfferingEntryId() {
		return _offeringEntryId;
	}

	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntryId = offeringEntryId;
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

	public long getOrderEntryId() {
		return _orderEntryId;
	}

	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	public long getSupportResponseId() {
		return _supportResponseId;
	}

	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;
	}

	public String getProductDescription() {
		return _productDescription;
	}

	public void setProductDescription(String productDescription) {
		_productDescription = productDescription;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getPlatform() {
		return _platform;
	}

	public void setPlatform(String platform) {
		_platform = platform;
	}

	public String getPlatformVersion() {
		return _platformVersion;
	}

	public void setPlatformVersion(String platformVersion) {
		_platformVersion = platformVersion;
	}

	public boolean getLicenses() {
		return _licenses;
	}

	public boolean isLicenses() {
		return _licenses;
	}

	public void setLicenses(boolean licenses) {
		_licenses = licenses;
	}

	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	public void setLicenseLifetime(long licenseLifetime) {
		_licenseLifetime = licenseLifetime;
	}

	public long getMaxConcurrentUsers() {
		return _maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_maxConcurrentUsers = maxConcurrentUsers;
	}

	public long getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		_maxUsers = maxUsers;
	}

	public boolean getSupportTickets() {
		return _supportTickets;
	}

	public boolean isSupportTickets() {
		return _supportTickets;
	}

	public void setSupportTickets(boolean supportTickets) {
		_supportTickets = supportTickets;
	}

	public long getSupportLifetime() {
		return _supportLifetime;
	}

	public void setSupportLifetime(long supportLifetime) {
		_supportLifetime = supportLifetime;
	}

	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;
	}

	public int getSizing() {
		return _sizing;
	}

	public void setSizing(int sizing) {
		_sizing = sizing;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _offeringEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _orderEntryId;
	private long _productEntryId;
	private long _supportResponseId;
	private String _productDescription;
	private int _type;
	private int _version;
	private String _platform;
	private String _platformVersion;
	private boolean _licenses;
	private long _licenseLifetime;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private boolean _supportTickets;
	private long _supportLifetime;
	private Date _supportEndDate;
	private int _sizing;
	private int _quantity;
	private int _status;
}