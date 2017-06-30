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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketCannedResponseServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TicketCannedResponseServiceSoap
 * @generated
 */
public class TicketCannedResponseSoap implements Serializable {
	public static TicketCannedResponseSoap toSoapModel(
		TicketCannedResponse model) {
		TicketCannedResponseSoap soapModel = new TicketCannedResponseSoap();

		soapModel.setTicketCannedResponseId(model.getTicketCannedResponseId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setContent(model.getContent());
		soapModel.setUseCount(model.getUseCount());

		return soapModel;
	}

	public static TicketCannedResponseSoap[] toSoapModels(
		TicketCannedResponse[] models) {
		TicketCannedResponseSoap[] soapModels = new TicketCannedResponseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketCannedResponseSoap[][] toSoapModels(
		TicketCannedResponse[][] models) {
		TicketCannedResponseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketCannedResponseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketCannedResponseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketCannedResponseSoap[] toSoapModels(
		List<TicketCannedResponse> models) {
		List<TicketCannedResponseSoap> soapModels = new ArrayList<TicketCannedResponseSoap>(models.size());

		for (TicketCannedResponse model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketCannedResponseSoap[soapModels.size()]);
	}

	public TicketCannedResponseSoap() {
	}

	public long getPrimaryKey() {
		return _ticketCannedResponseId;
	}

	public void setPrimaryKey(long pk) {
		setTicketCannedResponseId(pk);
	}

	public long getTicketCannedResponseId() {
		return _ticketCannedResponseId;
	}

	public void setTicketCannedResponseId(long ticketCannedResponseId) {
		_ticketCannedResponseId = ticketCannedResponseId;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getUseCount() {
		return _useCount;
	}

	public void setUseCount(int useCount) {
		_useCount = useCount;
	}

	private long _ticketCannedResponseId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _content;
	private int _useCount;
}