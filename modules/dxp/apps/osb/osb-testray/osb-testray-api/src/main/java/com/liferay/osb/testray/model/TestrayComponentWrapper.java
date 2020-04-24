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
 * This class is a wrapper for {@link TestrayComponent}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayComponent
 * @generated
 */
@ProviderType
public class TestrayComponentWrapper
	implements TestrayComponent, ModelWrapper<TestrayComponent> {

	public TestrayComponentWrapper(TestrayComponent testrayComponent) {
		_testrayComponent = testrayComponent;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayComponent.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayComponent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayComponentId", getTestrayComponentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("testrayProjectId", getTestrayProjectId());
		attributes.put("testrayTeamId", getTestrayTeamId());
		attributes.put("name", getName());
		attributes.put("originationKey", getOriginationKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayComponentId = (Long)attributes.get("testrayComponentId");

		if (testrayComponentId != null) {
			setTestrayComponentId(testrayComponentId);
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

		Long testrayTeamId = (Long)attributes.get("testrayTeamId");

		if (testrayTeamId != null) {
			setTestrayTeamId(testrayTeamId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long originationKey = (Long)attributes.get("originationKey");

		if (originationKey != null) {
			setOriginationKey(originationKey);
		}
	}

	@Override
	public Object clone() {
		return new TestrayComponentWrapper(
			(TestrayComponent)_testrayComponent.clone());
	}

	@Override
	public int compareTo(TestrayComponent testrayComponent) {
		return _testrayComponent.compareTo(testrayComponent);
	}

	/**
	 * Returns the company ID of this testray component.
	 *
	 * @return the company ID of this testray component
	 */
	@Override
	public long getCompanyId() {
		return _testrayComponent.getCompanyId();
	}

	/**
	 * Returns the create date of this testray component.
	 *
	 * @return the create date of this testray component
	 */
	@Override
	public Date getCreateDate() {
		return _testrayComponent.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayComponent.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray component.
	 *
	 * @return the group ID of this testray component
	 */
	@Override
	public long getGroupId() {
		return _testrayComponent.getGroupId();
	}

	/**
	 * Returns the modified date of this testray component.
	 *
	 * @return the modified date of this testray component
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayComponent.getModifiedDate();
	}

	/**
	 * Returns the name of this testray component.
	 *
	 * @return the name of this testray component
	 */
	@Override
	public String getName() {
		return _testrayComponent.getName();
	}

	/**
	 * Returns the origination key of this testray component.
	 *
	 * @return the origination key of this testray component
	 */
	@Override
	public long getOriginationKey() {
		return _testrayComponent.getOriginationKey();
	}

	/**
	 * Returns the primary key of this testray component.
	 *
	 * @return the primary key of this testray component
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayComponent.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayComponent.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray component ID of this testray component.
	 *
	 * @return the testray component ID of this testray component
	 */
	@Override
	public long getTestrayComponentId() {
		return _testrayComponent.getTestrayComponentId();
	}

	/**
	 * Returns the testray project ID of this testray component.
	 *
	 * @return the testray project ID of this testray component
	 */
	@Override
	public long getTestrayProjectId() {
		return _testrayComponent.getTestrayProjectId();
	}

	/**
	 * Returns the testray team ID of this testray component.
	 *
	 * @return the testray team ID of this testray component
	 */
	@Override
	public long getTestrayTeamId() {
		return _testrayComponent.getTestrayTeamId();
	}

	/**
	 * Returns the user ID of this testray component.
	 *
	 * @return the user ID of this testray component
	 */
	@Override
	public long getUserId() {
		return _testrayComponent.getUserId();
	}

	/**
	 * Returns the user name of this testray component.
	 *
	 * @return the user name of this testray component
	 */
	@Override
	public String getUserName() {
		return _testrayComponent.getUserName();
	}

	/**
	 * Returns the user uuid of this testray component.
	 *
	 * @return the user uuid of this testray component
	 */
	@Override
	public String getUserUuid() {
		return _testrayComponent.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayComponent.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayComponent.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayComponent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayComponent.isNew();
	}

	@Override
	public void persist() {
		_testrayComponent.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayComponent.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray component.
	 *
	 * @param companyId the company ID of this testray component
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayComponent.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray component.
	 *
	 * @param createDate the create date of this testray component
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayComponent.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayComponent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayComponent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayComponent.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray component.
	 *
	 * @param groupId the group ID of this testray component
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayComponent.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray component.
	 *
	 * @param modifiedDate the modified date of this testray component
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayComponent.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray component.
	 *
	 * @param name the name of this testray component
	 */
	@Override
	public void setName(String name) {
		_testrayComponent.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayComponent.setNew(n);
	}

	/**
	 * Sets the origination key of this testray component.
	 *
	 * @param originationKey the origination key of this testray component
	 */
	@Override
	public void setOriginationKey(long originationKey) {
		_testrayComponent.setOriginationKey(originationKey);
	}

	/**
	 * Sets the primary key of this testray component.
	 *
	 * @param primaryKey the primary key of this testray component
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayComponent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayComponent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray component ID of this testray component.
	 *
	 * @param testrayComponentId the testray component ID of this testray component
	 */
	@Override
	public void setTestrayComponentId(long testrayComponentId) {
		_testrayComponent.setTestrayComponentId(testrayComponentId);
	}

	/**
	 * Sets the testray project ID of this testray component.
	 *
	 * @param testrayProjectId the testray project ID of this testray component
	 */
	@Override
	public void setTestrayProjectId(long testrayProjectId) {
		_testrayComponent.setTestrayProjectId(testrayProjectId);
	}

	/**
	 * Sets the testray team ID of this testray component.
	 *
	 * @param testrayTeamId the testray team ID of this testray component
	 */
	@Override
	public void setTestrayTeamId(long testrayTeamId) {
		_testrayComponent.setTestrayTeamId(testrayTeamId);
	}

	/**
	 * Sets the user ID of this testray component.
	 *
	 * @param userId the user ID of this testray component
	 */
	@Override
	public void setUserId(long userId) {
		_testrayComponent.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray component.
	 *
	 * @param userName the user name of this testray component
	 */
	@Override
	public void setUserName(String userName) {
		_testrayComponent.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray component.
	 *
	 * @param userUuid the user uuid of this testray component
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayComponent.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayComponent>
		toCacheModel() {

		return _testrayComponent.toCacheModel();
	}

	@Override
	public TestrayComponent toEscapedModel() {
		return new TestrayComponentWrapper(_testrayComponent.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayComponent.toString();
	}

	@Override
	public TestrayComponent toUnescapedModel() {
		return new TestrayComponentWrapper(
			_testrayComponent.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayComponent.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayComponentWrapper)) {
			return false;
		}

		TestrayComponentWrapper testrayComponentWrapper =
			(TestrayComponentWrapper)obj;

		if (Objects.equals(
				_testrayComponent, testrayComponentWrapper._testrayComponent)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayComponent getWrappedModel() {
		return _testrayComponent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayComponent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayComponent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayComponent.resetOriginalValues();
	}

	private final TestrayComponent _testrayComponent;

}