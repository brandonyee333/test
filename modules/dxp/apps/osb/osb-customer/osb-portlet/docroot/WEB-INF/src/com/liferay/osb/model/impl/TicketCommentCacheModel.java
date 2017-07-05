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

import com.liferay.osb.model.TicketComment;

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
 * The cache model class for representing TicketComment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketComment
 * @generated
 */
@ProviderType
public class TicketCommentCacheModel implements CacheModel<TicketComment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketCommentCacheModel)) {
			return false;
		}

		TicketCommentCacheModel ticketCommentCacheModel = (TicketCommentCacheModel)obj;

		if (ticketCommentId == ticketCommentCacheModel.ticketCommentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketCommentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{ticketCommentId=");
		sb.append(ticketCommentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", body=");
		sb.append(body);
		sb.append(", type=");
		sb.append(type);
		sb.append(", format=");
		sb.append(format);
		sb.append(", visibility=");
		sb.append(visibility);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TicketComment toEntityModel() {
		TicketCommentImpl ticketCommentImpl = new TicketCommentImpl();

		ticketCommentImpl.setTicketCommentId(ticketCommentId);
		ticketCommentImpl.setUserId(userId);

		if (userName == null) {
			ticketCommentImpl.setUserName(StringPool.BLANK);
		}
		else {
			ticketCommentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketCommentImpl.setCreateDate(null);
		}
		else {
			ticketCommentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ticketCommentImpl.setModifiedDate(null);
		}
		else {
			ticketCommentImpl.setModifiedDate(new Date(modifiedDate));
		}

		ticketCommentImpl.setTicketEntryId(ticketEntryId);

		if (body == null) {
			ticketCommentImpl.setBody(StringPool.BLANK);
		}
		else {
			ticketCommentImpl.setBody(body);
		}

		ticketCommentImpl.setType(type);

		if (format == null) {
			ticketCommentImpl.setFormat(StringPool.BLANK);
		}
		else {
			ticketCommentImpl.setFormat(format);
		}

		ticketCommentImpl.setVisibility(visibility);

		if (settings == null) {
			ticketCommentImpl.setSettings(StringPool.BLANK);
		}
		else {
			ticketCommentImpl.setSettings(settings);
		}

		ticketCommentImpl.setStatus(status);

		ticketCommentImpl.resetOriginalValues();

		return ticketCommentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketCommentId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		ticketEntryId = objectInput.readLong();
		body = objectInput.readUTF();

		type = objectInput.readInt();
		format = objectInput.readUTF();

		visibility = objectInput.readInt();
		settings = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketCommentId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(ticketEntryId);

		if (body == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(body);
		}

		objectOutput.writeInt(type);

		if (format == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(format);
		}

		objectOutput.writeInt(visibility);

		if (settings == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(settings);
		}

		objectOutput.writeInt(status);
	}

	public long ticketCommentId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long ticketEntryId;
	public String body;
	public int type;
	public String format;
	public int visibility;
	public String settings;
	public int status;
}