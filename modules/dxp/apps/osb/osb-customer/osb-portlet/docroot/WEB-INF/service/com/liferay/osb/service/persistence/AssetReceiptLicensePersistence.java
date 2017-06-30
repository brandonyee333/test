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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset receipt license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicensePersistenceImpl
 * @see AssetReceiptLicenseUtil
 * @generated
 */
public interface AssetReceiptLicensePersistence extends BasePersistence<AssetReceiptLicense> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetReceiptLicenseUtil} to access the asset receipt license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset receipt license in the entity cache if it is enabled.
	*
	* @param assetReceiptLicense the asset receipt license
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense);

	/**
	* Caches the asset receipt licenses in the entity cache if it is enabled.
	*
	* @param assetReceiptLicenses the asset receipt licenses
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceiptLicense> assetReceiptLicenses);

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	public com.liferay.osb.model.AssetReceiptLicense create(
		long assetReceiptLicenseId);

	/**
	* Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense remove(
		long assetReceiptLicenseId)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetReceiptLicense updateImpl(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt license with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptLicenseException} if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByPrimaryKey(
		long assetReceiptLicenseId)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByPrimaryKey(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt license in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByUuid_PrevAndNext(
		long assetReceiptLicenseId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetReceiptId(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetReceiptId(
		long assetReceiptId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetReceiptId(
		long assetReceiptId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt license in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByAssetReceiptId_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetLicenseId(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetLicenseId(
		long assetLicenseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByAssetLicenseId(
		long assetLicenseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByAssetLicenseId_First(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license
	* @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense findByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt license in the ordered set where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense fetchByAssetLicenseId_Last(
		long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByAssetLicenseId_PrevAndNext(
		long assetReceiptLicenseId, long assetLicenseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByARI_LtSD_GtED_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByARI_GtSD_UT_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PCN(
		long ownerClassNameId, long ownerClassPK, long productClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PCN(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PCN(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PCN_First(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PCN_First(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PCN_Last(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PCN_Last(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByOCN_OCP_PCN_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @return the matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByOCN_OCP_PI_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByARI_LtSD_GtED_UT_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_LT_LL_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_LT_LL_First(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense findByOCN_OCP_PI_LT_LL_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense fetchByOCN_OCP_PI_LT_LL_Last(
		long ownerClassNameId, long ownerClassPK, java.lang.String productId,
		int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptLicense[] findByOCN_OCP_PI_LT_LL_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		java.lang.String productId, int licenseType, long licenseLifetime,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptLicenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt licenses.
	*
	* @return the asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where assetLicenseId = &#63; from the database.
	*
	* @param assetLicenseId the asset license ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARI_LtSD_GtED(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARI_GtSD_UT(long assetReceiptId,
		java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; from the database.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOCN_OCP_PCN(long ownerClassNameId, long ownerClassPK,
		long productClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; from the database.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOCN_OCP_PI(long ownerClassNameId, long ownerClassPK,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @param usageType the usage type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARI_LtSD_GtED_UT(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public void removeByOCN_OCP_PI_LT_LL(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId, int licenseType,
		long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt licenses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where assetLicenseId = &#63;.
	*
	* @param assetLicenseId the asset license ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByARI_LtSD_GtED(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByARI_GtSD_UT(long assetReceiptId,
		java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByOCN_OCP_PCN(long ownerClassNameId, long ownerClassPK,
		long productClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productId the product ID
	* @return the number of matching asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByOCN_OCP_PI(long ownerClassNameId, long ownerClassPK,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByARI_LtSD_GtED_UT(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByOCN_OCP_PI_LT_LL(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId, int licenseType,
		long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}