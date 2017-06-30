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

import com.liferay.osb.model.AccountEnvironment;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountEnvironment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironment
 * @generated
 */
public class AccountEnvironmentCacheModel implements CacheModel<AccountEnvironment>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{accountEnvironmentId=");
		sb.append(accountEnvironmentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", envOS=");
		sb.append(envOS);
		sb.append(", envOSCustom=");
		sb.append(envOSCustom);
		sb.append(", envDB=");
		sb.append(envDB);
		sb.append(", envJVM=");
		sb.append(envJVM);
		sb.append(", envAS=");
		sb.append(envAS);
		sb.append(", envLFR=");
		sb.append(envLFR);
		sb.append("}");

		return sb.toString();
	}

	public AccountEnvironment toEntityModel() {
		AccountEnvironmentImpl accountEnvironmentImpl = new AccountEnvironmentImpl();

		accountEnvironmentImpl.setAccountEnvironmentId(accountEnvironmentId);
		accountEnvironmentImpl.setUserId(userId);

		if (userName == null) {
			accountEnvironmentImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountEnvironmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountEnvironmentImpl.setCreateDate(null);
		}
		else {
			accountEnvironmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountEnvironmentImpl.setModifiedDate(null);
		}
		else {
			accountEnvironmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountEnvironmentImpl.setAccountEntryId(accountEntryId);
		accountEnvironmentImpl.setProductEntryId(productEntryId);

		if (name == null) {
			accountEnvironmentImpl.setName(StringPool.BLANK);
		}
		else {
			accountEnvironmentImpl.setName(name);
		}

		accountEnvironmentImpl.setEnvOS(envOS);

		if (envOSCustom == null) {
			accountEnvironmentImpl.setEnvOSCustom(StringPool.BLANK);
		}
		else {
			accountEnvironmentImpl.setEnvOSCustom(envOSCustom);
		}

		accountEnvironmentImpl.setEnvDB(envDB);
		accountEnvironmentImpl.setEnvJVM(envJVM);
		accountEnvironmentImpl.setEnvAS(envAS);
		accountEnvironmentImpl.setEnvLFR(envLFR);

		accountEnvironmentImpl.resetOriginalValues();

		return accountEnvironmentImpl;
	}

	public long accountEnvironmentId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public long productEntryId;
	public String name;
	public int envOS;
	public String envOSCustom;
	public int envDB;
	public int envJVM;
	public int envAS;
	public int envLFR;
}