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

import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.Date;

/**
 * @author Joan Kim
 * @author Haote Chou
 */
public class OSBDeveloperEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, DeveloperEntry developerEntry,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, developerEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long developerEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, developerEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DeveloperEntry developerEntry,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.SUBSCRIBER_UPDATE) ||
			actionId.equals(OSBActionKeys.UPDATE)) {

			Date subscriptionExpirationDate =
				developerEntry.getSubscriptionExpirationDate();

			int subscriptionStatus = developerEntry.getSubscriptionStatus();

			if (actionId.equals(OSBActionKeys.SUBSCRIBER_UPDATE) &&
				isSubscriptionExpired(
					subscriptionExpirationDate, subscriptionStatus)) {

				return false;
			}

			if (developerEntry.isUser() &&
				(developerEntry.getUserId() == permissionChecker.getUserId())) {

				return true;
			}
			else if (developerEntry.isCompany()) {
				if (developerEntry.isPending() &&
					(developerEntry.getUserId() ==
						permissionChecker.getUserId())) {

					return true;
				}

				CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
					developerEntry.getDossieraAccountKey());

				if (CorpEntryLocalServiceUtil.hasUserCorpEntry(
						permissionChecker.getUserId(),
						corpEntry.getCorpEntryId())) {

					return true;
				}
			}
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long developerEntryId,
			String actionId)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

		return contains(permissionChecker, developerEntry, actionId);
	}

	protected static boolean isSubscriptionExpired(
		Date subscriptionExpirationDate, int subscriptionStatus) {

		if (subscriptionStatus != WorkflowConstants.STATUS_APPROVED) {
			return false;
		}

		if (DateUtil.compareTo(new Date(), subscriptionExpirationDate) < 0) {
			return false;
		}

		return true;
	}

}