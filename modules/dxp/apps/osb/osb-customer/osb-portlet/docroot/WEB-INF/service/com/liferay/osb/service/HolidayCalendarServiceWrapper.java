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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link HolidayCalendarService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayCalendarService
 * @generated
 */
public class HolidayCalendarServiceWrapper implements HolidayCalendarService,
	ServiceWrapper<HolidayCalendarService> {
	public HolidayCalendarServiceWrapper(
		HolidayCalendarService holidayCalendarService) {
		_holidayCalendarService = holidayCalendarService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _holidayCalendarService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_holidayCalendarService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayCalendarService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public HolidayCalendarService getWrappedHolidayCalendarService() {
		return _holidayCalendarService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedHolidayCalendarService(
		HolidayCalendarService holidayCalendarService) {
		_holidayCalendarService = holidayCalendarService;
	}

	public HolidayCalendarService getWrappedService() {
		return _holidayCalendarService;
	}

	public void setWrappedService(HolidayCalendarService holidayCalendarService) {
		_holidayCalendarService = holidayCalendarService;
	}

	private HolidayCalendarService _holidayCalendarService;
}