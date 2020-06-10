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