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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/* TODO update app license integration
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceipt;

TODO update app license integration
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
*/

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

		try {
			AccountEntry accountEntry = licenseKey.getAccountEntry();

			if ((accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) &&
				RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.UPDATE_ADMIN)) {
			return false;
		}

		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(), licenseKey.getAccountEntryId());

			if ((accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) ||
				(accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_SALES)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.UPDATE_ADVANCED)) {

			/* TODO update app license integration

			if (RoleLocalServiceUtil.hasUserRole(
					permissionChecker.getUserId(),
					OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

				AssetReceiptLicense assetReceiptLicense =
					AssetReceiptLicenseLocalServiceUtil.
						fetchAssetReceiptLicense(
							licenseKey.getAssetReceiptLicenseId());

				if (assetReceiptLicense == null) {
					return false;
				}

				AssetReceipt assetReceipt =
					AssetReceiptLocalServiceUtil.fetchAssetReceipt(
						assetReceiptLicense.getAssetReceiptId());

				if (assetReceipt == null) {
					return false;
				}

				if (PortalUtil.getClassNameId(AppEntry.class) ==
						assetReceipt.getProductClassNameId()) {

					return true;
				}
			}

			*/

			return false;
		}

		if (actionId.equals(OSBActionKeys.VIEW) && !licenseKey.isActive()) {
			return false;
		}

		if (accountWorker != null) {
			return true;
		}

		if (actionId.equals(OSBActionKeys.UPDATE_BASIC)) {
			return false;
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