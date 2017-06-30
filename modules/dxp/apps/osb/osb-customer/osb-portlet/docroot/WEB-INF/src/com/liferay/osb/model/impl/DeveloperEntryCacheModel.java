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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.DeveloperEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DeveloperEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DeveloperEntry
 * @generated
 */
public class DeveloperEntryCacheModel implements CacheModel<DeveloperEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(67);

		sb.append("{developerEntryId=");
		sb.append(developerEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", middleName=");
		sb.append(middleName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", legalEntityName=");
		sb.append(legalEntityName);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", contractEntryId=");
		sb.append(contractEntryId);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", faxNumber=");
		sb.append(faxNumber);
		sb.append(", domainName=");
		sb.append(domainName);
		sb.append(", domainStatus=");
		sb.append(domainStatus);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", profileDescription=");
		sb.append(profileDescription);
		sb.append(", profileEmailAddress=");
		sb.append(profileEmailAddress);
		sb.append(", profileLogoId=");
		sb.append(profileLogoId);
		sb.append(", profileWebsite=");
		sb.append(profileWebsite);
		sb.append(", paymentEmailAddress=");
		sb.append(paymentEmailAddress);
		sb.append(", googleAnalyticsKey=");
		sb.append(googleAnalyticsKey);
		sb.append(", subscriptionExpirationDate=");
		sb.append(subscriptionExpirationDate);
		sb.append(", subscriptionStatus=");
		sb.append(subscriptionStatus);
		sb.append(", fatcaWithholdingPercentage=");
		sb.append(fatcaWithholdingPercentage);
		sb.append(", dossieraAccountKey=");
		sb.append(dossieraAccountKey);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", statusMessage=");
		sb.append(statusMessage);
		sb.append("}");

		return sb.toString();
	}

	public DeveloperEntry toEntityModel() {
		DeveloperEntryImpl developerEntryImpl = new DeveloperEntryImpl();

		developerEntryImpl.setDeveloperEntryId(developerEntryId);
		developerEntryImpl.setUserId(userId);

		if (userName == null) {
			developerEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			developerEntryImpl.setCreateDate(null);
		}
		else {
			developerEntryImpl.setCreateDate(new Date(createDate));
		}

		if (screenName == null) {
			developerEntryImpl.setScreenName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setScreenName(screenName);
		}

		if (firstName == null) {
			developerEntryImpl.setFirstName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setFirstName(firstName);
		}

		if (middleName == null) {
			developerEntryImpl.setMiddleName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setMiddleName(middleName);
		}

		if (lastName == null) {
			developerEntryImpl.setLastName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setLastName(lastName);
		}

		if (legalEntityName == null) {
			developerEntryImpl.setLegalEntityName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setLegalEntityName(legalEntityName);
		}

		if (emailAddress == null) {
			developerEntryImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setEmailAddress(emailAddress);
		}

		developerEntryImpl.setContractEntryId(contractEntryId);

		if (phoneNumber == null) {
			developerEntryImpl.setPhoneNumber(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setPhoneNumber(phoneNumber);
		}

		if (faxNumber == null) {
			developerEntryImpl.setFaxNumber(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setFaxNumber(faxNumber);
		}

		if (domainName == null) {
			developerEntryImpl.setDomainName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setDomainName(domainName);
		}

		developerEntryImpl.setDomainStatus(domainStatus);
		developerEntryImpl.setAddressId(addressId);
		developerEntryImpl.setCountryId(countryId);

		if (profileDescription == null) {
			developerEntryImpl.setProfileDescription(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setProfileDescription(profileDescription);
		}

		if (profileEmailAddress == null) {
			developerEntryImpl.setProfileEmailAddress(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setProfileEmailAddress(profileEmailAddress);
		}

		developerEntryImpl.setProfileLogoId(profileLogoId);

		if (profileWebsite == null) {
			developerEntryImpl.setProfileWebsite(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setProfileWebsite(profileWebsite);
		}

		if (paymentEmailAddress == null) {
			developerEntryImpl.setPaymentEmailAddress(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setPaymentEmailAddress(paymentEmailAddress);
		}

		if (googleAnalyticsKey == null) {
			developerEntryImpl.setGoogleAnalyticsKey(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setGoogleAnalyticsKey(googleAnalyticsKey);
		}

		if (subscriptionExpirationDate == Long.MIN_VALUE) {
			developerEntryImpl.setSubscriptionExpirationDate(null);
		}
		else {
			developerEntryImpl.setSubscriptionExpirationDate(new Date(
					subscriptionExpirationDate));
		}

		developerEntryImpl.setSubscriptionStatus(subscriptionStatus);
		developerEntryImpl.setFatcaWithholdingPercentage(fatcaWithholdingPercentage);

		if (dossieraAccountKey == null) {
			developerEntryImpl.setDossieraAccountKey(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		developerEntryImpl.setType(type);
		developerEntryImpl.setStatus(status);
		developerEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			developerEntryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			developerEntryImpl.setStatusDate(null);
		}
		else {
			developerEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			developerEntryImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			developerEntryImpl.setStatusMessage(statusMessage);
		}

		developerEntryImpl.resetOriginalValues();

		return developerEntryImpl;
	}

	public long developerEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public String screenName;
	public String firstName;
	public String middleName;
	public String lastName;
	public String legalEntityName;
	public String emailAddress;
	public long contractEntryId;
	public String phoneNumber;
	public String faxNumber;
	public String domainName;
	public int domainStatus;
	public long addressId;
	public long countryId;
	public String profileDescription;
	public String profileEmailAddress;
	public long profileLogoId;
	public String profileWebsite;
	public String paymentEmailAddress;
	public String googleAnalyticsKey;
	public long subscriptionExpirationDate;
	public int subscriptionStatus;
	public double fatcaWithholdingPercentage;
	public String dossieraAccountKey;
	public int type;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
}