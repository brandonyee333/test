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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AppVersion}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppVersion
 * @generated
 */
public class AppVersionWrapper implements AppVersion, ModelWrapper<AppVersion> {
	public AppVersionWrapper(AppVersion appVersion) {
		_appVersion = appVersion;
	}

	public Class<?> getModelClass() {
		return AppVersion.class;
	}

	public String getModelClassName() {
		return AppVersion.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appVersionId", getAppVersionId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
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
		attributes.put("versionId", getVersionId());
		attributes.put("versionOrder", getVersionOrder());
		attributes.put("changeLog", getChangeLog());
		attributes.put("iconImageId", getIconImageId());
		attributes.put("size", getSize());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("paclEnabled", getPaclEnabled());
		attributes.put("releaseDate", getReleaseDate());
		attributes.put("releaseType", getReleaseType());
		attributes.put("contractEntryId", getContractEntryId());
		attributes.put("supported", getSupported());
		attributes.put("orderURL", getOrderURL());
		attributes.put("hidden", getHidden());
		attributes.put("portalRequired", getPortalRequired());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
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

		Integer versionId = (Integer)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Integer versionOrder = (Integer)attributes.get("versionOrder");

		if (versionOrder != null) {
			setVersionOrder(versionOrder);
		}

		String changeLog = (String)attributes.get("changeLog");

		if (changeLog != null) {
			setChangeLog(changeLog);
		}

		Long iconImageId = (Long)attributes.get("iconImageId");

		if (iconImageId != null) {
			setIconImageId(iconImageId);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		Integer downloadCount = (Integer)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}

		Boolean paclEnabled = (Boolean)attributes.get("paclEnabled");

		if (paclEnabled != null) {
			setPaclEnabled(paclEnabled);
		}

		Date releaseDate = (Date)attributes.get("releaseDate");

		if (releaseDate != null) {
			setReleaseDate(releaseDate);
		}

		Integer releaseType = (Integer)attributes.get("releaseType");

		if (releaseType != null) {
			setReleaseType(releaseType);
		}

		Long contractEntryId = (Long)attributes.get("contractEntryId");

		if (contractEntryId != null) {
			setContractEntryId(contractEntryId);
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

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}
	}

	/**
	* Returns the primary key of this app version.
	*
	* @return the primary key of this app version
	*/
	public long getPrimaryKey() {
		return _appVersion.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app version.
	*
	* @param primaryKey the primary key of this app version
	*/
	public void setPrimaryKey(long primaryKey) {
		_appVersion.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the app version ID of this app version.
	*
	* @return the app version ID of this app version
	*/
	public long getAppVersionId() {
		return _appVersion.getAppVersionId();
	}

	/**
	* Sets the app version ID of this app version.
	*
	* @param appVersionId the app version ID of this app version
	*/
	public void setAppVersionId(long appVersionId) {
		_appVersion.setAppVersionId(appVersionId);
	}

	/**
	* Returns the user ID of this app version.
	*
	* @return the user ID of this app version
	*/
	public long getUserId() {
		return _appVersion.getUserId();
	}

	/**
	* Sets the user ID of this app version.
	*
	* @param userId the user ID of this app version
	*/
	public void setUserId(long userId) {
		_appVersion.setUserId(userId);
	}

	/**
	* Returns the user uuid of this app version.
	*
	* @return the user uuid of this app version
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getUserUuid();
	}

	/**
	* Sets the user uuid of this app version.
	*
	* @param userUuid the user uuid of this app version
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_appVersion.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this app version.
	*
	* @return the user name of this app version
	*/
	public java.lang.String getUserName() {
		return _appVersion.getUserName();
	}

	/**
	* Sets the user name of this app version.
	*
	* @param userName the user name of this app version
	*/
	public void setUserName(java.lang.String userName) {
		_appVersion.setUserName(userName);
	}

	/**
	* Returns the create date of this app version.
	*
	* @return the create date of this app version
	*/
	public java.util.Date getCreateDate() {
		return _appVersion.getCreateDate();
	}

	/**
	* Sets the create date of this app version.
	*
	* @param createDate the create date of this app version
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appVersion.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this app version.
	*
	* @return the modified date of this app version
	*/
	public java.util.Date getModifiedDate() {
		return _appVersion.getModifiedDate();
	}

	/**
	* Sets the modified date of this app version.
	*
	* @param modifiedDate the modified date of this app version
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_appVersion.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the app entry ID of this app version.
	*
	* @return the app entry ID of this app version
	*/
	public long getAppEntryId() {
		return _appVersion.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app version.
	*
	* @param appEntryId the app entry ID of this app version
	*/
	public void setAppEntryId(long appEntryId) {
		_appVersion.setAppEntryId(appEntryId);
	}

	/**
	* Returns the developer entry ID of this app version.
	*
	* @return the developer entry ID of this app version
	*/
	public long getDeveloperEntryId() {
		return _appVersion.getDeveloperEntryId();
	}

	/**
	* Sets the developer entry ID of this app version.
	*
	* @param developerEntryId the developer entry ID of this app version
	*/
	public void setDeveloperEntryId(long developerEntryId) {
		_appVersion.setDeveloperEntryId(developerEntryId);
	}

	/**
	* Returns the developer name of this app version.
	*
	* @return the developer name of this app version
	*/
	public java.lang.String getDeveloperName() {
		return _appVersion.getDeveloperName();
	}

	/**
	* Sets the developer name of this app version.
	*
	* @param developerName the developer name of this app version
	*/
	public void setDeveloperName(java.lang.String developerName) {
		_appVersion.setDeveloperName(developerName);
	}

	/**
	* Returns the title of this app version.
	*
	* @return the title of this app version
	*/
	public java.lang.String getTitle() {
		return _appVersion.getTitle();
	}

	/**
	* Sets the title of this app version.
	*
	* @param title the title of this app version
	*/
	public void setTitle(java.lang.String title) {
		_appVersion.setTitle(title);
	}

	/**
	* Returns the description of this app version.
	*
	* @return the description of this app version
	*/
	public java.lang.String getDescription() {
		return _appVersion.getDescription();
	}

	/**
	* Returns the localized description of this app version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this app version
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _appVersion.getDescription(locale);
	}

	/**
	* Returns the localized description of this app version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this app version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _appVersion.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this app version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this app version
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _appVersion.getDescription(languageId);
	}

	/**
	* Returns the localized description of this app version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this app version
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _appVersion.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _appVersion.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _appVersion.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this app version.
	*
	* @return the locales and localized descriptions of this app version
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _appVersion.getDescriptionMap();
	}

	/**
	* Sets the description of this app version.
	*
	* @param description the description of this app version
	*/
	public void setDescription(java.lang.String description) {
		_appVersion.setDescription(description);
	}

	/**
	* Sets the localized description of this app version in the language.
	*
	* @param description the localized description of this app version
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_appVersion.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this app version in the language, and sets the default locale.
	*
	* @param description the localized description of this app version
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_appVersion.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_appVersion.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this app version from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this app version
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_appVersion.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this app version from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this app version
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_appVersion.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the website of this app version.
	*
	* @return the website of this app version
	*/
	public java.lang.String getWebsite() {
		return _appVersion.getWebsite();
	}

	/**
	* Sets the website of this app version.
	*
	* @param website the website of this app version
	*/
	public void setWebsite(java.lang.String website) {
		_appVersion.setWebsite(website);
	}

	/**
	* Returns the demo website of this app version.
	*
	* @return the demo website of this app version
	*/
	public java.lang.String getDemoWebsite() {
		return _appVersion.getDemoWebsite();
	}

	/**
	* Sets the demo website of this app version.
	*
	* @param demoWebsite the demo website of this app version
	*/
	public void setDemoWebsite(java.lang.String demoWebsite) {
		_appVersion.setDemoWebsite(demoWebsite);
	}

	/**
	* Returns the documentation website of this app version.
	*
	* @return the documentation website of this app version
	*/
	public java.lang.String getDocumentationWebsite() {
		return _appVersion.getDocumentationWebsite();
	}

	/**
	* Sets the documentation website of this app version.
	*
	* @param documentationWebsite the documentation website of this app version
	*/
	public void setDocumentationWebsite(java.lang.String documentationWebsite) {
		_appVersion.setDocumentationWebsite(documentationWebsite);
	}

	/**
	* Returns the reference website of this app version.
	*
	* @return the reference website of this app version
	*/
	public java.lang.String getReferenceWebsite() {
		return _appVersion.getReferenceWebsite();
	}

	/**
	* Sets the reference website of this app version.
	*
	* @param referenceWebsite the reference website of this app version
	*/
	public void setReferenceWebsite(java.lang.String referenceWebsite) {
		_appVersion.setReferenceWebsite(referenceWebsite);
	}

	/**
	* Returns the source code website of this app version.
	*
	* @return the source code website of this app version
	*/
	public java.lang.String getSourceCodeWebsite() {
		return _appVersion.getSourceCodeWebsite();
	}

	/**
	* Sets the source code website of this app version.
	*
	* @param sourceCodeWebsite the source code website of this app version
	*/
	public void setSourceCodeWebsite(java.lang.String sourceCodeWebsite) {
		_appVersion.setSourceCodeWebsite(sourceCodeWebsite);
	}

	/**
	* Returns the support website of this app version.
	*
	* @return the support website of this app version
	*/
	public java.lang.String getSupportWebsite() {
		return _appVersion.getSupportWebsite();
	}

	/**
	* Sets the support website of this app version.
	*
	* @param supportWebsite the support website of this app version
	*/
	public void setSupportWebsite(java.lang.String supportWebsite) {
		_appVersion.setSupportWebsite(supportWebsite);
	}

	/**
	* Returns the labs of this app version.
	*
	* @return the labs of this app version
	*/
	public boolean getLabs() {
		return _appVersion.getLabs();
	}

	/**
	* Returns <code>true</code> if this app version is labs.
	*
	* @return <code>true</code> if this app version is labs; <code>false</code> otherwise
	*/
	public boolean isLabs() {
		return _appVersion.isLabs();
	}

	/**
	* Sets whether this app version is labs.
	*
	* @param labs the labs of this app version
	*/
	public void setLabs(boolean labs) {
		_appVersion.setLabs(labs);
	}

	/**
	* Returns the product type of this app version.
	*
	* @return the product type of this app version
	*/
	public int getProductType() {
		return _appVersion.getProductType();
	}

	/**
	* Sets the product type of this app version.
	*
	* @param productType the product type of this app version
	*/
	public void setProductType(int productType) {
		_appVersion.setProductType(productType);
	}

	/**
	* Returns the version of this app version.
	*
	* @return the version of this app version
	*/
	public java.lang.String getVersion() {
		return _appVersion.getVersion();
	}

	/**
	* Sets the version of this app version.
	*
	* @param version the version of this app version
	*/
	public void setVersion(java.lang.String version) {
		_appVersion.setVersion(version);
	}

	/**
	* Returns the version ID of this app version.
	*
	* @return the version ID of this app version
	*/
	public int getVersionId() {
		return _appVersion.getVersionId();
	}

	/**
	* Sets the version ID of this app version.
	*
	* @param versionId the version ID of this app version
	*/
	public void setVersionId(int versionId) {
		_appVersion.setVersionId(versionId);
	}

	/**
	* Returns the version order of this app version.
	*
	* @return the version order of this app version
	*/
	public int getVersionOrder() {
		return _appVersion.getVersionOrder();
	}

	/**
	* Sets the version order of this app version.
	*
	* @param versionOrder the version order of this app version
	*/
	public void setVersionOrder(int versionOrder) {
		_appVersion.setVersionOrder(versionOrder);
	}

	/**
	* Returns the change log of this app version.
	*
	* @return the change log of this app version
	*/
	public java.lang.String getChangeLog() {
		return _appVersion.getChangeLog();
	}

	/**
	* Returns the localized change log of this app version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized change log of this app version
	*/
	public java.lang.String getChangeLog(java.util.Locale locale) {
		return _appVersion.getChangeLog(locale);
	}

	/**
	* Returns the localized change log of this app version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized change log of this app version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getChangeLog(java.util.Locale locale,
		boolean useDefault) {
		return _appVersion.getChangeLog(locale, useDefault);
	}

	/**
	* Returns the localized change log of this app version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized change log of this app version
	*/
	public java.lang.String getChangeLog(java.lang.String languageId) {
		return _appVersion.getChangeLog(languageId);
	}

	/**
	* Returns the localized change log of this app version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized change log of this app version
	*/
	public java.lang.String getChangeLog(java.lang.String languageId,
		boolean useDefault) {
		return _appVersion.getChangeLog(languageId, useDefault);
	}

	public java.lang.String getChangeLogCurrentLanguageId() {
		return _appVersion.getChangeLogCurrentLanguageId();
	}

	public java.lang.String getChangeLogCurrentValue() {
		return _appVersion.getChangeLogCurrentValue();
	}

	/**
	* Returns a map of the locales and localized change logs of this app version.
	*
	* @return the locales and localized change logs of this app version
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getChangeLogMap() {
		return _appVersion.getChangeLogMap();
	}

	/**
	* Sets the change log of this app version.
	*
	* @param changeLog the change log of this app version
	*/
	public void setChangeLog(java.lang.String changeLog) {
		_appVersion.setChangeLog(changeLog);
	}

	/**
	* Sets the localized change log of this app version in the language.
	*
	* @param changeLog the localized change log of this app version
	* @param locale the locale of the language
	*/
	public void setChangeLog(java.lang.String changeLog, java.util.Locale locale) {
		_appVersion.setChangeLog(changeLog, locale);
	}

	/**
	* Sets the localized change log of this app version in the language, and sets the default locale.
	*
	* @param changeLog the localized change log of this app version
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setChangeLog(java.lang.String changeLog,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_appVersion.setChangeLog(changeLog, locale, defaultLocale);
	}

	public void setChangeLogCurrentLanguageId(java.lang.String languageId) {
		_appVersion.setChangeLogCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized change logs of this app version from the map of locales and localized change logs.
	*
	* @param changeLogMap the locales and localized change logs of this app version
	*/
	public void setChangeLogMap(
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap) {
		_appVersion.setChangeLogMap(changeLogMap);
	}

	/**
	* Sets the localized change logs of this app version from the map of locales and localized change logs, and sets the default locale.
	*
	* @param changeLogMap the locales and localized change logs of this app version
	* @param defaultLocale the default locale
	*/
	public void setChangeLogMap(
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		java.util.Locale defaultLocale) {
		_appVersion.setChangeLogMap(changeLogMap, defaultLocale);
	}

	/**
	* Returns the icon image ID of this app version.
	*
	* @return the icon image ID of this app version
	*/
	public long getIconImageId() {
		return _appVersion.getIconImageId();
	}

	/**
	* Sets the icon image ID of this app version.
	*
	* @param iconImageId the icon image ID of this app version
	*/
	public void setIconImageId(long iconImageId) {
		_appVersion.setIconImageId(iconImageId);
	}

	/**
	* Returns the size of this app version.
	*
	* @return the size of this app version
	*/
	public long getSize() {
		return _appVersion.getSize();
	}

	/**
	* Sets the size of this app version.
	*
	* @param size the size of this app version
	*/
	public void setSize(long size) {
		_appVersion.setSize(size);
	}

	/**
	* Returns the download count of this app version.
	*
	* @return the download count of this app version
	*/
	public int getDownloadCount() {
		return _appVersion.getDownloadCount();
	}

	/**
	* Sets the download count of this app version.
	*
	* @param downloadCount the download count of this app version
	*/
	public void setDownloadCount(int downloadCount) {
		_appVersion.setDownloadCount(downloadCount);
	}

	/**
	* Returns the pacl enabled of this app version.
	*
	* @return the pacl enabled of this app version
	*/
	public boolean getPaclEnabled() {
		return _appVersion.getPaclEnabled();
	}

	/**
	* Returns <code>true</code> if this app version is pacl enabled.
	*
	* @return <code>true</code> if this app version is pacl enabled; <code>false</code> otherwise
	*/
	public boolean isPaclEnabled() {
		return _appVersion.isPaclEnabled();
	}

	/**
	* Sets whether this app version is pacl enabled.
	*
	* @param paclEnabled the pacl enabled of this app version
	*/
	public void setPaclEnabled(boolean paclEnabled) {
		_appVersion.setPaclEnabled(paclEnabled);
	}

	/**
	* Returns the release date of this app version.
	*
	* @return the release date of this app version
	*/
	public java.util.Date getReleaseDate() {
		return _appVersion.getReleaseDate();
	}

	/**
	* Sets the release date of this app version.
	*
	* @param releaseDate the release date of this app version
	*/
	public void setReleaseDate(java.util.Date releaseDate) {
		_appVersion.setReleaseDate(releaseDate);
	}

	/**
	* Returns the release type of this app version.
	*
	* @return the release type of this app version
	*/
	public int getReleaseType() {
		return _appVersion.getReleaseType();
	}

	/**
	* Sets the release type of this app version.
	*
	* @param releaseType the release type of this app version
	*/
	public void setReleaseType(int releaseType) {
		_appVersion.setReleaseType(releaseType);
	}

	/**
	* Returns the contract entry ID of this app version.
	*
	* @return the contract entry ID of this app version
	*/
	public long getContractEntryId() {
		return _appVersion.getContractEntryId();
	}

	/**
	* Sets the contract entry ID of this app version.
	*
	* @param contractEntryId the contract entry ID of this app version
	*/
	public void setContractEntryId(long contractEntryId) {
		_appVersion.setContractEntryId(contractEntryId);
	}

	/**
	* Returns the supported of this app version.
	*
	* @return the supported of this app version
	*/
	public boolean getSupported() {
		return _appVersion.getSupported();
	}

	/**
	* Returns <code>true</code> if this app version is supported.
	*
	* @return <code>true</code> if this app version is supported; <code>false</code> otherwise
	*/
	public boolean isSupported() {
		return _appVersion.isSupported();
	}

	/**
	* Sets whether this app version is supported.
	*
	* @param supported the supported of this app version
	*/
	public void setSupported(boolean supported) {
		_appVersion.setSupported(supported);
	}

	/**
	* Returns the order u r l of this app version.
	*
	* @return the order u r l of this app version
	*/
	public java.lang.String getOrderURL() {
		return _appVersion.getOrderURL();
	}

	/**
	* Sets the order u r l of this app version.
	*
	* @param orderURL the order u r l of this app version
	*/
	public void setOrderURL(java.lang.String orderURL) {
		_appVersion.setOrderURL(orderURL);
	}

	/**
	* Returns the hidden of this app version.
	*
	* @return the hidden of this app version
	*/
	public boolean getHidden() {
		return _appVersion.getHidden();
	}

	/**
	* Returns <code>true</code> if this app version is hidden.
	*
	* @return <code>true</code> if this app version is hidden; <code>false</code> otherwise
	*/
	public boolean isHidden() {
		return _appVersion.isHidden();
	}

	/**
	* Sets whether this app version is hidden.
	*
	* @param hidden the hidden of this app version
	*/
	public void setHidden(boolean hidden) {
		_appVersion.setHidden(hidden);
	}

	/**
	* Returns the portal required of this app version.
	*
	* @return the portal required of this app version
	*/
	public boolean getPortalRequired() {
		return _appVersion.getPortalRequired();
	}

	/**
	* Returns <code>true</code> if this app version is portal required.
	*
	* @return <code>true</code> if this app version is portal required; <code>false</code> otherwise
	*/
	public boolean isPortalRequired() {
		return _appVersion.isPortalRequired();
	}

	/**
	* Sets whether this app version is portal required.
	*
	* @param portalRequired the portal required of this app version
	*/
	public void setPortalRequired(boolean portalRequired) {
		_appVersion.setPortalRequired(portalRequired);
	}

	/**
	* Returns the status of this app version.
	*
	* @return the status of this app version
	*/
	public int getStatus() {
		return _appVersion.getStatus();
	}

	/**
	* Sets the status of this app version.
	*
	* @param status the status of this app version
	*/
	public void setStatus(int status) {
		_appVersion.setStatus(status);
	}

	/**
	* Returns the status by user ID of this app version.
	*
	* @return the status by user ID of this app version
	*/
	public long getStatusByUserId() {
		return _appVersion.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this app version.
	*
	* @param statusByUserId the status by user ID of this app version
	*/
	public void setStatusByUserId(long statusByUserId) {
		_appVersion.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this app version.
	*
	* @return the status by user uuid of this app version
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this app version.
	*
	* @param statusByUserUuid the status by user uuid of this app version
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_appVersion.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this app version.
	*
	* @return the status by user name of this app version
	*/
	public java.lang.String getStatusByUserName() {
		return _appVersion.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this app version.
	*
	* @param statusByUserName the status by user name of this app version
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_appVersion.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this app version.
	*
	* @return the status date of this app version
	*/
	public java.util.Date getStatusDate() {
		return _appVersion.getStatusDate();
	}

	/**
	* Sets the status date of this app version.
	*
	* @param statusDate the status date of this app version
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_appVersion.setStatusDate(statusDate);
	}

	/**
	* Returns the status message of this app version.
	*
	* @return the status message of this app version
	*/
	public java.lang.String getStatusMessage() {
		return _appVersion.getStatusMessage();
	}

	/**
	* Sets the status message of this app version.
	*
	* @param statusMessage the status message of this app version
	*/
	public void setStatusMessage(java.lang.String statusMessage) {
		_appVersion.setStatusMessage(statusMessage);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _appVersion.getApproved();
	}

	/**
	* Returns <code>true</code> if this app version is approved.
	*
	* @return <code>true</code> if this app version is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _appVersion.isApproved();
	}

	/**
	* Returns <code>true</code> if this app version is denied.
	*
	* @return <code>true</code> if this app version is denied; <code>false</code> otherwise
	*/
	public boolean isDenied() {
		return _appVersion.isDenied();
	}

	/**
	* Returns <code>true</code> if this app version is a draft.
	*
	* @return <code>true</code> if this app version is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _appVersion.isDraft();
	}

	/**
	* Returns <code>true</code> if this app version is expired.
	*
	* @return <code>true</code> if this app version is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _appVersion.isExpired();
	}

	/**
	* Returns <code>true</code> if this app version is inactive.
	*
	* @return <code>true</code> if this app version is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _appVersion.isInactive();
	}

	/**
	* Returns <code>true</code> if this app version is incomplete.
	*
	* @return <code>true</code> if this app version is incomplete; <code>false</code> otherwise
	*/
	public boolean isIncomplete() {
		return _appVersion.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this app version is pending.
	*
	* @return <code>true</code> if this app version is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _appVersion.isPending();
	}

	/**
	* Returns <code>true</code> if this app version is scheduled.
	*
	* @return <code>true</code> if this app version is scheduled; <code>false</code> otherwise
	*/
	public boolean isScheduled() {
		return _appVersion.isScheduled();
	}

	public boolean isNew() {
		return _appVersion.isNew();
	}

	public void setNew(boolean n) {
		_appVersion.setNew(n);
	}

	public boolean isCachedModel() {
		return _appVersion.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appVersion.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appVersion.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appVersion.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appVersion.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appVersion.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_appVersion.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new AppVersionWrapper((AppVersion)_appVersion.clone());
	}

	public int compareTo(com.liferay.osb.model.AppVersion appVersion) {
		return _appVersion.compareTo(appVersion);
	}

	@Override
	public int hashCode() {
		return _appVersion.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppVersion> toCacheModel() {
		return _appVersion.toCacheModel();
	}

	public com.liferay.osb.model.AppVersion toEscapedModel() {
		return new AppVersionWrapper(_appVersion.toEscapedModel());
	}

	public com.liferay.osb.model.AppVersion toUnescapedModel() {
		return new AppVersionWrapper(_appVersion.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appVersion.toString();
	}

	public java.lang.String toXmlString() {
		return _appVersion.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appVersion.persist();
	}

	public com.liferay.osb.model.AppEntry getAppEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getAppEntry();
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getAssetLicenses();
	}

	public com.liferay.osb.model.ContractEntry getContractEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getContractEntry();
	}

	public java.lang.String getDefaultLanguageId() {
		return _appVersion.getDefaultLanguageId();
	}

	public com.liferay.osb.model.DeveloperEntry getDeveloperEntry()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getDeveloperEntry();
	}

	public java.lang.String getDeveloperEntryName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getDeveloperEntryName();
	}

	public int getLicenseType()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.getLicenseType();
	}

	public java.lang.String getProductTypeLabel() {
		return _appVersion.getProductTypeLabel();
	}

	public java.lang.String getStatusLabel() {
		return _appVersion.getStatusLabel();
	}

	public boolean hasCustomOrderWorkflow() {
		return _appVersion.hasCustomOrderWorkflow();
	}

	public boolean hasTrialLicense()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.hasTrialLicense();
	}

	public boolean isAddResourcesImporter()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.isAddResourcesImporter();
	}

	public boolean isDeveloperEntryCorpEntry()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.isDeveloperEntryCorpEntry();
	}

	public boolean isDeveloperEntryLiferayInc()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.isDeveloperEntryLiferayInc();
	}

	public boolean isDeveloperEntryUser()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.isDeveloperEntryUser();
	}

	public boolean isFree()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.isFree();
	}

	public boolean isLatestApproved()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appVersion.isLatestApproved();
	}

	public boolean isNewRelease() {
		return _appVersion.isNewRelease();
	}

	public boolean isSOEEPlugin() {
		return _appVersion.isSOEEPlugin();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppVersionWrapper)) {
			return false;
		}

		AppVersionWrapper appVersionWrapper = (AppVersionWrapper)obj;

		if (Validator.equals(_appVersion, appVersionWrapper._appVersion)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppVersion getWrappedAppVersion() {
		return _appVersion;
	}

	public AppVersion getWrappedModel() {
		return _appVersion;
	}

	public void resetOriginalValues() {
		_appVersion.resetOriginalValues();
	}

	private AppVersion _appVersion;
}