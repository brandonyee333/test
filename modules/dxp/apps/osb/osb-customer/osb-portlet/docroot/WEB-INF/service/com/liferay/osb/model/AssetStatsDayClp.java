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

import com.liferay.osb.service.AssetStatsDayLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
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
public class AssetStatsDayClp extends BaseModelImpl<AssetStatsDay>
	implements AssetStatsDay {
	public AssetStatsDayClp() {
	}

	public Class<?> getModelClass() {
		return AssetStatsDay.class;
	}

	public String getModelClassName() {
		return AssetStatsDay.class.getName();
	}

	public long getPrimaryKey() {
		return _assetStatsDayId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetStatsDayId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetStatsDayId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetStatsDayId", getAssetStatsDayId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("day", getDay());
		attributes.put("year", getYear());
		attributes.put("viewCount", getViewCount());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("purchaseCount", getPurchaseCount());
		attributes.put("currencyCodeRevenues", getCurrencyCodeRevenues());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetStatsDayId = (Long)attributes.get("assetStatsDayId");

		if (assetStatsDayId != null) {
			setAssetStatsDayId(assetStatsDayId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer day = (Integer)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Long viewCount = (Long)attributes.get("viewCount");

		if (viewCount != null) {
			setViewCount(viewCount);
		}

		Long downloadCount = (Long)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}

		Long purchaseCount = (Long)attributes.get("purchaseCount");

		if (purchaseCount != null) {
			setPurchaseCount(purchaseCount);
		}

		String currencyCodeRevenues = (String)attributes.get(
				"currencyCodeRevenues");

		if (currencyCodeRevenues != null) {
			setCurrencyCodeRevenues(currencyCodeRevenues);
		}
	}

	public long getAssetStatsDayId() {
		return _assetStatsDayId;
	}

	public void setAssetStatsDayId(long assetStatsDayId) {
		_assetStatsDayId = assetStatsDayId;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetStatsDayId", long.class);

				method.invoke(_assetStatsDayRemoteModel, assetStatsDayId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetStatsDayRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetStatsDayRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getDay() {
		return _day;
	}

	public void setDay(int day) {
		_day = day;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setDay", int.class);

				method.invoke(_assetStatsDayRemoteModel, day);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setYear", int.class);

				method.invoke(_assetStatsDayRemoteModel, year);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getViewCount() {
		return _viewCount;
	}

	public void setViewCount(long viewCount) {
		_viewCount = viewCount;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setViewCount", long.class);

				method.invoke(_assetStatsDayRemoteModel, viewCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		_downloadCount = downloadCount;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setDownloadCount", long.class);

				method.invoke(_assetStatsDayRemoteModel, downloadCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getPurchaseCount() {
		return _purchaseCount;
	}

	public void setPurchaseCount(long purchaseCount) {
		_purchaseCount = purchaseCount;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setPurchaseCount", long.class);

				method.invoke(_assetStatsDayRemoteModel, purchaseCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getCurrencyCodeRevenues() {
		return _currencyCodeRevenues;
	}

	public void setCurrencyCodeRevenues(String currencyCodeRevenues) {
		_currencyCodeRevenues = currencyCodeRevenues;

		if (_assetStatsDayRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsDayRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyCodeRevenues",
						String.class);

				method.invoke(_assetStatsDayRemoteModel, currencyCodeRevenues);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getStats(int statsType) {
		try {
			String methodName = "getStats";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { statsType };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getStartDate(java.util.TimeZone timeZone,
		java.util.Locale locale) {
		try {
			String methodName = "getStartDate";

			Class<?>[] parameterTypes = new Class<?>[] {
					java.util.TimeZone.class, java.util.Locale.class
				};

			Object[] parameterValues = new Object[] { timeZone, locale };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAssetStatsDayRemoteModel() {
		return _assetStatsDayRemoteModel;
	}

	public void setAssetStatsDayRemoteModel(
		BaseModel<?> assetStatsDayRemoteModel) {
		_assetStatsDayRemoteModel = assetStatsDayRemoteModel;
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

		Class<?> remoteModelClass = _assetStatsDayRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetStatsDayRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetStatsDayLocalServiceUtil.addAssetStatsDay(this);
		}
		else {
			AssetStatsDayLocalServiceUtil.updateAssetStatsDay(this);
		}
	}

	@Override
	public AssetStatsDay toEscapedModel() {
		return (AssetStatsDay)ProxyUtil.newProxyInstance(AssetStatsDay.class.getClassLoader(),
			new Class[] { AssetStatsDay.class }, new AutoEscapeBeanHandler(this));
	}

	public AssetStatsDay toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetStatsDayClp clone = new AssetStatsDayClp();

		clone.setAssetStatsDayId(getAssetStatsDayId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setDay(getDay());
		clone.setYear(getYear());
		clone.setViewCount(getViewCount());
		clone.setDownloadCount(getDownloadCount());
		clone.setPurchaseCount(getPurchaseCount());
		clone.setCurrencyCodeRevenues(getCurrencyCodeRevenues());

		return clone;
	}

	public int compareTo(AssetStatsDay assetStatsDay) {
		long primaryKey = assetStatsDay.getPrimaryKey();

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

		if (!(obj instanceof AssetStatsDayClp)) {
			return false;
		}

		AssetStatsDayClp assetStatsDay = (AssetStatsDayClp)obj;

		long primaryKey = assetStatsDay.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{assetStatsDayId=");
		sb.append(getAssetStatsDayId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", day=");
		sb.append(getDay());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", viewCount=");
		sb.append(getViewCount());
		sb.append(", downloadCount=");
		sb.append(getDownloadCount());
		sb.append(", purchaseCount=");
		sb.append(getPurchaseCount());
		sb.append(", currencyCodeRevenues=");
		sb.append(getCurrencyCodeRevenues());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetStatsDay");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetStatsDayId</column-name><column-value><![CDATA[");
		sb.append(getAssetStatsDayId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>day</column-name><column-value><![CDATA[");
		sb.append(getDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>viewCount</column-name><column-value><![CDATA[");
		sb.append(getViewCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>downloadCount</column-name><column-value><![CDATA[");
		sb.append(getDownloadCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>purchaseCount</column-name><column-value><![CDATA[");
		sb.append(getPurchaseCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyCodeRevenues</column-name><column-value><![CDATA[");
		sb.append(getCurrencyCodeRevenues());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetStatsDayId;
	private long _classNameId;
	private long _classPK;
	private int _day;
	private int _year;
	private long _viewCount;
	private long _downloadCount;
	private long _purchaseCount;
	private String _currencyCodeRevenues;
	private BaseModel<?> _assetStatsDayRemoteModel;
}