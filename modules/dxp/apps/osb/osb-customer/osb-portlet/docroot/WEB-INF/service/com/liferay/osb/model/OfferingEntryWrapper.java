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
 * This class is a wrapper for {@link OfferingEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntry
 * @generated
 */
@ProviderType
public class OfferingEntryWrapper implements OfferingEntry,
	ModelWrapper<OfferingEntry> {
	public OfferingEntryWrapper(OfferingEntry offeringEntry) {
		_offeringEntry = offeringEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return OfferingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return OfferingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringEntryId", getOfferingEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("orderEntryId", getOrderEntryId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("productDescription", getProductDescription());
		attributes.put("type", getType());
		attributes.put("version", getVersion());
		attributes.put("platform", getPlatform());
		attributes.put("platformVersion", getPlatformVersion());
		attributes.put("licenses", getLicenses());
		attributes.put("licenseLifetime", getLicenseLifetime());
		attributes.put("maxConcurrentUsers", getMaxConcurrentUsers());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("supportTickets", getSupportTickets());
		attributes.put("supportLifetime", getSupportLifetime());
		attributes.put("supportEndDate", getSupportEndDate());
		attributes.put("sizing", getSizing());
		attributes.put("quantity", getQuantity());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringEntryId = (Long)attributes.get("offeringEntryId");

		if (offeringEntryId != null) {
			setOfferingEntryId(offeringEntryId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long orderEntryId = (Long)attributes.get("orderEntryId");

		if (orderEntryId != null) {
			setOrderEntryId(orderEntryId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
		}

		String productDescription = (String)attributes.get("productDescription");

		if (productDescription != null) {
			setProductDescription(productDescription);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String platform = (String)attributes.get("platform");

		if (platform != null) {
			setPlatform(platform);
		}

		String platformVersion = (String)attributes.get("platformVersion");

		if (platformVersion != null) {
			setPlatformVersion(platformVersion);
		}

		Boolean licenses = (Boolean)attributes.get("licenses");

		if (licenses != null) {
			setLicenses(licenses);
		}

		Long licenseLifetime = (Long)attributes.get("licenseLifetime");

		if (licenseLifetime != null) {
			setLicenseLifetime(licenseLifetime);
		}

		Long maxConcurrentUsers = (Long)attributes.get("maxConcurrentUsers");

		if (maxConcurrentUsers != null) {
			setMaxConcurrentUsers(maxConcurrentUsers);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Boolean supportTickets = (Boolean)attributes.get("supportTickets");

		if (supportTickets != null) {
			setSupportTickets(supportTickets);
		}

		Long supportLifetime = (Long)attributes.get("supportLifetime");

		if (supportLifetime != null) {
			setSupportLifetime(supportLifetime);
		}

		Date supportEndDate = (Date)attributes.get("supportEndDate");

		if (supportEndDate != null) {
			setSupportEndDate(supportEndDate);
		}

		Integer sizing = (Integer)attributes.get("sizing");

		if (sizing != null) {
			setSizing(sizing);
		}

		Integer quantity = (Integer)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getAccountEntry();
	}

	@Override
	public OfferingEntry toEscapedModel() {
		return new OfferingEntryWrapper(_offeringEntry.toEscapedModel());
	}

	@Override
	public OfferingEntry toUnescapedModel() {
		return new OfferingEntryWrapper(_offeringEntry.toUnescapedModel());
	}

	@Override
	public OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getOrderEntry();
	}

	@Override
	public ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getProductEntry();
	}

	@Override
	public SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getSupportResponse();
	}

	/**
	* Returns the licenses of this offering entry.
	*
	* @return the licenses of this offering entry
	*/
	@Override
	public boolean getLicenses() {
		return _offeringEntry.getLicenses();
	}

	/**
	* Returns the support tickets of this offering entry.
	*
	* @return the support tickets of this offering entry
	*/
	@Override
	public boolean getSupportTickets() {
		return _offeringEntry.getSupportTickets();
	}

	@Override
	public boolean isCachedModel() {
		return _offeringEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _offeringEntry.isEscapedModel();
	}

	@Override
	public boolean isInformationalOnly() {
		return _offeringEntry.isInformationalOnly();
	}

	/**
	* Returns <code>true</code> if this offering entry is licenses.
	*
	* @return <code>true</code> if this offering entry is licenses; <code>false</code> otherwise
	*/
	@Override
	public boolean isLicenses() {
		return _offeringEntry.isLicenses();
	}

	@Override
	public boolean isNew() {
		return _offeringEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this offering entry is support tickets.
	*
	* @return <code>true</code> if this offering entry is support tickets; <code>false</code> otherwise
	*/
	@Override
	public boolean isSupportTickets() {
		return _offeringEntry.isSupportTickets();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _offeringEntry.getExpandoBridge();
	}

	@Override
	public OfferingEntryGroup getOfferingEntryGroup()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getOfferingEntryGroup();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OfferingEntry> toCacheModel() {
		return _offeringEntry.toCacheModel();
	}

	@Override
	public int compareTo(OfferingEntry offeringEntry) {
		return _offeringEntry.compareTo(offeringEntry);
	}

	@Override
	public int getAvailableServers()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getAvailableServers();
	}

	@Override
	public int getLicenseKeysCount() {
		return _offeringEntry.getLicenseKeysCount();
	}

	/**
	* Returns the quantity of this offering entry.
	*
	* @return the quantity of this offering entry
	*/
	@Override
	public int getQuantity() {
		return _offeringEntry.getQuantity();
	}

	/**
	* Returns the sizing of this offering entry.
	*
	* @return the sizing of this offering entry
	*/
	@Override
	public int getSizing() {
		return _offeringEntry.getSizing();
	}

	/**
	* Returns the status of this offering entry.
	*
	* @return the status of this offering entry
	*/
	@Override
	public int getStatus() {
		return _offeringEntry.getStatus();
	}

	/**
	* Returns the type of this offering entry.
	*
	* @return the type of this offering entry
	*/
	@Override
	public int getType() {
		return _offeringEntry.getType();
	}

	/**
	* Returns the version of this offering entry.
	*
	* @return the version of this offering entry
	*/
	@Override
	public int getVersion() {
		return _offeringEntry.getVersion();
	}

	@Override
	public int hashCode() {
		return _offeringEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _offeringEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OfferingEntryWrapper((OfferingEntry)_offeringEntry.clone());
	}

	@Override
	public java.lang.String getKey()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getKey();
	}

	/**
	* Returns the platform of this offering entry.
	*
	* @return the platform of this offering entry
	*/
	@Override
	public java.lang.String getPlatform() {
		return _offeringEntry.getPlatform();
	}

	/**
	* Returns the platform version of this offering entry.
	*
	* @return the platform version of this offering entry
	*/
	@Override
	public java.lang.String getPlatformVersion() {
		return _offeringEntry.getPlatformVersion();
	}

	/**
	* Returns the product description of this offering entry.
	*
	* @return the product description of this offering entry
	*/
	@Override
	public java.lang.String getProductDescription() {
		return _offeringEntry.getProductDescription();
	}

	@Override
	public java.lang.String getProductType() {
		return _offeringEntry.getProductType();
	}

	@Override
	public java.lang.String getSizingLabel() {
		return _offeringEntry.getSizingLabel();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _offeringEntry.getStatusLabel();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _offeringEntry.getTypeLabel();
	}

	/**
	* Returns the user name of this offering entry.
	*
	* @return the user name of this offering entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _offeringEntry.getUserName();
	}

	/**
	* Returns the user uuid of this offering entry.
	*
	* @return the user uuid of this offering entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _offeringEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _offeringEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _offeringEntry.toXmlString();
	}

	@Override
	public Date getActualStartDate()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getActualStartDate();
	}

	/**
	* Returns the create date of this offering entry.
	*
	* @return the create date of this offering entry
	*/
	@Override
	public Date getCreateDate() {
		return _offeringEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this offering entry.
	*
	* @return the modified date of this offering entry
	*/
	@Override
	public Date getModifiedDate() {
		return _offeringEntry.getModifiedDate();
	}

	@Override
	public Date getStartDate()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntry.getStartDate();
	}

	/**
	* Returns the support end date of this offering entry.
	*
	* @return the support end date of this offering entry
	*/
	@Override
	public Date getSupportEndDate() {
		return _offeringEntry.getSupportEndDate();
	}

	@Override
	public java.util.List<LicenseKey> getLicenseKeys() {
		return _offeringEntry.getLicenseKeys();
	}

	/**
	* Returns the account entry ID of this offering entry.
	*
	* @return the account entry ID of this offering entry
	*/
	@Override
	public long getAccountEntryId() {
		return _offeringEntry.getAccountEntryId();
	}

	/**
	* Returns the license lifetime of this offering entry.
	*
	* @return the license lifetime of this offering entry
	*/
	@Override
	public long getLicenseLifetime() {
		return _offeringEntry.getLicenseLifetime();
	}

	/**
	* Returns the max concurrent users of this offering entry.
	*
	* @return the max concurrent users of this offering entry
	*/
	@Override
	public long getMaxConcurrentUsers() {
		return _offeringEntry.getMaxConcurrentUsers();
	}

	/**
	* Returns the max users of this offering entry.
	*
	* @return the max users of this offering entry
	*/
	@Override
	public long getMaxUsers() {
		return _offeringEntry.getMaxUsers();
	}

	/**
	* Returns the offering entry ID of this offering entry.
	*
	* @return the offering entry ID of this offering entry
	*/
	@Override
	public long getOfferingEntryId() {
		return _offeringEntry.getOfferingEntryId();
	}

	/**
	* Returns the order entry ID of this offering entry.
	*
	* @return the order entry ID of this offering entry
	*/
	@Override
	public long getOrderEntryId() {
		return _offeringEntry.getOrderEntryId();
	}

	/**
	* Returns the primary key of this offering entry.
	*
	* @return the primary key of this offering entry
	*/
	@Override
	public long getPrimaryKey() {
		return _offeringEntry.getPrimaryKey();
	}

	/**
	* Returns the product entry ID of this offering entry.
	*
	* @return the product entry ID of this offering entry
	*/
	@Override
	public long getProductEntryId() {
		return _offeringEntry.getProductEntryId();
	}

	/**
	* Returns the support lifetime of this offering entry.
	*
	* @return the support lifetime of this offering entry
	*/
	@Override
	public long getSupportLifetime() {
		return _offeringEntry.getSupportLifetime();
	}

	/**
	* Returns the support response ID of this offering entry.
	*
	* @return the support response ID of this offering entry
	*/
	@Override
	public long getSupportResponseId() {
		return _offeringEntry.getSupportResponseId();
	}

	/**
	* Returns the user ID of this offering entry.
	*
	* @return the user ID of this offering entry
	*/
	@Override
	public long getUserId() {
		return _offeringEntry.getUserId();
	}

	@Override
	public void persist() {
		_offeringEntry.persist();
	}

	/**
	* Sets the account entry ID of this offering entry.
	*
	* @param accountEntryId the account entry ID of this offering entry
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_offeringEntry.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_offeringEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this offering entry.
	*
	* @param createDate the create date of this offering entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_offeringEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_offeringEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_offeringEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_offeringEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setInformationalOnly(boolean informationalOnly) {
		_offeringEntry.setInformationalOnly(informationalOnly);
	}

	/**
	* Sets the license lifetime of this offering entry.
	*
	* @param licenseLifetime the license lifetime of this offering entry
	*/
	@Override
	public void setLicenseLifetime(long licenseLifetime) {
		_offeringEntry.setLicenseLifetime(licenseLifetime);
	}

	/**
	* Sets whether this offering entry is licenses.
	*
	* @param licenses the licenses of this offering entry
	*/
	@Override
	public void setLicenses(boolean licenses) {
		_offeringEntry.setLicenses(licenses);
	}

	/**
	* Sets the max concurrent users of this offering entry.
	*
	* @param maxConcurrentUsers the max concurrent users of this offering entry
	*/
	@Override
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_offeringEntry.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	* Sets the max users of this offering entry.
	*
	* @param maxUsers the max users of this offering entry
	*/
	@Override
	public void setMaxUsers(long maxUsers) {
		_offeringEntry.setMaxUsers(maxUsers);
	}

	/**
	* Sets the modified date of this offering entry.
	*
	* @param modifiedDate the modified date of this offering entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_offeringEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_offeringEntry.setNew(n);
	}

	/**
	* Sets the offering entry ID of this offering entry.
	*
	* @param offeringEntryId the offering entry ID of this offering entry
	*/
	@Override
	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntry.setOfferingEntryId(offeringEntryId);
	}

	/**
	* Sets the order entry ID of this offering entry.
	*
	* @param orderEntryId the order entry ID of this offering entry
	*/
	@Override
	public void setOrderEntryId(long orderEntryId) {
		_offeringEntry.setOrderEntryId(orderEntryId);
	}

	/**
	* Sets the platform of this offering entry.
	*
	* @param platform the platform of this offering entry
	*/
	@Override
	public void setPlatform(java.lang.String platform) {
		_offeringEntry.setPlatform(platform);
	}

	/**
	* Sets the platform version of this offering entry.
	*
	* @param platformVersion the platform version of this offering entry
	*/
	@Override
	public void setPlatformVersion(java.lang.String platformVersion) {
		_offeringEntry.setPlatformVersion(platformVersion);
	}

	/**
	* Sets the primary key of this offering entry.
	*
	* @param primaryKey the primary key of this offering entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_offeringEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_offeringEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product description of this offering entry.
	*
	* @param productDescription the product description of this offering entry
	*/
	@Override
	public void setProductDescription(java.lang.String productDescription) {
		_offeringEntry.setProductDescription(productDescription);
	}

	/**
	* Sets the product entry ID of this offering entry.
	*
	* @param productEntryId the product entry ID of this offering entry
	*/
	@Override
	public void setProductEntryId(long productEntryId) {
		_offeringEntry.setProductEntryId(productEntryId);
	}

	@Override
	public void setProductType(java.lang.String productType) {
		_offeringEntry.setProductType(productType);
	}

	/**
	* Sets the quantity of this offering entry.
	*
	* @param quantity the quantity of this offering entry
	*/
	@Override
	public void setQuantity(int quantity) {
		_offeringEntry.setQuantity(quantity);
	}

	/**
	* Sets the sizing of this offering entry.
	*
	* @param sizing the sizing of this offering entry
	*/
	@Override
	public void setSizing(int sizing) {
		_offeringEntry.setSizing(sizing);
	}

	/**
	* Sets the status of this offering entry.
	*
	* @param status the status of this offering entry
	*/
	@Override
	public void setStatus(int status) {
		_offeringEntry.setStatus(status);
	}

	/**
	* Sets the support end date of this offering entry.
	*
	* @param supportEndDate the support end date of this offering entry
	*/
	@Override
	public void setSupportEndDate(Date supportEndDate) {
		_offeringEntry.setSupportEndDate(supportEndDate);
	}

	/**
	* Sets the support lifetime of this offering entry.
	*
	* @param supportLifetime the support lifetime of this offering entry
	*/
	@Override
	public void setSupportLifetime(long supportLifetime) {
		_offeringEntry.setSupportLifetime(supportLifetime);
	}

	/**
	* Sets the support response ID of this offering entry.
	*
	* @param supportResponseId the support response ID of this offering entry
	*/
	@Override
	public void setSupportResponseId(long supportResponseId) {
		_offeringEntry.setSupportResponseId(supportResponseId);
	}

	/**
	* Sets whether this offering entry is support tickets.
	*
	* @param supportTickets the support tickets of this offering entry
	*/
	@Override
	public void setSupportTickets(boolean supportTickets) {
		_offeringEntry.setSupportTickets(supportTickets);
	}

	/**
	* Sets the type of this offering entry.
	*
	* @param type the type of this offering entry
	*/
	@Override
	public void setType(int type) {
		_offeringEntry.setType(type);
	}

	/**
	* Sets the user ID of this offering entry.
	*
	* @param userId the user ID of this offering entry
	*/
	@Override
	public void setUserId(long userId) {
		_offeringEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this offering entry.
	*
	* @param userName the user name of this offering entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_offeringEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this offering entry.
	*
	* @param userUuid the user uuid of this offering entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_offeringEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the version of this offering entry.
	*
	* @param version the version of this offering entry
	*/
	@Override
	public void setVersion(int version) {
		_offeringEntry.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferingEntryWrapper)) {
			return false;
		}

		OfferingEntryWrapper offeringEntryWrapper = (OfferingEntryWrapper)obj;

		if (Objects.equals(_offeringEntry, offeringEntryWrapper._offeringEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public OfferingEntry getWrappedModel() {
		return _offeringEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _offeringEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _offeringEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_offeringEntry.resetOriginalValues();
	}

	private final OfferingEntry _offeringEntry;
}