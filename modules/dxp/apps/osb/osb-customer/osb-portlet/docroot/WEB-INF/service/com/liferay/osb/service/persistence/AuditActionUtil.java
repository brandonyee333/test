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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.AuditAction;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the audit action service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AuditActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditActionPersistence
 * @see com.liferay.osb.service.persistence.impl.AuditActionPersistenceImpl
 * @generated
 */
@ProviderType
public class AuditActionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AuditAction auditAction) {
		getPersistence().clearCache(auditAction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditAction update(AuditAction auditAction) {
		return getPersistence().update(auditAction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditAction update(AuditAction auditAction,
		ServiceContext serviceContext) {
		return getPersistence().update(auditAction, serviceContext);
	}

	/**
	* Returns all the audit actions where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching audit actions
	*/
	public static List<AuditAction> findByLtModifiedDate(Date modifiedDate) {
		return getPersistence().findByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	*/
	public static List<AuditAction> findByLtModifiedDate(Date modifiedDate,
		int start, int end) {
		return getPersistence().findByLtModifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByLtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByLtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .fetchByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator) {
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
	* @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	*/
	public static AuditAction[] findByLtModifiedDate_PrevAndNext(
		long auditActionId, Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByLtModifiedDate_PrevAndNext(auditActionId,
			modifiedDate, orderByComparator);
	}

	/**
	* Removes all the audit actions where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public static void removeByLtModifiedDate(Date modifiedDate) {
		getPersistence().removeByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of audit actions where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching audit actions
	*/
	public static int countByLtModifiedDate(Date modifiedDate) {
		return getPersistence().countByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or throws a {@link NoSuchAuditActionException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @return the matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByC_C_MC(long classNameId, long classPK,
		long mappingClassPK)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) {
		return getPersistence()
				   .fetchByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByC_C_MC(long classNameId, long classPK,
		long mappingClassPK, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_C_MC(classNameId, classPK, mappingClassPK,
			retrieveFromCache);
	}

	/**
	* Removes the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @return the audit action that was removed
	*/
	public static AuditAction removeByC_C_MC(long classNameId, long classPK,
		long mappingClassPK)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .removeByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns the number of audit actions where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @return the number of matching audit actions
	*/
	public static int countByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) {
		return getPersistence()
				   .countByC_C_MC(classNameId, classPK, mappingClassPK);
	}

	/**
	* Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @return the matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action) {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end) {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByGtMD_C_GtMC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByGtMD_C_GtMC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .fetchByGtMD_C_GtMC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByGtMD_C_GtMC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A_Last(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByGtMD_C_GtMC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
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
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	*/
	public static AuditAction[] findByGtMD_C_GtMC_A_PrevAndNext(
		long auditActionId, Date modifiedDate, long classNameId,
		long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByGtMD_C_GtMC_A_PrevAndNext(auditActionId,
			modifiedDate, classNameId, mappingClassPK, action, orderByComparator);
	}

	/**
	* Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	*/
	public static void removeByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action) {
		getPersistence()
			.removeByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action);
	}

	/**
	* Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @return the number of matching audit actions
	*/
	public static int countByGtMD_C_GtMC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		return getPersistence()
				   .countByGtMD_C_GtMC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @return the matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action) {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end) {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByGtMD_C_MC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByGtMD_C_MC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByGtMD_C_MC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .fetchByGtMD_C_MC_A_First(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByGtMD_C_MC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByGtMD_C_MC_A_Last(modifiedDate, classNameId,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByGtMD_C_MC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
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
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	*/
	public static AuditAction[] findByGtMD_C_MC_A_PrevAndNext(
		long auditActionId, Date modifiedDate, long classNameId,
		long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByGtMD_C_MC_A_PrevAndNext(auditActionId, modifiedDate,
			classNameId, mappingClassPK, action, orderByComparator);
	}

	/**
	* Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	*/
	public static void removeByGtMD_C_MC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		getPersistence()
			.removeByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action);
	}

	/**
	* Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @return the number of matching audit actions
	*/
	public static int countByGtMD_C_MC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		return getPersistence()
				   .countByGtMD_C_MC_A(modifiedDate, classNameId,
			mappingClassPK, action);
	}

	/**
	* Returns all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @return the matching audit actions
	*/
	public static List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action) {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	* Returns a range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of matching audit actions
	*/
	public static List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end) {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end, OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit actions
	*/
	public static List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end, OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByMD_C_C_MC_A_First(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByMD_C_C_MC_A_First(modifiedDate, classNameId, classPK,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByMD_C_C_MC_A_First(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence()
				   .fetchByMD_C_C_MC_A_First(modifiedDate, classNameId,
			classPK, mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action
	* @throws NoSuchAuditActionException if a matching audit action could not be found
	*/
	public static AuditAction findByMD_C_C_MC_A_Last(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByMD_C_C_MC_A_Last(modifiedDate, classNameId, classPK,
			mappingClassPK, action, orderByComparator);
	}

	/**
	* Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	*/
	public static AuditAction fetchByMD_C_C_MC_A_Last(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
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
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit action
	* @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	*/
	public static AuditAction[] findByMD_C_C_MC_A_PrevAndNext(
		long auditActionId, Date modifiedDate, long classNameId, long classPK,
		long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence()
				   .findByMD_C_C_MC_A_PrevAndNext(auditActionId, modifiedDate,
			classNameId, classPK, mappingClassPK, action, orderByComparator);
	}

	/**
	* Removes all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	*/
	public static void removeByMD_C_C_MC_A(Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action) {
		getPersistence()
			.removeByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	* Returns the number of audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	*
	* @param modifiedDate the modified date
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param mappingClassPK the mapping class pk
	* @param action the action
	* @return the number of matching audit actions
	*/
	public static int countByMD_C_C_MC_A(Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action) {
		return getPersistence()
				   .countByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	* Caches the audit action in the entity cache if it is enabled.
	*
	* @param auditAction the audit action
	*/
	public static void cacheResult(AuditAction auditAction) {
		getPersistence().cacheResult(auditAction);
	}

	/**
	* Caches the audit actions in the entity cache if it is enabled.
	*
	* @param auditActions the audit actions
	*/
	public static void cacheResult(List<AuditAction> auditActions) {
		getPersistence().cacheResult(auditActions);
	}

	/**
	* Creates a new audit action with the primary key. Does not add the audit action to the database.
	*
	* @param auditActionId the primary key for the new audit action
	* @return the new audit action
	*/
	public static AuditAction create(long auditActionId) {
		return getPersistence().create(auditActionId);
	}

	/**
	* Removes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action that was removed
	* @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	*/
	public static AuditAction remove(long auditActionId)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence().remove(auditActionId);
	}

	public static AuditAction updateImpl(AuditAction auditAction) {
		return getPersistence().updateImpl(auditAction);
	}

	/**
	* Returns the audit action with the primary key or throws a {@link NoSuchAuditActionException} if it could not be found.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action
	* @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	*/
	public static AuditAction findByPrimaryKey(long auditActionId)
		throws com.liferay.osb.exception.NoSuchAuditActionException {
		return getPersistence().findByPrimaryKey(auditActionId);
	}

	/**
	* Returns the audit action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action, or <code>null</code> if a audit action with the primary key could not be found
	*/
	public static AuditAction fetchByPrimaryKey(long auditActionId) {
		return getPersistence().fetchByPrimaryKey(auditActionId);
	}

	public static java.util.Map<java.io.Serializable, AuditAction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the audit actions.
	*
	* @return the audit actions
	*/
	public static List<AuditAction> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of audit actions
	*/
	public static List<AuditAction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit actions
	*/
	public static List<AuditAction> findAll(int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit actions
	*/
	public static List<AuditAction> findAll(int start, int end,
		OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the audit actions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit actions.
	*
	* @return the number of audit actions
	*/
	public static int countAll() {
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

	private static AuditActionPersistence _persistence;
}