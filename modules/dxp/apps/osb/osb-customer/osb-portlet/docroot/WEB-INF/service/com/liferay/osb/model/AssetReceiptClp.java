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

import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
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
public class AssetReceiptClp extends BaseModelImpl<AssetReceipt>
	implements AssetReceipt {
	public AssetReceiptClp() {
	}

	public Class<?> getModelClass() {
		return AssetReceipt.class;
	}

	public String getModelClassName() {
		return AssetReceipt.class.getName();
	}

	public long getPrimaryKey() {
		return _assetReceiptId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetReceiptId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetReceiptId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public long getAssetReceiptId() {
		return _assetReceiptId;
	}

	public void setAssetReceiptId(long assetReceiptId) {
		_assetReceiptId = assetReceiptId;

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptId", long.class);

				method.invoke(_assetReceiptRemoteModel, assetReceiptId);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetReceiptRemoteModel, userId);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_assetReceiptRemoteModel, userName);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetReceiptRemoteModel, createDate);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetEntryId", long.class);

				method.invoke(_assetReceiptRemoteModel, assetEntryId);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerClassNameId",
						long.class);

				method.invoke(_assetReceiptRemoteModel, ownerClassNameId);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerClassPK", long.class);

				method.invoke(_assetReceiptRemoteModel, ownerClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setLegalEntityName",
						String.class);

				method.invoke(_assetReceiptRemoteModel, legalEntityName);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassNameId",
						long.class);

				method.invoke(_assetReceiptRemoteModel, productClassNameId);
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

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassPK", long.class);

				method.invoke(_assetReceiptRemoteModel, productClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getType() {
		return _type;
	}

	public void setType(long type) {
		_type = type;

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setType", long.class);

				method.invoke(_assetReceiptRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyEntryId", long.class);

				method.invoke(_assetReceiptRemoteModel, currencyEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getActualPrice() {
		return _actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		_actualPrice = actualPrice;

		if (_assetReceiptRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptRemoteModel.getClass();

				Method method = clazz.getMethod("setActualPrice", double.class);

				method.invoke(_assetReceiptRemoteModel, actualPrice);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getFormalLegalEntityName() {
		try {
			String methodName = "getFormalLegalEntityName";

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

	public boolean hasActiveAssetReceiptLicenses(int usageType) {
		try {
			String methodName = "hasActiveAssetReceiptLicenses";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getAssetReceiptLicensesEndDate(int usageType) {
		try {
			String methodName = "getAssetReceiptLicensesEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getRenewedAssetReceiptLicensesEndDate(int usageType) {
		try {
			String methodName = "getRenewedAssetReceiptLicensesEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getActiveAssetReceiptLicensesEndDate(int usageType) {
		try {
			String methodName = "getActiveAssetReceiptLicensesEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getOwnerClassName() {
		try {
			String methodName = "getOwnerClassName";

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

	public java.lang.String getOwnerName() {
		try {
			String methodName = "getOwnerName";

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

	public java.util.Date getActiveAssetReceiptSupportsEndDate(int usageType) {
		try {
			String methodName = "getActiveAssetReceiptSupportsEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasRenewedAssetReceiptSupports(int usageType) {
		try {
			String methodName = "hasRenewedAssetReceiptSupports";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isOwnerCorpProject() {
		try {
			String methodName = "isOwnerCorpProject";

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

	public boolean hasActiveAssetReceiptSupports(int usageType) {
		try {
			String methodName = "hasActiveAssetReceiptSupports";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses() {
		try {
			String methodName = "getAssetLicenses";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.AssetLicense> returnObj = (java.util.List<com.liferay.osb.model.AssetLicense>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasRenewedAssetReceiptLicenses(int usageType) {
		try {
			String methodName = "hasRenewedAssetReceiptLicenses";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getRenewedAssetReceiptSupportsEndDate(int usageType) {
		try {
			String methodName = "getRenewedAssetReceiptSupportsEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
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

	public boolean isOwnerUser() {
		try {
			String methodName = "isOwnerUser";

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

	public java.lang.String getProductClassName() {
		try {
			String methodName = "getProductClassName";

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

	public boolean hasActiveAssetReceiptLicenses() {
		try {
			String methodName = "hasActiveAssetReceiptLicenses";

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

	public java.util.Date getAssetReceiptSupportsEndDate(int usageType) {
		try {
			String methodName = "getAssetReceiptSupportsEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAssetReceiptRemoteModel() {
		return _assetReceiptRemoteModel;
	}

	public void setAssetReceiptRemoteModel(BaseModel<?> assetReceiptRemoteModel) {
		_assetReceiptRemoteModel = assetReceiptRemoteModel;
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

		Class<?> remoteModelClass = _assetReceiptRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetReceiptRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetReceiptLocalServiceUtil.addAssetReceipt(this);
		}
		else {
			AssetReceiptLocalServiceUtil.updateAssetReceipt(this);
		}
	}

	@Override
	public AssetReceipt toEscapedModel() {
		return (AssetReceipt)ProxyUtil.newProxyInstance(AssetReceipt.class.getClassLoader(),
			new Class[] { AssetReceipt.class }, new AutoEscapeBeanHandler(this));
	}

	public AssetReceipt toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetReceiptClp clone = new AssetReceiptClp();

		clone.setAssetReceiptId(getAssetReceiptId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setAssetEntryId(getAssetEntryId());
		clone.setOwnerClassNameId(getOwnerClassNameId());
		clone.setOwnerClassPK(getOwnerClassPK());
		clone.setLegalEntityName(getLegalEntityName());
		clone.setProductClassNameId(getProductClassNameId());
		clone.setProductClassPK(getProductClassPK());
		clone.setType(getType());
		clone.setCurrencyEntryId(getCurrencyEntryId());
		clone.setActualPrice(getActualPrice());

		return clone;
	}

	public int compareTo(AssetReceipt assetReceipt) {
		long primaryKey = assetReceipt.getPrimaryKey();

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

		if (!(obj instanceof AssetReceiptClp)) {
			return false;
		}

		AssetReceiptClp assetReceipt = (AssetReceiptClp)obj;

		long primaryKey = assetReceipt.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{assetReceiptId=");
		sb.append(getAssetReceiptId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", assetEntryId=");
		sb.append(getAssetEntryId());
		sb.append(", ownerClassNameId=");
		sb.append(getOwnerClassNameId());
		sb.append(", ownerClassPK=");
		sb.append(getOwnerClassPK());
		sb.append(", legalEntityName=");
		sb.append(getLegalEntityName());
		sb.append(", productClassNameId=");
		sb.append(getProductClassNameId());
		sb.append(", productClassPK=");
		sb.append(getProductClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", currencyEntryId=");
		sb.append(getCurrencyEntryId());
		sb.append(", actualPrice=");
		sb.append(getActualPrice());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetReceipt");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetReceiptId</column-name><column-value><![CDATA[");
		sb.append(getAssetReceiptId());
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
			"<column><column-name>legalEntityName</column-name><column-value><![CDATA[");
		sb.append(getLegalEntityName());
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
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyEntryId</column-name><column-value><![CDATA[");
		sb.append(getCurrencyEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualPrice</column-name><column-value><![CDATA[");
		sb.append(getActualPrice());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetReceiptId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _assetEntryId;
	private long _ownerClassNameId;
	private long _ownerClassPK;
	private String _legalEntityName;
	private long _productClassNameId;
	private long _productClassPK;
	private long _type;
	private long _currencyEntryId;
	private double _actualPrice;
	private BaseModel<?> _assetReceiptRemoteModel;
}