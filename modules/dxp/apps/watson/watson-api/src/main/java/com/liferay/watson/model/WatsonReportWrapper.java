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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link WatsonReport}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonReport
 * @generated
 */
@ProviderType
public class WatsonReportWrapper implements WatsonReport,
	ModelWrapper<WatsonReport> {
	public WatsonReportWrapper(WatsonReport watsonReport) {
		_watsonReport = watsonReport;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonReport.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonReport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonReportId", getWatsonReportId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("originalWatsonReportId", getOriginalWatsonReportId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonChildId", getWatsonChildId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("fullReport", getFullReport());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("timeSpent", getTimeSpent());
		attributes.put("reportedUser", getReportedUser());
		attributes.put("reportDate", getReportDate());
		attributes.put("key", getKey());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonReportId = (Long)attributes.get("watsonReportId");

		if (watsonReportId != null) {
			setWatsonReportId(watsonReportId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long originalWatsonReportId = (Long)attributes.get(
				"originalWatsonReportId");

		if (originalWatsonReportId != null) {
			setOriginalWatsonReportId(originalWatsonReportId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonChildId = (Long)attributes.get("watsonChildId");

		if (watsonChildId != null) {
			setWatsonChildId(watsonChildId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String fullReport = (String)attributes.get("fullReport");

		if (fullReport != null) {
			setFullReport(fullReport);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		String timeSpent = (String)attributes.get("timeSpent");

		if (timeSpent != null) {
			setTimeSpent(timeSpent);
		}

		String reportedUser = (String)attributes.get("reportedUser");

		if (reportedUser != null) {
			setReportedUser(reportedUser);
		}

		Date reportDate = (Date)attributes.get("reportDate");

		if (reportDate != null) {
			setReportDate(reportDate);
		}

		Integer key = (Integer)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonReportWrapper((WatsonReport)_watsonReport.clone());
	}

	@Override
	public int compareTo(WatsonReport watsonReport) {
		return _watsonReport.compareTo(watsonReport);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonReport.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson report.
	*
	* @return the company ID of this watson report
	*/
	@Override
	public long getCompanyId() {
		return _watsonReport.getCompanyId();
	}

	/**
	* Returns the create date of this watson report.
	*
	* @return the create date of this watson report
	*/
	@Override
	public Date getCreateDate() {
		return _watsonReport.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonReport.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson report.
	*
	* @return the description of this watson report
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonReport.getDescription();
	}

	/**
	* Returns the localized description of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson report
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonReport.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson report. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonReport.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson report
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonReport.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson report
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReport.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonReport.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonReport.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson report.
	*
	* @return the locales and localized descriptions of this watson report
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonReport.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonReport.getExpandoBridge();
	}

	/**
	* Returns the full report of this watson report.
	*
	* @return the full report of this watson report
	*/
	@Override
	public java.lang.String getFullReport() {
		return _watsonReport.getFullReport();
	}

	/**
	* Returns the localized full report of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized full report of this watson report
	*/
	@Override
	public java.lang.String getFullReport(java.util.Locale locale) {
		return _watsonReport.getFullReport(locale);
	}

	/**
	* Returns the localized full report of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized full report of this watson report. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFullReport(java.util.Locale locale,
		boolean useDefault) {
		return _watsonReport.getFullReport(locale, useDefault);
	}

	/**
	* Returns the localized full report of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized full report of this watson report
	*/
	@Override
	public java.lang.String getFullReport(java.lang.String languageId) {
		return _watsonReport.getFullReport(languageId);
	}

	/**
	* Returns the localized full report of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized full report of this watson report
	*/
	@Override
	public java.lang.String getFullReport(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReport.getFullReport(languageId, useDefault);
	}

	@Override
	public java.lang.String getFullReportCurrentLanguageId() {
		return _watsonReport.getFullReportCurrentLanguageId();
	}

	@Override
	public java.lang.String getFullReportCurrentValue() {
		return _watsonReport.getFullReportCurrentValue();
	}

	/**
	* Returns a map of the locales and localized full reports of this watson report.
	*
	* @return the locales and localized full reports of this watson report
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFullReportMap() {
		return _watsonReport.getFullReportMap();
	}

	/**
	* Returns the group ID of this watson report.
	*
	* @return the group ID of this watson report
	*/
	@Override
	public long getGroupId() {
		return _watsonReport.getGroupId();
	}

	/**
	* Returns the image payload of this watson report.
	*
	* @return the image payload of this watson report
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonReport.getImagePayload();
	}

	/**
	* Returns the key of this watson report.
	*
	* @return the key of this watson report
	*/
	@Override
	public int getKey() {
		return _watsonReport.getKey();
	}

	/**
	* Returns the modified date of this watson report.
	*
	* @return the modified date of this watson report
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonReport.getModifiedDate();
	}

	/**
	* Returns the name of this watson report.
	*
	* @return the name of this watson report
	*/
	@Override
	public java.lang.String getName() {
		return _watsonReport.getName();
	}

	/**
	* Returns the localized name of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson report
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _watsonReport.getName(locale);
	}

	/**
	* Returns the localized name of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson report. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonReport.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson report
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _watsonReport.getName(languageId);
	}

	/**
	* Returns the localized name of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson report
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReport.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _watsonReport.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _watsonReport.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson report.
	*
	* @return the locales and localized names of this watson report
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _watsonReport.getNameMap();
	}

	/**
	* Returns the original watson report ID of this watson report.
	*
	* @return the original watson report ID of this watson report
	*/
	@Override
	public long getOriginalWatsonReportId() {
		return _watsonReport.getOriginalWatsonReportId();
	}

	/**
	* Returns the primary key of this watson report.
	*
	* @return the primary key of this watson report
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonReport.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonReport.getPrimaryKeyObj();
	}

	/**
	* Returns the report date of this watson report.
	*
	* @return the report date of this watson report
	*/
	@Override
	public Date getReportDate() {
		return _watsonReport.getReportDate();
	}

	/**
	* Returns the reported user of this watson report.
	*
	* @return the reported user of this watson report
	*/
	@Override
	public java.lang.String getReportedUser() {
		return _watsonReport.getReportedUser();
	}

	/**
	* Returns the localized reported user of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized reported user of this watson report
	*/
	@Override
	public java.lang.String getReportedUser(java.util.Locale locale) {
		return _watsonReport.getReportedUser(locale);
	}

	/**
	* Returns the localized reported user of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized reported user of this watson report. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getReportedUser(java.util.Locale locale,
		boolean useDefault) {
		return _watsonReport.getReportedUser(locale, useDefault);
	}

	/**
	* Returns the localized reported user of this watson report in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized reported user of this watson report
	*/
	@Override
	public java.lang.String getReportedUser(java.lang.String languageId) {
		return _watsonReport.getReportedUser(languageId);
	}

	/**
	* Returns the localized reported user of this watson report in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized reported user of this watson report
	*/
	@Override
	public java.lang.String getReportedUser(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReport.getReportedUser(languageId, useDefault);
	}

	@Override
	public java.lang.String getReportedUserCurrentLanguageId() {
		return _watsonReport.getReportedUserCurrentLanguageId();
	}

	@Override
	public java.lang.String getReportedUserCurrentValue() {
		return _watsonReport.getReportedUserCurrentValue();
	}

	/**
	* Returns a map of the locales and localized reported users of this watson report.
	*
	* @return the locales and localized reported users of this watson report
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getReportedUserMap() {
		return _watsonReport.getReportedUserMap();
	}

	/**
	* Returns the status of this watson report.
	*
	* @return the status of this watson report
	*/
	@Override
	public int getStatus() {
		return _watsonReport.getStatus();
	}

	/**
	* Returns the time spent of this watson report.
	*
	* @return the time spent of this watson report
	*/
	@Override
	public java.lang.String getTimeSpent() {
		return _watsonReport.getTimeSpent();
	}

	/**
	* Returns the type watson list type ID of this watson report.
	*
	* @return the type watson list type ID of this watson report
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonReport.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson report.
	*
	* @return the user ID of this watson report
	*/
	@Override
	public long getUserId() {
		return _watsonReport.getUserId();
	}

	/**
	* Returns the user name of this watson report.
	*
	* @return the user name of this watson report
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonReport.getUserName();
	}

	/**
	* Returns the user uuid of this watson report.
	*
	* @return the user uuid of this watson report
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonReport.getUserUuid();
	}

	/**
	* Returns the watson child ID of this watson report.
	*
	* @return the watson child ID of this watson report
	*/
	@Override
	public long getWatsonChildId() {
		return _watsonReport.getWatsonChildId();
	}

	/**
	* Returns the watson report ID of this watson report.
	*
	* @return the watson report ID of this watson report
	*/
	@Override
	public long getWatsonReportId() {
		return _watsonReport.getWatsonReportId();
	}

	@Override
	public int hashCode() {
		return _watsonReport.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonReport.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonReport.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonReport.isNew();
	}

	@Override
	public void persist() {
		_watsonReport.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonReport.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonReport.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonReport.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson report.
	*
	* @param companyId the company ID of this watson report
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonReport.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson report.
	*
	* @param createDate the create date of this watson report
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonReport.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson report.
	*
	* @param description the description of this watson report
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonReport.setDescription(description);
	}

	/**
	* Sets the localized description of this watson report in the language.
	*
	* @param description the localized description of this watson report
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonReport.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson report in the language, and sets the default locale.
	*
	* @param description the localized description of this watson report
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonReport.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonReport.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson report from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson report
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonReport.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson report from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson report
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonReport.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonReport.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonReport.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonReport.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the full report of this watson report.
	*
	* @param fullReport the full report of this watson report
	*/
	@Override
	public void setFullReport(java.lang.String fullReport) {
		_watsonReport.setFullReport(fullReport);
	}

	/**
	* Sets the localized full report of this watson report in the language.
	*
	* @param fullReport the localized full report of this watson report
	* @param locale the locale of the language
	*/
	@Override
	public void setFullReport(java.lang.String fullReport,
		java.util.Locale locale) {
		_watsonReport.setFullReport(fullReport, locale);
	}

	/**
	* Sets the localized full report of this watson report in the language, and sets the default locale.
	*
	* @param fullReport the localized full report of this watson report
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFullReport(java.lang.String fullReport,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonReport.setFullReport(fullReport, locale, defaultLocale);
	}

	@Override
	public void setFullReportCurrentLanguageId(java.lang.String languageId) {
		_watsonReport.setFullReportCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized full reports of this watson report from the map of locales and localized full reports.
	*
	* @param fullReportMap the locales and localized full reports of this watson report
	*/
	@Override
	public void setFullReportMap(
		Map<java.util.Locale, java.lang.String> fullReportMap) {
		_watsonReport.setFullReportMap(fullReportMap);
	}

	/**
	* Sets the localized full reports of this watson report from the map of locales and localized full reports, and sets the default locale.
	*
	* @param fullReportMap the locales and localized full reports of this watson report
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFullReportMap(
		Map<java.util.Locale, java.lang.String> fullReportMap,
		java.util.Locale defaultLocale) {
		_watsonReport.setFullReportMap(fullReportMap, defaultLocale);
	}

	/**
	* Sets the group ID of this watson report.
	*
	* @param groupId the group ID of this watson report
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonReport.setGroupId(groupId);
	}

	/**
	* Sets the image payload of this watson report.
	*
	* @param imagePayload the image payload of this watson report
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonReport.setImagePayload(imagePayload);
	}

	/**
	* Sets the key of this watson report.
	*
	* @param key the key of this watson report
	*/
	@Override
	public void setKey(int key) {
		_watsonReport.setKey(key);
	}

	/**
	* Sets the modified date of this watson report.
	*
	* @param modifiedDate the modified date of this watson report
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonReport.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson report.
	*
	* @param name the name of this watson report
	*/
	@Override
	public void setName(java.lang.String name) {
		_watsonReport.setName(name);
	}

	/**
	* Sets the localized name of this watson report in the language.
	*
	* @param name the localized name of this watson report
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_watsonReport.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson report in the language, and sets the default locale.
	*
	* @param name the localized name of this watson report
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonReport.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_watsonReport.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson report from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson report
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_watsonReport.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson report from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson report
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonReport.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonReport.setNew(n);
	}

	/**
	* Sets the original watson report ID of this watson report.
	*
	* @param originalWatsonReportId the original watson report ID of this watson report
	*/
	@Override
	public void setOriginalWatsonReportId(long originalWatsonReportId) {
		_watsonReport.setOriginalWatsonReportId(originalWatsonReportId);
	}

	/**
	* Sets the primary key of this watson report.
	*
	* @param primaryKey the primary key of this watson report
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonReport.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonReport.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report date of this watson report.
	*
	* @param reportDate the report date of this watson report
	*/
	@Override
	public void setReportDate(Date reportDate) {
		_watsonReport.setReportDate(reportDate);
	}

	/**
	* Sets the reported user of this watson report.
	*
	* @param reportedUser the reported user of this watson report
	*/
	@Override
	public void setReportedUser(java.lang.String reportedUser) {
		_watsonReport.setReportedUser(reportedUser);
	}

	/**
	* Sets the localized reported user of this watson report in the language.
	*
	* @param reportedUser the localized reported user of this watson report
	* @param locale the locale of the language
	*/
	@Override
	public void setReportedUser(java.lang.String reportedUser,
		java.util.Locale locale) {
		_watsonReport.setReportedUser(reportedUser, locale);
	}

	/**
	* Sets the localized reported user of this watson report in the language, and sets the default locale.
	*
	* @param reportedUser the localized reported user of this watson report
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setReportedUser(java.lang.String reportedUser,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonReport.setReportedUser(reportedUser, locale, defaultLocale);
	}

	@Override
	public void setReportedUserCurrentLanguageId(java.lang.String languageId) {
		_watsonReport.setReportedUserCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized reported users of this watson report from the map of locales and localized reported users.
	*
	* @param reportedUserMap the locales and localized reported users of this watson report
	*/
	@Override
	public void setReportedUserMap(
		Map<java.util.Locale, java.lang.String> reportedUserMap) {
		_watsonReport.setReportedUserMap(reportedUserMap);
	}

	/**
	* Sets the localized reported users of this watson report from the map of locales and localized reported users, and sets the default locale.
	*
	* @param reportedUserMap the locales and localized reported users of this watson report
	* @param defaultLocale the default locale
	*/
	@Override
	public void setReportedUserMap(
		Map<java.util.Locale, java.lang.String> reportedUserMap,
		java.util.Locale defaultLocale) {
		_watsonReport.setReportedUserMap(reportedUserMap, defaultLocale);
	}

	/**
	* Sets the status of this watson report.
	*
	* @param status the status of this watson report
	*/
	@Override
	public void setStatus(int status) {
		_watsonReport.setStatus(status);
	}

	/**
	* Sets the time spent of this watson report.
	*
	* @param timeSpent the time spent of this watson report
	*/
	@Override
	public void setTimeSpent(java.lang.String timeSpent) {
		_watsonReport.setTimeSpent(timeSpent);
	}

	/**
	* Sets the type watson list type ID of this watson report.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson report
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonReport.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson report.
	*
	* @param userId the user ID of this watson report
	*/
	@Override
	public void setUserId(long userId) {
		_watsonReport.setUserId(userId);
	}

	/**
	* Sets the user name of this watson report.
	*
	* @param userName the user name of this watson report
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonReport.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson report.
	*
	* @param userUuid the user uuid of this watson report
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonReport.setUserUuid(userUuid);
	}

	/**
	* Sets the watson child ID of this watson report.
	*
	* @param watsonChildId the watson child ID of this watson report
	*/
	@Override
	public void setWatsonChildId(long watsonChildId) {
		_watsonReport.setWatsonChildId(watsonChildId);
	}

	/**
	* Sets the watson report ID of this watson report.
	*
	* @param watsonReportId the watson report ID of this watson report
	*/
	@Override
	public void setWatsonReportId(long watsonReportId) {
		_watsonReport.setWatsonReportId(watsonReportId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonReport> toCacheModel() {
		return _watsonReport.toCacheModel();
	}

	@Override
	public WatsonReport toEscapedModel() {
		return new WatsonReportWrapper(_watsonReport.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonReport.toString();
	}

	@Override
	public WatsonReport toUnescapedModel() {
		return new WatsonReportWrapper(_watsonReport.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonReport.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonReportWrapper)) {
			return false;
		}

		WatsonReportWrapper watsonReportWrapper = (WatsonReportWrapper)obj;

		if (Objects.equals(_watsonReport, watsonReportWrapper._watsonReport)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonReport getWrappedModel() {
		return _watsonReport;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonReport.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonReport.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonReport.resetOriginalValues();
	}

	private final WatsonReport _watsonReport;
}