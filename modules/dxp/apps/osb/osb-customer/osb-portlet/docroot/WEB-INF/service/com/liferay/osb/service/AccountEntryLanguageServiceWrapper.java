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
 * This class is a wrapper for {@link AccountEntryLanguageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEntryLanguageService
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
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountEntryLanguageService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountEntryLanguageService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEntryLanguageService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountEntryLanguageService getWrappedAccountEntryLanguageService() {
		return _accountEntryLanguageService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountEntryLanguageService(
		AccountEntryLanguageService accountEntryLanguageService) {
		_accountEntryLanguageService = accountEntryLanguageService;
	}

	public AccountEntryLanguageService getWrappedService() {
		return _accountEntryLanguageService;
	}

	public void setWrappedService(
		AccountEntryLanguageService accountEntryLanguageService) {
		_accountEntryLanguageService = accountEntryLanguageService;
	}

	private AccountEntryLanguageService _accountEntryLanguageService;
}