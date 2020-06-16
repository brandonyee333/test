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

import com.liferay.osb.testray.exception.NoSuchTestrayRunException;
import com.liferay.osb.testray.model.TestrayRun;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray run service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayRunPersistenceImpl
 * @see TestrayRunUtil
 * @generated
 */
@ProviderType
public interface TestrayRunPersistence extends BasePersistence<TestrayRun> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayRunUtil} to access the testray run persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or throws a {@link NoSuchTestrayRunException} if it could not be found.
	*
	* @param testrayBuildId the testray build ID
	* @param externalReferencePK the external reference pk
	* @param externalReferenceType the external reference type
	* @return the matching testray run
	* @throws NoSuchTestrayRunException if a matching testray run could not be found
	*/
	public TestrayRun findByT_E_E(long testrayBuildId,
		java.lang.String externalReferencePK, int externalReferenceType)
		throws NoSuchTestrayRunException;

	/**
	* Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayBuildId the testray build ID
	* @param externalReferencePK the external reference pk
	* @param externalReferenceType the external reference type
	* @return the matching testray run, or <code>null</code> if a matching testray run could not be found
	*/
	public TestrayRun fetchByT_E_E(long testrayBuildId,
		java.lang.String externalReferencePK, int externalReferenceType);

	/**
	* Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayBuildId the testray build ID
	* @param externalReferencePK the external reference pk
	* @param externalReferenceType the external reference type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray run, or <code>null</code> if a matching testray run could not be found
	*/
	public TestrayRun fetchByT_E_E(long testrayBuildId,
		java.lang.String externalReferencePK, int externalReferenceType,
		boolean retrieveFromCache);

	/**
	* Removes the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; from the database.
	*
	* @param testrayBuildId the testray build ID
	* @param externalReferencePK the external reference pk
	* @param externalReferenceType the external reference type
	* @return the testray run that was removed
	*/
	public TestrayRun removeByT_E_E(long testrayBuildId,
		java.lang.String externalReferencePK, int externalReferenceType)
		throws NoSuchTestrayRunException;

	/**
	* Returns the number of testray runs where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63;.
	*
	* @param testrayBuildId the testray build ID
	* @param externalReferencePK the external reference pk
	* @param externalReferenceType the external reference type
	* @return the number of matching testray runs
	*/
	public int countByT_E_E(long testrayBuildId,
		java.lang.String externalReferencePK, int externalReferenceType);

	/**
	* Caches the testray run in the entity cache if it is enabled.
	*
	* @param testrayRun the testray run
	*/
	public void cacheResult(TestrayRun testrayRun);

	/**
	* Caches the testray runs in the entity cache if it is enabled.
	*
	* @param testrayRuns the testray runs
	*/
	public void cacheResult(java.util.List<TestrayRun> testrayRuns);

	/**
	* Creates a new testray run with the primary key. Does not add the testray run to the database.
	*
	* @param testrayRunId the primary key for the new testray run
	* @return the new testray run
	*/
	public TestrayRun create(long testrayRunId);

	/**
	* Removes the testray run with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayRunId the primary key of the testray run
	* @return the testray run that was removed
	* @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	*/
	public TestrayRun remove(long testrayRunId)
		throws NoSuchTestrayRunException;

	public TestrayRun updateImpl(TestrayRun testrayRun);

	/**
	* Returns the testray run with the primary key or throws a {@link NoSuchTestrayRunException} if it could not be found.
	*
	* @param testrayRunId the primary key of the testray run
	* @return the testray run
	* @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	*/
	public TestrayRun findByPrimaryKey(long testrayRunId)
		throws NoSuchTestrayRunException;

	/**
	* Returns the testray run with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayRunId the primary key of the testray run
	* @return the testray run, or <code>null</code> if a testray run with the primary key could not be found
	*/
	public TestrayRun fetchByPrimaryKey(long testrayRunId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayRun> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray runs.
	*
	* @return the testray runs
	*/
	public java.util.List<TestrayRun> findAll();

	/**
	* Returns a range of all the testray runs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray runs
	* @param end the upper bound of the range of testray runs (not inclusive)
	* @return the range of testray runs
	*/
	public java.util.List<TestrayRun> findAll(int start, int end);

	/**
	* Returns an ordered range of all the testray runs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray runs
	* @param end the upper bound of the range of testray runs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray runs
	*/
	public java.util.List<TestrayRun> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRun> orderByComparator);

	/**
	* Returns an ordered range of all the testray runs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray runs
	* @param end the upper bound of the range of testray runs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray runs
	*/
	public java.util.List<TestrayRun> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRun> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray runs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray runs.
	*
	* @return the number of testray runs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}