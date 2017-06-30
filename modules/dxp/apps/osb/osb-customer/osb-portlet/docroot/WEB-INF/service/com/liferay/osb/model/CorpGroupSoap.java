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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CorpGroupServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.CorpGroupServiceSoap
 * @generated
 */
public class CorpGroupSoap implements Serializable {
	public static CorpGroupSoap toSoapModel(CorpGroup model) {
		CorpGroupSoap soapModel = new CorpGroupSoap();

		soapModel.setCorpGroupId(model.getCorpGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setOrganizationId(model.getOrganizationId());
		soapModel.setLogoId(model.getLogoId());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setWebsite(model.getWebsite());

		return soapModel;
	}

	public static CorpGroupSoap[] toSoapModels(CorpGroup[] models) {
		CorpGroupSoap[] soapModels = new CorpGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CorpGroupSoap[][] toSoapModels(CorpGroup[][] models) {
		CorpGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CorpGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CorpGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CorpGroupSoap[] toSoapModels(List<CorpGroup> models) {
		List<CorpGroupSoap> soapModels = new ArrayList<CorpGroupSoap>(models.size());

		for (CorpGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CorpGroupSoap[soapModels.size()]);
	}

	public CorpGroupSoap() {
	}

	public long getPrimaryKey() {
		return _corpGroupId;
	}

	public void setPrimaryKey(long pk) {
		setCorpGroupId(pk);
	}

	public long getCorpGroupId() {
		return _corpGroupId;
	}

	public void setCorpGroupId(long corpGroupId) {
		_corpGroupId = corpGroupId;
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

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	private long _corpGroupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private long _organizationId;
	private long _logoId;
	private String _emailAddress;
	private String _website;
}