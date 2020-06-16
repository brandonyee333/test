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

import com.liferay.osb.testray.model.TestraySuite;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray suite service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestraySuitePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySuitePersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestraySuitePersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static TestraySuite update(TestraySuite testraySuite,
		ServiceContext serviceContext) {
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
	* Returns the testray suite with the primary key or throws a {@link NoSuchTestraySuiteException} if it could not be found.
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

	public static java.util.Map<java.io.Serializable, TestraySuite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray suites
	* @param end the upper bound of the range of testray suites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray suites
	*/
	public static List<TestraySuite> findAll(int start, int end,
		OrderByComparator<TestraySuite> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray suites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray suites
	* @param end the upper bound of the range of testray suites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray suites
	*/
	public static List<TestraySuite> findAll(int start, int end,
		OrderByComparator<TestraySuite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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
	public static List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk) {
		return getPersistence().getTestrayCases(pk);
	}

	/**
	* Returns a range of all the testray cases associated with the testray suite.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the testray suite
	* @param start the lower bound of the range of testray suites
	* @param end the upper bound of the range of testray suites (not inclusive)
	* @return the range of testray cases associated with the testray suite
	*/
	public static List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk, int start, int end) {
		return getPersistence().getTestrayCases(pk, start, end);
	}

	/**
	* Returns an ordered range of all the testray cases associated with the testray suite.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the testray suite
	* @param start the lower bound of the range of testray suites
	* @param end the upper bound of the range of testray suites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray cases associated with the testray suite
	*/
	public static List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {
		return getPersistence()
				   .getTestrayCases(pk, start, end, orderByComparator);
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
	public static void addTestrayCase(long pk,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {
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
	public static void addTestrayCases(long pk,
		List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {
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
	public static void removeTestrayCase(long pk,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {
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
	public static void removeTestrayCases(long pk,
		List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {
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
	public static void setTestrayCases(long pk,
		List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {
		getPersistence().setTestrayCases(pk, testrayCases);
	}

	public static TestraySuitePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestraySuitePersistence, TestraySuitePersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestraySuitePersistence.class);
}