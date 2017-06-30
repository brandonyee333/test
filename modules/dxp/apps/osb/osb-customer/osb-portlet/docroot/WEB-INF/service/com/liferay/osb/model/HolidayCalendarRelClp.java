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
import com.liferay.osb.service.HolidayCalendarRelLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class HolidayCalendarRelClp extends BaseModelImpl<HolidayCalendarRel>
	implements HolidayCalendarRel {
	public HolidayCalendarRelClp() {
	}

	public Class<?> getModelClass() {
		return HolidayCalendarRel.class;
	}

	public String getModelClassName() {
		return HolidayCalendarRel.class.getName();
	}

	public long getPrimaryKey() {
		return _holidayCalendarRelId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHolidayCalendarRelId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_holidayCalendarRelId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarRelId", getHolidayCalendarRelId());
		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayCalendarRelId = (Long)attributes.get("holidayCalendarRelId");

		if (holidayCalendarRelId != null) {
			setHolidayCalendarRelId(holidayCalendarRelId);
		}

		Long holidayCalendarId = (Long)attributes.get("holidayCalendarId");

		if (holidayCalendarId != null) {
			setHolidayCalendarId(holidayCalendarId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	public long getHolidayCalendarRelId() {
		return _holidayCalendarRelId;
	}

	public void setHolidayCalendarRelId(long holidayCalendarRelId) {
		_holidayCalendarRelId = holidayCalendarRelId;

		if (_holidayCalendarRelRemoteModel != null) {
			try {
				Class<?> clazz = _holidayCalendarRelRemoteModel.getClass();

				Method method = clazz.getMethod("setHolidayCalendarRelId",
						long.class);

				method.invoke(_holidayCalendarRelRemoteModel,
					holidayCalendarRelId);
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

		if (_holidayCalendarRelRemoteModel != null) {
			try {
				Class<?> clazz = _holidayCalendarRelRemoteModel.getClass();

				Method method = clazz.getMethod("setHolidayCalendarId",
						long.class);

				method.invoke(_holidayCalendarRelRemoteModel, holidayCalendarId);
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

		if (_holidayCalendarRelRemoteModel != null) {
			try {
				Class<?> clazz = _holidayCalendarRelRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_holidayCalendarRelRemoteModel, userId);
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

	public BaseModel<?> getHolidayCalendarRelRemoteModel() {
		return _holidayCalendarRelRemoteModel;
	}

	public void setHolidayCalendarRelRemoteModel(
		BaseModel<?> holidayCalendarRelRemoteModel) {
		_holidayCalendarRelRemoteModel = holidayCalendarRelRemoteModel;
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

		Class<?> remoteModelClass = _holidayCalendarRelRemoteModel.getClass();

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

		Object returnValue = method.invoke(_holidayCalendarRelRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			HolidayCalendarRelLocalServiceUtil.addHolidayCalendarRel(this);
		}
		else {
			HolidayCalendarRelLocalServiceUtil.updateHolidayCalendarRel(this);
		}
	}

	@Override
	public HolidayCalendarRel toEscapedModel() {
		return (HolidayCalendarRel)ProxyUtil.newProxyInstance(HolidayCalendarRel.class.getClassLoader(),
			new Class[] { HolidayCalendarRel.class },
			new AutoEscapeBeanHandler(this));
	}

	public HolidayCalendarRel toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		HolidayCalendarRelClp clone = new HolidayCalendarRelClp();

		clone.setHolidayCalendarRelId(getHolidayCalendarRelId());
		clone.setHolidayCalendarId(getHolidayCalendarId());
		clone.setUserId(getUserId());

		return clone;
	}

	public int compareTo(HolidayCalendarRel holidayCalendarRel) {
		long primaryKey = holidayCalendarRel.getPrimaryKey();

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

		if (!(obj instanceof HolidayCalendarRelClp)) {
			return false;
		}

		HolidayCalendarRelClp holidayCalendarRel = (HolidayCalendarRelClp)obj;

		long primaryKey = holidayCalendarRel.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{holidayCalendarRelId=");
		sb.append(getHolidayCalendarRelId());
		sb.append(", holidayCalendarId=");
		sb.append(getHolidayCalendarId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.HolidayCalendarRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>holidayCalendarRelId</column-name><column-value><![CDATA[");
		sb.append(getHolidayCalendarRelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>holidayCalendarId</column-name><column-value><![CDATA[");
		sb.append(getHolidayCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _holidayCalendarRelId;
	private long _holidayCalendarId;
	private long _userId;
	private String _userUuid;
	private BaseModel<?> _holidayCalendarRelRemoteModel;
}