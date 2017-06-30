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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;

/**
 * @author Joan Kim
 */
public class OSBCorpProjectPermission {

	public static void check(
			PermissionChecker permissionChecker, CorpProject corpProject,
			long[] userIds, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, corpProject, userIds, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, CorpProject corpProject,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, corpProject, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long corpProjectId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, corpProjectId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, CorpProject corpProject,
			long[] userIds, String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_CORP_PROJECT_ADMIN_ID) ||
			RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.DELETE_CORP_PROJECT) ||
			actionId.equals(OSBActionKeys.MANAGE)) {

			return false;
		}

		if (actionId.equals(OSBActionKeys.VIEW)) {
			return true;
		}

		Group group = corpProject.getGroup();

		if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
				permissionChecker.getUserId(), group.getGroupId(),
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID)) {

			return true;
		}

		if (!RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_VERIFIED_USER_ID)) {

			return false;
		}

		if (actionId.equals(OSBActionKeys.ASSIGN_MEMBERS)) {
			if ((userIds.length == 1) &&
				(permissionChecker.getUserId() == userIds[0])) {

				return true;
			}
		}
		else if (actionId.equals(OSBActionKeys.PURCHASE_ASSET)) {
			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
					permissionChecker.getUserId(), group.getGroupId(),
					OSBConstants.ROLE_OSB_CORP_BUYER_ID)) {

				return true;
			}
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, CorpProject corpProject,
			String actionId)
		throws PortalException, SystemException {

		return contains(permissionChecker, corpProject, new long[0], actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long corpProjectId,
			String actionId)
		throws PortalException, SystemException {

		CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(
			corpProjectId);

		return contains(permissionChecker, corpProject, actionId);
	}

}