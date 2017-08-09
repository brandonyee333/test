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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.watson.service.ClpSerializer;
import com.liferay.watson.service.WatsonActivityLocalServiceUtil;

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
public class WatsonActivityClp extends BaseModelImpl<WatsonActivity>
	implements WatsonActivity {
	public WatsonActivityClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonActivity.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonActivity.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _watsonActivityId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonActivityId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonActivityId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonActivityId", getWatsonActivityId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("narrative", getNarrative());
		attributes.put("reportDate", getReportDate());
		attributes.put("startDate", getStartDate());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonActivityId = (Long)attributes.get("watsonActivityId");

		if (watsonActivityId != null) {
			setWatsonActivityId(watsonActivityId);
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

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		String narrative = (String)attributes.get("narrative");

		if (narrative != null) {
			setNarrative(narrative);
		}

		Date reportDate = (Date)attributes.get("reportDate");

		if (reportDate != null) {
			setReportDate(reportDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonActivityId() {
		return _watsonActivityId;
	}

	@Override
	public void setWatsonActivityId(long watsonActivityId) {
		_watsonActivityId = watsonActivityId;

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonActivityId",
						long.class);

				method.invoke(_watsonActivityRemoteModel, watsonActivityId);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonActivityRemoteModel, companyId);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonActivityRemoteModel, userId);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonActivityRemoteModel, userName);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonActivityRemoteModel, createDate);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonActivityRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeWatsonListTypeId",
						long.class);

				method.invoke(_watsonActivityRemoteModel, typeWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId",
						long.class);

				method.invoke(_watsonActivityRemoteModel, watsonIncidentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNarrative() {
		return _narrative;
	}

	@Override
	public String getNarrative(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getNarrative(languageId);
	}

	@Override
	public String getNarrative(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getNarrative(languageId, useDefault);
	}

	@Override
	public String getNarrative(String languageId) {
		return LocalizationUtil.getLocalization(getNarrative(), languageId);
	}

	@Override
	public String getNarrative(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getNarrative(), languageId,
			useDefault);
	}

	@Override
	public String getNarrativeCurrentLanguageId() {
		return _narrativeCurrentLanguageId;
	}

	@Override
	public String getNarrativeCurrentValue() {
		Locale locale = getLocale(_narrativeCurrentLanguageId);

		return getNarrative(locale);
	}

	@Override
	public Map<Locale, String> getNarrativeMap() {
		return LocalizationUtil.getLocalizationMap(getNarrative());
	}

	@Override
	public void setNarrative(String narrative) {
		_narrative = narrative;

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setNarrative", String.class);

				method.invoke(_watsonActivityRemoteModel, narrative);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setNarrative(String narrative, Locale locale) {
		setNarrative(narrative, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setNarrative(String narrative, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(narrative)) {
			setNarrative(LocalizationUtil.updateLocalization(getNarrative(),
					"Narrative", narrative, languageId, defaultLanguageId));
		}
		else {
			setNarrative(LocalizationUtil.removeLocalization(getNarrative(),
					"Narrative", languageId));
		}
	}

	@Override
	public void setNarrativeCurrentLanguageId(String languageId) {
		_narrativeCurrentLanguageId = languageId;
	}

	@Override
	public void setNarrativeMap(Map<Locale, String> narrativeMap) {
		setNarrativeMap(narrativeMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNarrativeMap(Map<Locale, String> narrativeMap,
		Locale defaultLocale) {
		if (narrativeMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setNarrative(LocalizationUtil.updateLocalization(narrativeMap,
					getNarrative(), "Narrative",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public Date getReportDate() {
		return _reportDate;
	}

	@Override
	public void setReportDate(Date reportDate) {
		_reportDate = reportDate;

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setReportDate", Date.class);

				method.invoke(_watsonActivityRemoteModel, reportDate);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_watsonActivityRemoteModel, startDate);
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

		if (_watsonActivityRemoteModel != null) {
			try {
				Class<?> clazz = _watsonActivityRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonActivityRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonActivityRemoteModel() {
		return _watsonActivityRemoteModel;
	}

	public void setWatsonActivityRemoteModel(
		BaseModel<?> watsonActivityRemoteModel) {
		_watsonActivityRemoteModel = watsonActivityRemoteModel;
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

		Class<?> remoteModelClass = _watsonActivityRemoteModel.getClass();

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

		Object returnValue = method.invoke(_watsonActivityRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonActivityLocalServiceUtil.addWatsonActivity(this);
		}
		else {
			WatsonActivityLocalServiceUtil.updateWatsonActivity(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> narrativeMap = getNarrativeMap();

		for (Map.Entry<Locale, String> entry : narrativeMap.entrySet()) {
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
		String xml = getNarrative();

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

		String narrative = getNarrative(defaultLocale);

		if (Validator.isNull(narrative)) {
			setNarrative(getNarrative(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setNarrative(getNarrative(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public WatsonActivity toEscapedModel() {
		return (WatsonActivity)ProxyUtil.newProxyInstance(WatsonActivity.class.getClassLoader(),
			new Class[] { WatsonActivity.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonActivityClp clone = new WatsonActivityClp();

		clone.setWatsonActivityId(getWatsonActivityId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTypeWatsonListTypeId(getTypeWatsonListTypeId());
		clone.setWatsonIncidentId(getWatsonIncidentId());
		clone.setNarrative(getNarrative());
		clone.setReportDate(getReportDate());
		clone.setStartDate(getStartDate());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonActivity watsonActivity) {
		long primaryKey = watsonActivity.getPrimaryKey();

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

		if (!(obj instanceof WatsonActivityClp)) {
			return false;
		}

		WatsonActivityClp watsonActivity = (WatsonActivityClp)obj;

		long primaryKey = watsonActivity.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{watsonActivityId=");
		sb.append(getWatsonActivityId());
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
		sb.append(", typeWatsonListTypeId=");
		sb.append(getTypeWatsonListTypeId());
		sb.append(", watsonIncidentId=");
		sb.append(getWatsonIncidentId());
		sb.append(", narrative=");
		sb.append(getNarrative());
		sb.append(", reportDate=");
		sb.append(getReportDate());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonActivity");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonActivityId</column-name><column-value><![CDATA[");
		sb.append(getWatsonActivityId());
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
			"<column><column-name>typeWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getTypeWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>watsonIncidentId</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>narrative</column-name><column-value><![CDATA[");
		sb.append(getNarrative());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportDate</column-name><column-value><![CDATA[");
		sb.append(getReportDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonActivityId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _narrative;
	private String _narrativeCurrentLanguageId;
	private Date _reportDate;
	private Date _startDate;
	private int _status;
	private BaseModel<?> _watsonActivityRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}