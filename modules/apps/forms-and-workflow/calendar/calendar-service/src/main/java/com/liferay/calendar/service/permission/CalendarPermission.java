/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.service.permission;

import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarPermission {

	public static void check(
			PermissionChecker permissionChecker, Calendar calendar,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendar, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Calendar calendar,
		String actionId) {

		if (!actionId.equals(CalendarActionKeys.VIEW_BOOKING_DETAILS)) {
			Boolean hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, calendar.getGroupId(),
				Calendar.class.getName(), calendar.getCalendarId(),
				CalendarPortletKeys.CALENDAR, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		if (permissionChecker.hasOwnerPermission(
				calendar.getCompanyId(), Calendar.class.getName(),
				calendar.getCalendarId(), calendar.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendar.getGroupId(), Calendar.class.getName(),
			calendar.getCalendarId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarId,
			String actionId)
		throws PortalException {

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		return contains(permissionChecker, calendar, actionId);
	}

}