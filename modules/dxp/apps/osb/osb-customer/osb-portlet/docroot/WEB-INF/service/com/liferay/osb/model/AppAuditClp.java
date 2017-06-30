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

import com.liferay.osb.service.AppAuditLocalServiceUtil;
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
public class AppAuditClp extends BaseModelImpl<AppAudit> implements AppAudit {
	public AppAuditClp() {
	}

	public Class<?> getModelClass() {
		return AppAudit.class;
	}

	public String getModelClassName() {
		return AppAudit.class.getName();
	}

	public long getPrimaryKey() {
		return _appAuditId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppAuditId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appAuditId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appAuditId", getAppAuditId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appAuditId = (Long)attributes.get("appAuditId");

		if (appAuditId != null) {
			setAppAuditId(appAuditId);
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

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_appAuditRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppAuditId() {
		return _appAuditId;
	}

	public void setAppAuditId(long appAuditId) {
		_appAuditId = appAuditId;

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setAppAuditId", long.class);

				method.invoke(_appAuditRemoteModel, appAuditId);
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

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_appAuditRemoteModel, userId);
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

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_appAuditRemoteModel, userName);
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

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_appAuditRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppEntryId() {
		return _appEntryId;
	}

	public void setAppEntryId(long appEntryId) {
		_appEntryId = appEntryId;

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_appAuditRemoteModel, appEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppVersionId() {
		return _appVersionId;
	}

	public void setAppVersionId(long appVersionId) {
		_appVersionId = appVersionId;

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setAppVersionId", long.class);

				method.invoke(_appAuditRemoteModel, appVersionId);
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

		if (_appAuditRemoteModel != null) {
			try {
				Class<?> clazz = _appAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_appAuditRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAppAuditRemoteModel() {
		return _appAuditRemoteModel;
	}

	public void setAppAuditRemoteModel(BaseModel<?> appAuditRemoteModel) {
		_appAuditRemoteModel = appAuditRemoteModel;
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

		Class<?> remoteModelClass = _appAuditRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appAuditRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppAuditLocalServiceUtil.addAppAudit(this);
		}
		else {
			AppAuditLocalServiceUtil.updateAppAudit(this);
		}
	}

	@Override
	public AppAudit toEscapedModel() {
		return (AppAudit)ProxyUtil.newProxyInstance(AppAudit.class.getClassLoader(),
			new Class[] { AppAudit.class }, new AutoEscapeBeanHandler(this));
	}

	public AppAudit toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppAuditClp clone = new AppAuditClp();

		clone.setUuid(getUuid());
		clone.setAppAuditId(getAppAuditId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setAppEntryId(getAppEntryId());
		clone.setAppVersionId(getAppVersionId());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(AppAudit appAudit) {
		long primaryKey = appAudit.getPrimaryKey();

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

		if (!(obj instanceof AppAuditClp)) {
			return false;
		}

		AppAuditClp appAudit = (AppAuditClp)obj;

		long primaryKey = appAudit.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", appAuditId=");
		sb.append(getAppAuditId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", appVersionId=");
		sb.append(getAppVersionId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppAudit");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appAuditId</column-name><column-value><![CDATA[");
		sb.append(getAppAuditId());
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
			"<column><column-name>appEntryId</column-name><column-value><![CDATA[");
		sb.append(getAppEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appVersionId</column-name><column-value><![CDATA[");
		sb.append(getAppVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _appAuditId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _appEntryId;
	private long _appVersionId;
	private int _status;
	private BaseModel<?> _appAuditRemoteModel;
}