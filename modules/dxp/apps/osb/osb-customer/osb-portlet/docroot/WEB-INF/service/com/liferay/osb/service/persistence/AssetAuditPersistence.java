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

import com.liferay.osb.model.AssetAudit;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAuditPersistenceImpl
 * @see AssetAuditUtil
 * @generated
 */
public interface AssetAuditPersistence extends BasePersistence<AssetAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetAuditUtil} to access the asset audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset audit in the entity cache if it is enabled.
	*
	* @param assetAudit the asset audit
	*/
	public void cacheResult(com.liferay.osb.model.AssetAudit assetAudit);

	/**
	* Caches the asset audits in the entity cache if it is enabled.
	*
	* @param assetAudits the asset audits
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetAudit> assetAudits);

	/**
	* Creates a new asset audit with the primary key. Does not add the asset audit to the database.
	*
	* @param assetAuditId the primary key for the new asset audit
	* @return the new asset audit
	*/
	public com.liferay.osb.model.AssetAudit create(long assetAuditId);

	/**
	* Removes the asset audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit that was removed
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit remove(long assetAuditId)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetAudit updateImpl(
		com.liferay.osb.model.AssetAudit assetAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset audit with the primary key or throws a {@link com.liferay.osb.NoSuchAssetAuditException} if it could not be found.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByPrimaryKey(long assetAuditId)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit, or <code>null</code> if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByPrimaryKey(long assetAuditId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset audits where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset audits where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @return the range of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset audits where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByC_C_First(long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByC_C_First(long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByC_C_Last(long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByC_C_Last(long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset audits before and after the current asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetAuditId the primary key of the current asset audit
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit[] findByC_C_PrevAndNext(
		long assetAuditId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @return the range of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByU_C_C_T_First(long userId,
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByU_C_C_T_First(long userId,
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByU_C_C_T_Last(long userId,
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByU_C_C_T_Last(long userId,
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset audits before and after the current asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param assetAuditId the primary key of the current asset audit
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit[] findByU_C_C_T_PrevAndNext(
		long assetAuditId, long userId, long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByC_C_C_T(
		java.util.Date createDate, long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @return the range of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByC_C_C_T(
		java.util.Date createDate, long classNameId, long classPK, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findByC_C_C_T(
		java.util.Date createDate, long classNameId, long classPK, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByC_C_C_T_First(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByC_C_C_T_First(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit findByC_C_C_T_Last(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit fetchByC_C_C_T_Last(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset audits before and after the current asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param assetAuditId the primary key of the current asset audit
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAudit[] findByC_C_C_T_PrevAndNext(
		long assetAuditId, java.util.Date createDate, long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset audits.
	*
	* @return the asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @return the range of asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAudit> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset audits where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_C_T(java.util.Date createDate, long classNameId,
		long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset audits from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset audits where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_C_T(java.util.Date createDate, long classNameId,
		long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset audits.
	*
	* @return the number of asset audits
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}