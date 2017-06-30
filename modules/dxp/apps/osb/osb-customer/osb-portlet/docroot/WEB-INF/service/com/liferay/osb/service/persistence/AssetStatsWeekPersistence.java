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

import com.liferay.osb.model.AssetStatsWeek;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset stats week service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsWeekPersistenceImpl
 * @see AssetStatsWeekUtil
 * @generated
 */
public interface AssetStatsWeekPersistence extends BasePersistence<AssetStatsWeek> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetStatsWeekUtil} to access the asset stats week persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset stats week in the entity cache if it is enabled.
	*
	* @param assetStatsWeek the asset stats week
	*/
	public void cacheResult(com.liferay.osb.model.AssetStatsWeek assetStatsWeek);

	/**
	* Caches the asset stats weeks in the entity cache if it is enabled.
	*
	* @param assetStatsWeeks the asset stats weeks
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetStatsWeek> assetStatsWeeks);

	/**
	* Creates a new asset stats week with the primary key. Does not add the asset stats week to the database.
	*
	* @param assetStatsWeekId the primary key for the new asset stats week
	* @return the new asset stats week
	*/
	public com.liferay.osb.model.AssetStatsWeek create(long assetStatsWeekId);

	/**
	* Removes the asset stats week with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsWeekId the primary key of the asset stats week
	* @return the asset stats week that was removed
	* @throws com.liferay.osb.NoSuchAssetStatsWeekException if a asset stats week with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek remove(long assetStatsWeekId)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetStatsWeek updateImpl(
		com.liferay.osb.model.AssetStatsWeek assetStatsWeek, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats week with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsWeekException} if it could not be found.
	*
	* @param assetStatsWeekId the primary key of the asset stats week
	* @return the asset stats week
	* @throws com.liferay.osb.NoSuchAssetStatsWeekException if a asset stats week with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek findByPrimaryKey(
		long assetStatsWeekId)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats week with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetStatsWeekId the primary key of the asset stats week
	* @return the asset stats week, or <code>null</code> if a asset stats week with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek fetchByPrimaryKey(
		long assetStatsWeekId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset stats weeks where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset stats weeks where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset stats weeks
	* @param end the upper bound of the range of asset stats weeks (not inclusive)
	* @return the range of matching asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset stats weeks where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset stats weeks
	* @param end the upper bound of the range of asset stats weeks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset stats week in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats week
	* @throws com.liferay.osb.NoSuchAssetStatsWeekException if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset stats week in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats week, or <code>null</code> if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset stats week in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats week
	* @throws com.liferay.osb.NoSuchAssetStatsWeekException if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset stats week in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats week, or <code>null</code> if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats weeks before and after the current asset stats week in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetStatsWeekId the primary key of the current asset stats week
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset stats week
	* @throws com.liferay.osb.NoSuchAssetStatsWeekException if a asset stats week with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek[] findByC_C_PrevAndNext(
		long assetStatsWeekId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats week where classNameId = &#63; and classPK = &#63; and week = &#63; and year = &#63; or throws a {@link com.liferay.osb.NoSuchAssetStatsWeekException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param week the week
	* @param year the year
	* @return the matching asset stats week
	* @throws com.liferay.osb.NoSuchAssetStatsWeekException if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek findByC_C_W_Y(
		long classNameId, long classPK, int week, int year)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats week where classNameId = &#63; and classPK = &#63; and week = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param week the week
	* @param year the year
	* @return the matching asset stats week, or <code>null</code> if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek fetchByC_C_W_Y(
		long classNameId, long classPK, int week, int year)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats week where classNameId = &#63; and classPK = &#63; and week = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param week the week
	* @param year the year
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset stats week, or <code>null</code> if a matching asset stats week could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek fetchByC_C_W_Y(
		long classNameId, long classPK, int week, int year,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset stats weeks.
	*
	* @return the asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset stats weeks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats weeks
	* @param end the upper bound of the range of asset stats weeks (not inclusive)
	* @return the range of asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset stats weeks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats weeks
	* @param end the upper bound of the range of asset stats weeks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset stats weeks where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset stats week where classNameId = &#63; and classPK = &#63; and week = &#63; and year = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param week the week
	* @param year the year
	* @return the asset stats week that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek removeByC_C_W_Y(
		long classNameId, long classPK, int week, int year)
		throws com.liferay.osb.NoSuchAssetStatsWeekException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset stats weeks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats weeks where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats weeks where classNameId = &#63; and classPK = &#63; and week = &#63; and year = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param week the week
	* @param year the year
	* @return the number of matching asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_W_Y(long classNameId, long classPK, int week, int year)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats weeks.
	*
	* @return the number of asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}