/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.AccountAttachment;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccountAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountAttachmentCacheModel
	implements CacheModel<AccountAttachment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountAttachmentCacheModel)) {
			return false;
		}

		AccountAttachmentCacheModel accountAttachmentCacheModel =
			(AccountAttachmentCacheModel)object;

		if (accountAttachmentId ==
				accountAttachmentCacheModel.accountAttachmentId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountAttachmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{accountAttachmentId=");
		sb.append(accountAttachmentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", accountProjectId=");
		sb.append(accountProjectId);
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
	public AccountAttachment toEntityModel() {
		AccountAttachmentImpl accountAttachmentImpl =
			new AccountAttachmentImpl();

		accountAttachmentImpl.setAccountAttachmentId(accountAttachmentId);
		accountAttachmentImpl.setUserId(userId);

		if (userName == null) {
			accountAttachmentImpl.setUserName("");
		}
		else {
			accountAttachmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountAttachmentImpl.setCreateDate(null);
		}
		else {
			accountAttachmentImpl.setCreateDate(new Date(createDate));
		}

		accountAttachmentImpl.setAccountEntryId(accountEntryId);
		accountAttachmentImpl.setAccountProjectId(accountProjectId);

		if (fileName == null) {
			accountAttachmentImpl.setFileName("");
		}
		else {
			accountAttachmentImpl.setFileName(fileName);
		}

		accountAttachmentImpl.setFileSize(fileSize);
		accountAttachmentImpl.setType(type);

		accountAttachmentImpl.resetOriginalValues();

		return accountAttachmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountAttachmentId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		accountProjectId = objectInput.readLong();
		fileName = objectInput.readUTF();

		fileSize = objectInput.readLong();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(accountAttachmentId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(accountProjectId);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(fileSize);

		objectOutput.writeInt(type);
	}

	public long accountAttachmentId;
	public long userId;
	public String userName;
	public long createDate;
	public long accountEntryId;
	public long accountProjectId;
	public String fileName;
	public long fileSize;
	public int type;

}