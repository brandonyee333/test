/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.social.privatemessaging.model.UserThread;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserThread in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserThreadCacheModel
	implements CacheModel<UserThread>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserThreadCacheModel)) {
			return false;
		}

		UserThreadCacheModel userThreadCacheModel =
			(UserThreadCacheModel)object;

		if (userThreadId == userThreadCacheModel.userThreadId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userThreadId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{userThreadId=");
		sb.append(userThreadId);
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
		sb.append(", mbThreadId=");
		sb.append(mbThreadId);
		sb.append(", topMBMessageId=");
		sb.append(topMBMessageId);
		sb.append(", read=");
		sb.append(read);
		sb.append(", deleted=");
		sb.append(deleted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserThread toEntityModel() {
		UserThreadImpl userThreadImpl = new UserThreadImpl();

		userThreadImpl.setUserThreadId(userThreadId);
		userThreadImpl.setCompanyId(companyId);
		userThreadImpl.setUserId(userId);

		if (userName == null) {
			userThreadImpl.setUserName("");
		}
		else {
			userThreadImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			userThreadImpl.setCreateDate(null);
		}
		else {
			userThreadImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userThreadImpl.setModifiedDate(null);
		}
		else {
			userThreadImpl.setModifiedDate(new Date(modifiedDate));
		}

		userThreadImpl.setMbThreadId(mbThreadId);
		userThreadImpl.setTopMBMessageId(topMBMessageId);
		userThreadImpl.setRead(read);
		userThreadImpl.setDeleted(deleted);

		userThreadImpl.resetOriginalValues();

		return userThreadImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userThreadId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		mbThreadId = objectInput.readLong();

		topMBMessageId = objectInput.readLong();

		read = objectInput.readBoolean();

		deleted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userThreadId);

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

		objectOutput.writeLong(mbThreadId);

		objectOutput.writeLong(topMBMessageId);

		objectOutput.writeBoolean(read);

		objectOutput.writeBoolean(deleted);
	}

	public long userThreadId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long mbThreadId;
	public long topMBMessageId;
	public boolean read;
	public boolean deleted;

}