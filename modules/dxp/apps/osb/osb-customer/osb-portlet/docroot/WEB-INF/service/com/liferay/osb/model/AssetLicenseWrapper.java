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
 * This class is a wrapper for {@link AssetLicense}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetLicense
 * @generated
 */
public class AssetLicenseWrapper implements AssetLicense,
	ModelWrapper<AssetLicense> {
	public AssetLicenseWrapper(AssetLicense assetLicense) {
		_assetLicense = assetLicense;
	}

	public Class<?> getModelClass() {
		return AssetLicense.class;
	}

	public String getModelClassName() {
		return AssetLicense.class.getName();
	}

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

	/**
	* Returns the primary key of this asset license.
	*
	* @return the primary key of this asset license
	*/
	public long getPrimaryKey() {
		return _assetLicense.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset license.
	*
	* @param primaryKey the primary key of this asset license
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetLicense.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset license ID of this asset license.
	*
	* @return the asset license ID of this asset license
	*/
	public long getAssetLicenseId() {
		return _assetLicense.getAssetLicenseId();
	}

	/**
	* Sets the asset license ID of this asset license.
	*
	* @param assetLicenseId the asset license ID of this asset license
	*/
	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicense.setAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns the user ID of this asset license.
	*
	* @return the user ID of this asset license
	*/
	public long getUserId() {
		return _assetLicense.getUserId();
	}

	/**
	* Sets the user ID of this asset license.
	*
	* @param userId the user ID of this asset license
	*/
	public void setUserId(long userId) {
		_assetLicense.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset license.
	*
	* @return the user uuid of this asset license
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicense.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset license.
	*
	* @param userUuid the user uuid of this asset license
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetLicense.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this asset license.
	*
	* @return the create date of this asset license
	*/
	public java.util.Date getCreateDate() {
		return _assetLicense.getCreateDate();
	}

	/**
	* Sets the create date of this asset license.
	*
	* @param createDate the create date of this asset license
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetLicense.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset license.
	*
	* @return the modified date of this asset license
	*/
	public java.util.Date getModifiedDate() {
		return _assetLicense.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset license.
	*
	* @param modifiedDate the modified date of this asset license
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetLicense.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this asset license.
	*
	* @return the fully qualified class name of this asset license
	*/
	public java.lang.String getClassName() {
		return _assetLicense.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetLicense.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset license.
	*
	* @return the class name ID of this asset license
	*/
	public long getClassNameId() {
		return _assetLicense.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset license.
	*
	* @param classNameId the class name ID of this asset license
	*/
	public void setClassNameId(long classNameId) {
		_assetLicense.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset license.
	*
	* @return the class p k of this asset license
	*/
	public long getClassPK() {
		return _assetLicense.getClassPK();
	}

	/**
	* Sets the class p k of this asset license.
	*
	* @param classPK the class p k of this asset license
	*/
	public void setClassPK(long classPK) {
		_assetLicense.setClassPK(classPK);
	}

	/**
	* Returns the license ID of this asset license.
	*
	* @return the license ID of this asset license
	*/
	public java.lang.String getLicenseId() {
		return _assetLicense.getLicenseId();
	}

	/**
	* Sets the license ID of this asset license.
	*
	* @param licenseId the license ID of this asset license
	*/
	public void setLicenseId(java.lang.String licenseId) {
		_assetLicense.setLicenseId(licenseId);
	}

	/**
	* Returns the name of this asset license.
	*
	* @return the name of this asset license
	*/
	public java.lang.String getName() {
		return _assetLicense.getName();
	}

	/**
	* Sets the name of this asset license.
	*
	* @param name the name of this asset license
	*/
	public void setName(java.lang.String name) {
		_assetLicense.setName(name);
	}

	/**
	* Returns the required version of this asset license.
	*
	* @return the required version of this asset license
	*/
	public double getRequiredVersion() {
		return _assetLicense.getRequiredVersion();
	}

	/**
	* Sets the required version of this asset license.
	*
	* @param requiredVersion the required version of this asset license
	*/
	public void setRequiredVersion(double requiredVersion) {
		_assetLicense.setRequiredVersion(requiredVersion);
	}

	/**
	* Returns the usage type of this asset license.
	*
	* @return the usage type of this asset license
	*/
	public int getUsageType() {
		return _assetLicense.getUsageType();
	}

	/**
	* Sets the usage type of this asset license.
	*
	* @param usageType the usage type of this asset license
	*/
	public void setUsageType(int usageType) {
		_assetLicense.setUsageType(usageType);
	}

	/**
	* Returns the license type of this asset license.
	*
	* @return the license type of this asset license
	*/
	public int getLicenseType() {
		return _assetLicense.getLicenseType();
	}

	/**
	* Sets the license type of this asset license.
	*
	* @param licenseType the license type of this asset license
	*/
	public void setLicenseType(int licenseType) {
		_assetLicense.setLicenseType(licenseType);
	}

	/**
	* Returns the license type allotment of this asset license.
	*
	* @return the license type allotment of this asset license
	*/
	public long getLicenseTypeAllotment() {
		return _assetLicense.getLicenseTypeAllotment();
	}

	/**
	* Sets the license type allotment of this asset license.
	*
	* @param licenseTypeAllotment the license type allotment of this asset license
	*/
	public void setLicenseTypeAllotment(long licenseTypeAllotment) {
		_assetLicense.setLicenseTypeAllotment(licenseTypeAllotment);
	}

	/**
	* Returns the lifetime of this asset license.
	*
	* @return the lifetime of this asset license
	*/
	public long getLifetime() {
		return _assetLicense.getLifetime();
	}

	/**
	* Sets the lifetime of this asset license.
	*
	* @param lifetime the lifetime of this asset license
	*/
	public void setLifetime(long lifetime) {
		_assetLicense.setLifetime(lifetime);
	}

	/**
	* Returns the status of this asset license.
	*
	* @return the status of this asset license
	*/
	public int getStatus() {
		return _assetLicense.getStatus();
	}

	/**
	* Sets the status of this asset license.
	*
	* @param status the status of this asset license
	*/
	public void setStatus(int status) {
		_assetLicense.setStatus(status);
	}

	public boolean isNew() {
		return _assetLicense.isNew();
	}

	public void setNew(boolean n) {
		_assetLicense.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetLicense.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetLicense.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetLicense.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetLicense.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetLicense.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetLicense.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetLicense.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetLicenseWrapper((AssetLicense)_assetLicense.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetLicense assetLicense) {
		return _assetLicense.compareTo(assetLicense);
	}

	@Override
	public int hashCode() {
		return _assetLicense.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetLicense> toCacheModel() {
		return _assetLicense.toCacheModel();
	}

	public com.liferay.osb.model.AssetLicense toEscapedModel() {
		return new AssetLicenseWrapper(_assetLicense.toEscapedModel());
	}

	public com.liferay.osb.model.AssetLicense toUnescapedModel() {
		return new AssetLicenseWrapper(_assetLicense.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetLicense.toString();
	}

	public java.lang.String toXmlString() {
		return _assetLicense.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetLicense.persist();
	}

	public java.lang.String getLicenseKeyType()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetLicense.getLicenseKeyType();
	}

	public java.lang.String getLicenseTypeLabel() {
		return _assetLicense.getLicenseTypeLabel();
	}

	public java.lang.String getLifetimeLabel() {
		return _assetLicense.getLifetimeLabel();
	}

	public java.lang.String getUsageTypeLabel() {
		return _assetLicense.getUsageTypeLabel();
	}

	public boolean isPurchased()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicense.isPurchased();
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

		if (Validator.equals(_assetLicense, assetLicenseWrapper._assetLicense)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetLicense getWrappedAssetLicense() {
		return _assetLicense;
	}

	public AssetLicense getWrappedModel() {
		return _assetLicense;
	}

	public void resetOriginalValues() {
		_assetLicense.resetOriginalValues();
	}

	private AssetLicense _assetLicense;
}