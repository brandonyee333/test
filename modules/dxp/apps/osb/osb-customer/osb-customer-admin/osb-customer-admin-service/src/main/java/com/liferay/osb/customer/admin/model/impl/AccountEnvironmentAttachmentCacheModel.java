/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccountEnvironmentAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEnvironmentAttachmentCacheModel
	implements CacheModel<AccountEnvironmentAttachment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountEnvironmentAttachmentCacheModel)) {
			return false;
		}

		AccountEnvironmentAttachmentCacheModel
			accountEnvironmentAttachmentCacheModel =
				(AccountEnvironmentAttachmentCacheModel)object;

		if (accountEnvironmentAttachmentId ==
				accountEnvironmentAttachmentCacheModel.
					accountEnvironmentAttachmentId) {

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
		AccountEnvironmentAttachmentImpl accountEnvironmentAttachmentImpl =
			new AccountEnvironmentAttachmentImpl();

		accountEnvironmentAttachmentImpl.setAccountEnvironmentAttachmentId(
			accountEnvironmentAttachmentId);
		accountEnvironmentAttachmentImpl.setUserId(userId);

		if (userName == null) {
			accountEnvironmentAttachmentImpl.setUserName("");
		}
		else {
			accountEnvironmentAttachmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountEnvironmentAttachmentImpl.setCreateDate(null);
		}
		else {
			accountEnvironmentAttachmentImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountEnvironmentAttachmentImpl.setModifiedDate(null);
		}
		else {
			accountEnvironmentAttachmentImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		accountEnvironmentAttachmentImpl.setAccountEnvironmentId(
			accountEnvironmentId);

		if (fileName == null) {
			accountEnvironmentAttachmentImpl.setFileName("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(accountEnvironmentAttachmentId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEnvironmentId);

		if (fileName == null) {
			objectOutput.writeUTF("");
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