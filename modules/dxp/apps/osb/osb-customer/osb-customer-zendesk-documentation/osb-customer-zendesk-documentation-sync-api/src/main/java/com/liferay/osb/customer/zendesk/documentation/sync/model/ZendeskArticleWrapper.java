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
 * This class is a wrapper for {@link ZendeskArticle}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticle
 * @generated
 */
@ProviderType
public class ZendeskArticleWrapper implements ZendeskArticle,
	ModelWrapper<ZendeskArticle> {
	public ZendeskArticleWrapper(ZendeskArticle zendeskArticle) {
		_zendeskArticle = zendeskArticle;
	}

	@Override
	public Class<?> getModelClass() {
		return ZendeskArticle.class;
	}

	@Override
	public String getModelClassName() {
		return ZendeskArticle.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zendeskArticleId", getZendeskArticleId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("zendeskCategoryId", getZendeskCategoryId());
		attributes.put("zendeskSectionId", getZendeskSectionId());
		attributes.put("documentationKey", getDocumentationKey());
		attributes.put("documentationOriginalURL", getDocumentationOriginalURL());
		attributes.put("previousArticleDocumentationKey",
			getPreviousArticleDocumentationKey());
		attributes.put("nextArticleDocumentationKey",
			getNextArticleDocumentationKey());
		attributes.put("remoteId", getRemoteId());
		attributes.put("remoteHtmlURL", getRemoteHtmlURL());
		attributes.put("remoteTitle", getRemoteTitle());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zendeskArticleId = (Long)attributes.get("zendeskArticleId");

		if (zendeskArticleId != null) {
			setZendeskArticleId(zendeskArticleId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long zendeskCategoryId = (Long)attributes.get("zendeskCategoryId");

		if (zendeskCategoryId != null) {
			setZendeskCategoryId(zendeskCategoryId);
		}

		Long zendeskSectionId = (Long)attributes.get("zendeskSectionId");

		if (zendeskSectionId != null) {
			setZendeskSectionId(zendeskSectionId);
		}

		String documentationKey = (String)attributes.get("documentationKey");

		if (documentationKey != null) {
			setDocumentationKey(documentationKey);
		}

		String documentationOriginalURL = (String)attributes.get(
				"documentationOriginalURL");

		if (documentationOriginalURL != null) {
			setDocumentationOriginalURL(documentationOriginalURL);
		}

		String previousArticleDocumentationKey = (String)attributes.get(
				"previousArticleDocumentationKey");

		if (previousArticleDocumentationKey != null) {
			setPreviousArticleDocumentationKey(previousArticleDocumentationKey);
		}

		String nextArticleDocumentationKey = (String)attributes.get(
				"nextArticleDocumentationKey");

		if (nextArticleDocumentationKey != null) {
			setNextArticleDocumentationKey(nextArticleDocumentationKey);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		String remoteHtmlURL = (String)attributes.get("remoteHtmlURL");

		if (remoteHtmlURL != null) {
			setRemoteHtmlURL(remoteHtmlURL);
		}

		String remoteTitle = (String)attributes.get("remoteTitle");

		if (remoteTitle != null) {
			setRemoteTitle(remoteTitle);
		}
	}

	@Override
	public ZendeskArticle toEscapedModel() {
		return new ZendeskArticleWrapper(_zendeskArticle.toEscapedModel());
	}

	@Override
	public ZendeskArticle toUnescapedModel() {
		return new ZendeskArticleWrapper(_zendeskArticle.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _zendeskArticle.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _zendeskArticle.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _zendeskArticle.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zendeskArticle.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZendeskArticle> toCacheModel() {
		return _zendeskArticle.toCacheModel();
	}

	@Override
	public int compareTo(ZendeskArticle zendeskArticle) {
		return _zendeskArticle.compareTo(zendeskArticle);
	}

	@Override
	public int hashCode() {
		return _zendeskArticle.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zendeskArticle.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ZendeskArticleWrapper((ZendeskArticle)_zendeskArticle.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _zendeskArticle.getDefaultLanguageId();
	}

	/**
	* Returns the documentation key of this zendesk article.
	*
	* @return the documentation key of this zendesk article
	*/
	@Override
	public java.lang.String getDocumentationKey() {
		return _zendeskArticle.getDocumentationKey();
	}

	/**
	* Returns the documentation original url of this zendesk article.
	*
	* @return the documentation original url of this zendesk article
	*/
	@Override
	public java.lang.String getDocumentationOriginalURL() {
		return _zendeskArticle.getDocumentationOriginalURL();
	}

	/**
	* Returns the next article documentation key of this zendesk article.
	*
	* @return the next article documentation key of this zendesk article
	*/
	@Override
	public java.lang.String getNextArticleDocumentationKey() {
		return _zendeskArticle.getNextArticleDocumentationKey();
	}

	/**
	* Returns the previous article documentation key of this zendesk article.
	*
	* @return the previous article documentation key of this zendesk article
	*/
	@Override
	public java.lang.String getPreviousArticleDocumentationKey() {
		return _zendeskArticle.getPreviousArticleDocumentationKey();
	}

	/**
	* Returns the remote html url of this zendesk article.
	*
	* @return the remote html url of this zendesk article
	*/
	@Override
	public java.lang.String getRemoteHtmlURL() {
		return _zendeskArticle.getRemoteHtmlURL();
	}

	@Override
	public java.lang.String getRemoteHtmlURL(java.lang.String zendeskLocale) {
		return _zendeskArticle.getRemoteHtmlURL(zendeskLocale);
	}

	/**
	* Returns the remote title of this zendesk article.
	*
	* @return the remote title of this zendesk article
	*/
	@Override
	public java.lang.String getRemoteTitle() {
		return _zendeskArticle.getRemoteTitle();
	}

	/**
	* Returns the localized remote title of this zendesk article in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized remote title of this zendesk article
	*/
	@Override
	public java.lang.String getRemoteTitle(java.lang.String languageId) {
		return _zendeskArticle.getRemoteTitle(languageId);
	}

	/**
	* Returns the localized remote title of this zendesk article in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized remote title of this zendesk article
	*/
	@Override
	public java.lang.String getRemoteTitle(java.lang.String languageId,
		boolean useDefault) {
		return _zendeskArticle.getRemoteTitle(languageId, useDefault);
	}

	/**
	* Returns the localized remote title of this zendesk article in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized remote title of this zendesk article
	*/
	@Override
	public java.lang.String getRemoteTitle(java.util.Locale locale) {
		return _zendeskArticle.getRemoteTitle(locale);
	}

	/**
	* Returns the localized remote title of this zendesk article in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized remote title of this zendesk article. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getRemoteTitle(java.util.Locale locale,
		boolean useDefault) {
		return _zendeskArticle.getRemoteTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getRemoteTitleCurrentLanguageId() {
		return _zendeskArticle.getRemoteTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getRemoteTitleCurrentValue() {
		return _zendeskArticle.getRemoteTitleCurrentValue();
	}

	@Override
	public java.lang.String toString() {
		return _zendeskArticle.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _zendeskArticle.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _zendeskArticle.getAvailableLanguageIds();
	}

	/**
	* Returns the modified date of this zendesk article.
	*
	* @return the modified date of this zendesk article
	*/
	@Override
	public Date getModifiedDate() {
		return _zendeskArticle.getModifiedDate();
	}

	/**
	* Returns a map of the locales and localized remote titles of this zendesk article.
	*
	* @return the locales and localized remote titles of this zendesk article
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getRemoteTitleMap() {
		return _zendeskArticle.getRemoteTitleMap();
	}

	/**
	* Returns the primary key of this zendesk article.
	*
	* @return the primary key of this zendesk article
	*/
	@Override
	public long getPrimaryKey() {
		return _zendeskArticle.getPrimaryKey();
	}

	/**
	* Returns the remote ID of this zendesk article.
	*
	* @return the remote ID of this zendesk article
	*/
	@Override
	public long getRemoteId() {
		return _zendeskArticle.getRemoteId();
	}

	/**
	* Returns the zendesk article ID of this zendesk article.
	*
	* @return the zendesk article ID of this zendesk article
	*/
	@Override
	public long getZendeskArticleId() {
		return _zendeskArticle.getZendeskArticleId();
	}

	/**
	* Returns the zendesk category ID of this zendesk article.
	*
	* @return the zendesk category ID of this zendesk article
	*/
	@Override
	public long getZendeskCategoryId() {
		return _zendeskArticle.getZendeskCategoryId();
	}

	/**
	* Returns the zendesk section ID of this zendesk article.
	*
	* @return the zendesk section ID of this zendesk article
	*/
	@Override
	public long getZendeskSectionId() {
		return _zendeskArticle.getZendeskSectionId();
	}

	@Override
	public void persist() {
		_zendeskArticle.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_zendeskArticle.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_zendeskArticle.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_zendeskArticle.setCachedModel(cachedModel);
	}

	/**
	* Sets the documentation key of this zendesk article.
	*
	* @param documentationKey the documentation key of this zendesk article
	*/
	@Override
	public void setDocumentationKey(java.lang.String documentationKey) {
		_zendeskArticle.setDocumentationKey(documentationKey);
	}

	/**
	* Sets the documentation original url of this zendesk article.
	*
	* @param documentationOriginalURL the documentation original url of this zendesk article
	*/
	@Override
	public void setDocumentationOriginalURL(
		java.lang.String documentationOriginalURL) {
		_zendeskArticle.setDocumentationOriginalURL(documentationOriginalURL);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zendeskArticle.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zendeskArticle.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_zendeskArticle.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this zendesk article.
	*
	* @param modifiedDate the modified date of this zendesk article
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_zendeskArticle.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_zendeskArticle.setNew(n);
	}

	/**
	* Sets the next article documentation key of this zendesk article.
	*
	* @param nextArticleDocumentationKey the next article documentation key of this zendesk article
	*/
	@Override
	public void setNextArticleDocumentationKey(
		java.lang.String nextArticleDocumentationKey) {
		_zendeskArticle.setNextArticleDocumentationKey(nextArticleDocumentationKey);
	}

	/**
	* Sets the previous article documentation key of this zendesk article.
	*
	* @param previousArticleDocumentationKey the previous article documentation key of this zendesk article
	*/
	@Override
	public void setPreviousArticleDocumentationKey(
		java.lang.String previousArticleDocumentationKey) {
		_zendeskArticle.setPreviousArticleDocumentationKey(previousArticleDocumentationKey);
	}

	/**
	* Sets the primary key of this zendesk article.
	*
	* @param primaryKey the primary key of this zendesk article
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_zendeskArticle.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_zendeskArticle.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote html url of this zendesk article.
	*
	* @param remoteHtmlURL the remote html url of this zendesk article
	*/
	@Override
	public void setRemoteHtmlURL(java.lang.String remoteHtmlURL) {
		_zendeskArticle.setRemoteHtmlURL(remoteHtmlURL);
	}

	/**
	* Sets the remote ID of this zendesk article.
	*
	* @param remoteId the remote ID of this zendesk article
	*/
	@Override
	public void setRemoteId(long remoteId) {
		_zendeskArticle.setRemoteId(remoteId);
	}

	/**
	* Sets the remote title of this zendesk article.
	*
	* @param remoteTitle the remote title of this zendesk article
	*/
	@Override
	public void setRemoteTitle(java.lang.String remoteTitle) {
		_zendeskArticle.setRemoteTitle(remoteTitle);
	}

	/**
	* Sets the localized remote title of this zendesk article in the language.
	*
	* @param remoteTitle the localized remote title of this zendesk article
	* @param locale the locale of the language
	*/
	@Override
	public void setRemoteTitle(java.lang.String remoteTitle,
		java.util.Locale locale) {
		_zendeskArticle.setRemoteTitle(remoteTitle, locale);
	}

	/**
	* Sets the localized remote title of this zendesk article in the language, and sets the default locale.
	*
	* @param remoteTitle the localized remote title of this zendesk article
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setRemoteTitle(java.lang.String remoteTitle,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_zendeskArticle.setRemoteTitle(remoteTitle, locale, defaultLocale);
	}

	@Override
	public void setRemoteTitleCurrentLanguageId(java.lang.String languageId) {
		_zendeskArticle.setRemoteTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized remote titles of this zendesk article from the map of locales and localized remote titles.
	*
	* @param remoteTitleMap the locales and localized remote titles of this zendesk article
	*/
	@Override
	public void setRemoteTitleMap(
		Map<java.util.Locale, java.lang.String> remoteTitleMap) {
		_zendeskArticle.setRemoteTitleMap(remoteTitleMap);
	}

	/**
	* Sets the localized remote titles of this zendesk article from the map of locales and localized remote titles, and sets the default locale.
	*
	* @param remoteTitleMap the locales and localized remote titles of this zendesk article
	* @param defaultLocale the default locale
	*/
	@Override
	public void setRemoteTitleMap(
		Map<java.util.Locale, java.lang.String> remoteTitleMap,
		java.util.Locale defaultLocale) {
		_zendeskArticle.setRemoteTitleMap(remoteTitleMap, defaultLocale);
	}

	/**
	* Sets the zendesk article ID of this zendesk article.
	*
	* @param zendeskArticleId the zendesk article ID of this zendesk article
	*/
	@Override
	public void setZendeskArticleId(long zendeskArticleId) {
		_zendeskArticle.setZendeskArticleId(zendeskArticleId);
	}

	/**
	* Sets the zendesk category ID of this zendesk article.
	*
	* @param zendeskCategoryId the zendesk category ID of this zendesk article
	*/
	@Override
	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskArticle.setZendeskCategoryId(zendeskCategoryId);
	}

	/**
	* Sets the zendesk section ID of this zendesk article.
	*
	* @param zendeskSectionId the zendesk section ID of this zendesk article
	*/
	@Override
	public void setZendeskSectionId(long zendeskSectionId) {
		_zendeskArticle.setZendeskSectionId(zendeskSectionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskArticleWrapper)) {
			return false;
		}

		ZendeskArticleWrapper zendeskArticleWrapper = (ZendeskArticleWrapper)obj;

		if (Objects.equals(_zendeskArticle,
					zendeskArticleWrapper._zendeskArticle)) {
			return true;
		}

		return false;
	}

	@Override
	public ZendeskArticle getWrappedModel() {
		return _zendeskArticle;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _zendeskArticle.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _zendeskArticle.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_zendeskArticle.resetOriginalValues();
	}

	private final ZendeskArticle _zendeskArticle;
}