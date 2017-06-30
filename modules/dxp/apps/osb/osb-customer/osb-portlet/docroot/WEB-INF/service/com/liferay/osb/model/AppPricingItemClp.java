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

import com.liferay.osb.service.AppPricingItemLocalServiceUtil;
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
public class AppPricingItemClp extends BaseModelImpl<AppPricingItem>
	implements AppPricingItem {
	public AppPricingItemClp() {
	}

	public Class<?> getModelClass() {
		return AppPricingItem.class;
	}

	public String getModelClassName() {
		return AppPricingItem.class.getName();
	}

	public long getPrimaryKey() {
		return _appPricingItemId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppPricingItemId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appPricingItemId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPricingItemId", getAppPricingItemId());
		attributes.put("appPricingId", getAppPricingId());
		attributes.put("assetLicenseId", getAssetLicenseId());
		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("price", getPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPricingItemId = (Long)attributes.get("appPricingItemId");

		if (appPricingItemId != null) {
			setAppPricingItemId(appPricingItemId);
		}

		Long appPricingId = (Long)attributes.get("appPricingId");

		if (appPricingId != null) {
			setAppPricingId(appPricingId);
		}

		Long assetLicenseId = (Long)attributes.get("assetLicenseId");

		if (assetLicenseId != null) {
			setAssetLicenseId(assetLicenseId);
		}

		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	public long getAppPricingItemId() {
		return _appPricingItemId;
	}

	public void setAppPricingItemId(long appPricingItemId) {
		_appPricingItemId = appPricingItemId;

		if (_appPricingItemRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingItemRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPricingItemId",
						long.class);

				method.invoke(_appPricingItemRemoteModel, appPricingItemId);
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

		if (_appPricingItemRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingItemRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPricingId", long.class);

				method.invoke(_appPricingItemRemoteModel, appPricingId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAssetLicenseId() {
		return _assetLicenseId;
	}

	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicenseId = assetLicenseId;

		if (_appPricingItemRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingItemRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetLicenseId", long.class);

				method.invoke(_appPricingItemRemoteModel, assetLicenseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;

		if (_appPricingItemRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingItemRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyEntryId", long.class);

				method.invoke(_appPricingItemRemoteModel, currencyEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;

		if (_appPricingItemRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingItemRemoteModel.getClass();

				Method method = clazz.getMethod("setPrice", double.class);

				method.invoke(_appPricingItemRemoteModel, price);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getCurrencyCode() {
		try {
			String methodName = "getCurrencyCode";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.CurrencyEntry getCurrencyEntry() {
		try {
			String methodName = "getCurrencyEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.CurrencyEntry returnObj = (com.liferay.osb.model.CurrencyEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getFormattedPrice(java.util.Locale locale) {
		try {
			String methodName = "getFormattedPrice";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Locale.class };

			Object[] parameterValues = new Object[] { locale };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.Country getCountry() {
		try {
			String methodName = "getCountry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.Country returnObj = (com.liferay.portal.model.Country)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAppPricingItemRemoteModel() {
		return _appPricingItemRemoteModel;
	}

	public void setAppPricingItemRemoteModel(
		BaseModel<?> appPricingItemRemoteModel) {
		_appPricingItemRemoteModel = appPricingItemRemoteModel;
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

		Class<?> remoteModelClass = _appPricingItemRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appPricingItemRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppPricingItemLocalServiceUtil.addAppPricingItem(this);
		}
		else {
			AppPricingItemLocalServiceUtil.updateAppPricingItem(this);
		}
	}

	@Override
	public AppPricingItem toEscapedModel() {
		return (AppPricingItem)ProxyUtil.newProxyInstance(AppPricingItem.class.getClassLoader(),
			new Class[] { AppPricingItem.class },
			new AutoEscapeBeanHandler(this));
	}

	public AppPricingItem toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppPricingItemClp clone = new AppPricingItemClp();

		clone.setAppPricingItemId(getAppPricingItemId());
		clone.setAppPricingId(getAppPricingId());
		clone.setAssetLicenseId(getAssetLicenseId());
		clone.setCurrencyEntryId(getCurrencyEntryId());
		clone.setPrice(getPrice());

		return clone;
	}

	public int compareTo(AppPricingItem appPricingItem) {
		long primaryKey = appPricingItem.getPrimaryKey();

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

		if (!(obj instanceof AppPricingItemClp)) {
			return false;
		}

		AppPricingItemClp appPricingItem = (AppPricingItemClp)obj;

		long primaryKey = appPricingItem.getPrimaryKey();

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

		sb.append("{appPricingItemId=");
		sb.append(getAppPricingItemId());
		sb.append(", appPricingId=");
		sb.append(getAppPricingId());
		sb.append(", assetLicenseId=");
		sb.append(getAssetLicenseId());
		sb.append(", currencyEntryId=");
		sb.append(getCurrencyEntryId());
		sb.append(", price=");
		sb.append(getPrice());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppPricingItem");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>appPricingItemId</column-name><column-value><![CDATA[");
		sb.append(getAppPricingItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appPricingId</column-name><column-value><![CDATA[");
		sb.append(getAppPricingId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetLicenseId</column-name><column-value><![CDATA[");
		sb.append(getAssetLicenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyEntryId</column-name><column-value><![CDATA[");
		sb.append(getCurrencyEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>price</column-name><column-value><![CDATA[");
		sb.append(getPrice());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _appPricingItemId;
	private long _appPricingId;
	private long _assetLicenseId;
	private long _currencyEntryId;
	private double _price;
	private BaseModel<?> _appPricingItemRemoteModel;
}