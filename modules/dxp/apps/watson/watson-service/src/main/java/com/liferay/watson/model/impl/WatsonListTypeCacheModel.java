/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.watson.model.WatsonListType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonListType in entity cache.
 *
 * @author Steven Smith
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
		StringBundler sb = new StringBundler(23);

		sb.append("{watsonListTypeId=");
		sb.append(watsonListTypeId);
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
		watsonListTypeImpl.setGroupId(groupId);
		watsonListTypeImpl.setCompanyId(companyId);
		watsonListTypeImpl.setUserId(userId);

		if (userName == null) {
			watsonListTypeImpl.setUserName("");
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
			watsonListTypeImpl.setName("");
		}
		else {
			watsonListTypeImpl.setName(name);
		}

		if (type == null) {
			watsonListTypeImpl.setType("");
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

		groupId = objectInput.readLong();

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

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(parentWatsonListTypeId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(status);
	}

	public long watsonListTypeId;
	public long groupId;
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