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

import com.liferay.osb.loop.asset.entry.set.exception.NoSuchAssetEntrySetException;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset entry set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.loop.asset.entry.set.service.persistence.impl.AssetEntrySetPersistenceImpl
 * @see AssetEntrySetUtil
 * @generated
 */
@ProviderType
public interface AssetEntrySetPersistence extends BasePersistence<AssetEntrySet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntrySetUtil} to access the asset entry set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId);

	/**
	* Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByParentAssetEntrySetId_First(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByParentAssetEntrySetId_First(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] findByParentAssetEntrySetId_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId);

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] filterFindByParentAssetEntrySetId_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Removes all the asset entry sets where parentAssetEntrySetId = &#63; from the database.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	*/
	public void removeByParentAssetEntrySetId(long parentAssetEntrySetId);

	/**
	* Returns the number of asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets
	*/
	public int countByParentAssetEntrySetId(long parentAssetEntrySetId);

	/**
	* Returns the number of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets that the user has permission to view
	*/
	public int filterCountByParentAssetEntrySetId(long parentAssetEntrySetId);

	/**
	* Returns all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId);

	/**
	* Returns a range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByGtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByGtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByGtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByGtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] findByGtCT_PAESI_PrevAndNext(long assetEntrySetId,
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns all the asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId);

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] filterFindByGtCT_PAESI_PrevAndNext(
		long assetEntrySetId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Removes all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63; from the database.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	*/
	public void removeByGtCT_PAESI(long createTime, long parentAssetEntrySetId);

	/**
	* Returns the number of asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets
	*/
	public int countByGtCT_PAESI(long createTime, long parentAssetEntrySetId);

	/**
	* Returns the number of asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets that the user has permission to view
	*/
	public int filterCountByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId);

	/**
	* Returns all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId);

	/**
	* Returns a range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByLtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByLtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByLtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByLtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] findByLtCT_PAESI_PrevAndNext(long assetEntrySetId,
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns all the asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId);

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] filterFindByLtCT_PAESI_PrevAndNext(
		long assetEntrySetId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Removes all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63; from the database.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	*/
	public void removeByLtCT_PAESI(long createTime, long parentAssetEntrySetId);

	/**
	* Returns the number of asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets
	*/
	public int countByLtCT_PAESI(long createTime, long parentAssetEntrySetId);

	/**
	* Returns the number of asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets that the user has permission to view
	*/
	public int filterCountByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId);

	/**
	* Returns all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId);

	/**
	* Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByPAESI_CCNI_First(long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByPAESI_CCNI_First(long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByPAESI_CCNI_Last(long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByPAESI_CCNI_Last(long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] findByPAESI_CCNI_PrevAndNext(long assetEntrySetId,
		long parentAssetEntrySetId, long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId);

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] filterFindByPAESI_CCNI_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Removes all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; from the database.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	*/
	public void removeByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId);

	/**
	* Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the number of matching asset entry sets
	*/
	public int countByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId);

	/**
	* Returns the number of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the number of matching asset entry sets that the user has permission to view
	*/
	public int filterCountByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId);

	/**
	* Returns all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK(long classNameId,
		long classPK);

	/**
	* Returns a range of all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK(long classNameId,
		long classPK, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByCNI_CPK_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByCNI_CPK_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByCNI_CPK_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByCNI_CPK_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] findByCNI_CPK_PrevAndNext(long assetEntrySetId,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByCNI_CPK(long classNameId,
		long classPK);

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByCNI_CPK(long classNameId,
		long classPK, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByCNI_CPK(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] filterFindByCNI_CPK_PrevAndNext(
		long assetEntrySetId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Removes all the asset entry sets where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public void removeByCNI_CPK(long classNameId, long classPK);

	/**
	* Returns the number of asset entry sets where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching asset entry sets
	*/
	public int countByCNI_CPK(long classNameId, long classPK);

	/**
	* Returns the number of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching asset entry sets that the user has permission to view
	*/
	public int filterCountByCNI_CPK(long classNameId, long classPK);

	/**
	* Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or throws a {@link NoSuchAssetEntrySetException} if it could not be found.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class pk
	* @return the matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class pk
	* @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK);

	/**
	* Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK, boolean retrieveFromCache);

	/**
	* Removes the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; from the database.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class pk
	* @return the asset entry set that was removed
	*/
	public AssetEntrySet removeByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class pk
	* @return the number of matching asset entry sets
	*/
	public int countByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK);

	/**
	* Returns the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; or throws a {@link NoSuchAssetEntrySetException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param title the title
	* @return the matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByCNI_CPK_Title(long classNameId, long classPK,
		java.lang.String title) throws NoSuchAssetEntrySetException;

	/**
	* Returns the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param title the title
	* @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByCNI_CPK_Title(long classNameId, long classPK,
		java.lang.String title);

	/**
	* Returns the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param title the title
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByCNI_CPK_Title(long classNameId, long classPK,
		java.lang.String title, boolean retrieveFromCache);

	/**
	* Removes the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param title the title
	* @return the asset entry set that was removed
	*/
	public AssetEntrySet removeByCNI_CPK_Title(long classNameId, long classPK,
		java.lang.String title) throws NoSuchAssetEntrySetException;

	/**
	* Returns the number of asset entry sets where classNameId = &#63; and classPK = &#63; and title = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param title the title
	* @return the number of matching asset entry sets
	*/
	public int countByCNI_CPK_Title(long classNameId, long classPK,
		java.lang.String title);

	/**
	* Returns all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK_Type(long classNameId,
		long classPK, int type);

	/**
	* Returns a range of all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK_Type(long classNameId,
		long classPK, int type, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK_Type(long classNameId,
		long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset entry sets
	*/
	public java.util.List<AssetEntrySet> findByCNI_CPK_Type(long classNameId,
		long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByCNI_CPK_Type_First(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByCNI_CPK_Type_First(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	*/
	public AssetEntrySet findByCNI_CPK_Type_Last(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	*/
	public AssetEntrySet fetchByCNI_CPK_Type_Last(long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] findByCNI_CPK_Type_PrevAndNext(
		long assetEntrySetId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByCNI_CPK_Type(
		long classNameId, long classPK, int type);

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	*/
	public java.util.List<AssetEntrySet> filterFindByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet[] filterFindByCNI_CPK_Type_PrevAndNext(
		long assetEntrySetId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator)
		throws NoSuchAssetEntrySetException;

	/**
	* Removes all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	*/
	public void removeByCNI_CPK_Type(long classNameId, long classPK, int type);

	/**
	* Returns the number of asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the number of matching asset entry sets
	*/
	public int countByCNI_CPK_Type(long classNameId, long classPK, int type);

	/**
	* Returns the number of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the number of matching asset entry sets that the user has permission to view
	*/
	public int filterCountByCNI_CPK_Type(long classNameId, long classPK,
		int type);

	/**
	* Caches the asset entry set in the entity cache if it is enabled.
	*
	* @param assetEntrySet the asset entry set
	*/
	public void cacheResult(AssetEntrySet assetEntrySet);

	/**
	* Caches the asset entry sets in the entity cache if it is enabled.
	*
	* @param assetEntrySets the asset entry sets
	*/
	public void cacheResult(java.util.List<AssetEntrySet> assetEntrySets);

	/**
	* Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	*
	* @param assetEntrySetId the primary key for the new asset entry set
	* @return the new asset entry set
	*/
	public AssetEntrySet create(long assetEntrySetId);

	/**
	* Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set that was removed
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet remove(long assetEntrySetId)
		throws NoSuchAssetEntrySetException;

	public AssetEntrySet updateImpl(AssetEntrySet assetEntrySet);

	/**
	* Returns the asset entry set with the primary key or throws a {@link NoSuchAssetEntrySetException} if it could not be found.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set
	* @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet findByPrimaryKey(long assetEntrySetId)
		throws NoSuchAssetEntrySetException;

	/**
	* Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	*/
	public AssetEntrySet fetchByPrimaryKey(long assetEntrySetId);

	@Override
	public java.util.Map<java.io.Serializable, AssetEntrySet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset entry sets.
	*
	* @return the asset entry sets
	*/
	public java.util.List<AssetEntrySet> findAll();

	/**
	* Returns a range of all the asset entry sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of asset entry sets
	*/
	public java.util.List<AssetEntrySet> findAll(int start, int end);

	/**
	* Returns an ordered range of all the asset entry sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry sets
	*/
	public java.util.List<AssetEntrySet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator);

	/**
	* Returns an ordered range of all the asset entry sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset entry sets
	*/
	public java.util.List<AssetEntrySet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntrySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset entry sets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset entry sets.
	*
	* @return the number of asset entry sets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}