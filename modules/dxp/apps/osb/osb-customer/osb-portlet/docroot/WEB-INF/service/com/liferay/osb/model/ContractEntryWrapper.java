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
 * This class is a wrapper for {@link ContractEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntry
 * @generated
 */
@ProviderType
public class ContractEntryWrapper implements ContractEntry,
	ModelWrapper<ContractEntry> {
	public ContractEntryWrapper(ContractEntry contractEntry) {
		_contractEntry = contractEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return ContractEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ContractEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractEntryId", getContractEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("version", getVersion());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contractEntryId = (Long)attributes.get("contractEntryId");

		if (contractEntryId != null) {
			setContractEntryId(contractEntryId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	@Override
	public ContractEntry toEscapedModel() {
		return new ContractEntryWrapper(_contractEntry.toEscapedModel());
	}

	@Override
	public ContractEntry toUnescapedModel() {
		return new ContractEntryWrapper(_contractEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _contractEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contractEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contractEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contractEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContractEntry> toCacheModel() {
		return _contractEntry.toCacheModel();
	}

	@Override
	public int compareTo(ContractEntry contractEntry) {
		return _contractEntry.compareTo(contractEntry);
	}

	/**
	* Returns the type of this contract entry.
	*
	* @return the type of this contract entry
	*/
	@Override
	public int getType() {
		return _contractEntry.getType();
	}

	/**
	* Returns the version of this contract entry.
	*
	* @return the version of this contract entry
	*/
	@Override
	public int getVersion() {
		return _contractEntry.getVersion();
	}

	@Override
	public int hashCode() {
		return _contractEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contractEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ContractEntryWrapper((ContractEntry)_contractEntry.clone());
	}

	/**
	* Returns the fully qualified class name of this contract entry.
	*
	* @return the fully qualified class name of this contract entry
	*/
	@Override
	public java.lang.String getClassName() {
		return _contractEntry.getClassName();
	}

	/**
	* Returns the content of this contract entry.
	*
	* @return the content of this contract entry
	*/
	@Override
	public java.lang.String getContent() {
		return _contractEntry.getContent();
	}

	/**
	* Returns the localized content of this contract entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized content of this contract entry
	*/
	@Override
	public java.lang.String getContent(java.lang.String languageId) {
		return _contractEntry.getContent(languageId);
	}

	/**
	* Returns the localized content of this contract entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized content of this contract entry
	*/
	@Override
	public java.lang.String getContent(java.lang.String languageId,
		boolean useDefault) {
		return _contractEntry.getContent(languageId, useDefault);
	}

	/**
	* Returns the localized content of this contract entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized content of this contract entry
	*/
	@Override
	public java.lang.String getContent(java.util.Locale locale) {
		return _contractEntry.getContent(locale);
	}

	/**
	* Returns the localized content of this contract entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized content of this contract entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getContent(java.util.Locale locale,
		boolean useDefault) {
		return _contractEntry.getContent(locale, useDefault);
	}

	@Override
	public java.lang.String getContentCurrentLanguageId() {
		return _contractEntry.getContentCurrentLanguageId();
	}

	@Override
	public java.lang.String getContentCurrentValue() {
		return _contractEntry.getContentCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _contractEntry.getDefaultLanguageId();
	}

	/**
	* Returns the user name of this contract entry.
	*
	* @return the user name of this contract entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _contractEntry.getUserName();
	}

	/**
	* Returns the user uuid of this contract entry.
	*
	* @return the user uuid of this contract entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _contractEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _contractEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _contractEntry.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _contractEntry.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this contract entry.
	*
	* @return the create date of this contract entry
	*/
	@Override
	public Date getCreateDate() {
		return _contractEntry.getCreateDate();
	}

	/**
	* Returns a map of the locales and localized contents of this contract entry.
	*
	* @return the locales and localized contents of this contract entry
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getContentMap() {
		return _contractEntry.getContentMap();
	}

	/**
	* Returns the class name ID of this contract entry.
	*
	* @return the class name ID of this contract entry
	*/
	@Override
	public long getClassNameId() {
		return _contractEntry.getClassNameId();
	}

	/**
	* Returns the class pk of this contract entry.
	*
	* @return the class pk of this contract entry
	*/
	@Override
	public long getClassPK() {
		return _contractEntry.getClassPK();
	}

	/**
	* Returns the contract entry ID of this contract entry.
	*
	* @return the contract entry ID of this contract entry
	*/
	@Override
	public long getContractEntryId() {
		return _contractEntry.getContractEntryId();
	}

	/**
	* Returns the primary key of this contract entry.
	*
	* @return the primary key of this contract entry
	*/
	@Override
	public long getPrimaryKey() {
		return _contractEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this contract entry.
	*
	* @return the user ID of this contract entry
	*/
	@Override
	public long getUserId() {
		return _contractEntry.getUserId();
	}

	@Override
	public void persist() {
		_contractEntry.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_contractEntry.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_contractEntry.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contractEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_contractEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this contract entry.
	*
	* @param classNameId the class name ID of this contract entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_contractEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this contract entry.
	*
	* @param classPK the class pk of this contract entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_contractEntry.setClassPK(classPK);
	}

	/**
	* Sets the content of this contract entry.
	*
	* @param content the content of this contract entry
	*/
	@Override
	public void setContent(java.lang.String content) {
		_contractEntry.setContent(content);
	}

	/**
	* Sets the localized content of this contract entry in the language.
	*
	* @param content the localized content of this contract entry
	* @param locale the locale of the language
	*/
	@Override
	public void setContent(java.lang.String content, java.util.Locale locale) {
		_contractEntry.setContent(content, locale);
	}

	/**
	* Sets the localized content of this contract entry in the language, and sets the default locale.
	*
	* @param content the localized content of this contract entry
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContent(java.lang.String content, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_contractEntry.setContent(content, locale, defaultLocale);
	}

	@Override
	public void setContentCurrentLanguageId(java.lang.String languageId) {
		_contractEntry.setContentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized contents of this contract entry from the map of locales and localized contents.
	*
	* @param contentMap the locales and localized contents of this contract entry
	*/
	@Override
	public void setContentMap(
		Map<java.util.Locale, java.lang.String> contentMap) {
		_contractEntry.setContentMap(contentMap);
	}

	/**
	* Sets the localized contents of this contract entry from the map of locales and localized contents, and sets the default locale.
	*
	* @param contentMap the locales and localized contents of this contract entry
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContentMap(
		Map<java.util.Locale, java.lang.String> contentMap,
		java.util.Locale defaultLocale) {
		_contractEntry.setContentMap(contentMap, defaultLocale);
	}

	/**
	* Sets the contract entry ID of this contract entry.
	*
	* @param contractEntryId the contract entry ID of this contract entry
	*/
	@Override
	public void setContractEntryId(long contractEntryId) {
		_contractEntry.setContractEntryId(contractEntryId);
	}

	/**
	* Sets the create date of this contract entry.
	*
	* @param createDate the create date of this contract entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_contractEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contractEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contractEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contractEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_contractEntry.setNew(n);
	}

	/**
	* Sets the primary key of this contract entry.
	*
	* @param primaryKey the primary key of this contract entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contractEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contractEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this contract entry.
	*
	* @param type the type of this contract entry
	*/
	@Override
	public void setType(int type) {
		_contractEntry.setType(type);
	}

	/**
	* Sets the user ID of this contract entry.
	*
	* @param userId the user ID of this contract entry
	*/
	@Override
	public void setUserId(long userId) {
		_contractEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this contract entry.
	*
	* @param userName the user name of this contract entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_contractEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this contract entry.
	*
	* @param userUuid the user uuid of this contract entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_contractEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the version of this contract entry.
	*
	* @param version the version of this contract entry
	*/
	@Override
	public void setVersion(int version) {
		_contractEntry.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractEntryWrapper)) {
			return false;
		}

		ContractEntryWrapper contractEntryWrapper = (ContractEntryWrapper)obj;

		if (Objects.equals(_contractEntry, contractEntryWrapper._contractEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public ContractEntry getWrappedModel() {
		return _contractEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contractEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contractEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contractEntry.resetOriginalValues();
	}

	private final ContractEntry _contractEntry;
}