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

import com.liferay.osb.loop.model.LoopJobTitle;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoopJobTitle in entity cache.
 *
 * @author Ethan Bustad
 * @see LoopJobTitle
 * @generated
 */
@ProviderType
public class LoopJobTitleCacheModel implements CacheModel<LoopJobTitle>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopJobTitleCacheModel)) {
			return false;
		}

		LoopJobTitleCacheModel loopJobTitleCacheModel = (LoopJobTitleCacheModel)obj;

		if (loopJobTitleId == loopJobTitleCacheModel.loopJobTitleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopJobTitleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{loopJobTitleId=");
		sb.append(loopJobTitleId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopJobTitle toEntityModel() {
		LoopJobTitleImpl loopJobTitleImpl = new LoopJobTitleImpl();

		loopJobTitleImpl.setLoopJobTitleId(loopJobTitleId);
		loopJobTitleImpl.setCompanyId(companyId);
		loopJobTitleImpl.setUserId(userId);

		if (userName == null) {
			loopJobTitleImpl.setUserName("");
		}
		else {
			loopJobTitleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			loopJobTitleImpl.setCreateDate(null);
		}
		else {
			loopJobTitleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			loopJobTitleImpl.setModifiedDate(null);
		}
		else {
			loopJobTitleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			loopJobTitleImpl.setName("");
		}
		else {
			loopJobTitleImpl.setName(name);
		}

		if (description == null) {
			loopJobTitleImpl.setDescription("");
		}
		else {
			loopJobTitleImpl.setDescription(description);
		}

		loopJobTitleImpl.setStatus(status);

		loopJobTitleImpl.resetOriginalValues();

		return loopJobTitleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopJobTitleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(loopJobTitleId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(status);
	}

	public long loopJobTitleId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public int status;
}