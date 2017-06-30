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

import com.liferay.osb.model.TicketComment;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketComment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketComment
 * @generated
 */
public class TicketCommentCacheModel implements CacheModel<TicketComment>,
	Serializable {
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