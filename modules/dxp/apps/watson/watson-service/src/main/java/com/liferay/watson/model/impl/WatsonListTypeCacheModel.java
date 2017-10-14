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

import com.liferay.watson.model.WatsonListType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonListType in entity cache.
 *
 * @author Eddie Olson
 * @see WatsonListType
 * @generated
 */
@ProviderType
public class WatsonListTypeCacheModel implements CacheModel<WatsonListType>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeCacheModel)) {
			return false;
		}

		WatsonListTypeCacheModel watsonListTypeCacheModel = (WatsonListTypeCacheModel)obj;

		if (watsonListTypeId == watsonListTypeCacheModel.watsonListTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonListTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{watsonListTypeId=");
		sb.append(watsonListTypeId);
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
		sb.append(", parentWatsonListTypeId=");
		sb.append(parentWatsonListTypeId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonListType toEntityModel() {
		WatsonListTypeImpl watsonListTypeImpl = new WatsonListTypeImpl();

		watsonListTypeImpl.setWatsonListTypeId(watsonListTypeId);
		watsonListTypeImpl.setCompanyId(companyId);
		watsonListTypeImpl.setUserId(userId);

		if (userName == null) {
			watsonListTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			watsonListTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonListTypeImpl.setCreateDate(null);
		}
		else {
			watsonListTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonListTypeImpl.setModifiedDate(null);
		}
		else {
			watsonListTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonListTypeImpl.setParentWatsonListTypeId(parentWatsonListTypeId);

		if (name == null) {
			watsonListTypeImpl.setName(StringPool.BLANK);
		}
		else {
			watsonListTypeImpl.setName(name);
		}

		if (type == null) {
			watsonListTypeImpl.setType(StringPool.BLANK);
		}
		else {
			watsonListTypeImpl.setType(type);
		}

		watsonListTypeImpl.setStatus(status);

		watsonListTypeImpl.resetOriginalValues();

		return watsonListTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonListTypeId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentWatsonListTypeId = objectInput.readLong();
		name = objectInput.readUTF();
		type = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonListTypeId);

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

		objectOutput.writeLong(parentWatsonListTypeId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(status);
	}

	public long watsonListTypeId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentWatsonListTypeId;
	public String name;
	public String type;
	public int status;
}