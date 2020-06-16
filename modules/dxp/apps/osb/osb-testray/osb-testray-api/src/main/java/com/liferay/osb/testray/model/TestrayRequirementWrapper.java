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
 * This class is a wrapper for {@link TestrayRequirement}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRequirement
 * @generated
 */
@ProviderType
public class TestrayRequirementWrapper implements TestrayRequirement,
	ModelWrapper<TestrayRequirement> {
	public TestrayRequirementWrapper(TestrayRequirement testrayRequirement) {
		_testrayRequirement = testrayRequirement;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayRequirement.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayRequirement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayRequirementId", getTestrayRequirementId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayComponentId", getTestrayComponentId());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("key", getKey());
		attributes.put("summary", getSummary());
		attributes.put("components", getComponents());
		attributes.put("linkTitle", getLinkTitle());
		attributes.put("linkURL", getLinkURL());
		attributes.put("description", getDescription());
		attributes.put("descriptionType", getDescriptionType());
		attributes.put("goals", getGoals());
		attributes.put("goalsType", getGoalsType());
		attributes.put("variations", getVariations());
		attributes.put("variationsType", getVariationsType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayRequirementId = (Long)attributes.get("testrayRequirementId");

		if (testrayRequirementId != null) {
			setTestrayRequirementId(testrayRequirementId);
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

		Long testrayComponentId = (Long)attributes.get("testrayComponentId");

		if (testrayComponentId != null) {
			setTestrayComponentId(testrayComponentId);
		}

		Long testrayProjectId = (Long)attributes.get("testrayProjectId");

		if (testrayProjectId != null) {
			setTestrayProjectId(testrayProjectId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String components = (String)attributes.get("components");

		if (components != null) {
			setComponents(components);
		}

		String linkTitle = (String)attributes.get("linkTitle");

		if (linkTitle != null) {
			setLinkTitle(linkTitle);
		}

		String linkURL = (String)attributes.get("linkURL");

		if (linkURL != null) {
			setLinkURL(linkURL);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String descriptionType = (String)attributes.get("descriptionType");

		if (descriptionType != null) {
			setDescriptionType(descriptionType);
		}

		String goals = (String)attributes.get("goals");

		if (goals != null) {
			setGoals(goals);
		}

		String goalsType = (String)attributes.get("goalsType");

		if (goalsType != null) {
			setGoalsType(goalsType);
		}

		String variations = (String)attributes.get("variations");

		if (variations != null) {
			setVariations(variations);
		}

		String variationsType = (String)attributes.get("variationsType");

		if (variationsType != null) {
			setVariationsType(variationsType);
		}
	}

	@Override
	public TestrayRequirement toEscapedModel() {
		return new TestrayRequirementWrapper(_testrayRequirement.toEscapedModel());
	}

	@Override
	public TestrayRequirement toUnescapedModel() {
		return new TestrayRequirementWrapper(_testrayRequirement.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _testrayRequirement.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayRequirement.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayRequirement.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayRequirement.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayRequirement> toCacheModel() {
		return _testrayRequirement.toCacheModel();
	}

	@Override
	public int compareTo(TestrayRequirement testrayRequirement) {
		return _testrayRequirement.compareTo(testrayRequirement);
	}

	@Override
	public int hashCode() {
		return _testrayRequirement.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayRequirement.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TestrayRequirementWrapper((TestrayRequirement)_testrayRequirement.clone());
	}

	/**
	* Returns the components of this testray requirement.
	*
	* @return the components of this testray requirement
	*/
	@Override
	public java.lang.String getComponents() {
		return _testrayRequirement.getComponents();
	}

	/**
	* Returns the description of this testray requirement.
	*
	* @return the description of this testray requirement
	*/
	@Override
	public java.lang.String getDescription() {
		return _testrayRequirement.getDescription();
	}

	/**
	* Returns the description type of this testray requirement.
	*
	* @return the description type of this testray requirement
	*/
	@Override
	public java.lang.String getDescriptionType() {
		return _testrayRequirement.getDescriptionType();
	}

	/**
	* Returns the goals of this testray requirement.
	*
	* @return the goals of this testray requirement
	*/
	@Override
	public java.lang.String getGoals() {
		return _testrayRequirement.getGoals();
	}

	/**
	* Returns the goals type of this testray requirement.
	*
	* @return the goals type of this testray requirement
	*/
	@Override
	public java.lang.String getGoalsType() {
		return _testrayRequirement.getGoalsType();
	}

	/**
	* Returns the key of this testray requirement.
	*
	* @return the key of this testray requirement
	*/
	@Override
	public java.lang.String getKey() {
		return _testrayRequirement.getKey();
	}

	/**
	* Returns the link title of this testray requirement.
	*
	* @return the link title of this testray requirement
	*/
	@Override
	public java.lang.String getLinkTitle() {
		return _testrayRequirement.getLinkTitle();
	}

	/**
	* Returns the link url of this testray requirement.
	*
	* @return the link url of this testray requirement
	*/
	@Override
	public java.lang.String getLinkURL() {
		return _testrayRequirement.getLinkURL();
	}

	/**
	* Returns the summary of this testray requirement.
	*
	* @return the summary of this testray requirement
	*/
	@Override
	public java.lang.String getSummary() {
		return _testrayRequirement.getSummary();
	}

	/**
	* Returns the user name of this testray requirement.
	*
	* @return the user name of this testray requirement
	*/
	@Override
	public java.lang.String getUserName() {
		return _testrayRequirement.getUserName();
	}

	/**
	* Returns the user uuid of this testray requirement.
	*
	* @return the user uuid of this testray requirement
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _testrayRequirement.getUserUuid();
	}

	/**
	* Returns the variations of this testray requirement.
	*
	* @return the variations of this testray requirement
	*/
	@Override
	public java.lang.String getVariations() {
		return _testrayRequirement.getVariations();
	}

	/**
	* Returns the variations type of this testray requirement.
	*
	* @return the variations type of this testray requirement
	*/
	@Override
	public java.lang.String getVariationsType() {
		return _testrayRequirement.getVariationsType();
	}

	@Override
	public java.lang.String toString() {
		return _testrayRequirement.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _testrayRequirement.toXmlString();
	}

	/**
	* Returns the create date of this testray requirement.
	*
	* @return the create date of this testray requirement
	*/
	@Override
	public Date getCreateDate() {
		return _testrayRequirement.getCreateDate();
	}

	/**
	* Returns the modified date of this testray requirement.
	*
	* @return the modified date of this testray requirement
	*/
	@Override
	public Date getModifiedDate() {
		return _testrayRequirement.getModifiedDate();
	}

	/**
	* Returns the company ID of this testray requirement.
	*
	* @return the company ID of this testray requirement
	*/
	@Override
	public long getCompanyId() {
		return _testrayRequirement.getCompanyId();
	}

	/**
	* Returns the group ID of this testray requirement.
	*
	* @return the group ID of this testray requirement
	*/
	@Override
	public long getGroupId() {
		return _testrayRequirement.getGroupId();
	}

	/**
	* Returns the primary key of this testray requirement.
	*
	* @return the primary key of this testray requirement
	*/
	@Override
	public long getPrimaryKey() {
		return _testrayRequirement.getPrimaryKey();
	}

	/**
	* Returns the testray component ID of this testray requirement.
	*
	* @return the testray component ID of this testray requirement
	*/
	@Override
	public long getTestrayComponentId() {
		return _testrayRequirement.getTestrayComponentId();
	}

	/**
	* Returns the testray project ID of this testray requirement.
	*
	* @return the testray project ID of this testray requirement
	*/
	@Override
	public long getTestrayProjectId() {
		return _testrayRequirement.getTestrayProjectId();
	}

	/**
	* Returns the testray requirement ID of this testray requirement.
	*
	* @return the testray requirement ID of this testray requirement
	*/
	@Override
	public long getTestrayRequirementId() {
		return _testrayRequirement.getTestrayRequirementId();
	}

	/**
	* Returns the user ID of this testray requirement.
	*
	* @return the user ID of this testray requirement
	*/
	@Override
	public long getUserId() {
		return _testrayRequirement.getUserId();
	}

	@Override
	public void persist() {
		_testrayRequirement.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayRequirement.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this testray requirement.
	*
	* @param companyId the company ID of this testray requirement
	*/
	@Override
	public void setCompanyId(long companyId) {
		_testrayRequirement.setCompanyId(companyId);
	}

	/**
	* Sets the components of this testray requirement.
	*
	* @param components the components of this testray requirement
	*/
	@Override
	public void setComponents(java.lang.String components) {
		_testrayRequirement.setComponents(components);
	}

	/**
	* Sets the create date of this testray requirement.
	*
	* @param createDate the create date of this testray requirement
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_testrayRequirement.setCreateDate(createDate);
	}

	/**
	* Sets the description of this testray requirement.
	*
	* @param description the description of this testray requirement
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_testrayRequirement.setDescription(description);
	}

	/**
	* Sets the description type of this testray requirement.
	*
	* @param descriptionType the description type of this testray requirement
	*/
	@Override
	public void setDescriptionType(java.lang.String descriptionType) {
		_testrayRequirement.setDescriptionType(descriptionType);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayRequirement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_testrayRequirement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayRequirement.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the goals of this testray requirement.
	*
	* @param goals the goals of this testray requirement
	*/
	@Override
	public void setGoals(java.lang.String goals) {
		_testrayRequirement.setGoals(goals);
	}

	/**
	* Sets the goals type of this testray requirement.
	*
	* @param goalsType the goals type of this testray requirement
	*/
	@Override
	public void setGoalsType(java.lang.String goalsType) {
		_testrayRequirement.setGoalsType(goalsType);
	}

	/**
	* Sets the group ID of this testray requirement.
	*
	* @param groupId the group ID of this testray requirement
	*/
	@Override
	public void setGroupId(long groupId) {
		_testrayRequirement.setGroupId(groupId);
	}

	/**
	* Sets the key of this testray requirement.
	*
	* @param key the key of this testray requirement
	*/
	@Override
	public void setKey(java.lang.String key) {
		_testrayRequirement.setKey(key);
	}

	/**
	* Sets the link title of this testray requirement.
	*
	* @param linkTitle the link title of this testray requirement
	*/
	@Override
	public void setLinkTitle(java.lang.String linkTitle) {
		_testrayRequirement.setLinkTitle(linkTitle);
	}

	/**
	* Sets the link url of this testray requirement.
	*
	* @param linkURL the link url of this testray requirement
	*/
	@Override
	public void setLinkURL(java.lang.String linkURL) {
		_testrayRequirement.setLinkURL(linkURL);
	}

	/**
	* Sets the modified date of this testray requirement.
	*
	* @param modifiedDate the modified date of this testray requirement
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayRequirement.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayRequirement.setNew(n);
	}

	/**
	* Sets the primary key of this testray requirement.
	*
	* @param primaryKey the primary key of this testray requirement
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayRequirement.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayRequirement.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the summary of this testray requirement.
	*
	* @param summary the summary of this testray requirement
	*/
	@Override
	public void setSummary(java.lang.String summary) {
		_testrayRequirement.setSummary(summary);
	}

	/**
	* Sets the testray component ID of this testray requirement.
	*
	* @param testrayComponentId the testray component ID of this testray requirement
	*/
	@Override
	public void setTestrayComponentId(long testrayComponentId) {
		_testrayRequirement.setTestrayComponentId(testrayComponentId);
	}

	/**
	* Sets the testray project ID of this testray requirement.
	*
	* @param testrayProjectId the testray project ID of this testray requirement
	*/
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayRequirement.setTestrayProjectId(testrayProjectId);
	}

	/**
	* Sets the testray requirement ID of this testray requirement.
	*
	* @param testrayRequirementId the testray requirement ID of this testray requirement
	*/
	@Override
	public void setTestrayRequirementId(long testrayRequirementId) {
		_testrayRequirement.setTestrayRequirementId(testrayRequirementId);
	}

	/**
	* Sets the user ID of this testray requirement.
	*
	* @param userId the user ID of this testray requirement
	*/
	@Override
	public void setUserId(long userId) {
		_testrayRequirement.setUserId(userId);
	}

	/**
	* Sets the user name of this testray requirement.
	*
	* @param userName the user name of this testray requirement
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_testrayRequirement.setUserName(userName);
	}

	/**
	* Sets the user uuid of this testray requirement.
	*
	* @param userUuid the user uuid of this testray requirement
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_testrayRequirement.setUserUuid(userUuid);
	}

	/**
	* Sets the variations of this testray requirement.
	*
	* @param variations the variations of this testray requirement
	*/
	@Override
	public void setVariations(java.lang.String variations) {
		_testrayRequirement.setVariations(variations);
	}

	/**
	* Sets the variations type of this testray requirement.
	*
	* @param variationsType the variations type of this testray requirement
	*/
	@Override
	public void setVariationsType(java.lang.String variationsType) {
		_testrayRequirement.setVariationsType(variationsType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRequirementWrapper)) {
			return false;
		}

		TestrayRequirementWrapper testrayRequirementWrapper = (TestrayRequirementWrapper)obj;

		if (Objects.equals(_testrayRequirement,
					testrayRequirementWrapper._testrayRequirement)) {
			return true;
		}

		return false;
	}

	@Override
	public TestrayRequirement getWrappedModel() {
		return _testrayRequirement;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayRequirement.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayRequirement.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayRequirement.resetOriginalValues();
	}

	private final TestrayRequirement _testrayRequirement;
}