/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.service.internal;

import com.liferay.osb.customer.koroneiki.service.AccountService;
import com.liferay.osb.customer.koroneiki.service.permission.AccountPermission;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.BaseServiceImpl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = AccountService.class)
public class AccountServiceImpl
	extends BaseServiceImpl implements AccountService {

	public Account getAccount(String accountKey) throws Exception {
		Account account = _accountWebService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return account;
	}

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private AccountWebService _accountWebService;

}