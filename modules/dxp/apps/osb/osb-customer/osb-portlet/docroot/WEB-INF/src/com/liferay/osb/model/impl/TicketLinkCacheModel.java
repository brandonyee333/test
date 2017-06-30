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

import com.liferay.osb.model.TicketLink;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketLink
 * @generated
 */
public class TicketLinkCacheModel implements CacheModel<TicketLink>,
	Serializable {
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