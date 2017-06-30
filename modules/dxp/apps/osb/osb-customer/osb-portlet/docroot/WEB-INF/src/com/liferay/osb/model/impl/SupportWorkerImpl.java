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

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.NoSuchSupportLaborException;
import com.liferay.osb.model.SupportLabor;
import com.liferay.osb.model.SupportLaborConstants;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportWorkerAccountTier;
import com.liferay.osb.model.SupportWorkerComponent;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.SupportWorkerSeverity;
import com.liferay.osb.service.HolidayEntryLocalServiceUtil;
import com.liferay.osb.service.SupportLaborLocalServiceUtil;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerAccountTierLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerComponentLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerSeverityLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Amos Fong
 */
public class SupportWorkerImpl extends SupportWorkerBaseImpl {

	public static int END_OF_DAY_MINUTES = 1439;

	public SupportWorkerImpl() {
	}

	public List<Integer> getAccountTiers() throws SystemException {
		List<SupportWorkerAccountTier> supportWorkerAccountTiers =
			SupportWorkerAccountTierLocalServiceUtil.
				getSupportWorkerAccountTiers(getSupportWorkerId());

		List<Integer> accountTiers = new ArrayList<Integer>(
			supportWorkerAccountTiers.size());

		for (SupportWorkerAccountTier supportWorkerAccountTier :
				supportWorkerAccountTiers) {

			accountTiers.add(supportWorkerAccountTier.getAccountTier());
		}

		return accountTiers;
	}

	public List<Integer> getComponents() throws SystemException {
		List<SupportWorkerComponent> supportWorkerComponents =
			SupportWorkerComponentLocalServiceUtil.getSupportWorkerComponents(
				getSupportWorkerId());

		List<Integer> components = new ArrayList<Integer>(
			supportWorkerComponents.size());

		for (SupportWorkerComponent supportWorkerComponent :
				supportWorkerComponents) {

			components.add(supportWorkerComponent.getComponent());
		}

		return components;
	}

	public String getNotificationsLabel() {
		return SupportWorkerConstants.getNotificationsLabel(getNotifications());
	}

	public String getRoleLabel() {
		return SupportWorkerConstants.getRoleLabel(getRole());
	}

	public List<Integer> getSeverities() throws SystemException {
		List<SupportWorkerSeverity> supportWorkerSeverities =
			SupportWorkerSeverityLocalServiceUtil.getSupportWorkerSeverities(
				getSupportWorkerId());

		List<Integer> severities = new ArrayList<Integer>(
			supportWorkerSeverities.size());

		for (SupportWorkerSeverity supportWorkerSeverity :
				supportWorkerSeverities) {

			severities.add(supportWorkerSeverity.getSeverity());
		}

		return severities;
	}

	public SupportLabor getSupportLabor()
		throws PortalException, SystemException {

		try {
			return SupportLaborLocalServiceUtil.getSupportLabor(
				getSupportLaborId());
		}
		catch (PortalException pe) {
			SupportTeam supportTeam = getSupportTeam();

			return SupportLaborLocalServiceUtil.getSupportLabor(
				supportTeam.getSupportLaborId());
		}
	}

	public SupportTeam getSupportTeam()
		throws PortalException, SystemException {

		return SupportTeamLocalServiceUtil.getSupportTeam(getSupportTeamId());
	}

	public Long getTimeUntilClose() throws PortalException, SystemException {
		SupportLabor supportLabor = getSupportLabor();

		Calendar now = Calendar.getInstance(
			TimeZone.getTimeZone(supportLabor.getTimeZoneId()));

		if (hasHoliday(now.getTime())) {
			return 0L;
		}

		Calendar openCal = (Calendar)now.clone();

		openCal.set(Calendar.HOUR, 0);
		openCal.set(Calendar.MINUTE, 0);
		openCal.set(Calendar.SECOND, 0);

		int openTime = supportLabor.getTime(
			openCal.get(Calendar.DAY_OF_WEEK), SupportLaborConstants.TYPE_OPEN);

		openCal.add(Calendar.MINUTE, openTime);

		if (openCal.after(now)) {
			return 0L;
		}

		for (int i = 0; i < 7; i++) {
			Calendar closeCal = (Calendar)now.clone();

			closeCal.add(Calendar.DAY_OF_MONTH, i);

			closeCal.set(Calendar.HOUR, 0);
			closeCal.set(Calendar.MINUTE, 0);
			closeCal.set(Calendar.SECOND, 0);

			if (hasHoliday(closeCal.getTime())) {
				return closeCal.getTimeInMillis() - now.getTimeInMillis();
			}

			int closeTime = supportLabor.getTime(
				closeCal.get(Calendar.DAY_OF_WEEK),
				SupportLaborConstants.TYPE_CLOSE);

			closeCal.add(Calendar.MINUTE, closeTime);

			if (closeCal.before(now)) {
				return 0L;
			}

			if (closeTime == END_OF_DAY_MINUTES) {
				Calendar nextDayOpenCal = (Calendar)closeCal.clone();

				nextDayOpenCal.add(Calendar.DAY_OF_MONTH, 1);

				int nextDayOpenTime = supportLabor.getTime(
					nextDayOpenCal.get(Calendar.DAY_OF_WEEK),
					SupportLaborConstants.TYPE_OPEN);

				if (nextDayOpenTime == 0) {
					continue;
				}
			}

			return closeCal.getTimeInMillis() - now.getTimeInMillis();
		}

		return null;
	}

	public Long getTimeUntilOpen() throws PortalException, SystemException {
		SupportLabor supportLabor = getSupportLabor();

		Calendar now = Calendar.getInstance(
			TimeZone.getTimeZone(supportLabor.getTimeZoneId()));

		for (int i = 0; i < 7; i++) {
			Calendar openCal = (Calendar)now.clone();
			Calendar closeCal = (Calendar)now.clone();

			openCal.add(Calendar.DAY_OF_MONTH, i);
			closeCal.add(Calendar.DAY_OF_MONTH, i);

			int openTime = supportLabor.getTime(
				openCal.get(Calendar.DAY_OF_WEEK),
				SupportLaborConstants.TYPE_OPEN);
			int closeTime = supportLabor.getTime(
				closeCal.get(Calendar.DAY_OF_WEEK),
				SupportLaborConstants.TYPE_CLOSE);

			if (openTime == closeTime) {
				continue;
			}

			openCal.set(Calendar.HOUR_OF_DAY, 0);
			openCal.set(Calendar.MINUTE, 0);
			openCal.set(Calendar.SECOND, 0);

			openCal.add(Calendar.MINUTE, openTime);

			closeCal.set(Calendar.HOUR_OF_DAY, 0);
			closeCal.set(Calendar.MINUTE, 0);
			closeCal.set(Calendar.SECOND, 0);

			closeCal.add(Calendar.MINUTE, closeTime);

			if (openCal.before(now)) {
				if (closeCal.after(now)) {
					return 0L;
				}
				else {
					continue;
				}
			}

			if (hasHoliday(openCal.getTime())) {
				continue;
			}

			return openCal.getTimeInMillis() - now.getTimeInMillis();
		}

		return null;
	}

	public boolean isActive() throws PortalException, SystemException {
		try {
			User user = UserLocalServiceUtil.getUser(getUserId());

			if (user.isActive()) {
				return true;
			}
		}
		catch (NoSuchUserException nsue) {
		}

		return false;
	}

	public boolean isAvailable() throws PortalException, SystemException {
		Date now = new Date();

		if (hasHoliday(now)) {
			return false;
		}

		if (!isClockedIn()) {
			return false;
		}

		if (!isOpen(now)) {
			return false;
		}

		return true;
	}

	protected boolean hasHoliday(Date date)
		throws PortalException, SystemException {

		SupportLabor supportLabor = null;

		try {
			supportLabor = getSupportLabor();
		}
		catch (NoSuchSupportLaborException nssle) {
			return true;
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		TimeZone timeZone = TimeZone.getTimeZone(supportLabor.getTimeZoneId());

		int timeZoneOffset = (int)(timeZone.getRawOffset() / Time.MINUTE);

		cal.add(Calendar.MINUTE, timeZoneOffset);

		if (HolidayEntryLocalServiceUtil.getHolidayEntriesCount(
				getUserId(), cal.getTime()) > 0) {

			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isOpen(Date date)
		throws PortalException, SystemException {

		SupportLabor supportLabor = null;

		try {
			supportLabor = getSupportLabor();
		}
		catch (NoSuchSupportLaborException nssle) {
			return false;
		}

		Calendar calendar = Calendar.getInstance(
			TimeZone.getTimeZone(supportLabor.getTimeZoneId()));

		calendar.setTime(date);

		int openTime = supportLabor.getTime(
			calendar.get(
				Calendar.DAY_OF_WEEK), SupportLaborConstants.TYPE_OPEN);
		int closeTime = supportLabor.getTime(
			calendar.get(
				Calendar.DAY_OF_WEEK), SupportLaborConstants.TYPE_CLOSE);

		if (openTime == closeTime) {
			return false;
		}

		int minuteOfDay =
			(calendar.get(Calendar.HOUR_OF_DAY) * 60) +
				calendar.get(Calendar.MINUTE);

		if ((minuteOfDay < closeTime) && (minuteOfDay > openTime)) {
			return true;
		}

		if ((openTime > closeTime) &&
			((minuteOfDay > openTime) || (minuteOfDay < closeTime))) {

			return true;
		}

		return false;
	}

}