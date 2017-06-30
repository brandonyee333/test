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
 * This class is a wrapper for {@link DeveloperEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DeveloperEntry
 * @generated
 */
public class DeveloperEntryWrapper implements DeveloperEntry,
	ModelWrapper<DeveloperEntry> {
	public DeveloperEntryWrapper(DeveloperEntry developerEntry) {
		_developerEntry = developerEntry;
	}

	public Class<?> getModelClass() {
		return DeveloperEntry.class;
	}

	public String getModelClassName() {
		return DeveloperEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("developerEntryId", getDeveloperEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("screenName", getScreenName());
		attributes.put("firstName", getFirstName());
		attributes.put("middleName", getMiddleName());
		attributes.put("lastName", getLastName());
		attributes.put("legalEntityName", getLegalEntityName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("contractEntryId", getContractEntryId());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("faxNumber", getFaxNumber());
		attributes.put("domainName", getDomainName());
		attributes.put("domainStatus", getDomainStatus());
		attributes.put("addressId", getAddressId());
		attributes.put("countryId", getCountryId());
		attributes.put("profileDescription", getProfileDescription());
		attributes.put("profileEmailAddress", getProfileEmailAddress());
		attributes.put("profileLogoId", getProfileLogoId());
		attributes.put("profileWebsite", getProfileWebsite());
		attributes.put("paymentEmailAddress", getPaymentEmailAddress());
		attributes.put("googleAnalyticsKey", getGoogleAnalyticsKey());
		attributes.put("subscriptionExpirationDate",
			getSubscriptionExpirationDate());
		attributes.put("subscriptionStatus", getSubscriptionStatus());
		attributes.put("fatcaWithholdingPercentage",
			getFatcaWithholdingPercentage());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("type", getType());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long developerEntryId = (Long)attributes.get("developerEntryId");

		if (developerEntryId != null) {
			setDeveloperEntryId(developerEntryId);
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

		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String middleName = (String)attributes.get("middleName");

		if (middleName != null) {
			setMiddleName(middleName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String legalEntityName = (String)attributes.get("legalEntityName");

		if (legalEntityName != null) {
			setLegalEntityName(legalEntityName);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Long contractEntryId = (Long)attributes.get("contractEntryId");

		if (contractEntryId != null) {
			setContractEntryId(contractEntryId);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String faxNumber = (String)attributes.get("faxNumber");

		if (faxNumber != null) {
			setFaxNumber(faxNumber);
		}

		String domainName = (String)attributes.get("domainName");

		if (domainName != null) {
			setDomainName(domainName);
		}

		Integer domainStatus = (Integer)attributes.get("domainStatus");

		if (domainStatus != null) {
			setDomainStatus(domainStatus);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String profileDescription = (String)attributes.get("profileDescription");

		if (profileDescription != null) {
			setProfileDescription(profileDescription);
		}

		String profileEmailAddress = (String)attributes.get(
				"profileEmailAddress");

		if (profileEmailAddress != null) {
			setProfileEmailAddress(profileEmailAddress);
		}

		Long profileLogoId = (Long)attributes.get("profileLogoId");

		if (profileLogoId != null) {
			setProfileLogoId(profileLogoId);
		}

		String profileWebsite = (String)attributes.get("profileWebsite");

		if (profileWebsite != null) {
			setProfileWebsite(profileWebsite);
		}

		String paymentEmailAddress = (String)attributes.get(
				"paymentEmailAddress");

		if (paymentEmailAddress != null) {
			setPaymentEmailAddress(paymentEmailAddress);
		}

		String googleAnalyticsKey = (String)attributes.get("googleAnalyticsKey");

		if (googleAnalyticsKey != null) {
			setGoogleAnalyticsKey(googleAnalyticsKey);
		}

		Date subscriptionExpirationDate = (Date)attributes.get(
				"subscriptionExpirationDate");

		if (subscriptionExpirationDate != null) {
			setSubscriptionExpirationDate(subscriptionExpirationDate);
		}

		Integer subscriptionStatus = (Integer)attributes.get(
				"subscriptionStatus");

		if (subscriptionStatus != null) {
			setSubscriptionStatus(subscriptionStatus);
		}

		Double fatcaWithholdingPercentage = (Double)attributes.get(
				"fatcaWithholdingPercentage");

		if (fatcaWithholdingPercentage != null) {
			setFatcaWithholdingPercentage(fatcaWithholdingPercentage);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
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
	* Returns the primary key of this developer entry.
	*
	* @return the primary key of this developer entry
	*/
	public long getPrimaryKey() {
		return _developerEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this developer entry.
	*
	* @param primaryKey the primary key of this developer entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_developerEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the developer entry ID of this developer entry.
	*
	* @return the developer entry ID of this developer entry
	*/
	public long getDeveloperEntryId() {
		return _developerEntry.getDeveloperEntryId();
	}

	/**
	* Sets the developer entry ID of this developer entry.
	*
	* @param developerEntryId the developer entry ID of this developer entry
	*/
	public void setDeveloperEntryId(long developerEntryId) {
		_developerEntry.setDeveloperEntryId(developerEntryId);
	}

	/**
	* Returns the user ID of this developer entry.
	*
	* @return the user ID of this developer entry
	*/
	public long getUserId() {
		return _developerEntry.getUserId();
	}

	/**
	* Sets the user ID of this developer entry.
	*
	* @param userId the user ID of this developer entry
	*/
	public void setUserId(long userId) {
		_developerEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this developer entry.
	*
	* @return the user uuid of this developer entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this developer entry.
	*
	* @param userUuid the user uuid of this developer entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_developerEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this developer entry.
	*
	* @return the user name of this developer entry
	*/
	public java.lang.String getUserName() {
		return _developerEntry.getUserName();
	}

	/**
	* Sets the user name of this developer entry.
	*
	* @param userName the user name of this developer entry
	*/
	public void setUserName(java.lang.String userName) {
		_developerEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this developer entry.
	*
	* @return the create date of this developer entry
	*/
	public java.util.Date getCreateDate() {
		return _developerEntry.getCreateDate();
	}

	/**
	* Sets the create date of this developer entry.
	*
	* @param createDate the create date of this developer entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_developerEntry.setCreateDate(createDate);
	}

	/**
	* Returns the screen name of this developer entry.
	*
	* @return the screen name of this developer entry
	*/
	public java.lang.String getScreenName() {
		return _developerEntry.getScreenName();
	}

	/**
	* Sets the screen name of this developer entry.
	*
	* @param screenName the screen name of this developer entry
	*/
	public void setScreenName(java.lang.String screenName) {
		_developerEntry.setScreenName(screenName);
	}

	/**
	* Returns the first name of this developer entry.
	*
	* @return the first name of this developer entry
	*/
	public java.lang.String getFirstName() {
		return _developerEntry.getFirstName();
	}

	/**
	* Sets the first name of this developer entry.
	*
	* @param firstName the first name of this developer entry
	*/
	public void setFirstName(java.lang.String firstName) {
		_developerEntry.setFirstName(firstName);
	}

	/**
	* Returns the middle name of this developer entry.
	*
	* @return the middle name of this developer entry
	*/
	public java.lang.String getMiddleName() {
		return _developerEntry.getMiddleName();
	}

	/**
	* Sets the middle name of this developer entry.
	*
	* @param middleName the middle name of this developer entry
	*/
	public void setMiddleName(java.lang.String middleName) {
		_developerEntry.setMiddleName(middleName);
	}

	/**
	* Returns the last name of this developer entry.
	*
	* @return the last name of this developer entry
	*/
	public java.lang.String getLastName() {
		return _developerEntry.getLastName();
	}

	/**
	* Sets the last name of this developer entry.
	*
	* @param lastName the last name of this developer entry
	*/
	public void setLastName(java.lang.String lastName) {
		_developerEntry.setLastName(lastName);
	}

	/**
	* Returns the legal entity name of this developer entry.
	*
	* @return the legal entity name of this developer entry
	*/
	public java.lang.String getLegalEntityName() {
		return _developerEntry.getLegalEntityName();
	}

	/**
	* Sets the legal entity name of this developer entry.
	*
	* @param legalEntityName the legal entity name of this developer entry
	*/
	public void setLegalEntityName(java.lang.String legalEntityName) {
		_developerEntry.setLegalEntityName(legalEntityName);
	}

	/**
	* Returns the email address of this developer entry.
	*
	* @return the email address of this developer entry
	*/
	public java.lang.String getEmailAddress() {
		return _developerEntry.getEmailAddress();
	}

	/**
	* Sets the email address of this developer entry.
	*
	* @param emailAddress the email address of this developer entry
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_developerEntry.setEmailAddress(emailAddress);
	}

	/**
	* Returns the contract entry ID of this developer entry.
	*
	* @return the contract entry ID of this developer entry
	*/
	public long getContractEntryId() {
		return _developerEntry.getContractEntryId();
	}

	/**
	* Sets the contract entry ID of this developer entry.
	*
	* @param contractEntryId the contract entry ID of this developer entry
	*/
	public void setContractEntryId(long contractEntryId) {
		_developerEntry.setContractEntryId(contractEntryId);
	}

	/**
	* Returns the phone number of this developer entry.
	*
	* @return the phone number of this developer entry
	*/
	public java.lang.String getPhoneNumber() {
		return _developerEntry.getPhoneNumber();
	}

	/**
	* Sets the phone number of this developer entry.
	*
	* @param phoneNumber the phone number of this developer entry
	*/
	public void setPhoneNumber(java.lang.String phoneNumber) {
		_developerEntry.setPhoneNumber(phoneNumber);
	}

	/**
	* Returns the fax number of this developer entry.
	*
	* @return the fax number of this developer entry
	*/
	public java.lang.String getFaxNumber() {
		return _developerEntry.getFaxNumber();
	}

	/**
	* Sets the fax number of this developer entry.
	*
	* @param faxNumber the fax number of this developer entry
	*/
	public void setFaxNumber(java.lang.String faxNumber) {
		_developerEntry.setFaxNumber(faxNumber);
	}

	/**
	* Returns the domain name of this developer entry.
	*
	* @return the domain name of this developer entry
	*/
	public java.lang.String getDomainName() {
		return _developerEntry.getDomainName();
	}

	/**
	* Sets the domain name of this developer entry.
	*
	* @param domainName the domain name of this developer entry
	*/
	public void setDomainName(java.lang.String domainName) {
		_developerEntry.setDomainName(domainName);
	}

	/**
	* Returns the domain status of this developer entry.
	*
	* @return the domain status of this developer entry
	*/
	public int getDomainStatus() {
		return _developerEntry.getDomainStatus();
	}

	/**
	* Sets the domain status of this developer entry.
	*
	* @param domainStatus the domain status of this developer entry
	*/
	public void setDomainStatus(int domainStatus) {
		_developerEntry.setDomainStatus(domainStatus);
	}

	/**
	* Returns the address ID of this developer entry.
	*
	* @return the address ID of this developer entry
	*/
	public long getAddressId() {
		return _developerEntry.getAddressId();
	}

	/**
	* Sets the address ID of this developer entry.
	*
	* @param addressId the address ID of this developer entry
	*/
	public void setAddressId(long addressId) {
		_developerEntry.setAddressId(addressId);
	}

	/**
	* Returns the country ID of this developer entry.
	*
	* @return the country ID of this developer entry
	*/
	public long getCountryId() {
		return _developerEntry.getCountryId();
	}

	/**
	* Sets the country ID of this developer entry.
	*
	* @param countryId the country ID of this developer entry
	*/
	public void setCountryId(long countryId) {
		_developerEntry.setCountryId(countryId);
	}

	/**
	* Returns the profile description of this developer entry.
	*
	* @return the profile description of this developer entry
	*/
	public java.lang.String getProfileDescription() {
		return _developerEntry.getProfileDescription();
	}

	/**
	* Returns the localized profile description of this developer entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized profile description of this developer entry
	*/
	public java.lang.String getProfileDescription(java.util.Locale locale) {
		return _developerEntry.getProfileDescription(locale);
	}

	/**
	* Returns the localized profile description of this developer entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized profile description of this developer entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getProfileDescription(java.util.Locale locale,
		boolean useDefault) {
		return _developerEntry.getProfileDescription(locale, useDefault);
	}

	/**
	* Returns the localized profile description of this developer entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized profile description of this developer entry
	*/
	public java.lang.String getProfileDescription(java.lang.String languageId) {
		return _developerEntry.getProfileDescription(languageId);
	}

	/**
	* Returns the localized profile description of this developer entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized profile description of this developer entry
	*/
	public java.lang.String getProfileDescription(java.lang.String languageId,
		boolean useDefault) {
		return _developerEntry.getProfileDescription(languageId, useDefault);
	}

	public java.lang.String getProfileDescriptionCurrentLanguageId() {
		return _developerEntry.getProfileDescriptionCurrentLanguageId();
	}

	public java.lang.String getProfileDescriptionCurrentValue() {
		return _developerEntry.getProfileDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized profile descriptions of this developer entry.
	*
	* @return the locales and localized profile descriptions of this developer entry
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getProfileDescriptionMap() {
		return _developerEntry.getProfileDescriptionMap();
	}

	/**
	* Sets the profile description of this developer entry.
	*
	* @param profileDescription the profile description of this developer entry
	*/
	public void setProfileDescription(java.lang.String profileDescription) {
		_developerEntry.setProfileDescription(profileDescription);
	}

	/**
	* Sets the localized profile description of this developer entry in the language.
	*
	* @param profileDescription the localized profile description of this developer entry
	* @param locale the locale of the language
	*/
	public void setProfileDescription(java.lang.String profileDescription,
		java.util.Locale locale) {
		_developerEntry.setProfileDescription(profileDescription, locale);
	}

	/**
	* Sets the localized profile description of this developer entry in the language, and sets the default locale.
	*
	* @param profileDescription the localized profile description of this developer entry
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setProfileDescription(java.lang.String profileDescription,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_developerEntry.setProfileDescription(profileDescription, locale,
			defaultLocale);
	}

	public void setProfileDescriptionCurrentLanguageId(
		java.lang.String languageId) {
		_developerEntry.setProfileDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized profile descriptions of this developer entry from the map of locales and localized profile descriptions.
	*
	* @param profileDescriptionMap the locales and localized profile descriptions of this developer entry
	*/
	public void setProfileDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap) {
		_developerEntry.setProfileDescriptionMap(profileDescriptionMap);
	}

	/**
	* Sets the localized profile descriptions of this developer entry from the map of locales and localized profile descriptions, and sets the default locale.
	*
	* @param profileDescriptionMap the locales and localized profile descriptions of this developer entry
	* @param defaultLocale the default locale
	*/
	public void setProfileDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.util.Locale defaultLocale) {
		_developerEntry.setProfileDescriptionMap(profileDescriptionMap,
			defaultLocale);
	}

	/**
	* Returns the profile email address of this developer entry.
	*
	* @return the profile email address of this developer entry
	*/
	public java.lang.String getProfileEmailAddress() {
		return _developerEntry.getProfileEmailAddress();
	}

	/**
	* Sets the profile email address of this developer entry.
	*
	* @param profileEmailAddress the profile email address of this developer entry
	*/
	public void setProfileEmailAddress(java.lang.String profileEmailAddress) {
		_developerEntry.setProfileEmailAddress(profileEmailAddress);
	}

	/**
	* Returns the profile logo ID of this developer entry.
	*
	* @return the profile logo ID of this developer entry
	*/
	public long getProfileLogoId() {
		return _developerEntry.getProfileLogoId();
	}

	/**
	* Sets the profile logo ID of this developer entry.
	*
	* @param profileLogoId the profile logo ID of this developer entry
	*/
	public void setProfileLogoId(long profileLogoId) {
		_developerEntry.setProfileLogoId(profileLogoId);
	}

	/**
	* Returns the profile website of this developer entry.
	*
	* @return the profile website of this developer entry
	*/
	public java.lang.String getProfileWebsite() {
		return _developerEntry.getProfileWebsite();
	}

	/**
	* Sets the profile website of this developer entry.
	*
	* @param profileWebsite the profile website of this developer entry
	*/
	public void setProfileWebsite(java.lang.String profileWebsite) {
		_developerEntry.setProfileWebsite(profileWebsite);
	}

	/**
	* Returns the payment email address of this developer entry.
	*
	* @return the payment email address of this developer entry
	*/
	public java.lang.String getPaymentEmailAddress() {
		return _developerEntry.getPaymentEmailAddress();
	}

	/**
	* Sets the payment email address of this developer entry.
	*
	* @param paymentEmailAddress the payment email address of this developer entry
	*/
	public void setPaymentEmailAddress(java.lang.String paymentEmailAddress) {
		_developerEntry.setPaymentEmailAddress(paymentEmailAddress);
	}

	/**
	* Returns the google analytics key of this developer entry.
	*
	* @return the google analytics key of this developer entry
	*/
	public java.lang.String getGoogleAnalyticsKey() {
		return _developerEntry.getGoogleAnalyticsKey();
	}

	/**
	* Sets the google analytics key of this developer entry.
	*
	* @param googleAnalyticsKey the google analytics key of this developer entry
	*/
	public void setGoogleAnalyticsKey(java.lang.String googleAnalyticsKey) {
		_developerEntry.setGoogleAnalyticsKey(googleAnalyticsKey);
	}

	/**
	* Returns the subscription expiration date of this developer entry.
	*
	* @return the subscription expiration date of this developer entry
	*/
	public java.util.Date getSubscriptionExpirationDate() {
		return _developerEntry.getSubscriptionExpirationDate();
	}

	/**
	* Sets the subscription expiration date of this developer entry.
	*
	* @param subscriptionExpirationDate the subscription expiration date of this developer entry
	*/
	public void setSubscriptionExpirationDate(
		java.util.Date subscriptionExpirationDate) {
		_developerEntry.setSubscriptionExpirationDate(subscriptionExpirationDate);
	}

	/**
	* Returns the subscription status of this developer entry.
	*
	* @return the subscription status of this developer entry
	*/
	public int getSubscriptionStatus() {
		return _developerEntry.getSubscriptionStatus();
	}

	/**
	* Sets the subscription status of this developer entry.
	*
	* @param subscriptionStatus the subscription status of this developer entry
	*/
	public void setSubscriptionStatus(int subscriptionStatus) {
		_developerEntry.setSubscriptionStatus(subscriptionStatus);
	}

	/**
	* Returns the fatca withholding percentage of this developer entry.
	*
	* @return the fatca withholding percentage of this developer entry
	*/
	public double getFatcaWithholdingPercentage() {
		return _developerEntry.getFatcaWithholdingPercentage();
	}

	/**
	* Sets the fatca withholding percentage of this developer entry.
	*
	* @param fatcaWithholdingPercentage the fatca withholding percentage of this developer entry
	*/
	public void setFatcaWithholdingPercentage(double fatcaWithholdingPercentage) {
		_developerEntry.setFatcaWithholdingPercentage(fatcaWithholdingPercentage);
	}

	/**
	* Returns the dossiera account key of this developer entry.
	*
	* @return the dossiera account key of this developer entry
	*/
	public java.lang.String getDossieraAccountKey() {
		return _developerEntry.getDossieraAccountKey();
	}

	/**
	* Sets the dossiera account key of this developer entry.
	*
	* @param dossieraAccountKey the dossiera account key of this developer entry
	*/
	public void setDossieraAccountKey(java.lang.String dossieraAccountKey) {
		_developerEntry.setDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the type of this developer entry.
	*
	* @return the type of this developer entry
	*/
	public int getType() {
		return _developerEntry.getType();
	}

	/**
	* Sets the type of this developer entry.
	*
	* @param type the type of this developer entry
	*/
	public void setType(int type) {
		_developerEntry.setType(type);
	}

	/**
	* Returns the status of this developer entry.
	*
	* @return the status of this developer entry
	*/
	public int getStatus() {
		return _developerEntry.getStatus();
	}

	/**
	* Sets the status of this developer entry.
	*
	* @param status the status of this developer entry
	*/
	public void setStatus(int status) {
		_developerEntry.setStatus(status);
	}

	/**
	* Returns the status by user ID of this developer entry.
	*
	* @return the status by user ID of this developer entry
	*/
	public long getStatusByUserId() {
		return _developerEntry.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this developer entry.
	*
	* @param statusByUserId the status by user ID of this developer entry
	*/
	public void setStatusByUserId(long statusByUserId) {
		_developerEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this developer entry.
	*
	* @return the status by user uuid of this developer entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this developer entry.
	*
	* @param statusByUserUuid the status by user uuid of this developer entry
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_developerEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this developer entry.
	*
	* @return the status by user name of this developer entry
	*/
	public java.lang.String getStatusByUserName() {
		return _developerEntry.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this developer entry.
	*
	* @param statusByUserName the status by user name of this developer entry
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_developerEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this developer entry.
	*
	* @return the status date of this developer entry
	*/
	public java.util.Date getStatusDate() {
		return _developerEntry.getStatusDate();
	}

	/**
	* Sets the status date of this developer entry.
	*
	* @param statusDate the status date of this developer entry
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_developerEntry.setStatusDate(statusDate);
	}

	/**
	* Returns the status message of this developer entry.
	*
	* @return the status message of this developer entry
	*/
	public java.lang.String getStatusMessage() {
		return _developerEntry.getStatusMessage();
	}

	/**
	* Sets the status message of this developer entry.
	*
	* @param statusMessage the status message of this developer entry
	*/
	public void setStatusMessage(java.lang.String statusMessage) {
		_developerEntry.setStatusMessage(statusMessage);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _developerEntry.getApproved();
	}

	/**
	* Returns <code>true</code> if this developer entry is approved.
	*
	* @return <code>true</code> if this developer entry is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _developerEntry.isApproved();
	}

	/**
	* Returns <code>true</code> if this developer entry is denied.
	*
	* @return <code>true</code> if this developer entry is denied; <code>false</code> otherwise
	*/
	public boolean isDenied() {
		return _developerEntry.isDenied();
	}

	/**
	* Returns <code>true</code> if this developer entry is a draft.
	*
	* @return <code>true</code> if this developer entry is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _developerEntry.isDraft();
	}

	/**
	* Returns <code>true</code> if this developer entry is expired.
	*
	* @return <code>true</code> if this developer entry is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _developerEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this developer entry is inactive.
	*
	* @return <code>true</code> if this developer entry is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _developerEntry.isInactive();
	}

	/**
	* Returns <code>true</code> if this developer entry is incomplete.
	*
	* @return <code>true</code> if this developer entry is incomplete; <code>false</code> otherwise
	*/
	public boolean isIncomplete() {
		return _developerEntry.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this developer entry is pending.
	*
	* @return <code>true</code> if this developer entry is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _developerEntry.isPending();
	}

	/**
	* Returns <code>true</code> if this developer entry is scheduled.
	*
	* @return <code>true</code> if this developer entry is scheduled; <code>false</code> otherwise
	*/
	public boolean isScheduled() {
		return _developerEntry.isScheduled();
	}

	public boolean isNew() {
		return _developerEntry.isNew();
	}

	public void setNew(boolean n) {
		_developerEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _developerEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_developerEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _developerEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _developerEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_developerEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _developerEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_developerEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_developerEntry.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new DeveloperEntryWrapper((DeveloperEntry)_developerEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.DeveloperEntry developerEntry) {
		return _developerEntry.compareTo(developerEntry);
	}

	@Override
	public int hashCode() {
		return _developerEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.DeveloperEntry> toCacheModel() {
		return _developerEntry.toCacheModel();
	}

	public com.liferay.osb.model.DeveloperEntry toEscapedModel() {
		return new DeveloperEntryWrapper(_developerEntry.toEscapedModel());
	}

	public com.liferay.osb.model.DeveloperEntry toUnescapedModel() {
		return new DeveloperEntryWrapper(_developerEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _developerEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _developerEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_developerEntry.persist();
	}

	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getAddress();
	}

	public java.lang.String getCity() {
		return _developerEntry.getCity();
	}

	public com.liferay.portal.model.Country getCountry() {
		return _developerEntry.getCountry();
	}

	public java.lang.String getCountryCode() {
		return _developerEntry.getCountryCode();
	}

	public java.util.List<com.liferay.osb.model.AssetAttachment> getDocumentAssetAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getDocumentAssetAttachments();
	}

	public double getFatcaWithholdingPercentageValue()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getFatcaWithholdingPercentageValue();
	}

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getGroup();
	}

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getGroupId();
	}

	public java.lang.String getName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getName();
	}

	public java.lang.String getProfileURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntry.getProfileURL(themeDisplay);
	}

	public com.liferay.portal.model.Region getRegion() {
		return _developerEntry.getRegion();
	}

	public long getRegionId() {
		return _developerEntry.getRegionId();
	}

	public java.lang.String getStatusLabel() {
		return _developerEntry.getStatusLabel();
	}

	public java.lang.String getStreet1() {
		return _developerEntry.getStreet1();
	}

	public java.lang.String getStreet2() {
		return _developerEntry.getStreet2();
	}

	public java.lang.String getStreet3() {
		return _developerEntry.getStreet3();
	}

	public java.lang.String getZip() {
		return _developerEntry.getZip();
	}

	public boolean isCompany() {
		return _developerEntry.isCompany();
	}

	public boolean isDomainStatusApproved() {
		return _developerEntry.isDomainStatusApproved();
	}

	public boolean isLiferayInc() {
		return _developerEntry.isLiferayInc();
	}

	public boolean isSubscriptionApproved() {
		return _developerEntry.isSubscriptionApproved();
	}

	public boolean isSubscriptionExpired() {
		return _developerEntry.isSubscriptionExpired();
	}

	public boolean isUser() {
		return _developerEntry.isUser();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeveloperEntryWrapper)) {
			return false;
		}

		DeveloperEntryWrapper developerEntryWrapper = (DeveloperEntryWrapper)obj;

		if (Validator.equals(_developerEntry,
					developerEntryWrapper._developerEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DeveloperEntry getWrappedDeveloperEntry() {
		return _developerEntry;
	}

	public DeveloperEntry getWrappedModel() {
		return _developerEntry;
	}

	public void resetOriginalValues() {
		_developerEntry.resetOriginalValues();
	}

	private DeveloperEntry _developerEntry;
}