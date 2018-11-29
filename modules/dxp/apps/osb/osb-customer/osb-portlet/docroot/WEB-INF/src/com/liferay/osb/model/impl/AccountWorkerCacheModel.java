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

import com.liferay.osb.model.AccountWorker;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccountWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorker
 * @generated
 */
@ProviderType
public class AccountWorkerCacheModel implements CacheModel<AccountWorker>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountWorkerCacheModel)) {
			return false;
		}

		AccountWorkerCacheModel accountWorkerCacheModel = (AccountWorkerCacheModel)obj;

		if (accountWorkerId == accountWorkerCacheModel.accountWorkerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountWorkerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{accountWorkerId=");
		sb.append(accountWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", role=");
		sb.append(role);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountWorker toEntityModel() {
		AccountWorkerImpl accountWorkerImpl = new AccountWorkerImpl();

		accountWorkerImpl.setAccountWorkerId(accountWorkerId);
		accountWorkerImpl.setUserId(userId);
		accountWorkerImpl.setAccountEntryId(accountEntryId);
		accountWorkerImpl.setRole(role);

		accountWorkerImpl.resetOriginalValues();

		return accountWorkerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountWorkerId = objectInput.readLong();

		userId = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		role = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountWorkerId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeInt(role);
	}

	public long accountWorkerId;
	public long userId;
	public long accountEntryId;
	public int role;
}