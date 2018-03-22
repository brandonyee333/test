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

import com.liferay.osb.model.AccountProject;

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
 * The cache model class for representing AccountProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountProject
 * @generated
 */
@ProviderType
public class AccountProjectCacheModel implements CacheModel<AccountProject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountProjectCacheModel)) {
			return false;
		}

		AccountProjectCacheModel accountProjectCacheModel = (AccountProjectCacheModel)obj;

		if (accountProjectId == accountProjectCacheModel.accountProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{accountProjectId=");
		sb.append(accountProjectId);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountProject toEntityModel() {
		AccountProjectImpl accountProjectImpl = new AccountProjectImpl();

		accountProjectImpl.setAccountProjectId(accountProjectId);
		accountProjectImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountProjectImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountProjectImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountProjectImpl.setModifiedDate(null);
		}
		else {
			accountProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountProjectImpl.setAccountEntryId(accountEntryId);

		if (name == null) {
			accountProjectImpl.setName(StringPool.BLANK);
		}
		else {
			accountProjectImpl.setName(name);
		}

		accountProjectImpl.resetOriginalValues();

		return accountProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountProjectId = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountProjectId);

		objectOutput.writeLong(modifiedUserId);

		if (modifiedUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long accountProjectId;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public String name;
}