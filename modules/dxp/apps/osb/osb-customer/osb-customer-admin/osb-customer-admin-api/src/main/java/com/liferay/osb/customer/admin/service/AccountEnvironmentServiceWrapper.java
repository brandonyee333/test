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

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEnvironmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentService
 * @generated
 */
public class AccountEnvironmentServiceWrapper
	implements AccountEnvironmentService,
			   ServiceWrapper<AccountEnvironmentService> {

	public AccountEnvironmentServiceWrapper(
		AccountEnvironmentService accountEnvironmentService) {

		_accountEnvironmentService = accountEnvironmentService;
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			addAccountEnvironment(
				long accountEntryId, long productEntryId, String name,
				int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
				int envLFR, int envCommerce, int envBrowser, int envCS,
				String envSearch)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentService.addAccountEnvironment(
			accountEntryId, productEntryId, name, envOS, envOSCustom, envDB,
			envJVM, envAS, envLFR, envCommerce, envBrowser, envCS, envSearch);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			deleteAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentService.deleteAccountEnvironment(
			accountEnvironmentId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			getAccountEnvironment(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentService.getAccountEnvironment(
			accountEnvironmentId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
				getAccountEnvironments(long accountEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentService.getAccountEnvironments(
			accountEntryId);
	}

	@Override
	public java.util.Map
		<String,
		 java.util.List
			 <com.liferay.osb.customer.admin.model.AccountEnvironment>>
					getAccountEnvironmentsMap(long accountEntryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentService.getAccountEnvironmentsMap(
			accountEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountEnvironmentService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironment
			updateAccountEnvironment(
				long accountEnvironmentId, long productEntryId, String name,
				int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
				int envLFR, int envCommerce, int envBrowser, int envCS,
				String envSearch)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentService.updateAccountEnvironment(
			accountEnvironmentId, productEntryId, name, envOS, envOSCustom,
			envDB, envJVM, envAS, envLFR, envCommerce, envBrowser, envCS,
			envSearch);
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