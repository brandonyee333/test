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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class UserProfileSoap implements Serializable {
	public static UserProfileSoap toSoapModel(UserProfile model) {
		UserProfileSoap soapModel = new UserProfileSoap();

		soapModel.setUserProfileId(model.getUserProfileId());
		soapModel.setUserId(model.getUserId());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setLastName(model.getLastName());
		soapModel.setLegalEntityName(model.getLegalEntityName());

		return soapModel;
	}

	public static UserProfileSoap[] toSoapModels(UserProfile[] models) {
		UserProfileSoap[] soapModels = new UserProfileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserProfileSoap[][] toSoapModels(UserProfile[][] models) {
		UserProfileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserProfileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserProfileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserProfileSoap[] toSoapModels(List<UserProfile> models) {
		List<UserProfileSoap> soapModels = new ArrayList<UserProfileSoap>(models.size());

		for (UserProfile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserProfileSoap[soapModels.size()]);
	}

	public UserProfileSoap() {
	}

	public long getPrimaryKey() {
		return _userProfileId;
	}

	public void setPrimaryKey(long pk) {
		setUserProfileId(pk);
	}

	public long getUserProfileId() {
		return _userProfileId;
	}

	public void setUserProfileId(long userProfileId) {
		_userProfileId = userProfileId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	private long _userProfileId;
	private long _userId;
	private String _emailAddress;
	private String _firstName;
	private String _lastName;
	private String _legalEntityName;
}