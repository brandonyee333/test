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

import com.liferay.watson.model.WatsonListTypeRelAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonListTypeRelAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAudit
 * @generated
 */
@ProviderType
public class WatsonListTypeRelAuditCacheModel implements CacheModel<WatsonListTypeRelAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeRelAuditCacheModel)) {
			return false;
		}

		WatsonListTypeRelAuditCacheModel watsonListTypeRelAuditCacheModel = (WatsonListTypeRelAuditCacheModel)obj;

		if (watsonListTypeRelAuditId == watsonListTypeRelAuditCacheModel.watsonListTypeRelAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonListTypeRelAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{watsonListTypeRelAuditId=");
		sb.append(watsonListTypeRelAuditId);
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
		sb.append(", watsonListTypeRelId=");
		sb.append(watsonListTypeRelId);
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
	public WatsonListTypeRelAudit toEntityModel() {
		WatsonListTypeRelAuditImpl watsonListTypeRelAuditImpl = new WatsonListTypeRelAuditImpl();

		watsonListTypeRelAuditImpl.setWatsonListTypeRelAuditId(watsonListTypeRelAuditId);
		watsonListTypeRelAuditImpl.setGroupId(groupId);
		watsonListTypeRelAuditImpl.setCompanyId(companyId);
		watsonListTypeRelAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonListTypeRelAuditImpl.setUserName("");
		}
		else {
			watsonListTypeRelAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonListTypeRelAuditImpl.setCreateDate(null);
		}
		else {
			watsonListTypeRelAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonListTypeRelAuditImpl.setModifiedDate(null);
		}
		else {
			watsonListTypeRelAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonListTypeRelAuditImpl.setWatsonListTypeId(watsonListTypeId);
		watsonListTypeRelAuditImpl.setWatsonListTypeRelId(watsonListTypeRelId);
		watsonListTypeRelAuditImpl.setClassNameId(classNameId);
		watsonListTypeRelAuditImpl.setClassPK(classPK);
		watsonListTypeRelAuditImpl.setPrimary(primary);

		if (value == null) {
			watsonListTypeRelAuditImpl.setValue("");
		}
		else {
			watsonListTypeRelAuditImpl.setValue(value);
		}

		if (type == null) {
			watsonListTypeRelAuditImpl.setType("");
		}
		else {
			watsonListTypeRelAuditImpl.setType(type);
		}

		watsonListTypeRelAuditImpl.setStatus(status);

		watsonListTypeRelAuditImpl.resetOriginalValues();

		return watsonListTypeRelAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonListTypeRelAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonListTypeId = objectInput.readLong();

		watsonListTypeRelId = objectInput.readLong();

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
		objectOutput.writeLong(watsonListTypeRelAuditId);

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

		objectOutput.writeLong(watsonListTypeRelId);

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

	public long watsonListTypeRelAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonListTypeId;
	public long watsonListTypeRelId;
	public long classNameId;
	public long classPK;
	public boolean primary;
	public String value;
	public String type;
	public int status;
}