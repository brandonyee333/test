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
 * This class is a wrapper for {@link ProductEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProductEntry
 * @generated
 */
public class ProductEntryWrapper implements ProductEntry,
	ModelWrapper<ProductEntry> {
	public ProductEntryWrapper(ProductEntry productEntry) {
		_productEntry = productEntry;
	}

	public Class<?> getModelClass() {
		return ProductEntry.class;
	}

	public String getModelClassName() {
		return ProductEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("productEntryId", getProductEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("environment", getEnvironment());
		attributes.put("versionsListType", getVersionsListType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer environment = (Integer)attributes.get("environment");

		if (environment != null) {
			setEnvironment(environment);
		}

		String versionsListType = (String)attributes.get("versionsListType");

		if (versionsListType != null) {
			setVersionsListType(versionsListType);
		}
	}

	/**
	* Returns the primary key of this product entry.
	*
	* @return the primary key of this product entry
	*/
	public long getPrimaryKey() {
		return _productEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this product entry.
	*
	* @param primaryKey the primary key of this product entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_productEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the product entry ID of this product entry.
	*
	* @return the product entry ID of this product entry
	*/
	public long getProductEntryId() {
		return _productEntry.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this product entry.
	*
	* @param productEntryId the product entry ID of this product entry
	*/
	public void setProductEntryId(long productEntryId) {
		_productEntry.setProductEntryId(productEntryId);
	}

	/**
	* Returns the user ID of this product entry.
	*
	* @return the user ID of this product entry
	*/
	public long getUserId() {
		return _productEntry.getUserId();
	}

	/**
	* Sets the user ID of this product entry.
	*
	* @param userId the user ID of this product entry
	*/
	public void setUserId(long userId) {
		_productEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this product entry.
	*
	* @return the user uuid of this product entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _productEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this product entry.
	*
	* @param userUuid the user uuid of this product entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_productEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this product entry.
	*
	* @return the user name of this product entry
	*/
	public java.lang.String getUserName() {
		return _productEntry.getUserName();
	}

	/**
	* Sets the user name of this product entry.
	*
	* @param userName the user name of this product entry
	*/
	public void setUserName(java.lang.String userName) {
		_productEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this product entry.
	*
	* @return the create date of this product entry
	*/
	public java.util.Date getCreateDate() {
		return _productEntry.getCreateDate();
	}

	/**
	* Sets the create date of this product entry.
	*
	* @param createDate the create date of this product entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_productEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this product entry.
	*
	* @return the modified date of this product entry
	*/
	public java.util.Date getModifiedDate() {
		return _productEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this product entry.
	*
	* @param modifiedDate the modified date of this product entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_productEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this product entry.
	*
	* @return the name of this product entry
	*/
	public java.lang.String getName() {
		return _productEntry.getName();
	}

	/**
	* Sets the name of this product entry.
	*
	* @param name the name of this product entry
	*/
	public void setName(java.lang.String name) {
		_productEntry.setName(name);
	}

	/**
	* Returns the type of this product entry.
	*
	* @return the type of this product entry
	*/
	public int getType() {
		return _productEntry.getType();
	}

	/**
	* Sets the type of this product entry.
	*
	* @param type the type of this product entry
	*/
	public void setType(int type) {
		_productEntry.setType(type);
	}

	/**
	* Returns the environment of this product entry.
	*
	* @return the environment of this product entry
	*/
	public int getEnvironment() {
		return _productEntry.getEnvironment();
	}

	/**
	* Sets the environment of this product entry.
	*
	* @param environment the environment of this product entry
	*/
	public void setEnvironment(int environment) {
		_productEntry.setEnvironment(environment);
	}

	/**
	* Returns the versions list type of this product entry.
	*
	* @return the versions list type of this product entry
	*/
	public java.lang.String getVersionsListType() {
		return _productEntry.getVersionsListType();
	}

	/**
	* Sets the versions list type of this product entry.
	*
	* @param versionsListType the versions list type of this product entry
	*/
	public void setVersionsListType(java.lang.String versionsListType) {
		_productEntry.setVersionsListType(versionsListType);
	}

	public boolean isNew() {
		return _productEntry.isNew();
	}

	public void setNew(boolean n) {
		_productEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _productEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_productEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _productEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _productEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_productEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _productEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_productEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ProductEntryWrapper((ProductEntry)_productEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.ProductEntry productEntry) {
		return _productEntry.compareTo(productEntry);
	}

	@Override
	public int hashCode() {
		return _productEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.ProductEntry> toCacheModel() {
		return _productEntry.toCacheModel();
	}

	public com.liferay.osb.model.ProductEntry toEscapedModel() {
		return new ProductEntryWrapper(_productEntry.toEscapedModel());
	}

	public com.liferay.osb.model.ProductEntry toUnescapedModel() {
		return new ProductEntryWrapper(_productEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _productEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _productEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_productEntry.persist();
	}

	public java.util.List<com.liferay.portal.model.ListType> getAllVersionsListTypes()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _productEntry.getAllVersionsListTypes();
	}

	public java.lang.String[] getDossieraIdMappings()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _productEntry.getDossieraIdMappings();
	}

	public java.lang.String getEnvironmentLabel() {
		return _productEntry.getEnvironmentLabel();
	}

	public java.lang.String getLESADisplayName() {
		return _productEntry.getLESADisplayName();
	}

	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _productEntry.getLicenseEntries();
	}

	public java.lang.String getTypeLabel() {
		return _productEntry.getTypeLabel();
	}

	public java.util.List<com.liferay.portal.model.ListType> getVersionsListTypes()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _productEntry.getVersionsListTypes();
	}

	public boolean isDeveloperTools() {
		return _productEntry.isDeveloperTools();
	}

	public boolean isDeviceDetection() {
		return _productEntry.isDeviceDetection();
	}

	public boolean isDigitalEnterprise() {
		return _productEntry.isDigitalEnterprise();
	}

	public boolean isEnterpriseSearchPremium() {
		return _productEntry.isEnterpriseSearchPremium();
	}

	public boolean isEnterpriseSearchStandard() {
		return _productEntry.isEnterpriseSearchStandard();
	}

	public boolean isManagementTools() {
		return _productEntry.isManagementTools();
	}

	public boolean isMobileExperience() {
		return _productEntry.isMobileExperience();
	}

	public boolean isPortal() {
		return _productEntry.isPortal();
	}

	public boolean isProductivityTools() {
		return _productEntry.isProductivityTools();
	}

	public boolean isSocialOffice() {
		return _productEntry.isSocialOffice();
	}

	public boolean isTicketComponentRequired() {
		return _productEntry.isTicketComponentRequired();
	}

	public boolean isUnlimitedEnterpriseWide() {
		return _productEntry.isUnlimitedEnterpriseWide();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProductEntryWrapper)) {
			return false;
		}

		ProductEntryWrapper productEntryWrapper = (ProductEntryWrapper)obj;

		if (Validator.equals(_productEntry, productEntryWrapper._productEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ProductEntry getWrappedProductEntry() {
		return _productEntry;
	}

	public ProductEntry getWrappedModel() {
		return _productEntry;
	}

	public void resetOriginalValues() {
		_productEntry.resetOriginalValues();
	}

	private ProductEntry _productEntry;
}