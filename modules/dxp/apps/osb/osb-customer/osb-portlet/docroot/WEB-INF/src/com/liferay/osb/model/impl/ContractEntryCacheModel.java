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

import com.liferay.osb.model.ContractEntry;

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
 * The cache model class for representing ContractEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntry
 * @generated
 */
@ProviderType
public class ContractEntryCacheModel implements CacheModel<ContractEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractEntryCacheModel)) {
			return false;
		}

		ContractEntryCacheModel contractEntryCacheModel = (ContractEntryCacheModel)obj;

		if (contractEntryId == contractEntryCacheModel.contractEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contractEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contractEntryId=");
		sb.append(contractEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", version=");
		sb.append(version);
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContractEntry toEntityModel() {
		ContractEntryImpl contractEntryImpl = new ContractEntryImpl();

		contractEntryImpl.setContractEntryId(contractEntryId);
		contractEntryImpl.setUserId(userId);

		if (userName == null) {
			contractEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			contractEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			contractEntryImpl.setCreateDate(null);
		}
		else {
			contractEntryImpl.setCreateDate(new Date(createDate));
		}

		contractEntryImpl.setClassNameId(classNameId);
		contractEntryImpl.setClassPK(classPK);
		contractEntryImpl.setType(type);
		contractEntryImpl.setVersion(version);

		if (content == null) {
			contractEntryImpl.setContent(StringPool.BLANK);
		}
		else {
			contractEntryImpl.setContent(content);
		}

		contractEntryImpl.resetOriginalValues();

		return contractEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contractEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		type = objectInput.readInt();

		version = objectInput.readInt();
		content = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(contractEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(type);

		objectOutput.writeInt(version);

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}
	}

	public long contractEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public int type;
	public int version;
	public String content;
}