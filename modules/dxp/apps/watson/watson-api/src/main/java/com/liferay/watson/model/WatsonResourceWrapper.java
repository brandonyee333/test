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
 * This class is a wrapper for {@link WatsonResource}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonResource
 * @generated
 */
@ProviderType
public class WatsonResourceWrapper implements WatsonResource,
	ModelWrapper<WatsonResource> {
	public WatsonResourceWrapper(WatsonResource watsonResource) {
		_watsonResource = watsonResource;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonResource.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonResource.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonResourceId", getWatsonResourceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("originalWatsonResourceId", getOriginalWatsonResourceId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonResourceId = (Long)attributes.get("watsonResourceId");

		if (watsonResourceId != null) {
			setWatsonResourceId(watsonResourceId);
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
		return new WatsonResourceWrapper((WatsonResource)_watsonResource.clone());
	}

	@Override
	public int compareTo(WatsonResource watsonResource) {
		return _watsonResource.compareTo(watsonResource);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonResource.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson resource.
	*
	* @return the company ID of this watson resource
	*/
	@Override
	public long getCompanyId() {
		return _watsonResource.getCompanyId();
	}

	/**
	* Returns the create date of this watson resource.
	*
	* @return the create date of this watson resource
	*/
	@Override
	public Date getCreateDate() {
		return _watsonResource.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonResource.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson resource.
	*
	* @return the description of this watson resource
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonResource.getDescription();
	}

	/**
	* Returns the localized description of this watson resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson resource
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonResource.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonResource.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson resource
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonResource.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson resource
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonResource.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonResource.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonResource.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson resource.
	*
	* @return the locales and localized descriptions of this watson resource
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonResource.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonResource.getExpandoBridge();
	}

	/**
	* Returns the image payload of this watson resource.
	*
	* @return the image payload of this watson resource
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonResource.getImagePayload();
	}

	/**
	* Returns the modified date of this watson resource.
	*
	* @return the modified date of this watson resource
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonResource.getModifiedDate();
	}

	/**
	* Returns the name of this watson resource.
	*
	* @return the name of this watson resource
	*/
	@Override
	public java.lang.String getName() {
		return _watsonResource.getName();
	}

	/**
	* Returns the localized name of this watson resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson resource
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _watsonResource.getName(locale);
	}

	/**
	* Returns the localized name of this watson resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonResource.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson resource
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _watsonResource.getName(languageId);
	}

	/**
	* Returns the localized name of this watson resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson resource
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _watsonResource.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _watsonResource.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _watsonResource.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson resource.
	*
	* @return the locales and localized names of this watson resource
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _watsonResource.getNameMap();
	}

	/**
	* Returns the original watson resource ID of this watson resource.
	*
	* @return the original watson resource ID of this watson resource
	*/
	@Override
	public long getOriginalWatsonResourceId() {
		return _watsonResource.getOriginalWatsonResourceId();
	}

	/**
	* Returns the primary key of this watson resource.
	*
	* @return the primary key of this watson resource
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonResource.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonResource.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson resource.
	*
	* @return the status of this watson resource
	*/
	@Override
	public int getStatus() {
		return _watsonResource.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson resource.
	*
	* @return the type watson list type ID of this watson resource
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonResource.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson resource.
	*
	* @return the user ID of this watson resource
	*/
	@Override
	public long getUserId() {
		return _watsonResource.getUserId();
	}

	/**
	* Returns the user name of this watson resource.
	*
	* @return the user name of this watson resource
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonResource.getUserName();
	}

	/**
	* Returns the user uuid of this watson resource.
	*
	* @return the user uuid of this watson resource
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonResource.getUserUuid();
	}

	/**
	* Returns the watson incident ID of this watson resource.
	*
	* @return the watson incident ID of this watson resource
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonResource.getWatsonIncidentId();
	}

	/**
	* Returns the watson resource ID of this watson resource.
	*
	* @return the watson resource ID of this watson resource
	*/
	@Override
	public long getWatsonResourceId() {
		return _watsonResource.getWatsonResourceId();
	}

	@Override
	public int hashCode() {
		return _watsonResource.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonResource.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonResource.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonResource.isNew();
	}

	@Override
	public void persist() {
		_watsonResource.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonResource.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonResource.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonResource.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson resource.
	*
	* @param companyId the company ID of this watson resource
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonResource.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson resource.
	*
	* @param createDate the create date of this watson resource
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonResource.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson resource.
	*
	* @param description the description of this watson resource
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonResource.setDescription(description);
	}

	/**
	* Sets the localized description of this watson resource in the language.
	*
	* @param description the localized description of this watson resource
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonResource.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson resource in the language, and sets the default locale.
	*
	* @param description the localized description of this watson resource
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonResource.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonResource.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson resource from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson resource
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonResource.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson resource from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson resource
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonResource.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonResource.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonResource.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonResource.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the image payload of this watson resource.
	*
	* @param imagePayload the image payload of this watson resource
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonResource.setImagePayload(imagePayload);
	}

	/**
	* Sets the modified date of this watson resource.
	*
	* @param modifiedDate the modified date of this watson resource
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonResource.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson resource.
	*
	* @param name the name of this watson resource
	*/
	@Override
	public void setName(java.lang.String name) {
		_watsonResource.setName(name);
	}

	/**
	* Sets the localized name of this watson resource in the language.
	*
	* @param name the localized name of this watson resource
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_watsonResource.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson resource in the language, and sets the default locale.
	*
	* @param name the localized name of this watson resource
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonResource.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_watsonResource.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson resource from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson resource
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_watsonResource.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson resource from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson resource
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonResource.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonResource.setNew(n);
	}

	/**
	* Sets the original watson resource ID of this watson resource.
	*
	* @param originalWatsonResourceId the original watson resource ID of this watson resource
	*/
	@Override
	public void setOriginalWatsonResourceId(long originalWatsonResourceId) {
		_watsonResource.setOriginalWatsonResourceId(originalWatsonResourceId);
	}

	/**
	* Sets the primary key of this watson resource.
	*
	* @param primaryKey the primary key of this watson resource
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonResource.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonResource.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson resource.
	*
	* @param status the status of this watson resource
	*/
	@Override
	public void setStatus(int status) {
		_watsonResource.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson resource.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson resource
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonResource.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson resource.
	*
	* @param userId the user ID of this watson resource
	*/
	@Override
	public void setUserId(long userId) {
		_watsonResource.setUserId(userId);
	}

	/**
	* Sets the user name of this watson resource.
	*
	* @param userName the user name of this watson resource
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonResource.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson resource.
	*
	* @param userUuid the user uuid of this watson resource
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonResource.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident ID of this watson resource.
	*
	* @param watsonIncidentId the watson incident ID of this watson resource
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonResource.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	* Sets the watson resource ID of this watson resource.
	*
	* @param watsonResourceId the watson resource ID of this watson resource
	*/
	@Override
	public void setWatsonResourceId(long watsonResourceId) {
		_watsonResource.setWatsonResourceId(watsonResourceId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonResource> toCacheModel() {
		return _watsonResource.toCacheModel();
	}

	@Override
	public WatsonResource toEscapedModel() {
		return new WatsonResourceWrapper(_watsonResource.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonResource.toString();
	}

	@Override
	public WatsonResource toUnescapedModel() {
		return new WatsonResourceWrapper(_watsonResource.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonResource.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonResourceWrapper)) {
			return false;
		}

		WatsonResourceWrapper watsonResourceWrapper = (WatsonResourceWrapper)obj;

		if (Objects.equals(_watsonResource,
					watsonResourceWrapper._watsonResource)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonResource getWrappedModel() {
		return _watsonResource;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonResource.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonResource.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonResource.resetOriginalValues();
	}

	private final WatsonResource _watsonResource;
}