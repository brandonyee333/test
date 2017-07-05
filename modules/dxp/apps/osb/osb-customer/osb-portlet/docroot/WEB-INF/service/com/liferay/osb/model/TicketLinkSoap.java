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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketLinkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.TicketLinkServiceSoap
 * @generated
 */
@ProviderType
public class TicketLinkSoap implements Serializable {
	public static TicketLinkSoap toSoapModel(TicketLink model) {
		TicketLinkSoap soapModel = new TicketLinkSoap();

		soapModel.setTicketLinkId(model.getTicketLinkId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setTicketSolutionId(model.getTicketSolutionId());
		soapModel.setUrl(model.getUrl());
		soapModel.setType(model.getType());
		soapModel.setVisibility(model.getVisibility());

		return soapModel;
	}

	public static TicketLinkSoap[] toSoapModels(TicketLink[] models) {
		TicketLinkSoap[] soapModels = new TicketLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketLinkSoap[][] toSoapModels(TicketLink[][] models) {
		TicketLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketLinkSoap[] toSoapModels(List<TicketLink> models) {
		List<TicketLinkSoap> soapModels = new ArrayList<TicketLinkSoap>(models.size());

		for (TicketLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketLinkSoap[soapModels.size()]);
	}

	public TicketLinkSoap() {
	}

	public long getPrimaryKey() {
		return _ticketLinkId;
	}

	public void setPrimaryKey(long pk) {
		setTicketLinkId(pk);
	}

	public long getTicketLinkId() {
		return _ticketLinkId;
	}

	public void setTicketLinkId(long ticketLinkId) {
		_ticketLinkId = ticketLinkId;
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

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;
	}

	public long getTicketSolutionId() {
		return _ticketSolutionId;
	}

	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketSolutionId = ticketSolutionId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	private long _ticketLinkId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private long _ticketSolutionId;
	private String _url;
	private int _type;
	private int _visibility;
}