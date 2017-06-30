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
 * This class is a wrapper for {@link AccountInformationService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountInformationService
 * @generated
 */
public class AccountInformationServiceWrapper
	implements AccountInformationService,
		ServiceWrapper<AccountInformationService> {
	public AccountInformationServiceWrapper(
		AccountInformationService accountInformationService) {
		_accountInformationService = accountInformationService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountInformationService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountInformationService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountInformationService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.AccountInformation> updateAccountInformation(
		long accountEntryId, long accountProjectId,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationService.updateAccountInformation(accountEntryId,
			accountProjectId, data);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountInformationService getWrappedAccountInformationService() {
		return _accountInformationService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountInformationService(
		AccountInformationService accountInformationService) {
		_accountInformationService = accountInformationService;
	}

	public AccountInformationService getWrappedService() {
		return _accountInformationService;
	}

	public void setWrappedService(
		AccountInformationService accountInformationService) {
		_accountInformationService = accountInformationService;
	}

	private AccountInformationService _accountInformationService;
}