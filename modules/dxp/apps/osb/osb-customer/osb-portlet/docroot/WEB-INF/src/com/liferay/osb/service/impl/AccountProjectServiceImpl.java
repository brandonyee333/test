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

import com.liferay.osb.model.AccountProject;
import com.liferay.osb.service.base.AccountProjectServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.util.Map;

/**
 * @author Alan Zhang
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountProjectServiceImpl extends AccountProjectServiceBaseImpl {

	public AccountProject deleteAccountProject(long accountProjectId)
		throws PortalException {

		AccountProject accountProject =
			accountProjectLocalService.getAccountProject(accountProjectId);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountProject.getAccountEntryId(),
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountProjectLocalService.deleteAccountProject(
			accountProjectId);
	}

	public AccountProject updateAccountProject(
			long accountProjectId, long accountEntryId, String name,
			Map<Integer, String> data)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountProjectLocalService.updateAccountProject(
			getUserId(), accountProjectId, accountEntryId, name, data);
	}

}