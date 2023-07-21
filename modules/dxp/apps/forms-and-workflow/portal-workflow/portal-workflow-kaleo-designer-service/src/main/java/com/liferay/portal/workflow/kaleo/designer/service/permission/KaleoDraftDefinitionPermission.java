/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalServiceUtil;

/**
 * @author Marcellus Tavares
 */
public class KaleoDraftDefinitionPermission {

	public static void check(
			PermissionChecker permissionChecker,
			KaleoDraftDefinition kaleoDraftDefinition, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kaleoDraftDefinition, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kaleoDraftDefinitionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kaleoDraftDefinitionId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker,
		KaleoDraftDefinition kaleoDraftDefinition, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				kaleoDraftDefinition.getCompanyId(),
				KaleoDraftDefinition.class.getName(),
				kaleoDraftDefinition.getKaleoDraftDefinitionId(),
				kaleoDraftDefinition.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kaleoDraftDefinition.getGroupId(),
			KaleoDraftDefinition.class.getName(),
			kaleoDraftDefinition.getKaleoDraftDefinitionId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kaleoDraftDefinitionId,
			String actionId)
		throws PortalException {

		KaleoDraftDefinition kaleoDraftDefinition =
			KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinition(
				kaleoDraftDefinitionId);

		return contains(permissionChecker, kaleoDraftDefinition, actionId);
	}

}