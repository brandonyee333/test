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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LicenseKeySet}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseKeySet
 * @generated
 */
public class LicenseKeySetWrapper implements LicenseKeySet,
	ModelWrapper<LicenseKeySet> {
	public LicenseKeySetWrapper(LicenseKeySet licenseKeySet) {
		_licenseKeySet = licenseKeySet;
	}

	public Class<?> getModelClass() {
		return LicenseKeySet.class;
	}

	public String getModelClassName() {
		return LicenseKeySet.class.getName();
	}

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

	/**
	* Returns the primary key of this license key set.
	*
	* @return the primary key of this license key set
	*/
	public long getPrimaryKey() {
		return _licenseKeySet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this license key set.
	*
	* @param primaryKey the primary key of this license key set
	*/
	public void setPrimaryKey(long primaryKey) {
		_licenseKeySet.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the license key set ID of this license key set.
	*
	* @return the license key set ID of this license key set
	*/
	public long getLicenseKeySetId() {
		return _licenseKeySet.getLicenseKeySetId();
	}

	/**
	* Sets the license key set ID of this license key set.
	*
	* @param licenseKeySetId the license key set ID of this license key set
	*/
	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKeySet.setLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Returns the user ID of this license key set.
	*
	* @return the user ID of this license key set
	*/
	public long getUserId() {
		return _licenseKeySet.getUserId();
	}

	/**
	* Sets the user ID of this license key set.
	*
	* @param userId the user ID of this license key set
	*/
	public void setUserId(long userId) {
		_licenseKeySet.setUserId(userId);
	}

	/**
	* Returns the user uuid of this license key set.
	*
	* @return the user uuid of this license key set
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySet.getUserUuid();
	}

	/**
	* Sets the user uuid of this license key set.
	*
	* @param userUuid the user uuid of this license key set
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_licenseKeySet.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this license key set.
	*
	* @return the user name of this license key set
	*/
	public java.lang.String getUserName() {
		return _licenseKeySet.getUserName();
	}

	/**
	* Sets the user name of this license key set.
	*
	* @param userName the user name of this license key set
	*/
	public void setUserName(java.lang.String userName) {
		_licenseKeySet.setUserName(userName);
	}

	/**
	* Returns the create date of this license key set.
	*
	* @return the create date of this license key set
	*/
	public java.util.Date getCreateDate() {
		return _licenseKeySet.getCreateDate();
	}

	/**
	* Sets the create date of this license key set.
	*
	* @param createDate the create date of this license key set
	*/
	public void setCreateDate(java.util.Date createDate) {
		_licenseKeySet.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this license key set.
	*
	* @return the modified date of this license key set
	*/
	public java.util.Date getModifiedDate() {
		return _licenseKeySet.getModifiedDate();
	}

	/**
	* Sets the modified date of this license key set.
	*
	* @param modifiedDate the modified date of this license key set
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_licenseKeySet.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this license key set.
	*
	* @return the account entry ID of this license key set
	*/
	public long getAccountEntryId() {
		return _licenseKeySet.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this license key set.
	*
	* @param accountEntryId the account entry ID of this license key set
	*/
	public void setAccountEntryId(long accountEntryId) {
		_licenseKeySet.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the name of this license key set.
	*
	* @return the name of this license key set
	*/
	public java.lang.String getName() {
		return _licenseKeySet.getName();
	}

	/**
	* Sets the name of this license key set.
	*
	* @param name the name of this license key set
	*/
	public void setName(java.lang.String name) {
		_licenseKeySet.setName(name);
	}

	public boolean isNew() {
		return _licenseKeySet.isNew();
	}

	public void setNew(boolean n) {
		_licenseKeySet.setNew(n);
	}

	public boolean isCachedModel() {
		return _licenseKeySet.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_licenseKeySet.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _licenseKeySet.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _licenseKeySet.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_licenseKeySet.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _licenseKeySet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_licenseKeySet.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LicenseKeySetWrapper((LicenseKeySet)_licenseKeySet.clone());
	}

	public int compareTo(com.liferay.osb.model.LicenseKeySet licenseKeySet) {
		return _licenseKeySet.compareTo(licenseKeySet);
	}

	@Override
	public int hashCode() {
		return _licenseKeySet.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.LicenseKeySet> toCacheModel() {
		return _licenseKeySet.toCacheModel();
	}

	public com.liferay.osb.model.LicenseKeySet toEscapedModel() {
		return new LicenseKeySetWrapper(_licenseKeySet.toEscapedModel());
	}

	public com.liferay.osb.model.LicenseKeySet toUnescapedModel() {
		return new LicenseKeySetWrapper(_licenseKeySet.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _licenseKeySet.toString();
	}

	public java.lang.String toXmlString() {
		return _licenseKeySet.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_licenseKeySet.persist();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySet.getAccountEntry();
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

		if (Validator.equals(_licenseKeySet, licenseKeySetWrapper._licenseKeySet)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public LicenseKeySet getWrappedLicenseKeySet() {
		return _licenseKeySet;
	}

	public LicenseKeySet getWrappedModel() {
		return _licenseKeySet;
	}

	public void resetOriginalValues() {
		_licenseKeySet.resetOriginalValues();
	}

	private LicenseKeySet _licenseKeySet;
}