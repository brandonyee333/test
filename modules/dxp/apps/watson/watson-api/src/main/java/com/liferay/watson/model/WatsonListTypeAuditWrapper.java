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
 * This class is a wrapper for {@link WatsonListTypeAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeAudit
 * @generated
 */
@ProviderType
public class WatsonListTypeAuditWrapper implements WatsonListTypeAudit,
	ModelWrapper<WatsonListTypeAudit> {
	public WatsonListTypeAuditWrapper(WatsonListTypeAudit watsonListTypeAudit) {
		_watsonListTypeAudit = watsonListTypeAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonListTypeAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonListTypeAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonListTypeAuditId", getWatsonListTypeAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentWatsonListTypeId", getParentWatsonListTypeId());
		attributes.put("watsonListTypeId", getWatsonListTypeId());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonListTypeAuditId = (Long)attributes.get(
				"watsonListTypeAuditId");

		if (watsonListTypeAuditId != null) {
			setWatsonListTypeAuditId(watsonListTypeAuditId);
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

		Long parentWatsonListTypeId = (Long)attributes.get(
				"parentWatsonListTypeId");

		if (parentWatsonListTypeId != null) {
			setParentWatsonListTypeId(parentWatsonListTypeId);
		}

		Long watsonListTypeId = (Long)attributes.get("watsonListTypeId");

		if (watsonListTypeId != null) {
			setWatsonListTypeId(watsonListTypeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
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
	public Object clone() {
		return new WatsonListTypeAuditWrapper((WatsonListTypeAudit)_watsonListTypeAudit.clone());
	}

	@Override
	public int compareTo(WatsonListTypeAudit watsonListTypeAudit) {
		return _watsonListTypeAudit.compareTo(watsonListTypeAudit);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonListTypeAudit.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson list type audit.
	*
	* @return the company ID of this watson list type audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonListTypeAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson list type audit.
	*
	* @return the create date of this watson list type audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonListTypeAudit.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonListTypeAudit.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonListTypeAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson list type audit.
	*
	* @return the group ID of this watson list type audit
	*/
	@Override
	public long getGroupId() {
		return _watsonListTypeAudit.getGroupId();
	}

	/**
	* Returns the modified date of this watson list type audit.
	*
	* @return the modified date of this watson list type audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonListTypeAudit.getModifiedDate();
	}

	/**
	* Returns the name of this watson list type audit.
	*
	* @return the name of this watson list type audit
	*/
	@Override
	public String getName() {
		return _watsonListTypeAudit.getName();
	}

	/**
	* Returns the localized name of this watson list type audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson list type audit
	*/
	@Override
	public String getName(java.util.Locale locale) {
		return _watsonListTypeAudit.getName(locale);
	}

	/**
	* Returns the localized name of this watson list type audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson list type audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonListTypeAudit.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson list type audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson list type audit
	*/
	@Override
	public String getName(String languageId) {
		return _watsonListTypeAudit.getName(languageId);
	}

	/**
	* Returns the localized name of this watson list type audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson list type audit
	*/
	@Override
	public String getName(String languageId, boolean useDefault) {
		return _watsonListTypeAudit.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _watsonListTypeAudit.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _watsonListTypeAudit.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson list type audit.
	*
	* @return the locales and localized names of this watson list type audit
	*/
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return _watsonListTypeAudit.getNameMap();
	}

	/**
	* Returns the parent watson list type ID of this watson list type audit.
	*
	* @return the parent watson list type ID of this watson list type audit
	*/
	@Override
	public long getParentWatsonListTypeId() {
		return _watsonListTypeAudit.getParentWatsonListTypeId();
	}

	/**
	* Returns the primary key of this watson list type audit.
	*
	* @return the primary key of this watson list type audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonListTypeAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonListTypeAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson list type audit.
	*
	* @return the status of this watson list type audit
	*/
	@Override
	public int getStatus() {
		return _watsonListTypeAudit.getStatus();
	}

	/**
	* Returns the type of this watson list type audit.
	*
	* @return the type of this watson list type audit
	*/
	@Override
	public String getType() {
		return _watsonListTypeAudit.getType();
	}

	/**
	* Returns the user ID of this watson list type audit.
	*
	* @return the user ID of this watson list type audit
	*/
	@Override
	public long getUserId() {
		return _watsonListTypeAudit.getUserId();
	}

	/**
	* Returns the user name of this watson list type audit.
	*
	* @return the user name of this watson list type audit
	*/
	@Override
	public String getUserName() {
		return _watsonListTypeAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson list type audit.
	*
	* @return the user uuid of this watson list type audit
	*/
	@Override
	public String getUserUuid() {
		return _watsonListTypeAudit.getUserUuid();
	}

	/**
	* Returns the watson list type audit ID of this watson list type audit.
	*
	* @return the watson list type audit ID of this watson list type audit
	*/
	@Override
	public long getWatsonListTypeAuditId() {
		return _watsonListTypeAudit.getWatsonListTypeAuditId();
	}

	/**
	* Returns the watson list type ID of this watson list type audit.
	*
	* @return the watson list type ID of this watson list type audit
	*/
	@Override
	public long getWatsonListTypeId() {
		return _watsonListTypeAudit.getWatsonListTypeId();
	}

	@Override
	public int hashCode() {
		return _watsonListTypeAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonListTypeAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonListTypeAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonListTypeAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonListTypeAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonListTypeAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonListTypeAudit.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonListTypeAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson list type audit.
	*
	* @param companyId the company ID of this watson list type audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonListTypeAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson list type audit.
	*
	* @param createDate the create date of this watson list type audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonListTypeAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonListTypeAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonListTypeAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonListTypeAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson list type audit.
	*
	* @param groupId the group ID of this watson list type audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonListTypeAudit.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson list type audit.
	*
	* @param modifiedDate the modified date of this watson list type audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonListTypeAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson list type audit.
	*
	* @param name the name of this watson list type audit
	*/
	@Override
	public void setName(String name) {
		_watsonListTypeAudit.setName(name);
	}

	/**
	* Sets the localized name of this watson list type audit in the language.
	*
	* @param name the localized name of this watson list type audit
	* @param locale the locale of the language
	*/
	@Override
	public void setName(String name, java.util.Locale locale) {
		_watsonListTypeAudit.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson list type audit in the language, and sets the default locale.
	*
	* @param name the localized name of this watson list type audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonListTypeAudit.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_watsonListTypeAudit.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson list type audit from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson list type audit
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		_watsonListTypeAudit.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson list type audit from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson list type audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonListTypeAudit.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonListTypeAudit.setNew(n);
	}

	/**
	* Sets the parent watson list type ID of this watson list type audit.
	*
	* @param parentWatsonListTypeId the parent watson list type ID of this watson list type audit
	*/
	@Override
	public void setParentWatsonListTypeId(long parentWatsonListTypeId) {
		_watsonListTypeAudit.setParentWatsonListTypeId(parentWatsonListTypeId);
	}

	/**
	* Sets the primary key of this watson list type audit.
	*
	* @param primaryKey the primary key of this watson list type audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonListTypeAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonListTypeAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson list type audit.
	*
	* @param status the status of this watson list type audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonListTypeAudit.setStatus(status);
	}

	/**
	* Sets the type of this watson list type audit.
	*
	* @param type the type of this watson list type audit
	*/
	@Override
	public void setType(String type) {
		_watsonListTypeAudit.setType(type);
	}

	/**
	* Sets the user ID of this watson list type audit.
	*
	* @param userId the user ID of this watson list type audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonListTypeAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson list type audit.
	*
	* @param userName the user name of this watson list type audit
	*/
	@Override
	public void setUserName(String userName) {
		_watsonListTypeAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson list type audit.
	*
	* @param userUuid the user uuid of this watson list type audit
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonListTypeAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson list type audit ID of this watson list type audit.
	*
	* @param watsonListTypeAuditId the watson list type audit ID of this watson list type audit
	*/
	@Override
	public void setWatsonListTypeAuditId(long watsonListTypeAuditId) {
		_watsonListTypeAudit.setWatsonListTypeAuditId(watsonListTypeAuditId);
	}

	/**
	* Sets the watson list type ID of this watson list type audit.
	*
	* @param watsonListTypeId the watson list type ID of this watson list type audit
	*/
	@Override
	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeAudit.setWatsonListTypeId(watsonListTypeId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonListTypeAudit> toCacheModel() {
		return _watsonListTypeAudit.toCacheModel();
	}

	@Override
	public WatsonListTypeAudit toEscapedModel() {
		return new WatsonListTypeAuditWrapper(_watsonListTypeAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonListTypeAudit.toString();
	}

	@Override
	public WatsonListTypeAudit toUnescapedModel() {
		return new WatsonListTypeAuditWrapper(_watsonListTypeAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonListTypeAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeAuditWrapper)) {
			return false;
		}

		WatsonListTypeAuditWrapper watsonListTypeAuditWrapper = (WatsonListTypeAuditWrapper)obj;

		if (Objects.equals(_watsonListTypeAudit,
					watsonListTypeAuditWrapper._watsonListTypeAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonListTypeAudit getWrappedModel() {
		return _watsonListTypeAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonListTypeAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonListTypeAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonListTypeAudit.resetOriginalValues();
	}

	private final WatsonListTypeAudit _watsonListTypeAudit;
}