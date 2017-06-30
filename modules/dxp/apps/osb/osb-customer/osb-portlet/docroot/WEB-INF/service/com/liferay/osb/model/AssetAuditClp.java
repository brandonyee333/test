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

import com.liferay.osb.service.AssetAuditLocalServiceUtil;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetAuditClp extends BaseModelImpl<AssetAudit>
	implements AssetAudit {
	public AssetAuditClp() {
	}

	public Class<?> getModelClass() {
		return AssetAudit.class;
	}

	public String getModelClassName() {
		return AssetAudit.class.getName();
	}

	public long getPrimaryKey() {
		return _assetAuditId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetAuditId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetAuditId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetAuditId", getAssetAuditId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("legalEntityName", getLegalEntityName());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("vendorClassNameId", getVendorClassNameId());
		attributes.put("vendorClassPK", getVendorClassPK());
		attributes.put("type", getType());
		attributes.put("domain", getDomain());
		attributes.put("time", getTime());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("price", getPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetAuditId = (Long)attributes.get("assetAuditId");

		if (assetAuditId != null) {
			setAssetAuditId(assetAuditId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		String legalEntityName = (String)attributes.get("legalEntityName");

		if (legalEntityName != null) {
			setLegalEntityName(legalEntityName);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long vendorClassNameId = (Long)attributes.get("vendorClassNameId");

		if (vendorClassNameId != null) {
			setVendorClassNameId(vendorClassNameId);
		}

		Long vendorClassPK = (Long)attributes.get("vendorClassPK");

		if (vendorClassPK != null) {
			setVendorClassPK(vendorClassPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer domain = (Integer)attributes.get("domain");

		if (domain != null) {
			setDomain(domain);
		}

		Long time = (Long)attributes.get("time");

		if (time != null) {
			setTime(time);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	public long getAssetAuditId() {
		return _assetAuditId;
	}

	public void setAssetAuditId(long assetAuditId) {
		_assetAuditId = assetAuditId;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetAuditId", long.class);

				method.invoke(_assetAuditRemoteModel, assetAuditId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_assetAuditRemoteModel, companyId);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetAuditRemoteModel, userId);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_assetAuditRemoteModel, userName);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetAuditRemoteModel, createDate);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_assetAuditRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setLegalEntityName",
						String.class);

				method.invoke(_assetAuditRemoteModel, legalEntityName);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetAuditRemoteModel, classNameId);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetAuditRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getVendorClassNameId() {
		return _vendorClassNameId;
	}

	public void setVendorClassNameId(long vendorClassNameId) {
		_vendorClassNameId = vendorClassNameId;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setVendorClassNameId",
						long.class);

				method.invoke(_assetAuditRemoteModel, vendorClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getVendorClassPK() {
		return _vendorClassPK;
	}

	public void setVendorClassPK(long vendorClassPK) {
		_vendorClassPK = vendorClassPK;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setVendorClassPK", long.class);

				method.invoke(_assetAuditRemoteModel, vendorClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_assetAuditRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getDomain() {
		return _domain;
	}

	public void setDomain(int domain) {
		_domain = domain;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setDomain", int.class);

				method.invoke(_assetAuditRemoteModel, domain);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTime() {
		return _time;
	}

	public void setTime(long time) {
		_time = time;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setTime", long.class);

				method.invoke(_assetAuditRemoteModel, time);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrencyCode", String.class);

				method.invoke(_assetAuditRemoteModel, currencyCode);
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

		if (_assetAuditRemoteModel != null) {
			try {
				Class<?> clazz = _assetAuditRemoteModel.getClass();

				Method method = clazz.getMethod("setPrice", double.class);

				method.invoke(_assetAuditRemoteModel, price);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetAuditRemoteModel() {
		return _assetAuditRemoteModel;
	}

	public void setAssetAuditRemoteModel(BaseModel<?> assetAuditRemoteModel) {
		_assetAuditRemoteModel = assetAuditRemoteModel;
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

		Class<?> remoteModelClass = _assetAuditRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetAuditRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetAuditLocalServiceUtil.addAssetAudit(this);
		}
		else {
			AssetAuditLocalServiceUtil.updateAssetAudit(this);
		}
	}

	@Override
	public AssetAudit toEscapedModel() {
		return (AssetAudit)ProxyUtil.newProxyInstance(AssetAudit.class.getClassLoader(),
			new Class[] { AssetAudit.class }, new AutoEscapeBeanHandler(this));
	}

	public AssetAudit toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AssetAuditClp clone = new AssetAuditClp();

		clone.setAssetAuditId(getAssetAuditId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setLegalEntityName(getLegalEntityName());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setVendorClassNameId(getVendorClassNameId());
		clone.setVendorClassPK(getVendorClassPK());
		clone.setType(getType());
		clone.setDomain(getDomain());
		clone.setTime(getTime());
		clone.setCurrencyCode(getCurrencyCode());
		clone.setPrice(getPrice());

		return clone;
	}

	public int compareTo(AssetAudit assetAudit) {
		long primaryKey = assetAudit.getPrimaryKey();

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

		if (!(obj instanceof AssetAuditClp)) {
			return false;
		}

		AssetAuditClp assetAudit = (AssetAuditClp)obj;

		long primaryKey = assetAudit.getPrimaryKey();

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
		StringBundler sb = new StringBundler(33);

		sb.append("{assetAuditId=");
		sb.append(getAssetAuditId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", legalEntityName=");
		sb.append(getLegalEntityName());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", vendorClassNameId=");
		sb.append(getVendorClassNameId());
		sb.append(", vendorClassPK=");
		sb.append(getVendorClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", domain=");
		sb.append(getDomain());
		sb.append(", time=");
		sb.append(getTime());
		sb.append(", currencyCode=");
		sb.append(getCurrencyCode());
		sb.append(", price=");
		sb.append(getPrice());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AssetAudit");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetAuditId</column-name><column-value><![CDATA[");
		sb.append(getAssetAuditId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>legalEntityName</column-name><column-value><![CDATA[");
		sb.append(getLegalEntityName());
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
			"<column><column-name>vendorClassNameId</column-name><column-value><![CDATA[");
		sb.append(getVendorClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>vendorClassPK</column-name><column-value><![CDATA[");
		sb.append(getVendorClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>domain</column-name><column-value><![CDATA[");
		sb.append(getDomain());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>time</column-name><column-value><![CDATA[");
		sb.append(getTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyCode</column-name><column-value><![CDATA[");
		sb.append(getCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>price</column-name><column-value><![CDATA[");
		sb.append(getPrice());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetAuditId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _legalEntityName;
	private long _classNameId;
	private long _classPK;
	private long _vendorClassNameId;
	private long _vendorClassPK;
	private int _type;
	private int _domain;
	private long _time;
	private String _currencyCode;
	private double _price;
	private BaseModel<?> _assetAuditRemoteModel;
}