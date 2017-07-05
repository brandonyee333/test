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

import com.liferay.osb.model.AccountInformation;

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
 * The cache model class for representing AccountInformation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformation
 * @generated
 */
@ProviderType
public class AccountInformationCacheModel implements CacheModel<AccountInformation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountInformationCacheModel)) {
			return false;
		}

		AccountInformationCacheModel accountInformationCacheModel = (AccountInformationCacheModel)obj;

		if (accountInformationId == accountInformationCacheModel.accountInformationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountInformationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{accountInformationId=");
		sb.append(accountInformationId);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", accountProjectId=");
		sb.append(accountProjectId);
		sb.append(", fieldId=");
		sb.append(fieldId);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountInformation toEntityModel() {
		AccountInformationImpl accountInformationImpl = new AccountInformationImpl();

		accountInformationImpl.setAccountInformationId(accountInformationId);
		accountInformationImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountInformationImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountInformationImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountInformationImpl.setModifiedDate(null);
		}
		else {
			accountInformationImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountInformationImpl.setAccountEntryId(accountEntryId);
		accountInformationImpl.setAccountProjectId(accountProjectId);
		accountInformationImpl.setFieldId(fieldId);

		if (data == null) {
			accountInformationImpl.setData(StringPool.BLANK);
		}
		else {
			accountInformationImpl.setData(data);
		}

		accountInformationImpl.resetOriginalValues();

		return accountInformationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountInformationId = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		accountProjectId = objectInput.readLong();

		fieldId = objectInput.readInt();
		data = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountInformationId);

		objectOutput.writeLong(modifiedUserId);

		if (modifiedUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(accountProjectId);

		objectOutput.writeInt(fieldId);

		if (data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(data);
		}
	}

	public long accountInformationId;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public long accountProjectId;
	public int fieldId;
	public String data;
}