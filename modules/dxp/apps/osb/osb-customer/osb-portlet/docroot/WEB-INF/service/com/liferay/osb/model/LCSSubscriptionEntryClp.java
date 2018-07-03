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
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryClp extends BaseModelImpl<LCSSubscriptionEntry>
	implements LCSSubscriptionEntry {
	public LCSSubscriptionEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return LCSSubscriptionEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LCSSubscriptionEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _lcsSubscriptionEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLcsSubscriptionEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsSubscriptionEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsSubscriptionEntryId", getLcsSubscriptionEntryId());
		attributes.put("lcsProjectId", getLcsProjectId());
		attributes.put("product", getProduct());
		attributes.put("productVersion", getProductVersion());
		attributes.put("type", getType());
		attributes.put("platform", getPlatform());
		attributes.put("platformVersion", getPlatformVersion());
		attributes.put("serversAllowed", getServersAllowed());
		attributes.put("serversUsed", getServersUsed());
		attributes.put("quantity", getQuantity());
		attributes.put("instanceSize", getInstanceSize());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("supportStartDate", getSupportStartDate());
		attributes.put("supportEndDate", getSupportEndDate());
		attributes.put("actualPrice", getActualPrice());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("active", getActive());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsSubscriptionEntryId = (Long)attributes.get(
				"lcsSubscriptionEntryId");

		if (lcsSubscriptionEntryId != null) {
			setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
		}

		Long lcsProjectId = (Long)attributes.get("lcsProjectId");

		if (lcsProjectId != null) {
			setLcsProjectId(lcsProjectId);
		}

		String product = (String)attributes.get("product");

		if (product != null) {
			setProduct(product);
		}

		Integer productVersion = (Integer)attributes.get("productVersion");

		if (productVersion != null) {
			setProductVersion(productVersion);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String platform = (String)attributes.get("platform");

		if (platform != null) {
			setPlatform(platform);
		}

		String platformVersion = (String)attributes.get("platformVersion");

		if (platformVersion != null) {
			setPlatformVersion(platformVersion);
		}

		Integer serversAllowed = (Integer)attributes.get("serversAllowed");

		if (serversAllowed != null) {
			setServersAllowed(serversAllowed);
		}

		Integer serversUsed = (Integer)attributes.get("serversUsed");

		if (serversUsed != null) {
			setServersUsed(serversUsed);
		}

		Integer quantity = (Integer)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		Integer instanceSize = (Integer)attributes.get("instanceSize");

		if (instanceSize != null) {
			setInstanceSize(instanceSize);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date supportStartDate = (Date)attributes.get("supportStartDate");

		if (supportStartDate != null) {
			setSupportStartDate(supportStartDate);
		}

		Date supportEndDate = (Date)attributes.get("supportEndDate");

		if (supportEndDate != null) {
			setSupportEndDate(supportEndDate);
		}

		Double actualPrice = (Double)attributes.get("actualPrice");

		if (actualPrice != null) {
			setActualPrice(actualPrice);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getLcsSubscriptionEntryId() {
		return _lcsSubscriptionEntryId;
	}

	@Override
	public void setLcsSubscriptionEntryId(long lcsSubscriptionEntryId) {
		_lcsSubscriptionEntryId = lcsSubscriptionEntryId;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLcsSubscriptionEntryId",
						long.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel,
					lcsSubscriptionEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	@Override
	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLcsProjectId", long.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, lcsProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProduct() {
		return _product;
	}

	@Override
	public void setProduct(String product) {
		_product = product;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProduct", String.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, product);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getProductVersion() {
		return _productVersion;
	}

	@Override
	public void setProductVersion(int productVersion) {
		_productVersion = productVersion;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductVersion", int.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, productVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPlatform() {
		return _platform;
	}

	@Override
	public void setPlatform(String platform) {
		_platform = platform;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPlatform", String.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, platform);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPlatformVersion() {
		return _platformVersion;
	}

	@Override
	public void setPlatformVersion(String platformVersion) {
		_platformVersion = platformVersion;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPlatformVersion",
						String.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, platformVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getServersAllowed() {
		return _serversAllowed;
	}

	@Override
	public void setServersAllowed(int serversAllowed) {
		_serversAllowed = serversAllowed;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setServersAllowed", int.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, serversAllowed);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getServersUsed() {
		return _serversUsed;
	}

	@Override
	public void setServersUsed(int serversUsed) {
		_serversUsed = serversUsed;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setServersUsed", int.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, serversUsed);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setQuantity", int.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, quantity);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getInstanceSize() {
		return _instanceSize;
	}

	@Override
	public void setInstanceSize(int instanceSize) {
		_instanceSize = instanceSize;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setInstanceSize", int.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, instanceSize);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDate", Date.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, endDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getSupportStartDate() {
		return _supportStartDate;
	}

	@Override
	public void setSupportStartDate(Date supportStartDate) {
		_supportStartDate = supportStartDate;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportStartDate",
						Date.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, supportStartDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	@Override
	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportEndDate", Date.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, supportEndDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getActualPrice() {
		return _actualPrice;
	}

	@Override
	public void setActualPrice(double actualPrice) {
		_actualPrice = actualPrice;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setActualPrice", double.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, actualPrice);
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

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyCode", String.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, currencyCode);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;

		if (_lcsSubscriptionEntryRemoteModel != null) {
			try {
				Class<?> clazz = _lcsSubscriptionEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setActive", boolean.class);

				method.invoke(_lcsSubscriptionEntryRemoteModel, active);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getLCSSubscriptionEntryRemoteModel() {
		return _lcsSubscriptionEntryRemoteModel;
	}

	public void setLCSSubscriptionEntryRemoteModel(
		BaseModel<?> lcsSubscriptionEntryRemoteModel) {
		_lcsSubscriptionEntryRemoteModel = lcsSubscriptionEntryRemoteModel;
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

		Class<?> remoteModelClass = _lcsSubscriptionEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_lcsSubscriptionEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			LCSSubscriptionEntryLocalServiceUtil.addLCSSubscriptionEntry(this);
		}
		else {
			LCSSubscriptionEntryLocalServiceUtil.updateLCSSubscriptionEntry(this);
		}
	}

	@Override
	public LCSSubscriptionEntry toEscapedModel() {
		return (LCSSubscriptionEntry)ProxyUtil.newProxyInstance(LCSSubscriptionEntry.class.getClassLoader(),
			new Class[] { LCSSubscriptionEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		LCSSubscriptionEntryClp clone = new LCSSubscriptionEntryClp();

		clone.setLcsSubscriptionEntryId(getLcsSubscriptionEntryId());
		clone.setLcsProjectId(getLcsProjectId());
		clone.setProduct(getProduct());
		clone.setProductVersion(getProductVersion());
		clone.setType(getType());
		clone.setPlatform(getPlatform());
		clone.setPlatformVersion(getPlatformVersion());
		clone.setServersAllowed(getServersAllowed());
		clone.setServersUsed(getServersUsed());
		clone.setQuantity(getQuantity());
		clone.setInstanceSize(getInstanceSize());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setSupportStartDate(getSupportStartDate());
		clone.setSupportEndDate(getSupportEndDate());
		clone.setActualPrice(getActualPrice());
		clone.setCurrencyCode(getCurrencyCode());
		clone.setActive(getActive());

		return clone;
	}

	@Override
	public int compareTo(LCSSubscriptionEntry lcsSubscriptionEntry) {
		long primaryKey = lcsSubscriptionEntry.getPrimaryKey();

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

		if (!(obj instanceof LCSSubscriptionEntryClp)) {
			return false;
		}

		LCSSubscriptionEntryClp lcsSubscriptionEntry = (LCSSubscriptionEntryClp)obj;

		long primaryKey = lcsSubscriptionEntry.getPrimaryKey();

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

		sb.append("{lcsSubscriptionEntryId=");
		sb.append(getLcsSubscriptionEntryId());
		sb.append(", lcsProjectId=");
		sb.append(getLcsProjectId());
		sb.append(", product=");
		sb.append(getProduct());
		sb.append(", productVersion=");
		sb.append(getProductVersion());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", platform=");
		sb.append(getPlatform());
		sb.append(", platformVersion=");
		sb.append(getPlatformVersion());
		sb.append(", serversAllowed=");
		sb.append(getServersAllowed());
		sb.append(", serversUsed=");
		sb.append(getServersUsed());
		sb.append(", quantity=");
		sb.append(getQuantity());
		sb.append(", instanceSize=");
		sb.append(getInstanceSize());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", supportStartDate=");
		sb.append(getSupportStartDate());
		sb.append(", supportEndDate=");
		sb.append(getSupportEndDate());
		sb.append(", actualPrice=");
		sb.append(getActualPrice());
		sb.append(", currencyCode=");
		sb.append(getCurrencyCode());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.LCSSubscriptionEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>lcsSubscriptionEntryId</column-name><column-value><![CDATA[");
		sb.append(getLcsSubscriptionEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lcsProjectId</column-name><column-value><![CDATA[");
		sb.append(getLcsProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>product</column-name><column-value><![CDATA[");
		sb.append(getProduct());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productVersion</column-name><column-value><![CDATA[");
		sb.append(getProductVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>platform</column-name><column-value><![CDATA[");
		sb.append(getPlatform());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>platformVersion</column-name><column-value><![CDATA[");
		sb.append(getPlatformVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serversAllowed</column-name><column-value><![CDATA[");
		sb.append(getServersAllowed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serversUsed</column-name><column-value><![CDATA[");
		sb.append(getServersUsed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quantity</column-name><column-value><![CDATA[");
		sb.append(getQuantity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>instanceSize</column-name><column-value><![CDATA[");
		sb.append(getInstanceSize());
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
			"<column><column-name>supportStartDate</column-name><column-value><![CDATA[");
		sb.append(getSupportStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportEndDate</column-name><column-value><![CDATA[");
		sb.append(getSupportEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualPrice</column-name><column-value><![CDATA[");
		sb.append(getActualPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyCode</column-name><column-value><![CDATA[");
		sb.append(getCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _lcsSubscriptionEntryId;
	private long _lcsProjectId;
	private String _product;
	private int _productVersion;
	private String _type;
	private String _platform;
	private String _platformVersion;
	private int _serversAllowed;
	private int _serversUsed;
	private int _quantity;
	private int _instanceSize;
	private Date _startDate;
	private Date _endDate;
	private Date _supportStartDate;
	private Date _supportEndDate;
	private double _actualPrice;
	private String _currencyCode;
	private boolean _active;
	private BaseModel<?> _lcsSubscriptionEntryRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}