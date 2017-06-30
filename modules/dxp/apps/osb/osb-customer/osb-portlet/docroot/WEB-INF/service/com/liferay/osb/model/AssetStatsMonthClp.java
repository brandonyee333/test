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

import com.liferay.osb.service.AssetStatsMonthLocalServiceUtil;
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
public class AssetStatsMonthClp extends BaseModelImpl<AssetStatsMonth>
	implements AssetStatsMonth {
	public AssetStatsMonthClp() {
	}

	public Class<?> getModelClass() {
		return AssetStatsMonth.class;
	}

	public String getModelClassName() {
		return AssetStatsMonth.class.getName();
	}

	public long getPrimaryKey() {
		return _assetStatsMonthId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetStatsMonthId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetStatsMonthId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetStatsMonthId", getAssetStatsMonthId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("month", getMonth());
		attributes.put("year", getYear());
		attributes.put("viewCount", getViewCount());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("purchaseCount", getPurchaseCount());
		attributes.put("currencyCodeRevenues", getCurrencyCodeRevenues());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetStatsMonthId = (Long)attributes.get("assetStatsMonthId");

		if (assetStatsMonthId != null) {
			setAssetStatsMonthId(assetStatsMonthId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer month = (Integer)attributes.get("month");

		if (month != null) {
			setMonth(month);
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

	public long getAssetStatsMonthId() {
		return _assetStatsMonthId;
	}

	public void setAssetStatsMonthId(long assetStatsMonthId) {
		_assetStatsMonthId = assetStatsMonthId;

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetStatsMonthId",
						long.class);

				method.invoke(_assetStatsMonthRemoteModel, assetStatsMonthId);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetStatsMonthRemoteModel, classNameId);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetStatsMonthRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getMonth() {
		return _month;
	}

	public void setMonth(int month) {
		_month = month;

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setMonth", int.class);

				method.invoke(_assetStatsMonthRemoteModel, month);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setYear", int.class);

				method.invoke(_assetStatsMonthRemoteModel, year);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setViewCount", long.class);

				method.invoke(_assetStatsMonthRemoteModel, viewCount);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setDownloadCount", long.class);

				method.invoke(_assetStatsMonthRemoteModel, downloadCount);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setPurchaseCount", long.class);

				method.invoke(_assetStatsMonthRemoteModel, purchaseCount);
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

		if (_assetStatsMonthRemoteModel != null) {
			try {
				Class<?> clazz = _assetStatsMonthRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyCodeRevenues",
						String.class);

				method.invoke(_assetStatsMonthRemoteModel, currencyCodeRevenues);
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

	public BaseModel<?> getAssetStatsMonthRemoteModel() {
		return _assetStatsMonthRemoteModel;
	}

	public void setAssetStatsMonthRemoteModel(
		BaseModel<?> assetStatsMonthRemoteModel) {
		_assetStatsMonthRemoteModel = assetStatsMonthRemoteModel;
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

		Class<?> remoteModelClass = _assetStatsMonthRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetStatsMonthRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetStatsMonthLocalServiceUtil.addAssetStatsMonth(this);
		}
		else {
			AssetStatsMonthLocalServiceUtil.updateAssetStatsMonth(this);
		}
	}

	@Override
	public AssetStatsMonth toEscapedModel() {
		return (AssetStatsMonth)ProxyUtil.newProxyInstance(AssetStatsMonth.class.getClassLoader(),
			new Class[] { AssetStatsMonth.class },
			new AutoEscapeBeanHandler(this));
	}

	public AssetStatsMonth toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetStatsMonthClp clone = new AssetStatsMonthClp();

		clone.setAssetStatsMonthId(getAssetStatsMonthId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setMonth(getMonth());
		clone.setYear(getYear());
		clone.setViewCount(getViewCount());
		clone.setDownloadCount(getDownloadCount());
		clone.setPurchaseCount(getPurchaseCount());
		clone.setCurrencyCodeRevenues(getCurrencyCodeRevenues());

		return clone;
	}

	public int compareTo(AssetStatsMonth assetStatsMonth) {
		long primaryKey = assetStatsMonth.getPrimaryKey();

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

		if (!(obj instanceof AssetStatsMonthClp)) {
			return false;
		}

		AssetStatsMonthClp assetStatsMonth = (AssetStatsMonthClp)obj;

		long primaryKey = assetStatsMonth.getPrimaryKey();

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

		sb.append("{assetStatsMonthId=");
		sb.append(getAssetStatsMonthId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", month=");
		sb.append(getMonth());
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
		sb.append("com.liferay.osb.model.AssetStatsMonth");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetStatsMonthId</column-name><column-value><![CDATA[");
		sb.append(getAssetStatsMonthId());
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
			"<column><column-name>month</column-name><column-value><![CDATA[");
		sb.append(getMonth());
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

	private long _assetStatsMonthId;
	private long _classNameId;
	private long _classPK;
	private int _month;
	private int _year;
	private long _viewCount;
	private long _downloadCount;
	private long _purchaseCount;
	private String _currencyCodeRevenues;
	private BaseModel<?> _assetStatsMonthRemoteModel;
}