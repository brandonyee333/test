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

import com.liferay.watson.model.WatsonIncidentRelAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonIncidentRelAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonIncidentRelAudit
 * @generated
 */
@ProviderType
public class WatsonIncidentRelAuditCacheModel implements CacheModel<WatsonIncidentRelAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentRelAuditCacheModel)) {
			return false;
		}

		WatsonIncidentRelAuditCacheModel watsonIncidentRelAuditCacheModel = (WatsonIncidentRelAuditCacheModel)obj;

		if (watsonIncidentRelAuditId == watsonIncidentRelAuditCacheModel.watsonIncidentRelAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonIncidentRelAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{watsonIncidentRelAuditId=");
		sb.append(watsonIncidentRelAuditId);
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
		sb.append(", watsonIncidentRelId=");
		sb.append(watsonIncidentRelId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonIncidentRelAudit toEntityModel() {
		WatsonIncidentRelAuditImpl watsonIncidentRelAuditImpl = new WatsonIncidentRelAuditImpl();

		watsonIncidentRelAuditImpl.setWatsonIncidentRelAuditId(watsonIncidentRelAuditId);
		watsonIncidentRelAuditImpl.setGroupId(groupId);
		watsonIncidentRelAuditImpl.setCompanyId(companyId);
		watsonIncidentRelAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonIncidentRelAuditImpl.setUserName("");
		}
		else {
			watsonIncidentRelAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonIncidentRelAuditImpl.setCreateDate(null);
		}
		else {
			watsonIncidentRelAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonIncidentRelAuditImpl.setModifiedDate(null);
		}
		else {
			watsonIncidentRelAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonIncidentRelAuditImpl.setWatsonIncidentId1(watsonIncidentId1);
		watsonIncidentRelAuditImpl.setWatsonIncidentId2(watsonIncidentId2);
		watsonIncidentRelAuditImpl.setWatsonIncidentRelId(watsonIncidentRelId);

		if (type == null) {
			watsonIncidentRelAuditImpl.setType("");
		}
		else {
			watsonIncidentRelAuditImpl.setType(type);
		}

		watsonIncidentRelAuditImpl.setStatus(status);

		watsonIncidentRelAuditImpl.resetOriginalValues();

		return watsonIncidentRelAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonIncidentRelAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonIncidentId1 = objectInput.readLong();

		watsonIncidentId2 = objectInput.readLong();

		watsonIncidentRelId = objectInput.readLong();
		type = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonIncidentRelAuditId);

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

		objectOutput.writeLong(watsonIncidentId1);

		objectOutput.writeLong(watsonIncidentId2);

		objectOutput.writeLong(watsonIncidentRelId);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(status);
	}

	public long watsonIncidentRelAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonIncidentId1;
	public long watsonIncidentId2;
	public long watsonIncidentRelId;
	public String type;
	public int status;
}