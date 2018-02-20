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
 * This class is a wrapper for {@link WatsonReportAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonReportAudit
 * @generated
 */
@ProviderType
public class WatsonReportAuditWrapper implements WatsonReportAudit,
	ModelWrapper<WatsonReportAudit> {
	public WatsonReportAuditWrapper(WatsonReportAudit watsonReportAudit) {
		_watsonReportAudit = watsonReportAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonReportAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonReportAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonReportAuditId", getWatsonReportAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("originalWatsonReportId", getOriginalWatsonReportId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonChildId", getWatsonChildId());
		attributes.put("watsonReportId", getWatsonReportId());
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
		Long watsonReportAuditId = (Long)attributes.get("watsonReportAuditId");

		if (watsonReportAuditId != null) {
			setWatsonReportAuditId(watsonReportAuditId);
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

		Long watsonReportId = (Long)attributes.get("watsonReportId");

		if (watsonReportId != null) {
			setWatsonReportId(watsonReportId);
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
		return new WatsonReportAuditWrapper((WatsonReportAudit)_watsonReportAudit.clone());
	}

	@Override
	public int compareTo(WatsonReportAudit watsonReportAudit) {
		return _watsonReportAudit.compareTo(watsonReportAudit);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonReportAudit.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson report audit.
	*
	* @return the company ID of this watson report audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonReportAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson report audit.
	*
	* @return the create date of this watson report audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonReportAudit.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonReportAudit.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson report audit.
	*
	* @return the description of this watson report audit
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonReportAudit.getDescription();
	}

	/**
	* Returns the localized description of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson report audit
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonReportAudit.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson report audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonReportAudit.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson report audit
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonReportAudit.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson report audit
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReportAudit.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonReportAudit.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonReportAudit.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson report audit.
	*
	* @return the locales and localized descriptions of this watson report audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonReportAudit.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonReportAudit.getExpandoBridge();
	}

	/**
	* Returns the full report of this watson report audit.
	*
	* @return the full report of this watson report audit
	*/
	@Override
	public java.lang.String getFullReport() {
		return _watsonReportAudit.getFullReport();
	}

	/**
	* Returns the localized full report of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized full report of this watson report audit
	*/
	@Override
	public java.lang.String getFullReport(java.util.Locale locale) {
		return _watsonReportAudit.getFullReport(locale);
	}

	/**
	* Returns the localized full report of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized full report of this watson report audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFullReport(java.util.Locale locale,
		boolean useDefault) {
		return _watsonReportAudit.getFullReport(locale, useDefault);
	}

	/**
	* Returns the localized full report of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized full report of this watson report audit
	*/
	@Override
	public java.lang.String getFullReport(java.lang.String languageId) {
		return _watsonReportAudit.getFullReport(languageId);
	}

	/**
	* Returns the localized full report of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized full report of this watson report audit
	*/
	@Override
	public java.lang.String getFullReport(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReportAudit.getFullReport(languageId, useDefault);
	}

	@Override
	public java.lang.String getFullReportCurrentLanguageId() {
		return _watsonReportAudit.getFullReportCurrentLanguageId();
	}

	@Override
	public java.lang.String getFullReportCurrentValue() {
		return _watsonReportAudit.getFullReportCurrentValue();
	}

	/**
	* Returns a map of the locales and localized full reports of this watson report audit.
	*
	* @return the locales and localized full reports of this watson report audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFullReportMap() {
		return _watsonReportAudit.getFullReportMap();
	}

	/**
	* Returns the group ID of this watson report audit.
	*
	* @return the group ID of this watson report audit
	*/
	@Override
	public long getGroupId() {
		return _watsonReportAudit.getGroupId();
	}

	/**
	* Returns the image payload of this watson report audit.
	*
	* @return the image payload of this watson report audit
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonReportAudit.getImagePayload();
	}

	/**
	* Returns the key of this watson report audit.
	*
	* @return the key of this watson report audit
	*/
	@Override
	public int getKey() {
		return _watsonReportAudit.getKey();
	}

	/**
	* Returns the modified date of this watson report audit.
	*
	* @return the modified date of this watson report audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonReportAudit.getModifiedDate();
	}

	/**
	* Returns the name of this watson report audit.
	*
	* @return the name of this watson report audit
	*/
	@Override
	public java.lang.String getName() {
		return _watsonReportAudit.getName();
	}

	/**
	* Returns the localized name of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson report audit
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _watsonReportAudit.getName(locale);
	}

	/**
	* Returns the localized name of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson report audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonReportAudit.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson report audit
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _watsonReportAudit.getName(languageId);
	}

	/**
	* Returns the localized name of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson report audit
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReportAudit.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _watsonReportAudit.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _watsonReportAudit.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson report audit.
	*
	* @return the locales and localized names of this watson report audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _watsonReportAudit.getNameMap();
	}

	/**
	* Returns the original watson report ID of this watson report audit.
	*
	* @return the original watson report ID of this watson report audit
	*/
	@Override
	public long getOriginalWatsonReportId() {
		return _watsonReportAudit.getOriginalWatsonReportId();
	}

	/**
	* Returns the primary key of this watson report audit.
	*
	* @return the primary key of this watson report audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonReportAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonReportAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the report date of this watson report audit.
	*
	* @return the report date of this watson report audit
	*/
	@Override
	public Date getReportDate() {
		return _watsonReportAudit.getReportDate();
	}

	/**
	* Returns the reported user of this watson report audit.
	*
	* @return the reported user of this watson report audit
	*/
	@Override
	public java.lang.String getReportedUser() {
		return _watsonReportAudit.getReportedUser();
	}

	/**
	* Returns the localized reported user of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized reported user of this watson report audit
	*/
	@Override
	public java.lang.String getReportedUser(java.util.Locale locale) {
		return _watsonReportAudit.getReportedUser(locale);
	}

	/**
	* Returns the localized reported user of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized reported user of this watson report audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getReportedUser(java.util.Locale locale,
		boolean useDefault) {
		return _watsonReportAudit.getReportedUser(locale, useDefault);
	}

	/**
	* Returns the localized reported user of this watson report audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized reported user of this watson report audit
	*/
	@Override
	public java.lang.String getReportedUser(java.lang.String languageId) {
		return _watsonReportAudit.getReportedUser(languageId);
	}

	/**
	* Returns the localized reported user of this watson report audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized reported user of this watson report audit
	*/
	@Override
	public java.lang.String getReportedUser(java.lang.String languageId,
		boolean useDefault) {
		return _watsonReportAudit.getReportedUser(languageId, useDefault);
	}

	@Override
	public java.lang.String getReportedUserCurrentLanguageId() {
		return _watsonReportAudit.getReportedUserCurrentLanguageId();
	}

	@Override
	public java.lang.String getReportedUserCurrentValue() {
		return _watsonReportAudit.getReportedUserCurrentValue();
	}

	/**
	* Returns a map of the locales and localized reported users of this watson report audit.
	*
	* @return the locales and localized reported users of this watson report audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getReportedUserMap() {
		return _watsonReportAudit.getReportedUserMap();
	}

	/**
	* Returns the status of this watson report audit.
	*
	* @return the status of this watson report audit
	*/
	@Override
	public int getStatus() {
		return _watsonReportAudit.getStatus();
	}

	/**
	* Returns the time spent of this watson report audit.
	*
	* @return the time spent of this watson report audit
	*/
	@Override
	public java.lang.String getTimeSpent() {
		return _watsonReportAudit.getTimeSpent();
	}

	/**
	* Returns the type watson list type ID of this watson report audit.
	*
	* @return the type watson list type ID of this watson report audit
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonReportAudit.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson report audit.
	*
	* @return the user ID of this watson report audit
	*/
	@Override
	public long getUserId() {
		return _watsonReportAudit.getUserId();
	}

	/**
	* Returns the user name of this watson report audit.
	*
	* @return the user name of this watson report audit
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonReportAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson report audit.
	*
	* @return the user uuid of this watson report audit
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonReportAudit.getUserUuid();
	}

	/**
	* Returns the watson child ID of this watson report audit.
	*
	* @return the watson child ID of this watson report audit
	*/
	@Override
	public long getWatsonChildId() {
		return _watsonReportAudit.getWatsonChildId();
	}

	/**
	* Returns the watson report audit ID of this watson report audit.
	*
	* @return the watson report audit ID of this watson report audit
	*/
	@Override
	public long getWatsonReportAuditId() {
		return _watsonReportAudit.getWatsonReportAuditId();
	}

	/**
	* Returns the watson report ID of this watson report audit.
	*
	* @return the watson report ID of this watson report audit
	*/
	@Override
	public long getWatsonReportId() {
		return _watsonReportAudit.getWatsonReportId();
	}

	@Override
	public int hashCode() {
		return _watsonReportAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonReportAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonReportAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonReportAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonReportAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonReportAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonReportAudit.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonReportAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson report audit.
	*
	* @param companyId the company ID of this watson report audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonReportAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson report audit.
	*
	* @param createDate the create date of this watson report audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonReportAudit.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson report audit.
	*
	* @param description the description of this watson report audit
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonReportAudit.setDescription(description);
	}

	/**
	* Sets the localized description of this watson report audit in the language.
	*
	* @param description the localized description of this watson report audit
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonReportAudit.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson report audit in the language, and sets the default locale.
	*
	* @param description the localized description of this watson report audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonReportAudit.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonReportAudit.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson report audit from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson report audit
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonReportAudit.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson report audit from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson report audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonReportAudit.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonReportAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonReportAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonReportAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the full report of this watson report audit.
	*
	* @param fullReport the full report of this watson report audit
	*/
	@Override
	public void setFullReport(java.lang.String fullReport) {
		_watsonReportAudit.setFullReport(fullReport);
	}

	/**
	* Sets the localized full report of this watson report audit in the language.
	*
	* @param fullReport the localized full report of this watson report audit
	* @param locale the locale of the language
	*/
	@Override
	public void setFullReport(java.lang.String fullReport,
		java.util.Locale locale) {
		_watsonReportAudit.setFullReport(fullReport, locale);
	}

	/**
	* Sets the localized full report of this watson report audit in the language, and sets the default locale.
	*
	* @param fullReport the localized full report of this watson report audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFullReport(java.lang.String fullReport,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonReportAudit.setFullReport(fullReport, locale, defaultLocale);
	}

	@Override
	public void setFullReportCurrentLanguageId(java.lang.String languageId) {
		_watsonReportAudit.setFullReportCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized full reports of this watson report audit from the map of locales and localized full reports.
	*
	* @param fullReportMap the locales and localized full reports of this watson report audit
	*/
	@Override
	public void setFullReportMap(
		Map<java.util.Locale, java.lang.String> fullReportMap) {
		_watsonReportAudit.setFullReportMap(fullReportMap);
	}

	/**
	* Sets the localized full reports of this watson report audit from the map of locales and localized full reports, and sets the default locale.
	*
	* @param fullReportMap the locales and localized full reports of this watson report audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFullReportMap(
		Map<java.util.Locale, java.lang.String> fullReportMap,
		java.util.Locale defaultLocale) {
		_watsonReportAudit.setFullReportMap(fullReportMap, defaultLocale);
	}

	/**
	* Sets the group ID of this watson report audit.
	*
	* @param groupId the group ID of this watson report audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonReportAudit.setGroupId(groupId);
	}

	/**
	* Sets the image payload of this watson report audit.
	*
	* @param imagePayload the image payload of this watson report audit
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonReportAudit.setImagePayload(imagePayload);
	}

	/**
	* Sets the key of this watson report audit.
	*
	* @param key the key of this watson report audit
	*/
	@Override
	public void setKey(int key) {
		_watsonReportAudit.setKey(key);
	}

	/**
	* Sets the modified date of this watson report audit.
	*
	* @param modifiedDate the modified date of this watson report audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonReportAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson report audit.
	*
	* @param name the name of this watson report audit
	*/
	@Override
	public void setName(java.lang.String name) {
		_watsonReportAudit.setName(name);
	}

	/**
	* Sets the localized name of this watson report audit in the language.
	*
	* @param name the localized name of this watson report audit
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_watsonReportAudit.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson report audit in the language, and sets the default locale.
	*
	* @param name the localized name of this watson report audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonReportAudit.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_watsonReportAudit.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson report audit from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson report audit
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_watsonReportAudit.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson report audit from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson report audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonReportAudit.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonReportAudit.setNew(n);
	}

	/**
	* Sets the original watson report ID of this watson report audit.
	*
	* @param originalWatsonReportId the original watson report ID of this watson report audit
	*/
	@Override
	public void setOriginalWatsonReportId(long originalWatsonReportId) {
		_watsonReportAudit.setOriginalWatsonReportId(originalWatsonReportId);
	}

	/**
	* Sets the primary key of this watson report audit.
	*
	* @param primaryKey the primary key of this watson report audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonReportAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonReportAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report date of this watson report audit.
	*
	* @param reportDate the report date of this watson report audit
	*/
	@Override
	public void setReportDate(Date reportDate) {
		_watsonReportAudit.setReportDate(reportDate);
	}

	/**
	* Sets the reported user of this watson report audit.
	*
	* @param reportedUser the reported user of this watson report audit
	*/
	@Override
	public void setReportedUser(java.lang.String reportedUser) {
		_watsonReportAudit.setReportedUser(reportedUser);
	}

	/**
	* Sets the localized reported user of this watson report audit in the language.
	*
	* @param reportedUser the localized reported user of this watson report audit
	* @param locale the locale of the language
	*/
	@Override
	public void setReportedUser(java.lang.String reportedUser,
		java.util.Locale locale) {
		_watsonReportAudit.setReportedUser(reportedUser, locale);
	}

	/**
	* Sets the localized reported user of this watson report audit in the language, and sets the default locale.
	*
	* @param reportedUser the localized reported user of this watson report audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setReportedUser(java.lang.String reportedUser,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonReportAudit.setReportedUser(reportedUser, locale, defaultLocale);
	}

	@Override
	public void setReportedUserCurrentLanguageId(java.lang.String languageId) {
		_watsonReportAudit.setReportedUserCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized reported users of this watson report audit from the map of locales and localized reported users.
	*
	* @param reportedUserMap the locales and localized reported users of this watson report audit
	*/
	@Override
	public void setReportedUserMap(
		Map<java.util.Locale, java.lang.String> reportedUserMap) {
		_watsonReportAudit.setReportedUserMap(reportedUserMap);
	}

	/**
	* Sets the localized reported users of this watson report audit from the map of locales and localized reported users, and sets the default locale.
	*
	* @param reportedUserMap the locales and localized reported users of this watson report audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setReportedUserMap(
		Map<java.util.Locale, java.lang.String> reportedUserMap,
		java.util.Locale defaultLocale) {
		_watsonReportAudit.setReportedUserMap(reportedUserMap, defaultLocale);
	}

	/**
	* Sets the status of this watson report audit.
	*
	* @param status the status of this watson report audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonReportAudit.setStatus(status);
	}

	/**
	* Sets the time spent of this watson report audit.
	*
	* @param timeSpent the time spent of this watson report audit
	*/
	@Override
	public void setTimeSpent(java.lang.String timeSpent) {
		_watsonReportAudit.setTimeSpent(timeSpent);
	}

	/**
	* Sets the type watson list type ID of this watson report audit.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson report audit
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonReportAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson report audit.
	*
	* @param userId the user ID of this watson report audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonReportAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson report audit.
	*
	* @param userName the user name of this watson report audit
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonReportAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson report audit.
	*
	* @param userUuid the user uuid of this watson report audit
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonReportAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson child ID of this watson report audit.
	*
	* @param watsonChildId the watson child ID of this watson report audit
	*/
	@Override
	public void setWatsonChildId(long watsonChildId) {
		_watsonReportAudit.setWatsonChildId(watsonChildId);
	}

	/**
	* Sets the watson report audit ID of this watson report audit.
	*
	* @param watsonReportAuditId the watson report audit ID of this watson report audit
	*/
	@Override
	public void setWatsonReportAuditId(long watsonReportAuditId) {
		_watsonReportAudit.setWatsonReportAuditId(watsonReportAuditId);
	}

	/**
	* Sets the watson report ID of this watson report audit.
	*
	* @param watsonReportId the watson report ID of this watson report audit
	*/
	@Override
	public void setWatsonReportId(long watsonReportId) {
		_watsonReportAudit.setWatsonReportId(watsonReportId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonReportAudit> toCacheModel() {
		return _watsonReportAudit.toCacheModel();
	}

	@Override
	public WatsonReportAudit toEscapedModel() {
		return new WatsonReportAuditWrapper(_watsonReportAudit.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonReportAudit.toString();
	}

	@Override
	public WatsonReportAudit toUnescapedModel() {
		return new WatsonReportAuditWrapper(_watsonReportAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonReportAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonReportAuditWrapper)) {
			return false;
		}

		WatsonReportAuditWrapper watsonReportAuditWrapper = (WatsonReportAuditWrapper)obj;

		if (Objects.equals(_watsonReportAudit,
					watsonReportAuditWrapper._watsonReportAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonReportAudit getWrappedModel() {
		return _watsonReportAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonReportAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonReportAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonReportAudit.resetOriginalValues();
	}

	private final WatsonReportAudit _watsonReportAudit;
}