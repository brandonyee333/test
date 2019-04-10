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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountEntryServiceSoap
 * @generated
 */
@ProviderType
public class AccountEntrySoap implements Serializable {
	public static AccountEntrySoap toSoapModel(AccountEntry model) {
		AccountEntrySoap soapModel = new AccountEntrySoap();

		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCorpProjectUuid(model.getCorpProjectUuid());
		soapModel.setCorpProjectId(model.getCorpProjectId());
		soapModel.setDossieraAccountKey(model.getDossieraAccountKey());
		soapModel.setCorpEntryName(model.getCorpEntryName());
		soapModel.setName(model.getName());
		soapModel.setCode(model.getCode());
		soapModel.setRedirectAccountEntryId(model.getRedirectAccountEntryId());
		soapModel.setType(model.getType());
		soapModel.setIndustry(model.getIndustry());
		soapModel.setCountryId(model.getCountryId());
		soapModel.setPartnerEntryId(model.getPartnerEntryId());
		soapModel.setPartnerManagedSupport(model.getPartnerManagedSupport());
		soapModel.setTier(model.getTier());
		soapModel.setMaxCustomers(model.getMaxCustomers());
		soapModel.setInstructions(model.getInstructions());
		soapModel.setNotes(model.getNotes());
		soapModel.setHighestSupportResponseId(model.getHighestSupportResponseId());
		soapModel.setActiveSupport(model.getActiveSupport());
		soapModel.setActiveTicketSupport(model.getActiveTicketSupport());
		soapModel.setLastAuditDate(model.getLastAuditDate());
		soapModel.setLastZendeskAuditDate(model.getLastZendeskAuditDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStatusMessage(model.getStatusMessage());

		return soapModel;
	}

	public static AccountEntrySoap[] toSoapModels(AccountEntry[] models) {
		AccountEntrySoap[] soapModels = new AccountEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountEntrySoap[][] toSoapModels(AccountEntry[][] models) {
		AccountEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountEntrySoap[] toSoapModels(List<AccountEntry> models) {
		List<AccountEntrySoap> soapModels = new ArrayList<AccountEntrySoap>(models.size());

		for (AccountEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountEntrySoap[soapModels.size()]);
	}

	public AccountEntrySoap() {
	}

	public long getPrimaryKey() {
		return _accountEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAccountEntryId(pk);
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getCorpProjectUuid() {
		return _corpProjectUuid;
	}

	public void setCorpProjectUuid(String corpProjectUuid) {
		_corpProjectUuid = corpProjectUuid;
	}

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
	}

	public String getDossieraAccountKey() {
		return _dossieraAccountKey;
	}

	public void setDossieraAccountKey(String dossieraAccountKey) {
		_dossieraAccountKey = dossieraAccountKey;
	}

	public String getCorpEntryName() {
		return _corpEntryName;
	}

	public void setCorpEntryName(String corpEntryName) {
		_corpEntryName = corpEntryName;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public long getRedirectAccountEntryId() {
		return _redirectAccountEntryId;
	}

	public void setRedirectAccountEntryId(long redirectAccountEntryId) {
		_redirectAccountEntryId = redirectAccountEntryId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getIndustry() {
		return _industry;
	}

	public void setIndustry(int industry) {
		_industry = industry;
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;
	}

	public boolean getPartnerManagedSupport() {
		return _partnerManagedSupport;
	}

	public boolean isPartnerManagedSupport() {
		return _partnerManagedSupport;
	}

	public void setPartnerManagedSupport(boolean partnerManagedSupport) {
		_partnerManagedSupport = partnerManagedSupport;
	}

	public int getTier() {
		return _tier;
	}

	public void setTier(int tier) {
		_tier = tier;
	}

	public int getMaxCustomers() {
		return _maxCustomers;
	}

	public void setMaxCustomers(int maxCustomers) {
		_maxCustomers = maxCustomers;
	}

	public String getInstructions() {
		return _instructions;
	}

	public void setInstructions(String instructions) {
		_instructions = instructions;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	public long getHighestSupportResponseId() {
		return _highestSupportResponseId;
	}

	public void setHighestSupportResponseId(long highestSupportResponseId) {
		_highestSupportResponseId = highestSupportResponseId;
	}

	public boolean getActiveSupport() {
		return _activeSupport;
	}

	public boolean isActiveSupport() {
		return _activeSupport;
	}

	public void setActiveSupport(boolean activeSupport) {
		_activeSupport = activeSupport;
	}

	public boolean getActiveTicketSupport() {
		return _activeTicketSupport;
	}

	public boolean isActiveTicketSupport() {
		return _activeTicketSupport;
	}

	public void setActiveTicketSupport(boolean activeTicketSupport) {
		_activeTicketSupport = activeTicketSupport;
	}

	public Date getLastAuditDate() {
		return _lastAuditDate;
	}

	public void setLastAuditDate(Date lastAuditDate) {
		_lastAuditDate = lastAuditDate;
	}

	public Date getLastZendeskAuditDate() {
		return _lastZendeskAuditDate;
	}

	public void setLastZendeskAuditDate(Date lastZendeskAuditDate) {
		_lastZendeskAuditDate = lastZendeskAuditDate;
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

	private long _accountEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private String _corpProjectUuid;
	private long _corpProjectId;
	private String _dossieraAccountKey;
	private String _corpEntryName;
	private String _name;
	private String _code;
	private long _redirectAccountEntryId;
	private int _type;
	private int _industry;
	private long _countryId;
	private long _partnerEntryId;
	private boolean _partnerManagedSupport;
	private int _tier;
	private int _maxCustomers;
	private String _instructions;
	private String _notes;
	private long _highestSupportResponseId;
	private boolean _activeSupport;
	private boolean _activeTicketSupport;
	private Date _lastAuditDate;
	private Date _lastZendeskAuditDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
}