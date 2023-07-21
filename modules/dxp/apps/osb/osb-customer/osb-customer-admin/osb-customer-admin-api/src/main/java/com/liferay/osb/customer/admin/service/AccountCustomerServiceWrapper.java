/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountCustomerService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerService
 * @generated
 */
public class AccountCustomerServiceWrapper
	implements AccountCustomerService, ServiceWrapper<AccountCustomerService> {

	public AccountCustomerServiceWrapper(
		AccountCustomerService accountCustomerService) {

		_accountCustomerService = accountCustomerService;
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
			getCorpProjectAccountCustomerUsers(String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountCustomerService.getCorpProjectAccountCustomerUsers(
			corpProjectUuid);
	}

	@Override
	public java.util.List<String> getCorpProjectAccountCustomerUUIDs(
			String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountCustomerService.getCorpProjectAccountCustomerUUIDs(
			corpProjectUuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountCustomerService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountCustomerService getWrappedService() {
		return _accountCustomerService;
	}

	@Override
	public void setWrappedService(
		AccountCustomerService accountCustomerService) {

		_accountCustomerService = accountCustomerService;
	}

	private AccountCustomerService _accountCustomerService;

}