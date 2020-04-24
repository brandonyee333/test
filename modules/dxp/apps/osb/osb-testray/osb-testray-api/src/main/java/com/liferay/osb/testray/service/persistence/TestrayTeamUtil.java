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

import com.liferay.osb.testray.model.TestrayTeam;
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
 * The persistence utility for the testray team service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayTeamPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayTeamPersistence
 * @generated
 */
@ProviderType
public class TestrayTeamUtil {

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
	public static void clearCache(TestrayTeam testrayTeam) {
		getPersistence().clearCache(testrayTeam);
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
	public static Map<Serializable, TestrayTeam> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayTeam> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayTeam> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayTeam> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayTeam> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayTeam update(TestrayTeam testrayTeam) {
		return getPersistence().update(testrayTeam);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayTeam update(
		TestrayTeam testrayTeam, ServiceContext serviceContext) {

		return getPersistence().update(testrayTeam, serviceContext);
	}

	/**
	 * Returns the testray team where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayTeamException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray team
	 * @throws NoSuchTestrayTeamException if a matching testray team could not be found
	 */
	public static TestrayTeam findByTPI_N(long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayTeamException {

		return getPersistence().findByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray team where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray team, or <code>null</code> if a matching testray team could not be found
	 */
	public static TestrayTeam fetchByTPI_N(long testrayProjectId, String name) {
		return getPersistence().fetchByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray team where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray team, or <code>null</code> if a matching testray team could not be found
	 */
	public static TestrayTeam fetchByTPI_N(
		long testrayProjectId, String name, boolean retrieveFromCache) {

		return getPersistence().fetchByTPI_N(
			testrayProjectId, name, retrieveFromCache);
	}

	/**
	 * Removes the testray team where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray team that was removed
	 */
	public static TestrayTeam removeByTPI_N(long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayTeamException {

		return getPersistence().removeByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the number of testray teams where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray teams
	 */
	public static int countByTPI_N(long testrayProjectId, String name) {
		return getPersistence().countByTPI_N(testrayProjectId, name);
	}

	/**
	 * Caches the testray team in the entity cache if it is enabled.
	 *
	 * @param testrayTeam the testray team
	 */
	public static void cacheResult(TestrayTeam testrayTeam) {
		getPersistence().cacheResult(testrayTeam);
	}

	/**
	 * Caches the testray teams in the entity cache if it is enabled.
	 *
	 * @param testrayTeams the testray teams
	 */
	public static void cacheResult(List<TestrayTeam> testrayTeams) {
		getPersistence().cacheResult(testrayTeams);
	}

	/**
	 * Creates a new testray team with the primary key. Does not add the testray team to the database.
	 *
	 * @param testrayTeamId the primary key for the new testray team
	 * @return the new testray team
	 */
	public static TestrayTeam create(long testrayTeamId) {
		return getPersistence().create(testrayTeamId);
	}

	/**
	 * Removes the testray team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTeamId the primary key of the testray team
	 * @return the testray team that was removed
	 * @throws NoSuchTestrayTeamException if a testray team with the primary key could not be found
	 */
	public static TestrayTeam remove(long testrayTeamId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayTeamException {

		return getPersistence().remove(testrayTeamId);
	}

	public static TestrayTeam updateImpl(TestrayTeam testrayTeam) {
		return getPersistence().updateImpl(testrayTeam);
	}

	/**
	 * Returns the testray team with the primary key or throws a <code>NoSuchTestrayTeamException</code> if it could not be found.
	 *
	 * @param testrayTeamId the primary key of the testray team
	 * @return the testray team
	 * @throws NoSuchTestrayTeamException if a testray team with the primary key could not be found
	 */
	public static TestrayTeam findByPrimaryKey(long testrayTeamId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayTeamException {

		return getPersistence().findByPrimaryKey(testrayTeamId);
	}

	/**
	 * Returns the testray team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayTeamId the primary key of the testray team
	 * @return the testray team, or <code>null</code> if a testray team with the primary key could not be found
	 */
	public static TestrayTeam fetchByPrimaryKey(long testrayTeamId) {
		return getPersistence().fetchByPrimaryKey(testrayTeamId);
	}

	/**
	 * Returns all the testray teams.
	 *
	 * @return the testray teams
	 */
	public static List<TestrayTeam> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray teams
	 * @param end the upper bound of the range of testray teams (not inclusive)
	 * @return the range of testray teams
	 */
	public static List<TestrayTeam> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray teams
	 * @param end the upper bound of the range of testray teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray teams
	 */
	public static List<TestrayTeam> findAll(
		int start, int end, OrderByComparator<TestrayTeam> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray teams
	 * @param end the upper bound of the range of testray teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray teams
	 */
	public static List<TestrayTeam> findAll(
		int start, int end, OrderByComparator<TestrayTeam> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the testray teams from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray teams.
	 *
	 * @return the number of testray teams
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayTeamPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayTeamPersistence, TestrayTeamPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayTeamPersistence.class);

		ServiceTracker<TestrayTeamPersistence, TestrayTeamPersistence>
			serviceTracker =
				new ServiceTracker
					<TestrayTeamPersistence, TestrayTeamPersistence>(
						bundle.getBundleContext(), TestrayTeamPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}