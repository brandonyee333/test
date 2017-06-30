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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset list asset entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListAssetEntryPersistenceImpl
 * @see AssetListAssetEntryUtil
 * @generated
 */
public interface AssetListAssetEntryPersistence extends BasePersistence<AssetListAssetEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetListAssetEntryUtil} to access the asset list asset entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset list asset entry in the entity cache if it is enabled.
	*
	* @param assetListAssetEntry the asset list asset entry
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry);

	/**
	* Caches the asset list asset entries in the entity cache if it is enabled.
	*
	* @param assetListAssetEntries the asset list asset entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetListAssetEntry> assetListAssetEntries);

	/**
	* Creates a new asset list asset entry with the primary key. Does not add the asset list asset entry to the database.
	*
	* @param assetListAssetEntryId the primary key for the new asset list asset entry
	* @return the new asset list asset entry
	*/
	public com.liferay.osb.model.AssetListAssetEntry create(
		long assetListAssetEntryId);

	/**
	* Removes the asset list asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry that was removed
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry remove(
		long assetListAssetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetListAssetEntry updateImpl(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list asset entry with the primary key or throws a {@link com.liferay.osb.NoSuchAssetListAssetEntryException} if it could not be found.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry findByPrimaryKey(
		long assetListAssetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list asset entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry, or <code>null</code> if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByPrimaryKey(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset list asset entries where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @return the matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetListId(
		long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetListId(
		long assetListId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetListId(
		long assetListId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry findByAssetListId_First(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByAssetListId_First(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry findByAssetListId_Last(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset list asset entry in the ordered set where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByAssetListId_Last(
		long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetListAssetEntry[] findByAssetListId_PrevAndNext(
		long assetListAssetEntryId, long assetListId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset list asset entries where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetEntryId(
		long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetEntryId(
		long assetEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findByAssetEntryId(
		long assetEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry findByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry findByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset list asset entry in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetListAssetEntry[] findByAssetEntryId_PrevAndNext(
		long assetListAssetEntryId, long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAssetListAssetEntryException} if it could not be found.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the matching asset list asset entry
	* @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry findByALI_AEI(
		long assetListId, long assetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByALI_AEI(
		long assetListId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry fetchByALI_AEI(
		long assetListId, long assetEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset list asset entries.
	*
	* @return the asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetListAssetEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset list asset entries where assetListId = &#63; from the database.
	*
	* @param assetListId the asset list ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetListId(long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset list asset entries where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; from the database.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the asset list asset entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetListAssetEntry removeByALI_AEI(
		long assetListId, long assetEntryId)
		throws com.liferay.osb.NoSuchAssetListAssetEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset list asset entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset list asset entries where assetListId = &#63;.
	*
	* @param assetListId the asset list ID
	* @return the number of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetListId(long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset list asset entries where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset list asset entries where assetListId = &#63; and assetEntryId = &#63;.
	*
	* @param assetListId the asset list ID
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByALI_AEI(long assetListId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset list asset entries.
	*
	* @return the number of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}