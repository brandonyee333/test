/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.asset.entry.set.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.asset.entry.set.exception.NoSuchLikeException;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the asset entry set like service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeUtil
 * @generated
 */
@ProviderType
public interface AssetEntrySetLikePersistence
	extends BasePersistence<AssetEntrySetLike> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntrySetLikeUtil} to access the asset entry set like persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, AssetEntrySetLike> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetLikeModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetLikeModelImpl</code>.
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
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
			orderByComparator);

	/**
	 * Returns an ordered range of all the asset entry set likes where assetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetLikeModelImpl</code>.
	 * </p>
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry set likes
	 */
	public java.util.List<AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set like
	 * @throws NoSuchLikeException if a matching asset entry set like could not be found
	 */
	public AssetEntrySetLike findByAssetEntrySetId_First(
			long assetEntrySetId,
			com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
				orderByComparator)
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
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
			orderByComparator);

	/**
	 * Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set like
	 * @throws NoSuchLikeException if a matching asset entry set like could not be found
	 */
	public AssetEntrySetLike findByAssetEntrySetId_Last(
			long assetEntrySetId,
			com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
				orderByComparator)
		throws NoSuchLikeException;

	/**
	 * Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	 */
	public AssetEntrySetLike fetchByAssetEntrySetId_Last(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
			orderByComparator);

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
			com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
				orderByComparator)
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
	 * Returns the asset entry set like with the primary key or throws a <code>NoSuchLikeException</code> if it could not be found.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like
	 * @throws NoSuchLikeException if a asset entry set like with the primary key could not be found
	 */
	public AssetEntrySetLike findByPrimaryKey(
			AssetEntrySetLikePK assetEntrySetLikePK)
		throws NoSuchLikeException;

	/**
	 * Returns the asset entry set like with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like, or <code>null</code> if a asset entry set like with the primary key could not be found
	 */
	public AssetEntrySetLike fetchByPrimaryKey(
		AssetEntrySetLikePK assetEntrySetLikePK);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetLikeModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetLikeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry set likes
	 */
	public java.util.List<AssetEntrySetLike> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
			orderByComparator);

	/**
	 * Returns an ordered range of all the asset entry set likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetLikeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of asset entry set likes
	 */
	public java.util.List<AssetEntrySetLike> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySetLike>
			orderByComparator,
		boolean useFinderCache);

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

	public Set<String> getCompoundPKColumnNames();

}