/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.service.permission;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Eduardo Lundgren
 * @author Michael C. Han
 */
public class CalendarResourcePermission {

	public static void check(
			PermissionChecker permissionChecker,
			CalendarResource calendarResource, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarResource, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarResourceId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarResource calendarResource,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, calendarResource.getGroupId(),
			CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(),
			CalendarPortletKeys.CALENDAR, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				calendarResource.getCompanyId(),
				CalendarResource.class.getName(),
				calendarResource.getCalendarResourceId(),
				calendarResource.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendarResource.getGroupId(), CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.getCalendarResource(
				calendarResourceId);

		return contains(permissionChecker, calendarResource, actionId);
	}

}