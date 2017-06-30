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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketInformationServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TicketInformationServiceSoap
 * @generated
 */
public class TicketInformationSoap implements Serializable {
	public static TicketInformationSoap toSoapModel(TicketInformation model) {
		TicketInformationSoap soapModel = new TicketInformationSoap();

		soapModel.setTicketInformationId(model.getTicketInformationId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setFieldId(model.getFieldId());
		soapModel.setData(model.getData());

		return soapModel;
	}

	public static TicketInformationSoap[] toSoapModels(
		TicketInformation[] models) {
		TicketInformationSoap[] soapModels = new TicketInformationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketInformationSoap[][] toSoapModels(
		TicketInformation[][] models) {
		TicketInformationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketInformationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketInformationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketInformationSoap[] toSoapModels(
		List<TicketInformation> models) {
		List<TicketInformationSoap> soapModels = new ArrayList<TicketInformationSoap>(models.size());

		for (TicketInformation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketInformationSoap[soapModels.size()]);
	}

	public TicketInformationSoap() {
	}

	public long getPrimaryKey() {
		return _ticketInformationId;
	}

	public void setPrimaryKey(long pk) {
		setTicketInformationId(pk);
	}

	public long getTicketInformationId() {
		return _ticketInformationId;
	}

	public void setTicketInformationId(long ticketInformationId) {
		_ticketInformationId = ticketInformationId;
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

	public long getFieldId() {
		return _fieldId;
	}

	public void setFieldId(long fieldId) {
		_fieldId = fieldId;
	}

	public String getData() {
		return _data;
	}

	public void setData(String data) {
		_data = data;
	}

	private long _ticketInformationId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ticketEntryId;
	private long _fieldId;
	private String _data;
}