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

import com.liferay.watson.model.WatsonListTypeRel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonListTypeRel in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonListTypeRelCacheModel implements CacheModel<WatsonListTypeRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeRelCacheModel)) {
			return false;
		}

		WatsonListTypeRelCacheModel watsonListTypeRelCacheModel = (WatsonListTypeRelCacheModel)obj;

		if (watsonListTypeRelId == watsonListTypeRelCacheModel.watsonListTypeRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonListTypeRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{watsonListTypeRelId=");
		sb.append(watsonListTypeRelId);
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
		sb.append(", watsonListTypeId=");
		sb.append(watsonListTypeId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", primary=");
		sb.append(primary);
		sb.append(", value=");
		sb.append(value);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonListTypeRel toEntityModel() {
		WatsonListTypeRelImpl watsonListTypeRelImpl = new WatsonListTypeRelImpl();

		watsonListTypeRelImpl.setWatsonListTypeRelId(watsonListTypeRelId);
		watsonListTypeRelImpl.setGroupId(groupId);
		watsonListTypeRelImpl.setCompanyId(companyId);
		watsonListTypeRelImpl.setUserId(userId);

		if (userName == null) {
			watsonListTypeRelImpl.setUserName("");
		}
		else {
			watsonListTypeRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonListTypeRelImpl.setCreateDate(null);
		}
		else {
			watsonListTypeRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonListTypeRelImpl.setModifiedDate(null);
		}
		else {
			watsonListTypeRelImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonListTypeRelImpl.setWatsonListTypeId(watsonListTypeId);
		watsonListTypeRelImpl.setClassNameId(classNameId);
		watsonListTypeRelImpl.setClassPK(classPK);
		watsonListTypeRelImpl.setPrimary(primary);

		if (value == null) {
			watsonListTypeRelImpl.setValue("");
		}
		else {
			watsonListTypeRelImpl.setValue(value);
		}

		if (type == null) {
			watsonListTypeRelImpl.setType("");
		}
		else {
			watsonListTypeRelImpl.setType(type);
		}

		watsonListTypeRelImpl.setStatus(status);

		watsonListTypeRelImpl.resetOriginalValues();

		return watsonListTypeRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonListTypeRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonListTypeId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		primary = objectInput.readBoolean();
		value = objectInput.readUTF();
		type = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonListTypeRelId);

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

		objectOutput.writeLong(watsonListTypeId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeBoolean(primary);

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(status);
	}

	public long watsonListTypeRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonListTypeId;
	public long classNameId;
	public long classPK;
	public boolean primary;
	public String value;
	public String type;
	public int status;
}