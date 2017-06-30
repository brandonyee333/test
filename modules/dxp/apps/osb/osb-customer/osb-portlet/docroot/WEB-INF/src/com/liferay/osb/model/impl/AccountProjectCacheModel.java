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

import com.liferay.osb.model.AccountProject;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountProject
 * @generated
 */
public class AccountProjectCacheModel implements CacheModel<AccountProject>,
	Serializable {
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

	public long accountProjectId;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public String name;
}