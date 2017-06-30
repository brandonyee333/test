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

import com.liferay.osb.service.AppFlagLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AppFlagClp extends BaseModelImpl<AppFlag> implements AppFlag {
	public AppFlagClp() {
	}

	public Class<?> getModelClass() {
		return AppFlag.class;
	}

	public String getModelClassName() {
		return AppFlag.class.getName();
	}

	public long getPrimaryKey() {
		return _appFlagId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppFlagId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appFlagId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appFlagId", getAppFlagId());
		attributes.put("createDate", getCreateDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appFlagId = (Long)attributes.get("appFlagId");

		if (appFlagId != null) {
			setAppFlagId(appFlagId);
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

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_appFlagRemoteModel != null) {
			try {
				Class<?> clazz = _appFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_appFlagRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppFlagId() {
		return _appFlagId;
	}

	public void setAppFlagId(long appFlagId) {
		_appFlagId = appFlagId;

		if (_appFlagRemoteModel != null) {
			try {
				Class<?> clazz = _appFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setAppFlagId", long.class);

				method.invoke(_appFlagRemoteModel, appFlagId);
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

		if (_appFlagRemoteModel != null) {
			try {
				Class<?> clazz = _appFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_appFlagRemoteModel, createDate);
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

		if (_appFlagRemoteModel != null) {
			try {
				Class<?> clazz = _appFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_appFlagRemoteModel, appEntryId);
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

		if (_appFlagRemoteModel != null) {
			try {
				Class<?> clazz = _appFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setAppVersionId", long.class);

				method.invoke(_appFlagRemoteModel, appVersionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_appFlagRemoteModel != null) {
			try {
				Class<?> clazz = _appFlagRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_appFlagRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAppFlagRemoteModel() {
		return _appFlagRemoteModel;
	}

	public void setAppFlagRemoteModel(BaseModel<?> appFlagRemoteModel) {
		_appFlagRemoteModel = appFlagRemoteModel;
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

		Class<?> remoteModelClass = _appFlagRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appFlagRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppFlagLocalServiceUtil.addAppFlag(this);
		}
		else {
			AppFlagLocalServiceUtil.updateAppFlag(this);
		}
	}

	@Override
	public AppFlag toEscapedModel() {
		return (AppFlag)ProxyUtil.newProxyInstance(AppFlag.class.getClassLoader(),
			new Class[] { AppFlag.class }, new AutoEscapeBeanHandler(this));
	}

	public AppFlag toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppFlagClp clone = new AppFlagClp();

		clone.setUuid(getUuid());
		clone.setAppFlagId(getAppFlagId());
		clone.setCreateDate(getCreateDate());
		clone.setAppEntryId(getAppEntryId());
		clone.setAppVersionId(getAppVersionId());
		clone.setType(getType());

		return clone;
	}

	public int compareTo(AppFlag appFlag) {
		long primaryKey = appFlag.getPrimaryKey();

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

		if (!(obj instanceof AppFlagClp)) {
			return false;
		}

		AppFlagClp appFlag = (AppFlagClp)obj;

		long primaryKey = appFlag.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", appFlagId=");
		sb.append(getAppFlagId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", appVersionId=");
		sb.append(getAppVersionId());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppFlag");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appFlagId</column-name><column-value><![CDATA[");
		sb.append(getAppFlagId());
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
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _appFlagId;
	private Date _createDate;
	private long _appEntryId;
	private long _appVersionId;
	private int _type;
	private BaseModel<?> _appFlagRemoteModel;
}