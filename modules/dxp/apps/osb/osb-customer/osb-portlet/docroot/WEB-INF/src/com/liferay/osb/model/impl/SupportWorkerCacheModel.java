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

import com.liferay.osb.model.SupportWorker;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing SupportWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorker
 * @generated
 */
public class SupportWorkerCacheModel implements CacheModel<SupportWorker>,
	Serializable {
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