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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AccountCall;
import com.liferay.osb.service.base.AccountCallServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Alan Zhang
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountCallServiceImpl extends AccountCallServiceBaseImpl {

	public AccountCall deleteAccountCall(long accountCallId)
		throws PortalException, SystemException {

		AccountCall accountCall = accountCallLocalService.getAccountCall(
			accountCallId);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountCall.getAccountEntryId(),
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountCallLocalService.deleteAccountCall(accountCall);
	}

	public AccountCall updateAccountCall(
			long accountCallId, long accountEntryId, int type,
			int callDateMonth, int callDateDay, int callDateYear,
			int callDateHour, int callDateMinute, long callLength,
			String summary, String clientsPresent, String notes,
			String actionItems)
		throws PortalException, SystemException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountCallLocalService.updateAccountCall(
			getUserId(), accountCallId, accountEntryId, type, callDateMonth,
			callDateDay, callDateYear, callDateHour, callDateMinute, callLength,
			summary, clientsPresent, notes, actionItems);
	}

}