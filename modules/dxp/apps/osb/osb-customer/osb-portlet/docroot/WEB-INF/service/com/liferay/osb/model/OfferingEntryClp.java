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
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;

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
public class OfferingEntryClp extends BaseModelImpl<OfferingEntry>
	implements OfferingEntry {
	public OfferingEntryClp() {
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
	public long getPrimaryKey() {
		return _offeringEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOfferingEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _offeringEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getOfferingEntryId() {
		return _offeringEntryId;
	}

	@Override
	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntryId = offeringEntryId;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOfferingEntryId", long.class);

				method.invoke(_offeringEntryRemoteModel, offeringEntryId);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_offeringEntryRemoteModel, userId);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_offeringEntryRemoteModel, userName);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_offeringEntryRemoteModel, createDate);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_offeringEntryRemoteModel, modifiedDate);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_offeringEntryRemoteModel, accountEntryId);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOrderEntryId", long.class);

				method.invoke(_offeringEntryRemoteModel, orderEntryId);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryId", long.class);

				method.invoke(_offeringEntryRemoteModel, productEntryId);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportResponseId",
						long.class);

				method.invoke(_offeringEntryRemoteModel, supportResponseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProductDescription() {
		return _productDescription;
	}

	@Override
	public void setProductDescription(String productDescription) {
		_productDescription = productDescription;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductDescription",
						String.class);

				method.invoke(_offeringEntryRemoteModel, productDescription);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_offeringEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getVersion() {
		return _version;
	}

	@Override
	public void setVersion(int version) {
		_version = version;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", int.class);

				method.invoke(_offeringEntryRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPlatform() {
		return _platform;
	}

	@Override
	public void setPlatform(String platform) {
		_platform = platform;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPlatform", String.class);

				method.invoke(_offeringEntryRemoteModel, platform);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPlatformVersion() {
		return _platformVersion;
	}

	@Override
	public void setPlatformVersion(String platformVersion) {
		_platformVersion = platformVersion;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPlatformVersion",
						String.class);

				method.invoke(_offeringEntryRemoteModel, platformVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getLicenses() {
		return _licenses;
	}

	@Override
	public boolean isLicenses() {
		return _licenses;
	}

	@Override
	public void setLicenses(boolean licenses) {
		_licenses = licenses;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenses", boolean.class);

				method.invoke(_offeringEntryRemoteModel, licenses);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	@Override
	public void setLicenseLifetime(long licenseLifetime) {
		_licenseLifetime = licenseLifetime;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseLifetime", long.class);

				method.invoke(_offeringEntryRemoteModel, licenseLifetime);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxConcurrentUsers",
						long.class);

				method.invoke(_offeringEntryRemoteModel, maxConcurrentUsers);
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

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxUsers", long.class);

				method.invoke(_offeringEntryRemoteModel, maxUsers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getSupportTickets() {
		return _supportTickets;
	}

	@Override
	public boolean isSupportTickets() {
		return _supportTickets;
	}

	@Override
	public void setSupportTickets(boolean supportTickets) {
		_supportTickets = supportTickets;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportTickets",
						boolean.class);

				method.invoke(_offeringEntryRemoteModel, supportTickets);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSupportLifetime() {
		return _supportLifetime;
	}

	@Override
	public void setSupportLifetime(long supportLifetime) {
		_supportLifetime = supportLifetime;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportLifetime", long.class);

				method.invoke(_offeringEntryRemoteModel, supportLifetime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	@Override
	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportEndDate", Date.class);

				method.invoke(_offeringEntryRemoteModel, supportEndDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSizing() {
		return _sizing;
	}

	@Override
	public void setSizing(int sizing) {
		_sizing = sizing;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSizing", int.class);

				method.invoke(_offeringEntryRemoteModel, sizing);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setQuantity", int.class);

				method.invoke(_offeringEntryRemoteModel, quantity);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_offeringEntryRemoteModel != null) {
			try {
				Class<?> clazz = _offeringEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_offeringEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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
	public Date getActualStartDate() {
		try {
			String methodName = "getActualStartDate";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Date returnObj = (Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getAvailableServers() {
		try {
			String methodName = "getAvailableServers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getKey() {
		try {
			String methodName = "getKey";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<LicenseKey> getLicenseKeys() {
		try {
			String methodName = "getLicenseKeys";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<LicenseKey> returnObj = (java.util.List<LicenseKey>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getLicenseKeysCount() {
		try {
			String methodName = "getLicenseKeysCount";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public OfferingEntryGroup getOfferingEntryGroup() {
		try {
			String methodName = "getOfferingEntryGroup";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			OfferingEntryGroup returnObj = (OfferingEntryGroup)invokeOnRemoteModel(methodName,
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
	public java.lang.String getSizingLabel() {
		try {
			String methodName = "getSizingLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public Date getStartDate() {
		try {
			String methodName = "getStartDate";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Date returnObj = (Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getStatusLabel() {
		try {
			String methodName = "getStatusLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public SupportResponse getSupportResponse() {
		try {
			String methodName = "getSupportResponse";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			SupportResponse returnObj = (SupportResponse)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getTypeLabel() {
		try {
			String methodName = "getTypeLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isInformationalOnly() {
		try {
			String methodName = "isInformationalOnly";

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
	public void setInformationalOnly(boolean informationalOnly) {
		try {
			String methodName = "setInformationalOnly";

			Class<?>[] parameterTypes = new Class<?>[] { boolean.class };

			Object[] parameterValues = new Object[] { informationalOnly };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getOfferingEntryRemoteModel() {
		return _offeringEntryRemoteModel;
	}

	public void setOfferingEntryRemoteModel(
		BaseModel<?> offeringEntryRemoteModel) {
		_offeringEntryRemoteModel = offeringEntryRemoteModel;
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

		Class<?> remoteModelClass = _offeringEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_offeringEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			OfferingEntryLocalServiceUtil.addOfferingEntry(this);
		}
		else {
			OfferingEntryLocalServiceUtil.updateOfferingEntry(this);
		}
	}

	@Override
	public OfferingEntry toEscapedModel() {
		return (OfferingEntry)ProxyUtil.newProxyInstance(OfferingEntry.class.getClassLoader(),
			new Class[] { OfferingEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OfferingEntryClp clone = new OfferingEntryClp();

		clone.setOfferingEntryId(getOfferingEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setOrderEntryId(getOrderEntryId());
		clone.setProductEntryId(getProductEntryId());
		clone.setSupportResponseId(getSupportResponseId());
		clone.setProductDescription(getProductDescription());
		clone.setType(getType());
		clone.setVersion(getVersion());
		clone.setPlatform(getPlatform());
		clone.setPlatformVersion(getPlatformVersion());
		clone.setLicenses(getLicenses());
		clone.setLicenseLifetime(getLicenseLifetime());
		clone.setMaxConcurrentUsers(getMaxConcurrentUsers());
		clone.setMaxUsers(getMaxUsers());
		clone.setSupportTickets(getSupportTickets());
		clone.setSupportLifetime(getSupportLifetime());
		clone.setSupportEndDate(getSupportEndDate());
		clone.setSizing(getSizing());
		clone.setQuantity(getQuantity());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(OfferingEntry offeringEntry) {
		int value = 0;

		if (getOfferingEntryId() < offeringEntry.getOfferingEntryId()) {
			value = -1;
		}
		else if (getOfferingEntryId() > offeringEntry.getOfferingEntryId()) {
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

		if (!(obj instanceof OfferingEntryClp)) {
			return false;
		}

		OfferingEntryClp offeringEntry = (OfferingEntryClp)obj;

		long primaryKey = offeringEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(49);

		sb.append("{offeringEntryId=");
		sb.append(getOfferingEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", orderEntryId=");
		sb.append(getOrderEntryId());
		sb.append(", productEntryId=");
		sb.append(getProductEntryId());
		sb.append(", supportResponseId=");
		sb.append(getSupportResponseId());
		sb.append(", productDescription=");
		sb.append(getProductDescription());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", platform=");
		sb.append(getPlatform());
		sb.append(", platformVersion=");
		sb.append(getPlatformVersion());
		sb.append(", licenses=");
		sb.append(getLicenses());
		sb.append(", licenseLifetime=");
		sb.append(getLicenseLifetime());
		sb.append(", maxConcurrentUsers=");
		sb.append(getMaxConcurrentUsers());
		sb.append(", maxUsers=");
		sb.append(getMaxUsers());
		sb.append(", supportTickets=");
		sb.append(getSupportTickets());
		sb.append(", supportLifetime=");
		sb.append(getSupportLifetime());
		sb.append(", supportEndDate=");
		sb.append(getSupportEndDate());
		sb.append(", sizing=");
		sb.append(getSizing());
		sb.append(", quantity=");
		sb.append(getQuantity());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(76);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.OfferingEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>offeringEntryId</column-name><column-value><![CDATA[");
		sb.append(getOfferingEntryId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
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
			"<column><column-name>productEntryId</column-name><column-value><![CDATA[");
		sb.append(getProductEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportResponseId</column-name><column-value><![CDATA[");
		sb.append(getSupportResponseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productDescription</column-name><column-value><![CDATA[");
		sb.append(getProductDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>platform</column-name><column-value><![CDATA[");
		sb.append(getPlatform());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>platformVersion</column-name><column-value><![CDATA[");
		sb.append(getPlatformVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenses</column-name><column-value><![CDATA[");
		sb.append(getLicenses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseLifetime</column-name><column-value><![CDATA[");
		sb.append(getLicenseLifetime());
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
			"<column><column-name>supportTickets</column-name><column-value><![CDATA[");
		sb.append(getSupportTickets());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportLifetime</column-name><column-value><![CDATA[");
		sb.append(getSupportLifetime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportEndDate</column-name><column-value><![CDATA[");
		sb.append(getSupportEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sizing</column-name><column-value><![CDATA[");
		sb.append(getSizing());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quantity</column-name><column-value><![CDATA[");
		sb.append(getQuantity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _offeringEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _orderEntryId;
	private long _productEntryId;
	private long _supportResponseId;
	private String _productDescription;
	private int _type;
	private int _version;
	private String _platform;
	private String _platformVersion;
	private boolean _licenses;
	private long _licenseLifetime;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private boolean _supportTickets;
	private long _supportLifetime;
	private Date _supportEndDate;
	private int _sizing;
	private int _quantity;
	private int _status;
	private BaseModel<?> _offeringEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}