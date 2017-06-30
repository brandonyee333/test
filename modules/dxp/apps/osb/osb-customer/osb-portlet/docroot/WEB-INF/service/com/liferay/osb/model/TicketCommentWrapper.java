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
 * This class is a wrapper for {@link TicketComment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketComment
 * @generated
 */
public class TicketCommentWrapper implements TicketComment,
	ModelWrapper<TicketComment> {
	public TicketCommentWrapper(TicketComment ticketComment) {
		_ticketComment = ticketComment;
	}

	public Class<?> getModelClass() {
		return TicketComment.class;
	}

	public String getModelClassName() {
		return TicketComment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketCommentId", getTicketCommentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("body", getBody());
		attributes.put("type", getType());
		attributes.put("format", getFormat());
		attributes.put("visibility", getVisibility());
		attributes.put("settings", getSettings());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketCommentId = (Long)attributes.get("ticketCommentId");

		if (ticketCommentId != null) {
			setTicketCommentId(ticketCommentId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String format = (String)attributes.get("format");

		if (format != null) {
			setFormat(format);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this ticket comment.
	*
	* @return the primary key of this ticket comment
	*/
	public long getPrimaryKey() {
		return _ticketComment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket comment.
	*
	* @param primaryKey the primary key of this ticket comment
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketComment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket comment ID of this ticket comment.
	*
	* @return the ticket comment ID of this ticket comment
	*/
	public long getTicketCommentId() {
		return _ticketComment.getTicketCommentId();
	}

	/**
	* Sets the ticket comment ID of this ticket comment.
	*
	* @param ticketCommentId the ticket comment ID of this ticket comment
	*/
	public void setTicketCommentId(long ticketCommentId) {
		_ticketComment.setTicketCommentId(ticketCommentId);
	}

	/**
	* Returns the user ID of this ticket comment.
	*
	* @return the user ID of this ticket comment
	*/
	public long getUserId() {
		return _ticketComment.getUserId();
	}

	/**
	* Sets the user ID of this ticket comment.
	*
	* @param userId the user ID of this ticket comment
	*/
	public void setUserId(long userId) {
		_ticketComment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket comment.
	*
	* @return the user uuid of this ticket comment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketComment.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket comment.
	*
	* @param userUuid the user uuid of this ticket comment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketComment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this ticket comment.
	*
	* @return the user name of this ticket comment
	*/
	public java.lang.String getUserName() {
		return _ticketComment.getUserName();
	}

	/**
	* Sets the user name of this ticket comment.
	*
	* @param userName the user name of this ticket comment
	*/
	public void setUserName(java.lang.String userName) {
		_ticketComment.setUserName(userName);
	}

	/**
	* Returns the create date of this ticket comment.
	*
	* @return the create date of this ticket comment
	*/
	public java.util.Date getCreateDate() {
		return _ticketComment.getCreateDate();
	}

	/**
	* Sets the create date of this ticket comment.
	*
	* @param createDate the create date of this ticket comment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketComment.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this ticket comment.
	*
	* @return the modified date of this ticket comment
	*/
	public java.util.Date getModifiedDate() {
		return _ticketComment.getModifiedDate();
	}

	/**
	* Sets the modified date of this ticket comment.
	*
	* @param modifiedDate the modified date of this ticket comment
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_ticketComment.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the ticket entry ID of this ticket comment.
	*
	* @return the ticket entry ID of this ticket comment
	*/
	public long getTicketEntryId() {
		return _ticketComment.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket comment.
	*
	* @param ticketEntryId the ticket entry ID of this ticket comment
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketComment.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the body of this ticket comment.
	*
	* @return the body of this ticket comment
	*/
	public java.lang.String getBody() {
		return _ticketComment.getBody();
	}

	/**
	* Sets the body of this ticket comment.
	*
	* @param body the body of this ticket comment
	*/
	public void setBody(java.lang.String body) {
		_ticketComment.setBody(body);
	}

	/**
	* Returns the type of this ticket comment.
	*
	* @return the type of this ticket comment
	*/
	public int getType() {
		return _ticketComment.getType();
	}

	/**
	* Sets the type of this ticket comment.
	*
	* @param type the type of this ticket comment
	*/
	public void setType(int type) {
		_ticketComment.setType(type);
	}

	/**
	* Returns the format of this ticket comment.
	*
	* @return the format of this ticket comment
	*/
	public java.lang.String getFormat() {
		return _ticketComment.getFormat();
	}

	/**
	* Sets the format of this ticket comment.
	*
	* @param format the format of this ticket comment
	*/
	public void setFormat(java.lang.String format) {
		_ticketComment.setFormat(format);
	}

	/**
	* Returns the visibility of this ticket comment.
	*
	* @return the visibility of this ticket comment
	*/
	public int getVisibility() {
		return _ticketComment.getVisibility();
	}

	/**
	* Sets the visibility of this ticket comment.
	*
	* @param visibility the visibility of this ticket comment
	*/
	public void setVisibility(int visibility) {
		_ticketComment.setVisibility(visibility);
	}

	/**
	* Returns the settings of this ticket comment.
	*
	* @return the settings of this ticket comment
	*/
	public java.lang.String getSettings() {
		return _ticketComment.getSettings();
	}

	/**
	* Sets the settings of this ticket comment.
	*
	* @param settings the settings of this ticket comment
	*/
	public void setSettings(java.lang.String settings) {
		_ticketComment.setSettings(settings);
	}

	/**
	* Returns the status of this ticket comment.
	*
	* @return the status of this ticket comment
	*/
	public int getStatus() {
		return _ticketComment.getStatus();
	}

	/**
	* Sets the status of this ticket comment.
	*
	* @param status the status of this ticket comment
	*/
	public void setStatus(int status) {
		_ticketComment.setStatus(status);
	}

	public boolean isNew() {
		return _ticketComment.isNew();
	}

	public void setNew(boolean n) {
		_ticketComment.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketComment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketComment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketComment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketComment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketComment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketComment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketComment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketCommentWrapper((TicketComment)_ticketComment.clone());
	}

	public int compareTo(com.liferay.osb.model.TicketComment ticketComment) {
		return _ticketComment.compareTo(ticketComment);
	}

	@Override
	public int hashCode() {
		return _ticketComment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketComment> toCacheModel() {
		return _ticketComment.toCacheModel();
	}

	public com.liferay.osb.model.TicketComment toEscapedModel() {
		return new TicketCommentWrapper(_ticketComment.toEscapedModel());
	}

	public com.liferay.osb.model.TicketComment toUnescapedModel() {
		return new TicketCommentWrapper(_ticketComment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketComment.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketComment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketComment.persist();
	}

	public java.lang.String getKey() {
		return _ticketComment.getKey();
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties() {
		return _ticketComment.getSettingsProperties();
	}

	public java.lang.String getSettingsProperty(java.lang.String key) {
		return _ticketComment.getSettingsProperty(key);
	}

	public java.lang.String getVisibilityLabel() {
		return _ticketComment.getVisibilityLabel();
	}

	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties) {
		_ticketComment.setSettingsProperties(settingsProperties);
	}

	public void setSettingsProperty(java.lang.String key, java.lang.String value) {
		_ticketComment.setSettingsProperty(key, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketCommentWrapper)) {
			return false;
		}

		TicketCommentWrapper ticketCommentWrapper = (TicketCommentWrapper)obj;

		if (Validator.equals(_ticketComment, ticketCommentWrapper._ticketComment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketComment getWrappedTicketComment() {
		return _ticketComment;
	}

	public TicketComment getWrappedModel() {
		return _ticketComment;
	}

	public void resetOriginalValues() {
		_ticketComment.resetOriginalValues();
	}

	private TicketComment _ticketComment;
}