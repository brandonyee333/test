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

import com.liferay.osb.model.SupportTeam;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SupportTeam in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeam
 * @generated
 */
public class SupportTeamCacheModel implements CacheModel<SupportTeam>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{supportTeamId=");
		sb.append(supportTeamId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentSupportTeamId=");
		sb.append(parentSupportTeamId);
		sb.append(", supportLaborId=");
		sb.append(supportLaborId);
		sb.append(", locationSupportRegionId=");
		sb.append(locationSupportRegionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", assignedWork=");
		sb.append(assignedWork);
		sb.append(", maxWork=");
		sb.append(maxWork);
		sb.append("}");

		return sb.toString();
	}

	public SupportTeam toEntityModel() {
		SupportTeamImpl supportTeamImpl = new SupportTeamImpl();

		supportTeamImpl.setSupportTeamId(supportTeamId);
		supportTeamImpl.setUserId(userId);

		if (userName == null) {
			supportTeamImpl.setUserName(StringPool.BLANK);
		}
		else {
			supportTeamImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			supportTeamImpl.setCreateDate(null);
		}
		else {
			supportTeamImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			supportTeamImpl.setModifiedDate(null);
		}
		else {
			supportTeamImpl.setModifiedDate(new Date(modifiedDate));
		}

		supportTeamImpl.setParentSupportTeamId(parentSupportTeamId);
		supportTeamImpl.setSupportLaborId(supportLaborId);
		supportTeamImpl.setLocationSupportRegionId(locationSupportRegionId);

		if (name == null) {
			supportTeamImpl.setName(StringPool.BLANK);
		}
		else {
			supportTeamImpl.setName(name);
		}

		if (description == null) {
			supportTeamImpl.setDescription(StringPool.BLANK);
		}
		else {
			supportTeamImpl.setDescription(description);
		}

		supportTeamImpl.setType(type);
		supportTeamImpl.setAssignedWork(assignedWork);
		supportTeamImpl.setMaxWork(maxWork);

		supportTeamImpl.resetOriginalValues();

		return supportTeamImpl;
	}

	public long supportTeamId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentSupportTeamId;
	public long supportLaborId;
	public long locationSupportRegionId;
	public String name;
	public String description;
	public int type;
	public double assignedWork;
	public double maxWork;
}