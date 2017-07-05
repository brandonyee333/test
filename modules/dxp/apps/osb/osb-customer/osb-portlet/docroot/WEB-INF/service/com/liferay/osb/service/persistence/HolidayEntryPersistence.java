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

import com.liferay.osb.exception.NoSuchHolidayEntryException;
import com.liferay.osb.model.HolidayEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the holiday entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.HolidayEntryPersistenceImpl
 * @see HolidayEntryUtil
 * @generated
 */
@ProviderType
public interface HolidayEntryPersistence extends BasePersistence<HolidayEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HolidayEntryUtil} to access the holiday entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the holiday entries where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the matching holiday entries
	*/
	public java.util.List<HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId);

	/**
	* Returns a range of all the holiday entries where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @return the range of matching holiday entries
	*/
	public java.util.List<HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId, int start, int end);

	/**
	* Returns an ordered range of all the holiday entries where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holiday entries
	*/
	public java.util.List<HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator);

	/**
	* Returns an ordered range of all the holiday entries where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching holiday entries
	*/
	public java.util.List<HolidayEntry> findByHolidayCalendar(
		long holidayCalendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday entry
	* @throws NoSuchHolidayEntryException if a matching holiday entry could not be found
	*/
	public HolidayEntry findByHolidayCalendar_First(long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator)
		throws NoSuchHolidayEntryException;

	/**
	* Returns the first holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday entry, or <code>null</code> if a matching holiday entry could not be found
	*/
	public HolidayEntry fetchByHolidayCalendar_First(long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator);

	/**
	* Returns the last holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday entry
	* @throws NoSuchHolidayEntryException if a matching holiday entry could not be found
	*/
	public HolidayEntry findByHolidayCalendar_Last(long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator)
		throws NoSuchHolidayEntryException;

	/**
	* Returns the last holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday entry, or <code>null</code> if a matching holiday entry could not be found
	*/
	public HolidayEntry fetchByHolidayCalendar_Last(long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator);

	/**
	* Returns the holiday entries before and after the current holiday entry in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayEntryId the primary key of the current holiday entry
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday entry
	* @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	*/
	public HolidayEntry[] findByHolidayCalendar_PrevAndNext(
		long holidayEntryId, long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator)
		throws NoSuchHolidayEntryException;

	/**
	* Removes all the holiday entries where holidayCalendarId = &#63; from the database.
	*
	* @param holidayCalendarId the holiday calendar ID
	*/
	public void removeByHolidayCalendar(long holidayCalendarId);

	/**
	* Returns the number of holiday entries where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the number of matching holiday entries
	*/
	public int countByHolidayCalendar(long holidayCalendarId);

	/**
	* Caches the holiday entry in the entity cache if it is enabled.
	*
	* @param holidayEntry the holiday entry
	*/
	public void cacheResult(HolidayEntry holidayEntry);

	/**
	* Caches the holiday entries in the entity cache if it is enabled.
	*
	* @param holidayEntries the holiday entries
	*/
	public void cacheResult(java.util.List<HolidayEntry> holidayEntries);

	/**
	* Creates a new holiday entry with the primary key. Does not add the holiday entry to the database.
	*
	* @param holidayEntryId the primary key for the new holiday entry
	* @return the new holiday entry
	*/
	public HolidayEntry create(long holidayEntryId);

	/**
	* Removes the holiday entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry that was removed
	* @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	*/
	public HolidayEntry remove(long holidayEntryId)
		throws NoSuchHolidayEntryException;

	public HolidayEntry updateImpl(HolidayEntry holidayEntry);

	/**
	* Returns the holiday entry with the primary key or throws a {@link NoSuchHolidayEntryException} if it could not be found.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry
	* @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	*/
	public HolidayEntry findByPrimaryKey(long holidayEntryId)
		throws NoSuchHolidayEntryException;

	/**
	* Returns the holiday entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry, or <code>null</code> if a holiday entry with the primary key could not be found
	*/
	public HolidayEntry fetchByPrimaryKey(long holidayEntryId);

	@Override
	public java.util.Map<java.io.Serializable, HolidayEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the holiday entries.
	*
	* @return the holiday entries
	*/
	public java.util.List<HolidayEntry> findAll();

	/**
	* Returns a range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @return the range of holiday entries
	*/
	public java.util.List<HolidayEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holiday entries
	*/
	public java.util.List<HolidayEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator);

	/**
	* Returns an ordered range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of holiday entries
	*/
	public java.util.List<HolidayEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HolidayEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the holiday entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of holiday entries.
	*
	* @return the number of holiday entries
	*/
	public int countAll();
}