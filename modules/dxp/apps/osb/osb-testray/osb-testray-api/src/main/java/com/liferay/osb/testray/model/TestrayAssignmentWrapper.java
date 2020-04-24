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
 * This class is a wrapper for {@link TestrayAssignment}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayAssignment
 * @generated
 */
@ProviderType
public class TestrayAssignmentWrapper
	implements TestrayAssignment, ModelWrapper<TestrayAssignment> {

	public TestrayAssignmentWrapper(TestrayAssignment testrayAssignment) {
		_testrayAssignment = testrayAssignment;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayAssignment.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayAssignment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayAssignmentId", getTestrayAssignmentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("assignedUserId", getAssignedUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayAssignmentId = (Long)attributes.get("testrayAssignmentId");

		if (testrayAssignmentId != null) {
			setTestrayAssignmentId(testrayAssignmentId);
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

		Long assignedUserId = (Long)attributes.get("assignedUserId");

		if (assignedUserId != null) {
			setAssignedUserId(assignedUserId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public Object clone() {
		return new TestrayAssignmentWrapper(
			(TestrayAssignment)_testrayAssignment.clone());
	}

	@Override
	public int compareTo(TestrayAssignment testrayAssignment) {
		return _testrayAssignment.compareTo(testrayAssignment);
	}

	/**
	 * Returns the assigned user ID of this testray assignment.
	 *
	 * @return the assigned user ID of this testray assignment
	 */
	@Override
	public long getAssignedUserId() {
		return _testrayAssignment.getAssignedUserId();
	}

	/**
	 * Returns the assigned user uuid of this testray assignment.
	 *
	 * @return the assigned user uuid of this testray assignment
	 */
	@Override
	public String getAssignedUserUuid() {
		return _testrayAssignment.getAssignedUserUuid();
	}

	/**
	 * Returns the fully qualified class name of this testray assignment.
	 *
	 * @return the fully qualified class name of this testray assignment
	 */
	@Override
	public String getClassName() {
		return _testrayAssignment.getClassName();
	}

	/**
	 * Returns the class name ID of this testray assignment.
	 *
	 * @return the class name ID of this testray assignment
	 */
	@Override
	public long getClassNameId() {
		return _testrayAssignment.getClassNameId();
	}

	/**
	 * Returns the class pk of this testray assignment.
	 *
	 * @return the class pk of this testray assignment
	 */
	@Override
	public long getClassPK() {
		return _testrayAssignment.getClassPK();
	}

	/**
	 * Returns the company ID of this testray assignment.
	 *
	 * @return the company ID of this testray assignment
	 */
	@Override
	public long getCompanyId() {
		return _testrayAssignment.getCompanyId();
	}

	/**
	 * Returns the create date of this testray assignment.
	 *
	 * @return the create date of this testray assignment
	 */
	@Override
	public Date getCreateDate() {
		return _testrayAssignment.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayAssignment.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray assignment.
	 *
	 * @return the group ID of this testray assignment
	 */
	@Override
	public long getGroupId() {
		return _testrayAssignment.getGroupId();
	}

	/**
	 * Returns the modified date of this testray assignment.
	 *
	 * @return the modified date of this testray assignment
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayAssignment.getModifiedDate();
	}

	/**
	 * Returns the primary key of this testray assignment.
	 *
	 * @return the primary key of this testray assignment
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayAssignment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayAssignment.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray assignment ID of this testray assignment.
	 *
	 * @return the testray assignment ID of this testray assignment
	 */
	@Override
	public long getTestrayAssignmentId() {
		return _testrayAssignment.getTestrayAssignmentId();
	}

	/**
	 * Returns the user ID of this testray assignment.
	 *
	 * @return the user ID of this testray assignment
	 */
	@Override
	public long getUserId() {
		return _testrayAssignment.getUserId();
	}

	/**
	 * Returns the user name of this testray assignment.
	 *
	 * @return the user name of this testray assignment
	 */
	@Override
	public String getUserName() {
		return _testrayAssignment.getUserName();
	}

	/**
	 * Returns the user uuid of this testray assignment.
	 *
	 * @return the user uuid of this testray assignment
	 */
	@Override
	public String getUserUuid() {
		return _testrayAssignment.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayAssignment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayAssignment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayAssignment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayAssignment.isNew();
	}

	@Override
	public void persist() {
		_testrayAssignment.persist();
	}

	/**
	 * Sets the assigned user ID of this testray assignment.
	 *
	 * @param assignedUserId the assigned user ID of this testray assignment
	 */
	@Override
	public void setAssignedUserId(long assignedUserId) {
		_testrayAssignment.setAssignedUserId(assignedUserId);
	}

	/**
	 * Sets the assigned user uuid of this testray assignment.
	 *
	 * @param assignedUserUuid the assigned user uuid of this testray assignment
	 */
	@Override
	public void setAssignedUserUuid(String assignedUserUuid) {
		_testrayAssignment.setAssignedUserUuid(assignedUserUuid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayAssignment.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_testrayAssignment.setClassName(className);
	}

	/**
	 * Sets the class name ID of this testray assignment.
	 *
	 * @param classNameId the class name ID of this testray assignment
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_testrayAssignment.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this testray assignment.
	 *
	 * @param classPK the class pk of this testray assignment
	 */
	@Override
	public void setClassPK(long classPK) {
		_testrayAssignment.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this testray assignment.
	 *
	 * @param companyId the company ID of this testray assignment
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayAssignment.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray assignment.
	 *
	 * @param createDate the create date of this testray assignment
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayAssignment.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayAssignment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayAssignment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray assignment.
	 *
	 * @param groupId the group ID of this testray assignment
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayAssignment.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray assignment.
	 *
	 * @param modifiedDate the modified date of this testray assignment
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayAssignment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayAssignment.setNew(n);
	}

	/**
	 * Sets the primary key of this testray assignment.
	 *
	 * @param primaryKey the primary key of this testray assignment
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayAssignment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayAssignment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray assignment ID of this testray assignment.
	 *
	 * @param testrayAssignmentId the testray assignment ID of this testray assignment
	 */
	@Override
	public void setTestrayAssignmentId(long testrayAssignmentId) {
		_testrayAssignment.setTestrayAssignmentId(testrayAssignmentId);
	}

	/**
	 * Sets the user ID of this testray assignment.
	 *
	 * @param userId the user ID of this testray assignment
	 */
	@Override
	public void setUserId(long userId) {
		_testrayAssignment.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray assignment.
	 *
	 * @param userName the user name of this testray assignment
	 */
	@Override
	public void setUserName(String userName) {
		_testrayAssignment.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray assignment.
	 *
	 * @param userUuid the user uuid of this testray assignment
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayAssignment.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayAssignment>
		toCacheModel() {

		return _testrayAssignment.toCacheModel();
	}

	@Override
	public TestrayAssignment toEscapedModel() {
		return new TestrayAssignmentWrapper(
			_testrayAssignment.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayAssignment.toString();
	}

	@Override
	public TestrayAssignment toUnescapedModel() {
		return new TestrayAssignmentWrapper(
			_testrayAssignment.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayAssignment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayAssignmentWrapper)) {
			return false;
		}

		TestrayAssignmentWrapper testrayAssignmentWrapper =
			(TestrayAssignmentWrapper)obj;

		if (Objects.equals(
				_testrayAssignment,
				testrayAssignmentWrapper._testrayAssignment)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayAssignment getWrappedModel() {
		return _testrayAssignment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayAssignment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayAssignment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayAssignment.resetOriginalValues();
	}

	private final TestrayAssignment _testrayAssignment;

}