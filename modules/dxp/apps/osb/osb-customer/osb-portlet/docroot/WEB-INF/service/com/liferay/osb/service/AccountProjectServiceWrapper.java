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
 * This class is a wrapper for {@link AccountProjectService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountProjectService
 * @generated
 */
public class AccountProjectServiceWrapper implements AccountProjectService,
	ServiceWrapper<AccountProjectService> {
	public AccountProjectServiceWrapper(
		AccountProjectService accountProjectService) {
		_accountProjectService = accountProjectService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountProjectService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountProjectService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountProjectService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AccountProject deleteAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectService.deleteAccountProject(accountProjectId);
	}

	public com.liferay.osb.model.AccountProject updateAccountProject(
		long accountProjectId, long accountEntryId, java.lang.String name,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectService.updateAccountProject(accountProjectId,
			accountEntryId, name, data);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountProjectService getWrappedAccountProjectService() {
		return _accountProjectService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountProjectService(
		AccountProjectService accountProjectService) {
		_accountProjectService = accountProjectService;
	}

	public AccountProjectService getWrappedService() {
		return _accountProjectService;
	}

	public void setWrappedService(AccountProjectService accountProjectService) {
		_accountProjectService = accountProjectService;
	}

	private AccountProjectService _accountProjectService;
}