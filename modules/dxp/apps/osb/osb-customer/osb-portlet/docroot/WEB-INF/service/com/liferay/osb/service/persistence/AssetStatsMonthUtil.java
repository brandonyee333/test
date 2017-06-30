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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset stats month service. This utility wraps {@link AssetStatsMonthPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsMonthPersistence
 * @see AssetStatsMonthPersistenceImpl
 * @generated
 */
public class AssetStatsMonthUtil {
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
	public static void clearCache(AssetStatsMonth assetStatsMonth) {
		getPersistence().clearCache(assetStatsMonth);
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
	public static List<AssetStatsMonth> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetStatsMonth> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetStatsMonth> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetStatsMonth update(AssetStatsMonth assetStatsMonth,
		boolean merge) throws SystemException {
		return getPersistence().update(assetStatsMonth, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetStatsMonth update(AssetStatsMonth assetStatsMonth,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetStatsMonth, merge, serviceContext);
	}

	/**
	* Caches the asset stats month in the entity cache if it is enabled.
	*
	* @param assetStatsMonth the asset stats month
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth) {
		getPersistence().cacheResult(assetStatsMonth);
	}

	/**
	* Caches the asset stats months in the entity cache if it is enabled.
	*
	* @param assetStatsMonths the asset stats months
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetStatsMonth> assetStatsMonths) {
		getPersistence().cacheResult(assetStatsMonths);
	}

	/**
	* Creates a new asset stats month with the primary key. Does not add the asset stats month to the database.
	*
	* @param assetStatsMonthId the primary key for the new asset stats month
	* @return the new asset stats month
	*/
	public static com.liferay.osb.model.AssetStatsMonth create(
		long assetStatsMonthId) {
		return getPersistence().create(assetStatsMonthId);
	}

	/**
	* Removes the asset stats month with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month that was removed
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth remove(
		long assetStatsMonthId)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetStatsMonthId);
	}

	public static com.liferay.osb.model.AssetStatsMonth updateImpl(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetStatsMonth, merge);
	}

	/**
	* Returns the asset stats month with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsMonthException} if it could not be found.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month
	* @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth findByPrimaryKey(
		long assetStatsMonthId)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetStatsMonthId);
	}

	/**
	* Returns the asset stats month with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month, or <code>null</code> if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth fetchByPrimaryKey(
		long assetStatsMonthId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetStatsMonthId);
	}

	/**
	* Returns all the asset stats months where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth[] findByC_C_PrevAndNext(
		long assetStatsMonthId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(assetStatsMonthId, classNameId,
			classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth findByC_C_M_Y(
		long classNameId, long classPK, int month, int year)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_M_Y(classNameId, classPK, month, year);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth fetchByC_C_M_Y(
		long classNameId, long classPK, int month, int year)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C_M_Y(classNameId, classPK, month, year);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth fetchByC_C_M_Y(
		long classNameId, long classPK, int month, int year,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_M_Y(classNameId, classPK, month, year,
			retrieveFromCache);
	}

	/**
	* Returns all the asset stats months.
	*
	* @return the asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset stats months where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

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
	public static com.liferay.osb.model.AssetStatsMonth removeByC_C_M_Y(
		long classNameId, long classPK, int month, int year)
		throws com.liferay.osb.NoSuchAssetStatsMonthException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByC_C_M_Y(classNameId, classPK, month, year);
	}

	/**
	* Removes all the asset stats months from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset stats months where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

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
	public static int countByC_C_M_Y(long classNameId, long classPK, int month,
		int year) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_M_Y(classNameId, classPK, month, year);
	}

	/**
	* Returns the number of asset stats months.
	*
	* @return the number of asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetStatsMonthPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetStatsMonthPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetStatsMonthPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetStatsMonthUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetStatsMonthPersistence persistence) {
	}

	private static AssetStatsMonthPersistence _persistence;
}