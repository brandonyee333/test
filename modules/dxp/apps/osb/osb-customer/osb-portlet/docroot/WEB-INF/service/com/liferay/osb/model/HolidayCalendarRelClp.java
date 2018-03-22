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
import com.liferay.osb.service.HolidayCalendarRelLocalServiceUtil;

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

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class HolidayCalendarRelClp extends BaseModelImpl<HolidayCalendarRel>
	implements HolidayCalendarRel {
	public HolidayCalendarRelClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return HolidayCalendarRel.class;
	}

	@Override
	public String getModelClassName() {
		return HolidayCalendarRel.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _holidayCalendarRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setHolidayCalendarRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holidayCalendarRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarRelId", getHolidayCalendarRelId());
		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("userId", getUserId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getHolidayCalendarRelId() {
		return _holidayCalendarRelId;
	}

	@Override
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

	@Override
	public long getHolidayCalendarId() {
		return _holidayCalendarId;
	}

	@Override
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

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
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

	@Override
	public void persist() {
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

	@Override
	public Object clone() {
		HolidayCalendarRelClp clone = new HolidayCalendarRelClp();

		clone.setHolidayCalendarRelId(getHolidayCalendarRelId());
		clone.setHolidayCalendarId(getHolidayCalendarId());
		clone.setUserId(getUserId());

		return clone;
	}

	@Override
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

		sb.append("{holidayCalendarRelId=");
		sb.append(getHolidayCalendarRelId());
		sb.append(", holidayCalendarId=");
		sb.append(getHolidayCalendarId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append("}");

		return sb.toString();
	}

	@Override
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
	private BaseModel<?> _holidayCalendarRelRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}