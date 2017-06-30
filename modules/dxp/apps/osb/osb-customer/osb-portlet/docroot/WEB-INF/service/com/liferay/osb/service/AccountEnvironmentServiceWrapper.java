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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AccountEnvironmentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEnvironmentService
 * @generated
 */
public class AccountEnvironmentServiceWrapper
	implements AccountEnvironmentService,
		ServiceWrapper<AccountEnvironmentService> {
	public AccountEnvironmentServiceWrapper(
		AccountEnvironmentService accountEnvironmentService) {
		_accountEnvironmentService = accountEnvironmentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountEnvironmentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountEnvironmentService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEnvironmentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		long accountEntryId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentService.addAccountEnvironment(accountEntryId,
			productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
			envLFR, files, types);
	}

	public com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentService.deleteAccountEnvironment(accountEnvironmentId);
	}

	public com.liferay.osb.model.AccountEnvironment getAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentService.getAccountEnvironment(accountEnvironmentId);
	}

	public java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentService.getAccountEnvironments(accountEntryId);
	}

	public java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>> getAccountEnvironmentsMap(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentService.getAccountEnvironmentsMap(accountEntryId);
	}

	public com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		long accountEnvironmentId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentService.updateAccountEnvironment(accountEnvironmentId,
			productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
			envLFR, files, types);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountEnvironmentService getWrappedAccountEnvironmentService() {
		return _accountEnvironmentService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountEnvironmentService(
		AccountEnvironmentService accountEnvironmentService) {
		_accountEnvironmentService = accountEnvironmentService;
	}

	public AccountEnvironmentService getWrappedService() {
		return _accountEnvironmentService;
	}

	public void setWrappedService(
		AccountEnvironmentService accountEnvironmentService) {
		_accountEnvironmentService = accountEnvironmentService;
	}

	private AccountEnvironmentService _accountEnvironmentService;
}