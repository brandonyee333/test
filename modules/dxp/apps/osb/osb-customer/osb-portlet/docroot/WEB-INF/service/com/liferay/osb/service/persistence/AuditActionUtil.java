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

import com.liferay.osb.model.AuditAction;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the audit action service. This utility wraps {@link AuditActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditActionPersistence
 * @see AuditActionPersistenceImpl
 * @generated
 */
public class AuditActionUtil {
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
	public static void clearCache(AuditAction auditAction) {
		getPersistence().clearCache(auditAction);
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
	public static List<AuditAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AuditAction update(AuditAction auditAction, boolean merge)
		throws SystemException {
		return getPersistence().update(auditAction, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AuditAction update(AuditAction auditAction, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(auditAction, merge, serviceContext);
	}

	/**
	* Caches the audit action in the entity cache if it is enabled.
	*
	* @param auditAction the audit action
	*/
	public static void cacheResult(
		com.liferay.osb.model.AuditAction auditAction) {
		getPersistence().cacheResult(auditAction);
	}

	/**
	* Caches the audit actions in the entity cache if it is enabled.
	*
	* @param auditActions the audit actions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AuditAction> auditActions) {
		getPersistence().cacheResult(auditActions);
	}

	/**
	* Creates a new audit action with the primary key. Does not add the audit action to the database.
	*
	* @param auditActionId the primary key for the new audit action
	* @return the new audit action
	*/
	public static com.liferay.osb.model.AuditAction create(long auditActionId) {
		return getPersistence().create(auditActionId);
	}

	/**
	* Removes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action that was removed
	* @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction remove(long auditActionId)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(auditActionId);
	}

	public static com.liferay.osb.model.AuditAction updateImpl(
		com.liferay.osb.model.AuditAction auditAction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(auditAction, merge);
	}

	/**
	* Returns the audit action with the primary key or throws a {@link com.liferay.osb.NoSuchAuditActionException} if it could not be found.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByPrimaryKey(
		long auditActionId)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(auditActionId);
	}

	/**
	* Returns the audit action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action, or <code>null</code> if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByPrimaryKey(
		long auditActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(auditActionId);
	}

	/**
	* Returns all the audit actions where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByLtModifiedDate(
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByLtModifiedDate(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtModifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByLtModifiedDate(
		java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByLtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByLtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByLtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByLtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param auditActionId the primary key of the current audit action
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction[] findByLtModifiedDate_PrevAndNext(
		long auditActionId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate_PrevAndNext(auditActionId,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or throws a {@link com.liferay.osb.NoSuchAuditActionException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @return the matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByC_C_MC(
		long classNameId, long classPK, long mappingClassPK)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByC_C_MC(
		long classNameId, long classPK, long mappingClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByC_C_MC(
		long classNameId, long classPK, long mappingClassPK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_MC(classNameId, classPK, mappingClassPK,
			retrieveFromCache);
	}

	/**
	* Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @return the matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByGtMD_C_GtMC_A(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByGtMD_C_GtMC_A(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByGtMD_C_GtMC_A(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByGtMD_C_GtMC_A_First(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByGtMD_C_GtMC_A_First(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtMD_C_GtMC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByGtMD_C_GtMC_A_Last(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A_Last(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByGtMD_C_GtMC_A_Last(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtMD_C_GtMC_A_Last(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param auditActionId the primary key of the current audit action
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction[] findByGtMD_C_GtMC_A_PrevAndNext(
		long auditActionId, java.util.Date modifiedDate, long classNameId,
		long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A_PrevAndNext(auditActionId,
			modifiedDate, classNameId, mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @return the matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByGtMD_C_MC_A(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByGtMD_C_MC_A(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByGtMD_C_MC_A(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByGtMD_C_MC_A_First(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_MC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByGtMD_C_MC_A_First(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtMD_C_MC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByGtMD_C_MC_A_Last(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_MC_A_Last(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByGtMD_C_MC_A_Last(
		java.util.Date modifiedDate, long classNameId, long mappingClassPK,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtMD_C_MC_A_Last(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param auditActionId the primary key of the current audit action
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction[] findByGtMD_C_MC_A_PrevAndNext(
		long auditActionId, java.util.Date modifiedDate, long classNameId,
		long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtMD_C_MC_A_PrevAndNext(auditActionId, modifiedDate,
			classNameId, mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @return the matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByMD_C_C_MC_A(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByMD_C_C_MC_A(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findByMD_C_C_MC_A(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByMD_C_C_MC_A_First(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_C_C_MC_A_First(modifiedDate, classNameId, classPK,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByMD_C_C_MC_A_First(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMD_C_C_MC_A_First(modifiedDate, classNameId,
			classPK, mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction findByMD_C_C_MC_A_Last(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_C_C_MC_A_Last(modifiedDate, classNameId, classPK,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction fetchByMD_C_C_MC_A_Last(
		java.util.Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMD_C_C_MC_A_Last(modifiedDate, classNameId, classPK,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the audit actions before and after the current audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param auditActionId the primary key of the current audit action
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws com.liferay.osb.NoSuchAuditActionException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction[] findByMD_C_C_MC_A_PrevAndNext(
		long auditActionId, java.util.Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_C_C_MC_A_PrevAndNext(auditActionId, modifiedDate,
			classNameId, classPK, mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns all the audit actions.
	*
	* @return the audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditAction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the audit actions where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByLtModifiedDate(modifiedDate);
	}

	/**
	* Removes the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @return the audit action that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditAction removeByC_C_MC(
		long classNameId, long classPK, long mappingClassPK)
		throws com.liferay.osb.NoSuchAuditActionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGtMD_C_GtMC_A(java.util.Date modifiedDate,
		long classNameId, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action);
	}

	/**
	* Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGtMD_C_MC_A(java.util.Date modifiedDate,
		long classNameId, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action);
	}

	/**
	* Removes all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMD_C_C_MC_A(java.util.Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	* Removes all the audit actions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit actions where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of audit actions where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @return the number of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_MC(long classNameId, long classPK,
		long mappingClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @return the number of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGtMD_C_GtMC_A(java.util.Date modifiedDate,
		long classNameId, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @return the number of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGtMD_C_MC_A(java.util.Date modifiedDate,
		long classNameId, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns the number of audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param mappingClassPK the mapping class p k
	* @param action the action
	* @return the number of matching audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMD_C_C_MC_A(java.util.Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	* Returns the number of audit actions.
	*
	* @return the number of audit actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AuditActionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AuditActionPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AuditActionPersistence.class.getName());

			ReferenceRegistry.registerReference(AuditActionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AuditActionPersistence persistence) {
	}

	private static AuditActionPersistence _persistence;
}