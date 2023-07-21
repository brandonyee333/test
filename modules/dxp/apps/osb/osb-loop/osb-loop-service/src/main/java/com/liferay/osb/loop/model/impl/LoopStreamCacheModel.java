/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.model.impl;

import com.liferay.osb.loop.model.LoopStream;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopStream in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopStreamCacheModel
	implements CacheModel<LoopStream>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoopStreamCacheModel)) {
			return false;
		}

		LoopStreamCacheModel loopStreamCacheModel =
			(LoopStreamCacheModel)object;

		if (loopStreamId == loopStreamCacheModel.loopStreamId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopStreamId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{loopStreamId=");
		sb.append(loopStreamId);
		sb.append(", loopPersonId=");
		sb.append(loopPersonId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopStream toEntityModel() {
		LoopStreamImpl loopStreamImpl = new LoopStreamImpl();

		loopStreamImpl.setLoopStreamId(loopStreamId);
		loopStreamImpl.setLoopPersonId(loopPersonId);

		if (name == null) {
			loopStreamImpl.setName("");
		}
		else {
			loopStreamImpl.setName(name);
		}

		if (description == null) {
			loopStreamImpl.setDescription("");
		}
		else {
			loopStreamImpl.setDescription(description);
		}

		loopStreamImpl.resetOriginalValues();

		return loopStreamImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopStreamId = objectInput.readLong();

		loopPersonId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopStreamId);

		objectOutput.writeLong(loopPersonId);

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
	}

	public long loopStreamId;
	public long loopPersonId;
	public String name;
	public String description;

}