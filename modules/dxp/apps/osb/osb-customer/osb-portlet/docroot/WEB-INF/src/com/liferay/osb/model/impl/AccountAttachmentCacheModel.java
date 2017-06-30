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

import com.liferay.osb.model.AccountAttachment;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachment
 * @generated
 */
public class AccountAttachmentCacheModel implements CacheModel<AccountAttachment>,
	Serializable {
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

	public AccountAttachment toEntityModel() {
		AccountAttachmentImpl accountAttachmentImpl = new AccountAttachmentImpl();

		accountAttachmentImpl.setAccountAttachmentId(accountAttachmentId);
		accountAttachmentImpl.setUserId(userId);

		if (userName == null) {
			accountAttachmentImpl.setUserName(StringPool.BLANK);
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
			accountAttachmentImpl.setFileName(StringPool.BLANK);
		}
		else {
			accountAttachmentImpl.setFileName(fileName);
		}

		accountAttachmentImpl.setFileSize(fileSize);
		accountAttachmentImpl.setType(type);

		accountAttachmentImpl.resetOriginalValues();

		return accountAttachmentImpl;
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