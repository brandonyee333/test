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

import com.liferay.osb.model.HolidayCalendarRel;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the holiday calendar rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRelPersistenceImpl
 * @see HolidayCalendarRelUtil
 * @generated
 */
public interface HolidayCalendarRelPersistence extends BasePersistence<HolidayCalendarRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HolidayCalendarRelUtil} to access the holiday calendar rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the holiday calendar rel in the entity cache if it is enabled.
	*
	* @param holidayCalendarRel the holiday calendar rel
	*/
	public void cacheResult(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel);

	/**
	* Caches the holiday calendar rels in the entity cache if it is enabled.
	*
	* @param holidayCalendarRels the holiday calendar rels
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.HolidayCalendarRel> holidayCalendarRels);

	/**
	* Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	*
	* @param holidayCalendarRelId the primary key for the new holiday calendar rel
	* @return the new holiday calendar rel
	*/
	public com.liferay.osb.model.HolidayCalendarRel create(
		long holidayCalendarRelId);

	/**
	* Removes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel that was removed
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel remove(
		long holidayCalendarRelId)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.HolidayCalendarRel updateImpl(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rel with the primary key or throws a {@link com.liferay.osb.NoSuchHolidayCalendarRelException} if it could not be found.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel findByPrimaryKey(
		long holidayCalendarRelId)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel, or <code>null</code> if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByPrimaryKey(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the holiday calendar rels where holidayCalendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel findByHolidayCalendarId_First(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByHolidayCalendarId_First(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel findByHolidayCalendarId_Last(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByHolidayCalendarId_Last(
		long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rels before and after the current holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarRelId the primary key of the current holiday calendar rel
	* @param holidayCalendarId the holiday calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel[] findByHolidayCalendarId_PrevAndNext(
		long holidayCalendarRelId, long holidayCalendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the holiday calendar rels where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the holiday calendar rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the holiday calendar rels where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first holiday calendar rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first holiday calendar rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last holiday calendar rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last holiday calendar rel in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rels before and after the current holiday calendar rel in the ordered set where userId = &#63;.
	*
	* @param holidayCalendarRelId the primary key of the current holiday calendar rel
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel[] findByUserId_PrevAndNext(
		long holidayCalendarRelId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or throws a {@link com.liferay.osb.NoSuchHolidayCalendarRelException} if it could not be found.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the matching holiday calendar rel
	* @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel findByHC_U(
		long holidayCalendarId, long userId)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByHC_U(
		long holidayCalendarId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel fetchByHC_U(
		long holidayCalendarId, long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the holiday calendar rels.
	*
	* @return the holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the holiday calendar rels where holidayCalendarId = &#63; from the database.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByHolidayCalendarId(long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the holiday calendar rels where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; from the database.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the holiday calendar rel that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel removeByHC_U(
		long holidayCalendarId, long userId)
		throws com.liferay.osb.NoSuchHolidayCalendarRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the holiday calendar rels from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of holiday calendar rels where holidayCalendarId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @return the number of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public int countByHolidayCalendarId(long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of holiday calendar rels where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of holiday calendar rels where holidayCalendarId = &#63; and userId = &#63;.
	*
	* @param holidayCalendarId the holiday calendar ID
	* @param userId the user ID
	* @return the number of matching holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public int countByHC_U(long holidayCalendarId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of holiday calendar rels.
	*
	* @return the number of holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}