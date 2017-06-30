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
import com.liferay.osb.service.CountryAppPricingLocalServiceUtil;

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
public class CountryAppPricingClp extends BaseModelImpl<CountryAppPricing>
	implements CountryAppPricing {
	public CountryAppPricingClp() {
	}

	public Class<?> getModelClass() {
		return CountryAppPricing.class;
	}

	public String getModelClassName() {
		return CountryAppPricing.class.getName();
	}

	public long getPrimaryKey() {
		return _countryAppPricingId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCountryAppPricingId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_countryAppPricingId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("countryAppPricingId", getCountryAppPricingId());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("appPricingId", getAppPricingId());
		attributes.put("countryId", getCountryId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long countryAppPricingId = (Long)attributes.get("countryAppPricingId");

		if (countryAppPricingId != null) {
			setCountryAppPricingId(countryAppPricingId);
		}

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		Long appPricingId = (Long)attributes.get("appPricingId");

		if (appPricingId != null) {
			setAppPricingId(appPricingId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	public long getCountryAppPricingId() {
		return _countryAppPricingId;
	}

	public void setCountryAppPricingId(long countryAppPricingId) {
		_countryAppPricingId = countryAppPricingId;

		if (_countryAppPricingRemoteModel != null) {
			try {
				Class<?> clazz = _countryAppPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryAppPricingId",
						long.class);

				method.invoke(_countryAppPricingRemoteModel, countryAppPricingId);
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

		if (_countryAppPricingRemoteModel != null) {
			try {
				Class<?> clazz = _countryAppPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_countryAppPricingRemoteModel, appEntryId);
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

		if (_countryAppPricingRemoteModel != null) {
			try {
				Class<?> clazz = _countryAppPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setAppVersionId", long.class);

				method.invoke(_countryAppPricingRemoteModel, appVersionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAppPricingId() {
		return _appPricingId;
	}

	public void setAppPricingId(long appPricingId) {
		_appPricingId = appPricingId;

		if (_countryAppPricingRemoteModel != null) {
			try {
				Class<?> clazz = _countryAppPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPricingId", long.class);

				method.invoke(_countryAppPricingRemoteModel, appPricingId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_countryAppPricingRemoteModel != null) {
			try {
				Class<?> clazz = _countryAppPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_countryAppPricingRemoteModel, countryId);
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

		if (_countryAppPricingRemoteModel != null) {
			try {
				Class<?> clazz = _countryAppPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_countryAppPricingRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.osb.model.AppPricing getAppPricing() {
		try {
			String methodName = "getAppPricing";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppPricing returnObj = (com.liferay.osb.model.AppPricing)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getCountryAppPricingRemoteModel() {
		return _countryAppPricingRemoteModel;
	}

	public void setCountryAppPricingRemoteModel(
		BaseModel<?> countryAppPricingRemoteModel) {
		_countryAppPricingRemoteModel = countryAppPricingRemoteModel;
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

		Class<?> remoteModelClass = _countryAppPricingRemoteModel.getClass();

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

		Object returnValue = method.invoke(_countryAppPricingRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			CountryAppPricingLocalServiceUtil.addCountryAppPricing(this);
		}
		else {
			CountryAppPricingLocalServiceUtil.updateCountryAppPricing(this);
		}
	}

	@Override
	public CountryAppPricing toEscapedModel() {
		return (CountryAppPricing)ProxyUtil.newProxyInstance(CountryAppPricing.class.getClassLoader(),
			new Class[] { CountryAppPricing.class },
			new AutoEscapeBeanHandler(this));
	}

	public CountryAppPricing toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		CountryAppPricingClp clone = new CountryAppPricingClp();

		clone.setCountryAppPricingId(getCountryAppPricingId());
		clone.setAppEntryId(getAppEntryId());
		clone.setAppVersionId(getAppVersionId());
		clone.setAppPricingId(getAppPricingId());
		clone.setCountryId(getCountryId());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(CountryAppPricing countryAppPricing) {
		int value = 0;

		value = getName().toLowerCase()
					.compareTo(countryAppPricing.getName().toLowerCase());

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

		if (!(obj instanceof CountryAppPricingClp)) {
			return false;
		}

		CountryAppPricingClp countryAppPricing = (CountryAppPricingClp)obj;

		long primaryKey = countryAppPricing.getPrimaryKey();

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

		sb.append("{countryAppPricingId=");
		sb.append(getCountryAppPricingId());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", appVersionId=");
		sb.append(getAppVersionId());
		sb.append(", appPricingId=");
		sb.append(getAppPricingId());
		sb.append(", countryId=");
		sb.append(getCountryId());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.CountryAppPricing");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>countryAppPricingId</column-name><column-value><![CDATA[");
		sb.append(getCountryAppPricingId());
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
			"<column><column-name>appPricingId</column-name><column-value><![CDATA[");
		sb.append(getAppPricingId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _countryAppPricingId;
	private long _appEntryId;
	private long _appVersionId;
	private long _appPricingId;
	private long _countryId;
	private String _name;
	private BaseModel<?> _countryAppPricingRemoteModel;
}