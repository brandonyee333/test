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
 * This class is a wrapper for {@link AccountEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEntry
 * @generated
 */
public class AccountEntryWrapper implements AccountEntry,
	ModelWrapper<AccountEntry> {
	public AccountEntryWrapper(AccountEntry accountEntry) {
		_accountEntry = accountEntry;
	}

	public Class<?> getModelClass() {
		return AccountEntry.class;
	}

	public String getModelClassName() {
		return AccountEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpProjectId", getCorpProjectId());
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
		attributes.put("lastAuditDate", getLastAuditDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
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

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
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

	/**
	* Returns the primary key of this account entry.
	*
	* @return the primary key of this account entry
	*/
	public long getPrimaryKey() {
		return _accountEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account entry.
	*
	* @param primaryKey the primary key of this account entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account entry ID of this account entry.
	*
	* @return the account entry ID of this account entry
	*/
	public long getAccountEntryId() {
		return _accountEntry.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account entry.
	*
	* @param accountEntryId the account entry ID of this account entry
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountEntry.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the user ID of this account entry.
	*
	* @return the user ID of this account entry
	*/
	public long getUserId() {
		return _accountEntry.getUserId();
	}

	/**
	* Sets the user ID of this account entry.
	*
	* @param userId the user ID of this account entry
	*/
	public void setUserId(long userId) {
		_accountEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account entry.
	*
	* @return the user uuid of this account entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this account entry.
	*
	* @param userUuid the user uuid of this account entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account entry.
	*
	* @return the user name of this account entry
	*/
	public java.lang.String getUserName() {
		return _accountEntry.getUserName();
	}

	/**
	* Sets the user name of this account entry.
	*
	* @param userName the user name of this account entry
	*/
	public void setUserName(java.lang.String userName) {
		_accountEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this account entry.
	*
	* @return the create date of this account entry
	*/
	public java.util.Date getCreateDate() {
		return _accountEntry.getCreateDate();
	}

	/**
	* Sets the create date of this account entry.
	*
	* @param createDate the create date of this account entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_accountEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified user ID of this account entry.
	*
	* @return the modified user ID of this account entry
	*/
	public long getModifiedUserId() {
		return _accountEntry.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this account entry.
	*
	* @param modifiedUserId the modified user ID of this account entry
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_accountEntry.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this account entry.
	*
	* @return the modified user uuid of this account entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this account entry.
	*
	* @param modifiedUserUuid the modified user uuid of this account entry
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountEntry.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this account entry.
	*
	* @return the modified user name of this account entry
	*/
	public java.lang.String getModifiedUserName() {
		return _accountEntry.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this account entry.
	*
	* @param modifiedUserName the modified user name of this account entry
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountEntry.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified date of this account entry.
	*
	* @return the modified date of this account entry
	*/
	public java.util.Date getModifiedDate() {
		return _accountEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this account entry.
	*
	* @param modifiedDate the modified date of this account entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_accountEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the corp project ID of this account entry.
	*
	* @return the corp project ID of this account entry
	*/
	public long getCorpProjectId() {
		return _accountEntry.getCorpProjectId();
	}

	/**
	* Sets the corp project ID of this account entry.
	*
	* @param corpProjectId the corp project ID of this account entry
	*/
	public void setCorpProjectId(long corpProjectId) {
		_accountEntry.setCorpProjectId(corpProjectId);
	}

	/**
	* Returns the corp entry name of this account entry.
	*
	* @return the corp entry name of this account entry
	*/
	public java.lang.String getCorpEntryName() {
		return _accountEntry.getCorpEntryName();
	}

	/**
	* Sets the corp entry name of this account entry.
	*
	* @param corpEntryName the corp entry name of this account entry
	*/
	public void setCorpEntryName(java.lang.String corpEntryName) {
		_accountEntry.setCorpEntryName(corpEntryName);
	}

	/**
	* Returns the name of this account entry.
	*
	* @return the name of this account entry
	*/
	public java.lang.String getName() {
		return _accountEntry.getName();
	}

	/**
	* Sets the name of this account entry.
	*
	* @param name the name of this account entry
	*/
	public void setName(java.lang.String name) {
		_accountEntry.setName(name);
	}

	/**
	* Returns the code of this account entry.
	*
	* @return the code of this account entry
	*/
	public java.lang.String getCode() {
		return _accountEntry.getCode();
	}

	/**
	* Sets the code of this account entry.
	*
	* @param code the code of this account entry
	*/
	public void setCode(java.lang.String code) {
		_accountEntry.setCode(code);
	}

	/**
	* Returns the redirect account entry ID of this account entry.
	*
	* @return the redirect account entry ID of this account entry
	*/
	public long getRedirectAccountEntryId() {
		return _accountEntry.getRedirectAccountEntryId();
	}

	/**
	* Sets the redirect account entry ID of this account entry.
	*
	* @param redirectAccountEntryId the redirect account entry ID of this account entry
	*/
	public void setRedirectAccountEntryId(long redirectAccountEntryId) {
		_accountEntry.setRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Returns the type of this account entry.
	*
	* @return the type of this account entry
	*/
	public int getType() {
		return _accountEntry.getType();
	}

	/**
	* Sets the type of this account entry.
	*
	* @param type the type of this account entry
	*/
	public void setType(int type) {
		_accountEntry.setType(type);
	}

	/**
	* Returns the industry of this account entry.
	*
	* @return the industry of this account entry
	*/
	public int getIndustry() {
		return _accountEntry.getIndustry();
	}

	/**
	* Sets the industry of this account entry.
	*
	* @param industry the industry of this account entry
	*/
	public void setIndustry(int industry) {
		_accountEntry.setIndustry(industry);
	}

	/**
	* Returns the country ID of this account entry.
	*
	* @return the country ID of this account entry
	*/
	public long getCountryId() {
		return _accountEntry.getCountryId();
	}

	/**
	* Sets the country ID of this account entry.
	*
	* @param countryId the country ID of this account entry
	*/
	public void setCountryId(long countryId) {
		_accountEntry.setCountryId(countryId);
	}

	/**
	* Returns the partner entry ID of this account entry.
	*
	* @return the partner entry ID of this account entry
	*/
	public long getPartnerEntryId() {
		return _accountEntry.getPartnerEntryId();
	}

	/**
	* Sets the partner entry ID of this account entry.
	*
	* @param partnerEntryId the partner entry ID of this account entry
	*/
	public void setPartnerEntryId(long partnerEntryId) {
		_accountEntry.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the partner managed support of this account entry.
	*
	* @return the partner managed support of this account entry
	*/
	public boolean getPartnerManagedSupport() {
		return _accountEntry.getPartnerManagedSupport();
	}

	/**
	* Returns <code>true</code> if this account entry is partner managed support.
	*
	* @return <code>true</code> if this account entry is partner managed support; <code>false</code> otherwise
	*/
	public boolean isPartnerManagedSupport() {
		return _accountEntry.isPartnerManagedSupport();
	}

	/**
	* Sets whether this account entry is partner managed support.
	*
	* @param partnerManagedSupport the partner managed support of this account entry
	*/
	public void setPartnerManagedSupport(boolean partnerManagedSupport) {
		_accountEntry.setPartnerManagedSupport(partnerManagedSupport);
	}

	/**
	* Returns the tier of this account entry.
	*
	* @return the tier of this account entry
	*/
	public int getTier() {
		return _accountEntry.getTier();
	}

	/**
	* Sets the tier of this account entry.
	*
	* @param tier the tier of this account entry
	*/
	public void setTier(int tier) {
		_accountEntry.setTier(tier);
	}

	/**
	* Returns the max customers of this account entry.
	*
	* @return the max customers of this account entry
	*/
	public int getMaxCustomers() {
		return _accountEntry.getMaxCustomers();
	}

	/**
	* Sets the max customers of this account entry.
	*
	* @param maxCustomers the max customers of this account entry
	*/
	public void setMaxCustomers(int maxCustomers) {
		_accountEntry.setMaxCustomers(maxCustomers);
	}

	/**
	* Returns the instructions of this account entry.
	*
	* @return the instructions of this account entry
	*/
	public java.lang.String getInstructions() {
		return _accountEntry.getInstructions();
	}

	/**
	* Sets the instructions of this account entry.
	*
	* @param instructions the instructions of this account entry
	*/
	public void setInstructions(java.lang.String instructions) {
		_accountEntry.setInstructions(instructions);
	}

	/**
	* Returns the notes of this account entry.
	*
	* @return the notes of this account entry
	*/
	public java.lang.String getNotes() {
		return _accountEntry.getNotes();
	}

	/**
	* Sets the notes of this account entry.
	*
	* @param notes the notes of this account entry
	*/
	public void setNotes(java.lang.String notes) {
		_accountEntry.setNotes(notes);
	}

	/**
	* Returns the highest support response ID of this account entry.
	*
	* @return the highest support response ID of this account entry
	*/
	public long getHighestSupportResponseId() {
		return _accountEntry.getHighestSupportResponseId();
	}

	/**
	* Sets the highest support response ID of this account entry.
	*
	* @param highestSupportResponseId the highest support response ID of this account entry
	*/
	public void setHighestSupportResponseId(long highestSupportResponseId) {
		_accountEntry.setHighestSupportResponseId(highestSupportResponseId);
	}

	/**
	* Returns the last audit date of this account entry.
	*
	* @return the last audit date of this account entry
	*/
	public java.util.Date getLastAuditDate() {
		return _accountEntry.getLastAuditDate();
	}

	/**
	* Sets the last audit date of this account entry.
	*
	* @param lastAuditDate the last audit date of this account entry
	*/
	public void setLastAuditDate(java.util.Date lastAuditDate) {
		_accountEntry.setLastAuditDate(lastAuditDate);
	}

	/**
	* Returns the status of this account entry.
	*
	* @return the status of this account entry
	*/
	public int getStatus() {
		return _accountEntry.getStatus();
	}

	/**
	* Sets the status of this account entry.
	*
	* @param status the status of this account entry
	*/
	public void setStatus(int status) {
		_accountEntry.setStatus(status);
	}

	/**
	* Returns the status by user ID of this account entry.
	*
	* @return the status by user ID of this account entry
	*/
	public long getStatusByUserId() {
		return _accountEntry.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this account entry.
	*
	* @param statusByUserId the status by user ID of this account entry
	*/
	public void setStatusByUserId(long statusByUserId) {
		_accountEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this account entry.
	*
	* @return the status by user uuid of this account entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this account entry.
	*
	* @param statusByUserUuid the status by user uuid of this account entry
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_accountEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this account entry.
	*
	* @return the status by user name of this account entry
	*/
	public java.lang.String getStatusByUserName() {
		return _accountEntry.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this account entry.
	*
	* @param statusByUserName the status by user name of this account entry
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_accountEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this account entry.
	*
	* @return the status date of this account entry
	*/
	public java.util.Date getStatusDate() {
		return _accountEntry.getStatusDate();
	}

	/**
	* Sets the status date of this account entry.
	*
	* @param statusDate the status date of this account entry
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_accountEntry.setStatusDate(statusDate);
	}

	/**
	* Returns the status message of this account entry.
	*
	* @return the status message of this account entry
	*/
	public java.lang.String getStatusMessage() {
		return _accountEntry.getStatusMessage();
	}

	/**
	* Sets the status message of this account entry.
	*
	* @param statusMessage the status message of this account entry
	*/
	public void setStatusMessage(java.lang.String statusMessage) {
		_accountEntry.setStatusMessage(statusMessage);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _accountEntry.getApproved();
	}

	/**
	* Returns <code>true</code> if this account entry is approved.
	*
	* @return <code>true</code> if this account entry is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _accountEntry.isApproved();
	}

	/**
	* Returns <code>true</code> if this account entry is denied.
	*
	* @return <code>true</code> if this account entry is denied; <code>false</code> otherwise
	*/
	public boolean isDenied() {
		return _accountEntry.isDenied();
	}

	/**
	* Returns <code>true</code> if this account entry is a draft.
	*
	* @return <code>true</code> if this account entry is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _accountEntry.isDraft();
	}

	/**
	* Returns <code>true</code> if this account entry is expired.
	*
	* @return <code>true</code> if this account entry is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _accountEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this account entry is inactive.
	*
	* @return <code>true</code> if this account entry is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _accountEntry.isInactive();
	}

	/**
	* Returns <code>true</code> if this account entry is incomplete.
	*
	* @return <code>true</code> if this account entry is incomplete; <code>false</code> otherwise
	*/
	public boolean isIncomplete() {
		return _accountEntry.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this account entry is pending.
	*
	* @return <code>true</code> if this account entry is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _accountEntry.isPending();
	}

	/**
	* Returns <code>true</code> if this account entry is scheduled.
	*
	* @return <code>true</code> if this account entry is scheduled; <code>false</code> otherwise
	*/
	public boolean isScheduled() {
		return _accountEntry.isScheduled();
	}

	public boolean isNew() {
		return _accountEntry.isNew();
	}

	public void setNew(boolean n) {
		_accountEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEntryWrapper((AccountEntry)_accountEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.AccountEntry accountEntry) {
		return _accountEntry.compareTo(accountEntry);
	}

	@Override
	public int hashCode() {
		return _accountEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountEntry> toCacheModel() {
		return _accountEntry.toCacheModel();
	}

	public com.liferay.osb.model.AccountEntry toEscapedModel() {
		return new AccountEntryWrapper(_accountEntry.toEscapedModel());
	}

	public com.liferay.osb.model.AccountEntry toUnescapedModel() {
		return new AccountEntryWrapper(_accountEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _accountEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountEntry.persist();
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getAccountAttachments(accountProjectId);
	}

	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getAccountCustomers();
	}

	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getAccountWorkers();
	}

	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getAddress();
	}

	public java.lang.String getEWSADossieraProjectKey()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getEWSADossieraProjectKey();
	}

	public java.lang.String getIndustryLabel() {
		return _accountEntry.getIndustryLabel();
	}

	public java.lang.String[] getLanguageIds()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getLanguageIds();
	}

	public java.util.List<com.liferay.osb.model.OfferingEntry> getOfferingEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getOfferingEntries();
	}

	public java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getOrderEntries();
	}

	public com.liferay.osb.model.PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getPartnerEntry();
	}

	public java.lang.String getStatusLabel() {
		return _accountEntry.getStatusLabel();
	}

	public long[] getSupportRegionIds()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getSupportRegionIds();
	}

	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntry.getSupportRegions();
	}

	public java.lang.String getTypeLabel() {
		return _accountEntry.getTypeLabel();
	}

	public void setAddress(com.liferay.portal.model.Address address) {
		_accountEntry.setAddress(address);
	}

	public void setLanguageIds(java.lang.String[] languageIds) {
		_accountEntry.setLanguageIds(languageIds);
	}

	public void setSupportRegionIds(long[] supportRegionIds) {
		_accountEntry.setSupportRegionIds(supportRegionIds);
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

		if (Validator.equals(_accountEntry, accountEntryWrapper._accountEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountEntry getWrappedAccountEntry() {
		return _accountEntry;
	}

	public AccountEntry getWrappedModel() {
		return _accountEntry;
	}

	public void resetOriginalValues() {
		_accountEntry.resetOriginalValues();
	}

	private AccountEntry _accountEntry;
}