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
 * This class is a wrapper for {@link TestrayRoutine}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRoutine
 * @generated
 */
@ProviderType
public class TestrayRoutineWrapper implements TestrayRoutine,
	ModelWrapper<TestrayRoutine> {
	public TestrayRoutineWrapper(TestrayRoutine testrayRoutine) {
		_testrayRoutine = testrayRoutine;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayRoutine.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayRoutine.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayRoutineId", getTestrayRoutineId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("name", getName());
		attributes.put("autoanalyze", getAutoanalyze());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayRoutineId = (Long)attributes.get("testrayRoutineId");

		if (testrayRoutineId != null) {
			setTestrayRoutineId(testrayRoutineId);
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

		Boolean autoanalyze = (Boolean)attributes.get("autoanalyze");

		if (autoanalyze != null) {
			setAutoanalyze(autoanalyze);
		}
	}

	@Override
	public TestrayRoutine toEscapedModel() {
		return new TestrayRoutineWrapper(_testrayRoutine.toEscapedModel());
	}

	@Override
	public TestrayRoutine toUnescapedModel() {
		return new TestrayRoutineWrapper(_testrayRoutine.toUnescapedModel());
	}

	/**
	* Returns the autoanalyze of this testray routine.
	*
	* @return the autoanalyze of this testray routine
	*/
	@Override
	public boolean getAutoanalyze() {
		return _testrayRoutine.getAutoanalyze();
	}

	/**
	* Returns <code>true</code> if this testray routine is autoanalyze.
	*
	* @return <code>true</code> if this testray routine is autoanalyze; <code>false</code> otherwise
	*/
	@Override
	public boolean isAutoanalyze() {
		return _testrayRoutine.isAutoanalyze();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayRoutine.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayRoutine.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayRoutine.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayRoutine.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayRoutine> toCacheModel() {
		return _testrayRoutine.toCacheModel();
	}

	@Override
	public int compareTo(TestrayRoutine testrayRoutine) {
		return _testrayRoutine.compareTo(testrayRoutine);
	}

	@Override
	public int hashCode() {
		return _testrayRoutine.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayRoutine.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayRoutineWrapper((TestrayRoutine)_testrayRoutine.clone());
	}

	/**
	* Returns the name of this testray routine.
	*
	* @return the name of this testray routine
	*/
	@Override
	public java.lang.String getName() {
		return _testrayRoutine.getName();
	}

	/**
	* Returns the user name of this testray routine.
	*
	* @return the user name of this testray routine
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayRoutine.getUserName();
	}

	/**
	* Returns the user uuid of this testray routine.
	*
	* @return the user uuid of this testray routine
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayRoutine.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _testrayRoutine.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayRoutine.toXmlString();
	}

	/**
	* Returns the create date of this testray routine.
	*
	* @return the create date of this testray routine
	*/
	@Override
	public Date getCreateDate() {
		return _testrayRoutine.getCreateDate();
	}

	/**
	* Returns the modified date of this testray routine.
	*
	* @return the modified date of this testray routine
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayRoutine.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray routine.
	*
	* @return the company ID of this testray routine
	*/
	@Override
	public long getCompanyId() {
		return _testrayRoutine.getCompanyId();
	}

	/**
	* Returns the group ID of this testray routine.
	*
	* @return the group ID of this testray routine
	*/
	@Override
	public long getGroupId() {
		return _testrayRoutine.getGroupId();
	}

	/**
	* Returns the primary key of this testray routine.
	*
	* @return the primary key of this testray routine
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayRoutine.getPrimaryKey();
	}

	/**
	* Returns the testray project ID of this testray routine.
	*
	* @return the testray project ID of this testray routine
	*/
	@Override
	public long getTestrayProjectId() {
		return _testrayRoutine.getTestrayProjectId();
	}

	/**
	* Returns the testray routine ID of this testray routine.
	*
	* @return the testray routine ID of this testray routine
	*/
	@Override
	public long getTestrayRoutineId() {
		return _testrayRoutine.getTestrayRoutineId();
	}

	/**
	* Returns the user ID of this testray routine.
	*
	* @return the user ID of this testray routine
	*/
	@Override
	public long getUserId() {
		return _testrayRoutine.getUserId();
	}

	@Override
	public void persist() {
		_testrayRoutine.persist();
	}

	/**
	* Sets whether this testray routine is autoanalyze.
	*
	* @param autoanalyze the autoanalyze of this testray routine
	*/
	@Override
	public void setAutoanalyze(boolean autoanalyze) {
		_testrayRoutine.setAutoanalyze(autoanalyze);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayRoutine.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray routine.
	*
	* @param companyId the company ID of this testray routine
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayRoutine.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this testray routine.
	*
	* @param createDate the create date of this testray routine
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayRoutine.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayRoutine.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayRoutine.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayRoutine.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this testray routine.
	*
	* @param groupId the group ID of this testray routine
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayRoutine.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray routine.
	*
	* @param modifiedDate the modified date of this testray routine
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayRoutine.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this testray routine.
	*
	* @param name the name of this testray routine
	*/
	@Override
	public void setName(java.lang.String name) {
		_testrayRoutine.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayRoutine.setNew(n);
	}

	/**
	* Sets the primary key of this testray routine.
	*
	* @param primaryKey the primary key of this testray routine
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayRoutine.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayRoutine.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the testray project ID of this testray routine.
	*
	* @param testrayProjectId the testray project ID of this testray routine
	*/
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayRoutine.setTestrayProjectId(testrayProjectId);
	}

	/**
	* Sets the testray routine ID of this testray routine.
	*
	* @param testrayRoutineId the testray routine ID of this testray routine
	*/
	@Override
	public void setTestrayRoutineId(long testrayRoutineId) {
		_testrayRoutine.setTestrayRoutineId(testrayRoutineId);
	}

	/**
	* Sets the user ID of this testray routine.
	*
	* @param userId the user ID of this testray routine
	*/
	@Override
	public void setUserId(long userId) {
		_testrayRoutine.setUserId(userId);
	}

	/**
	* Sets the user name of this testray routine.
	*
	* @param userName the user name of this testray routine
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayRoutine.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray routine.
	*
	* @param userUuid the user uuid of this testray routine
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayRoutine.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRoutineWrapper)) {
			return false;
		}

		TestrayRoutineWrapper testrayRoutineWrapper = (TestrayRoutineWrapper)obj;

		if (Objects.equals(_testrayRoutine,
					testrayRoutineWrapper._testrayRoutine)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayRoutine getWrappedModel() {
		return _testrayRoutine;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayRoutine.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayRoutine.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayRoutine.resetOriginalValues();
	}

	private final TestrayRoutine _testrayRoutine;
}