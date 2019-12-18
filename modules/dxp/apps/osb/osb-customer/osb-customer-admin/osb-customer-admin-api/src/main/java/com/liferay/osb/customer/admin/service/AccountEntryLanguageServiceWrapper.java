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
 * Provides a wrapper for {@link AccountEntryLanguageService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguageService
 * @generated
 */
public class AccountEntryLanguageServiceWrapper
	implements AccountEntryLanguageService,
			   ServiceWrapper<AccountEntryLanguageService> {

	public AccountEntryLanguageServiceWrapper(
		AccountEntryLanguageService accountEntryLanguageService) {

		_accountEntryLanguageService = accountEntryLanguageService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountEntryLanguageService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountEntryLanguageService getWrappedService() {
		return _accountEntryLanguageService;
	}

	@Override
	public void setWrappedService(
		AccountEntryLanguageService accountEntryLanguageService) {

		_accountEntryLanguageService = accountEntryLanguageService;
	}

	private AccountEntryLanguageService _accountEntryLanguageService;

}