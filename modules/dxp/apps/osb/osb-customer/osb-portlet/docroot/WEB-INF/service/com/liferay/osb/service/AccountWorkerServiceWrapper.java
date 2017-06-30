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
 * This class is a wrapper for {@link AccountWorkerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountWorkerService
 * @generated
 */
public class AccountWorkerServiceWrapper implements AccountWorkerService,
	ServiceWrapper<AccountWorkerService> {
	public AccountWorkerServiceWrapper(
		AccountWorkerService accountWorkerService) {
		_accountWorkerService = accountWorkerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountWorkerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountWorkerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountWorkerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addAccountWorkers(long[] userIds, long accountEntryId,
		int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountWorkerService.addAccountWorkers(userIds, accountEntryId, roles,
			notifications);
	}

	public void deleteAccountWorkers(long[] userIds, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountWorkerService.deleteAccountWorkers(userIds, accountEntryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountWorkerService getWrappedAccountWorkerService() {
		return _accountWorkerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountWorkerService(
		AccountWorkerService accountWorkerService) {
		_accountWorkerService = accountWorkerService;
	}

	public AccountWorkerService getWrappedService() {
		return _accountWorkerService;
	}

	public void setWrappedService(AccountWorkerService accountWorkerService) {
		_accountWorkerService = accountWorkerService;
	}

	private AccountWorkerService _accountWorkerService;
}