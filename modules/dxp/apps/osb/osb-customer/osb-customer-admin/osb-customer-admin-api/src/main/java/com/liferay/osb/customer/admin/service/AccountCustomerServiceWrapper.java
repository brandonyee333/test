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