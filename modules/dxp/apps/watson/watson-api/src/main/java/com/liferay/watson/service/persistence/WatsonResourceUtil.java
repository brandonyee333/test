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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.model.WatsonResource;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson resource service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonResourcePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonResourcePersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonResourcePersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonResourceUtil {
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
	public static void clearCache(WatsonResource watsonResource) {
		getPersistence().clearCache(watsonResource);
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
	public static List<WatsonResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonResource> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonResource update(WatsonResource watsonResource) {
		return getPersistence().update(watsonResource);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonResource update(WatsonResource watsonResource,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonResource, serviceContext);
	}

	/**
	* Caches the watson resource in the entity cache if it is enabled.
	*
	* @param watsonResource the watson resource
	*/
	public static void cacheResult(WatsonResource watsonResource) {
		getPersistence().cacheResult(watsonResource);
	}

	/**
	* Caches the watson resources in the entity cache if it is enabled.
	*
	* @param watsonResources the watson resources
	*/
	public static void cacheResult(List<WatsonResource> watsonResources) {
		getPersistence().cacheResult(watsonResources);
	}

	/**
	* Creates a new watson resource with the primary key. Does not add the watson resource to the database.
	*
	* @param watsonResourceId the primary key for the new watson resource
	* @return the new watson resource
	*/
	public static WatsonResource create(long watsonResourceId) {
		return getPersistence().create(watsonResourceId);
	}

	/**
	* Removes the watson resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource that was removed
	* @throws NoSuchResourceException if a watson resource with the primary key could not be found
	*/
	public static WatsonResource remove(long watsonResourceId)
		throws com.liferay.watson.exception.NoSuchResourceException {
		return getPersistence().remove(watsonResourceId);
	}

	public static WatsonResource updateImpl(WatsonResource watsonResource) {
		return getPersistence().updateImpl(watsonResource);
	}

	/**
	* Returns the watson resource with the primary key or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource
	* @throws NoSuchResourceException if a watson resource with the primary key could not be found
	*/
	public static WatsonResource findByPrimaryKey(long watsonResourceId)
		throws com.liferay.watson.exception.NoSuchResourceException {
		return getPersistence().findByPrimaryKey(watsonResourceId);
	}

	/**
	* Returns the watson resource with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource, or <code>null</code> if a watson resource with the primary key could not be found
	*/
	public static WatsonResource fetchByPrimaryKey(long watsonResourceId) {
		return getPersistence().fetchByPrimaryKey(watsonResourceId);
	}

	public static java.util.Map<java.io.Serializable, WatsonResource> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson resources.
	*
	* @return the watson resources
	*/
	public static List<WatsonResource> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @return the range of watson resources
	*/
	public static List<WatsonResource> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson resources
	*/
	public static List<WatsonResource> findAll(int start, int end,
		OrderByComparator<WatsonResource> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson resources
	*/
	public static List<WatsonResource> findAll(int start, int end,
		OrderByComparator<WatsonResource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson resources from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson resources.
	*
	* @return the number of watson resources
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonResourcePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonResourcePersistence, WatsonResourcePersistence> _serviceTracker =
		ServiceTrackerFactory.open(WatsonResourcePersistence.class);
}