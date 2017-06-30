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

import com.liferay.osb.service.AppEntryRelLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AppEntryRelClp extends BaseModelImpl<AppEntryRel>
	implements AppEntryRel {
	public AppEntryRelClp() {
	}

	public Class<?> getModelClass() {
		return AppEntryRel.class;
	}

	public String getModelClassName() {
		return AppEntryRel.class.getName();
	}

	public long getPrimaryKey() {
		return _appEntryRelId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppEntryRelId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appEntryRelId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appEntryRelId", getAppEntryRelId());
		attributes.put("appEntryId1", getAppEntryId1());
		attributes.put("appEntryId2", getAppEntryId2());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appEntryRelId = (Long)attributes.get("appEntryRelId");

		if (appEntryRelId != null) {
			setAppEntryRelId(appEntryRelId);
		}

		Long appEntryId1 = (Long)attributes.get("appEntryId1");

		if (appEntryId1 != null) {
			setAppEntryId1(appEntryId1);
		}

		Long appEntryId2 = (Long)attributes.get("appEntryId2");

		if (appEntryId2 != null) {
			setAppEntryId2(appEntryId2);
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

		if (_appEntryRelRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRelRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_appEntryRelRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppEntryRelId() {
		return _appEntryRelId;
	}

	public void setAppEntryRelId(long appEntryRelId) {
		_appEntryRelId = appEntryRelId;

		if (_appEntryRelRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRelRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryRelId", long.class);

				method.invoke(_appEntryRelRemoteModel, appEntryRelId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppEntryId1() {
		return _appEntryId1;
	}

	public void setAppEntryId1(long appEntryId1) {
		_appEntryId1 = appEntryId1;

		if (_appEntryRelRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRelRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId1", long.class);

				method.invoke(_appEntryRelRemoteModel, appEntryId1);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppEntryId2() {
		return _appEntryId2;
	}

	public void setAppEntryId2(long appEntryId2) {
		_appEntryId2 = appEntryId2;

		if (_appEntryRelRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRelRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId2", long.class);

				method.invoke(_appEntryRelRemoteModel, appEntryId2);
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

		if (_appEntryRelRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRelRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_appEntryRelRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAppEntryRelRemoteModel() {
		return _appEntryRelRemoteModel;
	}

	public void setAppEntryRelRemoteModel(BaseModel<?> appEntryRelRemoteModel) {
		_appEntryRelRemoteModel = appEntryRelRemoteModel;
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

		Class<?> remoteModelClass = _appEntryRelRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appEntryRelRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppEntryRelLocalServiceUtil.addAppEntryRel(this);
		}
		else {
			AppEntryRelLocalServiceUtil.updateAppEntryRel(this);
		}
	}

	@Override
	public AppEntryRel toEscapedModel() {
		return (AppEntryRel)ProxyUtil.newProxyInstance(AppEntryRel.class.getClassLoader(),
			new Class[] { AppEntryRel.class }, new AutoEscapeBeanHandler(this));
	}

	public AppEntryRel toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppEntryRelClp clone = new AppEntryRelClp();

		clone.setUuid(getUuid());
		clone.setAppEntryRelId(getAppEntryRelId());
		clone.setAppEntryId1(getAppEntryId1());
		clone.setAppEntryId2(getAppEntryId2());
		clone.setType(getType());

		return clone;
	}

	public int compareTo(AppEntryRel appEntryRel) {
		long primaryKey = appEntryRel.getPrimaryKey();

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

		if (!(obj instanceof AppEntryRelClp)) {
			return false;
		}

		AppEntryRelClp appEntryRel = (AppEntryRelClp)obj;

		long primaryKey = appEntryRel.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", appEntryRelId=");
		sb.append(getAppEntryRelId());
		sb.append(", appEntryId1=");
		sb.append(getAppEntryId1());
		sb.append(", appEntryId2=");
		sb.append(getAppEntryId2());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppEntryRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appEntryRelId</column-name><column-value><![CDATA[");
		sb.append(getAppEntryRelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appEntryId1</column-name><column-value><![CDATA[");
		sb.append(getAppEntryId1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appEntryId2</column-name><column-value><![CDATA[");
		sb.append(getAppEntryId2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _appEntryRelId;
	private long _appEntryId1;
	private long _appEntryId2;
	private int _type;
	private BaseModel<?> _appEntryRelRemoteModel;
}