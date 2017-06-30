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

import com.liferay.osb.model.AssetRecommendationSet;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset recommendation set service. This utility wraps {@link AssetRecommendationSetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetRecommendationSetPersistence
 * @see AssetRecommendationSetPersistenceImpl
 * @generated
 */
public class AssetRecommendationSetUtil {
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
	public static void clearCache(AssetRecommendationSet assetRecommendationSet) {
		getPersistence().clearCache(assetRecommendationSet);
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
	public static List<AssetRecommendationSet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetRecommendationSet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetRecommendationSet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetRecommendationSet update(
		AssetRecommendationSet assetRecommendationSet, boolean merge)
		throws SystemException {
		return getPersistence().update(assetRecommendationSet, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetRecommendationSet update(
		AssetRecommendationSet assetRecommendationSet, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(assetRecommendationSet, merge, serviceContext);
	}

	/**
	* Caches the asset recommendation set in the entity cache if it is enabled.
	*
	* @param assetRecommendationSet the asset recommendation set
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet) {
		getPersistence().cacheResult(assetRecommendationSet);
	}

	/**
	* Caches the asset recommendation sets in the entity cache if it is enabled.
	*
	* @param assetRecommendationSets the asset recommendation sets
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetRecommendationSet> assetRecommendationSets) {
		getPersistence().cacheResult(assetRecommendationSets);
	}

	/**
	* Creates a new asset recommendation set with the primary key. Does not add the asset recommendation set to the database.
	*
	* @param assetRecommendationSetId the primary key for the new asset recommendation set
	* @return the new asset recommendation set
	*/
	public static com.liferay.osb.model.AssetRecommendationSet create(
		long assetRecommendationSetId) {
		return getPersistence().create(assetRecommendationSetId);
	}

	/**
	* Removes the asset recommendation set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set that was removed
	* @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet remove(
		long assetRecommendationSetId)
		throws com.liferay.osb.NoSuchAssetRecommendationSetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetRecommendationSetId);
	}

	public static com.liferay.osb.model.AssetRecommendationSet updateImpl(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetRecommendationSet, merge);
	}

	/**
	* Returns the asset recommendation set with the primary key or throws a {@link com.liferay.osb.NoSuchAssetRecommendationSetException} if it could not be found.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set
	* @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet findByPrimaryKey(
		long assetRecommendationSetId)
		throws com.liferay.osb.NoSuchAssetRecommendationSetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetRecommendationSetId);
	}

	/**
	* Returns the asset recommendation set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set, or <code>null</code> if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet fetchByPrimaryKey(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetRecommendationSetId);
	}

	/**
	* Returns the asset recommendation set where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetRecommendationSetException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset recommendation set
	* @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a matching asset recommendation set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet findByC_C(
		long classNameId, long classPK)
		throws com.liferay.osb.NoSuchAssetRecommendationSetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns the asset recommendation set where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset recommendation set, or <code>null</code> if a matching asset recommendation set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet fetchByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	* Returns the asset recommendation set where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset recommendation set, or <code>null</code> if a matching asset recommendation set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C(classNameId, classPK, retrieveFromCache);
	}

	/**
	* Returns all the asset recommendation sets.
	*
	* @return the asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetRecommendationSet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset recommendation sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation sets
	* @param end the upper bound of the range of asset recommendation sets (not inclusive)
	* @return the range of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetRecommendationSet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset recommendation sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation sets
	* @param end the upper bound of the range of asset recommendation sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetRecommendationSet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the asset recommendation set where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the asset recommendation set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet removeByC_C(
		long classNameId, long classPK)
		throws com.liferay.osb.NoSuchAssetRecommendationSetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the asset recommendation sets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset recommendation sets where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of asset recommendation sets.
	*
	* @return the number of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetRecommendationSetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetRecommendationSetPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetRecommendationSetPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetRecommendationSetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetRecommendationSetPersistence persistence) {
	}

	private static AssetRecommendationSetPersistence _persistence;
}