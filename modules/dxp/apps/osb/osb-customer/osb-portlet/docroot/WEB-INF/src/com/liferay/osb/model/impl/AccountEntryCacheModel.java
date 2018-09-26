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
		StringBundler sb = new StringBundler(63);

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
		sb.append(", corpProjectUuid=");
		sb.append(corpProjectUuid);
		sb.append(", corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", dossieraAccountKey=");
		sb.append(dossieraAccountKey);
		sb.append(", corpEntryName=");
		sb.append(corpEntryName);
		sb.append(", name=");
		sb.append(name);
		sb.append(", code=");
		sb.append(code);
		sb.append(", redirectAccountEntryId=");
		sb.append(redirectAccountEntryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", industry=");
		sb.append(industry);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", partnerEntryId=");
		sb.append(partnerEntryId);
		sb.append(", partnerManagedSupport=");
		sb.append(partnerManagedSupport);
		sb.append(", tier=");
		sb.append(tier);
		sb.append(", maxCustomers=");
		sb.append(maxCustomers);
		sb.append(", instructions=");
		sb.append(instructions);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", highestSupportResponseId=");
		sb.append(highestSupportResponseId);
		sb.append(", lastAuditDate=");
		sb.append(lastAuditDate);
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

		if (corpProjectUuid == null) {
			accountEntryImpl.setCorpProjectUuid(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setCorpProjectUuid(corpProjectUuid);
		}

		accountEntryImpl.setCorpProjectId(corpProjectId);

		if (dossieraAccountKey == null) {
			accountEntryImpl.setDossieraAccountKey(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		if (corpEntryName == null) {
			accountEntryImpl.setCorpEntryName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setCorpEntryName(corpEntryName);
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

		accountEntryImpl.setRedirectAccountEntryId(redirectAccountEntryId);
		accountEntryImpl.setType(type);
		accountEntryImpl.setIndustry(industry);
		accountEntryImpl.setCountryId(countryId);
		accountEntryImpl.setPartnerEntryId(partnerEntryId);
		accountEntryImpl.setPartnerManagedSupport(partnerManagedSupport);
		accountEntryImpl.setTier(tier);
		accountEntryImpl.setMaxCustomers(maxCustomers);

		if (instructions == null) {
			accountEntryImpl.setInstructions(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setInstructions(instructions);
		}

		if (notes == null) {
			accountEntryImpl.setNotes(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setNotes(notes);
		}

		accountEntryImpl.setHighestSupportResponseId(highestSupportResponseId);

		if (lastAuditDate == Long.MIN_VALUE) {
			accountEntryImpl.setLastAuditDate(null);
		}
		else {
			accountEntryImpl.setLastAuditDate(new Date(lastAuditDate));
		}

		accountEntryImpl.setStatus(status);
		accountEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			accountEntryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			accountEntryImpl.setStatusDate(null);
		}
		else {
			accountEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			accountEntryImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setStatusMessage(statusMessage);
		}

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
		corpProjectUuid = objectInput.readUTF();

		corpProjectId = objectInput.readLong();
		dossieraAccountKey = objectInput.readUTF();
		corpEntryName = objectInput.readUTF();
		name = objectInput.readUTF();
		code = objectInput.readUTF();

		redirectAccountEntryId = objectInput.readLong();

		type = objectInput.readInt();

		industry = objectInput.readInt();

		countryId = objectInput.readLong();

		partnerEntryId = objectInput.readLong();

		partnerManagedSupport = objectInput.readBoolean();

		tier = objectInput.readInt();

		maxCustomers = objectInput.readInt();
		instructions = objectInput.readUTF();
		notes = objectInput.readUTF();

		highestSupportResponseId = objectInput.readLong();
		lastAuditDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		statusMessage = objectInput.readUTF();
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

		if (corpProjectUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(corpProjectUuid);
		}

		objectOutput.writeLong(corpProjectId);

		if (dossieraAccountKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossieraAccountKey);
		}

		if (corpEntryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(corpEntryName);
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

		objectOutput.writeLong(redirectAccountEntryId);

		objectOutput.writeInt(type);

		objectOutput.writeInt(industry);

		objectOutput.writeLong(countryId);

		objectOutput.writeLong(partnerEntryId);

		objectOutput.writeBoolean(partnerManagedSupport);

		objectOutput.writeInt(tier);

		objectOutput.writeInt(maxCustomers);

		if (instructions == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(instructions);
		}

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}

		objectOutput.writeLong(highestSupportResponseId);
		objectOutput.writeLong(lastAuditDate);

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

	public long accountEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public String corpProjectUuid;
	public long corpProjectId;
	public String dossieraAccountKey;
	public String corpEntryName;
	public String name;
	public String code;
	public long redirectAccountEntryId;
	public int type;
	public int industry;
	public long countryId;
	public long partnerEntryId;
	public boolean partnerManagedSupport;
	public int tier;
	public int maxCustomers;
	public String instructions;
	public String notes;
	public long highestSupportResponseId;
	public long lastAuditDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
}