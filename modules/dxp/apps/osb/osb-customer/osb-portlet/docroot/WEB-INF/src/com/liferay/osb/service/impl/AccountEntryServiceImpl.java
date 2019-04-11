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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.service.base.AccountEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountEntryServiceImpl extends AccountEntryServiceBaseImpl {

	public AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId, ActionKeys.DELETE);

		return accountEntryLocalService.deleteAccountEntry(
			getUserId(), accountEntryId);
	}

	@JSONWebService
	public AccountEntry fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		return accountEntryPersistence.fetchByCorpProjectUuid(corpProjectUuid);
	}

	@JSONWebService
	public List<AccountEntry> getAccountEntries(
			String userUuid, long[] productEntryIds)
		throws PortalException {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBConstants.COMPANY_ID);

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountCustomer", Long.valueOf(user.getUserId()));
		params.put("productEntryIds", productEntryIds);
		params.put("status", AccountEntryConstants.STATUSES_ACTIVE);

		return accountEntryLocalService.search(
			null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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

	@JSONWebService
	public AccountEntry getCorpProjectAccountEntry(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		return accountEntryPersistence.findByCorpProjectUuid(corpProjectUuid);
	}

	public List<AccountEntry> search(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String dossieraAccountKey,
			String corpEntryName, String name, String code, int[] industries,
			Boolean partnerManagedSupport, int[] tiers, int[] statuses,
			String instructions, String notes, String partnerEntryCode,
			String street, Long countryId, Long regionId, String city,
			String zip, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws PortalException {

		if (params == null) {
			params = new LinkedHashMap<>();
		}

		addAccountMembershipParams(params);

		return accountEntryLocalService.search(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, dossieraAccountKey, corpEntryName, name, code,
			industries, partnerManagedSupport, tiers, statuses, instructions,
			notes, partnerEntryCode, street, countryId, regionId, city, zip,
			params, andOperator, start, end, obc);
	}

	public int searchCount(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String dossieraAccountKey,
			String corpEntryName, String name, String code, int[] industries,
			Boolean partnerManagedSupport, int[] tiers, int[] statuses,
			String instructions, String notes, String partnerEntryCode,
			String street, Long countryId, Long regionId, String city,
			String zip, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws PortalException {

		if (params == null) {
			params = new LinkedHashMap<>();
		}

		addAccountMembershipParams(params);

		return accountEntryLocalService.searchCount(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, dossieraAccountKey, corpEntryName, name, code,
			industries, partnerManagedSupport, tiers, statuses, instructions,
			notes, partnerEntryCode, street, countryId, regionId, city, zip,
			params, andOperator);
	}

	public AccountEntry updateInstructions(
			long accountEntryId, String instructions)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS);

		return accountEntryLocalService.updateInstructions(
			getUserId(), accountEntryId, instructions);
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