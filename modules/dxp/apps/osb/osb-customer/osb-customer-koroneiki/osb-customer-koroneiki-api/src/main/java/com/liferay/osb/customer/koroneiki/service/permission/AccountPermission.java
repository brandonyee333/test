/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.service.permission;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Amos Fong
 */
public interface AccountPermission {

	public void check(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, String koroneikiAccountKey,
			String actionId)
		throws PortalException;

}