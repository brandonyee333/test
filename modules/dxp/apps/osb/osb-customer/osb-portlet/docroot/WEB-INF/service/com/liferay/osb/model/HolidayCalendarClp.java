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
import com.liferay.osb.service.HolidayCalendarLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class HolidayCalendarClp extends BaseModelImpl<HolidayCalendar>
	implements HolidayCalendar {
	public HolidayCalendarClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return HolidayCalendar.class;
	}

	@Override
	public String getModelClassName() {
		return HolidayCalendar.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _holidayCalendarId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setHolidayCalendarId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holidayCalendarId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayCalendarId = (Long)attributes.get("holidayCalendarId");

		if (holidayCalendarId != null) {
			setHolidayCalendarId(holidayCalendarId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getHolidayCalendarId() {
		return _holidayCalendarId;
	}

	@Override
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendarId = holidayCalendarId;

		if (_holidayCalendarRemoteModel != null) {
			try {
				Class<?> clazz = _holidayCalendarRemoteModel.getClass();

				Method method = clazz.getMethod("setHolidayCalendarId",
						long.class);

				method.invoke(_holidayCalendarRemoteModel, holidayCalendarId);
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

		if (_holidayCalendarRemoteModel != null) {
			try {
				Class<?> clazz = _holidayCalendarRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_holidayCalendarRemoteModel, name);
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

		if (_holidayCalendarRemoteModel != null) {
			try {
				Class<?> clazz = _holidayCalendarRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_holidayCalendarRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getHolidayCalendarRemoteModel() {
		return _holidayCalendarRemoteModel;
	}

	public void setHolidayCalendarRemoteModel(
		BaseModel<?> holidayCalendarRemoteModel) {
		_holidayCalendarRemoteModel = holidayCalendarRemoteModel;
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

		Class<?> remoteModelClass = _holidayCalendarRemoteModel.getClass();

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

		Object returnValue = method.invoke(_holidayCalendarRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			HolidayCalendarLocalServiceUtil.addHolidayCalendar(this);
		}
		else {
			HolidayCalendarLocalServiceUtil.updateHolidayCalendar(this);
		}
	}

	@Override
	public HolidayCalendar toEscapedModel() {
		return (HolidayCalendar)ProxyUtil.newProxyInstance(HolidayCalendar.class.getClassLoader(),
			new Class[] { HolidayCalendar.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		HolidayCalendarClp clone = new HolidayCalendarClp();

		clone.setHolidayCalendarId(getHolidayCalendarId());
		clone.setName(getName());
		clone.setDescription(getDescription());

		return clone;
	}

	@Override
	public int compareTo(HolidayCalendar holidayCalendar) {
		long primaryKey = holidayCalendar.getPrimaryKey();

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

		if (!(obj instanceof HolidayCalendarClp)) {
			return false;
		}

		HolidayCalendarClp holidayCalendar = (HolidayCalendarClp)obj;

		long primaryKey = holidayCalendar.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{holidayCalendarId=");
		sb.append(getHolidayCalendarId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.HolidayCalendar");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>holidayCalendarId</column-name><column-value><![CDATA[");
		sb.append(getHolidayCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _holidayCalendarId;
	private String _name;
	private String _description;
	private BaseModel<?> _holidayCalendarRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}