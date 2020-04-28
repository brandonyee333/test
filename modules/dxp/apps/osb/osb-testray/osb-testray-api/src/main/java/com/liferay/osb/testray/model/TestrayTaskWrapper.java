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
 * This class is a wrapper for {@link TestrayTask}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayTask
 * @generated
 */
public class TestrayTaskWrapper
	implements ModelWrapper<TestrayTask>, TestrayTask {

	public TestrayTaskWrapper(TestrayTask testrayTask) {
		_testrayTask = testrayTask;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayTask.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayTaskId", getTestrayTaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayBuildId", getTestrayBuildId());
		attributes.put("name", getName());
		attributes.put("statusUpdateDate", getStatusUpdateDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayTaskId = (Long)attributes.get("testrayTaskId");

		if (testrayTaskId != null) {
			setTestrayTaskId(testrayTaskId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date statusUpdateDate = (Date)attributes.get("statusUpdateDate");

		if (statusUpdateDate != null) {
			setStatusUpdateDate(statusUpdateDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new TestrayTaskWrapper((TestrayTask)_testrayTask.clone());
	}

	@Override
	public int compareTo(TestrayTask testrayTask) {
		return _testrayTask.compareTo(testrayTask);
	}

	/**
	 * Returns the company ID of this testray task.
	 *
	 * @return the company ID of this testray task
	 */
	@Override
	public long getCompanyId() {
		return _testrayTask.getCompanyId();
	}

	/**
	 * Returns the create date of this testray task.
	 *
	 * @return the create date of this testray task
	 */
	@Override
	public Date getCreateDate() {
		return _testrayTask.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayTask.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray task.
	 *
	 * @return the group ID of this testray task
	 */
	@Override
	public long getGroupId() {
		return _testrayTask.getGroupId();
	}

	/**
	 * Returns the modified date of this testray task.
	 *
	 * @return the modified date of this testray task
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayTask.getModifiedDate();
	}

	/**
	 * Returns the name of this testray task.
	 *
	 * @return the name of this testray task
	 */
	@Override
	public String getName() {
		return _testrayTask.getName();
	}

	/**
	 * Returns the primary key of this testray task.
	 *
	 * @return the primary key of this testray task
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayTask.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayTask.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this testray task.
	 *
	 * @return the status of this testray task
	 */
	@Override
	public int getStatus() {
		return _testrayTask.getStatus();
	}

	/**
	 * Returns the status update date of this testray task.
	 *
	 * @return the status update date of this testray task
	 */
	@Override
	public Date getStatusUpdateDate() {
		return _testrayTask.getStatusUpdateDate();
	}

	/**
	 * Returns the testray build ID of this testray task.
	 *
	 * @return the testray build ID of this testray task
	 */
	@Override
	public long getTestrayBuildId() {
		return _testrayTask.getTestrayBuildId();
	}

	/**
	 * Returns the testray task ID of this testray task.
	 *
	 * @return the testray task ID of this testray task
	 */
	@Override
	public long getTestrayTaskId() {
		return _testrayTask.getTestrayTaskId();
	}

	/**
	 * Returns the user ID of this testray task.
	 *
	 * @return the user ID of this testray task
	 */
	@Override
	public long getUserId() {
		return _testrayTask.getUserId();
	}

	/**
	 * Returns the user name of this testray task.
	 *
	 * @return the user name of this testray task
	 */
	@Override
	public String getUserName() {
		return _testrayTask.getUserName();
	}

	/**
	 * Returns the user uuid of this testray task.
	 *
	 * @return the user uuid of this testray task
	 */
	@Override
	public String getUserUuid() {
		return _testrayTask.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayTask.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayTask.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayTask.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayTask.isNew();
	}

	@Override
	public void persist() {
		_testrayTask.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayTask.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray task.
	 *
	 * @param companyId the company ID of this testray task
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayTask.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray task.
	 *
	 * @param createDate the create date of this testray task
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayTask.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayTask.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayTask.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayTask.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray task.
	 *
	 * @param groupId the group ID of this testray task
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayTask.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray task.
	 *
	 * @param modifiedDate the modified date of this testray task
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayTask.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray task.
	 *
	 * @param name the name of this testray task
	 */
	@Override
	public void setName(String name) {
		_testrayTask.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayTask.setNew(n);
	}

	/**
	 * Sets the primary key of this testray task.
	 *
	 * @param primaryKey the primary key of this testray task
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayTask.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayTask.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this testray task.
	 *
	 * @param status the status of this testray task
	 */
	@Override
	public void setStatus(int status) {
		_testrayTask.setStatus(status);
	}

	/**
	 * Sets the status update date of this testray task.
	 *
	 * @param statusUpdateDate the status update date of this testray task
	 */
	@Override
	public void setStatusUpdateDate(Date statusUpdateDate) {
		_testrayTask.setStatusUpdateDate(statusUpdateDate);
	}

	/**
	 * Sets the testray build ID of this testray task.
	 *
	 * @param testrayBuildId the testray build ID of this testray task
	 */
	@Override
	public void setTestrayBuildId(long testrayBuildId) {
		_testrayTask.setTestrayBuildId(testrayBuildId);
	}

	/**
	 * Sets the testray task ID of this testray task.
	 *
	 * @param testrayTaskId the testray task ID of this testray task
	 */
	@Override
	public void setTestrayTaskId(long testrayTaskId) {
		_testrayTask.setTestrayTaskId(testrayTaskId);
	}

	/**
	 * Sets the user ID of this testray task.
	 *
	 * @param userId the user ID of this testray task
	 */
	@Override
	public void setUserId(long userId) {
		_testrayTask.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray task.
	 *
	 * @param userName the user name of this testray task
	 */
	@Override
	public void setUserName(String userName) {
		_testrayTask.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray task.
	 *
	 * @param userUuid the user uuid of this testray task
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayTask.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayTask>
		toCacheModel() {

		return _testrayTask.toCacheModel();
	}

	@Override
	public TestrayTask toEscapedModel() {
		return new TestrayTaskWrapper(_testrayTask.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayTask.toString();
	}

	@Override
	public TestrayTask toUnescapedModel() {
		return new TestrayTaskWrapper(_testrayTask.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayTask.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayTaskWrapper)) {
			return false;
		}

		TestrayTaskWrapper testrayTaskWrapper = (TestrayTaskWrapper)obj;

		if (Objects.equals(_testrayTask, testrayTaskWrapper._testrayTask)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayTask getWrappedModel() {
		return _testrayTask;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayTask.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayTask.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayTask.resetOriginalValues();
	}

	private final TestrayTask _testrayTask;

}