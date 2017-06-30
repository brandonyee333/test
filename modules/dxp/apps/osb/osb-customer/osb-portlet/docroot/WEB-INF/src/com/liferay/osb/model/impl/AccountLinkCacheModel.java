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

import com.liferay.osb.model.AccountLink;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountLink
 * @generated
 */
public class AccountLinkCacheModel implements CacheModel<AccountLink>,
	Serializable {
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

	public long accountLinkId;
	public long userId;
	public String userName;
	public long createDate;
	public long accountEntryId;
	public String url;
}