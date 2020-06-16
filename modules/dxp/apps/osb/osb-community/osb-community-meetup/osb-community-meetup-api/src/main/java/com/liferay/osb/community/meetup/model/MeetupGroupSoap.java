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

package com.liferay.osb.community.meetup.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Jamie Sammons
 * @generated
 */
@ProviderType
public class MeetupGroupSoap implements Serializable {
	public static MeetupGroupSoap toSoapModel(MeetupGroup model) {
		MeetupGroupSoap soapModel = new MeetupGroupSoap();

		soapModel.setMeetupGroupId(model.getMeetupGroupId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setCity(model.getCity());
		soapModel.setMemberCount(model.getMemberCount());
		soapModel.setDescription(model.getDescription());
		soapModel.setUrl(model.getUrl());

		return soapModel;
	}

	public static MeetupGroupSoap[] toSoapModels(MeetupGroup[] models) {
		MeetupGroupSoap[] soapModels = new MeetupGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MeetupGroupSoap[][] toSoapModels(MeetupGroup[][] models) {
		MeetupGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MeetupGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MeetupGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MeetupGroupSoap[] toSoapModels(List<MeetupGroup> models) {
		List<MeetupGroupSoap> soapModels = new ArrayList<MeetupGroupSoap>(models.size());

		for (MeetupGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeetupGroupSoap[soapModels.size()]);
	}

	public MeetupGroupSoap() {
	}

	public long getPrimaryKey() {
		return _meetupGroupId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupGroupId(pk);
	}

	public long getMeetupGroupId() {
		return _meetupGroupId;
	}

	public void setMeetupGroupId(long meetupGroupId) {
		_meetupGroupId = meetupGroupId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public int getMemberCount() {
		return _memberCount;
	}

	public void setMemberCount(int memberCount) {
		_memberCount = memberCount;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private long _meetupGroupId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _city;
	private int _memberCount;
	private String _description;
	private String _url;
}