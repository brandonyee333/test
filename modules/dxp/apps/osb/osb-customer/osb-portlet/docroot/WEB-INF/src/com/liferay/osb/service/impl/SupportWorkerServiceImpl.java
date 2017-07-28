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

import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.service.base.SupportWorkerServiceBaseImpl;
import com.liferay.osb.service.permission.OSBSupportTeamPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Brian Wing Shun Chan
 * @author Brent Krone-Schmidt
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class SupportWorkerServiceImpl extends SupportWorkerServiceBaseImpl {

	public void clockInOut(long supportWorkerId) throws PortalException {
		SupportWorker supportWorker =
			supportWorkerLocalService.getSupportWorker(supportWorkerId);

		OSBSupportTeamPermission.check(
			getPermissionChecker(), supportWorker.getSupportTeam());

		supportWorkerLocalService.clockInOut(supportWorkerId);
	}

}