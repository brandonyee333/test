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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayRoutine;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray routine service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayRoutinePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRoutinePersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayRoutinePersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayRoutineUtil {
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
	public static void clearCache(TestrayRoutine testrayRoutine) {
		getPersistence().clearCache(testrayRoutine);
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
	public static List<TestrayRoutine> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayRoutine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayRoutine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayRoutine> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayRoutine update(TestrayRoutine testrayRoutine) {
		return getPersistence().update(testrayRoutine);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayRoutine update(TestrayRoutine testrayRoutine,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayRoutine, serviceContext);
	}

	/**
	* Caches the testray routine in the entity cache if it is enabled.
	*
	* @param testrayRoutine the testray routine
	*/
	public static void cacheResult(TestrayRoutine testrayRoutine) {
		getPersistence().cacheResult(testrayRoutine);
	}

	/**
	* Caches the testray routines in the entity cache if it is enabled.
	*
	* @param testrayRoutines the testray routines
	*/
	public static void cacheResult(List<TestrayRoutine> testrayRoutines) {
		getPersistence().cacheResult(testrayRoutines);
	}

	/**
	* Creates a new testray routine with the primary key. Does not add the testray routine to the database.
	*
	* @param testrayRoutineId the primary key for the new testray routine
	* @return the new testray routine
	*/
	public static TestrayRoutine create(long testrayRoutineId) {
		return getPersistence().create(testrayRoutineId);
	}

	/**
	* Removes the testray routine with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayRoutineId the primary key of the testray routine
	* @return the testray routine that was removed
	* @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	*/
	public static TestrayRoutine remove(long testrayRoutineId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayRoutineException {
		return getPersistence().remove(testrayRoutineId);
	}

	public static TestrayRoutine updateImpl(TestrayRoutine testrayRoutine) {
		return getPersistence().updateImpl(testrayRoutine);
	}

	/**
	* Returns the testray routine with the primary key or throws a {@link NoSuchTestrayRoutineException} if it could not be found.
	*
	* @param testrayRoutineId the primary key of the testray routine
	* @return the testray routine
	* @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	*/
	public static TestrayRoutine findByPrimaryKey(long testrayRoutineId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayRoutineException {
		return getPersistence().findByPrimaryKey(testrayRoutineId);
	}

	/**
	* Returns the testray routine with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayRoutineId the primary key of the testray routine
	* @return the testray routine, or <code>null</code> if a testray routine with the primary key could not be found
	*/
	public static TestrayRoutine fetchByPrimaryKey(long testrayRoutineId) {
		return getPersistence().fetchByPrimaryKey(testrayRoutineId);
	}

	public static java.util.Map<java.io.Serializable, TestrayRoutine> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray routines.
	*
	* @return the testray routines
	*/
	public static List<TestrayRoutine> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray routines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRoutineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray routines
	* @param end the upper bound of the range of testray routines (not inclusive)
	* @return the range of testray routines
	*/
	public static List<TestrayRoutine> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray routines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRoutineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray routines
	* @param end the upper bound of the range of testray routines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray routines
	*/
	public static List<TestrayRoutine> findAll(int start, int end,
		OrderByComparator<TestrayRoutine> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray routines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRoutineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray routines
	* @param end the upper bound of the range of testray routines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray routines
	*/
	public static List<TestrayRoutine> findAll(int start, int end,
		OrderByComparator<TestrayRoutine> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray routines from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray routines.
	*
	* @return the number of testray routines
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayRoutinePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayRoutinePersistence, TestrayRoutinePersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayRoutinePersistence.class);
}