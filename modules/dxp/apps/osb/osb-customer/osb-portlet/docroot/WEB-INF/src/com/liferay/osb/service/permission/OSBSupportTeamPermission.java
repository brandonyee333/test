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

package com.liferay.osb.service.permission;

import com.liferay.osb.NoSuchSupportWorkerException;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Brent Krone-Schmidt
 */
public class OSBSupportTeamPermission {

	public static void check(
			PermissionChecker permissionChecker, SupportTeam supportTeam)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, supportTeam)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, SupportTeam supportTeam)
		throws PortalException, SystemException {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		try {
			SupportWorker supportWorker =
				SupportWorkerLocalServiceUtil.getSupportWorker(
					permissionChecker.getUserId(),
					supportTeam.getSupportTeamId());

			if (supportWorker.getRole() ==
					SupportWorkerConstants.ROLE_MANAGER) {

				return true;
			}
		}
		catch (NoSuchSupportWorkerException nsswe) {
		}

		return false;
	}

}