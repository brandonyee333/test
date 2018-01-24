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

import com.liferay.osb.model.TicketCannedResponse;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TicketCannedResponse in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponse
 * @generated
 */
@ProviderType
public class TicketCannedResponseCacheModel implements CacheModel<TicketCannedResponse>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketCannedResponseCacheModel)) {
			return false;
		}

		TicketCannedResponseCacheModel ticketCannedResponseCacheModel = (TicketCannedResponseCacheModel)obj;

		if (ticketCannedResponseId == ticketCannedResponseCacheModel.ticketCannedResponseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketCannedResponseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{ticketCannedResponseId=");
		sb.append(ticketCannedResponseId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", content=");
		sb.append(content);
		sb.append(", useCount=");
		sb.append(useCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TicketCannedResponse toEntityModel() {
		TicketCannedResponseImpl ticketCannedResponseImpl = new TicketCannedResponseImpl();

		ticketCannedResponseImpl.setTicketCannedResponseId(ticketCannedResponseId);
		ticketCannedResponseImpl.setUserId(userId);

		if (userName == null) {
			ticketCannedResponseImpl.setUserName("");
		}
		else {
			ticketCannedResponseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketCannedResponseImpl.setCreateDate(null);
		}
		else {
			ticketCannedResponseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ticketCannedResponseImpl.setModifiedDate(null);
		}
		else {
			ticketCannedResponseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			ticketCannedResponseImpl.setName("");
		}
		else {
			ticketCannedResponseImpl.setName(name);
		}

		if (content == null) {
			ticketCannedResponseImpl.setContent("");
		}
		else {
			ticketCannedResponseImpl.setContent(content);
		}

		ticketCannedResponseImpl.setUseCount(useCount);

		ticketCannedResponseImpl.resetOriginalValues();

		return ticketCannedResponseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketCannedResponseId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		content = objectInput.readUTF();

		useCount = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketCannedResponseId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(useCount);
	}

	public long ticketCannedResponseId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String content;
	public int useCount;
}