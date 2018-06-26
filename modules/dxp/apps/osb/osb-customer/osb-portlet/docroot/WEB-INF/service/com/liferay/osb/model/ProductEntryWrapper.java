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
 * This class is a wrapper for {@link ProductEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntry
 * @generated
 */
@ProviderType
public class ProductEntryWrapper implements ProductEntry,
	ModelWrapper<ProductEntry> {
	public ProductEntryWrapper(ProductEntry productEntry) {
		_productEntry = productEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return ProductEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ProductEntry.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public ProductEntry toEscapedModel() {
		return new ProductEntryWrapper(_productEntry.toEscapedModel());
	}

	@Override
	public ProductEntry toUnescapedModel() {
		return new ProductEntryWrapper(_productEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _productEntry.isCachedModel();
	}

	@Override
	public boolean isDeveloperTools() {
		return _productEntry.isDeveloperTools();
	}

	@Override
	public boolean isDeviceDetection() {
		return _productEntry.isDeviceDetection();
	}

	@Override
	public boolean isDigitalEnterprise() {
		return _productEntry.isDigitalEnterprise();
	}

	@Override
	public boolean isEnterpriseSearch() {
		return _productEntry.isEnterpriseSearch();
	}

	@Override
	public boolean isEnterpriseSearchPremium() {
		return _productEntry.isEnterpriseSearchPremium();
	}

	@Override
	public boolean isEnterpriseSearchStandard() {
		return _productEntry.isEnterpriseSearchStandard();
	}

	@Override
	public boolean isEscapedModel() {
		return _productEntry.isEscapedModel();
	}

	@Override
	public boolean isExtendedPremiumSupport() {
		return _productEntry.isExtendedPremiumSupport();
	}

	@Override
	public boolean isManagementTools() {
		return _productEntry.isManagementTools();
	}

	@Override
	public boolean isMobileExperience() {
		return _productEntry.isMobileExperience();
	}

	@Override
	public boolean isNew() {
		return _productEntry.isNew();
	}

	@Override
	public boolean isPortal() {
		return _productEntry.isPortal();
	}

	@Override
	public boolean isProductivityTools() {
		return _productEntry.isProductivityTools();
	}

	@Override
	public boolean isSocialOffice() {
		return _productEntry.isSocialOffice();
	}

	@Override
	public boolean isUnlimitedEnterpriseWide() {
		return _productEntry.isUnlimitedEnterpriseWide();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _productEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProductEntry> toCacheModel() {
		return _productEntry.toCacheModel();
	}

	@Override
	public int compareTo(ProductEntry productEntry) {
		return _productEntry.compareTo(productEntry);
	}

	/**
	* Returns the environment of this product entry.
	*
	* @return the environment of this product entry
	*/
	@Override
	public int getEnvironment() {
		return _productEntry.getEnvironment();
	}

	/**
	* Returns the type of this product entry.
	*
	* @return the type of this product entry
	*/
	@Override
	public int getType() {
		return _productEntry.getType();
	}

	@Override
	public int hashCode() {
		return _productEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _productEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ProductEntryWrapper((ProductEntry)_productEntry.clone());
	}

	@Override
	public java.lang.String getEnvironmentLabel() {
		return _productEntry.getEnvironmentLabel();
	}

	/**
	* Returns the name of this product entry.
	*
	* @return the name of this product entry
	*/
	@Override
	public java.lang.String getName() {
		return _productEntry.getName();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _productEntry.getTypeLabel();
	}

	/**
	* Returns the user name of this product entry.
	*
	* @return the user name of this product entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _productEntry.getUserName();
	}

	/**
	* Returns the user uuid of this product entry.
	*
	* @return the user uuid of this product entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _productEntry.getUserUuid();
	}

	/**
	* Returns the versions list type of this product entry.
	*
	* @return the versions list type of this product entry
	*/
	@Override
	public java.lang.String getVersionsListType() {
		return _productEntry.getVersionsListType();
	}

	@Override
	public java.lang.String toString() {
		return _productEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _productEntry.toXmlString();
	}

	@Override
	public java.lang.String[] getDossieraIdMappings() {
		return _productEntry.getDossieraIdMappings();
	}

	/**
	* Returns the create date of this product entry.
	*
	* @return the create date of this product entry
	*/
	@Override
	public Date getCreateDate() {
		return _productEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this product entry.
	*
	* @return the modified date of this product entry
	*/
	@Override
	public Date getModifiedDate() {
		return _productEntry.getModifiedDate();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.ListType> getAllVersionsListTypes() {
		return _productEntry.getAllVersionsListTypes();
	}

	@Override
	public java.util.List<LicenseEntry> getLicenseEntries() {
		return _productEntry.getLicenseEntries();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.ListType> getVersionsListTypes() {
		return _productEntry.getVersionsListTypes();
	}

	/**
	* Returns the primary key of this product entry.
	*
	* @return the primary key of this product entry
	*/
	@Override
	public long getPrimaryKey() {
		return _productEntry.getPrimaryKey();
	}

	/**
	* Returns the product entry ID of this product entry.
	*
	* @return the product entry ID of this product entry
	*/
	@Override
	public long getProductEntryId() {
		return _productEntry.getProductEntryId();
	}

	/**
	* Returns the user ID of this product entry.
	*
	* @return the user ID of this product entry
	*/
	@Override
	public long getUserId() {
		return _productEntry.getUserId();
	}

	@Override
	public void persist() {
		_productEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_productEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this product entry.
	*
	* @param createDate the create date of this product entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_productEntry.setCreateDate(createDate);
	}

	/**
	* Sets the environment of this product entry.
	*
	* @param environment the environment of this product entry
	*/
	@Override
	public void setEnvironment(int environment) {
		_productEntry.setEnvironment(environment);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_productEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_productEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_productEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this product entry.
	*
	* @param modifiedDate the modified date of this product entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_productEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this product entry.
	*
	* @param name the name of this product entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_productEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_productEntry.setNew(n);
	}

	/**
	* Sets the primary key of this product entry.
	*
	* @param primaryKey the primary key of this product entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_productEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_productEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product entry ID of this product entry.
	*
	* @param productEntryId the product entry ID of this product entry
	*/
	@Override
	public void setProductEntryId(long productEntryId) {
		_productEntry.setProductEntryId(productEntryId);
	}

	/**
	* Sets the type of this product entry.
	*
	* @param type the type of this product entry
	*/
	@Override
	public void setType(int type) {
		_productEntry.setType(type);
	}

	/**
	* Sets the user ID of this product entry.
	*
	* @param userId the user ID of this product entry
	*/
	@Override
	public void setUserId(long userId) {
		_productEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this product entry.
	*
	* @param userName the user name of this product entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_productEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this product entry.
	*
	* @param userUuid the user uuid of this product entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_productEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the versions list type of this product entry.
	*
	* @param versionsListType the versions list type of this product entry
	*/
	@Override
	public void setVersionsListType(java.lang.String versionsListType) {
		_productEntry.setVersionsListType(versionsListType);
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

		if (Objects.equals(_productEntry, productEntryWrapper._productEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public ProductEntry getWrappedModel() {
		return _productEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _productEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _productEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_productEntry.resetOriginalValues();
	}

	private final ProductEntry _productEntry;
}