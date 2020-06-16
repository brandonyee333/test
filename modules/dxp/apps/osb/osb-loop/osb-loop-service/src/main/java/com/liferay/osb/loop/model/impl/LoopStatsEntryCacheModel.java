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

import com.liferay.osb.loop.model.LoopStatsEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopStatsEntry in entity cache.
 *
 * @author Ethan Bustad
 * @see LoopStatsEntry
 * @generated
 */
@ProviderType
public class LoopStatsEntryCacheModel implements CacheModel<LoopStatsEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopStatsEntryCacheModel)) {
			return false;
		}

		LoopStatsEntryCacheModel loopStatsEntryCacheModel = (LoopStatsEntryCacheModel)obj;

		if (loopStatsEntryId == loopStatsEntryCacheModel.loopStatsEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopStatsEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{loopStatsEntryId=");
		sb.append(loopStatsEntryId);
		sb.append(", modifiedTime=");
		sb.append(modifiedTime);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", score=");
		sb.append(score);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopStatsEntry toEntityModel() {
		LoopStatsEntryImpl loopStatsEntryImpl = new LoopStatsEntryImpl();

		loopStatsEntryImpl.setLoopStatsEntryId(loopStatsEntryId);
		loopStatsEntryImpl.setModifiedTime(modifiedTime);
		loopStatsEntryImpl.setClassNameId(classNameId);
		loopStatsEntryImpl.setClassPK(classPK);
		loopStatsEntryImpl.setScore(score);

		loopStatsEntryImpl.resetOriginalValues();

		return loopStatsEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopStatsEntryId = objectInput.readLong();

		modifiedTime = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		score = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(loopStatsEntryId);

		objectOutput.writeLong(modifiedTime);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeDouble(score);
	}

	public long loopStatsEntryId;
	public long modifiedTime;
	public long classNameId;
	public long classPK;
	public double score;
}