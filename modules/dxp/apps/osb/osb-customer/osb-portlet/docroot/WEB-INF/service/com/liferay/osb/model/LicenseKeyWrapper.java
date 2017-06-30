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
 * This class is a wrapper for {@link LicenseKey}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseKey
 * @generated
 */
public class LicenseKeyWrapper implements LicenseKey, ModelWrapper<LicenseKey> {
	public LicenseKeyWrapper(LicenseKey licenseKey) {
		_licenseKey = licenseKey;
	}

	public Class<?> getModelClass() {
		return LicenseKey.class;
	}

	public String getModelClassName() {
		return LicenseKey.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseKeyId", getLicenseKeyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("licenseKeySetId", getLicenseKeySetId());
		attributes.put("assetReceiptLicenseId", getAssetReceiptLicenseId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("orderEntryId", getOrderEntryId());
		attributes.put("offeringEntryId", getOfferingEntryId());
		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("accountEntryName", getAccountEntryName());
		attributes.put("licenseEntryName", getLicenseEntryName());
		attributes.put("licenseEntryType", getLicenseEntryType());
		attributes.put("licenseVersion", getLicenseVersion());
		attributes.put("productEntryName", getProductEntryName());
		attributes.put("productId", getProductId());
		attributes.put("productVersion", getProductVersion());
		attributes.put("productVersionLabel", getProductVersionLabel());
		attributes.put("clusterId", getClusterId());
		attributes.put("owner", getOwner());
		attributes.put("maxServers", getMaxServers());
		attributes.put("maxConcurrentUsers", getMaxConcurrentUsers());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("maxHttpSessions", getMaxHttpSessions());
		attributes.put("description", getDescription());
		attributes.put("hostName", getHostName());
		attributes.put("ipAddresses", getIpAddresses());
		attributes.put("macAddresses", getMacAddresses());
		attributes.put("serverId", getServerId());
		attributes.put("key", getKey());
		attributes.put("startDate", getStartDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("additionalInfo", getAdditionalInfo());
		attributes.put("complimentary", getComplimentary());
		attributes.put("active", getActive());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseKeyId = (Long)attributes.get("licenseKeyId");

		if (licenseKeyId != null) {
			setLicenseKeyId(licenseKeyId);
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

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long licenseKeySetId = (Long)attributes.get("licenseKeySetId");

		if (licenseKeySetId != null) {
			setLicenseKeySetId(licenseKeySetId);
		}

		Long assetReceiptLicenseId = (Long)attributes.get(
				"assetReceiptLicenseId");

		if (assetReceiptLicenseId != null) {
			setAssetReceiptLicenseId(assetReceiptLicenseId);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long orderEntryId = (Long)attributes.get("orderEntryId");

		if (orderEntryId != null) {
			setOrderEntryId(orderEntryId);
		}

		Long offeringEntryId = (Long)attributes.get("offeringEntryId");

		if (offeringEntryId != null) {
			setOfferingEntryId(offeringEntryId);
		}

		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
		}

		String accountEntryName = (String)attributes.get("accountEntryName");

		if (accountEntryName != null) {
			setAccountEntryName(accountEntryName);
		}

		String licenseEntryName = (String)attributes.get("licenseEntryName");

		if (licenseEntryName != null) {
			setLicenseEntryName(licenseEntryName);
		}

		String licenseEntryType = (String)attributes.get("licenseEntryType");

		if (licenseEntryType != null) {
			setLicenseEntryType(licenseEntryType);
		}

		Integer licenseVersion = (Integer)attributes.get("licenseVersion");

		if (licenseVersion != null) {
			setLicenseVersion(licenseVersion);
		}

		String productEntryName = (String)attributes.get("productEntryName");

		if (productEntryName != null) {
			setProductEntryName(productEntryName);
		}

		String productId = (String)attributes.get("productId");

		if (productId != null) {
			setProductId(productId);
		}

		Integer productVersion = (Integer)attributes.get("productVersion");

		if (productVersion != null) {
			setProductVersion(productVersion);
		}

		String productVersionLabel = (String)attributes.get(
				"productVersionLabel");

		if (productVersionLabel != null) {
			setProductVersionLabel(productVersionLabel);
		}

		Long clusterId = (Long)attributes.get("clusterId");

		if (clusterId != null) {
			setClusterId(clusterId);
		}

		String owner = (String)attributes.get("owner");

		if (owner != null) {
			setOwner(owner);
		}

		Integer maxServers = (Integer)attributes.get("maxServers");

		if (maxServers != null) {
			setMaxServers(maxServers);
		}

		Long maxConcurrentUsers = (Long)attributes.get("maxConcurrentUsers");

		if (maxConcurrentUsers != null) {
			setMaxConcurrentUsers(maxConcurrentUsers);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Integer maxHttpSessions = (Integer)attributes.get("maxHttpSessions");

		if (maxHttpSessions != null) {
			setMaxHttpSessions(maxHttpSessions);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String hostName = (String)attributes.get("hostName");

		if (hostName != null) {
			setHostName(hostName);
		}

		String ipAddresses = (String)attributes.get("ipAddresses");

		if (ipAddresses != null) {
			setIpAddresses(ipAddresses);
		}

		String macAddresses = (String)attributes.get("macAddresses");

		if (macAddresses != null) {
			setMacAddresses(macAddresses);
		}

		String serverId = (String)attributes.get("serverId");

		if (serverId != null) {
			setServerId(serverId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		String additionalInfo = (String)attributes.get("additionalInfo");

		if (additionalInfo != null) {
			setAdditionalInfo(additionalInfo);
		}

		Boolean complimentary = (Boolean)attributes.get("complimentary");

		if (complimentary != null) {
			setComplimentary(complimentary);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	/**
	* Returns the primary key of this license key.
	*
	* @return the primary key of this license key
	*/
	public long getPrimaryKey() {
		return _licenseKey.getPrimaryKey();
	}

	/**
	* Sets the primary key of this license key.
	*
	* @param primaryKey the primary key of this license key
	*/
	public void setPrimaryKey(long primaryKey) {
		_licenseKey.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the license key ID of this license key.
	*
	* @return the license key ID of this license key
	*/
	public long getLicenseKeyId() {
		return _licenseKey.getLicenseKeyId();
	}

	/**
	* Sets the license key ID of this license key.
	*
	* @param licenseKeyId the license key ID of this license key
	*/
	public void setLicenseKeyId(long licenseKeyId) {
		_licenseKey.setLicenseKeyId(licenseKeyId);
	}

	/**
	* Returns the user ID of this license key.
	*
	* @return the user ID of this license key
	*/
	public long getUserId() {
		return _licenseKey.getUserId();
	}

	/**
	* Sets the user ID of this license key.
	*
	* @param userId the user ID of this license key
	*/
	public void setUserId(long userId) {
		_licenseKey.setUserId(userId);
	}

	/**
	* Returns the user uuid of this license key.
	*
	* @return the user uuid of this license key
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getUserUuid();
	}

	/**
	* Sets the user uuid of this license key.
	*
	* @param userUuid the user uuid of this license key
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_licenseKey.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this license key.
	*
	* @return the user name of this license key
	*/
	public java.lang.String getUserName() {
		return _licenseKey.getUserName();
	}

	/**
	* Sets the user name of this license key.
	*
	* @param userName the user name of this license key
	*/
	public void setUserName(java.lang.String userName) {
		_licenseKey.setUserName(userName);
	}

	/**
	* Returns the create date of this license key.
	*
	* @return the create date of this license key
	*/
	public java.util.Date getCreateDate() {
		return _licenseKey.getCreateDate();
	}

	/**
	* Sets the create date of this license key.
	*
	* @param createDate the create date of this license key
	*/
	public void setCreateDate(java.util.Date createDate) {
		_licenseKey.setCreateDate(createDate);
	}

	/**
	* Returns the modified user ID of this license key.
	*
	* @return the modified user ID of this license key
	*/
	public long getModifiedUserId() {
		return _licenseKey.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this license key.
	*
	* @param modifiedUserId the modified user ID of this license key
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_licenseKey.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this license key.
	*
	* @return the modified user uuid of this license key
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this license key.
	*
	* @param modifiedUserUuid the modified user uuid of this license key
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_licenseKey.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this license key.
	*
	* @return the modified user name of this license key
	*/
	public java.lang.String getModifiedUserName() {
		return _licenseKey.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this license key.
	*
	* @param modifiedUserName the modified user name of this license key
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_licenseKey.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified date of this license key.
	*
	* @return the modified date of this license key
	*/
	public java.util.Date getModifiedDate() {
		return _licenseKey.getModifiedDate();
	}

	/**
	* Sets the modified date of this license key.
	*
	* @param modifiedDate the modified date of this license key
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_licenseKey.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the license key set ID of this license key.
	*
	* @return the license key set ID of this license key
	*/
	public long getLicenseKeySetId() {
		return _licenseKey.getLicenseKeySetId();
	}

	/**
	* Sets the license key set ID of this license key.
	*
	* @param licenseKeySetId the license key set ID of this license key
	*/
	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKey.setLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Returns the asset receipt license ID of this license key.
	*
	* @return the asset receipt license ID of this license key
	*/
	public long getAssetReceiptLicenseId() {
		return _licenseKey.getAssetReceiptLicenseId();
	}

	/**
	* Sets the asset receipt license ID of this license key.
	*
	* @param assetReceiptLicenseId the asset receipt license ID of this license key
	*/
	public void setAssetReceiptLicenseId(long assetReceiptLicenseId) {
		_licenseKey.setAssetReceiptLicenseId(assetReceiptLicenseId);
	}

	/**
	* Returns the account entry ID of this license key.
	*
	* @return the account entry ID of this license key
	*/
	public long getAccountEntryId() {
		return _licenseKey.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this license key.
	*
	* @param accountEntryId the account entry ID of this license key
	*/
	public void setAccountEntryId(long accountEntryId) {
		_licenseKey.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the order entry ID of this license key.
	*
	* @return the order entry ID of this license key
	*/
	public long getOrderEntryId() {
		return _licenseKey.getOrderEntryId();
	}

	/**
	* Sets the order entry ID of this license key.
	*
	* @param orderEntryId the order entry ID of this license key
	*/
	public void setOrderEntryId(long orderEntryId) {
		_licenseKey.setOrderEntryId(orderEntryId);
	}

	/**
	* Returns the offering entry ID of this license key.
	*
	* @return the offering entry ID of this license key
	*/
	public long getOfferingEntryId() {
		return _licenseKey.getOfferingEntryId();
	}

	/**
	* Sets the offering entry ID of this license key.
	*
	* @param offeringEntryId the offering entry ID of this license key
	*/
	public void setOfferingEntryId(long offeringEntryId) {
		_licenseKey.setOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the license entry ID of this license key.
	*
	* @return the license entry ID of this license key
	*/
	public long getLicenseEntryId() {
		return _licenseKey.getLicenseEntryId();
	}

	/**
	* Sets the license entry ID of this license key.
	*
	* @param licenseEntryId the license entry ID of this license key
	*/
	public void setLicenseEntryId(long licenseEntryId) {
		_licenseKey.setLicenseEntryId(licenseEntryId);
	}

	/**
	* Returns the product entry ID of this license key.
	*
	* @return the product entry ID of this license key
	*/
	public long getProductEntryId() {
		return _licenseKey.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this license key.
	*
	* @param productEntryId the product entry ID of this license key
	*/
	public void setProductEntryId(long productEntryId) {
		_licenseKey.setProductEntryId(productEntryId);
	}

	/**
	* Returns the support response ID of this license key.
	*
	* @return the support response ID of this license key
	*/
	public long getSupportResponseId() {
		return _licenseKey.getSupportResponseId();
	}

	/**
	* Sets the support response ID of this license key.
	*
	* @param supportResponseId the support response ID of this license key
	*/
	public void setSupportResponseId(long supportResponseId) {
		_licenseKey.setSupportResponseId(supportResponseId);
	}

	/**
	* Returns the account entry name of this license key.
	*
	* @return the account entry name of this license key
	*/
	public java.lang.String getAccountEntryName() {
		return _licenseKey.getAccountEntryName();
	}

	/**
	* Sets the account entry name of this license key.
	*
	* @param accountEntryName the account entry name of this license key
	*/
	public void setAccountEntryName(java.lang.String accountEntryName) {
		_licenseKey.setAccountEntryName(accountEntryName);
	}

	/**
	* Returns the license entry name of this license key.
	*
	* @return the license entry name of this license key
	*/
	public java.lang.String getLicenseEntryName() {
		return _licenseKey.getLicenseEntryName();
	}

	/**
	* Sets the license entry name of this license key.
	*
	* @param licenseEntryName the license entry name of this license key
	*/
	public void setLicenseEntryName(java.lang.String licenseEntryName) {
		_licenseKey.setLicenseEntryName(licenseEntryName);
	}

	/**
	* Returns the license entry type of this license key.
	*
	* @return the license entry type of this license key
	*/
	public java.lang.String getLicenseEntryType() {
		return _licenseKey.getLicenseEntryType();
	}

	/**
	* Sets the license entry type of this license key.
	*
	* @param licenseEntryType the license entry type of this license key
	*/
	public void setLicenseEntryType(java.lang.String licenseEntryType) {
		_licenseKey.setLicenseEntryType(licenseEntryType);
	}

	/**
	* Returns the license version of this license key.
	*
	* @return the license version of this license key
	*/
	public int getLicenseVersion() {
		return _licenseKey.getLicenseVersion();
	}

	/**
	* Sets the license version of this license key.
	*
	* @param licenseVersion the license version of this license key
	*/
	public void setLicenseVersion(int licenseVersion) {
		_licenseKey.setLicenseVersion(licenseVersion);
	}

	/**
	* Returns the product entry name of this license key.
	*
	* @return the product entry name of this license key
	*/
	public java.lang.String getProductEntryName() {
		return _licenseKey.getProductEntryName();
	}

	/**
	* Sets the product entry name of this license key.
	*
	* @param productEntryName the product entry name of this license key
	*/
	public void setProductEntryName(java.lang.String productEntryName) {
		_licenseKey.setProductEntryName(productEntryName);
	}

	/**
	* Returns the product ID of this license key.
	*
	* @return the product ID of this license key
	*/
	public java.lang.String getProductId() {
		return _licenseKey.getProductId();
	}

	/**
	* Sets the product ID of this license key.
	*
	* @param productId the product ID of this license key
	*/
	public void setProductId(java.lang.String productId) {
		_licenseKey.setProductId(productId);
	}

	/**
	* Returns the product version of this license key.
	*
	* @return the product version of this license key
	*/
	public int getProductVersion() {
		return _licenseKey.getProductVersion();
	}

	/**
	* Sets the product version of this license key.
	*
	* @param productVersion the product version of this license key
	*/
	public void setProductVersion(int productVersion) {
		_licenseKey.setProductVersion(productVersion);
	}

	/**
	* Returns the product version label of this license key.
	*
	* @return the product version label of this license key
	*/
	public java.lang.String getProductVersionLabel() {
		return _licenseKey.getProductVersionLabel();
	}

	/**
	* Sets the product version label of this license key.
	*
	* @param productVersionLabel the product version label of this license key
	*/
	public void setProductVersionLabel(java.lang.String productVersionLabel) {
		_licenseKey.setProductVersionLabel(productVersionLabel);
	}

	/**
	* Returns the cluster ID of this license key.
	*
	* @return the cluster ID of this license key
	*/
	public long getClusterId() {
		return _licenseKey.getClusterId();
	}

	/**
	* Sets the cluster ID of this license key.
	*
	* @param clusterId the cluster ID of this license key
	*/
	public void setClusterId(long clusterId) {
		_licenseKey.setClusterId(clusterId);
	}

	/**
	* Returns the owner of this license key.
	*
	* @return the owner of this license key
	*/
	public java.lang.String getOwner() {
		return _licenseKey.getOwner();
	}

	/**
	* Sets the owner of this license key.
	*
	* @param owner the owner of this license key
	*/
	public void setOwner(java.lang.String owner) {
		_licenseKey.setOwner(owner);
	}

	/**
	* Returns the max servers of this license key.
	*
	* @return the max servers of this license key
	*/
	public int getMaxServers() {
		return _licenseKey.getMaxServers();
	}

	/**
	* Sets the max servers of this license key.
	*
	* @param maxServers the max servers of this license key
	*/
	public void setMaxServers(int maxServers) {
		_licenseKey.setMaxServers(maxServers);
	}

	/**
	* Returns the max concurrent users of this license key.
	*
	* @return the max concurrent users of this license key
	*/
	public long getMaxConcurrentUsers() {
		return _licenseKey.getMaxConcurrentUsers();
	}

	/**
	* Sets the max concurrent users of this license key.
	*
	* @param maxConcurrentUsers the max concurrent users of this license key
	*/
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_licenseKey.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	* Returns the max users of this license key.
	*
	* @return the max users of this license key
	*/
	public long getMaxUsers() {
		return _licenseKey.getMaxUsers();
	}

	/**
	* Sets the max users of this license key.
	*
	* @param maxUsers the max users of this license key
	*/
	public void setMaxUsers(long maxUsers) {
		_licenseKey.setMaxUsers(maxUsers);
	}

	/**
	* Returns the max http sessions of this license key.
	*
	* @return the max http sessions of this license key
	*/
	public int getMaxHttpSessions() {
		return _licenseKey.getMaxHttpSessions();
	}

	/**
	* Sets the max http sessions of this license key.
	*
	* @param maxHttpSessions the max http sessions of this license key
	*/
	public void setMaxHttpSessions(int maxHttpSessions) {
		_licenseKey.setMaxHttpSessions(maxHttpSessions);
	}

	/**
	* Returns the description of this license key.
	*
	* @return the description of this license key
	*/
	public java.lang.String getDescription() {
		return _licenseKey.getDescription();
	}

	/**
	* Sets the description of this license key.
	*
	* @param description the description of this license key
	*/
	public void setDescription(java.lang.String description) {
		_licenseKey.setDescription(description);
	}

	/**
	* Returns the host name of this license key.
	*
	* @return the host name of this license key
	*/
	public java.lang.String getHostName() {
		return _licenseKey.getHostName();
	}

	/**
	* Sets the host name of this license key.
	*
	* @param hostName the host name of this license key
	*/
	public void setHostName(java.lang.String hostName) {
		_licenseKey.setHostName(hostName);
	}

	/**
	* Returns the ip addresses of this license key.
	*
	* @return the ip addresses of this license key
	*/
	public java.lang.String getIpAddresses() {
		return _licenseKey.getIpAddresses();
	}

	/**
	* Sets the ip addresses of this license key.
	*
	* @param ipAddresses the ip addresses of this license key
	*/
	public void setIpAddresses(java.lang.String ipAddresses) {
		_licenseKey.setIpAddresses(ipAddresses);
	}

	/**
	* Returns the mac addresses of this license key.
	*
	* @return the mac addresses of this license key
	*/
	public java.lang.String getMacAddresses() {
		return _licenseKey.getMacAddresses();
	}

	/**
	* Sets the mac addresses of this license key.
	*
	* @param macAddresses the mac addresses of this license key
	*/
	public void setMacAddresses(java.lang.String macAddresses) {
		_licenseKey.setMacAddresses(macAddresses);
	}

	/**
	* Returns the server ID of this license key.
	*
	* @return the server ID of this license key
	*/
	public java.lang.String getServerId() {
		return _licenseKey.getServerId();
	}

	/**
	* Sets the server ID of this license key.
	*
	* @param serverId the server ID of this license key
	*/
	public void setServerId(java.lang.String serverId) {
		_licenseKey.setServerId(serverId);
	}

	/**
	* Returns the key of this license key.
	*
	* @return the key of this license key
	*/
	public java.lang.String getKey() {
		return _licenseKey.getKey();
	}

	/**
	* Sets the key of this license key.
	*
	* @param key the key of this license key
	*/
	public void setKey(java.lang.String key) {
		_licenseKey.setKey(key);
	}

	/**
	* Returns the start date of this license key.
	*
	* @return the start date of this license key
	*/
	public java.util.Date getStartDate() {
		return _licenseKey.getStartDate();
	}

	/**
	* Sets the start date of this license key.
	*
	* @param startDate the start date of this license key
	*/
	public void setStartDate(java.util.Date startDate) {
		_licenseKey.setStartDate(startDate);
	}

	/**
	* Returns the expiration date of this license key.
	*
	* @return the expiration date of this license key
	*/
	public java.util.Date getExpirationDate() {
		return _licenseKey.getExpirationDate();
	}

	/**
	* Sets the expiration date of this license key.
	*
	* @param expirationDate the expiration date of this license key
	*/
	public void setExpirationDate(java.util.Date expirationDate) {
		_licenseKey.setExpirationDate(expirationDate);
	}

	/**
	* Returns the additional info of this license key.
	*
	* @return the additional info of this license key
	*/
	public java.lang.String getAdditionalInfo() {
		return _licenseKey.getAdditionalInfo();
	}

	/**
	* Sets the additional info of this license key.
	*
	* @param additionalInfo the additional info of this license key
	*/
	public void setAdditionalInfo(java.lang.String additionalInfo) {
		_licenseKey.setAdditionalInfo(additionalInfo);
	}

	/**
	* Returns the complimentary of this license key.
	*
	* @return the complimentary of this license key
	*/
	public boolean getComplimentary() {
		return _licenseKey.getComplimentary();
	}

	/**
	* Returns <code>true</code> if this license key is complimentary.
	*
	* @return <code>true</code> if this license key is complimentary; <code>false</code> otherwise
	*/
	public boolean isComplimentary() {
		return _licenseKey.isComplimentary();
	}

	/**
	* Sets whether this license key is complimentary.
	*
	* @param complimentary the complimentary of this license key
	*/
	public void setComplimentary(boolean complimentary) {
		_licenseKey.setComplimentary(complimentary);
	}

	/**
	* Returns the active of this license key.
	*
	* @return the active of this license key
	*/
	public boolean getActive() {
		return _licenseKey.getActive();
	}

	/**
	* Returns <code>true</code> if this license key is active.
	*
	* @return <code>true</code> if this license key is active; <code>false</code> otherwise
	*/
	public boolean isActive() {
		return _licenseKey.isActive();
	}

	/**
	* Sets whether this license key is active.
	*
	* @param active the active of this license key
	*/
	public void setActive(boolean active) {
		_licenseKey.setActive(active);
	}

	public boolean isNew() {
		return _licenseKey.isNew();
	}

	public void setNew(boolean n) {
		_licenseKey.setNew(n);
	}

	public boolean isCachedModel() {
		return _licenseKey.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_licenseKey.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _licenseKey.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _licenseKey.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_licenseKey.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _licenseKey.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_licenseKey.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LicenseKeyWrapper((LicenseKey)_licenseKey.clone());
	}

	public int compareTo(com.liferay.osb.model.LicenseKey licenseKey) {
		return _licenseKey.compareTo(licenseKey);
	}

	@Override
	public int hashCode() {
		return _licenseKey.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.LicenseKey> toCacheModel() {
		return _licenseKey.toCacheModel();
	}

	public com.liferay.osb.model.LicenseKey toEscapedModel() {
		return new LicenseKeyWrapper(_licenseKey.toEscapedModel());
	}

	public com.liferay.osb.model.LicenseKey toUnescapedModel() {
		return new LicenseKeyWrapper(_licenseKey.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _licenseKey.toString();
	}

	public java.lang.String toXmlString() {
		return _licenseKey.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_licenseKey.persist();
	}

	public boolean canRenew()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.canRenew();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getAccountEntry();
	}

	public com.liferay.osb.model.LicenseEntry getLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getLicenseEntry();
	}

	public com.liferay.osb.model.LicenseKeySet getLicenseKeySet()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getLicenseKeySet();
	}

	public com.liferay.osb.model.OfferingEntry getOfferingEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getOfferingEntry();
	}

	public com.liferay.osb.model.OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getOrderEntry();
	}

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKey.getProductEntry();
	}

	public boolean isExpired() {
		return _licenseKey.isExpired();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseKeyWrapper)) {
			return false;
		}

		LicenseKeyWrapper licenseKeyWrapper = (LicenseKeyWrapper)obj;

		if (Validator.equals(_licenseKey, licenseKeyWrapper._licenseKey)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public LicenseKey getWrappedLicenseKey() {
		return _licenseKey;
	}

	public LicenseKey getWrappedModel() {
		return _licenseKey;
	}

	public void resetOriginalValues() {
		_licenseKey.resetOriginalValues();
	}

	private LicenseKey _licenseKey;
}