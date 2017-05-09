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

package com.liferay.osb.customer.web.internal.permission;

import com.liferay.osb.customer.constants.OSBCustomerActionKeys;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Alan Zhang
 */
public class OSBCustomerArticlePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (PortalUtil.isOmniadmin(permissionChecker.getUserId())) {
			return true;
		}

		if (actionId.equals(OSBCustomerActionKeys.VIEW_DASHBOARD)) {
			return false;
		}

		if (actionId.equals(OSBCustomerActionKeys.SUBMIT_ARTICLE) ||
			actionId.equals(
				OSBCustomerActionKeys.VIEW_JOURNAL_ARTICLE_ADVANCED)) {

			Role role = RoleLocalServiceUtil.getRole(
				OSBCustomerConstants.ROLE_LIFERAY_EMPLOYEE_ID);

			if (RoleServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					permissionChecker.getCompanyId(), role.getName(), true)) {

				return true;
			}

			return false;
		}

		if (actionId.equals(OSBCustomerActionKeys.VIEW_JOURNAL_ARTICLE)) {
			return permissionChecker.hasPermission(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID,
				OSBCustomerConstants.RESOURCE_NAME_KNOWLEDGE_DISPLAY, 0,
				actionId);
		}

		return false;
	}

	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException();
		}
	}

}