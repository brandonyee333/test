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
 * This class is a wrapper for {@link TicketComment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketComment
 * @generated
 */
@ProviderType
public class TicketCommentWrapper implements TicketComment,
	ModelWrapper<TicketComment> {
	public TicketCommentWrapper(TicketComment ticketComment) {
		_ticketComment = ticketComment;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketComment.class;
	}

	@Override
	public String getModelClassName() {
		return TicketComment.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public TicketComment toEscapedModel() {
		return new TicketCommentWrapper(_ticketComment.toEscapedModel());
	}

	@Override
	public TicketComment toUnescapedModel() {
		return new TicketCommentWrapper(_ticketComment.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketComment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketComment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketComment.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketComment.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketComment> toCacheModel() {
		return _ticketComment.toCacheModel();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties() {
		return _ticketComment.getSettingsProperties();
	}

	@Override
	public int compareTo(TicketComment ticketComment) {
		return _ticketComment.compareTo(ticketComment);
	}

	/**
	* Returns the status of this ticket comment.
	*
	* @return the status of this ticket comment
	*/
	@Override
	public int getStatus() {
		return _ticketComment.getStatus();
	}

	/**
	* Returns the type of this ticket comment.
	*
	* @return the type of this ticket comment
	*/
	@Override
	public int getType() {
		return _ticketComment.getType();
	}

	/**
	* Returns the visibility of this ticket comment.
	*
	* @return the visibility of this ticket comment
	*/
	@Override
	public int getVisibility() {
		return _ticketComment.getVisibility();
	}

	@Override
	public int hashCode() {
		return _ticketComment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketComment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketCommentWrapper((TicketComment)_ticketComment.clone());
	}

	/**
	* Returns the body of this ticket comment.
	*
	* @return the body of this ticket comment
	*/
	@Override
	public java.lang.String getBody() {
		return _ticketComment.getBody();
	}

	/**
	* Returns the format of this ticket comment.
	*
	* @return the format of this ticket comment
	*/
	@Override
	public java.lang.String getFormat() {
		return _ticketComment.getFormat();
	}

	@Override
	public java.lang.String getKey() {
		return _ticketComment.getKey();
	}

	/**
	* Returns the settings of this ticket comment.
	*
	* @return the settings of this ticket comment
	*/
	@Override
	public java.lang.String getSettings() {
		return _ticketComment.getSettings();
	}

	@Override
	public java.lang.String getSettingsProperty(java.lang.String key) {
		return _ticketComment.getSettingsProperty(key);
	}

	/**
	* Returns the user name of this ticket comment.
	*
	* @return the user name of this ticket comment
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketComment.getUserName();
	}

	/**
	* Returns the user uuid of this ticket comment.
	*
	* @return the user uuid of this ticket comment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketComment.getUserUuid();
	}

	@Override
	public java.lang.String getVisibilityLabel() {
		return _ticketComment.getVisibilityLabel();
	}

	@Override
	public java.lang.String toString() {
		return _ticketComment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketComment.toXmlString();
	}

	/**
	* Returns the create date of this ticket comment.
	*
	* @return the create date of this ticket comment
	*/
	@Override
	public Date getCreateDate() {
		return _ticketComment.getCreateDate();
	}

	/**
	* Returns the modified date of this ticket comment.
	*
	* @return the modified date of this ticket comment
	*/
	@Override
	public Date getModifiedDate() {
		return _ticketComment.getModifiedDate();
	}

	/**
	* Returns the primary key of this ticket comment.
	*
	* @return the primary key of this ticket comment
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketComment.getPrimaryKey();
	}

	/**
	* Returns the ticket comment ID of this ticket comment.
	*
	* @return the ticket comment ID of this ticket comment
	*/
	@Override
	public long getTicketCommentId() {
		return _ticketComment.getTicketCommentId();
	}

	/**
	* Returns the ticket entry ID of this ticket comment.
	*
	* @return the ticket entry ID of this ticket comment
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketComment.getTicketEntryId();
	}

	/**
	* Returns the user ID of this ticket comment.
	*
	* @return the user ID of this ticket comment
	*/
	@Override
	public long getUserId() {
		return _ticketComment.getUserId();
	}

	@Override
	public void persist() {
		_ticketComment.persist();
	}

	/**
	* Sets the body of this ticket comment.
	*
	* @param body the body of this ticket comment
	*/
	@Override
	public void setBody(java.lang.String body) {
		_ticketComment.setBody(body);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketComment.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this ticket comment.
	*
	* @param createDate the create date of this ticket comment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketComment.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketComment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketComment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketComment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the format of this ticket comment.
	*
	* @param format the format of this ticket comment
	*/
	@Override
	public void setFormat(java.lang.String format) {
		_ticketComment.setFormat(format);
	}

	/**
	* Sets the modified date of this ticket comment.
	*
	* @param modifiedDate the modified date of this ticket comment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ticketComment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ticketComment.setNew(n);
	}

	/**
	* Sets the primary key of this ticket comment.
	*
	* @param primaryKey the primary key of this ticket comment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketComment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketComment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the settings of this ticket comment.
	*
	* @param settings the settings of this ticket comment
	*/
	@Override
	public void setSettings(java.lang.String settings) {
		_ticketComment.setSettings(settings);
	}

	@Override
	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties) {
		_ticketComment.setSettingsProperties(settingsProperties);
	}

	@Override
	public void setSettingsProperty(java.lang.String key, java.lang.String value) {
		_ticketComment.setSettingsProperty(key, value);
	}

	/**
	* Sets the status of this ticket comment.
	*
	* @param status the status of this ticket comment
	*/
	@Override
	public void setStatus(int status) {
		_ticketComment.setStatus(status);
	}

	/**
	* Sets the ticket comment ID of this ticket comment.
	*
	* @param ticketCommentId the ticket comment ID of this ticket comment
	*/
	@Override
	public void setTicketCommentId(long ticketCommentId) {
		_ticketComment.setTicketCommentId(ticketCommentId);
	}

	/**
	* Sets the ticket entry ID of this ticket comment.
	*
	* @param ticketEntryId the ticket entry ID of this ticket comment
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketComment.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the type of this ticket comment.
	*
	* @param type the type of this ticket comment
	*/
	@Override
	public void setType(int type) {
		_ticketComment.setType(type);
	}

	/**
	* Sets the user ID of this ticket comment.
	*
	* @param userId the user ID of this ticket comment
	*/
	@Override
	public void setUserId(long userId) {
		_ticketComment.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket comment.
	*
	* @param userName the user name of this ticket comment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketComment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket comment.
	*
	* @param userUuid the user uuid of this ticket comment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketComment.setUserUuid(userUuid);
	}

	/**
	* Sets the visibility of this ticket comment.
	*
	* @param visibility the visibility of this ticket comment
	*/
	@Override
	public void setVisibility(int visibility) {
		_ticketComment.setVisibility(visibility);
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

		if (Objects.equals(_ticketComment, ticketCommentWrapper._ticketComment)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketComment getWrappedModel() {
		return _ticketComment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketComment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketComment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketComment.resetOriginalValues();
	}

	private final TicketComment _ticketComment;
}