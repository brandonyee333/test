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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class LicenseKeyClp extends BaseModelImpl<LicenseKey>
	implements LicenseKey {
	public LicenseKeyClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return LicenseKey.class;
	}

	@Override
	public String getModelClassName() {
		return LicenseKey.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _licenseKeyId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLicenseKeyId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseKeyId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getLicenseKeyId() {
		return _licenseKeyId;
	}

	@Override
	public void setLicenseKeyId(long licenseKeyId) {
		_licenseKeyId = licenseKeyId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseKeyId", long.class);

				method.invoke(_licenseKeyRemoteModel, licenseKeyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_licenseKeyRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_licenseKeyRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_licenseKeyRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserId", long.class);

				method.invoke(_licenseKeyRemoteModel, modifiedUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getModifiedUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getModifiedUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setModifiedUserUuid(String modifiedUserUuid) {
	}

	@Override
	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	@Override
	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedUserName",
						String.class);

				method.invoke(_licenseKeyRemoteModel, modifiedUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_licenseKeyRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLicenseKeySetId() {
		return _licenseKeySetId;
	}

	@Override
	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKeySetId = licenseKeySetId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseKeySetId", long.class);

				method.invoke(_licenseKeyRemoteModel, licenseKeySetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAssetReceiptLicenseId() {
		return _assetReceiptLicenseId;
	}

	@Override
	public void setAssetReceiptLicenseId(long assetReceiptLicenseId) {
		_assetReceiptLicenseId = assetReceiptLicenseId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptLicenseId",
						long.class);

				method.invoke(_licenseKeyRemoteModel, assetReceiptLicenseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_licenseKeyRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOrderEntryId() {
		return _orderEntryId;
	}

	@Override
	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setOrderEntryId", long.class);

				method.invoke(_licenseKeyRemoteModel, orderEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOfferingEntryId() {
		return _offeringEntryId;
	}

	@Override
	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntryId = offeringEntryId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setOfferingEntryId", long.class);

				method.invoke(_licenseKeyRemoteModel, offeringEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLicenseEntryId() {
		return _licenseEntryId;
	}

	@Override
	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntryId = licenseEntryId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseEntryId", long.class);

				method.invoke(_licenseKeyRemoteModel, licenseEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getProductEntryId() {
		return _productEntryId;
	}

	@Override
	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryId", long.class);

				method.invoke(_licenseKeyRemoteModel, productEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSupportResponseId() {
		return _supportResponseId;
	}

	@Override
	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportResponseId",
						long.class);

				method.invoke(_licenseKeyRemoteModel, supportResponseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccountEntryName() {
		return _accountEntryName;
	}

	@Override
	public void setAccountEntryName(String accountEntryName) {
		_accountEntryName = accountEntryName;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryName",
						String.class);

				method.invoke(_licenseKeyRemoteModel, accountEntryName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLicenseEntryName() {
		return _licenseEntryName;
	}

	@Override
	public void setLicenseEntryName(String licenseEntryName) {
		_licenseEntryName = licenseEntryName;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseEntryName",
						String.class);

				method.invoke(_licenseKeyRemoteModel, licenseEntryName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLicenseEntryType() {
		return _licenseEntryType;
	}

	@Override
	public void setLicenseEntryType(String licenseEntryType) {
		_licenseEntryType = licenseEntryType;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseEntryType",
						String.class);

				method.invoke(_licenseKeyRemoteModel, licenseEntryType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getLicenseVersion() {
		return _licenseVersion;
	}

	@Override
	public void setLicenseVersion(int licenseVersion) {
		_licenseVersion = licenseVersion;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseVersion", int.class);

				method.invoke(_licenseKeyRemoteModel, licenseVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProductEntryName() {
		return _productEntryName;
	}

	@Override
	public void setProductEntryName(String productEntryName) {
		_productEntryName = productEntryName;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryName",
						String.class);

				method.invoke(_licenseKeyRemoteModel, productEntryName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProductId() {
		return _productId;
	}

	@Override
	public void setProductId(String productId) {
		_productId = productId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setProductId", String.class);

				method.invoke(_licenseKeyRemoteModel, productId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getProductVersion() {
		return _productVersion;
	}

	@Override
	public void setProductVersion(int productVersion) {
		_productVersion = productVersion;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setProductVersion", int.class);

				method.invoke(_licenseKeyRemoteModel, productVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProductVersionLabel() {
		return _productVersionLabel;
	}

	@Override
	public void setProductVersionLabel(String productVersionLabel) {
		_productVersionLabel = productVersionLabel;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setProductVersionLabel",
						String.class);

				method.invoke(_licenseKeyRemoteModel, productVersionLabel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClusterId() {
		return _clusterId;
	}

	@Override
	public void setClusterId(long clusterId) {
		_clusterId = clusterId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setClusterId", long.class);

				method.invoke(_licenseKeyRemoteModel, clusterId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOwner() {
		return _owner;
	}

	@Override
	public void setOwner(String owner) {
		_owner = owner;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setOwner", String.class);

				method.invoke(_licenseKeyRemoteModel, owner);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getMaxServers() {
		return _maxServers;
	}

	@Override
	public void setMaxServers(int maxServers) {
		_maxServers = maxServers;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxServers", int.class);

				method.invoke(_licenseKeyRemoteModel, maxServers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMaxConcurrentUsers() {
		return _maxConcurrentUsers;
	}

	@Override
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_maxConcurrentUsers = maxConcurrentUsers;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxConcurrentUsers",
						long.class);

				method.invoke(_licenseKeyRemoteModel, maxConcurrentUsers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMaxUsers() {
		return _maxUsers;
	}

	@Override
	public void setMaxUsers(long maxUsers) {
		_maxUsers = maxUsers;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxUsers", long.class);

				method.invoke(_licenseKeyRemoteModel, maxUsers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getMaxHttpSessions() {
		return _maxHttpSessions;
	}

	@Override
	public void setMaxHttpSessions(int maxHttpSessions) {
		_maxHttpSessions = maxHttpSessions;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxHttpSessions", int.class);

				method.invoke(_licenseKeyRemoteModel, maxHttpSessions);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_licenseKeyRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getHostName() {
		return _hostName;
	}

	@Override
	public void setHostName(String hostName) {
		_hostName = hostName;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setHostName", String.class);

				method.invoke(_licenseKeyRemoteModel, hostName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getIpAddresses() {
		return _ipAddresses;
	}

	@Override
	public void setIpAddresses(String ipAddresses) {
		_ipAddresses = ipAddresses;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setIpAddresses", String.class);

				method.invoke(_licenseKeyRemoteModel, ipAddresses);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMacAddresses() {
		return _macAddresses;
	}

	@Override
	public void setMacAddresses(String macAddresses) {
		_macAddresses = macAddresses;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setMacAddresses", String.class);

				method.invoke(_licenseKeyRemoteModel, macAddresses);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getServerId() {
		return _serverId;
	}

	@Override
	public void setServerId(String serverId) {
		_serverId = serverId;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setServerId", String.class);

				method.invoke(_licenseKeyRemoteModel, serverId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public void setKey(String key) {
		_key = key;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_licenseKeyRemoteModel, key);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_licenseKeyRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setExpirationDate", Date.class);

				method.invoke(_licenseKeyRemoteModel, expirationDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAdditionalInfo() {
		return _additionalInfo;
	}

	@Override
	public void setAdditionalInfo(String additionalInfo) {
		_additionalInfo = additionalInfo;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setAdditionalInfo",
						String.class);

				method.invoke(_licenseKeyRemoteModel, additionalInfo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getComplimentary() {
		return _complimentary;
	}

	@Override
	public boolean isComplimentary() {
		return _complimentary;
	}

	@Override
	public void setComplimentary(boolean complimentary) {
		_complimentary = complimentary;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setComplimentary",
						boolean.class);

				method.invoke(_licenseKeyRemoteModel, complimentary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;

		if (_licenseKeyRemoteModel != null) {
			try {
				Class<?> clazz = _licenseKeyRemoteModel.getClass();

				Method method = clazz.getMethod("setActive", boolean.class);

				method.invoke(_licenseKeyRemoteModel, active);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean canRenew() {
		try {
			String methodName = "canRenew";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public AccountEntry getAccountEntry() {
		try {
			String methodName = "getAccountEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			AccountEntry returnObj = (AccountEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public LicenseEntry getLicenseEntry() {
		try {
			String methodName = "getLicenseEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			LicenseEntry returnObj = (LicenseEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public LicenseKeySet getLicenseKeySet() {
		try {
			String methodName = "getLicenseKeySet";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			LicenseKeySet returnObj = (LicenseKeySet)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public OfferingEntry getOfferingEntry() {
		try {
			String methodName = "getOfferingEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			OfferingEntry returnObj = (OfferingEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public OrderEntry getOrderEntry() {
		try {
			String methodName = "getOrderEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			OrderEntry returnObj = (OrderEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public ProductEntry getProductEntry() {
		try {
			String methodName = "getProductEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			ProductEntry returnObj = (ProductEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isExpired() {
		try {
			String methodName = "isExpired";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getLicenseKeyRemoteModel() {
		return _licenseKeyRemoteModel;
	}

	public void setLicenseKeyRemoteModel(BaseModel<?> licenseKeyRemoteModel) {
		_licenseKeyRemoteModel = licenseKeyRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _licenseKeyRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_licenseKeyRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			LicenseKeyLocalServiceUtil.addLicenseKey(this);
		}
		else {
			LicenseKeyLocalServiceUtil.updateLicenseKey(this);
		}
	}

	@Override
	public LicenseKey toEscapedModel() {
		return (LicenseKey)ProxyUtil.newProxyInstance(LicenseKey.class.getClassLoader(),
			new Class[] { LicenseKey.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		LicenseKeyClp clone = new LicenseKeyClp();

		clone.setLicenseKeyId(getLicenseKeyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setLicenseKeySetId(getLicenseKeySetId());
		clone.setAssetReceiptLicenseId(getAssetReceiptLicenseId());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setOrderEntryId(getOrderEntryId());
		clone.setOfferingEntryId(getOfferingEntryId());
		clone.setLicenseEntryId(getLicenseEntryId());
		clone.setProductEntryId(getProductEntryId());
		clone.setSupportResponseId(getSupportResponseId());
		clone.setAccountEntryName(getAccountEntryName());
		clone.setLicenseEntryName(getLicenseEntryName());
		clone.setLicenseEntryType(getLicenseEntryType());
		clone.setLicenseVersion(getLicenseVersion());
		clone.setProductEntryName(getProductEntryName());
		clone.setProductId(getProductId());
		clone.setProductVersion(getProductVersion());
		clone.setProductVersionLabel(getProductVersionLabel());
		clone.setClusterId(getClusterId());
		clone.setOwner(getOwner());
		clone.setMaxServers(getMaxServers());
		clone.setMaxConcurrentUsers(getMaxConcurrentUsers());
		clone.setMaxUsers(getMaxUsers());
		clone.setMaxHttpSessions(getMaxHttpSessions());
		clone.setDescription(getDescription());
		clone.setHostName(getHostName());
		clone.setIpAddresses(getIpAddresses());
		clone.setMacAddresses(getMacAddresses());
		clone.setServerId(getServerId());
		clone.setKey(getKey());
		clone.setStartDate(getStartDate());
		clone.setExpirationDate(getExpirationDate());
		clone.setAdditionalInfo(getAdditionalInfo());
		clone.setComplimentary(getComplimentary());
		clone.setActive(getActive());

		return clone;
	}

	@Override
	public int compareTo(LicenseKey licenseKey) {
		int value = 0;

		if (getActive() == licenseKey.getActive()) {
			value = -1;
		}
		else if (getActive() != licenseKey.getActive()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		if (getLicenseKeyId() < licenseKey.getLicenseKeyId()) {
			value = -1;
		}
		else if (getLicenseKeyId() > licenseKey.getLicenseKeyId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseKeyClp)) {
			return false;
		}

		LicenseKeyClp licenseKey = (LicenseKeyClp)obj;

		long primaryKey = licenseKey.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(81);

		sb.append("{licenseKeyId=");
		sb.append(getLicenseKeyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedUserId=");
		sb.append(getModifiedUserId());
		sb.append(", modifiedUserName=");
		sb.append(getModifiedUserName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", licenseKeySetId=");
		sb.append(getLicenseKeySetId());
		sb.append(", assetReceiptLicenseId=");
		sb.append(getAssetReceiptLicenseId());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", orderEntryId=");
		sb.append(getOrderEntryId());
		sb.append(", offeringEntryId=");
		sb.append(getOfferingEntryId());
		sb.append(", licenseEntryId=");
		sb.append(getLicenseEntryId());
		sb.append(", productEntryId=");
		sb.append(getProductEntryId());
		sb.append(", supportResponseId=");
		sb.append(getSupportResponseId());
		sb.append(", accountEntryName=");
		sb.append(getAccountEntryName());
		sb.append(", licenseEntryName=");
		sb.append(getLicenseEntryName());
		sb.append(", licenseEntryType=");
		sb.append(getLicenseEntryType());
		sb.append(", licenseVersion=");
		sb.append(getLicenseVersion());
		sb.append(", productEntryName=");
		sb.append(getProductEntryName());
		sb.append(", productId=");
		sb.append(getProductId());
		sb.append(", productVersion=");
		sb.append(getProductVersion());
		sb.append(", productVersionLabel=");
		sb.append(getProductVersionLabel());
		sb.append(", clusterId=");
		sb.append(getClusterId());
		sb.append(", owner=");
		sb.append(getOwner());
		sb.append(", maxServers=");
		sb.append(getMaxServers());
		sb.append(", maxConcurrentUsers=");
		sb.append(getMaxConcurrentUsers());
		sb.append(", maxUsers=");
		sb.append(getMaxUsers());
		sb.append(", maxHttpSessions=");
		sb.append(getMaxHttpSessions());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", hostName=");
		sb.append(getHostName());
		sb.append(", ipAddresses=");
		sb.append(getIpAddresses());
		sb.append(", macAddresses=");
		sb.append(getMacAddresses());
		sb.append(", serverId=");
		sb.append(getServerId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", expirationDate=");
		sb.append(getExpirationDate());
		sb.append(", additionalInfo=");
		sb.append(getAdditionalInfo());
		sb.append(", complimentary=");
		sb.append(getComplimentary());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(124);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.LicenseKey");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>licenseKeyId</column-name><column-value><![CDATA[");
		sb.append(getLicenseKeyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserId</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserName</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseKeySetId</column-name><column-value><![CDATA[");
		sb.append(getLicenseKeySetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetReceiptLicenseId</column-name><column-value><![CDATA[");
		sb.append(getAssetReceiptLicenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderEntryId</column-name><column-value><![CDATA[");
		sb.append(getOrderEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>offeringEntryId</column-name><column-value><![CDATA[");
		sb.append(getOfferingEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseEntryId</column-name><column-value><![CDATA[");
		sb.append(getLicenseEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productEntryId</column-name><column-value><![CDATA[");
		sb.append(getProductEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportResponseId</column-name><column-value><![CDATA[");
		sb.append(getSupportResponseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountEntryName</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseEntryName</column-name><column-value><![CDATA[");
		sb.append(getLicenseEntryName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseEntryType</column-name><column-value><![CDATA[");
		sb.append(getLicenseEntryType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseVersion</column-name><column-value><![CDATA[");
		sb.append(getLicenseVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productEntryName</column-name><column-value><![CDATA[");
		sb.append(getProductEntryName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productId</column-name><column-value><![CDATA[");
		sb.append(getProductId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productVersion</column-name><column-value><![CDATA[");
		sb.append(getProductVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productVersionLabel</column-name><column-value><![CDATA[");
		sb.append(getProductVersionLabel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clusterId</column-name><column-value><![CDATA[");
		sb.append(getClusterId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>owner</column-name><column-value><![CDATA[");
		sb.append(getOwner());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxServers</column-name><column-value><![CDATA[");
		sb.append(getMaxServers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxConcurrentUsers</column-name><column-value><![CDATA[");
		sb.append(getMaxConcurrentUsers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxUsers</column-name><column-value><![CDATA[");
		sb.append(getMaxUsers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxHttpSessions</column-name><column-value><![CDATA[");
		sb.append(getMaxHttpSessions());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hostName</column-name><column-value><![CDATA[");
		sb.append(getHostName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ipAddresses</column-name><column-value><![CDATA[");
		sb.append(getIpAddresses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>macAddresses</column-name><column-value><![CDATA[");
		sb.append(getMacAddresses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serverId</column-name><column-value><![CDATA[");
		sb.append(getServerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expirationDate</column-name><column-value><![CDATA[");
		sb.append(getExpirationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>additionalInfo</column-name><column-value><![CDATA[");
		sb.append(getAdditionalInfo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>complimentary</column-name><column-value><![CDATA[");
		sb.append(getComplimentary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _licenseKeyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _licenseKeySetId;
	private long _assetReceiptLicenseId;
	private long _accountEntryId;
	private long _orderEntryId;
	private long _offeringEntryId;
	private long _licenseEntryId;
	private long _productEntryId;
	private long _supportResponseId;
	private String _accountEntryName;
	private String _licenseEntryName;
	private String _licenseEntryType;
	private int _licenseVersion;
	private String _productEntryName;
	private String _productId;
	private int _productVersion;
	private String _productVersionLabel;
	private long _clusterId;
	private String _owner;
	private int _maxServers;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private int _maxHttpSessions;
	private String _description;
	private String _hostName;
	private String _ipAddresses;
	private String _macAddresses;
	private String _serverId;
	private String _key;
	private Date _startDate;
	private Date _expirationDate;
	private String _additionalInfo;
	private boolean _complimentary;
	private boolean _active;
	private BaseModel<?> _licenseKeyRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}