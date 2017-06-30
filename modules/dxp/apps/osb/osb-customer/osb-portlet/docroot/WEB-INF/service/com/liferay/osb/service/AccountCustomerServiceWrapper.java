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
 * This class is a wrapper for {@link AccountCustomerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountCustomerService
 * @generated
 */
public class AccountCustomerServiceWrapper implements AccountCustomerService,
	ServiceWrapper<AccountCustomerService> {
	public AccountCustomerServiceWrapper(
		AccountCustomerService accountCustomerService) {
		_accountCustomerService = accountCustomerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountCustomerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountCustomerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountCustomerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addAccountCustomers(long[] userIds, long accountEntryId,
		int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerService.addAccountCustomers(userIds, accountEntryId,
			roles, notifications);
	}

	public void deleteAccountCustomers(long[] userIds, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerService.deleteAccountCustomers(userIds, accountEntryId);
	}

	public java.util.List<java.lang.String> getCorpProjectAccountCustomerUUIDs(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerService.getCorpProjectAccountCustomerUUIDs(corpProjectId);
	}

	public void toggleNotifications(long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerService.toggleNotifications(accountCustomerId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountCustomerService getWrappedAccountCustomerService() {
		return _accountCustomerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountCustomerService(
		AccountCustomerService accountCustomerService) {
		_accountCustomerService = accountCustomerService;
	}

	public AccountCustomerService getWrappedService() {
		return _accountCustomerService;
	}

	public void setWrappedService(AccountCustomerService accountCustomerService) {
		_accountCustomerService = accountCustomerService;
	}

	private AccountCustomerService _accountCustomerService;
}