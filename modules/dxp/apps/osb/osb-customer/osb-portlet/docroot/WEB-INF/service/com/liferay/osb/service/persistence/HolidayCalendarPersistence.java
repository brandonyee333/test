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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.HolidayCalendar;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the holiday calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarPersistenceImpl
 * @see HolidayCalendarUtil
 * @generated
 */
public interface HolidayCalendarPersistence extends BasePersistence<HolidayCalendar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HolidayCalendarUtil} to access the holiday calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the holiday calendar in the entity cache if it is enabled.
	*
	* @param holidayCalendar the holiday calendar
	*/
	public void cacheResult(
		com.liferay.osb.model.HolidayCalendar holidayCalendar);

	/**
	* Caches the holiday calendars in the entity cache if it is enabled.
	*
	* @param holidayCalendars the holiday calendars
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.HolidayCalendar> holidayCalendars);

	/**
	* Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	*
	* @param holidayCalendarId the primary key for the new holiday calendar
	* @return the new holiday calendar
	*/
	public com.liferay.osb.model.HolidayCalendar create(long holidayCalendarId);

	/**
	* Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar that was removed
	* @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar remove(long holidayCalendarId)
		throws com.liferay.osb.NoSuchHolidayCalendarException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.HolidayCalendar updateImpl(
		com.liferay.osb.model.HolidayCalendar holidayCalendar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar with the primary key or throws a {@link com.liferay.osb.NoSuchHolidayCalendarException} if it could not be found.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar
	* @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar findByPrimaryKey(
		long holidayCalendarId)
		throws com.liferay.osb.NoSuchHolidayCalendarException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar fetchByPrimaryKey(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the holiday calendars.
	*
	* @return the holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendar> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @return the range of holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendar> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the holiday calendars from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of holiday calendars.
	*
	* @return the number of holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}