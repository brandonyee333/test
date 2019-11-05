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

package com.liferay.osb.service.permission;

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
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
		PermissionChecker permissionChecker, AccountEntry accountEntry,
		String actionId) {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.DELETE)) {
			return false;
		}

		if (actionId.equals(OSBActionKeys.ADD_LICENSE) ||
			actionId.equals(OSBActionKeys.ADD_LICENSE_KEY_SET)) {

			if (RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

				return true;
			}

			if ((accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) &&
				RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

				return true;
			}

			if ((accountEntry.getAccountEntryId() ==
					OSBConstants.ACCOUNT_ENTRY_LRDCOM_ID) &&
				OrganizationLocalServiceUtil.hasUserOrganization(
					permissionChecker.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return true;
			}

			return false;
		}

		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(),
				accountEntry.getAccountEntryId());

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
					permissionChecker.getUserId(),
					accountEntry.getAccountEntryId());

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

		if ((accountCustomer != null) &&
			(accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_DEVELOPER)) {

			return true;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID) ||
			OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		PartnerWorker partnerWorker = null;

		try {
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

	public static boolean contains(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		return contains(permissionChecker, accountEntry, actionId);
	}

}