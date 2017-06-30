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

import com.liferay.osb.model.AssetReceiptSupport;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset receipt support service. This utility wraps {@link AssetReceiptSupportPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptSupportPersistence
 * @see AssetReceiptSupportPersistenceImpl
 * @generated
 */
public class AssetReceiptSupportUtil {
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
	public static void clearCache(AssetReceiptSupport assetReceiptSupport) {
		getPersistence().clearCache(assetReceiptSupport);
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
	public static List<AssetReceiptSupport> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetReceiptSupport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetReceiptSupport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetReceiptSupport update(
		AssetReceiptSupport assetReceiptSupport, boolean merge)
		throws SystemException {
		return getPersistence().update(assetReceiptSupport, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetReceiptSupport update(
		AssetReceiptSupport assetReceiptSupport, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(assetReceiptSupport, merge, serviceContext);
	}

	/**
	* Caches the asset receipt support in the entity cache if it is enabled.
	*
	* @param assetReceiptSupport the asset receipt support
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport) {
		getPersistence().cacheResult(assetReceiptSupport);
	}

	/**
	* Caches the asset receipt supports in the entity cache if it is enabled.
	*
	* @param assetReceiptSupports the asset receipt supports
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceiptSupport> assetReceiptSupports) {
		getPersistence().cacheResult(assetReceiptSupports);
	}

	/**
	* Creates a new asset receipt support with the primary key. Does not add the asset receipt support to the database.
	*
	* @param assetReceiptSupportId the primary key for the new asset receipt support
	* @return the new asset receipt support
	*/
	public static com.liferay.osb.model.AssetReceiptSupport create(
		long assetReceiptSupportId) {
		return getPersistence().create(assetReceiptSupportId);
	}

	/**
	* Removes the asset receipt support with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport remove(
		long assetReceiptSupportId)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetReceiptSupportId);
	}

	public static com.liferay.osb.model.AssetReceiptSupport updateImpl(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetReceiptSupport, merge);
	}

	/**
	* Returns the asset receipt support with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptSupportException} if it could not be found.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByPrimaryKey(
		long assetReceiptSupportId)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetReceiptSupportId);
	}

	/**
	* Returns the asset receipt support with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support, or <code>null</code> if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByPrimaryKey(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetReceiptSupportId);
	}

	/**
	* Returns all the asset receipt supports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the asset receipt supports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the asset receipt supports before and after the current asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param assetReceiptSupportId the primary key of the current asset receipt support
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport[] findByUuid_PrevAndNext(
		long assetReceiptSupportId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(assetReceiptSupportId, uuid,
			orderByComparator);
	}

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByAssetReceiptId(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns a range of all the asset receipt supports where assetReceiptId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByAssetReceiptId(
		long assetReceiptId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetReceiptId(assetReceiptId, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByAssetReceiptId(
		long assetReceiptId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId(assetReceiptId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId_First(assetReceiptId, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetReceiptId_First(assetReceiptId,
			orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId_Last(assetReceiptId, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetReceiptId_Last(assetReceiptId, orderByComparator);
	}

	/**
	* Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptSupportId the primary key of the current asset receipt support
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport[] findByAssetReceiptId_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId_PrevAndNext(assetReceiptSupportId,
			assetReceiptId, orderByComparator);
	}

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK(
		long assetReceiptId, long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK);
	}

	/**
	* Returns a range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK(
		long assetReceiptId, long productClassNameId, long productClassPK,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK(
		long assetReceiptId, long productClassNameId, long productClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK, start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_First(assetReceiptId,
			productClassNameId, productClassPK, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_PCNI_PCPK_First(assetReceiptId,
			productClassNameId, productClassPK, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_Last(assetReceiptId,
			productClassNameId, productClassPK, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_PCNI_PCPK_Last(assetReceiptId,
			productClassNameId, productClassPK, orderByComparator);
	}

	/**
	* Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptSupportId the primary key of the current asset receipt support
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport[] findByARI_PCNI_PCPK_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_PrevAndNext(assetReceiptSupportId,
			assetReceiptId, productClassNameId, productClassPK,
			orderByComparator);
	}

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT(assetReceiptId, startDate, usageType);
	}

	/**
	* Returns a range of all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT(assetReceiptId, startDate, usageType,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT(assetReceiptId, startDate, usageType,
			start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT_First(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_GtSD_UT_First(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT_Last(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_GtSD_UT_Last(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptSupportId the primary key of the current asset receipt support
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport[] findByARI_GtSD_UT_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT_PrevAndNext(assetReceiptSupportId,
			assetReceiptId, startDate, usageType, orderByComparator);
	}

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType);
	}

	/**
	* Returns a range of all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT_First(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_LtSD_GtED_UT_First(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT_Last(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_LtSD_GtED_UT_Last(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptSupportId the primary key of the current asset receipt support
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport[] findByARI_LtSD_GtED_UT_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT_PrevAndNext(assetReceiptSupportId,
			assetReceiptId, startDate, endDate, usageType, orderByComparator);
	}

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate);
	}

	/**
	* Returns a range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_LtSD_GtED_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_LtSD_GtED_First(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_LtSD_GtED_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_PCNI_PCPK_LtSD_GtED_First(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate,
			orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_LtSD_GtED_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_LtSD_GtED_Last(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate,
			orderByComparator);
	}

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_LtSD_GtED_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_PCNI_PCPK_LtSD_GtED_Last(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate,
			orderByComparator);
	}

	/**
	* Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptSupportId the primary key of the current asset receipt support
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport[] findByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		long productClassNameId, long productClassPK, java.util.Date startDate,
		java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(assetReceiptSupportId,
			assetReceiptId, productClassNameId, productClassPK, startDate,
			endDate, orderByComparator);
	}

	/**
	* Returns all the asset receipt supports.
	*
	* @return the asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset receipt supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset receipt supports where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetReceiptId(assetReceiptId);
	}

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK);
	}

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARI_GtSD_UT(long assetReceiptId,
		java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARI_GtSD_UT(assetReceiptId, startDate, usageType);
	}

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARI_LtSD_GtED_UT(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType);
	}

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARI_PCNI_PCPK_LtSD_GtED(long assetReceiptId,
		long productClassNameId, long productClassPK, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate);
	}

	/**
	* Removes all the asset receipt supports from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset receipt supports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK);
	}

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARI_GtSD_UT(long assetReceiptId,
		java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARI_GtSD_UT(assetReceiptId, startDate, usageType);
	}

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARI_LtSD_GtED_UT(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType);
	}

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARI_PCNI_PCPK_LtSD_GtED(long assetReceiptId,
		long productClassNameId, long productClassPK, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate);
	}

	/**
	* Returns the number of asset receipt supports.
	*
	* @return the number of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetReceiptSupportPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetReceiptSupportPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetReceiptSupportPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetReceiptSupportUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetReceiptSupportPersistence persistence) {
	}

	private static AssetReceiptSupportPersistence _persistence;
}