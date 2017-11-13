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

import com.liferay.watson.model.WatsonIncidentRel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonIncidentRel in entity cache.
 *
 * @author Steven Smith
 * @see WatsonIncidentRel
 * @generated
 */
@ProviderType
public class WatsonIncidentRelCacheModel implements CacheModel<WatsonIncidentRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentRelCacheModel)) {
			return false;
		}

		WatsonIncidentRelCacheModel watsonIncidentRelCacheModel = (WatsonIncidentRelCacheModel)obj;

		if (watsonIncidentRelId == watsonIncidentRelCacheModel.watsonIncidentRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonIncidentRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{watsonIncidentRelId=");
		sb.append(watsonIncidentRelId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", watsonIncidentId1=");
		sb.append(watsonIncidentId1);
		sb.append(", watsonIncidentId2=");
		sb.append(watsonIncidentId2);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonIncidentRel toEntityModel() {
		WatsonIncidentRelImpl watsonIncidentRelImpl = new WatsonIncidentRelImpl();

		watsonIncidentRelImpl.setWatsonIncidentRelId(watsonIncidentRelId);
		watsonIncidentRelImpl.setGroupId(groupId);
		watsonIncidentRelImpl.setCompanyId(companyId);
		watsonIncidentRelImpl.setUserId(userId);

		if (userName == null) {
			watsonIncidentRelImpl.setUserName(StringPool.BLANK);
		}
		else {
			watsonIncidentRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonIncidentRelImpl.setCreateDate(null);
		}
		else {
			watsonIncidentRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonIncidentRelImpl.setModifiedDate(null);
		}
		else {
			watsonIncidentRelImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonIncidentRelImpl.setWatsonIncidentId1(watsonIncidentId1);
		watsonIncidentRelImpl.setWatsonIncidentId2(watsonIncidentId2);

		if (type == null) {
			watsonIncidentRelImpl.setType(StringPool.BLANK);
		}
		else {
			watsonIncidentRelImpl.setType(type);
		}

		watsonIncidentRelImpl.setStatus(status);

		watsonIncidentRelImpl.resetOriginalValues();

		return watsonIncidentRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonIncidentRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonIncidentId1 = objectInput.readLong();

		watsonIncidentId2 = objectInput.readLong();
		type = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonIncidentRelId);

		objectOutput.writeLong(groupId);

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

		objectOutput.writeLong(watsonIncidentId1);

		objectOutput.writeLong(watsonIncidentId2);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(status);
	}

	public long watsonIncidentRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonIncidentId1;
	public long watsonIncidentId2;
	public String type;
	public int status;
}