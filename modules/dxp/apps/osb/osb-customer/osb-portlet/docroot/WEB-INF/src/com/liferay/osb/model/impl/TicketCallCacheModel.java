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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.TicketCall;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TicketCall in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCall
 * @generated
 */
@ProviderType
public class TicketCallCacheModel implements CacheModel<TicketCall>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketCallCacheModel)) {
			return false;
		}

		TicketCallCacheModel ticketCallCacheModel = (TicketCallCacheModel)obj;

		if (ticketCallId == ticketCallCacheModel.ticketCallId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketCallId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketCallId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		ticketEntryId = objectInput.readLong();

		type = objectInput.readInt();
		callDate = objectInput.readLong();

		callLength = objectInput.readLong();
		customerName = objectInput.readUTF();
		customerContact = objectInput.readUTF();
		confirmation = objectInput.readUTF();
		instructions = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketCallId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(ticketEntryId);

		objectOutput.writeInt(type);
		objectOutput.writeLong(callDate);

		objectOutput.writeLong(callLength);

		if (customerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerName);
		}

		if (customerContact == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerContact);
		}

		if (confirmation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(confirmation);
		}

		if (instructions == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(instructions);
		}
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