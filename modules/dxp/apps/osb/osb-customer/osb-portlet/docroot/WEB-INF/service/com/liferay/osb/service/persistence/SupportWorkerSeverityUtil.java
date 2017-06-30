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

import com.liferay.osb.model.SupportWorkerSeverity;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the support worker severity service. This utility wraps {@link SupportWorkerSeverityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverityPersistence
 * @see SupportWorkerSeverityPersistenceImpl
 * @generated
 */
public class SupportWorkerSeverityUtil {
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
	public static void clearCache(SupportWorkerSeverity supportWorkerSeverity) {
		getPersistence().clearCache(supportWorkerSeverity);
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
	public static List<SupportWorkerSeverity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportWorkerSeverity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportWorkerSeverity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SupportWorkerSeverity update(
		SupportWorkerSeverity supportWorkerSeverity, boolean merge)
		throws SystemException {
		return getPersistence().update(supportWorkerSeverity, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SupportWorkerSeverity update(
		SupportWorkerSeverity supportWorkerSeverity, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(supportWorkerSeverity, merge, serviceContext);
	}

	/**
	* Caches the support worker severity in the entity cache if it is enabled.
	*
	* @param supportWorkerSeverity the support worker severity
	*/
	public static void cacheResult(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		getPersistence().cacheResult(supportWorkerSeverity);
	}

	/**
	* Caches the support worker severities in the entity cache if it is enabled.
	*
	* @param supportWorkerSeverities the support worker severities
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SupportWorkerSeverity> supportWorkerSeverities) {
		getPersistence().cacheResult(supportWorkerSeverities);
	}

	/**
	* Creates a new support worker severity with the primary key. Does not add the support worker severity to the database.
	*
	* @param supportWorkerSeverityId the primary key for the new support worker severity
	* @return the new support worker severity
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity create(
		long supportWorkerSeverityId) {
		return getPersistence().create(supportWorkerSeverityId);
	}

	/**
	* Removes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity that was removed
	* @throws com.liferay.osb.NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity remove(
		long supportWorkerSeverityId)
		throws com.liferay.osb.NoSuchSupportWorkerSeverityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(supportWorkerSeverityId);
	}

	public static com.liferay.osb.model.SupportWorkerSeverity updateImpl(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(supportWorkerSeverity, merge);
	}

	/**
	* Returns the support worker severity with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerSeverityException} if it could not be found.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity
	* @throws com.liferay.osb.NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity findByPrimaryKey(
		long supportWorkerSeverityId)
		throws com.liferay.osb.NoSuchSupportWorkerSeverityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(supportWorkerSeverityId);
	}

	/**
	* Returns the support worker severity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity, or <code>null</code> if a support worker severity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity fetchByPrimaryKey(
		long supportWorkerSeverityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(supportWorkerSeverityId);
	}

	/**
	* Returns all the support worker severities where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns a range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of matching support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end);
	}

	/**
	* Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker severity
	* @throws com.liferay.osb.NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerSeverityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId_First(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportWorkerId_First(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker severity
	* @throws com.liferay.osb.NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerSeverityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId_Last(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportWorkerId_Last(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the support worker severities before and after the current support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerSeverityId the primary key of the current support worker severity
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker severity
	* @throws com.liferay.osb.NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerSeverity[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerSeverityId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerSeverityException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId_PrevAndNext(supportWorkerSeverityId,
			supportWorkerId, orderByComparator);
	}

	/**
	* Returns all the support worker severities.
	*
	* @return the support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerSeverity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the support worker severities where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportWorkerId(supportWorkerId);
	}

	/**
	* Removes all the support worker severities from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support worker severities where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the number of support worker severities.
	*
	* @return the number of support worker severities
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SupportWorkerSeverityPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportWorkerSeverityPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportWorkerSeverityPersistence.class.getName());

			ReferenceRegistry.registerReference(SupportWorkerSeverityUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SupportWorkerSeverityPersistence persistence) {
	}

	private static SupportWorkerSeverityPersistence _persistence;
}