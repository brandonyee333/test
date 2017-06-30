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
import com.liferay.osb.service.HolidayEntryLocalServiceUtil;

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
public class HolidayEntryClp extends BaseModelImpl<HolidayEntry>
	implements HolidayEntry {
	public HolidayEntryClp() {
	}

	public Class<?> getModelClass() {
		return HolidayEntry.class;
	}

	public String getModelClassName() {
		return HolidayEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _holidayEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHolidayEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_holidayEntryId);
	}

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
	}

	public long getHolidayEntryId() {
		return _holidayEntryId;
	}

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

	public long getHolidayCalendarId() {
		return _holidayCalendarId;
	}

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

	public String getName() {
		return _name;
	}

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

	public String getDescription() {
		return _description;
	}

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

	public Date getStartDate() {
		return _startDate;
	}

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

	public Date getEndDate() {
		return _endDate;
	}

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

	public boolean getRepeatYearly() {
		return _repeatYearly;
	}

	public boolean isRepeatYearly() {
		return _repeatYearly;
	}

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

	public void persist() throws SystemException {
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

	public HolidayEntry toUnescapedModel() {
		return this;
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

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
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
}