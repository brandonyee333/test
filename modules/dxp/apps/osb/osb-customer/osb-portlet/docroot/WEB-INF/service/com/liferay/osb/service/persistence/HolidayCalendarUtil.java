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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the holiday calendar service. This utility wraps {@link HolidayCalendarPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarPersistence
 * @see HolidayCalendarPersistenceImpl
 * @generated
 */
public class HolidayCalendarUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(HolidayCalendar holidayCalendar) {
		getPersistence().clearCache(holidayCalendar);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HolidayCalendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HolidayCalendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HolidayCalendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HolidayCalendar update(HolidayCalendar holidayCalendar,
		boolean merge) throws SystemException {
		return getPersistence().update(holidayCalendar, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HolidayCalendar update(HolidayCalendar holidayCalendar,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(holidayCalendar, merge, serviceContext);
	}

	/**
	* Caches the holiday calendar in the entity cache if it is enabled.
	*
	* @param holidayCalendar the holiday calendar
	*/
	public static void cacheResult(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		getPersistence().cacheResult(holidayCalendar);
	}

	/**
	* Caches the holiday calendars in the entity cache if it is enabled.
	*
	* @param holidayCalendars the holiday calendars
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.HolidayCalendar> holidayCalendars) {
		getPersistence().cacheResult(holidayCalendars);
	}

	/**
	* Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	*
	* @param holidayCalendarId the primary key for the new holiday calendar
	* @return the new holiday calendar
	*/
	public static com.liferay.osb.model.HolidayCalendar create(
		long holidayCalendarId) {
		return getPersistence().create(holidayCalendarId);
	}

	/**
	* Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar that was removed
	* @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayCalendar remove(
		long holidayCalendarId)
		throws com.liferay.osb.NoSuchHolidayCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(holidayCalendarId);
	}

	public static com.liferay.osb.model.HolidayCalendar updateImpl(
		com.liferay.osb.model.HolidayCalendar holidayCalendar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(holidayCalendar, merge);
	}

	/**
	* Returns the holiday calendar with the primary key or throws a {@link com.liferay.osb.NoSuchHolidayCalendarException} if it could not be found.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar
	* @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayCalendar findByPrimaryKey(
		long holidayCalendarId)
		throws com.liferay.osb.NoSuchHolidayCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(holidayCalendarId);
	}

	/**
	* Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayCalendar fetchByPrimaryKey(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(holidayCalendarId);
	}

	/**
	* Returns all the holiday calendars.
	*
	* @return the holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayCalendar> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.HolidayCalendar> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.HolidayCalendar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the holiday calendars from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of holiday calendars.
	*
	* @return the number of holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HolidayCalendarPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HolidayCalendarPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					HolidayCalendarPersistence.class.getName());

			ReferenceRegistry.registerReference(HolidayCalendarUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(HolidayCalendarPersistence persistence) {
	}

	private static HolidayCalendarPersistence _persistence;
}