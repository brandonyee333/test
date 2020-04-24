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
 * This class is a wrapper for {@link TestrayRun}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRun
 * @generated
 */
@ProviderType
public class TestrayRunWrapper implements TestrayRun, ModelWrapper<TestrayRun> {

	public TestrayRunWrapper(TestrayRun testrayRun) {
		_testrayRun = testrayRun;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayRun.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayRun.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayRunId", getTestrayRunId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayBuildId", getTestrayBuildId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("externalReferencePK", getExternalReferencePK());
		attributes.put("externalReferenceType", getExternalReferenceType());
		attributes.put("jenkinsJobKey", getJenkinsJobKey());
		attributes.put("number", getNumber());
		attributes.put("environmentHash", getEnvironmentHash());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayRunId = (Long)attributes.get("testrayRunId");

		if (testrayRunId != null) {
			setTestrayRunId(testrayRunId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String externalReferencePK = (String)attributes.get(
			"externalReferencePK");

		if (externalReferencePK != null) {
			setExternalReferencePK(externalReferencePK);
		}

		Integer externalReferenceType = (Integer)attributes.get(
			"externalReferenceType");

		if (externalReferenceType != null) {
			setExternalReferenceType(externalReferenceType);
		}

		Long jenkinsJobKey = (Long)attributes.get("jenkinsJobKey");

		if (jenkinsJobKey != null) {
			setJenkinsJobKey(jenkinsJobKey);
		}

		Long number = (Long)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		String environmentHash = (String)attributes.get("environmentHash");

		if (environmentHash != null) {
			setEnvironmentHash(environmentHash);
		}
	}

	@Override
	public Object clone() {
		return new TestrayRunWrapper((TestrayRun)_testrayRun.clone());
	}

	@Override
	public int compareTo(TestrayRun testrayRun) {
		return _testrayRun.compareTo(testrayRun);
	}

	/**
	 * Returns the company ID of this testray run.
	 *
	 * @return the company ID of this testray run
	 */
	@Override
	public long getCompanyId() {
		return _testrayRun.getCompanyId();
	}

	/**
	 * Returns the create date of this testray run.
	 *
	 * @return the create date of this testray run
	 */
	@Override
	public Date getCreateDate() {
		return _testrayRun.getCreateDate();
	}

	/**
	 * Returns the description of this testray run.
	 *
	 * @return the description of this testray run
	 */
	@Override
	public String getDescription() {
		return _testrayRun.getDescription();
	}

	/**
	 * Returns the environment hash of this testray run.
	 *
	 * @return the environment hash of this testray run
	 */
	@Override
	public String getEnvironmentHash() {
		return _testrayRun.getEnvironmentHash();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayRun.getExpandoBridge();
	}

	/**
	 * Returns the external reference pk of this testray run.
	 *
	 * @return the external reference pk of this testray run
	 */
	@Override
	public String getExternalReferencePK() {
		return _testrayRun.getExternalReferencePK();
	}

	/**
	 * Returns the external reference type of this testray run.
	 *
	 * @return the external reference type of this testray run
	 */
	@Override
	public int getExternalReferenceType() {
		return _testrayRun.getExternalReferenceType();
	}

	/**
	 * Returns the group ID of this testray run.
	 *
	 * @return the group ID of this testray run
	 */
	@Override
	public long getGroupId() {
		return _testrayRun.getGroupId();
	}

	/**
	 * Returns the jenkins job key of this testray run.
	 *
	 * @return the jenkins job key of this testray run
	 */
	@Override
	public long getJenkinsJobKey() {
		return _testrayRun.getJenkinsJobKey();
	}

	/**
	 * Returns the modified date of this testray run.
	 *
	 * @return the modified date of this testray run
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayRun.getModifiedDate();
	}

	/**
	 * Returns the name of this testray run.
	 *
	 * @return the name of this testray run
	 */
	@Override
	public String getName() {
		return _testrayRun.getName();
	}

	/**
	 * Returns the number of this testray run.
	 *
	 * @return the number of this testray run
	 */
	@Override
	public long getNumber() {
		return _testrayRun.getNumber();
	}

	/**
	 * Returns the primary key of this testray run.
	 *
	 * @return the primary key of this testray run
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayRun.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayRun.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray build ID of this testray run.
	 *
	 * @return the testray build ID of this testray run
	 */
	@Override
	public long getTestrayBuildId() {
		return _testrayRun.getTestrayBuildId();
	}

	/**
	 * Returns the testray run ID of this testray run.
	 *
	 * @return the testray run ID of this testray run
	 */
	@Override
	public long getTestrayRunId() {
		return _testrayRun.getTestrayRunId();
	}

	/**
	 * Returns the user ID of this testray run.
	 *
	 * @return the user ID of this testray run
	 */
	@Override
	public long getUserId() {
		return _testrayRun.getUserId();
	}

	/**
	 * Returns the user name of this testray run.
	 *
	 * @return the user name of this testray run
	 */
	@Override
	public String getUserName() {
		return _testrayRun.getUserName();
	}

	/**
	 * Returns the user uuid of this testray run.
	 *
	 * @return the user uuid of this testray run
	 */
	@Override
	public String getUserUuid() {
		return _testrayRun.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayRun.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayRun.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayRun.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayRun.isNew();
	}

	@Override
	public void persist() {
		_testrayRun.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayRun.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray run.
	 *
	 * @param companyId the company ID of this testray run
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayRun.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray run.
	 *
	 * @param createDate the create date of this testray run
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayRun.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this testray run.
	 *
	 * @param description the description of this testray run
	 */
	@Override
	public void setDescription(String description) {
		_testrayRun.setDescription(description);
	}

	/**
	 * Sets the environment hash of this testray run.
	 *
	 * @param environmentHash the environment hash of this testray run
	 */
	@Override
	public void setEnvironmentHash(String environmentHash) {
		_testrayRun.setEnvironmentHash(environmentHash);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayRun.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayRun.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayRun.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the external reference pk of this testray run.
	 *
	 * @param externalReferencePK the external reference pk of this testray run
	 */
	@Override
	public void setExternalReferencePK(String externalReferencePK) {
		_testrayRun.setExternalReferencePK(externalReferencePK);
	}

	/**
	 * Sets the external reference type of this testray run.
	 *
	 * @param externalReferenceType the external reference type of this testray run
	 */
	@Override
	public void setExternalReferenceType(int externalReferenceType) {
		_testrayRun.setExternalReferenceType(externalReferenceType);
	}

	/**
	 * Sets the group ID of this testray run.
	 *
	 * @param groupId the group ID of this testray run
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayRun.setGroupId(groupId);
	}

	/**
	 * Sets the jenkins job key of this testray run.
	 *
	 * @param jenkinsJobKey the jenkins job key of this testray run
	 */
	@Override
	public void setJenkinsJobKey(long jenkinsJobKey) {
		_testrayRun.setJenkinsJobKey(jenkinsJobKey);
	}

	/**
	 * Sets the modified date of this testray run.
	 *
	 * @param modifiedDate the modified date of this testray run
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayRun.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray run.
	 *
	 * @param name the name of this testray run
	 */
	@Override
	public void setName(String name) {
		_testrayRun.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayRun.setNew(n);
	}

	/**
	 * Sets the number of this testray run.
	 *
	 * @param number the number of this testray run
	 */
	@Override
	public void setNumber(long number) {
		_testrayRun.setNumber(number);
	}

	/**
	 * Sets the primary key of this testray run.
	 *
	 * @param primaryKey the primary key of this testray run
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayRun.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayRun.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray build ID of this testray run.
	 *
	 * @param testrayBuildId the testray build ID of this testray run
	 */
	@Override
	public void setTestrayBuildId(long testrayBuildId) {
		_testrayRun.setTestrayBuildId(testrayBuildId);
	}

	/**
	 * Sets the testray run ID of this testray run.
	 *
	 * @param testrayRunId the testray run ID of this testray run
	 */
	@Override
	public void setTestrayRunId(long testrayRunId) {
		_testrayRun.setTestrayRunId(testrayRunId);
	}

	/**
	 * Sets the user ID of this testray run.
	 *
	 * @param userId the user ID of this testray run
	 */
	@Override
	public void setUserId(long userId) {
		_testrayRun.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray run.
	 *
	 * @param userName the user name of this testray run
	 */
	@Override
	public void setUserName(String userName) {
		_testrayRun.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray run.
	 *
	 * @param userUuid the user uuid of this testray run
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayRun.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayRun>
		toCacheModel() {

		return _testrayRun.toCacheModel();
	}

	@Override
	public TestrayRun toEscapedModel() {
		return new TestrayRunWrapper(_testrayRun.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayRun.toString();
	}

	@Override
	public TestrayRun toUnescapedModel() {
		return new TestrayRunWrapper(_testrayRun.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayRun.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRunWrapper)) {
			return false;
		}

		TestrayRunWrapper testrayRunWrapper = (TestrayRunWrapper)obj;

		if (Objects.equals(_testrayRun, testrayRunWrapper._testrayRun)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayRun getWrappedModel() {
		return _testrayRun;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayRun.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayRun.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayRun.resetOriginalValues();
	}

	private final TestrayRun _testrayRun;

}