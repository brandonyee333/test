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

import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopParticipantAssignment in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopParticipantAssignmentCacheModel
	implements CacheModel<LoopParticipantAssignment>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopParticipantAssignmentCacheModel)) {
			return false;
		}

		LoopParticipantAssignmentCacheModel
			loopParticipantAssignmentCacheModel =
				(LoopParticipantAssignmentCacheModel)obj;

		if (loopParticipantAssignmentId ==
				loopParticipantAssignmentCacheModel.
					loopParticipantAssignmentId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopParticipantAssignmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{loopParticipantAssignmentId=");
		sb.append(loopParticipantAssignmentId);
		sb.append(", loopDivisionId=");
		sb.append(loopDivisionId);
		sb.append(", loopPersonId=");
		sb.append(loopPersonId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopParticipantAssignment toEntityModel() {
		LoopParticipantAssignmentImpl loopParticipantAssignmentImpl =
			new LoopParticipantAssignmentImpl();

		loopParticipantAssignmentImpl.setLoopParticipantAssignmentId(
			loopParticipantAssignmentId);
		loopParticipantAssignmentImpl.setLoopDivisionId(loopDivisionId);
		loopParticipantAssignmentImpl.setLoopPersonId(loopPersonId);

		if (description == null) {
			loopParticipantAssignmentImpl.setDescription("");
		}
		else {
			loopParticipantAssignmentImpl.setDescription(description);
		}

		loopParticipantAssignmentImpl.setType(type);

		loopParticipantAssignmentImpl.resetOriginalValues();

		return loopParticipantAssignmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopParticipantAssignmentId = objectInput.readLong();

		loopDivisionId = objectInput.readLong();

		loopPersonId = objectInput.readLong();
		description = objectInput.readUTF();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopParticipantAssignmentId);

		objectOutput.writeLong(loopDivisionId);

		objectOutput.writeLong(loopPersonId);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(type);
	}

	public long loopParticipantAssignmentId;
	public long loopDivisionId;
	public long loopPersonId;
	public String description;
	public int type;

}