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

import com.liferay.osb.testray.model.TestraySuite;
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
 * The persistence utility for the testray suite service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestraySuitePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySuitePersistence
 * @generated
 */
public class TestraySuiteUtil {

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
	public static void clearCache(TestraySuite testraySuite) {
		getPersistence().clearCache(testraySuite);
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
	public static Map<Serializable, TestraySuite> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestraySuite> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestraySuite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestraySuite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestraySuite> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestraySuite update(TestraySuite testraySuite) {
		return getPersistence().update(testraySuite);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestraySuite update(
		TestraySuite testraySuite, ServiceContext serviceContext) {

		return getPersistence().update(testraySuite, serviceContext);
	}

	/**
	 * Caches the testray suite in the entity cache if it is enabled.
	 *
	 * @param testraySuite the testray suite
	 */
	public static void cacheResult(TestraySuite testraySuite) {
		getPersistence().cacheResult(testraySuite);
	}

	/**
	 * Caches the testray suites in the entity cache if it is enabled.
	 *
	 * @param testraySuites the testray suites
	 */
	public static void cacheResult(List<TestraySuite> testraySuites) {
		getPersistence().cacheResult(testraySuites);
	}

	/**
	 * Creates a new testray suite with the primary key. Does not add the testray suite to the database.
	 *
	 * @param testraySuiteId the primary key for the new testray suite
	 * @return the new testray suite
	 */
	public static TestraySuite create(long testraySuiteId) {
		return getPersistence().create(testraySuiteId);
	}

	/**
	 * Removes the testray suite with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite that was removed
	 * @throws NoSuchTestraySuiteException if a testray suite with the primary key could not be found
	 */
	public static TestraySuite remove(long testraySuiteId)
		throws com.liferay.osb.testray.exception.NoSuchTestraySuiteException {

		return getPersistence().remove(testraySuiteId);
	}

	public static TestraySuite updateImpl(TestraySuite testraySuite) {
		return getPersistence().updateImpl(testraySuite);
	}

	/**
	 * Returns the testray suite with the primary key or throws a <code>NoSuchTestraySuiteException</code> if it could not be found.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite
	 * @throws NoSuchTestraySuiteException if a testray suite with the primary key could not be found
	 */
	public static TestraySuite findByPrimaryKey(long testraySuiteId)
		throws com.liferay.osb.testray.exception.NoSuchTestraySuiteException {

		return getPersistence().findByPrimaryKey(testraySuiteId);
	}

	/**
	 * Returns the testray suite with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite, or <code>null</code> if a testray suite with the primary key could not be found
	 */
	public static TestraySuite fetchByPrimaryKey(long testraySuiteId) {
		return getPersistence().fetchByPrimaryKey(testraySuiteId);
	}

	/**
	 * Returns all the testray suites.
	 *
	 * @return the testray suites
	 */
	public static List<TestraySuite> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @return the range of testray suites
	 */
	public static List<TestraySuite> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray suites
	 */
	public static List<TestraySuite> findAll(
		int start, int end, OrderByComparator<TestraySuite> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray suites
	 */
	public static List<TestraySuite> findAll(
		int start, int end, OrderByComparator<TestraySuite> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray suites from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray suites.
	 *
	 * @return the number of testray suites
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray cases associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @return long[] of the primaryKeys of testray cases associated with the testray suite
	 */
	public static long[] getTestrayCasePrimaryKeys(long pk) {
		return getPersistence().getTestrayCasePrimaryKeys(pk);
	}

	/**
	 * Returns all the testray cases associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @return the testray cases associated with the testray suite
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk) {

		return getPersistence().getTestrayCases(pk);
	}

	/**
	 * Returns a range of all the testray cases associated with the testray suite.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray suite
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @return the range of testray cases associated with the testray suite
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end) {

		return getPersistence().getTestrayCases(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray cases associated with the testray suite.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray suite
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases associated with the testray suite
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayCase>
				orderByComparator) {

		return getPersistence().getTestrayCases(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray cases associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @return the number of testray cases associated with the testray suite
	 */
	public static int getTestrayCasesSize(long pk) {
		return getPersistence().getTestrayCasesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray suite; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCase(long pk, long testrayCasePK) {
		return getPersistence().containsTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Returns <code>true</code> if the testray suite has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray suite to check for associations with testray cases
	 * @return <code>true</code> if the testray suite has any testray cases associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCases(long pk) {
		return getPersistence().containsTestrayCases(pk);
	}

	/**
	 * Adds an association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePK the primary key of the testray case
	 */
	public static void addTestrayCase(long pk, long testrayCasePK) {
		getPersistence().addTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Adds an association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCase the testray case
	 */
	public static void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getPersistence().addTestrayCase(pk, testrayCase);
	}

	/**
	 * Adds an association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public static void addTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().addTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Adds an association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCases the testray cases
	 */
	public static void addTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().addTestrayCases(pk, testrayCases);
	}

	/**
	 * Clears all associations between the testray suite and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite to clear the associated testray cases from
	 */
	public static void clearTestrayCases(long pk) {
		getPersistence().clearTestrayCases(pk);
	}

	/**
	 * Removes the association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePK the primary key of the testray case
	 */
	public static void removeTestrayCase(long pk, long testrayCasePK) {
		getPersistence().removeTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Removes the association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCase the testray case
	 */
	public static void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getPersistence().removeTestrayCase(pk, testrayCase);
	}

	/**
	 * Removes the association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public static void removeTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().removeTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Removes the association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCases the testray cases
	 */
	public static void removeTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().removeTestrayCases(pk, testrayCases);
	}

	/**
	 * Sets the testray cases associated with the testray suite, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray suite
	 */
	public static void setTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().setTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Sets the testray cases associated with the testray suite, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCases the testray cases to be associated with the testray suite
	 */
	public static void setTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().setTestrayCases(pk, testrayCases);
	}

	public static TestraySuitePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestraySuitePersistence, TestraySuitePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestraySuitePersistence.class);

		ServiceTracker<TestraySuitePersistence, TestraySuitePersistence>
			serviceTracker =
				new ServiceTracker
					<TestraySuitePersistence, TestraySuitePersistence>(
						bundle.getBundleContext(),
						TestraySuitePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}