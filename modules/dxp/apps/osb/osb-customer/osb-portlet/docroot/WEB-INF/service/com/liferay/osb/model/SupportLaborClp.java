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
import com.liferay.osb.service.SupportLaborLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class SupportLaborClp extends BaseModelImpl<SupportLabor>
	implements SupportLabor {
	public SupportLaborClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SupportLabor.class;
	}

	@Override
	public String getModelClassName() {
		return SupportLabor.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _supportLaborId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSupportLaborId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportLaborId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportLaborId", getSupportLaborId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("sunOpen", getSunOpen());
		attributes.put("sunClose", getSunClose());
		attributes.put("monOpen", getMonOpen());
		attributes.put("monClose", getMonClose());
		attributes.put("tueOpen", getTueOpen());
		attributes.put("tueClose", getTueClose());
		attributes.put("wedOpen", getWedOpen());
		attributes.put("wedClose", getWedClose());
		attributes.put("thuOpen", getThuOpen());
		attributes.put("thuClose", getThuClose());
		attributes.put("friOpen", getFriOpen());
		attributes.put("friClose", getFriClose());
		attributes.put("satOpen", getSatOpen());
		attributes.put("satClose", getSatClose());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportLaborId = (Long)attributes.get("supportLaborId");

		if (supportLaborId != null) {
			setSupportLaborId(supportLaborId);
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

		Integer sunOpen = (Integer)attributes.get("sunOpen");

		if (sunOpen != null) {
			setSunOpen(sunOpen);
		}

		Integer sunClose = (Integer)attributes.get("sunClose");

		if (sunClose != null) {
			setSunClose(sunClose);
		}

		Integer monOpen = (Integer)attributes.get("monOpen");

		if (monOpen != null) {
			setMonOpen(monOpen);
		}

		Integer monClose = (Integer)attributes.get("monClose");

		if (monClose != null) {
			setMonClose(monClose);
		}

		Integer tueOpen = (Integer)attributes.get("tueOpen");

		if (tueOpen != null) {
			setTueOpen(tueOpen);
		}

		Integer tueClose = (Integer)attributes.get("tueClose");

		if (tueClose != null) {
			setTueClose(tueClose);
		}

		Integer wedOpen = (Integer)attributes.get("wedOpen");

		if (wedOpen != null) {
			setWedOpen(wedOpen);
		}

		Integer wedClose = (Integer)attributes.get("wedClose");

		if (wedClose != null) {
			setWedClose(wedClose);
		}

		Integer thuOpen = (Integer)attributes.get("thuOpen");

		if (thuOpen != null) {
			setThuOpen(thuOpen);
		}

		Integer thuClose = (Integer)attributes.get("thuClose");

		if (thuClose != null) {
			setThuClose(thuClose);
		}

		Integer friOpen = (Integer)attributes.get("friOpen");

		if (friOpen != null) {
			setFriOpen(friOpen);
		}

		Integer friClose = (Integer)attributes.get("friClose");

		if (friClose != null) {
			setFriClose(friClose);
		}

		Integer satOpen = (Integer)attributes.get("satOpen");

		if (satOpen != null) {
			setSatOpen(satOpen);
		}

		Integer satClose = (Integer)attributes.get("satClose");

		if (satClose != null) {
			setSatClose(satClose);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSupportLaborId() {
		return _supportLaborId;
	}

	@Override
	public void setSupportLaborId(long supportLaborId) {
		_supportLaborId = supportLaborId;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportLaborId", long.class);

				method.invoke(_supportLaborRemoteModel, supportLaborId);
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

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_supportLaborRemoteModel, name);
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

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_supportLaborRemoteModel, description);
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

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setTimeZoneId", String.class);

				method.invoke(_supportLaborRemoteModel, timeZoneId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSunOpen() {
		return _sunOpen;
	}

	@Override
	public void setSunOpen(int sunOpen) {
		_sunOpen = sunOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setSunOpen", int.class);

				method.invoke(_supportLaborRemoteModel, sunOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSunClose() {
		return _sunClose;
	}

	@Override
	public void setSunClose(int sunClose) {
		_sunClose = sunClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setSunClose", int.class);

				method.invoke(_supportLaborRemoteModel, sunClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getMonOpen() {
		return _monOpen;
	}

	@Override
	public void setMonOpen(int monOpen) {
		_monOpen = monOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setMonOpen", int.class);

				method.invoke(_supportLaborRemoteModel, monOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getMonClose() {
		return _monClose;
	}

	@Override
	public void setMonClose(int monClose) {
		_monClose = monClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setMonClose", int.class);

				method.invoke(_supportLaborRemoteModel, monClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getTueOpen() {
		return _tueOpen;
	}

	@Override
	public void setTueOpen(int tueOpen) {
		_tueOpen = tueOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setTueOpen", int.class);

				method.invoke(_supportLaborRemoteModel, tueOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getTueClose() {
		return _tueClose;
	}

	@Override
	public void setTueClose(int tueClose) {
		_tueClose = tueClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setTueClose", int.class);

				method.invoke(_supportLaborRemoteModel, tueClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getWedOpen() {
		return _wedOpen;
	}

	@Override
	public void setWedOpen(int wedOpen) {
		_wedOpen = wedOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setWedOpen", int.class);

				method.invoke(_supportLaborRemoteModel, wedOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getWedClose() {
		return _wedClose;
	}

	@Override
	public void setWedClose(int wedClose) {
		_wedClose = wedClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setWedClose", int.class);

				method.invoke(_supportLaborRemoteModel, wedClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getThuOpen() {
		return _thuOpen;
	}

	@Override
	public void setThuOpen(int thuOpen) {
		_thuOpen = thuOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setThuOpen", int.class);

				method.invoke(_supportLaborRemoteModel, thuOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getThuClose() {
		return _thuClose;
	}

	@Override
	public void setThuClose(int thuClose) {
		_thuClose = thuClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setThuClose", int.class);

				method.invoke(_supportLaborRemoteModel, thuClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getFriOpen() {
		return _friOpen;
	}

	@Override
	public void setFriOpen(int friOpen) {
		_friOpen = friOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setFriOpen", int.class);

				method.invoke(_supportLaborRemoteModel, friOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getFriClose() {
		return _friClose;
	}

	@Override
	public void setFriClose(int friClose) {
		_friClose = friClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setFriClose", int.class);

				method.invoke(_supportLaborRemoteModel, friClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSatOpen() {
		return _satOpen;
	}

	@Override
	public void setSatOpen(int satOpen) {
		_satOpen = satOpen;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setSatOpen", int.class);

				method.invoke(_supportLaborRemoteModel, satOpen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSatClose() {
		return _satClose;
	}

	@Override
	public void setSatClose(int satClose) {
		_satClose = satClose;

		if (_supportLaborRemoteModel != null) {
			try {
				Class<?> clazz = _supportLaborRemoteModel.getClass();

				Method method = clazz.getMethod("setSatClose", int.class);

				method.invoke(_supportLaborRemoteModel, satClose);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String formatDayHours(Locale locale, int day) {
		try {
			String methodName = "formatDayHours";

			Class<?>[] parameterTypes = new Class<?>[] { Locale.class, int.class };

			Object[] parameterValues = new Object[] { locale, day };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String formatTime(Locale locale, int day, int type) {
		try {
			String methodName = "formatTime";

			Class<?>[] parameterTypes = new Class<?>[] {
					Locale.class, int.class, int.class
				};

			Object[] parameterValues = new Object[] { locale, day, type };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
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
	public int getTime(int day, int type) {
		try {
			String methodName = "getTime";

			Class<?>[] parameterTypes = new Class<?>[] { int.class, int.class };

			Object[] parameterValues = new Object[] { day, type };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSupportLaborRemoteModel() {
		return _supportLaborRemoteModel;
	}

	public void setSupportLaborRemoteModel(BaseModel<?> supportLaborRemoteModel) {
		_supportLaborRemoteModel = supportLaborRemoteModel;
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

		Class<?> remoteModelClass = _supportLaborRemoteModel.getClass();

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

		Object returnValue = method.invoke(_supportLaborRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SupportLaborLocalServiceUtil.addSupportLabor(this);
		}
		else {
			SupportLaborLocalServiceUtil.updateSupportLabor(this);
		}
	}

	@Override
	public SupportLabor toEscapedModel() {
		return (SupportLabor)ProxyUtil.newProxyInstance(SupportLabor.class.getClassLoader(),
			new Class[] { SupportLabor.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SupportLaborClp clone = new SupportLaborClp();

		clone.setSupportLaborId(getSupportLaborId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setTimeZoneId(getTimeZoneId());
		clone.setSunOpen(getSunOpen());
		clone.setSunClose(getSunClose());
		clone.setMonOpen(getMonOpen());
		clone.setMonClose(getMonClose());
		clone.setTueOpen(getTueOpen());
		clone.setTueClose(getTueClose());
		clone.setWedOpen(getWedOpen());
		clone.setWedClose(getWedClose());
		clone.setThuOpen(getThuOpen());
		clone.setThuClose(getThuClose());
		clone.setFriOpen(getFriOpen());
		clone.setFriClose(getFriClose());
		clone.setSatOpen(getSatOpen());
		clone.setSatClose(getSatClose());

		return clone;
	}

	@Override
	public int compareTo(SupportLabor supportLabor) {
		int value = 0;

		value = getName().compareTo(supportLabor.getName());

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

		if (!(obj instanceof SupportLaborClp)) {
			return false;
		}

		SupportLaborClp supportLabor = (SupportLaborClp)obj;

		long primaryKey = supportLabor.getPrimaryKey();

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
		StringBundler sb = new StringBundler(37);

		sb.append("{supportLaborId=");
		sb.append(getSupportLaborId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", timeZoneId=");
		sb.append(getTimeZoneId());
		sb.append(", sunOpen=");
		sb.append(getSunOpen());
		sb.append(", sunClose=");
		sb.append(getSunClose());
		sb.append(", monOpen=");
		sb.append(getMonOpen());
		sb.append(", monClose=");
		sb.append(getMonClose());
		sb.append(", tueOpen=");
		sb.append(getTueOpen());
		sb.append(", tueClose=");
		sb.append(getTueClose());
		sb.append(", wedOpen=");
		sb.append(getWedOpen());
		sb.append(", wedClose=");
		sb.append(getWedClose());
		sb.append(", thuOpen=");
		sb.append(getThuOpen());
		sb.append(", thuClose=");
		sb.append(getThuClose());
		sb.append(", friOpen=");
		sb.append(getFriOpen());
		sb.append(", friClose=");
		sb.append(getFriClose());
		sb.append(", satOpen=");
		sb.append(getSatOpen());
		sb.append(", satClose=");
		sb.append(getSatClose());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.SupportLabor");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>supportLaborId</column-name><column-value><![CDATA[");
		sb.append(getSupportLaborId());
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
			"<column><column-name>sunOpen</column-name><column-value><![CDATA[");
		sb.append(getSunOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sunClose</column-name><column-value><![CDATA[");
		sb.append(getSunClose());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>monOpen</column-name><column-value><![CDATA[");
		sb.append(getMonOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>monClose</column-name><column-value><![CDATA[");
		sb.append(getMonClose());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tueOpen</column-name><column-value><![CDATA[");
		sb.append(getTueOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tueClose</column-name><column-value><![CDATA[");
		sb.append(getTueClose());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wedOpen</column-name><column-value><![CDATA[");
		sb.append(getWedOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wedClose</column-name><column-value><![CDATA[");
		sb.append(getWedClose());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>thuOpen</column-name><column-value><![CDATA[");
		sb.append(getThuOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>thuClose</column-name><column-value><![CDATA[");
		sb.append(getThuClose());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>friOpen</column-name><column-value><![CDATA[");
		sb.append(getFriOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>friClose</column-name><column-value><![CDATA[");
		sb.append(getFriClose());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>satOpen</column-name><column-value><![CDATA[");
		sb.append(getSatOpen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>satClose</column-name><column-value><![CDATA[");
		sb.append(getSatClose());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _supportLaborId;
	private String _name;
	private String _description;
	private String _timeZoneId;
	private int _sunOpen;
	private int _sunClose;
	private int _monOpen;
	private int _monClose;
	private int _tueOpen;
	private int _tueClose;
	private int _wedOpen;
	private int _wedClose;
	private int _thuOpen;
	private int _thuClose;
	private int _friOpen;
	private int _friClose;
	private int _satOpen;
	private int _satClose;
	private BaseModel<?> _supportLaborRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}