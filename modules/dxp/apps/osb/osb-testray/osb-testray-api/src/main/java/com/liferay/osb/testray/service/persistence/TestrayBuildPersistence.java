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

import com.liferay.osb.testray.exception.NoSuchTestrayBuildException;
import com.liferay.osb.testray.model.TestrayBuild;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray build service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayBuildUtil
 * @generated
 */
@ProviderType
public interface TestrayBuildPersistence extends BasePersistence<TestrayBuild> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayBuildUtil} to access the testray build persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayBuild> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray build where testrayRoutineId = &#63; and name = &#63; or throws a <code>NoSuchTestrayBuildException</code> if it could not be found.
	 *
	 * @param testrayRoutineId the testray routine ID
	 * @param name the name
	 * @return the matching testray build
	 * @throws NoSuchTestrayBuildException if a matching testray build could not be found
	 */
	public TestrayBuild findByTRI_N(long testrayRoutineId, String name)
		throws NoSuchTestrayBuildException;

	/**
	 * Returns the testray build where testrayRoutineId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayRoutineId the testray routine ID
	 * @param name the name
	 * @return the matching testray build, or <code>null</code> if a matching testray build could not be found
	 */
	public TestrayBuild fetchByTRI_N(long testrayRoutineId, String name);

	/**
	 * Returns the testray build where testrayRoutineId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayRoutineId the testray routine ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray build, or <code>null</code> if a matching testray build could not be found
	 */
	public TestrayBuild fetchByTRI_N(
		long testrayRoutineId, String name, boolean useFinderCache);

	/**
	 * Removes the testray build where testrayRoutineId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayRoutineId the testray routine ID
	 * @param name the name
	 * @return the testray build that was removed
	 */
	public TestrayBuild removeByTRI_N(long testrayRoutineId, String name)
		throws NoSuchTestrayBuildException;

	/**
	 * Returns the number of testray builds where testrayRoutineId = &#63; and name = &#63;.
	 *
	 * @param testrayRoutineId the testray routine ID
	 * @param name the name
	 * @return the number of matching testray builds
	 */
	public int countByTRI_N(long testrayRoutineId, String name);

	/**
	 * Caches the testray build in the entity cache if it is enabled.
	 *
	 * @param testrayBuild the testray build
	 */
	public void cacheResult(TestrayBuild testrayBuild);

	/**
	 * Caches the testray builds in the entity cache if it is enabled.
	 *
	 * @param testrayBuilds the testray builds
	 */
	public void cacheResult(java.util.List<TestrayBuild> testrayBuilds);

	/**
	 * Creates a new testray build with the primary key. Does not add the testray build to the database.
	 *
	 * @param testrayBuildId the primary key for the new testray build
	 * @return the new testray build
	 */
	public TestrayBuild create(long testrayBuildId);

	/**
	 * Removes the testray build with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayBuildId the primary key of the testray build
	 * @return the testray build that was removed
	 * @throws NoSuchTestrayBuildException if a testray build with the primary key could not be found
	 */
	public TestrayBuild remove(long testrayBuildId)
		throws NoSuchTestrayBuildException;

	public TestrayBuild updateImpl(TestrayBuild testrayBuild);

	/**
	 * Returns the testray build with the primary key or throws a <code>NoSuchTestrayBuildException</code> if it could not be found.
	 *
	 * @param testrayBuildId the primary key of the testray build
	 * @return the testray build
	 * @throws NoSuchTestrayBuildException if a testray build with the primary key could not be found
	 */
	public TestrayBuild findByPrimaryKey(long testrayBuildId)
		throws NoSuchTestrayBuildException;

	/**
	 * Returns the testray build with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayBuildId the primary key of the testray build
	 * @return the testray build, or <code>null</code> if a testray build with the primary key could not be found
	 */
	public TestrayBuild fetchByPrimaryKey(long testrayBuildId);

	/**
	 * Returns all the testray builds.
	 *
	 * @return the testray builds
	 */
	public java.util.List<TestrayBuild> findAll();

	/**
	 * Returns a range of all the testray builds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayBuildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray builds
	 * @param end the upper bound of the range of testray builds (not inclusive)
	 * @return the range of testray builds
	 */
	public java.util.List<TestrayBuild> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray builds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayBuildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray builds
	 * @param end the upper bound of the range of testray builds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray builds
	 */
	public java.util.List<TestrayBuild> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayBuild>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray builds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayBuildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray builds
	 * @param end the upper bound of the range of testray builds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray builds
	 */
	public java.util.List<TestrayBuild> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayBuild>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the testray builds from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray builds.
	 *
	 * @return the number of testray builds
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray cases associated with the testray build.
	 *
	 * @param pk the primary key of the testray build
	 * @return long[] of the primaryKeys of testray cases associated with the testray build
	 */
	public long[] getTestrayCasePrimaryKeys(long pk);

	/**
	 * Returns all the testray cases associated with the testray build.
	 *
	 * @param pk the primary key of the testray build
	 * @return the testray cases associated with the testray build
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk);

	/**
	 * Returns a range of all the testray cases associated with the testray build.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayBuildModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray build
	 * @param start the lower bound of the range of testray builds
	 * @param end the upper bound of the range of testray builds (not inclusive)
	 * @return the range of testray cases associated with the testray build
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray cases associated with the testray build.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayBuildModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray build
	 * @param start the lower bound of the range of testray builds
	 * @param end the upper bound of the range of testray builds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases associated with the testray build
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator);

	/**
	 * Returns the number of testray cases associated with the testray build.
	 *
	 * @param pk the primary key of the testray build
	 * @return the number of testray cases associated with the testray build
	 */
	public int getTestrayCasesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray build.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray build; <code>false</code> otherwise
	 */
	public boolean containsTestrayCase(long pk, long testrayCasePK);

	/**
	 * Returns <code>true</code> if the testray build has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray build to check for associations with testray cases
	 * @return <code>true</code> if the testray build has any testray cases associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCases(long pk);

	/**
	 * Adds an association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void addTestrayCase(long pk, long testrayCasePK);

	/**
	 * Adds an association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCase the testray case
	 */
	public void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Adds an association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void addTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Adds an association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCases the testray cases
	 */
	public void addTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Clears all associations between the testray build and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build to clear the associated testray cases from
	 */
	public void clearTestrayCases(long pk);

	/**
	 * Removes the association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void removeTestrayCase(long pk, long testrayCasePK);

	/**
	 * Removes the association between the testray build and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCase the testray case
	 */
	public void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Removes the association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void removeTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Removes the association between the testray build and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCases the testray cases
	 */
	public void removeTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Sets the testray cases associated with the testray build, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray build
	 */
	public void setTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Sets the testray cases associated with the testray build, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray build
	 * @param testrayCases the testray cases to be associated with the testray build
	 */
	public void setTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

}