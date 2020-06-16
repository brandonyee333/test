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

package com.liferay.osb.customer.ticket.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.ticket.service.http.TicketAttachmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.ticket.service.http.TicketAttachmentServiceSoap
 * @generated
 */
@ProviderType
public class TicketAttachmentSoap implements Serializable {
	public static TicketAttachmentSoap toSoapModel(TicketAttachment model) {
		TicketAttachmentSoap soapModel = new TicketAttachmentSoap();

		soapModel.setTicketAttachmentId(model.getTicketAttachmentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setZendeskTicketId(model.getZendeskTicketId());
		soapModel.setFileRepositoryId(model.getFileRepositoryId());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileSize(model.getFileSize());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static TicketAttachmentSoap[] toSoapModels(TicketAttachment[] models) {
		TicketAttachmentSoap[] soapModels = new TicketAttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketAttachmentSoap[][] toSoapModels(
		TicketAttachment[][] models) {
		TicketAttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketAttachmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketAttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketAttachmentSoap[] toSoapModels(
		List<TicketAttachment> models) {
		List<TicketAttachmentSoap> soapModels = new ArrayList<TicketAttachmentSoap>(models.size());

		for (TicketAttachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketAttachmentSoap[soapModels.size()]);
	}

	public TicketAttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _ticketAttachmentId;
	}

	public void setPrimaryKey(long pk) {
		setTicketAttachmentId(pk);
	}

	public long getTicketAttachmentId() {
		return _ticketAttachmentId;
	}

	public void setTicketAttachmentId(long ticketAttachmentId) {
		_ticketAttachmentId = ticketAttachmentId;
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

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getZendeskTicketId() {
		return _zendeskTicketId;
	}

	public void setZendeskTicketId(long zendeskTicketId) {
		_zendeskTicketId = zendeskTicketId;
	}

	public String getFileRepositoryId() {
		return _fileRepositoryId;
	}

	public void setFileRepositoryId(String fileRepositoryId) {
		_fileRepositoryId = fileRepositoryId;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getFileSize() {
		return _fileSize;
	}

	public void setFileSize(long fileSize) {
		_fileSize = fileSize;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _ticketAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private long _zendeskTicketId;
	private String _fileRepositoryId;
	private String _fileName;
	private long _fileSize;
	private int _type;
}