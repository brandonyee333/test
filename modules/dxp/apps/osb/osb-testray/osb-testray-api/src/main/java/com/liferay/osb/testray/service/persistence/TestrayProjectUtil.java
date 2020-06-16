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

import com.liferay.osb.testray.model.TestrayProject;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray project service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayProjectPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayProjectUtil {
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
	public static void clearCache(TestrayProject testrayProject) {
		getPersistence().clearCache(testrayProject);
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
	public static List<TestrayProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayProject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayProject update(TestrayProject testrayProject) {
		return getPersistence().update(testrayProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayProject update(TestrayProject testrayProject,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayProject, serviceContext);
	}

	/**
	* Returns the testray project where groupId = &#63; and name = &#63; or throws a {@link NoSuchTestrayProjectException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching testray project
	* @throws NoSuchTestrayProjectException if a matching testray project could not be found
	*/
	public static TestrayProject findByGI_N(long groupId, java.lang.String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayProjectException {
		return getPersistence().findByGI_N(groupId, name);
	}

	/**
	* Returns the testray project where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching testray project, or <code>null</code> if a matching testray project could not be found
	*/
	public static TestrayProject fetchByGI_N(long groupId, java.lang.String name) {
		return getPersistence().fetchByGI_N(groupId, name);
	}

	/**
	* Returns the testray project where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray project, or <code>null</code> if a matching testray project could not be found
	*/
	public static TestrayProject fetchByGI_N(long groupId,
		java.lang.String name, boolean retrieveFromCache) {
		return getPersistence().fetchByGI_N(groupId, name, retrieveFromCache);
	}

	/**
	* Removes the testray project where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the testray project that was removed
	*/
	public static TestrayProject removeByGI_N(long groupId,
		java.lang.String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayProjectException {
		return getPersistence().removeByGI_N(groupId, name);
	}

	/**
	* Returns the number of testray projects where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching testray projects
	*/
	public static int countByGI_N(long groupId, java.lang.String name) {
		return getPersistence().countByGI_N(groupId, name);
	}

	/**
	* Caches the testray project in the entity cache if it is enabled.
	*
	* @param testrayProject the testray project
	*/
	public static void cacheResult(TestrayProject testrayProject) {
		getPersistence().cacheResult(testrayProject);
	}

	/**
	* Caches the testray projects in the entity cache if it is enabled.
	*
	* @param testrayProjects the testray projects
	*/
	public static void cacheResult(List<TestrayProject> testrayProjects) {
		getPersistence().cacheResult(testrayProjects);
	}

	/**
	* Creates a new testray project with the primary key. Does not add the testray project to the database.
	*
	* @param testrayProjectId the primary key for the new testray project
	* @return the new testray project
	*/
	public static TestrayProject create(long testrayProjectId) {
		return getPersistence().create(testrayProjectId);
	}

	/**
	* Removes the testray project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayProjectId the primary key of the testray project
	* @return the testray project that was removed
	* @throws NoSuchTestrayProjectException if a testray project with the primary key could not be found
	*/
	public static TestrayProject remove(long testrayProjectId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayProjectException {
		return getPersistence().remove(testrayProjectId);
	}

	public static TestrayProject updateImpl(TestrayProject testrayProject) {
		return getPersistence().updateImpl(testrayProject);
	}

	/**
	* Returns the testray project with the primary key or throws a {@link NoSuchTestrayProjectException} if it could not be found.
	*
	* @param testrayProjectId the primary key of the testray project
	* @return the testray project
	* @throws NoSuchTestrayProjectException if a testray project with the primary key could not be found
	*/
	public static TestrayProject findByPrimaryKey(long testrayProjectId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayProjectException {
		return getPersistence().findByPrimaryKey(testrayProjectId);
	}

	/**
	* Returns the testray project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayProjectId the primary key of the testray project
	* @return the testray project, or <code>null</code> if a testray project with the primary key could not be found
	*/
	public static TestrayProject fetchByPrimaryKey(long testrayProjectId) {
		return getPersistence().fetchByPrimaryKey(testrayProjectId);
	}

	public static java.util.Map<java.io.Serializable, TestrayProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray projects.
	*
	* @return the testray projects
	*/
	public static List<TestrayProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray projects
	* @param end the upper bound of the range of testray projects (not inclusive)
	* @return the range of testray projects
	*/
	public static List<TestrayProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray projects
	* @param end the upper bound of the range of testray projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray projects
	*/
	public static List<TestrayProject> findAll(int start, int end,
		OrderByComparator<TestrayProject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray projects
	* @param end the upper bound of the range of testray projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray projects
	*/
	public static List<TestrayProject> findAll(int start, int end,
		OrderByComparator<TestrayProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray projects.
	*
	* @return the number of testray projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayProjectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayProjectPersistence, TestrayProjectPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayProjectPersistence.class);
}