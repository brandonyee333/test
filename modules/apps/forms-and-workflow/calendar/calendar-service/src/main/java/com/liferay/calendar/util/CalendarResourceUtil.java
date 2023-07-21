/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.util;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.util.comparator.CalendarResourceCodeComparator;
import com.liferay.calendar.util.comparator.CalendarResourceNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Marcellus Tavares
 */
public class CalendarResourceUtil {

	public static CalendarResource fetchGuestCalendarResource(long companyId)
		throws PortalException {

		return CalendarResourceLocalServiceUtil.fetchCalendarResource(
			PortalUtil.getClassNameId(User.class),
			UserLocalServiceUtil.getDefaultUserId(companyId));
	}

	public static CalendarResource getCalendarResource(
			PortletRequest portletRequest, long classNameId, long classPK)
		throws PortalException {

		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		if (classNameId == groupClassNameId) {
			return getGroupCalendarResource(portletRequest, classPK);
		}

		long userClassNameId = PortalUtil.getClassNameId(User.class);

		if (classNameId == userClassNameId) {
			return getUserCalendarResource(portletRequest, classPK);
		}

		return CalendarResourceServiceUtil.fetchCalendarResource(
			classNameId, classPK);
	}

	public static CalendarResource getGroupCalendarResource(
			long groupId, ServiceContext serviceContext)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isUser()) {
			return null;
		}

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.fetchCalendarResource(
				PortalUtil.getClassNameId(Group.class), groupId);

		if (calendarResource != null) {
			return calendarResource;
		}
		else if (group.hasStagingGroup() &&
				 group.isStagedPortlet(CalendarPortletKeys.CALENDAR)) {

			return null;
		}

		long userId = group.getCreatorUserId();

		User user = UserLocalServiceUtil.fetchUserById(userId);

		if ((user == null) || user.isDefaultUser()) {
			Role role = RoleLocalServiceUtil.getRole(
				group.getCompanyId(), RoleConstants.ADMINISTRATOR);

			long[] userIds = UserLocalServiceUtil.getRoleUserIds(
				role.getRoleId());

			userId = userIds[0];
		}

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getSiteDefault(), group.getDescriptiveName());

		Map<Locale, String> descriptionMap = new HashMap<>();

		return CalendarResourceLocalServiceUtil.addCalendarResource(
			userId, groupId, PortalUtil.getClassNameId(Group.class), groupId,
			null, null,
			LocalizationUtil.populateLocalizationMap(
				nameMap, LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()),
				groupId),
			descriptionMap, true, serviceContext);
	}

	public static CalendarResource getGroupCalendarResource(
			PortletRequest portletRequest, long groupId)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		return getGroupCalendarResource(groupId, serviceContext);
	}

	public static OrderByComparator<CalendarResource> getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CalendarResource> orderByComparator = null;

		if (orderByCol.equals("name")) {
			orderByComparator = new CalendarResourceNameComparator(orderByAsc);
		}
		else {
			orderByComparator = new CalendarResourceCodeComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static CalendarResource getScopeGroupCalendarResource(
			long groupId, ServiceContext serviceContext)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isUser()) {
			return getUserCalendarResource(group.getClassPK(), serviceContext);
		}

		return getGroupCalendarResource(groupId, serviceContext);
	}

	public static CalendarResource getScopeGroupCalendarResource(
			PortletRequest portletRequest, long groupId)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		return getScopeGroupCalendarResource(groupId, serviceContext);
	}

	public static CalendarResource getUserCalendarResource(
			long userId, ServiceContext serviceContext)
		throws PortalException {

		long classNameId = PortalUtil.getClassNameId(User.class);

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.fetchCalendarResource(
				classNameId, userId);

		if (calendarResource != null) {
			return calendarResource;
		}

		User user = UserLocalServiceUtil.getUser(userId);

		Group userGroup = null;

		String userName = user.getFullName();

		if (user.isDefaultUser()) {
			userGroup = GroupLocalServiceUtil.getGroup(
				serviceContext.getCompanyId(), GroupConstants.GUEST);

			userName = GroupConstants.GUEST;
		}
		else {
			userGroup = GroupLocalServiceUtil.getUserGroup(
				serviceContext.getCompanyId(), userId);
		}

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getSiteDefault(), userName);

		Map<Locale, String> descriptionMap = new HashMap<>();

		return CalendarResourceLocalServiceUtil.addCalendarResource(
			userId, userGroup.getGroupId(),
			PortalUtil.getClassNameId(User.class), userId, null, null,
			LocalizationUtil.populateLocalizationMap(
				nameMap, LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()),
				userGroup.getGroupId()),
			descriptionMap, true, serviceContext);
	}

	public static CalendarResource getUserCalendarResource(
			PortletRequest portletRequest, long userId)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		serviceContext.setUserId(userId);

		return getUserCalendarResource(userId, serviceContext);
	}

}