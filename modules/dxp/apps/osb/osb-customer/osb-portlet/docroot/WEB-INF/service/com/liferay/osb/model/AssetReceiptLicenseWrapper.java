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
 * This class is a wrapper for {@link AssetReceiptLicense}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicense
 * @generated
 */
@ProviderType
public class AssetReceiptLicenseWrapper implements AssetReceiptLicense,
	ModelWrapper<AssetReceiptLicense> {
	public AssetReceiptLicenseWrapper(AssetReceiptLicense assetReceiptLicense) {
		_assetReceiptLicense = assetReceiptLicense;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetReceiptLicense.class;
	}

	@Override
	public String getModelClassName() {
		return AssetReceiptLicense.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("assetReceiptLicenseId", getAssetReceiptLicenseId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("assetReceiptId", getAssetReceiptId());
		attributes.put("assetLicenseId", getAssetLicenseId());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("ownerClassNameId", getOwnerClassNameId());
		attributes.put("ownerClassPK", getOwnerClassPK());
		attributes.put("productClassNameId", getProductClassNameId());
		attributes.put("productClassPK", getProductClassPK());
		attributes.put("productId", getProductId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("usageType", getUsageType());
		attributes.put("licenseType", getLicenseType());
		attributes.put("licenseTypeAllotment", getLicenseTypeAllotment());
		attributes.put("licenseLifetime", getLicenseLifetime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long assetReceiptLicenseId = (Long)attributes.get(
				"assetReceiptLicenseId");

		if (assetReceiptLicenseId != null) {
			setAssetReceiptLicenseId(assetReceiptLicenseId);
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

		Long assetReceiptId = (Long)attributes.get("assetReceiptId");

		if (assetReceiptId != null) {
			setAssetReceiptId(assetReceiptId);
		}

		Long assetLicenseId = (Long)attributes.get("assetLicenseId");

		if (assetLicenseId != null) {
			setAssetLicenseId(assetLicenseId);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long ownerClassNameId = (Long)attributes.get("ownerClassNameId");

		if (ownerClassNameId != null) {
			setOwnerClassNameId(ownerClassNameId);
		}

		Long ownerClassPK = (Long)attributes.get("ownerClassPK");

		if (ownerClassPK != null) {
			setOwnerClassPK(ownerClassPK);
		}

		Long productClassNameId = (Long)attributes.get("productClassNameId");

		if (productClassNameId != null) {
			setProductClassNameId(productClassNameId);
		}

		Long productClassPK = (Long)attributes.get("productClassPK");

		if (productClassPK != null) {
			setProductClassPK(productClassPK);
		}

		String productId = (String)attributes.get("productId");

		if (productId != null) {
			setProductId(productId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
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

		Long licenseLifetime = (Long)attributes.get("licenseLifetime");

		if (licenseLifetime != null) {
			setLicenseLifetime(licenseLifetime);
		}
	}

	@Override
	public AssetReceiptLicense toEscapedModel() {
		return new AssetReceiptLicenseWrapper(_assetReceiptLicense.toEscapedModel());
	}

	@Override
	public AssetReceiptLicense toUnescapedModel() {
		return new AssetReceiptLicenseWrapper(_assetReceiptLicense.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _assetReceiptLicense.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetReceiptLicense.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetReceiptLicense.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetReceiptLicense.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetReceiptLicense> toCacheModel() {
		return _assetReceiptLicense.toCacheModel();
	}

	@Override
	public int compareTo(AssetReceiptLicense assetReceiptLicense) {
		return _assetReceiptLicense.compareTo(assetReceiptLicense);
	}

	/**
	* Returns the license type of this asset receipt license.
	*
	* @return the license type of this asset receipt license
	*/
	@Override
	public int getLicenseType() {
		return _assetReceiptLicense.getLicenseType();
	}

	/**
	* Returns the usage type of this asset receipt license.
	*
	* @return the usage type of this asset receipt license
	*/
	@Override
	public int getUsageType() {
		return _assetReceiptLicense.getUsageType();
	}

	@Override
	public int hashCode() {
		return _assetReceiptLicense.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetReceiptLicense.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssetReceiptLicenseWrapper((AssetReceiptLicense)_assetReceiptLicense.clone());
	}

	/**
	* Returns the product ID of this asset receipt license.
	*
	* @return the product ID of this asset receipt license
	*/
	@Override
	public java.lang.String getProductId() {
		return _assetReceiptLicense.getProductId();
	}

	/**
	* Returns the user name of this asset receipt license.
	*
	* @return the user name of this asset receipt license
	*/
	@Override
	public java.lang.String getUserName() {
		return _assetReceiptLicense.getUserName();
	}

	/**
	* Returns the user uuid of this asset receipt license.
	*
	* @return the user uuid of this asset receipt license
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _assetReceiptLicense.getUserUuid();
	}

	/**
	* Returns the uuid of this asset receipt license.
	*
	* @return the uuid of this asset receipt license
	*/
	@Override
	public java.lang.String getUuid() {
		return _assetReceiptLicense.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _assetReceiptLicense.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetReceiptLicense.toXmlString();
	}

	/**
	* Returns the create date of this asset receipt license.
	*
	* @return the create date of this asset receipt license
	*/
	@Override
	public Date getCreateDate() {
		return _assetReceiptLicense.getCreateDate();
	}

	/**
	* Returns the end date of this asset receipt license.
	*
	* @return the end date of this asset receipt license
	*/
	@Override
	public Date getEndDate() {
		return _assetReceiptLicense.getEndDate();
	}

	/**
	* Returns the modified date of this asset receipt license.
	*
	* @return the modified date of this asset receipt license
	*/
	@Override
	public Date getModifiedDate() {
		return _assetReceiptLicense.getModifiedDate();
	}

	/**
	* Returns the start date of this asset receipt license.
	*
	* @return the start date of this asset receipt license
	*/
	@Override
	public Date getStartDate() {
		return _assetReceiptLicense.getStartDate();
	}

	/**
	* Returns the asset entry ID of this asset receipt license.
	*
	* @return the asset entry ID of this asset receipt license
	*/
	@Override
	public long getAssetEntryId() {
		return _assetReceiptLicense.getAssetEntryId();
	}

	/**
	* Returns the asset license ID of this asset receipt license.
	*
	* @return the asset license ID of this asset receipt license
	*/
	@Override
	public long getAssetLicenseId() {
		return _assetReceiptLicense.getAssetLicenseId();
	}

	/**
	* Returns the asset receipt ID of this asset receipt license.
	*
	* @return the asset receipt ID of this asset receipt license
	*/
	@Override
	public long getAssetReceiptId() {
		return _assetReceiptLicense.getAssetReceiptId();
	}

	/**
	* Returns the asset receipt license ID of this asset receipt license.
	*
	* @return the asset receipt license ID of this asset receipt license
	*/
	@Override
	public long getAssetReceiptLicenseId() {
		return _assetReceiptLicense.getAssetReceiptLicenseId();
	}

	/**
	* Returns the license lifetime of this asset receipt license.
	*
	* @return the license lifetime of this asset receipt license
	*/
	@Override
	public long getLicenseLifetime() {
		return _assetReceiptLicense.getLicenseLifetime();
	}

	/**
	* Returns the license type allotment of this asset receipt license.
	*
	* @return the license type allotment of this asset receipt license
	*/
	@Override
	public long getLicenseTypeAllotment() {
		return _assetReceiptLicense.getLicenseTypeAllotment();
	}

	/**
	* Returns the owner class name ID of this asset receipt license.
	*
	* @return the owner class name ID of this asset receipt license
	*/
	@Override
	public long getOwnerClassNameId() {
		return _assetReceiptLicense.getOwnerClassNameId();
	}

	/**
	* Returns the owner class pk of this asset receipt license.
	*
	* @return the owner class pk of this asset receipt license
	*/
	@Override
	public long getOwnerClassPK() {
		return _assetReceiptLicense.getOwnerClassPK();
	}

	/**
	* Returns the primary key of this asset receipt license.
	*
	* @return the primary key of this asset receipt license
	*/
	@Override
	public long getPrimaryKey() {
		return _assetReceiptLicense.getPrimaryKey();
	}

	/**
	* Returns the product class name ID of this asset receipt license.
	*
	* @return the product class name ID of this asset receipt license
	*/
	@Override
	public long getProductClassNameId() {
		return _assetReceiptLicense.getProductClassNameId();
	}

	/**
	* Returns the product class pk of this asset receipt license.
	*
	* @return the product class pk of this asset receipt license
	*/
	@Override
	public long getProductClassPK() {
		return _assetReceiptLicense.getProductClassPK();
	}

	/**
	* Returns the user ID of this asset receipt license.
	*
	* @return the user ID of this asset receipt license
	*/
	@Override
	public long getUserId() {
		return _assetReceiptLicense.getUserId();
	}

	@Override
	public void persist() {
		_assetReceiptLicense.persist();
	}

	/**
	* Sets the asset entry ID of this asset receipt license.
	*
	* @param assetEntryId the asset entry ID of this asset receipt license
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetReceiptLicense.setAssetEntryId(assetEntryId);
	}

	/**
	* Sets the asset license ID of this asset receipt license.
	*
	* @param assetLicenseId the asset license ID of this asset receipt license
	*/
	@Override
	public void setAssetLicenseId(long assetLicenseId) {
		_assetReceiptLicense.setAssetLicenseId(assetLicenseId);
	}

	/**
	* Sets the asset receipt ID of this asset receipt license.
	*
	* @param assetReceiptId the asset receipt ID of this asset receipt license
	*/
	@Override
	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptLicense.setAssetReceiptId(assetReceiptId);
	}

	/**
	* Sets the asset receipt license ID of this asset receipt license.
	*
	* @param assetReceiptLicenseId the asset receipt license ID of this asset receipt license
	*/
	@Override
	public void setAssetReceiptLicenseId(long assetReceiptLicenseId) {
		_assetReceiptLicense.setAssetReceiptLicenseId(assetReceiptLicenseId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetReceiptLicense.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this asset receipt license.
	*
	* @param createDate the create date of this asset receipt license
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_assetReceiptLicense.setCreateDate(createDate);
	}

	/**
	* Sets the end date of this asset receipt license.
	*
	* @param endDate the end date of this asset receipt license
	*/
	@Override
	public void setEndDate(Date endDate) {
		_assetReceiptLicense.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetReceiptLicense.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetReceiptLicense.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetReceiptLicense.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the license lifetime of this asset receipt license.
	*
	* @param licenseLifetime the license lifetime of this asset receipt license
	*/
	@Override
	public void setLicenseLifetime(long licenseLifetime) {
		_assetReceiptLicense.setLicenseLifetime(licenseLifetime);
	}

	/**
	* Sets the license type of this asset receipt license.
	*
	* @param licenseType the license type of this asset receipt license
	*/
	@Override
	public void setLicenseType(int licenseType) {
		_assetReceiptLicense.setLicenseType(licenseType);
	}

	/**
	* Sets the license type allotment of this asset receipt license.
	*
	* @param licenseTypeAllotment the license type allotment of this asset receipt license
	*/
	@Override
	public void setLicenseTypeAllotment(long licenseTypeAllotment) {
		_assetReceiptLicense.setLicenseTypeAllotment(licenseTypeAllotment);
	}

	/**
	* Sets the modified date of this asset receipt license.
	*
	* @param modifiedDate the modified date of this asset receipt license
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_assetReceiptLicense.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_assetReceiptLicense.setNew(n);
	}

	/**
	* Sets the owner class name ID of this asset receipt license.
	*
	* @param ownerClassNameId the owner class name ID of this asset receipt license
	*/
	@Override
	public void setOwnerClassNameId(long ownerClassNameId) {
		_assetReceiptLicense.setOwnerClassNameId(ownerClassNameId);
	}

	/**
	* Sets the owner class pk of this asset receipt license.
	*
	* @param ownerClassPK the owner class pk of this asset receipt license
	*/
	@Override
	public void setOwnerClassPK(long ownerClassPK) {
		_assetReceiptLicense.setOwnerClassPK(ownerClassPK);
	}

	/**
	* Sets the primary key of this asset receipt license.
	*
	* @param primaryKey the primary key of this asset receipt license
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetReceiptLicense.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetReceiptLicense.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product class name ID of this asset receipt license.
	*
	* @param productClassNameId the product class name ID of this asset receipt license
	*/
	@Override
	public void setProductClassNameId(long productClassNameId) {
		_assetReceiptLicense.setProductClassNameId(productClassNameId);
	}

	/**
	* Sets the product class pk of this asset receipt license.
	*
	* @param productClassPK the product class pk of this asset receipt license
	*/
	@Override
	public void setProductClassPK(long productClassPK) {
		_assetReceiptLicense.setProductClassPK(productClassPK);
	}

	/**
	* Sets the product ID of this asset receipt license.
	*
	* @param productId the product ID of this asset receipt license
	*/
	@Override
	public void setProductId(java.lang.String productId) {
		_assetReceiptLicense.setProductId(productId);
	}

	/**
	* Sets the start date of this asset receipt license.
	*
	* @param startDate the start date of this asset receipt license
	*/
	@Override
	public void setStartDate(Date startDate) {
		_assetReceiptLicense.setStartDate(startDate);
	}

	/**
	* Sets the usage type of this asset receipt license.
	*
	* @param usageType the usage type of this asset receipt license
	*/
	@Override
	public void setUsageType(int usageType) {
		_assetReceiptLicense.setUsageType(usageType);
	}

	/**
	* Sets the user ID of this asset receipt license.
	*
	* @param userId the user ID of this asset receipt license
	*/
	@Override
	public void setUserId(long userId) {
		_assetReceiptLicense.setUserId(userId);
	}

	/**
	* Sets the user name of this asset receipt license.
	*
	* @param userName the user name of this asset receipt license
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_assetReceiptLicense.setUserName(userName);
	}

	/**
	* Sets the user uuid of this asset receipt license.
	*
	* @param userUuid the user uuid of this asset receipt license
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_assetReceiptLicense.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this asset receipt license.
	*
	* @param uuid the uuid of this asset receipt license
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_assetReceiptLicense.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetReceiptLicenseWrapper)) {
			return false;
		}

		AssetReceiptLicenseWrapper assetReceiptLicenseWrapper = (AssetReceiptLicenseWrapper)obj;

		if (Objects.equals(_assetReceiptLicense,
					assetReceiptLicenseWrapper._assetReceiptLicense)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetReceiptLicense getWrappedModel() {
		return _assetReceiptLicense;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetReceiptLicense.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetReceiptLicense.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetReceiptLicense.resetOriginalValues();
	}

	private final AssetReceiptLicense _assetReceiptLicense;
}