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
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class OSBAccountEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long accountEntryId,
		String actionId) {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(), accountEntryId);

			if ((accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) ||
				(accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_SALES)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		AccountCustomer accountCustomer = null;

		try {
			accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					permissionChecker.getUserId(), accountEntryId);

			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_MANAGER) {

				return true;
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.ASSIGN_CUSTOMERS)) {
			if (RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

				return true;
			}

			return false;
		}

		if (accountWorker != null) {
			return true;
		}

		if (actionId.equals(OSBActionKeys.ADD_LICENSE) ||
			actionId.equals(OSBActionKeys.ADD_LICENSE_KEY_SET)) {

			if (RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) ||
				RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

				return true;
			}

			return false;
		}

		if ((accountCustomer != null) &&
			(accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_DEVELOPER)) {

			return true;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		PartnerWorker partnerWorker = null;

		try {
			AccountEntry accountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

			if (accountEntry.isPartnerManagedSupport()) {
				partnerWorker = PartnerWorkerLocalServiceUtil.getPartnerWorker(
					permissionChecker.getUserId(),
					accountEntry.getPartnerEntryId());

				if ((partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_MANAGER) ||
					(partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_MEMBER)) {

					return true;
				}
			}
		}
		catch (Exception e) {
		}

		if ((accountCustomer != null) || (partnerWorker != null)) {
			return true;
		}

		return false;
	}

}