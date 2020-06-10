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

package com.liferay.osb.customer.koroneiki.service.internal.permission;

import com.liferay.osb.customer.koroneiki.service.permission.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = AccountPermission.class)
public class AccountPermissionImpl implements AccountPermission {

	public void check(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, account, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, actionId);
		}
	}

	public boolean contains(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		return true;
	}

}