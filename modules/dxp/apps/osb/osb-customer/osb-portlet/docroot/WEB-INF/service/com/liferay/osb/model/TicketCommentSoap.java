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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketCommentServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TicketCommentServiceSoap
 * @generated
 */
public class TicketCommentSoap implements Serializable {
	public static TicketCommentSoap toSoapModel(TicketComment model) {
		TicketCommentSoap soapModel = new TicketCommentSoap();

		soapModel.setTicketCommentId(model.getTicketCommentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setBody(model.getBody());
		soapModel.setType(model.getType());
		soapModel.setFormat(model.getFormat());
		soapModel.setVisibility(model.getVisibility());
		soapModel.setSettings(model.getSettings());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TicketCommentSoap[] toSoapModels(TicketComment[] models) {
		TicketCommentSoap[] soapModels = new TicketCommentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketCommentSoap[][] toSoapModels(TicketComment[][] models) {
		TicketCommentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketCommentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketCommentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketCommentSoap[] toSoapModels(List<TicketComment> models) {
		List<TicketCommentSoap> soapModels = new ArrayList<TicketCommentSoap>(models.size());

		for (TicketComment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketCommentSoap[soapModels.size()]);
	}

	public TicketCommentSoap() {
	}

	public long getPrimaryKey() {
		return _ticketCommentId;
	}

	public void setPrimaryKey(long pk) {
		setTicketCommentId(pk);
	}

	public long getTicketCommentId() {
		return _ticketCommentId;
	}

	public void setTicketCommentId(long ticketCommentId) {
		_ticketCommentId = ticketCommentId;
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

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getFormat() {
		return _format;
	}

	public void setFormat(String format) {
		_format = format;
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	public String getSettings() {
		return _settings;
	}

	public void setSettings(String settings) {
		_settings = settings;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _ticketCommentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ticketEntryId;
	private String _body;
	private int _type;
	private String _format;
	private int _visibility;
	private String _settings;
	private int _status;
}