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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.SupportLaborConstants;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportLaborImpl extends SupportLaborBaseImpl {

	public SupportLaborImpl() {
	}

	public String formatDayHours(Locale locale, int day) {
		int openTime = getTime(day, SupportLaborConstants.TYPE_OPEN);
		int closeTime = getTime(day, SupportLaborConstants.TYPE_CLOSE);

		if (openTime == closeTime) {
			return LanguageUtil.get(locale, "closed");
		}
		else {
			String openTimeString = formatTime(
				locale, day, SupportLaborConstants.TYPE_OPEN);
			String closeTimeString = formatTime(
				locale, day, SupportLaborConstants.TYPE_CLOSE);

			return openTimeString + " - " + closeTimeString;
		}
	}

	public String formatTime(Locale locale, int day, int type) {
		StringBundler sb = new StringBundler(7);

		int time = getTime(day, type);

		int hour = time / 60;

		hour = hour % 12;

		if (hour == 0) {
			hour = 12;
		}

		sb.append(hour);
		sb.append(":");

		int minute = time % 60;

		if (minute < 10) {
			sb.append("0");
		}

		sb.append(minute);
		sb.append(" ");

		if ((time / 60) >= 12) {
			sb.append(LanguageUtil.get(locale, "pm"));
		}
		else {
			sb.append(LanguageUtil.get(locale, "am"));
		}

		return sb.toString();
	}

	public List<SupportTeam> getSupportTeams() throws PortalException {
		return SupportTeamLocalServiceUtil.getSupportLaborSupportTeams(
			getSupportLaborId());
	}

	public int getTime(int day, int type) {
		if (day == Calendar.SUNDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getSunOpen();
			}
			else {
				return getSunClose();
			}
		}
		else if (day == Calendar.MONDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getMonOpen();
			}
			else {
				return getMonClose();
			}
		}
		else if (day == Calendar.TUESDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getTueOpen();
			}
			else {
				return getTueClose();
			}
		}
		else if (day == Calendar.WEDNESDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getWedOpen();
			}
			else {
				return getWedClose();
			}
		}
		else if (day == Calendar.THURSDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getThuOpen();
			}
			else {
				return getThuClose();
			}
		}
		else if (day == Calendar.FRIDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getFriOpen();
			}
			else {
				return getFriClose();
			}
		}
		else if (day == Calendar.SATURDAY) {
			if (type == SupportLaborConstants.TYPE_OPEN) {
				return getSatOpen();
			}
			else {
				return getSatClose();
			}
		}

		return 0;
	}

}