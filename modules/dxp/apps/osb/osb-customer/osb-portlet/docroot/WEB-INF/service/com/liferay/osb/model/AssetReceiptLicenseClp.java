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

import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetReceiptLicenseClp extends BaseModelImpl<AssetReceiptLicense>
	implements AssetReceiptLicense {
	public AssetReceiptLicenseClp() {
	}

	public Class<?> getModelClass() {
		return AssetReceiptLicense.class;
	}

	public String getModelClassName() {
		return AssetReceiptLicense.class.getName();
	}

	public long getPrimaryKey() {
		return _assetReceiptLicenseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetReceiptLicenseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetReceiptLicenseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_assetReceiptLicenseRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetReceiptLicenseId() {
		return _assetReceiptLicenseId;
	}

	public void setAssetReceiptLicenseId(long assetReceiptLicenseId) {
		_assetReceiptLicenseId = assetReceiptLicenseId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptLicenseId",
						long.class);

				method.invoke(_assetReceiptLicenseRemoteModel,
					assetReceiptLicenseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_assetReceiptLicenseRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetReceiptLicenseRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_assetReceiptLicenseRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetReceiptId() {
		return _assetReceiptId;
	}

	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptId = assetReceiptId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptId", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, assetReceiptId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetLicenseId() {
		return _assetLicenseId;
	}

	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicenseId = assetLicenseId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetLicenseId", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, assetLicenseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetEntryId", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, assetEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getOwnerClassNameId() {
		return _ownerClassNameId;
	}

	public void setOwnerClassNameId(long ownerClassNameId) {
		_ownerClassNameId = ownerClassNameId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerClassNameId",
						long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, ownerClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getOwnerClassPK() {
		return _ownerClassPK;
	}

	public void setOwnerClassPK(long ownerClassPK) {
		_ownerClassPK = ownerClassPK;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerClassPK", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, ownerClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProductClassNameId() {
		return _productClassNameId;
	}

	public void setProductClassNameId(long productClassNameId) {
		_productClassNameId = productClassNameId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassNameId",
						long.class);

				method.invoke(_assetReceiptLicenseRemoteModel,
					productClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProductClassPK() {
		return _productClassPK;
	}

	public void setProductClassPK(long productClassPK) {
		_productClassPK = productClassPK;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassPK", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, productClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getProductId() {
		return _productId;
	}

	public void setProductId(String productId) {
		_productId = productId;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setProductId", String.class);

				method.invoke(_assetReceiptLicenseRemoteModel, productId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_assetReceiptLicenseRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDate", Date.class);

				method.invoke(_assetReceiptLicenseRemoteModel, endDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getUsageType() {
		return _usageType;
	}

	public void setUsageType(int usageType) {
		_usageType = usageType;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setUsageType", int.class);

				method.invoke(_assetReceiptLicenseRemoteModel, usageType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getLicenseType() {
		return _licenseType;
	}

	public void setLicenseType(int licenseType) {
		_licenseType = licenseType;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseType", int.class);

				method.invoke(_assetReceiptLicenseRemoteModel, licenseType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getLicenseTypeAllotment() {
		return _licenseTypeAllotment;
	}

	public void setLicenseTypeAllotment(long licenseTypeAllotment) {
		_licenseTypeAllotment = licenseTypeAllotment;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseTypeAllotment",
						long.class);

				method.invoke(_assetReceiptLicenseRemoteModel,
					licenseTypeAllotment);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	public void setLicenseLifetime(long licenseLifetime) {
		_licenseLifetime = licenseLifetime;

		if (_assetReceiptLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseLifetime", long.class);

				method.invoke(_assetReceiptLicenseRemoteModel, licenseLifetime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.osb.model.AssetLicense getAssetLicense() {
		try {
			String methodName = "getAssetLicense";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AssetLicense returnObj = (com.liferay.osb.model.AssetLicense)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

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

	public boolean hasUnlimitedServers() {
		try {
			String methodName = "hasUnlimitedServers";

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

	public boolean isExpiring() {
		try {
			String methodName = "isExpiring";

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

	public java.lang.String getUsageTypeLabel() {
		try {
			String methodName = "getUsageTypeLabel";

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

	public int getAvailableLicenseKeyCount() {
		try {
			String methodName = "getAvailableLicenseKeyCount";

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

	public com.liferay.osb.model.AssetReceipt getAssetReceipt() {
		try {
			String methodName = "getAssetReceipt";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AssetReceipt returnObj = (com.liferay.osb.model.AssetReceipt)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public int getServers() {
		try {
			String methodName = "getServers";

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

	public long getMaxUsers() {
		try {
			String methodName = "getMaxUsers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry() {
		try {
			String methodName = "getAssetEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portlet.asset.model.AssetEntry returnObj = (com.liferay.portlet.asset.model.AssetEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long getRemainingTime() {
		try {
			String methodName = "getRemainingTime";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasAvailableLicenseKeys() {
		try {
			String methodName = "hasAvailableLicenseKeys";

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

	public long getRemainingDays() {
		try {
			String methodName = "getRemainingDays";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAssetReceiptLicenseRemoteModel() {
		return _assetReceiptLicenseRemoteModel;
	}

	public void setAssetReceiptLicenseRemoteModel(
		BaseModel<?> assetReceiptLicenseRemoteModel) {
		_assetReceiptLicenseRemoteModel = assetReceiptLicenseRemoteModel;
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

		Class<?> remoteModelClass = _assetReceiptLicenseRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetReceiptLicenseRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetReceiptLicenseLocalServiceUtil.addAssetReceiptLicense(this);
		}
		else {
			AssetReceiptLicenseLocalServiceUtil.updateAssetReceiptLicense(this);
		}
	}

	@Override
	public AssetReceiptLicense toEscapedModel() {
		return (AssetReceiptLicense)ProxyUtil.newProxyInstance(AssetReceiptLicense.class.getClassLoader(),
			new Class[] { AssetReceiptLicense.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetReceiptLicense toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetReceiptLicenseClp clone = new AssetReceiptLicenseClp();

		clone.setUuid(getUuid());
		clone.setAssetReceiptLicenseId(getAssetReceiptLicenseId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAssetReceiptId(getAssetReceiptId());
		clone.setAssetLicenseId(getAssetLicenseId());
		clone.setAssetEntryId(getAssetEntryId());
		clone.setOwnerClassNameId(getOwnerClassNameId());
		clone.setOwnerClassPK(getOwnerClassPK());
		clone.setProductClassNameId(getProductClassNameId());
		clone.setProductClassPK(getProductClassPK());
		clone.setProductId(getProductId());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setUsageType(getUsageType());
		clone.setLicenseType(getLicenseType());
		clone.setLicenseTypeAllotment(getLicenseTypeAllotment());
		clone.setLicenseLifetime(getLicenseLifetime());

		return clone;
	}

	public int compareTo(AssetReceiptLicense assetReceiptLicense) {
		long primaryKey = assetReceiptLicense.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetReceiptLicenseClp)) {
			return false;
		}

		AssetReceiptLicenseClp assetReceiptLicense = (AssetReceiptLicenseClp)obj;

		long primaryKey = assetReceiptLicense.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", assetReceiptLicenseId=");
		sb.append(getAssetReceiptLicenseId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", assetReceiptId=");
		sb.append(getAssetReceiptId());
		sb.append(", assetLicenseId=");
		sb.append(getAssetLicenseId());
		sb.append(", assetEntryId=");
		sb.append(getAssetEntryId());
		sb.append(", ownerClassNameId=");
		sb.append(getOwnerClassNameId());
		sb.append(", ownerClassPK=");
		sb.append(getOwnerClassPK());
		sb.append(", productClassNameId=");
		sb.append(getProductClassNameId());
		sb.append(", productClassPK=");
		sb.append(getProductClassPK());
		sb.append(", productId=");
		sb.append(getProductId());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", usageType=");
		sb.append(getUsageType());
		sb.append(", licenseType=");
		sb.append(getLicenseType());
		sb.append(", licenseTypeAllotment=");
		sb.append(getLicenseTypeAllotment());
		sb.append(", licenseLifetime=");
		sb.append(getLicenseLifetime());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetReceiptLicense");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetReceiptLicenseId</column-name><column-value><![CDATA[");
		sb.append(getAssetReceiptLicenseId());
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
			"<column><column-name>assetReceiptId</column-name><column-value><![CDATA[");
		sb.append(getAssetReceiptId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetLicenseId</column-name><column-value><![CDATA[");
		sb.append(getAssetLicenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ownerClassNameId</column-name><column-value><![CDATA[");
		sb.append(getOwnerClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ownerClassPK</column-name><column-value><![CDATA[");
		sb.append(getOwnerClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productClassNameId</column-name><column-value><![CDATA[");
		sb.append(getProductClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productClassPK</column-name><column-value><![CDATA[");
		sb.append(getProductClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productId</column-name><column-value><![CDATA[");
		sb.append(getProductId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>usageType</column-name><column-value><![CDATA[");
		sb.append(getUsageType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseType</column-name><column-value><![CDATA[");
		sb.append(getLicenseType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseTypeAllotment</column-name><column-value><![CDATA[");
		sb.append(getLicenseTypeAllotment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseLifetime</column-name><column-value><![CDATA[");
		sb.append(getLicenseLifetime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _assetReceiptLicenseId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetReceiptId;
	private long _assetLicenseId;
	private long _assetEntryId;
	private long _ownerClassNameId;
	private long _ownerClassPK;
	private long _productClassNameId;
	private long _productClassPK;
	private String _productId;
	private Date _startDate;
	private Date _endDate;
	private int _usageType;
	private int _licenseType;
	private long _licenseTypeAllotment;
	private long _licenseLifetime;
	private BaseModel<?> _assetReceiptLicenseRemoteModel;
}