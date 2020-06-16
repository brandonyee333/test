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

import com.liferay.osb.testray.model.TestrayTeam;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray team service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayTeamPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayTeamPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayTeamPersistenceImpl
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static TestrayTeam update(TestrayTeam testrayTeam,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayTeam, serviceContext);
	}

	/**
	* Returns the testray team where testrayProjectId = &#63; and name = &#63; or throws a {@link NoSuchTestrayTeamException} if it could not be found.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the matching testray team
	* @throws NoSuchTestrayTeamException if a matching testray team could not be found
	*/
	public static TestrayTeam findByTPI_N(long testrayProjectId,
		java.lang.String name)
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
	public static TestrayTeam fetchByTPI_N(long testrayProjectId,
		java.lang.String name) {
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
	public static TestrayTeam fetchByTPI_N(long testrayProjectId,
		java.lang.String name, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTPI_N(testrayProjectId, name, retrieveFromCache);
	}

	/**
	* Removes the testray team where testrayProjectId = &#63; and name = &#63; from the database.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the testray team that was removed
	*/
	public static TestrayTeam removeByTPI_N(long testrayProjectId,
		java.lang.String name)
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
	public static int countByTPI_N(long testrayProjectId, java.lang.String name) {
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
	* Returns the testray team with the primary key or throws a {@link NoSuchTestrayTeamException} if it could not be found.
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

	public static java.util.Map<java.io.Serializable, TestrayTeam> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray teams
	* @param end the upper bound of the range of testray teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray teams
	*/
	public static List<TestrayTeam> findAll(int start, int end,
		OrderByComparator<TestrayTeam> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray teams
	* @param end the upper bound of the range of testray teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray teams
	*/
	public static List<TestrayTeam> findAll(int start, int end,
		OrderByComparator<TestrayTeam> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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

	private static ServiceTracker<TestrayTeamPersistence, TestrayTeamPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayTeamPersistence.class);
}