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
 * This class is a wrapper for {@link AssetReceiptSupport}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptSupport
 * @generated
 */
public class AssetReceiptSupportWrapper implements AssetReceiptSupport,
	ModelWrapper<AssetReceiptSupport> {
	public AssetReceiptSupportWrapper(AssetReceiptSupport assetReceiptSupport) {
		_assetReceiptSupport = assetReceiptSupport;
	}

	public Class<?> getModelClass() {
		return AssetReceiptSupport.class;
	}

	public String getModelClassName() {
		return AssetReceiptSupport.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("assetReceiptSupportId", getAssetReceiptSupportId());
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
		attributes.put("supportLifetime", getSupportLifetime());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long assetReceiptSupportId = (Long)attributes.get(
				"assetReceiptSupportId");

		if (assetReceiptSupportId != null) {
			setAssetReceiptSupportId(assetReceiptSupportId);
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

		Long supportLifetime = (Long)attributes.get("supportLifetime");

		if (supportLifetime != null) {
			setSupportLifetime(supportLifetime);
		}
	}

	/**
	* Returns the primary key of this asset receipt support.
	*
	* @return the primary key of this asset receipt support
	*/
	public long getPrimaryKey() {
		return _assetReceiptSupport.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset receipt support.
	*
	* @param primaryKey the primary key of this asset receipt support
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetReceiptSupport.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this asset receipt support.
	*
	* @return the uuid of this asset receipt support
	*/
	public java.lang.String getUuid() {
		return _assetReceiptSupport.getUuid();
	}

	/**
	* Sets the uuid of this asset receipt support.
	*
	* @param uuid the uuid of this asset receipt support
	*/
	public void setUuid(java.lang.String uuid) {
		_assetReceiptSupport.setUuid(uuid);
	}

	/**
	* Returns the asset receipt support ID of this asset receipt support.
	*
	* @return the asset receipt support ID of this asset receipt support
	*/
	public long getAssetReceiptSupportId() {
		return _assetReceiptSupport.getAssetReceiptSupportId();
	}

	/**
	* Sets the asset receipt support ID of this asset receipt support.
	*
	* @param assetReceiptSupportId the asset receipt support ID of this asset receipt support
	*/
	public void setAssetReceiptSupportId(long assetReceiptSupportId) {
		_assetReceiptSupport.setAssetReceiptSupportId(assetReceiptSupportId);
	}

	/**
	* Returns the user ID of this asset receipt support.
	*
	* @return the user ID of this asset receipt support
	*/
	public long getUserId() {
		return _assetReceiptSupport.getUserId();
	}

	/**
	* Sets the user ID of this asset receipt support.
	*
	* @param userId the user ID of this asset receipt support
	*/
	public void setUserId(long userId) {
		_assetReceiptSupport.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset receipt support.
	*
	* @return the user uuid of this asset receipt support
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptSupport.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset receipt support.
	*
	* @param userUuid the user uuid of this asset receipt support
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetReceiptSupport.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset receipt support.
	*
	* @return the user name of this asset receipt support
	*/
	public java.lang.String getUserName() {
		return _assetReceiptSupport.getUserName();
	}

	/**
	* Sets the user name of this asset receipt support.
	*
	* @param userName the user name of this asset receipt support
	*/
	public void setUserName(java.lang.String userName) {
		_assetReceiptSupport.setUserName(userName);
	}

	/**
	* Returns the create date of this asset receipt support.
	*
	* @return the create date of this asset receipt support
	*/
	public java.util.Date getCreateDate() {
		return _assetReceiptSupport.getCreateDate();
	}

	/**
	* Sets the create date of this asset receipt support.
	*
	* @param createDate the create date of this asset receipt support
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetReceiptSupport.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset receipt support.
	*
	* @return the modified date of this asset receipt support
	*/
	public java.util.Date getModifiedDate() {
		return _assetReceiptSupport.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset receipt support.
	*
	* @param modifiedDate the modified date of this asset receipt support
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetReceiptSupport.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the asset receipt ID of this asset receipt support.
	*
	* @return the asset receipt ID of this asset receipt support
	*/
	public long getAssetReceiptId() {
		return _assetReceiptSupport.getAssetReceiptId();
	}

	/**
	* Sets the asset receipt ID of this asset receipt support.
	*
	* @param assetReceiptId the asset receipt ID of this asset receipt support
	*/
	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptSupport.setAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns the asset license ID of this asset receipt support.
	*
	* @return the asset license ID of this asset receipt support
	*/
	public long getAssetLicenseId() {
		return _assetReceiptSupport.getAssetLicenseId();
	}

	/**
	* Sets the asset license ID of this asset receipt support.
	*
	* @param assetLicenseId the asset license ID of this asset receipt support
	*/
	public void setAssetLicenseId(long assetLicenseId) {
		_assetReceiptSupport.setAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns the asset entry ID of this asset receipt support.
	*
	* @return the asset entry ID of this asset receipt support
	*/
	public long getAssetEntryId() {
		return _assetReceiptSupport.getAssetEntryId();
	}

	/**
	* Sets the asset entry ID of this asset receipt support.
	*
	* @param assetEntryId the asset entry ID of this asset receipt support
	*/
	public void setAssetEntryId(long assetEntryId) {
		_assetReceiptSupport.setAssetEntryId(assetEntryId);
	}

	/**
	* Returns the owner class name ID of this asset receipt support.
	*
	* @return the owner class name ID of this asset receipt support
	*/
	public long getOwnerClassNameId() {
		return _assetReceiptSupport.getOwnerClassNameId();
	}

	/**
	* Sets the owner class name ID of this asset receipt support.
	*
	* @param ownerClassNameId the owner class name ID of this asset receipt support
	*/
	public void setOwnerClassNameId(long ownerClassNameId) {
		_assetReceiptSupport.setOwnerClassNameId(ownerClassNameId);
	}

	/**
	* Returns the owner class p k of this asset receipt support.
	*
	* @return the owner class p k of this asset receipt support
	*/
	public long getOwnerClassPK() {
		return _assetReceiptSupport.getOwnerClassPK();
	}

	/**
	* Sets the owner class p k of this asset receipt support.
	*
	* @param ownerClassPK the owner class p k of this asset receipt support
	*/
	public void setOwnerClassPK(long ownerClassPK) {
		_assetReceiptSupport.setOwnerClassPK(ownerClassPK);
	}

	/**
	* Returns the product class name ID of this asset receipt support.
	*
	* @return the product class name ID of this asset receipt support
	*/
	public long getProductClassNameId() {
		return _assetReceiptSupport.getProductClassNameId();
	}

	/**
	* Sets the product class name ID of this asset receipt support.
	*
	* @param productClassNameId the product class name ID of this asset receipt support
	*/
	public void setProductClassNameId(long productClassNameId) {
		_assetReceiptSupport.setProductClassNameId(productClassNameId);
	}

	/**
	* Returns the product class p k of this asset receipt support.
	*
	* @return the product class p k of this asset receipt support
	*/
	public long getProductClassPK() {
		return _assetReceiptSupport.getProductClassPK();
	}

	/**
	* Sets the product class p k of this asset receipt support.
	*
	* @param productClassPK the product class p k of this asset receipt support
	*/
	public void setProductClassPK(long productClassPK) {
		_assetReceiptSupport.setProductClassPK(productClassPK);
	}

	/**
	* Returns the product ID of this asset receipt support.
	*
	* @return the product ID of this asset receipt support
	*/
	public java.lang.String getProductId() {
		return _assetReceiptSupport.getProductId();
	}

	/**
	* Sets the product ID of this asset receipt support.
	*
	* @param productId the product ID of this asset receipt support
	*/
	public void setProductId(java.lang.String productId) {
		_assetReceiptSupport.setProductId(productId);
	}

	/**
	* Returns the start date of this asset receipt support.
	*
	* @return the start date of this asset receipt support
	*/
	public java.util.Date getStartDate() {
		return _assetReceiptSupport.getStartDate();
	}

	/**
	* Sets the start date of this asset receipt support.
	*
	* @param startDate the start date of this asset receipt support
	*/
	public void setStartDate(java.util.Date startDate) {
		_assetReceiptSupport.setStartDate(startDate);
	}

	/**
	* Returns the end date of this asset receipt support.
	*
	* @return the end date of this asset receipt support
	*/
	public java.util.Date getEndDate() {
		return _assetReceiptSupport.getEndDate();
	}

	/**
	* Sets the end date of this asset receipt support.
	*
	* @param endDate the end date of this asset receipt support
	*/
	public void setEndDate(java.util.Date endDate) {
		_assetReceiptSupport.setEndDate(endDate);
	}

	/**
	* Returns the usage type of this asset receipt support.
	*
	* @return the usage type of this asset receipt support
	*/
	public int getUsageType() {
		return _assetReceiptSupport.getUsageType();
	}

	/**
	* Sets the usage type of this asset receipt support.
	*
	* @param usageType the usage type of this asset receipt support
	*/
	public void setUsageType(int usageType) {
		_assetReceiptSupport.setUsageType(usageType);
	}

	/**
	* Returns the support lifetime of this asset receipt support.
	*
	* @return the support lifetime of this asset receipt support
	*/
	public long getSupportLifetime() {
		return _assetReceiptSupport.getSupportLifetime();
	}

	/**
	* Sets the support lifetime of this asset receipt support.
	*
	* @param supportLifetime the support lifetime of this asset receipt support
	*/
	public void setSupportLifetime(long supportLifetime) {
		_assetReceiptSupport.setSupportLifetime(supportLifetime);
	}

	public boolean isNew() {
		return _assetReceiptSupport.isNew();
	}

	public void setNew(boolean n) {
		_assetReceiptSupport.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetReceiptSupport.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetReceiptSupport.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetReceiptSupport.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetReceiptSupport.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetReceiptSupport.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetReceiptSupport.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetReceiptSupport.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetReceiptSupportWrapper((AssetReceiptSupport)_assetReceiptSupport.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport) {
		return _assetReceiptSupport.compareTo(assetReceiptSupport);
	}

	@Override
	public int hashCode() {
		return _assetReceiptSupport.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetReceiptSupport> toCacheModel() {
		return _assetReceiptSupport.toCacheModel();
	}

	public com.liferay.osb.model.AssetReceiptSupport toEscapedModel() {
		return new AssetReceiptSupportWrapper(_assetReceiptSupport.toEscapedModel());
	}

	public com.liferay.osb.model.AssetReceiptSupport toUnescapedModel() {
		return new AssetReceiptSupportWrapper(_assetReceiptSupport.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetReceiptSupport.toString();
	}

	public java.lang.String toXmlString() {
		return _assetReceiptSupport.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetReceiptSupport.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetReceiptSupportWrapper)) {
			return false;
		}

		AssetReceiptSupportWrapper assetReceiptSupportWrapper = (AssetReceiptSupportWrapper)obj;

		if (Validator.equals(_assetReceiptSupport,
					assetReceiptSupportWrapper._assetReceiptSupport)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetReceiptSupport getWrappedAssetReceiptSupport() {
		return _assetReceiptSupport;
	}

	public AssetReceiptSupport getWrappedModel() {
		return _assetReceiptSupport;
	}

	public void resetOriginalValues() {
		_assetReceiptSupport.resetOriginalValues();
	}

	private AssetReceiptSupport _assetReceiptSupport;
}