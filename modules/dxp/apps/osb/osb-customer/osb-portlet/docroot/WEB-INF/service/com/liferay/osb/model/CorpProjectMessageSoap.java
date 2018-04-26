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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CorpProjectMessageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.CorpProjectMessageServiceSoap
 * @generated
 */
@ProviderType
public class CorpProjectMessageSoap implements Serializable {
	public static CorpProjectMessageSoap toSoapModel(CorpProjectMessage model) {
		CorpProjectMessageSoap soapModel = new CorpProjectMessageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCorpProjectMessageId(model.getCorpProjectMessageId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCorpProjectId(model.getCorpProjectId());
		soapModel.setType(model.getType());
		soapModel.setSeverityLevel(model.getSeverityLevel());
		soapModel.setTitle(model.getTitle());
		soapModel.setContent(model.getContent());
		soapModel.setDisplayCP(model.getDisplayCP());
		soapModel.setDisplayLCS(model.getDisplayLCS());

		return soapModel;
	}

	public static CorpProjectMessageSoap[] toSoapModels(
		CorpProjectMessage[] models) {
		CorpProjectMessageSoap[] soapModels = new CorpProjectMessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CorpProjectMessageSoap[][] toSoapModels(
		CorpProjectMessage[][] models) {
		CorpProjectMessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CorpProjectMessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CorpProjectMessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CorpProjectMessageSoap[] toSoapModels(
		List<CorpProjectMessage> models) {
		List<CorpProjectMessageSoap> soapModels = new ArrayList<CorpProjectMessageSoap>(models.size());

		for (CorpProjectMessage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CorpProjectMessageSoap[soapModels.size()]);
	}

	public CorpProjectMessageSoap() {
	}

	public long getPrimaryKey() {
		return _corpProjectMessageId;
	}

	public void setPrimaryKey(long pk) {
		setCorpProjectMessageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCorpProjectMessageId() {
		return _corpProjectMessageId;
	}

	public void setCorpProjectMessageId(long corpProjectMessageId) {
		_corpProjectMessageId = corpProjectMessageId;
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

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getSeverityLevel() {
		return _severityLevel;
	}

	public void setSeverityLevel(int severityLevel) {
		_severityLevel = severityLevel;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public boolean getDisplayCP() {
		return _displayCP;
	}

	public boolean isDisplayCP() {
		return _displayCP;
	}

	public void setDisplayCP(boolean displayCP) {
		_displayCP = displayCP;
	}

	public boolean getDisplayLCS() {
		return _displayLCS;
	}

	public boolean isDisplayLCS() {
		return _displayLCS;
	}

	public void setDisplayLCS(boolean displayLCS) {
		_displayLCS = displayLCS;
	}

	private String _uuid;
	private long _corpProjectMessageId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _corpProjectId;
	private int _type;
	private int _severityLevel;
	private String _title;
	private String _content;
	private boolean _displayCP;
	private boolean _displayLCS;
}