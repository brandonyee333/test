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
 * This class is a wrapper for {@link AssetReceipt}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceipt
 * @generated
 */
public class AssetReceiptWrapper implements AssetReceipt,
	ModelWrapper<AssetReceipt> {
	public AssetReceiptWrapper(AssetReceipt assetReceipt) {
		_assetReceipt = assetReceipt;
	}

	public Class<?> getModelClass() {
		return AssetReceipt.class;
	}

	public String getModelClassName() {
		return AssetReceipt.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetReceiptId", getAssetReceiptId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("ownerClassNameId", getOwnerClassNameId());
		attributes.put("ownerClassPK", getOwnerClassPK());
		attributes.put("legalEntityName", getLegalEntityName());
		attributes.put("productClassNameId", getProductClassNameId());
		attributes.put("productClassPK", getProductClassPK());
		attributes.put("type", getType());
		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("actualPrice", getActualPrice());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetReceiptId = (Long)attributes.get("assetReceiptId");

		if (assetReceiptId != null) {
			setAssetReceiptId(assetReceiptId);
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

		String legalEntityName = (String)attributes.get("legalEntityName");

		if (legalEntityName != null) {
			setLegalEntityName(legalEntityName);
		}

		Long productClassNameId = (Long)attributes.get("productClassNameId");

		if (productClassNameId != null) {
			setProductClassNameId(productClassNameId);
		}

		Long productClassPK = (Long)attributes.get("productClassPK");

		if (productClassPK != null) {
			setProductClassPK(productClassPK);
		}

		Long type = (Long)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Double actualPrice = (Double)attributes.get("actualPrice");

		if (actualPrice != null) {
			setActualPrice(actualPrice);
		}
	}

	/**
	* Returns the primary key of this asset receipt.
	*
	* @return the primary key of this asset receipt
	*/
	public long getPrimaryKey() {
		return _assetReceipt.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset receipt.
	*
	* @param primaryKey the primary key of this asset receipt
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetReceipt.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset receipt ID of this asset receipt.
	*
	* @return the asset receipt ID of this asset receipt
	*/
	public long getAssetReceiptId() {
		return _assetReceipt.getAssetReceiptId();
	}

	/**
	* Sets the asset receipt ID of this asset receipt.
	*
	* @param assetReceiptId the asset receipt ID of this asset receipt
	*/
	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceipt.setAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns the user ID of this asset receipt.
	*
	* @return the user ID of this asset receipt
	*/
	public long getUserId() {
		return _assetReceipt.getUserId();
	}

	/**
	* Sets the user ID of this asset receipt.
	*
	* @param userId the user ID of this asset receipt
	*/
	public void setUserId(long userId) {
		_assetReceipt.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset receipt.
	*
	* @return the user uuid of this asset receipt
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset receipt.
	*
	* @param userUuid the user uuid of this asset receipt
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetReceipt.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset receipt.
	*
	* @return the user name of this asset receipt
	*/
	public java.lang.String getUserName() {
		return _assetReceipt.getUserName();
	}

	/**
	* Sets the user name of this asset receipt.
	*
	* @param userName the user name of this asset receipt
	*/
	public void setUserName(java.lang.String userName) {
		_assetReceipt.setUserName(userName);
	}

	/**
	* Returns the create date of this asset receipt.
	*
	* @return the create date of this asset receipt
	*/
	public java.util.Date getCreateDate() {
		return _assetReceipt.getCreateDate();
	}

	/**
	* Sets the create date of this asset receipt.
	*
	* @param createDate the create date of this asset receipt
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetReceipt.setCreateDate(createDate);
	}

	/**
	* Returns the asset entry ID of this asset receipt.
	*
	* @return the asset entry ID of this asset receipt
	*/
	public long getAssetEntryId() {
		return _assetReceipt.getAssetEntryId();
	}

	/**
	* Sets the asset entry ID of this asset receipt.
	*
	* @param assetEntryId the asset entry ID of this asset receipt
	*/
	public void setAssetEntryId(long assetEntryId) {
		_assetReceipt.setAssetEntryId(assetEntryId);
	}

	/**
	* Returns the owner class name ID of this asset receipt.
	*
	* @return the owner class name ID of this asset receipt
	*/
	public long getOwnerClassNameId() {
		return _assetReceipt.getOwnerClassNameId();
	}

	/**
	* Sets the owner class name ID of this asset receipt.
	*
	* @param ownerClassNameId the owner class name ID of this asset receipt
	*/
	public void setOwnerClassNameId(long ownerClassNameId) {
		_assetReceipt.setOwnerClassNameId(ownerClassNameId);
	}

	/**
	* Returns the owner class p k of this asset receipt.
	*
	* @return the owner class p k of this asset receipt
	*/
	public long getOwnerClassPK() {
		return _assetReceipt.getOwnerClassPK();
	}

	/**
	* Sets the owner class p k of this asset receipt.
	*
	* @param ownerClassPK the owner class p k of this asset receipt
	*/
	public void setOwnerClassPK(long ownerClassPK) {
		_assetReceipt.setOwnerClassPK(ownerClassPK);
	}

	/**
	* Returns the legal entity name of this asset receipt.
	*
	* @return the legal entity name of this asset receipt
	*/
	public java.lang.String getLegalEntityName() {
		return _assetReceipt.getLegalEntityName();
	}

	/**
	* Sets the legal entity name of this asset receipt.
	*
	* @param legalEntityName the legal entity name of this asset receipt
	*/
	public void setLegalEntityName(java.lang.String legalEntityName) {
		_assetReceipt.setLegalEntityName(legalEntityName);
	}

	/**
	* Returns the product class name ID of this asset receipt.
	*
	* @return the product class name ID of this asset receipt
	*/
	public long getProductClassNameId() {
		return _assetReceipt.getProductClassNameId();
	}

	/**
	* Sets the product class name ID of this asset receipt.
	*
	* @param productClassNameId the product class name ID of this asset receipt
	*/
	public void setProductClassNameId(long productClassNameId) {
		_assetReceipt.setProductClassNameId(productClassNameId);
	}

	/**
	* Returns the product class p k of this asset receipt.
	*
	* @return the product class p k of this asset receipt
	*/
	public long getProductClassPK() {
		return _assetReceipt.getProductClassPK();
	}

	/**
	* Sets the product class p k of this asset receipt.
	*
	* @param productClassPK the product class p k of this asset receipt
	*/
	public void setProductClassPK(long productClassPK) {
		_assetReceipt.setProductClassPK(productClassPK);
	}

	/**
	* Returns the type of this asset receipt.
	*
	* @return the type of this asset receipt
	*/
	public long getType() {
		return _assetReceipt.getType();
	}

	/**
	* Sets the type of this asset receipt.
	*
	* @param type the type of this asset receipt
	*/
	public void setType(long type) {
		_assetReceipt.setType(type);
	}

	/**
	* Returns the currency entry ID of this asset receipt.
	*
	* @return the currency entry ID of this asset receipt
	*/
	public long getCurrencyEntryId() {
		return _assetReceipt.getCurrencyEntryId();
	}

	/**
	* Sets the currency entry ID of this asset receipt.
	*
	* @param currencyEntryId the currency entry ID of this asset receipt
	*/
	public void setCurrencyEntryId(long currencyEntryId) {
		_assetReceipt.setCurrencyEntryId(currencyEntryId);
	}

	/**
	* Returns the actual price of this asset receipt.
	*
	* @return the actual price of this asset receipt
	*/
	public double getActualPrice() {
		return _assetReceipt.getActualPrice();
	}

	/**
	* Sets the actual price of this asset receipt.
	*
	* @param actualPrice the actual price of this asset receipt
	*/
	public void setActualPrice(double actualPrice) {
		_assetReceipt.setActualPrice(actualPrice);
	}

	public boolean isNew() {
		return _assetReceipt.isNew();
	}

	public void setNew(boolean n) {
		_assetReceipt.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetReceipt.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetReceipt.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetReceipt.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetReceipt.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetReceipt.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetReceipt.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetReceipt.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetReceiptWrapper((AssetReceipt)_assetReceipt.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetReceipt assetReceipt) {
		return _assetReceipt.compareTo(assetReceipt);
	}

	@Override
	public int hashCode() {
		return _assetReceipt.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetReceipt> toCacheModel() {
		return _assetReceipt.toCacheModel();
	}

	public com.liferay.osb.model.AssetReceipt toEscapedModel() {
		return new AssetReceiptWrapper(_assetReceipt.toEscapedModel());
	}

	public com.liferay.osb.model.AssetReceipt toUnescapedModel() {
		return new AssetReceiptWrapper(_assetReceipt.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetReceipt.toString();
	}

	public java.lang.String toXmlString() {
		return _assetReceipt.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetReceipt.persist();
	}

	public java.util.Date getActiveAssetReceiptLicensesEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getActiveAssetReceiptLicensesEndDate(usageType);
	}

	public java.util.Date getActiveAssetReceiptSupportsEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getActiveAssetReceiptSupportsEndDate(usageType);
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getAssetEntry();
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getAssetLicenses();
	}

	public java.util.Date getAssetReceiptLicensesEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getAssetReceiptLicensesEndDate(usageType);
	}

	public java.util.Date getAssetReceiptSupportsEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getAssetReceiptSupportsEndDate(usageType);
	}

	public java.lang.String getFormalLegalEntityName() {
		return _assetReceipt.getFormalLegalEntityName();
	}

	public java.lang.String getOwnerClassName() {
		return _assetReceipt.getOwnerClassName();
	}

	public java.lang.String getOwnerName() {
		return _assetReceipt.getOwnerName();
	}

	public java.lang.String getProductClassName() {
		return _assetReceipt.getProductClassName();
	}

	public java.util.Date getRenewedAssetReceiptLicensesEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getRenewedAssetReceiptLicensesEndDate(usageType);
	}

	public java.util.Date getRenewedAssetReceiptSupportsEndDate(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.getRenewedAssetReceiptSupportsEndDate(usageType);
	}

	public boolean hasActiveAssetReceiptLicenses()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.hasActiveAssetReceiptLicenses();
	}

	public boolean hasActiveAssetReceiptLicenses(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.hasActiveAssetReceiptLicenses(usageType);
	}

	public boolean hasActiveAssetReceiptSupports(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.hasActiveAssetReceiptSupports(usageType);
	}

	public boolean hasRenewedAssetReceiptLicenses(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.hasRenewedAssetReceiptLicenses(usageType);
	}

	public boolean hasRenewedAssetReceiptSupports(int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceipt.hasRenewedAssetReceiptSupports(usageType);
	}

	public boolean isOwnerCorpProject() {
		return _assetReceipt.isOwnerCorpProject();
	}

	public boolean isOwnerUser() {
		return _assetReceipt.isOwnerUser();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetReceiptWrapper)) {
			return false;
		}

		AssetReceiptWrapper assetReceiptWrapper = (AssetReceiptWrapper)obj;

		if (Validator.equals(_assetReceipt, assetReceiptWrapper._assetReceipt)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetReceipt getWrappedAssetReceipt() {
		return _assetReceipt;
	}

	public AssetReceipt getWrappedModel() {
		return _assetReceipt;
	}

	public void resetOriginalValues() {
		_assetReceipt.resetOriginalValues();
	}

	private AssetReceipt _assetReceipt;
}