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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class OSBLicenseKeySetPermission {

	public static void check(
			PermissionChecker permissionChecker, LicenseKeySet licenseKeySet,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeySet, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long licenseKeySetId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeySetId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, LicenseKeySet licenseKeySet,
		String actionId) {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

			return true;
		}

		try {
			AccountEntry accountEntry = licenseKeySet.getAccountEntry();

			if ((accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) &&
				RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(),
				licenseKeySet.getAccountEntryId());
		}
		catch (Exception e) {
		}

		if (accountWorker != null) {
			return true;
		}

		AccountCustomer accountCustomer = null;

		try {
			accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					permissionChecker.getUserId(),
					licenseKeySet.getAccountEntryId());
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.VIEW) && (accountCustomer != null)) {
			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long licenseKeySetId,
			String actionId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			LicenseKeySetLocalServiceUtil.getLicenseKeySet(licenseKeySetId);

		return contains(permissionChecker, licenseKeySet, actionId);
	}

}