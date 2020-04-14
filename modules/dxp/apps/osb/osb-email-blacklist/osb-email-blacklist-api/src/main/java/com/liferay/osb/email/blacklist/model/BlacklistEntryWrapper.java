/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.email.blacklist.model;

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
public class BlacklistEntryWrapper
	implements BlacklistEntry, ModelWrapper<BlacklistEntry> {

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
	public Object clone() {
		return new BlacklistEntryWrapper(
			(BlacklistEntry)_blacklistEntry.clone());
	}

	@Override
	public int compareTo(BlacklistEntry blacklistEntry) {
		return _blacklistEntry.compareTo(blacklistEntry);
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
	 * Returns the create date of this blacklist entry.
	 *
	 * @return the create date of this blacklist entry
	 */
	@Override
	public Date getCreateDate() {
		return _blacklistEntry.getCreateDate();
	}

	/**
	 * Returns the email address of this blacklist entry.
	 *
	 * @return the email address of this blacklist entry
	 */
	@Override
	public String getEmailAddress() {
		return _blacklistEntry.getEmailAddress();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _blacklistEntry.getExpandoBridge();
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
	public Serializable getPrimaryKeyObj() {
		return _blacklistEntry.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _blacklistEntry.hashCode();
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
	public void setEmailAddress(String emailAddress) {
		_blacklistEntry.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_blacklistEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_blacklistEntry.setExpandoBridgeAttributes(expandoBridge);
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
	public com.liferay.portal.kernel.model.CacheModel<BlacklistEntry>
		toCacheModel() {

		return _blacklistEntry.toCacheModel();
	}

	@Override
	public BlacklistEntry toEscapedModel() {
		return new BlacklistEntryWrapper(_blacklistEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _blacklistEntry.toString();
	}

	@Override
	public BlacklistEntry toUnescapedModel() {
		return new BlacklistEntryWrapper(_blacklistEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _blacklistEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BlacklistEntryWrapper)) {
			return false;
		}

		BlacklistEntryWrapper blacklistEntryWrapper =
			(BlacklistEntryWrapper)obj;

		if (Objects.equals(
				_blacklistEntry, blacklistEntryWrapper._blacklistEntry)) {

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