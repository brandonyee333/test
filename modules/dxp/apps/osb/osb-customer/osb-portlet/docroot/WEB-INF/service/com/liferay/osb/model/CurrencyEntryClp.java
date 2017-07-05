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
import com.liferay.osb.service.CurrencyEntryLocalServiceUtil;

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
public class CurrencyEntryClp extends BaseModelImpl<CurrencyEntry>
	implements CurrencyEntry {
	public CurrencyEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return CurrencyEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CurrencyEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _currencyEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCurrencyEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _currencyEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("countryId", getCountryId());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("marketplaceEnabled", getMarketplaceEnabled());
		attributes.put("marketplaceMinPrice", getMarketplaceMinPrice());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Boolean marketplaceEnabled = (Boolean)attributes.get(
				"marketplaceEnabled");

		if (marketplaceEnabled != null) {
			setMarketplaceEnabled(marketplaceEnabled);
		}

		Double marketplaceMinPrice = (Double)attributes.get(
				"marketplaceMinPrice");

		if (marketplaceMinPrice != null) {
			setMarketplaceMinPrice(marketplaceMinPrice);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	@Override
	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;

		if (_currencyEntryRemoteModel != null) {
			try {
				Class<?> clazz = _currencyEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyEntryId", long.class);

				method.invoke(_currencyEntryRemoteModel, currencyEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_currencyEntryRemoteModel != null) {
			try {
				Class<?> clazz = _currencyEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_currencyEntryRemoteModel, countryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCurrencyCode() {
		return _currencyCode;
	}

	@Override
	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;

		if (_currencyEntryRemoteModel != null) {
			try {
				Class<?> clazz = _currencyEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyCode", String.class);

				method.invoke(_currencyEntryRemoteModel, currencyCode);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getMarketplaceEnabled() {
		return _marketplaceEnabled;
	}

	@Override
	public boolean isMarketplaceEnabled() {
		return _marketplaceEnabled;
	}

	@Override
	public void setMarketplaceEnabled(boolean marketplaceEnabled) {
		_marketplaceEnabled = marketplaceEnabled;

		if (_currencyEntryRemoteModel != null) {
			try {
				Class<?> clazz = _currencyEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMarketplaceEnabled",
						boolean.class);

				method.invoke(_currencyEntryRemoteModel, marketplaceEnabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getMarketplaceMinPrice() {
		return _marketplaceMinPrice;
	}

	@Override
	public void setMarketplaceMinPrice(double marketplaceMinPrice) {
		_marketplaceMinPrice = marketplaceMinPrice;

		if (_currencyEntryRemoteModel != null) {
			try {
				Class<?> clazz = _currencyEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMarketplaceMinPrice",
						double.class);

				method.invoke(_currencyEntryRemoteModel, marketplaceMinPrice);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCurrencyEntryRemoteModel() {
		return _currencyEntryRemoteModel;
	}

	public void setCurrencyEntryRemoteModel(
		BaseModel<?> currencyEntryRemoteModel) {
		_currencyEntryRemoteModel = currencyEntryRemoteModel;
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

		Class<?> remoteModelClass = _currencyEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_currencyEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			CurrencyEntryLocalServiceUtil.addCurrencyEntry(this);
		}
		else {
			CurrencyEntryLocalServiceUtil.updateCurrencyEntry(this);
		}
	}

	@Override
	public CurrencyEntry toEscapedModel() {
		return (CurrencyEntry)ProxyUtil.newProxyInstance(CurrencyEntry.class.getClassLoader(),
			new Class[] { CurrencyEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CurrencyEntryClp clone = new CurrencyEntryClp();

		clone.setCurrencyEntryId(getCurrencyEntryId());
		clone.setCountryId(getCountryId());
		clone.setCurrencyCode(getCurrencyCode());
		clone.setMarketplaceEnabled(getMarketplaceEnabled());
		clone.setMarketplaceMinPrice(getMarketplaceMinPrice());

		return clone;
	}

	@Override
	public int compareTo(CurrencyEntry currencyEntry) {
		int value = 0;

		value = getCurrencyCode().compareTo(currencyEntry.getCurrencyCode());

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

		if (!(obj instanceof CurrencyEntryClp)) {
			return false;
		}

		CurrencyEntryClp currencyEntry = (CurrencyEntryClp)obj;

		long primaryKey = currencyEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{currencyEntryId=");
		sb.append(getCurrencyEntryId());
		sb.append(", countryId=");
		sb.append(getCountryId());
		sb.append(", currencyCode=");
		sb.append(getCurrencyCode());
		sb.append(", marketplaceEnabled=");
		sb.append(getMarketplaceEnabled());
		sb.append(", marketplaceMinPrice=");
		sb.append(getMarketplaceMinPrice());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.CurrencyEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>currencyEntryId</column-name><column-value><![CDATA[");
		sb.append(getCurrencyEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyCode</column-name><column-value><![CDATA[");
		sb.append(getCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>marketplaceEnabled</column-name><column-value><![CDATA[");
		sb.append(getMarketplaceEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>marketplaceMinPrice</column-name><column-value><![CDATA[");
		sb.append(getMarketplaceMinPrice());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _currencyEntryId;
	private long _countryId;
	private String _currencyCode;
	private boolean _marketplaceEnabled;
	private double _marketplaceMinPrice;
	private BaseModel<?> _currencyEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}