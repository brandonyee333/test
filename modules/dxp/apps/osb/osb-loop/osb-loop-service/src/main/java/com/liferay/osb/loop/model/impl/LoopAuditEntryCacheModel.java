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

package com.liferay.osb.loop.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.model.LoopAuditEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoopAuditEntry in entity cache.
 *
 * @author Ethan Bustad
 * @see LoopAuditEntry
 * @generated
 */
@ProviderType
public class LoopAuditEntryCacheModel implements CacheModel<LoopAuditEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopAuditEntryCacheModel)) {
			return false;
		}

		LoopAuditEntryCacheModel loopAuditEntryCacheModel = (LoopAuditEntryCacheModel)obj;

		if (loopAuditEntryId == loopAuditEntryCacheModel.loopAuditEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopAuditEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{loopAuditEntryId=");
		sb.append(loopAuditEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopAuditEntry toEntityModel() {
		LoopAuditEntryImpl loopAuditEntryImpl = new LoopAuditEntryImpl();

		loopAuditEntryImpl.setLoopAuditEntryId(loopAuditEntryId);
		loopAuditEntryImpl.setCompanyId(companyId);
		loopAuditEntryImpl.setUserId(userId);

		if (userName == null) {
			loopAuditEntryImpl.setUserName("");
		}
		else {
			loopAuditEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			loopAuditEntryImpl.setCreateDate(null);
		}
		else {
			loopAuditEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			loopAuditEntryImpl.setModifiedDate(null);
		}
		else {
			loopAuditEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		loopAuditEntryImpl.setClassNameId(classNameId);
		loopAuditEntryImpl.setClassPK(classPK);

		if (name == null) {
			loopAuditEntryImpl.setName("");
		}
		else {
			loopAuditEntryImpl.setName(name);
		}

		loopAuditEntryImpl.resetOriginalValues();

		return loopAuditEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopAuditEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(loopAuditEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long loopAuditEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String name;
}