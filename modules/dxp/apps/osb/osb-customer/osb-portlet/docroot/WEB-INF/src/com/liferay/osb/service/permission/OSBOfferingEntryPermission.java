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
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class OSBOfferingEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long offeringEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, offeringEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, OfferingEntry offeringEntry,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, offeringEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long offeringEntryId,
			String actionId)
		throws PortalException, SystemException {

		OfferingEntry offeringEntry =
			OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

		return contains(permissionChecker, offeringEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, OfferingEntry offeringEntry,
			String actionId)
		throws SystemException {

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

		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(),
				offeringEntry.getAccountEntryId());

			if ((accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) ||
				(accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_SALES)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.UPDATE)) {
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
					offeringEntry.getAccountEntryId());
		}
		catch (Exception e) {
		}

		if (accountCustomer != null) {
			return true;
		}

		return false;
	}

}