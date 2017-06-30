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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.UserProfileHistoryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.UserProfileHistoryServiceSoap
 * @generated
 */
public class UserProfileHistorySoap implements Serializable {
	public static UserProfileHistorySoap toSoapModel(UserProfileHistory model) {
		UserProfileHistorySoap soapModel = new UserProfileHistorySoap();

		soapModel.setUserProfileHistoryId(model.getUserProfileHistoryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setLastName(model.getLastName());
		soapModel.setLegalEntityName(model.getLegalEntityName());

		return soapModel;
	}

	public static UserProfileHistorySoap[] toSoapModels(
		UserProfileHistory[] models) {
		UserProfileHistorySoap[] soapModels = new UserProfileHistorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserProfileHistorySoap[][] toSoapModels(
		UserProfileHistory[][] models) {
		UserProfileHistorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserProfileHistorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserProfileHistorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserProfileHistorySoap[] toSoapModels(
		List<UserProfileHistory> models) {
		List<UserProfileHistorySoap> soapModels = new ArrayList<UserProfileHistorySoap>(models.size());

		for (UserProfileHistory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserProfileHistorySoap[soapModels.size()]);
	}

	public UserProfileHistorySoap() {
	}

	public long getPrimaryKey() {
		return _userProfileHistoryId;
	}

	public void setPrimaryKey(long pk) {
		setUserProfileHistoryId(pk);
	}

	public long getUserProfileHistoryId() {
		return _userProfileHistoryId;
	}

	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_userProfileHistoryId = userProfileHistoryId;
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

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;
	}

	private long _userProfileHistoryId;
	private long _userId;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _emailAddress;
	private String _firstName;
	private String _lastName;
	private String _legalEntityName;
}