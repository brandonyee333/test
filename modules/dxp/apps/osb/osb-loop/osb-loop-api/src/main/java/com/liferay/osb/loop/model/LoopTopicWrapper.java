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

package com.liferay.osb.loop.model;

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
 * This class is a wrapper for {@link LoopTopic}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopic
 * @generated
 */
@ProviderType
public class LoopTopicWrapper implements LoopTopic, ModelWrapper<LoopTopic> {
	public LoopTopicWrapper(LoopTopic loopTopic) {
		_loopTopic = loopTopic;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopTopic.class;
	}

	@Override
	public String getModelClassName() {
		return LoopTopic.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopTopicId", getLoopTopicId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentLoopTopicId", getParentLoopTopicId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("mergeTime", getMergeTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopTopicId = (Long)attributes.get("loopTopicId");

		if (loopTopicId != null) {
			setLoopTopicId(loopTopicId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long parentLoopTopicId = (Long)attributes.get("parentLoopTopicId");

		if (parentLoopTopicId != null) {
			setParentLoopTopicId(parentLoopTopicId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		Long mergeTime = (Long)attributes.get("mergeTime");

		if (mergeTime != null) {
			setMergeTime(mergeTime);
		}
	}

	@Override
	public Object clone() {
		return new LoopTopicWrapper((LoopTopic)_loopTopic.clone());
	}

	@Override
	public int compareTo(LoopTopic loopTopic) {
		return _loopTopic.compareTo(loopTopic);
	}

	/**
	* Returns the company ID of this loop topic.
	*
	* @return the company ID of this loop topic
	*/
	@Override
	public long getCompanyId() {
		return _loopTopic.getCompanyId();
	}

	/**
	* Returns the create date of this loop topic.
	*
	* @return the create date of this loop topic
	*/
	@Override
	public Date getCreateDate() {
		return _loopTopic.getCreateDate();
	}

	/**
	* Returns the description of this loop topic.
	*
	* @return the description of this loop topic
	*/
	@Override
	public String getDescription() {
		return _loopTopic.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopTopic.getExpandoBridge();
	}

	/**
	* Returns the image payload of this loop topic.
	*
	* @return the image payload of this loop topic
	*/
	@Override
	public String getImagePayload() {
		return _loopTopic.getImagePayload();
	}

	/**
	* Returns the loop topic ID of this loop topic.
	*
	* @return the loop topic ID of this loop topic
	*/
	@Override
	public long getLoopTopicId() {
		return _loopTopic.getLoopTopicId();
	}

	/**
	* Returns the merge time of this loop topic.
	*
	* @return the merge time of this loop topic
	*/
	@Override
	public long getMergeTime() {
		return _loopTopic.getMergeTime();
	}

	/**
	* Returns the modified date of this loop topic.
	*
	* @return the modified date of this loop topic
	*/
	@Override
	public Date getModifiedDate() {
		return _loopTopic.getModifiedDate();
	}

	/**
	* Returns the name of this loop topic.
	*
	* @return the name of this loop topic
	*/
	@Override
	public String getName() {
		return _loopTopic.getName();
	}

	/**
	* Returns the parent loop topic ID of this loop topic.
	*
	* @return the parent loop topic ID of this loop topic
	*/
	@Override
	public long getParentLoopTopicId() {
		return _loopTopic.getParentLoopTopicId();
	}

	/**
	* Returns the primary key of this loop topic.
	*
	* @return the primary key of this loop topic
	*/
	@Override
	public long getPrimaryKey() {
		return _loopTopic.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopTopic.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this loop topic.
	*
	* @return the user ID of this loop topic
	*/
	@Override
	public long getUserId() {
		return _loopTopic.getUserId();
	}

	/**
	* Returns the user name of this loop topic.
	*
	* @return the user name of this loop topic
	*/
	@Override
	public String getUserName() {
		return _loopTopic.getUserName();
	}

	/**
	* Returns the user uuid of this loop topic.
	*
	* @return the user uuid of this loop topic
	*/
	@Override
	public String getUserUuid() {
		return _loopTopic.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopTopic.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopTopic.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopTopic.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopTopic.isNew();
	}

	@Override
	public void persist() {
		_loopTopic.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopTopic.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this loop topic.
	*
	* @param companyId the company ID of this loop topic
	*/
	@Override
	public void setCompanyId(long companyId) {
		_loopTopic.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this loop topic.
	*
	* @param createDate the create date of this loop topic
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_loopTopic.setCreateDate(createDate);
	}

	/**
	* Sets the description of this loop topic.
	*
	* @param description the description of this loop topic
	*/
	@Override
	public void setDescription(String description) {
		_loopTopic.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_loopTopic.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopTopic.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopTopic.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the image payload of this loop topic.
	*
	* @param imagePayload the image payload of this loop topic
	*/
	@Override
	public void setImagePayload(String imagePayload) {
		_loopTopic.setImagePayload(imagePayload);
	}

	/**
	* Sets the loop topic ID of this loop topic.
	*
	* @param loopTopicId the loop topic ID of this loop topic
	*/
	@Override
	public void setLoopTopicId(long loopTopicId) {
		_loopTopic.setLoopTopicId(loopTopicId);
	}

	/**
	* Sets the merge time of this loop topic.
	*
	* @param mergeTime the merge time of this loop topic
	*/
	@Override
	public void setMergeTime(long mergeTime) {
		_loopTopic.setMergeTime(mergeTime);
	}

	/**
	* Sets the modified date of this loop topic.
	*
	* @param modifiedDate the modified date of this loop topic
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_loopTopic.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this loop topic.
	*
	* @param name the name of this loop topic
	*/
	@Override
	public void setName(String name) {
		_loopTopic.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_loopTopic.setNew(n);
	}

	/**
	* Sets the parent loop topic ID of this loop topic.
	*
	* @param parentLoopTopicId the parent loop topic ID of this loop topic
	*/
	@Override
	public void setParentLoopTopicId(long parentLoopTopicId) {
		_loopTopic.setParentLoopTopicId(parentLoopTopicId);
	}

	/**
	* Sets the primary key of this loop topic.
	*
	* @param primaryKey the primary key of this loop topic
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopTopic.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopTopic.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this loop topic.
	*
	* @param userId the user ID of this loop topic
	*/
	@Override
	public void setUserId(long userId) {
		_loopTopic.setUserId(userId);
	}

	/**
	* Sets the user name of this loop topic.
	*
	* @param userName the user name of this loop topic
	*/
	@Override
	public void setUserName(String userName) {
		_loopTopic.setUserName(userName);
	}

	/**
	* Sets the user uuid of this loop topic.
	*
	* @param userUuid the user uuid of this loop topic
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_loopTopic.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopTopic> toCacheModel() {
		return _loopTopic.toCacheModel();
	}

	@Override
	public LoopTopic toEscapedModel() {
		return new LoopTopicWrapper(_loopTopic.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopTopic.toString();
	}

	@Override
	public LoopTopic toUnescapedModel() {
		return new LoopTopicWrapper(_loopTopic.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopTopic.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopTopicWrapper)) {
			return false;
		}

		LoopTopicWrapper loopTopicWrapper = (LoopTopicWrapper)obj;

		if (Objects.equals(_loopTopic, loopTopicWrapper._loopTopic)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopTopic getWrappedModel() {
		return _loopTopic;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopTopic.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopTopic.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopTopic.resetOriginalValues();
	}

	private final LoopTopic _loopTopic;
}