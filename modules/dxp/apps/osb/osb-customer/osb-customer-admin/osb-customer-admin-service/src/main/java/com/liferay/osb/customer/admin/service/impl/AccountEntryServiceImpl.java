/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.constants.AccountEntryConstants;
import com.liferay.osb.customer.admin.exception.NoSuchAccountEntryException;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.base.AccountEntryServiceBaseImpl;
import com.liferay.osb.customer.admin.service.permission.AccountEntryPermission;
import com.liferay.osb.customer.constants.OSBActionKeys;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountEntryServiceImpl extends AccountEntryServiceBaseImpl {

	public AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException {

		AccountEntryPermission.check(
			getPermissionChecker(), accountEntryId, ActionKeys.DELETE);

		return accountEntryLocalService.deleteAccountEntry(
			getUserId(), accountEntryId);
	}

	@JSONWebService
	public AccountEntry fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		// TODO

		return null;
	}

	@JSONWebService
	public List<AccountEntry> getAccountEntries(
			String userUuid, long[] productEntryIds)
		throws PortalException {

		validateJSONWebServicePermissions();

		// TODO

		return null;
	}

	public AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException {

		AccountEntryPermission.check(
			getPermissionChecker(), accountEntryId, ActionKeys.VIEW);

		return accountEntryLocalService.getAccountEntry(accountEntryId);
	}

	@JSONWebService
	public AccountEntry getAccountEntryByCode(String code)
		throws PortalException {

		validateJSONWebServicePermissions();

		try {
			List<Account> accounts = _accountWebService.search(
				"code eq '" + code + "'", 1, 10, null);

			for (Account account : accounts) {
				AccountEntry accountEntry =
					accountEntryLocalService.fetchKoroneikiAccountEntry(
						account.getKey());

				if (accountEntry != null) {
					return accountEntry;
				}
			}

			throw new NoSuchAccountEntryException();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@JSONWebService
	public AccountEntry getCorpProjectAccountEntry(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		// TODO

		return null;
	}

	public AccountEntry updateInstructions(
			long accountEntryId, String instructions)
		throws PortalException {

		AccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS);

		return accountEntryLocalService.updateInstructions(
			getUserId(), accountEntryId, instructions);
	}

	protected void addAccountMembershipParams(
			LinkedHashMap<String, Object> params)
		throws PortalException {

		if (!organizationLocalService.hasUserOrganization(
				getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID) &&
			!organizationLocalService.hasUserOrganization(
				getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			params.put("accountEntryMembership", Long.valueOf(getUserId()));
			params.put("status", AccountEntryConstants.STATUSES_ACTIVE);
		}
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

	@ServiceReference(type = AccountWebService.class)
	private AccountWebService _accountWebService;

}