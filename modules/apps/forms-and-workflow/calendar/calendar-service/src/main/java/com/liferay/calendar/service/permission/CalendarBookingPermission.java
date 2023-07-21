/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.service.permission;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Jonathan McCann
 */
public class CalendarBookingPermission {

	public static void check(
			PermissionChecker permissionChecker,
			CalendarBooking calendarBooking, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarBooking, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarBookingId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarBookingId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarBooking calendarBooking,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, calendarBooking.getGroupId(),
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(),
			CalendarPortletKeys.CALENDAR, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				calendarBooking.getCompanyId(), CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId(),
				calendarBooking.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendarBooking.getGroupId(), CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarBookingId,
			String actionId)
		throws PortalException {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(
				calendarBookingId);

		return contains(permissionChecker, calendarBooking, actionId);
	}

}