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

import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;

/**
 * @author Ryan Park
 * @author Douglas Wong
 */
public class OSBMarketplacePermission {

	public static void check(
			PermissionChecker permissionChecker, long developerEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, developerEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long developerEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.ADD_APP)) {
			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(
					developerEntryId);

			if (developerEntry == null) {
				return false;
			}

			if (!developerEntry.isApproved()) {
				return false;
			}

			if (developerEntry.isCompany()) {
				CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
					developerEntry.getDossieraAccountKey());

				Group group = corpEntry.getGroup();

				if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
						permissionChecker.getUserId(), group.getGroupId(),
						OSBConstants.ROLE_OSB_CORP_ADMIN_ID)) {

					return true;
				}

				if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
						permissionChecker.getUserId(), group.getGroupId(),
						OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID)) {

					return true;
				}

				return false;
			}
			else if (developerEntry.isUser()) {
				return true;
			}
		}
		else if (actionId.equals(OSBActionKeys.ADD_DEVELOPER_ENTRY)) {
			if (permissionChecker.isSignedIn()) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return contains(permissionChecker, 0, actionId);
	}

	public static boolean hasEEPermissions(long userId) throws SystemException {
		if (OrganizationLocalServiceUtil.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_CUSTOMER_ID) ||
			OrganizationLocalServiceUtil.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID) ||
			OrganizationLocalServiceUtil.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

			return true;
		}

		return false;
	}

}