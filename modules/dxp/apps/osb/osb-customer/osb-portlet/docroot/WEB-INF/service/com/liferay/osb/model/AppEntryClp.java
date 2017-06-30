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

import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.ClpSerializer;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AppEntryClp extends BaseModelImpl<AppEntry> implements AppEntry {
	public AppEntryClp() {
	}

	public Class<?> getModelClass() {
		return AppEntry.class;
	}

	public String getModelClassName() {
		return AppEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _appEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAppEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_appEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("developerEntryId", getDeveloperEntryId());
		attributes.put("developerName", getDeveloperName());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("website", getWebsite());
		attributes.put("demoWebsite", getDemoWebsite());
		attributes.put("documentationWebsite", getDocumentationWebsite());
		attributes.put("referenceWebsite", getReferenceWebsite());
		attributes.put("sourceCodeWebsite", getSourceCodeWebsite());
		attributes.put("supportWebsite", getSupportWebsite());
		attributes.put("labs", getLabs());
		attributes.put("productType", getProductType());
		attributes.put("version", getVersion());
		attributes.put("changeLog", getChangeLog());
		attributes.put("iconImageId", getIconImageId());
		attributes.put("paclEnabled", getPaclEnabled());
		attributes.put("size", getSize());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("licenseType", getLicenseType());
		attributes.put("licenseLifetime", getLicenseLifetime());
		attributes.put("supported", getSupported());
		attributes.put("orderURL", getOrderURL());
		attributes.put("hidden", getHidden());
		attributes.put("portalRequired", getPortalRequired());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusVersionDate", getStatusVersionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
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

		Long developerEntryId = (Long)attributes.get("developerEntryId");

		if (developerEntryId != null) {
			setDeveloperEntryId(developerEntryId);
		}

		String developerName = (String)attributes.get("developerName");

		if (developerName != null) {
			setDeveloperName(developerName);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}

		String demoWebsite = (String)attributes.get("demoWebsite");

		if (demoWebsite != null) {
			setDemoWebsite(demoWebsite);
		}

		String documentationWebsite = (String)attributes.get(
				"documentationWebsite");

		if (documentationWebsite != null) {
			setDocumentationWebsite(documentationWebsite);
		}

		String referenceWebsite = (String)attributes.get("referenceWebsite");

		if (referenceWebsite != null) {
			setReferenceWebsite(referenceWebsite);
		}

		String sourceCodeWebsite = (String)attributes.get("sourceCodeWebsite");

		if (sourceCodeWebsite != null) {
			setSourceCodeWebsite(sourceCodeWebsite);
		}

		String supportWebsite = (String)attributes.get("supportWebsite");

		if (supportWebsite != null) {
			setSupportWebsite(supportWebsite);
		}

		Boolean labs = (Boolean)attributes.get("labs");

		if (labs != null) {
			setLabs(labs);
		}

		Integer productType = (Integer)attributes.get("productType");

		if (productType != null) {
			setProductType(productType);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String changeLog = (String)attributes.get("changeLog");

		if (changeLog != null) {
			setChangeLog(changeLog);
		}

		Long iconImageId = (Long)attributes.get("iconImageId");

		if (iconImageId != null) {
			setIconImageId(iconImageId);
		}

		Boolean paclEnabled = (Boolean)attributes.get("paclEnabled");

		if (paclEnabled != null) {
			setPaclEnabled(paclEnabled);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		Integer downloadCount = (Integer)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}

		Integer licenseType = (Integer)attributes.get("licenseType");

		if (licenseType != null) {
			setLicenseType(licenseType);
		}

		Long licenseLifetime = (Long)attributes.get("licenseLifetime");

		if (licenseLifetime != null) {
			setLicenseLifetime(licenseLifetime);
		}

		Boolean supported = (Boolean)attributes.get("supported");

		if (supported != null) {
			setSupported(supported);
		}

		String orderURL = (String)attributes.get("orderURL");

		if (orderURL != null) {
			setOrderURL(orderURL);
		}

		Boolean hidden = (Boolean)attributes.get("hidden");

		if (hidden != null) {
			setHidden(hidden);
		}

		Boolean portalRequired = (Boolean)attributes.get("portalRequired");

		if (portalRequired != null) {
			setPortalRequired(portalRequired);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		Date statusVersionDate = (Date)attributes.get("statusVersionDate");

		if (statusVersionDate != null) {
			setStatusVersionDate(statusVersionDate);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_appEntryRemoteModel, uuid);
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

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntryId", long.class);

				method.invoke(_appEntryRemoteModel, appEntryId);
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

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_appEntryRemoteModel, userId);
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

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_appEntryRemoteModel, userName);
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

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_appEntryRemoteModel, createDate);
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

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_appEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getDeveloperEntryId() {
		return _developerEntryId;
	}

	public void setDeveloperEntryId(long developerEntryId) {
		_developerEntryId = developerEntryId;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDeveloperEntryId",
						long.class);

				method.invoke(_appEntryRemoteModel, developerEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDeveloperName() {
		return _developerName;
	}

	public void setDeveloperName(String developerName) {
		_developerName = developerName;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDeveloperName", String.class);

				method.invoke(_appEntryRemoteModel, developerName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_appEntryRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDescription() {
		return _description;
	}

	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	public void setDescription(String description) {
		_description = description;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_appEntryRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

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

	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

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

			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (Locale locale : locales) {
				String description = descriptionMap.get(locale);

				setDescription(description, locale, defaultLocale);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setWebsite", String.class);

				method.invoke(_appEntryRemoteModel, website);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDemoWebsite() {
		return _demoWebsite;
	}

	public void setDemoWebsite(String demoWebsite) {
		_demoWebsite = demoWebsite;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDemoWebsite", String.class);

				method.invoke(_appEntryRemoteModel, demoWebsite);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDocumentationWebsite() {
		return _documentationWebsite;
	}

	public void setDocumentationWebsite(String documentationWebsite) {
		_documentationWebsite = documentationWebsite;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDocumentationWebsite",
						String.class);

				method.invoke(_appEntryRemoteModel, documentationWebsite);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getReferenceWebsite() {
		return _referenceWebsite;
	}

	public void setReferenceWebsite(String referenceWebsite) {
		_referenceWebsite = referenceWebsite;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setReferenceWebsite",
						String.class);

				method.invoke(_appEntryRemoteModel, referenceWebsite);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getSourceCodeWebsite() {
		return _sourceCodeWebsite;
	}

	public void setSourceCodeWebsite(String sourceCodeWebsite) {
		_sourceCodeWebsite = sourceCodeWebsite;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSourceCodeWebsite",
						String.class);

				method.invoke(_appEntryRemoteModel, sourceCodeWebsite);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getSupportWebsite() {
		return _supportWebsite;
	}

	public void setSupportWebsite(String supportWebsite) {
		_supportWebsite = supportWebsite;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupportWebsite",
						String.class);

				method.invoke(_appEntryRemoteModel, supportWebsite);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getLabs() {
		return _labs;
	}

	public boolean isLabs() {
		return _labs;
	}

	public void setLabs(boolean labs) {
		_labs = labs;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLabs", boolean.class);

				method.invoke(_appEntryRemoteModel, labs);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getProductType() {
		return _productType;
	}

	public void setProductType(int productType) {
		_productType = productType;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductType", int.class);

				method.invoke(_appEntryRemoteModel, productType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", String.class);

				method.invoke(_appEntryRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getChangeLog() {
		return _changeLog;
	}

	public String getChangeLog(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getChangeLog(languageId);
	}

	public String getChangeLog(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getChangeLog(languageId, useDefault);
	}

	public String getChangeLog(String languageId) {
		return LocalizationUtil.getLocalization(getChangeLog(), languageId);
	}

	public String getChangeLog(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getChangeLog(), languageId,
			useDefault);
	}

	public String getChangeLogCurrentLanguageId() {
		return _changeLogCurrentLanguageId;
	}

	public String getChangeLogCurrentValue() {
		Locale locale = getLocale(_changeLogCurrentLanguageId);

		return getChangeLog(locale);
	}

	public Map<Locale, String> getChangeLogMap() {
		return LocalizationUtil.getLocalizationMap(getChangeLog());
	}

	public void setChangeLog(String changeLog) {
		_changeLog = changeLog;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setChangeLog", String.class);

				method.invoke(_appEntryRemoteModel, changeLog);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setChangeLog(String changeLog, Locale locale) {
		setChangeLog(changeLog, locale, LocaleUtil.getDefault());
	}

	public void setChangeLog(String changeLog, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(changeLog)) {
			setChangeLog(LocalizationUtil.updateLocalization(getChangeLog(),
					"ChangeLog", changeLog, languageId, defaultLanguageId));
		}
		else {
			setChangeLog(LocalizationUtil.removeLocalization(getChangeLog(),
					"ChangeLog", languageId));
		}
	}

	public void setChangeLogCurrentLanguageId(String languageId) {
		_changeLogCurrentLanguageId = languageId;
	}

	public void setChangeLogMap(Map<Locale, String> changeLogMap) {
		setChangeLogMap(changeLogMap, LocaleUtil.getDefault());
	}

	public void setChangeLogMap(Map<Locale, String> changeLogMap,
		Locale defaultLocale) {
		if (changeLogMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (Locale locale : locales) {
				String changeLog = changeLogMap.get(locale);

				setChangeLog(changeLog, locale, defaultLocale);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public long getIconImageId() {
		return _iconImageId;
	}

	public void setIconImageId(long iconImageId) {
		_iconImageId = iconImageId;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setIconImageId", long.class);

				method.invoke(_appEntryRemoteModel, iconImageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getPaclEnabled() {
		return _paclEnabled;
	}

	public boolean isPaclEnabled() {
		return _paclEnabled;
	}

	public void setPaclEnabled(boolean paclEnabled) {
		_paclEnabled = paclEnabled;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPaclEnabled", boolean.class);

				method.invoke(_appEntryRemoteModel, paclEnabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSize", long.class);

				method.invoke(_appEntryRemoteModel, size);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		_downloadCount = downloadCount;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDownloadCount", int.class);

				method.invoke(_appEntryRemoteModel, downloadCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getLicenseType() {
		return _licenseType;
	}

	public void setLicenseType(int licenseType) {
		_licenseType = licenseType;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseType", int.class);

				method.invoke(_appEntryRemoteModel, licenseType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	public void setLicenseLifetime(long licenseLifetime) {
		_licenseLifetime = licenseLifetime;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLicenseLifetime", long.class);

				method.invoke(_appEntryRemoteModel, licenseLifetime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getSupported() {
		return _supported;
	}

	public boolean isSupported() {
		return _supported;
	}

	public void setSupported(boolean supported) {
		_supported = supported;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSupported", boolean.class);

				method.invoke(_appEntryRemoteModel, supported);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getOrderURL() {
		return _orderURL;
	}

	public void setOrderURL(String orderURL) {
		_orderURL = orderURL;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setOrderURL", String.class);

				method.invoke(_appEntryRemoteModel, orderURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getHidden() {
		return _hidden;
	}

	public boolean isHidden() {
		return _hidden;
	}

	public void setHidden(boolean hidden) {
		_hidden = hidden;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setHidden", boolean.class);

				method.invoke(_appEntryRemoteModel, hidden);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getPortalRequired() {
		return _portalRequired;
	}

	public boolean isPortalRequired() {
		return _portalRequired;
	}

	public void setPortalRequired(boolean portalRequired) {
		_portalRequired = portalRequired;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPortalRequired",
						boolean.class);

				method.invoke(_appEntryRemoteModel, portalRequired);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_appEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_appEntryRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_appEntryRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_appEntryRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getStatusVersionDate() {
		return _statusVersionDate;
	}

	public void setStatusVersionDate(Date statusVersionDate) {
		_statusVersionDate = statusVersionDate;

		if (_appEntryRemoteModel != null) {
			try {
				Class<?> clazz = _appEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusVersionDate",
						Date.class);

				method.invoke(_appEntryRemoteModel, statusVersionDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String[] getCompatibilities() {
		try {
			String methodName = "getCompatibilities";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String[] returnObj = (java.lang.String[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isDeveloperEntryUser() {
		try {
			String methodName = "isDeveloperEntryUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long[] getAssetListIds() {
		try {
			String methodName = "getAssetListIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			long[] returnObj = (long[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getLicenseTypeLabel() {
		try {
			String methodName = "getLicenseTypeLabel";

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

	public boolean isFree() {
		try {
			String methodName = "isFree";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasCustomOrderWorkflow() {
		try {
			String methodName = "hasCustomOrderWorkflow";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getAssetCategories() {
		try {
			String methodName = "getAssetCategories";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnObj =
				(java.util.List<com.liferay.portlet.asset.model.AssetCategory>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getStatusLabel() {
		try {
			String methodName = "getStatusLabel";

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

	public boolean isSOEEPlugin() {
		try {
			String methodName = "isSOEEPlugin";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.AppVersion getUnreleasedAppVersion() {
		try {
			String methodName = "getUnreleasedAppVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppVersion returnObj = (com.liferay.osb.model.AppVersion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.AppVersion getApprovedAppVersion() {
		try {
			String methodName = "getApprovedAppVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppVersion returnObj = (com.liferay.osb.model.AppVersion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry() {
		try {
			String methodName = "getAssetEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portlet.asset.model.AssetEntry returnObj = (com.liferay.portlet.asset.model.AssetEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasUnreleasedAppVersion() {
		try {
			String methodName = "hasUnreleasedAppVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.AppVersion getLatestAppVersion() {
		try {
			String methodName = "getLatestAppVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppVersion returnObj = (com.liferay.osb.model.AppVersion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getDeveloperEntryName() {
		try {
			String methodName = "getDeveloperEntryName";

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

	public com.liferay.osb.model.AppVersion getActionableAppVersion() {
		try {
			String methodName = "getActionableAppVersion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.AppVersion returnObj = (com.liferay.osb.model.AppVersion)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getLicenseLifetimeLabel() {
		try {
			String methodName = "getLicenseLifetimeLabel";

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

	public boolean hasAvailableCountry(long countryId) {
		try {
			String methodName = "hasAvailableCountry";

			Class<?>[] parameterTypes = new Class<?>[] { long.class };

			Object[] parameterValues = new Object[] { countryId };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean hasMultipleNewVersions() {
		try {
			String methodName = "hasMultipleNewVersions";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isDeveloperEntryLiferayInc() {
		try {
			String methodName = "isDeveloperEntryLiferayInc";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String[] getAssetTagNames() {
		try {
			String methodName = "getAssetTagNames";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String[] returnObj = (java.lang.String[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isFirstSubmission() {
		try {
			String methodName = "isFirstSubmission";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long[] getAvailableCountryIds() {
		try {
			String methodName = "getAvailableCountryIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			long[] returnObj = (long[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.DeveloperEntry getDeveloperEntry() {
		try {
			String methodName = "getDeveloperEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.DeveloperEntry returnObj = (com.liferay.osb.model.DeveloperEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long[] getAssetCategoryIds() {
		try {
			String methodName = "getAssetCategoryIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			long[] returnObj = (long[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isDeveloperEntryCorpEntry() {
		try {
			String methodName = "isDeveloperEntryCorpEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isStatusByDeveloper() {
		try {
			String methodName = "isStatusByDeveloper";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isLiferayEEPlugin() {
		try {
			String methodName = "isLiferayEEPlugin";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isCotermRequired() {
		try {
			String methodName = "isCotermRequired";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	/**
	 * @deprecated {@link #isApproved}
	 */
	public boolean getApproved() {
		return isApproved();
	}

	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getAppEntryRemoteModel() {
		return _appEntryRemoteModel;
	}

	public void setAppEntryRemoteModel(BaseModel<?> appEntryRemoteModel) {
		_appEntryRemoteModel = appEntryRemoteModel;
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

		Class<?> remoteModelClass = _appEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_appEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AppEntryLocalServiceUtil.addAppEntry(this);
		}
		else {
			AppEntryLocalServiceUtil.updateAppEntry(this);
		}
	}

	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		setDescription(getDescription(defaultImportLocale),
			defaultImportLocale, defaultImportLocale);
		setChangeLog(getChangeLog(defaultImportLocale), defaultImportLocale,
			defaultImportLocale);
	}

	@Override
	public AppEntry toEscapedModel() {
		return (AppEntry)ProxyUtil.newProxyInstance(AppEntry.class.getClassLoader(),
			new Class[] { AppEntry.class }, new AutoEscapeBeanHandler(this));
	}

	public AppEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		AppEntryClp clone = new AppEntryClp();

		clone.setUuid(getUuid());
		clone.setAppEntryId(getAppEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDeveloperEntryId(getDeveloperEntryId());
		clone.setDeveloperName(getDeveloperName());
		clone.setTitle(getTitle());
		clone.setDescription(getDescription());
		clone.setWebsite(getWebsite());
		clone.setDemoWebsite(getDemoWebsite());
		clone.setDocumentationWebsite(getDocumentationWebsite());
		clone.setReferenceWebsite(getReferenceWebsite());
		clone.setSourceCodeWebsite(getSourceCodeWebsite());
		clone.setSupportWebsite(getSupportWebsite());
		clone.setLabs(getLabs());
		clone.setProductType(getProductType());
		clone.setVersion(getVersion());
		clone.setChangeLog(getChangeLog());
		clone.setIconImageId(getIconImageId());
		clone.setPaclEnabled(getPaclEnabled());
		clone.setSize(getSize());
		clone.setDownloadCount(getDownloadCount());
		clone.setLicenseType(getLicenseType());
		clone.setLicenseLifetime(getLicenseLifetime());
		clone.setSupported(getSupported());
		clone.setOrderURL(getOrderURL());
		clone.setHidden(getHidden());
		clone.setPortalRequired(getPortalRequired());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setStatusVersionDate(getStatusVersionDate());

		return clone;
	}

	public int compareTo(AppEntry appEntry) {
		int value = 0;

		value = getTitle().toLowerCase()
					.compareTo(appEntry.getTitle().toLowerCase());

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

		if (!(obj instanceof AppEntryClp)) {
			return false;
		}

		AppEntryClp appEntry = (AppEntryClp)obj;

		long primaryKey = appEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(71);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", appEntryId=");
		sb.append(getAppEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", developerEntryId=");
		sb.append(getDeveloperEntryId());
		sb.append(", developerName=");
		sb.append(getDeveloperName());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", website=");
		sb.append(getWebsite());
		sb.append(", demoWebsite=");
		sb.append(getDemoWebsite());
		sb.append(", documentationWebsite=");
		sb.append(getDocumentationWebsite());
		sb.append(", referenceWebsite=");
		sb.append(getReferenceWebsite());
		sb.append(", sourceCodeWebsite=");
		sb.append(getSourceCodeWebsite());
		sb.append(", supportWebsite=");
		sb.append(getSupportWebsite());
		sb.append(", labs=");
		sb.append(getLabs());
		sb.append(", productType=");
		sb.append(getProductType());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", changeLog=");
		sb.append(getChangeLog());
		sb.append(", iconImageId=");
		sb.append(getIconImageId());
		sb.append(", paclEnabled=");
		sb.append(getPaclEnabled());
		sb.append(", size=");
		sb.append(getSize());
		sb.append(", downloadCount=");
		sb.append(getDownloadCount());
		sb.append(", licenseType=");
		sb.append(getLicenseType());
		sb.append(", licenseLifetime=");
		sb.append(getLicenseLifetime());
		sb.append(", supported=");
		sb.append(getSupported());
		sb.append(", orderURL=");
		sb.append(getOrderURL());
		sb.append(", hidden=");
		sb.append(getHidden());
		sb.append(", portalRequired=");
		sb.append(getPortalRequired());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", statusVersionDate=");
		sb.append(getStatusVersionDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(109);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.AppEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appEntryId</column-name><column-value><![CDATA[");
		sb.append(getAppEntryId());
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
			"<column><column-name>developerEntryId</column-name><column-value><![CDATA[");
		sb.append(getDeveloperEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>developerName</column-name><column-value><![CDATA[");
		sb.append(getDeveloperName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>website</column-name><column-value><![CDATA[");
		sb.append(getWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>demoWebsite</column-name><column-value><![CDATA[");
		sb.append(getDemoWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>documentationWebsite</column-name><column-value><![CDATA[");
		sb.append(getDocumentationWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referenceWebsite</column-name><column-value><![CDATA[");
		sb.append(getReferenceWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceCodeWebsite</column-name><column-value><![CDATA[");
		sb.append(getSourceCodeWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportWebsite</column-name><column-value><![CDATA[");
		sb.append(getSupportWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>labs</column-name><column-value><![CDATA[");
		sb.append(getLabs());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productType</column-name><column-value><![CDATA[");
		sb.append(getProductType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>changeLog</column-name><column-value><![CDATA[");
		sb.append(getChangeLog());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>iconImageId</column-name><column-value><![CDATA[");
		sb.append(getIconImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paclEnabled</column-name><column-value><![CDATA[");
		sb.append(getPaclEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>downloadCount</column-name><column-value><![CDATA[");
		sb.append(getDownloadCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseType</column-name><column-value><![CDATA[");
		sb.append(getLicenseType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>licenseLifetime</column-name><column-value><![CDATA[");
		sb.append(getLicenseLifetime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supported</column-name><column-value><![CDATA[");
		sb.append(getSupported());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderURL</column-name><column-value><![CDATA[");
		sb.append(getOrderURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hidden</column-name><column-value><![CDATA[");
		sb.append(getHidden());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalRequired</column-name><column-value><![CDATA[");
		sb.append(getPortalRequired());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusVersionDate</column-name><column-value><![CDATA[");
		sb.append(getStatusVersionDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _appEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _developerEntryId;
	private String _developerName;
	private String _title;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _website;
	private String _demoWebsite;
	private String _documentationWebsite;
	private String _referenceWebsite;
	private String _sourceCodeWebsite;
	private String _supportWebsite;
	private boolean _labs;
	private int _productType;
	private String _version;
	private String _changeLog;
	private String _changeLogCurrentLanguageId;
	private long _iconImageId;
	private boolean _paclEnabled;
	private long _size;
	private int _downloadCount;
	private int _licenseType;
	private long _licenseLifetime;
	private boolean _supported;
	private String _orderURL;
	private boolean _hidden;
	private boolean _portalRequired;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUserName;
	private Date _statusDate;
	private Date _statusVersionDate;
	private BaseModel<?> _appEntryRemoteModel;
}