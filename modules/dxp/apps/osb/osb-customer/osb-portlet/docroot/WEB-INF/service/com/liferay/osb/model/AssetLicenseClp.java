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

import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
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
public class AssetLicenseClp extends BaseModelImpl<AssetLicense>
	implements AssetLicense {
	public AssetLicenseClp() {
	}

	public Class<?> getModelClass() {
		return AssetLicense.class;
	}

	public String getModelClassName() {
		return AssetLicense.class.getName();
	}

	public long getPrimaryKey() {
		return _assetLicenseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetLicenseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetLicenseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetLicenseId", getAssetLicenseId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("licenseId", getLicenseId());
		attributes.put("name", getName());
		attributes.put("requiredVersion", getRequiredVersion());
		attributes.put("usageType", getUsageType());
		attributes.put("licenseType", getLicenseType());
		attributes.put("licenseTypeAllotment", getLicenseTypeAllotment());
		attributes.put("lifetime", getLifetime());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetLicenseId = (Long)attributes.get("assetLicenseId");

		if (assetLicenseId != null) {
			setAssetLicenseId(assetLicenseId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String licenseId = (String)attributes.get("licenseId");

		if (licenseId != null) {
			setLicenseId(licenseId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Double requiredVersion = (Double)attributes.get("requiredVersion");

		if (requiredVersion != null) {
			setRequiredVersion(requiredVersion);
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

		Long lifetime = (Long)attributes.get("lifetime");

		if (lifetime != null) {
			setLifetime(lifetime);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getAssetLicenseId() {
		return _assetLicenseId;
	}

	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicenseId = assetLicenseId;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetLicenseId", long.class);

				method.invoke(_assetLicenseRemoteModel, assetLicenseId);
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

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetLicenseRemoteModel, userId);
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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetLicenseRemoteModel, createDate);
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

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_assetLicenseRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetLicenseRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetLicenseRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLicenseId() {
		return _licenseId;
	}

	public void setLicenseId(String licenseId) {
		_licenseId = licenseId;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseId", String.class);

				method.invoke(_assetLicenseRemoteModel, licenseId);
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

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_assetLicenseRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getRequiredVersion() {
		return _requiredVersion;
	}

	public void setRequiredVersion(double requiredVersion) {
		_requiredVersion = requiredVersion;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setRequiredVersion",
						double.class);

				method.invoke(_assetLicenseRemoteModel, requiredVersion);
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

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setUsageType", int.class);

				method.invoke(_assetLicenseRemoteModel, usageType);
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

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseType", int.class);

				method.invoke(_assetLicenseRemoteModel, licenseType);
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

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseTypeAllotment",
						long.class);

				method.invoke(_assetLicenseRemoteModel, licenseTypeAllotment);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getLifetime() {
		return _lifetime;
	}

	public void setLifetime(long lifetime) {
		_lifetime = lifetime;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setLifetime", long.class);

				method.invoke(_assetLicenseRemoteModel, lifetime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;

		if (_assetLicenseRemoteModel != null) {
			try {
				Class<?> clazz = _assetLicenseRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_assetLicenseRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean isPurchased() {
		try {
			String methodName = "isPurchased";

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

	public java.lang.String getLifetimeLabel() {
		try {
			String methodName = "getLifetimeLabel";

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

	public java.lang.String getLicenseKeyType() {
		try {
			String methodName = "getLicenseKeyType";

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

	public java.lang.String getLicenseTypeLabel() {
		try {
			String methodName = "getLicenseTypeLabel";

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

	public BaseModel<?> getAssetLicenseRemoteModel() {
		return _assetLicenseRemoteModel;
	}

	public void setAssetLicenseRemoteModel(BaseModel<?> assetLicenseRemoteModel) {
		_assetLicenseRemoteModel = assetLicenseRemoteModel;
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

		Class<?> remoteModelClass = _assetLicenseRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetLicenseRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetLicenseLocalServiceUtil.addAssetLicense(this);
		}
		else {
			AssetLicenseLocalServiceUtil.updateAssetLicense(this);
		}
	}

	@Override
	public AssetLicense toEscapedModel() {
		return (AssetLicense)ProxyUtil.newProxyInstance(AssetLicense.class.getClassLoader(),
			new Class[] { AssetLicense.class }, new AutoEscapeBeanHandler(this));
	}

	public AssetLicense toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetLicenseClp clone = new AssetLicenseClp();

		clone.setAssetLicenseId(getAssetLicenseId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setLicenseId(getLicenseId());
		clone.setName(getName());
		clone.setRequiredVersion(getRequiredVersion());
		clone.setUsageType(getUsageType());
		clone.setLicenseType(getLicenseType());
		clone.setLicenseTypeAllotment(getLicenseTypeAllotment());
		clone.setLifetime(getLifetime());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(AssetLicense assetLicense) {
		int value = 0;

		if (getUsageType() < assetLicense.getUsageType()) {
			value = -1;
		}
		else if (getUsageType() > assetLicense.getUsageType()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getLicenseType() < assetLicense.getLicenseType()) {
			value = -1;
		}
		else if (getLicenseType() > assetLicense.getLicenseType()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getLicenseTypeAllotment() < assetLicense.getLicenseTypeAllotment()) {
			value = -1;
		}
		else if (getLicenseTypeAllotment() > assetLicense.getLicenseTypeAllotment()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getLifetime() < assetLicense.getLifetime()) {
			value = -1;
		}
		else if (getLifetime() > assetLicense.getLifetime()) {
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

		if (!(obj instanceof AssetLicenseClp)) {
			return false;
		}

		AssetLicenseClp assetLicense = (AssetLicenseClp)obj;

		long primaryKey = assetLicense.getPrimaryKey();

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
		StringBundler sb = new StringBundler(29);

		sb.append("{assetLicenseId=");
		sb.append(getAssetLicenseId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", licenseId=");
		sb.append(getLicenseId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", requiredVersion=");
		sb.append(getRequiredVersion());
		sb.append(", usageType=");
		sb.append(getUsageType());
		sb.append(", licenseType=");
		sb.append(getLicenseType());
		sb.append(", licenseTypeAllotment=");
		sb.append(getLicenseTypeAllotment());
		sb.append(", lifetime=");
		sb.append(getLifetime());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetLicense");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetLicenseId</column-name><column-value><![CDATA[");
		sb.append(getAssetLicenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseId</column-name><column-value><![CDATA[");
		sb.append(getLicenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requiredVersion</column-name><column-value><![CDATA[");
		sb.append(getRequiredVersion());
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
			"<column><column-name>lifetime</column-name><column-value><![CDATA[");
		sb.append(getLifetime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetLicenseId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _licenseId;
	private String _name;
	private double _requiredVersion;
	private int _usageType;
	private int _licenseType;
	private long _licenseTypeAllotment;
	private long _lifetime;
	private int _status;
	private BaseModel<?> _assetLicenseRemoteModel;
}