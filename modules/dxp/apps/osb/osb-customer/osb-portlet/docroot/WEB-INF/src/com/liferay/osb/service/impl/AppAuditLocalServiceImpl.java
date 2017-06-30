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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AppAudit;
import com.liferay.osb.service.base.AppAuditLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Haote Chou
 */
public class AppAuditLocalServiceImpl extends AppAuditLocalServiceBaseImpl {

	public void addAppAudit(
			long userId, long appEntryId, long appVersionId, int status)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long appAuditId = counterLocalService.increment();

		AppAudit appAudit = appAuditPersistence.create(appAuditId);

		appAudit.setUserId(user.getUserId());
		appAudit.setUserName(user.getFullName());
		appAudit.setCreateDate(now);
		appAudit.setAppEntryId(appEntryId);
		appAudit.setAppVersionId(appVersionId);
		appAudit.setStatus(status);

		appAuditPersistence.update(appAudit, false);
	}

	public List<AppAudit> getAppAudits(
			long appVersionId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return appAuditPersistence.findByAppVersionId(
			appVersionId, start, end, obc);
	}

}