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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.model.LoopPerson;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop person service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopPersonPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopPersonPersistenceImpl
 * @generated
 */
@ProviderType
public class LoopPersonUtil {
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
	public static void clearCache(LoopPerson loopPerson) {
		getPersistence().clearCache(loopPerson);
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
	public static List<LoopPerson> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopPerson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopPerson> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopPerson> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopPerson update(LoopPerson loopPerson) {
		return getPersistence().update(loopPerson);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopPerson update(LoopPerson loopPerson,
		ServiceContext serviceContext) {
		return getPersistence().update(loopPerson, serviceContext);
	}

	/**
	* Returns the loop person where personUserId = &#63; or throws a {@link NoSuchLoopPersonException} if it could not be found.
	*
	* @param personUserId the person user ID
	* @return the matching loop person
	* @throws NoSuchLoopPersonException if a matching loop person could not be found
	*/
	public static LoopPerson findByPersonUserId(long personUserId)
		throws com.liferay.osb.loop.exception.NoSuchLoopPersonException {
		return getPersistence().findByPersonUserId(personUserId);
	}

	/**
	* Returns the loop person where personUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param personUserId the person user ID
	* @return the matching loop person, or <code>null</code> if a matching loop person could not be found
	*/
	public static LoopPerson fetchByPersonUserId(long personUserId) {
		return getPersistence().fetchByPersonUserId(personUserId);
	}

	/**
	* Returns the loop person where personUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param personUserId the person user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop person, or <code>null</code> if a matching loop person could not be found
	*/
	public static LoopPerson fetchByPersonUserId(long personUserId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByPersonUserId(personUserId, retrieveFromCache);
	}

	/**
	* Removes the loop person where personUserId = &#63; from the database.
	*
	* @param personUserId the person user ID
	* @return the loop person that was removed
	*/
	public static LoopPerson removeByPersonUserId(long personUserId)
		throws com.liferay.osb.loop.exception.NoSuchLoopPersonException {
		return getPersistence().removeByPersonUserId(personUserId);
	}

	/**
	* Returns the number of loop persons where personUserId = &#63;.
	*
	* @param personUserId the person user ID
	* @return the number of matching loop persons
	*/
	public static int countByPersonUserId(long personUserId) {
		return getPersistence().countByPersonUserId(personUserId);
	}

	/**
	* Caches the loop person in the entity cache if it is enabled.
	*
	* @param loopPerson the loop person
	*/
	public static void cacheResult(LoopPerson loopPerson) {
		getPersistence().cacheResult(loopPerson);
	}

	/**
	* Caches the loop persons in the entity cache if it is enabled.
	*
	* @param loopPersons the loop persons
	*/
	public static void cacheResult(List<LoopPerson> loopPersons) {
		getPersistence().cacheResult(loopPersons);
	}

	/**
	* Creates a new loop person with the primary key. Does not add the loop person to the database.
	*
	* @param loopPersonId the primary key for the new loop person
	* @return the new loop person
	*/
	public static LoopPerson create(long loopPersonId) {
		return getPersistence().create(loopPersonId);
	}

	/**
	* Removes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person that was removed
	* @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	*/
	public static LoopPerson remove(long loopPersonId)
		throws com.liferay.osb.loop.exception.NoSuchLoopPersonException {
		return getPersistence().remove(loopPersonId);
	}

	public static LoopPerson updateImpl(LoopPerson loopPerson) {
		return getPersistence().updateImpl(loopPerson);
	}

	/**
	* Returns the loop person with the primary key or throws a {@link NoSuchLoopPersonException} if it could not be found.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person
	* @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	*/
	public static LoopPerson findByPrimaryKey(long loopPersonId)
		throws com.liferay.osb.loop.exception.NoSuchLoopPersonException {
		return getPersistence().findByPrimaryKey(loopPersonId);
	}

	/**
	* Returns the loop person with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person, or <code>null</code> if a loop person with the primary key could not be found
	*/
	public static LoopPerson fetchByPrimaryKey(long loopPersonId) {
		return getPersistence().fetchByPrimaryKey(loopPersonId);
	}

	public static java.util.Map<java.io.Serializable, LoopPerson> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the loop persons.
	*
	* @return the loop persons
	*/
	public static List<LoopPerson> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @return the range of loop persons
	*/
	public static List<LoopPerson> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop persons
	*/
	public static List<LoopPerson> findAll(int start, int end,
		OrderByComparator<LoopPerson> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop persons
	*/
	public static List<LoopPerson> findAll(int start, int end,
		OrderByComparator<LoopPerson> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the loop persons from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of loop persons.
	*
	* @return the number of loop persons
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopPersonPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopPersonPersistence, LoopPersonPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopPersonPersistence.class);

		ServiceTracker<LoopPersonPersistence, LoopPersonPersistence> serviceTracker =
			new ServiceTracker<LoopPersonPersistence, LoopPersonPersistence>(bundle.getBundleContext(),
				LoopPersonPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}