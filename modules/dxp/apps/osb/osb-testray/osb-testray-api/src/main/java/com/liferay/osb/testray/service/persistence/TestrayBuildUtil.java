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

import com.liferay.osb.testray.model.TestrayBuild;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray build service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayBuildPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayBuildPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayBuildPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayBuildUtil {
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
	public static void clearCache(TestrayBuild testrayBuild) {
		getPersistence().clearCache(testrayBuild);
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
	public static List<TestrayBuild> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayBuild> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayBuild> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayBuild> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayBuild update(TestrayBuild testrayBuild) {
		return getPersistence().update(testrayBuild);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayBuild update(TestrayBuild testrayBuild,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayBuild, serviceContext);
	}

	/**
	* Returns the testray build where testrayRoutineId = &#63; and name = &#63; or throws a {@link NoSuchTestrayBuildException} if it could not be found.
	*
	* @param testrayRoutineId the testray routine ID
	* @param name the name
	* @return the matching testray build
	* @throws NoSuchTestrayBuildException if a matching testray build could not be found
	*/
	public static TestrayBuild findByTRI_N(long testrayRoutineId,
		java.lang.String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildException {
		return getPersistence().findByTRI_N(testrayRoutineId, name);
	}

	/**
	* Returns the testray build where testrayRoutineId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayRoutineId the testray routine ID
	* @param name the name
	* @return the matching testray build, or <code>null</code> if a matching testray build could not be found
	*/
	public static TestrayBuild fetchByTRI_N(long testrayRoutineId,
		java.lang.String name) {
		return getPersistence().fetchByTRI_N(testrayRoutineId, name);
	}

	/**
	* Returns the testray build where testrayRoutineId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayRoutineId the testray routine ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray build, or <code>null</code> if a matching testray build could not be found
	*/
	public static TestrayBuild fetchByTRI_N(long testrayRoutineId,
		java.lang.String name, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTRI_N(testrayRoutineId, name, retrieveFromCache);
	}

	/**
	* Removes the testray build where testrayRoutineId = &#63; and name = &#63; from the database.
	*
	* @param testrayRoutineId the testray routine ID
	* @param name the name
	* @return the testray build that was removed
	*/
	public static TestrayBuild removeByTRI_N(long testrayRoutineId,
		java.lang.String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildException {
		return getPersistence().removeByTRI_N(testrayRoutineId, name);
	}

	/**
	* Returns the number of testray builds where testrayRoutineId = &#63; and name = &#63;.
	*
	* @param testrayRoutineId the testray routine ID
	* @param name the name
	* @return the number of matching testray builds
	*/
	public static int countByTRI_N(long testrayRoutineId, java.lang.String name) {
		return getPersistence().countByTRI_N(testrayRoutineId, name);
	}

	/**
	* Caches the testray build in the entity cache if it is enabled.
	*
	* @param testrayBuild the testray build
	*/
	public static void cacheResult(TestrayBuild testrayBuild) {
		getPersistence().cacheResult(testrayBuild);
	}

	/**
	* Caches the testray builds in the entity cache if it is enabled.
	*
	* @param testrayBuilds the testray builds
	*/
	public static void cacheResult(List<TestrayBuild> testrayBuilds) {
		getPersistence().cacheResult(testrayBuilds);
	}

	/**
	* Creates a new testray build with the primary key. Does not add the testray build to the database.
	*
	* @param testrayBuildId the primary key for the new testray build
	* @return the new testray build
	*/
	public static TestrayBuild create(long testrayBuildId) {
		return getPersistence().create(testrayBuildId);
	}

	/**
	* Removes the testray build with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build that was removed
	* @throws NoSuchTestrayBuildException if a testray build with the primary key could not be found
	*/
	public static TestrayBuild remove(long testrayBuildId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildException {
		return getPersistence().remove(testrayBuildId);
	}

	public static TestrayBuild updateImpl(TestrayBuild testrayBuild) {
		return getPersistence().updateImpl(testrayBuild);
	}

	/**
	* Returns the testray build with the primary key or throws a {@link NoSuchTestrayBuildException} if it could not be found.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build
	* @throws NoSuchTestrayBuildException if a testray build with the primary key could not be found
	*/
	public static TestrayBuild findByPrimaryKey(long testrayBuildId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildException {
		return getPersistence().findByPrimaryKey(testrayBuildId);
	}

	/**
	* Returns the testray build with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build, or <code>null</code> if a testray build with the primary key could not be found
	*/
	public static TestrayBuild fetchByPrimaryKey(long testrayBuildId) {
		return getPersistence().fetchByPrimaryKey(testrayBuildId);
	}

	public static java.util.Map<java.io.Serializable, TestrayBuild> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray builds.
	*
	* @return the testray builds
	*/
	public static List<TestrayBuild> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray builds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @return the range of testray builds
	*/
	public static List<TestrayBuild> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray builds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray builds
	*/
	public static List<TestrayBuild> findAll(int start, int end,
		OrderByComparator<TestrayBuild> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray builds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray builds
	*/
	public static List<TestrayBuild> findAll(int start, int end,
		OrderByComparator<TestrayBuild> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray builds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray builds.
	*
	* @return the number of testray builds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of testray cases associated with the testray build.
	*
	* @param pk the primary key of the testray build
	* @return long[] of the primaryKeys of testray cases associated with the testray build
	*/
	public static long[] getTestrayCasePrimaryKeys(long pk) {
		return getPersistence().getTestrayCasePrimaryKeys(pk);
	}

	/**
	* Returns all the testray cases associated with the testray build.
	*
	* @param pk the primary key of the testray build
	* @return the testray cases associated with the testray build
	*/
	public static List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk) {
		return getPersistence().getTestrayCases(pk);
	}

	/**
	* Returns a range of all the testray cases associated with the testray build.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the testray build
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @return the range of testray cases associated with the testray build
	*/
	public static List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk, int start, int end) {
		return getPersistence().getTestrayCases(pk, start, end);
	}

	/**
	* Returns an ordered range of all the testray cases associated with the testray build.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the testray build
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray cases associated with the testray build
	*/
	public static List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {
		return getPersistence()
				   .getTestrayCases(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of testray cases associated with the testray build.
	*
	* @param pk the primary key of the testray build
	* @return the number of testray cases associated with the testray build
	*/
	public static int getTestrayCasesSize(long pk) {
		return getPersistence().getTestrayCasesSize(pk);
	}

	/**
	* Returns <code>true</code> if the testray case is associated with the testray build.
	*
	* @param pk the primary key of the testray build
	* @param testrayCasePK the primary key of the testray case
	* @return <code>true</code> if the testray case is associated with the testray build; <code>false</code> otherwise
	*/
	public static boolean containsTestrayCase(long pk, long testrayCasePK) {
		return getPersistence().containsTestrayCase(pk, testrayCasePK);
	}

	/**
	* Returns <code>true</code> if the testray build has any testray cases associated with it.
	*
	* @param pk the primary key of the testray build to check for associations with testray cases
	* @return <code>true</code> if the testray build has any testray cases associated with it; <code>false</code> otherwise
	*/
	public static boolean containsTestrayCases(long pk) {
		return getPersistence().containsTestrayCases(pk);
	}

	/**
	* Adds an association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCasePK the primary key of the testray case
	*/
	public static void addTestrayCase(long pk, long testrayCasePK) {
		getPersistence().addTestrayCase(pk, testrayCasePK);
	}

	/**
	* Adds an association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCase the testray case
	*/
	public static void addTestrayCase(long pk,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {
		getPersistence().addTestrayCase(pk, testrayCase);
	}

	/**
	* Adds an association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCasePKs the primary keys of the testray cases
	*/
	public static void addTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().addTestrayCases(pk, testrayCasePKs);
	}

	/**
	* Adds an association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCases the testray cases
	*/
	public static void addTestrayCases(long pk,
		List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {
		getPersistence().addTestrayCases(pk, testrayCases);
	}

	/**
	* Clears all associations between the testray build and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build to clear the associated testray cases from
	*/
	public static void clearTestrayCases(long pk) {
		getPersistence().clearTestrayCases(pk);
	}

	/**
	* Removes the association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCasePK the primary key of the testray case
	*/
	public static void removeTestrayCase(long pk, long testrayCasePK) {
		getPersistence().removeTestrayCase(pk, testrayCasePK);
	}

	/**
	* Removes the association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCase the testray case
	*/
	public static void removeTestrayCase(long pk,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {
		getPersistence().removeTestrayCase(pk, testrayCase);
	}

	/**
	* Removes the association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCasePKs the primary keys of the testray cases
	*/
	public static void removeTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().removeTestrayCases(pk, testrayCasePKs);
	}

	/**
	* Removes the association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCases the testray cases
	*/
	public static void removeTestrayCases(long pk,
		List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {
		getPersistence().removeTestrayCases(pk, testrayCases);
	}

	/**
	* Sets the testray cases associated with the testray build, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCasePKs the primary keys of the testray cases to be associated with the testray build
	*/
	public static void setTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().setTestrayCases(pk, testrayCasePKs);
	}

	/**
	* Sets the testray cases associated with the testray build, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the testray build
	* @param testrayCases the testray cases to be associated with the testray build
	*/
	public static void setTestrayCases(long pk,
		List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {
		getPersistence().setTestrayCases(pk, testrayCases);
	}

	public static TestrayBuildPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayBuildPersistence, TestrayBuildPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayBuildPersistence.class);
}