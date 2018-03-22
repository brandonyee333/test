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

import com.liferay.osb.model.AccountCall;

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
 * The cache model class for representing AccountCall in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCall
 * @generated
 */
@ProviderType
public class AccountCallCacheModel implements CacheModel<AccountCall>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountCallCacheModel)) {
			return false;
		}

		AccountCallCacheModel accountCallCacheModel = (AccountCallCacheModel)obj;

		if (accountCallId == accountCallCacheModel.accountCallId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountCallId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{accountCallId=");
		sb.append(accountCallId);
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
		sb.append(", type=");
		sb.append(type);
		sb.append(", callDate=");
		sb.append(callDate);
		sb.append(", callLength=");
		sb.append(callLength);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", clientsPresent=");
		sb.append(clientsPresent);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", actionItems=");
		sb.append(actionItems);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountCall toEntityModel() {
		AccountCallImpl accountCallImpl = new AccountCallImpl();

		accountCallImpl.setAccountCallId(accountCallId);

		if (createDate == Long.MIN_VALUE) {
			accountCallImpl.setCreateDate(null);
		}
		else {
			accountCallImpl.setCreateDate(new Date(createDate));
		}

		accountCallImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountCallImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountCallImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountCallImpl.setModifiedDate(null);
		}
		else {
			accountCallImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountCallImpl.setAccountEntryId(accountEntryId);
		accountCallImpl.setType(type);

		if (callDate == Long.MIN_VALUE) {
			accountCallImpl.setCallDate(null);
		}
		else {
			accountCallImpl.setCallDate(new Date(callDate));
		}

		accountCallImpl.setCallLength(callLength);

		if (summary == null) {
			accountCallImpl.setSummary(StringPool.BLANK);
		}
		else {
			accountCallImpl.setSummary(summary);
		}

		if (clientsPresent == null) {
			accountCallImpl.setClientsPresent(StringPool.BLANK);
		}
		else {
			accountCallImpl.setClientsPresent(clientsPresent);
		}

		if (notes == null) {
			accountCallImpl.setNotes(StringPool.BLANK);
		}
		else {
			accountCallImpl.setNotes(notes);
		}

		if (actionItems == null) {
			accountCallImpl.setActionItems(StringPool.BLANK);
		}
		else {
			accountCallImpl.setActionItems(actionItems);
		}

		accountCallImpl.resetOriginalValues();

		return accountCallImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountCallId = objectInput.readLong();
		createDate = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		type = objectInput.readInt();
		callDate = objectInput.readLong();

		callLength = objectInput.readLong();
		summary = objectInput.readUTF();
		clientsPresent = objectInput.readUTF();
		notes = objectInput.readUTF();
		actionItems = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountCallId);
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

		objectOutput.writeInt(type);
		objectOutput.writeLong(callDate);

		objectOutput.writeLong(callLength);

		if (summary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (clientsPresent == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(clientsPresent);
		}

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}

		if (actionItems == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionItems);
		}
	}

	public long accountCallId;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public int type;
	public long callDate;
	public long callLength;
	public String summary;
	public String clientsPresent;
	public String notes;
	public String actionItems;
}