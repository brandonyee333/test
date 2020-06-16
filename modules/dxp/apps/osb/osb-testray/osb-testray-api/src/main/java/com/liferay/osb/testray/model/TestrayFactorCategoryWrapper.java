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
 * This class is a wrapper for {@link TestrayFactorCategory}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorCategory
 * @generated
 */
public class TestrayFactorCategoryWrapper
	implements ModelWrapper<TestrayFactorCategory>, TestrayFactorCategory {

	public TestrayFactorCategoryWrapper(
		TestrayFactorCategory testrayFactorCategory) {

		_testrayFactorCategory = testrayFactorCategory;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayFactorCategory.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayFactorCategory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayFactorCategoryId", getTestrayFactorCategoryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayFactorCategoryId = (Long)attributes.get(
			"testrayFactorCategoryId");

		if (testrayFactorCategoryId != null) {
			setTestrayFactorCategoryId(testrayFactorCategoryId);
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
	}

	@Override
	public Object clone() {
		return new TestrayFactorCategoryWrapper(
			(TestrayFactorCategory)_testrayFactorCategory.clone());
	}

	@Override
	public int compareTo(TestrayFactorCategory testrayFactorCategory) {
		return _testrayFactorCategory.compareTo(testrayFactorCategory);
	}

	/**
	 * Returns the company ID of this testray factor category.
	 *
	 * @return the company ID of this testray factor category
	 */
	@Override
	public long getCompanyId() {
		return _testrayFactorCategory.getCompanyId();
	}

	/**
	 * Returns the create date of this testray factor category.
	 *
	 * @return the create date of this testray factor category
	 */
	@Override
	public Date getCreateDate() {
		return _testrayFactorCategory.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayFactorCategory.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray factor category.
	 *
	 * @return the group ID of this testray factor category
	 */
	@Override
	public long getGroupId() {
		return _testrayFactorCategory.getGroupId();
	}

	/**
	 * Returns the modified date of this testray factor category.
	 *
	 * @return the modified date of this testray factor category
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayFactorCategory.getModifiedDate();
	}

	/**
	 * Returns the name of this testray factor category.
	 *
	 * @return the name of this testray factor category
	 */
	@Override
	public String getName() {
		return _testrayFactorCategory.getName();
	}

	/**
	 * Returns the primary key of this testray factor category.
	 *
	 * @return the primary key of this testray factor category
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayFactorCategory.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayFactorCategory.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray factor category ID of this testray factor category.
	 *
	 * @return the testray factor category ID of this testray factor category
	 */
	@Override
	public long getTestrayFactorCategoryId() {
		return _testrayFactorCategory.getTestrayFactorCategoryId();
	}

	/**
	 * Returns the user ID of this testray factor category.
	 *
	 * @return the user ID of this testray factor category
	 */
	@Override
	public long getUserId() {
		return _testrayFactorCategory.getUserId();
	}

	/**
	 * Returns the user name of this testray factor category.
	 *
	 * @return the user name of this testray factor category
	 */
	@Override
	public String getUserName() {
		return _testrayFactorCategory.getUserName();
	}

	/**
	 * Returns the user uuid of this testray factor category.
	 *
	 * @return the user uuid of this testray factor category
	 */
	@Override
	public String getUserUuid() {
		return _testrayFactorCategory.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayFactorCategory.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayFactorCategory.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayFactorCategory.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayFactorCategory.isNew();
	}

	@Override
	public void persist() {
		_testrayFactorCategory.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayFactorCategory.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray factor category.
	 *
	 * @param companyId the company ID of this testray factor category
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayFactorCategory.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray factor category.
	 *
	 * @param createDate the create date of this testray factor category
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayFactorCategory.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayFactorCategory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayFactorCategory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayFactorCategory.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray factor category.
	 *
	 * @param groupId the group ID of this testray factor category
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayFactorCategory.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray factor category.
	 *
	 * @param modifiedDate the modified date of this testray factor category
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayFactorCategory.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray factor category.
	 *
	 * @param name the name of this testray factor category
	 */
	@Override
	public void setName(String name) {
		_testrayFactorCategory.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayFactorCategory.setNew(n);
	}

	/**
	 * Sets the primary key of this testray factor category.
	 *
	 * @param primaryKey the primary key of this testray factor category
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayFactorCategory.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayFactorCategory.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray factor category ID of this testray factor category.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID of this testray factor category
	 */
	@Override
	public void setTestrayFactorCategoryId(long testrayFactorCategoryId) {
		_testrayFactorCategory.setTestrayFactorCategoryId(
			testrayFactorCategoryId);
	}

	/**
	 * Sets the user ID of this testray factor category.
	 *
	 * @param userId the user ID of this testray factor category
	 */
	@Override
	public void setUserId(long userId) {
		_testrayFactorCategory.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray factor category.
	 *
	 * @param userName the user name of this testray factor category
	 */
	@Override
	public void setUserName(String userName) {
		_testrayFactorCategory.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray factor category.
	 *
	 * @param userUuid the user uuid of this testray factor category
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayFactorCategory.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayFactorCategory>
		toCacheModel() {

		return _testrayFactorCategory.toCacheModel();
	}

	@Override
	public TestrayFactorCategory toEscapedModel() {
		return new TestrayFactorCategoryWrapper(
			_testrayFactorCategory.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayFactorCategory.toString();
	}

	@Override
	public TestrayFactorCategory toUnescapedModel() {
		return new TestrayFactorCategoryWrapper(
			_testrayFactorCategory.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayFactorCategory.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorCategoryWrapper)) {
			return false;
		}

		TestrayFactorCategoryWrapper testrayFactorCategoryWrapper =
			(TestrayFactorCategoryWrapper)obj;

		if (Objects.equals(
				_testrayFactorCategory,
				testrayFactorCategoryWrapper._testrayFactorCategory)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayFactorCategory getWrappedModel() {
		return _testrayFactorCategory;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayFactorCategory.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayFactorCategory.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayFactorCategory.resetOriginalValues();
	}

	private final TestrayFactorCategory _testrayFactorCategory;

}