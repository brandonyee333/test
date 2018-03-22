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

import com.liferay.osb.model.TicketInformation;

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
 * The cache model class for representing TicketInformation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformation
 * @generated
 */
@ProviderType
public class TicketInformationCacheModel implements CacheModel<TicketInformation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketInformationCacheModel)) {
			return false;
		}

		TicketInformationCacheModel ticketInformationCacheModel = (TicketInformationCacheModel)obj;

		if (ticketInformationId == ticketInformationCacheModel.ticketInformationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketInformationId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketInformationId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		ticketEntryId = objectInput.readLong();

		fieldId = objectInput.readLong();
		data = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketInformationId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(ticketEntryId);

		objectOutput.writeLong(fieldId);

		if (data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(data);
		}
	}

	public long ticketInformationId;
	public long createDate;
	public long modifiedDate;
	public long ticketEntryId;
	public long fieldId;
	public String data;
}