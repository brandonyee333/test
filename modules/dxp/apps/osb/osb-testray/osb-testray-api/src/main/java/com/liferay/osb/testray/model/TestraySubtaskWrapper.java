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
 * This class is a wrapper for {@link TestraySubtask}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySubtask
 * @generated
 */
@ProviderType
public class TestraySubtaskWrapper
	implements TestraySubtask, ModelWrapper<TestraySubtask> {

	public TestraySubtaskWrapper(TestraySubtask testraySubtask) {
		_testraySubtask = testraySubtask;
	}

	@Override
	public Class<?> getModelClass() {
		return TestraySubtask.class;
	}

	@Override
	public String getModelClassName() {
		return TestraySubtask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testraySubtaskId", getTestraySubtaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commentMBMessageId", getCommentMBMessageId());
		attributes.put(
			"mergedToTestraySubtaskId", getMergedToTestraySubtaskId());
		attributes.put(
			"splitFromTestraySubtaskId", getSplitFromTestraySubtaskId());
		attributes.put("testrayTaskId", getTestrayTaskId());
		attributes.put("name", getName());
		attributes.put("score", getScore());
		attributes.put("status", getStatus());
		attributes.put("statusUpdateDate", getStatusUpdateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testraySubtaskId = (Long)attributes.get("testraySubtaskId");

		if (testraySubtaskId != null) {
			setTestraySubtaskId(testraySubtaskId);
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

		Long commentMBMessageId = (Long)attributes.get("commentMBMessageId");

		if (commentMBMessageId != null) {
			setCommentMBMessageId(commentMBMessageId);
		}

		Long mergedToTestraySubtaskId = (Long)attributes.get(
			"mergedToTestraySubtaskId");

		if (mergedToTestraySubtaskId != null) {
			setMergedToTestraySubtaskId(mergedToTestraySubtaskId);
		}

		Long splitFromTestraySubtaskId = (Long)attributes.get(
			"splitFromTestraySubtaskId");

		if (splitFromTestraySubtaskId != null) {
			setSplitFromTestraySubtaskId(splitFromTestraySubtaskId);
		}

		Long testrayTaskId = (Long)attributes.get("testrayTaskId");

		if (testrayTaskId != null) {
			setTestrayTaskId(testrayTaskId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer score = (Integer)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date statusUpdateDate = (Date)attributes.get("statusUpdateDate");

		if (statusUpdateDate != null) {
			setStatusUpdateDate(statusUpdateDate);
		}
	}

	@Override
	public Object clone() {
		return new TestraySubtaskWrapper(
			(TestraySubtask)_testraySubtask.clone());
	}

	@Override
	public int compareTo(TestraySubtask testraySubtask) {
		return _testraySubtask.compareTo(testraySubtask);
	}

	/**
	 * Returns the comment mb message ID of this testray subtask.
	 *
	 * @return the comment mb message ID of this testray subtask
	 */
	@Override
	public long getCommentMBMessageId() {
		return _testraySubtask.getCommentMBMessageId();
	}

	/**
	 * Returns the company ID of this testray subtask.
	 *
	 * @return the company ID of this testray subtask
	 */
	@Override
	public long getCompanyId() {
		return _testraySubtask.getCompanyId();
	}

	/**
	 * Returns the create date of this testray subtask.
	 *
	 * @return the create date of this testray subtask
	 */
	@Override
	public Date getCreateDate() {
		return _testraySubtask.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testraySubtask.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray subtask.
	 *
	 * @return the group ID of this testray subtask
	 */
	@Override
	public long getGroupId() {
		return _testraySubtask.getGroupId();
	}

	/**
	 * Returns the merged to testray subtask ID of this testray subtask.
	 *
	 * @return the merged to testray subtask ID of this testray subtask
	 */
	@Override
	public long getMergedToTestraySubtaskId() {
		return _testraySubtask.getMergedToTestraySubtaskId();
	}

	/**
	 * Returns the modified date of this testray subtask.
	 *
	 * @return the modified date of this testray subtask
	 */
	@Override
	public Date getModifiedDate() {
		return _testraySubtask.getModifiedDate();
	}

	/**
	 * Returns the name of this testray subtask.
	 *
	 * @return the name of this testray subtask
	 */
	@Override
	public String getName() {
		return _testraySubtask.getName();
	}

	/**
	 * Returns the primary key of this testray subtask.
	 *
	 * @return the primary key of this testray subtask
	 */
	@Override
	public long getPrimaryKey() {
		return _testraySubtask.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testraySubtask.getPrimaryKeyObj();
	}

	/**
	 * Returns the score of this testray subtask.
	 *
	 * @return the score of this testray subtask
	 */
	@Override
	public int getScore() {
		return _testraySubtask.getScore();
	}

	/**
	 * Returns the split from testray subtask ID of this testray subtask.
	 *
	 * @return the split from testray subtask ID of this testray subtask
	 */
	@Override
	public long getSplitFromTestraySubtaskId() {
		return _testraySubtask.getSplitFromTestraySubtaskId();
	}

	/**
	 * Returns the status of this testray subtask.
	 *
	 * @return the status of this testray subtask
	 */
	@Override
	public int getStatus() {
		return _testraySubtask.getStatus();
	}

	/**
	 * Returns the status update date of this testray subtask.
	 *
	 * @return the status update date of this testray subtask
	 */
	@Override
	public Date getStatusUpdateDate() {
		return _testraySubtask.getStatusUpdateDate();
	}

	/**
	 * Returns the testray subtask ID of this testray subtask.
	 *
	 * @return the testray subtask ID of this testray subtask
	 */
	@Override
	public long getTestraySubtaskId() {
		return _testraySubtask.getTestraySubtaskId();
	}

	/**
	 * Returns the testray task ID of this testray subtask.
	 *
	 * @return the testray task ID of this testray subtask
	 */
	@Override
	public long getTestrayTaskId() {
		return _testraySubtask.getTestrayTaskId();
	}

	/**
	 * Returns the user ID of this testray subtask.
	 *
	 * @return the user ID of this testray subtask
	 */
	@Override
	public long getUserId() {
		return _testraySubtask.getUserId();
	}

	/**
	 * Returns the user name of this testray subtask.
	 *
	 * @return the user name of this testray subtask
	 */
	@Override
	public String getUserName() {
		return _testraySubtask.getUserName();
	}

	/**
	 * Returns the user uuid of this testray subtask.
	 *
	 * @return the user uuid of this testray subtask
	 */
	@Override
	public String getUserUuid() {
		return _testraySubtask.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testraySubtask.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testraySubtask.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testraySubtask.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testraySubtask.isNew();
	}

	@Override
	public void persist() {
		_testraySubtask.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testraySubtask.setCachedModel(cachedModel);
	}

	/**
	 * Sets the comment mb message ID of this testray subtask.
	 *
	 * @param commentMBMessageId the comment mb message ID of this testray subtask
	 */
	@Override
	public void setCommentMBMessageId(long commentMBMessageId) {
		_testraySubtask.setCommentMBMessageId(commentMBMessageId);
	}

	/**
	 * Sets the company ID of this testray subtask.
	 *
	 * @param companyId the company ID of this testray subtask
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testraySubtask.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray subtask.
	 *
	 * @param createDate the create date of this testray subtask
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testraySubtask.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testraySubtask.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testraySubtask.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testraySubtask.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray subtask.
	 *
	 * @param groupId the group ID of this testray subtask
	 */
	@Override
	public void setGroupId(long groupId) {
		_testraySubtask.setGroupId(groupId);
	}

	/**
	 * Sets the merged to testray subtask ID of this testray subtask.
	 *
	 * @param mergedToTestraySubtaskId the merged to testray subtask ID of this testray subtask
	 */
	@Override
	public void setMergedToTestraySubtaskId(long mergedToTestraySubtaskId) {
		_testraySubtask.setMergedToTestraySubtaskId(mergedToTestraySubtaskId);
	}

	/**
	 * Sets the modified date of this testray subtask.
	 *
	 * @param modifiedDate the modified date of this testray subtask
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testraySubtask.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray subtask.
	 *
	 * @param name the name of this testray subtask
	 */
	@Override
	public void setName(String name) {
		_testraySubtask.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testraySubtask.setNew(n);
	}

	/**
	 * Sets the primary key of this testray subtask.
	 *
	 * @param primaryKey the primary key of this testray subtask
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testraySubtask.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testraySubtask.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the score of this testray subtask.
	 *
	 * @param score the score of this testray subtask
	 */
	@Override
	public void setScore(int score) {
		_testraySubtask.setScore(score);
	}

	/**
	 * Sets the split from testray subtask ID of this testray subtask.
	 *
	 * @param splitFromTestraySubtaskId the split from testray subtask ID of this testray subtask
	 */
	@Override
	public void setSplitFromTestraySubtaskId(long splitFromTestraySubtaskId) {
		_testraySubtask.setSplitFromTestraySubtaskId(splitFromTestraySubtaskId);
	}

	/**
	 * Sets the status of this testray subtask.
	 *
	 * @param status the status of this testray subtask
	 */
	@Override
	public void setStatus(int status) {
		_testraySubtask.setStatus(status);
	}

	/**
	 * Sets the status update date of this testray subtask.
	 *
	 * @param statusUpdateDate the status update date of this testray subtask
	 */
	@Override
	public void setStatusUpdateDate(Date statusUpdateDate) {
		_testraySubtask.setStatusUpdateDate(statusUpdateDate);
	}

	/**
	 * Sets the testray subtask ID of this testray subtask.
	 *
	 * @param testraySubtaskId the testray subtask ID of this testray subtask
	 */
	@Override
	public void setTestraySubtaskId(long testraySubtaskId) {
		_testraySubtask.setTestraySubtaskId(testraySubtaskId);
	}

	/**
	 * Sets the testray task ID of this testray subtask.
	 *
	 * @param testrayTaskId the testray task ID of this testray subtask
	 */
	@Override
	public void setTestrayTaskId(long testrayTaskId) {
		_testraySubtask.setTestrayTaskId(testrayTaskId);
	}

	/**
	 * Sets the user ID of this testray subtask.
	 *
	 * @param userId the user ID of this testray subtask
	 */
	@Override
	public void setUserId(long userId) {
		_testraySubtask.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray subtask.
	 *
	 * @param userName the user name of this testray subtask
	 */
	@Override
	public void setUserName(String userName) {
		_testraySubtask.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray subtask.
	 *
	 * @param userUuid the user uuid of this testray subtask
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testraySubtask.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestraySubtask>
		toCacheModel() {

		return _testraySubtask.toCacheModel();
	}

	@Override
	public TestraySubtask toEscapedModel() {
		return new TestraySubtaskWrapper(_testraySubtask.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testraySubtask.toString();
	}

	@Override
	public TestraySubtask toUnescapedModel() {
		return new TestraySubtaskWrapper(_testraySubtask.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testraySubtask.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestraySubtaskWrapper)) {
			return false;
		}

		TestraySubtaskWrapper testraySubtaskWrapper =
			(TestraySubtaskWrapper)obj;

		if (Objects.equals(
				_testraySubtask, testraySubtaskWrapper._testraySubtask)) {

			return true;
		}

		return false;
	}

	@Override
	public TestraySubtask getWrappedModel() {
		return _testraySubtask;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testraySubtask.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testraySubtask.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testraySubtask.resetOriginalValues();
	}

	private final TestraySubtask _testraySubtask;

}