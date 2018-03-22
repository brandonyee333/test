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
import com.liferay.osb.service.HolidayEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class HolidayEntryClp extends BaseModelImpl<HolidayEntry>
	implements HolidayEntry {
	public HolidayEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return HolidayEntry.class;
	}

	@Override
	public String getModelClassName() {
		return HolidayEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _holidayEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setHolidayEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holidayEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayEntryId", getHolidayEntryId());
		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("repeatYearly", getRepeatYearly());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayEntryId = (Long)attributes.get("holidayEntryId");

		if (holidayEntryId != null) {
			setHolidayEntryId(holidayEntryId);
		}

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

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean repeatYearly = (Boolean)attributes.get("repeatYearly");

		if (repeatYearly != null) {
			setRepeatYearly(repeatYearly);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getHolidayEntryId() {
		return _holidayEntryId;
	}

	@Override
	public void setHolidayEntryId(long holidayEntryId) {
		_holidayEntryId = holidayEntryId;

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setHolidayEntryId", long.class);

				method.invoke(_holidayEntryRemoteModel, holidayEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getHolidayCalendarId() {
		return _holidayCalendarId;
	}

	@Override
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendarId = holidayCalendarId;

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setHolidayCalendarId",
						long.class);

				method.invoke(_holidayEntryRemoteModel, holidayCalendarId);
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

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_holidayEntryRemoteModel, name);
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

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_holidayEntryRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_holidayEntryRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDate", Date.class);

				method.invoke(_holidayEntryRemoteModel, endDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getRepeatYearly() {
		return _repeatYearly;
	}

	@Override
	public boolean isRepeatYearly() {
		return _repeatYearly;
	}

	@Override
	public void setRepeatYearly(boolean repeatYearly) {
		_repeatYearly = repeatYearly;

		if (_holidayEntryRemoteModel != null) {
			try {
				Class<?> clazz = _holidayEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setRepeatYearly", boolean.class);

				method.invoke(_holidayEntryRemoteModel, repeatYearly);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getHolidayEntryRemoteModel() {
		return _holidayEntryRemoteModel;
	}

	public void setHolidayEntryRemoteModel(BaseModel<?> holidayEntryRemoteModel) {
		_holidayEntryRemoteModel = holidayEntryRemoteModel;
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

		Class<?> remoteModelClass = _holidayEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_holidayEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			HolidayEntryLocalServiceUtil.addHolidayEntry(this);
		}
		else {
			HolidayEntryLocalServiceUtil.updateHolidayEntry(this);
		}
	}

	@Override
	public HolidayEntry toEscapedModel() {
		return (HolidayEntry)ProxyUtil.newProxyInstance(HolidayEntry.class.getClassLoader(),
			new Class[] { HolidayEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		HolidayEntryClp clone = new HolidayEntryClp();

		clone.setHolidayEntryId(getHolidayEntryId());
		clone.setHolidayCalendarId(getHolidayCalendarId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setRepeatYearly(getRepeatYearly());

		return clone;
	}

	@Override
	public int compareTo(HolidayEntry holidayEntry) {
		long primaryKey = holidayEntry.getPrimaryKey();

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

		if (!(obj instanceof HolidayEntryClp)) {
			return false;
		}

		HolidayEntryClp holidayEntry = (HolidayEntryClp)obj;

		long primaryKey = holidayEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{holidayEntryId=");
		sb.append(getHolidayEntryId());
		sb.append(", holidayCalendarId=");
		sb.append(getHolidayCalendarId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", repeatYearly=");
		sb.append(getRepeatYearly());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.HolidayEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>holidayEntryId</column-name><column-value><![CDATA[");
		sb.append(getHolidayEntryId());
		sb.append("]]></column-value></column>");
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
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>repeatYearly</column-name><column-value><![CDATA[");
		sb.append(getRepeatYearly());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _holidayEntryId;
	private long _holidayCalendarId;
	private String _name;
	private String _description;
	private Date _startDate;
	private Date _endDate;
	private boolean _repeatYearly;
	private BaseModel<?> _holidayEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}