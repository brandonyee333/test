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
 * @see LoopStream
 * @generated
 */
@ProviderType
public class LoopStreamCacheModel implements CacheModel<LoopStream>,
	Externalizable {
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
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
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