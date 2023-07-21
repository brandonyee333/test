/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.service.access.policy.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	property = "model.class.name=com.liferay.portal.security.service.access.policy.model.SAPEntry"
)
public class SAPEntryPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long sapEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, sapEntryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, SAPEntry.class.getName(), sapEntryId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, SAPEntry sapEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, sapEntry, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, SAPEntry.class.getName(),
				sapEntry.getSapEntryId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException {

		SAPEntry sapEntry = _sapEntryLocalService.getSAPEntry(classPK);

		return contains(permissionChecker, sapEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, SAPEntry sapEntry,
		String actionId) {

		return permissionChecker.hasPermission(
			null, SAPEntry.class.getName(), sapEntry.getSapEntryId(), actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setSAPEntryLocalService(
		SAPEntryLocalService sapEntryLocalService) {

		_sapEntryLocalService = sapEntryLocalService;
	}

	private static SAPEntryLocalService _sapEntryLocalService;

}