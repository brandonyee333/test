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

import com.liferay.osb.model.ContractEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ContractEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntry
 * @generated
 */
public class ContractEntryCacheModel implements CacheModel<ContractEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contractEntryId=");
		sb.append(contractEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", version=");
		sb.append(version);
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	public ContractEntry toEntityModel() {
		ContractEntryImpl contractEntryImpl = new ContractEntryImpl();

		contractEntryImpl.setContractEntryId(contractEntryId);
		contractEntryImpl.setUserId(userId);

		if (userName == null) {
			contractEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			contractEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			contractEntryImpl.setCreateDate(null);
		}
		else {
			contractEntryImpl.setCreateDate(new Date(createDate));
		}

		contractEntryImpl.setClassNameId(classNameId);
		contractEntryImpl.setClassPK(classPK);
		contractEntryImpl.setType(type);
		contractEntryImpl.setVersion(version);

		if (content == null) {
			contractEntryImpl.setContent(StringPool.BLANK);
		}
		else {
			contractEntryImpl.setContent(content);
		}

		contractEntryImpl.resetOriginalValues();

		return contractEntryImpl;
	}

	public long contractEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public int type;
	public int version;
	public String content;
}