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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketAttachmentServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TicketAttachmentServiceSoap
 * @generated
 */
public class TicketAttachmentSoap implements Serializable {
	public static TicketAttachmentSoap toSoapModel(TicketAttachment model) {
		TicketAttachmentSoap soapModel = new TicketAttachmentSoap();

		soapModel.setTicketAttachmentId(model.getTicketAttachmentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setTicketSolutionId(model.getTicketSolutionId());
		soapModel.setReleaseNotesId(model.getReleaseNotesId());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileSize(model.getFileSize());
		soapModel.setType(model.getType());
		soapModel.setVisibility(model.getVisibility());
		soapModel.setExtractedText(model.getExtractedText());
		soapModel.setAvailableFileRepositoryIds(model.getAvailableFileRepositoryIds());
		soapModel.setReplicate(model.getReplicate());
		soapModel.setDeleteDate(model.getDeleteDate());
		soapModel.setStatus(model.getStatus());

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

	public long getReleaseNotesId() {
		return _releaseNotesId;
	}

	public void setReleaseNotesId(long releaseNotesId) {
		_releaseNotesId = releaseNotesId;
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

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	public String getExtractedText() {
		return _extractedText;
	}

	public void setExtractedText(String extractedText) {
		_extractedText = extractedText;
	}

	public String getAvailableFileRepositoryIds() {
		return _availableFileRepositoryIds;
	}

	public void setAvailableFileRepositoryIds(String availableFileRepositoryIds) {
		_availableFileRepositoryIds = availableFileRepositoryIds;
	}

	public boolean getReplicate() {
		return _replicate;
	}

	public boolean isReplicate() {
		return _replicate;
	}

	public void setReplicate(boolean replicate) {
		_replicate = replicate;
	}

	public Date getDeleteDate() {
		return _deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		_deleteDate = deleteDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _ticketAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private long _ticketSolutionId;
	private long _releaseNotesId;
	private String _fileName;
	private long _fileSize;
	private int _type;
	private int _visibility;
	private String _extractedText;
	private String _availableFileRepositoryIds;
	private boolean _replicate;
	private Date _deleteDate;
	private int _status;
}