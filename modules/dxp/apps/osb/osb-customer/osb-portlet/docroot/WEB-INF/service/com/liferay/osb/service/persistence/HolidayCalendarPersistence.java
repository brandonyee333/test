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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchHolidayCalendarException;
import com.liferay.osb.model.HolidayCalendar;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the holiday calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.HolidayCalendarPersistenceImpl
 * @see HolidayCalendarUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(HolidayCalendar holidayCalendar);

	/**
	* Caches the holiday calendars in the entity cache if it is enabled.
	*
	* @param holidayCalendars the holiday calendars
	*/
	public void cacheResult(java.util.List<HolidayCalendar> holidayCalendars);

	/**
	* Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	*
	* @param holidayCalendarId the primary key for the new holiday calendar
	* @return the new holiday calendar
	*/
	public HolidayCalendar create(long holidayCalendarId);

	/**
	* Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar that was removed
	* @throws NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	*/
	public HolidayCalendar remove(long holidayCalendarId)
		throws NoSuchHolidayCalendarException;

	public HolidayCalendar updateImpl(HolidayCalendar holidayCalendar);

	/**
	* Returns the holiday calendar with the primary key or throws a {@link NoSuchHolidayCalendarException} if it could not be found.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar
	* @throws NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	*/
	public HolidayCalendar findByPrimaryKey(long holidayCalendarId)
		throws NoSuchHolidayCalendarException;

	/**
	* Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	*/
	public HolidayCalendar fetchByPrimaryKey(long holidayCalendarId);

	@Override
	public java.util.Map<java.io.Serializable, HolidayCalendar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the holiday calendars.
	*
	* @return the holiday calendars
	*/
	public java.util.List<HolidayCalendar> findAll();

	/**
	* Returns a range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @return the range of holiday calendars
	*/
	public java.util.List<HolidayCalendar> findAll(int start, int end);

	/**
	* Returns an ordered range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holiday calendars
	*/
	public java.util.List<HolidayCalendar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayCalendar> orderByComparator);

	/**
	* Returns an ordered range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of holiday calendars
	*/
	public java.util.List<HolidayCalendar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayCalendar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the holiday calendars from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of holiday calendars.
	*
	* @return the number of holiday calendars
	*/
	public int countAll();
}