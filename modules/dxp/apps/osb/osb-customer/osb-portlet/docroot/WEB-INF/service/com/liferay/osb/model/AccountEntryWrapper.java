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
 * This class is a wrapper for {@link AccountEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntry
 * @generated
 */
@ProviderType
public class AccountEntryWrapper implements AccountEntry,
	ModelWrapper<AccountEntry> {
	public AccountEntryWrapper(AccountEntry accountEntry) {
		_accountEntry = accountEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpProjectUuid", getCorpProjectUuid());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("corpEntryName", getCorpEntryName());
		attributes.put("name", getName());
		attributes.put("code", getCode());
		attributes.put("redirectAccountEntryId", getRedirectAccountEntryId());
		attributes.put("type", getType());
		attributes.put("industry", getIndustry());
		attributes.put("countryId", getCountryId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("partnerManagedSupport", getPartnerManagedSupport());
		attributes.put("tier", getTier());
		attributes.put("maxCustomers", getMaxCustomers());
		attributes.put("instructions", getInstructions());
		attributes.put("notes", getNotes());
		attributes.put("highestSupportResponseId", getHighestSupportResponseId());
		attributes.put("activeSupport", getActiveSupport());
		attributes.put("activeTicketSupport", getActiveTicketSupport());
		attributes.put("lastAuditDate", getLastAuditDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String corpProjectUuid = (String)attributes.get("corpProjectUuid");

		if (corpProjectUuid != null) {
			setCorpProjectUuid(corpProjectUuid);
		}

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		String corpEntryName = (String)attributes.get("corpEntryName");

		if (corpEntryName != null) {
			setCorpEntryName(corpEntryName);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		Long redirectAccountEntryId = (Long)attributes.get(
				"redirectAccountEntryId");

		if (redirectAccountEntryId != null) {
			setRedirectAccountEntryId(redirectAccountEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer industry = (Integer)attributes.get("industry");

		if (industry != null) {
			setIndustry(industry);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
		}

		Boolean partnerManagedSupport = (Boolean)attributes.get(
				"partnerManagedSupport");

		if (partnerManagedSupport != null) {
			setPartnerManagedSupport(partnerManagedSupport);
		}

		Integer tier = (Integer)attributes.get("tier");

		if (tier != null) {
			setTier(tier);
		}

		Integer maxCustomers = (Integer)attributes.get("maxCustomers");

		if (maxCustomers != null) {
			setMaxCustomers(maxCustomers);
		}

		String instructions = (String)attributes.get("instructions");

		if (instructions != null) {
			setInstructions(instructions);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Long highestSupportResponseId = (Long)attributes.get(
				"highestSupportResponseId");

		if (highestSupportResponseId != null) {
			setHighestSupportResponseId(highestSupportResponseId);
		}

		Boolean activeSupport = (Boolean)attributes.get("activeSupport");

		if (activeSupport != null) {
			setActiveSupport(activeSupport);
		}

		Boolean activeTicketSupport = (Boolean)attributes.get(
				"activeTicketSupport");

		if (activeTicketSupport != null) {
			setActiveTicketSupport(activeTicketSupport);
		}

		Date lastAuditDate = (Date)attributes.get("lastAuditDate");

		if (lastAuditDate != null) {
			setLastAuditDate(lastAuditDate);
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

	@Override
	public AccountEntry toEscapedModel() {
		return new AccountEntryWrapper(_accountEntry.toEscapedModel());
	}

	@Override
	public AccountEntry toUnescapedModel() {
		return new AccountEntryWrapper(_accountEntry.toUnescapedModel());
	}

	@Override
	public PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntry.getPartnerEntry();
	}

	/**
	* Returns the active support of this account entry.
	*
	* @return the active support of this account entry
	*/
	@Override
	public boolean getActiveSupport() {
		return _accountEntry.getActiveSupport();
	}

	/**
	* Returns the active ticket support of this account entry.
	*
	* @return the active ticket support of this account entry
	*/
	@Override
	public boolean getActiveTicketSupport() {
		return _accountEntry.getActiveTicketSupport();
	}

	/**
	* Returns the partner managed support of this account entry.
	*
	* @return the partner managed support of this account entry
	*/
	@Override
	public boolean getPartnerManagedSupport() {
		return _accountEntry.getPartnerManagedSupport();
	}

	@Override
	public boolean hasEnterpriseSearchOffering(int productEntryEnvironment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntry.hasEnterpriseSearchOffering(productEntryEnvironment);
	}

	/**
	* Returns <code>true</code> if this account entry is active support.
	*
	* @return <code>true</code> if this account entry is active support; <code>false</code> otherwise
	*/
	@Override
	public boolean isActiveSupport() {
		return _accountEntry.isActiveSupport();
	}

	/**
	* Returns <code>true</code> if this account entry is active ticket support.
	*
	* @return <code>true</code> if this account entry is active ticket support; <code>false</code> otherwise
	*/
	@Override
	public boolean isActiveTicketSupport() {
		return _accountEntry.isActiveTicketSupport();
	}

	/**
	* Returns <code>true</code> if this account entry is approved.
	*
	* @return <code>true</code> if this account entry is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _accountEntry.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _accountEntry.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this account entry is denied.
	*
	* @return <code>true</code> if this account entry is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _accountEntry.isDenied();
	}

	/**
	* Returns <code>true</code> if this account entry is a draft.
	*
	* @return <code>true</code> if this account entry is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _accountEntry.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this account entry is expired.
	*
	* @return <code>true</code> if this account entry is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _accountEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this account entry is inactive.
	*
	* @return <code>true</code> if this account entry is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _accountEntry.isInactive();
	}

	/**
	* Returns <code>true</code> if this account entry is incomplete.
	*
	* @return <code>true</code> if this account entry is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _accountEntry.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _accountEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this account entry is partner managed support.
	*
	* @return <code>true</code> if this account entry is partner managed support; <code>false</code> otherwise
	*/
	@Override
	public boolean isPartnerManagedSupport() {
		return _accountEntry.isPartnerManagedSupport();
	}

	/**
	* Returns <code>true</code> if this account entry is pending.
	*
	* @return <code>true</code> if this account entry is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _accountEntry.isPending();
	}

	/**
	* Returns <code>true</code> if this account entry is scheduled.
	*
	* @return <code>true</code> if this account entry is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _accountEntry.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.Address getAddress() {
		return _accountEntry.getAddress();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountEntry> toCacheModel() {
		return _accountEntry.toCacheModel();
	}

	@Override
	public int compareTo(AccountEntry accountEntry) {
		return _accountEntry.compareTo(accountEntry);
	}

	/**
	* Returns the industry of this account entry.
	*
	* @return the industry of this account entry
	*/
	@Override
	public int getIndustry() {
		return _accountEntry.getIndustry();
	}

	/**
	* Returns the max customers of this account entry.
	*
	* @return the max customers of this account entry
	*/
	@Override
	public int getMaxCustomers() {
		return _accountEntry.getMaxCustomers();
	}

	/**
	* Returns the status of this account entry.
	*
	* @return the status of this account entry
	*/
	@Override
	public int getStatus() {
		return _accountEntry.getStatus();
	}

	/**
	* Returns the tier of this account entry.
	*
	* @return the tier of this account entry
	*/
	@Override
	public int getTier() {
		return _accountEntry.getTier();
	}

	/**
	* Returns the type of this account entry.
	*
	* @return the type of this account entry
	*/
	@Override
	public int getType() {
		return _accountEntry.getType();
	}

	@Override
	public int hashCode() {
		return _accountEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEntryWrapper((AccountEntry)_accountEntry.clone());
	}

	/**
	* Returns the code of this account entry.
	*
	* @return the code of this account entry
	*/
	@Override
	public java.lang.String getCode() {
		return _accountEntry.getCode();
	}

	/**
	* Returns the corp entry name of this account entry.
	*
	* @return the corp entry name of this account entry
	*/
	@Override
	public java.lang.String getCorpEntryName() {
		return _accountEntry.getCorpEntryName();
	}

	/**
	* Returns the corp project uuid of this account entry.
	*
	* @return the corp project uuid of this account entry
	*/
	@Override
	public java.lang.String getCorpProjectUuid() {
		return _accountEntry.getCorpProjectUuid();
	}

	/**
	* Returns the dossiera account key of this account entry.
	*
	* @return the dossiera account key of this account entry
	*/
	@Override
	public java.lang.String getDossieraAccountKey() {
		return _accountEntry.getDossieraAccountKey();
	}

	@Override
	public java.lang.String getEWSADossieraProjectKey() {
		return _accountEntry.getEWSADossieraProjectKey();
	}

	@Override
	public java.lang.String getIndustryLabel() {
		return _accountEntry.getIndustryLabel();
	}

	/**
	* Returns the instructions of this account entry.
	*
	* @return the instructions of this account entry
	*/
	@Override
	public java.lang.String getInstructions() {
		return _accountEntry.getInstructions();
	}

	/**
	* Returns the modified user name of this account entry.
	*
	* @return the modified user name of this account entry
	*/
	@Override
	public java.lang.String getModifiedUserName() {
		return _accountEntry.getModifiedUserName();
	}

	/**
	* Returns the modified user uuid of this account entry.
	*
	* @return the modified user uuid of this account entry
	*/
	@Override
	public java.lang.String getModifiedUserUuid() {
		return _accountEntry.getModifiedUserUuid();
	}

	/**
	* Returns the name of this account entry.
	*
	* @return the name of this account entry
	*/
	@Override
	public java.lang.String getName() {
		return _accountEntry.getName();
	}

	/**
	* Returns the notes of this account entry.
	*
	* @return the notes of this account entry
	*/
	@Override
	public java.lang.String getNotes() {
		return _accountEntry.getNotes();
	}

	/**
	* Returns the status by user name of this account entry.
	*
	* @return the status by user name of this account entry
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _accountEntry.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this account entry.
	*
	* @return the status by user uuid of this account entry
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _accountEntry.getStatusByUserUuid();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _accountEntry.getStatusLabel();
	}

	/**
	* Returns the status message of this account entry.
	*
	* @return the status message of this account entry
	*/
	@Override
	public java.lang.String getStatusMessage() {
		return _accountEntry.getStatusMessage();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _accountEntry.getTypeLabel();
	}

	/**
	* Returns the user name of this account entry.
	*
	* @return the user name of this account entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _accountEntry.getUserName();
	}

	/**
	* Returns the user uuid of this account entry.
	*
	* @return the user uuid of this account entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountEntry.toXmlString();
	}

	@Override
	public java.lang.String[] getLanguageIds() {
		return _accountEntry.getLanguageIds();
	}

	/**
	* Returns the create date of this account entry.
	*
	* @return the create date of this account entry
	*/
	@Override
	public Date getCreateDate() {
		return _accountEntry.getCreateDate();
	}

	/**
	* Returns the last audit date of this account entry.
	*
	* @return the last audit date of this account entry
	*/
	@Override
	public Date getLastAuditDate() {
		return _accountEntry.getLastAuditDate();
	}

	/**
	* Returns the modified date of this account entry.
	*
	* @return the modified date of this account entry
	*/
	@Override
	public Date getModifiedDate() {
		return _accountEntry.getModifiedDate();
	}

	/**
	* Returns the status date of this account entry.
	*
	* @return the status date of this account entry
	*/
	@Override
	public Date getStatusDate() {
		return _accountEntry.getStatusDate();
	}

	@Override
	public java.util.List<AccountAttachment> getAccountAttachments(
		long accountProjectId) {
		return _accountEntry.getAccountAttachments(accountProjectId);
	}

	@Override
	public java.util.List<AccountCustomer> getAccountCustomers() {
		return _accountEntry.getAccountCustomers();
	}

	@Override
	public java.util.List<AccountWorker> getAccountWorkers() {
		return _accountEntry.getAccountWorkers();
	}

	@Override
	public java.util.List<OfferingEntry> getOfferingEntries() {
		return _accountEntry.getOfferingEntries();
	}

	@Override
	public java.util.List<OrderEntry> getOrderEntries() {
		return _accountEntry.getOrderEntries();
	}

	@Override
	public java.util.List<SupportRegion> getSupportRegions() {
		return _accountEntry.getSupportRegions();
	}

	/**
	* Returns the account entry ID of this account entry.
	*
	* @return the account entry ID of this account entry
	*/
	@Override
	public long getAccountEntryId() {
		return _accountEntry.getAccountEntryId();
	}

	/**
	* Returns the company ID of this account entry.
	*
	* @return the company ID of this account entry
	*/
	@Override
	public long getCompanyId() {
		return _accountEntry.getCompanyId();
	}

	/**
	* Returns the corp project ID of this account entry.
	*
	* @return the corp project ID of this account entry
	*/
	@Override
	public long getCorpProjectId() {
		return _accountEntry.getCorpProjectId();
	}

	/**
	* Returns the country ID of this account entry.
	*
	* @return the country ID of this account entry
	*/
	@Override
	public long getCountryId() {
		return _accountEntry.getCountryId();
	}

	/**
	* Returns the highest support response ID of this account entry.
	*
	* @return the highest support response ID of this account entry
	*/
	@Override
	public long getHighestSupportResponseId() {
		return _accountEntry.getHighestSupportResponseId();
	}

	/**
	* Returns the modified user ID of this account entry.
	*
	* @return the modified user ID of this account entry
	*/
	@Override
	public long getModifiedUserId() {
		return _accountEntry.getModifiedUserId();
	}

	/**
	* Returns the partner entry ID of this account entry.
	*
	* @return the partner entry ID of this account entry
	*/
	@Override
	public long getPartnerEntryId() {
		return _accountEntry.getPartnerEntryId();
	}

	/**
	* Returns the primary key of this account entry.
	*
	* @return the primary key of this account entry
	*/
	@Override
	public long getPrimaryKey() {
		return _accountEntry.getPrimaryKey();
	}

	/**
	* Returns the redirect account entry ID of this account entry.
	*
	* @return the redirect account entry ID of this account entry
	*/
	@Override
	public long getRedirectAccountEntryId() {
		return _accountEntry.getRedirectAccountEntryId();
	}

	/**
	* Returns the status by user ID of this account entry.
	*
	* @return the status by user ID of this account entry
	*/
	@Override
	public long getStatusByUserId() {
		return _accountEntry.getStatusByUserId();
	}

	/**
	* Returns the user ID of this account entry.
	*
	* @return the user ID of this account entry
	*/
	@Override
	public long getUserId() {
		return _accountEntry.getUserId();
	}

	@Override
	public long[] getSupportRegionIds() {
		return _accountEntry.getSupportRegionIds();
	}

	@Override
	public void persist() {
		_accountEntry.persist();
	}

	/**
	* Sets the account entry ID of this account entry.
	*
	* @param accountEntryId the account entry ID of this account entry
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntry.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets whether this account entry is active support.
	*
	* @param activeSupport the active support of this account entry
	*/
	@Override
	public void setActiveSupport(boolean activeSupport) {
		_accountEntry.setActiveSupport(activeSupport);
	}

	/**
	* Sets whether this account entry is active ticket support.
	*
	* @param activeTicketSupport the active ticket support of this account entry
	*/
	@Override
	public void setActiveTicketSupport(boolean activeTicketSupport) {
		_accountEntry.setActiveTicketSupport(activeTicketSupport);
	}

	@Override
	public void setAddress(com.liferay.portal.kernel.model.Address address) {
		_accountEntry.setAddress(address);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the code of this account entry.
	*
	* @param code the code of this account entry
	*/
	@Override
	public void setCode(java.lang.String code) {
		_accountEntry.setCode(code);
	}

	/**
	* Sets the company ID of this account entry.
	*
	* @param companyId the company ID of this account entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_accountEntry.setCompanyId(companyId);
	}

	/**
	* Sets the corp entry name of this account entry.
	*
	* @param corpEntryName the corp entry name of this account entry
	*/
	@Override
	public void setCorpEntryName(java.lang.String corpEntryName) {
		_accountEntry.setCorpEntryName(corpEntryName);
	}

	/**
	* Sets the corp project ID of this account entry.
	*
	* @param corpProjectId the corp project ID of this account entry
	*/
	@Override
	public void setCorpProjectId(long corpProjectId) {
		_accountEntry.setCorpProjectId(corpProjectId);
	}

	/**
	* Sets the corp project uuid of this account entry.
	*
	* @param corpProjectUuid the corp project uuid of this account entry
	*/
	@Override
	public void setCorpProjectUuid(java.lang.String corpProjectUuid) {
		_accountEntry.setCorpProjectUuid(corpProjectUuid);
	}

	/**
	* Sets the country ID of this account entry.
	*
	* @param countryId the country ID of this account entry
	*/
	@Override
	public void setCountryId(long countryId) {
		_accountEntry.setCountryId(countryId);
	}

	/**
	* Sets the create date of this account entry.
	*
	* @param createDate the create date of this account entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accountEntry.setCreateDate(createDate);
	}

	/**
	* Sets the dossiera account key of this account entry.
	*
	* @param dossieraAccountKey the dossiera account key of this account entry
	*/
	@Override
	public void setDossieraAccountKey(java.lang.String dossieraAccountKey) {
		_accountEntry.setDossieraAccountKey(dossieraAccountKey);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the highest support response ID of this account entry.
	*
	* @param highestSupportResponseId the highest support response ID of this account entry
	*/
	@Override
	public void setHighestSupportResponseId(long highestSupportResponseId) {
		_accountEntry.setHighestSupportResponseId(highestSupportResponseId);
	}

	/**
	* Sets the industry of this account entry.
	*
	* @param industry the industry of this account entry
	*/
	@Override
	public void setIndustry(int industry) {
		_accountEntry.setIndustry(industry);
	}

	/**
	* Sets the instructions of this account entry.
	*
	* @param instructions the instructions of this account entry
	*/
	@Override
	public void setInstructions(java.lang.String instructions) {
		_accountEntry.setInstructions(instructions);
	}

	@Override
	public void setLanguageIds(java.lang.String[] languageIds) {
		_accountEntry.setLanguageIds(languageIds);
	}

	/**
	* Sets the last audit date of this account entry.
	*
	* @param lastAuditDate the last audit date of this account entry
	*/
	@Override
	public void setLastAuditDate(Date lastAuditDate) {
		_accountEntry.setLastAuditDate(lastAuditDate);
	}

	/**
	* Sets the max customers of this account entry.
	*
	* @param maxCustomers the max customers of this account entry
	*/
	@Override
	public void setMaxCustomers(int maxCustomers) {
		_accountEntry.setMaxCustomers(maxCustomers);
	}

	/**
	* Sets the modified date of this account entry.
	*
	* @param modifiedDate the modified date of this account entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accountEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the modified user ID of this account entry.
	*
	* @param modifiedUserId the modified user ID of this account entry
	*/
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_accountEntry.setModifiedUserId(modifiedUserId);
	}

	/**
	* Sets the modified user name of this account entry.
	*
	* @param modifiedUserName the modified user name of this account entry
	*/
	@Override
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountEntry.setModifiedUserName(modifiedUserName);
	}

	/**
	* Sets the modified user uuid of this account entry.
	*
	* @param modifiedUserUuid the modified user uuid of this account entry
	*/
	@Override
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountEntry.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Sets the name of this account entry.
	*
	* @param name the name of this account entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_accountEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_accountEntry.setNew(n);
	}

	/**
	* Sets the notes of this account entry.
	*
	* @param notes the notes of this account entry
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_accountEntry.setNotes(notes);
	}

	/**
	* Sets the partner entry ID of this account entry.
	*
	* @param partnerEntryId the partner entry ID of this account entry
	*/
	@Override
	public void setPartnerEntryId(long partnerEntryId) {
		_accountEntry.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Sets whether this account entry is partner managed support.
	*
	* @param partnerManagedSupport the partner managed support of this account entry
	*/
	@Override
	public void setPartnerManagedSupport(boolean partnerManagedSupport) {
		_accountEntry.setPartnerManagedSupport(partnerManagedSupport);
	}

	/**
	* Sets the primary key of this account entry.
	*
	* @param primaryKey the primary key of this account entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the redirect account entry ID of this account entry.
	*
	* @param redirectAccountEntryId the redirect account entry ID of this account entry
	*/
	@Override
	public void setRedirectAccountEntryId(long redirectAccountEntryId) {
		_accountEntry.setRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Sets the status of this account entry.
	*
	* @param status the status of this account entry
	*/
	@Override
	public void setStatus(int status) {
		_accountEntry.setStatus(status);
	}

	/**
	* Sets the status by user ID of this account entry.
	*
	* @param statusByUserId the status by user ID of this account entry
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_accountEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this account entry.
	*
	* @param statusByUserName the status by user name of this account entry
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_accountEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this account entry.
	*
	* @param statusByUserUuid the status by user uuid of this account entry
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_accountEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this account entry.
	*
	* @param statusDate the status date of this account entry
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_accountEntry.setStatusDate(statusDate);
	}

	/**
	* Sets the status message of this account entry.
	*
	* @param statusMessage the status message of this account entry
	*/
	@Override
	public void setStatusMessage(java.lang.String statusMessage) {
		_accountEntry.setStatusMessage(statusMessage);
	}

	@Override
	public void setSupportRegionIds(long[] supportRegionIds) {
		_accountEntry.setSupportRegionIds(supportRegionIds);
	}

	/**
	* Sets the tier of this account entry.
	*
	* @param tier the tier of this account entry
	*/
	@Override
	public void setTier(int tier) {
		_accountEntry.setTier(tier);
	}

	/**
	* Sets the type of this account entry.
	*
	* @param type the type of this account entry
	*/
	@Override
	public void setType(int type) {
		_accountEntry.setType(type);
	}

	/**
	* Sets the user ID of this account entry.
	*
	* @param userId the user ID of this account entry
	*/
	@Override
	public void setUserId(long userId) {
		_accountEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this account entry.
	*
	* @param userName the user name of this account entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_accountEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this account entry.
	*
	* @param userUuid the user uuid of this account entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountEntry.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEntryWrapper)) {
			return false;
		}

		AccountEntryWrapper accountEntryWrapper = (AccountEntryWrapper)obj;

		if (Objects.equals(_accountEntry, accountEntryWrapper._accountEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountEntry getWrappedModel() {
		return _accountEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountEntry.resetOriginalValues();
	}

	private final AccountEntry _accountEntry;
}