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
 * Provides a wrapper for {@link AccountCallService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallService
 * @generated
 */
@ProviderType
public class AccountCallServiceWrapper implements AccountCallService,
	ServiceWrapper<AccountCallService> {
	public AccountCallServiceWrapper(AccountCallService accountCallService) {
		_accountCallService = accountCallService;
	}

	@Override
	public com.liferay.osb.model.AccountCall deleteAccountCall(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallService.deleteAccountCall(accountCallId);
	}

	@Override
	public com.liferay.osb.model.AccountCall updateAccountCall(
		long accountCallId, long accountEntryId, int type, int callDateMonth,
		int callDateDay, int callDateYear, int callDateHour,
		int callDateMinute, long callLength, java.lang.String summary,
		java.lang.String clientsPresent, java.lang.String notes,
		java.lang.String actionItems)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCallService.updateAccountCall(accountCallId,
			accountEntryId, type, callDateMonth, callDateDay, callDateYear,
			callDateHour, callDateMinute, callLength, summary, clientsPresent,
			notes, actionItems);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountCallService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountCallService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountCallService getWrappedService() {
		return _accountCallService;
	}

	@Override
	public void setWrappedService(AccountCallService accountCallService) {
		_accountCallService = accountCallService;
	}

	private AccountCallService _accountCallService;
}