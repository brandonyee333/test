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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.watson.service.ClpSerializer;
import com.liferay.watson.service.WatsonListTypeRelLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @generated
 */
@ProviderType
public class WatsonListTypeRelClp extends BaseModelImpl<WatsonListTypeRel>
	implements WatsonListTypeRel {
	public WatsonListTypeRelClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonListTypeRel.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonListTypeRel.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _watsonListTypeRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonListTypeRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonListTypeRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonListTypeRelId", getWatsonListTypeRelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonListTypeId", getWatsonListTypeId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("primary", getPrimary());
		attributes.put("value", getValue());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonListTypeRelId = (Long)attributes.get("watsonListTypeRelId");

		if (watsonListTypeRelId != null) {
			setWatsonListTypeRelId(watsonListTypeRelId);
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

		Long watsonListTypeId = (Long)attributes.get("watsonListTypeId");

		if (watsonListTypeId != null) {
			setWatsonListTypeId(watsonListTypeId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonListTypeRelId() {
		return _watsonListTypeRelId;
	}

	@Override
	public void setWatsonListTypeRelId(long watsonListTypeRelId) {
		_watsonListTypeRelId = watsonListTypeRelId;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonListTypeRelId",
						long.class);

				method.invoke(_watsonListTypeRelRemoteModel, watsonListTypeRelId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonListTypeRelRemoteModel, companyId);
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

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonListTypeRelRemoteModel, userId);
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

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonListTypeRelRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonListTypeRelRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonListTypeRelRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWatsonListTypeId() {
		return _watsonListTypeId;
	}

	@Override
	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeId = watsonListTypeId;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonListTypeId",
						long.class);

				method.invoke(_watsonListTypeRelRemoteModel, watsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_watsonListTypeRelRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_watsonListTypeRelRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPrimary() {
		return _primary;
	}

	@Override
	public boolean isPrimary() {
		return _primary;
	}

	@Override
	public void setPrimary(boolean primary) {
		_primary = primary;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setPrimary", boolean.class);

				method.invoke(_watsonListTypeRelRemoteModel, primary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getValue() {
		return _value;
	}

	@Override
	public String getValue(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getValue(languageId);
	}

	@Override
	public String getValue(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getValue(languageId, useDefault);
	}

	@Override
	public String getValue(String languageId) {
		return LocalizationUtil.getLocalization(getValue(), languageId);
	}

	@Override
	public String getValue(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getValue(), languageId,
			useDefault);
	}

	@Override
	public String getValueCurrentLanguageId() {
		return _valueCurrentLanguageId;
	}

	@Override
	public String getValueCurrentValue() {
		Locale locale = getLocale(_valueCurrentLanguageId);

		return getValue(locale);
	}

	@Override
	public Map<Locale, String> getValueMap() {
		return LocalizationUtil.getLocalizationMap(getValue());
	}

	@Override
	public void setValue(String value) {
		_value = value;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setValue", String.class);

				method.invoke(_watsonListTypeRelRemoteModel, value);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setValue(String value, Locale locale) {
		setValue(value, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setValue(String value, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(value)) {
			setValue(LocalizationUtil.updateLocalization(getValue(), "Value",
					value, languageId, defaultLanguageId));
		}
		else {
			setValue(LocalizationUtil.removeLocalization(getValue(), "Value",
					languageId));
		}
	}

	@Override
	public void setValueCurrentLanguageId(String languageId) {
		_valueCurrentLanguageId = languageId;
	}

	@Override
	public void setValueMap(Map<Locale, String> valueMap) {
		setValueMap(valueMap, LocaleUtil.getDefault());
	}

	@Override
	public void setValueMap(Map<Locale, String> valueMap, Locale defaultLocale) {
		if (valueMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setValue(LocalizationUtil.updateLocalization(valueMap, getValue(),
					"Value", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
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

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_watsonListTypeRelRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_watsonListTypeRelRemoteModel != null) {
			try {
				Class<?> clazz = _watsonListTypeRelRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonListTypeRelRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonListTypeRelRemoteModel() {
		return _watsonListTypeRelRemoteModel;
	}

	public void setWatsonListTypeRelRemoteModel(
		BaseModel<?> watsonListTypeRelRemoteModel) {
		_watsonListTypeRelRemoteModel = watsonListTypeRelRemoteModel;
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

		Class<?> remoteModelClass = _watsonListTypeRelRemoteModel.getClass();

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

		Object returnValue = method.invoke(_watsonListTypeRelRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonListTypeRelLocalServiceUtil.addWatsonListTypeRel(this);
		}
		else {
			WatsonListTypeRelLocalServiceUtil.updateWatsonListTypeRel(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> valueMap = getValueMap();

		for (Map.Entry<Locale, String> entry : valueMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getValue();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		prepareLocalizedFieldsForImport(null);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String value = getValue(defaultLocale);

		if (Validator.isNull(value)) {
			setValue(getValue(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setValue(getValue(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public WatsonListTypeRel toEscapedModel() {
		return (WatsonListTypeRel)ProxyUtil.newProxyInstance(WatsonListTypeRel.class.getClassLoader(),
			new Class[] { WatsonListTypeRel.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonListTypeRelClp clone = new WatsonListTypeRelClp();

		clone.setWatsonListTypeRelId(getWatsonListTypeRelId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setWatsonListTypeId(getWatsonListTypeId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setPrimary(getPrimary());
		clone.setValue(getValue());
		clone.setType(getType());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonListTypeRel watsonListTypeRel) {
		long primaryKey = watsonListTypeRel.getPrimaryKey();

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

		if (!(obj instanceof WatsonListTypeRelClp)) {
			return false;
		}

		WatsonListTypeRelClp watsonListTypeRel = (WatsonListTypeRelClp)obj;

		long primaryKey = watsonListTypeRel.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{watsonListTypeRelId=");
		sb.append(getWatsonListTypeRelId());
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
		sb.append(", watsonListTypeId=");
		sb.append(getWatsonListTypeId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", primary=");
		sb.append(getPrimary());
		sb.append(", value=");
		sb.append(getValue());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonListTypeRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonListTypeRelId</column-name><column-value><![CDATA[");
		sb.append(getWatsonListTypeRelId());
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
			"<column><column-name>watsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getWatsonListTypeId());
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
			"<column><column-name>primary</column-name><column-value><![CDATA[");
		sb.append(getPrimary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>value</column-name><column-value><![CDATA[");
		sb.append(getValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonListTypeRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonListTypeId;
	private long _classNameId;
	private long _classPK;
	private boolean _primary;
	private String _value;
	private String _valueCurrentLanguageId;
	private String _type;
	private int _status;
	private BaseModel<?> _watsonListTypeRelRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}