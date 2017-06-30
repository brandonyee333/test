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

import com.liferay.osb.model.AssetListAssetEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset list asset entry service. This utility wraps {@link AssetListAssetEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListAssetEntryPersistence
 * @see AssetListAssetEntryPersistenceImpl
 * @generated
 */
public class AssetListAssetEntryUtil {
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
	public static void clearCache(AssetListAssetEntry assetListAssetEntry) {
		getPersistence().clearCache(assetListAssetEntry);
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
	public static List<AssetListAssetEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetListAssetEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetListAssetEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetListAssetEntry update(
		AssetListAssetEntry assetListAssetEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(assetListAssetEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetListAssetEntry update(
		AssetListAssetEntry assetListAssetEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(assetListAssetEntry, merge, serviceContext);
	}

	/**
	* Caches the asset list asset entry in the entity cache if it is enabled.
	*
	* @param assetListAssetEntry the asset list asset entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry) {
		getPersistence().cacheResult(assetListAssetEntry);
	}

	/**
	* Caches the asset list asset entries in the entity cache if it is enabled.
	*
	* @param assetListAssetEntries the asset list asset entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetListAssetEntry> assetListAssetEntries) {
		getPersistence().cacheResult(assetListAssetEntries);
	}

	/**
	* Creates a new asset list asset entry with the primary key. Does not add the asset list asset entry to the database.
	*
	* @param assetListAssetEntryId the primary key for the new asset list asset entry
	* @return the new asset list asset entry
	*/
	public static com.liferay.osb.model.AssetListAssetEntry create(
		long assetListAssetEntryId) {
		return getPersistence().create(assetListAssetEntryId);
	}

	/**
	* Removes the asset list asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry that was removed
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry remove(
		long assetListAssetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetListAssetEntryId);
	}

	public static com.liferay.osb.model.AssetListAssetEntry updateImpl(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetListAssetEntry, merge);
	}

	/**
	* Returns the asset list asset entry with the primary key or throws a {@link com.liferay.osb.NoSuchAssetListAssetEntryException} if it could not be found.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry findByPrimaryKey(
		long assetListAssetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetListAssetEntryId);
	}

	/**
	* Returns the asset list asset entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry, or <code>null</code> if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByPrimaryKey(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetListAssetEntryId);
	}

	/**
	* Returns all the asset list asset entries where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @return the matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetListId(
		long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetListId(assetListId);
	}

	/**
	* Returns a range of all the asset list asset entries where assetListId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetListId the asset list ID
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @return the range of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetListId(
		long assetListId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetListId(assetListId, start, end);
	}

	/**
	* Returns an ordered range of all the asset list asset entries where assetListId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetListId the asset list ID
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetListId(
		long assetListId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetListId(assetListId, start, end, orderByComparator);
	}

	/**
	* Returns the first asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry findByAssetListId_First(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetListId_First(assetListId, orderByComparator);
	}

	/**
	* Returns the first asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByAssetListId_First(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetListId_First(assetListId, orderByComparator);
	}

	/**
	* Returns the last asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry findByAssetListId_Last(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetListId_Last(assetListId, orderByComparator);
	}

	/**
	* Returns the last asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByAssetListId_Last(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetListId_Last(assetListId, orderByComparator);
	}

	/**
	* Returns the asset list asset entries before and after the current asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListAssetEntryId the primary key of the current asset list asset entry
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry[] findByAssetListId_PrevAndNext(
		long assetListAssetEntryId, long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetListId_PrevAndNext(assetListAssetEntryId,
			assetListId, orderByComparator);
	}

	/**
	* Returns all the asset list asset entries where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetEntryId(
		long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetEntryId(assetEntryId);
	}

	/**
	* Returns a range of all the asset list asset entries where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @return the range of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetEntryId(
		long assetEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetEntryId(assetEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset list asset entries where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetEntryId(
		long assetEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry findByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the first asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetEntryId_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry findByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetEntryId_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the asset list asset entries before and after the current asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetListAssetEntryId the primary key of the current asset list asset entry
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry[] findByAssetEntryId_PrevAndNext(
		long assetListAssetEntryId, long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId_PrevAndNext(assetListAssetEntryId,
			assetEntryId, orderByComparator);
	}

	/**
	* Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAssetListAssetEntryException} if it could not be found.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry findByALI_AEI(
		long assetListId, long assetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByALI_AEI(assetListId, assetEntryId);
	}

	/**
	* Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByALI_AEI(
		long assetListId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByALI_AEI(assetListId, assetEntryId);
	}

	/**
	* Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry fetchByALI_AEI(
		long assetListId, long assetEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByALI_AEI(assetListId, assetEntryId, retrieveFromCache);
	}

	/**
	* Returns all the asset list asset entries.
	*
	* @return the asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset list asset entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @return the range of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset list asset entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset list asset entries where assetListId = &#63; from the database.
	*
	* @param assetListId the asset list ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetListId(long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetListId(assetListId);
	}

	/**
	* Removes all the asset list asset entries where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetEntryId(assetEntryId);
	}

	/**
	* Removes the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; from the database.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the asset list asset entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry removeByALI_AEI(
		long assetListId, long assetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByALI_AEI(assetListId, assetEntryId);
	}

	/**
	* Removes all the asset list asset entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset list asset entries where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @return the number of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetListId(long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetListId(assetListId);
	}

	/**
	* Returns the number of asset list asset entries where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetEntryId(assetEntryId);
	}

	/**
	* Returns the number of asset list asset entries where assetListId = &#63; and assetEntryId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByALI_AEI(long assetListId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByALI_AEI(assetListId, assetEntryId);
	}

	/**
	* Returns the number of asset list asset entries.
	*
	* @return the number of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetListAssetEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetListAssetEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetListAssetEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetListAssetEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetListAssetEntryPersistence persistence) {
	}

	private static AssetListAssetEntryPersistence _persistence;
}