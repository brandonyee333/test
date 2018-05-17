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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.service.base.AccountEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountEntryServiceImpl extends AccountEntryServiceBaseImpl {

	@JSONWebService
	public AccountEntry fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		return accountEntryPersistence.fetchByCorpProjectUuid(corpProjectUuid);
	}

	public AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId, ActionKeys.VIEW);

		return accountEntryLocalService.getAccountEntry(accountEntryId);
	}

	@JSONWebService
	public AccountEntry getAccountEntryByCode(String code)
		throws PortalException {

		AccountEntry accountEntry =
			accountEntryLocalService.getAccountEntryByCode(code);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntry.getAccountEntryId(),
			ActionKeys.VIEW);

		return accountEntry;
	}

	public List<AccountEntry> getSecurityPatchAccountEntries(String portletId)
		throws PortalException {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		addAccountMembershipParams(params);

		return accountEntryLocalService.getSecurityPatchAccountEntries(
			portletId, params);
	}

	protected void addAccountMembershipParams(
			LinkedHashMap<String, Object> params)
		throws PortalException {

		if (!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			params.put("accountEntryMembership", Long.valueOf(getUserId()));
			params.put("status", AccountEntryConstants.STATUSES_ACTIVE);
		}
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}