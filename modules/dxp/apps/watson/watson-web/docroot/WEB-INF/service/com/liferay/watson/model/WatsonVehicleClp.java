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
import com.liferay.watson.service.WatsonVehicleLocalServiceUtil;

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
public class WatsonVehicleClp extends BaseModelImpl<WatsonVehicle>
	implements WatsonVehicle {
	public WatsonVehicleClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonVehicle.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonVehicle.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _watsonVehicleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonVehicleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonVehicleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonVehicleId", getWatsonVehicleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("colorWatsonListTypeId", getColorWatsonListTypeId());
		attributes.put("makeWatsonListTypeId", getMakeWatsonListTypeId());
		attributes.put("modelWatsonListTypeId", getModelWatsonListTypeId());
		attributes.put("originalWatsonVehicleId", getOriginalWatsonVehicleId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("yearWatsonListTypeId", getYearWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("year", getYear());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("licensePlate", getLicensePlate());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonVehicleId = (Long)attributes.get("watsonVehicleId");

		if (watsonVehicleId != null) {
			setWatsonVehicleId(watsonVehicleId);
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

		Long colorWatsonListTypeId = (Long)attributes.get(
				"colorWatsonListTypeId");

		if (colorWatsonListTypeId != null) {
			setColorWatsonListTypeId(colorWatsonListTypeId);
		}

		Long makeWatsonListTypeId = (Long)attributes.get("makeWatsonListTypeId");

		if (makeWatsonListTypeId != null) {
			setMakeWatsonListTypeId(makeWatsonListTypeId);
		}

		Long modelWatsonListTypeId = (Long)attributes.get(
				"modelWatsonListTypeId");

		if (modelWatsonListTypeId != null) {
			setModelWatsonListTypeId(modelWatsonListTypeId);
		}

		Long originalWatsonVehicleId = (Long)attributes.get(
				"originalWatsonVehicleId");

		if (originalWatsonVehicleId != null) {
			setOriginalWatsonVehicleId(originalWatsonVehicleId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long yearWatsonListTypeId = (Long)attributes.get("yearWatsonListTypeId");

		if (yearWatsonListTypeId != null) {
			setYearWatsonListTypeId(yearWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		String licensePlate = (String)attributes.get("licensePlate");

		if (licensePlate != null) {
			setLicensePlate(licensePlate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonVehicleId() {
		return _watsonVehicleId;
	}

	@Override
	public void setWatsonVehicleId(long watsonVehicleId) {
		_watsonVehicleId = watsonVehicleId;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonVehicleId", long.class);

				method.invoke(_watsonVehicleRemoteModel, watsonVehicleId);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonVehicleRemoteModel, companyId);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonVehicleRemoteModel, userId);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonVehicleRemoteModel, userName);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonVehicleRemoteModel, createDate);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonVehicleRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getColorWatsonListTypeId() {
		return _colorWatsonListTypeId;
	}

	@Override
	public void setColorWatsonListTypeId(long colorWatsonListTypeId) {
		_colorWatsonListTypeId = colorWatsonListTypeId;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setColorWatsonListTypeId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, colorWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMakeWatsonListTypeId() {
		return _makeWatsonListTypeId;
	}

	@Override
	public void setMakeWatsonListTypeId(long makeWatsonListTypeId) {
		_makeWatsonListTypeId = makeWatsonListTypeId;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setMakeWatsonListTypeId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, makeWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModelWatsonListTypeId() {
		return _modelWatsonListTypeId;
	}

	@Override
	public void setModelWatsonListTypeId(long modelWatsonListTypeId) {
		_modelWatsonListTypeId = modelWatsonListTypeId;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setModelWatsonListTypeId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, modelWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOriginalWatsonVehicleId() {
		return _originalWatsonVehicleId;
	}

	@Override
	public void setOriginalWatsonVehicleId(long originalWatsonVehicleId) {
		_originalWatsonVehicleId = originalWatsonVehicleId;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setOriginalWatsonVehicleId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, originalWatsonVehicleId);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeWatsonListTypeId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, typeWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getYearWatsonListTypeId() {
		return _yearWatsonListTypeId;
	}

	@Override
	public void setYearWatsonListTypeId(long yearWatsonListTypeId) {
		_yearWatsonListTypeId = yearWatsonListTypeId;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setYearWatsonListTypeId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, yearWatsonListTypeId);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId",
						long.class);

				method.invoke(_watsonVehicleRemoteModel, watsonIncidentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getYear() {
		return _year;
	}

	@Override
	public void setYear(int year) {
		_year = year;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setYear", int.class);

				method.invoke(_watsonVehicleRemoteModel, year);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_watsonVehicleRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setDescription(LocalizationUtil.updateLocalization(descriptionMap,
					getDescription(), "Description",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getImagePayload() {
		return _imagePayload;
	}

	@Override
	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setImagePayload", String.class);

				method.invoke(_watsonVehicleRemoteModel, imagePayload);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLicensePlate() {
		return _licensePlate;
	}

	@Override
	public void setLicensePlate(String licensePlate) {
		_licensePlate = licensePlate;

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setLicensePlate", String.class);

				method.invoke(_watsonVehicleRemoteModel, licensePlate);
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

		if (_watsonVehicleRemoteModel != null) {
			try {
				Class<?> clazz = _watsonVehicleRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonVehicleRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonVehicleRemoteModel() {
		return _watsonVehicleRemoteModel;
	}

	public void setWatsonVehicleRemoteModel(
		BaseModel<?> watsonVehicleRemoteModel) {
		_watsonVehicleRemoteModel = watsonVehicleRemoteModel;
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

		Class<?> remoteModelClass = _watsonVehicleRemoteModel.getClass();

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

		Object returnValue = method.invoke(_watsonVehicleRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonVehicleLocalServiceUtil.addWatsonVehicle(this);
		}
		else {
			WatsonVehicleLocalServiceUtil.updateWatsonVehicle(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
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
		String xml = getDescription();

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

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public WatsonVehicle toEscapedModel() {
		return (WatsonVehicle)ProxyUtil.newProxyInstance(WatsonVehicle.class.getClassLoader(),
			new Class[] { WatsonVehicle.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonVehicleClp clone = new WatsonVehicleClp();

		clone.setWatsonVehicleId(getWatsonVehicleId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setColorWatsonListTypeId(getColorWatsonListTypeId());
		clone.setMakeWatsonListTypeId(getMakeWatsonListTypeId());
		clone.setModelWatsonListTypeId(getModelWatsonListTypeId());
		clone.setOriginalWatsonVehicleId(getOriginalWatsonVehicleId());
		clone.setTypeWatsonListTypeId(getTypeWatsonListTypeId());
		clone.setYearWatsonListTypeId(getYearWatsonListTypeId());
		clone.setWatsonIncidentId(getWatsonIncidentId());
		clone.setYear(getYear());
		clone.setDescription(getDescription());
		clone.setImagePayload(getImagePayload());
		clone.setLicensePlate(getLicensePlate());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonVehicle watsonVehicle) {
		long primaryKey = watsonVehicle.getPrimaryKey();

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

		if (!(obj instanceof WatsonVehicleClp)) {
			return false;
		}

		WatsonVehicleClp watsonVehicle = (WatsonVehicleClp)obj;

		long primaryKey = watsonVehicle.getPrimaryKey();

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

		sb.append("{watsonVehicleId=");
		sb.append(getWatsonVehicleId());
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
		sb.append(", colorWatsonListTypeId=");
		sb.append(getColorWatsonListTypeId());
		sb.append(", makeWatsonListTypeId=");
		sb.append(getMakeWatsonListTypeId());
		sb.append(", modelWatsonListTypeId=");
		sb.append(getModelWatsonListTypeId());
		sb.append(", originalWatsonVehicleId=");
		sb.append(getOriginalWatsonVehicleId());
		sb.append(", typeWatsonListTypeId=");
		sb.append(getTypeWatsonListTypeId());
		sb.append(", yearWatsonListTypeId=");
		sb.append(getYearWatsonListTypeId());
		sb.append(", watsonIncidentId=");
		sb.append(getWatsonIncidentId());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", imagePayload=");
		sb.append(getImagePayload());
		sb.append(", licensePlate=");
		sb.append(getLicensePlate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonVehicle");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonVehicleId</column-name><column-value><![CDATA[");
		sb.append(getWatsonVehicleId());
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
			"<column><column-name>colorWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getColorWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>makeWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getMakeWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modelWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getModelWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>originalWatsonVehicleId</column-name><column-value><![CDATA[");
		sb.append(getOriginalWatsonVehicleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getTypeWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>yearWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getYearWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>watsonIncidentId</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imagePayload</column-name><column-value><![CDATA[");
		sb.append(getImagePayload());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licensePlate</column-name><column-value><![CDATA[");
		sb.append(getLicensePlate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonVehicleId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _colorWatsonListTypeId;
	private long _makeWatsonListTypeId;
	private long _modelWatsonListTypeId;
	private long _originalWatsonVehicleId;
	private long _typeWatsonListTypeId;
	private long _yearWatsonListTypeId;
	private long _watsonIncidentId;
	private int _year;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _imagePayload;
	private String _licensePlate;
	private int _status;
	private BaseModel<?> _watsonVehicleRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}