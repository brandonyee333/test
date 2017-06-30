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
 * This class is a wrapper for {@link CorpProjectMessage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpProjectMessage
 * @generated
 */
public class CorpProjectMessageWrapper implements CorpProjectMessage,
	ModelWrapper<CorpProjectMessage> {
	public CorpProjectMessageWrapper(CorpProjectMessage corpProjectMessage) {
		_corpProjectMessage = corpProjectMessage;
	}

	public Class<?> getModelClass() {
		return CorpProjectMessage.class;
	}

	public String getModelClassName() {
		return CorpProjectMessage.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("corpProjectMessageId", getCorpProjectMessageId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("type", getType());
		attributes.put("severityLevel", getSeverityLevel());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());
		attributes.put("displayCP", getDisplayCP());
		attributes.put("displayLCS", getDisplayLCS());
		attributes.put("displayLESA", getDisplayLESA());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long corpProjectMessageId = (Long)attributes.get("corpProjectMessageId");

		if (corpProjectMessageId != null) {
			setCorpProjectMessageId(corpProjectMessageId);
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

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer severityLevel = (Integer)attributes.get("severityLevel");

		if (severityLevel != null) {
			setSeverityLevel(severityLevel);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Boolean displayCP = (Boolean)attributes.get("displayCP");

		if (displayCP != null) {
			setDisplayCP(displayCP);
		}

		Boolean displayLCS = (Boolean)attributes.get("displayLCS");

		if (displayLCS != null) {
			setDisplayLCS(displayLCS);
		}

		Boolean displayLESA = (Boolean)attributes.get("displayLESA");

		if (displayLESA != null) {
			setDisplayLESA(displayLESA);
		}
	}

	/**
	* Returns the primary key of this corp project message.
	*
	* @return the primary key of this corp project message
	*/
	public long getPrimaryKey() {
		return _corpProjectMessage.getPrimaryKey();
	}

	/**
	* Sets the primary key of this corp project message.
	*
	* @param primaryKey the primary key of this corp project message
	*/
	public void setPrimaryKey(long primaryKey) {
		_corpProjectMessage.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the corp project message ID of this corp project message.
	*
	* @return the corp project message ID of this corp project message
	*/
	public long getCorpProjectMessageId() {
		return _corpProjectMessage.getCorpProjectMessageId();
	}

	/**
	* Sets the corp project message ID of this corp project message.
	*
	* @param corpProjectMessageId the corp project message ID of this corp project message
	*/
	public void setCorpProjectMessageId(long corpProjectMessageId) {
		_corpProjectMessage.setCorpProjectMessageId(corpProjectMessageId);
	}

	/**
	* Returns the user ID of this corp project message.
	*
	* @return the user ID of this corp project message
	*/
	public long getUserId() {
		return _corpProjectMessage.getUserId();
	}

	/**
	* Sets the user ID of this corp project message.
	*
	* @param userId the user ID of this corp project message
	*/
	public void setUserId(long userId) {
		_corpProjectMessage.setUserId(userId);
	}

	/**
	* Returns the user uuid of this corp project message.
	*
	* @return the user uuid of this corp project message
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectMessage.getUserUuid();
	}

	/**
	* Sets the user uuid of this corp project message.
	*
	* @param userUuid the user uuid of this corp project message
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_corpProjectMessage.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this corp project message.
	*
	* @return the user name of this corp project message
	*/
	public java.lang.String getUserName() {
		return _corpProjectMessage.getUserName();
	}

	/**
	* Sets the user name of this corp project message.
	*
	* @param userName the user name of this corp project message
	*/
	public void setUserName(java.lang.String userName) {
		_corpProjectMessage.setUserName(userName);
	}

	/**
	* Returns the create date of this corp project message.
	*
	* @return the create date of this corp project message
	*/
	public java.util.Date getCreateDate() {
		return _corpProjectMessage.getCreateDate();
	}

	/**
	* Sets the create date of this corp project message.
	*
	* @param createDate the create date of this corp project message
	*/
	public void setCreateDate(java.util.Date createDate) {
		_corpProjectMessage.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this corp project message.
	*
	* @return the modified date of this corp project message
	*/
	public java.util.Date getModifiedDate() {
		return _corpProjectMessage.getModifiedDate();
	}

	/**
	* Sets the modified date of this corp project message.
	*
	* @param modifiedDate the modified date of this corp project message
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_corpProjectMessage.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the corp project ID of this corp project message.
	*
	* @return the corp project ID of this corp project message
	*/
	public long getCorpProjectId() {
		return _corpProjectMessage.getCorpProjectId();
	}

	/**
	* Sets the corp project ID of this corp project message.
	*
	* @param corpProjectId the corp project ID of this corp project message
	*/
	public void setCorpProjectId(long corpProjectId) {
		_corpProjectMessage.setCorpProjectId(corpProjectId);
	}

	/**
	* Returns the type of this corp project message.
	*
	* @return the type of this corp project message
	*/
	public int getType() {
		return _corpProjectMessage.getType();
	}

	/**
	* Sets the type of this corp project message.
	*
	* @param type the type of this corp project message
	*/
	public void setType(int type) {
		_corpProjectMessage.setType(type);
	}

	/**
	* Returns the severity level of this corp project message.
	*
	* @return the severity level of this corp project message
	*/
	public int getSeverityLevel() {
		return _corpProjectMessage.getSeverityLevel();
	}

	/**
	* Sets the severity level of this corp project message.
	*
	* @param severityLevel the severity level of this corp project message
	*/
	public void setSeverityLevel(int severityLevel) {
		_corpProjectMessage.setSeverityLevel(severityLevel);
	}

	/**
	* Returns the title of this corp project message.
	*
	* @return the title of this corp project message
	*/
	public java.lang.String getTitle() {
		return _corpProjectMessage.getTitle();
	}

	/**
	* Sets the title of this corp project message.
	*
	* @param title the title of this corp project message
	*/
	public void setTitle(java.lang.String title) {
		_corpProjectMessage.setTitle(title);
	}

	/**
	* Returns the content of this corp project message.
	*
	* @return the content of this corp project message
	*/
	public java.lang.String getContent() {
		return _corpProjectMessage.getContent();
	}

	/**
	* Sets the content of this corp project message.
	*
	* @param content the content of this corp project message
	*/
	public void setContent(java.lang.String content) {
		_corpProjectMessage.setContent(content);
	}

	/**
	* Returns the display c p of this corp project message.
	*
	* @return the display c p of this corp project message
	*/
	public boolean getDisplayCP() {
		return _corpProjectMessage.getDisplayCP();
	}

	/**
	* Returns <code>true</code> if this corp project message is display c p.
	*
	* @return <code>true</code> if this corp project message is display c p; <code>false</code> otherwise
	*/
	public boolean isDisplayCP() {
		return _corpProjectMessage.isDisplayCP();
	}

	/**
	* Sets whether this corp project message is display c p.
	*
	* @param displayCP the display c p of this corp project message
	*/
	public void setDisplayCP(boolean displayCP) {
		_corpProjectMessage.setDisplayCP(displayCP);
	}

	/**
	* Returns the display l c s of this corp project message.
	*
	* @return the display l c s of this corp project message
	*/
	public boolean getDisplayLCS() {
		return _corpProjectMessage.getDisplayLCS();
	}

	/**
	* Returns <code>true</code> if this corp project message is display l c s.
	*
	* @return <code>true</code> if this corp project message is display l c s; <code>false</code> otherwise
	*/
	public boolean isDisplayLCS() {
		return _corpProjectMessage.isDisplayLCS();
	}

	/**
	* Sets whether this corp project message is display l c s.
	*
	* @param displayLCS the display l c s of this corp project message
	*/
	public void setDisplayLCS(boolean displayLCS) {
		_corpProjectMessage.setDisplayLCS(displayLCS);
	}

	/**
	* Returns the display l e s a of this corp project message.
	*
	* @return the display l e s a of this corp project message
	*/
	public boolean getDisplayLESA() {
		return _corpProjectMessage.getDisplayLESA();
	}

	/**
	* Returns <code>true</code> if this corp project message is display l e s a.
	*
	* @return <code>true</code> if this corp project message is display l e s a; <code>false</code> otherwise
	*/
	public boolean isDisplayLESA() {
		return _corpProjectMessage.isDisplayLESA();
	}

	/**
	* Sets whether this corp project message is display l e s a.
	*
	* @param displayLESA the display l e s a of this corp project message
	*/
	public void setDisplayLESA(boolean displayLESA) {
		_corpProjectMessage.setDisplayLESA(displayLESA);
	}

	public boolean isNew() {
		return _corpProjectMessage.isNew();
	}

	public void setNew(boolean n) {
		_corpProjectMessage.setNew(n);
	}

	public boolean isCachedModel() {
		return _corpProjectMessage.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_corpProjectMessage.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _corpProjectMessage.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _corpProjectMessage.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_corpProjectMessage.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _corpProjectMessage.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_corpProjectMessage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CorpProjectMessageWrapper((CorpProjectMessage)_corpProjectMessage.clone());
	}

	public int compareTo(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage) {
		return _corpProjectMessage.compareTo(corpProjectMessage);
	}

	@Override
	public int hashCode() {
		return _corpProjectMessage.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CorpProjectMessage> toCacheModel() {
		return _corpProjectMessage.toCacheModel();
	}

	public com.liferay.osb.model.CorpProjectMessage toEscapedModel() {
		return new CorpProjectMessageWrapper(_corpProjectMessage.toEscapedModel());
	}

	public com.liferay.osb.model.CorpProjectMessage toUnescapedModel() {
		return new CorpProjectMessageWrapper(_corpProjectMessage.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _corpProjectMessage.toString();
	}

	public java.lang.String toXmlString() {
		return _corpProjectMessage.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpProjectMessage.persist();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectMessage.getAccountEntry();
	}

	public java.lang.String getSeverityLevelLabel() {
		return _corpProjectMessage.getSeverityLevelLabel();
	}

	public java.lang.String getTypeLabel() {
		return _corpProjectMessage.getTypeLabel();
	}

	public void setAccountEntry(com.liferay.osb.model.AccountEntry accountEntry) {
		_corpProjectMessage.setAccountEntry(accountEntry);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpProjectMessageWrapper)) {
			return false;
		}

		CorpProjectMessageWrapper corpProjectMessageWrapper = (CorpProjectMessageWrapper)obj;

		if (Validator.equals(_corpProjectMessage,
					corpProjectMessageWrapper._corpProjectMessage)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CorpProjectMessage getWrappedCorpProjectMessage() {
		return _corpProjectMessage;
	}

	public CorpProjectMessage getWrappedModel() {
		return _corpProjectMessage;
	}

	public void resetOriginalValues() {
		_corpProjectMessage.resetOriginalValues();
	}

	private CorpProjectMessage _corpProjectMessage;
}