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

import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopPersonRel in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopPersonRelCacheModel
	implements CacheModel<LoopPersonRel>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopPersonRelCacheModel)) {
			return false;
		}

		LoopPersonRelCacheModel loopPersonRelCacheModel =
			(LoopPersonRelCacheModel)obj;

		if (loopPersonRelId == loopPersonRelCacheModel.loopPersonRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopPersonRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{loopPersonRelId=");
		sb.append(loopPersonRelId);
		sb.append(", childLoopPersonId=");
		sb.append(childLoopPersonId);
		sb.append(", parentLoopPersonId=");
		sb.append(parentLoopPersonId);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopPersonRel toEntityModel() {
		LoopPersonRelImpl loopPersonRelImpl = new LoopPersonRelImpl();

		loopPersonRelImpl.setLoopPersonRelId(loopPersonRelId);
		loopPersonRelImpl.setChildLoopPersonId(childLoopPersonId);
		loopPersonRelImpl.setParentLoopPersonId(parentLoopPersonId);
		loopPersonRelImpl.setType(type);

		loopPersonRelImpl.resetOriginalValues();

		return loopPersonRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopPersonRelId = objectInput.readLong();

		childLoopPersonId = objectInput.readLong();

		parentLoopPersonId = objectInput.readLong();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopPersonRelId);

		objectOutput.writeLong(childLoopPersonId);

		objectOutput.writeLong(parentLoopPersonId);

		objectOutput.writeInt(type);
	}

	public long loopPersonRelId;
	public long childLoopPersonId;
	public long parentLoopPersonId;
	public int type;

}