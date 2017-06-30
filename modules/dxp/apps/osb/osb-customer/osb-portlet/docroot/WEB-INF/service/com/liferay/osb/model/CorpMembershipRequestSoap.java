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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CorpMembershipRequestServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.CorpMembershipRequestServiceSoap
 * @generated
 */
public class CorpMembershipRequestSoap implements Serializable {
	public static CorpMembershipRequestSoap toSoapModel(
		CorpMembershipRequest model) {
		CorpMembershipRequestSoap soapModel = new CorpMembershipRequestSoap();

		soapModel.setCorpMembershipRequestId(model.getCorpMembershipRequestId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCorpEntryId(model.getCorpEntryId());
		soapModel.setKey(model.getKey());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CorpMembershipRequestSoap[] toSoapModels(
		CorpMembershipRequest[] models) {
		CorpMembershipRequestSoap[] soapModels = new CorpMembershipRequestSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CorpMembershipRequestSoap[][] toSoapModels(
		CorpMembershipRequest[][] models) {
		CorpMembershipRequestSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CorpMembershipRequestSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CorpMembershipRequestSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CorpMembershipRequestSoap[] toSoapModels(
		List<CorpMembershipRequest> models) {
		List<CorpMembershipRequestSoap> soapModels = new ArrayList<CorpMembershipRequestSoap>(models.size());

		for (CorpMembershipRequest model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CorpMembershipRequestSoap[soapModels.size()]);
	}

	public CorpMembershipRequestSoap() {
	}

	public long getPrimaryKey() {
		return _corpMembershipRequestId;
	}

	public void setPrimaryKey(long pk) {
		setCorpMembershipRequestId(pk);
	}

	public long getCorpMembershipRequestId() {
		return _corpMembershipRequestId;
	}

	public void setCorpMembershipRequestId(long corpMembershipRequestId) {
		_corpMembershipRequestId = corpMembershipRequestId;
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

	public long getCorpEntryId() {
		return _corpEntryId;
	}

	public void setCorpEntryId(long corpEntryId) {
		_corpEntryId = corpEntryId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _corpMembershipRequestId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _corpEntryId;
	private String _key;
	private String _emailAddress;
	private int _status;
}