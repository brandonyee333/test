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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketCallServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.TicketCallServiceSoap
 * @generated
 */
@ProviderType
public class TicketCallSoap implements Serializable {
	public static TicketCallSoap toSoapModel(TicketCall model) {
		TicketCallSoap soapModel = new TicketCallSoap();

		soapModel.setTicketCallId(model.getTicketCallId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setType(model.getType());
		soapModel.setCallDate(model.getCallDate());
		soapModel.setCallLength(model.getCallLength());
		soapModel.setCustomerName(model.getCustomerName());
		soapModel.setCustomerContact(model.getCustomerContact());
		soapModel.setConfirmation(model.getConfirmation());
		soapModel.setInstructions(model.getInstructions());

		return soapModel;
	}

	public static TicketCallSoap[] toSoapModels(TicketCall[] models) {
		TicketCallSoap[] soapModels = new TicketCallSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketCallSoap[][] toSoapModels(TicketCall[][] models) {
		TicketCallSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketCallSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketCallSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketCallSoap[] toSoapModels(List<TicketCall> models) {
		List<TicketCallSoap> soapModels = new ArrayList<TicketCallSoap>(models.size());

		for (TicketCall model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketCallSoap[soapModels.size()]);
	}

	public TicketCallSoap() {
	}

	public long getPrimaryKey() {
		return _ticketCallId;
	}

	public void setPrimaryKey(long pk) {
		setTicketCallId(pk);
	}

	public long getTicketCallId() {
		return _ticketCallId;
	}

	public void setTicketCallId(long ticketCallId) {
		_ticketCallId = ticketCallId;
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

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public Date getCallDate() {
		return _callDate;
	}

	public void setCallDate(Date callDate) {
		_callDate = callDate;
	}

	public long getCallLength() {
		return _callLength;
	}

	public void setCallLength(long callLength) {
		_callLength = callLength;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		_customerName = customerName;
	}

	public String getCustomerContact() {
		return _customerContact;
	}

	public void setCustomerContact(String customerContact) {
		_customerContact = customerContact;
	}

	public String getConfirmation() {
		return _confirmation;
	}

	public void setConfirmation(String confirmation) {
		_confirmation = confirmation;
	}

	public String getInstructions() {
		return _instructions;
	}

	public void setInstructions(String instructions) {
		_instructions = instructions;
	}

	private long _ticketCallId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private int _type;
	private Date _callDate;
	private long _callLength;
	private String _customerName;
	private String _customerContact;
	private String _confirmation;
	private String _instructions;
}