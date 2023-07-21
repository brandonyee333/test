/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoopDivisionRelCacheModel)) {
			return false;
		}

		LoopDivisionRelCacheModel loopDivisionRelCacheModel =
			(LoopDivisionRelCacheModel)object;

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