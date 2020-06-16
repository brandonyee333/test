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

import com.liferay.osb.testray.exception.NoSuchTestrayTeamException;
import com.liferay.osb.testray.model.TestrayTeam;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayTeamPersistenceImpl
 * @see TestrayTeamUtil
 * @generated
 */
@ProviderType
public interface TestrayTeamPersistence extends BasePersistence<TestrayTeam> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayTeamUtil} to access the testray team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the testray team where testrayProjectId = &#63; and name = &#63; or throws a {@link NoSuchTestrayTeamException} if it could not be found.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the matching testray team
	* @throws NoSuchTestrayTeamException if a matching testray team could not be found
	*/
	public TestrayTeam findByTPI_N(long testrayProjectId, java.lang.String name)
		throws NoSuchTestrayTeamException;

	/**
	* Returns the testray team where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the matching testray team, or <code>null</code> if a matching testray team could not be found
	*/
	public TestrayTeam fetchByTPI_N(long testrayProjectId, java.lang.String name);

	/**
	* Returns the testray team where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray team, or <code>null</code> if a matching testray team could not be found
	*/
	public TestrayTeam fetchByTPI_N(long testrayProjectId,
		java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the testray team where testrayProjectId = &#63; and name = &#63; from the database.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the testray team that was removed
	*/
	public TestrayTeam removeByTPI_N(long testrayProjectId,
		java.lang.String name) throws NoSuchTestrayTeamException;

	/**
	* Returns the number of testray teams where testrayProjectId = &#63; and name = &#63;.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the number of matching testray teams
	*/
	public int countByTPI_N(long testrayProjectId, java.lang.String name);

	/**
	* Caches the testray team in the entity cache if it is enabled.
	*
	* @param testrayTeam the testray team
	*/
	public void cacheResult(TestrayTeam testrayTeam);

	/**
	* Caches the testray teams in the entity cache if it is enabled.
	*
	* @param testrayTeams the testray teams
	*/
	public void cacheResult(java.util.List<TestrayTeam> testrayTeams);

	/**
	* Creates a new testray team with the primary key. Does not add the testray team to the database.
	*
	* @param testrayTeamId the primary key for the new testray team
	* @return the new testray team
	*/
	public TestrayTeam create(long testrayTeamId);

	/**
	* Removes the testray team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayTeamId the primary key of the testray team
	* @return the testray team that was removed
	* @throws NoSuchTestrayTeamException if a testray team with the primary key could not be found
	*/
	public TestrayTeam remove(long testrayTeamId)
		throws NoSuchTestrayTeamException;

	public TestrayTeam updateImpl(TestrayTeam testrayTeam);

	/**
	* Returns the testray team with the primary key or throws a {@link NoSuchTestrayTeamException} if it could not be found.
	*
	* @param testrayTeamId the primary key of the testray team
	* @return the testray team
	* @throws NoSuchTestrayTeamException if a testray team with the primary key could not be found
	*/
	public TestrayTeam findByPrimaryKey(long testrayTeamId)
		throws NoSuchTestrayTeamException;

	/**
	* Returns the testray team with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayTeamId the primary key of the testray team
	* @return the testray team, or <code>null</code> if a testray team with the primary key could not be found
	*/
	public TestrayTeam fetchByPrimaryKey(long testrayTeamId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayTeam> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray teams.
	*
	* @return the testray teams
	*/
	public java.util.List<TestrayTeam> findAll();

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
	public java.util.List<TestrayTeam> findAll(int start, int end);

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
	public java.util.List<TestrayTeam> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayTeam> orderByComparator);

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
	public java.util.List<TestrayTeam> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayTeam> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray teams from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray teams.
	*
	* @return the number of testray teams
	*/
	public int countAll();
}