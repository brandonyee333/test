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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TicketAttachment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketAttachment
 * @generated
 */
public class TicketAttachmentWrapper implements TicketAttachment,
	ModelWrapper<TicketAttachment> {
	public TicketAttachmentWrapper(TicketAttachment ticketAttachment) {
		_ticketAttachment = ticketAttachment;
	}

	public Class<?> getModelClass() {
		return TicketAttachment.class;
	}

	public String getModelClassName() {
		return TicketAttachment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketAttachmentId", getTicketAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("ticketSolutionId", getTicketSolutionId());
		attributes.put("releaseNotesId", getReleaseNotesId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());
		attributes.put("visibility", getVisibility());
		attributes.put("extractedText", getExtractedText());
		attributes.put("availableFileRepositoryIds",
			getAvailableFileRepositoryIds());
		attributes.put("replicate", getReplicate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketAttachmentId = (Long)attributes.get("ticketAttachmentId");

		if (ticketAttachmentId != null) {
			setTicketAttachmentId(ticketAttachmentId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long ticketSolutionId = (Long)attributes.get("ticketSolutionId");

		if (ticketSolutionId != null) {
			setTicketSolutionId(ticketSolutionId);
		}

		Long releaseNotesId = (Long)attributes.get("releaseNotesId");

		if (releaseNotesId != null) {
			setReleaseNotesId(releaseNotesId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileSize = (Long)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String extractedText = (String)attributes.get("extractedText");

		if (extractedText != null) {
			setExtractedText(extractedText);
		}

		String availableFileRepositoryIds = (String)attributes.get(
				"availableFileRepositoryIds");

		if (availableFileRepositoryIds != null) {
			setAvailableFileRepositoryIds(availableFileRepositoryIds);
		}

		Boolean replicate = (Boolean)attributes.get("replicate");

		if (replicate != null) {
			setReplicate(replicate);
		}

		Date deleteDate = (Date)attributes.get("deleteDate");

		if (deleteDate != null) {
			setDeleteDate(deleteDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this ticket attachment.
	*
	* @return the primary key of this ticket attachment
	*/
	public long getPrimaryKey() {
		return _ticketAttachment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket attachment.
	*
	* @param primaryKey the primary key of this ticket attachment
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketAttachment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket attachment ID of this ticket attachment.
	*
	* @return the ticket attachment ID of this ticket attachment
	*/
	public long getTicketAttachmentId() {
		return _ticketAttachment.getTicketAttachmentId();
	}

	/**
	* Sets the ticket attachment ID of this ticket attachment.
	*
	* @param ticketAttachmentId the ticket attachment ID of this ticket attachment
	*/
	public void setTicketAttachmentId(long ticketAttachmentId) {
		_ticketAttachment.setTicketAttachmentId(ticketAttachmentId);
	}

	/**
	* Returns the user ID of this ticket attachment.
	*
	* @return the user ID of this ticket attachment
	*/
	public long getUserId() {
		return _ticketAttachment.getUserId();
	}

	/**
	* Sets the user ID of this ticket attachment.
	*
	* @param userId the user ID of this ticket attachment
	*/
	public void setUserId(long userId) {
		_ticketAttachment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket attachment.
	*
	* @return the user uuid of this ticket attachment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachment.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket attachment.
	*
	* @param userUuid the user uuid of this ticket attachment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketAttachment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this ticket attachment.
	*
	* @return the user name of this ticket attachment
	*/
	public java.lang.String getUserName() {
		return _ticketAttachment.getUserName();
	}

	/**
	* Sets the user name of this ticket attachment.
	*
	* @param userName the user name of this ticket attachment
	*/
	public void setUserName(java.lang.String userName) {
		_ticketAttachment.setUserName(userName);
	}

	/**
	* Returns the create date of this ticket attachment.
	*
	* @return the create date of this ticket attachment
	*/
	public java.util.Date getCreateDate() {
		return _ticketAttachment.getCreateDate();
	}

	/**
	* Sets the create date of this ticket attachment.
	*
	* @param createDate the create date of this ticket attachment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketAttachment.setCreateDate(createDate);
	}

	/**
	* Returns the ticket entry ID of this ticket attachment.
	*
	* @return the ticket entry ID of this ticket attachment
	*/
	public long getTicketEntryId() {
		return _ticketAttachment.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket attachment.
	*
	* @param ticketEntryId the ticket entry ID of this ticket attachment
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketAttachment.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the ticket solution ID of this ticket attachment.
	*
	* @return the ticket solution ID of this ticket attachment
	*/
	public long getTicketSolutionId() {
		return _ticketAttachment.getTicketSolutionId();
	}

	/**
	* Sets the ticket solution ID of this ticket attachment.
	*
	* @param ticketSolutionId the ticket solution ID of this ticket attachment
	*/
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketAttachment.setTicketSolutionId(ticketSolutionId);
	}

	/**
	* Returns the release notes ID of this ticket attachment.
	*
	* @return the release notes ID of this ticket attachment
	*/
	public long getReleaseNotesId() {
		return _ticketAttachment.getReleaseNotesId();
	}

	/**
	* Sets the release notes ID of this ticket attachment.
	*
	* @param releaseNotesId the release notes ID of this ticket attachment
	*/
	public void setReleaseNotesId(long releaseNotesId) {
		_ticketAttachment.setReleaseNotesId(releaseNotesId);
	}

	/**
	* Returns the file name of this ticket attachment.
	*
	* @return the file name of this ticket attachment
	*/
	public java.lang.String getFileName() {
		return _ticketAttachment.getFileName();
	}

	/**
	* Sets the file name of this ticket attachment.
	*
	* @param fileName the file name of this ticket attachment
	*/
	public void setFileName(java.lang.String fileName) {
		_ticketAttachment.setFileName(fileName);
	}

	/**
	* Returns the file size of this ticket attachment.
	*
	* @return the file size of this ticket attachment
	*/
	public long getFileSize() {
		return _ticketAttachment.getFileSize();
	}

	/**
	* Sets the file size of this ticket attachment.
	*
	* @param fileSize the file size of this ticket attachment
	*/
	public void setFileSize(long fileSize) {
		_ticketAttachment.setFileSize(fileSize);
	}

	/**
	* Returns the type of this ticket attachment.
	*
	* @return the type of this ticket attachment
	*/
	public int getType() {
		return _ticketAttachment.getType();
	}

	/**
	* Sets the type of this ticket attachment.
	*
	* @param type the type of this ticket attachment
	*/
	public void setType(int type) {
		_ticketAttachment.setType(type);
	}

	/**
	* Returns the visibility of this ticket attachment.
	*
	* @return the visibility of this ticket attachment
	*/
	public int getVisibility() {
		return _ticketAttachment.getVisibility();
	}

	/**
	* Sets the visibility of this ticket attachment.
	*
	* @param visibility the visibility of this ticket attachment
	*/
	public void setVisibility(int visibility) {
		_ticketAttachment.setVisibility(visibility);
	}

	/**
	* Returns the extracted text of this ticket attachment.
	*
	* @return the extracted text of this ticket attachment
	*/
	public java.lang.String getExtractedText() {
		return _ticketAttachment.getExtractedText();
	}

	/**
	* Sets the extracted text of this ticket attachment.
	*
	* @param extractedText the extracted text of this ticket attachment
	*/
	public void setExtractedText(java.lang.String extractedText) {
		_ticketAttachment.setExtractedText(extractedText);
	}

	/**
	* Returns the available file repository IDs of this ticket attachment.
	*
	* @return the available file repository IDs of this ticket attachment
	*/
	public java.lang.String getAvailableFileRepositoryIds() {
		return _ticketAttachment.getAvailableFileRepositoryIds();
	}

	/**
	* Sets the available file repository IDs of this ticket attachment.
	*
	* @param availableFileRepositoryIds the available file repository IDs of this ticket attachment
	*/
	public void setAvailableFileRepositoryIds(
		java.lang.String availableFileRepositoryIds) {
		_ticketAttachment.setAvailableFileRepositoryIds(availableFileRepositoryIds);
	}

	/**
	* Returns the replicate of this ticket attachment.
	*
	* @return the replicate of this ticket attachment
	*/
	public boolean getReplicate() {
		return _ticketAttachment.getReplicate();
	}

	/**
	* Returns <code>true</code> if this ticket attachment is replicate.
	*
	* @return <code>true</code> if this ticket attachment is replicate; <code>false</code> otherwise
	*/
	public boolean isReplicate() {
		return _ticketAttachment.isReplicate();
	}

	/**
	* Sets whether this ticket attachment is replicate.
	*
	* @param replicate the replicate of this ticket attachment
	*/
	public void setReplicate(boolean replicate) {
		_ticketAttachment.setReplicate(replicate);
	}

	/**
	* Returns the delete date of this ticket attachment.
	*
	* @return the delete date of this ticket attachment
	*/
	public java.util.Date getDeleteDate() {
		return _ticketAttachment.getDeleteDate();
	}

	/**
	* Sets the delete date of this ticket attachment.
	*
	* @param deleteDate the delete date of this ticket attachment
	*/
	public void setDeleteDate(java.util.Date deleteDate) {
		_ticketAttachment.setDeleteDate(deleteDate);
	}

	/**
	* Returns the status of this ticket attachment.
	*
	* @return the status of this ticket attachment
	*/
	public int getStatus() {
		return _ticketAttachment.getStatus();
	}

	/**
	* Sets the status of this ticket attachment.
	*
	* @param status the status of this ticket attachment
	*/
	public void setStatus(int status) {
		_ticketAttachment.setStatus(status);
	}

	public boolean isNew() {
		return _ticketAttachment.isNew();
	}

	public void setNew(boolean n) {
		_ticketAttachment.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketAttachment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketAttachment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketAttachment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketAttachment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketAttachment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketAttachmentWrapper((TicketAttachment)_ticketAttachment.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return _ticketAttachment.compareTo(ticketAttachment);
	}

	@Override
	public int hashCode() {
		return _ticketAttachment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketAttachment> toCacheModel() {
		return _ticketAttachment.toCacheModel();
	}

	public com.liferay.osb.model.TicketAttachment toEscapedModel() {
		return new TicketAttachmentWrapper(_ticketAttachment.toEscapedModel());
	}

	public com.liferay.osb.model.TicketAttachment toUnescapedModel() {
		return new TicketAttachmentWrapper(_ticketAttachment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketAttachment.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketAttachment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketAttachment.persist();
	}

	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachment.fileExists();
	}

	public java.util.Set<java.lang.String> getAvailableFileRepositoryIdsSet() {
		return _ticketAttachment.getAvailableFileRepositoryIdsSet();
	}

	public int getContentLength() {
		return _ticketAttachment.getContentLength();
	}

	public java.io.File getFile() {
		return _ticketAttachment.getFile();
	}

	public java.lang.String getFilePath() {
		return _ticketAttachment.getFilePath();
	}

	public java.lang.String getKey() {
		return _ticketAttachment.getKey();
	}

	public com.liferay.osb.model.TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachment.getTicketEntry();
	}

	public java.lang.String getTypeLabel() {
		return _ticketAttachment.getTypeLabel();
	}

	public java.lang.String getVisibilityLabel() {
		return _ticketAttachment.getVisibilityLabel();
	}

	public void setAvailableFileRepositoryIdsSet(
		java.util.Set<java.lang.String> availableFileRepositoryIds) {
		_ticketAttachment.setAvailableFileRepositoryIdsSet(availableFileRepositoryIds);
	}

	public void setFile(java.io.File file) {
		_ticketAttachment.setFile(file);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketAttachmentWrapper)) {
			return false;
		}

		TicketAttachmentWrapper ticketAttachmentWrapper = (TicketAttachmentWrapper)obj;

		if (Validator.equals(_ticketAttachment,
					ticketAttachmentWrapper._ticketAttachment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketAttachment getWrappedTicketAttachment() {
		return _ticketAttachment;
	}

	public TicketAttachment getWrappedModel() {
		return _ticketAttachment;
	}

	public void resetOriginalValues() {
		_ticketAttachment.resetOriginalValues();
	}

	private TicketAttachment _ticketAttachment;
}