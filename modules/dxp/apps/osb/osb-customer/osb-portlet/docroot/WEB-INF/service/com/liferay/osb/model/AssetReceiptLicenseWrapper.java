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
 * This class is a wrapper for {@link AssetReceiptLicense}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptLicense
 * @generated
 */
public class AssetReceiptLicenseWrapper implements AssetReceiptLicense,
	ModelWrapper<AssetReceiptLicense> {
	public AssetReceiptLicenseWrapper(AssetReceiptLicense assetReceiptLicense) {
		_assetReceiptLicense = assetReceiptLicense;
	}

	public Class<?> getModelClass() {
		return AssetReceiptLicense.class;
	}

	public String getModelClassName() {
		return AssetReceiptLicense.class.getName();
	}

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

	/**
	* Returns the primary key of this asset receipt license.
	*
	* @return the primary key of this asset receipt license
	*/
	public long getPrimaryKey() {
		return _assetReceiptLicense.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset receipt license.
	*
	* @param primaryKey the primary key of this asset receipt license
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetReceiptLicense.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this asset receipt license.
	*
	* @return the uuid of this asset receipt license
	*/
	public java.lang.String getUuid() {
		return _assetReceiptLicense.getUuid();
	}

	/**
	* Sets the uuid of this asset receipt license.
	*
	* @param uuid the uuid of this asset receipt license
	*/
	public void setUuid(java.lang.String uuid) {
		_assetReceiptLicense.setUuid(uuid);
	}

	/**
	* Returns the asset receipt license ID of this asset receipt license.
	*
	* @return the asset receipt license ID of this asset receipt license
	*/
	public long getAssetReceiptLicenseId() {
		return _assetReceiptLicense.getAssetReceiptLicenseId();
	}

	/**
	* Sets the asset receipt license ID of this asset receipt license.
	*
	* @param assetReceiptLicenseId the asset receipt license ID of this asset receipt license
	*/
	public void setAssetReceiptLicenseId(long assetReceiptLicenseId) {
		_assetReceiptLicense.setAssetReceiptLicenseId(assetReceiptLicenseId);
	}

	/**
	* Returns the user ID of this asset receipt license.
	*
	* @return the user ID of this asset receipt license
	*/
	public long getUserId() {
		return _assetReceiptLicense.getUserId();
	}

	/**
	* Sets the user ID of this asset receipt license.
	*
	* @param userId the user ID of this asset receipt license
	*/
	public void setUserId(long userId) {
		_assetReceiptLicense.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset receipt license.
	*
	* @return the user uuid of this asset receipt license
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicense.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset receipt license.
	*
	* @param userUuid the user uuid of this asset receipt license
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetReceiptLicense.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset receipt license.
	*
	* @return the user name of this asset receipt license
	*/
	public java.lang.String getUserName() {
		return _assetReceiptLicense.getUserName();
	}

	/**
	* Sets the user name of this asset receipt license.
	*
	* @param userName the user name of this asset receipt license
	*/
	public void setUserName(java.lang.String userName) {
		_assetReceiptLicense.setUserName(userName);
	}

	/**
	* Returns the create date of this asset receipt license.
	*
	* @return the create date of this asset receipt license
	*/
	public java.util.Date getCreateDate() {
		return _assetReceiptLicense.getCreateDate();
	}

	/**
	* Sets the create date of this asset receipt license.
	*
	* @param createDate the create date of this asset receipt license
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetReceiptLicense.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset receipt license.
	*
	* @return the modified date of this asset receipt license
	*/
	public java.util.Date getModifiedDate() {
		return _assetReceiptLicense.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset receipt license.
	*
	* @param modifiedDate the modified date of this asset receipt license
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetReceiptLicense.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the asset receipt ID of this asset receipt license.
	*
	* @return the asset receipt ID of this asset receipt license
	*/
	public long getAssetReceiptId() {
		return _assetReceiptLicense.getAssetReceiptId();
	}

	/**
	* Sets the asset receipt ID of this asset receipt license.
	*
	* @param assetReceiptId the asset receipt ID of this asset receipt license
	*/
	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptLicense.setAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns the asset license ID of this asset receipt license.
	*
	* @return the asset license ID of this asset receipt license
	*/
	public long getAssetLicenseId() {
		return _assetReceiptLicense.getAssetLicenseId();
	}

	/**
	* Sets the asset license ID of this asset receipt license.
	*
	* @param assetLicenseId the asset license ID of this asset receipt license
	*/
	public void setAssetLicenseId(long assetLicenseId) {
		_assetReceiptLicense.setAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns the asset entry ID of this asset receipt license.
	*
	* @return the asset entry ID of this asset receipt license
	*/
	public long getAssetEntryId() {
		return _assetReceiptLicense.getAssetEntryId();
	}

	/**
	* Sets the asset entry ID of this asset receipt license.
	*
	* @param assetEntryId the asset entry ID of this asset receipt license
	*/
	public void setAssetEntryId(long assetEntryId) {
		_assetReceiptLicense.setAssetEntryId(assetEntryId);
	}

	/**
	* Returns the owner class name ID of this asset receipt license.
	*
	* @return the owner class name ID of this asset receipt license
	*/
	public long getOwnerClassNameId() {
		return _assetReceiptLicense.getOwnerClassNameId();
	}

	/**
	* Sets the owner class name ID of this asset receipt license.
	*
	* @param ownerClassNameId the owner class name ID of this asset receipt license
	*/
	public void setOwnerClassNameId(long ownerClassNameId) {
		_assetReceiptLicense.setOwnerClassNameId(ownerClassNameId);
	}

	/**
	* Returns the owner class p k of this asset receipt license.
	*
	* @return the owner class p k of this asset receipt license
	*/
	public long getOwnerClassPK() {
		return _assetReceiptLicense.getOwnerClassPK();
	}

	/**
	* Sets the owner class p k of this asset receipt license.
	*
	* @param ownerClassPK the owner class p k of this asset receipt license
	*/
	public void setOwnerClassPK(long ownerClassPK) {
		_assetReceiptLicense.setOwnerClassPK(ownerClassPK);
	}

	/**
	* Returns the product class name ID of this asset receipt license.
	*
	* @return the product class name ID of this asset receipt license
	*/
	public long getProductClassNameId() {
		return _assetReceiptLicense.getProductClassNameId();
	}

	/**
	* Sets the product class name ID of this asset receipt license.
	*
	* @param productClassNameId the product class name ID of this asset receipt license
	*/
	public void setProductClassNameId(long productClassNameId) {
		_assetReceiptLicense.setProductClassNameId(productClassNameId);
	}

	/**
	* Returns the product class p k of this asset receipt license.
	*
	* @return the product class p k of this asset receipt license
	*/
	public long getProductClassPK() {
		return _assetReceiptLicense.getProductClassPK();
	}

	/**
	* Sets the product class p k of this asset receipt license.
	*
	* @param productClassPK the product class p k of this asset receipt license
	*/
	public void setProductClassPK(long productClassPK) {
		_assetReceiptLicense.setProductClassPK(productClassPK);
	}

	/**
	* Returns the product ID of this asset receipt license.
	*
	* @return the product ID of this asset receipt license
	*/
	public java.lang.String getProductId() {
		return _assetReceiptLicense.getProductId();
	}

	/**
	* Sets the product ID of this asset receipt license.
	*
	* @param productId the product ID of this asset receipt license
	*/
	public void setProductId(java.lang.String productId) {
		_assetReceiptLicense.setProductId(productId);
	}

	/**
	* Returns the start date of this asset receipt license.
	*
	* @return the start date of this asset receipt license
	*/
	public java.util.Date getStartDate() {
		return _assetReceiptLicense.getStartDate();
	}

	/**
	* Sets the start date of this asset receipt license.
	*
	* @param startDate the start date of this asset receipt license
	*/
	public void setStartDate(java.util.Date startDate) {
		_assetReceiptLicense.setStartDate(startDate);
	}

	/**
	* Returns the end date of this asset receipt license.
	*
	* @return the end date of this asset receipt license
	*/
	public java.util.Date getEndDate() {
		return _assetReceiptLicense.getEndDate();
	}

	/**
	* Sets the end date of this asset receipt license.
	*
	* @param endDate the end date of this asset receipt license
	*/
	public void setEndDate(java.util.Date endDate) {
		_assetReceiptLicense.setEndDate(endDate);
	}

	/**
	* Returns the usage type of this asset receipt license.
	*
	* @return the usage type of this asset receipt license
	*/
	public int getUsageType() {
		return _assetReceiptLicense.getUsageType();
	}

	/**
	* Sets the usage type of this asset receipt license.
	*
	* @param usageType the usage type of this asset receipt license
	*/
	public void setUsageType(int usageType) {
		_assetReceiptLicense.setUsageType(usageType);
	}

	/**
	* Returns the license type of this asset receipt license.
	*
	* @return the license type of this asset receipt license
	*/
	public int getLicenseType() {
		return _assetReceiptLicense.getLicenseType();
	}

	/**
	* Sets the license type of this asset receipt license.
	*
	* @param licenseType the license type of this asset receipt license
	*/
	public void setLicenseType(int licenseType) {
		_assetReceiptLicense.setLicenseType(licenseType);
	}

	/**
	* Returns the license type allotment of this asset receipt license.
	*
	* @return the license type allotment of this asset receipt license
	*/
	public long getLicenseTypeAllotment() {
		return _assetReceiptLicense.getLicenseTypeAllotment();
	}

	/**
	* Sets the license type allotment of this asset receipt license.
	*
	* @param licenseTypeAllotment the license type allotment of this asset receipt license
	*/
	public void setLicenseTypeAllotment(long licenseTypeAllotment) {
		_assetReceiptLicense.setLicenseTypeAllotment(licenseTypeAllotment);
	}

	/**
	* Returns the license lifetime of this asset receipt license.
	*
	* @return the license lifetime of this asset receipt license
	*/
	public long getLicenseLifetime() {
		return _assetReceiptLicense.getLicenseLifetime();
	}

	/**
	* Sets the license lifetime of this asset receipt license.
	*
	* @param licenseLifetime the license lifetime of this asset receipt license
	*/
	public void setLicenseLifetime(long licenseLifetime) {
		_assetReceiptLicense.setLicenseLifetime(licenseLifetime);
	}

	public boolean isNew() {
		return _assetReceiptLicense.isNew();
	}

	public void setNew(boolean n) {
		_assetReceiptLicense.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetReceiptLicense.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetReceiptLicense.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetReceiptLicense.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetReceiptLicense.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetReceiptLicense.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetReceiptLicense.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetReceiptLicense.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetReceiptLicenseWrapper((AssetReceiptLicense)_assetReceiptLicense.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense) {
		return _assetReceiptLicense.compareTo(assetReceiptLicense);
	}

	@Override
	public int hashCode() {
		return _assetReceiptLicense.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetReceiptLicense> toCacheModel() {
		return _assetReceiptLicense.toCacheModel();
	}

	public com.liferay.osb.model.AssetReceiptLicense toEscapedModel() {
		return new AssetReceiptLicenseWrapper(_assetReceiptLicense.toEscapedModel());
	}

	public com.liferay.osb.model.AssetReceiptLicense toUnescapedModel() {
		return new AssetReceiptLicenseWrapper(_assetReceiptLicense.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetReceiptLicense.toString();
	}

	public java.lang.String toXmlString() {
		return _assetReceiptLicense.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetReceiptLicense.persist();
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicense.getAssetEntry();
	}

	public com.liferay.osb.model.AssetLicense getAssetLicense()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicense.getAssetLicense();
	}

	public com.liferay.osb.model.AssetReceipt getAssetReceipt()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicense.getAssetReceipt();
	}

	public int getAvailableLicenseKeyCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicense.getAvailableLicenseKeyCount();
	}

	public long getMaxUsers() {
		return _assetReceiptLicense.getMaxUsers();
	}

	public long getRemainingDays() {
		return _assetReceiptLicense.getRemainingDays();
	}

	public long getRemainingTime() {
		return _assetReceiptLicense.getRemainingTime();
	}

	public int getServers() {
		return _assetReceiptLicense.getServers();
	}

	public java.lang.String getUsageTypeLabel() {
		return _assetReceiptLicense.getUsageTypeLabel();
	}

	public boolean hasAvailableLicenseKeys()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicense.hasAvailableLicenseKeys();
	}

	public boolean hasUnlimitedServers() {
		return _assetReceiptLicense.hasUnlimitedServers();
	}

	public boolean isExpired() {
		return _assetReceiptLicense.isExpired();
	}

	public boolean isExpiring() {
		return _assetReceiptLicense.isExpiring();
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

		if (Validator.equals(_assetReceiptLicense,
					assetReceiptLicenseWrapper._assetReceiptLicense)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetReceiptLicense getWrappedAssetReceiptLicense() {
		return _assetReceiptLicense;
	}

	public AssetReceiptLicense getWrappedModel() {
		return _assetReceiptLicense;
	}

	public void resetOriginalValues() {
		_assetReceiptLicense.resetOriginalValues();
	}

	private AssetReceiptLicense _assetReceiptLicense;
}