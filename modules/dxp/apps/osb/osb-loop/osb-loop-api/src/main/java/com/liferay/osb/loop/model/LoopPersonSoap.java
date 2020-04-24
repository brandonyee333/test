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

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class LoopPersonSoap implements Serializable {
	public static LoopPersonSoap toSoapModel(LoopPerson model) {
		LoopPersonSoap soapModel = new LoopPersonSoap();

		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLoopJobTitleId(model.getLoopJobTitleId());
		soapModel.setManagerLoopPersonId(model.getManagerLoopPersonId());
		soapModel.setPersonUserId(model.getPersonUserId());
		soapModel.setExtraData(model.getExtraData());
		soapModel.setGroupedUserNotificationEventsCount(model.getGroupedUserNotificationEventsCount());
		soapModel.setImagePayload(model.getImagePayload());

		return soapModel;
	}

	public static LoopPersonSoap[] toSoapModels(LoopPerson[] models) {
		LoopPersonSoap[] soapModels = new LoopPersonSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopPersonSoap[][] toSoapModels(LoopPerson[][] models) {
		LoopPersonSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopPersonSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopPersonSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopPersonSoap[] toSoapModels(List<LoopPerson> models) {
		List<LoopPersonSoap> soapModels = new ArrayList<LoopPersonSoap>(models.size());

		for (LoopPerson model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopPersonSoap[soapModels.size()]);
	}

	public LoopPersonSoap() {
	}

	public long getPrimaryKey() {
		return _loopPersonId;
	}

	public void setPrimaryKey(long pk) {
		setLoopPersonId(pk);
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
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

	public long getLoopJobTitleId() {
		return _loopJobTitleId;
	}

	public void setLoopJobTitleId(long loopJobTitleId) {
		_loopJobTitleId = loopJobTitleId;
	}

	public long getManagerLoopPersonId() {
		return _managerLoopPersonId;
	}

	public void setManagerLoopPersonId(long managerLoopPersonId) {
		_managerLoopPersonId = managerLoopPersonId;
	}

	public long getPersonUserId() {
		return _personUserId;
	}

	public void setPersonUserId(long personUserId) {
		_personUserId = personUserId;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	public int getGroupedUserNotificationEventsCount() {
		return _groupedUserNotificationEventsCount;
	}

	public void setGroupedUserNotificationEventsCount(
		int groupedUserNotificationEventsCount) {
		_groupedUserNotificationEventsCount = groupedUserNotificationEventsCount;
	}

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	private long _loopPersonId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _loopJobTitleId;
	private long _managerLoopPersonId;
	private long _personUserId;
	private String _extraData;
	private int _groupedUserNotificationEventsCount;
	private String _imagePayload;
}