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
		attributes.put("koroneikiAccountKey", getKoroneikiAccountKey());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("name", getName());
		attributes.put("code", getCode());
		attributes.put("instructions", getInstructions());
		attributes.put("activeSupport", getActiveSupport());
		attributes.put("activeTicketSupport", getActiveTicketSupport());
		attributes.put("lastZendeskAuditDate", getLastZendeskAuditDate());
		attributes.put("status", getStatus());

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

		String koroneikiAccountKey = (String)attributes.get(
				"koroneikiAccountKey");

		if (koroneikiAccountKey != null) {
			setKoroneikiAccountKey(koroneikiAccountKey);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String instructions = (String)attributes.get("instructions");

		if (instructions != null) {
			setInstructions(instructions);
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

		Date lastZendeskAuditDate = (Date)attributes.get("lastZendeskAuditDate");

		if (lastZendeskAuditDate != null) {
			setLastZendeskAuditDate(lastZendeskAuditDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
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

	@Override
	public boolean isCachedModel() {
		return _accountEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountEntry.getExpandoBridge();
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
	public com.liferay.portal.kernel.model.CacheModel<AccountEntry> toCacheModel() {
		return _accountEntry.toCacheModel();
	}

	@Override
	public int compareTo(AccountEntry accountEntry) {
		return _accountEntry.compareTo(accountEntry);
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

	@Override
	public java.lang.String getDescription() throws java.lang.Exception {
		return _accountEntry.getDescription();
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
	public java.lang.String getDossieraAccountURL() {
		return _accountEntry.getDossieraAccountURL();
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
	* Returns the koroneiki account key of this account entry.
	*
	* @return the koroneiki account key of this account entry
	*/
	@Override
	public java.lang.String getKoroneikiAccountKey() {
		return _accountEntry.getKoroneikiAccountKey();
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

	@Override
	public java.lang.String getStatusLabel() {
		return _accountEntry.getStatusLabel();
	}

	@Override
	public java.lang.String getTier() throws java.lang.Exception {
		return _accountEntry.getTier();
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
	* Returns the last zendesk audit date of this account entry.
	*
	* @return the last zendesk audit date of this account entry
	*/
	@Override
	public Date getLastZendeskAuditDate() {
		return _accountEntry.getLastZendeskAuditDate();
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

	@Override
	public java.util.List<AccountAttachment> getAccountAttachments(
		long accountProjectId) {
		return _accountEntry.getAccountAttachments(accountProjectId);
	}

	@Override
	public java.util.List<OfferingEntry> getOfferingEntries() {
		return _accountEntry.getOfferingEntries();
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

	@Override
	public long getCorpProjectId() {
		return _accountEntry.getCorpProjectId();
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
	* Returns the primary key of this account entry.
	*
	* @return the primary key of this account entry
	*/
	@Override
	public long getPrimaryKey() {
		return _accountEntry.getPrimaryKey();
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
	* Sets the instructions of this account entry.
	*
	* @param instructions the instructions of this account entry
	*/
	@Override
	public void setInstructions(java.lang.String instructions) {
		_accountEntry.setInstructions(instructions);
	}

	/**
	* Sets the koroneiki account key of this account entry.
	*
	* @param koroneikiAccountKey the koroneiki account key of this account entry
	*/
	@Override
	public void setKoroneikiAccountKey(java.lang.String koroneikiAccountKey) {
		_accountEntry.setKoroneikiAccountKey(koroneikiAccountKey);
	}

	@Override
	public void setLanguageIds(java.lang.String[] languageIds) {
		_accountEntry.setLanguageIds(languageIds);
	}

	/**
	* Sets the last zendesk audit date of this account entry.
	*
	* @param lastZendeskAuditDate the last zendesk audit date of this account entry
	*/
	@Override
	public void setLastZendeskAuditDate(Date lastZendeskAuditDate) {
		_accountEntry.setLastZendeskAuditDate(lastZendeskAuditDate);
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
	* Sets the status of this account entry.
	*
	* @param status the status of this account entry
	*/
	@Override
	public void setStatus(int status) {
		_accountEntry.setStatus(status);
	}

	@Override
	public void setSupportRegionIds(long[] supportRegionIds) {
		_accountEntry.setSupportRegionIds(supportRegionIds);
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