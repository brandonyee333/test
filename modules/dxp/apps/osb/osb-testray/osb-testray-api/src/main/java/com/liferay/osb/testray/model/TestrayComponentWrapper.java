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
 * This class is a wrapper for {@link TestrayComponent}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayComponent
 * @generated
 */
@ProviderType
public class TestrayComponentWrapper implements TestrayComponent,
	ModelWrapper<TestrayComponent> {
	public TestrayComponentWrapper(TestrayComponent testrayComponent) {
		_testrayComponent = testrayComponent;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayComponent.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayComponent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayComponentId", getTestrayComponentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("testrayTeamId", getTestrayTeamId());
		attributes.put("name", getName());
		attributes.put("originationKey", getOriginationKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayComponentId = (Long)attributes.get("testrayComponentId");

		if (testrayComponentId != null) {
			setTestrayComponentId(testrayComponentId);
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

		Long testrayTeamId = (Long)attributes.get("testrayTeamId");

		if (testrayTeamId != null) {
			setTestrayTeamId(testrayTeamId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long originationKey = (Long)attributes.get("originationKey");

		if (originationKey != null) {
			setOriginationKey(originationKey);
		}
	}

	@Override
	public TestrayComponent toEscapedModel() {
		return new TestrayComponentWrapper(_testrayComponent.toEscapedModel());
	}

	@Override
	public TestrayComponent toUnescapedModel() {
		return new TestrayComponentWrapper(_testrayComponent.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _testrayComponent.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayComponent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayComponent.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayComponent.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayComponent> toCacheModel() {
		return _testrayComponent.toCacheModel();
	}

	@Override
	public int compareTo(TestrayComponent testrayComponent) {
		return _testrayComponent.compareTo(testrayComponent);
	}

	@Override
	public int hashCode() {
		return _testrayComponent.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayComponent.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayComponentWrapper((TestrayComponent)_testrayComponent.clone());
	}

	/**
	* Returns the name of this testray component.
	*
	* @return the name of this testray component
	*/
	@Override
	public java.lang.String getName() {
		return _testrayComponent.getName();
	}

	/**
	* Returns the user name of this testray component.
	*
	* @return the user name of this testray component
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayComponent.getUserName();
	}

	/**
	* Returns the user uuid of this testray component.
	*
	* @return the user uuid of this testray component
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayComponent.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _testrayComponent.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayComponent.toXmlString();
	}

	/**
	* Returns the create date of this testray component.
	*
	* @return the create date of this testray component
	*/
	@Override
	public Date getCreateDate() {
		return _testrayComponent.getCreateDate();
	}

	/**
	* Returns the modified date of this testray component.
	*
	* @return the modified date of this testray component
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayComponent.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray component.
	*
	* @return the company ID of this testray component
	*/
	@Override
	public long getCompanyId() {
		return _testrayComponent.getCompanyId();
	}

	/**
	* Returns the group ID of this testray component.
	*
	* @return the group ID of this testray component
	*/
	@Override
	public long getGroupId() {
		return _testrayComponent.getGroupId();
	}

	/**
	* Returns the origination key of this testray component.
	*
	* @return the origination key of this testray component
	*/
	@Override
	public long getOriginationKey() {
		return _testrayComponent.getOriginationKey();
	}

	/**
	* Returns the primary key of this testray component.
	*
	* @return the primary key of this testray component
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayComponent.getPrimaryKey();
	}

	/**
	* Returns the testray component ID of this testray component.
	*
	* @return the testray component ID of this testray component
	*/
	@Override
	public long getTestrayComponentId() {
		return _testrayComponent.getTestrayComponentId();
	}

	/**
	* Returns the testray project ID of this testray component.
	*
	* @return the testray project ID of this testray component
	*/
	@Override
	public long getTestrayProjectId() {
		return _testrayComponent.getTestrayProjectId();
	}

	/**
	* Returns the testray team ID of this testray component.
	*
	* @return the testray team ID of this testray component
	*/
	@Override
	public long getTestrayTeamId() {
		return _testrayComponent.getTestrayTeamId();
	}

	/**
	* Returns the user ID of this testray component.
	*
	* @return the user ID of this testray component
	*/
	@Override
	public long getUserId() {
		return _testrayComponent.getUserId();
	}

	@Override
	public void persist() {
		_testrayComponent.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayComponent.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray component.
	*
	* @param companyId the company ID of this testray component
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayComponent.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this testray component.
	*
	* @param createDate the create date of this testray component
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayComponent.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayComponent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayComponent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayComponent.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this testray component.
	*
	* @param groupId the group ID of this testray component
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayComponent.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray component.
	*
	* @param modifiedDate the modified date of this testray component
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayComponent.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this testray component.
	*
	* @param name the name of this testray component
	*/
	@Override
	public void setName(java.lang.String name) {
		_testrayComponent.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayComponent.setNew(n);
	}

	/**
	* Sets the origination key of this testray component.
	*
	* @param originationKey the origination key of this testray component
	*/
	@Override
	public void setOriginationKey(long originationKey) {
		_testrayComponent.setOriginationKey(originationKey);
	}

	/**
	* Sets the primary key of this testray component.
	*
	* @param primaryKey the primary key of this testray component
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayComponent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayComponent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the testray component ID of this testray component.
	*
	* @param testrayComponentId the testray component ID of this testray component
	*/
	@Override
	public void setTestrayComponentId(long testrayComponentId) {
		_testrayComponent.setTestrayComponentId(testrayComponentId);
	}

	/**
	* Sets the testray project ID of this testray component.
	*
	* @param testrayProjectId the testray project ID of this testray component
	*/
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayComponent.setTestrayProjectId(testrayProjectId);
	}

	/**
	* Sets the testray team ID of this testray component.
	*
	* @param testrayTeamId the testray team ID of this testray component
	*/
	@Override
	public void setTestrayTeamId(long testrayTeamId) {
		_testrayComponent.setTestrayTeamId(testrayTeamId);
	}

	/**
	* Sets the user ID of this testray component.
	*
	* @param userId the user ID of this testray component
	*/
	@Override
	public void setUserId(long userId) {
		_testrayComponent.setUserId(userId);
	}

	/**
	* Sets the user name of this testray component.
	*
	* @param userName the user name of this testray component
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayComponent.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray component.
	*
	* @param userUuid the user uuid of this testray component
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayComponent.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayComponentWrapper)) {
			return false;
		}

		TestrayComponentWrapper testrayComponentWrapper = (TestrayComponentWrapper)obj;

		if (Objects.equals(_testrayComponent,
					testrayComponentWrapper._testrayComponent)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayComponent getWrappedModel() {
		return _testrayComponent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayComponent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayComponent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayComponent.resetOriginalValues();
	}

	private final TestrayComponent _testrayComponent;
}