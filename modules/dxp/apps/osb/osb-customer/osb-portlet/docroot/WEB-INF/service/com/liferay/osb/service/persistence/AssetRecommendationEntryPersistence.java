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

import com.liferay.osb.model.AssetRecommendationEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset recommendation entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetRecommendationEntryPersistenceImpl
 * @see AssetRecommendationEntryUtil
 * @generated
 */
public interface AssetRecommendationEntryPersistence extends BasePersistence<AssetRecommendationEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetRecommendationEntryUtil} to access the asset recommendation entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset recommendation entry in the entity cache if it is enabled.
	*
	* @param assetRecommendationEntry the asset recommendation entry
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry);

	/**
	* Caches the asset recommendation entries in the entity cache if it is enabled.
	*
	* @param assetRecommendationEntries the asset recommendation entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetRecommendationEntry> assetRecommendationEntries);

	/**
	* Creates a new asset recommendation entry with the primary key. Does not add the asset recommendation entry to the database.
	*
	* @param assetRecommendationEntryId the primary key for the new asset recommendation entry
	* @return the new asset recommendation entry
	*/
	public com.liferay.osb.model.AssetRecommendationEntry create(
		long assetRecommendationEntryId);

	/**
	* Removes the asset recommendation entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationEntryId the primary key of the asset recommendation entry
	* @return the asset recommendation entry that was removed
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry remove(
		long assetRecommendationEntryId)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetRecommendationEntry updateImpl(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entry with the primary key or throws a {@link com.liferay.osb.NoSuchAssetRecommendationEntryException} if it could not be found.
	*
	* @param assetRecommendationEntryId the primary key of the asset recommendation entry
	* @return the asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByPrimaryKey(
		long assetRecommendationEntryId)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetRecommendationEntryId the primary key of the asset recommendation entry
	* @return the asset recommendation entry, or <code>null</code> if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByPrimaryKey(
		long assetRecommendationEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset recommendation entries where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @return the matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByARSI_GtVAPC(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset recommendation entries where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @return the range of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByARSI_GtVAPC(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset recommendation entries where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByARSI_GtVAPC(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByARSI_GtVAPC_First(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByARSI_GtVAPC_First(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByARSI_GtVAPC_Last(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByARSI_GtVAPC_Last(
		long assetRecommendationSetId, int viewedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entries before and after the current asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationEntryId the primary key of the current asset recommendation entry
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry[] findByARSI_GtVAPC_PrevAndNext(
		long assetRecommendationEntryId, long assetRecommendationSetId,
		int viewedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset recommendation entries where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @return the matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByARSI_GtPAPC(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset recommendation entries where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @return the range of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByARSI_GtPAPC(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset recommendation entries where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByARSI_GtPAPC(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByARSI_GtPAPC_First(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByARSI_GtPAPC_First(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByARSI_GtPAPC_Last(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByARSI_GtPAPC_Last(
		long assetRecommendationSetId, int purchasedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entries before and after the current asset recommendation entry in the ordered set where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationEntryId the primary key of the current asset recommendation entry
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry[] findByARSI_GtPAPC_PrevAndNext(
		long assetRecommendationEntryId, long assetRecommendationSetId,
		int purchasedAlsoPurchasedCount,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset recommendation entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset recommendation entries where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @return the range of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset recommendation entries where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset recommendation entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset recommendation entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset recommendation entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset recommendation entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entries before and after the current asset recommendation entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetRecommendationEntryId the primary key of the current asset recommendation entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a asset recommendation entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry[] findByC_C_PrevAndNext(
		long assetRecommendationEntryId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entry where assetRecommendationSetId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetRecommendationEntryException} if it could not be found.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset recommendation entry
	* @throws com.liferay.osb.NoSuchAssetRecommendationEntryException if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry findByARSI_C_C(
		long assetRecommendationSetId, long classNameId, long classPK)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entry where assetRecommendationSetId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByARSI_C_C(
		long assetRecommendationSetId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset recommendation entry where assetRecommendationSetId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset recommendation entry, or <code>null</code> if a matching asset recommendation entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry fetchByARSI_C_C(
		long assetRecommendationSetId, long classNameId, long classPK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset recommendation entries.
	*
	* @return the asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset recommendation entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @return the range of asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset recommendation entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation entries
	* @param end the upper bound of the range of asset recommendation entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetRecommendationEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset recommendation entries where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63; from the database.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARSI_GtVAPC(long assetRecommendationSetId,
		int viewedAlsoPurchasedCount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset recommendation entries where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63; from the database.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARSI_GtPAPC(long assetRecommendationSetId,
		int purchasedAlsoPurchasedCount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset recommendation entries where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset recommendation entry where assetRecommendationSetId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the asset recommendation entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetRecommendationEntry removeByARSI_C_C(
		long assetRecommendationSetId, long classNameId, long classPK)
		throws com.liferay.osb.NoSuchAssetRecommendationEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset recommendation entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset recommendation entries where assetRecommendationSetId = &#63; and viewedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param viewedAlsoPurchasedCount the viewed also purchased count
	* @return the number of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByARSI_GtVAPC(long assetRecommendationSetId,
		int viewedAlsoPurchasedCount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset recommendation entries where assetRecommendationSetId = &#63; and purchasedAlsoPurchasedCount &gt; &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param purchasedAlsoPurchasedCount the purchased also purchased count
	* @return the number of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByARSI_GtPAPC(long assetRecommendationSetId,
		int purchasedAlsoPurchasedCount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset recommendation entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset recommendation entries where assetRecommendationSetId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param assetRecommendationSetId the asset recommendation set ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByARSI_C_C(long assetRecommendationSetId, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset recommendation entries.
	*
	* @return the number of asset recommendation entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}