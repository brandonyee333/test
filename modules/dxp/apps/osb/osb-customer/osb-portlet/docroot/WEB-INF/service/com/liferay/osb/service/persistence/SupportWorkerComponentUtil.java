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

import com.liferay.osb.model.SupportWorkerComponent;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the support worker component service. This utility wraps {@link SupportWorkerComponentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentPersistence
 * @see SupportWorkerComponentPersistenceImpl
 * @generated
 */
public class SupportWorkerComponentUtil {
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
	public static void clearCache(SupportWorkerComponent supportWorkerComponent) {
		getPersistence().clearCache(supportWorkerComponent);
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
	public static List<SupportWorkerComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportWorkerComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportWorkerComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SupportWorkerComponent update(
		SupportWorkerComponent supportWorkerComponent, boolean merge)
		throws SystemException {
		return getPersistence().update(supportWorkerComponent, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SupportWorkerComponent update(
		SupportWorkerComponent supportWorkerComponent, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(supportWorkerComponent, merge, serviceContext);
	}

	/**
	* Caches the support worker component in the entity cache if it is enabled.
	*
	* @param supportWorkerComponent the support worker component
	*/
	public static void cacheResult(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent) {
		getPersistence().cacheResult(supportWorkerComponent);
	}

	/**
	* Caches the support worker components in the entity cache if it is enabled.
	*
	* @param supportWorkerComponents the support worker components
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SupportWorkerComponent> supportWorkerComponents) {
		getPersistence().cacheResult(supportWorkerComponents);
	}

	/**
	* Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	*
	* @param supportWorkerComponentId the primary key for the new support worker component
	* @return the new support worker component
	*/
	public static com.liferay.osb.model.SupportWorkerComponent create(
		long supportWorkerComponentId) {
		return getPersistence().create(supportWorkerComponentId);
	}

	/**
	* Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component that was removed
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent remove(
		long supportWorkerComponentId)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(supportWorkerComponentId);
	}

	public static com.liferay.osb.model.SupportWorkerComponent updateImpl(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(supportWorkerComponent, merge);
	}

	/**
	* Returns the support worker component with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerComponentException} if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent findByPrimaryKey(
		long supportWorkerComponentId)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(supportWorkerComponentId);
	}

	/**
	* Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent fetchByPrimaryKey(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(supportWorkerComponentId);
	}

	/**
	* Returns all the support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns a range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end);
	}

	/**
	* Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId_First(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component, or <code>null</code> if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportWorkerId_First(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId_Last(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker component, or <code>null</code> if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportWorkerId_Last(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the support worker components before and after the current support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerComponentId the primary key of the current support worker component
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerComponent[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerComponentId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportWorkerId_PrevAndNext(supportWorkerComponentId,
			supportWorkerId, orderByComparator);
	}

	/**
	* Returns all the support worker components.
	*
	* @return the support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the support worker components where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportWorkerId(supportWorkerId);
	}

	/**
	* Removes all the support worker components from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the number of support worker components.
	*
	* @return the number of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SupportWorkerComponentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportWorkerComponentPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportWorkerComponentPersistence.class.getName());

			ReferenceRegistry.registerReference(SupportWorkerComponentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SupportWorkerComponentPersistence persistence) {
	}

	private static SupportWorkerComponentPersistence _persistence;
}