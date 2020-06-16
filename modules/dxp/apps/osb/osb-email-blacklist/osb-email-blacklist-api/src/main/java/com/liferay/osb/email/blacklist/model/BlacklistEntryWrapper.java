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

package com.liferay.osb.email.blacklist.model;

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
 * This class is a wrapper for {@link BlacklistEntry}.
 * </p>
 *
 * @author Jamie Sammons
 * @see BlacklistEntry
 * @generated
 */
@ProviderType
public class BlacklistEntryWrapper implements BlacklistEntry,
	ModelWrapper<BlacklistEntry> {
	public BlacklistEntryWrapper(BlacklistEntry blacklistEntry) {
		_blacklistEntry = blacklistEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return BlacklistEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BlacklistEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("blacklistEntryId", getBlacklistEntryId());
		attributes.put("createDate", getCreateDate());
		attributes.put("emailAddress", getEmailAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long blacklistEntryId = (Long)attributes.get("blacklistEntryId");

		if (blacklistEntryId != null) {
			setBlacklistEntryId(blacklistEntryId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}
	}

	@Override
	public BlacklistEntry toEscapedModel() {
		return new BlacklistEntryWrapper(_blacklistEntry.toEscapedModel());
	}

	@Override
	public BlacklistEntry toUnescapedModel() {
		return new BlacklistEntryWrapper(_blacklistEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _blacklistEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _blacklistEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _blacklistEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _blacklistEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BlacklistEntry> toCacheModel() {
		return _blacklistEntry.toCacheModel();
	}

	@Override
	public int compareTo(BlacklistEntry blacklistEntry) {
		return _blacklistEntry.compareTo(blacklistEntry);
	}

	@Override
	public int hashCode() {
		return _blacklistEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _blacklistEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BlacklistEntryWrapper((BlacklistEntry)_blacklistEntry.clone());
	}

	/**
	* Returns the email address of this blacklist entry.
	*
	* @return the email address of this blacklist entry
	*/
	@Override
	public java.lang.String getEmailAddress() {
		return _blacklistEntry.getEmailAddress();
	}

	@Override
	public java.lang.String toString() {
		return _blacklistEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _blacklistEntry.toXmlString();
	}

	/**
	* Returns the create date of this blacklist entry.
	*
	* @return the create date of this blacklist entry
	*/
	@Override
	public Date getCreateDate() {
		return _blacklistEntry.getCreateDate();
	}

	/**
	* Returns the blacklist entry ID of this blacklist entry.
	*
	* @return the blacklist entry ID of this blacklist entry
	*/
	@Override
	public long getBlacklistEntryId() {
		return _blacklistEntry.getBlacklistEntryId();
	}

	/**
	* Returns the primary key of this blacklist entry.
	*
	* @return the primary key of this blacklist entry
	*/
	@Override
	public long getPrimaryKey() {
		return _blacklistEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_blacklistEntry.persist();
	}

	/**
	* Sets the blacklist entry ID of this blacklist entry.
	*
	* @param blacklistEntryId the blacklist entry ID of this blacklist entry
	*/
	@Override
	public void setBlacklistEntryId(long blacklistEntryId) {
		_blacklistEntry.setBlacklistEntryId(blacklistEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_blacklistEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this blacklist entry.
	*
	* @param createDate the create date of this blacklist entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_blacklistEntry.setCreateDate(createDate);
	}

	/**
	* Sets the email address of this blacklist entry.
	*
	* @param emailAddress the email address of this blacklist entry
	*/
	@Override
	public void setEmailAddress(java.lang.String emailAddress) {
		_blacklistEntry.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_blacklistEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_blacklistEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_blacklistEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_blacklistEntry.setNew(n);
	}

	/**
	* Sets the primary key of this blacklist entry.
	*
	* @param primaryKey the primary key of this blacklist entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_blacklistEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_blacklistEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BlacklistEntryWrapper)) {
			return false;
		}

		BlacklistEntryWrapper blacklistEntryWrapper = (BlacklistEntryWrapper)obj;

		if (Objects.equals(_blacklistEntry,
					blacklistEntryWrapper._blacklistEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public BlacklistEntry getWrappedModel() {
		return _blacklistEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _blacklistEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _blacklistEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_blacklistEntry.resetOriginalValues();
	}

	private final BlacklistEntry _blacklistEntry;
}