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

import com.liferay.osb.model.TicketLink;

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
 * The cache model class for representing TicketLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketLink
 * @generated
 */
@ProviderType
public class TicketLinkCacheModel implements CacheModel<TicketLink>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketLinkCacheModel)) {
			return false;
		}

		TicketLinkCacheModel ticketLinkCacheModel = (TicketLinkCacheModel)obj;

		if (ticketLinkId == ticketLinkCacheModel.ticketLinkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketLinkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{ticketLinkId=");
		sb.append(ticketLinkId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", ticketSolutionId=");
		sb.append(ticketSolutionId);
		sb.append(", url=");
		sb.append(url);
		sb.append(", type=");
		sb.append(type);
		sb.append(", visibility=");
		sb.append(visibility);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TicketLink toEntityModel() {
		TicketLinkImpl ticketLinkImpl = new TicketLinkImpl();

		ticketLinkImpl.setTicketLinkId(ticketLinkId);
		ticketLinkImpl.setUserId(userId);

		if (userName == null) {
			ticketLinkImpl.setUserName(StringPool.BLANK);
		}
		else {
			ticketLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketLinkImpl.setCreateDate(null);
		}
		else {
			ticketLinkImpl.setCreateDate(new Date(createDate));
		}

		ticketLinkImpl.setTicketEntryId(ticketEntryId);
		ticketLinkImpl.setTicketSolutionId(ticketSolutionId);

		if (url == null) {
			ticketLinkImpl.setUrl(StringPool.BLANK);
		}
		else {
			ticketLinkImpl.setUrl(url);
		}

		ticketLinkImpl.setType(type);
		ticketLinkImpl.setVisibility(visibility);

		ticketLinkImpl.resetOriginalValues();

		return ticketLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketLinkId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		ticketEntryId = objectInput.readLong();

		ticketSolutionId = objectInput.readLong();
		url = objectInput.readUTF();

		type = objectInput.readInt();

		visibility = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketLinkId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(ticketEntryId);

		objectOutput.writeLong(ticketSolutionId);

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeInt(type);

		objectOutput.writeInt(visibility);
	}

	public long ticketLinkId;
	public long userId;
	public String userName;
	public long createDate;
	public long ticketEntryId;
	public long ticketSolutionId;
	public String url;
	public int type;
	public int visibility;
}