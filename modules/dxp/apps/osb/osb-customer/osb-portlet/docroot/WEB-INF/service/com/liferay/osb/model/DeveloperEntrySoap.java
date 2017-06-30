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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.DeveloperEntryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.DeveloperEntryServiceSoap
 * @generated
 */
public class DeveloperEntrySoap implements Serializable {
	public static DeveloperEntrySoap toSoapModel(DeveloperEntry model) {
		DeveloperEntrySoap soapModel = new DeveloperEntrySoap();

		soapModel.setDeveloperEntryId(model.getDeveloperEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setScreenName(model.getScreenName());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setMiddleName(model.getMiddleName());
		soapModel.setLastName(model.getLastName());
		soapModel.setLegalEntityName(model.getLegalEntityName());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setContractEntryId(model.getContractEntryId());
		soapModel.setPhoneNumber(model.getPhoneNumber());
		soapModel.setFaxNumber(model.getFaxNumber());
		soapModel.setDomainName(model.getDomainName());
		soapModel.setDomainStatus(model.getDomainStatus());
		soapModel.setAddressId(model.getAddressId());
		soapModel.setCountryId(model.getCountryId());
		soapModel.setProfileDescription(model.getProfileDescription());
		soapModel.setProfileEmailAddress(model.getProfileEmailAddress());
		soapModel.setProfileLogoId(model.getProfileLogoId());
		soapModel.setProfileWebsite(model.getProfileWebsite());
		soapModel.setPaymentEmailAddress(model.getPaymentEmailAddress());
		soapModel.setGoogleAnalyticsKey(model.getGoogleAnalyticsKey());
		soapModel.setSubscriptionExpirationDate(model.getSubscriptionExpirationDate());
		soapModel.setSubscriptionStatus(model.getSubscriptionStatus());
		soapModel.setFatcaWithholdingPercentage(model.getFatcaWithholdingPercentage());
		soapModel.setDossieraAccountKey(model.getDossieraAccountKey());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStatusMessage(model.getStatusMessage());

		return soapModel;
	}

	public static DeveloperEntrySoap[] toSoapModels(DeveloperEntry[] models) {
		DeveloperEntrySoap[] soapModels = new DeveloperEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeveloperEntrySoap[][] toSoapModels(DeveloperEntry[][] models) {
		DeveloperEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeveloperEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeveloperEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeveloperEntrySoap[] toSoapModels(List<DeveloperEntry> models) {
		List<DeveloperEntrySoap> soapModels = new ArrayList<DeveloperEntrySoap>(models.size());

		for (DeveloperEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeveloperEntrySoap[soapModels.size()]);
	}

	public DeveloperEntrySoap() {
	}

	public long getPrimaryKey() {
		return _developerEntryId;
	}

	public void setPrimaryKey(long pk) {
		setDeveloperEntryId(pk);
	}

	public long getDeveloperEntryId() {
		return _developerEntryId;
	}

	public void setDeveloperEntryId(long developerEntryId) {
		_developerEntryId = developerEntryId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getMiddleName() {
		return _middleName;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getLegalEntityName() {
		return _legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		_legalEntityName = legalEntityName;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return _faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		_faxNumber = faxNumber;
	}

	public String getDomainName() {
		return _domainName;
	}

	public void setDomainName(String domainName) {
		_domainName = domainName;
	}

	public int getDomainStatus() {
		return _domainStatus;
	}

	public void setDomainStatus(int domainStatus) {
		_domainStatus = domainStatus;
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public String getProfileDescription() {
		return _profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		_profileDescription = profileDescription;
	}

	public String getProfileEmailAddress() {
		return _profileEmailAddress;
	}

	public void setProfileEmailAddress(String profileEmailAddress) {
		_profileEmailAddress = profileEmailAddress;
	}

	public long getProfileLogoId() {
		return _profileLogoId;
	}

	public void setProfileLogoId(long profileLogoId) {
		_profileLogoId = profileLogoId;
	}

	public String getProfileWebsite() {
		return _profileWebsite;
	}

	public void setProfileWebsite(String profileWebsite) {
		_profileWebsite = profileWebsite;
	}

	public String getPaymentEmailAddress() {
		return _paymentEmailAddress;
	}

	public void setPaymentEmailAddress(String paymentEmailAddress) {
		_paymentEmailAddress = paymentEmailAddress;
	}

	public String getGoogleAnalyticsKey() {
		return _googleAnalyticsKey;
	}

	public void setGoogleAnalyticsKey(String googleAnalyticsKey) {
		_googleAnalyticsKey = googleAnalyticsKey;
	}

	public Date getSubscriptionExpirationDate() {
		return _subscriptionExpirationDate;
	}

	public void setSubscriptionExpirationDate(Date subscriptionExpirationDate) {
		_subscriptionExpirationDate = subscriptionExpirationDate;
	}

	public int getSubscriptionStatus() {
		return _subscriptionStatus;
	}

	public void setSubscriptionStatus(int subscriptionStatus) {
		_subscriptionStatus = subscriptionStatus;
	}

	public double getFatcaWithholdingPercentage() {
		return _fatcaWithholdingPercentage;
	}

	public void setFatcaWithholdingPercentage(double fatcaWithholdingPercentage) {
		_fatcaWithholdingPercentage = fatcaWithholdingPercentage;
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getStatusMessage() {
		return _statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	private long _developerEntryId;
	private long _userId;
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
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
}