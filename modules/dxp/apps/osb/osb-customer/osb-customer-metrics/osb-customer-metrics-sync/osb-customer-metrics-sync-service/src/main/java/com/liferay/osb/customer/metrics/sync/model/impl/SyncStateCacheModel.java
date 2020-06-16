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

package com.liferay.osb.customer.metrics.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.metrics.sync.model.SyncState;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SyncState in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SyncState
 * @generated
 */
@ProviderType
public class SyncStateCacheModel implements CacheModel<SyncState>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncStateCacheModel)) {
			return false;
		}

		SyncStateCacheModel syncStateCacheModel = (SyncStateCacheModel)obj;

		if (syncStateId == syncStateCacheModel.syncStateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncStateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{syncStateId=");
		sb.append(syncStateId);
		sb.append(", modelName=");
		sb.append(modelName);
		sb.append(", lastRunTime=");
		sb.append(lastRunTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncState toEntityModel() {
		SyncStateImpl syncStateImpl = new SyncStateImpl();

		syncStateImpl.setSyncStateId(syncStateId);

		if (modelName == null) {
			syncStateImpl.setModelName(StringPool.BLANK);
		}
		else {
			syncStateImpl.setModelName(modelName);
		}

		syncStateImpl.setLastRunTime(lastRunTime);

		syncStateImpl.resetOriginalValues();

		return syncStateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		syncStateId = objectInput.readLong();
		modelName = objectInput.readUTF();

		lastRunTime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(syncStateId);

		if (modelName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modelName);
		}

		objectOutput.writeLong(lastRunTime);
	}

	public long syncStateId;
	public String modelName;
	public long lastRunTime;
}