/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEnvironmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentService
 * @generated
 */
@ProviderType
public class AccountEnvironmentServiceWrapper
	implements AccountEnvironmentService,
		ServiceWrapper<AccountEnvironmentService> {
	public AccountEnvironmentServiceWrapper(
		AccountEnvironmentService accountEnvironmentService) {
		_accountEnvironmentService = accountEnvironmentService;
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		long accountEntryId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentService.addAccountEnvironment(accountEntryId,
			productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
			envLFR, envCommerce, envBrowser, envCS, envSearch, files, types);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentService.deleteAccountEnvironment(accountEnvironmentId);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment getAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentService.getAccountEnvironment(accountEnvironmentId);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		long accountEnvironmentId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentService.updateAccountEnvironment(accountEnvironmentId,
			productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
			envLFR, envCommerce, envBrowser, envCS, envSearch, files, types);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEnvironmentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountEnvironmentService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentService.getAccountEnvironments(accountEntryId);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>> getAccountEnvironmentsMap(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentService.getAccountEnvironmentsMap(accountEntryId);
	}

	@Override
	public AccountEnvironmentService getWrappedService() {
		return _accountEnvironmentService;
	}

	@Override
	public void setWrappedService(
		AccountEnvironmentService accountEnvironmentService) {
		_accountEnvironmentService = accountEnvironmentService;
	}

	private AccountEnvironmentService _accountEnvironmentService;
}