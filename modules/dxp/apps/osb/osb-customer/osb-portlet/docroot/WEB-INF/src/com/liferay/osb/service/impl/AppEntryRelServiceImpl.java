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

import com.liferay.osb.model.AppEntryRel;
import com.liferay.osb.service.base.AppEntryRelServiceBaseImpl;
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
public class AppEntryRelServiceImpl extends AppEntryRelServiceBaseImpl {

	public AppEntryRel addAppEntryRel(
			long appEntryId1, long appEntryId2, int type)
		throws PortalException, SystemException {

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryId1, OSBActionKeys.UPDATE);
		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryId2, OSBActionKeys.UPDATE);

		return appEntryRelLocalService.addAppEntryRel(
			appEntryId1, appEntryId2, type);
	}

	public AppEntryRel deleteAppEntryRel(long appEntryRelId)
		throws PortalException, SystemException {

		AppEntryRel appEntryRel = appEntryRelPersistence.findByPrimaryKey(
			appEntryRelId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryRel.getAppEntryId1(),
			OSBActionKeys.UPDATE);
		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryRel.getAppEntryId2(),
			OSBActionKeys.UPDATE);

		return appEntryRelLocalService.deleteAppEntryRel(appEntryRelId);
	}

}