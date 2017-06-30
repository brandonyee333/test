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

import com.liferay.osb.model.TicketInformation;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketInformation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformation
 * @generated
 */
public class TicketInformationCacheModel implements CacheModel<TicketInformation>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{ticketInformationId=");
		sb.append(ticketInformationId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", fieldId=");
		sb.append(fieldId);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	public TicketInformation toEntityModel() {
		TicketInformationImpl ticketInformationImpl = new TicketInformationImpl();

		ticketInformationImpl.setTicketInformationId(ticketInformationId);

		if (createDate == Long.MIN_VALUE) {
			ticketInformationImpl.setCreateDate(null);
		}
		else {
			ticketInformationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ticketInformationImpl.setModifiedDate(null);
		}
		else {
			ticketInformationImpl.setModifiedDate(new Date(modifiedDate));
		}

		ticketInformationImpl.setTicketEntryId(ticketEntryId);
		ticketInformationImpl.setFieldId(fieldId);

		if (data == null) {
			ticketInformationImpl.setData(StringPool.BLANK);
		}
		else {
			ticketInformationImpl.setData(data);
		}

		ticketInformationImpl.resetOriginalValues();

		return ticketInformationImpl;
	}

	public long ticketInformationId;
	public long createDate;
	public long modifiedDate;
	public long ticketEntryId;
	public long fieldId;
	public String data;
}