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
 * This class is a wrapper for {@link WatsonListTypeRel}.
 * </p>
 *
 * @author Eddie Olson
 * @see WatsonListTypeRel
 * @generated
 */
@ProviderType
public class WatsonListTypeRelWrapper implements WatsonListTypeRel,
	ModelWrapper<WatsonListTypeRel> {
	public WatsonListTypeRelWrapper(WatsonListTypeRel watsonListTypeRel) {
		_watsonListTypeRel = watsonListTypeRel;
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
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonListTypeRelWrapper((WatsonListTypeRel)_watsonListTypeRel.clone());
	}

	@Override
	public int compareTo(WatsonListTypeRel watsonListTypeRel) {
		return _watsonListTypeRel.compareTo(watsonListTypeRel);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonListTypeRel.getAvailableLanguageIds();
	}

	/**
	* Returns the fully qualified class name of this watson list type rel.
	*
	* @return the fully qualified class name of this watson list type rel
	*/
	@Override
	public java.lang.String getClassName() {
		return _watsonListTypeRel.getClassName();
	}

	/**
	* Returns the class name ID of this watson list type rel.
	*
	* @return the class name ID of this watson list type rel
	*/
	@Override
	public long getClassNameId() {
		return _watsonListTypeRel.getClassNameId();
	}

	/**
	* Returns the class pk of this watson list type rel.
	*
	* @return the class pk of this watson list type rel
	*/
	@Override
	public long getClassPK() {
		return _watsonListTypeRel.getClassPK();
	}

	/**
	* Returns the company ID of this watson list type rel.
	*
	* @return the company ID of this watson list type rel
	*/
	@Override
	public long getCompanyId() {
		return _watsonListTypeRel.getCompanyId();
	}

	/**
	* Returns the create date of this watson list type rel.
	*
	* @return the create date of this watson list type rel
	*/
	@Override
	public Date getCreateDate() {
		return _watsonListTypeRel.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonListTypeRel.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonListTypeRel.getExpandoBridge();
	}

	/**
	* Returns the modified date of this watson list type rel.
	*
	* @return the modified date of this watson list type rel
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonListTypeRel.getModifiedDate();
	}

	/**
	* Returns the primary of this watson list type rel.
	*
	* @return the primary of this watson list type rel
	*/
	@Override
	public boolean getPrimary() {
		return _watsonListTypeRel.getPrimary();
	}

	/**
	* Returns the primary key of this watson list type rel.
	*
	* @return the primary key of this watson list type rel
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonListTypeRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonListTypeRel.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson list type rel.
	*
	* @return the status of this watson list type rel
	*/
	@Override
	public int getStatus() {
		return _watsonListTypeRel.getStatus();
	}

	/**
	* Returns the type of this watson list type rel.
	*
	* @return the type of this watson list type rel
	*/
	@Override
	public java.lang.String getType() {
		return _watsonListTypeRel.getType();
	}

	/**
	* Returns the user ID of this watson list type rel.
	*
	* @return the user ID of this watson list type rel
	*/
	@Override
	public long getUserId() {
		return _watsonListTypeRel.getUserId();
	}

	/**
	* Returns the user name of this watson list type rel.
	*
	* @return the user name of this watson list type rel
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonListTypeRel.getUserName();
	}

	/**
	* Returns the user uuid of this watson list type rel.
	*
	* @return the user uuid of this watson list type rel
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonListTypeRel.getUserUuid();
	}

	/**
	* Returns the value of this watson list type rel.
	*
	* @return the value of this watson list type rel
	*/
	@Override
	public java.lang.String getValue() {
		return _watsonListTypeRel.getValue();
	}

	/**
	* Returns the localized value of this watson list type rel in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized value of this watson list type rel
	*/
	@Override
	public java.lang.String getValue(java.util.Locale locale) {
		return _watsonListTypeRel.getValue(locale);
	}

	/**
	* Returns the localized value of this watson list type rel in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized value of this watson list type rel. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getValue(java.util.Locale locale, boolean useDefault) {
		return _watsonListTypeRel.getValue(locale, useDefault);
	}

	/**
	* Returns the localized value of this watson list type rel in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized value of this watson list type rel
	*/
	@Override
	public java.lang.String getValue(java.lang.String languageId) {
		return _watsonListTypeRel.getValue(languageId);
	}

	/**
	* Returns the localized value of this watson list type rel in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized value of this watson list type rel
	*/
	@Override
	public java.lang.String getValue(java.lang.String languageId,
		boolean useDefault) {
		return _watsonListTypeRel.getValue(languageId, useDefault);
	}

	@Override
	public java.lang.String getValueCurrentLanguageId() {
		return _watsonListTypeRel.getValueCurrentLanguageId();
	}

	@Override
	public java.lang.String getValueCurrentValue() {
		return _watsonListTypeRel.getValueCurrentValue();
	}

	/**
	* Returns a map of the locales and localized values of this watson list type rel.
	*
	* @return the locales and localized values of this watson list type rel
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getValueMap() {
		return _watsonListTypeRel.getValueMap();
	}

	/**
	* Returns the watson list type ID of this watson list type rel.
	*
	* @return the watson list type ID of this watson list type rel
	*/
	@Override
	public long getWatsonListTypeId() {
		return _watsonListTypeRel.getWatsonListTypeId();
	}

	/**
	* Returns the watson list type rel ID of this watson list type rel.
	*
	* @return the watson list type rel ID of this watson list type rel
	*/
	@Override
	public long getWatsonListTypeRelId() {
		return _watsonListTypeRel.getWatsonListTypeRelId();
	}

	@Override
	public int hashCode() {
		return _watsonListTypeRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonListTypeRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonListTypeRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonListTypeRel.isNew();
	}

	/**
	* Returns <code>true</code> if this watson list type rel is primary.
	*
	* @return <code>true</code> if this watson list type rel is primary; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrimary() {
		return _watsonListTypeRel.isPrimary();
	}

	@Override
	public void persist() {
		_watsonListTypeRel.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonListTypeRel.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonListTypeRel.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonListTypeRel.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_watsonListTypeRel.setClassName(className);
	}

	/**
	* Sets the class name ID of this watson list type rel.
	*
	* @param classNameId the class name ID of this watson list type rel
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_watsonListTypeRel.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this watson list type rel.
	*
	* @param classPK the class pk of this watson list type rel
	*/
	@Override
	public void setClassPK(long classPK) {
		_watsonListTypeRel.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this watson list type rel.
	*
	* @param companyId the company ID of this watson list type rel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonListTypeRel.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson list type rel.
	*
	* @param createDate the create date of this watson list type rel
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonListTypeRel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonListTypeRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonListTypeRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonListTypeRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this watson list type rel.
	*
	* @param modifiedDate the modified date of this watson list type rel
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonListTypeRel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonListTypeRel.setNew(n);
	}

	/**
	* Sets whether this watson list type rel is primary.
	*
	* @param primary the primary of this watson list type rel
	*/
	@Override
	public void setPrimary(boolean primary) {
		_watsonListTypeRel.setPrimary(primary);
	}

	/**
	* Sets the primary key of this watson list type rel.
	*
	* @param primaryKey the primary key of this watson list type rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonListTypeRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonListTypeRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson list type rel.
	*
	* @param status the status of this watson list type rel
	*/
	@Override
	public void setStatus(int status) {
		_watsonListTypeRel.setStatus(status);
	}

	/**
	* Sets the type of this watson list type rel.
	*
	* @param type the type of this watson list type rel
	*/
	@Override
	public void setType(java.lang.String type) {
		_watsonListTypeRel.setType(type);
	}

	/**
	* Sets the user ID of this watson list type rel.
	*
	* @param userId the user ID of this watson list type rel
	*/
	@Override
	public void setUserId(long userId) {
		_watsonListTypeRel.setUserId(userId);
	}

	/**
	* Sets the user name of this watson list type rel.
	*
	* @param userName the user name of this watson list type rel
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonListTypeRel.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson list type rel.
	*
	* @param userUuid the user uuid of this watson list type rel
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonListTypeRel.setUserUuid(userUuid);
	}

	/**
	* Sets the value of this watson list type rel.
	*
	* @param value the value of this watson list type rel
	*/
	@Override
	public void setValue(java.lang.String value) {
		_watsonListTypeRel.setValue(value);
	}

	/**
	* Sets the localized value of this watson list type rel in the language.
	*
	* @param value the localized value of this watson list type rel
	* @param locale the locale of the language
	*/
	@Override
	public void setValue(java.lang.String value, java.util.Locale locale) {
		_watsonListTypeRel.setValue(value, locale);
	}

	/**
	* Sets the localized value of this watson list type rel in the language, and sets the default locale.
	*
	* @param value the localized value of this watson list type rel
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setValue(java.lang.String value, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonListTypeRel.setValue(value, locale, defaultLocale);
	}

	@Override
	public void setValueCurrentLanguageId(java.lang.String languageId) {
		_watsonListTypeRel.setValueCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized values of this watson list type rel from the map of locales and localized values.
	*
	* @param valueMap the locales and localized values of this watson list type rel
	*/
	@Override
	public void setValueMap(Map<java.util.Locale, java.lang.String> valueMap) {
		_watsonListTypeRel.setValueMap(valueMap);
	}

	/**
	* Sets the localized values of this watson list type rel from the map of locales and localized values, and sets the default locale.
	*
	* @param valueMap the locales and localized values of this watson list type rel
	* @param defaultLocale the default locale
	*/
	@Override
	public void setValueMap(Map<java.util.Locale, java.lang.String> valueMap,
		java.util.Locale defaultLocale) {
		_watsonListTypeRel.setValueMap(valueMap, defaultLocale);
	}

	/**
	* Sets the watson list type ID of this watson list type rel.
	*
	* @param watsonListTypeId the watson list type ID of this watson list type rel
	*/
	@Override
	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeRel.setWatsonListTypeId(watsonListTypeId);
	}

	/**
	* Sets the watson list type rel ID of this watson list type rel.
	*
	* @param watsonListTypeRelId the watson list type rel ID of this watson list type rel
	*/
	@Override
	public void setWatsonListTypeRelId(long watsonListTypeRelId) {
		_watsonListTypeRel.setWatsonListTypeRelId(watsonListTypeRelId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonListTypeRel> toCacheModel() {
		return _watsonListTypeRel.toCacheModel();
	}

	@Override
	public WatsonListTypeRel toEscapedModel() {
		return new WatsonListTypeRelWrapper(_watsonListTypeRel.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonListTypeRel.toString();
	}

	@Override
	public WatsonListTypeRel toUnescapedModel() {
		return new WatsonListTypeRelWrapper(_watsonListTypeRel.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonListTypeRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeRelWrapper)) {
			return false;
		}

		WatsonListTypeRelWrapper watsonListTypeRelWrapper = (WatsonListTypeRelWrapper)obj;

		if (Objects.equals(_watsonListTypeRel,
					watsonListTypeRelWrapper._watsonListTypeRel)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonListTypeRel getWrappedModel() {
		return _watsonListTypeRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonListTypeRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonListTypeRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonListTypeRel.resetOriginalValues();
	}

	private final WatsonListTypeRel _watsonListTypeRel;
}