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
 * This class is a wrapper for {@link BounceEntry}.
 * </p>
 *
 * @author Jamie Sammons
 * @see BounceEntry
 * @generated
 */
@ProviderType
public class BounceEntryWrapper implements BounceEntry,
	ModelWrapper<BounceEntry> {
	public BounceEntryWrapper(BounceEntry bounceEntry) {
		_bounceEntry = bounceEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return BounceEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BounceEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bounceEntryId", getBounceEntryId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("bounceDate", getBounceDate());
		attributes.put("bounceType", getBounceType());
		attributes.put("bounceSubtype", getBounceSubtype());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bounceEntryId = (Long)attributes.get("bounceEntryId");

		if (bounceEntryId != null) {
			setBounceEntryId(bounceEntryId);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Date bounceDate = (Date)attributes.get("bounceDate");

		if (bounceDate != null) {
			setBounceDate(bounceDate);
		}

		String bounceType = (String)attributes.get("bounceType");

		if (bounceType != null) {
			setBounceType(bounceType);
		}

		String bounceSubtype = (String)attributes.get("bounceSubtype");

		if (bounceSubtype != null) {
			setBounceSubtype(bounceSubtype);
		}
	}

	@Override
	public BounceEntry toEscapedModel() {
		return new BounceEntryWrapper(_bounceEntry.toEscapedModel());
	}

	@Override
	public BounceEntry toUnescapedModel() {
		return new BounceEntryWrapper(_bounceEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _bounceEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _bounceEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _bounceEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _bounceEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BounceEntry> toCacheModel() {
		return _bounceEntry.toCacheModel();
	}

	@Override
	public int compareTo(BounceEntry bounceEntry) {
		return _bounceEntry.compareTo(bounceEntry);
	}

	@Override
	public int hashCode() {
		return _bounceEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bounceEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BounceEntryWrapper((BounceEntry)_bounceEntry.clone());
	}

	/**
	* Returns the bounce subtype of this bounce entry.
	*
	* @return the bounce subtype of this bounce entry
	*/
	@Override
	public java.lang.String getBounceSubtype() {
		return _bounceEntry.getBounceSubtype();
	}

	/**
	* Returns the bounce type of this bounce entry.
	*
	* @return the bounce type of this bounce entry
	*/
	@Override
	public java.lang.String getBounceType() {
		return _bounceEntry.getBounceType();
	}

	/**
	* Returns the email address of this bounce entry.
	*
	* @return the email address of this bounce entry
	*/
	@Override
	public java.lang.String getEmailAddress() {
		return _bounceEntry.getEmailAddress();
	}

	@Override
	public java.lang.String toString() {
		return _bounceEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _bounceEntry.toXmlString();
	}

	/**
	* Returns the bounce date of this bounce entry.
	*
	* @return the bounce date of this bounce entry
	*/
	@Override
	public Date getBounceDate() {
		return _bounceEntry.getBounceDate();
	}

	/**
	* Returns the bounce entry ID of this bounce entry.
	*
	* @return the bounce entry ID of this bounce entry
	*/
	@Override
	public long getBounceEntryId() {
		return _bounceEntry.getBounceEntryId();
	}

	/**
	* Returns the primary key of this bounce entry.
	*
	* @return the primary key of this bounce entry
	*/
	@Override
	public long getPrimaryKey() {
		return _bounceEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_bounceEntry.persist();
	}

	/**
	* Sets the bounce date of this bounce entry.
	*
	* @param bounceDate the bounce date of this bounce entry
	*/
	@Override
	public void setBounceDate(Date bounceDate) {
		_bounceEntry.setBounceDate(bounceDate);
	}

	/**
	* Sets the bounce entry ID of this bounce entry.
	*
	* @param bounceEntryId the bounce entry ID of this bounce entry
	*/
	@Override
	public void setBounceEntryId(long bounceEntryId) {
		_bounceEntry.setBounceEntryId(bounceEntryId);
	}

	/**
	* Sets the bounce subtype of this bounce entry.
	*
	* @param bounceSubtype the bounce subtype of this bounce entry
	*/
	@Override
	public void setBounceSubtype(java.lang.String bounceSubtype) {
		_bounceEntry.setBounceSubtype(bounceSubtype);
	}

	/**
	* Sets the bounce type of this bounce entry.
	*
	* @param bounceType the bounce type of this bounce entry
	*/
	@Override
	public void setBounceType(java.lang.String bounceType) {
		_bounceEntry.setBounceType(bounceType);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_bounceEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the email address of this bounce entry.
	*
	* @param emailAddress the email address of this bounce entry
	*/
	@Override
	public void setEmailAddress(java.lang.String emailAddress) {
		_bounceEntry.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_bounceEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_bounceEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_bounceEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_bounceEntry.setNew(n);
	}

	/**
	* Sets the primary key of this bounce entry.
	*
	* @param primaryKey the primary key of this bounce entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_bounceEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_bounceEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BounceEntryWrapper)) {
			return false;
		}

		BounceEntryWrapper bounceEntryWrapper = (BounceEntryWrapper)obj;

		if (Objects.equals(_bounceEntry, bounceEntryWrapper._bounceEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public BounceEntry getWrappedModel() {
		return _bounceEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _bounceEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _bounceEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_bounceEntry.resetOriginalValues();
	}

	private final BounceEntry _bounceEntry;
}