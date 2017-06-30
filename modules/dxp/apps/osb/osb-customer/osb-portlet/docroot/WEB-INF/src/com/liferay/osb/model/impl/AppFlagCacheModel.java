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

import com.liferay.osb.model.AppFlag;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppFlag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppFlag
 * @generated
 */
public class AppFlagCacheModel implements CacheModel<AppFlag>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", appFlagId=");
		sb.append(appFlagId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", appVersionId=");
		sb.append(appVersionId);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public AppFlag toEntityModel() {
		AppFlagImpl appFlagImpl = new AppFlagImpl();

		if (uuid == null) {
			appFlagImpl.setUuid(StringPool.BLANK);
		}
		else {
			appFlagImpl.setUuid(uuid);
		}

		appFlagImpl.setAppFlagId(appFlagId);

		if (createDate == Long.MIN_VALUE) {
			appFlagImpl.setCreateDate(null);
		}
		else {
			appFlagImpl.setCreateDate(new Date(createDate));
		}

		appFlagImpl.setAppEntryId(appEntryId);
		appFlagImpl.setAppVersionId(appVersionId);
		appFlagImpl.setType(type);

		appFlagImpl.resetOriginalValues();

		return appFlagImpl;
	}

	public String uuid;
	public long appFlagId;
	public long createDate;
	public long appEntryId;
	public long appVersionId;
	public int type;
}