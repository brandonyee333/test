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

package com.liferay.osb.loop.asset.entry.set.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.asset.entry.set.exception.NoSuchLikeException;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset entry set like service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.loop.asset.entry.set.service.persistence.impl.AssetEntrySetLikePersistenceImpl
 * @see AssetEntrySetLikeUtil
 * @generated
 */
@ProviderType
public interface AssetEntrySetLikePersistence extends BasePersistence<AssetEntrySetLike> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntrySetLikeUtil} to access the asset entry set like persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @return the matching asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId);

	/**
	* Returns a range of all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntrySetId the asset entry set ID
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @return the range of matching asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntrySetId the asset entry set ID
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntrySetId the asset entry set ID
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set like
	* @throws NoSuchLikeException if a matching asset entry set like could not be found
	*/
	public AssetEntrySetLike findByAssetEntrySetId_First(long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	*/
	public AssetEntrySetLike fetchByAssetEntrySetId_First(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator);

	/**
	* Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set like
	* @throws NoSuchLikeException if a matching asset entry set like could not be found
	*/
	public AssetEntrySetLike findByAssetEntrySetId_Last(long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	*/
	public AssetEntrySetLike fetchByAssetEntrySetId_Last(long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator);

	/**
	* Returns the asset entry set likes before and after the current asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetLikePK the primary key of the current asset entry set like
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set like
	* @throws NoSuchLikeException if a asset entry set like with the primary key could not be found
	*/
	public AssetEntrySetLike[] findByAssetEntrySetId_PrevAndNext(
		AssetEntrySetLikePK assetEntrySetLikePK, long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Removes all the asset entry set likes where assetEntrySetId = &#63; from the database.
	*
	* @param assetEntrySetId the asset entry set ID
	*/
	public void removeByAssetEntrySetId(long assetEntrySetId);

	/**
	* Returns the number of asset entry set likes where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @return the number of matching asset entry set likes
	*/
	public int countByAssetEntrySetId(long assetEntrySetId);

	/**
	* Caches the asset entry set like in the entity cache if it is enabled.
	*
	* @param assetEntrySetLike the asset entry set like
	*/
	public void cacheResult(AssetEntrySetLike assetEntrySetLike);

	/**
	* Caches the asset entry set likes in the entity cache if it is enabled.
	*
	* @param assetEntrySetLikes the asset entry set likes
	*/
	public void cacheResult(
		java.util.List<AssetEntrySetLike> assetEntrySetLikes);

	/**
	* Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	*
	* @param assetEntrySetLikePK the primary key for the new asset entry set like
	* @return the new asset entry set like
	*/
	public AssetEntrySetLike create(AssetEntrySetLikePK assetEntrySetLikePK);

	/**
	* Removes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like that was removed
	* @throws NoSuchLikeException if a asset entry set like with the primary key could not be found
	*/
	public AssetEntrySetLike remove(AssetEntrySetLikePK assetEntrySetLikePK)
		throws NoSuchLikeException;

	public AssetEntrySetLike updateImpl(AssetEntrySetLike assetEntrySetLike);

	/**
	* Returns the asset entry set like with the primary key or throws a {@link NoSuchLikeException} if it could not be found.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like
	* @throws NoSuchLikeException if a asset entry set like with the primary key could not be found
	*/
	public AssetEntrySetLike findByPrimaryKey(
		AssetEntrySetLikePK assetEntrySetLikePK) throws NoSuchLikeException;

	/**
	* Returns the asset entry set like with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like, or <code>null</code> if a asset entry set like with the primary key could not be found
	*/
	public AssetEntrySetLike fetchByPrimaryKey(
		AssetEntrySetLikePK assetEntrySetLikePK);

	@Override
	public java.util.Map<java.io.Serializable, AssetEntrySetLike> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset entry set likes.
	*
	* @return the asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findAll();

	/**
	* Returns a range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @return the range of asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findAll(int start, int end);

	/**
	* Returns an ordered range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset entry set likes
	*/
	public java.util.List<AssetEntrySetLike> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset entry set likes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset entry set likes.
	*
	* @return the number of asset entry set likes
	*/
	public int countAll();

	public java.util.Set<java.lang.String> getCompoundPKColumnNames();
}