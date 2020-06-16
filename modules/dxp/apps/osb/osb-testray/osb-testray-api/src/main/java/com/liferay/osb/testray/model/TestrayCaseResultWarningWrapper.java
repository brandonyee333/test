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

package com.liferay.osb.testray.model;

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
 * This class is a wrapper for {@link TestrayCaseResultWarning}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultWarning
 * @generated
 */
@ProviderType
public class TestrayCaseResultWarningWrapper implements TestrayCaseResultWarning,
	ModelWrapper<TestrayCaseResultWarning> {
	public TestrayCaseResultWarningWrapper(
		TestrayCaseResultWarning testrayCaseResultWarning) {
		_testrayCaseResultWarning = testrayCaseResultWarning;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayCaseResultWarning.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayCaseResultWarning.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayCaseResultWarningId",
			getTestrayCaseResultWarningId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayCaseResultId", getTestrayCaseResultId());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayCaseResultWarningId = (Long)attributes.get(
				"testrayCaseResultWarningId");

		if (testrayCaseResultWarningId != null) {
			setTestrayCaseResultWarningId(testrayCaseResultWarningId);
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

		Long testrayCaseResultId = (Long)attributes.get("testrayCaseResultId");

		if (testrayCaseResultId != null) {
			setTestrayCaseResultId(testrayCaseResultId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	@Override
	public TestrayCaseResultWarning toEscapedModel() {
		return new TestrayCaseResultWarningWrapper(_testrayCaseResultWarning.toEscapedModel());
	}

	@Override
	public TestrayCaseResultWarning toUnescapedModel() {
		return new TestrayCaseResultWarningWrapper(_testrayCaseResultWarning.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _testrayCaseResultWarning.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayCaseResultWarning.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayCaseResultWarning.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayCaseResultWarning.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayCaseResultWarning> toCacheModel() {
		return _testrayCaseResultWarning.toCacheModel();
	}

	@Override
	public int compareTo(TestrayCaseResultWarning testrayCaseResultWarning) {
		return _testrayCaseResultWarning.compareTo(testrayCaseResultWarning);
	}

	@Override
	public int hashCode() {
		return _testrayCaseResultWarning.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayCaseResultWarning.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayCaseResultWarningWrapper((TestrayCaseResultWarning)_testrayCaseResultWarning.clone());
	}

	/**
	* Returns the content of this testray case result warning.
	*
	* @return the content of this testray case result warning
	*/
	@Override
	public java.lang.String getContent() {
		return _testrayCaseResultWarning.getContent();
	}

	/**
	* Returns the user name of this testray case result warning.
	*
	* @return the user name of this testray case result warning
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayCaseResultWarning.getUserName();
	}

	/**
	* Returns the user uuid of this testray case result warning.
	*
	* @return the user uuid of this testray case result warning
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayCaseResultWarning.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _testrayCaseResultWarning.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayCaseResultWarning.toXmlString();
	}

	/**
	* Returns the create date of this testray case result warning.
	*
	* @return the create date of this testray case result warning
	*/
	@Override
	public Date getCreateDate() {
		return _testrayCaseResultWarning.getCreateDate();
	}

	/**
	* Returns the modified date of this testray case result warning.
	*
	* @return the modified date of this testray case result warning
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayCaseResultWarning.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray case result warning.
	*
	* @return the company ID of this testray case result warning
	*/
	@Override
	public long getCompanyId() {
		return _testrayCaseResultWarning.getCompanyId();
	}

	/**
	* Returns the group ID of this testray case result warning.
	*
	* @return the group ID of this testray case result warning
	*/
	@Override
	public long getGroupId() {
		return _testrayCaseResultWarning.getGroupId();
	}

	/**
	* Returns the primary key of this testray case result warning.
	*
	* @return the primary key of this testray case result warning
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayCaseResultWarning.getPrimaryKey();
	}

	/**
	* Returns the testray case result ID of this testray case result warning.
	*
	* @return the testray case result ID of this testray case result warning
	*/
	@Override
	public long getTestrayCaseResultId() {
		return _testrayCaseResultWarning.getTestrayCaseResultId();
	}

	/**
	* Returns the testray case result warning ID of this testray case result warning.
	*
	* @return the testray case result warning ID of this testray case result warning
	*/
	@Override
	public long getTestrayCaseResultWarningId() {
		return _testrayCaseResultWarning.getTestrayCaseResultWarningId();
	}

	/**
	* Returns the user ID of this testray case result warning.
	*
	* @return the user ID of this testray case result warning
	*/
	@Override
	public long getUserId() {
		return _testrayCaseResultWarning.getUserId();
	}

	@Override
	public void persist() {
		_testrayCaseResultWarning.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayCaseResultWarning.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray case result warning.
	*
	* @param companyId the company ID of this testray case result warning
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayCaseResultWarning.setCompanyId(companyId);
	}

	/**
	* Sets the content of this testray case result warning.
	*
	* @param content the content of this testray case result warning
	*/
	@Override
	public void setContent(java.lang.String content) {
		_testrayCaseResultWarning.setContent(content);
	}

	/**
	* Sets the create date of this testray case result warning.
	*
	* @param createDate the create date of this testray case result warning
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayCaseResultWarning.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayCaseResultWarning.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayCaseResultWarning.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayCaseResultWarning.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this testray case result warning.
	*
	* @param groupId the group ID of this testray case result warning
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayCaseResultWarning.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray case result warning.
	*
	* @param modifiedDate the modified date of this testray case result warning
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayCaseResultWarning.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayCaseResultWarning.setNew(n);
	}

	/**
	* Sets the primary key of this testray case result warning.
	*
	* @param primaryKey the primary key of this testray case result warning
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayCaseResultWarning.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayCaseResultWarning.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the testray case result ID of this testray case result warning.
	*
	* @param testrayCaseResultId the testray case result ID of this testray case result warning
	*/
	@Override
	public void setTestrayCaseResultId(long testrayCaseResultId) {
		_testrayCaseResultWarning.setTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	* Sets the testray case result warning ID of this testray case result warning.
	*
	* @param testrayCaseResultWarningId the testray case result warning ID of this testray case result warning
	*/
	@Override
	public void setTestrayCaseResultWarningId(long testrayCaseResultWarningId) {
		_testrayCaseResultWarning.setTestrayCaseResultWarningId(testrayCaseResultWarningId);
	}

	/**
	* Sets the user ID of this testray case result warning.
	*
	* @param userId the user ID of this testray case result warning
	*/
	@Override
	public void setUserId(long userId) {
		_testrayCaseResultWarning.setUserId(userId);
	}

	/**
	* Sets the user name of this testray case result warning.
	*
	* @param userName the user name of this testray case result warning
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayCaseResultWarning.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray case result warning.
	*
	* @param userUuid the user uuid of this testray case result warning
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayCaseResultWarning.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseResultWarningWrapper)) {
			return false;
		}

		TestrayCaseResultWarningWrapper testrayCaseResultWarningWrapper = (TestrayCaseResultWarningWrapper)obj;

		if (Objects.equals(_testrayCaseResultWarning,
					testrayCaseResultWarningWrapper._testrayCaseResultWarning)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayCaseResultWarning getWrappedModel() {
		return _testrayCaseResultWarning;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayCaseResultWarning.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayCaseResultWarning.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayCaseResultWarning.resetOriginalValues();
	}

	private final TestrayCaseResultWarning _testrayCaseResultWarning;
}