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
 * This class is a wrapper for {@link TestrayTeam}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayTeam
 * @generated
 */
public class TestrayTeamWrapper
	implements ModelWrapper<TestrayTeam>, TestrayTeam {

	public TestrayTeamWrapper(TestrayTeam testrayTeam) {
		_testrayTeam = testrayTeam;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayTeam.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayTeam.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayTeamId", getTestrayTeamId());
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
		Long testrayTeamId = (Long)attributes.get("testrayTeamId");

		if (testrayTeamId != null) {
			setTestrayTeamId(testrayTeamId);
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
		return new TestrayTeamWrapper((TestrayTeam)_testrayTeam.clone());
	}

	@Override
	public int compareTo(TestrayTeam testrayTeam) {
		return _testrayTeam.compareTo(testrayTeam);
	}

	/**
	 * Returns the company ID of this testray team.
	 *
	 * @return the company ID of this testray team
	 */
	@Override
	public long getCompanyId() {
		return _testrayTeam.getCompanyId();
	}

	/**
	 * Returns the create date of this testray team.
	 *
	 * @return the create date of this testray team
	 */
	@Override
	public Date getCreateDate() {
		return _testrayTeam.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayTeam.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray team.
	 *
	 * @return the group ID of this testray team
	 */
	@Override
	public long getGroupId() {
		return _testrayTeam.getGroupId();
	}

	/**
	 * Returns the modified date of this testray team.
	 *
	 * @return the modified date of this testray team
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayTeam.getModifiedDate();
	}

	/**
	 * Returns the name of this testray team.
	 *
	 * @return the name of this testray team
	 */
	@Override
	public String getName() {
		return _testrayTeam.getName();
	}

	/**
	 * Returns the primary key of this testray team.
	 *
	 * @return the primary key of this testray team
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayTeam.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayTeam.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray project ID of this testray team.
	 *
	 * @return the testray project ID of this testray team
	 */
	@Override
	public long getTestrayProjectId() {
		return _testrayTeam.getTestrayProjectId();
	}

	/**
	 * Returns the testray team ID of this testray team.
	 *
	 * @return the testray team ID of this testray team
	 */
	@Override
	public long getTestrayTeamId() {
		return _testrayTeam.getTestrayTeamId();
	}

	/**
	 * Returns the user ID of this testray team.
	 *
	 * @return the user ID of this testray team
	 */
	@Override
	public long getUserId() {
		return _testrayTeam.getUserId();
	}

	/**
	 * Returns the user name of this testray team.
	 *
	 * @return the user name of this testray team
	 */
	@Override
	public String getUserName() {
		return _testrayTeam.getUserName();
	}

	/**
	 * Returns the user uuid of this testray team.
	 *
	 * @return the user uuid of this testray team
	 */
	@Override
	public String getUserUuid() {
		return _testrayTeam.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayTeam.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayTeam.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayTeam.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayTeam.isNew();
	}

	@Override
	public void persist() {
		_testrayTeam.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayTeam.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray team.
	 *
	 * @param companyId the company ID of this testray team
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayTeam.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray team.
	 *
	 * @param createDate the create date of this testray team
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayTeam.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayTeam.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayTeam.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayTeam.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray team.
	 *
	 * @param groupId the group ID of this testray team
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayTeam.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray team.
	 *
	 * @param modifiedDate the modified date of this testray team
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayTeam.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray team.
	 *
	 * @param name the name of this testray team
	 */
	@Override
	public void setName(String name) {
		_testrayTeam.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayTeam.setNew(n);
	}

	/**
	 * Sets the primary key of this testray team.
	 *
	 * @param primaryKey the primary key of this testray team
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayTeam.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayTeam.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray project ID of this testray team.
	 *
	 * @param testrayProjectId the testray project ID of this testray team
	 */
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayTeam.setTestrayProjectId(testrayProjectId);
	}

	/**
	 * Sets the testray team ID of this testray team.
	 *
	 * @param testrayTeamId the testray team ID of this testray team
	 */
	@Override
	public void setTestrayTeamId(long testrayTeamId) {
		_testrayTeam.setTestrayTeamId(testrayTeamId);
	}

	/**
	 * Sets the user ID of this testray team.
	 *
	 * @param userId the user ID of this testray team
	 */
	@Override
	public void setUserId(long userId) {
		_testrayTeam.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray team.
	 *
	 * @param userName the user name of this testray team
	 */
	@Override
	public void setUserName(String userName) {
		_testrayTeam.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray team.
	 *
	 * @param userUuid the user uuid of this testray team
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayTeam.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayTeam>
		toCacheModel() {

		return _testrayTeam.toCacheModel();
	}

	@Override
	public TestrayTeam toEscapedModel() {
		return new TestrayTeamWrapper(_testrayTeam.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayTeam.toString();
	}

	@Override
	public TestrayTeam toUnescapedModel() {
		return new TestrayTeamWrapper(_testrayTeam.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayTeam.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayTeamWrapper)) {
			return false;
		}

		TestrayTeamWrapper testrayTeamWrapper = (TestrayTeamWrapper)obj;

		if (Objects.equals(_testrayTeam, testrayTeamWrapper._testrayTeam)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayTeam getWrappedModel() {
		return _testrayTeam;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayTeam.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayTeam.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayTeam.resetOriginalValues();
	}

	private final TestrayTeam _testrayTeam;

}