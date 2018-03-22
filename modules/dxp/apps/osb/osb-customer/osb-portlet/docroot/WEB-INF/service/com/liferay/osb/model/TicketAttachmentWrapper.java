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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TicketAttachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachment
 * @generated
 */
@ProviderType
public class TicketAttachmentWrapper implements TicketAttachment,
	ModelWrapper<TicketAttachment> {
	public TicketAttachmentWrapper(TicketAttachment ticketAttachment) {
		_ticketAttachment = ticketAttachment;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return TicketAttachment.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public TicketAttachment toEscapedModel() {
		return new TicketAttachmentWrapper(_ticketAttachment.toEscapedModel());
	}

	@Override
	public TicketAttachment toUnescapedModel() {
		return new TicketAttachmentWrapper(_ticketAttachment.toUnescapedModel());
	}

	@Override
	public TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachment.getTicketEntry();
	}

	@Override
	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachment.fileExists();
	}

	/**
	* Returns the replicate of this ticket attachment.
	*
	* @return the replicate of this ticket attachment
	*/
	@Override
	public boolean getReplicate() {
		return _ticketAttachment.getReplicate();
	}

	@Override
	public boolean isCachedModel() {
		return _ticketAttachment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketAttachment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketAttachment.isNew();
	}

	/**
	* Returns <code>true</code> if this ticket attachment is replicate.
	*
	* @return <code>true</code> if this ticket attachment is replicate; <code>false</code> otherwise
	*/
	@Override
	public boolean isReplicate() {
		return _ticketAttachment.isReplicate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketAttachment.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketAttachment> toCacheModel() {
		return _ticketAttachment.toCacheModel();
	}

	@Override
	public int compareTo(TicketAttachment ticketAttachment) {
		return _ticketAttachment.compareTo(ticketAttachment);
	}

	@Override
	public int getContentLength() {
		return _ticketAttachment.getContentLength();
	}

	/**
	* Returns the status of this ticket attachment.
	*
	* @return the status of this ticket attachment
	*/
	@Override
	public int getStatus() {
		return _ticketAttachment.getStatus();
	}

	/**
	* Returns the type of this ticket attachment.
	*
	* @return the type of this ticket attachment
	*/
	@Override
	public int getType() {
		return _ticketAttachment.getType();
	}

	/**
	* Returns the visibility of this ticket attachment.
	*
	* @return the visibility of this ticket attachment
	*/
	@Override
	public int getVisibility() {
		return _ticketAttachment.getVisibility();
	}

	@Override
	public int hashCode() {
		return _ticketAttachment.hashCode();
	}

	@Override
	public java.io.File getFile() {
		return _ticketAttachment.getFile();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketAttachment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketAttachmentWrapper((TicketAttachment)_ticketAttachment.clone());
	}

	/**
	* Returns the available file repository IDs of this ticket attachment.
	*
	* @return the available file repository IDs of this ticket attachment
	*/
	@Override
	public java.lang.String getAvailableFileRepositoryIds() {
		return _ticketAttachment.getAvailableFileRepositoryIds();
	}

	/**
	* Returns the extracted text of this ticket attachment.
	*
	* @return the extracted text of this ticket attachment
	*/
	@Override
	public java.lang.String getExtractedText() {
		return _ticketAttachment.getExtractedText();
	}

	/**
	* Returns the file name of this ticket attachment.
	*
	* @return the file name of this ticket attachment
	*/
	@Override
	public java.lang.String getFileName() {
		return _ticketAttachment.getFileName();
	}

	@Override
	public java.lang.String getFilePath() {
		return _ticketAttachment.getFilePath();
	}

	@Override
	public java.lang.String getKey() {
		return _ticketAttachment.getKey();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _ticketAttachment.getTypeLabel();
	}

	/**
	* Returns the user name of this ticket attachment.
	*
	* @return the user name of this ticket attachment
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketAttachment.getUserName();
	}

	/**
	* Returns the user uuid of this ticket attachment.
	*
	* @return the user uuid of this ticket attachment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketAttachment.getUserUuid();
	}

	@Override
	public java.lang.String getVisibilityLabel() {
		return _ticketAttachment.getVisibilityLabel();
	}

	@Override
	public java.lang.String toString() {
		return _ticketAttachment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketAttachment.toXmlString();
	}

	/**
	* Returns the create date of this ticket attachment.
	*
	* @return the create date of this ticket attachment
	*/
	@Override
	public Date getCreateDate() {
		return _ticketAttachment.getCreateDate();
	}

	/**
	* Returns the delete date of this ticket attachment.
	*
	* @return the delete date of this ticket attachment
	*/
	@Override
	public Date getDeleteDate() {
		return _ticketAttachment.getDeleteDate();
	}

	@Override
	public java.util.Set<java.lang.String> getAvailableFileRepositoryIdsSet() {
		return _ticketAttachment.getAvailableFileRepositoryIdsSet();
	}

	/**
	* Returns the file size of this ticket attachment.
	*
	* @return the file size of this ticket attachment
	*/
	@Override
	public long getFileSize() {
		return _ticketAttachment.getFileSize();
	}

	/**
	* Returns the primary key of this ticket attachment.
	*
	* @return the primary key of this ticket attachment
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketAttachment.getPrimaryKey();
	}

	/**
	* Returns the release notes ID of this ticket attachment.
	*
	* @return the release notes ID of this ticket attachment
	*/
	@Override
	public long getReleaseNotesId() {
		return _ticketAttachment.getReleaseNotesId();
	}

	/**
	* Returns the ticket attachment ID of this ticket attachment.
	*
	* @return the ticket attachment ID of this ticket attachment
	*/
	@Override
	public long getTicketAttachmentId() {
		return _ticketAttachment.getTicketAttachmentId();
	}

	/**
	* Returns the ticket entry ID of this ticket attachment.
	*
	* @return the ticket entry ID of this ticket attachment
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketAttachment.getTicketEntryId();
	}

	/**
	* Returns the ticket solution ID of this ticket attachment.
	*
	* @return the ticket solution ID of this ticket attachment
	*/
	@Override
	public long getTicketSolutionId() {
		return _ticketAttachment.getTicketSolutionId();
	}

	/**
	* Returns the user ID of this ticket attachment.
	*
	* @return the user ID of this ticket attachment
	*/
	@Override
	public long getUserId() {
		return _ticketAttachment.getUserId();
	}

	@Override
	public void persist() {
		_ticketAttachment.persist();
	}

	/**
	* Sets the available file repository IDs of this ticket attachment.
	*
	* @param availableFileRepositoryIds the available file repository IDs of this ticket attachment
	*/
	@Override
	public void setAvailableFileRepositoryIds(
		java.lang.String availableFileRepositoryIds) {
		_ticketAttachment.setAvailableFileRepositoryIds(availableFileRepositoryIds);
	}

	@Override
	public void setAvailableFileRepositoryIdsSet(
		java.util.Set<java.lang.String> availableFileRepositoryIds) {
		_ticketAttachment.setAvailableFileRepositoryIdsSet(availableFileRepositoryIds);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketAttachment.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this ticket attachment.
	*
	* @param createDate the create date of this ticket attachment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketAttachment.setCreateDate(createDate);
	}

	/**
	* Sets the delete date of this ticket attachment.
	*
	* @param deleteDate the delete date of this ticket attachment
	*/
	@Override
	public void setDeleteDate(Date deleteDate) {
		_ticketAttachment.setDeleteDate(deleteDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketAttachment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketAttachment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extracted text of this ticket attachment.
	*
	* @param extractedText the extracted text of this ticket attachment
	*/
	@Override
	public void setExtractedText(java.lang.String extractedText) {
		_ticketAttachment.setExtractedText(extractedText);
	}

	@Override
	public void setFile(java.io.File file) {
		_ticketAttachment.setFile(file);
	}

	/**
	* Sets the file name of this ticket attachment.
	*
	* @param fileName the file name of this ticket attachment
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_ticketAttachment.setFileName(fileName);
	}

	/**
	* Sets the file size of this ticket attachment.
	*
	* @param fileSize the file size of this ticket attachment
	*/
	@Override
	public void setFileSize(long fileSize) {
		_ticketAttachment.setFileSize(fileSize);
	}

	@Override
	public void setNew(boolean n) {
		_ticketAttachment.setNew(n);
	}

	/**
	* Sets the primary key of this ticket attachment.
	*
	* @param primaryKey the primary key of this ticket attachment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketAttachment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the release notes ID of this ticket attachment.
	*
	* @param releaseNotesId the release notes ID of this ticket attachment
	*/
	@Override
	public void setReleaseNotesId(long releaseNotesId) {
		_ticketAttachment.setReleaseNotesId(releaseNotesId);
	}

	/**
	* Sets whether this ticket attachment is replicate.
	*
	* @param replicate the replicate of this ticket attachment
	*/
	@Override
	public void setReplicate(boolean replicate) {
		_ticketAttachment.setReplicate(replicate);
	}

	/**
	* Sets the status of this ticket attachment.
	*
	* @param status the status of this ticket attachment
	*/
	@Override
	public void setStatus(int status) {
		_ticketAttachment.setStatus(status);
	}

	/**
	* Sets the ticket attachment ID of this ticket attachment.
	*
	* @param ticketAttachmentId the ticket attachment ID of this ticket attachment
	*/
	@Override
	public void setTicketAttachmentId(long ticketAttachmentId) {
		_ticketAttachment.setTicketAttachmentId(ticketAttachmentId);
	}

	/**
	* Sets the ticket entry ID of this ticket attachment.
	*
	* @param ticketEntryId the ticket entry ID of this ticket attachment
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketAttachment.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket solution ID of this ticket attachment.
	*
	* @param ticketSolutionId the ticket solution ID of this ticket attachment
	*/
	@Override
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketAttachment.setTicketSolutionId(ticketSolutionId);
	}

	/**
	* Sets the type of this ticket attachment.
	*
	* @param type the type of this ticket attachment
	*/
	@Override
	public void setType(int type) {
		_ticketAttachment.setType(type);
	}

	/**
	* Sets the user ID of this ticket attachment.
	*
	* @param userId the user ID of this ticket attachment
	*/
	@Override
	public void setUserId(long userId) {
		_ticketAttachment.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket attachment.
	*
	* @param userName the user name of this ticket attachment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketAttachment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket attachment.
	*
	* @param userUuid the user uuid of this ticket attachment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketAttachment.setUserUuid(userUuid);
	}

	/**
	* Sets the visibility of this ticket attachment.
	*
	* @param visibility the visibility of this ticket attachment
	*/
	@Override
	public void setVisibility(int visibility) {
		_ticketAttachment.setVisibility(visibility);
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

		if (Objects.equals(_ticketAttachment,
					ticketAttachmentWrapper._ticketAttachment)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketAttachment getWrappedModel() {
		return _ticketAttachment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketAttachment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketAttachment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketAttachment.resetOriginalValues();
	}

	private final TicketAttachment _ticketAttachment;
}