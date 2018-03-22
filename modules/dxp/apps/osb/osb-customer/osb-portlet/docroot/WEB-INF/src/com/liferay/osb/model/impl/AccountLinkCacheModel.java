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

import com.liferay.osb.model.AccountLink;

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
 * The cache model class for representing AccountLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountLink
 * @generated
 */
@ProviderType
public class AccountLinkCacheModel implements CacheModel<AccountLink>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountLinkCacheModel)) {
			return false;
		}

		AccountLinkCacheModel accountLinkCacheModel = (AccountLinkCacheModel)obj;

		if (accountLinkId == accountLinkCacheModel.accountLinkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountLinkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{accountLinkId=");
		sb.append(accountLinkId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", url=");
		sb.append(url);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountLink toEntityModel() {
		AccountLinkImpl accountLinkImpl = new AccountLinkImpl();

		accountLinkImpl.setAccountLinkId(accountLinkId);
		accountLinkImpl.setUserId(userId);

		if (userName == null) {
			accountLinkImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountLinkImpl.setCreateDate(null);
		}
		else {
			accountLinkImpl.setCreateDate(new Date(createDate));
		}

		accountLinkImpl.setAccountEntryId(accountEntryId);

		if (url == null) {
			accountLinkImpl.setUrl(StringPool.BLANK);
		}
		else {
			accountLinkImpl.setUrl(url);
		}

		accountLinkImpl.resetOriginalValues();

		return accountLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountLinkId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		url = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountLinkId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(accountEntryId);

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}
	}

	public long accountLinkId;
	public long userId;
	public String userName;
	public long createDate;
	public long accountEntryId;
	public String url;
}