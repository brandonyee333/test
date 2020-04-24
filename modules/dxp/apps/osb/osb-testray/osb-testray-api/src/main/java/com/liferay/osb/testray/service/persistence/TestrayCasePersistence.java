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

import com.liferay.osb.testray.exception.NoSuchTestrayCaseException;
import com.liferay.osb.testray.model.TestrayCase;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray case service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseUtil
 * @generated
 */
@ProviderType
public interface TestrayCasePersistence extends BasePersistence<TestrayCase> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayCaseUtil} to access the testray case persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayCase> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray case where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayCaseException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray case
	 * @throws NoSuchTestrayCaseException if a matching testray case could not be found
	 */
	public TestrayCase findByTPI_N(long testrayProjectId, String name)
		throws NoSuchTestrayCaseException;

	/**
	 * Returns the testray case where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray case, or <code>null</code> if a matching testray case could not be found
	 */
	public TestrayCase fetchByTPI_N(long testrayProjectId, String name);

	/**
	 * Returns the testray case where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case, or <code>null</code> if a matching testray case could not be found
	 */
	public TestrayCase fetchByTPI_N(
		long testrayProjectId, String name, boolean retrieveFromCache);

	/**
	 * Removes the testray case where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray case that was removed
	 */
	public TestrayCase removeByTPI_N(long testrayProjectId, String name)
		throws NoSuchTestrayCaseException;

	/**
	 * Returns the number of testray cases where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray cases
	 */
	public int countByTPI_N(long testrayProjectId, String name);

	/**
	 * Caches the testray case in the entity cache if it is enabled.
	 *
	 * @param testrayCase the testray case
	 */
	public void cacheResult(TestrayCase testrayCase);

	/**
	 * Caches the testray cases in the entity cache if it is enabled.
	 *
	 * @param testrayCases the testray cases
	 */
	public void cacheResult(java.util.List<TestrayCase> testrayCases);

	/**
	 * Creates a new testray case with the primary key. Does not add the testray case to the database.
	 *
	 * @param testrayCaseId the primary key for the new testray case
	 * @return the new testray case
	 */
	public TestrayCase create(long testrayCaseId);

	/**
	 * Removes the testray case with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case that was removed
	 * @throws NoSuchTestrayCaseException if a testray case with the primary key could not be found
	 */
	public TestrayCase remove(long testrayCaseId)
		throws NoSuchTestrayCaseException;

	public TestrayCase updateImpl(TestrayCase testrayCase);

	/**
	 * Returns the testray case with the primary key or throws a <code>NoSuchTestrayCaseException</code> if it could not be found.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case
	 * @throws NoSuchTestrayCaseException if a testray case with the primary key could not be found
	 */
	public TestrayCase findByPrimaryKey(long testrayCaseId)
		throws NoSuchTestrayCaseException;

	/**
	 * Returns the testray case with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case, or <code>null</code> if a testray case with the primary key could not be found
	 */
	public TestrayCase fetchByPrimaryKey(long testrayCaseId);

	/**
	 * Returns all the testray cases.
	 *
	 * @return the testray cases
	 */
	public java.util.List<TestrayCase> findAll();

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
	public java.util.List<TestrayCase> findAll(int start, int end);

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
	public java.util.List<TestrayCase> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCase>
			orderByComparator);

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
	public java.util.List<TestrayCase> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCase>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray cases from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray cases.
	 *
	 * @return the number of testray cases
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray builds associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray builds associated with the testray case
	 */
	public long[] getTestrayBuildPrimaryKeys(long pk);

	/**
	 * Returns all the testray builds associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray builds associated with the testray case
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild>
		getTestrayBuilds(long pk);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild>
		getTestrayBuilds(long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild>
		getTestrayBuilds(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayBuild> orderByComparator);

	/**
	 * Returns the number of testray builds associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray builds associated with the testray case
	 */
	public int getTestrayBuildsSize(long pk);

	/**
	 * Returns <code>true</code> if the testray build is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPK the primary key of the testray build
	 * @return <code>true</code> if the testray build is associated with the testray case; <code>false</code> otherwise
	 */
	public boolean containsTestrayBuild(long pk, long testrayBuildPK);

	/**
	 * Returns <code>true</code> if the testray case has any testray builds associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray builds
	 * @return <code>true</code> if the testray case has any testray builds associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayBuilds(long pk);

	/**
	 * Adds an association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPK the primary key of the testray build
	 */
	public void addTestrayBuild(long pk, long testrayBuildPK);

	/**
	 * Adds an association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuild the testray build
	 */
	public void addTestrayBuild(
		long pk, com.liferay.osb.testray.model.TestrayBuild testrayBuild);

	/**
	 * Adds an association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPKs the primary keys of the testray builds
	 */
	public void addTestrayBuilds(long pk, long[] testrayBuildPKs);

	/**
	 * Adds an association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuilds the testray builds
	 */
	public void addTestrayBuilds(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild>
			testrayBuilds);

	/**
	 * Clears all associations between the testray case and its testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray builds from
	 */
	public void clearTestrayBuilds(long pk);

	/**
	 * Removes the association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPK the primary key of the testray build
	 */
	public void removeTestrayBuild(long pk, long testrayBuildPK);

	/**
	 * Removes the association between the testray case and the testray build. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuild the testray build
	 */
	public void removeTestrayBuild(
		long pk, com.liferay.osb.testray.model.TestrayBuild testrayBuild);

	/**
	 * Removes the association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPKs the primary keys of the testray builds
	 */
	public void removeTestrayBuilds(long pk, long[] testrayBuildPKs);

	/**
	 * Removes the association between the testray case and the testray builds. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuilds the testray builds
	 */
	public void removeTestrayBuilds(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild>
			testrayBuilds);

	/**
	 * Sets the testray builds associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuildPKs the primary keys of the testray builds to be associated with the testray case
	 */
	public void setTestrayBuilds(long pk, long[] testrayBuildPKs);

	/**
	 * Sets the testray builds associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayBuilds the testray builds to be associated with the testray case
	 */
	public void setTestrayBuilds(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild>
			testrayBuilds);

	/**
	 * Returns the primaryKeys of testray components associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray components associated with the testray case
	 */
	public long[] getTestrayComponentPrimaryKeys(long pk);

	/**
	 * Returns all the testray components associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray components associated with the testray case
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent>
		getTestrayComponents(long pk);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent>
		getTestrayComponents(long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent>
		getTestrayComponents(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayComponent>
					orderByComparator);

	/**
	 * Returns the number of testray components associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray components associated with the testray case
	 */
	public int getTestrayComponentsSize(long pk);

	/**
	 * Returns <code>true</code> if the testray component is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPK the primary key of the testray component
	 * @return <code>true</code> if the testray component is associated with the testray case; <code>false</code> otherwise
	 */
	public boolean containsTestrayComponent(long pk, long testrayComponentPK);

	/**
	 * Returns <code>true</code> if the testray case has any testray components associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray components
	 * @return <code>true</code> if the testray case has any testray components associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayComponents(long pk);

	/**
	 * Adds an association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPK the primary key of the testray component
	 */
	public void addTestrayComponent(long pk, long testrayComponentPK);

	/**
	 * Adds an association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponent the testray component
	 */
	public void addTestrayComponent(
		long pk,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent);

	/**
	 * Adds an association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPKs the primary keys of the testray components
	 */
	public void addTestrayComponents(long pk, long[] testrayComponentPKs);

	/**
	 * Adds an association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponents the testray components
	 */
	public void addTestrayComponents(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent>
			testrayComponents);

	/**
	 * Clears all associations between the testray case and its testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray components from
	 */
	public void clearTestrayComponents(long pk);

	/**
	 * Removes the association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPK the primary key of the testray component
	 */
	public void removeTestrayComponent(long pk, long testrayComponentPK);

	/**
	 * Removes the association between the testray case and the testray component. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponent the testray component
	 */
	public void removeTestrayComponent(
		long pk,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent);

	/**
	 * Removes the association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPKs the primary keys of the testray components
	 */
	public void removeTestrayComponents(long pk, long[] testrayComponentPKs);

	/**
	 * Removes the association between the testray case and the testray components. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponents the testray components
	 */
	public void removeTestrayComponents(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent>
			testrayComponents);

	/**
	 * Sets the testray components associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponentPKs the primary keys of the testray components to be associated with the testray case
	 */
	public void setTestrayComponents(long pk, long[] testrayComponentPKs);

	/**
	 * Sets the testray components associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayComponents the testray components to be associated with the testray case
	 */
	public void setTestrayComponents(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent>
			testrayComponents);

	/**
	 * Returns the primaryKeys of testray requirements associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray requirements associated with the testray case
	 */
	public long[] getTestrayRequirementPrimaryKeys(long pk);

	/**
	 * Returns all the testray requirements associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray requirements associated with the testray case
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(long pk);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayRequirement>
					orderByComparator);

	/**
	 * Returns the number of testray requirements associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray requirements associated with the testray case
	 */
	public int getTestrayRequirementsSize(long pk);

	/**
	 * Returns <code>true</code> if the testray requirement is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPK the primary key of the testray requirement
	 * @return <code>true</code> if the testray requirement is associated with the testray case; <code>false</code> otherwise
	 */
	public boolean containsTestrayRequirement(
		long pk, long testrayRequirementPK);

	/**
	 * Returns <code>true</code> if the testray case has any testray requirements associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray requirements
	 * @return <code>true</code> if the testray case has any testray requirements associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayRequirements(long pk);

	/**
	 * Adds an association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPK the primary key of the testray requirement
	 */
	public void addTestrayRequirement(long pk, long testrayRequirementPK);

	/**
	 * Adds an association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirement the testray requirement
	 */
	public void addTestrayRequirement(
		long pk,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement);

	/**
	 * Adds an association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPKs the primary keys of the testray requirements
	 */
	public void addTestrayRequirements(long pk, long[] testrayRequirementPKs);

	/**
	 * Adds an association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirements the testray requirements
	 */
	public void addTestrayRequirements(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements);

	/**
	 * Clears all associations between the testray case and its testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray requirements from
	 */
	public void clearTestrayRequirements(long pk);

	/**
	 * Removes the association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPK the primary key of the testray requirement
	 */
	public void removeTestrayRequirement(long pk, long testrayRequirementPK);

	/**
	 * Removes the association between the testray case and the testray requirement. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirement the testray requirement
	 */
	public void removeTestrayRequirement(
		long pk,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement);

	/**
	 * Removes the association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPKs the primary keys of the testray requirements
	 */
	public void removeTestrayRequirements(
		long pk, long[] testrayRequirementPKs);

	/**
	 * Removes the association between the testray case and the testray requirements. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirements the testray requirements
	 */
	public void removeTestrayRequirements(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements);

	/**
	 * Sets the testray requirements associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirementPKs the primary keys of the testray requirements to be associated with the testray case
	 */
	public void setTestrayRequirements(long pk, long[] testrayRequirementPKs);

	/**
	 * Sets the testray requirements associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testrayRequirements the testray requirements to be associated with the testray case
	 */
	public void setTestrayRequirements(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements);

	/**
	 * Returns the primaryKeys of testray suites associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return long[] of the primaryKeys of testray suites associated with the testray case
	 */
	public long[] getTestraySuitePrimaryKeys(long pk);

	/**
	 * Returns all the testray suites associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the testray suites associated with the testray case
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySuite>
		getTestraySuites(long pk);

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
	public java.util.List<com.liferay.osb.testray.model.TestraySuite>
		getTestraySuites(long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.testray.model.TestraySuite>
		getTestraySuites(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySuite> orderByComparator);

	/**
	 * Returns the number of testray suites associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @return the number of testray suites associated with the testray case
	 */
	public int getTestraySuitesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray suite is associated with the testray case.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePK the primary key of the testray suite
	 * @return <code>true</code> if the testray suite is associated with the testray case; <code>false</code> otherwise
	 */
	public boolean containsTestraySuite(long pk, long testraySuitePK);

	/**
	 * Returns <code>true</code> if the testray case has any testray suites associated with it.
	 *
	 * @param pk the primary key of the testray case to check for associations with testray suites
	 * @return <code>true</code> if the testray case has any testray suites associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestraySuites(long pk);

	/**
	 * Adds an association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePK the primary key of the testray suite
	 */
	public void addTestraySuite(long pk, long testraySuitePK);

	/**
	 * Adds an association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuite the testray suite
	 */
	public void addTestraySuite(
		long pk, com.liferay.osb.testray.model.TestraySuite testraySuite);

	/**
	 * Adds an association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePKs the primary keys of the testray suites
	 */
	public void addTestraySuites(long pk, long[] testraySuitePKs);

	/**
	 * Adds an association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuites the testray suites
	 */
	public void addTestraySuites(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySuite>
			testraySuites);

	/**
	 * Clears all associations between the testray case and its testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case to clear the associated testray suites from
	 */
	public void clearTestraySuites(long pk);

	/**
	 * Removes the association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePK the primary key of the testray suite
	 */
	public void removeTestraySuite(long pk, long testraySuitePK);

	/**
	 * Removes the association between the testray case and the testray suite. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuite the testray suite
	 */
	public void removeTestraySuite(
		long pk, com.liferay.osb.testray.model.TestraySuite testraySuite);

	/**
	 * Removes the association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePKs the primary keys of the testray suites
	 */
	public void removeTestraySuites(long pk, long[] testraySuitePKs);

	/**
	 * Removes the association between the testray case and the testray suites. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuites the testray suites
	 */
	public void removeTestraySuites(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySuite>
			testraySuites);

	/**
	 * Sets the testray suites associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuitePKs the primary keys of the testray suites to be associated with the testray case
	 */
	public void setTestraySuites(long pk, long[] testraySuitePKs);

	/**
	 * Sets the testray suites associated with the testray case, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case
	 * @param testraySuites the testray suites to be associated with the testray case
	 */
	public void setTestraySuites(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySuite>
			testraySuites);

}