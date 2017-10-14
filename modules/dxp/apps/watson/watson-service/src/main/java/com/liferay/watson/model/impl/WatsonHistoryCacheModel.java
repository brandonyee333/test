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

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.watson.model.WatsonHistory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonHistory in entity cache.
 *
 * @author Eddie Olson
 * @see WatsonHistory
 * @generated
 */
@ProviderType
public class WatsonHistoryCacheModel implements CacheModel<WatsonHistory>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonHistoryCacheModel)) {
			return false;
		}

		WatsonHistoryCacheModel watsonHistoryCacheModel = (WatsonHistoryCacheModel)obj;

		if (watsonHistoryId == watsonHistoryCacheModel.watsonHistoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonHistoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{watsonHistoryId=");
		sb.append(watsonHistoryId);
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
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonHistory toEntityModel() {
		WatsonHistoryImpl watsonHistoryImpl = new WatsonHistoryImpl();

		watsonHistoryImpl.setWatsonHistoryId(watsonHistoryId);
		watsonHistoryImpl.setCompanyId(companyId);
		watsonHistoryImpl.setUserId(userId);

		if (userName == null) {
			watsonHistoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			watsonHistoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonHistoryImpl.setCreateDate(null);
		}
		else {
			watsonHistoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonHistoryImpl.setModifiedDate(null);
		}
		else {
			watsonHistoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonHistoryImpl.setWatsonIncidentId(watsonIncidentId);
		watsonHistoryImpl.setClassNameId(classNameId);
		watsonHistoryImpl.setClassPK(classPK);
		watsonHistoryImpl.setType(type);
		watsonHistoryImpl.setStatus(status);

		watsonHistoryImpl.resetOriginalValues();

		return watsonHistoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonHistoryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		type = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonHistoryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(watsonIncidentId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(type);

		objectOutput.writeInt(status);
	}

	public long watsonHistoryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonIncidentId;
	public long classNameId;
	public long classPK;
	public int type;
	public int status;
}