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

import com.liferay.osb.model.AccountInformation;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountInformation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformation
 * @generated
 */
public class AccountInformationCacheModel implements CacheModel<AccountInformation>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{accountInformationId=");
		sb.append(accountInformationId);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", accountProjectId=");
		sb.append(accountProjectId);
		sb.append(", fieldId=");
		sb.append(fieldId);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	public AccountInformation toEntityModel() {
		AccountInformationImpl accountInformationImpl = new AccountInformationImpl();

		accountInformationImpl.setAccountInformationId(accountInformationId);
		accountInformationImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountInformationImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountInformationImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountInformationImpl.setModifiedDate(null);
		}
		else {
			accountInformationImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountInformationImpl.setAccountEntryId(accountEntryId);
		accountInformationImpl.setAccountProjectId(accountProjectId);
		accountInformationImpl.setFieldId(fieldId);

		if (data == null) {
			accountInformationImpl.setData(StringPool.BLANK);
		}
		else {
			accountInformationImpl.setData(data);
		}

		accountInformationImpl.resetOriginalValues();

		return accountInformationImpl;
	}

	public long accountInformationId;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long accountEntryId;
	public long accountProjectId;
	public int fieldId;
	public String data;
}