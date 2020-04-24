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

import com.liferay.osb.testray.exception.NoSuchTestrayTeamException;
import com.liferay.osb.testray.model.TestrayTeam;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
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
	@Override
	public Map<Serializable, TestrayTeam> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray team where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayTeamException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray team
	 * @throws NoSuchTestrayTeamException if a matching testray team could not be found
	 */
	public TestrayTeam findByTPI_N(long testrayProjectId, String name)
		throws NoSuchTestrayTeamException;

	/**
	 * Returns the testray team where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray team, or <code>null</code> if a matching testray team could not be found
	 */
	public TestrayTeam fetchByTPI_N(long testrayProjectId, String name);

	/**
	 * Returns the testray team where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray team, or <code>null</code> if a matching testray team could not be found
	 */
	public TestrayTeam fetchByTPI_N(
		long testrayProjectId, String name, boolean retrieveFromCache);

	/**
	 * Removes the testray team where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray team that was removed
	 */
	public TestrayTeam removeByTPI_N(long testrayProjectId, String name)
		throws NoSuchTestrayTeamException;

	/**
	 * Returns the number of testray teams where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray teams
	 */
	public int countByTPI_N(long testrayProjectId, String name);

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
	 * Returns the testray team with the primary key or throws a <code>NoSuchTestrayTeamException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray teams
	 * @param end the upper bound of the range of testray teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray teams
	 */
	public java.util.List<TestrayTeam> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayTeam>
			orderByComparator);

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
	public java.util.List<TestrayTeam> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayTeam>
			orderByComparator,
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