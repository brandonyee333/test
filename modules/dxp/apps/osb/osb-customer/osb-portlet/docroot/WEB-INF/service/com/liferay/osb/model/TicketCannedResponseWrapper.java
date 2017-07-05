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
 * This class is a wrapper for {@link TicketCannedResponse}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponse
 * @generated
 */
@ProviderType
public class TicketCannedResponseWrapper implements TicketCannedResponse,
	ModelWrapper<TicketCannedResponse> {
	public TicketCannedResponseWrapper(
		TicketCannedResponse ticketCannedResponse) {
		_ticketCannedResponse = ticketCannedResponse;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketCannedResponse.class;
	}

	@Override
	public String getModelClassName() {
		return TicketCannedResponse.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketCannedResponseId", getTicketCannedResponseId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("content", getContent());
		attributes.put("useCount", getUseCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketCannedResponseId = (Long)attributes.get(
				"ticketCannedResponseId");

		if (ticketCannedResponseId != null) {
			setTicketCannedResponseId(ticketCannedResponseId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer useCount = (Integer)attributes.get("useCount");

		if (useCount != null) {
			setUseCount(useCount);
		}
	}

	@Override
	public TicketCannedResponse toEscapedModel() {
		return new TicketCannedResponseWrapper(_ticketCannedResponse.toEscapedModel());
	}

	@Override
	public TicketCannedResponse toUnescapedModel() {
		return new TicketCannedResponseWrapper(_ticketCannedResponse.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketCannedResponse.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketCannedResponse.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketCannedResponse.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketCannedResponse.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketCannedResponse> toCacheModel() {
		return _ticketCannedResponse.toCacheModel();
	}

	@Override
	public int compareTo(TicketCannedResponse ticketCannedResponse) {
		return _ticketCannedResponse.compareTo(ticketCannedResponse);
	}

	/**
	* Returns the use count of this ticket canned response.
	*
	* @return the use count of this ticket canned response
	*/
	@Override
	public int getUseCount() {
		return _ticketCannedResponse.getUseCount();
	}

	@Override
	public int hashCode() {
		return _ticketCannedResponse.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketCannedResponse.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketCannedResponseWrapper((TicketCannedResponse)_ticketCannedResponse.clone());
	}

	/**
	* Returns the content of this ticket canned response.
	*
	* @return the content of this ticket canned response
	*/
	@Override
	public java.lang.String getContent() {
		return _ticketCannedResponse.getContent();
	}

	/**
	* Returns the localized content of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized content of this ticket canned response
	*/
	@Override
	public java.lang.String getContent(java.lang.String languageId) {
		return _ticketCannedResponse.getContent(languageId);
	}

	/**
	* Returns the localized content of this ticket canned response in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized content of this ticket canned response
	*/
	@Override
	public java.lang.String getContent(java.lang.String languageId,
		boolean useDefault) {
		return _ticketCannedResponse.getContent(languageId, useDefault);
	}

	/**
	* Returns the localized content of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized content of this ticket canned response
	*/
	@Override
	public java.lang.String getContent(java.util.Locale locale) {
		return _ticketCannedResponse.getContent(locale);
	}

	/**
	* Returns the localized content of this ticket canned response in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized content of this ticket canned response. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getContent(java.util.Locale locale,
		boolean useDefault) {
		return _ticketCannedResponse.getContent(locale, useDefault);
	}

	@Override
	public java.lang.String getContentCurrentLanguageId() {
		return _ticketCannedResponse.getContentCurrentLanguageId();
	}

	@Override
	public java.lang.String getContentCurrentValue() {
		return _ticketCannedResponse.getContentCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _ticketCannedResponse.getDefaultLanguageId();
	}

	/**
	* Returns the name of this ticket canned response.
	*
	* @return the name of this ticket canned response
	*/
	@Override
	public java.lang.String getName() {
		return _ticketCannedResponse.getName();
	}

	/**
	* Returns the localized name of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this ticket canned response
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _ticketCannedResponse.getName(languageId);
	}

	/**
	* Returns the localized name of this ticket canned response in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this ticket canned response
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _ticketCannedResponse.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this ticket canned response
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _ticketCannedResponse.getName(locale);
	}

	/**
	* Returns the localized name of this ticket canned response in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this ticket canned response. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _ticketCannedResponse.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _ticketCannedResponse.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _ticketCannedResponse.getNameCurrentValue();
	}

	/**
	* Returns the user name of this ticket canned response.
	*
	* @return the user name of this ticket canned response
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketCannedResponse.getUserName();
	}

	/**
	* Returns the user uuid of this ticket canned response.
	*
	* @return the user uuid of this ticket canned response
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketCannedResponse.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ticketCannedResponse.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketCannedResponse.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _ticketCannedResponse.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this ticket canned response.
	*
	* @return the create date of this ticket canned response
	*/
	@Override
	public Date getCreateDate() {
		return _ticketCannedResponse.getCreateDate();
	}

	/**
	* Returns the modified date of this ticket canned response.
	*
	* @return the modified date of this ticket canned response
	*/
	@Override
	public Date getModifiedDate() {
		return _ticketCannedResponse.getModifiedDate();
	}

	/**
	* Returns a map of the locales and localized contents of this ticket canned response.
	*
	* @return the locales and localized contents of this ticket canned response
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getContentMap() {
		return _ticketCannedResponse.getContentMap();
	}

	/**
	* Returns a map of the locales and localized names of this ticket canned response.
	*
	* @return the locales and localized names of this ticket canned response
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _ticketCannedResponse.getNameMap();
	}

	/**
	* Returns the primary key of this ticket canned response.
	*
	* @return the primary key of this ticket canned response
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketCannedResponse.getPrimaryKey();
	}

	/**
	* Returns the ticket canned response ID of this ticket canned response.
	*
	* @return the ticket canned response ID of this ticket canned response
	*/
	@Override
	public long getTicketCannedResponseId() {
		return _ticketCannedResponse.getTicketCannedResponseId();
	}

	/**
	* Returns the user ID of this ticket canned response.
	*
	* @return the user ID of this ticket canned response
	*/
	@Override
	public long getUserId() {
		return _ticketCannedResponse.getUserId();
	}

	@Override
	public void persist() {
		_ticketCannedResponse.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_ticketCannedResponse.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_ticketCannedResponse.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketCannedResponse.setCachedModel(cachedModel);
	}

	/**
	* Sets the content of this ticket canned response.
	*
	* @param content the content of this ticket canned response
	*/
	@Override
	public void setContent(java.lang.String content) {
		_ticketCannedResponse.setContent(content);
	}

	/**
	* Sets the localized content of this ticket canned response in the language.
	*
	* @param content the localized content of this ticket canned response
	* @param locale the locale of the language
	*/
	@Override
	public void setContent(java.lang.String content, java.util.Locale locale) {
		_ticketCannedResponse.setContent(content, locale);
	}

	/**
	* Sets the localized content of this ticket canned response in the language, and sets the default locale.
	*
	* @param content the localized content of this ticket canned response
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContent(java.lang.String content, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setContent(content, locale, defaultLocale);
	}

	@Override
	public void setContentCurrentLanguageId(java.lang.String languageId) {
		_ticketCannedResponse.setContentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized contents of this ticket canned response from the map of locales and localized contents.
	*
	* @param contentMap the locales and localized contents of this ticket canned response
	*/
	@Override
	public void setContentMap(
		Map<java.util.Locale, java.lang.String> contentMap) {
		_ticketCannedResponse.setContentMap(contentMap);
	}

	/**
	* Sets the localized contents of this ticket canned response from the map of locales and localized contents, and sets the default locale.
	*
	* @param contentMap the locales and localized contents of this ticket canned response
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContentMap(
		Map<java.util.Locale, java.lang.String> contentMap,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setContentMap(contentMap, defaultLocale);
	}

	/**
	* Sets the create date of this ticket canned response.
	*
	* @param createDate the create date of this ticket canned response
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketCannedResponse.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketCannedResponse.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketCannedResponse.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketCannedResponse.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this ticket canned response.
	*
	* @param modifiedDate the modified date of this ticket canned response
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ticketCannedResponse.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this ticket canned response.
	*
	* @param name the name of this ticket canned response
	*/
	@Override
	public void setName(java.lang.String name) {
		_ticketCannedResponse.setName(name);
	}

	/**
	* Sets the localized name of this ticket canned response in the language.
	*
	* @param name the localized name of this ticket canned response
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_ticketCannedResponse.setName(name, locale);
	}

	/**
	* Sets the localized name of this ticket canned response in the language, and sets the default locale.
	*
	* @param name the localized name of this ticket canned response
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_ticketCannedResponse.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this ticket canned response from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this ticket canned response
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_ticketCannedResponse.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this ticket canned response from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this ticket canned response
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_ticketCannedResponse.setNew(n);
	}

	/**
	* Sets the primary key of this ticket canned response.
	*
	* @param primaryKey the primary key of this ticket canned response
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketCannedResponse.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketCannedResponse.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ticket canned response ID of this ticket canned response.
	*
	* @param ticketCannedResponseId the ticket canned response ID of this ticket canned response
	*/
	@Override
	public void setTicketCannedResponseId(long ticketCannedResponseId) {
		_ticketCannedResponse.setTicketCannedResponseId(ticketCannedResponseId);
	}

	/**
	* Sets the use count of this ticket canned response.
	*
	* @param useCount the use count of this ticket canned response
	*/
	@Override
	public void setUseCount(int useCount) {
		_ticketCannedResponse.setUseCount(useCount);
	}

	/**
	* Sets the user ID of this ticket canned response.
	*
	* @param userId the user ID of this ticket canned response
	*/
	@Override
	public void setUserId(long userId) {
		_ticketCannedResponse.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket canned response.
	*
	* @param userName the user name of this ticket canned response
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketCannedResponse.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket canned response.
	*
	* @param userUuid the user uuid of this ticket canned response
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketCannedResponse.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketCannedResponseWrapper)) {
			return false;
		}

		TicketCannedResponseWrapper ticketCannedResponseWrapper = (TicketCannedResponseWrapper)obj;

		if (Objects.equals(_ticketCannedResponse,
					ticketCannedResponseWrapper._ticketCannedResponse)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketCannedResponse getWrappedModel() {
		return _ticketCannedResponse;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketCannedResponse.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketCannedResponse.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketCannedResponse.resetOriginalValues();
	}

	private final TicketCannedResponse _ticketCannedResponse;
}