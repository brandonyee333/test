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
 * This class is a wrapper for {@link AccountLinkService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountLinkService
 * @generated
 */
public class AccountLinkServiceWrapper implements AccountLinkService,
	ServiceWrapper<AccountLinkService> {
	public AccountLinkServiceWrapper(AccountLinkService accountLinkService) {
		_accountLinkService = accountLinkService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountLinkService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountLinkService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountLinkService.invokeMethod(name, parameterTypes, arguments);
	}

	public void addAccountLinks(long accountEntryId, java.lang.String[] urls)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountLinkService.addAccountLinks(accountEntryId, urls);
	}

	public com.liferay.osb.model.AccountLink deleteAccountLink(
		long accountLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLinkService.deleteAccountLink(accountLinkId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountLinkService getWrappedAccountLinkService() {
		return _accountLinkService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountLinkService(
		AccountLinkService accountLinkService) {
		_accountLinkService = accountLinkService;
	}

	public AccountLinkService getWrappedService() {
		return _accountLinkService;
	}

	public void setWrappedService(AccountLinkService accountLinkService) {
		_accountLinkService = accountLinkService;
	}

	private AccountLinkService _accountLinkService;
}