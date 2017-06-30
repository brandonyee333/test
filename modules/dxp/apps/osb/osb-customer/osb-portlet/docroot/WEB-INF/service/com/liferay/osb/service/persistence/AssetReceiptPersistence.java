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

import com.liferay.osb.model.AssetReceipt;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset receipt service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptPersistenceImpl
 * @see AssetReceiptUtil
 * @generated
 */
public interface AssetReceiptPersistence extends BasePersistence<AssetReceipt> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetReceiptUtil} to access the asset receipt persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset receipt in the entity cache if it is enabled.
	*
	* @param assetReceipt the asset receipt
	*/
	public void cacheResult(com.liferay.osb.model.AssetReceipt assetReceipt);

	/**
	* Caches the asset receipts in the entity cache if it is enabled.
	*
	* @param assetReceipts the asset receipts
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceipt> assetReceipts);

	/**
	* Creates a new asset receipt with the primary key. Does not add the asset receipt to the database.
	*
	* @param assetReceiptId the primary key for the new asset receipt
	* @return the new asset receipt
	*/
	public com.liferay.osb.model.AssetReceipt create(long assetReceiptId);

	/**
	* Removes the asset receipt with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt remove(long assetReceiptId)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetReceipt updateImpl(
		com.liferay.osb.model.AssetReceipt assetReceipt, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt findByPrimaryKey(
		long assetReceiptId)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt, or <code>null</code> if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByPrimaryKey(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipts where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> findByAssetEntryId(
		long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset receipts where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset receipts
	* @param end the upper bound of the range of asset receipts (not inclusive)
	* @return the range of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> findByAssetEntryId(
		long assetEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset receipts where assetEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param start the lower bound of the range of asset receipts
	* @param end the upper bound of the range of asset receipts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> findByAssetEntryId(
		long assetEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt findByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt findByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipts before and after the current asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetReceiptId the primary key of the current asset receipt
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt[] findByAssetEntryId_PrevAndNext(
		long assetReceiptId, long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt findByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt findByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt fetchByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipts.
	*
	* @return the asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset receipts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipts
	* @param end the upper bound of the range of asset receipts (not inclusive)
	* @return the range of asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset receipts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipts
	* @param end the upper bound of the range of asset receipts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceipt> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipts where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the asset receipt that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt removeByUI_PCNI_PCPK(
		long userId, long productClassNameId, long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the asset receipt that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceipt removeByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipts where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipts where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the number of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public int countByUI_PCNI_PCPK(long userId, long productClassNameId,
		long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipts where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param ownerClassNameId the owner class name ID
	* @param ownerClassPK the owner class p k
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the number of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public int countByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipts.
	*
	* @return the number of asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}