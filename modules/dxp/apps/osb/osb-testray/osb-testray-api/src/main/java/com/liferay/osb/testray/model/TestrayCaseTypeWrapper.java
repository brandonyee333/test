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
 * This class is a wrapper for {@link TestrayCaseType}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseType
 * @generated
 */
@ProviderType
public class TestrayCaseTypeWrapper
	implements TestrayCaseType, ModelWrapper<TestrayCaseType> {

	public TestrayCaseTypeWrapper(TestrayCaseType testrayCaseType) {
		_testrayCaseType = testrayCaseType;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayCaseType.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayCaseType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayCaseTypeId", getTestrayCaseTypeId());
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
		Long testrayCaseTypeId = (Long)attributes.get("testrayCaseTypeId");

		if (testrayCaseTypeId != null) {
			setTestrayCaseTypeId(testrayCaseTypeId);
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
		return new TestrayCaseTypeWrapper(
			(TestrayCaseType)_testrayCaseType.clone());
	}

	@Override
	public int compareTo(TestrayCaseType testrayCaseType) {
		return _testrayCaseType.compareTo(testrayCaseType);
	}

	/**
	 * Returns the company ID of this testray case type.
	 *
	 * @return the company ID of this testray case type
	 */
	@Override
	public long getCompanyId() {
		return _testrayCaseType.getCompanyId();
	}

	/**
	 * Returns the create date of this testray case type.
	 *
	 * @return the create date of this testray case type
	 */
	@Override
	public Date getCreateDate() {
		return _testrayCaseType.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayCaseType.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray case type.
	 *
	 * @return the group ID of this testray case type
	 */
	@Override
	public long getGroupId() {
		return _testrayCaseType.getGroupId();
	}

	/**
	 * Returns the modified date of this testray case type.
	 *
	 * @return the modified date of this testray case type
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayCaseType.getModifiedDate();
	}

	/**
	 * Returns the name of this testray case type.
	 *
	 * @return the name of this testray case type
	 */
	@Override
	public String getName() {
		return _testrayCaseType.getName();
	}

	/**
	 * Returns the primary key of this testray case type.
	 *
	 * @return the primary key of this testray case type
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayCaseType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayCaseType.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray case type ID of this testray case type.
	 *
	 * @return the testray case type ID of this testray case type
	 */
	@Override
	public long getTestrayCaseTypeId() {
		return _testrayCaseType.getTestrayCaseTypeId();
	}

	/**
	 * Returns the user ID of this testray case type.
	 *
	 * @return the user ID of this testray case type
	 */
	@Override
	public long getUserId() {
		return _testrayCaseType.getUserId();
	}

	/**
	 * Returns the user name of this testray case type.
	 *
	 * @return the user name of this testray case type
	 */
	@Override
	public String getUserName() {
		return _testrayCaseType.getUserName();
	}

	/**
	 * Returns the user uuid of this testray case type.
	 *
	 * @return the user uuid of this testray case type
	 */
	@Override
	public String getUserUuid() {
		return _testrayCaseType.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayCaseType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayCaseType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayCaseType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayCaseType.isNew();
	}

	@Override
	public void persist() {
		_testrayCaseType.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayCaseType.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this testray case type.
	 *
	 * @param companyId the company ID of this testray case type
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayCaseType.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray case type.
	 *
	 * @param createDate the create date of this testray case type
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayCaseType.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayCaseType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayCaseType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayCaseType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray case type.
	 *
	 * @param groupId the group ID of this testray case type
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayCaseType.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray case type.
	 *
	 * @param modifiedDate the modified date of this testray case type
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayCaseType.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this testray case type.
	 *
	 * @param name the name of this testray case type
	 */
	@Override
	public void setName(String name) {
		_testrayCaseType.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_testrayCaseType.setNew(n);
	}

	/**
	 * Sets the primary key of this testray case type.
	 *
	 * @param primaryKey the primary key of this testray case type
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayCaseType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayCaseType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray case type ID of this testray case type.
	 *
	 * @param testrayCaseTypeId the testray case type ID of this testray case type
	 */
	@Override
	public void setTestrayCaseTypeId(long testrayCaseTypeId) {
		_testrayCaseType.setTestrayCaseTypeId(testrayCaseTypeId);
	}

	/**
	 * Sets the user ID of this testray case type.
	 *
	 * @param userId the user ID of this testray case type
	 */
	@Override
	public void setUserId(long userId) {
		_testrayCaseType.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray case type.
	 *
	 * @param userName the user name of this testray case type
	 */
	@Override
	public void setUserName(String userName) {
		_testrayCaseType.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray case type.
	 *
	 * @param userUuid the user uuid of this testray case type
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayCaseType.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayCaseType>
		toCacheModel() {

		return _testrayCaseType.toCacheModel();
	}

	@Override
	public TestrayCaseType toEscapedModel() {
		return new TestrayCaseTypeWrapper(_testrayCaseType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayCaseType.toString();
	}

	@Override
	public TestrayCaseType toUnescapedModel() {
		return new TestrayCaseTypeWrapper(_testrayCaseType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayCaseType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseTypeWrapper)) {
			return false;
		}

		TestrayCaseTypeWrapper testrayCaseTypeWrapper =
			(TestrayCaseTypeWrapper)obj;

		if (Objects.equals(
				_testrayCaseType, testrayCaseTypeWrapper._testrayCaseType)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayCaseType getWrappedModel() {
		return _testrayCaseType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayCaseType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayCaseType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayCaseType.resetOriginalValues();
	}

	private final TestrayCaseType _testrayCaseType;

}