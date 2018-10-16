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
 * This class is a wrapper for {@link PartnerEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntry
 * @generated
 */
@ProviderType
public class PartnerEntryWrapper implements PartnerEntry,
	ModelWrapper<PartnerEntry> {
	public PartnerEntryWrapper(PartnerEntry partnerEntry) {
		_partnerEntry = partnerEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return PartnerEntry.class;
	}

	@Override
	public String getModelClassName() {
		return PartnerEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentPartnerEntryId", getParentPartnerEntryId());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("code", getCode());
		attributes.put("jiraProjectKey", getJiraProjectKey());
		attributes.put("notes", getNotes());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
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

		Long parentPartnerEntryId = (Long)attributes.get("parentPartnerEntryId");

		if (parentPartnerEntryId != null) {
			setParentPartnerEntryId(parentPartnerEntryId);
		}

		String dossieraAccountKey = (String)attributes.get("dossieraAccountKey");

		if (dossieraAccountKey != null) {
			setDossieraAccountKey(dossieraAccountKey);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String jiraProjectKey = (String)attributes.get("jiraProjectKey");

		if (jiraProjectKey != null) {
			setJiraProjectKey(jiraProjectKey);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public PartnerEntry toEscapedModel() {
		return new PartnerEntryWrapper(_partnerEntry.toEscapedModel());
	}

	@Override
	public PartnerEntry toUnescapedModel() {
		return new PartnerEntryWrapper(_partnerEntry.toUnescapedModel());
	}

	@Override
	public SupportRegion getSupportRegion() {
		return _partnerEntry.getSupportRegion();
	}

	@Override
	public boolean isCachedModel() {
		return _partnerEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _partnerEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _partnerEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _partnerEntry.getExpandoBridge();
	}

	@Override
	public PartnerEntry getParentPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntry.getParentPartnerEntry();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PartnerEntry> toCacheModel() {
		return _partnerEntry.toCacheModel();
	}

	@Override
	public int compareTo(PartnerEntry partnerEntry) {
		return _partnerEntry.compareTo(partnerEntry);
	}

	/**
	* Returns the status of this partner entry.
	*
	* @return the status of this partner entry
	*/
	@Override
	public int getStatus() {
		return _partnerEntry.getStatus();
	}

	@Override
	public int hashCode() {
		return _partnerEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _partnerEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PartnerEntryWrapper((PartnerEntry)_partnerEntry.clone());
	}

	/**
	* Returns the code of this partner entry.
	*
	* @return the code of this partner entry
	*/
	@Override
	public java.lang.String getCode() {
		return _partnerEntry.getCode();
	}

	/**
	* Returns the dossiera account key of this partner entry.
	*
	* @return the dossiera account key of this partner entry
	*/
	@Override
	public java.lang.String getDossieraAccountKey() {
		return _partnerEntry.getDossieraAccountKey();
	}

	/**
	* Returns the jira project key of this partner entry.
	*
	* @return the jira project key of this partner entry
	*/
	@Override
	public java.lang.String getJiraProjectKey() {
		return _partnerEntry.getJiraProjectKey();
	}

	/**
	* Returns the modified user name of this partner entry.
	*
	* @return the modified user name of this partner entry
	*/
	@Override
	public java.lang.String getModifiedUserName() {
		return _partnerEntry.getModifiedUserName();
	}

	/**
	* Returns the modified user uuid of this partner entry.
	*
	* @return the modified user uuid of this partner entry
	*/
	@Override
	public java.lang.String getModifiedUserUuid() {
		return _partnerEntry.getModifiedUserUuid();
	}

	/**
	* Returns the notes of this partner entry.
	*
	* @return the notes of this partner entry
	*/
	@Override
	public java.lang.String getNotes() {
		return _partnerEntry.getNotes();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _partnerEntry.getStatusLabel();
	}

	/**
	* Returns the user name of this partner entry.
	*
	* @return the user name of this partner entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _partnerEntry.getUserName();
	}

	/**
	* Returns the user uuid of this partner entry.
	*
	* @return the user uuid of this partner entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _partnerEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _partnerEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _partnerEntry.toXmlString();
	}

	/**
	* Returns the create date of this partner entry.
	*
	* @return the create date of this partner entry
	*/
	@Override
	public Date getCreateDate() {
		return _partnerEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this partner entry.
	*
	* @return the modified date of this partner entry
	*/
	@Override
	public Date getModifiedDate() {
		return _partnerEntry.getModifiedDate();
	}

	@Override
	public java.util.List<AccountEntry> getAccountEntries() {
		return _partnerEntry.getAccountEntries();
	}

	@Override
	public java.util.List<PartnerEntry> getChildPartnerEntries(
		boolean recursive) {
		return _partnerEntry.getChildPartnerEntries(recursive);
	}

	@Override
	public java.util.List<PartnerWorker> getPartnerWorkers() {
		return _partnerEntry.getPartnerWorkers();
	}

	/**
	* Returns the company ID of this partner entry.
	*
	* @return the company ID of this partner entry
	*/
	@Override
	public long getCompanyId() {
		return _partnerEntry.getCompanyId();
	}

	/**
	* Returns the modified user ID of this partner entry.
	*
	* @return the modified user ID of this partner entry
	*/
	@Override
	public long getModifiedUserId() {
		return _partnerEntry.getModifiedUserId();
	}

	/**
	* Returns the parent partner entry ID of this partner entry.
	*
	* @return the parent partner entry ID of this partner entry
	*/
	@Override
	public long getParentPartnerEntryId() {
		return _partnerEntry.getParentPartnerEntryId();
	}

	/**
	* Returns the partner entry ID of this partner entry.
	*
	* @return the partner entry ID of this partner entry
	*/
	@Override
	public long getPartnerEntryId() {
		return _partnerEntry.getPartnerEntryId();
	}

	/**
	* Returns the primary key of this partner entry.
	*
	* @return the primary key of this partner entry
	*/
	@Override
	public long getPrimaryKey() {
		return _partnerEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this partner entry.
	*
	* @return the user ID of this partner entry
	*/
	@Override
	public long getUserId() {
		return _partnerEntry.getUserId();
	}

	@Override
	public long[] getSupportRegionIds() {
		return _partnerEntry.getSupportRegionIds();
	}

	@Override
	public void persist() {
		_partnerEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_partnerEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the code of this partner entry.
	*
	* @param code the code of this partner entry
	*/
	@Override
	public void setCode(java.lang.String code) {
		_partnerEntry.setCode(code);
	}

	/**
	* Sets the company ID of this partner entry.
	*
	* @param companyId the company ID of this partner entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_partnerEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this partner entry.
	*
	* @param createDate the create date of this partner entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_partnerEntry.setCreateDate(createDate);
	}

	/**
	* Sets the dossiera account key of this partner entry.
	*
	* @param dossieraAccountKey the dossiera account key of this partner entry
	*/
	@Override
	public void setDossieraAccountKey(java.lang.String dossieraAccountKey) {
		_partnerEntry.setDossieraAccountKey(dossieraAccountKey);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_partnerEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_partnerEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_partnerEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the jira project key of this partner entry.
	*
	* @param jiraProjectKey the jira project key of this partner entry
	*/
	@Override
	public void setJiraProjectKey(java.lang.String jiraProjectKey) {
		_partnerEntry.setJiraProjectKey(jiraProjectKey);
	}

	/**
	* Sets the modified date of this partner entry.
	*
	* @param modifiedDate the modified date of this partner entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_partnerEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the modified user ID of this partner entry.
	*
	* @param modifiedUserId the modified user ID of this partner entry
	*/
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_partnerEntry.setModifiedUserId(modifiedUserId);
	}

	/**
	* Sets the modified user name of this partner entry.
	*
	* @param modifiedUserName the modified user name of this partner entry
	*/
	@Override
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_partnerEntry.setModifiedUserName(modifiedUserName);
	}

	/**
	* Sets the modified user uuid of this partner entry.
	*
	* @param modifiedUserUuid the modified user uuid of this partner entry
	*/
	@Override
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_partnerEntry.setModifiedUserUuid(modifiedUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_partnerEntry.setNew(n);
	}

	/**
	* Sets the notes of this partner entry.
	*
	* @param notes the notes of this partner entry
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_partnerEntry.setNotes(notes);
	}

	/**
	* Sets the parent partner entry ID of this partner entry.
	*
	* @param parentPartnerEntryId the parent partner entry ID of this partner entry
	*/
	@Override
	public void setParentPartnerEntryId(long parentPartnerEntryId) {
		_partnerEntry.setParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Sets the partner entry ID of this partner entry.
	*
	* @param partnerEntryId the partner entry ID of this partner entry
	*/
	@Override
	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntry.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Sets the primary key of this partner entry.
	*
	* @param primaryKey the primary key of this partner entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_partnerEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_partnerEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this partner entry.
	*
	* @param status the status of this partner entry
	*/
	@Override
	public void setStatus(int status) {
		_partnerEntry.setStatus(status);
	}

	/**
	* Sets the user ID of this partner entry.
	*
	* @param userId the user ID of this partner entry
	*/
	@Override
	public void setUserId(long userId) {
		_partnerEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this partner entry.
	*
	* @param userName the user name of this partner entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_partnerEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this partner entry.
	*
	* @param userUuid the user uuid of this partner entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_partnerEntry.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerEntryWrapper)) {
			return false;
		}

		PartnerEntryWrapper partnerEntryWrapper = (PartnerEntryWrapper)obj;

		if (Objects.equals(_partnerEntry, partnerEntryWrapper._partnerEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public PartnerEntry getWrappedModel() {
		return _partnerEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _partnerEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _partnerEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_partnerEntry.resetOriginalValues();
	}

	private final PartnerEntry _partnerEntry;
}