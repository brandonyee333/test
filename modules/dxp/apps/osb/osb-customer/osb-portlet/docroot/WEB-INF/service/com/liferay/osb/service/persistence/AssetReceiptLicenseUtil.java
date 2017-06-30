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

import com.liferay.osb.model.AssetReceiptLicense;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset receipt license service. This utility wraps {@link AssetReceiptLicensePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicensePersistence
 * @see AssetReceiptLicensePersistenceImpl
 * @generated
 */
public class AssetReceiptLicenseUtil {
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
	public static void clearCache(AssetReceiptLicense assetReceiptLicense) {
		getPersistence().clearCache(assetReceiptLicense);
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
	public static List<AssetReceiptLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetReceiptLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetReceiptLicense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetReceiptLicense update(
		AssetReceiptLicense assetReceiptLicense, boolean merge)
		throws SystemException {
		return getPersistence().update(assetReceiptLicense, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetReceiptLicense update(
		AssetReceiptLicense assetReceiptLicense, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(assetReceiptLicense, merge, serviceContext);
	}

	/**
	* Caches the asset receipt license in the entity cache if it is enabled.
	*
	* @param assetReceiptLicense the asset receipt license
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense) {
		getPersistence().cacheResult(assetReceiptLicense);
	}

	/**
	* Caches the asset receipt licenses in the entity cache if it is enabled.
	*
	* @param assetReceiptLicenses the asset receipt licenses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceiptLicense> assetReceiptLicenses) {
		getPersistence().cacheResult(assetReceiptLicenses);
	}

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	public static com.liferay.osb.model.AssetReceiptLicense create(
		long assetReceiptLicenseId) {
		return getPersistence().create(assetReceiptLicenseId);
	}

	/**
	* Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense remove(
		long assetReceiptLicenseId)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetReceiptLicenseId);
	}

	public static com.liferay.osb.model.AssetReceiptLicense updateImpl(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetReceiptLicense, merge);
	}

	/**
	* Returns the asset receipt license with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptLicenseException} if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByPrimaryKey(
		long assetReceiptLicenseId)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetReceiptLicenseId);
	}

	/**
	* Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByPrimaryKey(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetReceiptLicenseId);
	}

	/**
	* Returns all the asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the asset receipt licenses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByUuid_PrevAndNext(
		long assetReceiptLicenseId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(assetReceiptLicenseId, uuid,
			orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetReceiptId(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns a range of all the asset receipt licenses where assetReceiptId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetReceiptId(
		long assetReceiptId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetReceiptId(assetReceiptId, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetReceiptId(
		long assetReceiptId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId(assetReceiptId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId_First(assetReceiptId, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetReceiptId_First(assetReceiptId,
			orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId_Last(assetReceiptId, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetReceiptId_Last(assetReceiptId, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByAssetReceiptId_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetReceiptId_PrevAndNext(assetReceiptLicenseId,
			assetReceiptId, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetLicenseId(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns a range of all the asset receipt licenses where assetLicenseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetLicenseId the asset license ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetLicenseId(
		long assetLicenseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetLicenseId(assetLicenseId, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where assetLicenseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetLicenseId the asset license ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetLicenseId(
		long assetLicenseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId(assetLicenseId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId_First(assetLicenseId, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetLicenseId_First(assetLicenseId,
			orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId_Last(assetLicenseId, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetLicenseId_Last(assetLicenseId, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByAssetLicenseId_PrevAndNext(
		long assetReceiptLicenseId, long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetLicenseId_PrevAndNext(assetReceiptLicenseId,
			assetLicenseId, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED(assetReceiptId, startDate, endDate);
	}

	/**
	* Returns a range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED(assetReceiptId, startDate, endDate,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED(assetReceiptId, startDate, endDate,
			start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_First(assetReceiptId, startDate,
			endDate, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_LtSD_GtED_First(assetReceiptId, startDate,
			endDate, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_Last(assetReceiptId, startDate,
			endDate, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_LtSD_GtED_Last(assetReceiptId, startDate,
			endDate, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByARI_LtSD_GtED_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_PrevAndNext(assetReceiptLicenseId,
			assetReceiptId, startDate, endDate, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT(assetReceiptId, startDate, usageType);
	}

	/**
	* Returns a range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT(assetReceiptId, startDate, usageType,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT(assetReceiptId, startDate, usageType,
			start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT_First(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_GtSD_UT_First(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT_Last(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_GtSD_UT_Last(assetReceiptId, startDate,
			usageType, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByARI_GtSD_UT_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_GtSD_UT_PrevAndNext(assetReceiptLicenseId,
			assetReceiptId, startDate, usageType, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PCN(
		long ownerClassNameId, long ownerClassPK, long productClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId);
	}

	/**
	* Returns a range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PCN(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PCN(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId, start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PCN_First(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PCN_First(ownerClassNameId, ownerClassPK,
			productClassNameId, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PCN_First(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCN_OCP_PCN_First(ownerClassNameId, ownerClassPK,
			productClassNameId, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PCN_Last(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PCN_Last(ownerClassNameId, ownerClassPK,
			productClassNameId, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PCN_Last(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCN_OCP_PCN_Last(ownerClassNameId, ownerClassPK,
			productClassNameId, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByOCN_OCP_PCN_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PCN_PrevAndNext(assetReceiptLicenseId,
			ownerClassNameId, ownerClassPK, productClassNameId,
			orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId);
	}

	/**
	* Returns a range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_First(ownerClassNameId, ownerClassPK,
			productId, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCN_OCP_PI_First(ownerClassNameId, ownerClassPK,
			productId, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_Last(ownerClassNameId, ownerClassPK,
			productId, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCN_OCP_PI_Last(ownerClassNameId, ownerClassPK,
			productId, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByOCN_OCP_PI_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_PrevAndNext(assetReceiptLicenseId,
			ownerClassNameId, ownerClassPK, productId, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType);
	}

	/**
	* Returns a range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, start, end, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT_First(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_LtSD_GtED_UT_First(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT_Last(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByARI_LtSD_GtED_UT_Last(assetReceiptId, startDate,
			endDate, usageType, orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByARI_LtSD_GtED_UT_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByARI_LtSD_GtED_UT_PrevAndNext(assetReceiptLicenseId,
			assetReceiptId, startDate, endDate, usageType, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime);
	}

	/**
	* Returns a range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime, start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_LT_LL_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_LT_LL_First(ownerClassNameId,
			ownerClassPK, productId, licenseType, licenseLifetime,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_LT_LL_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCN_OCP_PI_LT_LL_First(ownerClassNameId,
			ownerClassPK, productId, licenseType, licenseLifetime,
			orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_LT_LL_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_LT_LL_Last(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime, orderByComparator);
	}

	/**
	* Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_LT_LL_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCN_OCP_PI_LT_LL_Last(ownerClassNameId,
			ownerClassPK, productId, licenseType, licenseLifetime,
			orderByComparator);
	}

	/**
	* Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param assetReceiptLicenseId the primary key of the current asset receipt license
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptLicense[] findByOCN_OCP_PI_LT_LL_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		java.lang.String productId, int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCN_OCP_PI_LT_LL_PrevAndNext(assetReceiptLicenseId,
			ownerClassNameId, ownerClassPK, productId, licenseType,
			licenseLifetime, orderByComparator);
	}

	/**
	* Returns all the asset receipt licenses.
	*
	* @return the asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset receipt licenses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetReceiptId(assetReceiptId);
	}

	/**
	* Removes all the asset receipt licenses where assetLicenseId = &#63; from the database.
	*
	* @param assetLicenseId the asset license ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetLicenseId(assetLicenseId);
	}

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByARI_LtSD_GtED(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByARI_LtSD_GtED(assetReceiptId, startDate, endDate);
	}

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63; from the database.
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
	* Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; from the database.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOCN_OCP_PCN(long ownerClassNameId,
		long ownerClassPK, long productClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId);
	}

	/**
	* Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; from the database.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOCN_OCP_PI(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId);
	}

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63; from the database.
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
	* Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63; from the database.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOCN_OCP_PI_LT_LL(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId, int licenseType,
		long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime);
	}

	/**
	* Removes all the asset receipt licenses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetReceiptId(assetReceiptId);
	}

	/**
	* Returns the number of asset receipt licenses where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARI_LtSD_GtED(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARI_LtSD_GtED(assetReceiptId, startDate, endDate);
	}

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByARI_GtSD_UT(long assetReceiptId,
		java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByARI_GtSD_UT(assetReceiptId, startDate, usageType);
	}

	/**
	* Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOCN_OCP_PCN(long ownerClassNameId,
		long ownerClassPK, long productClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId);
	}

	/**
	* Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOCN_OCP_PI(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId);
	}

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @return the number of matching asset receipt licenses
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
	* Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @param licenseType the license type
	* @param licenseLifetime the license lifetime
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOCN_OCP_PI_LT_LL(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId, int licenseType,
		long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime);
	}

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetReceiptLicensePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetReceiptLicensePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetReceiptLicensePersistence.class.getName());

			ReferenceRegistry.registerReference(AssetReceiptLicenseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetReceiptLicensePersistence persistence) {
	}

	private static AssetReceiptLicensePersistence _persistence;
}