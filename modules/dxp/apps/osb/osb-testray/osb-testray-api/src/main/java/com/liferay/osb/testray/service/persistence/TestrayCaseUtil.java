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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayCase;
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
 * The persistence utility for the testray case service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayCasePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCasePersistence
 * @generated
 */
@ProviderType
public class TestrayCaseUtil {

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
	public static void clearCache(TestrayCase testrayCase) {
		getPersistence().clearCache(testrayCase);
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
	public static Map<Serializable, TestrayCase> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayCase> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayCase> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayCase> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayCase> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayCase update(TestrayCase testrayCase) {
		return getPersistence().update(testrayCase);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayCase update(
		TestrayCase testrayCase, ServiceContext serviceContext) {

		return getPersistence().update(testrayCase, serviceContext);
	}

	/**
	 * Returns the testray case where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayCaseException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray case
	 * @throws NoSuchTestrayCaseException if a matching testray case could not be found
	 */
	public static TestrayCase findByTPI_N(long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseException {

		return getPersistence().findByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray case where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray case, or <code>null</code> if a matching testray case could not be found
	 */
	public static TestrayCase fetchByTPI_N(long testrayProjectId, String name) {
		return getPersistence().fetchByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray case where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case, or <code>null</code> if a matching testray case could not be found
	 */
	public static TestrayCase fetchByTPI_N(
		long testrayProjectId, String name, boolean retrieveFromCache) {

		return getPersistence().fetchByTPI_N(
			testrayProjectId, name, retrieveFromCache);
	}

	/**
	 * Removes the testray case where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray case that was removed
	 */
	public static TestrayCase removeByTPI_N(long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseException {

		return getPersistence().removeByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the number of testray cases where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray cases
	 */
	public static int countByTPI_N(long testrayProjectId, String name) {
		return getPersistence().countByTPI_N(testrayProjectId, name);
	}

	/**
	 * Caches the testray case in the entity cache if it is enabled.
	 *
	 * @param testrayCase the testray case
	 */
	public static void cacheResult(TestrayCase testrayCase) {
		getPersistence().cacheResult(testrayCase);
	}

	/**
	 * Caches the testray cases in the entity cache if it is enabled.
	 *
	 * @param testrayCases the testray cases
	 */
	public static void cacheResult(List<TestrayCase> testrayCases) {
		getPersistence().cacheResult(testrayCases);
	}

	/**
	 * Creates a new testray case with the primary key. Does not add the testray case to the database.
	 *
	 * @param testrayCaseId the primary key for the new testray case
	 * @return the new testray case
	 */
	public static TestrayCase create(long testrayCaseId) {
		return getPersistence().create(testrayCaseId);
	}

	/**
	 * Removes the testray case with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case that was removed
	 * @throws NoSuchTestrayCaseException if a testray case with the primary key could not be found
	 */
	public static TestrayCase remove(long testrayCaseId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseException {

		return getPersistence().remove(testrayCaseId);
	}

	public static TestrayCase updateImpl(TestrayCase testrayCase) {
		return getPersistence().updateImpl(testrayCase);
	}

	/**
	 * Returns the testray case with the primary key or throws a <code>NoSuchTestrayCaseException</code> if it could not be found.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case
	 * @throws NoSuchTestrayCaseException if a testray case with the primary key could not be found
	 */
	public static TestrayCase findByPrimaryKey(long testrayCaseId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayCaseException {

		return getPersistence().findByPrimaryKey(testrayCaseId);
	}

	/**
	 * Returns the testray case with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case, or <code>null</code> if a testray case with the primary key could not be found
	 */
	public static TestrayCase fetchByPrimaryKey(long testrayCaseId) {
		return getPersistence().fetchByPrimaryKey(testrayCaseId);
	}

	/**
	 * Returns all the testray cases.
	 *
	 * @return the testray cases
	 */
	public static List<TestrayCase> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray cases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray cases
	 */
	public static List<TestrayCase> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray cases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases
	 */
	public static List<TestrayCase> findAll(
		int start, int end, OrderByComparator<TestrayCase> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray cases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray cases
	 */
	public static List<TestrayCase> findAll(
		int start, int end, OrderByComparator<TestrayCase> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the testray cases from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray cases.
	 *
	 * @return the number of testray cases
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray builds associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray builds associated with the testray case
	 */
	public static long[] getTestrayBuildPrimaryKeys(long pk) {
		return getPersistence().getTestrayBuildPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray builds associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray builds associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayBuild>
		getTestrayBuilds(long pk) {

		return getPersistence().getTestrayBuilds(pk);
	}

	/**
	 * Returns a range of all the testray builds associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray builds associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayBuild>
		getTestrayBuilds(long pk, int start, int end) {

		return getPersistence().getTestrayBuilds(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray builds associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray builds associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayBuild>
		getTestrayBuilds(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayBuild>
				orderByComparator) {

		return getPersistence().getTestrayBuilds(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray builds associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray builds associated with the testray case
	 */
	public static int getTestrayBuildsSize(long pk) {
		return getPersistence().getTestrayBuildsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray build is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPK the primary key of the testray build
	 * @return <code>true</code> if the testray build is associated with the testray case; <code>false</code> otherwise
	 */
	public static boolean containsTestrayBuild(long pk, long testrayBuildPK) {
		return getPersistence().containsTestrayBuild(pk, testrayBuildPK);
	}

	/**
	 * Returns <code>true</code> if the testray case has any testray builds associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray builds
	 * @return <code>true</code> if the testray case has any testray builds associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayBuilds(long pk) {
		return getPersistence().containsTestrayBuilds(pk);
	}

	/**
	 * Adds an association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPK the primary key of the testray build
	 */
	public static void addTestrayBuild(long pk, long testrayBuildPK) {
		getPersistence().addTestrayBuild(pk, testrayBuildPK);
	}

	/**
	 * Adds an association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuild the testray build
	 */
	public static void addTestrayBuild(
		long pk, com.liferay.osb.testray.model.TestrayBuild testrayBuild) {

		getPersistence().addTestrayBuild(pk, testrayBuild);
	}

	/**
	 * Adds an association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPKs the primary keys of the testray builds
	 */
	public static void addTestrayBuilds(long pk, long[] testrayBuildPKs) {
		getPersistence().addTestrayBuilds(pk, testrayBuildPKs);
	}

	/**
	 * Adds an association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuilds the testray builds
	 */
	public static void addTestrayBuilds(
		long pk,
		List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {

		getPersistence().addTestrayBuilds(pk, testrayBuilds);
	}

	/**
	 * Clears all associations between the testray case and its testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray builds from
	 */
	public static void clearTestrayBuilds(long pk) {
		getPersistence().clearTestrayBuilds(pk);
	}

	/**
	 * Removes the association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPK the primary key of the testray build
	 */
	public static void removeTestrayBuild(long pk, long testrayBuildPK) {
		getPersistence().removeTestrayBuild(pk, testrayBuildPK);
	}

	/**
	 * Removes the association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuild the testray build
	 */
	public static void removeTestrayBuild(
		long pk, com.liferay.osb.testray.model.TestrayBuild testrayBuild) {

		getPersistence().removeTestrayBuild(pk, testrayBuild);
	}

	/**
	 * Removes the association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPKs the primary keys of the testray builds
	 */
	public static void removeTestrayBuilds(long pk, long[] testrayBuildPKs) {
		getPersistence().removeTestrayBuilds(pk, testrayBuildPKs);
	}

	/**
	 * Removes the association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuilds the testray builds
	 */
	public static void removeTestrayBuilds(
		long pk,
		List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {

		getPersistence().removeTestrayBuilds(pk, testrayBuilds);
	}

	/**
	 * Sets the testray builds associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPKs the primary keys of the testray builds to be associated with the testray case
	 */
	public static void setTestrayBuilds(long pk, long[] testrayBuildPKs) {
		getPersistence().setTestrayBuilds(pk, testrayBuildPKs);
	}

	/**
	 * Sets the testray builds associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuilds the testray builds to be associated with the testray case
	 */
	public static void setTestrayBuilds(
		long pk,
		List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {

		getPersistence().setTestrayBuilds(pk, testrayBuilds);
	}

	/**
	 * Returns the primaryKeys of testray components associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray components associated with the testray case
	 */
	public static long[] getTestrayComponentPrimaryKeys(long pk) {
		return getPersistence().getTestrayComponentPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray components associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray components associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayComponent>
		getTestrayComponents(long pk) {

		return getPersistence().getTestrayComponents(pk);
	}

	/**
	 * Returns a range of all the testray components associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray components associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayComponent>
		getTestrayComponents(long pk, int start, int end) {

		return getPersistence().getTestrayComponents(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray components associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray components associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayComponent>
		getTestrayComponents(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayComponent>
				orderByComparator) {

		return getPersistence().getTestrayComponents(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray components associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray components associated with the testray case
	 */
	public static int getTestrayComponentsSize(long pk) {
		return getPersistence().getTestrayComponentsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray component is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPK the primary key of the testray component
	 * @return <code>true</code> if the testray component is associated with the testray case; <code>false</code> otherwise
	 */
	public static boolean containsTestrayComponent(
		long pk, long testrayComponentPK) {

		return getPersistence().containsTestrayComponent(
			pk, testrayComponentPK);
	}

	/**
	 * Returns <code>true</code> if the testray case has any testray components associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray components
	 * @return <code>true</code> if the testray case has any testray components associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayComponents(long pk) {
		return getPersistence().containsTestrayComponents(pk);
	}

	/**
	 * Adds an association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPK the primary key of the testray component
	 */
	public static void addTestrayComponent(long pk, long testrayComponentPK) {
		getPersistence().addTestrayComponent(pk, testrayComponentPK);
	}

	/**
	 * Adds an association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponent the testray component
	 */
	public static void addTestrayComponent(
		long pk,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {

		getPersistence().addTestrayComponent(pk, testrayComponent);
	}

	/**
	 * Adds an association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPKs the primary keys of the testray components
	 */
	public static void addTestrayComponents(
		long pk, long[] testrayComponentPKs) {

		getPersistence().addTestrayComponents(pk, testrayComponentPKs);
	}

	/**
	 * Adds an association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponents the testray components
	 */
	public static void addTestrayComponents(
		long pk,
		List<com.liferay.osb.testray.model.TestrayComponent>
			testrayComponents) {

		getPersistence().addTestrayComponents(pk, testrayComponents);
	}

	/**
	 * Clears all associations between the testray case and its testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray components from
	 */
	public static void clearTestrayComponents(long pk) {
		getPersistence().clearTestrayComponents(pk);
	}

	/**
	 * Removes the association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPK the primary key of the testray component
	 */
	public static void removeTestrayComponent(
		long pk, long testrayComponentPK) {

		getPersistence().removeTestrayComponent(pk, testrayComponentPK);
	}

	/**
	 * Removes the association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponent the testray component
	 */
	public static void removeTestrayComponent(
		long pk,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {

		getPersistence().removeTestrayComponent(pk, testrayComponent);
	}

	/**
	 * Removes the association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPKs the primary keys of the testray components
	 */
	public static void removeTestrayComponents(
		long pk, long[] testrayComponentPKs) {

		getPersistence().removeTestrayComponents(pk, testrayComponentPKs);
	}

	/**
	 * Removes the association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponents the testray components
	 */
	public static void removeTestrayComponents(
		long pk,
		List<com.liferay.osb.testray.model.TestrayComponent>
			testrayComponents) {

		getPersistence().removeTestrayComponents(pk, testrayComponents);
	}

	/**
	 * Sets the testray components associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPKs the primary keys of the testray components to be associated with the testray case
	 */
	public static void setTestrayComponents(
		long pk, long[] testrayComponentPKs) {

		getPersistence().setTestrayComponents(pk, testrayComponentPKs);
	}

	/**
	 * Sets the testray components associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponents the testray components to be associated with the testray case
	 */
	public static void setTestrayComponents(
		long pk,
		List<com.liferay.osb.testray.model.TestrayComponent>
			testrayComponents) {

		getPersistence().setTestrayComponents(pk, testrayComponents);
	}

	/**
	 * Returns the primaryKeys of testray requirements associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray requirements associated with the testray case
	 */
	public static long[] getTestrayRequirementPrimaryKeys(long pk) {
		return getPersistence().getTestrayRequirementPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray requirements associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray requirements associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(long pk) {

		return getPersistence().getTestrayRequirements(pk);
	}

	/**
	 * Returns a range of all the testray requirements associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray requirements associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(long pk, int start, int end) {

		return getPersistence().getTestrayRequirements(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray requirements associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray requirements associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayRequirement>
				orderByComparator) {

		return getPersistence().getTestrayRequirements(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray requirements associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray requirements associated with the testray case
	 */
	public static int getTestrayRequirementsSize(long pk) {
		return getPersistence().getTestrayRequirementsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray requirement is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPK the primary key of the testray requirement
	 * @return <code>true</code> if the testray requirement is associated with the testray case; <code>false</code> otherwise
	 */
	public static boolean containsTestrayRequirement(
		long pk, long testrayRequirementPK) {

		return getPersistence().containsTestrayRequirement(
			pk, testrayRequirementPK);
	}

	/**
	 * Returns <code>true</code> if the testray case has any testray requirements associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray requirements
	 * @return <code>true</code> if the testray case has any testray requirements associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayRequirements(long pk) {
		return getPersistence().containsTestrayRequirements(pk);
	}

	/**
	 * Adds an association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPK the primary key of the testray requirement
	 */
	public static void addTestrayRequirement(
		long pk, long testrayRequirementPK) {

		getPersistence().addTestrayRequirement(pk, testrayRequirementPK);
	}

	/**
	 * Adds an association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirement the testray requirement
	 */
	public static void addTestrayRequirement(
		long pk,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement) {

		getPersistence().addTestrayRequirement(pk, testrayRequirement);
	}

	/**
	 * Adds an association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPKs the primary keys of the testray requirements
	 */
	public static void addTestrayRequirements(
		long pk, long[] testrayRequirementPKs) {

		getPersistence().addTestrayRequirements(pk, testrayRequirementPKs);
	}

	/**
	 * Adds an association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirements the testray requirements
	 */
	public static void addTestrayRequirements(
		long pk,
		List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		getPersistence().addTestrayRequirements(pk, testrayRequirements);
	}

	/**
	 * Clears all associations between the testray case and its testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray requirements from
	 */
	public static void clearTestrayRequirements(long pk) {
		getPersistence().clearTestrayRequirements(pk);
	}

	/**
	 * Removes the association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPK the primary key of the testray requirement
	 */
	public static void removeTestrayRequirement(
		long pk, long testrayRequirementPK) {

		getPersistence().removeTestrayRequirement(pk, testrayRequirementPK);
	}

	/**
	 * Removes the association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirement the testray requirement
	 */
	public static void removeTestrayRequirement(
		long pk,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement) {

		getPersistence().removeTestrayRequirement(pk, testrayRequirement);
	}

	/**
	 * Removes the association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPKs the primary keys of the testray requirements
	 */
	public static void removeTestrayRequirements(
		long pk, long[] testrayRequirementPKs) {

		getPersistence().removeTestrayRequirements(pk, testrayRequirementPKs);
	}

	/**
	 * Removes the association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirements the testray requirements
	 */
	public static void removeTestrayRequirements(
		long pk,
		List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		getPersistence().removeTestrayRequirements(pk, testrayRequirements);
	}

	/**
	 * Sets the testray requirements associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPKs the primary keys of the testray requirements to be associated with the testray case
	 */
	public static void setTestrayRequirements(
		long pk, long[] testrayRequirementPKs) {

		getPersistence().setTestrayRequirements(pk, testrayRequirementPKs);
	}

	/**
	 * Sets the testray requirements associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirements the testray requirements to be associated with the testray case
	 */
	public static void setTestrayRequirements(
		long pk,
		List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		getPersistence().setTestrayRequirements(pk, testrayRequirements);
	}

	/**
	 * Returns the primaryKeys of testray suites associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray suites associated with the testray case
	 */
	public static long[] getTestraySuitePrimaryKeys(long pk) {
		return getPersistence().getTestraySuitePrimaryKeys(pk);
	}

	/**
	 * Returns all the testray suites associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray suites associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestraySuite>
		getTestraySuites(long pk) {

		return getPersistence().getTestraySuites(pk);
	}

	/**
	 * Returns a range of all the testray suites associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray suites associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestraySuite>
		getTestraySuites(long pk, int start, int end) {

		return getPersistence().getTestraySuites(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray suites associated with the testray case.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray suites associated with the testray case
	 */
	public static List<com.liferay.osb.testray.model.TestraySuite>
		getTestraySuites(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestraySuite>
				orderByComparator) {

		return getPersistence().getTestraySuites(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray suites associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray suites associated with the testray case
	 */
	public static int getTestraySuitesSize(long pk) {
		return getPersistence().getTestraySuitesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray suite is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePK the primary key of the testray suite
	 * @return <code>true</code> if the testray suite is associated with the testray case; <code>false</code> otherwise
	 */
	public static boolean containsTestraySuite(long pk, long testraySuitePK) {
		return getPersistence().containsTestraySuite(pk, testraySuitePK);
	}

	/**
	 * Returns <code>true</code> if the testray case has any testray suites associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray suites
	 * @return <code>true</code> if the testray case has any testray suites associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestraySuites(long pk) {
		return getPersistence().containsTestraySuites(pk);
	}

	/**
	 * Adds an association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePK the primary key of the testray suite
	 */
	public static void addTestraySuite(long pk, long testraySuitePK) {
		getPersistence().addTestraySuite(pk, testraySuitePK);
	}

	/**
	 * Adds an association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuite the testray suite
	 */
	public static void addTestraySuite(
		long pk, com.liferay.osb.testray.model.TestraySuite testraySuite) {

		getPersistence().addTestraySuite(pk, testraySuite);
	}

	/**
	 * Adds an association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePKs the primary keys of the testray suites
	 */
	public static void addTestraySuites(long pk, long[] testraySuitePKs) {
		getPersistence().addTestraySuites(pk, testraySuitePKs);
	}

	/**
	 * Adds an association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuites the testray suites
	 */
	public static void addTestraySuites(
		long pk,
		List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {

		getPersistence().addTestraySuites(pk, testraySuites);
	}

	/**
	 * Clears all associations between the testray case and its testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray suites from
	 */
	public static void clearTestraySuites(long pk) {
		getPersistence().clearTestraySuites(pk);
	}

	/**
	 * Removes the association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePK the primary key of the testray suite
	 */
	public static void removeTestraySuite(long pk, long testraySuitePK) {
		getPersistence().removeTestraySuite(pk, testraySuitePK);
	}

	/**
	 * Removes the association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuite the testray suite
	 */
	public static void removeTestraySuite(
		long pk, com.liferay.osb.testray.model.TestraySuite testraySuite) {

		getPersistence().removeTestraySuite(pk, testraySuite);
	}

	/**
	 * Removes the association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePKs the primary keys of the testray suites
	 */
	public static void removeTestraySuites(long pk, long[] testraySuitePKs) {
		getPersistence().removeTestraySuites(pk, testraySuitePKs);
	}

	/**
	 * Removes the association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuites the testray suites
	 */
	public static void removeTestraySuites(
		long pk,
		List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {

		getPersistence().removeTestraySuites(pk, testraySuites);
	}

	/**
	 * Sets the testray suites associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePKs the primary keys of the testray suites to be associated with the testray case
	 */
	public static void setTestraySuites(long pk, long[] testraySuitePKs) {
		getPersistence().setTestraySuites(pk, testraySuitePKs);
	}

	/**
	 * Sets the testray suites associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuites the testray suites to be associated with the testray case
	 */
	public static void setTestraySuites(
		long pk,
		List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {

		getPersistence().setTestraySuites(pk, testraySuites);
	}

	public static TestrayCasePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayCasePersistence, TestrayCasePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayCasePersistence.class);

		ServiceTracker<TestrayCasePersistence, TestrayCasePersistence>
			serviceTracker =
				new ServiceTracker
					<TestrayCasePersistence, TestrayCasePersistence>(
						bundle.getBundleContext(), TestrayCasePersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}