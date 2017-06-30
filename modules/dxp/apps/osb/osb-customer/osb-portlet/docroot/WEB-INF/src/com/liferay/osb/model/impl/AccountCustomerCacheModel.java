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

import com.liferay.osb.model.AccountCustomer;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AccountCustomer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomer
 * @generated
 */
public class AccountCustomerCacheModel implements CacheModel<AccountCustomer>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{accountCustomerId=");
		sb.append(accountCustomerId);
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

	public AccountCustomer toEntityModel() {
		AccountCustomerImpl accountCustomerImpl = new AccountCustomerImpl();

		accountCustomerImpl.setAccountCustomerId(accountCustomerId);
		accountCustomerImpl.setUserId(userId);
		accountCustomerImpl.setAccountEntryId(accountEntryId);
		accountCustomerImpl.setRole(role);
		accountCustomerImpl.setNotifications(notifications);

		accountCustomerImpl.resetOriginalValues();

		return accountCustomerImpl;
	}

	public long accountCustomerId;
	public long userId;
	public long accountEntryId;
	public int role;
	public int notifications;
}