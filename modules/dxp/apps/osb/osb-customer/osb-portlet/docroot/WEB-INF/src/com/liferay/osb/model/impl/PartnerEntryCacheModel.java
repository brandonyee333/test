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

import com.liferay.osb.model.PartnerEntry;

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
 * The cache model class for representing PartnerEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntry
 * @generated
 */
@ProviderType
public class PartnerEntryCacheModel implements CacheModel<PartnerEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerEntryCacheModel)) {
			return false;
		}

		PartnerEntryCacheModel partnerEntryCacheModel = (PartnerEntryCacheModel)obj;

		if (partnerEntryId == partnerEntryCacheModel.partnerEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, partnerEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{partnerEntryId=");
		sb.append(partnerEntryId);
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
		sb.append(", parentPartnerEntryId=");
		sb.append(parentPartnerEntryId);
		sb.append(", dossieraAccountKey=");
		sb.append(dossieraAccountKey);
		sb.append(", jiraProjectKey=");
		sb.append(jiraProjectKey);
		sb.append(", code=");
		sb.append(code);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PartnerEntry toEntityModel() {
		PartnerEntryImpl partnerEntryImpl = new PartnerEntryImpl();

		partnerEntryImpl.setPartnerEntryId(partnerEntryId);
		partnerEntryImpl.setCompanyId(companyId);
		partnerEntryImpl.setUserId(userId);

		if (userName == null) {
			partnerEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			partnerEntryImpl.setCreateDate(null);
		}
		else {
			partnerEntryImpl.setCreateDate(new Date(createDate));
		}

		partnerEntryImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			partnerEntryImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			partnerEntryImpl.setModifiedDate(null);
		}
		else {
			partnerEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		partnerEntryImpl.setParentPartnerEntryId(parentPartnerEntryId);

		if (dossieraAccountKey == null) {
			partnerEntryImpl.setDossieraAccountKey(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		if (jiraProjectKey == null) {
			partnerEntryImpl.setJiraProjectKey(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setJiraProjectKey(jiraProjectKey);
		}

		if (code == null) {
			partnerEntryImpl.setCode(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setCode(code);
		}

		if (notes == null) {
			partnerEntryImpl.setNotes(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setNotes(notes);
		}

		partnerEntryImpl.setStatus(status);

		partnerEntryImpl.resetOriginalValues();

		return partnerEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		partnerEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		parentPartnerEntryId = objectInput.readLong();
		dossieraAccountKey = objectInput.readUTF();
		jiraProjectKey = objectInput.readUTF();
		code = objectInput.readUTF();
		notes = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(partnerEntryId);

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

		objectOutput.writeLong(parentPartnerEntryId);

		if (dossieraAccountKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossieraAccountKey);
		}

		if (jiraProjectKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jiraProjectKey);
		}

		if (code == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(code);
		}

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}

		objectOutput.writeInt(status);
	}

	public long partnerEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long parentPartnerEntryId;
	public String dossieraAccountKey;
	public String jiraProjectKey;
	public String code;
	public String notes;
	public int status;
}