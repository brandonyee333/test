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

package com.liferay.osb.model;

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
 * This class is a wrapper for {@link LicenseKeySet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySet
 * @generated
 */
@ProviderType
public class LicenseKeySetWrapper implements LicenseKeySet,
	ModelWrapper<LicenseKeySet> {
	public LicenseKeySetWrapper(LicenseKeySet licenseKeySet) {
		_licenseKeySet = licenseKeySet;
	}

	@Override
	public Class<?> getModelClass() {
		return LicenseKeySet.class;
	}

	@Override
	public String getModelClassName() {
		return LicenseKeySet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseKeySetId", getLicenseKeySetId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseKeySetId = (Long)attributes.get("licenseKeySetId");

		if (licenseKeySetId != null) {
			setLicenseKeySetId(licenseKeySetId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _licenseKeySet.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _licenseKeySet.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _licenseKeySet.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _licenseKeySet.getExpandoBridge();
	}

	@Override
	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeySet.getAccountEntry();
	}

	@Override
	public LicenseKeySet toEscapedModel() {
		return new LicenseKeySetWrapper(_licenseKeySet.toEscapedModel());
	}

	@Override
	public LicenseKeySet toUnescapedModel() {
		return new LicenseKeySetWrapper(_licenseKeySet.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LicenseKeySet> toCacheModel() {
		return _licenseKeySet.toCacheModel();
	}

	@Override
	public int compareTo(LicenseKeySet licenseKeySet) {
		return _licenseKeySet.compareTo(licenseKeySet);
	}

	@Override
	public int hashCode() {
		return _licenseKeySet.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseKeySet.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LicenseKeySetWrapper((LicenseKeySet)_licenseKeySet.clone());
	}

	/**
	* Returns the name of this license key set.
	*
	* @return the name of this license key set
	*/
	@Override
	public java.lang.String getName() {
		return _licenseKeySet.getName();
	}

	/**
	* Returns the user name of this license key set.
	*
	* @return the user name of this license key set
	*/
	@Override
	public java.lang.String getUserName() {
		return _licenseKeySet.getUserName();
	}

	/**
	* Returns the user uuid of this license key set.
	*
	* @return the user uuid of this license key set
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _licenseKeySet.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _licenseKeySet.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _licenseKeySet.toXmlString();
	}

	/**
	* Returns the create date of this license key set.
	*
	* @return the create date of this license key set
	*/
	@Override
	public Date getCreateDate() {
		return _licenseKeySet.getCreateDate();
	}

	/**
	* Returns the modified date of this license key set.
	*
	* @return the modified date of this license key set
	*/
	@Override
	public Date getModifiedDate() {
		return _licenseKeySet.getModifiedDate();
	}

	/**
	* Returns the account entry ID of this license key set.
	*
	* @return the account entry ID of this license key set
	*/
	@Override
	public long getAccountEntryId() {
		return _licenseKeySet.getAccountEntryId();
	}

	/**
	* Returns the license key set ID of this license key set.
	*
	* @return the license key set ID of this license key set
	*/
	@Override
	public long getLicenseKeySetId() {
		return _licenseKeySet.getLicenseKeySetId();
	}

	/**
	* Returns the primary key of this license key set.
	*
	* @return the primary key of this license key set
	*/
	@Override
	public long getPrimaryKey() {
		return _licenseKeySet.getPrimaryKey();
	}

	/**
	* Returns the user ID of this license key set.
	*
	* @return the user ID of this license key set
	*/
	@Override
	public long getUserId() {
		return _licenseKeySet.getUserId();
	}

	@Override
	public void persist() {
		_licenseKeySet.persist();
	}

	/**
	* Sets the account entry ID of this license key set.
	*
	* @param accountEntryId the account entry ID of this license key set
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_licenseKeySet.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_licenseKeySet.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this license key set.
	*
	* @param createDate the create date of this license key set
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_licenseKeySet.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_licenseKeySet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_licenseKeySet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_licenseKeySet.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the license key set ID of this license key set.
	*
	* @param licenseKeySetId the license key set ID of this license key set
	*/
	@Override
	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKeySet.setLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Sets the modified date of this license key set.
	*
	* @param modifiedDate the modified date of this license key set
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_licenseKeySet.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this license key set.
	*
	* @param name the name of this license key set
	*/
	@Override
	public void setName(java.lang.String name) {
		_licenseKeySet.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_licenseKeySet.setNew(n);
	}

	/**
	* Sets the primary key of this license key set.
	*
	* @param primaryKey the primary key of this license key set
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_licenseKeySet.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_licenseKeySet.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this license key set.
	*
	* @param userId the user ID of this license key set
	*/
	@Override
	public void setUserId(long userId) {
		_licenseKeySet.setUserId(userId);
	}

	/**
	* Sets the user name of this license key set.
	*
	* @param userName the user name of this license key set
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_licenseKeySet.setUserName(userName);
	}

	/**
	* Sets the user uuid of this license key set.
	*
	* @param userUuid the user uuid of this license key set
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_licenseKeySet.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseKeySetWrapper)) {
			return false;
		}

		LicenseKeySetWrapper licenseKeySetWrapper = (LicenseKeySetWrapper)obj;

		if (Objects.equals(_licenseKeySet, licenseKeySetWrapper._licenseKeySet)) {
			return true;
		}

		return false;
	}

	@Override
	public LicenseKeySet getWrappedModel() {
		return _licenseKeySet;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _licenseKeySet.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _licenseKeySet.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_licenseKeySet.resetOriginalValues();
	}

	private final LicenseKeySet _licenseKeySet;
}