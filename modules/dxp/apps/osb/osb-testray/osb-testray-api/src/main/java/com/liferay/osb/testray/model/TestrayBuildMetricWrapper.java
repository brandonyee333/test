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
 * This class is a wrapper for {@link TestrayBuildMetric}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayBuildMetric
 * @generated
 */
@ProviderType
public class TestrayBuildMetricWrapper implements TestrayBuildMetric,
	ModelWrapper<TestrayBuildMetric> {
	public TestrayBuildMetricWrapper(TestrayBuildMetric testrayBuildMetric) {
		_testrayBuildMetric = testrayBuildMetric;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayBuildMetric.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayBuildMetric.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayBuildMetricId", getTestrayBuildMetricId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayBuildId", getTestrayBuildId());
		attributes.put("testrayCaseTypeId", getTestrayCaseTypeId());
		attributes.put("status", getStatus());
		attributes.put("count", getCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayBuildMetricId = (Long)attributes.get("testrayBuildMetricId");

		if (testrayBuildMetricId != null) {
			setTestrayBuildMetricId(testrayBuildMetricId);
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

		Long testrayBuildId = (Long)attributes.get("testrayBuildId");

		if (testrayBuildId != null) {
			setTestrayBuildId(testrayBuildId);
		}

		Long testrayCaseTypeId = (Long)attributes.get("testrayCaseTypeId");

		if (testrayCaseTypeId != null) {
			setTestrayCaseTypeId(testrayCaseTypeId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long count = (Long)attributes.get("count");

		if (count != null) {
			setCount(count);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayBuildMetricWrapper((TestrayBuildMetric)_testrayBuildMetric.clone());
	}

	@Override
	public int compareTo(TestrayBuildMetric testrayBuildMetric) {
		return _testrayBuildMetric.compareTo(testrayBuildMetric);
	}

	/**
	* Returns the company ID of this testray build metric.
	*
	* @return the company ID of this testray build metric
	*/
	@Override
	public long getCompanyId() {
		return _testrayBuildMetric.getCompanyId();
	}

	/**
	* Returns the count of this testray build metric.
	*
	* @return the count of this testray build metric
	*/
	@Override
	public long getCount() {
		return _testrayBuildMetric.getCount();
	}

	/**
	* Returns the create date of this testray build metric.
	*
	* @return the create date of this testray build metric
	*/
	@Override
	public Date getCreateDate() {
		return _testrayBuildMetric.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayBuildMetric.getExpandoBridge();
	}

	/**
	* Returns the group ID of this testray build metric.
	*
	* @return the group ID of this testray build metric
	*/
	@Override
	public long getGroupId() {
		return _testrayBuildMetric.getGroupId();
	}

	/**
	* Returns the modified date of this testray build metric.
	*
	* @return the modified date of this testray build metric
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayBuildMetric.getModifiedDate();
	}

	/**
	* Returns the primary key of this testray build metric.
	*
	* @return the primary key of this testray build metric
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayBuildMetric.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayBuildMetric.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this testray build metric.
	*
	* @return the status of this testray build metric
	*/
	@Override
	public int getStatus() {
		return _testrayBuildMetric.getStatus();
	}

	/**
	* Returns the testray build ID of this testray build metric.
	*
	* @return the testray build ID of this testray build metric
	*/
	@Override
	public long getTestrayBuildId() {
		return _testrayBuildMetric.getTestrayBuildId();
	}

	/**
	* Returns the testray build metric ID of this testray build metric.
	*
	* @return the testray build metric ID of this testray build metric
	*/
	@Override
	public long getTestrayBuildMetricId() {
		return _testrayBuildMetric.getTestrayBuildMetricId();
	}

	/**
	* Returns the testray case type ID of this testray build metric.
	*
	* @return the testray case type ID of this testray build metric
	*/
	@Override
	public long getTestrayCaseTypeId() {
		return _testrayBuildMetric.getTestrayCaseTypeId();
	}

	/**
	* Returns the user ID of this testray build metric.
	*
	* @return the user ID of this testray build metric
	*/
	@Override
	public long getUserId() {
		return _testrayBuildMetric.getUserId();
	}

	/**
	* Returns the user name of this testray build metric.
	*
	* @return the user name of this testray build metric
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayBuildMetric.getUserName();
	}

	/**
	* Returns the user uuid of this testray build metric.
	*
	* @return the user uuid of this testray build metric
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayBuildMetric.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayBuildMetric.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayBuildMetric.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayBuildMetric.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayBuildMetric.isNew();
	}

	@Override
	public void persist() {
		_testrayBuildMetric.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayBuildMetric.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray build metric.
	*
	* @param companyId the company ID of this testray build metric
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayBuildMetric.setCompanyId(companyId);
	}

	/**
	* Sets the count of this testray build metric.
	*
	* @param count the count of this testray build metric
	*/
	@Override
	public void setCount(long count) {
		_testrayBuildMetric.setCount(count);
	}

	/**
	* Sets the create date of this testray build metric.
	*
	* @param createDate the create date of this testray build metric
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayBuildMetric.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayBuildMetric.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayBuildMetric.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayBuildMetric.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this testray build metric.
	*
	* @param groupId the group ID of this testray build metric
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayBuildMetric.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray build metric.
	*
	* @param modifiedDate the modified date of this testray build metric
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayBuildMetric.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayBuildMetric.setNew(n);
	}

	/**
	* Sets the primary key of this testray build metric.
	*
	* @param primaryKey the primary key of this testray build metric
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayBuildMetric.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayBuildMetric.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this testray build metric.
	*
	* @param status the status of this testray build metric
	*/
	@Override
	public void setStatus(int status) {
		_testrayBuildMetric.setStatus(status);
	}

	/**
	* Sets the testray build ID of this testray build metric.
	*
	* @param testrayBuildId the testray build ID of this testray build metric
	*/
	@Override
	public void setTestrayBuildId(long testrayBuildId) {
		_testrayBuildMetric.setTestrayBuildId(testrayBuildId);
	}

	/**
	* Sets the testray build metric ID of this testray build metric.
	*
	* @param testrayBuildMetricId the testray build metric ID of this testray build metric
	*/
	@Override
	public void setTestrayBuildMetricId(long testrayBuildMetricId) {
		_testrayBuildMetric.setTestrayBuildMetricId(testrayBuildMetricId);
	}

	/**
	* Sets the testray case type ID of this testray build metric.
	*
	* @param testrayCaseTypeId the testray case type ID of this testray build metric
	*/
	@Override
	public void setTestrayCaseTypeId(long testrayCaseTypeId) {
		_testrayBuildMetric.setTestrayCaseTypeId(testrayCaseTypeId);
	}

	/**
	* Sets the user ID of this testray build metric.
	*
	* @param userId the user ID of this testray build metric
	*/
	@Override
	public void setUserId(long userId) {
		_testrayBuildMetric.setUserId(userId);
	}

	/**
	* Sets the user name of this testray build metric.
	*
	* @param userName the user name of this testray build metric
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayBuildMetric.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray build metric.
	*
	* @param userUuid the user uuid of this testray build metric
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayBuildMetric.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayBuildMetric> toCacheModel() {
		return _testrayBuildMetric.toCacheModel();
	}

	@Override
	public TestrayBuildMetric toEscapedModel() {
		return new TestrayBuildMetricWrapper(_testrayBuildMetric.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _testrayBuildMetric.toString();
	}

	@Override
	public TestrayBuildMetric toUnescapedModel() {
		return new TestrayBuildMetricWrapper(_testrayBuildMetric.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayBuildMetric.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayBuildMetricWrapper)) {
			return false;
		}

		TestrayBuildMetricWrapper testrayBuildMetricWrapper = (TestrayBuildMetricWrapper)obj;

		if (Objects.equals(_testrayBuildMetric,
					testrayBuildMetricWrapper._testrayBuildMetric)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayBuildMetric getWrappedModel() {
		return _testrayBuildMetric;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayBuildMetric.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayBuildMetric.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayBuildMetric.resetOriginalValues();
	}

	private final TestrayBuildMetric _testrayBuildMetric;
}