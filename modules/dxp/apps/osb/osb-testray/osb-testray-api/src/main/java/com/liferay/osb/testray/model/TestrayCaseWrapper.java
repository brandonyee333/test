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
 * This class is a wrapper for {@link TestrayCase}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCase
 * @generated
 */
public class TestrayCaseWrapper
	implements ModelWrapper<TestrayCase>, TestrayCase {

	public TestrayCaseWrapper(TestrayCase testrayCase) {
		_testrayCase = testrayCase;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayCase.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayCase.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayCaseId", getTestrayCaseId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayCaseTypeId", getTestrayCaseTypeId());
		attributes.put("testrayComponentId", getTestrayComponentId());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("descriptionType", getDescriptionType());
		attributes.put("originationKey", getOriginationKey());
		attributes.put("priority", getPriority());
		attributes.put("estimatedDuration", getEstimatedDuration());
		attributes.put("steps", getSteps());
		attributes.put("stepsType", getStepsType());
		attributes.put("caseNumber", getCaseNumber());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayCaseId = (Long)attributes.get("testrayCaseId");

		if (testrayCaseId != null) {
			setTestrayCaseId(testrayCaseId);
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

		Long testrayCaseTypeId = (Long)attributes.get("testrayCaseTypeId");

		if (testrayCaseTypeId != null) {
			setTestrayCaseTypeId(testrayCaseTypeId);
		}

		Long testrayComponentId = (Long)attributes.get("testrayComponentId");

		if (testrayComponentId != null) {
			setTestrayComponentId(testrayComponentId);
		}

		Long testrayProjectId = (Long)attributes.get("testrayProjectId");

		if (testrayProjectId != null) {
			setTestrayProjectId(testrayProjectId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String descriptionType = (String)attributes.get("descriptionType");

		if (descriptionType != null) {
			setDescriptionType(descriptionType);
		}

		Long originationKey = (Long)attributes.get("originationKey");

		if (originationKey != null) {
			setOriginationKey(originationKey);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		Integer estimatedDuration = (Integer)attributes.get(
			"estimatedDuration");

		if (estimatedDuration != null) {
			setEstimatedDuration(estimatedDuration);
		}

		String steps = (String)attributes.get("steps");

		if (steps != null) {
			setSteps(steps);
		}

		String stepsType = (String)attributes.get("stepsType");

		if (stepsType != null) {
			setStepsType(stepsType);
		}

		Long caseNumber = (Long)attributes.get("caseNumber");

		if (caseNumber != null) {
			setCaseNumber(caseNumber);
		}
	}

	@Override
	public Object clone() {
		return new TestrayCaseWrapper((TestrayCase)_testrayCase.clone());
	}

	@Override
	public int compareTo(TestrayCase testrayCase) {
		return _testrayCase.compareTo(testrayCase);
	}

	/**
	 * Returns the case number of this testray case.
	 *
	 * @return the case number of this testray case
	 */
	@Override
	public long getCaseNumber() {
		return _testrayCase.getCaseNumber();
	}

	/**
	 * Returns the company ID of this testray case.
	 *
	 * @return the company ID of this testray case
	 */
	@Override
	public long getCompanyId() {
		return _testrayCase.getCompanyId();
	}

	/**
	 * Returns the create date of this testray case.
	 *
	 * @return the create date of this testray case
	 */
	@Override
	public Date getCreateDate() {
		return _testrayCase.getCreateDate();
	}

	/**
	 * Returns the description of this testray case.
	 *
	 * @return the description of this testray case
	 */
	@Override
	public String getDescription() {
		return _testrayCase.getDescription();
	}

	/**
	 * Returns the description type of this testray case.
	 *
	 * @return the description type of this testray case
	 */
	@Override
	public String getDescriptionType() {
		return _testrayCase.getDescriptionType();
	}

	/**
	 * Returns the estimated duration of this testray case.
	 *
	 * @return the estimated duration of this testray case
	 */
	@Override
	public int getEstimatedDuration() {
		return _testrayCase.getEstimatedDuration();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayCase.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray case.
	 *
	 * @return the group ID of this testray case
	 */
	@Override
	public long getGroupId() {
		return _testrayCase.getGroupId();
	}

	/**
	 * Returns the modified date of this testray case.
	 *
	 * @return the modified date of this testray case
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayCase.getModifiedDate();
	}

	/**
	 * Returns the name of this testray case.
	 *
	 * @return the name of this testray case
	 */
	@Override
	public String getName() {
		return _testrayCase.getName();
	}

	/**
	 * Returns the origination key of this testray case.
	 *
	 * @return the origination key of this testray case
	 */
	@Override
	public long getOriginationKey() {
		return _testrayCase.getOriginationKey();
	}

	/**
	 * Returns the primary key of this testray case.
	 *
	 * @return the primary key of this testray case
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayCase.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayCase.getPrimaryKeyObj();
	}

	/**
	 * Returns the priority of this testray case.
	 *
	 * @return the priority of this testray case
	 */
	@Override
	public int getPriority() {
		return _testrayCase.getPriority();
	}

	/**
	 * Returns the steps of this testray case.
	 *
	 * @return the steps of this testray case
	 */
	@Override
	public String getSteps() {
		return _testrayCase.getSteps();
	}

	/**
	 * Returns the steps type of this testray case.
	 *
	 * @return the steps type of this testray case
	 */
	@Override
	public String getStepsType() {
		return _testrayCase.getStepsType();
	}

	/**
	 * Returns the testray case ID of this testray case.
	 *
	 * @return the testray case ID of this testray case
	 */
	@Override
	public long getTestrayCaseId() {
		return _testrayCase.getTestrayCaseId();
	}

	/**
	 * Returns the testray case type ID of this testray case.
	 *
	 * @return the testray case type ID of this testray case
	 */
	@Override
	public long getTestrayCaseTypeId() {
		return _testrayCase.getTestrayCaseTypeId();
	}

	/**
	 * Returns the testray component ID of this testray case.
	 *
	 * @return the testray component ID of this testray case
	 */
	@Override
	public long getTestrayComponentId() {
		return _testrayCase.getTestrayComponentId();
	}

	/**
	 * Returns the testray project ID of this testray case.
	 *
	 * @return the testray project ID of this testray case
	 */
	@Override
	public long getTestrayProjectId() {
		return _testrayCase.getTestrayProjectId();
	}

	/**
	 * Returns the user ID of this testray case.
	 *
	 * @return the user ID of this testray case
	 */
	@Override
	public long getUserId() {
		return _testrayCase.getUserId();
	}

	/**
	 * Returns the user name of this testray case.
	 *
	 * @return the user name of this testray case
	 */
	@Override
	public String getUserName() {
		return _testrayCase.getUserName();
	}

	/**
	 * Returns the user uuid of this testray case.
	 *
	 * @return the user uuid of this testray case
	 */
	@Override
	public String getUserUuid() {
		return _testrayCase.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayCase.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayCase.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayCase.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayCase.isNew();
	}

	@Override
	public void persist() {
		_testrayCase.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayCase.setCachedModel(cachedModel);
	}

	/**
	 * Sets the case number of this testray case.
	 *
	 * @param caseNumber the case number of this testray case
	 */
	@Override
	public void setCaseNumber(long caseNumber) {
		_testrayCase.setCaseNumber(caseNumber);
	}

	/**
	 * Sets the company ID of this testray case.
	 *
	 * @param companyId the company ID of this testray case
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayCase.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray case.
	 *
	 * @param createDate the create date of this testray case
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayCase.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this testray case.
	 *
	 * @param description the description of this testray case
	 */
	@Override
	public void setDescription(String description) {
		_testrayCase.setDescription(description);
	}

	/**
	 * Sets the description type of this testray case.
	 *
	 * @param descriptionType the description type of this testray case
	 */
	@Override
	public void setDescriptionType(String descriptionType) {
		_testrayCase.setDescriptionType(descriptionType);
	}

	/**
	 * Sets the estimated duration of this testray case.
	 *
	 * @param estimatedDuration the estimated duration of this testray case
	 */
	@Override
	public void setEstimatedDuration(int estimatedDuration) {
		_testrayCase.setEstimatedDuration(estimatedDuration);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayCase.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayCase.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayCase.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray case.
	 *
	 * @param groupId the group ID of this testray case
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayCase.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray case.
	 *
	 * @param modifiedDate the modified date of this testray case
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayCase.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray case.
	 *
	 * @param name the name of this testray case
	 */
	@Override
	public void setName(String name) {
		_testrayCase.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayCase.setNew(n);
	}

	/**
	 * Sets the origination key of this testray case.
	 *
	 * @param originationKey the origination key of this testray case
	 */
	@Override
	public void setOriginationKey(long originationKey) {
		_testrayCase.setOriginationKey(originationKey);
	}

	/**
	 * Sets the primary key of this testray case.
	 *
	 * @param primaryKey the primary key of this testray case
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayCase.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayCase.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the priority of this testray case.
	 *
	 * @param priority the priority of this testray case
	 */
	@Override
	public void setPriority(int priority) {
		_testrayCase.setPriority(priority);
	}

	/**
	 * Sets the steps of this testray case.
	 *
	 * @param steps the steps of this testray case
	 */
	@Override
	public void setSteps(String steps) {
		_testrayCase.setSteps(steps);
	}

	/**
	 * Sets the steps type of this testray case.
	 *
	 * @param stepsType the steps type of this testray case
	 */
	@Override
	public void setStepsType(String stepsType) {
		_testrayCase.setStepsType(stepsType);
	}

	/**
	 * Sets the testray case ID of this testray case.
	 *
	 * @param testrayCaseId the testray case ID of this testray case
	 */
	@Override
	public void setTestrayCaseId(long testrayCaseId) {
		_testrayCase.setTestrayCaseId(testrayCaseId);
	}

	/**
	 * Sets the testray case type ID of this testray case.
	 *
	 * @param testrayCaseTypeId the testray case type ID of this testray case
	 */
	@Override
	public void setTestrayCaseTypeId(long testrayCaseTypeId) {
		_testrayCase.setTestrayCaseTypeId(testrayCaseTypeId);
	}

	/**
	 * Sets the testray component ID of this testray case.
	 *
	 * @param testrayComponentId the testray component ID of this testray case
	 */
	@Override
	public void setTestrayComponentId(long testrayComponentId) {
		_testrayCase.setTestrayComponentId(testrayComponentId);
	}

	/**
	 * Sets the testray project ID of this testray case.
	 *
	 * @param testrayProjectId the testray project ID of this testray case
	 */
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayCase.setTestrayProjectId(testrayProjectId);
	}

	/**
	 * Sets the user ID of this testray case.
	 *
	 * @param userId the user ID of this testray case
	 */
	@Override
	public void setUserId(long userId) {
		_testrayCase.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray case.
	 *
	 * @param userName the user name of this testray case
	 */
	@Override
	public void setUserName(String userName) {
		_testrayCase.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray case.
	 *
	 * @param userUuid the user uuid of this testray case
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayCase.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayCase>
		toCacheModel() {

		return _testrayCase.toCacheModel();
	}

	@Override
	public TestrayCase toEscapedModel() {
		return new TestrayCaseWrapper(_testrayCase.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayCase.toString();
	}

	@Override
	public TestrayCase toUnescapedModel() {
		return new TestrayCaseWrapper(_testrayCase.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayCase.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseWrapper)) {
			return false;
		}

		TestrayCaseWrapper testrayCaseWrapper = (TestrayCaseWrapper)obj;

		if (Objects.equals(_testrayCase, testrayCaseWrapper._testrayCase)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayCase getWrappedModel() {
		return _testrayCase;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayCase.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayCase.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayCase.resetOriginalValues();
	}

	private final TestrayCase _testrayCase;

}