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

import com.liferay.osb.testray.exception.NoSuchTestrayProjectException;
import com.liferay.osb.testray.model.TestrayProject;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayProjectPersistenceImpl
 * @see TestrayProjectUtil
 * @generated
 */
@ProviderType
public interface TestrayProjectPersistence extends BasePersistence<TestrayProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayProjectUtil} to access the testray project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the testray project where groupId = &#63; and name = &#63; or throws a {@link NoSuchTestrayProjectException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching testray project
	* @throws NoSuchTestrayProjectException if a matching testray project could not be found
	*/
	public TestrayProject findByGI_N(long groupId, java.lang.String name)
		throws NoSuchTestrayProjectException;

	/**
	* Returns the testray project where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching testray project, or <code>null</code> if a matching testray project could not be found
	*/
	public TestrayProject fetchByGI_N(long groupId, java.lang.String name);

	/**
	* Returns the testray project where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray project, or <code>null</code> if a matching testray project could not be found
	*/
	public TestrayProject fetchByGI_N(long groupId, java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the testray project where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the testray project that was removed
	*/
	public TestrayProject removeByGI_N(long groupId, java.lang.String name)
		throws NoSuchTestrayProjectException;

	/**
	* Returns the number of testray projects where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching testray projects
	*/
	public int countByGI_N(long groupId, java.lang.String name);

	/**
	* Caches the testray project in the entity cache if it is enabled.
	*
	* @param testrayProject the testray project
	*/
	public void cacheResult(TestrayProject testrayProject);

	/**
	* Caches the testray projects in the entity cache if it is enabled.
	*
	* @param testrayProjects the testray projects
	*/
	public void cacheResult(java.util.List<TestrayProject> testrayProjects);

	/**
	* Creates a new testray project with the primary key. Does not add the testray project to the database.
	*
	* @param testrayProjectId the primary key for the new testray project
	* @return the new testray project
	*/
	public TestrayProject create(long testrayProjectId);

	/**
	* Removes the testray project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayProjectId the primary key of the testray project
	* @return the testray project that was removed
	* @throws NoSuchTestrayProjectException if a testray project with the primary key could not be found
	*/
	public TestrayProject remove(long testrayProjectId)
		throws NoSuchTestrayProjectException;

	public TestrayProject updateImpl(TestrayProject testrayProject);

	/**
	* Returns the testray project with the primary key or throws a {@link NoSuchTestrayProjectException} if it could not be found.
	*
	* @param testrayProjectId the primary key of the testray project
	* @return the testray project
	* @throws NoSuchTestrayProjectException if a testray project with the primary key could not be found
	*/
	public TestrayProject findByPrimaryKey(long testrayProjectId)
		throws NoSuchTestrayProjectException;

	/**
	* Returns the testray project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayProjectId the primary key of the testray project
	* @return the testray project, or <code>null</code> if a testray project with the primary key could not be found
	*/
	public TestrayProject fetchByPrimaryKey(long testrayProjectId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray projects.
	*
	* @return the testray projects
	*/
	public java.util.List<TestrayProject> findAll();

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
	public java.util.List<TestrayProject> findAll(int start, int end);

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
	public java.util.List<TestrayProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayProject> orderByComparator);

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
	public java.util.List<TestrayProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray projects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray projects.
	*
	* @return the number of testray projects
	*/
	public int countAll();
}