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
 * This class is a wrapper for {@link CorpEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpEntry
 * @generated
 */
public class CorpEntryWrapper implements CorpEntry, ModelWrapper<CorpEntry> {
	public CorpEntryWrapper(CorpEntry corpEntry) {
		_corpEntry = corpEntry;
	}

	public Class<?> getModelClass() {
		return CorpEntry.class;
	}

	public String getModelClassName() {
		return CorpEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("corpEntryId", getCorpEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("organizationId", getOrganizationId());
		attributes.put("logoId", getLogoId());
		attributes.put("addressId", getAddressId());
		attributes.put("contactEmailAddress", getContactEmailAddress());
		attributes.put("profileEmailAddress", getProfileEmailAddress());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("faxNumber", getFaxNumber());
		attributes.put("website", getWebsite());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long corpEntryId = (Long)attributes.get("corpEntryId");

		if (corpEntryId != null) {
			setCorpEntryId(corpEntryId);
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

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		String contactEmailAddress = (String)attributes.get(
				"contactEmailAddress");

		if (contactEmailAddress != null) {
			setContactEmailAddress(contactEmailAddress);
		}

		String profileEmailAddress = (String)attributes.get(
				"profileEmailAddress");

		if (profileEmailAddress != null) {
			setProfileEmailAddress(profileEmailAddress);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String faxNumber = (String)attributes.get("faxNumber");

		if (faxNumber != null) {
			setFaxNumber(faxNumber);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}
	}

	/**
	* Returns the primary key of this corp entry.
	*
	* @return the primary key of this corp entry
	*/
	public long getPrimaryKey() {
		return _corpEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this corp entry.
	*
	* @param primaryKey the primary key of this corp entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_corpEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the corp entry ID of this corp entry.
	*
	* @return the corp entry ID of this corp entry
	*/
	public long getCorpEntryId() {
		return _corpEntry.getCorpEntryId();
	}

	/**
	* Sets the corp entry ID of this corp entry.
	*
	* @param corpEntryId the corp entry ID of this corp entry
	*/
	public void setCorpEntryId(long corpEntryId) {
		_corpEntry.setCorpEntryId(corpEntryId);
	}

	/**
	* Returns the user ID of this corp entry.
	*
	* @return the user ID of this corp entry
	*/
	public long getUserId() {
		return _corpEntry.getUserId();
	}

	/**
	* Sets the user ID of this corp entry.
	*
	* @param userId the user ID of this corp entry
	*/
	public void setUserId(long userId) {
		_corpEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this corp entry.
	*
	* @return the user uuid of this corp entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this corp entry.
	*
	* @param userUuid the user uuid of this corp entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_corpEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this corp entry.
	*
	* @return the user name of this corp entry
	*/
	public java.lang.String getUserName() {
		return _corpEntry.getUserName();
	}

	/**
	* Sets the user name of this corp entry.
	*
	* @param userName the user name of this corp entry
	*/
	public void setUserName(java.lang.String userName) {
		_corpEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this corp entry.
	*
	* @return the create date of this corp entry
	*/
	public java.util.Date getCreateDate() {
		return _corpEntry.getCreateDate();
	}

	/**
	* Sets the create date of this corp entry.
	*
	* @param createDate the create date of this corp entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_corpEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this corp entry.
	*
	* @return the modified date of this corp entry
	*/
	public java.util.Date getModifiedDate() {
		return _corpEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this corp entry.
	*
	* @param modifiedDate the modified date of this corp entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_corpEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this corp entry.
	*
	* @return the name of this corp entry
	*/
	public java.lang.String getName() {
		return _corpEntry.getName();
	}

	/**
	* Sets the name of this corp entry.
	*
	* @param name the name of this corp entry
	*/
	public void setName(java.lang.String name) {
		_corpEntry.setName(name);
	}

	/**
	* Returns the description of this corp entry.
	*
	* @return the description of this corp entry
	*/
	public java.lang.String getDescription() {
		return _corpEntry.getDescription();
	}

	/**
	* Returns the localized description of this corp entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this corp entry
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _corpEntry.getDescription(locale);
	}

	/**
	* Returns the localized description of this corp entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this corp entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _corpEntry.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this corp entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this corp entry
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _corpEntry.getDescription(languageId);
	}

	/**
	* Returns the localized description of this corp entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this corp entry
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _corpEntry.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _corpEntry.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _corpEntry.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this corp entry.
	*
	* @return the locales and localized descriptions of this corp entry
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _corpEntry.getDescriptionMap();
	}

	/**
	* Sets the description of this corp entry.
	*
	* @param description the description of this corp entry
	*/
	public void setDescription(java.lang.String description) {
		_corpEntry.setDescription(description);
	}

	/**
	* Sets the localized description of this corp entry in the language.
	*
	* @param description the localized description of this corp entry
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_corpEntry.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this corp entry in the language, and sets the default locale.
	*
	* @param description the localized description of this corp entry
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_corpEntry.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_corpEntry.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this corp entry from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this corp entry
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_corpEntry.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this corp entry from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this corp entry
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_corpEntry.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the organization ID of this corp entry.
	*
	* @return the organization ID of this corp entry
	*/
	public long getOrganizationId() {
		return _corpEntry.getOrganizationId();
	}

	/**
	* Sets the organization ID of this corp entry.
	*
	* @param organizationId the organization ID of this corp entry
	*/
	public void setOrganizationId(long organizationId) {
		_corpEntry.setOrganizationId(organizationId);
	}

	/**
	* Returns the logo ID of this corp entry.
	*
	* @return the logo ID of this corp entry
	*/
	public long getLogoId() {
		return _corpEntry.getLogoId();
	}

	/**
	* Sets the logo ID of this corp entry.
	*
	* @param logoId the logo ID of this corp entry
	*/
	public void setLogoId(long logoId) {
		_corpEntry.setLogoId(logoId);
	}

	/**
	* Returns the address ID of this corp entry.
	*
	* @return the address ID of this corp entry
	*/
	public long getAddressId() {
		return _corpEntry.getAddressId();
	}

	/**
	* Sets the address ID of this corp entry.
	*
	* @param addressId the address ID of this corp entry
	*/
	public void setAddressId(long addressId) {
		_corpEntry.setAddressId(addressId);
	}

	/**
	* Returns the contact email address of this corp entry.
	*
	* @return the contact email address of this corp entry
	*/
	public java.lang.String getContactEmailAddress() {
		return _corpEntry.getContactEmailAddress();
	}

	/**
	* Sets the contact email address of this corp entry.
	*
	* @param contactEmailAddress the contact email address of this corp entry
	*/
	public void setContactEmailAddress(java.lang.String contactEmailAddress) {
		_corpEntry.setContactEmailAddress(contactEmailAddress);
	}

	/**
	* Returns the profile email address of this corp entry.
	*
	* @return the profile email address of this corp entry
	*/
	public java.lang.String getProfileEmailAddress() {
		return _corpEntry.getProfileEmailAddress();
	}

	/**
	* Sets the profile email address of this corp entry.
	*
	* @param profileEmailAddress the profile email address of this corp entry
	*/
	public void setProfileEmailAddress(java.lang.String profileEmailAddress) {
		_corpEntry.setProfileEmailAddress(profileEmailAddress);
	}

	/**
	* Returns the phone number of this corp entry.
	*
	* @return the phone number of this corp entry
	*/
	public java.lang.String getPhoneNumber() {
		return _corpEntry.getPhoneNumber();
	}

	/**
	* Sets the phone number of this corp entry.
	*
	* @param phoneNumber the phone number of this corp entry
	*/
	public void setPhoneNumber(java.lang.String phoneNumber) {
		_corpEntry.setPhoneNumber(phoneNumber);
	}

	/**
	* Returns the fax number of this corp entry.
	*
	* @return the fax number of this corp entry
	*/
	public java.lang.String getFaxNumber() {
		return _corpEntry.getFaxNumber();
	}

	/**
	* Sets the fax number of this corp entry.
	*
	* @param faxNumber the fax number of this corp entry
	*/
	public void setFaxNumber(java.lang.String faxNumber) {
		_corpEntry.setFaxNumber(faxNumber);
	}

	/**
	* Returns the website of this corp entry.
	*
	* @return the website of this corp entry
	*/
	public java.lang.String getWebsite() {
		return _corpEntry.getWebsite();
	}

	/**
	* Sets the website of this corp entry.
	*
	* @param website the website of this corp entry
	*/
	public void setWebsite(java.lang.String website) {
		_corpEntry.setWebsite(website);
	}

	/**
	* Returns the dossiera account key of this corp entry.
	*
	* @return the dossiera account key of this corp entry
	*/
	public java.lang.String getDossieraAccountKey() {
		return _corpEntry.getDossieraAccountKey();
	}

	/**
	* Sets the dossiera account key of this corp entry.
	*
	* @param dossieraAccountKey the dossiera account key of this corp entry
	*/
	public void setDossieraAccountKey(java.lang.String dossieraAccountKey) {
		_corpEntry.setDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the status of this corp entry.
	*
	* @return the status of this corp entry
	*/
	public int getStatus() {
		return _corpEntry.getStatus();
	}

	/**
	* Sets the status of this corp entry.
	*
	* @param status the status of this corp entry
	*/
	public void setStatus(int status) {
		_corpEntry.setStatus(status);
	}

	/**
	* Returns the status by user ID of this corp entry.
	*
	* @return the status by user ID of this corp entry
	*/
	public long getStatusByUserId() {
		return _corpEntry.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this corp entry.
	*
	* @param statusByUserId the status by user ID of this corp entry
	*/
	public void setStatusByUserId(long statusByUserId) {
		_corpEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this corp entry.
	*
	* @return the status by user uuid of this corp entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntry.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this corp entry.
	*
	* @param statusByUserUuid the status by user uuid of this corp entry
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_corpEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this corp entry.
	*
	* @return the status by user name of this corp entry
	*/
	public java.lang.String getStatusByUserName() {
		return _corpEntry.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this corp entry.
	*
	* @param statusByUserName the status by user name of this corp entry
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_corpEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this corp entry.
	*
	* @return the status date of this corp entry
	*/
	public java.util.Date getStatusDate() {
		return _corpEntry.getStatusDate();
	}

	/**
	* Sets the status date of this corp entry.
	*
	* @param statusDate the status date of this corp entry
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_corpEntry.setStatusDate(statusDate);
	}

	/**
	* Returns the status message of this corp entry.
	*
	* @return the status message of this corp entry
	*/
	public java.lang.String getStatusMessage() {
		return _corpEntry.getStatusMessage();
	}

	/**
	* Sets the status message of this corp entry.
	*
	* @param statusMessage the status message of this corp entry
	*/
	public void setStatusMessage(java.lang.String statusMessage) {
		_corpEntry.setStatusMessage(statusMessage);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _corpEntry.getApproved();
	}

	/**
	* Returns <code>true</code> if this corp entry is approved.
	*
	* @return <code>true</code> if this corp entry is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _corpEntry.isApproved();
	}

	/**
	* Returns <code>true</code> if this corp entry is denied.
	*
	* @return <code>true</code> if this corp entry is denied; <code>false</code> otherwise
	*/
	public boolean isDenied() {
		return _corpEntry.isDenied();
	}

	/**
	* Returns <code>true</code> if this corp entry is a draft.
	*
	* @return <code>true</code> if this corp entry is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _corpEntry.isDraft();
	}

	/**
	* Returns <code>true</code> if this corp entry is expired.
	*
	* @return <code>true</code> if this corp entry is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _corpEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this corp entry is inactive.
	*
	* @return <code>true</code> if this corp entry is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _corpEntry.isInactive();
	}

	/**
	* Returns <code>true</code> if this corp entry is incomplete.
	*
	* @return <code>true</code> if this corp entry is incomplete; <code>false</code> otherwise
	*/
	public boolean isIncomplete() {
		return _corpEntry.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this corp entry is pending.
	*
	* @return <code>true</code> if this corp entry is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _corpEntry.isPending();
	}

	/**
	* Returns <code>true</code> if this corp entry is scheduled.
	*
	* @return <code>true</code> if this corp entry is scheduled; <code>false</code> otherwise
	*/
	public boolean isScheduled() {
		return _corpEntry.isScheduled();
	}

	public boolean isNew() {
		return _corpEntry.isNew();
	}

	public void setNew(boolean n) {
		_corpEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _corpEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_corpEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _corpEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _corpEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_corpEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _corpEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_corpEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_corpEntry.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new CorpEntryWrapper((CorpEntry)_corpEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.CorpEntry corpEntry) {
		return _corpEntry.compareTo(corpEntry);
	}

	@Override
	public int hashCode() {
		return _corpEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CorpEntry> toCacheModel() {
		return _corpEntry.toCacheModel();
	}

	public com.liferay.osb.model.CorpEntry toEscapedModel() {
		return new CorpEntryWrapper(_corpEntry.toEscapedModel());
	}

	public com.liferay.osb.model.CorpEntry toUnescapedModel() {
		return new CorpEntryWrapper(_corpEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _corpEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _corpEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntry.persist();
	}

	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntry.getAddress();
	}

	public java.lang.String getCity() {
		return _corpEntry.getCity();
	}

	public java.lang.String getCountryCode() {
		return _corpEntry.getCountryCode();
	}

	public long getCountryId() {
		return _corpEntry.getCountryId();
	}

	public java.lang.String getDefaultLanguageId() {
		return _corpEntry.getDefaultLanguageId();
	}

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntry.getGroup();
	}

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntry.getGroupId();
	}

	public com.liferay.portal.model.Organization getOrganization()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntry.getOrganization();
	}

	public long getRegionId() {
		return _corpEntry.getRegionId();
	}

	public java.lang.String getStreet1() {
		return _corpEntry.getStreet1();
	}

	public java.lang.String getStreet2() {
		return _corpEntry.getStreet2();
	}

	public java.lang.String getStreet3() {
		return _corpEntry.getStreet3();
	}

	public java.lang.String getZip() {
		return _corpEntry.getZip();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpEntryWrapper)) {
			return false;
		}

		CorpEntryWrapper corpEntryWrapper = (CorpEntryWrapper)obj;

		if (Validator.equals(_corpEntry, corpEntryWrapper._corpEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CorpEntry getWrappedCorpEntry() {
		return _corpEntry;
	}

	public CorpEntry getWrappedModel() {
		return _corpEntry;
	}

	public void resetOriginalValues() {
		_corpEntry.resetOriginalValues();
	}

	private CorpEntry _corpEntry;
}