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
 * This class is a wrapper for {@link TestrayProject}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayProject
 * @generated
 */
@ProviderType
public class TestrayProjectWrapper implements TestrayProject,
	ModelWrapper<TestrayProject> {
	public TestrayProjectWrapper(TestrayProject testrayProject) {
		_testrayProject = testrayProject;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayProject.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayProjectId = (Long)attributes.get("testrayProjectId");

		if (testrayProjectId != null) {
			setTestrayProjectId(testrayProjectId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public TestrayProject toEscapedModel() {
		return new TestrayProjectWrapper(_testrayProject.toEscapedModel());
	}

	@Override
	public TestrayProject toUnescapedModel() {
		return new TestrayProjectWrapper(_testrayProject.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _testrayProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayProject.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayProject> toCacheModel() {
		return _testrayProject.toCacheModel();
	}

	@Override
	public int compareTo(TestrayProject testrayProject) {
		return _testrayProject.compareTo(testrayProject);
	}

	@Override
	public int hashCode() {
		return _testrayProject.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayProjectWrapper((TestrayProject)_testrayProject.clone());
	}

	/**
	* Returns the description of this testray project.
	*
	* @return the description of this testray project
	*/
	@Override
	public java.lang.String getDescription() {
		return _testrayProject.getDescription();
	}

	/**
	* Returns the name of this testray project.
	*
	* @return the name of this testray project
	*/
	@Override
	public java.lang.String getName() {
		return _testrayProject.getName();
	}

	/**
	* Returns the user name of this testray project.
	*
	* @return the user name of this testray project
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayProject.getUserName();
	}

	/**
	* Returns the user uuid of this testray project.
	*
	* @return the user uuid of this testray project
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayProject.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _testrayProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayProject.toXmlString();
	}

	/**
	* Returns the create date of this testray project.
	*
	* @return the create date of this testray project
	*/
	@Override
	public Date getCreateDate() {
		return _testrayProject.getCreateDate();
	}

	/**
	* Returns the modified date of this testray project.
	*
	* @return the modified date of this testray project
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayProject.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray project.
	*
	* @return the company ID of this testray project
	*/
	@Override
	public long getCompanyId() {
		return _testrayProject.getCompanyId();
	}

	/**
	* Returns the group ID of this testray project.
	*
	* @return the group ID of this testray project
	*/
	@Override
	public long getGroupId() {
		return _testrayProject.getGroupId();
	}

	/**
	* Returns the primary key of this testray project.
	*
	* @return the primary key of this testray project
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayProject.getPrimaryKey();
	}

	/**
	* Returns the testray project ID of this testray project.
	*
	* @return the testray project ID of this testray project
	*/
	@Override
	public long getTestrayProjectId() {
		return _testrayProject.getTestrayProjectId();
	}

	/**
	* Returns the user ID of this testray project.
	*
	* @return the user ID of this testray project
	*/
	@Override
	public long getUserId() {
		return _testrayProject.getUserId();
	}

	@Override
	public void persist() {
		_testrayProject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayProject.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray project.
	*
	* @param companyId the company ID of this testray project
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayProject.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this testray project.
	*
	* @param createDate the create date of this testray project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayProject.setCreateDate(createDate);
	}

	/**
	* Sets the description of this testray project.
	*
	* @param description the description of this testray project
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_testrayProject.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this testray project.
	*
	* @param groupId the group ID of this testray project
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayProject.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray project.
	*
	* @param modifiedDate the modified date of this testray project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayProject.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this testray project.
	*
	* @param name the name of this testray project
	*/
	@Override
	public void setName(java.lang.String name) {
		_testrayProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayProject.setNew(n);
	}

	/**
	* Sets the primary key of this testray project.
	*
	* @param primaryKey the primary key of this testray project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayProject.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the testray project ID of this testray project.
	*
	* @param testrayProjectId the testray project ID of this testray project
	*/
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayProject.setTestrayProjectId(testrayProjectId);
	}

	/**
	* Sets the user ID of this testray project.
	*
	* @param userId the user ID of this testray project
	*/
	@Override
	public void setUserId(long userId) {
		_testrayProject.setUserId(userId);
	}

	/**
	* Sets the user name of this testray project.
	*
	* @param userName the user name of this testray project
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayProject.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray project.
	*
	* @param userUuid the user uuid of this testray project
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayProject.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayProjectWrapper)) {
			return false;
		}

		TestrayProjectWrapper testrayProjectWrapper = (TestrayProjectWrapper)obj;

		if (Objects.equals(_testrayProject,
					testrayProjectWrapper._testrayProject)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayProject getWrappedModel() {
		return _testrayProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayProject.resetOriginalValues();
	}

	private final TestrayProject _testrayProject;
}