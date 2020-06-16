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
 * This class is a wrapper for {@link TestraySuite}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySuite
 * @generated
 */
@ProviderType
public class TestraySuiteWrapper implements TestraySuite,
	ModelWrapper<TestraySuite> {
	public TestraySuiteWrapper(TestraySuite testraySuite) {
		_testraySuite = testraySuite;
	}

	@Override
	public Class<?> getModelClass() {
		return TestraySuite.class;
	}

	@Override
	public String getModelClassName() {
		return TestraySuite.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testraySuiteId", getTestraySuiteId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("caseParameters", getCaseParameters());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testraySuiteId = (Long)attributes.get("testraySuiteId");

		if (testraySuiteId != null) {
			setTestraySuiteId(testraySuiteId);
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

		Long testrayProjectId = (Long)attributes.get("testrayProjectId");

		if (testrayProjectId != null) {
			setTestrayProjectId(testrayProjectId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String caseParameters = (String)attributes.get("caseParameters");

		if (caseParameters != null) {
			setCaseParameters(caseParameters);
		}
	}

	@Override
	public TestraySuite toEscapedModel() {
		return new TestraySuiteWrapper(_testraySuite.toEscapedModel());
	}

	@Override
	public TestraySuite toUnescapedModel() {
		return new TestraySuiteWrapper(_testraySuite.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _testraySuite.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testraySuite.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testraySuite.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testraySuite.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestraySuite> toCacheModel() {
		return _testraySuite.toCacheModel();
	}

	@Override
	public int compareTo(TestraySuite testraySuite) {
		return _testraySuite.compareTo(testraySuite);
	}

	@Override
	public int hashCode() {
		return _testraySuite.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testraySuite.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestraySuiteWrapper((TestraySuite)_testraySuite.clone());
	}

	/**
	* Returns the case parameters of this testray suite.
	*
	* @return the case parameters of this testray suite
	*/
	@Override
	public java.lang.String getCaseParameters() {
		return _testraySuite.getCaseParameters();
	}

	/**
	* Returns the description of this testray suite.
	*
	* @return the description of this testray suite
	*/
	@Override
	public java.lang.String getDescription() {
		return _testraySuite.getDescription();
	}

	/**
	* Returns the name of this testray suite.
	*
	* @return the name of this testray suite
	*/
	@Override
	public java.lang.String getName() {
		return _testraySuite.getName();
	}

	/**
	* Returns the user name of this testray suite.
	*
	* @return the user name of this testray suite
	*/
	@Override
	public java.lang.String getUserName() {
		return _testraySuite.getUserName();
	}

	/**
	* Returns the user uuid of this testray suite.
	*
	* @return the user uuid of this testray suite
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testraySuite.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _testraySuite.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testraySuite.toXmlString();
	}

	/**
	* Returns the create date of this testray suite.
	*
	* @return the create date of this testray suite
	*/
	@Override
	public Date getCreateDate() {
		return _testraySuite.getCreateDate();
	}

	/**
	* Returns the modified date of this testray suite.
	*
	* @return the modified date of this testray suite
	*/
	@Override
	public Date getModifiedDate() {
		return _testraySuite.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray suite.
	*
	* @return the company ID of this testray suite
	*/
	@Override
	public long getCompanyId() {
		return _testraySuite.getCompanyId();
	}

	/**
	* Returns the group ID of this testray suite.
	*
	* @return the group ID of this testray suite
	*/
	@Override
	public long getGroupId() {
		return _testraySuite.getGroupId();
	}

	/**
	* Returns the primary key of this testray suite.
	*
	* @return the primary key of this testray suite
	*/
	@Override
	public long getPrimaryKey() {
		return _testraySuite.getPrimaryKey();
	}

	/**
	* Returns the testray project ID of this testray suite.
	*
	* @return the testray project ID of this testray suite
	*/
	@Override
	public long getTestrayProjectId() {
		return _testraySuite.getTestrayProjectId();
	}

	/**
	* Returns the testray suite ID of this testray suite.
	*
	* @return the testray suite ID of this testray suite
	*/
	@Override
	public long getTestraySuiteId() {
		return _testraySuite.getTestraySuiteId();
	}

	/**
	* Returns the user ID of this testray suite.
	*
	* @return the user ID of this testray suite
	*/
	@Override
	public long getUserId() {
		return _testraySuite.getUserId();
	}

	@Override
	public void persist() {
		_testraySuite.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testraySuite.setCachedModel(cachedModel);
	}

	/**
	* Sets the case parameters of this testray suite.
	*
	* @param caseParameters the case parameters of this testray suite
	*/
	@Override
	public void setCaseParameters(java.lang.String caseParameters) {
		_testraySuite.setCaseParameters(caseParameters);
	}

	/**
	* Sets the company ID of this testray suite.
	*
	* @param companyId the company ID of this testray suite
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testraySuite.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this testray suite.
	*
	* @param createDate the create date of this testray suite
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testraySuite.setCreateDate(createDate);
	}

	/**
	* Sets the description of this testray suite.
	*
	* @param description the description of this testray suite
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_testraySuite.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testraySuite.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testraySuite.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testraySuite.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this testray suite.
	*
	* @param groupId the group ID of this testray suite
	*/
	@Override
	public void setGroupId(long groupId) {
		_testraySuite.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray suite.
	*
	* @param modifiedDate the modified date of this testray suite
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testraySuite.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this testray suite.
	*
	* @param name the name of this testray suite
	*/
	@Override
	public void setName(java.lang.String name) {
		_testraySuite.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testraySuite.setNew(n);
	}

	/**
	* Sets the primary key of this testray suite.
	*
	* @param primaryKey the primary key of this testray suite
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testraySuite.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testraySuite.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the testray project ID of this testray suite.
	*
	* @param testrayProjectId the testray project ID of this testray suite
	*/
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testraySuite.setTestrayProjectId(testrayProjectId);
	}

	/**
	* Sets the testray suite ID of this testray suite.
	*
	* @param testraySuiteId the testray suite ID of this testray suite
	*/
	@Override
	public void setTestraySuiteId(long testraySuiteId) {
		_testraySuite.setTestraySuiteId(testraySuiteId);
	}

	/**
	* Sets the user ID of this testray suite.
	*
	* @param userId the user ID of this testray suite
	*/
	@Override
	public void setUserId(long userId) {
		_testraySuite.setUserId(userId);
	}

	/**
	* Sets the user name of this testray suite.
	*
	* @param userName the user name of this testray suite
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testraySuite.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray suite.
	*
	* @param userUuid the user uuid of this testray suite
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testraySuite.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestraySuiteWrapper)) {
			return false;
		}

		TestraySuiteWrapper testraySuiteWrapper = (TestraySuiteWrapper)obj;

		if (Objects.equals(_testraySuite, testraySuiteWrapper._testraySuite)) {
			return true;
		}

		return false;
	}

	@Override
	public TestraySuite getWrappedModel() {
		return _testraySuite;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testraySuite.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testraySuite.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testraySuite.resetOriginalValues();
	}

	private final TestraySuite _testraySuite;
}