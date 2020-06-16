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

package com.liferay.osb.customer.zendesk.documentation.sync.model;

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
 * This class is a wrapper for {@link ZendeskSection}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskSection
 * @generated
 */
@ProviderType
public class ZendeskSectionWrapper implements ZendeskSection,
	ModelWrapper<ZendeskSection> {
	public ZendeskSectionWrapper(ZendeskSection zendeskSection) {
		_zendeskSection = zendeskSection;
	}

	@Override
	public Class<?> getModelClass() {
		return ZendeskSection.class;
	}

	@Override
	public String getModelClassName() {
		return ZendeskSection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zendeskSectionId", getZendeskSectionId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("zendeskCategoryId", getZendeskCategoryId());
		attributes.put("documentationKey", getDocumentationKey());
		attributes.put("remoteId", getRemoteId());
		attributes.put("remoteHtmlURL", getRemoteHtmlURL());
		attributes.put("remoteName", getRemoteName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zendeskSectionId = (Long)attributes.get("zendeskSectionId");

		if (zendeskSectionId != null) {
			setZendeskSectionId(zendeskSectionId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long zendeskCategoryId = (Long)attributes.get("zendeskCategoryId");

		if (zendeskCategoryId != null) {
			setZendeskCategoryId(zendeskCategoryId);
		}

		String documentationKey = (String)attributes.get("documentationKey");

		if (documentationKey != null) {
			setDocumentationKey(documentationKey);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		String remoteHtmlURL = (String)attributes.get("remoteHtmlURL");

		if (remoteHtmlURL != null) {
			setRemoteHtmlURL(remoteHtmlURL);
		}

		String remoteName = (String)attributes.get("remoteName");

		if (remoteName != null) {
			setRemoteName(remoteName);
		}
	}

	@Override
	public ZendeskSection toEscapedModel() {
		return new ZendeskSectionWrapper(_zendeskSection.toEscapedModel());
	}

	@Override
	public ZendeskSection toUnescapedModel() {
		return new ZendeskSectionWrapper(_zendeskSection.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _zendeskSection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _zendeskSection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _zendeskSection.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zendeskSection.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZendeskSection> toCacheModel() {
		return _zendeskSection.toCacheModel();
	}

	@Override
	public int compareTo(ZendeskSection zendeskSection) {
		return _zendeskSection.compareTo(zendeskSection);
	}

	@Override
	public int hashCode() {
		return _zendeskSection.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zendeskSection.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ZendeskSectionWrapper((ZendeskSection)_zendeskSection.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _zendeskSection.getDefaultLanguageId();
	}

	/**
	* Returns the documentation key of this zendesk section.
	*
	* @return the documentation key of this zendesk section
	*/
	@Override
	public java.lang.String getDocumentationKey() {
		return _zendeskSection.getDocumentationKey();
	}

	/**
	* Returns the remote html url of this zendesk section.
	*
	* @return the remote html url of this zendesk section
	*/
	@Override
	public java.lang.String getRemoteHtmlURL() {
		return _zendeskSection.getRemoteHtmlURL();
	}

	/**
	* Returns the remote name of this zendesk section.
	*
	* @return the remote name of this zendesk section
	*/
	@Override
	public java.lang.String getRemoteName() {
		return _zendeskSection.getRemoteName();
	}

	/**
	* Returns the localized remote name of this zendesk section in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized remote name of this zendesk section
	*/
	@Override
	public java.lang.String getRemoteName(java.lang.String languageId) {
		return _zendeskSection.getRemoteName(languageId);
	}

	/**
	* Returns the localized remote name of this zendesk section in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized remote name of this zendesk section
	*/
	@Override
	public java.lang.String getRemoteName(java.lang.String languageId,
		boolean useDefault) {
		return _zendeskSection.getRemoteName(languageId, useDefault);
	}

	/**
	* Returns the localized remote name of this zendesk section in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized remote name of this zendesk section
	*/
	@Override
	public java.lang.String getRemoteName(java.util.Locale locale) {
		return _zendeskSection.getRemoteName(locale);
	}

	/**
	* Returns the localized remote name of this zendesk section in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized remote name of this zendesk section. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getRemoteName(java.util.Locale locale,
		boolean useDefault) {
		return _zendeskSection.getRemoteName(locale, useDefault);
	}

	@Override
	public java.lang.String getRemoteNameCurrentLanguageId() {
		return _zendeskSection.getRemoteNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getRemoteNameCurrentValue() {
		return _zendeskSection.getRemoteNameCurrentValue();
	}

	@Override
	public java.lang.String toString() {
		return _zendeskSection.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _zendeskSection.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _zendeskSection.getAvailableLanguageIds();
	}

	/**
	* Returns the modified date of this zendesk section.
	*
	* @return the modified date of this zendesk section
	*/
	@Override
	public Date getModifiedDate() {
		return _zendeskSection.getModifiedDate();
	}

	/**
	* Returns a map of the locales and localized remote names of this zendesk section.
	*
	* @return the locales and localized remote names of this zendesk section
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getRemoteNameMap() {
		return _zendeskSection.getRemoteNameMap();
	}

	/**
	* Returns the primary key of this zendesk section.
	*
	* @return the primary key of this zendesk section
	*/
	@Override
	public long getPrimaryKey() {
		return _zendeskSection.getPrimaryKey();
	}

	/**
	* Returns the remote ID of this zendesk section.
	*
	* @return the remote ID of this zendesk section
	*/
	@Override
	public long getRemoteId() {
		return _zendeskSection.getRemoteId();
	}

	/**
	* Returns the zendesk category ID of this zendesk section.
	*
	* @return the zendesk category ID of this zendesk section
	*/
	@Override
	public long getZendeskCategoryId() {
		return _zendeskSection.getZendeskCategoryId();
	}

	/**
	* Returns the zendesk section ID of this zendesk section.
	*
	* @return the zendesk section ID of this zendesk section
	*/
	@Override
	public long getZendeskSectionId() {
		return _zendeskSection.getZendeskSectionId();
	}

	@Override
	public void persist() {
		_zendeskSection.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_zendeskSection.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_zendeskSection.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_zendeskSection.setCachedModel(cachedModel);
	}

	/**
	* Sets the documentation key of this zendesk section.
	*
	* @param documentationKey the documentation key of this zendesk section
	*/
	@Override
	public void setDocumentationKey(java.lang.String documentationKey) {
		_zendeskSection.setDocumentationKey(documentationKey);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zendeskSection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zendeskSection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_zendeskSection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this zendesk section.
	*
	* @param modifiedDate the modified date of this zendesk section
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_zendeskSection.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_zendeskSection.setNew(n);
	}

	/**
	* Sets the primary key of this zendesk section.
	*
	* @param primaryKey the primary key of this zendesk section
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_zendeskSection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_zendeskSection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote html url of this zendesk section.
	*
	* @param remoteHtmlURL the remote html url of this zendesk section
	*/
	@Override
	public void setRemoteHtmlURL(java.lang.String remoteHtmlURL) {
		_zendeskSection.setRemoteHtmlURL(remoteHtmlURL);
	}

	/**
	* Sets the remote ID of this zendesk section.
	*
	* @param remoteId the remote ID of this zendesk section
	*/
	@Override
	public void setRemoteId(long remoteId) {
		_zendeskSection.setRemoteId(remoteId);
	}

	/**
	* Sets the remote name of this zendesk section.
	*
	* @param remoteName the remote name of this zendesk section
	*/
	@Override
	public void setRemoteName(java.lang.String remoteName) {
		_zendeskSection.setRemoteName(remoteName);
	}

	/**
	* Sets the localized remote name of this zendesk section in the language.
	*
	* @param remoteName the localized remote name of this zendesk section
	* @param locale the locale of the language
	*/
	@Override
	public void setRemoteName(java.lang.String remoteName,
		java.util.Locale locale) {
		_zendeskSection.setRemoteName(remoteName, locale);
	}

	/**
	* Sets the localized remote name of this zendesk section in the language, and sets the default locale.
	*
	* @param remoteName the localized remote name of this zendesk section
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setRemoteName(java.lang.String remoteName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_zendeskSection.setRemoteName(remoteName, locale, defaultLocale);
	}

	@Override
	public void setRemoteNameCurrentLanguageId(java.lang.String languageId) {
		_zendeskSection.setRemoteNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized remote names of this zendesk section from the map of locales and localized remote names.
	*
	* @param remoteNameMap the locales and localized remote names of this zendesk section
	*/
	@Override
	public void setRemoteNameMap(
		Map<java.util.Locale, java.lang.String> remoteNameMap) {
		_zendeskSection.setRemoteNameMap(remoteNameMap);
	}

	/**
	* Sets the localized remote names of this zendesk section from the map of locales and localized remote names, and sets the default locale.
	*
	* @param remoteNameMap the locales and localized remote names of this zendesk section
	* @param defaultLocale the default locale
	*/
	@Override
	public void setRemoteNameMap(
		Map<java.util.Locale, java.lang.String> remoteNameMap,
		java.util.Locale defaultLocale) {
		_zendeskSection.setRemoteNameMap(remoteNameMap, defaultLocale);
	}

	/**
	* Sets the zendesk category ID of this zendesk section.
	*
	* @param zendeskCategoryId the zendesk category ID of this zendesk section
	*/
	@Override
	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskSection.setZendeskCategoryId(zendeskCategoryId);
	}

	/**
	* Sets the zendesk section ID of this zendesk section.
	*
	* @param zendeskSectionId the zendesk section ID of this zendesk section
	*/
	@Override
	public void setZendeskSectionId(long zendeskSectionId) {
		_zendeskSection.setZendeskSectionId(zendeskSectionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskSectionWrapper)) {
			return false;
		}

		ZendeskSectionWrapper zendeskSectionWrapper = (ZendeskSectionWrapper)obj;

		if (Objects.equals(_zendeskSection,
					zendeskSectionWrapper._zendeskSection)) {
			return true;
		}

		return false;
	}

	@Override
	public ZendeskSection getWrappedModel() {
		return _zendeskSection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _zendeskSection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _zendeskSection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_zendeskSection.resetOriginalValues();
	}

	private final ZendeskSection _zendeskSection;
}