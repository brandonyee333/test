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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.service.base.AccountEnvironmentServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEnvironmentPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.io.File;

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
			int envBrowser, int envCS, String envSearch,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException {

		OSBAccountEnvironmentPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT);

		return accountEnvironmentLocalService.addAccountEnvironment(
			getUserId(), accountEntryId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envBrowser, envCS,
			envSearch, files, types);
	}

	public AccountEnvironment deleteAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentId);

		OSBAccountEnvironmentPermission.check(
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

		OSBAccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.VIEW);

		return accountEnvironment;
	}

	public List<AccountEnvironment> getAccountEnvironments(long accountEntryId)
		throws PortalException {

		OSBAccountEnvironmentPermission.check(
			getPermissionChecker(), accountEntryId, OSBActionKeys.VIEW);

		return accountEnvironmentLocalService.getAccountEnvironments(
			accountEntryId);
	}

	public Map<String, List<AccountEnvironment>> getAccountEnvironmentsMap(
			long accountEntryId)
		throws PortalException {

		OSBAccountEnvironmentPermission.check(
			getPermissionChecker(), accountEntryId, OSBActionKeys.VIEW);

		return accountEnvironmentLocalService.getAccountEnvironmentsMap(
			accountEntryId);
	}

	public AccountEnvironment updateAccountEnvironment(
			long accountEnvironmentId, long productEntryId, String name,
			int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
			int envLFR, int envBrowser, int envCS, String envSearch,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentId);

		OSBAccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.UPDATE);

		return accountEnvironmentLocalService.updateAccountEnvironment(
			getUserId(), accountEnvironmentId, productEntryId, name, envOS,
			envOSCustom, envDB, envJVM, envAS, envLFR, envBrowser, envCS,
			envSearch, files, types);
	}

}