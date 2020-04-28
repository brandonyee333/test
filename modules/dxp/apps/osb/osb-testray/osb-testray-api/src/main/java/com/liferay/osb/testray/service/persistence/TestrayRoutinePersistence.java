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

import com.liferay.osb.testray.exception.NoSuchTestrayRoutineException;
import com.liferay.osb.testray.model.TestrayRoutine;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray routine service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRoutineUtil
 * @generated
 */
@ProviderType
public interface TestrayRoutinePersistence
	extends BasePersistence<TestrayRoutine> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayRoutineUtil} to access the testray routine persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayRoutine> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Returns the testray routine with the primary key or throws a <code>NoSuchTestrayRoutineException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray routines
	 */
	public java.util.List<TestrayRoutine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRoutine>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray routines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray routines
	 */
	public java.util.List<TestrayRoutine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRoutine>
			orderByComparator,
		boolean useFinderCache);

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