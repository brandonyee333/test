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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;

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
public class LicenseEntryClp extends BaseModelImpl<LicenseEntry>
	implements LicenseEntry {
	public LicenseEntryClp() {
	}

	public Class<?> getModelClass() {
		return LicenseEntry.class;
	}

	public String getModelClassName() {
		return LicenseEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _licenseEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setLicenseEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_licenseEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("portalVersionMin", getPortalVersionMin());
		attributes.put("portalVersionMax", getPortalVersionMax());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
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

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer portalVersionMin = (Integer)attributes.get("portalVersionMin");

		if (portalVersionMin != null) {
			setPortalVersionMin(portalVersionMin);
		}

		Integer portalVersionMax = (Integer)attributes.get("portalVersionMax");

		if (portalVersionMax != null) {
			setPortalVersionMax(portalVersionMax);
		}
	}

	public long getLicenseEntryId() {
		return _licenseEntryId;
	}

	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntryId = licenseEntryId;

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseEntryId", long.class);

				method.invoke(_licenseEntryRemoteModel, licenseEntryId);
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

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_licenseEntryRemoteModel, userId);
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

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_licenseEntryRemoteModel, userName);
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

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_licenseEntryRemoteModel, createDate);
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

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_licenseEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductEntryId", long.class);

				method.invoke(_licenseEntryRemoteModel, productEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_licenseEntryRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_licenseEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getPortalVersionMin() {
		return _portalVersionMin;
	}

	public void setPortalVersionMin(int portalVersionMin) {
		_portalVersionMin = portalVersionMin;

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPortalVersionMin", int.class);

				method.invoke(_licenseEntryRemoteModel, portalVersionMin);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getPortalVersionMax() {
		return _portalVersionMax;
	}

	public void setPortalVersionMax(int portalVersionMax) {
		_portalVersionMax = portalVersionMax;

		if (_licenseEntryRemoteModel != null) {
			try {
				Class<?> clazz = _licenseEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPortalVersionMax", int.class);

				method.invoke(_licenseEntryRemoteModel, portalVersionMax);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getPortalVersionLabel() {
		try {
			String methodName = "getPortalVersionLabel";

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

	public com.liferay.osb.model.ProductEntry getProductEntry() {
		try {
			String methodName = "getProductEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.ProductEntry returnObj = (com.liferay.osb.model.ProductEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getLicenseEntryRemoteModel() {
		return _licenseEntryRemoteModel;
	}

	public void setLicenseEntryRemoteModel(BaseModel<?> licenseEntryRemoteModel) {
		_licenseEntryRemoteModel = licenseEntryRemoteModel;
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

		Class<?> remoteModelClass = _licenseEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_licenseEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			LicenseEntryLocalServiceUtil.addLicenseEntry(this);
		}
		else {
			LicenseEntryLocalServiceUtil.updateLicenseEntry(this);
		}
	}

	@Override
	public LicenseEntry toEscapedModel() {
		return (LicenseEntry)ProxyUtil.newProxyInstance(LicenseEntry.class.getClassLoader(),
			new Class[] { LicenseEntry.class }, new AutoEscapeBeanHandler(this));
	}

	public LicenseEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		LicenseEntryClp clone = new LicenseEntryClp();

		clone.setLicenseEntryId(getLicenseEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setProductEntryId(getProductEntryId());
		clone.setName(getName());
		clone.setType(getType());
		clone.setPortalVersionMin(getPortalVersionMin());
		clone.setPortalVersionMax(getPortalVersionMax());

		return clone;
	}

	public int compareTo(LicenseEntry licenseEntry) {
		int value = 0;

		value = getName().toLowerCase()
					.compareTo(licenseEntry.getName().toLowerCase());

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

		if (!(obj instanceof LicenseEntryClp)) {
			return false;
		}

		LicenseEntryClp licenseEntry = (LicenseEntryClp)obj;

		long primaryKey = licenseEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{licenseEntryId=");
		sb.append(getLicenseEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", productEntryId=");
		sb.append(getProductEntryId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", portalVersionMin=");
		sb.append(getPortalVersionMin());
		sb.append(", portalVersionMax=");
		sb.append(getPortalVersionMax());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.LicenseEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>licenseEntryId</column-name><column-value><![CDATA[");
		sb.append(getLicenseEntryId());
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
			"<column><column-name>productEntryId</column-name><column-value><![CDATA[");
		sb.append(getProductEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalVersionMin</column-name><column-value><![CDATA[");
		sb.append(getPortalVersionMin());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalVersionMax</column-name><column-value><![CDATA[");
		sb.append(getPortalVersionMax());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _licenseEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _productEntryId;
	private String _name;
	private String _type;
	private int _portalVersionMin;
	private int _portalVersionMax;
	private BaseModel<?> _licenseEntryRemoteModel;
}