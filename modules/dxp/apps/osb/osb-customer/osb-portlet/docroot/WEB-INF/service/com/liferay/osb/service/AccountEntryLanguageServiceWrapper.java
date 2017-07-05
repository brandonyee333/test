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
 * Provides a wrapper for {@link AccountEntryLanguageService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguageService
 * @generated
 */
@ProviderType
public class AccountEntryLanguageServiceWrapper
	implements AccountEntryLanguageService,
		ServiceWrapper<AccountEntryLanguageService> {
	public AccountEntryLanguageServiceWrapper(
		AccountEntryLanguageService accountEntryLanguageService) {
		_accountEntryLanguageService = accountEntryLanguageService;
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEntryLanguageService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
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