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

import com.liferay.osb.model.AppAudit;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppAudit in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppAudit
 * @generated
 */
public class AppAuditCacheModel implements CacheModel<AppAudit>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", appAuditId=");
		sb.append(appAuditId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", appVersionId=");
		sb.append(appVersionId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public AppAudit toEntityModel() {
		AppAuditImpl appAuditImpl = new AppAuditImpl();

		if (uuid == null) {
			appAuditImpl.setUuid(StringPool.BLANK);
		}
		else {
			appAuditImpl.setUuid(uuid);
		}

		appAuditImpl.setAppAuditId(appAuditId);
		appAuditImpl.setUserId(userId);

		if (userName == null) {
			appAuditImpl.setUserName(StringPool.BLANK);
		}
		else {
			appAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			appAuditImpl.setCreateDate(null);
		}
		else {
			appAuditImpl.setCreateDate(new Date(createDate));
		}

		appAuditImpl.setAppEntryId(appEntryId);
		appAuditImpl.setAppVersionId(appVersionId);
		appAuditImpl.setStatus(status);

		appAuditImpl.resetOriginalValues();

		return appAuditImpl;
	}

	public String uuid;
	public long appAuditId;
	public long userId;
	public String userName;
	public long createDate;
	public long appEntryId;
	public long appVersionId;
	public int status;
}