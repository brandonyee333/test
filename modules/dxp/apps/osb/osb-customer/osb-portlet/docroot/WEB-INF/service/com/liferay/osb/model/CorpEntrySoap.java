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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CorpEntryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.CorpEntryServiceSoap
 * @generated
 */
public class CorpEntrySoap implements Serializable {
	public static CorpEntrySoap toSoapModel(CorpEntry model) {
		CorpEntrySoap soapModel = new CorpEntrySoap();

		soapModel.setCorpEntryId(model.getCorpEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setOrganizationId(model.getOrganizationId());
		soapModel.setLogoId(model.getLogoId());
		soapModel.setAddressId(model.getAddressId());
		soapModel.setContactEmailAddress(model.getContactEmailAddress());
		soapModel.setProfileEmailAddress(model.getProfileEmailAddress());
		soapModel.setPhoneNumber(model.getPhoneNumber());
		soapModel.setFaxNumber(model.getFaxNumber());
		soapModel.setWebsite(model.getWebsite());
		soapModel.setDossieraAccountKey(model.getDossieraAccountKey());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStatusMessage(model.getStatusMessage());

		return soapModel;
	}

	public static CorpEntrySoap[] toSoapModels(CorpEntry[] models) {
		CorpEntrySoap[] soapModels = new CorpEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CorpEntrySoap[][] toSoapModels(CorpEntry[][] models) {
		CorpEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CorpEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CorpEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CorpEntrySoap[] toSoapModels(List<CorpEntry> models) {
		List<CorpEntrySoap> soapModels = new ArrayList<CorpEntrySoap>(models.size());

		for (CorpEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CorpEntrySoap[soapModels.size()]);
	}

	public CorpEntrySoap() {
	}

	public long getPrimaryKey() {
		return _corpEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCorpEntryId(pk);
	}

	public long getCorpEntryId() {
		return _corpEntryId;
	}

	public void setCorpEntryId(long corpEntryId) {
		_corpEntryId = corpEntryId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;
	}

	public String getContactEmailAddress() {
		return _contactEmailAddress;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		_contactEmailAddress = contactEmailAddress;
	}

	public String getProfileEmailAddress() {
		return _profileEmailAddress;
	}

	public void setProfileEmailAddress(String profileEmailAddress) {
		_profileEmailAddress = profileEmailAddress;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return _faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		_faxNumber = faxNumber;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getStatusMessage() {
		return _statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	private long _corpEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private long _organizationId;
	private long _logoId;
	private long _addressId;
	private String _contactEmailAddress;
	private String _profileEmailAddress;
	private String _phoneNumber;
	private String _faxNumber;
	private String _website;
	private String _dossieraAccountKey;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
}