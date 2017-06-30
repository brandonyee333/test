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

import com.liferay.osb.model.AssetList;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset list service. This utility wraps {@link AssetListPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListPersistence
 * @see AssetListPersistenceImpl
 * @generated
 */
public class AssetListUtil {
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
	public static void clearCache(AssetList assetList) {
		getPersistence().clearCache(assetList);
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
	public static List<AssetList> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetList> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetList> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetList update(AssetList assetList, boolean merge)
		throws SystemException {
		return getPersistence().update(assetList, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetList update(AssetList assetList, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetList, merge, serviceContext);
	}

	/**
	* Caches the asset list in the entity cache if it is enabled.
	*
	* @param assetList the asset list
	*/
	public static void cacheResult(com.liferay.osb.model.AssetList assetList) {
		getPersistence().cacheResult(assetList);
	}

	/**
	* Caches the asset lists in the entity cache if it is enabled.
	*
	* @param assetLists the asset lists
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetList> assetLists) {
		getPersistence().cacheResult(assetLists);
	}

	/**
	* Creates a new asset list with the primary key. Does not add the asset list to the database.
	*
	* @param assetListId the primary key for the new asset list
	* @return the new asset list
	*/
	public static com.liferay.osb.model.AssetList create(long assetListId) {
		return getPersistence().create(assetListId);
	}

	/**
	* Removes the asset list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list that was removed
	* @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList remove(long assetListId)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetListId);
	}

	public static com.liferay.osb.model.AssetList updateImpl(
		com.liferay.osb.model.AssetList assetList, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetList, merge);
	}

	/**
	* Returns the asset list with the primary key or throws a {@link com.liferay.osb.NoSuchAssetListException} if it could not be found.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList findByPrimaryKey(
		long assetListId)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetListId);
	}

	/**
	* Returns the asset list with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list, or <code>null</code> if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList fetchByPrimaryKey(
		long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetListId);
	}

	/**
	* Returns all the asset lists where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @return the matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> findByAssetCategoryId(
		long assetCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetCategoryId(assetCategoryId);
	}

	/**
	* Returns a range of all the asset lists where assetCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetCategoryId the asset category ID
	* @param start the lower bound of the range of asset lists
	* @param end the upper bound of the range of asset lists (not inclusive)
	* @return the range of matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> findByAssetCategoryId(
		long assetCategoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetCategoryId(assetCategoryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset lists where assetCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetCategoryId the asset category ID
	* @param start the lower bound of the range of asset lists
	* @param end the upper bound of the range of asset lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> findByAssetCategoryId(
		long assetCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetCategoryId(assetCategoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList findByAssetCategoryId_First(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetCategoryId_First(assetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the first asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList fetchByAssetCategoryId_First(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetCategoryId_First(assetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList findByAssetCategoryId_Last(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetCategoryId_Last(assetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList fetchByAssetCategoryId_Last(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetCategoryId_Last(assetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the asset lists before and after the current asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetListId the primary key of the current asset list
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList[] findByAssetCategoryId_PrevAndNext(
		long assetListId, long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetCategoryId_PrevAndNext(assetListId,
			assetCategoryId, orderByComparator);
	}

	/**
	* Returns the asset list where assetCategoryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAssetListException} if it could not be found.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the matching asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList findByACI_T(
		long assetCategoryId, int type)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByACI_T(assetCategoryId, type);
	}

	/**
	* Returns the asset list where assetCategoryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList fetchByACI_T(
		long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByACI_T(assetCategoryId, type);
	}

	/**
	* Returns the asset list where assetCategoryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList fetchByACI_T(
		long assetCategoryId, int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByACI_T(assetCategoryId, type, retrieveFromCache);
	}

	/**
	* Returns all the asset lists.
	*
	* @return the asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset lists
	* @param end the upper bound of the range of asset lists (not inclusive)
	* @return the range of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset lists
	* @param end the upper bound of the range of asset lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetList> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset lists where assetCategoryId = &#63; from the database.
	*
	* @param assetCategoryId the asset category ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetCategoryId(long assetCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetCategoryId(assetCategoryId);
	}

	/**
	* Removes the asset list where assetCategoryId = &#63; and type = &#63; from the database.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the asset list that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetList removeByACI_T(
		long assetCategoryId, int type)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByACI_T(assetCategoryId, type);
	}

	/**
	* Removes all the asset lists from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset lists where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @return the number of matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetCategoryId(long assetCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetCategoryId(assetCategoryId);
	}

	/**
	* Returns the number of asset lists where assetCategoryId = &#63; and type = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the number of matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static int countByACI_T(long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByACI_T(assetCategoryId, type);
	}

	/**
	* Returns the number of asset lists.
	*
	* @return the number of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetListPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetListPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetListPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetListUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetListPersistence persistence) {
	}

	private static AssetListPersistence _persistence;
}