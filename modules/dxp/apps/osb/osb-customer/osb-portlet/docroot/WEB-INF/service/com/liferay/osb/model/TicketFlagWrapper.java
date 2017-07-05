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
 * This class is a wrapper for {@link TicketFlag}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlag
 * @generated
 */
@ProviderType
public class TicketFlagWrapper implements TicketFlag, ModelWrapper<TicketFlag> {
	public TicketFlagWrapper(TicketFlag ticketFlag) {
		_ticketFlag = ticketFlag;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketFlag.class;
	}

	@Override
	public String getModelClassName() {
		return TicketFlag.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketFlagId", getTicketFlagId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("type", getType());
		attributes.put("flag", getFlag());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketFlagId = (Long)attributes.get("ticketFlagId");

		if (ticketFlagId != null) {
			setTicketFlagId(ticketFlagId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer flag = (Integer)attributes.get("flag");

		if (flag != null) {
			setFlag(flag);
		}
	}

	@Override
	public TicketFlag toEscapedModel() {
		return new TicketFlagWrapper(_ticketFlag.toEscapedModel());
	}

	@Override
	public TicketFlag toUnescapedModel() {
		return new TicketFlagWrapper(_ticketFlag.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketFlag.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketFlag.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketFlag.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketFlag.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketFlag> toCacheModel() {
		return _ticketFlag.toCacheModel();
	}

	@Override
	public int compareTo(TicketFlag ticketFlag) {
		return _ticketFlag.compareTo(ticketFlag);
	}

	/**
	* Returns the flag of this ticket flag.
	*
	* @return the flag of this ticket flag
	*/
	@Override
	public int getFlag() {
		return _ticketFlag.getFlag();
	}

	/**
	* Returns the type of this ticket flag.
	*
	* @return the type of this ticket flag
	*/
	@Override
	public int getType() {
		return _ticketFlag.getType();
	}

	@Override
	public int hashCode() {
		return _ticketFlag.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketFlag.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketFlagWrapper((TicketFlag)_ticketFlag.clone());
	}

	/**
	* Returns the user uuid of this ticket flag.
	*
	* @return the user uuid of this ticket flag
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketFlag.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ticketFlag.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketFlag.toXmlString();
	}

	/**
	* Returns the modified date of this ticket flag.
	*
	* @return the modified date of this ticket flag
	*/
	@Override
	public Date getModifiedDate() {
		return _ticketFlag.getModifiedDate();
	}

	/**
	* Returns the account entry ID of this ticket flag.
	*
	* @return the account entry ID of this ticket flag
	*/
	@Override
	public long getAccountEntryId() {
		return _ticketFlag.getAccountEntryId();
	}

	/**
	* Returns the primary key of this ticket flag.
	*
	* @return the primary key of this ticket flag
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketFlag.getPrimaryKey();
	}

	/**
	* Returns the ticket entry ID of this ticket flag.
	*
	* @return the ticket entry ID of this ticket flag
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketFlag.getTicketEntryId();
	}

	/**
	* Returns the ticket flag ID of this ticket flag.
	*
	* @return the ticket flag ID of this ticket flag
	*/
	@Override
	public long getTicketFlagId() {
		return _ticketFlag.getTicketFlagId();
	}

	/**
	* Returns the user ID of this ticket flag.
	*
	* @return the user ID of this ticket flag
	*/
	@Override
	public long getUserId() {
		return _ticketFlag.getUserId();
	}

	@Override
	public void persist() {
		_ticketFlag.persist();
	}

	/**
	* Sets the account entry ID of this ticket flag.
	*
	* @param accountEntryId the account entry ID of this ticket flag
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_ticketFlag.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketFlag.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketFlag.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketFlag.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketFlag.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the flag of this ticket flag.
	*
	* @param flag the flag of this ticket flag
	*/
	@Override
	public void setFlag(int flag) {
		_ticketFlag.setFlag(flag);
	}

	/**
	* Sets the modified date of this ticket flag.
	*
	* @param modifiedDate the modified date of this ticket flag
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ticketFlag.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ticketFlag.setNew(n);
	}

	/**
	* Sets the primary key of this ticket flag.
	*
	* @param primaryKey the primary key of this ticket flag
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketFlag.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketFlag.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ticket entry ID of this ticket flag.
	*
	* @param ticketEntryId the ticket entry ID of this ticket flag
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketFlag.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket flag ID of this ticket flag.
	*
	* @param ticketFlagId the ticket flag ID of this ticket flag
	*/
	@Override
	public void setTicketFlagId(long ticketFlagId) {
		_ticketFlag.setTicketFlagId(ticketFlagId);
	}

	/**
	* Sets the type of this ticket flag.
	*
	* @param type the type of this ticket flag
	*/
	@Override
	public void setType(int type) {
		_ticketFlag.setType(type);
	}

	/**
	* Sets the user ID of this ticket flag.
	*
	* @param userId the user ID of this ticket flag
	*/
	@Override
	public void setUserId(long userId) {
		_ticketFlag.setUserId(userId);
	}

	/**
	* Sets the user uuid of this ticket flag.
	*
	* @param userUuid the user uuid of this ticket flag
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketFlag.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketFlagWrapper)) {
			return false;
		}

		TicketFlagWrapper ticketFlagWrapper = (TicketFlagWrapper)obj;

		if (Objects.equals(_ticketFlag, ticketFlagWrapper._ticketFlag)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketFlag getWrappedModel() {
		return _ticketFlag;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketFlag.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketFlag.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketFlag.resetOriginalValues();
	}

	private final TicketFlag _ticketFlag;
}