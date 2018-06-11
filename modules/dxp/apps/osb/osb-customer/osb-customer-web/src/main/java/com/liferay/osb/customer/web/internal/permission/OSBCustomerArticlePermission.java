/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
				OSBCustomerConstants.RESOURCE_NAME_KNOWLEDGE_DISPLAY,
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID, actionId);
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