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

package com.liferay.watson.model;

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
 * This class is a wrapper for {@link WatsonHistory}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistory
 * @generated
 */
@ProviderType
public class WatsonHistoryWrapper implements WatsonHistory,
	ModelWrapper<WatsonHistory> {
	public WatsonHistoryWrapper(WatsonHistory watsonHistory) {
		_watsonHistory = watsonHistory;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonHistory.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonHistory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonHistoryId", getWatsonHistoryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonParentId", getWatsonParentId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonHistoryId = (Long)attributes.get("watsonHistoryId");

		if (watsonHistoryId != null) {
			setWatsonHistoryId(watsonHistoryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long watsonParentId = (Long)attributes.get("watsonParentId");

		if (watsonParentId != null) {
			setWatsonParentId(watsonParentId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new WatsonHistoryWrapper((WatsonHistory)_watsonHistory.clone());
	}

	@Override
	public int compareTo(WatsonHistory watsonHistory) {
		return _watsonHistory.compareTo(watsonHistory);
	}

	/**
	* Returns the fully qualified class name of this watson history.
	*
	* @return the fully qualified class name of this watson history
	*/
	@Override
	public String getClassName() {
		return _watsonHistory.getClassName();
	}

	/**
	* Returns the class name ID of this watson history.
	*
	* @return the class name ID of this watson history
	*/
	@Override
	public long getClassNameId() {
		return _watsonHistory.getClassNameId();
	}

	/**
	* Returns the class pk of this watson history.
	*
	* @return the class pk of this watson history
	*/
	@Override
	public long getClassPK() {
		return _watsonHistory.getClassPK();
	}

	/**
	* Returns the company ID of this watson history.
	*
	* @return the company ID of this watson history
	*/
	@Override
	public long getCompanyId() {
		return _watsonHistory.getCompanyId();
	}

	/**
	* Returns the create date of this watson history.
	*
	* @return the create date of this watson history
	*/
	@Override
	public Date getCreateDate() {
		return _watsonHistory.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonHistory.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson history.
	*
	* @return the group ID of this watson history
	*/
	@Override
	public long getGroupId() {
		return _watsonHistory.getGroupId();
	}

	/**
	* Returns the modified date of this watson history.
	*
	* @return the modified date of this watson history
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonHistory.getModifiedDate();
	}

	/**
	* Returns the primary key of this watson history.
	*
	* @return the primary key of this watson history
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonHistory.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonHistory.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson history.
	*
	* @return the status of this watson history
	*/
	@Override
	public int getStatus() {
		return _watsonHistory.getStatus();
	}

	/**
	* Returns the type of this watson history.
	*
	* @return the type of this watson history
	*/
	@Override
	public int getType() {
		return _watsonHistory.getType();
	}

	/**
	* Returns the user ID of this watson history.
	*
	* @return the user ID of this watson history
	*/
	@Override
	public long getUserId() {
		return _watsonHistory.getUserId();
	}

	/**
	* Returns the user name of this watson history.
	*
	* @return the user name of this watson history
	*/
	@Override
	public String getUserName() {
		return _watsonHistory.getUserName();
	}

	/**
	* Returns the user uuid of this watson history.
	*
	* @return the user uuid of this watson history
	*/
	@Override
	public String getUserUuid() {
		return _watsonHistory.getUserUuid();
	}

	/**
	* Returns the watson history ID of this watson history.
	*
	* @return the watson history ID of this watson history
	*/
	@Override
	public long getWatsonHistoryId() {
		return _watsonHistory.getWatsonHistoryId();
	}

	/**
	* Returns the watson parent ID of this watson history.
	*
	* @return the watson parent ID of this watson history
	*/
	@Override
	public long getWatsonParentId() {
		return _watsonHistory.getWatsonParentId();
	}

	@Override
	public int hashCode() {
		return _watsonHistory.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonHistory.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonHistory.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonHistory.isNew();
	}

	@Override
	public void persist() {
		_watsonHistory.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonHistory.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_watsonHistory.setClassName(className);
	}

	/**
	* Sets the class name ID of this watson history.
	*
	* @param classNameId the class name ID of this watson history
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_watsonHistory.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this watson history.
	*
	* @param classPK the class pk of this watson history
	*/
	@Override
	public void setClassPK(long classPK) {
		_watsonHistory.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this watson history.
	*
	* @param companyId the company ID of this watson history
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonHistory.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson history.
	*
	* @param createDate the create date of this watson history
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonHistory.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonHistory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonHistory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonHistory.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson history.
	*
	* @param groupId the group ID of this watson history
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonHistory.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson history.
	*
	* @param modifiedDate the modified date of this watson history
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonHistory.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonHistory.setNew(n);
	}

	/**
	* Sets the primary key of this watson history.
	*
	* @param primaryKey the primary key of this watson history
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonHistory.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonHistory.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson history.
	*
	* @param status the status of this watson history
	*/
	@Override
	public void setStatus(int status) {
		_watsonHistory.setStatus(status);
	}

	/**
	* Sets the type of this watson history.
	*
	* @param type the type of this watson history
	*/
	@Override
	public void setType(int type) {
		_watsonHistory.setType(type);
	}

	/**
	* Sets the user ID of this watson history.
	*
	* @param userId the user ID of this watson history
	*/
	@Override
	public void setUserId(long userId) {
		_watsonHistory.setUserId(userId);
	}

	/**
	* Sets the user name of this watson history.
	*
	* @param userName the user name of this watson history
	*/
	@Override
	public void setUserName(String userName) {
		_watsonHistory.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson history.
	*
	* @param userUuid the user uuid of this watson history
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonHistory.setUserUuid(userUuid);
	}

	/**
	* Sets the watson history ID of this watson history.
	*
	* @param watsonHistoryId the watson history ID of this watson history
	*/
	@Override
	public void setWatsonHistoryId(long watsonHistoryId) {
		_watsonHistory.setWatsonHistoryId(watsonHistoryId);
	}

	/**
	* Sets the watson parent ID of this watson history.
	*
	* @param watsonParentId the watson parent ID of this watson history
	*/
	@Override
	public void setWatsonParentId(long watsonParentId) {
		_watsonHistory.setWatsonParentId(watsonParentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonHistory> toCacheModel() {
		return _watsonHistory.toCacheModel();
	}

	@Override
	public WatsonHistory toEscapedModel() {
		return new WatsonHistoryWrapper(_watsonHistory.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonHistory.toString();
	}

	@Override
	public WatsonHistory toUnescapedModel() {
		return new WatsonHistoryWrapper(_watsonHistory.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonHistory.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonHistoryWrapper)) {
			return false;
		}

		WatsonHistoryWrapper watsonHistoryWrapper = (WatsonHistoryWrapper)obj;

		if (Objects.equals(_watsonHistory, watsonHistoryWrapper._watsonHistory)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonHistory getWrappedModel() {
		return _watsonHistory;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonHistory.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonHistory.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonHistory.resetOriginalValues();
	}

	private final WatsonHistory _watsonHistory;
}