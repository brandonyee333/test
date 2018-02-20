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
 * This class is a wrapper for {@link WatsonResourceAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonResourceAudit
 * @generated
 */
@ProviderType
public class WatsonResourceAuditWrapper implements WatsonResourceAudit,
	ModelWrapper<WatsonResourceAudit> {
	public WatsonResourceAuditWrapper(WatsonResourceAudit watsonResourceAudit) {
		_watsonResourceAudit = watsonResourceAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonResourceAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonResourceAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonResourceAuditId", getWatsonResourceAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("originalWatsonResourceId", getOriginalWatsonResourceId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("watsonResourceId", getWatsonResourceId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonResourceAuditId = (Long)attributes.get(
				"watsonResourceAuditId");

		if (watsonResourceAuditId != null) {
			setWatsonResourceAuditId(watsonResourceAuditId);
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

		Long originalWatsonResourceId = (Long)attributes.get(
				"originalWatsonResourceId");

		if (originalWatsonResourceId != null) {
			setOriginalWatsonResourceId(originalWatsonResourceId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Long watsonResourceId = (Long)attributes.get("watsonResourceId");

		if (watsonResourceId != null) {
			setWatsonResourceId(watsonResourceId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonResourceAuditWrapper((WatsonResourceAudit)_watsonResourceAudit.clone());
	}

	@Override
	public int compareTo(WatsonResourceAudit watsonResourceAudit) {
		return _watsonResourceAudit.compareTo(watsonResourceAudit);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonResourceAudit.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson resource audit.
	*
	* @return the company ID of this watson resource audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonResourceAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson resource audit.
	*
	* @return the create date of this watson resource audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonResourceAudit.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonResourceAudit.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson resource audit.
	*
	* @return the description of this watson resource audit
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonResourceAudit.getDescription();
	}

	/**
	* Returns the localized description of this watson resource audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson resource audit
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonResourceAudit.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson resource audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson resource audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonResourceAudit.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson resource audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson resource audit
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonResourceAudit.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson resource audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson resource audit
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonResourceAudit.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonResourceAudit.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonResourceAudit.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson resource audit.
	*
	* @return the locales and localized descriptions of this watson resource audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonResourceAudit.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonResourceAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson resource audit.
	*
	* @return the group ID of this watson resource audit
	*/
	@Override
	public long getGroupId() {
		return _watsonResourceAudit.getGroupId();
	}

	/**
	* Returns the image payload of this watson resource audit.
	*
	* @return the image payload of this watson resource audit
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonResourceAudit.getImagePayload();
	}

	/**
	* Returns the modified date of this watson resource audit.
	*
	* @return the modified date of this watson resource audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonResourceAudit.getModifiedDate();
	}

	/**
	* Returns the name of this watson resource audit.
	*
	* @return the name of this watson resource audit
	*/
	@Override
	public java.lang.String getName() {
		return _watsonResourceAudit.getName();
	}

	/**
	* Returns the localized name of this watson resource audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson resource audit
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _watsonResourceAudit.getName(locale);
	}

	/**
	* Returns the localized name of this watson resource audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson resource audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonResourceAudit.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson resource audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson resource audit
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _watsonResourceAudit.getName(languageId);
	}

	/**
	* Returns the localized name of this watson resource audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson resource audit
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _watsonResourceAudit.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _watsonResourceAudit.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _watsonResourceAudit.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson resource audit.
	*
	* @return the locales and localized names of this watson resource audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _watsonResourceAudit.getNameMap();
	}

	/**
	* Returns the original watson resource ID of this watson resource audit.
	*
	* @return the original watson resource ID of this watson resource audit
	*/
	@Override
	public long getOriginalWatsonResourceId() {
		return _watsonResourceAudit.getOriginalWatsonResourceId();
	}

	/**
	* Returns the primary key of this watson resource audit.
	*
	* @return the primary key of this watson resource audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonResourceAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonResourceAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson resource audit.
	*
	* @return the status of this watson resource audit
	*/
	@Override
	public int getStatus() {
		return _watsonResourceAudit.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson resource audit.
	*
	* @return the type watson list type ID of this watson resource audit
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonResourceAudit.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson resource audit.
	*
	* @return the user ID of this watson resource audit
	*/
	@Override
	public long getUserId() {
		return _watsonResourceAudit.getUserId();
	}

	/**
	* Returns the user name of this watson resource audit.
	*
	* @return the user name of this watson resource audit
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonResourceAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson resource audit.
	*
	* @return the user uuid of this watson resource audit
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonResourceAudit.getUserUuid();
	}

	/**
	* Returns the watson incident ID of this watson resource audit.
	*
	* @return the watson incident ID of this watson resource audit
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonResourceAudit.getWatsonIncidentId();
	}

	/**
	* Returns the watson resource audit ID of this watson resource audit.
	*
	* @return the watson resource audit ID of this watson resource audit
	*/
	@Override
	public long getWatsonResourceAuditId() {
		return _watsonResourceAudit.getWatsonResourceAuditId();
	}

	/**
	* Returns the watson resource ID of this watson resource audit.
	*
	* @return the watson resource ID of this watson resource audit
	*/
	@Override
	public long getWatsonResourceId() {
		return _watsonResourceAudit.getWatsonResourceId();
	}

	@Override
	public int hashCode() {
		return _watsonResourceAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonResourceAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonResourceAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonResourceAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonResourceAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonResourceAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonResourceAudit.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonResourceAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson resource audit.
	*
	* @param companyId the company ID of this watson resource audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonResourceAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson resource audit.
	*
	* @param createDate the create date of this watson resource audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonResourceAudit.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson resource audit.
	*
	* @param description the description of this watson resource audit
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonResourceAudit.setDescription(description);
	}

	/**
	* Sets the localized description of this watson resource audit in the language.
	*
	* @param description the localized description of this watson resource audit
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonResourceAudit.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson resource audit in the language, and sets the default locale.
	*
	* @param description the localized description of this watson resource audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonResourceAudit.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonResourceAudit.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson resource audit from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson resource audit
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonResourceAudit.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson resource audit from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson resource audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonResourceAudit.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonResourceAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonResourceAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonResourceAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson resource audit.
	*
	* @param groupId the group ID of this watson resource audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonResourceAudit.setGroupId(groupId);
	}

	/**
	* Sets the image payload of this watson resource audit.
	*
	* @param imagePayload the image payload of this watson resource audit
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonResourceAudit.setImagePayload(imagePayload);
	}

	/**
	* Sets the modified date of this watson resource audit.
	*
	* @param modifiedDate the modified date of this watson resource audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonResourceAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson resource audit.
	*
	* @param name the name of this watson resource audit
	*/
	@Override
	public void setName(java.lang.String name) {
		_watsonResourceAudit.setName(name);
	}

	/**
	* Sets the localized name of this watson resource audit in the language.
	*
	* @param name the localized name of this watson resource audit
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_watsonResourceAudit.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson resource audit in the language, and sets the default locale.
	*
	* @param name the localized name of this watson resource audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonResourceAudit.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_watsonResourceAudit.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson resource audit from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson resource audit
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_watsonResourceAudit.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson resource audit from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson resource audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonResourceAudit.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonResourceAudit.setNew(n);
	}

	/**
	* Sets the original watson resource ID of this watson resource audit.
	*
	* @param originalWatsonResourceId the original watson resource ID of this watson resource audit
	*/
	@Override
	public void setOriginalWatsonResourceId(long originalWatsonResourceId) {
		_watsonResourceAudit.setOriginalWatsonResourceId(originalWatsonResourceId);
	}

	/**
	* Sets the primary key of this watson resource audit.
	*
	* @param primaryKey the primary key of this watson resource audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonResourceAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonResourceAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson resource audit.
	*
	* @param status the status of this watson resource audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonResourceAudit.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson resource audit.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson resource audit
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonResourceAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson resource audit.
	*
	* @param userId the user ID of this watson resource audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonResourceAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson resource audit.
	*
	* @param userName the user name of this watson resource audit
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonResourceAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson resource audit.
	*
	* @param userUuid the user uuid of this watson resource audit
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonResourceAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident ID of this watson resource audit.
	*
	* @param watsonIncidentId the watson incident ID of this watson resource audit
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonResourceAudit.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	* Sets the watson resource audit ID of this watson resource audit.
	*
	* @param watsonResourceAuditId the watson resource audit ID of this watson resource audit
	*/
	@Override
	public void setWatsonResourceAuditId(long watsonResourceAuditId) {
		_watsonResourceAudit.setWatsonResourceAuditId(watsonResourceAuditId);
	}

	/**
	* Sets the watson resource ID of this watson resource audit.
	*
	* @param watsonResourceId the watson resource ID of this watson resource audit
	*/
	@Override
	public void setWatsonResourceId(long watsonResourceId) {
		_watsonResourceAudit.setWatsonResourceId(watsonResourceId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonResourceAudit> toCacheModel() {
		return _watsonResourceAudit.toCacheModel();
	}

	@Override
	public WatsonResourceAudit toEscapedModel() {
		return new WatsonResourceAuditWrapper(_watsonResourceAudit.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonResourceAudit.toString();
	}

	@Override
	public WatsonResourceAudit toUnescapedModel() {
		return new WatsonResourceAuditWrapper(_watsonResourceAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonResourceAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonResourceAuditWrapper)) {
			return false;
		}

		WatsonResourceAuditWrapper watsonResourceAuditWrapper = (WatsonResourceAuditWrapper)obj;

		if (Objects.equals(_watsonResourceAudit,
					watsonResourceAuditWrapper._watsonResourceAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonResourceAudit getWrappedModel() {
		return _watsonResourceAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonResourceAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonResourceAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonResourceAudit.resetOriginalValues();
	}

	private final WatsonResourceAudit _watsonResourceAudit;
}