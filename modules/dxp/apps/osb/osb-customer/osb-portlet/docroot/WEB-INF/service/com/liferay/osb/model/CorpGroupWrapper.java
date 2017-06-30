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
 * This class is a wrapper for {@link CorpGroup}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpGroup
 * @generated
 */
public class CorpGroupWrapper implements CorpGroup, ModelWrapper<CorpGroup> {
	public CorpGroupWrapper(CorpGroup corpGroup) {
		_corpGroup = corpGroup;
	}

	public Class<?> getModelClass() {
		return CorpGroup.class;
	}

	public String getModelClassName() {
		return CorpGroup.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("corpGroupId", getCorpGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("organizationId", getOrganizationId());
		attributes.put("logoId", getLogoId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("website", getWebsite());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long corpGroupId = (Long)attributes.get("corpGroupId");

		if (corpGroupId != null) {
			setCorpGroupId(corpGroupId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}
	}

	/**
	* Returns the primary key of this corp group.
	*
	* @return the primary key of this corp group
	*/
	public long getPrimaryKey() {
		return _corpGroup.getPrimaryKey();
	}

	/**
	* Sets the primary key of this corp group.
	*
	* @param primaryKey the primary key of this corp group
	*/
	public void setPrimaryKey(long primaryKey) {
		_corpGroup.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the corp group ID of this corp group.
	*
	* @return the corp group ID of this corp group
	*/
	public long getCorpGroupId() {
		return _corpGroup.getCorpGroupId();
	}

	/**
	* Sets the corp group ID of this corp group.
	*
	* @param corpGroupId the corp group ID of this corp group
	*/
	public void setCorpGroupId(long corpGroupId) {
		_corpGroup.setCorpGroupId(corpGroupId);
	}

	/**
	* Returns the user ID of this corp group.
	*
	* @return the user ID of this corp group
	*/
	public long getUserId() {
		return _corpGroup.getUserId();
	}

	/**
	* Sets the user ID of this corp group.
	*
	* @param userId the user ID of this corp group
	*/
	public void setUserId(long userId) {
		_corpGroup.setUserId(userId);
	}

	/**
	* Returns the user uuid of this corp group.
	*
	* @return the user uuid of this corp group
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroup.getUserUuid();
	}

	/**
	* Sets the user uuid of this corp group.
	*
	* @param userUuid the user uuid of this corp group
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_corpGroup.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this corp group.
	*
	* @return the user name of this corp group
	*/
	public java.lang.String getUserName() {
		return _corpGroup.getUserName();
	}

	/**
	* Sets the user name of this corp group.
	*
	* @param userName the user name of this corp group
	*/
	public void setUserName(java.lang.String userName) {
		_corpGroup.setUserName(userName);
	}

	/**
	* Returns the create date of this corp group.
	*
	* @return the create date of this corp group
	*/
	public java.util.Date getCreateDate() {
		return _corpGroup.getCreateDate();
	}

	/**
	* Sets the create date of this corp group.
	*
	* @param createDate the create date of this corp group
	*/
	public void setCreateDate(java.util.Date createDate) {
		_corpGroup.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this corp group.
	*
	* @return the modified date of this corp group
	*/
	public java.util.Date getModifiedDate() {
		return _corpGroup.getModifiedDate();
	}

	/**
	* Sets the modified date of this corp group.
	*
	* @param modifiedDate the modified date of this corp group
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_corpGroup.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this corp group.
	*
	* @return the name of this corp group
	*/
	public java.lang.String getName() {
		return _corpGroup.getName();
	}

	/**
	* Sets the name of this corp group.
	*
	* @param name the name of this corp group
	*/
	public void setName(java.lang.String name) {
		_corpGroup.setName(name);
	}

	/**
	* Returns the description of this corp group.
	*
	* @return the description of this corp group
	*/
	public java.lang.String getDescription() {
		return _corpGroup.getDescription();
	}

	/**
	* Returns the localized description of this corp group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this corp group
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _corpGroup.getDescription(locale);
	}

	/**
	* Returns the localized description of this corp group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this corp group. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _corpGroup.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this corp group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this corp group
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _corpGroup.getDescription(languageId);
	}

	/**
	* Returns the localized description of this corp group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this corp group
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _corpGroup.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _corpGroup.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _corpGroup.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this corp group.
	*
	* @return the locales and localized descriptions of this corp group
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _corpGroup.getDescriptionMap();
	}

	/**
	* Sets the description of this corp group.
	*
	* @param description the description of this corp group
	*/
	public void setDescription(java.lang.String description) {
		_corpGroup.setDescription(description);
	}

	/**
	* Sets the localized description of this corp group in the language.
	*
	* @param description the localized description of this corp group
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_corpGroup.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this corp group in the language, and sets the default locale.
	*
	* @param description the localized description of this corp group
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_corpGroup.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_corpGroup.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this corp group from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this corp group
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_corpGroup.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this corp group from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this corp group
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_corpGroup.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the organization ID of this corp group.
	*
	* @return the organization ID of this corp group
	*/
	public long getOrganizationId() {
		return _corpGroup.getOrganizationId();
	}

	/**
	* Sets the organization ID of this corp group.
	*
	* @param organizationId the organization ID of this corp group
	*/
	public void setOrganizationId(long organizationId) {
		_corpGroup.setOrganizationId(organizationId);
	}

	/**
	* Returns the logo ID of this corp group.
	*
	* @return the logo ID of this corp group
	*/
	public long getLogoId() {
		return _corpGroup.getLogoId();
	}

	/**
	* Sets the logo ID of this corp group.
	*
	* @param logoId the logo ID of this corp group
	*/
	public void setLogoId(long logoId) {
		_corpGroup.setLogoId(logoId);
	}

	/**
	* Returns the email address of this corp group.
	*
	* @return the email address of this corp group
	*/
	public java.lang.String getEmailAddress() {
		return _corpGroup.getEmailAddress();
	}

	/**
	* Sets the email address of this corp group.
	*
	* @param emailAddress the email address of this corp group
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_corpGroup.setEmailAddress(emailAddress);
	}

	/**
	* Returns the website of this corp group.
	*
	* @return the website of this corp group
	*/
	public java.lang.String getWebsite() {
		return _corpGroup.getWebsite();
	}

	/**
	* Sets the website of this corp group.
	*
	* @param website the website of this corp group
	*/
	public void setWebsite(java.lang.String website) {
		_corpGroup.setWebsite(website);
	}

	public boolean isNew() {
		return _corpGroup.isNew();
	}

	public void setNew(boolean n) {
		_corpGroup.setNew(n);
	}

	public boolean isCachedModel() {
		return _corpGroup.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_corpGroup.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _corpGroup.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _corpGroup.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_corpGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _corpGroup.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_corpGroup.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_corpGroup.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new CorpGroupWrapper((CorpGroup)_corpGroup.clone());
	}

	public int compareTo(com.liferay.osb.model.CorpGroup corpGroup) {
		return _corpGroup.compareTo(corpGroup);
	}

	@Override
	public int hashCode() {
		return _corpGroup.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CorpGroup> toCacheModel() {
		return _corpGroup.toCacheModel();
	}

	public com.liferay.osb.model.CorpGroup toEscapedModel() {
		return new CorpGroupWrapper(_corpGroup.toEscapedModel());
	}

	public com.liferay.osb.model.CorpGroup toUnescapedModel() {
		return new CorpGroupWrapper(_corpGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _corpGroup.toString();
	}

	public java.lang.String toXmlString() {
		return _corpGroup.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroup.persist();
	}

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroup.getGroup();
	}

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroup.getGroupId();
	}

	public com.liferay.portal.model.Organization getOrganization()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroup.getOrganization();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpGroupWrapper)) {
			return false;
		}

		CorpGroupWrapper corpGroupWrapper = (CorpGroupWrapper)obj;

		if (Validator.equals(_corpGroup, corpGroupWrapper._corpGroup)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CorpGroup getWrappedCorpGroup() {
		return _corpGroup;
	}

	public CorpGroup getWrappedModel() {
		return _corpGroup;
	}

	public void resetOriginalValues() {
		_corpGroup.resetOriginalValues();
	}

	private CorpGroup _corpGroup;
}