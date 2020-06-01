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