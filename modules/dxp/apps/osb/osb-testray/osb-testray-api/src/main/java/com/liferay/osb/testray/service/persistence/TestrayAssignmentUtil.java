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

import com.liferay.osb.testray.model.TestrayAssignment;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray assignment service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayAssignmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayAssignmentPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayAssignmentPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayAssignmentUtil {
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
	public static void clearCache(TestrayAssignment testrayAssignment) {
		getPersistence().clearCache(testrayAssignment);
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
	public static List<TestrayAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayAssignment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayAssignment update(TestrayAssignment testrayAssignment) {
		return getPersistence().update(testrayAssignment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayAssignment update(
		TestrayAssignment testrayAssignment, ServiceContext serviceContext) {
		return getPersistence().update(testrayAssignment, serviceContext);
	}

	/**
	* Caches the testray assignment in the entity cache if it is enabled.
	*
	* @param testrayAssignment the testray assignment
	*/
	public static void cacheResult(TestrayAssignment testrayAssignment) {
		getPersistence().cacheResult(testrayAssignment);
	}

	/**
	* Caches the testray assignments in the entity cache if it is enabled.
	*
	* @param testrayAssignments the testray assignments
	*/
	public static void cacheResult(List<TestrayAssignment> testrayAssignments) {
		getPersistence().cacheResult(testrayAssignments);
	}

	/**
	* Creates a new testray assignment with the primary key. Does not add the testray assignment to the database.
	*
	* @param testrayAssignmentId the primary key for the new testray assignment
	* @return the new testray assignment
	*/
	public static TestrayAssignment create(long testrayAssignmentId) {
		return getPersistence().create(testrayAssignmentId);
	}

	/**
	* Removes the testray assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment that was removed
	* @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	*/
	public static TestrayAssignment remove(long testrayAssignmentId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayAssignmentException {
		return getPersistence().remove(testrayAssignmentId);
	}

	public static TestrayAssignment updateImpl(
		TestrayAssignment testrayAssignment) {
		return getPersistence().updateImpl(testrayAssignment);
	}

	/**
	* Returns the testray assignment with the primary key or throws a {@link NoSuchTestrayAssignmentException} if it could not be found.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment
	* @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	*/
	public static TestrayAssignment findByPrimaryKey(long testrayAssignmentId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayAssignmentException {
		return getPersistence().findByPrimaryKey(testrayAssignmentId);
	}

	/**
	* Returns the testray assignment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment, or <code>null</code> if a testray assignment with the primary key could not be found
	*/
	public static TestrayAssignment fetchByPrimaryKey(long testrayAssignmentId) {
		return getPersistence().fetchByPrimaryKey(testrayAssignmentId);
	}

	public static java.util.Map<java.io.Serializable, TestrayAssignment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray assignments.
	*
	* @return the testray assignments
	*/
	public static List<TestrayAssignment> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @return the range of testray assignments
	*/
	public static List<TestrayAssignment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray assignments
	*/
	public static List<TestrayAssignment> findAll(int start, int end,
		OrderByComparator<TestrayAssignment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray assignments
	*/
	public static List<TestrayAssignment> findAll(int start, int end,
		OrderByComparator<TestrayAssignment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray assignments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray assignments.
	*
	* @return the number of testray assignments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayAssignmentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayAssignmentPersistence, TestrayAssignmentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayAssignmentPersistence.class);
}