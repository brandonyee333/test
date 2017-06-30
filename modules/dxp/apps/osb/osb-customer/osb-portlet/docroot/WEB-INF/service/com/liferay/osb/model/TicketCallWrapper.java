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
 * This class is a wrapper for {@link TicketCall}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketCall
 * @generated
 */
public class TicketCallWrapper implements TicketCall, ModelWrapper<TicketCall> {
	public TicketCallWrapper(TicketCall ticketCall) {
		_ticketCall = ticketCall;
	}

	public Class<?> getModelClass() {
		return TicketCall.class;
	}

	public String getModelClassName() {
		return TicketCall.class.getName();
	}

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

	/**
	* Returns the primary key of this ticket call.
	*
	* @return the primary key of this ticket call
	*/
	public long getPrimaryKey() {
		return _ticketCall.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket call.
	*
	* @param primaryKey the primary key of this ticket call
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketCall.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket call ID of this ticket call.
	*
	* @return the ticket call ID of this ticket call
	*/
	public long getTicketCallId() {
		return _ticketCall.getTicketCallId();
	}

	/**
	* Sets the ticket call ID of this ticket call.
	*
	* @param ticketCallId the ticket call ID of this ticket call
	*/
	public void setTicketCallId(long ticketCallId) {
		_ticketCall.setTicketCallId(ticketCallId);
	}

	/**
	* Returns the user ID of this ticket call.
	*
	* @return the user ID of this ticket call
	*/
	public long getUserId() {
		return _ticketCall.getUserId();
	}

	/**
	* Sets the user ID of this ticket call.
	*
	* @param userId the user ID of this ticket call
	*/
	public void setUserId(long userId) {
		_ticketCall.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket call.
	*
	* @return the user uuid of this ticket call
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCall.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket call.
	*
	* @param userUuid the user uuid of this ticket call
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketCall.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this ticket call.
	*
	* @return the user name of this ticket call
	*/
	public java.lang.String getUserName() {
		return _ticketCall.getUserName();
	}

	/**
	* Sets the user name of this ticket call.
	*
	* @param userName the user name of this ticket call
	*/
	public void setUserName(java.lang.String userName) {
		_ticketCall.setUserName(userName);
	}

	/**
	* Returns the create date of this ticket call.
	*
	* @return the create date of this ticket call
	*/
	public java.util.Date getCreateDate() {
		return _ticketCall.getCreateDate();
	}

	/**
	* Sets the create date of this ticket call.
	*
	* @param createDate the create date of this ticket call
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketCall.setCreateDate(createDate);
	}

	/**
	* Returns the ticket entry ID of this ticket call.
	*
	* @return the ticket entry ID of this ticket call
	*/
	public long getTicketEntryId() {
		return _ticketCall.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket call.
	*
	* @param ticketEntryId the ticket entry ID of this ticket call
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketCall.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the type of this ticket call.
	*
	* @return the type of this ticket call
	*/
	public int getType() {
		return _ticketCall.getType();
	}

	/**
	* Sets the type of this ticket call.
	*
	* @param type the type of this ticket call
	*/
	public void setType(int type) {
		_ticketCall.setType(type);
	}

	/**
	* Returns the call date of this ticket call.
	*
	* @return the call date of this ticket call
	*/
	public java.util.Date getCallDate() {
		return _ticketCall.getCallDate();
	}

	/**
	* Sets the call date of this ticket call.
	*
	* @param callDate the call date of this ticket call
	*/
	public void setCallDate(java.util.Date callDate) {
		_ticketCall.setCallDate(callDate);
	}

	/**
	* Returns the call length of this ticket call.
	*
	* @return the call length of this ticket call
	*/
	public long getCallLength() {
		return _ticketCall.getCallLength();
	}

	/**
	* Sets the call length of this ticket call.
	*
	* @param callLength the call length of this ticket call
	*/
	public void setCallLength(long callLength) {
		_ticketCall.setCallLength(callLength);
	}

	/**
	* Returns the customer name of this ticket call.
	*
	* @return the customer name of this ticket call
	*/
	public java.lang.String getCustomerName() {
		return _ticketCall.getCustomerName();
	}

	/**
	* Sets the customer name of this ticket call.
	*
	* @param customerName the customer name of this ticket call
	*/
	public void setCustomerName(java.lang.String customerName) {
		_ticketCall.setCustomerName(customerName);
	}

	/**
	* Returns the customer contact of this ticket call.
	*
	* @return the customer contact of this ticket call
	*/
	public java.lang.String getCustomerContact() {
		return _ticketCall.getCustomerContact();
	}

	/**
	* Sets the customer contact of this ticket call.
	*
	* @param customerContact the customer contact of this ticket call
	*/
	public void setCustomerContact(java.lang.String customerContact) {
		_ticketCall.setCustomerContact(customerContact);
	}

	/**
	* Returns the confirmation of this ticket call.
	*
	* @return the confirmation of this ticket call
	*/
	public java.lang.String getConfirmation() {
		return _ticketCall.getConfirmation();
	}

	/**
	* Sets the confirmation of this ticket call.
	*
	* @param confirmation the confirmation of this ticket call
	*/
	public void setConfirmation(java.lang.String confirmation) {
		_ticketCall.setConfirmation(confirmation);
	}

	/**
	* Returns the instructions of this ticket call.
	*
	* @return the instructions of this ticket call
	*/
	public java.lang.String getInstructions() {
		return _ticketCall.getInstructions();
	}

	/**
	* Sets the instructions of this ticket call.
	*
	* @param instructions the instructions of this ticket call
	*/
	public void setInstructions(java.lang.String instructions) {
		_ticketCall.setInstructions(instructions);
	}

	public boolean isNew() {
		return _ticketCall.isNew();
	}

	public void setNew(boolean n) {
		_ticketCall.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketCall.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketCall.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketCall.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketCall.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketCall.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketCall.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketCall.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketCallWrapper((TicketCall)_ticketCall.clone());
	}

	public int compareTo(com.liferay.osb.model.TicketCall ticketCall) {
		return _ticketCall.compareTo(ticketCall);
	}

	@Override
	public int hashCode() {
		return _ticketCall.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketCall> toCacheModel() {
		return _ticketCall.toCacheModel();
	}

	public com.liferay.osb.model.TicketCall toEscapedModel() {
		return new TicketCallWrapper(_ticketCall.toEscapedModel());
	}

	public com.liferay.osb.model.TicketCall toUnescapedModel() {
		return new TicketCallWrapper(_ticketCall.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketCall.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketCall.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketCall.persist();
	}

	public java.lang.String getCallLengthLabel() {
		return _ticketCall.getCallLengthLabel();
	}

	public java.lang.String getTypeLabel() {
		return _ticketCall.getTypeLabel();
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

		if (Validator.equals(_ticketCall, ticketCallWrapper._ticketCall)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketCall getWrappedTicketCall() {
		return _ticketCall;
	}

	public TicketCall getWrappedModel() {
		return _ticketCall;
	}

	public void resetOriginalValues() {
		_ticketCall.resetOriginalValues();
	}

	private TicketCall _ticketCall;
}