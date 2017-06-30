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
 * This class is a wrapper for {@link AppEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppEntry
 * @generated
 */
public class AppEntryWrapper implements AppEntry, ModelWrapper<AppEntry> {
	public AppEntryWrapper(AppEntry appEntry) {
		_appEntry = appEntry;
	}

	public Class<?> getModelClass() {
		return AppEntry.class;
	}

	public String getModelClassName() {
		return AppEntry.class.getName();
	}

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

	/**
	* Returns the primary key of this app entry.
	*
	* @return the primary key of this app entry
	*/
	public long getPrimaryKey() {
		return _appEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app entry.
	*
	* @param primaryKey the primary key of this app entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_appEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this app entry.
	*
	* @return the uuid of this app entry
	*/
	public java.lang.String getUuid() {
		return _appEntry.getUuid();
	}

	/**
	* Sets the uuid of this app entry.
	*
	* @param uuid the uuid of this app entry
	*/
	public void setUuid(java.lang.String uuid) {
		_appEntry.setUuid(uuid);
	}

	/**
	* Returns the app entry ID of this app entry.
	*
	* @return the app entry ID of this app entry
	*/
	public long getAppEntryId() {
		return _appEntry.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app entry.
	*
	* @param appEntryId the app entry ID of this app entry
	*/
	public void setAppEntryId(long appEntryId) {
		_appEntry.setAppEntryId(appEntryId);
	}

	/**
	* Returns the user ID of this app entry.
	*
	* @return the user ID of this app entry
	*/
	public long getUserId() {
		return _appEntry.getUserId();
	}

	/**
	* Sets the user ID of this app entry.
	*
	* @param userId the user ID of this app entry
	*/
	public void setUserId(long userId) {
		_appEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this app entry.
	*
	* @return the user uuid of this app entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this app entry.
	*
	* @param userUuid the user uuid of this app entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_appEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this app entry.
	*
	* @return the user name of this app entry
	*/
	public java.lang.String getUserName() {
		return _appEntry.getUserName();
	}

	/**
	* Sets the user name of this app entry.
	*
	* @param userName the user name of this app entry
	*/
	public void setUserName(java.lang.String userName) {
		_appEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this app entry.
	*
	* @return the create date of this app entry
	*/
	public java.util.Date getCreateDate() {
		return _appEntry.getCreateDate();
	}

	/**
	* Sets the create date of this app entry.
	*
	* @param createDate the create date of this app entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this app entry.
	*
	* @return the modified date of this app entry
	*/
	public java.util.Date getModifiedDate() {
		return _appEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this app entry.
	*
	* @param modifiedDate the modified date of this app entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_appEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the developer entry ID of this app entry.
	*
	* @return the developer entry ID of this app entry
	*/
	public long getDeveloperEntryId() {
		return _appEntry.getDeveloperEntryId();
	}

	/**
	* Sets the developer entry ID of this app entry.
	*
	* @param developerEntryId the developer entry ID of this app entry
	*/
	public void setDeveloperEntryId(long developerEntryId) {
		_appEntry.setDeveloperEntryId(developerEntryId);
	}

	/**
	* Returns the developer name of this app entry.
	*
	* @return the developer name of this app entry
	*/
	public java.lang.String getDeveloperName() {
		return _appEntry.getDeveloperName();
	}

	/**
	* Sets the developer name of this app entry.
	*
	* @param developerName the developer name of this app entry
	*/
	public void setDeveloperName(java.lang.String developerName) {
		_appEntry.setDeveloperName(developerName);
	}

	/**
	* Returns the title of this app entry.
	*
	* @return the title of this app entry
	*/
	public java.lang.String getTitle() {
		return _appEntry.getTitle();
	}

	/**
	* Sets the title of this app entry.
	*
	* @param title the title of this app entry
	*/
	public void setTitle(java.lang.String title) {
		_appEntry.setTitle(title);
	}

	/**
	* Returns the description of this app entry.
	*
	* @return the description of this app entry
	*/
	public java.lang.String getDescription() {
		return _appEntry.getDescription();
	}

	/**
	* Returns the localized description of this app entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this app entry
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _appEntry.getDescription(locale);
	}

	/**
	* Returns the localized description of this app entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this app entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _appEntry.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this app entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this app entry
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _appEntry.getDescription(languageId);
	}

	/**
	* Returns the localized description of this app entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this app entry
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _appEntry.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _appEntry.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _appEntry.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this app entry.
	*
	* @return the locales and localized descriptions of this app entry
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _appEntry.getDescriptionMap();
	}

	/**
	* Sets the description of this app entry.
	*
	* @param description the description of this app entry
	*/
	public void setDescription(java.lang.String description) {
		_appEntry.setDescription(description);
	}

	/**
	* Sets the localized description of this app entry in the language.
	*
	* @param description the localized description of this app entry
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_appEntry.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this app entry in the language, and sets the default locale.
	*
	* @param description the localized description of this app entry
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_appEntry.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_appEntry.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this app entry from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this app entry
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_appEntry.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this app entry from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this app entry
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_appEntry.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the website of this app entry.
	*
	* @return the website of this app entry
	*/
	public java.lang.String getWebsite() {
		return _appEntry.getWebsite();
	}

	/**
	* Sets the website of this app entry.
	*
	* @param website the website of this app entry
	*/
	public void setWebsite(java.lang.String website) {
		_appEntry.setWebsite(website);
	}

	/**
	* Returns the demo website of this app entry.
	*
	* @return the demo website of this app entry
	*/
	public java.lang.String getDemoWebsite() {
		return _appEntry.getDemoWebsite();
	}

	/**
	* Sets the demo website of this app entry.
	*
	* @param demoWebsite the demo website of this app entry
	*/
	public void setDemoWebsite(java.lang.String demoWebsite) {
		_appEntry.setDemoWebsite(demoWebsite);
	}

	/**
	* Returns the documentation website of this app entry.
	*
	* @return the documentation website of this app entry
	*/
	public java.lang.String getDocumentationWebsite() {
		return _appEntry.getDocumentationWebsite();
	}

	/**
	* Sets the documentation website of this app entry.
	*
	* @param documentationWebsite the documentation website of this app entry
	*/
	public void setDocumentationWebsite(java.lang.String documentationWebsite) {
		_appEntry.setDocumentationWebsite(documentationWebsite);
	}

	/**
	* Returns the reference website of this app entry.
	*
	* @return the reference website of this app entry
	*/
	public java.lang.String getReferenceWebsite() {
		return _appEntry.getReferenceWebsite();
	}

	/**
	* Sets the reference website of this app entry.
	*
	* @param referenceWebsite the reference website of this app entry
	*/
	public void setReferenceWebsite(java.lang.String referenceWebsite) {
		_appEntry.setReferenceWebsite(referenceWebsite);
	}

	/**
	* Returns the source code website of this app entry.
	*
	* @return the source code website of this app entry
	*/
	public java.lang.String getSourceCodeWebsite() {
		return _appEntry.getSourceCodeWebsite();
	}

	/**
	* Sets the source code website of this app entry.
	*
	* @param sourceCodeWebsite the source code website of this app entry
	*/
	public void setSourceCodeWebsite(java.lang.String sourceCodeWebsite) {
		_appEntry.setSourceCodeWebsite(sourceCodeWebsite);
	}

	/**
	* Returns the support website of this app entry.
	*
	* @return the support website of this app entry
	*/
	public java.lang.String getSupportWebsite() {
		return _appEntry.getSupportWebsite();
	}

	/**
	* Sets the support website of this app entry.
	*
	* @param supportWebsite the support website of this app entry
	*/
	public void setSupportWebsite(java.lang.String supportWebsite) {
		_appEntry.setSupportWebsite(supportWebsite);
	}

	/**
	* Returns the labs of this app entry.
	*
	* @return the labs of this app entry
	*/
	public boolean getLabs() {
		return _appEntry.getLabs();
	}

	/**
	* Returns <code>true</code> if this app entry is labs.
	*
	* @return <code>true</code> if this app entry is labs; <code>false</code> otherwise
	*/
	public boolean isLabs() {
		return _appEntry.isLabs();
	}

	/**
	* Sets whether this app entry is labs.
	*
	* @param labs the labs of this app entry
	*/
	public void setLabs(boolean labs) {
		_appEntry.setLabs(labs);
	}

	/**
	* Returns the product type of this app entry.
	*
	* @return the product type of this app entry
	*/
	public int getProductType() {
		return _appEntry.getProductType();
	}

	/**
	* Sets the product type of this app entry.
	*
	* @param productType the product type of this app entry
	*/
	public void setProductType(int productType) {
		_appEntry.setProductType(productType);
	}

	/**
	* Returns the version of this app entry.
	*
	* @return the version of this app entry
	*/
	public java.lang.String getVersion() {
		return _appEntry.getVersion();
	}

	/**
	* Sets the version of this app entry.
	*
	* @param version the version of this app entry
	*/
	public void setVersion(java.lang.String version) {
		_appEntry.setVersion(version);
	}

	/**
	* Returns the change log of this app entry.
	*
	* @return the change log of this app entry
	*/
	public java.lang.String getChangeLog() {
		return _appEntry.getChangeLog();
	}

	/**
	* Returns the localized change log of this app entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized change log of this app entry
	*/
	public java.lang.String getChangeLog(java.util.Locale locale) {
		return _appEntry.getChangeLog(locale);
	}

	/**
	* Returns the localized change log of this app entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized change log of this app entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getChangeLog(java.util.Locale locale,
		boolean useDefault) {
		return _appEntry.getChangeLog(locale, useDefault);
	}

	/**
	* Returns the localized change log of this app entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized change log of this app entry
	*/
	public java.lang.String getChangeLog(java.lang.String languageId) {
		return _appEntry.getChangeLog(languageId);
	}

	/**
	* Returns the localized change log of this app entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized change log of this app entry
	*/
	public java.lang.String getChangeLog(java.lang.String languageId,
		boolean useDefault) {
		return _appEntry.getChangeLog(languageId, useDefault);
	}

	public java.lang.String getChangeLogCurrentLanguageId() {
		return _appEntry.getChangeLogCurrentLanguageId();
	}

	public java.lang.String getChangeLogCurrentValue() {
		return _appEntry.getChangeLogCurrentValue();
	}

	/**
	* Returns a map of the locales and localized change logs of this app entry.
	*
	* @return the locales and localized change logs of this app entry
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getChangeLogMap() {
		return _appEntry.getChangeLogMap();
	}

	/**
	* Sets the change log of this app entry.
	*
	* @param changeLog the change log of this app entry
	*/
	public void setChangeLog(java.lang.String changeLog) {
		_appEntry.setChangeLog(changeLog);
	}

	/**
	* Sets the localized change log of this app entry in the language.
	*
	* @param changeLog the localized change log of this app entry
	* @param locale the locale of the language
	*/
	public void setChangeLog(java.lang.String changeLog, java.util.Locale locale) {
		_appEntry.setChangeLog(changeLog, locale);
	}

	/**
	* Sets the localized change log of this app entry in the language, and sets the default locale.
	*
	* @param changeLog the localized change log of this app entry
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setChangeLog(java.lang.String changeLog,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_appEntry.setChangeLog(changeLog, locale, defaultLocale);
	}

	public void setChangeLogCurrentLanguageId(java.lang.String languageId) {
		_appEntry.setChangeLogCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized change logs of this app entry from the map of locales and localized change logs.
	*
	* @param changeLogMap the locales and localized change logs of this app entry
	*/
	public void setChangeLogMap(
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap) {
		_appEntry.setChangeLogMap(changeLogMap);
	}

	/**
	* Sets the localized change logs of this app entry from the map of locales and localized change logs, and sets the default locale.
	*
	* @param changeLogMap the locales and localized change logs of this app entry
	* @param defaultLocale the default locale
	*/
	public void setChangeLogMap(
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		java.util.Locale defaultLocale) {
		_appEntry.setChangeLogMap(changeLogMap, defaultLocale);
	}

	/**
	* Returns the icon image ID of this app entry.
	*
	* @return the icon image ID of this app entry
	*/
	public long getIconImageId() {
		return _appEntry.getIconImageId();
	}

	/**
	* Sets the icon image ID of this app entry.
	*
	* @param iconImageId the icon image ID of this app entry
	*/
	public void setIconImageId(long iconImageId) {
		_appEntry.setIconImageId(iconImageId);
	}

	/**
	* Returns the pacl enabled of this app entry.
	*
	* @return the pacl enabled of this app entry
	*/
	public boolean getPaclEnabled() {
		return _appEntry.getPaclEnabled();
	}

	/**
	* Returns <code>true</code> if this app entry is pacl enabled.
	*
	* @return <code>true</code> if this app entry is pacl enabled; <code>false</code> otherwise
	*/
	public boolean isPaclEnabled() {
		return _appEntry.isPaclEnabled();
	}

	/**
	* Sets whether this app entry is pacl enabled.
	*
	* @param paclEnabled the pacl enabled of this app entry
	*/
	public void setPaclEnabled(boolean paclEnabled) {
		_appEntry.setPaclEnabled(paclEnabled);
	}

	/**
	* Returns the size of this app entry.
	*
	* @return the size of this app entry
	*/
	public long getSize() {
		return _appEntry.getSize();
	}

	/**
	* Sets the size of this app entry.
	*
	* @param size the size of this app entry
	*/
	public void setSize(long size) {
		_appEntry.setSize(size);
	}

	/**
	* Returns the download count of this app entry.
	*
	* @return the download count of this app entry
	*/
	public int getDownloadCount() {
		return _appEntry.getDownloadCount();
	}

	/**
	* Sets the download count of this app entry.
	*
	* @param downloadCount the download count of this app entry
	*/
	public void setDownloadCount(int downloadCount) {
		_appEntry.setDownloadCount(downloadCount);
	}

	/**
	* Returns the license type of this app entry.
	*
	* @return the license type of this app entry
	*/
	public int getLicenseType() {
		return _appEntry.getLicenseType();
	}

	/**
	* Sets the license type of this app entry.
	*
	* @param licenseType the license type of this app entry
	*/
	public void setLicenseType(int licenseType) {
		_appEntry.setLicenseType(licenseType);
	}

	/**
	* Returns the license lifetime of this app entry.
	*
	* @return the license lifetime of this app entry
	*/
	public long getLicenseLifetime() {
		return _appEntry.getLicenseLifetime();
	}

	/**
	* Sets the license lifetime of this app entry.
	*
	* @param licenseLifetime the license lifetime of this app entry
	*/
	public void setLicenseLifetime(long licenseLifetime) {
		_appEntry.setLicenseLifetime(licenseLifetime);
	}

	/**
	* Returns the supported of this app entry.
	*
	* @return the supported of this app entry
	*/
	public boolean getSupported() {
		return _appEntry.getSupported();
	}

	/**
	* Returns <code>true</code> if this app entry is supported.
	*
	* @return <code>true</code> if this app entry is supported; <code>false</code> otherwise
	*/
	public boolean isSupported() {
		return _appEntry.isSupported();
	}

	/**
	* Sets whether this app entry is supported.
	*
	* @param supported the supported of this app entry
	*/
	public void setSupported(boolean supported) {
		_appEntry.setSupported(supported);
	}

	/**
	* Returns the order u r l of this app entry.
	*
	* @return the order u r l of this app entry
	*/
	public java.lang.String getOrderURL() {
		return _appEntry.getOrderURL();
	}

	/**
	* Sets the order u r l of this app entry.
	*
	* @param orderURL the order u r l of this app entry
	*/
	public void setOrderURL(java.lang.String orderURL) {
		_appEntry.setOrderURL(orderURL);
	}

	/**
	* Returns the hidden of this app entry.
	*
	* @return the hidden of this app entry
	*/
	public boolean getHidden() {
		return _appEntry.getHidden();
	}

	/**
	* Returns <code>true</code> if this app entry is hidden.
	*
	* @return <code>true</code> if this app entry is hidden; <code>false</code> otherwise
	*/
	public boolean isHidden() {
		return _appEntry.isHidden();
	}

	/**
	* Sets whether this app entry is hidden.
	*
	* @param hidden the hidden of this app entry
	*/
	public void setHidden(boolean hidden) {
		_appEntry.setHidden(hidden);
	}

	/**
	* Returns the portal required of this app entry.
	*
	* @return the portal required of this app entry
	*/
	public boolean getPortalRequired() {
		return _appEntry.getPortalRequired();
	}

	/**
	* Returns <code>true</code> if this app entry is portal required.
	*
	* @return <code>true</code> if this app entry is portal required; <code>false</code> otherwise
	*/
	public boolean isPortalRequired() {
		return _appEntry.isPortalRequired();
	}

	/**
	* Sets whether this app entry is portal required.
	*
	* @param portalRequired the portal required of this app entry
	*/
	public void setPortalRequired(boolean portalRequired) {
		_appEntry.setPortalRequired(portalRequired);
	}

	/**
	* Returns the status of this app entry.
	*
	* @return the status of this app entry
	*/
	public int getStatus() {
		return _appEntry.getStatus();
	}

	/**
	* Sets the status of this app entry.
	*
	* @param status the status of this app entry
	*/
	public void setStatus(int status) {
		_appEntry.setStatus(status);
	}

	/**
	* Returns the status by user ID of this app entry.
	*
	* @return the status by user ID of this app entry
	*/
	public long getStatusByUserId() {
		return _appEntry.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this app entry.
	*
	* @param statusByUserId the status by user ID of this app entry
	*/
	public void setStatusByUserId(long statusByUserId) {
		_appEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this app entry.
	*
	* @return the status by user uuid of this app entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this app entry.
	*
	* @param statusByUserUuid the status by user uuid of this app entry
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_appEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this app entry.
	*
	* @return the status by user name of this app entry
	*/
	public java.lang.String getStatusByUserName() {
		return _appEntry.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this app entry.
	*
	* @param statusByUserName the status by user name of this app entry
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_appEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this app entry.
	*
	* @return the status date of this app entry
	*/
	public java.util.Date getStatusDate() {
		return _appEntry.getStatusDate();
	}

	/**
	* Sets the status date of this app entry.
	*
	* @param statusDate the status date of this app entry
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_appEntry.setStatusDate(statusDate);
	}

	/**
	* Returns the status version date of this app entry.
	*
	* @return the status version date of this app entry
	*/
	public java.util.Date getStatusVersionDate() {
		return _appEntry.getStatusVersionDate();
	}

	/**
	* Sets the status version date of this app entry.
	*
	* @param statusVersionDate the status version date of this app entry
	*/
	public void setStatusVersionDate(java.util.Date statusVersionDate) {
		_appEntry.setStatusVersionDate(statusVersionDate);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _appEntry.getApproved();
	}

	/**
	* Returns <code>true</code> if this app entry is approved.
	*
	* @return <code>true</code> if this app entry is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _appEntry.isApproved();
	}

	/**
	* Returns <code>true</code> if this app entry is denied.
	*
	* @return <code>true</code> if this app entry is denied; <code>false</code> otherwise
	*/
	public boolean isDenied() {
		return _appEntry.isDenied();
	}

	/**
	* Returns <code>true</code> if this app entry is a draft.
	*
	* @return <code>true</code> if this app entry is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _appEntry.isDraft();
	}

	/**
	* Returns <code>true</code> if this app entry is expired.
	*
	* @return <code>true</code> if this app entry is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _appEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this app entry is inactive.
	*
	* @return <code>true</code> if this app entry is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _appEntry.isInactive();
	}

	/**
	* Returns <code>true</code> if this app entry is incomplete.
	*
	* @return <code>true</code> if this app entry is incomplete; <code>false</code> otherwise
	*/
	public boolean isIncomplete() {
		return _appEntry.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this app entry is pending.
	*
	* @return <code>true</code> if this app entry is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _appEntry.isPending();
	}

	/**
	* Returns <code>true</code> if this app entry is scheduled.
	*
	* @return <code>true</code> if this app entry is scheduled; <code>false</code> otherwise
	*/
	public boolean isScheduled() {
		return _appEntry.isScheduled();
	}

	public boolean isNew() {
		return _appEntry.isNew();
	}

	public void setNew(boolean n) {
		_appEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _appEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_appEntry.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new AppEntryWrapper((AppEntry)_appEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.AppEntry appEntry) {
		return _appEntry.compareTo(appEntry);
	}

	@Override
	public int hashCode() {
		return _appEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppEntry> toCacheModel() {
		return _appEntry.toCacheModel();
	}

	public com.liferay.osb.model.AppEntry toEscapedModel() {
		return new AppEntryWrapper(_appEntry.toEscapedModel());
	}

	public com.liferay.osb.model.AppEntry toUnescapedModel() {
		return new AppEntryWrapper(_appEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _appEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appEntry.persist();
	}

	public com.liferay.osb.model.AppVersion getActionableAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getActionableAppVersion();
	}

	public com.liferay.osb.model.AppVersion getApprovedAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getApprovedAppVersion();
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getAssetCategories()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getAssetCategories();
	}

	public long[] getAssetCategoryIds()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getAssetCategoryIds();
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getAssetEntry();
	}

	public long[] getAssetListIds()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getAssetListIds();
	}

	public java.lang.String[] getAssetTagNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getAssetTagNames();
	}

	public long[] getAvailableCountryIds()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getAvailableCountryIds();
	}

	public java.lang.String[] getCompatibilities()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getCompatibilities();
	}

	public com.liferay.osb.model.DeveloperEntry getDeveloperEntry()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getDeveloperEntry();
	}

	public java.lang.String getDeveloperEntryName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getDeveloperEntryName();
	}

	public com.liferay.osb.model.AppVersion getLatestAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getLatestAppVersion();
	}

	public java.lang.String getLicenseLifetimeLabel() {
		return _appEntry.getLicenseLifetimeLabel();
	}

	public java.lang.String getLicenseTypeLabel() {
		return _appEntry.getLicenseTypeLabel();
	}

	public java.lang.String getStatusLabel() {
		return _appEntry.getStatusLabel();
	}

	public com.liferay.osb.model.AppVersion getUnreleasedAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.getUnreleasedAppVersion();
	}

	public boolean hasAvailableCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.hasAvailableCountry(countryId);
	}

	public boolean hasCustomOrderWorkflow() {
		return _appEntry.hasCustomOrderWorkflow();
	}

	public boolean hasMultipleNewVersions()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.hasMultipleNewVersions();
	}

	public boolean hasUnreleasedAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.hasUnreleasedAppVersion();
	}

	public boolean isCotermRequired() {
		return _appEntry.isCotermRequired();
	}

	public boolean isDeveloperEntryCorpEntry()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.isDeveloperEntryCorpEntry();
	}

	public boolean isDeveloperEntryLiferayInc()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.isDeveloperEntryLiferayInc();
	}

	public boolean isDeveloperEntryUser()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.isDeveloperEntryUser();
	}

	public boolean isFirstSubmission()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.isFirstSubmission();
	}

	public boolean isFree() {
		return _appEntry.isFree();
	}

	public boolean isLiferayEEPlugin()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.isLiferayEEPlugin();
	}

	public boolean isSOEEPlugin() {
		return _appEntry.isSOEEPlugin();
	}

	public boolean isStatusByDeveloper()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appEntry.isStatusByDeveloper();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppEntryWrapper)) {
			return false;
		}

		AppEntryWrapper appEntryWrapper = (AppEntryWrapper)obj;

		if (Validator.equals(_appEntry, appEntryWrapper._appEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppEntry getWrappedAppEntry() {
		return _appEntry;
	}

	public AppEntry getWrappedModel() {
		return _appEntry;
	}

	public void resetOriginalValues() {
		_appEntry.resetOriginalValues();
	}

	private AppEntry _appEntry;
}