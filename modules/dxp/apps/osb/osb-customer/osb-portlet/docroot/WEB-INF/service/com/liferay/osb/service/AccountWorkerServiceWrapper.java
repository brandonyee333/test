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
 * Provides a wrapper for {@link AccountWorkerService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerService
 * @generated
 */
@ProviderType
public class AccountWorkerServiceWrapper implements AccountWorkerService,
	ServiceWrapper<AccountWorkerService> {
	public AccountWorkerServiceWrapper(
		AccountWorkerService accountWorkerService) {
		_accountWorkerService = accountWorkerService;
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountWorkerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountWorkerService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountWorkerService getWrappedService() {
		return _accountWorkerService;
	}

	@Override
	public void setWrappedService(AccountWorkerService accountWorkerService) {
		_accountWorkerService = accountWorkerService;
	}

	private AccountWorkerService _accountWorkerService;
}