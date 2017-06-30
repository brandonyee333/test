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

import com.liferay.osb.model.TicketWorker;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing TicketWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorker
 * @generated
 */
public class TicketWorkerCacheModel implements CacheModel<TicketWorker>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{ticketWorkerId=");
		sb.append(ticketWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", sourceClassNameId=");
		sb.append(sourceClassNameId);
		sb.append(", sourceClassPK=");
		sb.append(sourceClassPK);
		sb.append(", role=");
		sb.append(role);
		sb.append(", primary=");
		sb.append(primary);
		sb.append("}");

		return sb.toString();
	}

	public TicketWorker toEntityModel() {
		TicketWorkerImpl ticketWorkerImpl = new TicketWorkerImpl();

		ticketWorkerImpl.setTicketWorkerId(ticketWorkerId);
		ticketWorkerImpl.setUserId(userId);
		ticketWorkerImpl.setTicketEntryId(ticketEntryId);
		ticketWorkerImpl.setSourceClassNameId(sourceClassNameId);
		ticketWorkerImpl.setSourceClassPK(sourceClassPK);
		ticketWorkerImpl.setRole(role);
		ticketWorkerImpl.setPrimary(primary);

		ticketWorkerImpl.resetOriginalValues();

		return ticketWorkerImpl;
	}

	public long ticketWorkerId;
	public long userId;
	public long ticketEntryId;
	public long sourceClassNameId;
	public long sourceClassPK;
	public int role;
	public boolean primary;
}