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

package com.liferay.osb.community.doc.project.internal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Haote Chou
 */
public class DocProjectPermission {

	public static final String RESOURCE_NAME =
		"com.liferay.osb.community.doc.project";

	public static void check(
			PermissionChecker permissionChecker, String actionKey)
		throws PortalException {

		if (!contains(permissionChecker, actionKey)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, 0, actionKey);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionKey) {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		return false;
	}

}