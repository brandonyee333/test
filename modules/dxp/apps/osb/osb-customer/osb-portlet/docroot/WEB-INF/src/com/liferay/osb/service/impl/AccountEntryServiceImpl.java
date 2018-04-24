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
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountEntryServiceImpl extends AccountEntryServiceBaseImpl {

	@JSONWebService
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException {

		AccountEntry accountEntry =
			accountEntryPersistence.fetchByCorpProjectId(corpProjectId);

		if (accountEntry == null) {
			return null;
		}

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntry.getAccountEntryId(),
			ActionKeys.VIEW);

		return accountEntry;
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

	public List<AccountEntry> search(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String name, String code, int[] industries,
			Boolean partnerManagedSupport, int[] tiers, int[] statuses,
			String instructions, String notes, String partnerEntryCode,
			String street, Long countryId, Long regionId, String city,
			String zip, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws PortalException {

		addAccountMembershipParams(params);

		return accountEntryLocalService.search(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, name, code, industries, partnerManagedSupport,
			tiers, statuses, instructions, notes, partnerEntryCode, street,
			countryId, regionId, city, zip, params, andOperator, start, end,
			obc);
	}

	public List<AccountEntry> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		addAccountMembershipParams(params);

		return accountEntryLocalService.search(
			keywords, params, start, end, obc);
	}

	public List<AccountEntry> search(String name, String code)
		throws PortalException {

		if (organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return accountEntryLocalService.search(name, code);
		}
		else {
			return null;
		}
	}

	public int searchCount(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String name, String code, int[] industries,
			Boolean partnerManagedSupport, int[] tiers, int[] statuses,
			String instructions, String notes, String partnerEntryCode,
			String street, Long countryId, Long regionId, String city,
			String zip, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws PortalException {

		addAccountMembershipParams(params);

		return accountEntryLocalService.searchCount(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, name, code, industries, partnerManagedSupport,
			tiers, statuses, instructions, notes, partnerEntryCode, street,
			countryId, regionId, city, zip, params, andOperator);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws PortalException {

		addAccountMembershipParams(params);

		return accountEntryLocalService.searchCount(keywords, params);
	}

	public AccountEntry updateAccountEntry(
			long accountEntryId, String corpProjectUuid, String corpEntryName,
			String name, String code, int type, int industry,
			long partnerEntryId, boolean partnerManagedSupport, int tier,
			int maxCustomers, String instructions, String notes,
			String[] languageIds, long[] supportRegionIds, long addressId,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId,
			String ewsaDossieraProjectKey)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountEntryLocalService.updateAccountEntry(
			getUserId(), accountEntryId, corpProjectUuid, corpEntryName, name,
			code, type, industry, partnerEntryId, partnerManagedSupport, tier,
			maxCustomers, instructions, notes, languageIds, supportRegionIds,
			addressId, street1, street2, street3, city, zip, regionId,
			countryId, ewsaDossieraProjectKey);
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

	public AccountEntry updateTier(long accountEntryId, int tier)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_TIER);

		return accountEntryLocalService.updateTier(
			getUserId(), accountEntryId, tier);
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

}