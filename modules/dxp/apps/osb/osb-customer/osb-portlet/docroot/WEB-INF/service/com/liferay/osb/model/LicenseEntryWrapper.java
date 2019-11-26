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
 * This class is a wrapper for {@link LicenseEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntry
 * @generated
 */
@ProviderType
public class LicenseEntryWrapper implements LicenseEntry,
	ModelWrapper<LicenseEntry> {
	public LicenseEntryWrapper(LicenseEntry licenseEntry) {
		_licenseEntry = licenseEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LicenseEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LicenseEntry.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public boolean isCachedModel() {
		return _licenseEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _licenseEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _licenseEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _licenseEntry.getExpandoBridge();
	}

	@Override
	public LicenseEntry toEscapedModel() {
		return new LicenseEntryWrapper(_licenseEntry.toEscapedModel());
	}

	@Override
	public LicenseEntry toUnescapedModel() {
		return new LicenseEntryWrapper(_licenseEntry.toUnescapedModel());
	}

	@Override
	public ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntry.getProductEntry();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LicenseEntry> toCacheModel() {
		return _licenseEntry.toCacheModel();
	}

	@Override
	public int compareTo(LicenseEntry licenseEntry) {
		return _licenseEntry.compareTo(licenseEntry);
	}

	/**
	* Returns the portal version max of this license entry.
	*
	* @return the portal version max of this license entry
	*/
	@Override
	public int getPortalVersionMax() {
		return _licenseEntry.getPortalVersionMax();
	}

	/**
	* Returns the portal version min of this license entry.
	*
	* @return the portal version min of this license entry
	*/
	@Override
	public int getPortalVersionMin() {
		return _licenseEntry.getPortalVersionMin();
	}

	@Override
	public int hashCode() {
		return _licenseEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LicenseEntryWrapper((LicenseEntry)_licenseEntry.clone());
	}

	/**
	* Returns the name of this license entry.
	*
	* @return the name of this license entry
	*/
	@Override
	public java.lang.String getName() {
		return _licenseEntry.getName();
	}

	@Override
	public java.lang.String getPortalVersionLabel() {
		return _licenseEntry.getPortalVersionLabel();
	}

	/**
	* Returns the type of this license entry.
	*
	* @return the type of this license entry
	*/
	@Override
	public java.lang.String getType() {
		return _licenseEntry.getType();
	}

	/**
	* Returns the user name of this license entry.
	*
	* @return the user name of this license entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _licenseEntry.getUserName();
	}

	/**
	* Returns the user uuid of this license entry.
	*
	* @return the user uuid of this license entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _licenseEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _licenseEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _licenseEntry.toXmlString();
	}

	/**
	* Returns the create date of this license entry.
	*
	* @return the create date of this license entry
	*/
	@Override
	public Date getCreateDate() {
		return _licenseEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this license entry.
	*
	* @return the modified date of this license entry
	*/
	@Override
	public Date getModifiedDate() {
		return _licenseEntry.getModifiedDate();
	}

	/**
	* Returns the license entry ID of this license entry.
	*
	* @return the license entry ID of this license entry
	*/
	@Override
	public long getLicenseEntryId() {
		return _licenseEntry.getLicenseEntryId();
	}

	/**
	* Returns the primary key of this license entry.
	*
	* @return the primary key of this license entry
	*/
	@Override
	public long getPrimaryKey() {
		return _licenseEntry.getPrimaryKey();
	}

	/**
	* Returns the product entry ID of this license entry.
	*
	* @return the product entry ID of this license entry
	*/
	@Override
	public long getProductEntryId() {
		return _licenseEntry.getProductEntryId();
	}

	/**
	* Returns the user ID of this license entry.
	*
	* @return the user ID of this license entry
	*/
	@Override
	public long getUserId() {
		return _licenseEntry.getUserId();
	}

	@Override
	public void persist() {
		_licenseEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_licenseEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this license entry.
	*
	* @param createDate the create date of this license entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_licenseEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_licenseEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_licenseEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_licenseEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the license entry ID of this license entry.
	*
	* @param licenseEntryId the license entry ID of this license entry
	*/
	@Override
	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntry.setLicenseEntryId(licenseEntryId);
	}

	/**
	* Sets the modified date of this license entry.
	*
	* @param modifiedDate the modified date of this license entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_licenseEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this license entry.
	*
	* @param name the name of this license entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_licenseEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_licenseEntry.setNew(n);
	}

	/**
	* Sets the portal version max of this license entry.
	*
	* @param portalVersionMax the portal version max of this license entry
	*/
	@Override
	public void setPortalVersionMax(int portalVersionMax) {
		_licenseEntry.setPortalVersionMax(portalVersionMax);
	}

	/**
	* Sets the portal version min of this license entry.
	*
	* @param portalVersionMin the portal version min of this license entry
	*/
	@Override
	public void setPortalVersionMin(int portalVersionMin) {
		_licenseEntry.setPortalVersionMin(portalVersionMin);
	}

	/**
	* Sets the primary key of this license entry.
	*
	* @param primaryKey the primary key of this license entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_licenseEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_licenseEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product entry ID of this license entry.
	*
	* @param productEntryId the product entry ID of this license entry
	*/
	@Override
	public void setProductEntryId(long productEntryId) {
		_licenseEntry.setProductEntryId(productEntryId);
	}

	/**
	* Sets the type of this license entry.
	*
	* @param type the type of this license entry
	*/
	@Override
	public void setType(java.lang.String type) {
		_licenseEntry.setType(type);
	}

	/**
	* Sets the user ID of this license entry.
	*
	* @param userId the user ID of this license entry
	*/
	@Override
	public void setUserId(long userId) {
		_licenseEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this license entry.
	*
	* @param userName the user name of this license entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_licenseEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this license entry.
	*
	* @param userUuid the user uuid of this license entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_licenseEntry.setUserUuid(userUuid);
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

		if (Objects.equals(_licenseEntry, licenseEntryWrapper._licenseEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LicenseEntry getWrappedModel() {
		return _licenseEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _licenseEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _licenseEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_licenseEntry.resetOriginalValues();
	}

	private final LicenseEntry _licenseEntry;
}