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
 * This class is a wrapper for {@link TicketCall}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCall
 * @generated
 */
@ProviderType
public class TicketCallWrapper implements TicketCall, ModelWrapper<TicketCall> {
	public TicketCallWrapper(TicketCall ticketCall) {
		_ticketCall = ticketCall;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketCall.class;
	}

	@Override
	public String getModelClassName() {
		return TicketCall.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketCallId", getTicketCallId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("type", getType());
		attributes.put("callDate", getCallDate());
		attributes.put("callLength", getCallLength());
		attributes.put("customerName", getCustomerName());
		attributes.put("customerContact", getCustomerContact());
		attributes.put("confirmation", getConfirmation());
		attributes.put("instructions", getInstructions());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketCallId = (Long)attributes.get("ticketCallId");

		if (ticketCallId != null) {
			setTicketCallId(ticketCallId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date callDate = (Date)attributes.get("callDate");

		if (callDate != null) {
			setCallDate(callDate);
		}

		Long callLength = (Long)attributes.get("callLength");

		if (callLength != null) {
			setCallLength(callLength);
		}

		String customerName = (String)attributes.get("customerName");

		if (customerName != null) {
			setCustomerName(customerName);
		}

		String customerContact = (String)attributes.get("customerContact");

		if (customerContact != null) {
			setCustomerContact(customerContact);
		}

		String confirmation = (String)attributes.get("confirmation");

		if (confirmation != null) {
			setConfirmation(confirmation);
		}

		String instructions = (String)attributes.get("instructions");

		if (instructions != null) {
			setInstructions(instructions);
		}
	}

	@Override
	public TicketCall toEscapedModel() {
		return new TicketCallWrapper(_ticketCall.toEscapedModel());
	}

	@Override
	public TicketCall toUnescapedModel() {
		return new TicketCallWrapper(_ticketCall.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketCall.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketCall.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketCall.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketCall.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketCall> toCacheModel() {
		return _ticketCall.toCacheModel();
	}

	@Override
	public int compareTo(TicketCall ticketCall) {
		return _ticketCall.compareTo(ticketCall);
	}

	/**
	* Returns the type of this ticket call.
	*
	* @return the type of this ticket call
	*/
	@Override
	public int getType() {
		return _ticketCall.getType();
	}

	@Override
	public int hashCode() {
		return _ticketCall.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketCall.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketCallWrapper((TicketCall)_ticketCall.clone());
	}

	@Override
	public java.lang.String getCallLengthLabel() {
		return _ticketCall.getCallLengthLabel();
	}

	/**
	* Returns the confirmation of this ticket call.
	*
	* @return the confirmation of this ticket call
	*/
	@Override
	public java.lang.String getConfirmation() {
		return _ticketCall.getConfirmation();
	}

	/**
	* Returns the customer contact of this ticket call.
	*
	* @return the customer contact of this ticket call
	*/
	@Override
	public java.lang.String getCustomerContact() {
		return _ticketCall.getCustomerContact();
	}

	/**
	* Returns the customer name of this ticket call.
	*
	* @return the customer name of this ticket call
	*/
	@Override
	public java.lang.String getCustomerName() {
		return _ticketCall.getCustomerName();
	}

	/**
	* Returns the instructions of this ticket call.
	*
	* @return the instructions of this ticket call
	*/
	@Override
	public java.lang.String getInstructions() {
		return _ticketCall.getInstructions();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _ticketCall.getTypeLabel();
	}

	/**
	* Returns the user name of this ticket call.
	*
	* @return the user name of this ticket call
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketCall.getUserName();
	}

	/**
	* Returns the user uuid of this ticket call.
	*
	* @return the user uuid of this ticket call
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketCall.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ticketCall.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketCall.toXmlString();
	}

	/**
	* Returns the call date of this ticket call.
	*
	* @return the call date of this ticket call
	*/
	@Override
	public Date getCallDate() {
		return _ticketCall.getCallDate();
	}

	/**
	* Returns the create date of this ticket call.
	*
	* @return the create date of this ticket call
	*/
	@Override
	public Date getCreateDate() {
		return _ticketCall.getCreateDate();
	}

	/**
	* Returns the call length of this ticket call.
	*
	* @return the call length of this ticket call
	*/
	@Override
	public long getCallLength() {
		return _ticketCall.getCallLength();
	}

	/**
	* Returns the primary key of this ticket call.
	*
	* @return the primary key of this ticket call
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketCall.getPrimaryKey();
	}

	/**
	* Returns the ticket call ID of this ticket call.
	*
	* @return the ticket call ID of this ticket call
	*/
	@Override
	public long getTicketCallId() {
		return _ticketCall.getTicketCallId();
	}

	/**
	* Returns the ticket entry ID of this ticket call.
	*
	* @return the ticket entry ID of this ticket call
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketCall.getTicketEntryId();
	}

	/**
	* Returns the user ID of this ticket call.
	*
	* @return the user ID of this ticket call
	*/
	@Override
	public long getUserId() {
		return _ticketCall.getUserId();
	}

	@Override
	public void persist() {
		_ticketCall.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketCall.setCachedModel(cachedModel);
	}

	/**
	* Sets the call date of this ticket call.
	*
	* @param callDate the call date of this ticket call
	*/
	@Override
	public void setCallDate(Date callDate) {
		_ticketCall.setCallDate(callDate);
	}

	/**
	* Sets the call length of this ticket call.
	*
	* @param callLength the call length of this ticket call
	*/
	@Override
	public void setCallLength(long callLength) {
		_ticketCall.setCallLength(callLength);
	}

	/**
	* Sets the confirmation of this ticket call.
	*
	* @param confirmation the confirmation of this ticket call
	*/
	@Override
	public void setConfirmation(java.lang.String confirmation) {
		_ticketCall.setConfirmation(confirmation);
	}

	/**
	* Sets the create date of this ticket call.
	*
	* @param createDate the create date of this ticket call
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketCall.setCreateDate(createDate);
	}

	/**
	* Sets the customer contact of this ticket call.
	*
	* @param customerContact the customer contact of this ticket call
	*/
	@Override
	public void setCustomerContact(java.lang.String customerContact) {
		_ticketCall.setCustomerContact(customerContact);
	}

	/**
	* Sets the customer name of this ticket call.
	*
	* @param customerName the customer name of this ticket call
	*/
	@Override
	public void setCustomerName(java.lang.String customerName) {
		_ticketCall.setCustomerName(customerName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketCall.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketCall.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketCall.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the instructions of this ticket call.
	*
	* @param instructions the instructions of this ticket call
	*/
	@Override
	public void setInstructions(java.lang.String instructions) {
		_ticketCall.setInstructions(instructions);
	}

	@Override
	public void setNew(boolean n) {
		_ticketCall.setNew(n);
	}

	/**
	* Sets the primary key of this ticket call.
	*
	* @param primaryKey the primary key of this ticket call
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketCall.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketCall.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ticket call ID of this ticket call.
	*
	* @param ticketCallId the ticket call ID of this ticket call
	*/
	@Override
	public void setTicketCallId(long ticketCallId) {
		_ticketCall.setTicketCallId(ticketCallId);
	}

	/**
	* Sets the ticket entry ID of this ticket call.
	*
	* @param ticketEntryId the ticket entry ID of this ticket call
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketCall.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the type of this ticket call.
	*
	* @param type the type of this ticket call
	*/
	@Override
	public void setType(int type) {
		_ticketCall.setType(type);
	}

	/**
	* Sets the user ID of this ticket call.
	*
	* @param userId the user ID of this ticket call
	*/
	@Override
	public void setUserId(long userId) {
		_ticketCall.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket call.
	*
	* @param userName the user name of this ticket call
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketCall.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket call.
	*
	* @param userUuid the user uuid of this ticket call
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketCall.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketCallWrapper)) {
			return false;
		}

		TicketCallWrapper ticketCallWrapper = (TicketCallWrapper)obj;

		if (Objects.equals(_ticketCall, ticketCallWrapper._ticketCall)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketCall getWrappedModel() {
		return _ticketCall;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketCall.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketCall.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketCall.resetOriginalValues();
	}

	private final TicketCall _ticketCall;
}