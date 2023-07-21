/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service.permission;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.service.KBTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 * @author Roberto Díaz
 */
@Component(
	property = "model.class.name=com.liferay.knowledge.base.model.KBTemplate",
	service = BaseModelPermissionChecker.class
)
public class KBTemplatePermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, KBTemplate kbTemplate,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kbTemplate, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kbTemplateId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kbTemplateId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, KBTemplate kbTemplate,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				kbTemplate.getCompanyId(), KBTemplate.class.getName(),
				kbTemplate.getKbTemplateId(), kbTemplate.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kbTemplate.getGroupId(), KBTemplate.class.getName(),
			kbTemplate.getKbTemplateId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kbTemplateId,
			String actionId)
		throws PortalException {

		KBTemplate kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
			kbTemplateId);

		return contains(permissionChecker, kbTemplate, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

}