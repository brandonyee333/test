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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopStreamCacheModel)) {
			return false;
		}

		LoopStreamCacheModel loopStreamCacheModel = (LoopStreamCacheModel)obj;

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