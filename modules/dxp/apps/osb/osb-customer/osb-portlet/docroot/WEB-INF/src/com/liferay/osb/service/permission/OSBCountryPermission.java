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

import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Alan Zhang
 */
public class OSBCountryPermission {

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws SystemException {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		if (actionId.equals(OSBActionKeys.DELETE)) {
			return false;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.UPDATE)) {
			return false;
		}

		if (actionId.equals(OSBActionKeys.VIEW)) {
			return true;
		}

		return false;
	}

}