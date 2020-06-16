/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
 * This class is a wrapper for {@link TestrayBuild}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayBuild
 * @generated
 */
@ProviderType
public class TestrayBuildWrapper implements TestrayBuild,
	ModelWrapper<TestrayBuild> {
	public TestrayBuildWrapper(TestrayBuild testrayBuild) {
		_testrayBuild = testrayBuild;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayBuild.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayBuild.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayBuildId", getTestrayBuildId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("templateTestrayBuildId", getTemplateTestrayBuildId());
		attributes.put("testrayRoutineId", getTestrayRoutineId());
		attributes.put("testrayProductVersionId", getTestrayProductVersionId());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("descriptionType", getDescriptionType());
		attributes.put("template", getTemplate());
		attributes.put("dueDate", getDueDate());
		attributes.put("gitHash", getGitHash());
		attributes.put("githubCompareURLs", getGithubCompareURLs());
		attributes.put("promoted", getPromoted());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayBuildId = (Long)attributes.get("testrayBuildId");

		if (testrayBuildId != null) {
			setTestrayBuildId(testrayBuildId);
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

		Long templateTestrayBuildId = (Long)attributes.get(
				"templateTestrayBuildId");

		if (templateTestrayBuildId != null) {
			setTemplateTestrayBuildId(templateTestrayBuildId);
		}

		Long testrayRoutineId = (Long)attributes.get("testrayRoutineId");

		if (testrayRoutineId != null) {
			setTestrayRoutineId(testrayRoutineId);
		}

		Long testrayProductVersionId = (Long)attributes.get(
				"testrayProductVersionId");

		if (testrayProductVersionId != null) {
			setTestrayProductVersionId(testrayProductVersionId);
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

		Boolean template = (Boolean)attributes.get("template");

		if (template != null) {
			setTemplate(template);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		String gitHash = (String)attributes.get("gitHash");

		if (gitHash != null) {
			setGitHash(gitHash);
		}

		String githubCompareURLs = (String)attributes.get("githubCompareURLs");

		if (githubCompareURLs != null) {
			setGithubCompareURLs(githubCompareURLs);
		}

		Boolean promoted = (Boolean)attributes.get("promoted");

		if (promoted != null) {
			setPromoted(promoted);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public TestrayBuild toEscapedModel() {
		return new TestrayBuildWrapper(_testrayBuild.toEscapedModel());
	}

	@Override
	public TestrayBuild toUnescapedModel() {
		return new TestrayBuildWrapper(_testrayBuild.toUnescapedModel());
	}

	/**
	* Returns the promoted of this testray build.
	*
	* @return the promoted of this testray build
	*/
	@Override
	public boolean getPromoted() {
		return _testrayBuild.getPromoted();
	}

	/**
	* Returns the template of this testray build.
	*
	* @return the template of this testray build
	*/
	@Override
	public boolean getTemplate() {
		return _testrayBuild.getTemplate();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayBuild.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayBuild.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayBuild.isNew();
	}

	/**
	* Returns <code>true</code> if this testray build is promoted.
	*
	* @return <code>true</code> if this testray build is promoted; <code>false</code> otherwise
	*/
	@Override
	public boolean isPromoted() {
		return _testrayBuild.isPromoted();
	}

	/**
	* Returns <code>true</code> if this testray build is template.
	*
	* @return <code>true</code> if this testray build is template; <code>false</code> otherwise
	*/
	@Override
	public boolean isTemplate() {
		return _testrayBuild.isTemplate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayBuild.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayBuild> toCacheModel() {
		return _testrayBuild.toCacheModel();
	}

	@Override
	public int compareTo(TestrayBuild testrayBuild) {
		return _testrayBuild.compareTo(testrayBuild);
	}

	/**
	* Returns the status of this testray build.
	*
	* @return the status of this testray build
	*/
	@Override
	public int getStatus() {
		return _testrayBuild.getStatus();
	}

	@Override
	public int hashCode() {
		return _testrayBuild.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayBuild.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayBuildWrapper((TestrayBuild)_testrayBuild.clone());
	}

	/**
	* Returns the description of this testray build.
	*
	* @return the description of this testray build
	*/
	@Override
	public java.lang.String getDescription() {
		return _testrayBuild.getDescription();
	}

	/**
	* Returns the description type of this testray build.
	*
	* @return the description type of this testray build
	*/
	@Override
	public java.lang.String getDescriptionType() {
		return _testrayBuild.getDescriptionType();
	}

	/**
	* Returns the git hash of this testray build.
	*
	* @return the git hash of this testray build
	*/
	@Override
	public java.lang.String getGitHash() {
		return _testrayBuild.getGitHash();
	}

	/**
	* Returns the github compare ur ls of this testray build.
	*
	* @return the github compare ur ls of this testray build
	*/
	@Override
	public java.lang.String getGithubCompareURLs() {
		return _testrayBuild.getGithubCompareURLs();
	}

	/**
	* Returns the name of this testray build.
	*
	* @return the name of this testray build
	*/
	@Override
	public java.lang.String getName() {
		return _testrayBuild.getName();
	}

	/**
	* Returns the user name of this testray build.
	*
	* @return the user name of this testray build
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayBuild.getUserName();
	}

	/**
	* Returns the user uuid of this testray build.
	*
	* @return the user uuid of this testray build
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayBuild.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _testrayBuild.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayBuild.toXmlString();
	}

	/**
	* Returns the create date of this testray build.
	*
	* @return the create date of this testray build
	*/
	@Override
	public Date getCreateDate() {
		return _testrayBuild.getCreateDate();
	}

	/**
	* Returns the due date of this testray build.
	*
	* @return the due date of this testray build
	*/
	@Override
	public Date getDueDate() {
		return _testrayBuild.getDueDate();
	}

	/**
	* Returns the modified date of this testray build.
	*
	* @return the modified date of this testray build
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayBuild.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray build.
	*
	* @return the company ID of this testray build
	*/
	@Override
	public long getCompanyId() {
		return _testrayBuild.getCompanyId();
	}

	/**
	* Returns the group ID of this testray build.
	*
	* @return the group ID of this testray build
	*/
	@Override
	public long getGroupId() {
		return _testrayBuild.getGroupId();
	}

	/**
	* Returns the primary key of this testray build.
	*
	* @return the primary key of this testray build
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayBuild.getPrimaryKey();
	}

	/**
	* Returns the template testray build ID of this testray build.
	*
	* @return the template testray build ID of this testray build
	*/
	@Override
	public long getTemplateTestrayBuildId() {
		return _testrayBuild.getTemplateTestrayBuildId();
	}

	/**
	* Returns the testray build ID of this testray build.
	*
	* @return the testray build ID of this testray build
	*/
	@Override
	public long getTestrayBuildId() {
		return _testrayBuild.getTestrayBuildId();
	}

	/**
	* Returns the testray product version ID of this testray build.
	*
	* @return the testray product version ID of this testray build
	*/
	@Override
	public long getTestrayProductVersionId() {
		return _testrayBuild.getTestrayProductVersionId();
	}

	/**
	* Returns the testray project ID of this testray build.
	*
	* @return the testray project ID of this testray build
	*/
	@Override
	public long getTestrayProjectId() {
		return _testrayBuild.getTestrayProjectId();
	}

	/**
	* Returns the testray routine ID of this testray build.
	*
	* @return the testray routine ID of this testray build
	*/
	@Override
	public long getTestrayRoutineId() {
		return _testrayBuild.getTestrayRoutineId();
	}

	/**
	* Returns the user ID of this testray build.
	*
	* @return the user ID of this testray build
	*/
	@Override
	public long getUserId() {
		return _testrayBuild.getUserId();
	}

	@Override
	public void persist() {
		_testrayBuild.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayBuild.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray build.
	*
	* @param companyId the company ID of this testray build
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayBuild.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this testray build.
	*
	* @param createDate the create date of this testray build
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayBuild.setCreateDate(createDate);
	}

	/**
	* Sets the description of this testray build.
	*
	* @param description the description of this testray build
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_testrayBuild.setDescription(description);
	}

	/**
	* Sets the description type of this testray build.
	*
	* @param descriptionType the description type of this testray build
	*/
	@Override
	public void setDescriptionType(java.lang.String descriptionType) {
		_testrayBuild.setDescriptionType(descriptionType);
	}

	/**
	* Sets the due date of this testray build.
	*
	* @param dueDate the due date of this testray build
	*/
	@Override
	public void setDueDate(Date dueDate) {
		_testrayBuild.setDueDate(dueDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayBuild.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayBuild.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayBuild.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the git hash of this testray build.
	*
	* @param gitHash the git hash of this testray build
	*/
	@Override
	public void setGitHash(java.lang.String gitHash) {
		_testrayBuild.setGitHash(gitHash);
	}

	/**
	* Sets the github compare ur ls of this testray build.
	*
	* @param githubCompareURLs the github compare ur ls of this testray build
	*/
	@Override
	public void setGithubCompareURLs(java.lang.String githubCompareURLs) {
		_testrayBuild.setGithubCompareURLs(githubCompareURLs);
	}

	/**
	* Sets the group ID of this testray build.
	*
	* @param groupId the group ID of this testray build
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayBuild.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this testray build.
	*
	* @param modifiedDate the modified date of this testray build
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayBuild.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this testray build.
	*
	* @param name the name of this testray build
	*/
	@Override
	public void setName(java.lang.String name) {
		_testrayBuild.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayBuild.setNew(n);
	}

	/**
	* Sets the primary key of this testray build.
	*
	* @param primaryKey the primary key of this testray build
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayBuild.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayBuild.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this testray build is promoted.
	*
	* @param promoted the promoted of this testray build
	*/
	@Override
	public void setPromoted(boolean promoted) {
		_testrayBuild.setPromoted(promoted);
	}

	/**
	* Sets the status of this testray build.
	*
	* @param status the status of this testray build
	*/
	@Override
	public void setStatus(int status) {
		_testrayBuild.setStatus(status);
	}

	/**
	* Sets whether this testray build is template.
	*
	* @param template the template of this testray build
	*/
	@Override
	public void setTemplate(boolean template) {
		_testrayBuild.setTemplate(template);
	}

	/**
	* Sets the template testray build ID of this testray build.
	*
	* @param templateTestrayBuildId the template testray build ID of this testray build
	*/
	@Override
	public void setTemplateTestrayBuildId(long templateTestrayBuildId) {
		_testrayBuild.setTemplateTestrayBuildId(templateTestrayBuildId);
	}

	/**
	* Sets the testray build ID of this testray build.
	*
	* @param testrayBuildId the testray build ID of this testray build
	*/
	@Override
	public void setTestrayBuildId(long testrayBuildId) {
		_testrayBuild.setTestrayBuildId(testrayBuildId);
	}

	/**
	* Sets the testray product version ID of this testray build.
	*
	* @param testrayProductVersionId the testray product version ID of this testray build
	*/
	@Override
	public void setTestrayProductVersionId(long testrayProductVersionId) {
		_testrayBuild.setTestrayProductVersionId(testrayProductVersionId);
	}

	/**
	* Sets the testray project ID of this testray build.
	*
	* @param testrayProjectId the testray project ID of this testray build
	*/
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayBuild.setTestrayProjectId(testrayProjectId);
	}

	/**
	* Sets the testray routine ID of this testray build.
	*
	* @param testrayRoutineId the testray routine ID of this testray build
	*/
	@Override
	public void setTestrayRoutineId(long testrayRoutineId) {
		_testrayBuild.setTestrayRoutineId(testrayRoutineId);
	}

	/**
	* Sets the user ID of this testray build.
	*
	* @param userId the user ID of this testray build
	*/
	@Override
	public void setUserId(long userId) {
		_testrayBuild.setUserId(userId);
	}

	/**
	* Sets the user name of this testray build.
	*
	* @param userName the user name of this testray build
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayBuild.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray build.
	*
	* @param userUuid the user uuid of this testray build
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayBuild.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayBuildWrapper)) {
			return false;
		}

		TestrayBuildWrapper testrayBuildWrapper = (TestrayBuildWrapper)obj;

		if (Objects.equals(_testrayBuild, testrayBuildWrapper._testrayBuild)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayBuild getWrappedModel() {
		return _testrayBuild;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayBuild.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayBuild.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayBuild.resetOriginalValues();
	}

	private final TestrayBuild _testrayBuild;
}