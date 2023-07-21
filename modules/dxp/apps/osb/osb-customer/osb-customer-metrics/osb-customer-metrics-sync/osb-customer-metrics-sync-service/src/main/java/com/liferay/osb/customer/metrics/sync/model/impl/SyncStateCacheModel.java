/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.model.impl;

import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SyncState in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SyncStateCacheModel
	implements CacheModel<SyncState>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SyncStateCacheModel)) {
			return false;
		}

		SyncStateCacheModel syncStateCacheModel = (SyncStateCacheModel)object;

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
			syncStateImpl.setModelName("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(syncStateId);

		if (modelName == null) {
			objectOutput.writeUTF("");
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