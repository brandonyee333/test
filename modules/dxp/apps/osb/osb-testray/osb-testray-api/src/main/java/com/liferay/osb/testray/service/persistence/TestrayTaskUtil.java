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

import com.liferay.osb.testray.model.TestrayTask;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray task service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayTaskPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayTaskPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayTaskPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayTaskUtil {
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
	public static void clearCache(TestrayTask testrayTask) {
		getPersistence().clearCache(testrayTask);
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
	public static List<TestrayTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayTask> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayTask update(TestrayTask testrayTask) {
		return getPersistence().update(testrayTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayTask update(TestrayTask testrayTask,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayTask, serviceContext);
	}

	/**
	* Caches the testray task in the entity cache if it is enabled.
	*
	* @param testrayTask the testray task
	*/
	public static void cacheResult(TestrayTask testrayTask) {
		getPersistence().cacheResult(testrayTask);
	}

	/**
	* Caches the testray tasks in the entity cache if it is enabled.
	*
	* @param testrayTasks the testray tasks
	*/
	public static void cacheResult(List<TestrayTask> testrayTasks) {
		getPersistence().cacheResult(testrayTasks);
	}

	/**
	* Creates a new testray task with the primary key. Does not add the testray task to the database.
	*
	* @param testrayTaskId the primary key for the new testray task
	* @return the new testray task
	*/
	public static TestrayTask create(long testrayTaskId) {
		return getPersistence().create(testrayTaskId);
	}

	/**
	* Removes the testray task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayTaskId the primary key of the testray task
	* @return the testray task that was removed
	* @throws NoSuchTestrayTaskException if a testray task with the primary key could not be found
	*/
	public static TestrayTask remove(long testrayTaskId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayTaskException {
		return getPersistence().remove(testrayTaskId);
	}

	public static TestrayTask updateImpl(TestrayTask testrayTask) {
		return getPersistence().updateImpl(testrayTask);
	}

	/**
	* Returns the testray task with the primary key or throws a {@link NoSuchTestrayTaskException} if it could not be found.
	*
	* @param testrayTaskId the primary key of the testray task
	* @return the testray task
	* @throws NoSuchTestrayTaskException if a testray task with the primary key could not be found
	*/
	public static TestrayTask findByPrimaryKey(long testrayTaskId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayTaskException {
		return getPersistence().findByPrimaryKey(testrayTaskId);
	}

	/**
	* Returns the testray task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayTaskId the primary key of the testray task
	* @return the testray task, or <code>null</code> if a testray task with the primary key could not be found
	*/
	public static TestrayTask fetchByPrimaryKey(long testrayTaskId) {
		return getPersistence().fetchByPrimaryKey(testrayTaskId);
	}

	public static java.util.Map<java.io.Serializable, TestrayTask> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray tasks.
	*
	* @return the testray tasks
	*/
	public static List<TestrayTask> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray tasks
	* @param end the upper bound of the range of testray tasks (not inclusive)
	* @return the range of testray tasks
	*/
	public static List<TestrayTask> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray tasks
	* @param end the upper bound of the range of testray tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray tasks
	*/
	public static List<TestrayTask> findAll(int start, int end,
		OrderByComparator<TestrayTask> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray tasks
	* @param end the upper bound of the range of testray tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray tasks
	*/
	public static List<TestrayTask> findAll(int start, int end,
		OrderByComparator<TestrayTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray tasks from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray tasks.
	*
	* @return the number of testray tasks
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of testray case types associated with the testray task.
	*
	* @param pk the primary key of the testray task
	* @return long[] of the primaryKeys of testray case types associated with the testray task
	*/
	public static long[] getTestrayCaseTypePrimaryKeys(long pk) {
		return getPersistence().getTestrayCaseTypePrimaryKeys(pk);
	}

	/**
	* Returns all the testray case types associated with the testray task.
	*
	* @param pk the primary key of the testray task
	* @return the testray case types associated with the testray task
	*/
	public static List<com.liferay.osb.testray.model.TestrayCaseType> getTestrayCaseTypes(
		long pk) {
		return getPersistence().getTestrayCaseTypes(pk);
	}

	/**
	* Returns a range of all the testray case types associated with the testray task.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the testray task
	* @param start the lower bound of the range of testray tasks
	* @param end the upper bound of the range of testray tasks (not inclusive)
	* @return the range of testray case types associated with the testray task
	*/
	public static List<com.liferay.osb.testray.model.TestrayCaseType> getTestrayCaseTypes(
		long pk, int start, int end) {
		return getPersistence().getTestrayCaseTypes(pk, start, end);
	}

	/**
	* Returns an ordered range of all the testray case types associated with the testray task.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the testray task
	* @param start the lower bound of the range of testray tasks
	* @param end the upper bound of the range of testray tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray case types associated with the testray task
	*/
	public static List<com.liferay.osb.testray.model.TestrayCaseType> getTestrayCaseTypes(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.testray.model.TestrayCaseType> orderByComparator) {
		return getPersistence()
				   .getTestrayCaseTypes(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of testray case types associated with the testray task.
	*
	* @param pk the primary key of the testray task
	* @return the number of testray case types associated with the testray task
	*/
	public static int getTestrayCaseTypesSize(long pk) {
		return getPersistence().getTestrayCaseTypesSize(pk);
	}

	/**
	* Returns <code>true</code> if the testray case type is associated with the testray task.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypePK the primary key of the testray case type
	* @return <code>true</code> if the testray case type is associated with the testray task; <code>false</code> otherwise
	*/
	public static boolean containsTestrayCaseType(long pk,
		long testrayCaseTypePK) {
		return getPersistence().containsTestrayCaseType(pk, testrayCaseTypePK);
	}

	/**
	* Returns <code>true</code> if the testray task has any testray case types associated with it.
	*
	* @param pk the primary key of the testray task to check for associations with testray case types
	* @return <code>true</code> if the testray task has any testray case types associated with it; <code>false</code> otherwise
	*/
	public static boolean containsTestrayCaseTypes(long pk) {
		return getPersistence().containsTestrayCaseTypes(pk);
	}

	/**
	* Adds an association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypePK the primary key of the testray case type
	*/
	public static void addTestrayCaseType(long pk, long testrayCaseTypePK) {
		getPersistence().addTestrayCaseType(pk, testrayCaseTypePK);
	}

	/**
	* Adds an association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseType the testray case type
	*/
	public static void addTestrayCaseType(long pk,
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {
		getPersistence().addTestrayCaseType(pk, testrayCaseType);
	}

	/**
	* Adds an association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypePKs the primary keys of the testray case types
	*/
	public static void addTestrayCaseTypes(long pk, long[] testrayCaseTypePKs) {
		getPersistence().addTestrayCaseTypes(pk, testrayCaseTypePKs);
	}

	/**
	* Adds an association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypes the testray case types
	*/
	public static void addTestrayCaseTypes(long pk,
		List<com.liferay.osb.testray.model.TestrayCaseType> testrayCaseTypes) {
		getPersistence().addTestrayCaseTypes(pk, testrayCaseTypes);
	}

	/**
	* Clears all associations between the testray task and its testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task to clear the associated testray case types from
	*/
	public static void clearTestrayCaseTypes(long pk) {
		getPersistence().clearTestrayCaseTypes(pk);
	}

	/**
	* Removes the association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypePK the primary key of the testray case type
	*/
	public static void removeTestrayCaseType(long pk, long testrayCaseTypePK) {
		getPersistence().removeTestrayCaseType(pk, testrayCaseTypePK);
	}

	/**
	* Removes the association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseType the testray case type
	*/
	public static void removeTestrayCaseType(long pk,
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {
		getPersistence().removeTestrayCaseType(pk, testrayCaseType);
	}

	/**
	* Removes the association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypePKs the primary keys of the testray case types
	*/
	public static void removeTestrayCaseTypes(long pk, long[] testrayCaseTypePKs) {
		getPersistence().removeTestrayCaseTypes(pk, testrayCaseTypePKs);
	}

	/**
	* Removes the association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypes the testray case types
	*/
	public static void removeTestrayCaseTypes(long pk,
		List<com.liferay.osb.testray.model.TestrayCaseType> testrayCaseTypes) {
		getPersistence().removeTestrayCaseTypes(pk, testrayCaseTypes);
	}

	/**
	* Sets the testray case types associated with the testray task, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypePKs the primary keys of the testray case types to be associated with the testray task
	*/
	public static void setTestrayCaseTypes(long pk, long[] testrayCaseTypePKs) {
		getPersistence().setTestrayCaseTypes(pk, testrayCaseTypePKs);
	}

	/**
	* Sets the testray case types associated with the testray task, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray task
	* @param testrayCaseTypes the testray case types to be associated with the testray task
	*/
	public static void setTestrayCaseTypes(long pk,
		List<com.liferay.osb.testray.model.TestrayCaseType> testrayCaseTypes) {
		getPersistence().setTestrayCaseTypes(pk, testrayCaseTypes);
	}

	public static TestrayTaskPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayTaskPersistence, TestrayTaskPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayTaskPersistence.class);
}