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

import com.liferay.osb.model.AppPricingItem;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app pricing item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingItemPersistenceImpl
 * @see AppPricingItemUtil
 * @generated
 */
public interface AppPricingItemPersistence extends BasePersistence<AppPricingItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppPricingItemUtil} to access the app pricing item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app pricing item in the entity cache if it is enabled.
	*
	* @param appPricingItem the app pricing item
	*/
	public void cacheResult(com.liferay.osb.model.AppPricingItem appPricingItem);

	/**
	* Caches the app pricing items in the entity cache if it is enabled.
	*
	* @param appPricingItems the app pricing items
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppPricingItem> appPricingItems);

	/**
	* Creates a new app pricing item with the primary key. Does not add the app pricing item to the database.
	*
	* @param appPricingItemId the primary key for the new app pricing item
	* @return the new app pricing item
	*/
	public com.liferay.osb.model.AppPricingItem create(long appPricingItemId);

	/**
	* Removes the app pricing item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item that was removed
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem remove(long appPricingItemId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppPricingItem updateImpl(
		com.liferay.osb.model.AppPricingItem appPricingItem, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing item with the primary key or throws a {@link com.liferay.osb.NoSuchAppPricingItemException} if it could not be found.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem findByPrimaryKey(
		long appPricingItemId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item, or <code>null</code> if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByPrimaryKey(
		long appPricingItemId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app pricing items where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @return the matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findByAppPricingId(
		long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app pricing items where appPricingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPricingId the app pricing ID
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @return the range of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findByAppPricingId(
		long appPricingId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app pricing items where appPricingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPricingId the app pricing ID
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findByAppPricingId(
		long appPricingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem findByAppPricingId_First(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByAppPricingId_First(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem findByAppPricingId_Last(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByAppPricingId_Last(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing items before and after the current app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingItemId the primary key of the current app pricing item
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem[] findByAppPricingId_PrevAndNext(
		long appPricingItemId, long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app pricing items where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findByAssetLicenseId(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app pricing items where assetLicenseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetLicenseId the asset license ID
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @return the range of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findByAssetLicenseId(
		long assetLicenseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app pricing items where assetLicenseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetLicenseId the asset license ID
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findByAssetLicenseId(
		long assetLicenseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem findByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem findByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing items before and after the current app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param appPricingItemId the primary key of the current app pricing item
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem[] findByAssetLicenseId_PrevAndNext(
		long appPricingItemId, long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or throws a {@link com.liferay.osb.NoSuchAppPricingItemException} if it could not be found.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem findByAPI_ALI(
		long appPricingId, long assetLicenseId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByAPI_ALI(
		long appPricingId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem fetchByAPI_ALI(
		long appPricingId, long assetLicenseId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app pricing items.
	*
	* @return the app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app pricing items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @return the range of app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app pricing items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricingItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app pricing items where appPricingId = &#63; from the database.
	*
	* @param appPricingId the app pricing ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app pricing items where assetLicenseId = &#63; from the database.
	*
	* @param assetLicenseId the asset license ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; from the database.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the app pricing item that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricingItem removeByAPI_ALI(
		long appPricingId, long assetLicenseId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app pricing items from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app pricing items where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @return the number of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public int countByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app pricing items where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the number of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app pricing items where appPricingId = &#63; and assetLicenseId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the number of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public int countByAPI_ALI(long appPricingId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app pricing items.
	*
	* @return the number of app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}