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

import com.liferay.osb.service.AssetReceiptSupportLocalServiceUtil;
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
public class AssetReceiptSupportClp extends BaseModelImpl<AssetReceiptSupport>
	implements AssetReceiptSupport {
	public AssetReceiptSupportClp() {
	}

	public Class<?> getModelClass() {
		return AssetReceiptSupport.class;
	}

	public String getModelClassName() {
		return AssetReceiptSupport.class.getName();
	}

	public long getPrimaryKey() {
		return _assetReceiptSupportId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetReceiptSupportId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetReceiptSupportId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("assetReceiptSupportId", getAssetReceiptSupportId());
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
		attributes.put("supportLifetime", getSupportLifetime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long assetReceiptSupportId = (Long)attributes.get(
				"assetReceiptSupportId");

		if (assetReceiptSupportId != null) {
			setAssetReceiptSupportId(assetReceiptSupportId);
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

		Long supportLifetime = (Long)attributes.get("supportLifetime");

		if (supportLifetime != null) {
			setSupportLifetime(supportLifetime);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_assetReceiptSupportRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetReceiptSupportId() {
		return _assetReceiptSupportId;
	}

	public void setAssetReceiptSupportId(long assetReceiptSupportId) {
		_assetReceiptSupportId = assetReceiptSupportId;

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptSupportId",
						long.class);

				method.invoke(_assetReceiptSupportRemoteModel,
					assetReceiptSupportId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, userId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_assetReceiptSupportRemoteModel, userName);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetReceiptSupportRemoteModel, createDate);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_assetReceiptSupportRemoteModel, modifiedDate);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetReceiptId", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, assetReceiptId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetLicenseId", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, assetLicenseId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetEntryId", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, assetEntryId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerClassNameId",
						long.class);

				method.invoke(_assetReceiptSupportRemoteModel, ownerClassNameId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerClassPK", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, ownerClassPK);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassNameId",
						long.class);

				method.invoke(_assetReceiptSupportRemoteModel,
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setProductClassPK", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, productClassPK);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setProductId", String.class);

				method.invoke(_assetReceiptSupportRemoteModel, productId);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_assetReceiptSupportRemoteModel, startDate);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDate", Date.class);

				method.invoke(_assetReceiptSupportRemoteModel, endDate);
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

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setUsageType", int.class);

				method.invoke(_assetReceiptSupportRemoteModel, usageType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSupportLifetime() {
		return _supportLifetime;
	}

	public void setSupportLifetime(long supportLifetime) {
		_supportLifetime = supportLifetime;

		if (_assetReceiptSupportRemoteModel != null) {
			try {
				Class<?> clazz = _assetReceiptSupportRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportLifetime", long.class);

				method.invoke(_assetReceiptSupportRemoteModel, supportLifetime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetReceiptSupportRemoteModel() {
		return _assetReceiptSupportRemoteModel;
	}

	public void setAssetReceiptSupportRemoteModel(
		BaseModel<?> assetReceiptSupportRemoteModel) {
		_assetReceiptSupportRemoteModel = assetReceiptSupportRemoteModel;
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

		Class<?> remoteModelClass = _assetReceiptSupportRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetReceiptSupportRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetReceiptSupportLocalServiceUtil.addAssetReceiptSupport(this);
		}
		else {
			AssetReceiptSupportLocalServiceUtil.updateAssetReceiptSupport(this);
		}
	}

	@Override
	public AssetReceiptSupport toEscapedModel() {
		return (AssetReceiptSupport)ProxyUtil.newProxyInstance(AssetReceiptSupport.class.getClassLoader(),
			new Class[] { AssetReceiptSupport.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetReceiptSupport toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetReceiptSupportClp clone = new AssetReceiptSupportClp();

		clone.setUuid(getUuid());
		clone.setAssetReceiptSupportId(getAssetReceiptSupportId());
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
		clone.setSupportLifetime(getSupportLifetime());

		return clone;
	}

	public int compareTo(AssetReceiptSupport assetReceiptSupport) {
		long primaryKey = assetReceiptSupport.getPrimaryKey();

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

		if (!(obj instanceof AssetReceiptSupportClp)) {
			return false;
		}

		AssetReceiptSupportClp assetReceiptSupport = (AssetReceiptSupportClp)obj;

		long primaryKey = assetReceiptSupport.getPrimaryKey();

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
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", assetReceiptSupportId=");
		sb.append(getAssetReceiptSupportId());
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
		sb.append(", supportLifetime=");
		sb.append(getSupportLifetime());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetReceiptSupport");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetReceiptSupportId</column-name><column-value><![CDATA[");
		sb.append(getAssetReceiptSupportId());
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
			"<column><column-name>supportLifetime</column-name><column-value><![CDATA[");
		sb.append(getSupportLifetime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _assetReceiptSupportId;
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
	private long _supportLifetime;
	private BaseModel<?> _assetReceiptSupportRemoteModel;
}