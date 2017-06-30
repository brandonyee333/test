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
 * This class is a wrapper for {@link LicenseEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseEntry
 * @generated
 */
public class LicenseEntryWrapper implements LicenseEntry,
	ModelWrapper<LicenseEntry> {
	public LicenseEntryWrapper(LicenseEntry licenseEntry) {
		_licenseEntry = licenseEntry;
	}

	public Class<?> getModelClass() {
		return LicenseEntry.class;
	}

	public String getModelClassName() {
		return LicenseEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("portalVersionMin", getPortalVersionMin());
		attributes.put("portalVersionMax", getPortalVersionMax());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
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

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer portalVersionMin = (Integer)attributes.get("portalVersionMin");

		if (portalVersionMin != null) {
			setPortalVersionMin(portalVersionMin);
		}

		Integer portalVersionMax = (Integer)attributes.get("portalVersionMax");

		if (portalVersionMax != null) {
			setPortalVersionMax(portalVersionMax);
		}
	}

	/**
	* Returns the primary key of this license entry.
	*
	* @return the primary key of this license entry
	*/
	public long getPrimaryKey() {
		return _licenseEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this license entry.
	*
	* @param primaryKey the primary key of this license entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_licenseEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the license entry ID of this license entry.
	*
	* @return the license entry ID of this license entry
	*/
	public long getLicenseEntryId() {
		return _licenseEntry.getLicenseEntryId();
	}

	/**
	* Sets the license entry ID of this license entry.
	*
	* @param licenseEntryId the license entry ID of this license entry
	*/
	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntry.setLicenseEntryId(licenseEntryId);
	}

	/**
	* Returns the user ID of this license entry.
	*
	* @return the user ID of this license entry
	*/
	public long getUserId() {
		return _licenseEntry.getUserId();
	}

	/**
	* Sets the user ID of this license entry.
	*
	* @param userId the user ID of this license entry
	*/
	public void setUserId(long userId) {
		_licenseEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this license entry.
	*
	* @return the user uuid of this license entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this license entry.
	*
	* @param userUuid the user uuid of this license entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_licenseEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this license entry.
	*
	* @return the user name of this license entry
	*/
	public java.lang.String getUserName() {
		return _licenseEntry.getUserName();
	}

	/**
	* Sets the user name of this license entry.
	*
	* @param userName the user name of this license entry
	*/
	public void setUserName(java.lang.String userName) {
		_licenseEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this license entry.
	*
	* @return the create date of this license entry
	*/
	public java.util.Date getCreateDate() {
		return _licenseEntry.getCreateDate();
	}

	/**
	* Sets the create date of this license entry.
	*
	* @param createDate the create date of this license entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_licenseEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this license entry.
	*
	* @return the modified date of this license entry
	*/
	public java.util.Date getModifiedDate() {
		return _licenseEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this license entry.
	*
	* @param modifiedDate the modified date of this license entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_licenseEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the product entry ID of this license entry.
	*
	* @return the product entry ID of this license entry
	*/
	public long getProductEntryId() {
		return _licenseEntry.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this license entry.
	*
	* @param productEntryId the product entry ID of this license entry
	*/
	public void setProductEntryId(long productEntryId) {
		_licenseEntry.setProductEntryId(productEntryId);
	}

	/**
	* Returns the name of this license entry.
	*
	* @return the name of this license entry
	*/
	public java.lang.String getName() {
		return _licenseEntry.getName();
	}

	/**
	* Sets the name of this license entry.
	*
	* @param name the name of this license entry
	*/
	public void setName(java.lang.String name) {
		_licenseEntry.setName(name);
	}

	/**
	* Returns the type of this license entry.
	*
	* @return the type of this license entry
	*/
	public java.lang.String getType() {
		return _licenseEntry.getType();
	}

	/**
	* Sets the type of this license entry.
	*
	* @param type the type of this license entry
	*/
	public void setType(java.lang.String type) {
		_licenseEntry.setType(type);
	}

	/**
	* Returns the portal version min of this license entry.
	*
	* @return the portal version min of this license entry
	*/
	public int getPortalVersionMin() {
		return _licenseEntry.getPortalVersionMin();
	}

	/**
	* Sets the portal version min of this license entry.
	*
	* @param portalVersionMin the portal version min of this license entry
	*/
	public void setPortalVersionMin(int portalVersionMin) {
		_licenseEntry.setPortalVersionMin(portalVersionMin);
	}

	/**
	* Returns the portal version max of this license entry.
	*
	* @return the portal version max of this license entry
	*/
	public int getPortalVersionMax() {
		return _licenseEntry.getPortalVersionMax();
	}

	/**
	* Sets the portal version max of this license entry.
	*
	* @param portalVersionMax the portal version max of this license entry
	*/
	public void setPortalVersionMax(int portalVersionMax) {
		_licenseEntry.setPortalVersionMax(portalVersionMax);
	}

	public boolean isNew() {
		return _licenseEntry.isNew();
	}

	public void setNew(boolean n) {
		_licenseEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _licenseEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_licenseEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _licenseEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _licenseEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_licenseEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _licenseEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_licenseEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LicenseEntryWrapper((LicenseEntry)_licenseEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.LicenseEntry licenseEntry) {
		return _licenseEntry.compareTo(licenseEntry);
	}

	@Override
	public int hashCode() {
		return _licenseEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.LicenseEntry> toCacheModel() {
		return _licenseEntry.toCacheModel();
	}

	public com.liferay.osb.model.LicenseEntry toEscapedModel() {
		return new LicenseEntryWrapper(_licenseEntry.toEscapedModel());
	}

	public com.liferay.osb.model.LicenseEntry toUnescapedModel() {
		return new LicenseEntryWrapper(_licenseEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _licenseEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _licenseEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_licenseEntry.persist();
	}

	public java.lang.String getPortalVersionLabel() {
		return _licenseEntry.getPortalVersionLabel();
	}

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntry.getProductEntry();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseEntryWrapper)) {
			return false;
		}

		LicenseEntryWrapper licenseEntryWrapper = (LicenseEntryWrapper)obj;

		if (Validator.equals(_licenseEntry, licenseEntryWrapper._licenseEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public LicenseEntry getWrappedLicenseEntry() {
		return _licenseEntry;
	}

	public LicenseEntry getWrappedModel() {
		return _licenseEntry;
	}

	public void resetOriginalValues() {
		_licenseEntry.resetOriginalValues();
	}

	private LicenseEntry _licenseEntry;
}