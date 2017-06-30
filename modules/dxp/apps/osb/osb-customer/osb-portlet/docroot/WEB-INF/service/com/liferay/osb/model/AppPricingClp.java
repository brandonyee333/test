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

import com.liferay.osb.service.AppPricingLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPricingClp extends BaseModelImpl<AppPricing>
	implements AppPricing {
	public AppPricingClp() {
	}

	public Class<?> getModelClass() {
		return AppPricing.class;
	}

	public String getModelClassName() {
		return AppPricing.class.getName();
	}

	public long getPrimaryKey() {
		return _appPricingId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppPricingId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appPricingId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPricingId", getAppPricingId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("name", getName());
		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("standardSupportPrice", getStandardSupportPrice());
		attributes.put("developerSupportPrice", getDeveloperSupportPrice());
		attributes.put("rank", getRank());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPricingId = (Long)attributes.get("appPricingId");

		if (appPricingId != null) {
			setAppPricingId(appPricingId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Double standardSupportPrice = (Double)attributes.get(
				"standardSupportPrice");

		if (standardSupportPrice != null) {
			setStandardSupportPrice(standardSupportPrice);
		}

		Double developerSupportPrice = (Double)attributes.get(
				"developerSupportPrice");

		if (developerSupportPrice != null) {
			setDeveloperSupportPrice(developerSupportPrice);
		}

		Integer rank = (Integer)attributes.get("rank");

		if (rank != null) {
			setRank(rank);
		}
	}

	public long getAppPricingId() {
		return _appPricingId;
	}

	public void setAppPricingId(long appPricingId) {
		_appPricingId = appPricingId;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setAppPricingId", long.class);

				method.invoke(_appPricingRemoteModel, appPricingId);
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

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_appPricingRemoteModel, userId);
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_appPricingRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_appPricingRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_appPricingRemoteModel, modifiedDate);
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

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_appPricingRemoteModel, appEntryId);
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

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setAppVersionId", long.class);

				method.invoke(_appPricingRemoteModel, appVersionId);
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

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_appPricingRemoteModel, name);
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

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyEntryId", long.class);

				method.invoke(_appPricingRemoteModel, currencyEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getStandardSupportPrice() {
		return _standardSupportPrice;
	}

	public void setStandardSupportPrice(double standardSupportPrice) {
		_standardSupportPrice = standardSupportPrice;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setStandardSupportPrice",
						double.class);

				method.invoke(_appPricingRemoteModel, standardSupportPrice);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getDeveloperSupportPrice() {
		return _developerSupportPrice;
	}

	public void setDeveloperSupportPrice(double developerSupportPrice) {
		_developerSupportPrice = developerSupportPrice;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setDeveloperSupportPrice",
						double.class);

				method.invoke(_appPricingRemoteModel, developerSupportPrice);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getRank() {
		return _rank;
	}

	public void setRank(int rank) {
		_rank = rank;

		if (_appPricingRemoteModel != null) {
			try {
				Class<?> clazz = _appPricingRemoteModel.getClass();

				Method method = clazz.getMethod("setRank", int.class);

				method.invoke(_appPricingRemoteModel, rank);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getSupportPrice(int usageType) {
		try {
			String methodName = "getSupportPrice";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { usageType };

			Double returnObj = (Double)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
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

	public java.lang.String getFormattedSupportPrice(int usageType,
		java.util.Locale locale) {
		try {
			String methodName = "getFormattedSupportPrice";

			Class<?>[] parameterTypes = new Class<?>[] {
					int.class, java.util.Locale.class
				};

			Object[] parameterValues = new Object[] { usageType, locale };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAppPricingRemoteModel() {
		return _appPricingRemoteModel;
	}

	public void setAppPricingRemoteModel(BaseModel<?> appPricingRemoteModel) {
		_appPricingRemoteModel = appPricingRemoteModel;
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

		Class<?> remoteModelClass = _appPricingRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appPricingRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppPricingLocalServiceUtil.addAppPricing(this);
		}
		else {
			AppPricingLocalServiceUtil.updateAppPricing(this);
		}
	}

	@Override
	public AppPricing toEscapedModel() {
		return (AppPricing)ProxyUtil.newProxyInstance(AppPricing.class.getClassLoader(),
			new Class[] { AppPricing.class }, new AutoEscapeBeanHandler(this));
	}

	public AppPricing toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppPricingClp clone = new AppPricingClp();

		clone.setAppPricingId(getAppPricingId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAppEntryId(getAppEntryId());
		clone.setAppVersionId(getAppVersionId());
		clone.setName(getName());
		clone.setCurrencyEntryId(getCurrencyEntryId());
		clone.setStandardSupportPrice(getStandardSupportPrice());
		clone.setDeveloperSupportPrice(getDeveloperSupportPrice());
		clone.setRank(getRank());

		return clone;
	}

	public int compareTo(AppPricing appPricing) {
		long primaryKey = appPricing.getPrimaryKey();

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

		if (!(obj instanceof AppPricingClp)) {
			return false;
		}

		AppPricingClp appPricing = (AppPricingClp)obj;

		long primaryKey = appPricing.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{appPricingId=");
		sb.append(getAppPricingId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", appVersionId=");
		sb.append(getAppVersionId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", currencyEntryId=");
		sb.append(getCurrencyEntryId());
		sb.append(", standardSupportPrice=");
		sb.append(getStandardSupportPrice());
		sb.append(", developerSupportPrice=");
		sb.append(getDeveloperSupportPrice());
		sb.append(", rank=");
		sb.append(getRank());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppPricing");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>appPricingId</column-name><column-value><![CDATA[");
		sb.append(getAppPricingId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyEntryId</column-name><column-value><![CDATA[");
		sb.append(getCurrencyEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>standardSupportPrice</column-name><column-value><![CDATA[");
		sb.append(getStandardSupportPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>developerSupportPrice</column-name><column-value><![CDATA[");
		sb.append(getDeveloperSupportPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rank</column-name><column-value><![CDATA[");
		sb.append(getRank());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _appPricingId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
	private long _appVersionId;
	private String _name;
	private long _currencyEntryId;
	private double _standardSupportPrice;
	private double _developerSupportPrice;
	private int _rank;
	private BaseModel<?> _appPricingRemoteModel;
}