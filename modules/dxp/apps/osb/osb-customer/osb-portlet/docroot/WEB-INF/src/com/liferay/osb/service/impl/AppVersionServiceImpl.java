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

import com.liferay.osb.model.AppVersion;
import com.liferay.osb.service.base.AppVersionServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Ryan Park
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AppVersionServiceImpl extends AppVersionServiceBaseImpl {

	public AppVersion deleteAppVersion(long appVersionId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.DELETE);

		return appVersionLocalService.deleteAppVersion(appVersion);
	}

	public AppVersion getAppVersion(long appVersionId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.VIEW);

		return appVersion;
	}

}