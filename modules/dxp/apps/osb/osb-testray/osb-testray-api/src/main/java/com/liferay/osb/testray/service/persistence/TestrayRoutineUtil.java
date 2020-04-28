/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.service.persistence;

import com.liferay.osb.testray.model.TestrayRoutine;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the testray routine service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayRoutinePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRoutinePersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, TestrayRoutine> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static TestrayRoutine update(
		TestrayRoutine testrayRoutine, ServiceContext serviceContext) {

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
	 * Returns the testray routine with the primary key or throws a <code>NoSuchTestrayRoutineException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray routines
	 */
	public static List<TestrayRoutine> findAll(
		int start, int end,
		OrderByComparator<TestrayRoutine> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray routines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray routines
	 */
	public static List<TestrayRoutine> findAll(
		int start, int end, OrderByComparator<TestrayRoutine> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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

	private static ServiceTracker
		<TestrayRoutinePersistence, TestrayRoutinePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayRoutinePersistence.class);

		ServiceTracker<TestrayRoutinePersistence, TestrayRoutinePersistence>
			serviceTracker =
				new ServiceTracker
					<TestrayRoutinePersistence, TestrayRoutinePersistence>(
						bundle.getBundleContext(),
						TestrayRoutinePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}