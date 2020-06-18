/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccountEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEntryCacheModel
	implements CacheModel<AccountEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountEntryCacheModel)) {
			return false;
		}

		AccountEntryCacheModel accountEntryCacheModel =
			(AccountEntryCacheModel)object;

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
			accountEntryImpl.setUserName("");
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
			accountEntryImpl.setModifiedUserName("");
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
			accountEntryImpl.setKoroneikiAccountKey("");
		}
		else {
			accountEntryImpl.setKoroneikiAccountKey(koroneikiAccountKey);
		}

		if (dossieraAccountKey == null) {
			accountEntryImpl.setDossieraAccountKey("");
		}
		else {
			accountEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		if (name == null) {
			accountEntryImpl.setName("");
		}
		else {
			accountEntryImpl.setName(name);
		}

		if (code == null) {
			accountEntryImpl.setCode("");
		}
		else {
			accountEntryImpl.setCode(code);
		}

		if (instructions == null) {
			accountEntryImpl.setInstructions("");
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
			accountEntryImpl.setLastZendeskAuditDate(
				new Date(lastZendeskAuditDate));
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(modifiedUserId);

		if (modifiedUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		if (koroneikiAccountKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(koroneikiAccountKey);
		}

		if (dossieraAccountKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossieraAccountKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (code == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(code);
		}

		if (instructions == null) {
			objectOutput.writeUTF("");
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