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

import com.liferay.osb.model.OrderEntry;

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
 * The cache model class for representing OrderEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntry
 * @generated
 */
@ProviderType
public class OrderEntryCacheModel implements CacheModel<OrderEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OrderEntryCacheModel)) {
			return false;
		}

		OrderEntryCacheModel orderEntryCacheModel = (OrderEntryCacheModel)obj;

		if (orderEntryId == orderEntryCacheModel.orderEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, orderEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", orderEntryId=");
		sb.append(orderEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", purchaseOrderKey=");
		sb.append(purchaseOrderKey);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", prorated=");
		sb.append(prorated);
		sb.append(", actualStartDate=");
		sb.append(actualStartDate);
		sb.append(", renewCount=");
		sb.append(renewCount);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", statusMessage=");
		sb.append(statusMessage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OrderEntry toEntityModel() {
		OrderEntryImpl orderEntryImpl = new OrderEntryImpl();

		if (uuid == null) {
			orderEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			orderEntryImpl.setUuid(uuid);
		}

		orderEntryImpl.setOrderEntryId(orderEntryId);
		orderEntryImpl.setUserId(userId);

		if (userName == null) {
			orderEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			orderEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			orderEntryImpl.setCreateDate(null);
		}
		else {
			orderEntryImpl.setCreateDate(new Date(createDate));
		}

		orderEntryImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			orderEntryImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			orderEntryImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			orderEntryImpl.setModifiedDate(null);
		}
		else {
			orderEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		orderEntryImpl.setAccountEntryId(accountEntryId);

		if (purchaseOrderKey == null) {
			orderEntryImpl.setPurchaseOrderKey(StringPool.BLANK);
		}
		else {
			orderEntryImpl.setPurchaseOrderKey(purchaseOrderKey);
		}

		if (startDate == Long.MIN_VALUE) {
			orderEntryImpl.setStartDate(null);
		}
		else {
			orderEntryImpl.setStartDate(new Date(startDate));
		}

		orderEntryImpl.setProrated(prorated);

		if (actualStartDate == Long.MIN_VALUE) {
			orderEntryImpl.setActualStartDate(null);
		}
		else {
			orderEntryImpl.setActualStartDate(new Date(actualStartDate));
		}

		orderEntryImpl.setRenewCount(renewCount);
		orderEntryImpl.setStatus(status);
		orderEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			orderEntryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			orderEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			orderEntryImpl.setStatusDate(null);
		}
		else {
			orderEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			orderEntryImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			orderEntryImpl.setStatusMessage(statusMessage);
		}

		orderEntryImpl.resetOriginalValues();

		return orderEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		orderEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		purchaseOrderKey = objectInput.readUTF();
		startDate = objectInput.readLong();

		prorated = objectInput.readBoolean();
		actualStartDate = objectInput.readLong();

		renewCount = objectInput.readInt();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		statusMessage = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(orderEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(modifiedUserId);

		if (modifiedUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		if (purchaseOrderKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(purchaseOrderKey);
		}

		objectOutput.writeLong(startDate);

		objectOutput.writeBoolean(prorated);
		objectOutput.writeLong(actualStartDate);

		objectOutput.writeInt(renewCount);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (statusMessage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusMessage);
		}
	}

	public String uuid;
	public long orderEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public String purchaseOrderKey;
	public long startDate;
	public boolean prorated;
	public long actualStartDate;
	public int renewCount;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
}