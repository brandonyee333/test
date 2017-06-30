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

import com.liferay.osb.model.AccountWorker;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AccountWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorker
 * @generated
 */
public class AccountWorkerCacheModel implements CacheModel<AccountWorker>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{accountWorkerId=");
		sb.append(accountWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", role=");
		sb.append(role);
		sb.append(", notifications=");
		sb.append(notifications);
		sb.append("}");

		return sb.toString();
	}

	public AccountWorker toEntityModel() {
		AccountWorkerImpl accountWorkerImpl = new AccountWorkerImpl();

		accountWorkerImpl.setAccountWorkerId(accountWorkerId);
		accountWorkerImpl.setUserId(userId);
		accountWorkerImpl.setAccountEntryId(accountEntryId);
		accountWorkerImpl.setRole(role);
		accountWorkerImpl.setNotifications(notifications);

		accountWorkerImpl.resetOriginalValues();

		return accountWorkerImpl;
	}

	public long accountWorkerId;
	public long userId;
	public long accountEntryId;
	public int role;
	public int notifications;
}