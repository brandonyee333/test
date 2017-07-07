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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.NoSuchHolidayCalendarRelException;
import com.liferay.osb.model.HolidayCalendarRel;
import com.liferay.osb.service.base.HolidayCalendarRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class HolidayCalendarRelLocalServiceImpl
	extends HolidayCalendarRelLocalServiceBaseImpl {

	public void addUsers(long holidayCalendarId, long[] userIds)
		throws PortalException, SystemException {

		for (long userId : userIds) {
			HolidayCalendarRel holidayCalendarRel =
				holidayCalendarRelPersistence.fetchByHC_U(
					holidayCalendarId, userId);

			if (holidayCalendarRel != null) {
				continue;
			}

			long holidayCalendarRelId = counterLocalService.increment();

			holidayCalendarRel = holidayCalendarRelPersistence.create(
				holidayCalendarRelId);

			holidayCalendarRel.setHolidayCalendarId(holidayCalendarId);
			holidayCalendarRel.setUserId(userId);
			
			//TODO implement serviceContext how needed
			
			ServiceContext serviceContext = new ServiceContext();

			holidayCalendarRelPersistence.update(holidayCalendarRel, serviceContext);
		}
	}

	public void deleteHolidayCalendarRels(long holidayCalendarId)
		throws SystemException {

		holidayCalendarRelPersistence.removeByHolidayCalendarId(
			holidayCalendarId);
	}

	public void deleteHolidayCalendarRels(
			long holidayCalendarId, long[] userIds)
		throws SystemException {

		for (long userId : userIds) {
			try {
				holidayCalendarRelPersistence.removeByHC_U(
					holidayCalendarId, userId);
			}
			catch (NoSuchHolidayCalendarRelException nshcre) {
			}
		}
	}

	public HolidayCalendarRel getHolidayCalendarRel(
			long holidayCalendarId, long userId)
		throws PortalException, SystemException {

		return holidayCalendarRelPersistence.findByHC_U(
			holidayCalendarId, userId);
	}

	public List<HolidayCalendarRel> getHolidayCalendarRels(
			long holidayCalendarId)
		throws SystemException {

		return holidayCalendarRelPersistence.findByHolidayCalendarId(
			holidayCalendarId);
	}

	public boolean hasHolidayCalendarRel(long holidayCalendarId, long userId)
		throws SystemException {

		int count = holidayCalendarRelPersistence.countByHC_U(
			holidayCalendarId, userId);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}