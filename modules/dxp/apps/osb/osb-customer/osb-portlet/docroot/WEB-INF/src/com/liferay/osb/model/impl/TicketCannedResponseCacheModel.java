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

import com.liferay.osb.model.TicketCannedResponse;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketCannedResponse in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponse
 * @generated
 */
public class TicketCannedResponseCacheModel implements CacheModel<TicketCannedResponse>,
	Serializable {
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

	public TicketCannedResponse toEntityModel() {
		TicketCannedResponseImpl ticketCannedResponseImpl = new TicketCannedResponseImpl();

		ticketCannedResponseImpl.setTicketCannedResponseId(ticketCannedResponseId);
		ticketCannedResponseImpl.setUserId(userId);

		if (userName == null) {
			ticketCannedResponseImpl.setUserName(StringPool.BLANK);
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
			ticketCannedResponseImpl.setName(StringPool.BLANK);
		}
		else {
			ticketCannedResponseImpl.setName(name);
		}

		if (content == null) {
			ticketCannedResponseImpl.setContent(StringPool.BLANK);
		}
		else {
			ticketCannedResponseImpl.setContent(content);
		}

		ticketCannedResponseImpl.setUseCount(useCount);

		ticketCannedResponseImpl.resetOriginalValues();

		return ticketCannedResponseImpl;
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