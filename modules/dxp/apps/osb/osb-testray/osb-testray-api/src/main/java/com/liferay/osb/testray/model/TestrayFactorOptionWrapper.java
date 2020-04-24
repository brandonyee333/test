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
 * This class is a wrapper for {@link TestrayFactorOption}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorOption
 * @generated
 */
@ProviderType
public class TestrayFactorOptionWrapper
	implements TestrayFactorOption, ModelWrapper<TestrayFactorOption> {

	public TestrayFactorOptionWrapper(TestrayFactorOption testrayFactorOption) {
		_testrayFactorOption = testrayFactorOption;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayFactorOption.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayFactorOption.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayFactorOptionId", getTestrayFactorOptionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayFactorCategoryId", getTestrayFactorCategoryId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayFactorOptionId = (Long)attributes.get(
			"testrayFactorOptionId");

		if (testrayFactorOptionId != null) {
			setTestrayFactorOptionId(testrayFactorOptionId);
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

		Long testrayFactorCategoryId = (Long)attributes.get(
			"testrayFactorCategoryId");

		if (testrayFactorCategoryId != null) {
			setTestrayFactorCategoryId(testrayFactorCategoryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new TestrayFactorOptionWrapper(
			(TestrayFactorOption)_testrayFactorOption.clone());
	}

	@Override
	public int compareTo(TestrayFactorOption testrayFactorOption) {
		return _testrayFactorOption.compareTo(testrayFactorOption);
	}

	/**
	 * Returns the company ID of this testray factor option.
	 *
	 * @return the company ID of this testray factor option
	 */
	@Override
	public long getCompanyId() {
		return _testrayFactorOption.getCompanyId();
	}

	/**
	 * Returns the create date of this testray factor option.
	 *
	 * @return the create date of this testray factor option
	 */
	@Override
	public Date getCreateDate() {
		return _testrayFactorOption.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayFactorOption.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray factor option.
	 *
	 * @return the group ID of this testray factor option
	 */
	@Override
	public long getGroupId() {
		return _testrayFactorOption.getGroupId();
	}

	/**
	 * Returns the modified date of this testray factor option.
	 *
	 * @return the modified date of this testray factor option
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayFactorOption.getModifiedDate();
	}

	/**
	 * Returns the name of this testray factor option.
	 *
	 * @return the name of this testray factor option
	 */
	@Override
	public String getName() {
		return _testrayFactorOption.getName();
	}

	/**
	 * Returns the primary key of this testray factor option.
	 *
	 * @return the primary key of this testray factor option
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayFactorOption.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayFactorOption.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray factor category ID of this testray factor option.
	 *
	 * @return the testray factor category ID of this testray factor option
	 */
	@Override
	public long getTestrayFactorCategoryId() {
		return _testrayFactorOption.getTestrayFactorCategoryId();
	}

	/**
	 * Returns the testray factor option ID of this testray factor option.
	 *
	 * @return the testray factor option ID of this testray factor option
	 */
	@Override
	public long getTestrayFactorOptionId() {
		return _testrayFactorOption.getTestrayFactorOptionId();
	}

	/**
	 * Returns the user ID of this testray factor option.
	 *
	 * @return the user ID of this testray factor option
	 */
	@Override
	public long getUserId() {
		return _testrayFactorOption.getUserId();
	}

	/**
	 * Returns the user name of this testray factor option.
	 *
	 * @return the user name of this testray factor option
	 */
	@Override
	public String getUserName() {
		return _testrayFactorOption.getUserName();
	}

	/**
	 * Returns the user uuid of this testray factor option.
	 *
	 * @return the user uuid of this testray factor option
	 */
	@Override
	public String getUserUuid() {
		return _testrayFactorOption.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayFactorOption.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayFactorOption.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayFactorOption.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayFactorOption.isNew();
	}

	@Override
	public void persist() {
		_testrayFactorOption.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayFactorOption.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray factor option.
	 *
	 * @param companyId the company ID of this testray factor option
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayFactorOption.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray factor option.
	 *
	 * @param createDate the create date of this testray factor option
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayFactorOption.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayFactorOption.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayFactorOption.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayFactorOption.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray factor option.
	 *
	 * @param groupId the group ID of this testray factor option
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayFactorOption.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray factor option.
	 *
	 * @param modifiedDate the modified date of this testray factor option
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayFactorOption.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray factor option.
	 *
	 * @param name the name of this testray factor option
	 */
	@Override
	public void setName(String name) {
		_testrayFactorOption.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayFactorOption.setNew(n);
	}

	/**
	 * Sets the primary key of this testray factor option.
	 *
	 * @param primaryKey the primary key of this testray factor option
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayFactorOption.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayFactorOption.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray factor category ID of this testray factor option.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID of this testray factor option
	 */
	@Override
	public void setTestrayFactorCategoryId(long testrayFactorCategoryId) {
		_testrayFactorOption.setTestrayFactorCategoryId(
			testrayFactorCategoryId);
	}

	/**
	 * Sets the testray factor option ID of this testray factor option.
	 *
	 * @param testrayFactorOptionId the testray factor option ID of this testray factor option
	 */
	@Override
	public void setTestrayFactorOptionId(long testrayFactorOptionId) {
		_testrayFactorOption.setTestrayFactorOptionId(testrayFactorOptionId);
	}

	/**
	 * Sets the user ID of this testray factor option.
	 *
	 * @param userId the user ID of this testray factor option
	 */
	@Override
	public void setUserId(long userId) {
		_testrayFactorOption.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray factor option.
	 *
	 * @param userName the user name of this testray factor option
	 */
	@Override
	public void setUserName(String userName) {
		_testrayFactorOption.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray factor option.
	 *
	 * @param userUuid the user uuid of this testray factor option
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayFactorOption.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayFactorOption>
		toCacheModel() {

		return _testrayFactorOption.toCacheModel();
	}

	@Override
	public TestrayFactorOption toEscapedModel() {
		return new TestrayFactorOptionWrapper(
			_testrayFactorOption.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayFactorOption.toString();
	}

	@Override
	public TestrayFactorOption toUnescapedModel() {
		return new TestrayFactorOptionWrapper(
			_testrayFactorOption.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayFactorOption.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorOptionWrapper)) {
			return false;
		}

		TestrayFactorOptionWrapper testrayFactorOptionWrapper =
			(TestrayFactorOptionWrapper)obj;

		if (Objects.equals(
				_testrayFactorOption,
				testrayFactorOptionWrapper._testrayFactorOption)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayFactorOption getWrappedModel() {
		return _testrayFactorOption;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayFactorOption.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayFactorOption.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayFactorOption.resetOriginalValues();
	}

	private final TestrayFactorOption _testrayFactorOption;

}