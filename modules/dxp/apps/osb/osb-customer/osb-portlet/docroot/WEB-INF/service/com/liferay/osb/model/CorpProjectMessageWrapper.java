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
 * This class is a wrapper for {@link CorpProjectMessage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessage
 * @generated
 */
@ProviderType
public class CorpProjectMessageWrapper implements CorpProjectMessage,
	ModelWrapper<CorpProjectMessage> {
	public CorpProjectMessageWrapper(CorpProjectMessage corpProjectMessage) {
		_corpProjectMessage = corpProjectMessage;
	}

	@Override
	public Class<?> getModelClass() {
		return CorpProjectMessage.class;
	}

	@Override
	public String getModelClassName() {
		return CorpProjectMessage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
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

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

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

	@Override
	public CorpProjectMessage toEscapedModel() {
		return new CorpProjectMessageWrapper(_corpProjectMessage.toEscapedModel());
	}

	@Override
	public CorpProjectMessage toUnescapedModel() {
		return new CorpProjectMessageWrapper(_corpProjectMessage.toUnescapedModel());
	}

	/**
	* Returns the display cp of this corp project message.
	*
	* @return the display cp of this corp project message
	*/
	@Override
	public boolean getDisplayCP() {
		return _corpProjectMessage.getDisplayCP();
	}

	/**
	* Returns the display lcs of this corp project message.
	*
	* @return the display lcs of this corp project message
	*/
	@Override
	public boolean getDisplayLCS() {
		return _corpProjectMessage.getDisplayLCS();
	}

	/**
	* Returns the display lesa of this corp project message.
	*
	* @return the display lesa of this corp project message
	*/
	@Override
	public boolean getDisplayLESA() {
		return _corpProjectMessage.getDisplayLESA();
	}

	@Override
	public boolean isCachedModel() {
		return _corpProjectMessage.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this corp project message is display cp.
	*
	* @return <code>true</code> if this corp project message is display cp; <code>false</code> otherwise
	*/
	@Override
	public boolean isDisplayCP() {
		return _corpProjectMessage.isDisplayCP();
	}

	/**
	* Returns <code>true</code> if this corp project message is display lcs.
	*
	* @return <code>true</code> if this corp project message is display lcs; <code>false</code> otherwise
	*/
	@Override
	public boolean isDisplayLCS() {
		return _corpProjectMessage.isDisplayLCS();
	}

	/**
	* Returns <code>true</code> if this corp project message is display lesa.
	*
	* @return <code>true</code> if this corp project message is display lesa; <code>false</code> otherwise
	*/
	@Override
	public boolean isDisplayLESA() {
		return _corpProjectMessage.isDisplayLESA();
	}

	@Override
	public boolean isEscapedModel() {
		return _corpProjectMessage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _corpProjectMessage.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _corpProjectMessage.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CorpProjectMessage> toCacheModel() {
		return _corpProjectMessage.toCacheModel();
	}

	@Override
	public int compareTo(CorpProjectMessage corpProjectMessage) {
		return _corpProjectMessage.compareTo(corpProjectMessage);
	}

	/**
	* Returns the severity level of this corp project message.
	*
	* @return the severity level of this corp project message
	*/
	@Override
	public int getSeverityLevel() {
		return _corpProjectMessage.getSeverityLevel();
	}

	/**
	* Returns the type of this corp project message.
	*
	* @return the type of this corp project message
	*/
	@Override
	public int getType() {
		return _corpProjectMessage.getType();
	}

	@Override
	public int hashCode() {
		return _corpProjectMessage.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _corpProjectMessage.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CorpProjectMessageWrapper((CorpProjectMessage)_corpProjectMessage.clone());
	}

	/**
	* Returns the content of this corp project message.
	*
	* @return the content of this corp project message
	*/
	@Override
	public java.lang.String getContent() {
		return _corpProjectMessage.getContent();
	}

	/**
	* Returns the title of this corp project message.
	*
	* @return the title of this corp project message
	*/
	@Override
	public java.lang.String getTitle() {
		return _corpProjectMessage.getTitle();
	}

	/**
	* Returns the user name of this corp project message.
	*
	* @return the user name of this corp project message
	*/
	@Override
	public java.lang.String getUserName() {
		return _corpProjectMessage.getUserName();
	}

	/**
	* Returns the user uuid of this corp project message.
	*
	* @return the user uuid of this corp project message
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _corpProjectMessage.getUserUuid();
	}

	/**
	* Returns the uuid of this corp project message.
	*
	* @return the uuid of this corp project message
	*/
	@Override
	public java.lang.String getUuid() {
		return _corpProjectMessage.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _corpProjectMessage.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _corpProjectMessage.toXmlString();
	}

	/**
	* Returns the create date of this corp project message.
	*
	* @return the create date of this corp project message
	*/
	@Override
	public Date getCreateDate() {
		return _corpProjectMessage.getCreateDate();
	}

	/**
	* Returns the modified date of this corp project message.
	*
	* @return the modified date of this corp project message
	*/
	@Override
	public Date getModifiedDate() {
		return _corpProjectMessage.getModifiedDate();
	}

	/**
	* Returns the corp project ID of this corp project message.
	*
	* @return the corp project ID of this corp project message
	*/
	@Override
	public long getCorpProjectId() {
		return _corpProjectMessage.getCorpProjectId();
	}

	/**
	* Returns the corp project message ID of this corp project message.
	*
	* @return the corp project message ID of this corp project message
	*/
	@Override
	public long getCorpProjectMessageId() {
		return _corpProjectMessage.getCorpProjectMessageId();
	}

	/**
	* Returns the primary key of this corp project message.
	*
	* @return the primary key of this corp project message
	*/
	@Override
	public long getPrimaryKey() {
		return _corpProjectMessage.getPrimaryKey();
	}

	/**
	* Returns the user ID of this corp project message.
	*
	* @return the user ID of this corp project message
	*/
	@Override
	public long getUserId() {
		return _corpProjectMessage.getUserId();
	}

	@Override
	public void persist() {
		_corpProjectMessage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_corpProjectMessage.setCachedModel(cachedModel);
	}

	/**
	* Sets the content of this corp project message.
	*
	* @param content the content of this corp project message
	*/
	@Override
	public void setContent(java.lang.String content) {
		_corpProjectMessage.setContent(content);
	}

	/**
	* Sets the corp project ID of this corp project message.
	*
	* @param corpProjectId the corp project ID of this corp project message
	*/
	@Override
	public void setCorpProjectId(long corpProjectId) {
		_corpProjectMessage.setCorpProjectId(corpProjectId);
	}

	/**
	* Sets the corp project message ID of this corp project message.
	*
	* @param corpProjectMessageId the corp project message ID of this corp project message
	*/
	@Override
	public void setCorpProjectMessageId(long corpProjectMessageId) {
		_corpProjectMessage.setCorpProjectMessageId(corpProjectMessageId);
	}

	/**
	* Sets the create date of this corp project message.
	*
	* @param createDate the create date of this corp project message
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_corpProjectMessage.setCreateDate(createDate);
	}

	/**
	* Sets whether this corp project message is display cp.
	*
	* @param displayCP the display cp of this corp project message
	*/
	@Override
	public void setDisplayCP(boolean displayCP) {
		_corpProjectMessage.setDisplayCP(displayCP);
	}

	/**
	* Sets whether this corp project message is display lcs.
	*
	* @param displayLCS the display lcs of this corp project message
	*/
	@Override
	public void setDisplayLCS(boolean displayLCS) {
		_corpProjectMessage.setDisplayLCS(displayLCS);
	}

	/**
	* Sets whether this corp project message is display lesa.
	*
	* @param displayLESA the display lesa of this corp project message
	*/
	@Override
	public void setDisplayLESA(boolean displayLESA) {
		_corpProjectMessage.setDisplayLESA(displayLESA);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_corpProjectMessage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_corpProjectMessage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_corpProjectMessage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this corp project message.
	*
	* @param modifiedDate the modified date of this corp project message
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_corpProjectMessage.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_corpProjectMessage.setNew(n);
	}

	/**
	* Sets the primary key of this corp project message.
	*
	* @param primaryKey the primary key of this corp project message
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_corpProjectMessage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_corpProjectMessage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the severity level of this corp project message.
	*
	* @param severityLevel the severity level of this corp project message
	*/
	@Override
	public void setSeverityLevel(int severityLevel) {
		_corpProjectMessage.setSeverityLevel(severityLevel);
	}

	/**
	* Sets the title of this corp project message.
	*
	* @param title the title of this corp project message
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_corpProjectMessage.setTitle(title);
	}

	/**
	* Sets the type of this corp project message.
	*
	* @param type the type of this corp project message
	*/
	@Override
	public void setType(int type) {
		_corpProjectMessage.setType(type);
	}

	/**
	* Sets the user ID of this corp project message.
	*
	* @param userId the user ID of this corp project message
	*/
	@Override
	public void setUserId(long userId) {
		_corpProjectMessage.setUserId(userId);
	}

	/**
	* Sets the user name of this corp project message.
	*
	* @param userName the user name of this corp project message
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_corpProjectMessage.setUserName(userName);
	}

	/**
	* Sets the user uuid of this corp project message.
	*
	* @param userUuid the user uuid of this corp project message
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_corpProjectMessage.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this corp project message.
	*
	* @param uuid the uuid of this corp project message
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_corpProjectMessage.setUuid(uuid);
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

		if (Objects.equals(_corpProjectMessage,
					corpProjectMessageWrapper._corpProjectMessage)) {
			return true;
		}

		return false;
	}

	@Override
	public CorpProjectMessage getWrappedModel() {
		return _corpProjectMessage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _corpProjectMessage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _corpProjectMessage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_corpProjectMessage.resetOriginalValues();
	}

	private final CorpProjectMessage _corpProjectMessage;
}