/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.osb.customer.admin.service.base.AccountEnvironmentServiceBaseImpl;
import com.liferay.osb.customer.admin.service.permission.AccountEnvironmentPermission;
import com.liferay.osb.customer.constants.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.util.List;
import java.util.Map;

/**
 * @author Lin Cui
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountEnvironmentServiceImpl
	extends AccountEnvironmentServiceBaseImpl {

	public AccountEnvironment addAccountEnvironment(
			long accountEntryId, long productEntryId, String name, int envOS,
			String envOSCustom, int envDB, int envJVM, int envAS, int envLFR,
			int envCommerce, int envBrowser, int envCS, String envSearch)
		throws PortalException {

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT);

		return accountEnvironmentLocalService.addAccountEnvironment(
			getUserId(), accountEntryId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
			envCS, envSearch);
	}

	public AccountEnvironment deleteAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentId);

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.DELETE);

		return accountEnvironmentLocalService.deleteAccountEnvironment(
			accountEnvironmentId);
	}

	public AccountEnvironment getAccountEnvironment(long accountEnvironmentId)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentId);

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.VIEW);

		return accountEnvironment;
	}

	public List<AccountEnvironment> getAccountEnvironments(long accountEntryId)
		throws PortalException {

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEntryId, OSBActionKeys.VIEW);

		return accountEnvironmentLocalService.getAccountEnvironments(
			accountEntryId);
	}

	public Map<String, List<AccountEnvironment>> getAccountEnvironmentsMap(
			long accountEntryId)
		throws PortalException {

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEntryId, OSBActionKeys.VIEW);

		return accountEnvironmentLocalService.getAccountEnvironmentsMap(
			accountEntryId);
	}

	public AccountEnvironment updateAccountEnvironment(
			long accountEnvironmentId, long productEntryId, String name,
			int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
			int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentId);

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.UPDATE);

		return accountEnvironmentLocalService.updateAccountEnvironment(
			getUserId(), accountEnvironmentId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
			envCS, envSearch);
	}

}