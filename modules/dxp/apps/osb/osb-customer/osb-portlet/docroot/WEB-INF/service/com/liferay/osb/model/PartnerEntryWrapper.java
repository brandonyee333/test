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
 * This class is a wrapper for {@link PartnerEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PartnerEntry
 * @generated
 */
public class PartnerEntryWrapper implements PartnerEntry,
	ModelWrapper<PartnerEntry> {
	public PartnerEntryWrapper(PartnerEntry partnerEntry) {
		_partnerEntry = partnerEntry;
	}

	public Class<?> getModelClass() {
		return PartnerEntry.class;
	}

	public String getModelClassName() {
		return PartnerEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentPartnerEntryId", getParentPartnerEntryId());
		attributes.put("dossieraAccountKey", getDossieraAccountKey());
		attributes.put("code", getCode());
		attributes.put("notes", getNotes());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
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

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this partner entry.
	*
	* @return the primary key of this partner entry
	*/
	public long getPrimaryKey() {
		return _partnerEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this partner entry.
	*
	* @param primaryKey the primary key of this partner entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_partnerEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the partner entry ID of this partner entry.
	*
	* @return the partner entry ID of this partner entry
	*/
	public long getPartnerEntryId() {
		return _partnerEntry.getPartnerEntryId();
	}

	/**
	* Sets the partner entry ID of this partner entry.
	*
	* @param partnerEntryId the partner entry ID of this partner entry
	*/
	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntry.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the user ID of this partner entry.
	*
	* @return the user ID of this partner entry
	*/
	public long getUserId() {
		return _partnerEntry.getUserId();
	}

	/**
	* Sets the user ID of this partner entry.
	*
	* @param userId the user ID of this partner entry
	*/
	public void setUserId(long userId) {
		_partnerEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this partner entry.
	*
	* @return the user uuid of this partner entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this partner entry.
	*
	* @param userUuid the user uuid of this partner entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_partnerEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this partner entry.
	*
	* @return the user name of this partner entry
	*/
	public java.lang.String getUserName() {
		return _partnerEntry.getUserName();
	}

	/**
	* Sets the user name of this partner entry.
	*
	* @param userName the user name of this partner entry
	*/
	public void setUserName(java.lang.String userName) {
		_partnerEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this partner entry.
	*
	* @return the create date of this partner entry
	*/
	public java.util.Date getCreateDate() {
		return _partnerEntry.getCreateDate();
	}

	/**
	* Sets the create date of this partner entry.
	*
	* @param createDate the create date of this partner entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_partnerEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified user ID of this partner entry.
	*
	* @return the modified user ID of this partner entry
	*/
	public long getModifiedUserId() {
		return _partnerEntry.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this partner entry.
	*
	* @param modifiedUserId the modified user ID of this partner entry
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_partnerEntry.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this partner entry.
	*
	* @return the modified user uuid of this partner entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this partner entry.
	*
	* @param modifiedUserUuid the modified user uuid of this partner entry
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_partnerEntry.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this partner entry.
	*
	* @return the modified user name of this partner entry
	*/
	public java.lang.String getModifiedUserName() {
		return _partnerEntry.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this partner entry.
	*
	* @param modifiedUserName the modified user name of this partner entry
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_partnerEntry.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified date of this partner entry.
	*
	* @return the modified date of this partner entry
	*/
	public java.util.Date getModifiedDate() {
		return _partnerEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this partner entry.
	*
	* @param modifiedDate the modified date of this partner entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_partnerEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the parent partner entry ID of this partner entry.
	*
	* @return the parent partner entry ID of this partner entry
	*/
	public long getParentPartnerEntryId() {
		return _partnerEntry.getParentPartnerEntryId();
	}

	/**
	* Sets the parent partner entry ID of this partner entry.
	*
	* @param parentPartnerEntryId the parent partner entry ID of this partner entry
	*/
	public void setParentPartnerEntryId(long parentPartnerEntryId) {
		_partnerEntry.setParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Returns the dossiera account key of this partner entry.
	*
	* @return the dossiera account key of this partner entry
	*/
	public java.lang.String getDossieraAccountKey() {
		return _partnerEntry.getDossieraAccountKey();
	}

	/**
	* Sets the dossiera account key of this partner entry.
	*
	* @param dossieraAccountKey the dossiera account key of this partner entry
	*/
	public void setDossieraAccountKey(java.lang.String dossieraAccountKey) {
		_partnerEntry.setDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the code of this partner entry.
	*
	* @return the code of this partner entry
	*/
	public java.lang.String getCode() {
		return _partnerEntry.getCode();
	}

	/**
	* Sets the code of this partner entry.
	*
	* @param code the code of this partner entry
	*/
	public void setCode(java.lang.String code) {
		_partnerEntry.setCode(code);
	}

	/**
	* Returns the notes of this partner entry.
	*
	* @return the notes of this partner entry
	*/
	public java.lang.String getNotes() {
		return _partnerEntry.getNotes();
	}

	/**
	* Sets the notes of this partner entry.
	*
	* @param notes the notes of this partner entry
	*/
	public void setNotes(java.lang.String notes) {
		_partnerEntry.setNotes(notes);
	}

	/**
	* Returns the status of this partner entry.
	*
	* @return the status of this partner entry
	*/
	public int getStatus() {
		return _partnerEntry.getStatus();
	}

	/**
	* Sets the status of this partner entry.
	*
	* @param status the status of this partner entry
	*/
	public void setStatus(int status) {
		_partnerEntry.setStatus(status);
	}

	public boolean isNew() {
		return _partnerEntry.isNew();
	}

	public void setNew(boolean n) {
		_partnerEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _partnerEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_partnerEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _partnerEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _partnerEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_partnerEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _partnerEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_partnerEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PartnerEntryWrapper((PartnerEntry)_partnerEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.PartnerEntry partnerEntry) {
		return _partnerEntry.compareTo(partnerEntry);
	}

	@Override
	public int hashCode() {
		return _partnerEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.PartnerEntry> toCacheModel() {
		return _partnerEntry.toCacheModel();
	}

	public com.liferay.osb.model.PartnerEntry toEscapedModel() {
		return new PartnerEntryWrapper(_partnerEntry.toEscapedModel());
	}

	public com.liferay.osb.model.PartnerEntry toUnescapedModel() {
		return new PartnerEntryWrapper(_partnerEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _partnerEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _partnerEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntry.persist();
	}

	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getAccountEntries();
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> getChildPartnerEntries(
		boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getChildPartnerEntries(recursive);
	}

	public com.liferay.osb.model.PartnerEntry getParentPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getParentPartnerEntry();
	}

	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getPartnerWorkers();
	}

	public java.lang.String getStatusLabel() {
		return _partnerEntry.getStatusLabel();
	}

	public com.liferay.osb.model.SupportRegion getSupportRegion()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getSupportRegion();
	}

	public long[] getSupportRegionIds()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntry.getSupportRegionIds();
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

		if (Validator.equals(_partnerEntry, partnerEntryWrapper._partnerEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PartnerEntry getWrappedPartnerEntry() {
		return _partnerEntry;
	}

	public PartnerEntry getWrappedModel() {
		return _partnerEntry;
	}

	public void resetOriginalValues() {
		_partnerEntry.resetOriginalValues();
	}

	private PartnerEntry _partnerEntry;
}