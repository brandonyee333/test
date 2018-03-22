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

import com.liferay.osb.model.SupportWorkerComponent;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the support worker component service. This utility wraps {@link com.liferay.osb.service.persistence.impl.SupportWorkerComponentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentPersistence
 * @see com.liferay.osb.service.persistence.impl.SupportWorkerComponentPersistenceImpl
 * @generated
 */
@ProviderType
public class SupportWorkerComponentUtil {
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
	public static void clearCache(SupportWorkerComponent supportWorkerComponent) {
		getPersistence().clearCache(supportWorkerComponent);
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
	public static List<SupportWorkerComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportWorkerComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportWorkerComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SupportWorkerComponent update(
		SupportWorkerComponent supportWorkerComponent) {
		return getPersistence().update(supportWorkerComponent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SupportWorkerComponent update(
		SupportWorkerComponent supportWorkerComponent,
		ServiceContext serviceContext) {
		return getPersistence().update(supportWorkerComponent, serviceContext);
	}

	/**
	* Returns all the support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker components
	*/
	public static List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId) {
		return getPersistence().findBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns a range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of matching support worker components
	*/
	public static List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end) {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end);
	}

	/**
	* Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker components
	*/
	public static List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support worker components
	*/
	public static List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component
	* @throws NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	*/
	public static SupportWorkerComponent findBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportWorkerComponentException {
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
	*/
	public static SupportWorkerComponent fetchBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
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
	* @throws NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	*/
	public static SupportWorkerComponent findBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportWorkerComponentException {
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
	*/
	public static SupportWorkerComponent fetchBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
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
	* @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	*/
	public static SupportWorkerComponent[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerComponentId, long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportWorkerComponentException {
		return getPersistence()
				   .findBySupportWorkerId_PrevAndNext(supportWorkerComponentId,
			supportWorkerId, orderByComparator);
	}

	/**
	* Removes all the support worker components where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	*/
	public static void removeBySupportWorkerId(long supportWorkerId) {
		getPersistence().removeBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the number of support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker components
	*/
	public static int countBySupportWorkerId(long supportWorkerId) {
		return getPersistence().countBySupportWorkerId(supportWorkerId);
	}

	/**
	* Caches the support worker component in the entity cache if it is enabled.
	*
	* @param supportWorkerComponent the support worker component
	*/
	public static void cacheResult(
		SupportWorkerComponent supportWorkerComponent) {
		getPersistence().cacheResult(supportWorkerComponent);
	}

	/**
	* Caches the support worker components in the entity cache if it is enabled.
	*
	* @param supportWorkerComponents the support worker components
	*/
	public static void cacheResult(
		List<SupportWorkerComponent> supportWorkerComponents) {
		getPersistence().cacheResult(supportWorkerComponents);
	}

	/**
	* Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	*
	* @param supportWorkerComponentId the primary key for the new support worker component
	* @return the new support worker component
	*/
	public static SupportWorkerComponent create(long supportWorkerComponentId) {
		return getPersistence().create(supportWorkerComponentId);
	}

	/**
	* Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component that was removed
	* @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	*/
	public static SupportWorkerComponent remove(long supportWorkerComponentId)
		throws com.liferay.osb.exception.NoSuchSupportWorkerComponentException {
		return getPersistence().remove(supportWorkerComponentId);
	}

	public static SupportWorkerComponent updateImpl(
		SupportWorkerComponent supportWorkerComponent) {
		return getPersistence().updateImpl(supportWorkerComponent);
	}

	/**
	* Returns the support worker component with the primary key or throws a {@link NoSuchSupportWorkerComponentException} if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component
	* @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	*/
	public static SupportWorkerComponent findByPrimaryKey(
		long supportWorkerComponentId)
		throws com.liferay.osb.exception.NoSuchSupportWorkerComponentException {
		return getPersistence().findByPrimaryKey(supportWorkerComponentId);
	}

	/**
	* Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	*/
	public static SupportWorkerComponent fetchByPrimaryKey(
		long supportWorkerComponentId) {
		return getPersistence().fetchByPrimaryKey(supportWorkerComponentId);
	}

	public static java.util.Map<java.io.Serializable, SupportWorkerComponent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the support worker components.
	*
	* @return the support worker components
	*/
	public static List<SupportWorkerComponent> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of support worker components
	*/
	public static List<SupportWorkerComponent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker components
	*/
	public static List<SupportWorkerComponent> findAll(int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support worker components
	*/
	public static List<SupportWorkerComponent> findAll(int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the support worker components from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support worker components.
	*
	* @return the number of support worker components
	*/
	public static int countAll() {
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

	private static SupportWorkerComponentPersistence _persistence;
}