/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.service.permission;

import com.liferay.osb.loop.token.auth.constants.ActionKeys;
import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.osb.loop.token.auth.service.TokenAuthEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Farache
 */
@Component(immediate = true, service = TokenAuthPermission.class)
public class TokenAuthPermission {

	public static void check(
			PermissionChecker permissionChecker, long tokenAuthEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tokenAuthEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long tokenAuthEntryId,
			String actionId)
		throws PortalException {

		TokenAuthEntry authToken =
			TokenAuthEntryLocalServiceUtil.getTokenAuthEntry(tokenAuthEntryId);

		return contains(permissionChecker, authToken, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionId) {

		return permissionChecker.hasPermission(0, _RESOURCE_NAME, 0, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, TokenAuthEntry tokenAuthEntry,
		String actionId) {

		if (actionId.equals(ActionKeys.DELETE)) {
			if (contains(permissionChecker, ActionKeys.MANAGE_TOKENS)) {
				return true;
			}

			boolean hasOwnerPermission = permissionChecker.hasOwnerPermission(
				tokenAuthEntry.getCompanyId(), TokenAuthEntry.class.getName(),
				tokenAuthEntry.getTokenAuthEntryId(),
				tokenAuthEntry.getUserId(), actionId);

			if (hasOwnerPermission) {
				return true;
			}
		}

		return permissionChecker.hasPermission(0, _RESOURCE_NAME, 0, actionId);
	}

	private static final String _RESOURCE_NAME =
		"com.liferay.osb.loop.token.auth";

}