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

package com.liferay.osb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.SupportWorker;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SupportWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorker
 * @generated
 */
@ProviderType
public class SupportWorkerCacheModel implements CacheModel<SupportWorker>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportWorkerCacheModel)) {
			return false;
		}

		SupportWorkerCacheModel supportWorkerCacheModel = (SupportWorkerCacheModel)obj;

		if (supportWorkerId == supportWorkerCacheModel.supportWorkerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportWorkerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{supportWorkerId=");
		sb.append(supportWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", supportTeamId=");
		sb.append(supportTeamId);
		sb.append(", supportLaborId=");
		sb.append(supportLaborId);
		sb.append(", autoAssign=");
		sb.append(autoAssign);
		sb.append(", assignedWork=");
		sb.append(assignedWork);
		sb.append(", maxWork=");
		sb.append(maxWork);
		sb.append(", escalationLevel=");
		sb.append(escalationLevel);
		sb.append(", role=");
		sb.append(role);
		sb.append(", escalationLevel2Role=");
		sb.append(escalationLevel2Role);
		sb.append(", notifications=");
		sb.append(notifications);
		sb.append(", clockedIn=");
		sb.append(clockedIn);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportWorker toEntityModel() {
		SupportWorkerImpl supportWorkerImpl = new SupportWorkerImpl();

		supportWorkerImpl.setSupportWorkerId(supportWorkerId);
		supportWorkerImpl.setUserId(userId);
		supportWorkerImpl.setSupportTeamId(supportTeamId);
		supportWorkerImpl.setSupportLaborId(supportLaborId);
		supportWorkerImpl.setAutoAssign(autoAssign);
		supportWorkerImpl.setAssignedWork(assignedWork);
		supportWorkerImpl.setMaxWork(maxWork);
		supportWorkerImpl.setEscalationLevel(escalationLevel);
		supportWorkerImpl.setRole(role);
		supportWorkerImpl.setEscalationLevel2Role(escalationLevel2Role);
		supportWorkerImpl.setNotifications(notifications);
		supportWorkerImpl.setClockedIn(clockedIn);

		supportWorkerImpl.resetOriginalValues();

		return supportWorkerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		supportWorkerId = objectInput.readLong();

		userId = objectInput.readLong();

		supportTeamId = objectInput.readLong();

		supportLaborId = objectInput.readLong();

		autoAssign = objectInput.readBoolean();

		assignedWork = objectInput.readDouble();

		maxWork = objectInput.readDouble();

		escalationLevel = objectInput.readInt();

		role = objectInput.readInt();

		escalationLevel2Role = objectInput.readInt();

		notifications = objectInput.readInt();

		clockedIn = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(supportWorkerId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(supportTeamId);

		objectOutput.writeLong(supportLaborId);

		objectOutput.writeBoolean(autoAssign);

		objectOutput.writeDouble(assignedWork);

		objectOutput.writeDouble(maxWork);

		objectOutput.writeInt(escalationLevel);

		objectOutput.writeInt(role);

		objectOutput.writeInt(escalationLevel2Role);

		objectOutput.writeInt(notifications);

		objectOutput.writeBoolean(clockedIn);
	}

	public long supportWorkerId;
	public long userId;
	public long supportTeamId;
	public long supportLaborId;
	public boolean autoAssign;
	public double assignedWork;
	public double maxWork;
	public int escalationLevel;
	public int role;
	public int escalationLevel2Role;
	public int notifications;
	public boolean clockedIn;
}