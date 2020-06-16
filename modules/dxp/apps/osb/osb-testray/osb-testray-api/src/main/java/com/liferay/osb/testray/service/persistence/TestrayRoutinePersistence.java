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

import com.liferay.osb.testray.exception.NoSuchTestrayRoutineException;
import com.liferay.osb.testray.model.TestrayRoutine;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray routine service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayRoutinePersistenceImpl
 * @see TestrayRoutineUtil
 * @generated
 */
@ProviderType
public interface TestrayRoutinePersistence extends BasePersistence<TestrayRoutine> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayRoutineUtil} to access the testray routine persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the testray routine in the entity cache if it is enabled.
	*
	* @param testrayRoutine the testray routine
	*/
	public void cacheResult(TestrayRoutine testrayRoutine);

	/**
	* Caches the testray routines in the entity cache if it is enabled.
	*
	* @param testrayRoutines the testray routines
	*/
	public void cacheResult(java.util.List<TestrayRoutine> testrayRoutines);

	/**
	* Creates a new testray routine with the primary key. Does not add the testray routine to the database.
	*
	* @param testrayRoutineId the primary key for the new testray routine
	* @return the new testray routine
	*/
	public TestrayRoutine create(long testrayRoutineId);

	/**
	* Removes the testray routine with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayRoutineId the primary key of the testray routine
	* @return the testray routine that was removed
	* @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	*/
	public TestrayRoutine remove(long testrayRoutineId)
		throws NoSuchTestrayRoutineException;

	public TestrayRoutine updateImpl(TestrayRoutine testrayRoutine);

	/**
	* Returns the testray routine with the primary key or throws a {@link NoSuchTestrayRoutineException} if it could not be found.
	*
	* @param testrayRoutineId the primary key of the testray routine
	* @return the testray routine
	* @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	*/
	public TestrayRoutine findByPrimaryKey(long testrayRoutineId)
		throws NoSuchTestrayRoutineException;

	/**
	* Returns the testray routine with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayRoutineId the primary key of the testray routine
	* @return the testray routine, or <code>null</code> if a testray routine with the primary key could not be found
	*/
	public TestrayRoutine fetchByPrimaryKey(long testrayRoutineId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayRoutine> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray routines.
	*
	* @return the testray routines
	*/
	public java.util.List<TestrayRoutine> findAll();

	/**
	* Returns a range of all the testray routines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRoutineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray routines
	* @param end the upper bound of the range of testray routines (not inclusive)
	* @return the range of testray routines
	*/
	public java.util.List<TestrayRoutine> findAll(int start, int end);

	/**
	* Returns an ordered range of all the testray routines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRoutineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray routines
	* @param end the upper bound of the range of testray routines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray routines
	*/
	public java.util.List<TestrayRoutine> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRoutine> orderByComparator);

	/**
	* Returns an ordered range of all the testray routines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRoutineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray routines
	* @param end the upper bound of the range of testray routines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray routines
	*/
	public java.util.List<TestrayRoutine> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRoutine> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray routines from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray routines.
	*
	* @return the number of testray routines
	*/
	public int countAll();
}