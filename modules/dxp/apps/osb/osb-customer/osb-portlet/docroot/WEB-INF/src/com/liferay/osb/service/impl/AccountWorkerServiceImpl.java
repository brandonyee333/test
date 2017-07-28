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

import com.liferay.osb.service.base.AccountWorkerServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountWorkerServiceImpl extends AccountWorkerServiceBaseImpl {

	public void addAccountWorkers(
			long[] userIds, long accountEntryId, int[] roles,
			int[] notifications)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.ASSIGN_WORKERS);

		accountWorkerLocalService.addAccountWorkers(
			getUserId(), userIds, accountEntryId, roles, notifications);
	}

	public void deleteAccountWorkers(long[] userIds, long accountEntryId)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.ASSIGN_WORKERS);

		accountWorkerLocalService.deleteAccountWorkers(
			getUserId(), userIds, accountEntryId);
	}

}