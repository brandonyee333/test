/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.model;

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
 * This class is a wrapper for {@link TestrayProductVersion}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayProductVersion
 * @generated
 */
public class TestrayProductVersionWrapper
	implements ModelWrapper<TestrayProductVersion>, TestrayProductVersion {

	public TestrayProductVersionWrapper(
		TestrayProductVersion testrayProductVersion) {

		_testrayProductVersion = testrayProductVersion;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayProductVersion.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayProductVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayProductVersionId", getTestrayProductVersionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayProductVersionId = (Long)attributes.get(
			"testrayProductVersionId");

		if (testrayProductVersionId != null) {
			setTestrayProductVersionId(testrayProductVersionId);
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
	}

	@Override
	public Object clone() {
		return new TestrayProductVersionWrapper(
			(TestrayProductVersion)_testrayProductVersion.clone());
	}

	@Override
	public int compareTo(TestrayProductVersion testrayProductVersion) {
		return _testrayProductVersion.compareTo(testrayProductVersion);
	}

	/**
	 * Returns the company ID of this testray product version.
	 *
	 * @return the company ID of this testray product version
	 */
	@Override
	public long getCompanyId() {
		return _testrayProductVersion.getCompanyId();
	}

	/**
	 * Returns the create date of this testray product version.
	 *
	 * @return the create date of this testray product version
	 */
	@Override
	public Date getCreateDate() {
		return _testrayProductVersion.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayProductVersion.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray product version.
	 *
	 * @return the group ID of this testray product version
	 */
	@Override
	public long getGroupId() {
		return _testrayProductVersion.getGroupId();
	}

	/**
	 * Returns the modified date of this testray product version.
	 *
	 * @return the modified date of this testray product version
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayProductVersion.getModifiedDate();
	}

	/**
	 * Returns the name of this testray product version.
	 *
	 * @return the name of this testray product version
	 */
	@Override
	public String getName() {
		return _testrayProductVersion.getName();
	}

	/**
	 * Returns the primary key of this testray product version.
	 *
	 * @return the primary key of this testray product version
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayProductVersion.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayProductVersion.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray product version ID of this testray product version.
	 *
	 * @return the testray product version ID of this testray product version
	 */
	@Override
	public long getTestrayProductVersionId() {
		return _testrayProductVersion.getTestrayProductVersionId();
	}

	/**
	 * Returns the testray project ID of this testray product version.
	 *
	 * @return the testray project ID of this testray product version
	 */
	@Override
	public long getTestrayProjectId() {
		return _testrayProductVersion.getTestrayProjectId();
	}

	/**
	 * Returns the user ID of this testray product version.
	 *
	 * @return the user ID of this testray product version
	 */
	@Override
	public long getUserId() {
		return _testrayProductVersion.getUserId();
	}

	/**
	 * Returns the user name of this testray product version.
	 *
	 * @return the user name of this testray product version
	 */
	@Override
	public String getUserName() {
		return _testrayProductVersion.getUserName();
	}

	/**
	 * Returns the user uuid of this testray product version.
	 *
	 * @return the user uuid of this testray product version
	 */
	@Override
	public String getUserUuid() {
		return _testrayProductVersion.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayProductVersion.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayProductVersion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayProductVersion.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayProductVersion.isNew();
	}

	@Override
	public void persist() {
		_testrayProductVersion.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayProductVersion.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray product version.
	 *
	 * @param companyId the company ID of this testray product version
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayProductVersion.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray product version.
	 *
	 * @param createDate the create date of this testray product version
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayProductVersion.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayProductVersion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayProductVersion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayProductVersion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray product version.
	 *
	 * @param groupId the group ID of this testray product version
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayProductVersion.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray product version.
	 *
	 * @param modifiedDate the modified date of this testray product version
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayProductVersion.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray product version.
	 *
	 * @param name the name of this testray product version
	 */
	@Override
	public void setName(String name) {
		_testrayProductVersion.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayProductVersion.setNew(n);
	}

	/**
	 * Sets the primary key of this testray product version.
	 *
	 * @param primaryKey the primary key of this testray product version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayProductVersion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayProductVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray product version ID of this testray product version.
	 *
	 * @param testrayProductVersionId the testray product version ID of this testray product version
	 */
	@Override
	public void setTestrayProductVersionId(long testrayProductVersionId) {
		_testrayProductVersion.setTestrayProductVersionId(
			testrayProductVersionId);
	}

	/**
	 * Sets the testray project ID of this testray product version.
	 *
	 * @param testrayProjectId the testray project ID of this testray product version
	 */
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayProductVersion.setTestrayProjectId(testrayProjectId);
	}

	/**
	 * Sets the user ID of this testray product version.
	 *
	 * @param userId the user ID of this testray product version
	 */
	@Override
	public void setUserId(long userId) {
		_testrayProductVersion.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray product version.
	 *
	 * @param userName the user name of this testray product version
	 */
	@Override
	public void setUserName(String userName) {
		_testrayProductVersion.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray product version.
	 *
	 * @param userUuid the user uuid of this testray product version
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayProductVersion.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayProductVersion>
		toCacheModel() {

		return _testrayProductVersion.toCacheModel();
	}

	@Override
	public TestrayProductVersion toEscapedModel() {
		return new TestrayProductVersionWrapper(
			_testrayProductVersion.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayProductVersion.toString();
	}

	@Override
	public TestrayProductVersion toUnescapedModel() {
		return new TestrayProductVersionWrapper(
			_testrayProductVersion.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayProductVersion.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayProductVersionWrapper)) {
			return false;
		}

		TestrayProductVersionWrapper testrayProductVersionWrapper =
			(TestrayProductVersionWrapper)obj;

		if (Objects.equals(
				_testrayProductVersion,
				testrayProductVersionWrapper._testrayProductVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayProductVersion getWrappedModel() {
		return _testrayProductVersion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayProductVersion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayProductVersion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayProductVersion.resetOriginalValues();
	}

	private final TestrayProductVersion _testrayProductVersion;

}