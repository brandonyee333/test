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

import com.liferay.osb.loop.model.LoopStreamEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopStreamEntry in entity cache.
 *
 * @author Ethan Bustad
 * @see LoopStreamEntry
 * @generated
 */
@ProviderType
public class LoopStreamEntryCacheModel implements CacheModel<LoopStreamEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopStreamEntryCacheModel)) {
			return false;
		}

		LoopStreamEntryCacheModel loopStreamEntryCacheModel = (LoopStreamEntryCacheModel)obj;

		if (loopStreamEntryId == loopStreamEntryCacheModel.loopStreamEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopStreamEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{loopStreamEntryId=");
		sb.append(loopStreamEntryId);
		sb.append(", loopPersonId=");
		sb.append(loopPersonId);
		sb.append(", loopStreamId=");
		sb.append(loopStreamId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", following=");
		sb.append(following);
		sb.append(", followingType=");
		sb.append(followingType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopStreamEntry toEntityModel() {
		LoopStreamEntryImpl loopStreamEntryImpl = new LoopStreamEntryImpl();

		loopStreamEntryImpl.setLoopStreamEntryId(loopStreamEntryId);
		loopStreamEntryImpl.setLoopPersonId(loopPersonId);
		loopStreamEntryImpl.setLoopStreamId(loopStreamId);
		loopStreamEntryImpl.setClassNameId(classNameId);
		loopStreamEntryImpl.setClassPK(classPK);
		loopStreamEntryImpl.setFollowing(following);
		loopStreamEntryImpl.setFollowingType(followingType);

		loopStreamEntryImpl.resetOriginalValues();

		return loopStreamEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopStreamEntryId = objectInput.readLong();

		loopPersonId = objectInput.readLong();

		loopStreamId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		following = objectInput.readBoolean();

		followingType = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(loopStreamEntryId);

		objectOutput.writeLong(loopPersonId);

		objectOutput.writeLong(loopStreamId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeBoolean(following);

		objectOutput.writeInt(followingType);
	}

	public long loopStreamEntryId;
	public long loopPersonId;
	public long loopStreamId;
	public long classNameId;
	public long classPK;
	public boolean following;
	public int followingType;
}