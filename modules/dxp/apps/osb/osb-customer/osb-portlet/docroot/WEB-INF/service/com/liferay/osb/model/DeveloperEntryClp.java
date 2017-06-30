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

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class DeveloperEntryClp extends BaseModelImpl<DeveloperEntry>
	implements DeveloperEntry {
	public DeveloperEntryClp() {
	}

	public Class<?> getModelClass() {
		return DeveloperEntry.class;
	}

	public String getModelClassName() {
		return DeveloperEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _developerEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setDeveloperEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_developerEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	@Override
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

	public long getDeveloperEntryId() {
		return _developerEntryId;
	}

	public void setDeveloperEntryId(long developerEntryId) {
		_developerEntryId = developerEntryId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDeveloperEntryId",
						long.class);

				method.invoke(_developerEntryRemoteModel, developerEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_developerEntryRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_developerEntryRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_developerEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setScreenName", String.class);

				method.invoke(_developerEntryRemoteModel, screenName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstName", String.class);

				method.invoke(_developerEntryRemoteModel, firstName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getMiddleName() {
		return _middleName;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMiddleName", String.class);

				method.invoke(_developerEntryRemoteModel, middleName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLastName", String.class);

				method.invoke(_developerEntryRemoteModel, lastName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLegalEntityName",
						String.class);

				method.invoke(_developerEntryRemoteModel, legalEntityName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEmailAddress", String.class);

				method.invoke(_developerEntryRemoteModel, emailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setContractEntryId", long.class);

				method.invoke(_developerEntryRemoteModel, contractEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPhoneNumber", String.class);

				method.invoke(_developerEntryRemoteModel, phoneNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFaxNumber() {
		return _faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		_faxNumber = faxNumber;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFaxNumber", String.class);

				method.invoke(_developerEntryRemoteModel, faxNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDomainName() {
		return _domainName;
	}

	public void setDomainName(String domainName) {
		_domainName = domainName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDomainName", String.class);

				method.invoke(_developerEntryRemoteModel, domainName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getDomainStatus() {
		return _domainStatus;
	}

	public void setDomainStatus(int domainStatus) {
		_domainStatus = domainStatus;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDomainStatus", int.class);

				method.invoke(_developerEntryRemoteModel, domainStatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAddressId", long.class);

				method.invoke(_developerEntryRemoteModel, addressId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_developerEntryRemoteModel, countryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getProfileDescription() {
		return _profileDescription;
	}

	public String getProfileDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getProfileDescription(languageId);
	}

	public String getProfileDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getProfileDescription(languageId, useDefault);
	}

	public String getProfileDescription(String languageId) {
		return LocalizationUtil.getLocalization(getProfileDescription(),
			languageId);
	}

	public String getProfileDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getProfileDescription(),
			languageId, useDefault);
	}

	public String getProfileDescriptionCurrentLanguageId() {
		return _profileDescriptionCurrentLanguageId;
	}

	public String getProfileDescriptionCurrentValue() {
		Locale locale = getLocale(_profileDescriptionCurrentLanguageId);

		return getProfileDescription(locale);
	}

	public Map<Locale, String> getProfileDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getProfileDescription());
	}

	public void setProfileDescription(String profileDescription) {
		_profileDescription = profileDescription;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProfileDescription",
						String.class);

				method.invoke(_developerEntryRemoteModel, profileDescription);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setProfileDescription(String profileDescription, Locale locale) {
		setProfileDescription(profileDescription, locale,
			LocaleUtil.getDefault());
	}

	public void setProfileDescription(String profileDescription, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(profileDescription)) {
			setProfileDescription(LocalizationUtil.updateLocalization(
					getProfileDescription(), "ProfileDescription",
					profileDescription, languageId, defaultLanguageId));
		}
		else {
			setProfileDescription(LocalizationUtil.removeLocalization(
					getProfileDescription(), "ProfileDescription", languageId));
		}
	}

	public void setProfileDescriptionCurrentLanguageId(String languageId) {
		_profileDescriptionCurrentLanguageId = languageId;
	}

	public void setProfileDescriptionMap(
		Map<Locale, String> profileDescriptionMap) {
		setProfileDescriptionMap(profileDescriptionMap, LocaleUtil.getDefault());
	}

	public void setProfileDescriptionMap(
		Map<Locale, String> profileDescriptionMap, Locale defaultLocale) {
		if (profileDescriptionMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (Locale locale : locales) {
				String profileDescription = profileDescriptionMap.get(locale);

				setProfileDescription(profileDescription, locale, defaultLocale);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public String getProfileEmailAddress() {
		return _profileEmailAddress;
	}

	public void setProfileEmailAddress(String profileEmailAddress) {
		_profileEmailAddress = profileEmailAddress;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProfileEmailAddress",
						String.class);

				method.invoke(_developerEntryRemoteModel, profileEmailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getProfileLogoId() {
		return _profileLogoId;
	}

	public void setProfileLogoId(long profileLogoId) {
		_profileLogoId = profileLogoId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProfileLogoId", long.class);

				method.invoke(_developerEntryRemoteModel, profileLogoId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getProfileWebsite() {
		return _profileWebsite;
	}

	public void setProfileWebsite(String profileWebsite) {
		_profileWebsite = profileWebsite;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProfileWebsite",
						String.class);

				method.invoke(_developerEntryRemoteModel, profileWebsite);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getPaymentEmailAddress() {
		return _paymentEmailAddress;
	}

	public void setPaymentEmailAddress(String paymentEmailAddress) {
		_paymentEmailAddress = paymentEmailAddress;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPaymentEmailAddress",
						String.class);

				method.invoke(_developerEntryRemoteModel, paymentEmailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getGoogleAnalyticsKey() {
		return _googleAnalyticsKey;
	}

	public void setGoogleAnalyticsKey(String googleAnalyticsKey) {
		_googleAnalyticsKey = googleAnalyticsKey;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setGoogleAnalyticsKey",
						String.class);

				method.invoke(_developerEntryRemoteModel, googleAnalyticsKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getSubscriptionExpirationDate() {
		return _subscriptionExpirationDate;
	}

	public void setSubscriptionExpirationDate(Date subscriptionExpirationDate) {
		_subscriptionExpirationDate = subscriptionExpirationDate;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSubscriptionExpirationDate",
						Date.class);

				method.invoke(_developerEntryRemoteModel,
					subscriptionExpirationDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getSubscriptionStatus() {
		return _subscriptionStatus;
	}

	public void setSubscriptionStatus(int subscriptionStatus) {
		_subscriptionStatus = subscriptionStatus;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSubscriptionStatus",
						int.class);

				method.invoke(_developerEntryRemoteModel, subscriptionStatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getFatcaWithholdingPercentage() {
		return _fatcaWithholdingPercentage;
	}

	public void setFatcaWithholdingPercentage(double fatcaWithholdingPercentage) {
		_fatcaWithholdingPercentage = fatcaWithholdingPercentage;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFatcaWithholdingPercentage",
						double.class);

				method.invoke(_developerEntryRemoteModel,
					fatcaWithholdingPercentage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDossieraAccountKey",
						String.class);

				method.invoke(_developerEntryRemoteModel, dossieraAccountKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_developerEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_developerEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_developerEntryRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_developerEntryRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_developerEntryRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getStatusMessage() {
		return _statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;

		if (_developerEntryRemoteModel != null) {
			try {
				Class<?> clazz = _developerEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusMessage", String.class);

				method.invoke(_developerEntryRemoteModel, statusMessage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public double getFatcaWithholdingPercentageValue() {
		try {
			String methodName = "getFatcaWithholdingPercentageValue";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Double returnObj = (Double)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isSubscriptionExpired() {
		try {
			String methodName = "isSubscriptionExpired";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getCountryCode() {
		try {
			String methodName = "getCountryCode";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long getRegionId() {
		try {
			String methodName = "getRegionId";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getStreet3() {
		try {
			String methodName = "getStreet3";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.Region getRegion() {
		try {
			String methodName = "getRegion";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.Region returnObj = (com.liferay.portal.model.Region)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getStatusLabel() {
		try {
			String methodName = "getStatusLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getStreet1() {
		try {
			String methodName = "getStreet1";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isSubscriptionApproved() {
		try {
			String methodName = "isSubscriptionApproved";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getStreet2() {
		try {
			String methodName = "getStreet2";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getCity() {
		try {
			String methodName = "getCity";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isCompany() {
		try {
			String methodName = "isCompany";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getProfileURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		try {
			String methodName = "getProfileURL";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.theme.ThemeDisplay.class
				};

			Object[] parameterValues = new Object[] { themeDisplay };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isLiferayInc() {
		try {
			String methodName = "isLiferayInc";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getName() {
		try {
			String methodName = "getName";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.AssetAttachment> getDocumentAssetAttachments() {
		try {
			String methodName = "getDocumentAssetAttachments";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.AssetAttachment> returnObj = (java.util.List<com.liferay.osb.model.AssetAttachment>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long getGroupId() {
		try {
			String methodName = "getGroupId";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.Address getAddress() {
		try {
			String methodName = "getAddress";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.Address returnObj = (com.liferay.portal.model.Address)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.Country getCountry() {
		try {
			String methodName = "getCountry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.Country returnObj = (com.liferay.portal.model.Country)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.Group getGroup() {
		try {
			String methodName = "getGroup";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.Group returnObj = (com.liferay.portal.model.Group)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isUser() {
		try {
			String methodName = "isUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isDomainStatusApproved() {
		try {
			String methodName = "isDomainStatusApproved";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getZip() {
		try {
			String methodName = "getZip";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	/**
	 * @deprecated {@link #isApproved}
	 */
	public boolean getApproved() {
		return isApproved();
	}

	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getDeveloperEntryRemoteModel() {
		return _developerEntryRemoteModel;
	}

	public void setDeveloperEntryRemoteModel(
		BaseModel<?> developerEntryRemoteModel) {
		_developerEntryRemoteModel = developerEntryRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _developerEntryRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_developerEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			DeveloperEntryLocalServiceUtil.addDeveloperEntry(this);
		}
		else {
			DeveloperEntryLocalServiceUtil.updateDeveloperEntry(this);
		}
	}

	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		setProfileDescription(getProfileDescription(defaultImportLocale),
			defaultImportLocale, defaultImportLocale);
	}

	@Override
	public DeveloperEntry toEscapedModel() {
		return (DeveloperEntry)ProxyUtil.newProxyInstance(DeveloperEntry.class.getClassLoader(),
			new Class[] { DeveloperEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	public DeveloperEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		DeveloperEntryClp clone = new DeveloperEntryClp();

		clone.setDeveloperEntryId(getDeveloperEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setScreenName(getScreenName());
		clone.setFirstName(getFirstName());
		clone.setMiddleName(getMiddleName());
		clone.setLastName(getLastName());
		clone.setLegalEntityName(getLegalEntityName());
		clone.setEmailAddress(getEmailAddress());
		clone.setContractEntryId(getContractEntryId());
		clone.setPhoneNumber(getPhoneNumber());
		clone.setFaxNumber(getFaxNumber());
		clone.setDomainName(getDomainName());
		clone.setDomainStatus(getDomainStatus());
		clone.setAddressId(getAddressId());
		clone.setCountryId(getCountryId());
		clone.setProfileDescription(getProfileDescription());
		clone.setProfileEmailAddress(getProfileEmailAddress());
		clone.setProfileLogoId(getProfileLogoId());
		clone.setProfileWebsite(getProfileWebsite());
		clone.setPaymentEmailAddress(getPaymentEmailAddress());
		clone.setGoogleAnalyticsKey(getGoogleAnalyticsKey());
		clone.setSubscriptionExpirationDate(getSubscriptionExpirationDate());
		clone.setSubscriptionStatus(getSubscriptionStatus());
		clone.setFatcaWithholdingPercentage(getFatcaWithholdingPercentage());
		clone.setDossieraAccountKey(getDossieraAccountKey());
		clone.setType(getType());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setStatusMessage(getStatusMessage());

		return clone;
	}

	public int compareTo(DeveloperEntry developerEntry) {
		long primaryKey = developerEntry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeveloperEntryClp)) {
			return false;
		}

		DeveloperEntryClp developerEntry = (DeveloperEntryClp)obj;

		long primaryKey = developerEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(67);

		sb.append("{developerEntryId=");
		sb.append(getDeveloperEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", screenName=");
		sb.append(getScreenName());
		sb.append(", firstName=");
		sb.append(getFirstName());
		sb.append(", middleName=");
		sb.append(getMiddleName());
		sb.append(", lastName=");
		sb.append(getLastName());
		sb.append(", legalEntityName=");
		sb.append(getLegalEntityName());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", contractEntryId=");
		sb.append(getContractEntryId());
		sb.append(", phoneNumber=");
		sb.append(getPhoneNumber());
		sb.append(", faxNumber=");
		sb.append(getFaxNumber());
		sb.append(", domainName=");
		sb.append(getDomainName());
		sb.append(", domainStatus=");
		sb.append(getDomainStatus());
		sb.append(", addressId=");
		sb.append(getAddressId());
		sb.append(", countryId=");
		sb.append(getCountryId());
		sb.append(", profileDescription=");
		sb.append(getProfileDescription());
		sb.append(", profileEmailAddress=");
		sb.append(getProfileEmailAddress());
		sb.append(", profileLogoId=");
		sb.append(getProfileLogoId());
		sb.append(", profileWebsite=");
		sb.append(getProfileWebsite());
		sb.append(", paymentEmailAddress=");
		sb.append(getPaymentEmailAddress());
		sb.append(", googleAnalyticsKey=");
		sb.append(getGoogleAnalyticsKey());
		sb.append(", subscriptionExpirationDate=");
		sb.append(getSubscriptionExpirationDate());
		sb.append(", subscriptionStatus=");
		sb.append(getSubscriptionStatus());
		sb.append(", fatcaWithholdingPercentage=");
		sb.append(getFatcaWithholdingPercentage());
		sb.append(", dossieraAccountKey=");
		sb.append(getDossieraAccountKey());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", statusMessage=");
		sb.append(getStatusMessage());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(103);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.DeveloperEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>developerEntryId</column-name><column-value><![CDATA[");
		sb.append(getDeveloperEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>screenName</column-name><column-value><![CDATA[");
		sb.append(getScreenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstName</column-name><column-value><![CDATA[");
		sb.append(getFirstName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>middleName</column-name><column-value><![CDATA[");
		sb.append(getMiddleName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastName</column-name><column-value><![CDATA[");
		sb.append(getLastName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>legalEntityName</column-name><column-value><![CDATA[");
		sb.append(getLegalEntityName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractEntryId</column-name><column-value><![CDATA[");
		sb.append(getContractEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>phoneNumber</column-name><column-value><![CDATA[");
		sb.append(getPhoneNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>faxNumber</column-name><column-value><![CDATA[");
		sb.append(getFaxNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>domainName</column-name><column-value><![CDATA[");
		sb.append(getDomainName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>domainStatus</column-name><column-value><![CDATA[");
		sb.append(getDomainStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>addressId</column-name><column-value><![CDATA[");
		sb.append(getAddressId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>profileDescription</column-name><column-value><![CDATA[");
		sb.append(getProfileDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>profileEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getProfileEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>profileLogoId</column-name><column-value><![CDATA[");
		sb.append(getProfileLogoId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>profileWebsite</column-name><column-value><![CDATA[");
		sb.append(getProfileWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paymentEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getPaymentEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>googleAnalyticsKey</column-name><column-value><![CDATA[");
		sb.append(getGoogleAnalyticsKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subscriptionExpirationDate</column-name><column-value><![CDATA[");
		sb.append(getSubscriptionExpirationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subscriptionStatus</column-name><column-value><![CDATA[");
		sb.append(getSubscriptionStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fatcaWithholdingPercentage</column-name><column-value><![CDATA[");
		sb.append(getFatcaWithholdingPercentage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dossieraAccountKey</column-name><column-value><![CDATA[");
		sb.append(getDossieraAccountKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusMessage</column-name><column-value><![CDATA[");
		sb.append(getStatusMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _developerEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private String _screenName;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _legalEntityName;
	private String _emailAddress;
	private long _contractEntryId;
	private String _phoneNumber;
	private String _faxNumber;
	private String _domainName;
	private int _domainStatus;
	private long _addressId;
	private long _countryId;
	private String _profileDescription;
	private String _profileDescriptionCurrentLanguageId;
	private String _profileEmailAddress;
	private long _profileLogoId;
	private String _profileWebsite;
	private String _paymentEmailAddress;
	private String _googleAnalyticsKey;
	private Date _subscriptionExpirationDate;
	private int _subscriptionStatus;
	private double _fatcaWithholdingPercentage;
	private String _dossieraAccountKey;
	private int _type;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
	private BaseModel<?> _developerEntryRemoteModel;
}