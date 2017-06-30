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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.TicketCall;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketCall in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCall
 * @generated
 */
public class TicketCallCacheModel implements CacheModel<TicketCall>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{ticketCallId=");
		sb.append(ticketCallId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", callDate=");
		sb.append(callDate);
		sb.append(", callLength=");
		sb.append(callLength);
		sb.append(", customerName=");
		sb.append(customerName);
		sb.append(", customerContact=");
		sb.append(customerContact);
		sb.append(", confirmation=");
		sb.append(confirmation);
		sb.append(", instructions=");
		sb.append(instructions);
		sb.append("}");

		return sb.toString();
	}

	public TicketCall toEntityModel() {
		TicketCallImpl ticketCallImpl = new TicketCallImpl();

		ticketCallImpl.setTicketCallId(ticketCallId);
		ticketCallImpl.setUserId(userId);

		if (userName == null) {
			ticketCallImpl.setUserName(StringPool.BLANK);
		}
		else {
			ticketCallImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketCallImpl.setCreateDate(null);
		}
		else {
			ticketCallImpl.setCreateDate(new Date(createDate));
		}

		ticketCallImpl.setTicketEntryId(ticketEntryId);
		ticketCallImpl.setType(type);

		if (callDate == Long.MIN_VALUE) {
			ticketCallImpl.setCallDate(null);
		}
		else {
			ticketCallImpl.setCallDate(new Date(callDate));
		}

		ticketCallImpl.setCallLength(callLength);

		if (customerName == null) {
			ticketCallImpl.setCustomerName(StringPool.BLANK);
		}
		else {
			ticketCallImpl.setCustomerName(customerName);
		}

		if (customerContact == null) {
			ticketCallImpl.setCustomerContact(StringPool.BLANK);
		}
		else {
			ticketCallImpl.setCustomerContact(customerContact);
		}

		if (confirmation == null) {
			ticketCallImpl.setConfirmation(StringPool.BLANK);
		}
		else {
			ticketCallImpl.setConfirmation(confirmation);
		}

		if (instructions == null) {
			ticketCallImpl.setInstructions(StringPool.BLANK);
		}
		else {
			ticketCallImpl.setInstructions(instructions);
		}

		ticketCallImpl.resetOriginalValues();

		return ticketCallImpl;
	}

	public long ticketCallId;
	public long userId;
	public String userName;
	public long createDate;
	public long ticketEntryId;
	public int type;
	public long callDate;
	public long callLength;
	public String customerName;
	public String customerContact;
	public String confirmation;
	public String instructions;
}