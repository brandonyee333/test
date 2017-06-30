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

import com.liferay.osb.model.HolidayEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the holiday entry service. This utility wraps {@link HolidayEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayEntryPersistence
 * @see HolidayEntryPersistenceImpl
 * @generated
 */
public class HolidayEntryUtil {
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
	public static void clearCache(HolidayEntry holidayEntry) {
		getPersistence().clearCache(holidayEntry);
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
	public static List<HolidayEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HolidayEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HolidayEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HolidayEntry update(HolidayEntry holidayEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(holidayEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HolidayEntry update(HolidayEntry holidayEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(holidayEntry, merge, serviceContext);
	}

	/**
	* Caches the holiday entry in the entity cache if it is enabled.
	*
	* @param holidayEntry the holiday entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.HolidayEntry holidayEntry) {
		getPersistence().cacheResult(holidayEntry);
	}

	/**
	* Caches the holiday entries in the entity cache if it is enabled.
	*
	* @param holidayEntries the holiday entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries) {
		getPersistence().cacheResult(holidayEntries);
	}

	/**
	* Creates a new holiday entry with the primary key. Does not add the holiday entry to the database.
	*
	* @param holidayEntryId the primary key for the new holiday entry
	* @return the new holiday entry
	*/
	public static com.liferay.osb.model.HolidayEntry create(long holidayEntryId) {
		return getPersistence().create(holidayEntryId);
	}

	/**
	* Removes the holiday entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry that was removed
	* @throws com.liferay.osb.NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry remove(long holidayEntryId)
		throws com.liferay.osb.NoSuchHolidayEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(holidayEntryId);
	}

	public static com.liferay.osb.model.HolidayEntry updateImpl(
		com.liferay.osb.model.HolidayEntry holidayEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(holidayEntry, merge);
	}

	/**
	* Returns the holiday entry with the primary key or throws a {@link com.liferay.osb.NoSuchHolidayEntryException} if it could not be found.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry
	* @throws com.liferay.osb.NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry findByPrimaryKey(
		long holidayEntryId)
		throws com.liferay.osb.NoSuchHolidayEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(holidayEntryId);
	}

	/**
	* Returns the holiday entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry, or <code>null</code> if a holiday entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry fetchByPrimaryKey(
		long holidayEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(holidayEntryId);
	}

	/**
	* Returns all the holiday entries where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the matching holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByHolidayCalendar(holidayCalendarId);
	}

	/**
	* Returns a range of all the holiday entries where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @return the range of matching holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHolidayCalendar(holidayCalendarId, start, end);
	}

	/**
	* Returns an ordered range of all the holiday entries where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHolidayCalendar(holidayCalendarId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday entry
	* @throws com.liferay.osb.NoSuchHolidayEntryException if a matching holiday entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry findByHolidayCalendar_First(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHolidayCalendar_First(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the first holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday entry, or <code>null</code> if a matching holiday entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry fetchByHolidayCalendar_First(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByHolidayCalendar_First(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the last holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday entry
	* @throws com.liferay.osb.NoSuchHolidayEntryException if a matching holiday entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry findByHolidayCalendar_Last(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHolidayCalendar_Last(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the last holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday entry, or <code>null</code> if a matching holiday entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry fetchByHolidayCalendar_Last(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByHolidayCalendar_Last(holidayCalendarId,
			orderByComparator);
	}

	/**
	* Returns the holiday entries before and after the current holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayEntryId the primary key of the current holiday entry
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday entry
	* @throws com.liferay.osb.NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.HolidayEntry[] findByHolidayCalendar_PrevAndNext(
		long holidayEntryId, long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHolidayCalendar_PrevAndNext(holidayEntryId,
			holidayCalendarId, orderByComparator);
	}

	/**
	* Returns all the holiday entries.
	*
	* @return the holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @return the range of holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the holiday entries where holidayCalendarId = &#63; from the database.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByHolidayCalendar(long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByHolidayCalendar(holidayCalendarId);
	}

	/**
	* Removes all the holiday entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of holiday entries where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the number of matching holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByHolidayCalendar(long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByHolidayCalendar(holidayCalendarId);
	}

	/**
	* Returns the number of holiday entries.
	*
	* @return the number of holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HolidayEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HolidayEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					HolidayEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(HolidayEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(HolidayEntryPersistence persistence) {
	}

	private static HolidayEntryPersistence _persistence;
}