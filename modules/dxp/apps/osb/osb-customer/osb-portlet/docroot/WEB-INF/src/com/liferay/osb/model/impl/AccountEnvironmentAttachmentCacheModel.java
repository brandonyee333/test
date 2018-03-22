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

import com.liferay.osb.model.AccountEnvironmentAttachment;

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
 * The cache model class for representing AccountEnvironmentAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachment
 * @generated
 */
@ProviderType
public class AccountEnvironmentAttachmentCacheModel implements CacheModel<AccountEnvironmentAttachment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEnvironmentAttachmentCacheModel)) {
			return false;
		}

		AccountEnvironmentAttachmentCacheModel accountEnvironmentAttachmentCacheModel =
			(AccountEnvironmentAttachmentCacheModel)obj;

		if (accountEnvironmentAttachmentId == accountEnvironmentAttachmentCacheModel.accountEnvironmentAttachmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountEnvironmentAttachmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{accountEnvironmentAttachmentId=");
		sb.append(accountEnvironmentAttachmentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEnvironmentId=");
		sb.append(accountEnvironmentId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileSize=");
		sb.append(fileSize);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountEnvironmentAttachment toEntityModel() {
		AccountEnvironmentAttachmentImpl accountEnvironmentAttachmentImpl = new AccountEnvironmentAttachmentImpl();

		accountEnvironmentAttachmentImpl.setAccountEnvironmentAttachmentId(accountEnvironmentAttachmentId);
		accountEnvironmentAttachmentImpl.setUserId(userId);

		if (userName == null) {
			accountEnvironmentAttachmentImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountEnvironmentAttachmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountEnvironmentAttachmentImpl.setCreateDate(null);
		}
		else {
			accountEnvironmentAttachmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountEnvironmentAttachmentImpl.setModifiedDate(null);
		}
		else {
			accountEnvironmentAttachmentImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		accountEnvironmentAttachmentImpl.setAccountEnvironmentId(accountEnvironmentId);

		if (fileName == null) {
			accountEnvironmentAttachmentImpl.setFileName(StringPool.BLANK);
		}
		else {
			accountEnvironmentAttachmentImpl.setFileName(fileName);
		}

		accountEnvironmentAttachmentImpl.setFileSize(fileSize);
		accountEnvironmentAttachmentImpl.setType(type);

		accountEnvironmentAttachmentImpl.resetOriginalValues();

		return accountEnvironmentAttachmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountEnvironmentAttachmentId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountEnvironmentId = objectInput.readLong();
		fileName = objectInput.readUTF();

		fileSize = objectInput.readLong();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountEnvironmentAttachmentId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEnvironmentId);

		if (fileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(fileSize);

		objectOutput.writeInt(type);
	}

	public long accountEnvironmentAttachmentId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEnvironmentId;
	public String fileName;
	public long fileSize;
	public int type;
}