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
 * This class is a wrapper for {@link TestrayIssue}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayIssue
 * @generated
 */
@ProviderType
public class TestrayIssueWrapper
	implements TestrayIssue, ModelWrapper<TestrayIssue> {

	public TestrayIssueWrapper(TestrayIssue testrayIssue) {
		_testrayIssue = testrayIssue;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayIssue.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayIssue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayIssueId", getTestrayIssueId());
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
		Long testrayIssueId = (Long)attributes.get("testrayIssueId");

		if (testrayIssueId != null) {
			setTestrayIssueId(testrayIssueId);
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
		return new TestrayIssueWrapper((TestrayIssue)_testrayIssue.clone());
	}

	@Override
	public int compareTo(TestrayIssue testrayIssue) {
		return _testrayIssue.compareTo(testrayIssue);
	}

	/**
	 * Returns the company ID of this testray issue.
	 *
	 * @return the company ID of this testray issue
	 */
	@Override
	public long getCompanyId() {
		return _testrayIssue.getCompanyId();
	}

	/**
	 * Returns the create date of this testray issue.
	 *
	 * @return the create date of this testray issue
	 */
	@Override
	public Date getCreateDate() {
		return _testrayIssue.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayIssue.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray issue.
	 *
	 * @return the group ID of this testray issue
	 */
	@Override
	public long getGroupId() {
		return _testrayIssue.getGroupId();
	}

	/**
	 * Returns the modified date of this testray issue.
	 *
	 * @return the modified date of this testray issue
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayIssue.getModifiedDate();
	}

	/**
	 * Returns the name of this testray issue.
	 *
	 * @return the name of this testray issue
	 */
	@Override
	public String getName() {
		return _testrayIssue.getName();
	}

	/**
	 * Returns the primary key of this testray issue.
	 *
	 * @return the primary key of this testray issue
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayIssue.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayIssue.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray issue ID of this testray issue.
	 *
	 * @return the testray issue ID of this testray issue
	 */
	@Override
	public long getTestrayIssueId() {
		return _testrayIssue.getTestrayIssueId();
	}

	/**
	 * Returns the user ID of this testray issue.
	 *
	 * @return the user ID of this testray issue
	 */
	@Override
	public long getUserId() {
		return _testrayIssue.getUserId();
	}

	/**
	 * Returns the user name of this testray issue.
	 *
	 * @return the user name of this testray issue
	 */
	@Override
	public String getUserName() {
		return _testrayIssue.getUserName();
	}

	/**
	 * Returns the user uuid of this testray issue.
	 *
	 * @return the user uuid of this testray issue
	 */
	@Override
	public String getUserUuid() {
		return _testrayIssue.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayIssue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayIssue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayIssue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayIssue.isNew();
	}

	@Override
	public void persist() {
		_testrayIssue.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayIssue.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray issue.
	 *
	 * @param companyId the company ID of this testray issue
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayIssue.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray issue.
	 *
	 * @param createDate the create date of this testray issue
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayIssue.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayIssue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayIssue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayIssue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray issue.
	 *
	 * @param groupId the group ID of this testray issue
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayIssue.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray issue.
	 *
	 * @param modifiedDate the modified date of this testray issue
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayIssue.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray issue.
	 *
	 * @param name the name of this testray issue
	 */
	@Override
	public void setName(String name) {
		_testrayIssue.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayIssue.setNew(n);
	}

	/**
	 * Sets the primary key of this testray issue.
	 *
	 * @param primaryKey the primary key of this testray issue
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayIssue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayIssue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray issue ID of this testray issue.
	 *
	 * @param testrayIssueId the testray issue ID of this testray issue
	 */
	@Override
	public void setTestrayIssueId(long testrayIssueId) {
		_testrayIssue.setTestrayIssueId(testrayIssueId);
	}

	/**
	 * Sets the user ID of this testray issue.
	 *
	 * @param userId the user ID of this testray issue
	 */
	@Override
	public void setUserId(long userId) {
		_testrayIssue.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray issue.
	 *
	 * @param userName the user name of this testray issue
	 */
	@Override
	public void setUserName(String userName) {
		_testrayIssue.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray issue.
	 *
	 * @param userUuid the user uuid of this testray issue
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayIssue.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayIssue>
		toCacheModel() {

		return _testrayIssue.toCacheModel();
	}

	@Override
	public TestrayIssue toEscapedModel() {
		return new TestrayIssueWrapper(_testrayIssue.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayIssue.toString();
	}

	@Override
	public TestrayIssue toUnescapedModel() {
		return new TestrayIssueWrapper(_testrayIssue.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayIssue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayIssueWrapper)) {
			return false;
		}

		TestrayIssueWrapper testrayIssueWrapper = (TestrayIssueWrapper)obj;

		if (Objects.equals(_testrayIssue, testrayIssueWrapper._testrayIssue)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayIssue getWrappedModel() {
		return _testrayIssue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayIssue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayIssue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayIssue.resetOriginalValues();
	}

	private final TestrayIssue _testrayIssue;

}