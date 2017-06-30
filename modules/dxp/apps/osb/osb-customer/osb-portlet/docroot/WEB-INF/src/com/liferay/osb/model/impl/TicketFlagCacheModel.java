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

import com.liferay.osb.model.TicketFlag;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketFlag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlag
 * @generated
 */
public class TicketFlagCacheModel implements CacheModel<TicketFlag>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{ticketFlagId=");
		sb.append(ticketFlagId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", flag=");
		sb.append(flag);
		sb.append("}");

		return sb.toString();
	}

	public TicketFlag toEntityModel() {
		TicketFlagImpl ticketFlagImpl = new TicketFlagImpl();

		ticketFlagImpl.setTicketFlagId(ticketFlagId);
		ticketFlagImpl.setUserId(userId);

		if (modifiedDate == Long.MIN_VALUE) {
			ticketFlagImpl.setModifiedDate(null);
		}
		else {
			ticketFlagImpl.setModifiedDate(new Date(modifiedDate));
		}

		ticketFlagImpl.setAccountEntryId(accountEntryId);
		ticketFlagImpl.setTicketEntryId(ticketEntryId);
		ticketFlagImpl.setType(type);
		ticketFlagImpl.setFlag(flag);

		ticketFlagImpl.resetOriginalValues();

		return ticketFlagImpl;
	}

	public long ticketFlagId;
	public long userId;
	public long modifiedDate;
	public long accountEntryId;
	public long ticketEntryId;
	public int type;
	public int flag;
}