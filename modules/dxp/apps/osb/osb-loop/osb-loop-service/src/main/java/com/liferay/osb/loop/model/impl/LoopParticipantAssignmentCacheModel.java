/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoopParticipantAssignmentCacheModel)) {
			return false;
		}

		LoopParticipantAssignmentCacheModel
			loopParticipantAssignmentCacheModel =
				(LoopParticipantAssignmentCacheModel)object;

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