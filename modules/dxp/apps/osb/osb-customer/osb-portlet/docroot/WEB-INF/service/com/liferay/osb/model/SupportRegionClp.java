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
import com.liferay.osb.service.SupportRegionLocalServiceUtil;

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
public class SupportRegionClp extends BaseModelImpl<SupportRegion>
	implements SupportRegion {
	public SupportRegionClp() {
	}

	public Class<?> getModelClass() {
		return SupportRegion.class;
	}

	public String getModelClassName() {
		return SupportRegion.class.getName();
	}

	public long getPrimaryKey() {
		return _supportRegionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSupportRegionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_supportRegionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportRegionId", getSupportRegionId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("managerUserId", getManagerUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportRegionId = (Long)attributes.get("supportRegionId");

		if (supportRegionId != null) {
			setSupportRegionId(supportRegionId);
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
	}

	public long getSupportRegionId() {
		return _supportRegionId;
	}

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

	public long getUserId() {
		return _userId;
	}

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

	public Date getCreateDate() {
		return _createDate;
	}

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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

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

	public String getName() {
		return _name;
	}

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

	public String getDescription() {
		return _description;
	}

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

	public String getTimeZoneId() {
		return _timeZoneId;
	}

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

	public long getManagerUserId() {
		return _managerUserId;
	}

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

	public String getManagerUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getManagerUserId(), "uuid",
			_managerUserUuid);
	}

	public void setManagerUserUuid(String managerUserUuid) {
		_managerUserUuid = managerUserUuid;
	}

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

	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams() {
		try {
			String methodName = "getSupportTeams";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.SupportTeam> returnObj = (java.util.List<com.liferay.osb.model.SupportTeam>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.User getManagerUser() {
		try {
			String methodName = "getManagerUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.User returnObj = (com.liferay.portal.model.User)invokeOnRemoteModel(methodName,
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

	public void persist() throws SystemException {
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

	public SupportRegion toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SupportRegionClp clone = new SupportRegionClp();

		clone.setSupportRegionId(getSupportRegionId());
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

	public int compareTo(SupportRegion supportRegion) {
		int value = 0;

		value = getName().toLowerCase()
					.compareTo(supportRegion.getName().toLowerCase());

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

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{supportRegionId=");
		sb.append(getSupportRegionId());
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

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportRegion");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportRegionId</column-name><column-value><![CDATA[");
		sb.append(getSupportRegionId());
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
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _timeZoneId;
	private long _managerUserId;
	private String _managerUserUuid;
	private BaseModel<?> _supportRegionRemoteModel;
}