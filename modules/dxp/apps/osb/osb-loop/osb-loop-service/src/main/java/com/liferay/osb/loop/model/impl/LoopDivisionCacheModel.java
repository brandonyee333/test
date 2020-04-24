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

package com.liferay.osb.loop.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.model.LoopDivision;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoopDivision in entity cache.
 *
 * @author Ethan Bustad
 * @see LoopDivision
 * @generated
 */
@ProviderType
public class LoopDivisionCacheModel implements CacheModel<LoopDivision>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopDivisionCacheModel)) {
			return false;
		}

		LoopDivisionCacheModel loopDivisionCacheModel = (LoopDivisionCacheModel)obj;

		if (loopDivisionId == loopDivisionCacheModel.loopDivisionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopDivisionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{loopDivisionId=");
		sb.append(loopDivisionId);
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
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append(", parentLoopDivisionId=");
		sb.append(parentLoopDivisionId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", subtype=");
		sb.append(subtype);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopDivision toEntityModel() {
		LoopDivisionImpl loopDivisionImpl = new LoopDivisionImpl();

		loopDivisionImpl.setLoopDivisionId(loopDivisionId);
		loopDivisionImpl.setCompanyId(companyId);
		loopDivisionImpl.setUserId(userId);

		if (userName == null) {
			loopDivisionImpl.setUserName("");
		}
		else {
			loopDivisionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			loopDivisionImpl.setCreateDate(null);
		}
		else {
			loopDivisionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			loopDivisionImpl.setModifiedDate(null);
		}
		else {
			loopDivisionImpl.setModifiedDate(new Date(modifiedDate));
		}

		loopDivisionImpl.setOrganizationId(organizationId);
		loopDivisionImpl.setParentLoopDivisionId(parentLoopDivisionId);
		loopDivisionImpl.setType(type);
		loopDivisionImpl.setSubtype(subtype);

		if (extraData == null) {
			loopDivisionImpl.setExtraData("");
		}
		else {
			loopDivisionImpl.setExtraData(extraData);
		}

		if (imagePayload == null) {
			loopDivisionImpl.setImagePayload("");
		}
		else {
			loopDivisionImpl.setImagePayload(imagePayload);
		}

		loopDivisionImpl.resetOriginalValues();

		return loopDivisionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopDivisionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		organizationId = objectInput.readLong();

		parentLoopDivisionId = objectInput.readLong();

		type = objectInput.readInt();

		subtype = objectInput.readInt();
		extraData = objectInput.readUTF();
		imagePayload = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(loopDivisionId);

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

		objectOutput.writeLong(organizationId);

		objectOutput.writeLong(parentLoopDivisionId);

		objectOutput.writeInt(type);

		objectOutput.writeInt(subtype);

		if (extraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraData);
		}

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}
	}

	public long loopDivisionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long organizationId;
	public long parentLoopDivisionId;
	public int type;
	public int subtype;
	public String extraData;
	public String imagePayload;
}