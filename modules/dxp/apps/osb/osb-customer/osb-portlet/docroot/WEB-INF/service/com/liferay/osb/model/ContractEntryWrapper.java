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
 * This class is a wrapper for {@link ContractEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContractEntry
 * @generated
 */
public class ContractEntryWrapper implements ContractEntry,
	ModelWrapper<ContractEntry> {
	public ContractEntryWrapper(ContractEntry contractEntry) {
		_contractEntry = contractEntry;
	}

	public Class<?> getModelClass() {
		return ContractEntry.class;
	}

	public String getModelClassName() {
		return ContractEntry.class.getName();
	}

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

	/**
	* Returns the primary key of this contract entry.
	*
	* @return the primary key of this contract entry
	*/
	public long getPrimaryKey() {
		return _contractEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this contract entry.
	*
	* @param primaryKey the primary key of this contract entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_contractEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the contract entry ID of this contract entry.
	*
	* @return the contract entry ID of this contract entry
	*/
	public long getContractEntryId() {
		return _contractEntry.getContractEntryId();
	}

	/**
	* Sets the contract entry ID of this contract entry.
	*
	* @param contractEntryId the contract entry ID of this contract entry
	*/
	public void setContractEntryId(long contractEntryId) {
		_contractEntry.setContractEntryId(contractEntryId);
	}

	/**
	* Returns the user ID of this contract entry.
	*
	* @return the user ID of this contract entry
	*/
	public long getUserId() {
		return _contractEntry.getUserId();
	}

	/**
	* Sets the user ID of this contract entry.
	*
	* @param userId the user ID of this contract entry
	*/
	public void setUserId(long userId) {
		_contractEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this contract entry.
	*
	* @return the user uuid of this contract entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this contract entry.
	*
	* @param userUuid the user uuid of this contract entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_contractEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this contract entry.
	*
	* @return the user name of this contract entry
	*/
	public java.lang.String getUserName() {
		return _contractEntry.getUserName();
	}

	/**
	* Sets the user name of this contract entry.
	*
	* @param userName the user name of this contract entry
	*/
	public void setUserName(java.lang.String userName) {
		_contractEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this contract entry.
	*
	* @return the create date of this contract entry
	*/
	public java.util.Date getCreateDate() {
		return _contractEntry.getCreateDate();
	}

	/**
	* Sets the create date of this contract entry.
	*
	* @param createDate the create date of this contract entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_contractEntry.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this contract entry.
	*
	* @return the fully qualified class name of this contract entry
	*/
	public java.lang.String getClassName() {
		return _contractEntry.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_contractEntry.setClassName(className);
	}

	/**
	* Returns the class name ID of this contract entry.
	*
	* @return the class name ID of this contract entry
	*/
	public long getClassNameId() {
		return _contractEntry.getClassNameId();
	}

	/**
	* Sets the class name ID of this contract entry.
	*
	* @param classNameId the class name ID of this contract entry
	*/
	public void setClassNameId(long classNameId) {
		_contractEntry.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this contract entry.
	*
	* @return the class p k of this contract entry
	*/
	public long getClassPK() {
		return _contractEntry.getClassPK();
	}

	/**
	* Sets the class p k of this contract entry.
	*
	* @param classPK the class p k of this contract entry
	*/
	public void setClassPK(long classPK) {
		_contractEntry.setClassPK(classPK);
	}

	/**
	* Returns the type of this contract entry.
	*
	* @return the type of this contract entry
	*/
	public int getType() {
		return _contractEntry.getType();
	}

	/**
	* Sets the type of this contract entry.
	*
	* @param type the type of this contract entry
	*/
	public void setType(int type) {
		_contractEntry.setType(type);
	}

	/**
	* Returns the version of this contract entry.
	*
	* @return the version of this contract entry
	*/
	public int getVersion() {
		return _contractEntry.getVersion();
	}

	/**
	* Sets the version of this contract entry.
	*
	* @param version the version of this contract entry
	*/
	public void setVersion(int version) {
		_contractEntry.setVersion(version);
	}

	/**
	* Returns the content of this contract entry.
	*
	* @return the content of this contract entry
	*/
	public java.lang.String getContent() {
		return _contractEntry.getContent();
	}

	/**
	* Returns the localized content of this contract entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized content of this contract entry
	*/
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
	public java.lang.String getContent(java.util.Locale locale,
		boolean useDefault) {
		return _contractEntry.getContent(locale, useDefault);
	}

	/**
	* Returns the localized content of this contract entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized content of this contract entry
	*/
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
	public java.lang.String getContent(java.lang.String languageId,
		boolean useDefault) {
		return _contractEntry.getContent(languageId, useDefault);
	}

	public java.lang.String getContentCurrentLanguageId() {
		return _contractEntry.getContentCurrentLanguageId();
	}

	public java.lang.String getContentCurrentValue() {
		return _contractEntry.getContentCurrentValue();
	}

	/**
	* Returns a map of the locales and localized contents of this contract entry.
	*
	* @return the locales and localized contents of this contract entry
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getContentMap() {
		return _contractEntry.getContentMap();
	}

	/**
	* Sets the content of this contract entry.
	*
	* @param content the content of this contract entry
	*/
	public void setContent(java.lang.String content) {
		_contractEntry.setContent(content);
	}

	/**
	* Sets the localized content of this contract entry in the language.
	*
	* @param content the localized content of this contract entry
	* @param locale the locale of the language
	*/
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
	public void setContent(java.lang.String content, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_contractEntry.setContent(content, locale, defaultLocale);
	}

	public void setContentCurrentLanguageId(java.lang.String languageId) {
		_contractEntry.setContentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized contents of this contract entry from the map of locales and localized contents.
	*
	* @param contentMap the locales and localized contents of this contract entry
	*/
	public void setContentMap(
		java.util.Map<java.util.Locale, java.lang.String> contentMap) {
		_contractEntry.setContentMap(contentMap);
	}

	/**
	* Sets the localized contents of this contract entry from the map of locales and localized contents, and sets the default locale.
	*
	* @param contentMap the locales and localized contents of this contract entry
	* @param defaultLocale the default locale
	*/
	public void setContentMap(
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		java.util.Locale defaultLocale) {
		_contractEntry.setContentMap(contentMap, defaultLocale);
	}

	public boolean isNew() {
		return _contractEntry.isNew();
	}

	public void setNew(boolean n) {
		_contractEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _contractEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_contractEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _contractEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _contractEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_contractEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _contractEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_contractEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_contractEntry.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ContractEntryWrapper((ContractEntry)_contractEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.ContractEntry contractEntry) {
		return _contractEntry.compareTo(contractEntry);
	}

	@Override
	public int hashCode() {
		return _contractEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.ContractEntry> toCacheModel() {
		return _contractEntry.toCacheModel();
	}

	public com.liferay.osb.model.ContractEntry toEscapedModel() {
		return new ContractEntryWrapper(_contractEntry.toEscapedModel());
	}

	public com.liferay.osb.model.ContractEntry toUnescapedModel() {
		return new ContractEntryWrapper(_contractEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _contractEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _contractEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_contractEntry.persist();
	}

	public java.lang.String getTypeLabel() {
		return _contractEntry.getTypeLabel();
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

		if (Validator.equals(_contractEntry, contractEntryWrapper._contractEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ContractEntry getWrappedContractEntry() {
		return _contractEntry;
	}

	public ContractEntry getWrappedModel() {
		return _contractEntry;
	}

	public void resetOriginalValues() {
		_contractEntry.resetOriginalValues();
	}

	private ContractEntry _contractEntry;
}