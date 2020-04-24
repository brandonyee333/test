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

import com.liferay.osb.loop.model.LoopTopicAssignment;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoopTopicAssignment in entity cache.
 *
 * @author Ethan Bustad
 * @see LoopTopicAssignment
 * @generated
 */
@ProviderType
public class LoopTopicAssignmentCacheModel implements CacheModel<LoopTopicAssignment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopTopicAssignmentCacheModel)) {
			return false;
		}

		LoopTopicAssignmentCacheModel loopTopicAssignmentCacheModel = (LoopTopicAssignmentCacheModel)obj;

		if (loopTopicAssignmentId == loopTopicAssignmentCacheModel.loopTopicAssignmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopTopicAssignmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{loopTopicAssignmentId=");
		sb.append(loopTopicAssignmentId);
		sb.append(", loopPersonId=");
		sb.append(loopPersonId);
		sb.append(", loopTopicId=");
		sb.append(loopTopicId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusByDate=");
		sb.append(statusByDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopTopicAssignment toEntityModel() {
		LoopTopicAssignmentImpl loopTopicAssignmentImpl = new LoopTopicAssignmentImpl();

		loopTopicAssignmentImpl.setLoopTopicAssignmentId(loopTopicAssignmentId);
		loopTopicAssignmentImpl.setLoopPersonId(loopPersonId);
		loopTopicAssignmentImpl.setLoopTopicId(loopTopicId);
		loopTopicAssignmentImpl.setStatus(status);
		loopTopicAssignmentImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			loopTopicAssignmentImpl.setStatusByUserName("");
		}
		else {
			loopTopicAssignmentImpl.setStatusByUserName(statusByUserName);
		}

		if (statusByDate == Long.MIN_VALUE) {
			loopTopicAssignmentImpl.setStatusByDate(null);
		}
		else {
			loopTopicAssignmentImpl.setStatusByDate(new Date(statusByDate));
		}

		loopTopicAssignmentImpl.resetOriginalValues();

		return loopTopicAssignmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopTopicAssignmentId = objectInput.readLong();

		loopPersonId = objectInput.readLong();

		loopTopicId = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusByDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(loopTopicAssignmentId);

		objectOutput.writeLong(loopPersonId);

		objectOutput.writeLong(loopTopicId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusByDate);
	}

	public long loopTopicAssignmentId;
	public long loopPersonId;
	public long loopTopicId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusByDate;
}