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

import com.liferay.osb.model.AccountCustomer;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccountCustomer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomer
 * @generated
 */
@ProviderType
public class AccountCustomerCacheModel implements CacheModel<AccountCustomer>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountCustomerCacheModel)) {
			return false;
		}

		AccountCustomerCacheModel accountCustomerCacheModel = (AccountCustomerCacheModel)obj;

		if (accountCustomerId == accountCustomerCacheModel.accountCustomerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountCustomerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{accountCustomerId=");
		sb.append(accountCustomerId);
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
	public AccountCustomer toEntityModel() {
		AccountCustomerImpl accountCustomerImpl = new AccountCustomerImpl();

		accountCustomerImpl.setAccountCustomerId(accountCustomerId);
		accountCustomerImpl.setUserId(userId);
		accountCustomerImpl.setAccountEntryId(accountEntryId);
		accountCustomerImpl.setRole(role);

		accountCustomerImpl.resetOriginalValues();

		return accountCustomerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountCustomerId = objectInput.readLong();

		userId = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		role = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountCustomerId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeInt(role);
	}

	public long accountCustomerId;
	public long userId;
	public long accountEntryId;
	public int role;
}