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

import com.liferay.osb.model.AppFlag;
import com.liferay.osb.service.base.AppFlagServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Enoch Chu
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AppFlagServiceImpl extends AppFlagServiceBaseImpl {

	public AppFlag addAppFlag(long appEntryId, long appVersionId, int type)
		throws PortalException, SystemException {

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryId, OSBActionKeys.UPDATE_APP_FLAG);

		return appFlagLocalService.addAppFlag(appEntryId, appVersionId, type);
	}

	public AppFlag deleteAppFlag(long appFlagId)
		throws PortalException, SystemException {

		AppFlag appFlag = appFlagLocalService.getAppFlag(appFlagId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appFlag.getAppEntryId(),
			OSBActionKeys.UPDATE_APP_FLAG);

		return appFlagLocalService.deleteAppFlag(appFlagId);
	}

}