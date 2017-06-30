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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset receipt service. This utility wraps {@link AssetReceiptPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptPersistence
 * @see AssetReceiptPersistenceImpl
 * @generated
 */
public class AssetReceiptUtil {
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
	public static void clearCache(AssetReceipt assetReceipt) {
		getPersistence().clearCache(assetReceipt);
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
	public static List<AssetReceipt> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetReceipt> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetReceipt> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetReceipt update(AssetReceipt assetReceipt, boolean merge)
		throws SystemException {
		return getPersistence().update(assetReceipt, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetReceipt update(AssetReceipt assetReceipt, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetReceipt, merge, serviceContext);
	}

	/**
	* Caches the asset receipt in the entity cache if it is enabled.
	*
	* @param assetReceipt the asset receipt
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetReceipt assetReceipt) {
		getPersistence().cacheResult(assetReceipt);
	}

	/**
	* Caches the asset receipts in the entity cache if it is enabled.
	*
	* @param assetReceipts the asset receipts
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceipt> assetReceipts) {
		getPersistence().cacheResult(assetReceipts);
	}

	/**
	* Creates a new asset receipt with the primary key. Does not add the asset receipt to the database.
	*
	* @param assetReceiptId the primary key for the new asset receipt
	* @return the new asset receipt
	*/
	public static com.liferay.osb.model.AssetReceipt create(long assetReceiptId) {
		return getPersistence().create(assetReceiptId);
	}

	/**
	* Removes the asset receipt with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt remove(long assetReceiptId)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetReceiptId);
	}

	public static com.liferay.osb.model.AssetReceipt updateImpl(
		com.liferay.osb.model.AssetReceipt assetReceipt, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetReceipt, merge);
	}

	/**
	* Returns the asset receipt with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt findByPrimaryKey(
		long assetReceiptId)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetReceiptId);
	}

	/**
	* Returns the asset receipt with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetReceiptId the primary key of the asset receipt
	* @return the asset receipt, or <code>null</code> if a asset receipt with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt fetchByPrimaryKey(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetReceiptId);
	}

	/**
	* Returns all the asset receipts where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceipt> findByAssetEntryId(
		long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetEntryId(assetEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetReceipt> findByAssetEntryId(
		long assetEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetEntryId(assetEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetReceipt> findByAssetEntryId(
		long assetEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt findByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the first asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt fetchByAssetEntryId_First(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetEntryId_First(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt
	* @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt findByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId_Last(assetEntryId, orderByComparator);
	}

	/**
	* Returns the last asset receipt in the ordered set where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt fetchByAssetEntryId_Last(
		long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetEntryId_Last(assetEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetReceipt[] findByAssetEntryId_PrevAndNext(
		long assetReceiptId, long assetEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntryId_PrevAndNext(assetReceiptId,
			assetEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetReceipt findByUI_PCNI_PCPK(
		long userId, long productClassNameId, long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUI_PCNI_PCPK(userId, productClassNameId,
			productClassPK);
	}

	/**
	* Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt fetchByUI_PCNI_PCPK(
		long userId, long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUI_PCNI_PCPK(userId, productClassNameId,
			productClassPK);
	}

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
	public static com.liferay.osb.model.AssetReceipt fetchByUI_PCNI_PCPK(
		long userId, long productClassNameId, long productClassPK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUI_PCNI_PCPK(userId, productClassNameId,
			productClassPK, retrieveFromCache);
	}

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
	public static com.liferay.osb.model.AssetReceipt findByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOCNI_OCPK_PCNI_PCPK(ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK);
	}

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
	public static com.liferay.osb.model.AssetReceipt fetchByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCNI_OCPK_PCNI_PCPK(ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK);
	}

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
	public static com.liferay.osb.model.AssetReceipt fetchByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOCNI_OCPK_PCNI_PCPK(ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK, retrieveFromCache);
	}

	/**
	* Returns all the asset receipts.
	*
	* @return the asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceipt> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AssetReceipt> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetReceipt> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset receipts where assetEntryId = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetEntryId(assetEntryId);
	}

	/**
	* Removes the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the asset receipt that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceipt removeByUI_PCNI_PCPK(
		long userId, long productClassNameId, long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByUI_PCNI_PCPK(userId, productClassNameId,
			productClassPK);
	}

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
	public static com.liferay.osb.model.AssetReceipt removeByOCNI_OCPK_PCNI_PCPK(
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		long productClassPK)
		throws com.liferay.osb.NoSuchAssetReceiptException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByOCNI_OCPK_PCNI_PCPK(ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK);
	}

	/**
	* Removes all the asset receipts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset receipts where assetEntryId = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @return the number of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetEntryId(long assetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetEntryId(assetEntryId);
	}

	/**
	* Returns the number of asset receipts where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	*
	* @param userId the user ID
	* @param productClassNameId the product class name ID
	* @param productClassPK the product class p k
	* @return the number of matching asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUI_PCNI_PCPK(long userId, long productClassNameId,
		long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByUI_PCNI_PCPK(userId, productClassNameId,
			productClassPK);
	}

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
	public static int countByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByOCNI_OCPK_PCNI_PCPK(ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK);
	}

	/**
	* Returns the number of asset receipts.
	*
	* @return the number of asset receipts
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetReceiptPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetReceiptPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetReceiptPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetReceiptUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetReceiptPersistence persistence) {
	}

	private static AssetReceiptPersistence _persistence;
}