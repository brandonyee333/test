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

import com.liferay.osb.model.AccountCall;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountCall in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCall
 * @generated
 */
public class AccountCallCacheModel implements CacheModel<AccountCall>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{accountCallId=");
		sb.append(accountCallId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", callDate=");
		sb.append(callDate);
		sb.append(", callLength=");
		sb.append(callLength);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", clientsPresent=");
		sb.append(clientsPresent);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", actionItems=");
		sb.append(actionItems);
		sb.append("}");

		return sb.toString();
	}

	public AccountCall toEntityModel() {
		AccountCallImpl accountCallImpl = new AccountCallImpl();

		accountCallImpl.setAccountCallId(accountCallId);

		if (createDate == Long.MIN_VALUE) {
			accountCallImpl.setCreateDate(null);
		}
		else {
			accountCallImpl.setCreateDate(new Date(createDate));
		}

		accountCallImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountCallImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountCallImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountCallImpl.setModifiedDate(null);
		}
		else {
			accountCallImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountCallImpl.setAccountEntryId(accountEntryId);
		accountCallImpl.setType(type);

		if (callDate == Long.MIN_VALUE) {
			accountCallImpl.setCallDate(null);
		}
		else {
			accountCallImpl.setCallDate(new Date(callDate));
		}

		accountCallImpl.setCallLength(callLength);

		if (summary == null) {
			accountCallImpl.setSummary(StringPool.BLANK);
		}
		else {
			accountCallImpl.setSummary(summary);
		}

		if (clientsPresent == null) {
			accountCallImpl.setClientsPresent(StringPool.BLANK);
		}
		else {
			accountCallImpl.setClientsPresent(clientsPresent);
		}

		if (notes == null) {
			accountCallImpl.setNotes(StringPool.BLANK);
		}
		else {
			accountCallImpl.setNotes(notes);
		}

		if (actionItems == null) {
			accountCallImpl.setActionItems(StringPool.BLANK);
		}
		else {
			accountCallImpl.setActionItems(actionItems);
		}

		accountCallImpl.resetOriginalValues();

		return accountCallImpl;
	}

	public long accountCallId;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public int type;
	public long callDate;
	public long callLength;
	public String summary;
	public String clientsPresent;
	public String notes;
	public String actionItems;
}