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

import com.liferay.osb.model.AppEntryRel;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AppEntryRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryRel
 * @generated
 */
public class AppEntryRelCacheModel implements CacheModel<AppEntryRel>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", appEntryRelId=");
		sb.append(appEntryRelId);
		sb.append(", appEntryId1=");
		sb.append(appEntryId1);
		sb.append(", appEntryId2=");
		sb.append(appEntryId2);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public AppEntryRel toEntityModel() {
		AppEntryRelImpl appEntryRelImpl = new AppEntryRelImpl();

		if (uuid == null) {
			appEntryRelImpl.setUuid(StringPool.BLANK);
		}
		else {
			appEntryRelImpl.setUuid(uuid);
		}

		appEntryRelImpl.setAppEntryRelId(appEntryRelId);
		appEntryRelImpl.setAppEntryId1(appEntryId1);
		appEntryRelImpl.setAppEntryId2(appEntryId2);
		appEntryRelImpl.setType(type);

		appEntryRelImpl.resetOriginalValues();

		return appEntryRelImpl;
	}

	public String uuid;
	public long appEntryRelId;
	public long appEntryId1;
	public long appEntryId2;
	public int type;
}