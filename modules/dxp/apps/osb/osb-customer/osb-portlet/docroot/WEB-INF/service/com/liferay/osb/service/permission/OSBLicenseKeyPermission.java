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

import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class OSBLicenseKeyPermission {

	public static void check(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long licenseKeyId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeyId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, LicenseKey licenseKey,
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

		if (actionId.equals(OSBActionKeys.UPDATE_ADMIN)) {
			return false;
		}

		if (actionId.equals(OSBActionKeys.UPDATE_ADVANCED)) {
			return false;
		}

		if (actionId.equals(OSBActionKeys.UPDATE_BASIC)) {
			return false;
		}

		/*
		TODO
		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(), licenseKey.getAccountEntryId());

			if ((accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) ||
				(accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_CUSTOMER_SUCCESS) ||
				(accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_SALES)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.VIEW) && !licenseKey.isActive()) {
			return false;
		}

		if (accountWorker != null) {
			return true;
		}

		AccountCustomer accountCustomer = null;

		try {
			accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					permissionChecker.getUserId(),
					licenseKey.getAccountEntryId());
		}
		catch (Exception e) {
		}

		if (accountCustomer != null) {
			return true;
		}
		*/

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long licenseKeyId,
			String actionId)
		throws PortalException {

		LicenseKey licenseKey = LicenseKeyLocalServiceUtil.getLicenseKey(
			licenseKeyId);

		return contains(permissionChecker, licenseKey, actionId);
	}

}