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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app pricing item service. This utility wraps {@link AppPricingItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingItemPersistence
 * @see AppPricingItemPersistenceImpl
 * @generated
 */
public class AppPricingItemUtil {
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
	public static void clearCache(AppPricingItem appPricingItem) {
		getPersistence().clearCache(appPricingItem);
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
	public static List<AppPricingItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppPricingItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppPricingItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppPricingItem update(AppPricingItem appPricingItem,
		boolean merge) throws SystemException {
		return getPersistence().update(appPricingItem, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppPricingItem update(AppPricingItem appPricingItem,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appPricingItem, merge, serviceContext);
	}

	/**
	* Caches the app pricing item in the entity cache if it is enabled.
	*
	* @param appPricingItem the app pricing item
	*/
	public static void cacheResult(
		com.liferay.osb.model.AppPricingItem appPricingItem) {
		getPersistence().cacheResult(appPricingItem);
	}

	/**
	* Caches the app pricing items in the entity cache if it is enabled.
	*
	* @param appPricingItems the app pricing items
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppPricingItem> appPricingItems) {
		getPersistence().cacheResult(appPricingItems);
	}

	/**
	* Creates a new app pricing item with the primary key. Does not add the app pricing item to the database.
	*
	* @param appPricingItemId the primary key for the new app pricing item
	* @return the new app pricing item
	*/
	public static com.liferay.osb.model.AppPricingItem create(
		long appPricingItemId) {
		return getPersistence().create(appPricingItemId);
	}

	/**
	* Removes the app pricing item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item that was removed
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem remove(
		long appPricingItemId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appPricingItemId);
	}

	public static com.liferay.osb.model.AppPricingItem updateImpl(
		com.liferay.osb.model.AppPricingItem appPricingItem, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appPricingItem, merge);
	}

	/**
	* Returns the app pricing item with the primary key or throws a {@link com.liferay.osb.NoSuchAppPricingItemException} if it could not be found.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem findByPrimaryKey(
		long appPricingItemId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appPricingItemId);
	}

	/**
	* Returns the app pricing item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item, or <code>null</code> if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByPrimaryKey(
		long appPricingItemId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appPricingItemId);
	}

	/**
	* Returns all the app pricing items where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @return the matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAppPricingId(
		long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppPricingId(appPricingId);
	}

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
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAppPricingId(
		long appPricingId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppPricingId(appPricingId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAppPricingId(
		long appPricingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId(appPricingId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem findByAppPricingId_First(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId_First(appPricingId, orderByComparator);
	}

	/**
	* Returns the first app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByAppPricingId_First(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppPricingId_First(appPricingId, orderByComparator);
	}

	/**
	* Returns the last app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem findByAppPricingId_Last(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId_Last(appPricingId, orderByComparator);
	}

	/**
	* Returns the last app pricing item in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByAppPricingId_Last(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppPricingId_Last(appPricingId, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppPricingItem[] findByAppPricingId_PrevAndNext(
		long appPricingItemId, long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId_PrevAndNext(appPricingItemId,
			appPricingId, orderByComparator);
	}

	/**
	* Returns all the app pricing items where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAssetLicenseId(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetLicenseId(assetLicenseId);
	}

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
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAssetLicenseId(
		long assetLicenseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetLicenseId(assetLicenseId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAssetLicenseId(
		long assetLicenseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId(assetLicenseId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem findByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId_First(assetLicenseId, orderByComparator);
	}

	/**
	* Returns the first app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetLicenseId_First(assetLicenseId,
			orderByComparator);
	}

	/**
	* Returns the last app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem findByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId_Last(assetLicenseId, orderByComparator);
	}

	/**
	* Returns the last app pricing item in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetLicenseId_Last(assetLicenseId, orderByComparator);
	}

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
	public static com.liferay.osb.model.AppPricingItem[] findByAssetLicenseId_PrevAndNext(
		long appPricingItemId, long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId_PrevAndNext(appPricingItemId,
			assetLicenseId, orderByComparator);
	}

	/**
	* Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or throws a {@link com.liferay.osb.NoSuchAppPricingItemException} if it could not be found.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the matching app pricing item
	* @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem findByAPI_ALI(
		long appPricingId, long assetLicenseId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAPI_ALI(appPricingId, assetLicenseId);
	}

	/**
	* Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByAPI_ALI(
		long appPricingId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAPI_ALI(appPricingId, assetLicenseId);
	}

	/**
	* Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem fetchByAPI_ALI(
		long appPricingId, long assetLicenseId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAPI_ALI(appPricingId, assetLicenseId,
			retrieveFromCache);
	}

	/**
	* Returns all the app pricing items.
	*
	* @return the app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app pricing items where appPricingId = &#63; from the database.
	*
	* @param appPricingId the app pricing ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppPricingId(appPricingId);
	}

	/**
	* Removes all the app pricing items where assetLicenseId = &#63; from the database.
	*
	* @param assetLicenseId the asset license ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetLicenseId(assetLicenseId);
	}

	/**
	* Removes the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; from the database.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the app pricing item that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem removeByAPI_ALI(
		long appPricingId, long assetLicenseId)
		throws com.liferay.osb.NoSuchAppPricingItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByAPI_ALI(appPricingId, assetLicenseId);
	}

	/**
	* Removes all the app pricing items from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app pricing items where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @return the number of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppPricingId(appPricingId);
	}

	/**
	* Returns the number of app pricing items where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the number of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns the number of app pricing items where appPricingId = &#63; and assetLicenseId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param assetLicenseId the asset license ID
	* @return the number of matching app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAPI_ALI(long appPricingId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAPI_ALI(appPricingId, assetLicenseId);
	}

	/**
	* Returns the number of app pricing items.
	*
	* @return the number of app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppPricingItemPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppPricingItemPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppPricingItemPersistence.class.getName());

			ReferenceRegistry.registerReference(AppPricingItemUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppPricingItemPersistence persistence) {
	}

	private static AppPricingItemPersistence _persistence;
}