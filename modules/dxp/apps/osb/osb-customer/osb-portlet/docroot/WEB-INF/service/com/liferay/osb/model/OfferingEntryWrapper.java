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
 * This class is a wrapper for {@link OfferingEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingEntry
 * @generated
 */
public class OfferingEntryWrapper implements OfferingEntry,
	ModelWrapper<OfferingEntry> {
	public OfferingEntryWrapper(OfferingEntry offeringEntry) {
		_offeringEntry = offeringEntry;
	}

	public Class<?> getModelClass() {
		return OfferingEntry.class;
	}

	public String getModelClassName() {
		return OfferingEntry.class.getName();
	}

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

	/**
	* Returns the primary key of this offering entry.
	*
	* @return the primary key of this offering entry
	*/
	public long getPrimaryKey() {
		return _offeringEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this offering entry.
	*
	* @param primaryKey the primary key of this offering entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_offeringEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the offering entry ID of this offering entry.
	*
	* @return the offering entry ID of this offering entry
	*/
	public long getOfferingEntryId() {
		return _offeringEntry.getOfferingEntryId();
	}

	/**
	* Sets the offering entry ID of this offering entry.
	*
	* @param offeringEntryId the offering entry ID of this offering entry
	*/
	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntry.setOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the user ID of this offering entry.
	*
	* @return the user ID of this offering entry
	*/
	public long getUserId() {
		return _offeringEntry.getUserId();
	}

	/**
	* Sets the user ID of this offering entry.
	*
	* @param userId the user ID of this offering entry
	*/
	public void setUserId(long userId) {
		_offeringEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this offering entry.
	*
	* @return the user uuid of this offering entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this offering entry.
	*
	* @param userUuid the user uuid of this offering entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_offeringEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this offering entry.
	*
	* @return the user name of this offering entry
	*/
	public java.lang.String getUserName() {
		return _offeringEntry.getUserName();
	}

	/**
	* Sets the user name of this offering entry.
	*
	* @param userName the user name of this offering entry
	*/
	public void setUserName(java.lang.String userName) {
		_offeringEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this offering entry.
	*
	* @return the create date of this offering entry
	*/
	public java.util.Date getCreateDate() {
		return _offeringEntry.getCreateDate();
	}

	/**
	* Sets the create date of this offering entry.
	*
	* @param createDate the create date of this offering entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_offeringEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this offering entry.
	*
	* @return the modified date of this offering entry
	*/
	public java.util.Date getModifiedDate() {
		return _offeringEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this offering entry.
	*
	* @param modifiedDate the modified date of this offering entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_offeringEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this offering entry.
	*
	* @return the account entry ID of this offering entry
	*/
	public long getAccountEntryId() {
		return _offeringEntry.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this offering entry.
	*
	* @param accountEntryId the account entry ID of this offering entry
	*/
	public void setAccountEntryId(long accountEntryId) {
		_offeringEntry.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the order entry ID of this offering entry.
	*
	* @return the order entry ID of this offering entry
	*/
	public long getOrderEntryId() {
		return _offeringEntry.getOrderEntryId();
	}

	/**
	* Sets the order entry ID of this offering entry.
	*
	* @param orderEntryId the order entry ID of this offering entry
	*/
	public void setOrderEntryId(long orderEntryId) {
		_offeringEntry.setOrderEntryId(orderEntryId);
	}

	/**
	* Returns the product entry ID of this offering entry.
	*
	* @return the product entry ID of this offering entry
	*/
	public long getProductEntryId() {
		return _offeringEntry.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this offering entry.
	*
	* @param productEntryId the product entry ID of this offering entry
	*/
	public void setProductEntryId(long productEntryId) {
		_offeringEntry.setProductEntryId(productEntryId);
	}

	/**
	* Returns the support response ID of this offering entry.
	*
	* @return the support response ID of this offering entry
	*/
	public long getSupportResponseId() {
		return _offeringEntry.getSupportResponseId();
	}

	/**
	* Sets the support response ID of this offering entry.
	*
	* @param supportResponseId the support response ID of this offering entry
	*/
	public void setSupportResponseId(long supportResponseId) {
		_offeringEntry.setSupportResponseId(supportResponseId);
	}

	/**
	* Returns the product description of this offering entry.
	*
	* @return the product description of this offering entry
	*/
	public java.lang.String getProductDescription() {
		return _offeringEntry.getProductDescription();
	}

	/**
	* Sets the product description of this offering entry.
	*
	* @param productDescription the product description of this offering entry
	*/
	public void setProductDescription(java.lang.String productDescription) {
		_offeringEntry.setProductDescription(productDescription);
	}

	/**
	* Returns the type of this offering entry.
	*
	* @return the type of this offering entry
	*/
	public int getType() {
		return _offeringEntry.getType();
	}

	/**
	* Sets the type of this offering entry.
	*
	* @param type the type of this offering entry
	*/
	public void setType(int type) {
		_offeringEntry.setType(type);
	}

	/**
	* Returns the version of this offering entry.
	*
	* @return the version of this offering entry
	*/
	public int getVersion() {
		return _offeringEntry.getVersion();
	}

	/**
	* Sets the version of this offering entry.
	*
	* @param version the version of this offering entry
	*/
	public void setVersion(int version) {
		_offeringEntry.setVersion(version);
	}

	/**
	* Returns the platform of this offering entry.
	*
	* @return the platform of this offering entry
	*/
	public java.lang.String getPlatform() {
		return _offeringEntry.getPlatform();
	}

	/**
	* Sets the platform of this offering entry.
	*
	* @param platform the platform of this offering entry
	*/
	public void setPlatform(java.lang.String platform) {
		_offeringEntry.setPlatform(platform);
	}

	/**
	* Returns the platform version of this offering entry.
	*
	* @return the platform version of this offering entry
	*/
	public java.lang.String getPlatformVersion() {
		return _offeringEntry.getPlatformVersion();
	}

	/**
	* Sets the platform version of this offering entry.
	*
	* @param platformVersion the platform version of this offering entry
	*/
	public void setPlatformVersion(java.lang.String platformVersion) {
		_offeringEntry.setPlatformVersion(platformVersion);
	}

	/**
	* Returns the licenses of this offering entry.
	*
	* @return the licenses of this offering entry
	*/
	public boolean getLicenses() {
		return _offeringEntry.getLicenses();
	}

	/**
	* Returns <code>true</code> if this offering entry is licenses.
	*
	* @return <code>true</code> if this offering entry is licenses; <code>false</code> otherwise
	*/
	public boolean isLicenses() {
		return _offeringEntry.isLicenses();
	}

	/**
	* Sets whether this offering entry is licenses.
	*
	* @param licenses the licenses of this offering entry
	*/
	public void setLicenses(boolean licenses) {
		_offeringEntry.setLicenses(licenses);
	}

	/**
	* Returns the license lifetime of this offering entry.
	*
	* @return the license lifetime of this offering entry
	*/
	public long getLicenseLifetime() {
		return _offeringEntry.getLicenseLifetime();
	}

	/**
	* Sets the license lifetime of this offering entry.
	*
	* @param licenseLifetime the license lifetime of this offering entry
	*/
	public void setLicenseLifetime(long licenseLifetime) {
		_offeringEntry.setLicenseLifetime(licenseLifetime);
	}

	/**
	* Returns the max concurrent users of this offering entry.
	*
	* @return the max concurrent users of this offering entry
	*/
	public long getMaxConcurrentUsers() {
		return _offeringEntry.getMaxConcurrentUsers();
	}

	/**
	* Sets the max concurrent users of this offering entry.
	*
	* @param maxConcurrentUsers the max concurrent users of this offering entry
	*/
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_offeringEntry.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	* Returns the max users of this offering entry.
	*
	* @return the max users of this offering entry
	*/
	public long getMaxUsers() {
		return _offeringEntry.getMaxUsers();
	}

	/**
	* Sets the max users of this offering entry.
	*
	* @param maxUsers the max users of this offering entry
	*/
	public void setMaxUsers(long maxUsers) {
		_offeringEntry.setMaxUsers(maxUsers);
	}

	/**
	* Returns the support tickets of this offering entry.
	*
	* @return the support tickets of this offering entry
	*/
	public boolean getSupportTickets() {
		return _offeringEntry.getSupportTickets();
	}

	/**
	* Returns <code>true</code> if this offering entry is support tickets.
	*
	* @return <code>true</code> if this offering entry is support tickets; <code>false</code> otherwise
	*/
	public boolean isSupportTickets() {
		return _offeringEntry.isSupportTickets();
	}

	/**
	* Sets whether this offering entry is support tickets.
	*
	* @param supportTickets the support tickets of this offering entry
	*/
	public void setSupportTickets(boolean supportTickets) {
		_offeringEntry.setSupportTickets(supportTickets);
	}

	/**
	* Returns the support lifetime of this offering entry.
	*
	* @return the support lifetime of this offering entry
	*/
	public long getSupportLifetime() {
		return _offeringEntry.getSupportLifetime();
	}

	/**
	* Sets the support lifetime of this offering entry.
	*
	* @param supportLifetime the support lifetime of this offering entry
	*/
	public void setSupportLifetime(long supportLifetime) {
		_offeringEntry.setSupportLifetime(supportLifetime);
	}

	/**
	* Returns the support end date of this offering entry.
	*
	* @return the support end date of this offering entry
	*/
	public java.util.Date getSupportEndDate() {
		return _offeringEntry.getSupportEndDate();
	}

	/**
	* Sets the support end date of this offering entry.
	*
	* @param supportEndDate the support end date of this offering entry
	*/
	public void setSupportEndDate(java.util.Date supportEndDate) {
		_offeringEntry.setSupportEndDate(supportEndDate);
	}

	/**
	* Returns the sizing of this offering entry.
	*
	* @return the sizing of this offering entry
	*/
	public int getSizing() {
		return _offeringEntry.getSizing();
	}

	/**
	* Sets the sizing of this offering entry.
	*
	* @param sizing the sizing of this offering entry
	*/
	public void setSizing(int sizing) {
		_offeringEntry.setSizing(sizing);
	}

	/**
	* Returns the quantity of this offering entry.
	*
	* @return the quantity of this offering entry
	*/
	public int getQuantity() {
		return _offeringEntry.getQuantity();
	}

	/**
	* Sets the quantity of this offering entry.
	*
	* @param quantity the quantity of this offering entry
	*/
	public void setQuantity(int quantity) {
		_offeringEntry.setQuantity(quantity);
	}

	/**
	* Returns the status of this offering entry.
	*
	* @return the status of this offering entry
	*/
	public int getStatus() {
		return _offeringEntry.getStatus();
	}

	/**
	* Sets the status of this offering entry.
	*
	* @param status the status of this offering entry
	*/
	public void setStatus(int status) {
		_offeringEntry.setStatus(status);
	}

	public boolean isNew() {
		return _offeringEntry.isNew();
	}

	public void setNew(boolean n) {
		_offeringEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _offeringEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_offeringEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _offeringEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _offeringEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_offeringEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _offeringEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_offeringEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OfferingEntryWrapper((OfferingEntry)_offeringEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.OfferingEntry offeringEntry) {
		return _offeringEntry.compareTo(offeringEntry);
	}

	@Override
	public int hashCode() {
		return _offeringEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.OfferingEntry> toCacheModel() {
		return _offeringEntry.toCacheModel();
	}

	public com.liferay.osb.model.OfferingEntry toEscapedModel() {
		return new OfferingEntryWrapper(_offeringEntry.toEscapedModel());
	}

	public com.liferay.osb.model.OfferingEntry toUnescapedModel() {
		return new OfferingEntryWrapper(_offeringEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _offeringEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _offeringEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringEntry.persist();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getAccountEntry();
	}

	public java.util.Date getActualStartDate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getActualStartDate();
	}

	public int getAvailableServers()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getAvailableServers();
	}

	public java.lang.String getKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getKey();
	}

	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getLicenseKeys();
	}

	public int getLicenseKeysCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getLicenseKeysCount();
	}

	public com.liferay.osb.model.OfferingEntryGroup getOfferingEntryGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getOfferingEntryGroup();
	}

	public com.liferay.osb.model.OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getOrderEntry();
	}

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getProductEntry();
	}

	public java.lang.String getSizingLabel() {
		return _offeringEntry.getSizingLabel();
	}

	public java.util.Date getStartDate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getStartDate();
	}

	public java.lang.String getStatusLabel() {
		return _offeringEntry.getStatusLabel();
	}

	public com.liferay.osb.model.SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getSupportResponse();
	}

	public int getTicketEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntry.getTicketEntriesCount();
	}

	public java.lang.String getTypeLabel() {
		return _offeringEntry.getTypeLabel();
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

		if (Validator.equals(_offeringEntry, offeringEntryWrapper._offeringEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OfferingEntry getWrappedOfferingEntry() {
		return _offeringEntry;
	}

	public OfferingEntry getWrappedModel() {
		return _offeringEntry;
	}

	public void resetOriginalValues() {
		_offeringEntry.resetOriginalValues();
	}

	private OfferingEntry _offeringEntry;
}