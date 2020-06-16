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

import com.liferay.osb.testray.exception.NoSuchTestrayProductVersionException;
import com.liferay.osb.testray.model.TestrayProductVersion;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray product version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayProductVersionPersistenceImpl
 * @see TestrayProductVersionUtil
 * @generated
 */
@ProviderType
public interface TestrayProductVersionPersistence extends BasePersistence<TestrayProductVersion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayProductVersionUtil} to access the testray product version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the testray product version where testrayProjectId = &#63; and name = &#63; or throws a {@link NoSuchTestrayProductVersionException} if it could not be found.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the matching testray product version
	* @throws NoSuchTestrayProductVersionException if a matching testray product version could not be found
	*/
	public TestrayProductVersion findByT_N(long testrayProjectId,
		java.lang.String name) throws NoSuchTestrayProductVersionException;

	/**
	* Returns the testray product version where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the matching testray product version, or <code>null</code> if a matching testray product version could not be found
	*/
	public TestrayProductVersion fetchByT_N(long testrayProjectId,
		java.lang.String name);

	/**
	* Returns the testray product version where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray product version, or <code>null</code> if a matching testray product version could not be found
	*/
	public TestrayProductVersion fetchByT_N(long testrayProjectId,
		java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the testray product version where testrayProjectId = &#63; and name = &#63; from the database.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the testray product version that was removed
	*/
	public TestrayProductVersion removeByT_N(long testrayProjectId,
		java.lang.String name) throws NoSuchTestrayProductVersionException;

	/**
	* Returns the number of testray product versions where testrayProjectId = &#63; and name = &#63;.
	*
	* @param testrayProjectId the testray project ID
	* @param name the name
	* @return the number of matching testray product versions
	*/
	public int countByT_N(long testrayProjectId, java.lang.String name);

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
	* Returns the testray product version with the primary key or throws a {@link NoSuchTestrayProductVersionException} if it could not be found.
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
	public TestrayProductVersion fetchByPrimaryKey(long testrayProductVersionId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayProductVersion> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray product versions
	* @param end the upper bound of the range of testray product versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray product versions
	*/
	public java.util.List<TestrayProductVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayProductVersion> orderByComparator);

	/**
	* Returns an ordered range of all the testray product versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray product versions
	* @param end the upper bound of the range of testray product versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray product versions
	*/
	public java.util.List<TestrayProductVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayProductVersion> orderByComparator,
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