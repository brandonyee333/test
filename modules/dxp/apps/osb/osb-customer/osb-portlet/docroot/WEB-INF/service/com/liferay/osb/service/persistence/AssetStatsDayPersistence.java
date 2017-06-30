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

import com.liferay.osb.model.AssetStatsDay;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset stats day service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsDayPersistenceImpl
 * @see AssetStatsDayUtil
 * @generated
 */
public interface AssetStatsDayPersistence extends BasePersistence<AssetStatsDay> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetStatsDayUtil} to access the asset stats day persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset stats day in the entity cache if it is enabled.
	*
	* @param assetStatsDay the asset stats day
	*/
	public void cacheResult(com.liferay.osb.model.AssetStatsDay assetStatsDay);

	/**
	* Caches the asset stats daies in the entity cache if it is enabled.
	*
	* @param assetStatsDaies the asset stats daies
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetStatsDay> assetStatsDaies);

	/**
	* Creates a new asset stats day with the primary key. Does not add the asset stats day to the database.
	*
	* @param assetStatsDayId the primary key for the new asset stats day
	* @return the new asset stats day
	*/
	public com.liferay.osb.model.AssetStatsDay create(long assetStatsDayId);

	/**
	* Removes the asset stats day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day that was removed
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay remove(long assetStatsDayId)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetStatsDay updateImpl(
		com.liferay.osb.model.AssetStatsDay assetStatsDay, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats day with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsDayException} if it could not be found.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay findByPrimaryKey(
		long assetStatsDayId)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats day with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day, or <code>null</code> if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay fetchByPrimaryKey(
		long assetStatsDayId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsDay> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset stats daies
	* @param end the upper bound of the range of asset stats daies (not inclusive)
	* @return the range of matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsDay> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset stats daies
	* @param end the upper bound of the range of asset stats daies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsDay> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats day
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats day
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats daies before and after the current asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetStatsDayId the primary key of the current asset stats day
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset stats day
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay[] findByC_C_PrevAndNext(
		long assetStatsDayId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; or throws a {@link com.liferay.osb.NoSuchAssetStatsDayException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param day the day
	* @param year the year
	* @return the matching asset stats day
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay findByC_C_D_Y(long classNameId,
		long classPK, int day, int year)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param day the day
	* @param year the year
	* @return the matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay fetchByC_C_D_Y(
		long classNameId, long classPK, int day, int year)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param day the day
	* @param year the year
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay fetchByC_C_D_Y(
		long classNameId, long classPK, int day, int year,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset stats daies.
	*
	* @return the asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsDay> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset stats daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats daies
	* @param end the upper bound of the range of asset stats daies (not inclusive)
	* @return the range of asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsDay> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset stats daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats daies
	* @param end the upper bound of the range of asset stats daies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsDay> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset stats daies where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param day the day
	* @param year the year
	* @return the asset stats day that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsDay removeByC_C_D_Y(
		long classNameId, long classPK, int day, int year)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset stats daies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats daies where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats daies where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param day the day
	* @param year the year
	* @return the number of matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_D_Y(long classNameId, long classPK, int day, int year)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats daies.
	*
	* @return the number of asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}