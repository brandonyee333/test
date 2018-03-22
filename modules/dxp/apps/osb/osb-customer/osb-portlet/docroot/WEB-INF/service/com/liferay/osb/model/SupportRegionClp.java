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
import com.liferay.osb.service.SupportRegionLocalServiceUtil;

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
public class SupportRegionClp extends BaseModelImpl<SupportRegion>
	implements SupportRegion {
	public SupportRegionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SupportRegion.class;
	}

	@Override
	public String getModelClassName() {
		return SupportRegion.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _supportRegionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSupportRegionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportRegionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportRegionId", getSupportRegionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("managerUserId", getManagerUserId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportRegionId = (Long)attributes.get("supportRegionId");

		if (supportRegionId != null) {
			setSupportRegionId(supportRegionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Long managerUserId = (Long)attributes.get("managerUserId");

		if (managerUserId != null) {
			setManagerUserId(managerUserId);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSupportRegionId() {
		return _supportRegionId;
	}

	@Override
	public void setSupportRegionId(long supportRegionId) {
		_supportRegionId = supportRegionId;

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportRegionId", long.class);

				method.invoke(_supportRegionRemoteModel, supportRegionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_supportRegionRemoteModel, companyId);
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

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_supportRegionRemoteModel, userId);
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

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_supportRegionRemoteModel, userName);
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

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_supportRegionRemoteModel, createDate);
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

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_supportRegionRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_supportRegionRemoteModel, name);
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

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_supportRegionRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTimeZoneId() {
		return _timeZoneId;
	}

	@Override
	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setTimeZoneId", String.class);

				method.invoke(_supportRegionRemoteModel, timeZoneId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getManagerUserId() {
		return _managerUserId;
	}

	@Override
	public void setManagerUserId(long managerUserId) {
		_managerUserId = managerUserId;

		if (_supportRegionRemoteModel != null) {
			try {
				Class<?> clazz = _supportRegionRemoteModel.getClass();

				Method method = clazz.getMethod("setManagerUserId", long.class);

				method.invoke(_supportRegionRemoteModel, managerUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getManagerUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getManagerUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setManagerUserUuid(String managerUserUuid) {
	}

	@Override
	public User getManagerUser() {
		try {
			String methodName = "getManagerUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			User returnObj = (User)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<SupportTeam> getSupportTeams() {
		try {
			String methodName = "getSupportTeams";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<SupportTeam> returnObj = (java.util.List<SupportTeam>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.TimeZone getTimeZone() {
		try {
			String methodName = "getTimeZone";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.TimeZone returnObj = (java.util.TimeZone)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSupportRegionRemoteModel() {
		return _supportRegionRemoteModel;
	}

	public void setSupportRegionRemoteModel(
		BaseModel<?> supportRegionRemoteModel) {
		_supportRegionRemoteModel = supportRegionRemoteModel;
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

		Class<?> remoteModelClass = _supportRegionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportRegionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SupportRegionLocalServiceUtil.addSupportRegion(this);
		}
		else {
			SupportRegionLocalServiceUtil.updateSupportRegion(this);
		}
	}

	@Override
	public SupportRegion toEscapedModel() {
		return (SupportRegion)ProxyUtil.newProxyInstance(SupportRegion.class.getClassLoader(),
			new Class[] { SupportRegion.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SupportRegionClp clone = new SupportRegionClp();

		clone.setSupportRegionId(getSupportRegionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setTimeZoneId(getTimeZoneId());
		clone.setManagerUserId(getManagerUserId());

		return clone;
	}

	@Override
	public int compareTo(SupportRegion supportRegion) {
		int value = 0;

		value = getName().compareToIgnoreCase(supportRegion.getName());

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

		if (!(obj instanceof SupportRegionClp)) {
			return false;
		}

		SupportRegionClp supportRegion = (SupportRegionClp)obj;

		long primaryKey = supportRegion.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{supportRegionId=");
		sb.append(getSupportRegionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", timeZoneId=");
		sb.append(getTimeZoneId());
		sb.append(", managerUserId=");
		sb.append(getManagerUserId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportRegion");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportRegionId</column-name><column-value><![CDATA[");
		sb.append(getSupportRegionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>timeZoneId</column-name><column-value><![CDATA[");
		sb.append(getTimeZoneId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>managerUserId</column-name><column-value><![CDATA[");
		sb.append(getManagerUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportRegionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _timeZoneId;
	private long _managerUserId;
	private BaseModel<?> _supportRegionRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}