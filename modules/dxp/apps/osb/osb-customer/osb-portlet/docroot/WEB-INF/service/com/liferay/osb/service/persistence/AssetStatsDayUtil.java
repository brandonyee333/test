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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset stats day service. This utility wraps {@link AssetStatsDayPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsDayPersistence
 * @see AssetStatsDayPersistenceImpl
 * @generated
 */
public class AssetStatsDayUtil {
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
	public static void clearCache(AssetStatsDay assetStatsDay) {
		getPersistence().clearCache(assetStatsDay);
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
	public static List<AssetStatsDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetStatsDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetStatsDay> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetStatsDay update(AssetStatsDay assetStatsDay,
		boolean merge) throws SystemException {
		return getPersistence().update(assetStatsDay, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetStatsDay update(AssetStatsDay assetStatsDay,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetStatsDay, merge, serviceContext);
	}

	/**
	* Caches the asset stats day in the entity cache if it is enabled.
	*
	* @param assetStatsDay the asset stats day
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetStatsDay assetStatsDay) {
		getPersistence().cacheResult(assetStatsDay);
	}

	/**
	* Caches the asset stats daies in the entity cache if it is enabled.
	*
	* @param assetStatsDaies the asset stats daies
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetStatsDay> assetStatsDaies) {
		getPersistence().cacheResult(assetStatsDaies);
	}

	/**
	* Creates a new asset stats day with the primary key. Does not add the asset stats day to the database.
	*
	* @param assetStatsDayId the primary key for the new asset stats day
	* @return the new asset stats day
	*/
	public static com.liferay.osb.model.AssetStatsDay create(
		long assetStatsDayId) {
		return getPersistence().create(assetStatsDayId);
	}

	/**
	* Removes the asset stats day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day that was removed
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay remove(
		long assetStatsDayId)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetStatsDayId);
	}

	public static com.liferay.osb.model.AssetStatsDay updateImpl(
		com.liferay.osb.model.AssetStatsDay assetStatsDay, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetStatsDay, merge);
	}

	/**
	* Returns the asset stats day with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsDayException} if it could not be found.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day
	* @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay findByPrimaryKey(
		long assetStatsDayId)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetStatsDayId);
	}

	/**
	* Returns the asset stats day with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day, or <code>null</code> if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay fetchByPrimaryKey(
		long assetStatsDayId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetStatsDayId);
	}

	/**
	* Returns all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsDay findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsDay findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsDay[] findByC_C_PrevAndNext(
		long assetStatsDayId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(assetStatsDayId, classNameId,
			classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetStatsDay findByC_C_D_Y(
		long classNameId, long classPK, int day, int year)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_D_Y(classNameId, classPK, day, year);
	}

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
	public static com.liferay.osb.model.AssetStatsDay fetchByC_C_D_Y(
		long classNameId, long classPK, int day, int year)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C_D_Y(classNameId, classPK, day, year);
	}

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
	public static com.liferay.osb.model.AssetStatsDay fetchByC_C_D_Y(
		long classNameId, long classPK, int day, int year,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_D_Y(classNameId, classPK, day, year,
			retrieveFromCache);
	}

	/**
	* Returns all the asset stats daies.
	*
	* @return the asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset stats daies where classNameId = &#63; and classPK = &#63; from the database.
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
	* Removes the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param day the day
	* @param year the year
	* @return the asset stats day that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay removeByC_C_D_Y(
		long classNameId, long classPK, int day, int year)
		throws com.liferay.osb.NoSuchAssetStatsDayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_C_D_Y(classNameId, classPK, day, year);
	}

	/**
	* Removes all the asset stats daies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset stats daies where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

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
	public static int countByC_C_D_Y(long classNameId, long classPK, int day,
		int year) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_D_Y(classNameId, classPK, day, year);
	}

	/**
	* Returns the number of asset stats daies.
	*
	* @return the number of asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetStatsDayPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetStatsDayPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetStatsDayPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetStatsDayUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetStatsDayPersistence persistence) {
	}

	private static AssetStatsDayPersistence _persistence;
}