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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset audit service. This utility wraps {@link AssetAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAuditPersistence
 * @see AssetAuditPersistenceImpl
 * @generated
 */
public class AssetAuditUtil {
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
	public static void clearCache(AssetAudit assetAudit) {
		getPersistence().clearCache(assetAudit);
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
	public static List<AssetAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetAudit update(AssetAudit assetAudit, boolean merge)
		throws SystemException {
		return getPersistence().update(assetAudit, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetAudit update(AssetAudit assetAudit, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetAudit, merge, serviceContext);
	}

	/**
	* Caches the asset audit in the entity cache if it is enabled.
	*
	* @param assetAudit the asset audit
	*/
	public static void cacheResult(com.liferay.osb.model.AssetAudit assetAudit) {
		getPersistence().cacheResult(assetAudit);
	}

	/**
	* Caches the asset audits in the entity cache if it is enabled.
	*
	* @param assetAudits the asset audits
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetAudit> assetAudits) {
		getPersistence().cacheResult(assetAudits);
	}

	/**
	* Creates a new asset audit with the primary key. Does not add the asset audit to the database.
	*
	* @param assetAuditId the primary key for the new asset audit
	* @return the new asset audit
	*/
	public static com.liferay.osb.model.AssetAudit create(long assetAuditId) {
		return getPersistence().create(assetAuditId);
	}

	/**
	* Removes the asset audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit that was removed
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit remove(long assetAuditId)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetAuditId);
	}

	public static com.liferay.osb.model.AssetAudit updateImpl(
		com.liferay.osb.model.AssetAudit assetAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetAudit, merge);
	}

	/**
	* Returns the asset audit with the primary key or throws a {@link com.liferay.osb.NoSuchAssetAuditException} if it could not be found.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit
	* @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit findByPrimaryKey(
		long assetAuditId)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetAuditId);
	}

	/**
	* Returns the asset audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit, or <code>null</code> if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit fetchByPrimaryKey(
		long assetAuditId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetAuditId);
	}

	/**
	* Returns all the asset audits where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit[] findByC_C_PrevAndNext(
		long assetAuditId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(assetAuditId, classNameId, classPK,
			orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C_C_T(userId, classNameId, classPK, type);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_C_T(userId, classNameId, classPK, type, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByU_C_C_T(
		long userId, long classNameId, long classPK, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_C_T(userId, classNameId, classPK, type, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit findByU_C_C_T_First(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_C_T_First(userId, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit fetchByU_C_C_T_First(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_C_T_First(userId, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit findByU_C_C_T_Last(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_C_T_Last(userId, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit fetchByU_C_C_T_Last(
		long userId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_C_T_Last(userId, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit[] findByU_C_C_T_PrevAndNext(
		long assetAuditId, long userId, long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_C_T_PrevAndNext(assetAuditId, userId,
			classNameId, classPK, type, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByC_C_C_T(
		java.util.Date createDate, long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_C_T(createDate, classNameId, classPK, type);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByC_C_C_T(
		java.util.Date createDate, long classNameId, long classPK, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_C_T(createDate, classNameId, classPK, type,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByC_C_C_T(
		java.util.Date createDate, long classNameId, long classPK, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_C_T(createDate, classNameId, classPK, type,
			start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit findByC_C_C_T_First(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_C_T_First(createDate, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit fetchByC_C_C_T_First(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_C_T_First(createDate, classNameId, classPK,
			type, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit findByC_C_C_T_Last(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_C_T_Last(createDate, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit fetchByC_C_C_T_Last(
		java.util.Date createDate, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_C_T_Last(createDate, classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAudit[] findByC_C_C_T_PrevAndNext(
		long assetAuditId, java.util.Date createDate, long classNameId,
		long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_C_T_PrevAndNext(assetAuditId, createDate,
			classNameId, classPK, type, orderByComparator);
	}

	/**
	* Returns all the asset audits.
	*
	* @return the asset audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAudit> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset audits where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_C_C_T(long userId, long classNameId,
		long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_C_C_T(userId, classNameId, classPK, type);
	}

	/**
	* Removes all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_C_T(java.util.Date createDate,
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C_C_T(createDate, classNameId, classPK, type);
	}

	/**
	* Removes all the asset audits from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset audits where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

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
	public static int countByU_C_C_T(long userId, long classNameId,
		long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_C_C_T(userId, classNameId, classPK, type);
	}

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
	public static int countByC_C_C_T(java.util.Date createDate,
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_C_T(createDate, classNameId, classPK, type);
	}

	/**
	* Returns the number of asset audits.
	*
	* @return the number of asset audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetAuditPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetAuditPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetAuditPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetAuditUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetAuditPersistence persistence) {
	}

	private static AssetAuditPersistence _persistence;
}