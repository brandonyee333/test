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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset receipt support service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptSupportPersistenceImpl
 * @see AssetReceiptSupportUtil
 * @generated
 */
public interface AssetReceiptSupportPersistence extends BasePersistence<AssetReceiptSupport> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetReceiptSupportUtil} to access the asset receipt support persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset receipt support in the entity cache if it is enabled.
	*
	* @param assetReceiptSupport the asset receipt support
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport);

	/**
	* Caches the asset receipt supports in the entity cache if it is enabled.
	*
	* @param assetReceiptSupports the asset receipt supports
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceiptSupport> assetReceiptSupports);

	/**
	* Creates a new asset receipt support with the primary key. Does not add the asset receipt support to the database.
	*
	* @param assetReceiptSupportId the primary key for the new asset receipt support
	* @return the new asset receipt support
	*/
	public com.liferay.osb.model.AssetReceiptSupport create(
		long assetReceiptSupportId);

	/**
	* Removes the asset receipt support with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport remove(
		long assetReceiptSupportId)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetReceiptSupport updateImpl(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt support with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptSupportException} if it could not be found.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport findByPrimaryKey(
		long assetReceiptSupportId)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt support with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support, or <code>null</code> if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport fetchByPrimaryKey(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt supports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport[] findByUuid_PrevAndNext(
		long assetReceiptSupportId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByAssetReceiptId(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByAssetReceiptId(
		long assetReceiptId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByAssetReceiptId(
		long assetReceiptId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport findByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport fetchByAssetReceiptId_First(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support
	* @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport findByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt support in the ordered set where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptSupport fetchByAssetReceiptId_Last(
		long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport[] findByAssetReceiptId_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK(
		long assetReceiptId, long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK(
		long assetReceiptId, long productClassNameId, long productClassPK,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK(
		long assetReceiptId, long productClassNameId, long productClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport[] findByARI_PCNI_PCPK_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		long productClassNameId, long productClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_GtSD_UT(
		long assetReceiptId, java.util.Date startDate, int usageType,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_GtSD_UT_First(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_GtSD_UT_Last(
		long assetReceiptId, java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport[] findByARI_GtSD_UT_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		java.util.Date startDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_LtSD_GtED_UT_First(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, java.util.Date startDate, java.util.Date endDate,
		int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport[] findByARI_LtSD_GtED_UT_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_LtSD_GtED_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_LtSD_GtED_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport findByARI_PCNI_PCPK_LtSD_GtED_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport fetchByARI_PCNI_PCPK_LtSD_GtED_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AssetReceiptSupport[] findByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		long productClassNameId, long productClassPK, java.util.Date startDate,
		java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptSupportException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt supports.
	*
	* @return the asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptSupport> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt supports where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63; from the database.
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
	* Removes all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63; from the database.
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
	* Removes all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param startDate the start date
	* @param endDate the end date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByARI_PCNI_PCPK_LtSD_GtED(long assetReceiptId,
		long productClassNameId, long productClassPK, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt supports from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt supports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetReceiptId(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public int countByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	*
	* @param assetReceiptId the asset receipt ID
	* @param startDate the start date
	* @param usageType the usage type
	* @return the number of matching asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public int countByARI_GtSD_UT(long assetReceiptId,
		java.util.Date startDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByARI_LtSD_GtED_UT(long assetReceiptId,
		java.util.Date startDate, java.util.Date endDate, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByARI_PCNI_PCPK_LtSD_GtED(long assetReceiptId,
		long productClassNameId, long productClassPK, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt supports.
	*
	* @return the number of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}