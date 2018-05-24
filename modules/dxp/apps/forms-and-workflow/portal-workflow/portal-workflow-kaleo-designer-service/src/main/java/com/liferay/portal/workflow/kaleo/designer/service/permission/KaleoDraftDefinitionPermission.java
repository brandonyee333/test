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