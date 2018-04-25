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

package com.liferay.osb.service.permission;

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/**
 * @author Lin Cui
 */
public class OSBAccountEnvironmentPermission {

	public static void check(
			PermissionChecker permissionChecker, AccountEntry accountEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, AccountEntry accountEntry,
		String actionId) {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		AccountCustomer accountCustomer = null;

		try {
			accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					permissionChecker.getUserId(),
					accountEntry.getAccountEntryId());

			if ((accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_DEVELOPER) ||
				(accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_MANAGER)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		PartnerWorker partnerWorker = null;

		try {
			if (accountEntry.isPartnerManagedSupport()) {
				partnerWorker = PartnerWorkerLocalServiceUtil.getPartnerWorker(
					permissionChecker.getUserId(),
					accountEntry.getPartnerEntryId());

				if (partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_MANAGER) {

					return true;
				}
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.DELETE)) {
			return false;
		}

		if ((partnerWorker != null) &&
			(partnerWorker.getRole() == PartnerWorkerConstants.ROLE_MEMBER)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT) ||
			actionId.equals(OSBActionKeys.UPDATE)) {

			return false;
		}

		if (accountCustomer != null) {
			return true;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		return contains(permissionChecker, accountEntry, actionId);
	}

}