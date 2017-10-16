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

import com.liferay.watson.model.WatsonRelationship;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonRelationship in entity cache.
 *
 * @author Steven Smith
 * @see WatsonRelationship
 * @generated
 */
@ProviderType
public class WatsonRelationshipCacheModel implements CacheModel<WatsonRelationship>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonRelationshipCacheModel)) {
			return false;
		}

		WatsonRelationshipCacheModel watsonRelationshipCacheModel = (WatsonRelationshipCacheModel)obj;

		if (watsonRelationshipId == watsonRelationshipCacheModel.watsonRelationshipId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonRelationshipId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{watsonRelationshipId=");
		sb.append(watsonRelationshipId);
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
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", classNameId1=");
		sb.append(classNameId1);
		sb.append(", classPK1=");
		sb.append(classPK1);
		sb.append(", classNameId2=");
		sb.append(classNameId2);
		sb.append(", classPK2=");
		sb.append(classPK2);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonRelationship toEntityModel() {
		WatsonRelationshipImpl watsonRelationshipImpl = new WatsonRelationshipImpl();

		watsonRelationshipImpl.setWatsonRelationshipId(watsonRelationshipId);
		watsonRelationshipImpl.setCompanyId(companyId);
		watsonRelationshipImpl.setUserId(userId);

		if (userName == null) {
			watsonRelationshipImpl.setUserName(StringPool.BLANK);
		}
		else {
			watsonRelationshipImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonRelationshipImpl.setCreateDate(null);
		}
		else {
			watsonRelationshipImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonRelationshipImpl.setModifiedDate(null);
		}
		else {
			watsonRelationshipImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonRelationshipImpl.setWatsonIncidentId(watsonIncidentId);
		watsonRelationshipImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonRelationshipImpl.setClassNameId1(classNameId1);
		watsonRelationshipImpl.setClassPK1(classPK1);
		watsonRelationshipImpl.setClassNameId2(classNameId2);
		watsonRelationshipImpl.setClassPK2(classPK2);

		if (description == null) {
			watsonRelationshipImpl.setDescription(StringPool.BLANK);
		}
		else {
			watsonRelationshipImpl.setDescription(description);
		}

		watsonRelationshipImpl.setStatus(status);

		watsonRelationshipImpl.resetOriginalValues();

		return watsonRelationshipImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonRelationshipId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		classNameId1 = objectInput.readLong();

		classPK1 = objectInput.readLong();

		classNameId2 = objectInput.readLong();

		classPK2 = objectInput.readLong();
		description = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonRelationshipId);

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

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(classNameId1);

		objectOutput.writeLong(classPK1);

		objectOutput.writeLong(classNameId2);

		objectOutput.writeLong(classPK2);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(status);
	}

	public long watsonRelationshipId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonIncidentId;
	public long typeWatsonListTypeId;
	public long classNameId1;
	public long classPK1;
	public long classNameId2;
	public long classPK2;
	public String description;
	public int status;
}