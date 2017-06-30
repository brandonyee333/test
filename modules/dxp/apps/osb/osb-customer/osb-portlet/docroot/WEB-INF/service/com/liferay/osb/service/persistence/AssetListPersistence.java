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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListPersistenceImpl
 * @see AssetListUtil
 * @generated
 */
public interface AssetListPersistence extends BasePersistence<AssetList> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetListUtil} to access the asset list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset list in the entity cache if it is enabled.
	*
	* @param assetList the asset list
	*/
	public void cacheResult(com.liferay.osb.model.AssetList assetList);

	/**
	* Caches the asset lists in the entity cache if it is enabled.
	*
	* @param assetLists the asset lists
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetList> assetLists);

	/**
	* Creates a new asset list with the primary key. Does not add the asset list to the database.
	*
	* @param assetListId the primary key for the new asset list
	* @return the new asset list
	*/
	public com.liferay.osb.model.AssetList create(long assetListId);

	/**
	* Removes the asset list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list that was removed
	* @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList remove(long assetListId)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetList updateImpl(
		com.liferay.osb.model.AssetList assetList, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list with the primary key or throws a {@link com.liferay.osb.NoSuchAssetListException} if it could not be found.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList findByPrimaryKey(long assetListId)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetListId the primary key of the asset list
	* @return the asset list, or <code>null</code> if a asset list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList fetchByPrimaryKey(long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset lists where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @return the matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetList> findByAssetCategoryId(
		long assetCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetList> findByAssetCategoryId(
		long assetCategoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetList> findByAssetCategoryId(
		long assetCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList findByAssetCategoryId_First(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList fetchByAssetCategoryId_First(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList findByAssetCategoryId_Last(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset list in the ordered set where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList fetchByAssetCategoryId_Last(
		long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetList[] findByAssetCategoryId_PrevAndNext(
		long assetListId, long assetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list where assetCategoryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAssetListException} if it could not be found.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the matching asset list
	* @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList findByACI_T(long assetCategoryId,
		int type)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list where assetCategoryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList fetchByACI_T(long assetCategoryId,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list where assetCategoryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset list, or <code>null</code> if a matching asset list could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList fetchByACI_T(long assetCategoryId,
		int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset lists.
	*
	* @return the asset lists
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetList> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetList> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetList> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset lists where assetCategoryId = &#63; from the database.
	*
	* @param assetCategoryId the asset category ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetCategoryId(long assetCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset list where assetCategoryId = &#63; and type = &#63; from the database.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the asset list that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetList removeByACI_T(long assetCategoryId,
		int type)
		throws com.liferay.osb.NoSuchAssetListException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset lists from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset lists where assetCategoryId = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @return the number of matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetCategoryId(long assetCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset lists where assetCategoryId = &#63; and type = &#63;.
	*
	* @param assetCategoryId the asset category ID
	* @param type the type
	* @return the number of matching asset lists
	* @throws SystemException if a system exception occurred
	*/
	public int countByACI_T(long assetCategoryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset lists.
	*
	* @return the number of asset lists
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}