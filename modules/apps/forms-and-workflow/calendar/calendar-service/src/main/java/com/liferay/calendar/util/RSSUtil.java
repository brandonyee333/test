/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.util;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.configuration.CalendarServiceConfigurationValues;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;

import java.text.Format;

/**
 * @author Bruno Basto
 */
public class RSSUtil extends com.liferay.rss.util.RSSUtil {

	public static final long TIME_INTERVAL_DEFAULT = Time.WEEK;

	public static String getContent(
		CalendarBooking calendarBooking, String displayStyle,
		ThemeDisplay themeDisplay) {

		if (displayStyle.equals(DISPLAY_STYLE_ABSTRACT)) {
			return StringUtil.shorten(
				calendarBooking.getDescription(themeDisplay.getLocale()), 200);
		}

		if (displayStyle.equals(DISPLAY_STYLE_TITLE)) {
			return calendarBooking.getTitle(themeDisplay.getLocale());
		}

		String content = ContentUtil.get(
			RSSUtil.class.getClassLoader(),
			CalendarServiceConfigurationValues.CALENDAR_RSS_TEMPLATE);

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			themeDisplay.getLocale(),
			CalendarUtil.getCalendarBookingDisplayTimeZone(
				calendarBooking, themeDisplay.getTimeZone()));

		return StringUtil.replace(
			content,
			new String[] {
				"[$EVENT_DESCRIPTION$]", "[$EVENT_END_DATE$]",
				"[$EVENT_LOCATION$]", "[$EVENT_START_DATE$]", "[$EVENT_TITLE$]"
			},
			new String[] {
				calendarBooking.getDescription(themeDisplay.getLocale()),
				dateFormatDateTime.format(calendarBooking.getEndTime()),
				calendarBooking.getLocation(),
				dateFormatDateTime.format(calendarBooking.getStartTime()),
				calendarBooking.getTitle(themeDisplay.getLocale())
			});
	}

}