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

import com.liferay.osb.testray.exception.NoSuchTestrayProductVersionException;
import com.liferay.osb.testray.model.TestrayProductVersion;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray product version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayProductVersionUtil
 * @generated
 */
@ProviderType
public interface TestrayProductVersionPersistence
	extends BasePersistence<TestrayProductVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayProductVersionUtil} to access the testray product version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayProductVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray product version where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayProductVersionException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray product version
	 * @throws NoSuchTestrayProductVersionException if a matching testray product version could not be found
	 */
	public TestrayProductVersion findByT_N(long testrayProjectId, String name)
		throws NoSuchTestrayProductVersionException;

	/**
	 * Returns the testray product version where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray product version, or <code>null</code> if a matching testray product version could not be found
	 */
	public TestrayProductVersion fetchByT_N(long testrayProjectId, String name);

	/**
	 * Returns the testray product version where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray product version, or <code>null</code> if a matching testray product version could not be found
	 */
	public TestrayProductVersion fetchByT_N(
		long testrayProjectId, String name, boolean retrieveFromCache);

	/**
	 * Removes the testray product version where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray product version that was removed
	 */
	public TestrayProductVersion removeByT_N(long testrayProjectId, String name)
		throws NoSuchTestrayProductVersionException;

	/**
	 * Returns the number of testray product versions where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray product versions
	 */
	public int countByT_N(long testrayProjectId, String name);

	/**
	 * Caches the testray product version in the entity cache if it is enabled.
	 *
	 * @param testrayProductVersion the testray product version
	 */
	public void cacheResult(TestrayProductVersion testrayProductVersion);

	/**
	 * Caches the testray product versions in the entity cache if it is enabled.
	 *
	 * @param testrayProductVersions the testray product versions
	 */
	public void cacheResult(
		java.util.List<TestrayProductVersion> testrayProductVersions);

	/**
	 * Creates a new testray product version with the primary key. Does not add the testray product version to the database.
	 *
	 * @param testrayProductVersionId the primary key for the new testray product version
	 * @return the new testray product version
	 */
	public TestrayProductVersion create(long testrayProductVersionId);

	/**
	 * Removes the testray product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version that was removed
	 * @throws NoSuchTestrayProductVersionException if a testray product version with the primary key could not be found
	 */
	public TestrayProductVersion remove(long testrayProductVersionId)
		throws NoSuchTestrayProductVersionException;

	public TestrayProductVersion updateImpl(
		TestrayProductVersion testrayProductVersion);

	/**
	 * Returns the testray product version with the primary key or throws a <code>NoSuchTestrayProductVersionException</code> if it could not be found.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version
	 * @throws NoSuchTestrayProductVersionException if a testray product version with the primary key could not be found
	 */
	public TestrayProductVersion findByPrimaryKey(long testrayProductVersionId)
		throws NoSuchTestrayProductVersionException;

	/**
	 * Returns the testray product version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version, or <code>null</code> if a testray product version with the primary key could not be found
	 */
	public TestrayProductVersion fetchByPrimaryKey(
		long testrayProductVersionId);

	/**
	 * Returns all the testray product versions.
	 *
	 * @return the testray product versions
	 */
	public java.util.List<TestrayProductVersion> findAll();

	/**
	 * Returns a range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayProductVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @return the range of testray product versions
	 */
	public java.util.List<TestrayProductVersion> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayProductVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray product versions
	 */
	public java.util.List<TestrayProductVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayProductVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayProductVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray product versions
	 */
	public java.util.List<TestrayProductVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayProductVersion>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray product versions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray product versions.
	 *
	 * @return the number of testray product versions
	 */
	public int countAll();

}