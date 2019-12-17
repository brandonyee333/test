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

import com.liferay.osb.model.AccountEntry;

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
 * The cache model class for representing AccountEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntry
 * @generated
 */
@ProviderType
public class AccountEntryCacheModel implements CacheModel<AccountEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEntryCacheModel)) {
			return false;
		}

		AccountEntryCacheModel accountEntryCacheModel = (AccountEntryCacheModel)obj;

		if (accountEntryId == accountEntryCacheModel.accountEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
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
		sb.append(", koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);
		sb.append(", dossieraAccountKey=");
		sb.append(dossieraAccountKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", code=");
		sb.append(code);
		sb.append(", instructions=");
		sb.append(instructions);
		sb.append(", activeSupport=");
		sb.append(activeSupport);
		sb.append(", activeTicketSupport=");
		sb.append(activeTicketSupport);
		sb.append(", lastZendeskAuditDate=");
		sb.append(lastZendeskAuditDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountEntry toEntityModel() {
		AccountEntryImpl accountEntryImpl = new AccountEntryImpl();

		accountEntryImpl.setAccountEntryId(accountEntryId);
		accountEntryImpl.setCompanyId(companyId);
		accountEntryImpl.setUserId(userId);

		if (userName == null) {
			accountEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountEntryImpl.setCreateDate(null);
		}
		else {
			accountEntryImpl.setCreateDate(new Date(createDate));
		}

		accountEntryImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountEntryImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountEntryImpl.setModifiedDate(null);
		}
		else {
			accountEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (koroneikiAccountKey == null) {
			accountEntryImpl.setKoroneikiAccountKey(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setKoroneikiAccountKey(koroneikiAccountKey);
		}

		if (dossieraAccountKey == null) {
			accountEntryImpl.setDossieraAccountKey(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		if (name == null) {
			accountEntryImpl.setName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setName(name);
		}

		if (code == null) {
			accountEntryImpl.setCode(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setCode(code);
		}

		if (instructions == null) {
			accountEntryImpl.setInstructions(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setInstructions(instructions);
		}

		accountEntryImpl.setActiveSupport(activeSupport);
		accountEntryImpl.setActiveTicketSupport(activeTicketSupport);

		if (lastZendeskAuditDate == Long.MIN_VALUE) {
			accountEntryImpl.setLastZendeskAuditDate(null);
		}
		else {
			accountEntryImpl.setLastZendeskAuditDate(new Date(
					lastZendeskAuditDate));
		}

		accountEntryImpl.setStatus(status);

		accountEntryImpl.resetOriginalValues();

		return accountEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		koroneikiAccountKey = objectInput.readUTF();
		dossieraAccountKey = objectInput.readUTF();
		name = objectInput.readUTF();
		code = objectInput.readUTF();
		instructions = objectInput.readUTF();

		activeSupport = objectInput.readBoolean();

		activeTicketSupport = objectInput.readBoolean();
		lastZendeskAuditDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(companyId);

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

		if (koroneikiAccountKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(koroneikiAccountKey);
		}

		if (dossieraAccountKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossieraAccountKey);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (code == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(code);
		}

		if (instructions == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(instructions);
		}

		objectOutput.writeBoolean(activeSupport);

		objectOutput.writeBoolean(activeTicketSupport);
		objectOutput.writeLong(lastZendeskAuditDate);

		objectOutput.writeInt(status);
	}

	public long accountEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public String koroneikiAccountKey;
	public String dossieraAccountKey;
	public String name;
	public String code;
	public String instructions;
	public boolean activeSupport;
	public boolean activeTicketSupport;
	public long lastZendeskAuditDate;
	public int status;
}