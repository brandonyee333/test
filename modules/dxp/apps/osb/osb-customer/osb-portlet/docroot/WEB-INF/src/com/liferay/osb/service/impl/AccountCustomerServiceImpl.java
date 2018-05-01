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

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.base.AccountCustomerServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountCustomerServiceImpl extends AccountCustomerServiceBaseImpl {

	@JSONWebService
	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException {

		AccountEntry accountEntry =
			accountEntryPersistence.fetchByCorpProjectId(corpProjectId);

		if (accountEntry == null) {
			return Collections.emptyList();
		}

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntry.getAccountEntryId(),
			OSBActionKeys.ASSIGN_CUSTOMERS);

		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByAccountEntryId(
				accountEntry.getAccountEntryId());

		List<String> uuids = new ArrayList<>(accountCustomers.size());

		for (AccountCustomer accountCustomer : accountCustomers) {
			User user = userLocalService.getUser(accountCustomer.getUserId());

			uuids.add(user.getUuid());
		}

		return uuids;
	}

}