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
 * This class is a wrapper for {@link AssetLicense}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicense
 * @generated
 */
@ProviderType
public class AssetLicenseWrapper implements AssetLicense,
	ModelWrapper<AssetLicense> {
	public AssetLicenseWrapper(AssetLicense assetLicense) {
		_assetLicense = assetLicense;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetLicense.class;
	}

	@Override
	public String getModelClassName() {
		return AssetLicense.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetLicenseId", getAssetLicenseId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("licenseId", getLicenseId());
		attributes.put("name", getName());
		attributes.put("requiredVersion", getRequiredVersion());
		attributes.put("usageType", getUsageType());
		attributes.put("licenseType", getLicenseType());
		attributes.put("licenseTypeAllotment", getLicenseTypeAllotment());
		attributes.put("lifetime", getLifetime());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetLicenseId = (Long)attributes.get("assetLicenseId");

		if (assetLicenseId != null) {
			setAssetLicenseId(assetLicenseId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String licenseId = (String)attributes.get("licenseId");

		if (licenseId != null) {
			setLicenseId(licenseId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Double requiredVersion = (Double)attributes.get("requiredVersion");

		if (requiredVersion != null) {
			setRequiredVersion(requiredVersion);
		}

		Integer usageType = (Integer)attributes.get("usageType");

		if (usageType != null) {
			setUsageType(usageType);
		}

		Integer licenseType = (Integer)attributes.get("licenseType");

		if (licenseType != null) {
			setLicenseType(licenseType);
		}

		Long licenseTypeAllotment = (Long)attributes.get("licenseTypeAllotment");

		if (licenseTypeAllotment != null) {
			setLicenseTypeAllotment(licenseTypeAllotment);
		}

		Long lifetime = (Long)attributes.get("lifetime");

		if (lifetime != null) {
			setLifetime(lifetime);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AssetLicenseWrapper((AssetLicense)_assetLicense.clone());
	}

	@Override
	public int compareTo(AssetLicense assetLicense) {
		return _assetLicense.compareTo(assetLicense);
	}

	/**
	* Returns the asset license ID of this asset license.
	*
	* @return the asset license ID of this asset license
	*/
	@Override
	public long getAssetLicenseId() {
		return _assetLicense.getAssetLicenseId();
	}

	/**
	* Returns the fully qualified class name of this asset license.
	*
	* @return the fully qualified class name of this asset license
	*/
	@Override
	public java.lang.String getClassName() {
		return _assetLicense.getClassName();
	}

	/**
	* Returns the class name ID of this asset license.
	*
	* @return the class name ID of this asset license
	*/
	@Override
	public long getClassNameId() {
		return _assetLicense.getClassNameId();
	}

	/**
	* Returns the class pk of this asset license.
	*
	* @return the class pk of this asset license
	*/
	@Override
	public long getClassPK() {
		return _assetLicense.getClassPK();
	}

	/**
	* Returns the create date of this asset license.
	*
	* @return the create date of this asset license
	*/
	@Override
	public Date getCreateDate() {
		return _assetLicense.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetLicense.getExpandoBridge();
	}

	/**
	* Returns the license ID of this asset license.
	*
	* @return the license ID of this asset license
	*/
	@Override
	public java.lang.String getLicenseId() {
		return _assetLicense.getLicenseId();
	}

	/**
	* Returns the license type of this asset license.
	*
	* @return the license type of this asset license
	*/
	@Override
	public int getLicenseType() {
		return _assetLicense.getLicenseType();
	}

	/**
	* Returns the license type allotment of this asset license.
	*
	* @return the license type allotment of this asset license
	*/
	@Override
	public long getLicenseTypeAllotment() {
		return _assetLicense.getLicenseTypeAllotment();
	}

	/**
	* Returns the lifetime of this asset license.
	*
	* @return the lifetime of this asset license
	*/
	@Override
	public long getLifetime() {
		return _assetLicense.getLifetime();
	}

	/**
	* Returns the modified date of this asset license.
	*
	* @return the modified date of this asset license
	*/
	@Override
	public Date getModifiedDate() {
		return _assetLicense.getModifiedDate();
	}

	/**
	* Returns the name of this asset license.
	*
	* @return the name of this asset license
	*/
	@Override
	public java.lang.String getName() {
		return _assetLicense.getName();
	}

	/**
	* Returns the primary key of this asset license.
	*
	* @return the primary key of this asset license
	*/
	@Override
	public long getPrimaryKey() {
		return _assetLicense.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetLicense.getPrimaryKeyObj();
	}

	/**
	* Returns the required version of this asset license.
	*
	* @return the required version of this asset license
	*/
	@Override
	public double getRequiredVersion() {
		return _assetLicense.getRequiredVersion();
	}

	/**
	* Returns the status of this asset license.
	*
	* @return the status of this asset license
	*/
	@Override
	public int getStatus() {
		return _assetLicense.getStatus();
	}

	/**
	* Returns the usage type of this asset license.
	*
	* @return the usage type of this asset license
	*/
	@Override
	public int getUsageType() {
		return _assetLicense.getUsageType();
	}

	/**
	* Returns the user ID of this asset license.
	*
	* @return the user ID of this asset license
	*/
	@Override
	public long getUserId() {
		return _assetLicense.getUserId();
	}

	/**
	* Returns the user uuid of this asset license.
	*
	* @return the user uuid of this asset license
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _assetLicense.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _assetLicense.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetLicense.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetLicense.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetLicense.isNew();
	}

	@Override
	public void persist() {
		_assetLicense.persist();
	}

	/**
	* Sets the asset license ID of this asset license.
	*
	* @param assetLicenseId the asset license ID of this asset license
	*/
	@Override
	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicense.setAssetLicenseId(assetLicenseId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetLicense.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_assetLicense.setClassName(className);
	}

	/**
	* Sets the class name ID of this asset license.
	*
	* @param classNameId the class name ID of this asset license
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_assetLicense.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this asset license.
	*
	* @param classPK the class pk of this asset license
	*/
	@Override
	public void setClassPK(long classPK) {
		_assetLicense.setClassPK(classPK);
	}

	/**
	* Sets the create date of this asset license.
	*
	* @param createDate the create date of this asset license
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_assetLicense.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetLicense.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetLicense.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetLicense.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the license ID of this asset license.
	*
	* @param licenseId the license ID of this asset license
	*/
	@Override
	public void setLicenseId(java.lang.String licenseId) {
		_assetLicense.setLicenseId(licenseId);
	}

	/**
	* Sets the license type of this asset license.
	*
	* @param licenseType the license type of this asset license
	*/
	@Override
	public void setLicenseType(int licenseType) {
		_assetLicense.setLicenseType(licenseType);
	}

	/**
	* Sets the license type allotment of this asset license.
	*
	* @param licenseTypeAllotment the license type allotment of this asset license
	*/
	@Override
	public void setLicenseTypeAllotment(long licenseTypeAllotment) {
		_assetLicense.setLicenseTypeAllotment(licenseTypeAllotment);
	}

	/**
	* Sets the lifetime of this asset license.
	*
	* @param lifetime the lifetime of this asset license
	*/
	@Override
	public void setLifetime(long lifetime) {
		_assetLicense.setLifetime(lifetime);
	}

	/**
	* Sets the modified date of this asset license.
	*
	* @param modifiedDate the modified date of this asset license
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_assetLicense.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this asset license.
	*
	* @param name the name of this asset license
	*/
	@Override
	public void setName(java.lang.String name) {
		_assetLicense.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_assetLicense.setNew(n);
	}

	/**
	* Sets the primary key of this asset license.
	*
	* @param primaryKey the primary key of this asset license
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetLicense.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetLicense.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the required version of this asset license.
	*
	* @param requiredVersion the required version of this asset license
	*/
	@Override
	public void setRequiredVersion(double requiredVersion) {
		_assetLicense.setRequiredVersion(requiredVersion);
	}

	/**
	* Sets the status of this asset license.
	*
	* @param status the status of this asset license
	*/
	@Override
	public void setStatus(int status) {
		_assetLicense.setStatus(status);
	}

	/**
	* Sets the usage type of this asset license.
	*
	* @param usageType the usage type of this asset license
	*/
	@Override
	public void setUsageType(int usageType) {
		_assetLicense.setUsageType(usageType);
	}

	/**
	* Sets the user ID of this asset license.
	*
	* @param userId the user ID of this asset license
	*/
	@Override
	public void setUserId(long userId) {
		_assetLicense.setUserId(userId);
	}

	/**
	* Sets the user uuid of this asset license.
	*
	* @param userUuid the user uuid of this asset license
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_assetLicense.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetLicense> toCacheModel() {
		return _assetLicense.toCacheModel();
	}

	@Override
	public AssetLicense toEscapedModel() {
		return new AssetLicenseWrapper(_assetLicense.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetLicense.toString();
	}

	@Override
	public AssetLicense toUnescapedModel() {
		return new AssetLicenseWrapper(_assetLicense.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetLicense.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetLicenseWrapper)) {
			return false;
		}

		AssetLicenseWrapper assetLicenseWrapper = (AssetLicenseWrapper)obj;

		if (Objects.equals(_assetLicense, assetLicenseWrapper._assetLicense)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetLicense getWrappedModel() {
		return _assetLicense;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetLicense.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetLicense.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetLicense.resetOriginalValues();
	}

	private final AssetLicense _assetLicense;
}