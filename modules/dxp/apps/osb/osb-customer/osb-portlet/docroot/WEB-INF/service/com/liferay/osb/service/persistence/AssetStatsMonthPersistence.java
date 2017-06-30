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

import com.liferay.osb.model.AssetStatsMonth;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset stats month service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsMonthPersistenceImpl
 * @see AssetStatsMonthUtil
 * @generated
 */
public interface AssetStatsMonthPersistence extends BasePersistence<AssetStatsMonth> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetStatsMonthUtil} to access the asset stats month persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset stats month in the entity cache if it is enabled.
	*
	* @param assetStatsMonth the asset stats month
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth);

	/**
	* Caches the asset stats months in the entity cache if it is enabled.
	*
	* @param assetStatsMonths the asset stats months
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetStatsMonth> assetStatsMonths);

	/**
	* Creates a new asset stats month with the primary key. Does not add the asset stats month to the database.
	*
	* @param assetStatsMonthId the primary key for the new asset stats month
	* @return the new asset stats month
	*/
	public com.liferay.osb.model.AssetStatsMonth create(long assetStatsMonthId);

	/**
	* Removes the asset stats month with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month that was removed
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth remove(long assetStatsMonthId)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetStatsMonth updateImpl(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats month with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsMonthException} if it could not be found.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth findByPrimaryKey(
		long assetStatsMonthId)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats month with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month, or <code>null</code> if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth fetchByPrimaryKey(
		long assetStatsMonthId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset stats months where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsMonth> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset stats months where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset stats months
	* @param end the upper bound of the range of asset stats months (not inclusive)
	* @return the range of matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsMonth> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset stats months where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset stats months
	* @param end the upper bound of the range of asset stats months (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsMonth> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats month
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats month
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats months before and after the current asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetStatsMonthId the primary key of the current asset stats month
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset stats month
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth[] findByC_C_PrevAndNext(
		long assetStatsMonthId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; or throws a {@link com.liferay.osb.NoSuchAssetStatsMonthException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param month the month
	* @param year the year
	* @return the matching asset stats month
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth findByC_C_M_Y(
		long classNameId, long classPK, int month, int year)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param month the month
	* @param year the year
	* @return the matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth fetchByC_C_M_Y(
		long classNameId, long classPK, int month, int year)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param month the month
	* @param year the year
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth fetchByC_C_M_Y(
		long classNameId, long classPK, int month, int year,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset stats months.
	*
	* @return the asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsMonth> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset stats months.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats months
	* @param end the upper bound of the range of asset stats months (not inclusive)
	* @return the range of asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsMonth> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset stats months.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats months
	* @param end the upper bound of the range of asset stats months (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsMonth> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset stats months where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param month the month
	* @param year the year
	* @return the asset stats month that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsMonth removeByC_C_M_Y(
		long classNameId, long classPK, int month, int year)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset stats months from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats months where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats months where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param month the month
	* @param year the year
	* @return the number of matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_M_Y(long classNameId, long classPK, int month,
		int year) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset stats months.
	*
	* @return the number of asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}