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