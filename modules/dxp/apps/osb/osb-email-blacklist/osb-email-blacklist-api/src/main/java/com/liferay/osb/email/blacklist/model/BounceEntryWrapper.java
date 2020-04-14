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
 * This class is a wrapper for {@link BounceEntry}.
 * </p>
 *
 * @author Jamie Sammons
 * @see BounceEntry
 * @generated
 */
public class BounceEntryWrapper
	implements BounceEntry, ModelWrapper<BounceEntry> {

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
	public Object clone() {
		return new BounceEntryWrapper((BounceEntry)_bounceEntry.clone());
	}

	@Override
	public int compareTo(BounceEntry bounceEntry) {
		return _bounceEntry.compareTo(bounceEntry);
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
	 * Returns the bounce subtype of this bounce entry.
	 *
	 * @return the bounce subtype of this bounce entry
	 */
	@Override
	public String getBounceSubtype() {
		return _bounceEntry.getBounceSubtype();
	}

	/**
	 * Returns the bounce type of this bounce entry.
	 *
	 * @return the bounce type of this bounce entry
	 */
	@Override
	public String getBounceType() {
		return _bounceEntry.getBounceType();
	}

	/**
	 * Returns the email address of this bounce entry.
	 *
	 * @return the email address of this bounce entry
	 */
	@Override
	public String getEmailAddress() {
		return _bounceEntry.getEmailAddress();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _bounceEntry.getExpandoBridge();
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
	public Serializable getPrimaryKeyObj() {
		return _bounceEntry.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _bounceEntry.hashCode();
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
	public void setBounceSubtype(String bounceSubtype) {
		_bounceEntry.setBounceSubtype(bounceSubtype);
	}

	/**
	 * Sets the bounce type of this bounce entry.
	 *
	 * @param bounceType the bounce type of this bounce entry
	 */
	@Override
	public void setBounceType(String bounceType) {
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
	public void setEmailAddress(String emailAddress) {
		_bounceEntry.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_bounceEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_bounceEntry.setExpandoBridgeAttributes(expandoBridge);
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
	public com.liferay.portal.kernel.model.CacheModel<BounceEntry>
		toCacheModel() {

		return _bounceEntry.toCacheModel();
	}

	@Override
	public BounceEntry toEscapedModel() {
		return new BounceEntryWrapper(_bounceEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _bounceEntry.toString();
	}

	@Override
	public BounceEntry toUnescapedModel() {
		return new BounceEntryWrapper(_bounceEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _bounceEntry.toXmlString();
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