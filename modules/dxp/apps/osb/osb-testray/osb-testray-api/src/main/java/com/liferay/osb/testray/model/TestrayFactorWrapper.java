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
 * This class is a wrapper for {@link TestrayFactor}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactor
 * @generated
 */
public class TestrayFactorWrapper
	implements ModelWrapper<TestrayFactor>, TestrayFactor {

	public TestrayFactorWrapper(TestrayFactor testrayFactor) {
		_testrayFactor = testrayFactor;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayFactor.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayFactor.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayFactorId", getTestrayFactorId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("testrayFactorCategoryId", getTestrayFactorCategoryId());
		attributes.put(
			"testrayFactorCategoryName", getTestrayFactorCategoryName());
		attributes.put("testrayFactorOptionId", getTestrayFactorOptionId());
		attributes.put("testrayFactorOptionName", getTestrayFactorOptionName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayFactorId = (Long)attributes.get("testrayFactorId");

		if (testrayFactorId != null) {
			setTestrayFactorId(testrayFactorId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long testrayFactorCategoryId = (Long)attributes.get(
			"testrayFactorCategoryId");

		if (testrayFactorCategoryId != null) {
			setTestrayFactorCategoryId(testrayFactorCategoryId);
		}

		String testrayFactorCategoryName = (String)attributes.get(
			"testrayFactorCategoryName");

		if (testrayFactorCategoryName != null) {
			setTestrayFactorCategoryName(testrayFactorCategoryName);
		}

		Long testrayFactorOptionId = (Long)attributes.get(
			"testrayFactorOptionId");

		if (testrayFactorOptionId != null) {
			setTestrayFactorOptionId(testrayFactorOptionId);
		}

		String testrayFactorOptionName = (String)attributes.get(
			"testrayFactorOptionName");

		if (testrayFactorOptionName != null) {
			setTestrayFactorOptionName(testrayFactorOptionName);
		}
	}

	@Override
	public Object clone() {
		return new TestrayFactorWrapper((TestrayFactor)_testrayFactor.clone());
	}

	@Override
	public int compareTo(TestrayFactor testrayFactor) {
		return _testrayFactor.compareTo(testrayFactor);
	}

	/**
	 * Returns the fully qualified class name of this testray factor.
	 *
	 * @return the fully qualified class name of this testray factor
	 */
	@Override
	public String getClassName() {
		return _testrayFactor.getClassName();
	}

	/**
	 * Returns the class name ID of this testray factor.
	 *
	 * @return the class name ID of this testray factor
	 */
	@Override
	public long getClassNameId() {
		return _testrayFactor.getClassNameId();
	}

	/**
	 * Returns the class pk of this testray factor.
	 *
	 * @return the class pk of this testray factor
	 */
	@Override
	public long getClassPK() {
		return _testrayFactor.getClassPK();
	}

	/**
	 * Returns the company ID of this testray factor.
	 *
	 * @return the company ID of this testray factor
	 */
	@Override
	public long getCompanyId() {
		return _testrayFactor.getCompanyId();
	}

	/**
	 * Returns the create date of this testray factor.
	 *
	 * @return the create date of this testray factor
	 */
	@Override
	public Date getCreateDate() {
		return _testrayFactor.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayFactor.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray factor.
	 *
	 * @return the group ID of this testray factor
	 */
	@Override
	public long getGroupId() {
		return _testrayFactor.getGroupId();
	}

	/**
	 * Returns the modified date of this testray factor.
	 *
	 * @return the modified date of this testray factor
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayFactor.getModifiedDate();
	}

	/**
	 * Returns the primary key of this testray factor.
	 *
	 * @return the primary key of this testray factor
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayFactor.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayFactor.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray factor category ID of this testray factor.
	 *
	 * @return the testray factor category ID of this testray factor
	 */
	@Override
	public long getTestrayFactorCategoryId() {
		return _testrayFactor.getTestrayFactorCategoryId();
	}

	/**
	 * Returns the testray factor category name of this testray factor.
	 *
	 * @return the testray factor category name of this testray factor
	 */
	@Override
	public String getTestrayFactorCategoryName() {
		return _testrayFactor.getTestrayFactorCategoryName();
	}

	/**
	 * Returns the testray factor ID of this testray factor.
	 *
	 * @return the testray factor ID of this testray factor
	 */
	@Override
	public long getTestrayFactorId() {
		return _testrayFactor.getTestrayFactorId();
	}

	/**
	 * Returns the testray factor option ID of this testray factor.
	 *
	 * @return the testray factor option ID of this testray factor
	 */
	@Override
	public long getTestrayFactorOptionId() {
		return _testrayFactor.getTestrayFactorOptionId();
	}

	/**
	 * Returns the testray factor option name of this testray factor.
	 *
	 * @return the testray factor option name of this testray factor
	 */
	@Override
	public String getTestrayFactorOptionName() {
		return _testrayFactor.getTestrayFactorOptionName();
	}

	/**
	 * Returns the user ID of this testray factor.
	 *
	 * @return the user ID of this testray factor
	 */
	@Override
	public long getUserId() {
		return _testrayFactor.getUserId();
	}

	/**
	 * Returns the user name of this testray factor.
	 *
	 * @return the user name of this testray factor
	 */
	@Override
	public String getUserName() {
		return _testrayFactor.getUserName();
	}

	/**
	 * Returns the user uuid of this testray factor.
	 *
	 * @return the user uuid of this testray factor
	 */
	@Override
	public String getUserUuid() {
		return _testrayFactor.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayFactor.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayFactor.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayFactor.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayFactor.isNew();
	}

	@Override
	public void persist() {
		_testrayFactor.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayFactor.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_testrayFactor.setClassName(className);
	}

	/**
	 * Sets the class name ID of this testray factor.
	 *
	 * @param classNameId the class name ID of this testray factor
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_testrayFactor.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this testray factor.
	 *
	 * @param classPK the class pk of this testray factor
	 */
	@Override
	public void setClassPK(long classPK) {
		_testrayFactor.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this testray factor.
	 *
	 * @param companyId the company ID of this testray factor
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayFactor.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray factor.
	 *
	 * @param createDate the create date of this testray factor
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayFactor.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayFactor.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayFactor.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayFactor.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray factor.
	 *
	 * @param groupId the group ID of this testray factor
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayFactor.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray factor.
	 *
	 * @param modifiedDate the modified date of this testray factor
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayFactor.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayFactor.setNew(n);
	}

	/**
	 * Sets the primary key of this testray factor.
	 *
	 * @param primaryKey the primary key of this testray factor
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayFactor.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayFactor.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray factor category ID of this testray factor.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID of this testray factor
	 */
	@Override
	public void setTestrayFactorCategoryId(long testrayFactorCategoryId) {
		_testrayFactor.setTestrayFactorCategoryId(testrayFactorCategoryId);
	}

	/**
	 * Sets the testray factor category name of this testray factor.
	 *
	 * @param testrayFactorCategoryName the testray factor category name of this testray factor
	 */
	@Override
	public void setTestrayFactorCategoryName(String testrayFactorCategoryName) {
		_testrayFactor.setTestrayFactorCategoryName(testrayFactorCategoryName);
	}

	/**
	 * Sets the testray factor ID of this testray factor.
	 *
	 * @param testrayFactorId the testray factor ID of this testray factor
	 */
	@Override
	public void setTestrayFactorId(long testrayFactorId) {
		_testrayFactor.setTestrayFactorId(testrayFactorId);
	}

	/**
	 * Sets the testray factor option ID of this testray factor.
	 *
	 * @param testrayFactorOptionId the testray factor option ID of this testray factor
	 */
	@Override
	public void setTestrayFactorOptionId(long testrayFactorOptionId) {
		_testrayFactor.setTestrayFactorOptionId(testrayFactorOptionId);
	}

	/**
	 * Sets the testray factor option name of this testray factor.
	 *
	 * @param testrayFactorOptionName the testray factor option name of this testray factor
	 */
	@Override
	public void setTestrayFactorOptionName(String testrayFactorOptionName) {
		_testrayFactor.setTestrayFactorOptionName(testrayFactorOptionName);
	}

	/**
	 * Sets the user ID of this testray factor.
	 *
	 * @param userId the user ID of this testray factor
	 */
	@Override
	public void setUserId(long userId) {
		_testrayFactor.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray factor.
	 *
	 * @param userName the user name of this testray factor
	 */
	@Override
	public void setUserName(String userName) {
		_testrayFactor.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray factor.
	 *
	 * @param userUuid the user uuid of this testray factor
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayFactor.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayFactor>
		toCacheModel() {

		return _testrayFactor.toCacheModel();
	}

	@Override
	public TestrayFactor toEscapedModel() {
		return new TestrayFactorWrapper(_testrayFactor.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayFactor.toString();
	}

	@Override
	public TestrayFactor toUnescapedModel() {
		return new TestrayFactorWrapper(_testrayFactor.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayFactor.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorWrapper)) {
			return false;
		}

		TestrayFactorWrapper testrayFactorWrapper = (TestrayFactorWrapper)obj;

		if (Objects.equals(
				_testrayFactor, testrayFactorWrapper._testrayFactor)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayFactor getWrappedModel() {
		return _testrayFactor;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayFactor.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayFactor.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayFactor.resetOriginalValues();
	}

	private final TestrayFactor _testrayFactor;

}