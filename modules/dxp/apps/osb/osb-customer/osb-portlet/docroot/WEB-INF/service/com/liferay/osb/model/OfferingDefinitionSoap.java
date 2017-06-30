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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.OfferingDefinitionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.OfferingDefinitionServiceSoap
 * @generated
 */
public class OfferingDefinitionSoap implements Serializable {
	public static OfferingDefinitionSoap toSoapModel(OfferingDefinition model) {
		OfferingDefinitionSoap soapModel = new OfferingDefinitionSoap();

		soapModel.setOfferingDefinitionId(model.getOfferingDefinitionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setSupportResponseId(model.getSupportResponseId());
		soapModel.setProductDescription(model.getProductDescription());
		soapModel.setLicenses(model.getLicenses());
		soapModel.setUnlimitedLicenses(model.getUnlimitedLicenses());
		soapModel.setMaxConcurrentUsers(model.getMaxConcurrentUsers());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setSupportTickets(model.getSupportTickets());

		return soapModel;
	}

	public static OfferingDefinitionSoap[] toSoapModels(
		OfferingDefinition[] models) {
		OfferingDefinitionSoap[] soapModels = new OfferingDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OfferingDefinitionSoap[][] toSoapModels(
		OfferingDefinition[][] models) {
		OfferingDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OfferingDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OfferingDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OfferingDefinitionSoap[] toSoapModels(
		List<OfferingDefinition> models) {
		List<OfferingDefinitionSoap> soapModels = new ArrayList<OfferingDefinitionSoap>(models.size());

		for (OfferingDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OfferingDefinitionSoap[soapModels.size()]);
	}

	public OfferingDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _offeringDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setOfferingDefinitionId(pk);
	}

	public long getOfferingDefinitionId() {
		return _offeringDefinitionId;
	}

	public void setOfferingDefinitionId(long offeringDefinitionId) {
		_offeringDefinitionId = offeringDefinitionId;
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

	public boolean getLicenses() {
		return _licenses;
	}

	public boolean isLicenses() {
		return _licenses;
	}

	public void setLicenses(boolean licenses) {
		_licenses = licenses;
	}

	public boolean getUnlimitedLicenses() {
		return _unlimitedLicenses;
	}

	public boolean isUnlimitedLicenses() {
		return _unlimitedLicenses;
	}

	public void setUnlimitedLicenses(boolean unlimitedLicenses) {
		_unlimitedLicenses = unlimitedLicenses;
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

	private long _offeringDefinitionId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _productEntryId;
	private long _supportResponseId;
	private String _productDescription;
	private boolean _licenses;
	private boolean _unlimitedLicenses;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private boolean _supportTickets;
}