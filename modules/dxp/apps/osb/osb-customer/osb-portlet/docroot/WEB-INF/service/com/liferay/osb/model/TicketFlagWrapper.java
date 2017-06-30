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
 * This class is a wrapper for {@link TicketFlag}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketFlag
 * @generated
 */
public class TicketFlagWrapper implements TicketFlag, ModelWrapper<TicketFlag> {
	public TicketFlagWrapper(TicketFlag ticketFlag) {
		_ticketFlag = ticketFlag;
	}

	public Class<?> getModelClass() {
		return TicketFlag.class;
	}

	public String getModelClassName() {
		return TicketFlag.class.getName();
	}

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

	/**
	* Returns the primary key of this ticket flag.
	*
	* @return the primary key of this ticket flag
	*/
	public long getPrimaryKey() {
		return _ticketFlag.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket flag.
	*
	* @param primaryKey the primary key of this ticket flag
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketFlag.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket flag ID of this ticket flag.
	*
	* @return the ticket flag ID of this ticket flag
	*/
	public long getTicketFlagId() {
		return _ticketFlag.getTicketFlagId();
	}

	/**
	* Sets the ticket flag ID of this ticket flag.
	*
	* @param ticketFlagId the ticket flag ID of this ticket flag
	*/
	public void setTicketFlagId(long ticketFlagId) {
		_ticketFlag.setTicketFlagId(ticketFlagId);
	}

	/**
	* Returns the user ID of this ticket flag.
	*
	* @return the user ID of this ticket flag
	*/
	public long getUserId() {
		return _ticketFlag.getUserId();
	}

	/**
	* Sets the user ID of this ticket flag.
	*
	* @param userId the user ID of this ticket flag
	*/
	public void setUserId(long userId) {
		_ticketFlag.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket flag.
	*
	* @return the user uuid of this ticket flag
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketFlag.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket flag.
	*
	* @param userUuid the user uuid of this ticket flag
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketFlag.setUserUuid(userUuid);
	}

	/**
	* Returns the modified date of this ticket flag.
	*
	* @return the modified date of this ticket flag
	*/
	public java.util.Date getModifiedDate() {
		return _ticketFlag.getModifiedDate();
	}

	/**
	* Sets the modified date of this ticket flag.
	*
	* @param modifiedDate the modified date of this ticket flag
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_ticketFlag.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this ticket flag.
	*
	* @return the account entry ID of this ticket flag
	*/
	public long getAccountEntryId() {
		return _ticketFlag.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this ticket flag.
	*
	* @param accountEntryId the account entry ID of this ticket flag
	*/
	public void setAccountEntryId(long accountEntryId) {
		_ticketFlag.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the ticket entry ID of this ticket flag.
	*
	* @return the ticket entry ID of this ticket flag
	*/
	public long getTicketEntryId() {
		return _ticketFlag.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket flag.
	*
	* @param ticketEntryId the ticket entry ID of this ticket flag
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketFlag.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the type of this ticket flag.
	*
	* @return the type of this ticket flag
	*/
	public int getType() {
		return _ticketFlag.getType();
	}

	/**
	* Sets the type of this ticket flag.
	*
	* @param type the type of this ticket flag
	*/
	public void setType(int type) {
		_ticketFlag.setType(type);
	}

	/**
	* Returns the flag of this ticket flag.
	*
	* @return the flag of this ticket flag
	*/
	public int getFlag() {
		return _ticketFlag.getFlag();
	}

	/**
	* Sets the flag of this ticket flag.
	*
	* @param flag the flag of this ticket flag
	*/
	public void setFlag(int flag) {
		_ticketFlag.setFlag(flag);
	}

	public boolean isNew() {
		return _ticketFlag.isNew();
	}

	public void setNew(boolean n) {
		_ticketFlag.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketFlag.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketFlag.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketFlag.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketFlag.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketFlag.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketFlag.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketFlag.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketFlagWrapper((TicketFlag)_ticketFlag.clone());
	}

	public int compareTo(com.liferay.osb.model.TicketFlag ticketFlag) {
		return _ticketFlag.compareTo(ticketFlag);
	}

	@Override
	public int hashCode() {
		return _ticketFlag.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketFlag> toCacheModel() {
		return _ticketFlag.toCacheModel();
	}

	public com.liferay.osb.model.TicketFlag toEscapedModel() {
		return new TicketFlagWrapper(_ticketFlag.toEscapedModel());
	}

	public com.liferay.osb.model.TicketFlag toUnescapedModel() {
		return new TicketFlagWrapper(_ticketFlag.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketFlag.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketFlag.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketFlag.persist();
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

		if (Validator.equals(_ticketFlag, ticketFlagWrapper._ticketFlag)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketFlag getWrappedTicketFlag() {
		return _ticketFlag;
	}

	public TicketFlag getWrappedModel() {
		return _ticketFlag;
	}

	public void resetOriginalValues() {
		_ticketFlag.resetOriginalValues();
	}

	private TicketFlag _ticketFlag;
}