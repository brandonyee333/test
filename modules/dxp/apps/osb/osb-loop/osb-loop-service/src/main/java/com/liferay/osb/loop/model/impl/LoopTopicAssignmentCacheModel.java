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

package com.liferay.osb.loop.model.impl;

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
 * @generated
 */
public class LoopTopicAssignmentCacheModel
	implements CacheModel<LoopTopicAssignment>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopTopicAssignmentCacheModel)) {
			return false;
		}

		LoopTopicAssignmentCacheModel loopTopicAssignmentCacheModel =
			(LoopTopicAssignmentCacheModel)obj;

		if (loopTopicAssignmentId ==
				loopTopicAssignmentCacheModel.loopTopicAssignmentId) {

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
		sb.append(", statusByDate=");
		sb.append(statusByDate);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopTopicAssignment toEntityModel() {
		LoopTopicAssignmentImpl loopTopicAssignmentImpl =
			new LoopTopicAssignmentImpl();

		loopTopicAssignmentImpl.setLoopTopicAssignmentId(loopTopicAssignmentId);
		loopTopicAssignmentImpl.setLoopPersonId(loopPersonId);
		loopTopicAssignmentImpl.setLoopTopicId(loopTopicId);

		if (statusByDate == Long.MIN_VALUE) {
			loopTopicAssignmentImpl.setStatusByDate(null);
		}
		else {
			loopTopicAssignmentImpl.setStatusByDate(new Date(statusByDate));
		}

		loopTopicAssignmentImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			loopTopicAssignmentImpl.setStatusByUserName("");
		}
		else {
			loopTopicAssignmentImpl.setStatusByUserName(statusByUserName);
		}

		loopTopicAssignmentImpl.setStatus(status);

		loopTopicAssignmentImpl.resetOriginalValues();

		return loopTopicAssignmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopTopicAssignmentId = objectInput.readLong();

		loopPersonId = objectInput.readLong();

		loopTopicId = objectInput.readLong();
		statusByDate = objectInput.readLong();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopTopicAssignmentId);

		objectOutput.writeLong(loopPersonId);

		objectOutput.writeLong(loopTopicId);
		objectOutput.writeLong(statusByDate);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeInt(status);
	}

	public long loopTopicAssignmentId;
	public long loopPersonId;
	public long loopTopicId;
	public long statusByDate;
	public long statusByUserId;
	public String statusByUserName;
	public int status;

}