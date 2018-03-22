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
 * Provides a wrapper for {@link AccountProjectService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountProjectService
 * @generated
 */
@ProviderType
public class AccountProjectServiceWrapper implements AccountProjectService,
	ServiceWrapper<AccountProjectService> {
	public AccountProjectServiceWrapper(
		AccountProjectService accountProjectService) {
		_accountProjectService = accountProjectService;
	}

	@Override
	public com.liferay.osb.model.AccountProject deleteAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectService.deleteAccountProject(accountProjectId);
	}

	@Override
	public com.liferay.osb.model.AccountProject updateAccountProject(
		long accountProjectId, long accountEntryId, java.lang.String name,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectService.updateAccountProject(accountProjectId,
			accountEntryId, name, data);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountProjectService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountProjectService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountProjectService getWrappedService() {
		return _accountProjectService;
	}

	@Override
	public void setWrappedService(AccountProjectService accountProjectService) {
		_accountProjectService = accountProjectService;
	}

	private AccountProjectService _accountProjectService;
}