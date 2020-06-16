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

import com.liferay.osb.loop.model.LoopDivisionRel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopDivisionRel in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopDivisionRelCacheModel
	implements CacheModel<LoopDivisionRel>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopDivisionRelCacheModel)) {
			return false;
		}

		LoopDivisionRelCacheModel loopDivisionRelCacheModel =
			(LoopDivisionRelCacheModel)obj;

		if (loopDivisionRelId == loopDivisionRelCacheModel.loopDivisionRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopDivisionRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{loopDivisionRelId=");
		sb.append(loopDivisionRelId);
		sb.append(", childLoopDivisionId=");
		sb.append(childLoopDivisionId);
		sb.append(", loopPersonId=");
		sb.append(loopPersonId);
		sb.append(", parentLoopDivisionId=");
		sb.append(parentLoopDivisionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopDivisionRel toEntityModel() {
		LoopDivisionRelImpl loopDivisionRelImpl = new LoopDivisionRelImpl();

		loopDivisionRelImpl.setLoopDivisionRelId(loopDivisionRelId);
		loopDivisionRelImpl.setChildLoopDivisionId(childLoopDivisionId);
		loopDivisionRelImpl.setLoopPersonId(loopPersonId);
		loopDivisionRelImpl.setParentLoopDivisionId(parentLoopDivisionId);

		loopDivisionRelImpl.resetOriginalValues();

		return loopDivisionRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopDivisionRelId = objectInput.readLong();

		childLoopDivisionId = objectInput.readLong();

		loopPersonId = objectInput.readLong();

		parentLoopDivisionId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopDivisionRelId);

		objectOutput.writeLong(childLoopDivisionId);

		objectOutput.writeLong(loopPersonId);

		objectOutput.writeLong(parentLoopDivisionId);
	}

	public long loopDivisionRelId;
	public long childLoopDivisionId;
	public long loopPersonId;
	public long parentLoopDivisionId;

}