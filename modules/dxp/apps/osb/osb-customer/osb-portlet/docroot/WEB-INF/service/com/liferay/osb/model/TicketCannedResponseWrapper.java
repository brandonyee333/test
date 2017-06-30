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
 * This class is a wrapper for {@link TicketCannedResponse}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketCannedResponse
 * @generated
 */
public class TicketCannedResponseWrapper implements TicketCannedResponse,
	ModelWrapper<TicketCannedResponse> {
	public TicketCannedResponseWrapper(
		TicketCannedResponse ticketCannedResponse) {
		_ticketCannedResponse = ticketCannedResponse;
	}

	public Class<?> getModelClass() {
		return TicketCannedResponse.class;
	}

	public String getModelClassName() {
		return TicketCannedResponse.class.getName();
	}

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

	/**
	* Returns the primary key of this ticket canned response.
	*
	* @return the primary key of this ticket canned response
	*/
	public long getPrimaryKey() {
		return _ticketCannedResponse.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket canned response.
	*
	* @param primaryKey the primary key of this ticket canned response
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketCannedResponse.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket canned response ID of this ticket canned response.
	*
	* @return the ticket canned response ID of this ticket canned response
	*/
	public long getTicketCannedResponseId() {
		return _ticketCannedResponse.getTicketCannedResponseId();
	}

	/**
	* Sets the ticket canned response ID of this ticket canned response.
	*
	* @param ticketCannedResponseId the ticket canned response ID of this ticket canned response
	*/
	public void setTicketCannedResponseId(long ticketCannedResponseId) {
		_ticketCannedResponse.setTicketCannedResponseId(ticketCannedResponseId);
	}

	/**
	* Returns the user ID of this ticket canned response.
	*
	* @return the user ID of this ticket canned response
	*/
	public long getUserId() {
		return _ticketCannedResponse.getUserId();
	}

	/**
	* Sets the user ID of this ticket canned response.
	*
	* @param userId the user ID of this ticket canned response
	*/
	public void setUserId(long userId) {
		_ticketCannedResponse.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket canned response.
	*
	* @return the user uuid of this ticket canned response
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponse.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket canned response.
	*
	* @param userUuid the user uuid of this ticket canned response
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketCannedResponse.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this ticket canned response.
	*
	* @return the user name of this ticket canned response
	*/
	public java.lang.String getUserName() {
		return _ticketCannedResponse.getUserName();
	}

	/**
	* Sets the user name of this ticket canned response.
	*
	* @param userName the user name of this ticket canned response
	*/
	public void setUserName(java.lang.String userName) {
		_ticketCannedResponse.setUserName(userName);
	}

	/**
	* Returns the create date of this ticket canned response.
	*
	* @return the create date of this ticket canned response
	*/
	public java.util.Date getCreateDate() {
		return _ticketCannedResponse.getCreateDate();
	}

	/**
	* Sets the create date of this ticket canned response.
	*
	* @param createDate the create date of this ticket canned response
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketCannedResponse.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this ticket canned response.
	*
	* @return the modified date of this ticket canned response
	*/
	public java.util.Date getModifiedDate() {
		return _ticketCannedResponse.getModifiedDate();
	}

	/**
	* Sets the modified date of this ticket canned response.
	*
	* @param modifiedDate the modified date of this ticket canned response
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_ticketCannedResponse.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this ticket canned response.
	*
	* @return the name of this ticket canned response
	*/
	public java.lang.String getName() {
		return _ticketCannedResponse.getName();
	}

	/**
	* Returns the localized name of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this ticket canned response
	*/
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
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _ticketCannedResponse.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this ticket canned response
	*/
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
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _ticketCannedResponse.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _ticketCannedResponse.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _ticketCannedResponse.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this ticket canned response.
	*
	* @return the locales and localized names of this ticket canned response
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _ticketCannedResponse.getNameMap();
	}

	/**
	* Sets the name of this ticket canned response.
	*
	* @param name the name of this ticket canned response
	*/
	public void setName(java.lang.String name) {
		_ticketCannedResponse.setName(name);
	}

	/**
	* Sets the localized name of this ticket canned response in the language.
	*
	* @param name the localized name of this ticket canned response
	* @param locale the locale of the language
	*/
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
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_ticketCannedResponse.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this ticket canned response from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this ticket canned response
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_ticketCannedResponse.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this ticket canned response from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this ticket canned response
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the content of this ticket canned response.
	*
	* @return the content of this ticket canned response
	*/
	public java.lang.String getContent() {
		return _ticketCannedResponse.getContent();
	}

	/**
	* Returns the localized content of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized content of this ticket canned response
	*/
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
	public java.lang.String getContent(java.util.Locale locale,
		boolean useDefault) {
		return _ticketCannedResponse.getContent(locale, useDefault);
	}

	/**
	* Returns the localized content of this ticket canned response in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized content of this ticket canned response
	*/
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
	public java.lang.String getContent(java.lang.String languageId,
		boolean useDefault) {
		return _ticketCannedResponse.getContent(languageId, useDefault);
	}

	public java.lang.String getContentCurrentLanguageId() {
		return _ticketCannedResponse.getContentCurrentLanguageId();
	}

	public java.lang.String getContentCurrentValue() {
		return _ticketCannedResponse.getContentCurrentValue();
	}

	/**
	* Returns a map of the locales and localized contents of this ticket canned response.
	*
	* @return the locales and localized contents of this ticket canned response
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getContentMap() {
		return _ticketCannedResponse.getContentMap();
	}

	/**
	* Sets the content of this ticket canned response.
	*
	* @param content the content of this ticket canned response
	*/
	public void setContent(java.lang.String content) {
		_ticketCannedResponse.setContent(content);
	}

	/**
	* Sets the localized content of this ticket canned response in the language.
	*
	* @param content the localized content of this ticket canned response
	* @param locale the locale of the language
	*/
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
	public void setContent(java.lang.String content, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setContent(content, locale, defaultLocale);
	}

	public void setContentCurrentLanguageId(java.lang.String languageId) {
		_ticketCannedResponse.setContentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized contents of this ticket canned response from the map of locales and localized contents.
	*
	* @param contentMap the locales and localized contents of this ticket canned response
	*/
	public void setContentMap(
		java.util.Map<java.util.Locale, java.lang.String> contentMap) {
		_ticketCannedResponse.setContentMap(contentMap);
	}

	/**
	* Sets the localized contents of this ticket canned response from the map of locales and localized contents, and sets the default locale.
	*
	* @param contentMap the locales and localized contents of this ticket canned response
	* @param defaultLocale the default locale
	*/
	public void setContentMap(
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		java.util.Locale defaultLocale) {
		_ticketCannedResponse.setContentMap(contentMap, defaultLocale);
	}

	/**
	* Returns the use count of this ticket canned response.
	*
	* @return the use count of this ticket canned response
	*/
	public int getUseCount() {
		return _ticketCannedResponse.getUseCount();
	}

	/**
	* Sets the use count of this ticket canned response.
	*
	* @param useCount the use count of this ticket canned response
	*/
	public void setUseCount(int useCount) {
		_ticketCannedResponse.setUseCount(useCount);
	}

	public boolean isNew() {
		return _ticketCannedResponse.isNew();
	}

	public void setNew(boolean n) {
		_ticketCannedResponse.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketCannedResponse.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketCannedResponse.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketCannedResponse.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketCannedResponse.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketCannedResponse.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketCannedResponse.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketCannedResponse.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_ticketCannedResponse.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketCannedResponseWrapper((TicketCannedResponse)_ticketCannedResponse.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TicketCannedResponse ticketCannedResponse) {
		return _ticketCannedResponse.compareTo(ticketCannedResponse);
	}

	@Override
	public int hashCode() {
		return _ticketCannedResponse.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketCannedResponse> toCacheModel() {
		return _ticketCannedResponse.toCacheModel();
	}

	public com.liferay.osb.model.TicketCannedResponse toEscapedModel() {
		return new TicketCannedResponseWrapper(_ticketCannedResponse.toEscapedModel());
	}

	public com.liferay.osb.model.TicketCannedResponse toUnescapedModel() {
		return new TicketCannedResponseWrapper(_ticketCannedResponse.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketCannedResponse.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketCannedResponse.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketCannedResponse.persist();
	}

	public java.lang.String[] getAvailableLocales() {
		return _ticketCannedResponse.getAvailableLocales();
	}

	public java.lang.String getDefaultLocale() {
		return _ticketCannedResponse.getDefaultLocale();
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

		if (Validator.equals(_ticketCannedResponse,
					ticketCannedResponseWrapper._ticketCannedResponse)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketCannedResponse getWrappedTicketCannedResponse() {
		return _ticketCannedResponse;
	}

	public TicketCannedResponse getWrappedModel() {
		return _ticketCannedResponse;
	}

	public void resetOriginalValues() {
		_ticketCannedResponse.resetOriginalValues();
	}

	private TicketCannedResponse _ticketCannedResponse;
}